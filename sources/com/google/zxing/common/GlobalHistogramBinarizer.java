package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

/* loaded from: classes3.dex */
public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private final int[] buckets;
    private byte[] luminances;

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
        this.luminances = EMPTY;
        this.buckets = new int[32];
    }

    @Override // com.google.zxing.Binarizer
    public BitArray getBlackRow(int r11, BitArray bitArray) throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        if (bitArray == null || bitArray.getSize() < width) {
            bitArray = new BitArray(width);
        } else {
            bitArray.clear();
        }
        initArrays(width);
        byte[] row = luminanceSource.getRow(r11, this.luminances);
        int[] r0 = this.buckets;
        for (int r3 = 0; r3 < width; r3++) {
            int r4 = (row[r3] & 255) >> 3;
            r0[r4] = r0[r4] + 1;
        }
        int estimateBlackPoint = estimateBlackPoint(r0);
        if (width < 3) {
            for (int r2 = 0; r2 < width; r2++) {
                if ((row[r2] & 255) < estimateBlackPoint) {
                    bitArray.set(r2);
                }
            }
        } else {
            int r32 = row[1] & 255;
            int r42 = 1;
            int r33 = row[0] & 255;
            int r22 = r32;
            while (r42 < width - 1) {
                int r6 = r42 + 1;
                int r7 = row[r6] & 255;
                if ((((r22 << 2) - r33) - r7) / 2 < estimateBlackPoint) {
                    bitArray.set(r42);
                }
                r33 = r22;
                r42 = r6;
                r22 = r7;
            }
        }
        return bitArray;
    }

    @Override // com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        initArrays(width);
        int[] r4 = this.buckets;
        for (int r6 = 1; r6 < 5; r6++) {
            byte[] row = luminanceSource.getRow((height * r6) / 5, this.luminances);
            int r9 = (width << 2) / 5;
            for (int r7 = width / 5; r7 < r9; r7++) {
                int r10 = (row[r7] & 255) >> 3;
                r4[r10] = r4[r10] + 1;
            }
        }
        int estimateBlackPoint = estimateBlackPoint(r4);
        byte[] matrix = luminanceSource.getMatrix();
        for (int r62 = 0; r62 < height; r62++) {
            int r72 = r62 * width;
            for (int r8 = 0; r8 < width; r8++) {
                if ((matrix[r72 + r8] & 255) < estimateBlackPoint) {
                    bitMatrix.set(r8, r62);
                }
            }
        }
        return bitMatrix;
    }

    @Override // com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    private void initArrays(int r3) {
        if (this.luminances.length < r3) {
            this.luminances = new byte[r3];
        }
        for (int r0 = 0; r0 < 32; r0++) {
            this.buckets[r0] = 0;
        }
    }

    private static int estimateBlackPoint(int[] r9) throws NotFoundException {
        int length = r9.length;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        for (int r2 = 0; r2 < length; r2++) {
            if (r9[r2] > r3) {
                r3 = r9[r2];
                r5 = r2;
            }
            if (r9[r2] > r4) {
                r4 = r9[r2];
            }
        }
        int r22 = 0;
        int r32 = 0;
        for (int r1 = 0; r1 < length; r1++) {
            int r6 = r1 - r5;
            int r7 = r9[r1] * r6 * r6;
            if (r7 > r32) {
                r22 = r1;
                r32 = r7;
            }
        }
        if (r5 <= r22) {
            int r8 = r5;
            r5 = r22;
            r22 = r8;
        }
        if (r5 - r22 > length / 16) {
            int r0 = r5 - 1;
            int r12 = r0;
            int r33 = -1;
            while (r0 > r22) {
                int r62 = r0 - r22;
                int r63 = r62 * r62 * (r5 - r0) * (r4 - r9[r0]);
                if (r63 > r33) {
                    r12 = r0;
                    r33 = r63;
                }
                r0--;
            }
            return r12 << 3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
