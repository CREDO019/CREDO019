package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
final class FixedSampleSizeRechunker {
    private static final int MAX_SAMPLE_SIZE = 8192;

    /* loaded from: classes2.dex */
    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;

        private Results(long[] jArr, int[] r2, int r3, long[] jArr2, int[] r5, long j) {
            this.offsets = jArr;
            this.sizes = r2;
            this.maximumSize = r3;
            this.timestamps = jArr2;
            this.flags = r5;
            this.duration = j;
        }
    }

    public static Results rechunk(int r16, long[] jArr, int[] r18, long j) {
        int r1 = 8192 / r16;
        int r5 = 0;
        for (int r6 : r18) {
            r5 += Util.ceilDivide(r6, r1);
        }
        long[] jArr2 = new long[r5];
        int[] r8 = new int[r5];
        long[] jArr3 = new long[r5];
        int[] r11 = new int[r5];
        int r2 = 0;
        int r4 = 0;
        int r9 = 0;
        for (int r3 = 0; r3 < r18.length; r3++) {
            int r52 = r18[r3];
            long j2 = jArr[r3];
            while (r52 > 0) {
                int min = Math.min(r1, r52);
                jArr2[r4] = j2;
                r8[r4] = r16 * min;
                r9 = Math.max(r9, r8[r4]);
                jArr3[r4] = r2 * j;
                r11[r4] = 1;
                j2 += r8[r4];
                r2 += min;
                r52 -= min;
                r4++;
            }
        }
        return new Results(jArr2, r8, r9, jArr3, r11, j * r2);
    }

    private FixedSampleSizeRechunker() {
    }
}
