package org.bouncycastle.pqc.math.linearalgebra;

/* loaded from: classes4.dex */
public final class IntUtils {
    private IntUtils() {
    }

    public static int[] clone(int[] r3) {
        int[] r0 = new int[r3.length];
        System.arraycopy(r3, 0, r0, 0, r3.length);
        return r0;
    }

    public static boolean equals(int[] r6, int[] r7) {
        if (r6.length != r7.length) {
            return false;
        }
        boolean z = true;
        for (int length = r6.length - 1; length >= 0; length--) {
            z &= r6[length] == r7[length];
        }
        return z;
    }

    public static void fill(int[] r1, int r2) {
        for (int length = r1.length - 1; length >= 0; length--) {
            r1[length] = r2;
        }
    }

    private static int partition(int[] r3, int r4, int r5, int r6) {
        int r0 = r3[r6];
        r3[r6] = r3[r5];
        r3[r5] = r0;
        int r62 = r4;
        while (r4 < r5) {
            if (r3[r4] <= r0) {
                int r1 = r3[r62];
                r3[r62] = r3[r4];
                r3[r4] = r1;
                r62++;
            }
            r4++;
        }
        int r42 = r3[r62];
        r3[r62] = r3[r5];
        r3[r5] = r42;
        return r62;
    }

    public static void quicksort(int[] r2) {
        quicksort(r2, 0, r2.length - 1);
    }

    public static void quicksort(int[] r2, int r3, int r4) {
        if (r4 > r3) {
            int partition = partition(r2, r3, r4, r4);
            quicksort(r2, r3, partition - 1);
            quicksort(r2, partition + 1, r4);
        }
    }

    public static int[] subArray(int[] r2, int r3, int r4) {
        int r42 = r4 - r3;
        int[] r0 = new int[r42];
        System.arraycopy(r2, r3, r0, 0, r42);
        return r0;
    }

    public static String toHexString(int[] r0) {
        return ByteUtils.toHexString(BigEndianConversions.toByteArray(r0));
    }

    public static String toString(int[] r3) {
        String str = "";
        for (int r1 = 0; r1 < r3.length; r1++) {
            str = str + r3[r1] + " ";
        }
        return str;
    }
}
