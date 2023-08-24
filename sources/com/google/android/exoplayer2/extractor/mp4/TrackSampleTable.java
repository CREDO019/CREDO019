package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
final class TrackSampleTable {
    public final long durationUs;
    public final int[] flags;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;
    public final Track track;

    public TrackSampleTable(Track track, long[] jArr, int[] r7, int r8, long[] jArr2, int[] r10, long j) {
        Assertions.checkArgument(r7.length == jArr2.length);
        Assertions.checkArgument(jArr.length == jArr2.length);
        Assertions.checkArgument(r10.length == jArr2.length);
        this.track = track;
        this.offsets = jArr;
        this.sizes = r7;
        this.maximumSize = r8;
        this.timestampsUs = jArr2;
        this.flags = r10;
        this.durationUs = j;
        this.sampleCount = jArr.length;
        if (r10.length > 0) {
            int length = r10.length - 1;
            r10[length] = r10[length] | 536870912;
        }
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long j) {
        for (int binarySearchFloor = Util.binarySearchFloor(this.timestampsUs, j, true, false); binarySearchFloor >= 0; binarySearchFloor--) {
            if ((this.flags[binarySearchFloor] & 1) != 0) {
                return binarySearchFloor;
            }
        }
        return -1;
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long j) {
        for (int binarySearchCeil = Util.binarySearchCeil(this.timestampsUs, j, true, false); binarySearchCeil < this.timestampsUs.length; binarySearchCeil++) {
            if ((this.flags[binarySearchCeil] & 1) != 0) {
                return binarySearchCeil;
            }
        }
        return -1;
    }
}
