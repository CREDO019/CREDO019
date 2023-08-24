package com.google.zxing.common.detector;

/* loaded from: classes3.dex */
public final class MathUtils {
    public static int round(float f) {
        return (int) (f + (f < 0.0f ? -0.5f : 0.5f));
    }

    private MathUtils() {
    }

    public static float distance(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return (float) Math.sqrt((f5 * f5) + (f6 * f6));
    }

    public static float distance(int r0, int r1, int r2, int r3) {
        int r02 = r0 - r2;
        int r12 = r1 - r3;
        return (float) Math.sqrt((r02 * r02) + (r12 * r12));
    }

    public static int sum(int[] r4) {
        int r2 = 0;
        for (int r3 : r4) {
            r2 += r3;
        }
        return r2;
    }
}
