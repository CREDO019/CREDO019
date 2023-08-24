package org.apache.commons.lang3;

import java.util.Random;

/* loaded from: classes5.dex */
public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    public static byte[] nextBytes(int r3) {
        Validate.isTrue(r3 >= 0, "Count cannot be negative.", new Object[0]);
        byte[] bArr = new byte[r3];
        RANDOM.nextBytes(bArr);
        return bArr;
    }

    public static int nextInt(int r5, int r6) {
        Validate.isTrue(r6 >= r5, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(r5 >= 0, "Both range values must be non-negative.", new Object[0]);
        return r5 == r6 ? r5 : r5 + RANDOM.nextInt(r6 - r5);
    }

    public static int nextInt() {
        return nextInt(0, Integer.MAX_VALUE);
    }

    public static long nextLong(long j, long j2) {
        Validate.isTrue(j2 >= j, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(j >= 0, "Both range values must be non-negative.", new Object[0]);
        return j == j2 ? j : (long) nextDouble(j, j2);
    }

    public static long nextLong() {
        return nextLong(0L, Long.MAX_VALUE);
    }

    public static double nextDouble(double d, double d2) {
        Validate.isTrue(d2 >= d, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(d >= 0.0d, "Both range values must be non-negative.", new Object[0]);
        return d == d2 ? d : d + ((d2 - d) * RANDOM.nextDouble());
    }

    public static double nextDouble() {
        return nextDouble(0.0d, Double.MAX_VALUE);
    }

    public static float nextFloat(float f, float f2) {
        Validate.isTrue(f2 >= f, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(f >= 0.0f, "Both range values must be non-negative.", new Object[0]);
        return f == f2 ? f : f + ((f2 - f) * RANDOM.nextFloat());
    }

    public static float nextFloat() {
        return nextFloat(0.0f, Float.MAX_VALUE);
    }
}
