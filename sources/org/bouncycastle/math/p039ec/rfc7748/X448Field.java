package org.bouncycastle.math.p039ec.rfc7748;

import com.google.common.base.Ascii;
import org.bouncycastle.math.raw.Mod;

/* renamed from: org.bouncycastle.math.ec.rfc7748.X448Field */
/* loaded from: classes5.dex */
public abstract class X448Field {
    private static final int M28 = 268435455;
    private static final int[] P32 = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    public static final int SIZE = 16;
    private static final long U32 = 4294967295L;

    public static void add(int[] r3, int[] r4, int[] r5) {
        for (int r0 = 0; r0 < 16; r0++) {
            r5[r0] = r3[r0] + r4[r0];
        }
    }

    public static void addOne(int[] r2) {
        r2[0] = r2[0] + 1;
    }

    public static void addOne(int[] r1, int r2) {
        r1[r2] = r1[r2] + 1;
    }

    public static int areEqual(int[] r4, int[] r5) {
        int r1 = 0;
        for (int r0 = 0; r0 < 16; r0++) {
            r1 |= r4[r0] ^ r5[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static boolean areEqualVar(int[] r0, int[] r1) {
        return areEqual(r0, r1) != 0;
    }

    public static void carry(int[] r34) {
        int r1 = r34[0];
        int r3 = r34[1];
        int r5 = r34[2];
        int r7 = r34[3];
        int r9 = r34[4];
        int r11 = r34[5];
        int r13 = r34[6];
        int r15 = r34[7];
        int r17 = r34[8];
        int r19 = r34[9];
        int r21 = r34[10];
        int r23 = r34[11];
        int r25 = r34[12];
        int r27 = r34[13];
        int r29 = r34[14];
        int r31 = r34[15];
        int r32 = r3 + (r1 >>> 28);
        int r12 = r1 & M28;
        int r112 = r11 + (r9 >>> 28);
        int r92 = r9 & M28;
        int r192 = r19 + (r17 >>> 28);
        int r172 = r17 & M28;
        int r272 = r27 + (r25 >>> 28);
        int r252 = r25 & M28;
        int r52 = r5 + (r32 >>> 28);
        int r33 = r32 & M28;
        int r132 = r13 + (r112 >>> 28);
        int r113 = r112 & M28;
        int r212 = r21 + (r192 >>> 28);
        int r193 = r192 & M28;
        int r292 = r29 + (r272 >>> 28);
        int r273 = r272 & M28;
        int r72 = r7 + (r52 >>> 28);
        int r53 = r52 & M28;
        int r152 = r15 + (r132 >>> 28);
        int r133 = r132 & M28;
        int r232 = r23 + (r212 >>> 28);
        int r213 = r212 & M28;
        int r312 = r31 + (r292 >>> 28);
        int r293 = r292 & M28;
        int r332 = r312 >>> 28;
        int r313 = r312 & M28;
        int r14 = r12 + r332;
        int r93 = r92 + (r72 >>> 28);
        int r73 = r72 & M28;
        int r173 = r172 + r332 + (r152 >>> 28);
        int r153 = r152 & M28;
        int r253 = r252 + (r232 >>> 28);
        int r233 = r232 & M28;
        int r35 = r33 + (r14 >>> 28);
        int r16 = r14 & M28;
        int r114 = r113 + (r93 >>> 28);
        int r94 = r93 & M28;
        int r194 = r193 + (r173 >>> 28);
        int r174 = r173 & M28;
        int r254 = r253 & M28;
        r34[0] = r16;
        r34[1] = r35;
        r34[2] = r53;
        r34[3] = r73;
        r34[4] = r94;
        r34[5] = r114;
        r34[6] = r133;
        r34[7] = r153;
        r34[8] = r174;
        r34[9] = r194;
        r34[10] = r213;
        r34[11] = r233;
        r34[12] = r254;
        r34[13] = r273 + (r253 >>> 28);
        r34[14] = r293;
        r34[15] = r313;
    }

    public static void cmov(int r4, int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 0; r0 < 16; r0++) {
            int r1 = r8 + r0;
            int r2 = r7[r1];
            r7[r1] = r2 ^ ((r5[r6 + r0] ^ r2) & r4);
        }
    }

    public static void cnegate(int r2, int[] r3) {
        int[] create = create();
        sub(create, r3, create);
        cmov(-r2, create, 0, r3, 0);
    }

    public static void copy(int[] r3, int r4, int[] r5, int r6) {
        for (int r0 = 0; r0 < 16; r0++) {
            r5[r6 + r0] = r3[r4 + r0];
        }
    }

    public static int[] create() {
        return new int[16];
    }

    public static int[] createTable(int r0) {
        return new int[r0 * 16];
    }

    public static void cswap(int r4, int[] r5, int[] r6) {
        int r42 = 0 - r4;
        for (int r0 = 0; r0 < 16; r0++) {
            int r1 = r5[r0];
            int r2 = r6[r0];
            int r3 = (r1 ^ r2) & r42;
            r5[r0] = r1 ^ r3;
            r6[r0] = r2 ^ r3;
        }
    }

    public static void decode(byte[] bArr, int r3, int[] r4) {
        decode56(bArr, r3, r4, 0);
        decode56(bArr, r3 + 7, r4, 2);
        decode56(bArr, r3 + 14, r4, 4);
        decode56(bArr, r3 + 21, r4, 6);
        decode56(bArr, r3 + 28, r4, 8);
        decode56(bArr, r3 + 35, r4, 10);
        decode56(bArr, r3 + 42, r4, 12);
        decode56(bArr, r3 + 49, r4, 14);
    }

    public static void decode(int[] r1, int r2, int[] r3) {
        decode224(r1, r2, r3, 0);
        decode224(r1, r2 + 7, r3, 8);
    }

    private static void decode224(int[] r8, int r9, int[] r10, int r11) {
        int r0 = r8[r9 + 0];
        int r1 = r8[r9 + 1];
        int r2 = r8[r9 + 2];
        int r3 = r8[r9 + 3];
        int r4 = r8[r9 + 4];
        int r5 = r8[r9 + 5];
        int r82 = r8[r9 + 6];
        r10[r11 + 0] = r0 & M28;
        r10[r11 + 1] = ((r0 >>> 28) | (r1 << 4)) & M28;
        r10[r11 + 2] = ((r1 >>> 24) | (r2 << 8)) & M28;
        r10[r11 + 3] = ((r2 >>> 20) | (r3 << 12)) & M28;
        r10[r11 + 4] = ((r3 >>> 16) | (r4 << 16)) & M28;
        r10[r11 + 5] = ((r4 >>> 12) | (r5 << 20)) & M28;
        r10[r11 + 6] = ((r5 >>> 8) | (r82 << 24)) & M28;
        r10[r11 + 7] = r82 >>> 4;
    }

    private static int decode24(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        return ((bArr[r32 + 1] & 255) << 16) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] << Ascii.CAN) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8) | ((bArr[r33] & 255) << 16);
    }

    private static void decode56(byte[] bArr, int r2, int[] r3, int r4) {
        int decode32 = decode32(bArr, r2);
        int decode24 = decode24(bArr, r2 + 4);
        r3[r4] = M28 & decode32;
        r3[r4 + 1] = (decode24 << 4) | (decode32 >>> 28);
    }

    public static void encode(int[] r2, byte[] bArr, int r4) {
        encode56(r2, 0, bArr, r4);
        encode56(r2, 2, bArr, r4 + 7);
        encode56(r2, 4, bArr, r4 + 14);
        encode56(r2, 6, bArr, r4 + 21);
        encode56(r2, 8, bArr, r4 + 28);
        encode56(r2, 10, bArr, r4 + 35);
        encode56(r2, 12, bArr, r4 + 42);
        encode56(r2, 14, bArr, r4 + 49);
    }

    public static void encode(int[] r1, int[] r2, int r3) {
        encode224(r1, 0, r2, r3);
        encode224(r1, 8, r2, r3 + 7);
    }

    private static void encode224(int[] r8, int r9, int[] r10, int r11) {
        int r0 = r8[r9 + 0];
        int r1 = r8[r9 + 1];
        int r2 = r8[r9 + 2];
        int r3 = r8[r9 + 3];
        int r4 = r8[r9 + 4];
        int r5 = r8[r9 + 5];
        int r6 = r8[r9 + 6];
        int r82 = r8[r9 + 7];
        r10[r11 + 0] = r0 | (r1 << 28);
        r10[r11 + 1] = (r1 >>> 4) | (r2 << 24);
        r10[r11 + 2] = (r2 >>> 8) | (r3 << 20);
        r10[r11 + 3] = (r3 >>> 12) | (r4 << 16);
        r10[r11 + 4] = (r4 >>> 16) | (r5 << 12);
        r10[r11 + 5] = (r5 >>> 20) | (r6 << 8);
        r10[r11 + 6] = (r82 << 4) | (r6 >>> 24);
    }

    private static void encode24(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) r1;
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 8);
        bArr[r32 + 1] = (byte) (r1 >>> 16);
    }

    private static void encode32(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) r1;
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 8);
        int r33 = r32 + 1;
        bArr[r33] = (byte) (r1 >>> 16);
        bArr[r33 + 1] = (byte) (r1 >>> 24);
    }

    private static void encode56(int[] r1, int r2, byte[] bArr, int r4) {
        int r0 = r1[r2];
        int r12 = r1[r2 + 1];
        encode32((r12 << 28) | r0, bArr, r4);
        encode24(r12 >>> 4, bArr, r4 + 4);
    }

    public static void inv(int[] r3, int[] r4) {
        int[] create = create();
        int[] r1 = new int[14];
        copy(r3, 0, create, 0);
        normalize(create);
        encode(create, r1, 0);
        Mod.modOddInverse(P32, r1, r1);
        decode(r1, 0, r4);
    }

    public static void invVar(int[] r3, int[] r4) {
        int[] create = create();
        int[] r1 = new int[14];
        copy(r3, 0, create, 0);
        normalize(create);
        encode(create, r1, 0);
        Mod.modOddInverseVar(P32, r1, r1);
        decode(r1, 0, r4);
    }

    public static int isOne(int[] r4) {
        int r0 = r4[0] ^ 1;
        for (int r2 = 1; r2 < 16; r2++) {
            r0 |= r4[r2];
        }
        return (((r0 >>> 1) | (r0 & 1)) - 1) >> 31;
    }

    public static boolean isOneVar(int[] r0) {
        return isOne(r0) != 0;
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 16; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] r0) {
        return isZero(r0) != 0;
    }

    public static void mul(int[] r36, int r37, int[] r38) {
        int r1 = r36[0];
        int r3 = r36[1];
        int r5 = r36[2];
        int r7 = r36[3];
        int r9 = r36[4];
        int r11 = r36[5];
        int r13 = r36[6];
        int r15 = r36[7];
        int r10 = r36[8];
        int r2 = r36[9];
        int r0 = r36[10];
        int r8 = r36[11];
        int r14 = r36[12];
        int r6 = r36[13];
        int r12 = r36[14];
        int r4 = r36[15];
        long j = r3;
        long j2 = r37;
        long j3 = j * j2;
        int r16 = ((int) j3) & M28;
        long j4 = r11 * j2;
        int r112 = ((int) j4) & M28;
        long j5 = r2 * j2;
        int r113 = ((int) j5) & M28;
        long j6 = r6 * j2;
        int r114 = ((int) j6) & M28;
        long j7 = (j3 >>> 28) + (r5 * j2);
        r38[2] = ((int) j7) & M28;
        long j8 = (j4 >>> 28) + (r13 * j2);
        r38[6] = ((int) j8) & M28;
        long j9 = (j5 >>> 28) + (r0 * j2);
        r38[10] = ((int) j9) & M28;
        long j10 = (j6 >>> 28) + (r12 * j2);
        r38[14] = ((int) j10) & M28;
        long j11 = (j7 >>> 28) + (r7 * j2);
        r38[3] = ((int) j11) & M28;
        long j12 = (j8 >>> 28) + (r15 * j2);
        r38[7] = ((int) j12) & M28;
        long j13 = (j9 >>> 28) + (r8 * j2);
        r38[11] = ((int) j13) & M28;
        long j14 = (j10 >>> 28) + (r4 * j2);
        r38[15] = ((int) j14) & M28;
        long j15 = j14 >>> 28;
        long j16 = (j11 >>> 28) + (r9 * j2);
        r38[4] = ((int) j16) & M28;
        long j17 = (j12 >>> 28) + j15 + (r10 * j2);
        r38[8] = ((int) j17) & M28;
        long j18 = (j13 >>> 28) + (r14 * j2);
        r38[12] = ((int) j18) & M28;
        long j19 = j15 + (r1 * j2);
        r38[0] = ((int) j19) & M28;
        r38[1] = r16 + ((int) (j19 >>> 28));
        r38[5] = r112 + ((int) (j16 >>> 28));
        r38[9] = r113 + ((int) (j17 >>> 28));
        r38[13] = r114 + ((int) (j18 >>> 28));
    }

    public static void mul(int[] r141, int[] r142, int[] r143) {
        int r1 = r141[0];
        int r3 = r141[1];
        int r5 = r141[2];
        int r7 = r141[3];
        int r9 = r141[4];
        int r11 = r141[5];
        int r13 = r141[6];
        int r15 = r141[7];
        int r14 = r141[8];
        int r12 = r141[9];
        int r10 = r141[10];
        int r8 = r141[11];
        int r6 = r141[12];
        int r4 = r141[13];
        int r2 = r141[14];
        int r152 = r141[15];
        int r153 = r142[0];
        int r0 = r142[1];
        int r02 = r142[2];
        int r03 = r142[3];
        int r04 = r142[4];
        int r05 = r142[5];
        int r06 = r142[6];
        int r07 = r142[7];
        int r08 = r142[8];
        int r09 = r142[9];
        int r010 = r142[10];
        int r011 = r142[11];
        int r012 = r142[12];
        int r013 = r142[13];
        int r014 = r142[14];
        int r015 = r142[15];
        int r016 = r1 + r14;
        int r017 = r5 + r10;
        int r018 = r7 + r8;
        int r019 = r9 + r6;
        int r020 = r11 + r4;
        int r021 = r13 + r2;
        int r022 = r153 + r08;
        int r023 = r0 + r09;
        int r024 = r02 + r010;
        int r025 = r03 + r011;
        int r026 = r04 + r012;
        int r027 = r05 + r013;
        int r028 = r06 + r014;
        int r029 = r07 + r015;
        long j = r1;
        long j2 = r153;
        long j3 = j * j2;
        long j4 = r15;
        long j5 = r0;
        long j6 = j4 * j5;
        long j7 = r13;
        long j8 = r02;
        long j9 = r11;
        long j10 = r03;
        long j11 = r9;
        long j12 = r04;
        long j13 = r7;
        long j14 = r05;
        long j15 = r5;
        long j16 = r06;
        long j17 = r3;
        long j18 = r07;
        long j19 = r14;
        long j20 = r08;
        long j21 = j19 * j20;
        long j22 = r152;
        long j23 = r09;
        long j24 = j22 * j23;
        long j25 = r2;
        long j26 = r010;
        long j27 = r4;
        long j28 = r011;
        long j29 = r6;
        long j30 = r012;
        long j31 = r8;
        long j32 = r013;
        long j33 = r10;
        long j34 = r014;
        long j35 = r12;
        long j36 = r015;
        long j37 = r016;
        long j38 = r022;
        long j39 = j37 * j38;
        long j40 = r15 + r152;
        long j41 = r023;
        long j42 = j40 * j41;
        long j43 = r021;
        long j44 = r024;
        long j45 = r020;
        long j46 = r025;
        long j47 = r019;
        long j48 = r026;
        long j49 = r018;
        long j50 = r027;
        long j51 = r017;
        long j52 = r028;
        long j53 = r3 + r12;
        long j54 = r029;
        long j55 = j42 + (j43 * j44) + (j45 * j46) + (j47 * j48) + (j49 * j50) + (j51 * j52) + (j53 * j54);
        long j56 = ((j3 + j21) + j55) - ((((((j6 + (j7 * j8)) + (j9 * j10)) + (j11 * j12)) + (j13 * j14)) + (j15 * j16)) + (j17 * j18));
        int r122 = ((int) j56) & M28;
        long j57 = j56 >>> 28;
        long j58 = ((((((((j24 + (j25 * j26)) + (j27 * j28)) + (j29 * j30)) + (j31 * j32)) + (j33 * j34)) + (j35 * j36)) + j39) - j3) + j55;
        int r123 = ((int) j58) & M28;
        long j59 = (j17 * j2) + (j * j5);
        long j60 = (j22 * j26) + (j25 * j28) + (j27 * j30) + (j29 * j32) + (j31 * j34) + (j33 * j36);
        long j61 = (j53 * j38) + (j37 * j41);
        long j62 = (j40 * j44) + (j43 * j46) + (j45 * j48) + (j47 * j50) + (j49 * j52) + (j51 * j54);
        long j63 = j57 + (((j59 + ((j35 * j20) + (j19 * j23))) + j62) - ((((((j4 * j8) + (j7 * j10)) + (j9 * j12)) + (j11 * j14)) + (j13 * j16)) + (j15 * j18)));
        int r124 = ((int) j63) & M28;
        long j64 = (j58 >>> 28) + ((j60 + j61) - j59) + j62;
        int r125 = ((int) j64) & M28;
        long j65 = (j15 * j2) + (j17 * j5) + (j * j8);
        long j66 = (j22 * j28) + (j25 * j30) + (j27 * j32) + (j29 * j34) + (j31 * j36);
        long j67 = (j51 * j38) + (j53 * j41) + (j37 * j44);
        long j68 = (j40 * j46) + (j43 * j48) + (j45 * j50) + (j47 * j52) + (j49 * j54);
        long j69 = (j63 >>> 28) + (((j65 + (((j33 * j20) + (j35 * j23)) + (j19 * j26))) + j68) - (((((j4 * j10) + (j7 * j12)) + (j9 * j14)) + (j11 * j16)) + (j13 * j18)));
        int r126 = ((int) j69) & M28;
        long j70 = (j64 >>> 28) + ((j66 + j67) - j65) + j68;
        int r127 = ((int) j70) & M28;
        long j71 = (j13 * j2) + (j15 * j5) + (j17 * j8) + (j * j10);
        long j72 = (j22 * j30) + (j25 * j32) + (j27 * j34) + (j29 * j36);
        long j73 = (j49 * j38) + (j51 * j41) + (j53 * j44) + (j37 * j46);
        long j74 = (j40 * j48) + (j43 * j50) + (j45 * j52) + (j47 * j54);
        long j75 = (j69 >>> 28) + (((j71 + ((((j31 * j20) + (j33 * j23)) + (j35 * j26)) + (j19 * j28))) + j74) - ((((j4 * j12) + (j7 * j14)) + (j9 * j16)) + (j11 * j18)));
        int r128 = ((int) j75) & M28;
        long j76 = (j70 >>> 28) + ((j72 + j73) - j71) + j74;
        int r129 = ((int) j76) & M28;
        long j77 = (j11 * j2) + (j13 * j5) + (j15 * j8) + (j17 * j10) + (j * j12);
        long j78 = (j22 * j32) + (j25 * j34) + (j27 * j36);
        long j79 = (j47 * j38) + (j49 * j41) + (j51 * j44) + (j53 * j46) + (j37 * j48);
        long j80 = (j40 * j50) + (j43 * j52) + (j45 * j54);
        long j81 = (j75 >>> 28) + (((j77 + (((((j29 * j20) + (j31 * j23)) + (j33 * j26)) + (j35 * j28)) + (j19 * j30))) + j80) - (((j4 * j14) + (j7 * j16)) + (j9 * j18)));
        int r1210 = ((int) j81) & M28;
        long j82 = (j76 >>> 28) + ((j78 + j79) - j77) + j80;
        int r1211 = ((int) j82) & M28;
        long j83 = (j9 * j2) + (j11 * j5) + (j13 * j8) + (j15 * j10) + (j17 * j12) + (j * j14);
        long j84 = (j22 * j34) + (j25 * j36);
        long j85 = (j45 * j38) + (j47 * j41) + (j49 * j44) + (j51 * j46) + (j53 * j48) + (j37 * j50);
        long j86 = (j40 * j52) + (j43 * j54);
        long j87 = (j81 >>> 28) + (((j83 + ((((((j27 * j20) + (j29 * j23)) + (j31 * j26)) + (j33 * j28)) + (j35 * j30)) + (j19 * j32))) + j86) - ((j4 * j16) + (j7 * j18)));
        int r1212 = ((int) j87) & M28;
        long j88 = (j82 >>> 28) + ((j84 + j85) - j83) + j86;
        int r1213 = ((int) j88) & M28;
        long j89 = (j7 * j2) + (j9 * j5) + (j11 * j8) + (j13 * j10) + (j15 * j12) + (j17 * j14) + (j * j16);
        long j90 = j22 * j36;
        long j91 = (j43 * j38) + (j45 * j41) + (j47 * j44) + (j49 * j46) + (j51 * j48) + (j53 * j50) + (j37 * j52);
        long j92 = j40 * j54;
        long j93 = (j87 >>> 28) + (((j89 + (((((((j25 * j20) + (j27 * j23)) + (j29 * j26)) + (j31 * j28)) + (j33 * j30)) + (j35 * j32)) + (j19 * j34))) + j92) - (j4 * j18));
        int r1214 = ((int) j93) & M28;
        long j94 = (j88 >>> 28) + ((j90 + j91) - j89) + j92;
        int r1215 = ((int) j94) & M28;
        long j95 = (j2 * j4) + (j5 * j7) + (j9 * j8) + (j11 * j10) + (j13 * j12) + (j15 * j14) + (j17 * j16) + (j * j18);
        long j96 = (j40 * j38) + (j43 * j41) + (j45 * j44) + (j47 * j46) + (j49 * j48) + (j51 * j50) + (j53 * j52) + (j37 * j54);
        long j97 = (j93 >>> 28) + j95 + (j22 * j20) + (j23 * j25) + (j27 * j26) + (j29 * j28) + (j31 * j30) + (j33 * j32) + (j35 * j34) + (j19 * j36);
        int r22 = ((int) j97) & M28;
        long j98 = (j94 >>> 28) + (j96 - j95);
        int r030 = ((int) j98) & M28;
        long j99 = j98 >>> 28;
        long j100 = (j97 >>> 28) + j99 + r123;
        int r16 = ((int) j100) & M28;
        long j101 = j99 + r122;
        r143[0] = ((int) j101) & M28;
        r143[1] = r124 + ((int) (j101 >>> 28));
        r143[2] = r126;
        r143[3] = r128;
        r143[4] = r1210;
        r143[5] = r1212;
        r143[6] = r1214;
        r143[7] = r22;
        r143[8] = r16;
        r143[9] = r125 + ((int) (j100 >>> 28));
        r143[10] = r127;
        r143[11] = r129;
        r143[12] = r1211;
        r143[13] = r1213;
        r143[14] = r1215;
        r143[15] = r030;
    }

    public static void negate(int[] r1, int[] r2) {
        sub(create(), r1, r2);
    }

    public static void normalize(int[] r1) {
        reduce(r1, 1);
        reduce(r1, -1);
    }

    public static void one(int[] r3) {
        r3[0] = 1;
        for (int r1 = 1; r1 < 16; r1++) {
            r3[r1] = 0;
        }
    }

    private static void powPm3d4(int[] r4, int[] r5) {
        int[] create = create();
        sqr(r4, create);
        mul(r4, create, create);
        int[] create2 = create();
        sqr(create, create2);
        mul(r4, create2, create2);
        int[] create3 = create();
        sqr(create2, 3, create3);
        mul(create2, create3, create3);
        int[] create4 = create();
        sqr(create3, 3, create4);
        mul(create2, create4, create4);
        int[] create5 = create();
        sqr(create4, 9, create5);
        mul(create4, create5, create5);
        int[] create6 = create();
        sqr(create5, create6);
        mul(r4, create6, create6);
        int[] create7 = create();
        sqr(create6, 18, create7);
        mul(create5, create7, create7);
        int[] create8 = create();
        sqr(create7, 37, create8);
        mul(create7, create8, create8);
        int[] create9 = create();
        sqr(create8, 37, create9);
        mul(create7, create9, create9);
        int[] create10 = create();
        sqr(create9, 111, create10);
        mul(create9, create10, create10);
        int[] create11 = create();
        sqr(create10, create11);
        mul(r4, create11, create11);
        int[] create12 = create();
        sqr(create11, 223, create12);
        mul(create12, create10, r5);
    }

    private static void reduce(int[] r13, int r14) {
        int r1;
        int r12 = r13[15];
        int r3 = r12 & M28;
        long j = (r12 >>> 28) + r14;
        int r142 = 0;
        long j2 = j;
        while (true) {
            if (r142 >= 8) {
                break;
            }
            long j3 = j2 + (4294967295L & r13[r142]);
            r13[r142] = ((int) j3) & M28;
            j2 = j3 >> 28;
            r142++;
        }
        long j4 = j2 + j;
        for (r1 = 8; r1 < 15; r1++) {
            long j5 = j4 + (r13[r1] & 4294967295L);
            r13[r1] = ((int) j5) & M28;
            j4 = j5 >> 28;
        }
        r13[15] = r3 + ((int) j4);
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

    public static void sqr(int[] r110, int[] r111) {
        int r1 = r110[0];
        int r3 = r110[1];
        int r5 = r110[2];
        int r7 = r110[3];
        int r9 = r110[4];
        int r11 = r110[5];
        int r13 = r110[6];
        int r15 = r110[7];
        int r14 = r110[8];
        int r12 = r110[9];
        int r10 = r110[10];
        int r8 = r110[11];
        int r6 = r110[12];
        int r4 = r110[13];
        int r2 = r110[14];
        int r0 = r110[15];
        int r02 = r1 * 2;
        int r03 = r3 * 2;
        int r04 = r5 * 2;
        int r05 = r7 * 2;
        int r06 = r9 * 2;
        int r07 = r11 * 2;
        int r08 = r13 * 2;
        int r09 = r14 * 2;
        int r010 = r12 * 2;
        int r011 = r10 * 2;
        int r012 = r8 * 2;
        int r013 = r6 * 2;
        int r014 = r4 * 2;
        int r015 = r2 * 2;
        int r016 = r1 + r14;
        int r142 = r3 + r12;
        int r122 = r5 + r10;
        int r102 = r7 + r8;
        int r82 = r9 + r6;
        int r72 = r11 + r4;
        int r52 = r13 + r2;
        int r32 = r15 + r0;
        int r33 = r016 * 2;
        int r34 = r142 * 2;
        int r143 = r122 * 2;
        int r123 = r102 * 2;
        int r103 = r72 * 2;
        long j = r1;
        long j2 = j * j;
        long j3 = r15;
        long j4 = r03;
        long j5 = j3 * j4;
        long j6 = r13;
        long j7 = r04;
        long j8 = r11;
        long j9 = r05;
        long j10 = r9;
        long j11 = r14;
        long j12 = r0;
        long j13 = r010;
        long j14 = j12 * j13;
        long j15 = r2;
        long j16 = r011;
        long j17 = r4;
        long j18 = r012;
        long j19 = r6;
        long j20 = r016;
        long j21 = r32;
        long j22 = r34 & 4294967295L;
        long j23 = j21 * j22;
        long j24 = r52;
        long j25 = r143 & 4294967295L;
        long j26 = r72;
        long j27 = r123 & 4294967295L;
        long j28 = r82;
        long j29 = j23 + (j24 * j25) + (j26 * j27) + (j28 * j28);
        long j30 = ((j2 + (j11 * j11)) + j29) - (((j5 + (j6 * j7)) + (j8 * j9)) + (j10 * j10));
        int r152 = ((int) j30) & M28;
        long j31 = (((((j14 + (j15 * j16)) + (j17 * j18)) + (j19 * j19)) + (j20 * j20)) - j2) + j29;
        int r153 = ((int) j31) & M28;
        long j32 = j31 >>> 28;
        long j33 = r3;
        long j34 = r02;
        long j35 = j33 * j34;
        long j36 = r06;
        long j37 = r12;
        long j38 = r09;
        long j39 = j37 * j38;
        long j40 = r013;
        long j41 = r142;
        long j42 = r33 & 4294967295L;
        long j43 = (r82 * 2) & 4294967295L;
        long j44 = (j21 * j25) + (j24 * j27) + (j26 * j43);
        long j45 = (j30 >>> 28) + (((j35 + j39) + j44) - (((j3 * j7) + (j6 * j9)) + (j8 * j36)));
        int r154 = ((int) j45) & M28;
        long j46 = j32 + (((((j12 * j16) + (j15 * j18)) + (j17 * j40)) + (j41 * j42)) - j35) + j44;
        int r104 = ((int) j46) & M28;
        long j47 = j46 >>> 28;
        long j48 = r5;
        long j49 = (j48 * j34) + (j33 * j33);
        long j50 = (j3 * j9) + (j6 * j36) + (j8 * j8);
        long j51 = r10;
        long j52 = (j51 * j38) + (j37 * j37);
        long j53 = r122;
        long j54 = (j53 * j42) + (j41 * j41);
        long j55 = (j21 * j27) + (j24 * j43) + (j26 * j26);
        long j56 = (j45 >>> 28) + (((j49 + j52) + j55) - j50);
        int r155 = ((int) j56) & M28;
        long j57 = j47 + (((((j12 * j18) + (j15 * j40)) + (j17 * j17)) + j54) - j49) + j55;
        int r156 = ((int) j57) & M28;
        long j58 = r7;
        long j59 = (j58 * j34) + (j48 * j4);
        long j60 = r07;
        long j61 = r8;
        long j62 = (j61 * j38) + (j51 * j13);
        long j63 = r014;
        long j64 = r102;
        long j65 = j43 * j21;
        long j66 = r103 & 4294967295L;
        long j67 = j65 + (j24 * j66);
        long j68 = (j56 >>> 28) + (((j59 + j62) + j67) - ((j3 * j36) + (j6 * j60)));
        int r62 = ((int) j68) & M28;
        long j69 = (j57 >>> 28) + ((((j12 * j40) + (j15 * j63)) + ((j64 * j42) + (j53 * j22))) - j59) + j67;
        int r73 = ((int) j69) & M28;
        long j70 = (j10 * j34) + (j58 * j4) + (j48 * j48);
        long j71 = (j19 * j38) + (j61 * j13) + (j51 * j51);
        long j72 = (j28 * j42) + (j64 * j22) + (j53 * j53);
        long j73 = (j21 * j66) + (j24 * j24);
        long j74 = (j68 >>> 28) + (((j70 + j71) + j73) - ((j3 * j60) + (j6 * j6)));
        int r74 = ((int) j74) & M28;
        long j75 = (j69 >>> 28) + ((((j12 * j63) + (j15 * j15)) + j72) - j70) + j73;
        int r83 = ((int) j75) & M28;
        long j76 = (j8 * j34) + (j10 * j4) + (j58 * j7);
        long j77 = (j17 * j38) + (j19 * j13) + (j61 * j16);
        long j78 = ((r52 * 2) & 4294967295L) * j21;
        long j79 = (j74 >>> 28) + (((j76 + j77) + j78) - (r08 * j3));
        int r84 = ((int) j79) & M28;
        long j80 = (j75 >>> 28) + (((r015 * j12) + (((j26 * j42) + (j28 * j22)) + (j64 * j25))) - j76) + j78;
        int r63 = ((int) j80) & M28;
        long j81 = (j6 * j34) + (j8 * j4) + (j10 * j7) + (j58 * j58);
        long j82 = j21 * j21;
        long j83 = (j79 >>> 28) + (((j81 + ((((j15 * j38) + (j17 * j13)) + (j19 * j16)) + (j61 * j61))) + j82) - (j3 * j3));
        int r75 = ((int) j83) & M28;
        long j84 = (j80 >>> 28) + (((j12 * j12) + ((((j24 * j42) + (j26 * j22)) + (j28 * j25)) + (j64 * j64))) - j81) + j82;
        int r112 = ((int) j84) & M28;
        long j85 = (j3 * j34) + (j6 * j4) + (j8 * j7) + (j10 * j9);
        long j86 = (j83 >>> 28) + (j38 * j12) + (j15 * j13) + (j17 * j16) + (j19 * j18) + j85;
        int r017 = ((int) j86) & M28;
        long j87 = (j84 >>> 28) + (((((j42 * j21) + (j24 * j22)) + (j26 * j25)) + (j28 * j27)) - j85);
        int r16 = ((int) j87) & M28;
        long j88 = j87 >>> 28;
        long j89 = (j86 >>> 28) + j88 + r153;
        int r42 = ((int) j89) & M28;
        long j90 = j88 + r152;
        r111[0] = ((int) j90) & M28;
        r111[1] = r154 + ((int) (j90 >>> 28));
        r111[2] = r155;
        r111[3] = r62;
        r111[4] = r74;
        r111[5] = r84;
        r111[6] = r75;
        r111[7] = r017;
        r111[8] = r42;
        r111[9] = r104 + ((int) (j89 >>> 28));
        r111[10] = r156;
        r111[11] = r73;
        r111[12] = r83;
        r111[13] = r63;
        r111[14] = r112;
        r111[15] = r16;
    }

    public static boolean sqrtRatioVar(int[] r3, int[] r4, int[] r5) {
        int[] create = create();
        int[] create2 = create();
        sqr(r3, create);
        mul(create, r4, create);
        sqr(create, create2);
        mul(create, r3, create);
        mul(create2, r3, create2);
        mul(create2, r4, create2);
        int[] create3 = create();
        powPm3d4(create2, create3);
        mul(create3, create, create3);
        int[] create4 = create();
        sqr(create3, create4);
        mul(create4, r4, create4);
        sub(r3, create4, create4);
        normalize(create4);
        if (isZeroVar(create4)) {
            copy(create3, 0, r5, 0);
            return true;
        }
        return false;
    }

    public static void sub(int[] r49, int[] r50, int[] r51) {
        int r1 = r49[0];
        int r3 = r49[1];
        int r5 = r49[2];
        int r7 = r49[3];
        int r9 = r49[4];
        int r11 = r49[5];
        int r13 = r49[6];
        int r15 = r49[7];
        int r17 = r49[8];
        int r19 = r49[9];
        int r21 = r49[10];
        int r23 = r49[11];
        int r25 = r49[12];
        int r27 = r49[13];
        int r29 = r49[14];
        int r31 = r49[15];
        int r32 = r50[0];
        int r33 = r50[1];
        int r34 = r50[2];
        int r35 = r50[3];
        int r36 = r50[4];
        int r37 = r50[5];
        int r38 = r50[6];
        int r39 = r50[7];
        int r40 = r50[8];
        int r41 = r50[9];
        int r42 = r50[10];
        int r43 = r50[11];
        int r44 = r50[12];
        int r45 = r50[13];
        int r46 = r50[14];
        int r310 = (r3 + 536870910) - r33;
        int r112 = (r11 + 536870910) - r37;
        int r192 = (r19 + 536870910) - r41;
        int r272 = (r27 + 536870910) - r45;
        int r52 = ((r5 + 536870910) - r34) + (r310 >>> 28);
        int r311 = r310 & M28;
        int r132 = ((r13 + 536870910) - r38) + (r112 >>> 28);
        int r113 = r112 & M28;
        int r212 = ((r21 + 536870910) - r42) + (r192 >>> 28);
        int r193 = r192 & M28;
        int r292 = ((r29 + 536870910) - r46) + (r272 >>> 28);
        int r273 = r272 & M28;
        int r72 = ((r7 + 536870910) - r35) + (r52 >>> 28);
        int r53 = r52 & M28;
        int r152 = ((r15 + 536870910) - r39) + (r132 >>> 28);
        int r133 = r132 & M28;
        int r232 = ((r23 + 536870910) - r43) + (r212 >>> 28);
        int r213 = r212 & M28;
        int r312 = ((r31 + 536870910) - r50[15]) + (r292 >>> 28);
        int r293 = r292 & M28;
        int r332 = r312 >>> 28;
        int r313 = r312 & M28;
        int r12 = ((r1 + 536870910) - r32) + r332;
        int r92 = ((r9 + 536870910) - r36) + (r72 >>> 28);
        int r73 = r72 & M28;
        int r172 = ((r17 + 536870908) - r40) + r332 + (r152 >>> 28);
        int r153 = r152 & M28;
        int r252 = ((r25 + 536870910) - r44) + (r232 >>> 28);
        int r233 = r232 & M28;
        int r314 = r311 + (r12 >>> 28);
        int r14 = r12 & M28;
        int r114 = r113 + (r92 >>> 28);
        int r93 = r92 & M28;
        int r194 = r193 + (r172 >>> 28);
        int r173 = r172 & M28;
        int r253 = r252 & M28;
        r51[0] = r14;
        r51[1] = r314;
        r51[2] = r53;
        r51[3] = r73;
        r51[4] = r93;
        r51[5] = r114;
        r51[6] = r133;
        r51[7] = r153;
        r51[8] = r173;
        r51[9] = r194;
        r51[10] = r213;
        r51[11] = r233;
        r51[12] = r253;
        r51[13] = r273 + (r252 >>> 28);
        r51[14] = r293;
        r51[15] = r313;
    }

    public static void subOne(int[] r3) {
        int[] create = create();
        create[0] = 1;
        sub(r3, create, r3);
    }

    public static void zero(int[] r3) {
        for (int r1 = 0; r1 < 16; r1++) {
            r3[r1] = 0;
        }
    }
}
