package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class GCMUtil {

    /* renamed from: E1 */
    private static final int f2079E1 = -520093696;
    private static final long E1L = -2233785415175766016L;
    public static final int SIZE_BYTES = 16;
    public static final int SIZE_INTS = 4;
    public static final int SIZE_LONGS = 2;

    public static byte areEqual(byte[] bArr, byte[] bArr2) {
        int r1 = 0;
        for (int r0 = 0; r0 < 16; r0++) {
            r1 |= bArr[r0] ^ bArr2[r0];
        }
        return (byte) ((((r1 >>> 1) | (r1 & 1)) - 1) >> 31);
    }

    public static int areEqual(int[] r4, int[] r5) {
        int r3 = r4[2];
        int r42 = (r4[3] ^ r5[3]) | 0 | (r4[0] ^ r5[0]) | (r4[1] ^ r5[1]) | (r5[2] ^ r3);
        return (((r42 & 1) | (r42 >>> 1)) - 1) >> 31;
    }

    public static long areEqual(long[] jArr, long[] jArr2) {
        long j = (jArr2[1] ^ jArr[1]) | (jArr[0] ^ jArr2[0]) | 0;
        return (((j & 1) | (j >>> 1)) - 1) >> 63;
    }

    public static void asBytes(int[] r2, byte[] bArr) {
        Pack.intToBigEndian(r2, 0, 4, bArr, 0);
    }

    public static void asBytes(long[] jArr, byte[] bArr) {
        Pack.longToBigEndian(jArr, 0, 2, bArr, 0);
    }

    public static byte[] asBytes(int[] r3) {
        byte[] bArr = new byte[16];
        Pack.intToBigEndian(r3, 0, 4, bArr, 0);
        return bArr;
    }

    public static byte[] asBytes(long[] jArr) {
        byte[] bArr = new byte[16];
        Pack.longToBigEndian(jArr, 0, 2, bArr, 0);
        return bArr;
    }

    public static void asInts(byte[] bArr, int[] r3) {
        Pack.bigEndianToInt(bArr, 0, r3, 0, 4);
    }

    public static int[] asInts(byte[] bArr) {
        int[] r1 = new int[4];
        Pack.bigEndianToInt(bArr, 0, r1, 0, 4);
        return r1;
    }

    public static void asLongs(byte[] bArr, long[] jArr) {
        Pack.bigEndianToLong(bArr, 0, jArr, 0, 2);
    }

    public static long[] asLongs(byte[] bArr) {
        long[] jArr = new long[2];
        Pack.bigEndianToLong(bArr, 0, jArr, 0, 2);
        return jArr;
    }

    public static void copy(byte[] bArr, byte[] bArr2) {
        for (int r0 = 0; r0 < 16; r0++) {
            bArr2[r0] = bArr[r0];
        }
    }

    public static void copy(int[] r2, int[] r3) {
        r3[0] = r2[0];
        r3[1] = r2[1];
        r3[2] = r2[2];
        r3[3] = r2[3];
    }

    public static void copy(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
    }

    public static void divideP(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j >> 63;
        jArr2[0] = ((j ^ (E1L & j3)) << 1) | (j2 >>> 63);
        jArr2[1] = (j2 << 1) | (-j3);
    }

    private static long implMul64(long j, long j2) {
        long j3 = j & 1229782938247303441L;
        long j4 = j & 2459565876494606882L;
        long j5 = j & 4919131752989213764L;
        long j6 = j & (-8608480567731124088L);
        long j7 = j2 & 1229782938247303441L;
        long j8 = j2 & 2459565876494606882L;
        long j9 = j2 & 4919131752989213764L;
        long j10 = j2 & (-8608480567731124088L);
        return (((((j3 * j7) ^ (j4 * j10)) ^ (j5 * j9)) ^ (j6 * j8)) & 1229782938247303441L) | (((((j3 * j8) ^ (j4 * j7)) ^ (j5 * j10)) ^ (j6 * j9)) & 2459565876494606882L) | (((((j3 * j9) ^ (j4 * j8)) ^ (j5 * j7)) ^ (j6 * j10)) & 4919131752989213764L) | (((((j3 * j10) ^ (j4 * j9)) ^ (j5 * j8)) ^ (j6 * j7)) & (-8608480567731124088L));
    }

    public static void multiply(byte[] bArr, byte[] bArr2) {
        long[] asLongs = asLongs(bArr);
        multiply(asLongs, asLongs(bArr2));
        asBytes(asLongs, bArr);
    }

    public static void multiply(int[] r17, int[] r18) {
        int r1 = r18[0];
        int r3 = r18[1];
        int r5 = r18[2];
        int r7 = r18[3];
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
        for (int r8 = 0; r8 < 4; r8++) {
            int r13 = r17[r8];
            for (int r14 = 0; r14 < 32; r14++) {
                int r15 = r13 >> 31;
                r13 <<= 1;
                r9 ^= r1 & r15;
                r10 ^= r3 & r15;
                r11 ^= r5 & r15;
                r12 ^= r15 & r7;
                r7 = (r7 >>> 1) | (r5 << 31);
                r5 = (r5 >>> 1) | (r3 << 31);
                r3 = (r3 >>> 1) | (r1 << 31);
                r1 = (r1 >>> 1) ^ (((r7 << 31) >> 8) & f2079E1);
            }
        }
        r17[0] = r9;
        r17[1] = r10;
        r17[2] = r11;
        r17[3] = r12;
    }

    public static void multiply(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr2[0];
        long j4 = jArr2[1];
        long reverse = Longs.reverse(j);
        long reverse2 = Longs.reverse(j2);
        long reverse3 = Longs.reverse(j3);
        long reverse4 = Longs.reverse(j4);
        long reverse5 = Longs.reverse(implMul64(reverse, reverse3));
        long implMul64 = implMul64(j, j3) << 1;
        long reverse6 = Longs.reverse(implMul64(reverse2, reverse4));
        long implMul642 = implMul64(j2, j4) << 1;
        long implMul643 = ((implMul64(j ^ j2, j3 ^ j4) << 1) ^ ((reverse6 ^ implMul64) ^ implMul642)) ^ ((implMul642 << 62) ^ (implMul642 << 57));
        long j5 = implMul643 >>> 7;
        long j6 = (implMul643 << 57) ^ ((implMul643 << 63) ^ (implMul643 << 62));
        jArr[0] = reverse5 ^ (j5 ^ ((implMul643 ^ (implMul643 >>> 1)) ^ (implMul643 >>> 2)));
        jArr[1] = j6 ^ ((Longs.reverse(implMul64(reverse ^ reverse2, reverse3 ^ reverse4)) ^ ((implMul64 ^ reverse5) ^ reverse6)) ^ (((implMul642 ^ (implMul642 >>> 1)) ^ (implMul642 >>> 2)) ^ (implMul642 >>> 7)));
    }

    public static void multiplyP(int[] r11) {
        int r1 = r11[0];
        int r3 = r11[1];
        int r5 = r11[2];
        int r7 = r11[3];
        r11[0] = (((r7 << 31) >> 31) & f2079E1) ^ (r1 >>> 1);
        r11[1] = (r3 >>> 1) | (r1 << 31);
        r11[2] = (r5 >>> 1) | (r3 << 31);
        r11[3] = (r7 >>> 1) | (r5 << 31);
    }

    public static void multiplyP(int[] r10, int[] r11) {
        int r1 = r10[0];
        int r3 = r10[1];
        int r5 = r10[2];
        int r102 = r10[3];
        r11[0] = (((r102 << 31) >> 31) & f2079E1) ^ (r1 >>> 1);
        r11[1] = (r3 >>> 1) | (r1 << 31);
        r11[2] = (r5 >>> 1) | (r3 << 31);
        r11[3] = (r102 >>> 1) | (r5 << 31);
    }

    public static void multiplyP(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        jArr[0] = (((j2 << 63) >> 63) & E1L) ^ (j >>> 1);
        jArr[1] = (j << 63) | (j2 >>> 1);
    }

    public static void multiplyP(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        jArr2[0] = (((j2 << 63) >> 63) & E1L) ^ (j >>> 1);
        jArr2[1] = (j << 63) | (j2 >>> 1);
    }

    public static void multiplyP3(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j2 << 61;
        jArr2[0] = (j3 >>> 7) ^ ((((j >>> 3) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2));
        jArr2[1] = (j << 61) | (j2 >>> 3);
    }

    public static void multiplyP4(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j2 << 60;
        jArr2[0] = (j3 >>> 7) ^ ((((j >>> 4) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2));
        jArr2[1] = (j << 60) | (j2 >>> 4);
    }

    public static void multiplyP7(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j2 << 57;
        jArr2[0] = (j3 >>> 7) ^ ((((j >>> 7) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2));
        jArr2[1] = (j << 57) | (j2 >>> 7);
    }

    public static void multiplyP8(int[] r11) {
        int r1 = r11[0];
        int r3 = r11[1];
        int r5 = r11[2];
        int r7 = r11[3];
        int r8 = r7 << 24;
        r11[0] = (r8 >>> 7) ^ ((((r1 >>> 8) ^ r8) ^ (r8 >>> 1)) ^ (r8 >>> 2));
        r11[1] = (r3 >>> 8) | (r1 << 24);
        r11[2] = (r5 >>> 8) | (r3 << 24);
        r11[3] = (r7 >>> 8) | (r5 << 24);
    }

    public static void multiplyP8(int[] r10, int[] r11) {
        int r1 = r10[0];
        int r3 = r10[1];
        int r5 = r10[2];
        int r102 = r10[3];
        int r7 = r102 << 24;
        r11[0] = (r7 >>> 7) ^ ((((r1 >>> 8) ^ r7) ^ (r7 >>> 1)) ^ (r7 >>> 2));
        r11[1] = (r3 >>> 8) | (r1 << 24);
        r11[2] = (r5 >>> 8) | (r3 << 24);
        r11[3] = (r102 >>> 8) | (r5 << 24);
    }

    public static void multiplyP8(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j2 << 56;
        jArr[0] = (j3 >>> 7) ^ ((((j >>> 8) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2));
        jArr[1] = (j << 56) | (j2 >>> 8);
    }

    public static void multiplyP8(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j2 << 56;
        jArr2[0] = (j3 >>> 7) ^ ((((j >>> 8) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2));
        jArr2[1] = (j << 56) | (j2 >>> 8);
    }

    public static byte[] oneAsBytes() {
        byte[] bArr = new byte[16];
        bArr[0] = Byte.MIN_VALUE;
        return bArr;
    }

    public static int[] oneAsInts() {
        int[] r0 = new int[4];
        r0[0] = Integer.MIN_VALUE;
        return r0;
    }

    public static long[] oneAsLongs() {
        return new long[]{Long.MIN_VALUE};
    }

    public static long[] pAsLongs() {
        return new long[]{com.google.common.primitives.Longs.MAX_POWER_OF_TWO};
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[4];
        Interleave.expand64To128Rev(jArr[0], jArr3, 0);
        Interleave.expand64To128Rev(jArr[1], jArr3, 2);
        long j = jArr3[0];
        long j2 = jArr3[1];
        long j3 = jArr3[2];
        long j4 = jArr3[3];
        long j5 = j3 ^ ((j4 << 57) ^ ((j4 << 63) ^ (j4 << 62)));
        long j6 = j ^ ((((j5 >>> 1) ^ j5) ^ (j5 >>> 2)) ^ (j5 >>> 7));
        jArr2[0] = j6;
        jArr2[1] = (j2 ^ ((((j4 >>> 1) ^ j4) ^ (j4 >>> 2)) ^ (j4 >>> 7))) ^ ((j5 << 57) ^ ((j5 << 63) ^ (j5 << 62)));
    }

    public static void xor(byte[] bArr, int r4, byte[] bArr2, int r6, int r7) {
        while (true) {
            r7--;
            if (r7 < 0) {
                return;
            }
            int r0 = r4 + r7;
            bArr[r0] = (byte) (bArr[r0] ^ bArr2[r6 + r7]);
        }
    }

    public static void xor(byte[] bArr, int r5, byte[] bArr2, int r7, byte[] bArr3, int r9) {
        int r0 = 0;
        do {
            bArr3[r9 + r0] = (byte) (bArr[r5 + r0] ^ bArr2[r7 + r0]);
            int r02 = r0 + 1;
            bArr3[r9 + r02] = (byte) (bArr[r5 + r02] ^ bArr2[r7 + r02]);
            int r03 = r02 + 1;
            bArr3[r9 + r03] = (byte) (bArr[r5 + r03] ^ bArr2[r7 + r03]);
            int r04 = r03 + 1;
            bArr3[r9 + r04] = (byte) (bArr[r5 + r04] ^ bArr2[r7 + r04]);
            r0 = r04 + 1;
        } while (r0 < 16);
    }

    public static void xor(byte[] bArr, byte[] bArr2) {
        int r0 = 0;
        do {
            bArr[r0] = (byte) (bArr[r0] ^ bArr2[r0]);
            int r02 = r0 + 1;
            bArr[r02] = (byte) (bArr[r02] ^ bArr2[r02]);
            int r03 = r02 + 1;
            bArr[r03] = (byte) (bArr[r03] ^ bArr2[r03]);
            int r04 = r03 + 1;
            bArr[r04] = (byte) (bArr[r04] ^ bArr2[r04]);
            r0 = r04 + 1;
        } while (r0 < 16);
    }

    public static void xor(byte[] bArr, byte[] bArr2, int r5) {
        int r0 = 0;
        do {
            bArr[r0] = (byte) (bArr[r0] ^ bArr2[r5 + r0]);
            int r02 = r0 + 1;
            bArr[r02] = (byte) (bArr[r02] ^ bArr2[r5 + r02]);
            int r03 = r02 + 1;
            bArr[r03] = (byte) (bArr[r03] ^ bArr2[r5 + r03]);
            int r04 = r03 + 1;
            bArr[r04] = (byte) (bArr[r04] ^ bArr2[r5 + r04]);
            r0 = r04 + 1;
        } while (r0 < 16);
    }

    public static void xor(byte[] bArr, byte[] bArr2, int r4, int r5) {
        while (true) {
            r5--;
            if (r5 < 0) {
                return;
            }
            bArr[r5] = (byte) (bArr[r5] ^ bArr2[r4 + r5]);
        }
    }

    public static void xor(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int r0 = 0;
        do {
            bArr3[r0] = (byte) (bArr[r0] ^ bArr2[r0]);
            int r02 = r0 + 1;
            bArr3[r02] = (byte) (bArr[r02] ^ bArr2[r02]);
            int r03 = r02 + 1;
            bArr3[r03] = (byte) (bArr[r03] ^ bArr2[r03]);
            int r04 = r03 + 1;
            bArr3[r04] = (byte) (bArr[r04] ^ bArr2[r04]);
            r0 = r04 + 1;
        } while (r0 < 16);
    }

    public static void xor(int[] r3, int[] r4) {
        r3[0] = r3[0] ^ r4[0];
        r3[1] = r3[1] ^ r4[1];
        r3[2] = r3[2] ^ r4[2];
        r3[3] = r4[3] ^ r3[3];
    }

    public static void xor(int[] r3, int[] r4, int[] r5) {
        r5[0] = r3[0] ^ r4[0];
        r5[1] = r3[1] ^ r4[1];
        r5[2] = r3[2] ^ r4[2];
        r5[3] = r3[3] ^ r4[3];
    }

    public static void xor(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] ^ jArr2[0];
        jArr[1] = jArr[1] ^ jArr2[1];
    }

    public static void xor(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr2[1] ^ jArr[1];
    }
}
