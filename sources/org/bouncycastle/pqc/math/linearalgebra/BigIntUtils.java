package org.bouncycastle.pqc.math.linearalgebra;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public final class BigIntUtils {
    private BigIntUtils() {
    }

    public static boolean equals(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        if (bigIntegerArr.length != bigIntegerArr2.length) {
            return false;
        }
        int r1 = 0;
        for (int r0 = 0; r0 < bigIntegerArr.length; r0++) {
            r1 |= bigIntegerArr[r0].compareTo(bigIntegerArr2[r0]);
        }
        return r1 == 0;
    }

    public static void fill(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        for (int length = bigIntegerArr.length - 1; length >= 0; length--) {
            bigIntegerArr[length] = bigInteger;
        }
    }

    public static BigInteger[] subArray(BigInteger[] bigIntegerArr, int r3, int r4) {
        int r42 = r4 - r3;
        BigInteger[] bigIntegerArr2 = new BigInteger[r42];
        System.arraycopy(bigIntegerArr, r3, bigIntegerArr2, 0, r42);
        return bigIntegerArr2;
    }

    public static int[] toIntArray(BigInteger[] bigIntegerArr) {
        int[] r0 = new int[bigIntegerArr.length];
        for (int r1 = 0; r1 < bigIntegerArr.length; r1++) {
            r0[r1] = bigIntegerArr[r1].intValue();
        }
        return r0;
    }

    public static int[] toIntArrayModQ(int r3, BigInteger[] bigIntegerArr) {
        BigInteger valueOf = BigInteger.valueOf(r3);
        int[] r0 = new int[bigIntegerArr.length];
        for (int r1 = 0; r1 < bigIntegerArr.length; r1++) {
            r0[r1] = bigIntegerArr[r1].mod(valueOf).intValue();
        }
        return r0;
    }

    public static byte[] toMinimalByteArray(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == 1 || (bigInteger.bitLength() & 7) != 0) {
            return byteArray;
        }
        int bitLength = bigInteger.bitLength() >> 3;
        byte[] bArr = new byte[bitLength];
        System.arraycopy(byteArray, 1, bArr, 0, bitLength);
        return bArr;
    }
}
