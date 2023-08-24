package org.bouncycastle.util;

import com.google.common.base.Ascii;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public abstract class Pack {
    public static int bigEndianToInt(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] & 255) | (bArr[r3] << Ascii.CAN) | ((bArr[r32] & 255) << 16) | ((bArr[r33] & 255) << 8);
    }

    public static void bigEndianToInt(byte[] bArr, int r3, int[] r4) {
        for (int r0 = 0; r0 < r4.length; r0++) {
            r4[r0] = bigEndianToInt(bArr, r3);
            r3 += 4;
        }
    }

    public static void bigEndianToInt(byte[] bArr, int r4, int[] r5, int r6, int r7) {
        for (int r0 = 0; r0 < r7; r0++) {
            r5[r6 + r0] = bigEndianToInt(bArr, r4);
            r4 += 4;
        }
    }

    public static long bigEndianToLong(byte[] bArr, int r5) {
        int bigEndianToInt = bigEndianToInt(bArr, r5);
        return (bigEndianToInt(bArr, r5 + 4) & BodyPartID.bodyIdMax) | ((bigEndianToInt & BodyPartID.bodyIdMax) << 32);
    }

    public static void bigEndianToLong(byte[] bArr, int r4, long[] jArr) {
        for (int r0 = 0; r0 < jArr.length; r0++) {
            jArr[r0] = bigEndianToLong(bArr, r4);
            r4 += 8;
        }
    }

    public static void bigEndianToLong(byte[] bArr, int r5, long[] jArr, int r7, int r8) {
        for (int r0 = 0; r0 < r8; r0++) {
            jArr[r7 + r0] = bigEndianToLong(bArr, r5);
            r5 += 8;
        }
    }

    public static short bigEndianToShort(byte[] bArr, int r2) {
        return (short) ((bArr[r2 + 1] & 255) | ((bArr[r2] & 255) << 8));
    }

    public static void intToBigEndian(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) (r1 >>> 24);
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 16);
        int r33 = r32 + 1;
        bArr[r33] = (byte) (r1 >>> 8);
        bArr[r33 + 1] = (byte) r1;
    }

    public static void intToBigEndian(int[] r2, int r3, int r4, byte[] bArr, int r6) {
        for (int r0 = 0; r0 < r4; r0++) {
            intToBigEndian(r2[r3 + r0], bArr, r6);
            r6 += 4;
        }
    }

    public static void intToBigEndian(int[] r2, byte[] bArr, int r4) {
        for (int r1 : r2) {
            intToBigEndian(r1, bArr, r4);
            r4 += 4;
        }
    }

    public static byte[] intToBigEndian(int r2) {
        byte[] bArr = new byte[4];
        intToBigEndian(r2, bArr, 0);
        return bArr;
    }

    public static byte[] intToBigEndian(int[] r2) {
        byte[] bArr = new byte[r2.length * 4];
        intToBigEndian(r2, bArr, 0);
        return bArr;
    }

    public static void intToLittleEndian(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) r1;
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 8);
        int r33 = r32 + 1;
        bArr[r33] = (byte) (r1 >>> 16);
        bArr[r33 + 1] = (byte) (r1 >>> 24);
    }

    public static void intToLittleEndian(int[] r2, int r3, int r4, byte[] bArr, int r6) {
        for (int r0 = 0; r0 < r4; r0++) {
            intToLittleEndian(r2[r3 + r0], bArr, r6);
            r6 += 4;
        }
    }

    public static void intToLittleEndian(int[] r2, byte[] bArr, int r4) {
        for (int r1 : r2) {
            intToLittleEndian(r1, bArr, r4);
            r4 += 4;
        }
    }

    public static byte[] intToLittleEndian(int r2) {
        byte[] bArr = new byte[4];
        intToLittleEndian(r2, bArr, 0);
        return bArr;
    }

    public static byte[] intToLittleEndian(int[] r2) {
        byte[] bArr = new byte[r2.length * 4];
        intToLittleEndian(r2, bArr, 0);
        return bArr;
    }

    public static int littleEndianToInt(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] << Ascii.CAN) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8) | ((bArr[r33] & 255) << 16);
    }

    public static void littleEndianToInt(byte[] bArr, int r3, int[] r4) {
        for (int r0 = 0; r0 < r4.length; r0++) {
            r4[r0] = littleEndianToInt(bArr, r3);
            r3 += 4;
        }
    }

    public static void littleEndianToInt(byte[] bArr, int r4, int[] r5, int r6, int r7) {
        for (int r0 = 0; r0 < r7; r0++) {
            r5[r6 + r0] = littleEndianToInt(bArr, r4);
            r4 += 4;
        }
    }

    public static int[] littleEndianToInt(byte[] bArr, int r4, int r5) {
        int[] r0 = new int[r5];
        for (int r1 = 0; r1 < r5; r1++) {
            r0[r1] = littleEndianToInt(bArr, r4);
            r4 += 4;
        }
        return r0;
    }

    public static long littleEndianToLong(byte[] bArr, int r6) {
        return ((littleEndianToInt(bArr, r6 + 4) & BodyPartID.bodyIdMax) << 32) | (littleEndianToInt(bArr, r6) & BodyPartID.bodyIdMax);
    }

    public static void littleEndianToLong(byte[] bArr, int r4, long[] jArr) {
        for (int r0 = 0; r0 < jArr.length; r0++) {
            jArr[r0] = littleEndianToLong(bArr, r4);
            r4 += 8;
        }
    }

    public static void littleEndianToLong(byte[] bArr, int r5, long[] jArr, int r7, int r8) {
        for (int r0 = 0; r0 < r8; r0++) {
            jArr[r7 + r0] = littleEndianToLong(bArr, r5);
            r5 += 8;
        }
    }

    public static short littleEndianToShort(byte[] bArr, int r2) {
        return (short) (((bArr[r2 + 1] & 255) << 8) | (bArr[r2] & 255));
    }

    public static void longToBigEndian(long j, byte[] bArr, int r5) {
        intToBigEndian((int) (j >>> 32), bArr, r5);
        intToBigEndian((int) (j & BodyPartID.bodyIdMax), bArr, r5 + 4);
    }

    public static void longToBigEndian(long j, byte[] bArr, int r6, int r7) {
        for (int r72 = r7 - 1; r72 >= 0; r72--) {
            bArr[r72 + r6] = (byte) (255 & j);
            j >>>= 8;
        }
    }

    public static void longToBigEndian(long[] jArr, int r4, int r5, byte[] bArr, int r7) {
        for (int r0 = 0; r0 < r5; r0++) {
            longToBigEndian(jArr[r4 + r0], bArr, r7);
            r7 += 8;
        }
    }

    public static void longToBigEndian(long[] jArr, byte[] bArr, int r5) {
        for (long j : jArr) {
            longToBigEndian(j, bArr, r5);
            r5 += 8;
        }
    }

    public static byte[] longToBigEndian(long j) {
        byte[] bArr = new byte[8];
        longToBigEndian(j, bArr, 0);
        return bArr;
    }

    public static byte[] longToBigEndian(long[] jArr) {
        byte[] bArr = new byte[jArr.length * 8];
        longToBigEndian(jArr, bArr, 0);
        return bArr;
    }

    public static void longToLittleEndian(long j, byte[] bArr, int r5) {
        intToLittleEndian((int) (BodyPartID.bodyIdMax & j), bArr, r5);
        intToLittleEndian((int) (j >>> 32), bArr, r5 + 4);
    }

    public static void longToLittleEndian(long[] jArr, int r4, int r5, byte[] bArr, int r7) {
        for (int r0 = 0; r0 < r5; r0++) {
            longToLittleEndian(jArr[r4 + r0], bArr, r7);
            r7 += 8;
        }
    }

    public static void longToLittleEndian(long[] jArr, byte[] bArr, int r5) {
        for (long j : jArr) {
            longToLittleEndian(j, bArr, r5);
            r5 += 8;
        }
    }

    public static byte[] longToLittleEndian(long j) {
        byte[] bArr = new byte[8];
        longToLittleEndian(j, bArr, 0);
        return bArr;
    }

    public static byte[] longToLittleEndian(long[] jArr) {
        byte[] bArr = new byte[jArr.length * 8];
        longToLittleEndian(jArr, bArr, 0);
        return bArr;
    }

    public static void shortToBigEndian(short s, byte[] bArr, int r3) {
        bArr[r3] = (byte) (s >>> 8);
        bArr[r3 + 1] = (byte) s;
    }

    public static byte[] shortToBigEndian(short s) {
        byte[] bArr = new byte[2];
        shortToBigEndian(s, bArr, 0);
        return bArr;
    }

    public static void shortToLittleEndian(short s, byte[] bArr, int r3) {
        bArr[r3] = (byte) s;
        bArr[r3 + 1] = (byte) (s >>> 8);
    }

    public static byte[] shortToLittleEndian(short s) {
        byte[] bArr = new byte[2];
        shortToLittleEndian(s, bArr, 0);
        return bArr;
    }
}
