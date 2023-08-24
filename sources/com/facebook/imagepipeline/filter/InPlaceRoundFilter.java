package com.facebook.imagepipeline.filter;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;

/* loaded from: classes.dex */
public final class InPlaceRoundFilter {
    private InPlaceRoundFilter() {
    }

    public static void roundBitmapInPlace(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height) / 2;
        int r11 = width / 2;
        int r12 = height / 2;
        if (min == 0) {
            return;
        }
        Preconditions.checkArgument(Boolean.valueOf(min >= 1));
        Preconditions.checkArgument(Boolean.valueOf(width > 0 && ((float) width) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(height > 0 && ((float) height) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(r11 > 0 && r11 < width));
        Preconditions.checkArgument(Boolean.valueOf(r12 > 0 && r12 < height));
        int[] r15 = new int[width * height];
        bitmap.getPixels(r15, 0, width, 0, 0, width, height);
        int r0 = min - 1;
        Preconditions.checkArgument(Boolean.valueOf(r11 - r0 >= 0 && r12 - r0 >= 0 && r11 + r0 < width && r12 + r0 < height));
        int r1 = (-min) * 2;
        int[] r2 = new int[width];
        int r3 = r1 + 1;
        int r4 = 0;
        int r5 = 1;
        int r6 = 1;
        while (r0 >= r4) {
            int r7 = r11 + r0;
            int r13 = r11 - r0;
            int r14 = r11 + r4;
            int r16 = min;
            int r10 = r11 - r4;
            int r17 = r12 + r0;
            int r18 = r12 - r0;
            int r19 = r11;
            int r112 = r12 + r4;
            int r20 = r12 - r4;
            Preconditions.checkArgument(Boolean.valueOf(r0 >= 0 && r14 < width && r10 >= 0 && r112 < height && r20 >= 0));
            int r113 = r112 * width;
            int r21 = height;
            int r9 = width * r20;
            int r202 = r12;
            int r122 = width * r17;
            int r172 = r1;
            int r110 = width * r18;
            int r182 = r5;
            System.arraycopy(r2, 0, r15, r113, r13);
            System.arraycopy(r2, 0, r15, r9, r13);
            System.arraycopy(r2, 0, r15, r122, r10);
            System.arraycopy(r2, 0, r15, r110, r10);
            int r102 = width - r7;
            System.arraycopy(r2, 0, r15, r113 + r7, r102);
            System.arraycopy(r2, 0, r15, r9 + r7, r102);
            int r72 = width - r14;
            System.arraycopy(r2, 0, r15, r122 + r14, r72);
            System.arraycopy(r2, 0, r15, r110 + r14, r72);
            if (r3 <= 0) {
                r4++;
                r6 += 2;
                r3 += r6;
            }
            if (r3 > 0) {
                r0--;
                r5 = r182 + 2;
                r3 += r5 + r172;
                min = r16;
                r1 = r172;
            } else {
                min = r16;
                r1 = r172;
                r5 = r182;
            }
            r11 = r19;
            r12 = r202;
            height = r21;
        }
        int r212 = height;
        int r162 = min;
        int r203 = r12;
        for (int r123 = r203 - r162; r123 >= 0; r123--) {
            System.arraycopy(r2, 0, r15, r123 * width, width);
        }
        for (int r124 = r203 + r162; r124 < r212; r124++) {
            System.arraycopy(r2, 0, r15, r124 * width, width);
        }
        bitmap.setPixels(r15, 0, width, 0, 0, width, r212);
    }
}
