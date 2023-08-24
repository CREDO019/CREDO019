package org.bouncycastle.pqc.math.linearalgebra;

/* loaded from: classes4.dex */
public final class CharUtils {
    private CharUtils() {
    }

    public static char[] clone(char[] cArr) {
        char[] cArr2 = new char[cArr.length];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        return cArr2;
    }

    public static boolean equals(char[] cArr, char[] cArr2) {
        if (cArr.length != cArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = cArr.length - 1; length >= 0; length--) {
            z &= cArr[length] == cArr2[length];
        }
        return z;
    }

    public static byte[] toByteArray(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int length = cArr.length - 1; length >= 0; length--) {
            bArr[length] = (byte) cArr[length];
        }
        return bArr;
    }

    public static byte[] toByteArrayForPBE(char[] cArr) {
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int r3 = 0; r3 < cArr.length; r3++) {
            bArr[r3] = (byte) cArr[r3];
        }
        int r7 = length * 2;
        byte[] bArr2 = new byte[r7 + 2];
        for (int r4 = 0; r4 < length; r4++) {
            int r5 = r4 * 2;
            bArr2[r5] = 0;
            bArr2[r5 + 1] = bArr[r4];
        }
        bArr2[r7] = 0;
        bArr2[r7 + 1] = 0;
        return bArr2;
    }
}
