package org.bouncycastle.util;

/* loaded from: classes4.dex */
public class Longs {
    public static final int BYTES = 8;
    public static final int SIZE = 64;

    public static int numberOfLeadingZeros(long j) {
        return Long.numberOfLeadingZeros(j);
    }

    public static int numberOfTrailingZeros(long j) {
        return Long.numberOfTrailingZeros(j);
    }

    public static long reverse(long j) {
        return Long.reverse(j);
    }

    public static long reverseBytes(long j) {
        return Long.reverseBytes(j);
    }

    public static long rotateLeft(long j, int r2) {
        return Long.rotateLeft(j, r2);
    }

    public static long rotateRight(long j, int r2) {
        return Long.rotateRight(j, r2);
    }

    public static Long valueOf(long j) {
        return Long.valueOf(j);
    }
}
