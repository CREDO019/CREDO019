package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.util.Log;

/* loaded from: classes2.dex */
public final class BaseMediaChunkOutput implements ChunkExtractor.TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] r1, SampleQueue[] sampleQueueArr) {
        this.trackTypes = r1;
        this.sampleQueues = sampleQueueArr;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor.TrackOutputProvider
    public TrackOutput track(int r3, int r4) {
        int r32 = 0;
        while (true) {
            int[] r0 = this.trackTypes;
            if (r32 < r0.length) {
                if (r4 == r0[r32]) {
                    return this.sampleQueues[r32];
                }
                r32++;
            } else {
                Log.m1136e(TAG, "Unmatched track of type: " + r4);
                return new DummyTrackOutput();
            }
        }
    }

    public int[] getWriteIndices() {
        int[] r0 = new int[this.sampleQueues.length];
        int r1 = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            if (r1 >= sampleQueueArr.length) {
                return r0;
            }
            r0[r1] = sampleQueueArr[r1].getWriteIndex();
            r1++;
        }
    }

    public void setSampleOffsetUs(long j) {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.setSampleOffsetUs(j);
        }
    }
}
