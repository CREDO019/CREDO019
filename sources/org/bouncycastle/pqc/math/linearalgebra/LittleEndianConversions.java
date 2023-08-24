package org.bouncycastle.pqc.math.linearalgebra;

/* loaded from: classes4.dex */
public final class LittleEndianConversions {
    private LittleEndianConversions() {
    }

    public static void I2OSP(int r2, byte[] bArr, int r4) {
        int r0 = r4 + 1;
        bArr[r4] = (byte) r2;
        int r42 = r0 + 1;
        bArr[r0] = (byte) (r2 >>> 8);
        bArr[r42] = (byte) (r2 >>> 16);
        bArr[r42 + 1] = (byte) (r2 >>> 24);
    }

    public static void I2OSP(int r2, byte[] bArr, int r4, int r5) {
        for (int r52 = r5 - 1; r52 >= 0; r52--) {
            bArr[r4 + r52] = (byte) (r2 >>> (r52 * 8));
        }
    }

    public static void I2OSP(long j, byte[] bArr, int r6) {
        int r0 = r6 + 1;
        bArr[r6] = (byte) j;
        int r62 = r0 + 1;
        bArr[r0] = (byte) (j >>> 8);
        int r02 = r62 + 1;
        bArr[r62] = (byte) (j >>> 16);
        int r63 = r02 + 1;
        bArr[r02] = (byte) (j >>> 24);
        int r03 = r63 + 1;
        bArr[r63] = (byte) (j >>> 32);
        int r64 = r03 + 1;
        bArr[r03] = (byte) (j >>> 40);
        bArr[r64] = (byte) (j >>> 48);
        bArr[r64 + 1] = (byte) (j >>> 56);
    }

    public static byte[] I2OSP(int r3) {
        return new byte[]{(byte) r3, (byte) (r3 >>> 8), (byte) (r3 >>> 16), (byte) (r3 >>> 24)};
    }

    public static byte[] I2OSP(long j) {
        return new byte[]{(byte) j, (byte) (j >>> 8), (byte) (j >>> 16), (byte) (j >>> 24), (byte) (j >>> 32), (byte) (j >>> 40), (byte) (j >>> 48), (byte) (j >>> 56)};
    }

    public static int OS2IP(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public static int OS2IP(byte[] bArr, int r3) {
        int r0 = r3 + 1;
        int r1 = r0 + 1;
        int r32 = (bArr[r3] & 255) | ((bArr[r0] & 255) << 8);
        return ((bArr[r1 + 1] & 255) << 24) | r32 | ((bArr[r1] & 255) << 16);
    }

    public static int OS2IP(byte[] bArr, int r4, int r5) {
        int r0 = 0;
        for (int r52 = r5 - 1; r52 >= 0; r52--) {
            r0 |= (bArr[r4 + r52] & 255) << (r52 * 8);
        }
        return r0;
    }

    public static long OS2LIP(byte[] bArr, int r9) {
        int r0;
        int r92;
        int r2;
        int r93;
        int r22;
        long j = bArr[r9] & 255;
        long j2 = j | ((bArr[r0] & 255) << 8) | ((bArr[r92] & 255) << 16) | ((bArr[r2] & 255) << 24);
        int r94 = r9 + 1 + 1 + 1 + 1 + 1 + 1;
        return ((bArr[r94 + 1] & 255) << 56) | j2 | ((bArr[r93] & 255) << 32) | ((bArr[r22] & 255) << 40) | ((bArr[r94] & 255) << 48);
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
