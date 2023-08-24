package org.bouncycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public class RainbowUtil {
    public static byte[] convertArray(short[] sArr) {
        byte[] bArr = new byte[sArr.length];
        for (int r1 = 0; r1 < sArr.length; r1++) {
            bArr[r1] = (byte) sArr[r1];
        }
        return bArr;
    }

    public static short[] convertArray(byte[] bArr) {
        short[] sArr = new short[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            sArr[r1] = (short) (bArr[r1] & 255);
        }
        return sArr;
    }

    public static byte[][] convertArray(short[][] sArr) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, sArr.length, sArr[0].length);
        for (int r2 = 0; r2 < sArr.length; r2++) {
            for (int r3 = 0; r3 < sArr[0].length; r3++) {
                bArr[r2][r3] = (byte) sArr[r2][r3];
            }
        }
        return bArr;
    }

    public static short[][] convertArray(byte[][] bArr) {
        short[][] sArr = (short[][]) Array.newInstance(short.class, bArr.length, bArr[0].length);
        for (int r2 = 0; r2 < bArr.length; r2++) {
            for (int r3 = 0; r3 < bArr[0].length; r3++) {
                sArr[r2][r3] = (short) (bArr[r2][r3] & 255);
            }
        }
        return sArr;
    }

    public static byte[][][] convertArray(short[][][] sArr) {
        byte[][][] bArr = (byte[][][]) Array.newInstance(byte.class, sArr.length, sArr[0].length, sArr[0][0].length);
        for (int r2 = 0; r2 < sArr.length; r2++) {
            for (int r3 = 0; r3 < sArr[0].length; r3++) {
                for (int r4 = 0; r4 < sArr[0][0].length; r4++) {
                    bArr[r2][r3][r4] = (byte) sArr[r2][r3][r4];
                }
            }
        }
        return bArr;
    }

    public static short[][][] convertArray(byte[][][] bArr) {
        short[][][] sArr = (short[][][]) Array.newInstance(short.class, bArr.length, bArr[0].length, bArr[0][0].length);
        for (int r2 = 0; r2 < bArr.length; r2++) {
            for (int r3 = 0; r3 < bArr[0].length; r3++) {
                for (int r4 = 0; r4 < bArr[0][0].length; r4++) {
                    sArr[r2][r3][r4] = (short) (bArr[r2][r3][r4] & 255);
                }
            }
        }
        return sArr;
    }

    public static int[] convertArraytoInt(byte[] bArr) {
        int[] r0 = new int[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            r0[r1] = bArr[r1] & 255;
        }
        return r0;
    }

    public static byte[] convertIntArray(int[] r3) {
        byte[] bArr = new byte[r3.length];
        for (int r1 = 0; r1 < r3.length; r1++) {
            bArr[r1] = (byte) r3[r1];
        }
        return bArr;
    }

    public static boolean equals(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            z &= sArr[length] == sArr2[length];
        }
        return z;
    }

    public static boolean equals(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            z &= equals(sArr[length], sArr2[length]);
        }
        return z;
    }

    public static boolean equals(short[][][] sArr, short[][][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            z &= equals(sArr[length], sArr2[length]);
        }
        return z;
    }
}
