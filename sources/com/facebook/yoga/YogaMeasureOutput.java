package com.facebook.yoga;

/* loaded from: classes.dex */
public class YogaMeasureOutput {
    public static long make(float f, float f2) {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        return Float.floatToRawIntBits(f2) | (floatToRawIntBits << 32);
    }

    public static long make(int r0, int r1) {
        return make(r0, r1);
    }

    public static float getWidth(long j) {
        return Float.intBitsToFloat((int) ((j >> 32) & (-1)));
    }

    public static float getHeight(long j) {
        return Float.intBitsToFloat((int) (j & (-1)));
    }
}
