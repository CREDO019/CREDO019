package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class PlaybackStats {
    public static final PlaybackStats EMPTY = merge(new PlaybackStats[0]);
    public static final int PLAYBACK_STATE_ABANDONED = 15;
    public static final int PLAYBACK_STATE_BUFFERING = 6;
    static final int PLAYBACK_STATE_COUNT = 16;
    public static final int PLAYBACK_STATE_ENDED = 11;
    public static final int PLAYBACK_STATE_FAILED = 13;
    public static final int PLAYBACK_STATE_INTERRUPTED_BY_AD = 14;
    public static final int PLAYBACK_STATE_JOINING_BACKGROUND = 1;
    public static final int PLAYBACK_STATE_JOINING_FOREGROUND = 2;
    public static final int PLAYBACK_STATE_NOT_STARTED = 0;
    public static final int PLAYBACK_STATE_PAUSED = 4;
    public static final int PLAYBACK_STATE_PAUSED_BUFFERING = 7;
    public static final int PLAYBACK_STATE_PLAYING = 3;
    public static final int PLAYBACK_STATE_SEEKING = 5;
    public static final int PLAYBACK_STATE_STOPPED = 12;
    public static final int PLAYBACK_STATE_SUPPRESSED = 9;
    public static final int PLAYBACK_STATE_SUPPRESSED_BUFFERING = 10;
    public final int abandonedBeforeReadyCount;
    public final int adPlaybackCount;
    public final List<EventTimeAndFormat> audioFormatHistory;
    public final int backgroundJoiningCount;
    public final int endedCount;
    public final int fatalErrorCount;
    public final List<EventTimeAndException> fatalErrorHistory;
    public final int fatalErrorPlaybackCount;
    public final long firstReportedTimeMs;
    public final int foregroundPlaybackCount;
    public final int initialAudioFormatBitrateCount;
    public final int initialVideoFormatBitrateCount;
    public final int initialVideoFormatHeightCount;
    public final long maxRebufferTimeMs;
    public final List<long[]> mediaTimeHistory;
    public final int nonFatalErrorCount;
    public final List<EventTimeAndException> nonFatalErrorHistory;
    public final int playbackCount;
    private final long[] playbackStateDurationsMs;
    public final List<EventTimeAndPlaybackState> playbackStateHistory;
    public final long totalAudioFormatBitrateTimeProduct;
    public final long totalAudioFormatTimeMs;
    public final long totalAudioUnderruns;
    public final long totalBandwidthBytes;
    public final long totalBandwidthTimeMs;
    public final long totalDroppedFrames;
    public final long totalInitialAudioFormatBitrate;
    public final long totalInitialVideoFormatBitrate;
    public final int totalInitialVideoFormatHeight;
    public final int totalPauseBufferCount;
    public final int totalPauseCount;
    public final int totalRebufferCount;
    public final int totalSeekCount;
    public final long totalValidJoinTimeMs;
    public final long totalVideoFormatBitrateTimeMs;
    public final long totalVideoFormatBitrateTimeProduct;
    public final long totalVideoFormatHeightTimeMs;
    public final long totalVideoFormatHeightTimeProduct;
    public final int validJoinTimeCount;
    public final List<EventTimeAndFormat> videoFormatHistory;

    /* loaded from: classes2.dex */
    public static final class EventTimeAndPlaybackState {
        public final AnalyticsListener.EventTime eventTime;
        public final int playbackState;

        public EventTimeAndPlaybackState(AnalyticsListener.EventTime eventTime, int r2) {
            this.eventTime = eventTime;
            this.playbackState = r2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EventTimeAndPlaybackState eventTimeAndPlaybackState = (EventTimeAndPlaybackState) obj;
            if (this.playbackState != eventTimeAndPlaybackState.playbackState) {
                return false;
            }
            return this.eventTime.equals(eventTimeAndPlaybackState.eventTime);
        }

        public int hashCode() {
            return (this.eventTime.hashCode() * 31) + this.playbackState;
        }
    }

    /* loaded from: classes2.dex */
    public static final class EventTimeAndFormat {
        public final AnalyticsListener.EventTime eventTime;
        public final Format format;

        public EventTimeAndFormat(AnalyticsListener.EventTime eventTime, Format format) {
            this.eventTime = eventTime;
            this.format = format;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EventTimeAndFormat eventTimeAndFormat = (EventTimeAndFormat) obj;
            if (this.eventTime.equals(eventTimeAndFormat.eventTime)) {
                Format format = this.format;
                Format format2 = eventTimeAndFormat.format;
                return format != null ? format.equals(format2) : format2 == null;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.eventTime.hashCode() * 31;
            Format format = this.format;
            return hashCode + (format != null ? format.hashCode() : 0);
        }
    }

    /* loaded from: classes2.dex */
    public static final class EventTimeAndException {
        public final AnalyticsListener.EventTime eventTime;
        public final Exception exception;

        public EventTimeAndException(AnalyticsListener.EventTime eventTime, Exception exc) {
            this.eventTime = eventTime;
            this.exception = exc;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EventTimeAndException eventTimeAndException = (EventTimeAndException) obj;
            if (this.eventTime.equals(eventTimeAndException.eventTime)) {
                return this.exception.equals(eventTimeAndException.exception);
            }
            return false;
        }

        public int hashCode() {
            return (this.eventTime.hashCode() * 31) + this.exception.hashCode();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.analytics.PlaybackStats merge(com.google.android.exoplayer2.analytics.PlaybackStats... r66) {
        /*
            Method dump skipped, instructions count: 439
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.analytics.PlaybackStats.merge(com.google.android.exoplayer2.analytics.PlaybackStats[]):com.google.android.exoplayer2.analytics.PlaybackStats");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlaybackStats(int r4, long[] jArr, List<EventTimeAndPlaybackState> list, List<long[]> list2, long j, int r10, int r11, int r12, int r13, long j2, int r16, int r17, int r18, int r19, int r20, long j3, int r23, List<EventTimeAndFormat> list3, List<EventTimeAndFormat> list4, long j4, long j5, long j6, long j7, long j8, long j9, int r38, int r39, int r40, long j10, int r43, long j11, long j12, long j13, long j14, long j15, int r54, int r55, int r56, List<EventTimeAndException> list5, List<EventTimeAndException> list6) {
        this.playbackCount = r4;
        this.playbackStateDurationsMs = jArr;
        this.playbackStateHistory = Collections.unmodifiableList(list);
        this.mediaTimeHistory = Collections.unmodifiableList(list2);
        this.firstReportedTimeMs = j;
        this.foregroundPlaybackCount = r10;
        this.abandonedBeforeReadyCount = r11;
        this.endedCount = r12;
        this.backgroundJoiningCount = r13;
        this.totalValidJoinTimeMs = j2;
        this.validJoinTimeCount = r16;
        this.totalPauseCount = r17;
        this.totalPauseBufferCount = r18;
        this.totalSeekCount = r19;
        this.totalRebufferCount = r20;
        this.maxRebufferTimeMs = j3;
        this.adPlaybackCount = r23;
        this.videoFormatHistory = Collections.unmodifiableList(list3);
        this.audioFormatHistory = Collections.unmodifiableList(list4);
        this.totalVideoFormatHeightTimeMs = j4;
        this.totalVideoFormatHeightTimeProduct = j5;
        this.totalVideoFormatBitrateTimeMs = j6;
        this.totalVideoFormatBitrateTimeProduct = j7;
        this.totalAudioFormatTimeMs = j8;
        this.totalAudioFormatBitrateTimeProduct = j9;
        this.initialVideoFormatHeightCount = r38;
        this.initialVideoFormatBitrateCount = r39;
        this.totalInitialVideoFormatHeight = r40;
        this.totalInitialVideoFormatBitrate = j10;
        this.initialAudioFormatBitrateCount = r43;
        this.totalInitialAudioFormatBitrate = j11;
        this.totalBandwidthTimeMs = j12;
        this.totalBandwidthBytes = j13;
        this.totalDroppedFrames = j14;
        this.totalAudioUnderruns = j15;
        this.fatalErrorPlaybackCount = r54;
        this.fatalErrorCount = r55;
        this.nonFatalErrorCount = r56;
        this.fatalErrorHistory = Collections.unmodifiableList(list5);
        this.nonFatalErrorHistory = Collections.unmodifiableList(list6);
    }

    public long getPlaybackStateDurationMs(int r4) {
        return this.playbackStateDurationsMs[r4];
    }

    public int getPlaybackStateAtTime(long j) {
        int r1 = 0;
        for (EventTimeAndPlaybackState eventTimeAndPlaybackState : this.playbackStateHistory) {
            if (eventTimeAndPlaybackState.eventTime.realtimeMs > j) {
                break;
            }
            r1 = eventTimeAndPlaybackState.playbackState;
        }
        return r1;
    }

    public long getMediaTimeMsAtRealtimeMs(long j) {
        long j2;
        if (this.mediaTimeHistory.isEmpty()) {
            return C1856C.TIME_UNSET;
        }
        int r1 = 0;
        while (r1 < this.mediaTimeHistory.size() && this.mediaTimeHistory.get(r1)[0] <= j) {
            r1++;
        }
        if (r1 == 0) {
            return this.mediaTimeHistory.get(0)[1];
        }
        if (r1 == this.mediaTimeHistory.size()) {
            List<long[]> list = this.mediaTimeHistory;
            return list.get(list.size() - 1)[1];
        }
        int r4 = r1 - 1;
        long j3 = this.mediaTimeHistory.get(r4)[0];
        long j4 = this.mediaTimeHistory.get(r4)[1];
        long j5 = this.mediaTimeHistory.get(r1)[0];
        long j6 = this.mediaTimeHistory.get(r1)[1];
        if (j5 - j3 == 0) {
            return j4;
        }
        return j4 + (((float) (j6 - j4)) * (((float) (j - j3)) / ((float) j2)));
    }

    public long getMeanJoinTimeMs() {
        int r0 = this.validJoinTimeCount;
        return r0 == 0 ? C1856C.TIME_UNSET : this.totalValidJoinTimeMs / r0;
    }

    public long getTotalJoinTimeMs() {
        return getPlaybackStateDurationMs(2);
    }

    public long getTotalPlayTimeMs() {
        return getPlaybackStateDurationMs(3);
    }

    public long getMeanPlayTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C1856C.TIME_UNSET : getTotalPlayTimeMs() / this.foregroundPlaybackCount;
    }

    public long getTotalPausedTimeMs() {
        return getPlaybackStateDurationMs(4) + getPlaybackStateDurationMs(7);
    }

    public long getMeanPausedTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C1856C.TIME_UNSET : getTotalPausedTimeMs() / this.foregroundPlaybackCount;
    }

    public long getTotalRebufferTimeMs() {
        return getPlaybackStateDurationMs(6);
    }

    public long getMeanRebufferTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C1856C.TIME_UNSET : getTotalRebufferTimeMs() / this.foregroundPlaybackCount;
    }

    public long getMeanSingleRebufferTimeMs() {
        return this.totalRebufferCount == 0 ? C1856C.TIME_UNSET : (getPlaybackStateDurationMs(6) + getPlaybackStateDurationMs(7)) / this.totalRebufferCount;
    }

    public long getTotalSeekTimeMs() {
        return getPlaybackStateDurationMs(5);
    }

    public long getMeanSeekTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C1856C.TIME_UNSET : getTotalSeekTimeMs() / this.foregroundPlaybackCount;
    }

    public long getMeanSingleSeekTimeMs() {
        return this.totalSeekCount == 0 ? C1856C.TIME_UNSET : getTotalSeekTimeMs() / this.totalSeekCount;
    }

    public long getTotalWaitTimeMs() {
        return getPlaybackStateDurationMs(2) + getPlaybackStateDurationMs(6) + getPlaybackStateDurationMs(5);
    }

    public long getMeanWaitTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C1856C.TIME_UNSET : getTotalWaitTimeMs() / this.foregroundPlaybackCount;
    }

    public long getTotalPlayAndWaitTimeMs() {
        return getTotalPlayTimeMs() + getTotalWaitTimeMs();
    }

    public long getMeanPlayAndWaitTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C1856C.TIME_UNSET : getTotalPlayAndWaitTimeMs() / this.foregroundPlaybackCount;
    }

    public long getTotalElapsedTimeMs() {
        long j = 0;
        for (int r2 = 0; r2 < 16; r2++) {
            j += this.playbackStateDurationsMs[r2];
        }
        return j;
    }

    public long getMeanElapsedTimeMs() {
        return this.playbackCount == 0 ? C1856C.TIME_UNSET : getTotalElapsedTimeMs() / this.playbackCount;
    }

    public float getAbandonedBeforeReadyRatio() {
        int r0 = this.abandonedBeforeReadyCount;
        int r1 = this.playbackCount;
        int r2 = this.foregroundPlaybackCount;
        int r02 = r0 - (r1 - r2);
        if (r2 == 0) {
            return 0.0f;
        }
        return r02 / r2;
    }

    public float getEndedRatio() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.endedCount / r0;
    }

    public float getMeanPauseCount() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.totalPauseCount / r0;
    }

    public float getMeanPauseBufferCount() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.totalPauseBufferCount / r0;
    }

    public float getMeanSeekCount() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.totalSeekCount / r0;
    }

    public float getMeanRebufferCount() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.totalRebufferCount / r0;
    }

    public float getWaitTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalWaitTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public float getJoinTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalJoinTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public float getRebufferTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalRebufferTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public float getSeekTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalSeekTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public float getRebufferRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (this.totalRebufferCount * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getMeanTimeBetweenRebuffers() {
        return 1.0f / getRebufferRate();
    }

    public int getMeanInitialVideoFormatHeight() {
        int r0 = this.initialVideoFormatHeightCount;
        if (r0 == 0) {
            return -1;
        }
        return this.totalInitialVideoFormatHeight / r0;
    }

    public int getMeanInitialVideoFormatBitrate() {
        int r0 = this.initialVideoFormatBitrateCount;
        if (r0 == 0) {
            return -1;
        }
        return (int) (this.totalInitialVideoFormatBitrate / r0);
    }

    public int getMeanInitialAudioFormatBitrate() {
        int r0 = this.initialAudioFormatBitrateCount;
        if (r0 == 0) {
            return -1;
        }
        return (int) (this.totalInitialAudioFormatBitrate / r0);
    }

    public int getMeanVideoFormatHeight() {
        long j = this.totalVideoFormatHeightTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) (this.totalVideoFormatHeightTimeProduct / j);
    }

    public int getMeanVideoFormatBitrate() {
        long j = this.totalVideoFormatBitrateTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) (this.totalVideoFormatBitrateTimeProduct / j);
    }

    public int getMeanAudioFormatBitrate() {
        long j = this.totalAudioFormatTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) (this.totalAudioFormatBitrateTimeProduct / j);
    }

    public int getMeanBandwidth() {
        long j = this.totalBandwidthTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) ((this.totalBandwidthBytes * RtspMediaSource.DEFAULT_TIMEOUT_MS) / j);
    }

    public float getDroppedFramesRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.totalDroppedFrames) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getAudioUnderrunRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.totalAudioUnderruns) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getFatalErrorRatio() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.fatalErrorPlaybackCount / r0;
    }

    public float getFatalErrorRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (this.fatalErrorCount * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getMeanTimeBetweenFatalErrors() {
        return 1.0f / getFatalErrorRate();
    }

    public float getMeanNonFatalErrorCount() {
        int r0 = this.foregroundPlaybackCount;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.nonFatalErrorCount / r0;
    }

    public float getNonFatalErrorRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (this.nonFatalErrorCount * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getMeanTimeBetweenNonFatalErrors() {
        return 1.0f / getNonFatalErrorRate();
    }
}
