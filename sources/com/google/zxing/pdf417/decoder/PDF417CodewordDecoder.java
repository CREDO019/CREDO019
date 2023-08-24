package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

/* loaded from: classes3.dex */
final class PDF417CodewordDecoder {
    private static final float[][] RATIOS_TABLE = (float[][]) Array.newInstance(float.class, PDF417Common.SYMBOL_TABLE.length, 8);

    static {
        int r8;
        for (int r0 = 0; r0 < PDF417Common.SYMBOL_TABLE.length; r0++) {
            int r1 = PDF417Common.SYMBOL_TABLE[r0];
            int r5 = r1 & 1;
            int r6 = 0;
            while (r6 < 8) {
                float f = 0.0f;
                while (true) {
                    r8 = r1 & 1;
                    if (r8 == r5) {
                        f += 1.0f;
                        r1 >>= 1;
                    }
                }
                RATIOS_TABLE[r0][(8 - r6) - 1] = f / 17.0f;
                r6++;
                r5 = r8;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDecodedValue(int[] r2) {
        int decodedCodewordValue = getDecodedCodewordValue(sampleBitCounts(r2));
        return decodedCodewordValue != -1 ? decodedCodewordValue : getClosestDecodedValue(r2);
    }

    private static int[] sampleBitCounts(int[] r8) {
        float sum = MathUtils.sum(r8);
        int[] r1 = new int[8];
        int r3 = 0;
        int r4 = 0;
        for (int r2 = 0; r2 < 17; r2++) {
            if (r8[r4] + r3 <= (sum / 34.0f) + ((r2 * sum) / 17.0f)) {
                r3 += r8[r4];
                r4++;
            }
            r1[r4] = r1[r4] + 1;
        }
        return r1;
    }

    private static int getDecodedCodewordValue(int[] r2) {
        int bitValue = getBitValue(r2);
        if (PDF417Common.getCodeword(bitValue) == -1) {
            return -1;
        }
        return bitValue;
    }

    private static int getBitValue(int[] r7) {
        long j = 0;
        for (int r3 = 0; r3 < r7.length; r3++) {
            for (int r4 = 0; r4 < r7[r3]; r4++) {
                int r5 = 1;
                long j2 = j << 1;
                if (r3 % 2 != 0) {
                    r5 = 0;
                }
                j = j2 | r5;
            }
        }
        return (int) j;
    }

    private static int getClosestDecodedValue(int[] r10) {
        int sum = MathUtils.sum(r10);
        float[] fArr = new float[8];
        if (sum > 1) {
            for (int r4 = 0; r4 < 8; r4++) {
                fArr[r4] = r10[r4] / sum;
            }
        }
        float f = Float.MAX_VALUE;
        int r0 = -1;
        int r42 = 0;
        while (true) {
            float[][] fArr2 = RATIOS_TABLE;
            if (r42 >= fArr2.length) {
                return r0;
            }
            float f2 = 0.0f;
            float[] fArr3 = fArr2[r42];
            for (int r7 = 0; r7 < 8; r7++) {
                float f3 = fArr3[r7] - fArr[r7];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                r0 = PDF417Common.SYMBOL_TABLE[r42];
                f = f2;
            }
            r42++;
        }
    }
}
