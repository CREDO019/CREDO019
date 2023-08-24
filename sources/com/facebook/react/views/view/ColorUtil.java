package com.facebook.react.views.view;

import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class ColorUtil {
    public static int getOpacityFromColor(int r1) {
        int r12 = r1 >>> 24;
        if (r12 == 255) {
            return -1;
        }
        return r12 == 0 ? -2 : -3;
    }

    public static int multiplyColorAlpha(int r2, int r3) {
        if (r3 == 255) {
            return r2;
        }
        if (r3 == 0) {
            return r2 & ViewCompat.MEASURED_SIZE_MASK;
        }
        int r32 = r3 + (r3 >> 7);
        return (r2 & ViewCompat.MEASURED_SIZE_MASK) | ((((r2 >>> 24) * r32) >> 8) << 24);
    }

    public static int normalize(double d, double d2, double d3, double d4) {
        return (clamp255(d) << 16) | (clamp255(d4 * 255.0d) << 24) | (clamp255(d2) << 8) | clamp255(d3);
    }

    private static int clamp255(double d) {
        return Math.max(0, Math.min(255, (int) Math.round(d)));
    }
}
