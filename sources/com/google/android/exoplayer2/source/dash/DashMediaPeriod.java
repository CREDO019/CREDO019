package com.google.android.exoplayer2.source.dash;

import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Descriptor;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: classes2.dex */
final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<DashChunkSource>>, ChunkSampleStream.ReleaseCallback<DashChunkSource> {
    private static final Pattern CEA608_SERVICE_DESCRIPTOR_REGEX = Pattern.compile("CC([1-4])=(.+)");
    private static final Pattern CEA708_SERVICE_DESCRIPTOR_REGEX = Pattern.compile("([1-4])=lang:(\\w+)(,.+)?");
    private final Allocator allocator;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private MediaPeriod.Callback callback;
    private final DashChunkSource.Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final long elapsedRealtimeOffsetMs;
    private List<EventStream> eventStreams;

    /* renamed from: id */
    final int f241id;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private final PlayerId playerId;
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;
    private final TransferListener transferListener;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams = newSampleStreamArray(0);
    private EventSampleStream[] eventSampleStreams = new EventSampleStream[0];
    private final IdentityHashMap<ChunkSampleStream<DashChunkSource>, PlayerEmsgHandler.PlayerTrackEmsgHandler> trackEmsgHandlerBySampleStream = new IdentityHashMap<>();

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return C1856C.TIME_UNSET;
    }

    public DashMediaPeriod(int r9, DashManifest dashManifest, BaseUrlExclusionList baseUrlExclusionList, int r12, DashChunkSource.Factory factory, TransferListener transferListener, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, long j, LoaderErrorThrower loaderErrorThrower, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback, PlayerId playerId) {
        this.f241id = r9;
        this.manifest = dashManifest;
        this.baseUrlExclusionList = baseUrlExclusionList;
        this.periodIndex = r12;
        this.chunkSourceFactory = factory;
        this.transferListener = transferListener;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.elapsedRealtimeOffsetMs = j;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.allocator = allocator;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.playerId = playerId;
        this.playerEmsgHandler = new PlayerEmsgHandler(dashManifest, playerEmsgCallback, allocator);
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
        Period period = dashManifest.getPeriod(r12);
        this.eventStreams = period.eventStreams;
        Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups = buildTrackGroups(drmSessionManager, period.adaptationSets, this.eventStreams);
        this.trackGroups = (TrackGroupArray) buildTrackGroups.first;
        this.trackGroupInfos = (TrackGroupInfo[]) buildTrackGroups.second;
    }

    public void updateManifest(DashManifest dashManifest, int r11) {
        EventSampleStream[] eventSampleStreamArr;
        this.manifest = dashManifest;
        this.periodIndex = r11;
        this.playerEmsgHandler.updateManifest(dashManifest);
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
        if (chunkSampleStreamArr != null) {
            for (ChunkSampleStream<DashChunkSource> chunkSampleStream : chunkSampleStreamArr) {
                chunkSampleStream.getChunkSource().updateManifest(dashManifest, r11);
            }
            this.callback.onContinueLoadingRequested(this);
        }
        this.eventStreams = dashManifest.getPeriod(r11).eventStreams;
        for (EventSampleStream eventSampleStream : this.eventSampleStreams) {
            Iterator<EventStream> it = this.eventStreams.iterator();
            while (true) {
                if (it.hasNext()) {
                    EventStream next = it.next();
                    if (next.m1157id().equals(eventSampleStream.eventStreamId())) {
                        boolean z = true;
                        eventSampleStream.updateEventStream(next, (dashManifest.dynamic && r11 == dashManifest.getPeriodCount() - 1) ? false : false);
                    }
                }
            }
        }
    }

    public void release() {
        this.playerEmsgHandler.release();
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.release(this);
        }
        this.callback = null;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSampleStream.ReleaseCallback
    public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        PlayerEmsgHandler.PlayerTrackEmsgHandler remove = this.trackEmsgHandlerBySampleStream.remove(chunkSampleStream);
        if (remove != null) {
            remove.release();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        List<AdaptationSet> list2 = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection exoTrackSelection : list) {
            TrackGroupInfo trackGroupInfo = this.trackGroupInfos[this.trackGroups.indexOf(exoTrackSelection.getTrackGroup())];
            if (trackGroupInfo.trackGroupCategory == 0) {
                int[] r3 = trackGroupInfo.adaptationSetIndices;
                int length = exoTrackSelection.length();
                int[] r5 = new int[length];
                for (int r7 = 0; r7 < exoTrackSelection.length(); r7++) {
                    r5[r7] = exoTrackSelection.getIndexInTrackGroup(r7);
                }
                Arrays.sort(r5);
                int size = list2.get(r3[0]).representations.size();
                int r72 = 0;
                int r8 = 0;
                for (int r6 = 0; r6 < length; r6++) {
                    int r9 = r5[r6];
                    while (true) {
                        int r10 = r8 + size;
                        if (r9 >= r10) {
                            r72++;
                            size = list2.get(r3[r72]).representations.size();
                            r8 = r10;
                        }
                    }
                    arrayList.add(new StreamKey(this.periodIndex, r3[r72], r9 - r8));
                }
            }
        }
        return arrayList;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int[] streamIndexToTrackGroupIndex = getStreamIndexToTrackGroupIndex(exoTrackSelectionArr);
        releaseDisabledStreams(exoTrackSelectionArr, zArr, sampleStreamArr);
        releaseOrphanEmbeddedStreams(exoTrackSelectionArr, sampleStreamArr, streamIndexToTrackGroupIndex);
        selectNewStreams(exoTrackSelectionArr, sampleStreamArr, zArr2, j, streamIndexToTrackGroupIndex);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (SampleStream sampleStream : sampleStreamArr) {
            if (sampleStream instanceof ChunkSampleStream) {
                arrayList.add((ChunkSampleStream) sampleStream);
            } else if (sampleStream instanceof EventSampleStream) {
                arrayList2.add((EventSampleStream) sampleStream);
            }
        }
        ChunkSampleStream<DashChunkSource>[] newSampleStreamArray = newSampleStreamArray(arrayList.size());
        this.sampleStreams = newSampleStreamArray;
        arrayList.toArray(newSampleStreamArray);
        EventSampleStream[] eventSampleStreamArr = new EventSampleStream[arrayList2.size()];
        this.eventSampleStreams = eventSampleStreamArr;
        arrayList2.toArray(eventSampleStreamArr);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
        return j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.discardBuffer(j, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        return this.compositeSequenceableLoader.continueLoading(j);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.seekToUs(j);
        }
        for (EventSampleStream eventSampleStream : this.eventSampleStreams) {
            eventSampleStream.seekToUs(j);
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr;
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            if (chunkSampleStream.primaryTrackType == 2) {
                return chunkSampleStream.getAdjustedSeekPositionUs(j, seekParameters);
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }

    private int[] getStreamIndexToTrackGroupIndex(ExoTrackSelection[] exoTrackSelectionArr) {
        int[] r0 = new int[exoTrackSelectionArr.length];
        for (int r1 = 0; r1 < exoTrackSelectionArr.length; r1++) {
            if (exoTrackSelectionArr[r1] != null) {
                r0[r1] = this.trackGroups.indexOf(exoTrackSelectionArr[r1].getTrackGroup());
            } else {
                r0[r1] = -1;
            }
        }
        return r0;
    }

    private void releaseDisabledStreams(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr) {
        for (int r0 = 0; r0 < exoTrackSelectionArr.length; r0++) {
            if (exoTrackSelectionArr[r0] == null || !zArr[r0]) {
                if (sampleStreamArr[r0] instanceof ChunkSampleStream) {
                    ((ChunkSampleStream) sampleStreamArr[r0]).release(this);
                } else if (sampleStreamArr[r0] instanceof ChunkSampleStream.EmbeddedSampleStream) {
                    ((ChunkSampleStream.EmbeddedSampleStream) sampleStreamArr[r0]).release();
                }
                sampleStreamArr[r0] = null;
            }
        }
    }

    private void releaseOrphanEmbeddedStreams(ExoTrackSelection[] exoTrackSelectionArr, SampleStream[] sampleStreamArr, int[] r7) {
        boolean z;
        for (int r1 = 0; r1 < exoTrackSelectionArr.length; r1++) {
            if ((sampleStreamArr[r1] instanceof EmptySampleStream) || (sampleStreamArr[r1] instanceof ChunkSampleStream.EmbeddedSampleStream)) {
                int primaryStreamIndex = getPrimaryStreamIndex(r1, r7);
                if (primaryStreamIndex == -1) {
                    z = sampleStreamArr[r1] instanceof EmptySampleStream;
                } else {
                    z = (sampleStreamArr[r1] instanceof ChunkSampleStream.EmbeddedSampleStream) && ((ChunkSampleStream.EmbeddedSampleStream) sampleStreamArr[r1]).parent == sampleStreamArr[primaryStreamIndex];
                }
                if (!z) {
                    if (sampleStreamArr[r1] instanceof ChunkSampleStream.EmbeddedSampleStream) {
                        ((ChunkSampleStream.EmbeddedSampleStream) sampleStreamArr[r1]).release();
                    }
                    sampleStreamArr[r1] = null;
                }
            }
        }
    }

    private void selectNewStreams(ExoTrackSelection[] exoTrackSelectionArr, SampleStream[] sampleStreamArr, boolean[] zArr, long j, int[] r12) {
        for (int r1 = 0; r1 < exoTrackSelectionArr.length; r1++) {
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[r1];
            if (exoTrackSelection != null) {
                if (sampleStreamArr[r1] == null) {
                    zArr[r1] = true;
                    TrackGroupInfo trackGroupInfo = this.trackGroupInfos[r12[r1]];
                    if (trackGroupInfo.trackGroupCategory == 0) {
                        sampleStreamArr[r1] = buildSampleStream(trackGroupInfo, exoTrackSelection, j);
                    } else if (trackGroupInfo.trackGroupCategory == 2) {
                        sampleStreamArr[r1] = new EventSampleStream(this.eventStreams.get(trackGroupInfo.eventStreamGroupIndex), exoTrackSelection.getTrackGroup().getFormat(0), this.manifest.dynamic);
                    }
                } else if (sampleStreamArr[r1] instanceof ChunkSampleStream) {
                    ((DashChunkSource) ((ChunkSampleStream) sampleStreamArr[r1]).getChunkSource()).updateTrackSelection(exoTrackSelection);
                }
            }
        }
        for (int r0 = 0; r0 < exoTrackSelectionArr.length; r0++) {
            if (sampleStreamArr[r0] == null && exoTrackSelectionArr[r0] != null) {
                TrackGroupInfo trackGroupInfo2 = this.trackGroupInfos[r12[r0]];
                if (trackGroupInfo2.trackGroupCategory == 1) {
                    int primaryStreamIndex = getPrimaryStreamIndex(r0, r12);
                    if (primaryStreamIndex == -1) {
                        sampleStreamArr[r0] = new EmptySampleStream();
                    } else {
                        sampleStreamArr[r0] = ((ChunkSampleStream) sampleStreamArr[primaryStreamIndex]).selectEmbeddedTrack(j, trackGroupInfo2.trackType);
                    }
                }
            }
        }
    }

    private int getPrimaryStreamIndex(int r5, int[] r6) {
        int r52 = r6[r5];
        if (r52 == -1) {
            return -1;
        }
        int r53 = this.trackGroupInfos[r52].primaryTrackGroupIndex;
        for (int r1 = 0; r1 < r6.length; r1++) {
            int r2 = r6[r1];
            if (r2 == r53 && this.trackGroupInfos[r2].trackGroupCategory == 0) {
                return r1;
            }
        }
        return -1;
    }

    private static Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups(DrmSessionManager drmSessionManager, List<AdaptationSet> list, List<EventStream> list2) {
        int[][] groupedAdaptationSetIndices = getGroupedAdaptationSetIndices(list);
        int length = groupedAdaptationSetIndices.length;
        boolean[] zArr = new boolean[length];
        Format[][] formatArr = new Format[length];
        int identifyEmbeddedTracks = identifyEmbeddedTracks(length, list, groupedAdaptationSetIndices, zArr, formatArr) + length + list2.size();
        TrackGroup[] trackGroupArr = new TrackGroup[identifyEmbeddedTracks];
        TrackGroupInfo[] trackGroupInfoArr = new TrackGroupInfo[identifyEmbeddedTracks];
        buildManifestEventTrackGroupInfos(list2, trackGroupArr, trackGroupInfoArr, buildPrimaryAndEmbeddedTrackGroupInfos(drmSessionManager, list, groupedAdaptationSetIndices, length, zArr, formatArr, trackGroupArr, trackGroupInfoArr));
        return Pair.create(new TrackGroupArray(trackGroupArr), trackGroupInfoArr);
    }

    private static int[][] getGroupedAdaptationSetIndices(List<AdaptationSet> list) {
        int r7;
        Descriptor findAdaptationSetSwitchingProperty;
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        ArrayList arrayList = new ArrayList(size);
        SparseArray sparseArray = new SparseArray(size);
        for (int r5 = 0; r5 < size; r5++) {
            sparseIntArray.put(list.get(r5).f242id, r5);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Integer.valueOf(r5));
            arrayList.add(arrayList2);
            sparseArray.put(r5, arrayList2);
        }
        for (int r52 = 0; r52 < size; r52++) {
            AdaptationSet adaptationSet = list.get(r52);
            Descriptor findTrickPlayProperty = findTrickPlayProperty(adaptationSet.essentialProperties);
            if (findTrickPlayProperty == null) {
                findTrickPlayProperty = findTrickPlayProperty(adaptationSet.supplementalProperties);
            }
            if (findTrickPlayProperty == null || (r7 = sparseIntArray.get(Integer.parseInt(findTrickPlayProperty.value), -1)) == -1) {
                r7 = r52;
            }
            if (r7 == r52 && (findAdaptationSetSwitchingProperty = findAdaptationSetSwitchingProperty(adaptationSet.supplementalProperties)) != null) {
                for (String str : Util.split(findAdaptationSetSwitchingProperty.value, ",")) {
                    int r11 = sparseIntArray.get(Integer.parseInt(str), -1);
                    if (r11 != -1) {
                        r7 = Math.min(r7, r11);
                    }
                }
            }
            if (r7 != r52) {
                List list2 = (List) sparseArray.get(r52);
                List list3 = (List) sparseArray.get(r7);
                list3.addAll(list2);
                sparseArray.put(r52, list3);
                arrayList.remove(list2);
            }
        }
        int size2 = arrayList.size();
        int[][] r0 = new int[size2];
        for (int r4 = 0; r4 < size2; r4++) {
            r0[r4] = Ints.toArray((Collection) arrayList.get(r4));
            Arrays.sort(r0[r4]);
        }
        return r0;
    }

    private static int identifyEmbeddedTracks(int r3, List<AdaptationSet> list, int[][] r5, boolean[] zArr, Format[][] formatArr) {
        int r1 = 0;
        for (int r0 = 0; r0 < r3; r0++) {
            if (hasEventMessageTrack(list, r5[r0])) {
                zArr[r0] = true;
                r1++;
            }
            formatArr[r0] = getClosedCaptionTrackFormats(list, r5[r0]);
            if (formatArr[r0].length != 0) {
                r1++;
            }
        }
        return r1;
    }

    private static int buildPrimaryAndEmbeddedTrackGroupInfos(DrmSessionManager drmSessionManager, List<AdaptationSet> list, int[][] r18, int r19, boolean[] zArr, Format[][] formatArr, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr) {
        int r12;
        int r13;
        int r3 = 0;
        int r4 = 0;
        while (r3 < r19) {
            int[] r5 = r18[r3];
            ArrayList arrayList = new ArrayList();
            for (int r9 : r5) {
                arrayList.addAll(list.get(r9).representations);
            }
            int size = arrayList.size();
            Format[] formatArr2 = new Format[size];
            for (int r92 = 0; r92 < size; r92++) {
                Format format = ((Representation) arrayList.get(r92)).format;
                formatArr2[r92] = format.copyWithCryptoType(drmSessionManager.getCryptoType(format));
            }
            AdaptationSet adaptationSet = list.get(r5[0]);
            String num = adaptationSet.f242id != -1 ? Integer.toString(adaptationSet.f242id) : "unset:" + r3;
            int r10 = r4 + 1;
            if (zArr[r3]) {
                r12 = r10 + 1;
            } else {
                r12 = r10;
                r10 = -1;
            }
            if (formatArr[r3].length != 0) {
                r13 = r12 + 1;
            } else {
                r13 = r12;
                r12 = -1;
            }
            trackGroupArr[r4] = new TrackGroup(num, formatArr2);
            trackGroupInfoArr[r4] = TrackGroupInfo.primaryTrack(adaptationSet.type, r5, r4, r10, r12);
            if (r10 != -1) {
                String str = num + ":emsg";
                trackGroupArr[r10] = new TrackGroup(str, new Format.Builder().setId(str).setSampleMimeType(MimeTypes.APPLICATION_EMSG).build());
                trackGroupInfoArr[r10] = TrackGroupInfo.embeddedEmsgTrack(r5, r4);
            }
            if (r12 != -1) {
                trackGroupArr[r12] = new TrackGroup(num + ":cc", formatArr[r3]);
                trackGroupInfoArr[r12] = TrackGroupInfo.embeddedClosedCaptionTrack(r5, r4);
            }
            r3++;
            r4 = r13;
        }
        return r4;
    }

    private static void buildManifestEventTrackGroupInfos(List<EventStream> list, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr, int r9) {
        int r1 = 0;
        while (r1 < list.size()) {
            EventStream eventStream = list.get(r1);
            Format build = new Format.Builder().setId(eventStream.m1157id()).setSampleMimeType(MimeTypes.APPLICATION_EMSG).build();
            trackGroupArr[r9] = new TrackGroup(eventStream.m1157id() + ParameterizedMessage.ERROR_MSG_SEPARATOR + r1, build);
            trackGroupInfoArr[r9] = TrackGroupInfo.mpdEventTrack(r1);
            r1++;
            r9++;
        }
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo trackGroupInfo, ExoTrackSelection exoTrackSelection, long j) {
        TrackGroup trackGroup;
        int r6;
        TrackGroup trackGroup2;
        int r5;
        boolean z = trackGroupInfo.embeddedEventMessageTrackGroupIndex != -1;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = null;
        if (z) {
            trackGroup = this.trackGroups.get(trackGroupInfo.embeddedEventMessageTrackGroupIndex);
            r6 = 1;
        } else {
            trackGroup = null;
            r6 = 0;
        }
        boolean z2 = trackGroupInfo.embeddedClosedCaptionTrackGroupIndex != -1;
        if (z2) {
            trackGroup2 = this.trackGroups.get(trackGroupInfo.embeddedClosedCaptionTrackGroupIndex);
            r6 += trackGroup2.length;
        } else {
            trackGroup2 = null;
        }
        Format[] formatArr = new Format[r6];
        int[] r62 = new int[r6];
        if (z) {
            formatArr[0] = trackGroup.getFormat(0);
            r62[0] = 5;
            r5 = 1;
        } else {
            r5 = 0;
        }
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (int r4 = 0; r4 < trackGroup2.length; r4++) {
                formatArr[r5] = trackGroup2.getFormat(r4);
                r62[r5] = 3;
                arrayList.add(formatArr[r5]);
                r5++;
            }
        }
        if (this.manifest.dynamic && z) {
            playerTrackEmsgHandler = this.playerEmsgHandler.newPlayerTrackEmsgHandler();
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = playerTrackEmsgHandler;
        ChunkSampleStream<DashChunkSource> chunkSampleStream = new ChunkSampleStream<>(trackGroupInfo.trackType, r62, formatArr, this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.baseUrlExclusionList, this.periodIndex, trackGroupInfo.adaptationSetIndices, exoTrackSelection, trackGroupInfo.trackType, this.elapsedRealtimeOffsetMs, z, arrayList, playerTrackEmsgHandler2, this.transferListener, this.playerId), this, this.allocator, j, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.mediaSourceEventDispatcher);
        synchronized (this) {
            this.trackEmsgHandlerBySampleStream.put(chunkSampleStream, playerTrackEmsgHandler2);
        }
        return chunkSampleStream;
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> list) {
        return findDescriptor(list, "urn:mpeg:dash:adaptation-set-switching:2016");
    }

    private static Descriptor findTrickPlayProperty(List<Descriptor> list) {
        return findDescriptor(list, "http://dashif.org/guidelines/trickmode");
    }

    private static Descriptor findDescriptor(List<Descriptor> list, String str) {
        for (int r0 = 0; r0 < list.size(); r0++) {
            Descriptor descriptor = list.get(r0);
            if (str.equals(descriptor.schemeIdUri)) {
                return descriptor;
            }
        }
        return null;
    }

    private static boolean hasEventMessageTrack(List<AdaptationSet> list, int[] r7) {
        for (int r3 : r7) {
            List<Representation> list2 = list.get(r3).representations;
            for (int r4 = 0; r4 < list2.size(); r4++) {
                if (!list2.get(r4).inbandEventStreams.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Format[] getClosedCaptionTrackFormats(List<AdaptationSet> list, int[] r10) {
        for (int r3 : r10) {
            AdaptationSet adaptationSet = list.get(r3);
            List<Descriptor> list2 = list.get(r3).accessibilityDescriptors;
            for (int r5 = 0; r5 < list2.size(); r5++) {
                Descriptor descriptor = list2.get(r5);
                if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri)) {
                    return parseClosedCaptionDescriptor(descriptor, CEA608_SERVICE_DESCRIPTOR_REGEX, new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA608).setId(adaptationSet.f242id + ":cea608").build());
                } else if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri)) {
                    return parseClosedCaptionDescriptor(descriptor, CEA708_SERVICE_DESCRIPTOR_REGEX, new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA708).setId(adaptationSet.f242id + ":cea708").build());
                }
            }
        }
        return new Format[0];
    }

    private static Format[] parseClosedCaptionDescriptor(Descriptor descriptor, Pattern pattern, Format format) {
        String str = descriptor.value;
        if (str == null) {
            return new Format[]{format};
        }
        String[] split = Util.split(str, ";");
        Format[] formatArr = new Format[split.length];
        for (int r3 = 0; r3 < split.length; r3++) {
            Matcher matcher = pattern.matcher(split[r3]);
            if (!matcher.matches()) {
                return new Format[]{format};
            }
            int parseInt = Integer.parseInt(matcher.group(1));
            Format.Builder buildUpon = format.buildUpon();
            formatArr[r3] = buildUpon.setId(format.f212id + ParameterizedMessage.ERROR_MSG_SEPARATOR + parseInt).setAccessibilityChannel(parseInt).setLanguage(matcher.group(2)).build();
        }
        return formatArr;
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int r0) {
        return new ChunkSampleStream[r0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class TrackGroupInfo {
        private static final int CATEGORY_EMBEDDED = 1;
        private static final int CATEGORY_MANIFEST_EVENTS = 2;
        private static final int CATEGORY_PRIMARY = 0;
        public final int[] adaptationSetIndices;
        public final int embeddedClosedCaptionTrackGroupIndex;
        public final int embeddedEventMessageTrackGroupIndex;
        public final int eventStreamGroupIndex;
        public final int primaryTrackGroupIndex;
        public final int trackGroupCategory;
        public final int trackType;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface TrackGroupCategory {
        }

        public static TrackGroupInfo primaryTrack(int r9, int[] r10, int r11, int r12, int r13) {
            return new TrackGroupInfo(r9, 0, r10, r11, r12, r13, -1);
        }

        public static TrackGroupInfo embeddedEmsgTrack(int[] r9, int r10) {
            return new TrackGroupInfo(5, 1, r9, r10, -1, -1, -1);
        }

        public static TrackGroupInfo embeddedClosedCaptionTrack(int[] r9, int r10) {
            return new TrackGroupInfo(3, 1, r9, r10, -1, -1, -1);
        }

        public static TrackGroupInfo mpdEventTrack(int r9) {
            return new TrackGroupInfo(5, 2, new int[0], -1, -1, -1, r9);
        }

        private TrackGroupInfo(int r1, int r2, int[] r3, int r4, int r5, int r6, int r7) {
            this.trackType = r1;
            this.adaptationSetIndices = r3;
            this.trackGroupCategory = r2;
            this.primaryTrackGroupIndex = r4;
            this.embeddedEventMessageTrackGroupIndex = r5;
            this.embeddedClosedCaptionTrackGroupIndex = r6;
            this.eventStreamGroupIndex = r7;
        }
    }
}
