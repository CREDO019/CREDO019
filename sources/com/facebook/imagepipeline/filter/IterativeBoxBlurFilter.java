package com.facebook.imagepipeline.filter;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;

/* loaded from: classes.dex */
public abstract class IterativeBoxBlurFilter {
    private static final String TAG = "IterativeBoxBlurFilter";

    private static int bound(int x, int l, int h) {
        return x < l ? l : x > h ? h : x;
    }

    public static void boxBlurBitmapInPlace(final Bitmap bitmap, final int iterations, final int radius) {
        Preconditions.checkNotNull(bitmap);
        Preconditions.checkArgument(Boolean.valueOf(bitmap.isMutable()));
        Preconditions.checkArgument(Boolean.valueOf(((float) bitmap.getHeight()) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(((float) bitmap.getWidth()) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(radius > 0 && radius <= 25));
        Preconditions.checkArgument(Boolean.valueOf(iterations > 0));
        try {
            fastBoxBlur(bitmap, iterations, radius);
        } catch (OutOfMemoryError e) {
            FLog.m1328e(TAG, String.format(null, "OOM: %d iterations on %dx%d with %d radius", Integer.valueOf(iterations), Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Integer.valueOf(radius)));
            throw e;
        }
    }

    private static void fastBoxBlur(final Bitmap bitmap, final int iterations, final int radius) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] r10 = new int[width * height];
        bitmap.getPixels(r10, 0, width, 0, 0, width, height);
        int r0 = radius + 1;
        int r7 = r0 + radius;
        int[] r11 = new int[r7 * 256];
        int r1 = 1;
        while (true) {
            if (r1 > 255) {
                break;
            }
            for (int r12 = 0; r12 < r7; r12++) {
                r11[r0] = r1;
                r0++;
            }
            r1++;
        }
        int[] r13 = new int[Math.max(width, height)];
        for (int r15 = 0; r15 < iterations; r15++) {
            for (int r6 = 0; r6 < height; r6++) {
                internalHorizontalBlur(r10, r13, width, r6, r7, r11);
                System.arraycopy(r13, 0, r10, r6 * width, width);
            }
            int r62 = 0;
            while (r62 < width) {
                int r16 = r62;
                internalVerticalBlur(r10, r13, width, height, r62, r7, r11);
                int r63 = r16;
                for (int r02 = 0; r02 < height; r02++) {
                    r10[r63] = r13[r02];
                    r63 += width;
                }
                r62 = r16 + 1;
            }
        }
        bitmap.setPixels(r10, 0, width, 0, 0, width, height);
    }

    private static void internalHorizontalBlur(int[] pixels, int[] outRow, int w, int row, int diameter, int[] div) {
        int r0 = w * row;
        int r13 = ((row + 1) * w) - 1;
        int r1 = diameter >> 1;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        for (int r2 = -r1; r2 < w + r1; r2++) {
            int r7 = pixels[bound(r0 + r2, r0, r13)];
            r3 += (r7 >> 16) & 255;
            r4 += (r7 >> 8) & 255;
            r5 += r7 & 255;
            r6 += r7 >>> 24;
            if (r2 >= r1) {
                outRow[r2 - r1] = (div[r6] << 24) | (div[r3] << 16) | (div[r4] << 8) | div[r5];
                int r72 = pixels[bound((r2 - (diameter - 1)) + r0, r0, r13)];
                r3 -= (r72 >> 16) & 255;
                r4 -= (r72 >> 8) & 255;
                r5 -= r72 & 255;
                r6 -= r72 >>> 24;
            }
        }
    }

    private static void internalVerticalBlur(int[] pixels, int[] outCol, int w, int h, int col, int diameter, int[] div) {
        int r12 = ((h - 1) * w) + col;
        int r0 = (diameter >> 1) * w;
        int r14 = (diameter - 1) * w;
        int r1 = col - r0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        while (r1 <= r12 + r0) {
            int r7 = pixels[bound(r1, col, r12)];
            r2 += (r7 >> 16) & 255;
            r3 += (r7 >> 8) & 255;
            r4 += r7 & 255;
            r5 += r7 >>> 24;
            if (r1 - r0 >= col) {
                outCol[r6] = (div[r5] << 24) | (div[r2] << 16) | (div[r3] << 8) | div[r4];
                r6++;
                int r72 = pixels[bound(r1 - r14, col, r12)];
                r2 -= (r72 >> 16) & 255;
                r3 -= (r72 >> 8) & 255;
                r4 -= r72 & 255;
                r5 -= r72 >>> 24;
            }
            r1 += w;
        }
    }
}
