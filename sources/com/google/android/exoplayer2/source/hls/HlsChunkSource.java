package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
class HlsChunkSource {
    public static final int CHUNK_PUBLICATION_STATE_PRELOAD = 0;
    public static final int CHUNK_PUBLICATION_STATE_PUBLISHED = 1;
    public static final int CHUNK_PUBLICATION_STATE_REMOVED = 2;
    private static final int KEY_CACHE_SIZE = 4;
    private final DataSource encryptionDataSource;
    private Uri expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final PlayerId playerId;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private boolean seenExpectedPlaylistError;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private ExoTrackSelection trackSelection;
    private final FullSegmentEncryptionKeyCache keyCache = new FullSegmentEncryptionKeyCache(4);
    private byte[] scratchSpace = Util.EMPTY_BYTE_ARRAY;
    private long liveEdgeInPeriodTimeUs = C1856C.TIME_UNSET;

    /* loaded from: classes2.dex */
    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public Uri playlistUrl;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlistUrl = null;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, Uri[] uriArr, Format[] formatArr, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider, List<Format> list, PlayerId playerId) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.playlistUrls = uriArr;
        this.playlistFormats = formatArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider;
        this.muxedCaptionFormats = list;
        this.playerId = playerId;
        DataSource createDataSource = hlsDataSourceFactory.createDataSource(1);
        this.mediaDataSource = createDataSource;
        if (transferListener != null) {
            createDataSource.addTransferListener(transferListener);
        }
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        ArrayList arrayList = new ArrayList();
        for (int r2 = 0; r2 < uriArr.length; r2++) {
            if ((formatArr[r2].roleFlags & 16384) == 0) {
                arrayList.add(Integer.valueOf(r2));
            }
        }
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, Ints.toArray(arrayList));
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        Uri uri = this.expectedPlaylistUrl;
        if (uri == null || !this.seenExpectedPlaylistError) {
            return;
        }
        this.playlistTracker.maybeThrowPlaylistRefreshError(uri);
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void setTrackSelection(ExoTrackSelection exoTrackSelection) {
        this.trackSelection = exoTrackSelection;
    }

    public ExoTrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z) {
        this.isTimestampMaster = z;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        int selectedIndex = this.trackSelection.getSelectedIndex();
        Uri[] uriArr = this.playlistUrls;
        HlsMediaPlaylist playlistSnapshot = (selectedIndex >= uriArr.length || selectedIndex == -1) ? null : this.playlistTracker.getPlaylistSnapshot(uriArr[this.trackSelection.getSelectedIndexInTrackGroup()], true);
        if (playlistSnapshot == null || playlistSnapshot.segments.isEmpty() || !playlistSnapshot.hasIndependentSegments) {
            return j;
        }
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        long j2 = j - initialStartTimeUs;
        int binarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) playlistSnapshot.segments, Long.valueOf(j2), true, true);
        long j3 = playlistSnapshot.segments.get(binarySearchFloor).relativeStartTimeUs;
        return seekParameters.resolveSeekPositionUs(j2, j3, binarySearchFloor != playlistSnapshot.segments.size() - 1 ? playlistSnapshot.segments.get(binarySearchFloor + 1).relativeStartTimeUs : j3) + initialStartTimeUs;
    }

    public int getChunkPublicationState(HlsMediaChunk hlsMediaChunk) {
        List<HlsMediaPlaylist.Part> list;
        if (hlsMediaChunk.partIndex == -1) {
            return 1;
        }
        HlsMediaPlaylist hlsMediaPlaylist = (HlsMediaPlaylist) Assertions.checkNotNull(this.playlistTracker.getPlaylistSnapshot(this.playlistUrls[this.trackGroup.indexOf(hlsMediaChunk.trackFormat)], false));
        int r2 = (int) (hlsMediaChunk.chunkIndex - hlsMediaPlaylist.mediaSequence);
        if (r2 < 0) {
            return 1;
        }
        if (r2 < hlsMediaPlaylist.segments.size()) {
            list = hlsMediaPlaylist.segments.get(r2).parts;
        } else {
            list = hlsMediaPlaylist.trailingParts;
        }
        if (hlsMediaChunk.partIndex >= list.size()) {
            return 2;
        }
        HlsMediaPlaylist.Part part = list.get(hlsMediaChunk.partIndex);
        if (part.isPreload) {
            return 0;
        }
        return Util.areEqual(Uri.parse(UriUtil.resolve(hlsMediaPlaylist.baseUri, part.url)), hlsMediaChunk.dataSpec.uri) ? 1 : 2;
    }

    public void getNextChunk(long j, long j2, List<HlsMediaChunk> list, boolean z, HlsChunkHolder hlsChunkHolder) {
        HlsMediaPlaylist hlsMediaPlaylist;
        long j3;
        Uri uri;
        int r3;
        HlsMediaChunk hlsMediaChunk = list.isEmpty() ? null : (HlsMediaChunk) Iterables.getLast(list);
        int indexOf = hlsMediaChunk == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        long j4 = j2 - j;
        long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j);
        if (hlsMediaChunk != null && !this.independentSegments) {
            long durationUs = hlsMediaChunk.getDurationUs();
            j4 = Math.max(0L, j4 - durationUs);
            if (resolveTimeToLiveEdgeUs != C1856C.TIME_UNSET) {
                resolveTimeToLiveEdgeUs = Math.max(0L, resolveTimeToLiveEdgeUs - durationUs);
            }
        }
        this.trackSelection.updateSelectedTrack(j, j4, resolveTimeToLiveEdgeUs, list, createMediaChunkIterators(hlsMediaChunk, j2));
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z2 = indexOf != selectedIndexInTrackGroup;
        Uri uri2 = this.playlistUrls[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(uri2)) {
            hlsChunkHolder.playlistUrl = uri2;
            this.seenExpectedPlaylistError &= uri2.equals(this.expectedPlaylistUrl);
            this.expectedPlaylistUrl = uri2;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri2, true);
        Assertions.checkNotNull(playlistSnapshot);
        this.independentSegments = playlistSnapshot.hasIndependentSegments;
        updateLiveEdgeTimeUs(playlistSnapshot);
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(hlsMediaChunk, z2, playlistSnapshot, initialStartTimeUs, j2);
        long longValue = ((Long) nextMediaSequenceAndPartIndex.first).longValue();
        int intValue = ((Integer) nextMediaSequenceAndPartIndex.second).intValue();
        if (longValue >= playlistSnapshot.mediaSequence || hlsMediaChunk == null || !z2) {
            hlsMediaPlaylist = playlistSnapshot;
            j3 = initialStartTimeUs;
            uri = uri2;
            r3 = selectedIndexInTrackGroup;
        } else {
            Uri uri3 = this.playlistUrls[indexOf];
            HlsMediaPlaylist playlistSnapshot2 = this.playlistTracker.getPlaylistSnapshot(uri3, true);
            Assertions.checkNotNull(playlistSnapshot2);
            j3 = playlistSnapshot2.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            Pair<Long, Integer> nextMediaSequenceAndPartIndex2 = getNextMediaSequenceAndPartIndex(hlsMediaChunk, false, playlistSnapshot2, j3, j2);
            longValue = ((Long) nextMediaSequenceAndPartIndex2.first).longValue();
            intValue = ((Integer) nextMediaSequenceAndPartIndex2.second).intValue();
            r3 = indexOf;
            uri = uri3;
            hlsMediaPlaylist = playlistSnapshot2;
        }
        if (longValue < hlsMediaPlaylist.mediaSequence) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        SegmentBaseHolder nextSegmentHolder = getNextSegmentHolder(hlsMediaPlaylist, longValue, intValue);
        if (nextSegmentHolder == null) {
            if (!hlsMediaPlaylist.hasEndTag) {
                hlsChunkHolder.playlistUrl = uri;
                this.seenExpectedPlaylistError &= uri.equals(this.expectedPlaylistUrl);
                this.expectedPlaylistUrl = uri;
                return;
            } else if (z || hlsMediaPlaylist.segments.isEmpty()) {
                hlsChunkHolder.endOfStream = true;
                return;
            } else {
                nextSegmentHolder = new SegmentBaseHolder((HlsMediaPlaylist.SegmentBase) Iterables.getLast(hlsMediaPlaylist.segments), (hlsMediaPlaylist.mediaSequence + hlsMediaPlaylist.segments.size()) - 1, -1);
            }
        }
        this.seenExpectedPlaylistError = false;
        this.expectedPlaylistUrl = null;
        Uri fullEncryptionKeyUri = getFullEncryptionKeyUri(hlsMediaPlaylist, nextSegmentHolder.segmentBase.initializationSegment);
        hlsChunkHolder.chunk = maybeCreateEncryptionChunkFor(fullEncryptionKeyUri, r3);
        if (hlsChunkHolder.chunk != null) {
            return;
        }
        Uri fullEncryptionKeyUri2 = getFullEncryptionKeyUri(hlsMediaPlaylist, nextSegmentHolder.segmentBase);
        hlsChunkHolder.chunk = maybeCreateEncryptionChunkFor(fullEncryptionKeyUri2, r3);
        if (hlsChunkHolder.chunk != null) {
            return;
        }
        boolean shouldSpliceIn = HlsMediaChunk.shouldSpliceIn(hlsMediaChunk, uri, hlsMediaPlaylist, nextSegmentHolder, j3);
        if (shouldSpliceIn && nextSegmentHolder.isPreload) {
            return;
        }
        hlsChunkHolder.chunk = HlsMediaChunk.createInstance(this.extractorFactory, this.mediaDataSource, this.playlistFormats[r3], j3, hlsMediaPlaylist, nextSegmentHolder, uri, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.isTimestampMaster, this.timestampAdjusterProvider, hlsMediaChunk, this.keyCache.get(fullEncryptionKeyUri2), this.keyCache.get(fullEncryptionKeyUri), shouldSpliceIn, this.playerId);
    }

    private static SegmentBaseHolder getNextSegmentHolder(HlsMediaPlaylist hlsMediaPlaylist, long j, int r10) {
        int r1 = (int) (j - hlsMediaPlaylist.mediaSequence);
        if (r1 == hlsMediaPlaylist.segments.size()) {
            if (r10 == -1) {
                r10 = 0;
            }
            if (r10 < hlsMediaPlaylist.trailingParts.size()) {
                return new SegmentBaseHolder(hlsMediaPlaylist.trailingParts.get(r10), j, r10);
            }
            return null;
        }
        HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(r1);
        if (r10 == -1) {
            return new SegmentBaseHolder(segment, j, -1);
        }
        if (r10 < segment.parts.size()) {
            return new SegmentBaseHolder(segment.parts.get(r10), j, r10);
        }
        int r12 = r1 + 1;
        if (r12 < hlsMediaPlaylist.segments.size()) {
            return new SegmentBaseHolder(hlsMediaPlaylist.segments.get(r12), j + 1, -1);
        }
        if (hlsMediaPlaylist.trailingParts.isEmpty()) {
            return null;
        }
        return new SegmentBaseHolder(hlsMediaPlaylist.trailingParts.get(0), j + 1, 0);
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            this.keyCache.put(encryptionKeyChunk.dataSpec.uri, (byte[]) Assertions.checkNotNull(encryptionKeyChunk.getResult()));
        }
    }

    public boolean maybeExcludeTrack(Chunk chunk, long j) {
        ExoTrackSelection exoTrackSelection = this.trackSelection;
        return exoTrackSelection.blacklist(exoTrackSelection.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), j);
    }

    public boolean onPlaylistError(Uri uri, long j) {
        int indexOf;
        int r1 = 0;
        while (true) {
            Uri[] uriArr = this.playlistUrls;
            if (r1 >= uriArr.length) {
                r1 = -1;
                break;
            } else if (uriArr[r1].equals(uri)) {
                break;
            } else {
                r1++;
            }
        }
        if (r1 == -1 || (indexOf = this.trackSelection.indexOf(r1)) == -1) {
            return true;
        }
        this.seenExpectedPlaylistError |= uri.equals(this.expectedPlaylistUrl);
        return j == C1856C.TIME_UNSET || (this.trackSelection.blacklist(indexOf, j) && this.playlistTracker.excludeMediaPlaylist(uri, j));
    }

    public MediaChunkIterator[] createMediaChunkIterators(HlsMediaChunk hlsMediaChunk, long j) {
        int r16;
        int indexOf = hlsMediaChunk == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        int length = this.trackSelection.length();
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[length];
        boolean z = false;
        int r14 = 0;
        while (r14 < length) {
            int indexInTrackGroup = this.trackSelection.getIndexInTrackGroup(r14);
            Uri uri = this.playlistUrls[indexInTrackGroup];
            if (!this.playlistTracker.isSnapshotValid(uri)) {
                mediaChunkIteratorArr[r14] = MediaChunkIterator.EMPTY;
                r16 = r14;
            } else {
                HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri, z);
                Assertions.checkNotNull(playlistSnapshot);
                long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                r16 = r14;
                Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(hlsMediaChunk, indexInTrackGroup != indexOf, playlistSnapshot, initialStartTimeUs, j);
                mediaChunkIteratorArr[r16] = new HlsMediaPlaylistSegmentIterator(playlistSnapshot.baseUri, initialStartTimeUs, getSegmentBaseList(playlistSnapshot, ((Long) nextMediaSequenceAndPartIndex.first).longValue(), ((Integer) nextMediaSequenceAndPartIndex.second).intValue()));
            }
            r14 = r16 + 1;
            z = false;
        }
        return mediaChunkIteratorArr;
    }

    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return list.size();
        }
        return this.trackSelection.evaluateQueueSize(j, list);
    }

    public boolean shouldCancelLoad(long j, Chunk chunk, List<? extends MediaChunk> list) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(j, chunk, list);
    }

    static List<HlsMediaPlaylist.SegmentBase> getSegmentBaseList(HlsMediaPlaylist hlsMediaPlaylist, long j, int r10) {
        int r9 = (int) (j - hlsMediaPlaylist.mediaSequence);
        if (r9 < 0 || hlsMediaPlaylist.segments.size() < r9) {
            return ImmutableList.m409of();
        }
        ArrayList arrayList = new ArrayList();
        if (r9 < hlsMediaPlaylist.segments.size()) {
            if (r10 != -1) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(r9);
                if (r10 == 0) {
                    arrayList.add(segment);
                } else if (r10 < segment.parts.size()) {
                    arrayList.addAll(segment.parts.subList(r10, segment.parts.size()));
                }
                r9++;
            }
            arrayList.addAll(hlsMediaPlaylist.segments.subList(r9, hlsMediaPlaylist.segments.size()));
            r10 = 0;
        }
        if (hlsMediaPlaylist.partTargetDurationUs != C1856C.TIME_UNSET) {
            int r1 = r10 != -1 ? r10 : 0;
            if (r1 < hlsMediaPlaylist.trailingParts.size()) {
                arrayList.addAll(hlsMediaPlaylist.trailingParts.subList(r1, hlsMediaPlaylist.trailingParts.size()));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public boolean obtainsChunksForPlaylist(Uri uri) {
        return Util.contains(this.playlistUrls, uri);
    }

    private Pair<Long, Integer> getNextMediaSequenceAndPartIndex(HlsMediaChunk hlsMediaChunk, boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j, long j2) {
        List<HlsMediaPlaylist.Part> list;
        long j3;
        if (hlsMediaChunk == null || z) {
            long j4 = hlsMediaPlaylist.durationUs + j;
            if (hlsMediaChunk != null && !this.independentSegments) {
                j2 = hlsMediaChunk.startTimeUs;
            }
            if (!hlsMediaPlaylist.hasEndTag && j2 >= j4) {
                return new Pair<>(Long.valueOf(hlsMediaPlaylist.mediaSequence + hlsMediaPlaylist.segments.size()), -1);
            }
            long j5 = j2 - j;
            int r2 = 0;
            int binarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) hlsMediaPlaylist.segments, Long.valueOf(j5), true, !this.playlistTracker.isLive() || hlsMediaChunk == null);
            long j6 = binarySearchFloor + hlsMediaPlaylist.mediaSequence;
            if (binarySearchFloor >= 0) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(binarySearchFloor);
                if (j5 < segment.relativeStartTimeUs + segment.durationUs) {
                    list = segment.parts;
                } else {
                    list = hlsMediaPlaylist.trailingParts;
                }
                while (true) {
                    if (r2 >= list.size()) {
                        break;
                    }
                    HlsMediaPlaylist.Part part = list.get(r2);
                    if (j5 >= part.relativeStartTimeUs + part.durationUs) {
                        r2++;
                    } else if (part.isIndependent) {
                        j6 += list == hlsMediaPlaylist.trailingParts ? 1L : 0L;
                        r1 = r2;
                    }
                }
            }
            return new Pair<>(Long.valueOf(j6), Integer.valueOf(r1));
        } else if (hlsMediaChunk.isLoadCompleted()) {
            if (hlsMediaChunk.partIndex == -1) {
                j3 = hlsMediaChunk.getNextChunkIndex();
            } else {
                j3 = hlsMediaChunk.chunkIndex;
            }
            return new Pair<>(Long.valueOf(j3), Integer.valueOf(hlsMediaChunk.partIndex != -1 ? hlsMediaChunk.partIndex + 1 : -1));
        } else {
            return new Pair<>(Long.valueOf(hlsMediaChunk.chunkIndex), Integer.valueOf(hlsMediaChunk.partIndex));
        }
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        long j2 = this.liveEdgeInPeriodTimeUs;
        return (j2 > C1856C.TIME_UNSET ? 1 : (j2 == C1856C.TIME_UNSET ? 0 : -1)) != 0 ? j2 - j : C1856C.TIME_UNSET;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        this.liveEdgeInPeriodTimeUs = hlsMediaPlaylist.hasEndTag ? C1856C.TIME_UNSET : hlsMediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
    }

    private Chunk maybeCreateEncryptionChunkFor(Uri uri, int r9) {
        if (uri == null) {
            return null;
        }
        byte[] remove = this.keyCache.remove(uri);
        if (remove != null) {
            this.keyCache.put(uri, remove);
            return null;
        }
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec.Builder().setUri(uri).setFlags(1).build(), this.playlistFormats[r9], this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.scratchSpace);
    }

    private static Uri getFullEncryptionKeyUri(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.SegmentBase segmentBase) {
        if (segmentBase == null || segmentBase.fullSegmentEncryptionKeyUri == null) {
            return null;
        }
        return UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segmentBase.fullSegmentEncryptionKeyUri);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class SegmentBaseHolder {
        public final boolean isPreload;
        public final long mediaSequence;
        public final int partIndex;
        public final HlsMediaPlaylist.SegmentBase segmentBase;

        public SegmentBaseHolder(HlsMediaPlaylist.SegmentBase segmentBase, long j, int r4) {
            this.segmentBase = segmentBase;
            this.mediaSequence = j;
            this.partIndex = r4;
            this.isPreload = (segmentBase instanceof HlsMediaPlaylist.Part) && ((HlsMediaPlaylist.Part) segmentBase).isPreload;
        }
    }

    /* loaded from: classes2.dex */
    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        public InitializationTrackSelection(TrackGroup trackGroup, int[] r3) {
            super(trackGroup, r3);
            this.selectedIndex = indexOf(trackGroup.getFormat(r3[0]));
        }

        @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, elapsedRealtime)) {
                for (int r3 = this.length - 1; r3 >= 0; r3--) {
                    if (!isBlacklisted(r3, elapsedRealtime)) {
                        this.selectedIndex = r3;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return this.selectedIndex;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class EncryptionKeyChunk extends DataChunk {
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int r12, Object obj, byte[] bArr) {
            super(dataSource, dataSpec, 3, format, r12, obj, bArr);
        }

        @Override // com.google.android.exoplayer2.source.chunk.DataChunk
        protected void consume(byte[] bArr, int r2) {
            this.result = Arrays.copyOf(bArr, r2);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final String playlistBaseUri;
        private final List<HlsMediaPlaylist.SegmentBase> segmentBases;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(String str, long j, List<HlsMediaPlaylist.SegmentBase> list) {
            super(0L, list.size() - 1);
            this.playlistBaseUri = str;
            this.startOfPlaylistInPeriodUs = j;
            this.segmentBases = list;
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            return new DataSpec(UriUtil.resolveToUri(this.playlistBaseUri, segmentBase.url), segmentBase.byteRangeOffset, segmentBase.byteRangeLength);
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + this.segmentBases.get((int) getCurrentIndex()).relativeStartTimeUs;
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            return this.startOfPlaylistInPeriodUs + segmentBase.relativeStartTimeUs + segmentBase.durationUs;
        }
    }
}
