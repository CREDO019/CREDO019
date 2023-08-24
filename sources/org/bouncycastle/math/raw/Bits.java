package org.bouncycastle.math.raw;

/* loaded from: classes5.dex */
public abstract class Bits {
    public static int bitPermuteStep(int r1, int r2, int r3) {
        int r22 = r2 & ((r1 >>> r3) ^ r1);
        return r1 ^ (r22 ^ (r22 << r3));
    }

    public static long bitPermuteStep(long j, long j2, int r6) {
        long j3 = j2 & ((j >>> r6) ^ j);
        return j ^ (j3 ^ (j3 << r6));
    }

    public static int bitPermuteStepSimple(int r1, int r2, int r3) {
        return ((r1 >>> r3) & r2) | ((r1 & r2) << r3);
    }

    public static long bitPermuteStepSimple(long j, long j2, int r6) {
        return ((j >>> r6) & j2) | ((j & j2) << r6);
    }
}
