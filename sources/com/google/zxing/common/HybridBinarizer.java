package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    private static int cap(int r0, int r1, int r2) {
        return r0 < r1 ? r1 : r0 > r2 ? r2 : r0;
    }

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width >= 40 && height >= 40) {
            byte[] matrix = luminanceSource.getMatrix();
            int r0 = width >> 3;
            if ((width & 7) != 0) {
                r0++;
            }
            int r2 = r0;
            int r02 = height >> 3;
            if ((height & 7) != 0) {
                r02++;
            }
            int r3 = r02;
            int[][] calculateBlackPoints = calculateBlackPoints(matrix, r2, r3, width, height);
            BitMatrix bitMatrix2 = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, r2, r3, width, height, calculateBlackPoints, bitMatrix2);
            this.matrix = bitMatrix2;
        } else {
            this.matrix = super.getBlackMatrix();
        }
        return this.matrix;
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    private static void calculateThresholdForBlock(byte[] bArr, int r18, int r19, int r20, int r21, int[][] r22, BitMatrix bitMatrix) {
        int r2 = r21 - 8;
        int r9 = r20 - 8;
        for (int r11 = 0; r11 < r19; r11++) {
            int r3 = r11 << 3;
            int r12 = r3 > r2 ? r2 : r3;
            int cap = cap(r11, 2, r19 - 3);
            for (int r15 = 0; r15 < r18; r15++) {
                int r32 = r15 << 3;
                int r4 = r32 > r9 ? r9 : r32;
                int cap2 = cap(r15, 2, r18 - 3);
                int r6 = 0;
                for (int r5 = -2; r5 <= 2; r5++) {
                    int[] r7 = r22[cap + r5];
                    r6 += r7[cap2 - 2] + r7[cap2 - 1] + r7[cap2] + r7[cap2 + 1] + r7[cap2 + 2];
                }
                thresholdBlock(bArr, r4, r12, r6 / 25, r20, bitMatrix);
            }
        }
    }

    private static void thresholdBlock(byte[] bArr, int r8, int r9, int r10, int r11, BitMatrix bitMatrix) {
        int r0 = (r9 * r11) + r8;
        int r2 = 0;
        while (r2 < 8) {
            for (int r4 = 0; r4 < 8; r4++) {
                if ((bArr[r0 + r4] & 255) <= r10) {
                    bitMatrix.set(r8 + r4, r9 + r2);
                }
            }
            r2++;
            r0 += r11;
        }
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int r18, int r19, int r20, int r21) {
        int r2 = 8;
        int r3 = r21 - 8;
        int r4 = r20 - 8;
        int[][] r6 = (int[][]) Array.newInstance(int.class, r19, r18);
        int r9 = 0;
        while (r9 < r19) {
            int r10 = r9 << 3;
            if (r10 > r3) {
                r10 = r3;
            }
            int r11 = 0;
            while (r11 < r18) {
                int r12 = r11 << 3;
                if (r12 > r4) {
                    r12 = r4;
                }
                int r13 = (r10 * r20) + r12;
                int r8 = 255;
                int r14 = 0;
                int r15 = 0;
                int r16 = 0;
                while (r14 < r2) {
                    int r7 = r16;
                    int r5 = 0;
                    while (r5 < r2) {
                        int r22 = bArr[r13 + r5] & 255;
                        r15 += r22;
                        if (r22 < r8) {
                            r8 = r22;
                        }
                        if (r22 > r7) {
                            r7 = r22;
                        }
                        r5++;
                        r2 = 8;
                    }
                    if (r7 - r8 <= 24) {
                        r14++;
                        r13 += r20;
                        r16 = r7;
                        r2 = 8;
                    }
                    while (true) {
                        r14++;
                        r13 += r20;
                        if (r14 < 8) {
                            int r52 = 0;
                            for (int r23 = 8; r52 < r23; r23 = 8) {
                                r15 += bArr[r13 + r52] & 255;
                                r52++;
                            }
                        }
                    }
                    r14++;
                    r13 += r20;
                    r16 = r7;
                    r2 = 8;
                }
                int r53 = r15 >> 6;
                if (r16 - r8 <= 24) {
                    r53 = r8 / 2;
                    if (r9 > 0 && r11 > 0) {
                        int r72 = r9 - 1;
                        int r142 = r11 - 1;
                        int r73 = ((r6[r72][r11] + (r6[r9][r142] * 2)) + r6[r72][r142]) / 4;
                        if (r8 < r73) {
                            r53 = r73;
                        }
                        r6[r9][r11] = r53;
                        r11++;
                        r2 = 8;
                    }
                }
                r6[r9][r11] = r53;
                r11++;
                r2 = 8;
            }
            r9++;
            r2 = 8;
        }
        return r6;
    }
}
