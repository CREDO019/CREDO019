package com.google.android.exoplayer2.trackselection;

import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public class AdaptiveTrackSelection extends BaseTrackSelection {
    public static final float DEFAULT_BANDWIDTH_FRACTION = 0.7f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MAX_HEIGHT_TO_DISCARD = 719;
    public static final int DEFAULT_MAX_WIDTH_TO_DISCARD = 1279;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    private static final long MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 1000;
    private static final String TAG = "AdaptiveTrackSelection";
    private final ImmutableList<AdaptationCheckpoint> adaptationCheckpoints;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private MediaChunk lastBufferEvaluationMediaChunk;
    private long lastBufferEvaluationMs;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxHeightToDiscard;
    private final int maxWidthToDiscard;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    protected boolean canSelectFormat(Format format, int r3, long j) {
        return ((long) r3) <= j;
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public Object getSelectionData() {
        return null;
    }

    /* loaded from: classes2.dex */
    public static class Factory implements ExoTrackSelection.Factory {
        private final float bandwidthFraction;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxHeightToDiscard;
        private final int maxWidthToDiscard;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;

        public Factory() {
            this(10000, 25000, 25000, 0.7f);
        }

        public Factory(int r10, int r11, int r12, float f) {
            this(r10, r11, r12, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, f, 0.75f, Clock.DEFAULT);
        }

        public Factory(int r10, int r11, int r12, int r13, int r14, float f) {
            this(r10, r11, r12, r13, r14, f, 0.75f, Clock.DEFAULT);
        }

        public Factory(int r10, int r11, int r12, float f, float f2, Clock clock) {
            this(r10, r11, r12, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, f, f2, clock);
        }

        public Factory(int r1, int r2, int r3, int r4, int r5, float f, float f2, Clock clock) {
            this.minDurationForQualityIncreaseMs = r1;
            this.maxDurationForQualityDecreaseMs = r2;
            this.minDurationToRetainAfterDiscardMs = r3;
            this.maxWidthToDiscard = r4;
            this.maxHeightToDiscard = r5;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
            this.clock = clock;
        }

        @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection.Factory
        public final ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            ExoTrackSelection createAdaptiveTrackSelection;
            ImmutableList adaptationCheckpoints = AdaptiveTrackSelection.getAdaptationCheckpoints(definitionArr);
            ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
            for (int r4 = 0; r4 < definitionArr.length; r4++) {
                ExoTrackSelection.Definition definition = definitionArr[r4];
                if (definition != null && definition.tracks.length != 0) {
                    if (definition.tracks.length == 1) {
                        createAdaptiveTrackSelection = new FixedTrackSelection(definition.group, definition.tracks[0], definition.type);
                    } else {
                        createAdaptiveTrackSelection = createAdaptiveTrackSelection(definition.group, definition.tracks, definition.type, bandwidthMeter, (ImmutableList) adaptationCheckpoints.get(r4));
                    }
                    exoTrackSelectionArr[r4] = createAdaptiveTrackSelection;
                }
            }
            return exoTrackSelectionArr;
        }

        protected AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup trackGroup, int[] r21, int r22, BandwidthMeter bandwidthMeter, ImmutableList<AdaptationCheckpoint> immutableList) {
            return new AdaptiveTrackSelection(trackGroup, r21, r22, bandwidthMeter, this.minDurationForQualityIncreaseMs, this.maxDurationForQualityDecreaseMs, this.minDurationToRetainAfterDiscardMs, this.maxWidthToDiscard, this.maxHeightToDiscard, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, immutableList, this.clock);
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] r19, BandwidthMeter bandwidthMeter) {
        this(trackGroup, r19, 0, bandwidthMeter, WorkRequest.MIN_BACKOFF_MILLIS, 25000L, 25000L, DEFAULT_MAX_WIDTH_TO_DISCARD, DEFAULT_MAX_HEIGHT_TO_DISCARD, 0.7f, 0.75f, ImmutableList.m409of(), Clock.DEFAULT);
    }

    protected AdaptiveTrackSelection(TrackGroup trackGroup, int[] r9, int r10, BandwidthMeter bandwidthMeter, long j, long j2, long j3, int r18, int r19, float f, float f2, List<AdaptationCheckpoint> list, Clock clock) {
        super(trackGroup, r9, r10);
        BandwidthMeter bandwidthMeter2;
        long j4;
        if (j3 < j) {
            Log.m1132w(TAG, "Adjusting minDurationToRetainAfterDiscardMs to be at least minDurationForQualityIncreaseMs");
            bandwidthMeter2 = bandwidthMeter;
            j4 = j;
        } else {
            bandwidthMeter2 = bandwidthMeter;
            j4 = j3;
        }
        this.bandwidthMeter = bandwidthMeter2;
        this.minDurationForQualityIncreaseUs = j * 1000;
        this.maxDurationForQualityDecreaseUs = j2 * 1000;
        this.minDurationToRetainAfterDiscardUs = j4 * 1000;
        this.maxWidthToDiscard = r18;
        this.maxHeightToDiscard = r19;
        this.bandwidthFraction = f;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
        this.adaptationCheckpoints = ImmutableList.copyOf((Collection) list);
        this.clock = clock;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void enable() {
        this.lastBufferEvaluationMs = C1856C.TIME_UNSET;
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void disable() {
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        long nextChunkDurationUs = getNextChunkDurationUs(mediaChunkIteratorArr, list);
        int r13 = this.reason;
        if (r13 == 0) {
            this.reason = 1;
            this.selectedIndex = determineIdealSelectedIndex(elapsedRealtime, nextChunkDurationUs);
            return;
        }
        int r2 = this.selectedIndex;
        int indexOf = list.isEmpty() ? -1 : indexOf(((MediaChunk) Iterables.getLast(list)).trackFormat);
        if (indexOf != -1) {
            r13 = ((MediaChunk) Iterables.getLast(list)).trackSelectionReason;
            r2 = indexOf;
        }
        int determineIdealSelectedIndex = determineIdealSelectedIndex(elapsedRealtime, nextChunkDurationUs);
        if (!isBlacklisted(r2, elapsedRealtime)) {
            Format format = getFormat(r2);
            Format format2 = getFormat(determineIdealSelectedIndex);
            long minDurationForQualityIncreaseUs = minDurationForQualityIncreaseUs(j3, nextChunkDurationUs);
            if ((format2.bitrate > format.bitrate && j2 < minDurationForQualityIncreaseUs) || (format2.bitrate < format.bitrate && j2 >= this.maxDurationForQualityDecreaseUs)) {
                determineIdealSelectedIndex = r2;
            }
        }
        if (determineIdealSelectedIndex != r2) {
            r13 = 3;
        }
        this.reason = r13;
        this.selectedIndex = determineIdealSelectedIndex;
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        if (!shouldEvaluateQueueSize(elapsedRealtime, list)) {
            return list.size();
        }
        this.lastBufferEvaluationMs = elapsedRealtime;
        this.lastBufferEvaluationMediaChunk = list.isEmpty() ? null : (MediaChunk) Iterables.getLast(list);
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(list.get(size - 1).startTimeUs - j, this.playbackSpeed);
        long minDurationToRetainAfterDiscardUs = getMinDurationToRetainAfterDiscardUs();
        if (playoutDurationForMediaDuration < minDurationToRetainAfterDiscardUs) {
            return size;
        }
        Format format = getFormat(determineIdealSelectedIndex(elapsedRealtime, getLastChunkDurationUs(list)));
        for (int r3 = 0; r3 < size; r3++) {
            MediaChunk mediaChunk = list.get(r3);
            Format format2 = mediaChunk.trackFormat;
            if (Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j, this.playbackSpeed) >= minDurationToRetainAfterDiscardUs && format2.bitrate < format.bitrate && format2.height != -1 && format2.height <= this.maxHeightToDiscard && format2.width != -1 && format2.width <= this.maxWidthToDiscard && format2.height < format.height) {
                return r3;
            }
        }
        return size;
    }

    protected boolean shouldEvaluateQueueSize(long j, List<? extends MediaChunk> list) {
        long j2 = this.lastBufferEvaluationMs;
        return j2 == C1856C.TIME_UNSET || j - j2 >= 1000 || !(list.isEmpty() || ((MediaChunk) Iterables.getLast(list)).equals(this.lastBufferEvaluationMediaChunk));
    }

    protected long getMinDurationToRetainAfterDiscardUs() {
        return this.minDurationToRetainAfterDiscardUs;
    }

    private int determineIdealSelectedIndex(long j, long j2) {
        long allocatedBandwidth = getAllocatedBandwidth(j2);
        int r1 = 0;
        for (int r0 = 0; r0 < this.length; r0++) {
            if (j == Long.MIN_VALUE || !isBlacklisted(r0, j)) {
                Format format = getFormat(r0);
                if (canSelectFormat(format, format.bitrate, allocatedBandwidth)) {
                    return r0;
                }
                r1 = r0;
            }
        }
        return r1;
    }

    private long minDurationForQualityIncreaseUs(long j, long j2) {
        if (j == C1856C.TIME_UNSET) {
            return this.minDurationForQualityIncreaseUs;
        }
        if (j2 != C1856C.TIME_UNSET) {
            j -= j2;
        }
        return Math.min(((float) j) * this.bufferedFractionToLiveEdgeForQualityIncrease, this.minDurationForQualityIncreaseUs);
    }

    private long getNextChunkDurationUs(MediaChunkIterator[] mediaChunkIteratorArr, List<? extends MediaChunk> list) {
        int r0 = this.selectedIndex;
        if (r0 < mediaChunkIteratorArr.length && mediaChunkIteratorArr[r0].next()) {
            MediaChunkIterator mediaChunkIterator = mediaChunkIteratorArr[this.selectedIndex];
            return mediaChunkIterator.getChunkEndTimeUs() - mediaChunkIterator.getChunkStartTimeUs();
        }
        for (MediaChunkIterator mediaChunkIterator2 : mediaChunkIteratorArr) {
            if (mediaChunkIterator2.next()) {
                return mediaChunkIterator2.getChunkEndTimeUs() - mediaChunkIterator2.getChunkStartTimeUs();
            }
        }
        return getLastChunkDurationUs(list);
    }

    private long getLastChunkDurationUs(List<? extends MediaChunk> list) {
        if (list.isEmpty()) {
            return C1856C.TIME_UNSET;
        }
        MediaChunk mediaChunk = (MediaChunk) Iterables.getLast(list);
        return (mediaChunk.startTimeUs == C1856C.TIME_UNSET || mediaChunk.endTimeUs == C1856C.TIME_UNSET) ? C1856C.TIME_UNSET : mediaChunk.endTimeUs - mediaChunk.startTimeUs;
    }

    private long getAllocatedBandwidth(long j) {
        long totalAllocatableBandwidth = getTotalAllocatableBandwidth(j);
        if (this.adaptationCheckpoints.isEmpty()) {
            return totalAllocatableBandwidth;
        }
        int r1 = 1;
        while (r1 < this.adaptationCheckpoints.size() - 1 && this.adaptationCheckpoints.get(r1).totalBandwidth < totalAllocatableBandwidth) {
            r1++;
        }
        AdaptationCheckpoint adaptationCheckpoint = this.adaptationCheckpoints.get(r1 - 1);
        AdaptationCheckpoint adaptationCheckpoint2 = this.adaptationCheckpoints.get(r1);
        return adaptationCheckpoint.allocatedBandwidth + ((((float) (totalAllocatableBandwidth - adaptationCheckpoint.totalBandwidth)) / ((float) (adaptationCheckpoint2.totalBandwidth - adaptationCheckpoint.totalBandwidth))) * ((float) (adaptationCheckpoint2.allocatedBandwidth - adaptationCheckpoint.allocatedBandwidth)));
    }

    private long getTotalAllocatableBandwidth(long j) {
        long timeToFirstByteEstimateUs;
        long bitrateEstimate = ((float) this.bandwidthMeter.getBitrateEstimate()) * this.bandwidthFraction;
        if (this.bandwidthMeter.getTimeToFirstByteEstimateUs() == C1856C.TIME_UNSET || j == C1856C.TIME_UNSET) {
            return ((float) bitrateEstimate) / this.playbackSpeed;
        }
        float f = (float) j;
        return (((float) bitrateEstimate) * Math.max((f / this.playbackSpeed) - ((float) timeToFirstByteEstimateUs), 0.0f)) / f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImmutableList<ImmutableList<AdaptationCheckpoint>> getAdaptationCheckpoints(ExoTrackSelection.Definition[] definitionArr) {
        ArrayList arrayList = new ArrayList();
        for (int r2 = 0; r2 < definitionArr.length; r2++) {
            if (definitionArr[r2] != null && definitionArr[r2].tracks.length > 1) {
                ImmutableList.Builder builder = ImmutableList.builder();
                builder.add((ImmutableList.Builder) new AdaptationCheckpoint(0L, 0L));
                arrayList.add(builder);
            } else {
                arrayList.add(null);
            }
        }
        long[][] sortedTrackBitrates = getSortedTrackBitrates(definitionArr);
        int[] r3 = new int[sortedTrackBitrates.length];
        long[] jArr = new long[sortedTrackBitrates.length];
        for (int r8 = 0; r8 < sortedTrackBitrates.length; r8++) {
            jArr[r8] = sortedTrackBitrates[r8].length == 0 ? 0L : sortedTrackBitrates[r8][0];
        }
        addCheckpoint(arrayList, jArr);
        ImmutableList<Integer> switchOrder = getSwitchOrder(sortedTrackBitrates);
        for (int r5 = 0; r5 < switchOrder.size(); r5++) {
            int intValue = switchOrder.get(r5).intValue();
            int r9 = r3[intValue] + 1;
            r3[intValue] = r9;
            jArr[intValue] = sortedTrackBitrates[intValue][r9];
            addCheckpoint(arrayList, jArr);
        }
        for (int r22 = 0; r22 < definitionArr.length; r22++) {
            if (arrayList.get(r22) != null) {
                jArr[r22] = jArr[r22] * 2;
            }
        }
        addCheckpoint(arrayList, jArr);
        ImmutableList.Builder builder2 = ImmutableList.builder();
        for (int r1 = 0; r1 < arrayList.size(); r1++) {
            ImmutableList.Builder builder3 = (ImmutableList.Builder) arrayList.get(r1);
            builder2.add((ImmutableList.Builder) (builder3 == null ? ImmutableList.m409of() : builder3.build()));
        }
        return builder2.build();
    }

    private static long[][] getSortedTrackBitrates(ExoTrackSelection.Definition[] definitionArr) {
        long[][] jArr = new long[definitionArr.length];
        for (int r2 = 0; r2 < definitionArr.length; r2++) {
            ExoTrackSelection.Definition definition = definitionArr[r2];
            if (definition == null) {
                jArr[r2] = new long[0];
            } else {
                jArr[r2] = new long[definition.tracks.length];
                for (int r4 = 0; r4 < definition.tracks.length; r4++) {
                    jArr[r2][r4] = definition.group.getFormat(definition.tracks[r4]).bitrate;
                }
                Arrays.sort(jArr[r2]);
            }
        }
        return jArr;
    }

    private static ImmutableList<Integer> getSwitchOrder(long[][] jArr) {
        Multimap build = MultimapBuilder.treeKeys().arrayListValues().build();
        for (int r3 = 0; r3 < jArr.length; r3++) {
            if (jArr[r3].length > 1) {
                int length = jArr[r3].length;
                double[] dArr = new double[length];
                int r6 = 0;
                while (true) {
                    double d = 0.0d;
                    if (r6 >= jArr[r3].length) {
                        break;
                    }
                    if (jArr[r3][r6] != -1) {
                        d = Math.log(jArr[r3][r6]);
                    }
                    dArr[r6] = d;
                    r6++;
                }
                int r4 = length - 1;
                double d2 = dArr[r4] - dArr[0];
                int r10 = 0;
                while (r10 < r4) {
                    double d3 = dArr[r10];
                    r10++;
                    build.put(Double.valueOf(d2 == 0.0d ? 1.0d : (((d3 + dArr[r10]) * 0.5d) - dArr[0]) / d2), Integer.valueOf(r3));
                }
            }
        }
        return ImmutableList.copyOf(build.values());
    }

    private static void addCheckpoint(List<ImmutableList.Builder<AdaptationCheckpoint>> list, long[] jArr) {
        long j = 0;
        for (long j2 : jArr) {
            j += j2;
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            ImmutableList.Builder<AdaptationCheckpoint> builder = list.get(r0);
            if (builder != null) {
                builder.add((ImmutableList.Builder<AdaptationCheckpoint>) new AdaptationCheckpoint(j, jArr[r0]));
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class AdaptationCheckpoint {
        public final long allocatedBandwidth;
        public final long totalBandwidth;

        public AdaptationCheckpoint(long j, long j2) {
            this.totalBandwidth = j;
            this.allocatedBandwidth = j2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AdaptationCheckpoint) {
                AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) obj;
                return this.totalBandwidth == adaptationCheckpoint.totalBandwidth && this.allocatedBandwidth == adaptationCheckpoint.allocatedBandwidth;
            }
            return false;
        }

        public int hashCode() {
            return (((int) this.totalBandwidth) * 31) + ((int) this.allocatedBandwidth);
        }
    }
}
