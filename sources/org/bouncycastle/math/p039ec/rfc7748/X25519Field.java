package org.bouncycastle.math.p039ec.rfc7748;

import com.google.common.base.Ascii;
import org.bouncycastle.math.raw.Mod;

/* renamed from: org.bouncycastle.math.ec.rfc7748.X25519Field */
/* loaded from: classes5.dex */
public abstract class X25519Field {
    private static final int M24 = 16777215;
    private static final int M25 = 33554431;
    private static final int M26 = 67108863;
    private static final int[] P32 = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] ROOT_NEG_ONE = {34513072, 59165138, 4688974, 3500415, 6194736, 33281959, 54535759, 32551604, 163342, 5703241};
    public static final int SIZE = 10;

    public static void add(int[] r3, int[] r4, int[] r5) {
        for (int r0 = 0; r0 < 10; r0++) {
            r5[r0] = r3[r0] + r4[r0];
        }
    }

    public static void addOne(int[] r2) {
        r2[0] = r2[0] + 1;
    }

    public static void addOne(int[] r1, int r2) {
        r1[r2] = r1[r2] + 1;
    }

    public static void apm(int[] r4, int[] r5, int[] r6, int[] r7) {
        for (int r0 = 0; r0 < 10; r0++) {
            int r1 = r4[r0];
            int r2 = r5[r0];
            r6[r0] = r1 + r2;
            r7[r0] = r1 - r2;
        }
    }

    public static int areEqual(int[] r4, int[] r5) {
        int r1 = 0;
        for (int r0 = 0; r0 < 10; r0++) {
            r1 |= r4[r0] ^ r5[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static boolean areEqualVar(int[] r0, int[] r1) {
        return areEqual(r0, r1) != 0;
    }

    public static void carry(int[] r23) {
        int r1 = r23[0];
        int r3 = r23[1];
        int r5 = r23[2];
        int r7 = r23[3];
        int r9 = r23[4];
        int r11 = r23[5];
        int r13 = r23[6];
        int r15 = r23[7];
        int r17 = r23[8];
        int r19 = r23[9];
        int r52 = r5 + (r3 >> 26);
        int r32 = r3 & M26;
        int r92 = r9 + (r7 >> 26);
        int r72 = r7 & M26;
        int r152 = r15 + (r13 >> 26);
        int r132 = r13 & M26;
        int r192 = r19 + (r17 >> 26);
        int r172 = r17 & M26;
        int r73 = r72 + (r52 >> 25);
        int r53 = r52 & M25;
        int r112 = r11 + (r92 >> 25);
        int r93 = r92 & M25;
        int r173 = r172 + (r152 >> 25);
        int r153 = r152 & M25;
        int r12 = r1 + ((r192 >> 25) * 38);
        int r193 = r192 & M25;
        int r33 = r32 + (r12 >> 26);
        int r14 = r12 & M26;
        int r133 = r132 + (r112 >> 26);
        int r113 = r112 & M26;
        int r54 = r53 + (r33 >> 26);
        int r34 = r33 & M26;
        int r94 = r93 + (r73 >> 26);
        int r74 = r73 & M26;
        int r154 = r153 + (r133 >> 26);
        int r134 = r133 & M26;
        int r174 = r173 & M26;
        r23[0] = r14;
        r23[1] = r34;
        r23[2] = r54;
        r23[3] = r74;
        r23[4] = r94;
        r23[5] = r113;
        r23[6] = r134;
        r23[7] = r154;
        r23[8] = r174;
        r23[9] = r193 + (r173 >> 26);
    }

    public static void cmov(int r4, int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 0; r0 < 10; r0++) {
            int r1 = r8 + r0;
            int r2 = r7[r1];
            r7[r1] = r2 ^ ((r5[r6 + r0] ^ r2) & r4);
        }
    }

    public static void cnegate(int r2, int[] r3) {
        int r22 = 0 - r2;
        for (int r0 = 0; r0 < 10; r0++) {
            r3[r0] = (r3[r0] ^ r22) - r22;
        }
    }

    public static void copy(int[] r3, int r4, int[] r5, int r6) {
        for (int r0 = 0; r0 < 10; r0++) {
            r5[r6 + r0] = r3[r4 + r0];
        }
    }

    public static int[] create() {
        return new int[10];
    }

    public static int[] createTable(int r0) {
        return new int[r0 * 10];
    }

    public static void cswap(int r4, int[] r5, int[] r6) {
        int r42 = 0 - r4;
        for (int r0 = 0; r0 < 10; r0++) {
            int r1 = r5[r0];
            int r2 = r6[r0];
            int r3 = (r1 ^ r2) & r42;
            r5[r0] = r1 ^ r3;
            r6[r0] = r2 ^ r3;
        }
    }

    public static void decode(byte[] bArr, int r2, int[] r3) {
        decode128(bArr, r2, r3, 0);
        decode128(bArr, r2 + 16, r3, 5);
        r3[9] = r3[9] & 16777215;
    }

    public static void decode(int[] r1, int r2, int[] r3) {
        decode128(r1, r2, r3, 0);
        decode128(r1, r2 + 4, r3, 5);
        r3[9] = r3[9] & 16777215;
    }

    private static void decode128(byte[] bArr, int r6, int[] r7, int r8) {
        int decode32 = decode32(bArr, r6 + 0);
        int decode322 = decode32(bArr, r6 + 4);
        int decode323 = decode32(bArr, r6 + 8);
        int decode324 = decode32(bArr, r6 + 12);
        r7[r8 + 0] = decode32 & M26;
        r7[r8 + 1] = ((decode32 >>> 26) | (decode322 << 6)) & M26;
        r7[r8 + 2] = ((decode323 << 12) | (decode322 >>> 20)) & M25;
        r7[r8 + 3] = ((decode324 << 19) | (decode323 >>> 13)) & M26;
        r7[r8 + 4] = decode324 >>> 7;
    }

    private static void decode128(int[] r5, int r6, int[] r7, int r8) {
        int r0 = r5[r6 + 0];
        int r1 = r5[r6 + 1];
        int r2 = r5[r6 + 2];
        int r52 = r5[r6 + 3];
        r7[r8 + 0] = r0 & M26;
        r7[r8 + 1] = ((r0 >>> 26) | (r1 << 6)) & M26;
        r7[r8 + 2] = ((r2 << 12) | (r1 >>> 20)) & M25;
        r7[r8 + 3] = ((r52 << 19) | (r2 >>> 13)) & M26;
        r7[r8 + 4] = r52 >>> 7;
    }

    private static int decode32(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] << Ascii.CAN) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8) | ((bArr[r33] & 255) << 16);
    }

    public static void encode(int[] r1, byte[] bArr, int r3) {
        encode128(r1, 0, bArr, r3);
        encode128(r1, 5, bArr, r3 + 16);
    }

    public static void encode(int[] r1, int[] r2, int r3) {
        encode128(r1, 0, r2, r3);
        encode128(r1, 5, r2, r3 + 4);
    }

    private static void encode128(int[] r4, int r5, byte[] bArr, int r7) {
        int r0 = r4[r5 + 0];
        int r1 = r4[r5 + 1];
        int r2 = r4[r5 + 2];
        int r3 = r4[r5 + 3];
        int r42 = r4[r5 + 4];
        encode32((r1 << 26) | r0, bArr, r7 + 0);
        encode32((r1 >>> 6) | (r2 << 20), bArr, r7 + 4);
        encode32((r2 >>> 12) | (r3 << 13), bArr, r7 + 8);
        encode32((r42 << 7) | (r3 >>> 19), bArr, r7 + 12);
    }

    private static void encode128(int[] r5, int r6, int[] r7, int r8) {
        int r0 = r5[r6 + 0];
        int r1 = r5[r6 + 1];
        int r2 = r5[r6 + 2];
        int r3 = r5[r6 + 3];
        int r52 = r5[r6 + 4];
        r7[r8 + 0] = r0 | (r1 << 26);
        r7[r8 + 1] = (r1 >>> 6) | (r2 << 20);
        r7[r8 + 2] = (r2 >>> 12) | (r3 << 13);
        r7[r8 + 3] = (r52 << 7) | (r3 >>> 19);
    }

    private static void encode32(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) r1;
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 8);
        int r33 = r32 + 1;
        bArr[r33] = (byte) (r1 >>> 16);
        bArr[r33 + 1] = (byte) (r1 >>> 24);
    }

    public static void inv(int[] r3, int[] r4) {
        int[] create = create();
        int[] r1 = new int[8];
        copy(r3, 0, create, 0);
        normalize(create);
        encode(create, r1, 0);
        Mod.modOddInverse(P32, r1, r1);
        decode(r1, 0, r4);
    }

    public static void invVar(int[] r3, int[] r4) {
        int[] create = create();
        int[] r1 = new int[8];
        copy(r3, 0, create, 0);
        normalize(create);
        encode(create, r1, 0);
        Mod.modOddInverseVar(P32, r1, r1);
        decode(r1, 0, r4);
    }

    public static int isOne(int[] r4) {
        int r0 = r4[0] ^ 1;
        for (int r2 = 1; r2 < 10; r2++) {
            r0 |= r4[r2];
        }
        return (((r0 >>> 1) | (r0 & 1)) - 1) >> 31;
    }

    public static boolean isOneVar(int[] r0) {
        return isOne(r0) != 0;
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 10; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] r0) {
        return isZero(r0) != 0;
    }

    public static void mul(int[] r25, int r26, int[] r27) {
        int r1 = r25[0];
        int r3 = r25[1];
        int r5 = r25[2];
        int r7 = r25[3];
        int r9 = r25[4];
        int r11 = r25[5];
        int r13 = r25[6];
        int r15 = r25[7];
        int r14 = r25[8];
        int r8 = r25[9];
        long j = r26;
        long j2 = r5 * j;
        int r12 = ((int) j2) & M25;
        long j3 = r9 * j;
        int r92 = ((int) j3) & M25;
        long j4 = r15 * j;
        int r152 = ((int) j4) & M25;
        long j5 = r8 * j;
        int r82 = ((int) j5) & M25;
        long j6 = ((j5 >> 25) * 38) + (r1 * j);
        r27[0] = ((int) j6) & M26;
        long j7 = (j3 >> 25) + (r11 * j);
        r27[5] = ((int) j7) & M26;
        long j8 = (j6 >> 26) + (r3 * j);
        r27[1] = ((int) j8) & M26;
        long j9 = (j2 >> 25) + (r7 * j);
        r27[3] = ((int) j9) & M26;
        long j10 = (j7 >> 26) + (r13 * j);
        r27[6] = ((int) j10) & M26;
        long j11 = (j4 >> 25) + (r14 * j);
        r27[8] = ((int) j11) & M26;
        r27[2] = r12 + ((int) (j8 >> 26));
        r27[4] = r92 + ((int) (j9 >> 26));
        r27[7] = r152 + ((int) (j10 >> 26));
        r27[9] = r82 + ((int) (j11 >> 26));
    }

    public static void mul(int[] r73, int[] r74, int[] r75) {
        int r1 = r73[0];
        int r2 = r74[0];
        int r4 = r73[1];
        int r5 = r74[1];
        int r7 = r73[2];
        int r8 = r74[2];
        int r10 = r73[3];
        int r11 = r74[3];
        int r13 = r73[4];
        int r14 = r74[4];
        int r12 = r73[5];
        int r9 = r74[5];
        int r15 = r73[6];
        int r6 = r74[6];
        int r0 = r73[7];
        int r3 = r74[7];
        int r02 = r73[8];
        int r03 = r74[8];
        int r04 = r73[9];
        int r05 = r74[9];
        long j = r1;
        long j2 = r2;
        long j3 = j * j2;
        long j4 = r5;
        long j5 = r4;
        long j6 = r8;
        long j7 = r7;
        long j8 = (j * j6) + (j5 * j4) + (j7 * j2);
        long j9 = r11;
        long j10 = j * j9;
        long j11 = r10;
        long j12 = (((j5 * j6) + (j7 * j4)) << 1) + j10 + (j11 * j2);
        long j13 = r14;
        long j14 = r13;
        long j15 = ((j7 * j6) << 1) + (j * j13) + (j5 * j9) + (j11 * j4) + (j2 * j14);
        long j16 = ((((j5 * j13) + (j7 * j9)) + (j11 * j6)) + (j14 * j4)) << 1;
        long j17 = (((j7 * j13) + (j14 * j6)) << 1) + (j11 * j9);
        long j18 = (j11 * j13) + (j14 * j9);
        long j19 = (j14 * j13) << 1;
        long j20 = r12;
        long j21 = r9;
        long j22 = r6;
        long j23 = r15;
        long j24 = r3;
        long j25 = r0;
        long j26 = (j20 * j24) + (j23 * j22) + (j25 * j21);
        long j27 = r03;
        long j28 = r02;
        long j29 = (((j23 * j24) + (j25 * j22)) << 1) + (j20 * j27) + (j28 * j21);
        long j30 = r05;
        long j31 = r04;
        long j32 = ((j25 * j24) << 1) + (j20 * j30) + (j23 * j27) + (j28 * j22) + (j21 * j31);
        long j33 = j3 - (((((j23 * j30) + (j25 * j27)) + (j28 * j24)) + (j31 * j22)) * 76);
        long j34 = ((j * j4) + (j5 * j2)) - (((((j25 * j30) + (j31 * j24)) << 1) + (j28 * j27)) * 38);
        long j35 = j8 - (((j28 * j30) + (j27 * j31)) * 38);
        long j36 = j12 - ((j31 * j30) * 76);
        long j37 = j16 - (j20 * j21);
        long j38 = j17 - ((j20 * j22) + (j23 * j21));
        long j39 = j18 - j26;
        long j40 = j19 - j29;
        int r42 = r1 + r12;
        int r112 = r4 + r15;
        int r152 = r7 + r0;
        int r06 = r8 + r3;
        int r16 = r10 + r02;
        int r132 = r13 + r04;
        long j41 = r42;
        long j42 = r2 + r9;
        long j43 = r5 + r6;
        long j44 = r112;
        long j45 = (j41 * j43) + (j44 * j42);
        long j46 = r06;
        long j47 = r152;
        long j48 = (j41 * j46) + (j44 * j43) + (j47 * j42);
        long j49 = r11 + r03;
        long j50 = r16;
        long j51 = r14 + r05;
        long j52 = r132;
        long j53 = (((j47 * j51) + (j52 * j46)) << 1) + (j50 * j49);
        long j54 = j40 + (((((j44 * j46) + (j47 * j43)) << 1) + ((j41 * j49) + (j50 * j42))) - j36);
        int r07 = ((int) j54) & M26;
        long j55 = (j54 >> 26) + (((((j47 * j46) << 1) + ((((j41 * j51) + (j44 * j49)) + (j50 * j43)) + (j42 * j52))) - j15) - j32);
        int r133 = ((int) j55) & M25;
        long j56 = j33 + ((((j55 >> 25) + (((((j44 * j51) + (j47 * j49)) + (j50 * j46)) + (j52 * j43)) << 1)) - j37) * 38);
        r75[0] = ((int) j56) & M26;
        long j57 = (j56 >> 26) + j34 + ((j53 - j38) * 38);
        r75[1] = ((int) j57) & M26;
        long j58 = (j57 >> 26) + j35 + ((((j50 * j51) + (j52 * j49)) - j39) * 38);
        r75[2] = ((int) j58) & M25;
        long j59 = (j58 >> 25) + j36 + ((((j52 * j51) << 1) - j40) * 38);
        r75[3] = ((int) j59) & M26;
        long j60 = (j59 >> 26) + j15 + (j32 * 38);
        r75[4] = ((int) j60) & M25;
        long j61 = (j60 >> 25) + j37 + ((j41 * j42) - j33);
        r75[5] = ((int) j61) & M26;
        long j62 = (j61 >> 26) + j38 + (j45 - j34);
        r75[6] = ((int) j62) & M26;
        long j63 = (j62 >> 26) + j39 + (j48 - j35);
        r75[7] = ((int) j63) & M25;
        long j64 = (j63 >> 25) + r07;
        r75[8] = ((int) j64) & M26;
        r75[9] = r133 + ((int) (j64 >> 26));
    }

    public static void negate(int[] r2, int[] r3) {
        for (int r0 = 0; r0 < 10; r0++) {
            r3[r0] = -r2[r0];
        }
    }

    public static void normalize(int[] r1) {
        int r0 = (r1[9] >>> 23) & 1;
        reduce(r1, r0);
        reduce(r1, -r0);
    }

    public static void one(int[] r3) {
        r3[0] = 1;
        for (int r1 = 1; r1 < 10; r1++) {
            r3[r1] = 0;
        }
    }

    private static void powPm5d8(int[] r4, int[] r5, int[] r6) {
        sqr(r4, r5);
        mul(r4, r5, r5);
        int[] create = create();
        sqr(r5, create);
        mul(r4, create, create);
        sqr(create, 2, create);
        mul(r5, create, create);
        int[] create2 = create();
        sqr(create, 5, create2);
        mul(create, create2, create2);
        int[] create3 = create();
        sqr(create2, 5, create3);
        mul(create, create3, create3);
        sqr(create3, 10, create);
        mul(create2, create, create);
        sqr(create, 25, create2);
        mul(create, create2, create2);
        sqr(create2, 25, create3);
        mul(create, create3, create3);
        sqr(create3, 50, create);
        mul(create2, create, create);
        sqr(create, 125, create2);
        mul(create, create2, create2);
        sqr(create2, 2, create);
        mul(create, r4, r6);
    }

    private static void reduce(int[] r10, int r11) {
        int r1 = r10[9];
        long j = (((r1 >> 24) + r11) * 19) + r10[0];
        r10[0] = ((int) j) & M26;
        long j2 = (j >> 26) + r10[1];
        r10[1] = ((int) j2) & M26;
        long j3 = (j2 >> 26) + r10[2];
        r10[2] = ((int) j3) & M25;
        long j4 = (j3 >> 25) + r10[3];
        r10[3] = ((int) j4) & M26;
        long j5 = (j4 >> 26) + r10[4];
        r10[4] = ((int) j5) & M25;
        long j6 = (j5 >> 25) + r10[5];
        r10[5] = ((int) j6) & M26;
        long j7 = (j6 >> 26) + r10[6];
        r10[6] = ((int) j7) & M26;
        long j8 = (j7 >> 26) + r10[7];
        r10[7] = M25 & ((int) j8);
        long j9 = (j8 >> 25) + r10[8];
        r10[8] = M26 & ((int) j9);
        r10[9] = (16777215 & r1) + ((int) (j9 >> 26));
    }

    public static void sqr(int[] r0, int r1, int[] r2) {
        sqr(r0, r2);
        while (true) {
            r1--;
            if (r1 <= 0) {
                return;
            }
            sqr(r2, r2);
        }
    }

    public static void sqr(int[] r55, int[] r56) {
        int r1 = r55[0];
        int r3 = r55[1];
        int r5 = r55[2];
        int r7 = r55[3];
        int r9 = r55[4];
        int r11 = r55[5];
        int r13 = r55[6];
        int r15 = r55[7];
        int r14 = r55[8];
        int r12 = r55[9];
        long j = r1;
        long j2 = j * j;
        long j3 = r3 * 2;
        long j4 = j * j3;
        long j5 = r5 * 2;
        long j6 = r3;
        long j7 = (j * j5) + (j6 * j6);
        long j8 = r7 * 2;
        long j9 = r9 * 2;
        long j10 = (r5 * j5) + (j * j9) + (j6 * j8);
        long j11 = (j3 * j9) + (j8 * j5);
        long j12 = r7;
        long j13 = (j5 * j9) + (j12 * j12);
        long j14 = j12 * j9;
        long j15 = r9 * j9;
        int r52 = r12 * 2;
        long j16 = r11;
        long j17 = j16 * j16;
        long j18 = r13 * 2;
        long j19 = j16 * j18;
        long j20 = r15 * 2;
        long j21 = r13;
        long j22 = (j16 * j20) + (j21 * j21);
        long j23 = r14 * 2;
        long j24 = r52;
        long j25 = (r15 * j20) + (j16 * j24) + (j21 * j23);
        long j26 = r14;
        long j27 = j2 - (((j18 * j24) + (j23 * j20)) * 38);
        long j28 = j4 - (((j20 * j24) + (j26 * j26)) * 38);
        long j29 = j7 - ((j26 * j24) * 38);
        long j30 = ((j3 * j5) + (j * j8)) - ((r12 * j24) * 38);
        long j31 = j11 - j17;
        long j32 = j13 - j19;
        long j33 = j14 - j22;
        long j34 = j15 - ((j18 * j20) + (j16 * j23));
        int r152 = r3 + r13;
        int r4 = r5 + r15;
        int r2 = r7 + r14;
        int r32 = r9 + r12;
        long j35 = r1 + r11;
        long j36 = j35 * j35;
        long j37 = r152 * 2;
        long j38 = j35 * j37;
        long j39 = r4 * 2;
        long j40 = r152;
        long j41 = (j35 * j39) + (j40 * j40);
        long j42 = r2 * 2;
        long j43 = r32 * 2;
        long j44 = (j37 * j43) + (j42 * j39);
        long j45 = r2;
        long j46 = (j39 * j43) + (j45 * j45);
        long j47 = j45 * j43;
        long j48 = r32 * j43;
        long j49 = j34 + (((j37 * j39) + (j35 * j42)) - j30);
        int r6 = ((int) j49) & M26;
        long j50 = (j49 >> 26) + (((((r4 * j39) + (j35 * j43)) + (j40 * j42)) - j10) - j25);
        int r42 = ((int) j50) & M25;
        long j51 = j27 + ((((j50 >> 25) + j44) - j31) * 38);
        r56[0] = ((int) j51) & M26;
        long j52 = (j51 >> 26) + j28 + ((j46 - j32) * 38);
        r56[1] = ((int) j52) & M26;
        long j53 = (j52 >> 26) + j29 + ((j47 - j33) * 38);
        r56[2] = ((int) j53) & M25;
        long j54 = (j53 >> 25) + j30 + ((j48 - j34) * 38);
        r56[3] = ((int) j54) & M26;
        long j55 = (j54 >> 26) + j10 + (38 * j25);
        r56[4] = ((int) j55) & M25;
        long j56 = (j55 >> 25) + j31 + (j36 - j27);
        r56[5] = ((int) j56) & M26;
        long j57 = (j56 >> 26) + j32 + (j38 - j28);
        r56[6] = ((int) j57) & M26;
        long j58 = (j57 >> 26) + j33 + (j41 - j29);
        r56[7] = ((int) j58) & M25;
        long j59 = (j58 >> 25) + r6;
        r56[8] = ((int) j59) & M26;
        r56[9] = r42 + ((int) (j59 >> 26));
    }

    public static boolean sqrtRatioVar(int[] r5, int[] r6, int[] r7) {
        int[] create = create();
        int[] create2 = create();
        mul(r5, r6, create);
        sqr(r6, create2);
        mul(create, create2, create);
        sqr(create2, create2);
        mul(create2, create, create2);
        int[] create3 = create();
        int[] create4 = create();
        powPm5d8(create2, create3, create4);
        mul(create4, create, create4);
        int[] create5 = create();
        sqr(create4, create5);
        mul(create5, r6, create5);
        sub(create5, r5, create3);
        normalize(create3);
        if (isZeroVar(create3)) {
            copy(create4, 0, r7, 0);
            return true;
        }
        add(create5, r5, create3);
        normalize(create3);
        if (isZeroVar(create3)) {
            mul(create4, ROOT_NEG_ONE, r7);
            return true;
        }
        return false;
    }

    public static void sub(int[] r3, int[] r4, int[] r5) {
        for (int r0 = 0; r0 < 10; r0++) {
            r5[r0] = r3[r0] - r4[r0];
        }
    }

    public static void subOne(int[] r2) {
        r2[0] = r2[0] - 1;
    }

    public static void zero(int[] r3) {
        for (int r1 = 0; r1 < 10; r1++) {
            r3[r1] = 0;
        }
    }
}
