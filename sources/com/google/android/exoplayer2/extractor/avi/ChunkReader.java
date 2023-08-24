package com.google.android.exoplayer2.extractor.avi;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class ChunkReader {
    private static final int CHUNK_TYPE_AUDIO = 1651965952;
    private static final int CHUNK_TYPE_VIDEO_COMPRESSED = 1667497984;
    private static final int CHUNK_TYPE_VIDEO_UNCOMPRESSED = 1650720768;
    private static final int INITIAL_INDEX_SIZE = 512;
    private final int alternativeChunkId;
    private int bytesRemainingInCurrentChunk;
    private final int chunkId;
    private int currentChunkIndex;
    private int currentChunkSize;
    private final long durationUs;
    private int indexChunkCount;
    private int indexSize;
    private int[] keyFrameIndices;
    private long[] keyFrameOffsets;
    private final int streamHeaderChunkCount;
    protected final TrackOutput trackOutput;

    public ChunkReader(int r3, int r4, long j, int r7, TrackOutput trackOutput) {
        boolean z = true;
        if (r4 != 1 && r4 != 2) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.durationUs = j;
        this.streamHeaderChunkCount = r7;
        this.trackOutput = trackOutput;
        this.chunkId = getChunkIdFourCc(r3, r4 == 2 ? CHUNK_TYPE_VIDEO_COMPRESSED : CHUNK_TYPE_AUDIO);
        this.alternativeChunkId = r4 == 2 ? getChunkIdFourCc(r3, CHUNK_TYPE_VIDEO_UNCOMPRESSED) : -1;
        this.keyFrameOffsets = new long[512];
        this.keyFrameIndices = new int[512];
    }

    public void appendKeyFrameToIndex(long j) {
        if (this.indexSize == this.keyFrameIndices.length) {
            long[] jArr = this.keyFrameOffsets;
            this.keyFrameOffsets = Arrays.copyOf(jArr, (jArr.length * 3) / 2);
            int[] r0 = this.keyFrameIndices;
            this.keyFrameIndices = Arrays.copyOf(r0, (r0.length * 3) / 2);
        }
        long[] jArr2 = this.keyFrameOffsets;
        int r1 = this.indexSize;
        jArr2[r1] = j;
        this.keyFrameIndices[r1] = this.indexChunkCount;
        this.indexSize = r1 + 1;
    }

    public void advanceCurrentChunk() {
        this.currentChunkIndex++;
    }

    public long getCurrentChunkTimestampUs() {
        return getChunkTimestampUs(this.currentChunkIndex);
    }

    public long getFrameDurationUs() {
        return getChunkTimestampUs(1);
    }

    public void incrementIndexChunkCount() {
        this.indexChunkCount++;
    }

    public void compactIndex() {
        this.keyFrameOffsets = Arrays.copyOf(this.keyFrameOffsets, this.indexSize);
        this.keyFrameIndices = Arrays.copyOf(this.keyFrameIndices, this.indexSize);
    }

    public boolean handlesChunkId(int r2) {
        return this.chunkId == r2 || this.alternativeChunkId == r2;
    }

    public boolean isCurrentFrameAKeyFrame() {
        return Arrays.binarySearch(this.keyFrameIndices, this.currentChunkIndex) >= 0;
    }

    public boolean isVideo() {
        return (this.chunkId & CHUNK_TYPE_VIDEO_COMPRESSED) == CHUNK_TYPE_VIDEO_COMPRESSED;
    }

    public boolean isAudio() {
        return (this.chunkId & CHUNK_TYPE_AUDIO) == CHUNK_TYPE_AUDIO;
    }

    public void onChunkStart(int r1) {
        this.currentChunkSize = r1;
        this.bytesRemainingInCurrentChunk = r1;
    }

    public boolean onChunkData(ExtractorInput extractorInput) throws IOException {
        int r0 = this.bytesRemainingInCurrentChunk;
        int sampleData = r0 - this.trackOutput.sampleData((DataReader) extractorInput, r0, false);
        this.bytesRemainingInCurrentChunk = sampleData;
        boolean z = sampleData == 0;
        if (z) {
            if (this.currentChunkSize > 0) {
                this.trackOutput.sampleMetadata(getCurrentChunkTimestampUs(), isCurrentFrameAKeyFrame() ? 1 : 0, this.currentChunkSize, 0, null);
            }
            advanceCurrentChunk();
        }
        return z;
    }

    public void seekToPosition(long j) {
        if (this.indexSize == 0) {
            this.currentChunkIndex = 0;
            return;
        }
        this.currentChunkIndex = this.keyFrameIndices[Util.binarySearchFloor(this.keyFrameOffsets, j, true, true)];
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        int frameDurationUs = (int) (j / getFrameDurationUs());
        int binarySearchFloor = Util.binarySearchFloor(this.keyFrameIndices, frameDurationUs, true, true);
        if (this.keyFrameIndices[binarySearchFloor] == frameDurationUs) {
            return new SeekMap.SeekPoints(getSeekPoint(binarySearchFloor));
        }
        SeekPoint seekPoint = getSeekPoint(binarySearchFloor);
        int r3 = binarySearchFloor + 1;
        if (r3 < this.keyFrameOffsets.length) {
            return new SeekMap.SeekPoints(seekPoint, getSeekPoint(r3));
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    private long getChunkTimestampUs(int r5) {
        return (this.durationUs * r5) / this.streamHeaderChunkCount;
    }

    private SeekPoint getSeekPoint(int r7) {
        return new SeekPoint(this.keyFrameIndices[r7] * getFrameDurationUs(), this.keyFrameOffsets[r7]);
    }

    private static int getChunkIdFourCc(int r1, int r2) {
        return (((r1 % 10) + 48) << 8) | ((r1 / 10) + 48) | r2;
    }
}
