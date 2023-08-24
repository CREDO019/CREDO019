package org.bouncycastle.pqc.math.linearalgebra;

/* loaded from: classes4.dex */
public final class BigEndianConversions {
    private BigEndianConversions() {
    }

    public static void I2OSP(int r2, byte[] bArr, int r4) {
        int r0 = r4 + 1;
        bArr[r4] = (byte) (r2 >>> 24);
        int r42 = r0 + 1;
        bArr[r0] = (byte) (r2 >>> 16);
        bArr[r42] = (byte) (r2 >>> 8);
        bArr[r42 + 1] = (byte) r2;
    }

    public static void I2OSP(int r3, byte[] bArr, int r5, int r6) {
        int r62 = r6 - 1;
        for (int r0 = r62; r0 >= 0; r0--) {
            bArr[r5 + r0] = (byte) (r3 >>> ((r62 - r0) * 8));
        }
    }

    public static void I2OSP(long j, byte[] bArr, int r6) {
        int r0 = r6 + 1;
        bArr[r6] = (byte) (j >>> 56);
        int r62 = r0 + 1;
        bArr[r0] = (byte) (j >>> 48);
        int r02 = r62 + 1;
        bArr[r62] = (byte) (j >>> 40);
        int r63 = r02 + 1;
        bArr[r02] = (byte) (j >>> 32);
        int r03 = r63 + 1;
        bArr[r63] = (byte) (j >>> 24);
        int r64 = r03 + 1;
        bArr[r03] = (byte) (j >>> 16);
        bArr[r64] = (byte) (j >>> 8);
        bArr[r64 + 1] = (byte) j;
    }

    public static byte[] I2OSP(int r3) {
        return new byte[]{(byte) (r3 >>> 24), (byte) (r3 >>> 16), (byte) (r3 >>> 8), (byte) r3};
    }

    public static byte[] I2OSP(int r5, int r6) throws ArithmeticException {
        if (r5 < 0) {
            return null;
        }
        int ceilLog256 = IntegerFunctions.ceilLog256(r5);
        if (ceilLog256 <= r6) {
            byte[] bArr = new byte[r6];
            int r2 = r6 - 1;
            for (int r3 = r2; r3 >= r6 - ceilLog256; r3--) {
                bArr[r3] = (byte) (r5 >>> ((r2 - r3) * 8));
            }
            return bArr;
        }
        throw new ArithmeticException("Cannot encode given integer into specified number of octets.");
    }

    public static byte[] I2OSP(long j) {
        return new byte[]{(byte) (j >>> 56), (byte) (j >>> 48), (byte) (j >>> 40), (byte) (j >>> 32), (byte) (j >>> 24), (byte) (j >>> 16), (byte) (j >>> 8), (byte) j};
    }

    public static int OS2IP(byte[] bArr) {
        if (bArr.length <= 4) {
            if (bArr.length == 0) {
                return 0;
            }
            int r0 = 0;
            for (int r1 = 0; r1 < bArr.length; r1++) {
                r0 |= (bArr[r1] & 255) << (((bArr.length - 1) - r1) * 8);
            }
            return r0;
        }
        throw new ArithmeticException("invalid input length");
    }

    public static int OS2IP(byte[] bArr, int r3) {
        int r0 = r3 + 1;
        int r1 = r0 + 1;
        int r32 = ((bArr[r3] & 255) << 24) | ((bArr[r0] & 255) << 16);
        return (bArr[r1 + 1] & 255) | r32 | ((bArr[r1] & 255) << 8);
    }

    public static int OS2IP(byte[] bArr, int r5, int r6) {
        if (bArr.length == 0 || bArr.length < (r5 + r6) - 1) {
            return 0;
        }
        int r0 = 0;
        for (int r1 = 0; r1 < r6; r1++) {
            r0 |= (bArr[r5 + r1] & 255) << (((r6 - r1) - 1) * 8);
        }
        return r0;
    }

    public static long OS2LIP(byte[] bArr, int r8) {
        int r0 = r8 + 1;
        int r82 = r0 + 1;
        int r2 = r82 + 1;
        int r83 = r2 + 1;
        int r22 = r83 + 1;
        int r84 = r22 + 1;
        long j = ((bArr[r8] & 255) << 56) | ((bArr[r0] & 255) << 48) | ((bArr[r82] & 255) << 40) | ((bArr[r2] & 255) << 32) | ((255 & bArr[r83]) << 24) | ((bArr[r22] & 255) << 16);
        return (bArr[r84 + 1] & 255) | j | ((bArr[r84] & 255) << 8);
    }

    public static byte[] toByteArray(int[] r4) {
        byte[] bArr = new byte[r4.length << 2];
        for (int r1 = 0; r1 < r4.length; r1++) {
            I2OSP(r4[r1], bArr, r1 << 2);
        }
        return bArr;
    }

    public static byte[] toByteArray(int[] r5, int r6) {
        int length = r5.length;
        byte[] bArr = new byte[r6];
        int r2 = 0;
        int r3 = 0;
        while (r2 <= length - 2) {
            I2OSP(r5[r2], bArr, r3);
            r2++;
            r3 += 4;
        }
        I2OSP(r5[length - 1], bArr, r3, r6 - r3);
        return bArr;
    }

    public static int[] toIntArray(byte[] bArr) {
        int length = (bArr.length + 3) / 4;
        int length2 = bArr.length & 3;
        int[] r2 = new int[length];
        int r3 = 0;
        int r4 = 0;
        while (r3 <= length - 2) {
            r2[r3] = OS2IP(bArr, r4);
            r3++;
            r4 += 4;
        }
        int r0 = length - 1;
        if (length2 != 0) {
            r2[r0] = OS2IP(bArr, r4, length2);
        } else {
            r2[r0] = OS2IP(bArr, r4);
        }
        return r2;
    }
}
