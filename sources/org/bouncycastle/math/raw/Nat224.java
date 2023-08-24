package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat224 {

    /* renamed from: M */
    private static final long f2385M = 4294967295L;

    public static int add(int[] r9, int r10, int[] r11, int r12, int[] r13, int r14) {
        long j = (r9[r10 + 0] & 4294967295L) + (r11[r12 + 0] & 4294967295L) + 0;
        r13[r14 + 0] = (int) j;
        long j2 = (j >>> 32) + (r9[r10 + 1] & 4294967295L) + (r11[r12 + 1] & 4294967295L);
        r13[r14 + 1] = (int) j2;
        long j3 = (j2 >>> 32) + (r9[r10 + 2] & 4294967295L) + (r11[r12 + 2] & 4294967295L);
        r13[r14 + 2] = (int) j3;
        long j4 = (j3 >>> 32) + (r9[r10 + 3] & 4294967295L) + (r11[r12 + 3] & 4294967295L);
        r13[r14 + 3] = (int) j4;
        long j5 = (j4 >>> 32) + (r9[r10 + 4] & 4294967295L) + (r11[r12 + 4] & 4294967295L);
        r13[r14 + 4] = (int) j5;
        long j6 = (j5 >>> 32) + (r9[r10 + 5] & 4294967295L) + (r11[r12 + 5] & 4294967295L);
        r13[r14 + 5] = (int) j6;
        long j7 = (j6 >>> 32) + (r9[r10 + 6] & 4294967295L) + (r11[r12 + 6] & 4294967295L);
        r13[r14 + 6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int add(int[] r10, int[] r11, int[] r12) {
        long j = (r10[0] & 4294967295L) + (r11[0] & 4294967295L) + 0;
        r12[0] = (int) j;
        long j2 = (j >>> 32) + (r10[1] & 4294967295L) + (r11[1] & 4294967295L);
        r12[1] = (int) j2;
        long j3 = (j2 >>> 32) + (r10[2] & 4294967295L) + (r11[2] & 4294967295L);
        r12[2] = (int) j3;
        long j4 = (j3 >>> 32) + (r10[3] & 4294967295L) + (r11[3] & 4294967295L);
        r12[3] = (int) j4;
        long j5 = (j4 >>> 32) + (r10[4] & 4294967295L) + (r11[4] & 4294967295L);
        r12[4] = (int) j5;
        long j6 = (j5 >>> 32) + (r10[5] & 4294967295L) + (r11[5] & 4294967295L);
        r12[5] = (int) j6;
        long j7 = (j6 >>> 32) + (r10[6] & 4294967295L) + (r11[6] & 4294967295L);
        r12[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addBothTo(int[] r10, int r11, int[] r12, int r13, int[] r14, int r15) {
        int r4 = r15 + 0;
        long j = (r10[r11 + 0] & 4294967295L) + (r12[r13 + 0] & 4294967295L) + (r14[r4] & 4294967295L) + 0;
        r14[r4] = (int) j;
        int r7 = r15 + 1;
        long j2 = (j >>> 32) + (r10[r11 + 1] & 4294967295L) + (r12[r13 + 1] & 4294967295L) + (r14[r7] & 4294967295L);
        r14[r7] = (int) j2;
        int r72 = r15 + 2;
        long j3 = (j2 >>> 32) + (r10[r11 + 2] & 4294967295L) + (r12[r13 + 2] & 4294967295L) + (r14[r72] & 4294967295L);
        r14[r72] = (int) j3;
        int r73 = r15 + 3;
        long j4 = (j3 >>> 32) + (r10[r11 + 3] & 4294967295L) + (r12[r13 + 3] & 4294967295L) + (r14[r73] & 4294967295L);
        r14[r73] = (int) j4;
        int r74 = r15 + 4;
        long j5 = (j4 >>> 32) + (r10[r11 + 4] & 4294967295L) + (r12[r13 + 4] & 4294967295L) + (r14[r74] & 4294967295L);
        r14[r74] = (int) j5;
        int r75 = r15 + 5;
        long j6 = (j5 >>> 32) + (r10[r11 + 5] & 4294967295L) + (r12[r13 + 5] & 4294967295L) + (r14[r75] & 4294967295L);
        r14[r75] = (int) j6;
        int r152 = r15 + 6;
        long j7 = (j6 >>> 32) + (r10[r11 + 6] & 4294967295L) + (r12[r13 + 6] & 4294967295L) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addBothTo(int[] r10, int[] r11, int[] r12) {
        long j = (r10[0] & 4294967295L) + (r11[0] & 4294967295L) + (r12[0] & 4294967295L) + 0;
        r12[0] = (int) j;
        long j2 = (j >>> 32) + (r10[1] & 4294967295L) + (r11[1] & 4294967295L) + (r12[1] & 4294967295L);
        r12[1] = (int) j2;
        long j3 = (j2 >>> 32) + (r10[2] & 4294967295L) + (r11[2] & 4294967295L) + (r12[2] & 4294967295L);
        r12[2] = (int) j3;
        long j4 = (j3 >>> 32) + (r10[3] & 4294967295L) + (r11[3] & 4294967295L) + (r12[3] & 4294967295L);
        r12[3] = (int) j4;
        long j5 = (j4 >>> 32) + (r10[4] & 4294967295L) + (r11[4] & 4294967295L) + (r12[4] & 4294967295L);
        r12[4] = (int) j5;
        long j6 = (j5 >>> 32) + (r10[5] & 4294967295L) + (r11[5] & 4294967295L) + (r12[5] & 4294967295L);
        r12[5] = (int) j6;
        long j7 = (j6 >>> 32) + (r10[6] & 4294967295L) + (r11[6] & 4294967295L) + (r12[6] & 4294967295L);
        r12[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addTo(int[] r9, int r10, int[] r11, int r12, int r13) {
        int r132 = r12 + 0;
        long j = (r13 & 4294967295L) + (r9[r10 + 0] & 4294967295L) + (r11[r132] & 4294967295L);
        r11[r132] = (int) j;
        int r6 = r12 + 1;
        long j2 = (j >>> 32) + (r9[r10 + 1] & 4294967295L) + (r11[r6] & 4294967295L);
        r11[r6] = (int) j2;
        int r62 = r12 + 2;
        long j3 = (j2 >>> 32) + (r9[r10 + 2] & 4294967295L) + (r11[r62] & 4294967295L);
        r11[r62] = (int) j3;
        int r63 = r12 + 3;
        long j4 = (j3 >>> 32) + (r9[r10 + 3] & 4294967295L) + (r11[r63] & 4294967295L);
        r11[r63] = (int) j4;
        int r64 = r12 + 4;
        long j5 = (j4 >>> 32) + (r9[r10 + 4] & 4294967295L) + (r11[r64] & 4294967295L);
        r11[r64] = (int) j5;
        int r65 = r12 + 5;
        long j6 = (j5 >>> 32) + (r9[r10 + 5] & 4294967295L) + (r11[r65] & 4294967295L);
        r11[r65] = (int) j6;
        int r122 = r12 + 6;
        long j7 = (j6 >>> 32) + (r9[r10 + 6] & 4294967295L) + (4294967295L & r11[r122]);
        r11[r122] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addTo(int[] r10, int[] r11) {
        long j = (r10[0] & 4294967295L) + (r11[0] & 4294967295L) + 0;
        r11[0] = (int) j;
        long j2 = (j >>> 32) + (r10[1] & 4294967295L) + (r11[1] & 4294967295L);
        r11[1] = (int) j2;
        long j3 = (j2 >>> 32) + (r10[2] & 4294967295L) + (r11[2] & 4294967295L);
        r11[2] = (int) j3;
        long j4 = (j3 >>> 32) + (r10[3] & 4294967295L) + (r11[3] & 4294967295L);
        r11[3] = (int) j4;
        long j5 = (j4 >>> 32) + (r10[4] & 4294967295L) + (r11[4] & 4294967295L);
        r11[4] = (int) j5;
        long j6 = (j5 >>> 32) + (r10[5] & 4294967295L) + (r11[5] & 4294967295L);
        r11[5] = (int) j6;
        long j7 = (j6 >>> 32) + (r10[6] & 4294967295L) + (4294967295L & r11[6]);
        r11[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int addToEachOther(int[] r11, int r12, int[] r13, int r14) {
        int r0 = r12 + 0;
        int r5 = r14 + 0;
        long j = (r11[r0] & 4294967295L) + (r13[r5] & 4294967295L) + 0;
        int r6 = (int) j;
        r11[r0] = r6;
        r13[r5] = r6;
        int r52 = r12 + 1;
        int r8 = r14 + 1;
        long j2 = (j >>> 32) + (r11[r52] & 4294967295L) + (r13[r8] & 4294967295L);
        int r62 = (int) j2;
        r11[r52] = r62;
        r13[r8] = r62;
        int r53 = r12 + 2;
        int r82 = r14 + 2;
        long j3 = (j2 >>> 32) + (r11[r53] & 4294967295L) + (r13[r82] & 4294967295L);
        int r63 = (int) j3;
        r11[r53] = r63;
        r13[r82] = r63;
        int r54 = r12 + 3;
        int r83 = r14 + 3;
        long j4 = (j3 >>> 32) + (r11[r54] & 4294967295L) + (r13[r83] & 4294967295L);
        int r64 = (int) j4;
        r11[r54] = r64;
        r13[r83] = r64;
        int r55 = r12 + 4;
        int r84 = r14 + 4;
        long j5 = (j4 >>> 32) + (r11[r55] & 4294967295L) + (r13[r84] & 4294967295L);
        int r65 = (int) j5;
        r11[r55] = r65;
        r13[r84] = r65;
        int r56 = r12 + 5;
        int r85 = r14 + 5;
        long j6 = (j5 >>> 32) + (r11[r56] & 4294967295L) + (r13[r85] & 4294967295L);
        int r66 = (int) j6;
        r11[r56] = r66;
        r13[r85] = r66;
        int r122 = r12 + 6;
        int r142 = r14 + 6;
        long j7 = (j6 >>> 32) + (r11[r122] & 4294967295L) + (4294967295L & r13[r142]);
        int r3 = (int) j7;
        r11[r122] = r3;
        r13[r142] = r3;
        return (int) (j7 >>> 32);
    }

    public static void copy(int[] r2, int r3, int[] r4, int r5) {
        r4[r5 + 0] = r2[r3 + 0];
        r4[r5 + 1] = r2[r3 + 1];
        r4[r5 + 2] = r2[r3 + 2];
        r4[r5 + 3] = r2[r3 + 3];
        r4[r5 + 4] = r2[r3 + 4];
        r4[r5 + 5] = r2[r3 + 5];
        r4[r5 + 6] = r2[r3 + 6];
    }

    public static void copy(int[] r2, int[] r3) {
        r3[0] = r2[0];
        r3[1] = r2[1];
        r3[2] = r2[2];
        r3[3] = r2[3];
        r3[4] = r2[4];
        r3[5] = r2[5];
        r3[6] = r2[6];
    }

    public static int[] create() {
        return new int[7];
    }

    public static int[] createExt() {
        return new int[14];
    }

    public static boolean diff(int[] r7, int r8, int[] r9, int r10, int[] r11, int r12) {
        boolean gte = gte(r7, r8, r9, r10);
        if (gte) {
            sub(r7, r8, r9, r10, r11, r12);
        } else {
            sub(r9, r10, r7, r8, r11, r12);
        }
        return gte;
    }

    /* renamed from: eq */
    public static boolean m19eq(int[] r3, int[] r4) {
        for (int r0 = 6; r0 >= 0; r0--) {
            if (r3[r0] != r4[r0]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 224) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int r1 = 0; r1 < 7; r1++) {
            create[r1] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static int getBit(int[] r3, int r4) {
        int r32;
        if (r4 == 0) {
            r32 = r3[0];
        } else {
            int r1 = r4 >> 5;
            if (r1 < 0 || r1 >= 7) {
                return 0;
            }
            r32 = r3[r1] >>> (r4 & 31);
        }
        return r32 & 1;
    }

    public static boolean gte(int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 6; r0 >= 0; r0--) {
            int r2 = r5[r6 + r0] ^ Integer.MIN_VALUE;
            int r3 = Integer.MIN_VALUE ^ r7[r8 + r0];
            if (r2 < r3) {
                return false;
            }
            if (r2 > r3) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] r5, int[] r6) {
        for (int r0 = 6; r0 >= 0; r0--) {
            int r2 = r5[r0] ^ Integer.MIN_VALUE;
            int r3 = Integer.MIN_VALUE ^ r6[r0];
            if (r2 < r3) {
                return false;
            }
            if (r2 > r3) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] r4) {
        if (r4[0] != 1) {
            return false;
        }
        for (int r1 = 1; r1 < 7; r1++) {
            if (r4[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] r3) {
        for (int r1 = 0; r1 < 7; r1++) {
            if (r3[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] r32, int r33, int[] r34, int r35, int[] r36, int r37) {
        long j = r34[r35 + 0] & 4294967295L;
        long j2 = r34[r35 + 1] & 4294967295L;
        long j3 = r34[r35 + 2] & 4294967295L;
        long j4 = r34[r35 + 3] & 4294967295L;
        long j5 = r34[r35 + 4] & 4294967295L;
        long j6 = r34[r35 + 5] & 4294967295L;
        long j7 = r34[r35 + 6] & 4294967295L;
        long j8 = r32[r33 + 0] & 4294967295L;
        long j9 = (j8 * j) + 0;
        r36[r37 + 0] = (int) j9;
        long j10 = (j9 >>> 32) + (j8 * j2);
        r36[r37 + 1] = (int) j10;
        long j11 = (j10 >>> 32) + (j8 * j3);
        r36[r37 + 2] = (int) j11;
        long j12 = (j11 >>> 32) + (j8 * j4);
        r36[r37 + 3] = (int) j12;
        long j13 = (j12 >>> 32) + (j8 * j5);
        r36[r37 + 4] = (int) j13;
        long j14 = (j13 >>> 32) + (j8 * j6);
        r36[r37 + 5] = (int) j14;
        long j15 = j7;
        long j16 = (j14 >>> 32) + (j8 * j15);
        r36[r37 + 6] = (int) j16;
        r36[r37 + 7] = (int) (j16 >>> 32);
        int r1 = 1;
        int r2 = r37;
        int r5 = 1;
        while (r5 < 7) {
            r2 += r1;
            long j17 = r32[r33 + r5] & 4294967295L;
            int r26 = r2 + 0;
            long j18 = (j17 * j) + (r36[r26] & 4294967295L) + 0;
            r36[r26] = (int) j18;
            int r27 = r2 + 1;
            long j19 = j15;
            long j20 = (j18 >>> 32) + (j17 * j2) + (r36[r27] & 4294967295L);
            r36[r27] = (int) j20;
            int r25 = r2 + 2;
            long j21 = j3;
            long j22 = (j20 >>> 32) + (j17 * j3) + (r36[r25] & 4294967295L);
            r36[r25] = (int) j22;
            int r6 = r2 + 3;
            long j23 = (j22 >>> 32) + (j17 * j4) + (r36[r6] & 4294967295L);
            r36[r6] = (int) j23;
            int r62 = r2 + 4;
            long j24 = (j23 >>> 32) + (j17 * j5) + (r36[r62] & 4294967295L);
            r36[r62] = (int) j24;
            int r63 = r2 + 5;
            long j25 = (j24 >>> 32) + (j17 * j6) + (r36[r63] & 4294967295L);
            r36[r63] = (int) j25;
            int r4 = r2 + 6;
            long j26 = (j25 >>> 32) + (j17 * j19) + (r36[r4] & 4294967295L);
            r36[r4] = (int) j26;
            r36[r2 + 7] = (int) (j26 >>> 32);
            r5++;
            j3 = j21;
            j15 = j19;
            j4 = j4;
            r1 = 1;
        }
    }

    public static void mul(int[] r32, int[] r33, int[] r34) {
        long j = r33[0] & 4294967295L;
        long j2 = r33[1] & 4294967295L;
        long j3 = r33[2] & 4294967295L;
        long j4 = r33[3] & 4294967295L;
        long j5 = r33[4] & 4294967295L;
        long j6 = r33[5] & 4294967295L;
        long j7 = r33[6] & 4294967295L;
        long j8 = r32[0] & 4294967295L;
        long j9 = (j8 * j) + 0;
        r34[0] = (int) j9;
        long j10 = (j9 >>> 32) + (j8 * j2);
        r34[1] = (int) j10;
        long j11 = (j10 >>> 32) + (j8 * j3);
        r34[2] = (int) j11;
        long j12 = (j11 >>> 32) + (j8 * j4);
        r34[3] = (int) j12;
        long j13 = (j12 >>> 32) + (j8 * j5);
        r34[4] = (int) j13;
        long j14 = (j13 >>> 32) + (j8 * j6);
        r34[5] = (int) j14;
        long j15 = (j14 >>> 32) + (j8 * j7);
        r34[6] = (int) j15;
        int r4 = (int) (j15 >>> 32);
        r34[7] = r4;
        int r5 = 1;
        for (int r3 = 7; r5 < r3; r3 = 7) {
            long j16 = r32[r5] & 4294967295L;
            int r42 = r5 + 0;
            long j17 = (j16 * j) + (r34[r42] & 4294967295L) + 0;
            r34[r42] = (int) j17;
            int r18 = r5 + 1;
            long j18 = j2;
            long j19 = (j17 >>> 32) + (j16 * j2) + (r34[r18] & 4294967295L);
            r34[r18] = (int) j19;
            int r6 = r5 + 2;
            long j20 = j6;
            long j21 = (j19 >>> 32) + (j16 * j3) + (r34[r6] & 4294967295L);
            r34[r6] = (int) j21;
            int r62 = r5 + 3;
            long j22 = (j21 >>> 32) + (j16 * j4) + (r34[r62] & 4294967295L);
            r34[r62] = (int) j22;
            int r63 = r5 + 4;
            long j23 = (j22 >>> 32) + (j16 * j5) + (r34[r63] & 4294967295L);
            r34[r63] = (int) j23;
            int r35 = r5 + 5;
            long j24 = (j23 >>> 32) + (j16 * j20) + (r34[r35] & 4294967295L);
            r34[r35] = (int) j24;
            int r36 = r5 + 6;
            long j25 = (j24 >>> 32) + (j16 * j7) + (r34[r36] & 4294967295L);
            r34[r36] = (int) j25;
            r34[r5 + 7] = (int) (j25 >>> 32);
            r5 = r18;
            j = j;
            j2 = j18;
            j6 = j20;
        }
    }

    public static long mul33Add(int r13, int[] r14, int r15, int[] r16, int r17, int[] r18, int r19) {
        long j = r13 & 4294967295L;
        long j2 = r14[r15 + 0] & 4294967295L;
        long j3 = (j * j2) + (r16[r17 + 0] & 4294967295L) + 0;
        r18[r19 + 0] = (int) j3;
        long j4 = r14[r15 + 1] & 4294967295L;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (r16[r17 + 1] & 4294967295L);
        r18[r19 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = r14[r15 + 2] & 4294967295L;
        long j8 = j6 + (j * j7) + j4 + (r16[r17 + 2] & 4294967295L);
        r18[r19 + 2] = (int) j8;
        long j9 = r14[r15 + 3] & 4294967295L;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (r16[r17 + 3] & 4294967295L);
        r18[r19 + 3] = (int) j10;
        long j11 = r14[r15 + 4] & 4294967295L;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (r16[r17 + 4] & 4294967295L);
        r18[r19 + 4] = (int) j12;
        long j13 = r14[r15 + 5] & 4294967295L;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (r16[r17 + 5] & 4294967295L);
        r18[r19 + 5] = (int) j14;
        long j15 = r14[r15 + 6] & 4294967295L;
        long j16 = (j14 >>> 32) + (j * j15) + j13 + (4294967295L & r16[r17 + 6]);
        r18[r19 + 6] = (int) j16;
        return (j16 >>> 32) + j15;
    }

    public static int mul33DWordAdd(int r15, long j, int[] r18, int r19) {
        long j2 = r15 & 4294967295L;
        long j3 = j & 4294967295L;
        int r1 = r19 + 0;
        long j4 = (j2 * j3) + (r18[r1] & 4294967295L) + 0;
        r18[r1] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int r7 = r19 + 1;
        long j7 = (j4 >>> 32) + j6 + (r18[r7] & 4294967295L);
        r18[r7] = (int) j7;
        int r72 = r19 + 2;
        long j8 = (j7 >>> 32) + j5 + (r18[r72] & 4294967295L);
        r18[r72] = (int) j8;
        int r73 = r19 + 3;
        long j9 = (j8 >>> 32) + (4294967295L & r18[r73]);
        r18[r73] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, r18, r19, 4);
    }

    public static int mul33WordAdd(int r10, int r11, int[] r12, int r13) {
        long j = r11 & 4294967295L;
        int r4 = r13 + 0;
        long j2 = ((r10 & 4294967295L) * j) + (r12[r4] & 4294967295L) + 0;
        r12[r4] = (int) j2;
        int r7 = r13 + 1;
        long j3 = (j2 >>> 32) + j + (r12[r7] & 4294967295L);
        r12[r7] = (int) j3;
        long j4 = j3 >>> 32;
        int r0 = r13 + 2;
        long j5 = j4 + (r12[r0] & 4294967295L);
        r12[r0] = (int) j5;
        if ((j5 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, r12, r13, 3);
    }

    public static int mulAddTo(int[] r33, int r34, int[] r35, int r36, int[] r37, int r38) {
        int r26;
        int r5;
        int r52;
        int r53;
        int r54;
        int r55;
        int r18;
        long j = r35[r36 + 0] & 4294967295L;
        long j2 = r35[r36 + 1] & 4294967295L;
        long j3 = r35[r36 + 2] & 4294967295L;
        long j4 = r35[r36 + 3] & 4294967295L;
        long j5 = r35[r36 + 4] & 4294967295L;
        long j6 = r35[r36 + 5] & 4294967295L;
        long j7 = r35[r36 + 6] & 4294967295L;
        int r182 = r38;
        long j8 = 0;
        int r2 = 0;
        while (r2 < 7) {
            int r23 = r2;
            long j9 = r33[r34 + r2] & 4294967295L;
            long j10 = j;
            long j11 = (j9 * j) + (r37[r26] & 4294967295L) + 0;
            long j12 = j7;
            r37[r182 + 0] = (int) j11;
            int r15 = r182 + 1;
            long j13 = (j11 >>> 32) + (j9 * j2) + (r37[r15] & 4294967295L);
            r37[r15] = (int) j13;
            long j14 = (j13 >>> 32) + (j9 * j3) + (r37[r5] & 4294967295L);
            r37[r182 + 2] = (int) j14;
            long j15 = (j14 >>> 32) + (j9 * j4) + (r37[r52] & 4294967295L);
            r37[r182 + 3] = (int) j15;
            long j16 = (j15 >>> 32) + (j9 * j5) + (r37[r53] & 4294967295L);
            r37[r182 + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (j9 * j6) + (r37[r54] & 4294967295L);
            r37[r182 + 5] = (int) j17;
            long j18 = (j17 >>> 32) + (j9 * j12) + (r37[r55] & 4294967295L);
            r37[r182 + 6] = (int) j18;
            long j19 = (j18 >>> 32) + (r37[r18] & 4294967295L) + j8;
            r37[r182 + 7] = (int) j19;
            j8 = j19 >>> 32;
            r2 = r23 + 1;
            r182 = r15;
            j7 = j12;
            j = j10;
            j2 = j2;
        }
        return (int) j8;
    }

    public static int mulAddTo(int[] r32, int[] r33, int[] r34) {
        int r25;
        int r6;
        int r62;
        int r63;
        int r64;
        int r65;
        int r2;
        long j = r33[0] & 4294967295L;
        long j2 = r33[1] & 4294967295L;
        long j3 = r33[2] & 4294967295L;
        long j4 = r33[3] & 4294967295L;
        long j5 = r33[4] & 4294967295L;
        long j6 = r33[5] & 4294967295L;
        long j7 = r33[6] & 4294967295L;
        long j8 = 0;
        int r22 = 0;
        while (r22 < 7) {
            long j9 = j7;
            long j10 = r32[r22] & 4294967295L;
            long j11 = j6;
            long j12 = (j10 * j) + (r34[r25] & 4294967295L) + 0;
            r34[r22 + 0] = (int) j12;
            int r16 = r22 + 1;
            long j13 = j2;
            long j14 = (j12 >>> 32) + (j10 * j2) + (r34[r16] & 4294967295L);
            r34[r16] = (int) j14;
            long j15 = (j14 >>> 32) + (j10 * j3) + (r34[r6] & 4294967295L);
            r34[r22 + 2] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j4) + (r34[r62] & 4294967295L);
            r34[r22 + 3] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j5) + (r34[r63] & 4294967295L);
            r34[r22 + 4] = (int) j17;
            long j18 = (j17 >>> 32) + (j10 * j11) + (r34[r64] & 4294967295L);
            r34[r22 + 5] = (int) j18;
            long j19 = (j18 >>> 32) + (j10 * j9) + (r34[r65] & 4294967295L);
            r34[r22 + 6] = (int) j19;
            long j20 = (j19 >>> 32) + (r34[r2] & 4294967295L) + j8;
            r34[r22 + 7] = (int) j20;
            j8 = j20 >>> 32;
            r22 = r16;
            j7 = j9;
            j6 = j11;
            j2 = j13;
        }
        return (int) j8;
    }

    public static int mulByWord(int r9, int[] r10) {
        long j = r9 & 4294967295L;
        long j2 = ((r10[0] & 4294967295L) * j) + 0;
        r10[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((r10[1] & 4294967295L) * j);
        r10[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((r10[2] & 4294967295L) * j);
        r10[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((r10[3] & 4294967295L) * j);
        r10[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((r10[4] & 4294967295L) * j);
        r10[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((r10[5] & 4294967295L) * j);
        r10[5] = (int) j7;
        long j8 = (j7 >>> 32) + (j * (4294967295L & r10[6]));
        r10[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int mulByWordAddTo(int r11, int[] r12, int[] r13) {
        long j = r11 & 4294967295L;
        long j2 = ((r13[0] & 4294967295L) * j) + (r12[0] & 4294967295L) + 0;
        r13[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((r13[1] & 4294967295L) * j) + (r12[1] & 4294967295L);
        r13[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((r13[2] & 4294967295L) * j) + (r12[2] & 4294967295L);
        r13[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((r13[3] & 4294967295L) * j) + (r12[3] & 4294967295L);
        r13[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((r13[4] & 4294967295L) * j) + (r12[4] & 4294967295L);
        r13[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((r13[5] & 4294967295L) * j) + (r12[5] & 4294967295L);
        r13[5] = (int) j7;
        long j8 = (j7 >>> 32) + (j * (r13[6] & 4294967295L)) + (4294967295L & r12[6]);
        r13[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int mulWord(int r8, int[] r9, int[] r10, int r11) {
        long j = r8 & 4294967295L;
        long j2 = 0;
        int r82 = 0;
        do {
            long j3 = j2 + ((r9[r82] & 4294967295L) * j);
            r10[r11 + r82] = (int) j3;
            j2 = j3 >>> 32;
            r82++;
        } while (r82 < 7);
        return (int) j2;
    }

    public static int mulWordAddTo(int r11, int[] r12, int r13, int[] r14, int r15) {
        long j = r11 & 4294967295L;
        int r112 = r15 + 0;
        long j2 = ((r12[r13 + 0] & 4294967295L) * j) + (r14[r112] & 4294967295L) + 0;
        r14[r112] = (int) j2;
        int r8 = r15 + 1;
        long j3 = (j2 >>> 32) + ((r12[r13 + 1] & 4294967295L) * j) + (r14[r8] & 4294967295L);
        r14[r8] = (int) j3;
        int r82 = r15 + 2;
        long j4 = (j3 >>> 32) + ((r12[r13 + 2] & 4294967295L) * j) + (r14[r82] & 4294967295L);
        r14[r82] = (int) j4;
        int r83 = r15 + 3;
        long j5 = (j4 >>> 32) + ((r12[r13 + 3] & 4294967295L) * j) + (r14[r83] & 4294967295L);
        r14[r83] = (int) j5;
        int r84 = r15 + 4;
        long j6 = (j5 >>> 32) + ((r12[r13 + 4] & 4294967295L) * j) + (r14[r84] & 4294967295L);
        r14[r84] = (int) j6;
        int r85 = r15 + 5;
        long j7 = (j6 >>> 32) + ((r12[r13 + 5] & 4294967295L) * j) + (r14[r85] & 4294967295L);
        r14[r85] = (int) j7;
        int r152 = r15 + 6;
        long j8 = (j7 >>> 32) + (j * (r12[r13 + 6] & 4294967295L)) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int mulWordDwordAdd(int r10, long j, int[] r13, int r14) {
        long j2 = r10 & 4294967295L;
        int r102 = r14 + 0;
        long j3 = ((j & 4294967295L) * j2) + (r13[r102] & 4294967295L) + 0;
        r13[r102] = (int) j3;
        long j4 = j2 * (j >>> 32);
        int r11 = r14 + 1;
        long j5 = (j3 >>> 32) + j4 + (r13[r11] & 4294967295L);
        r13[r11] = (int) j5;
        int r0 = r14 + 2;
        long j6 = (j5 >>> 32) + (r13[r0] & 4294967295L);
        r13[r0] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, r13, r14, 3);
    }

    public static void square(int[] r43, int r44, int[] r45, int r46) {
        int r9;
        int r15;
        int r7;
        int r6;
        int r24;
        int r3;
        int r22;
        int r5;
        int r25;
        int r11;
        int r23;
        long j = r43[r44 + 0] & 4294967295L;
        int r52 = 0;
        int r62 = 14;
        int r72 = 6;
        while (true) {
            int r8 = r72 - 1;
            long j2 = r43[r44 + r72] & 4294967295L;
            long j3 = j2 * j2;
            int r63 = r62 - 1;
            r45[r46 + r63] = (r52 << 31) | ((int) (j3 >>> 33));
            r62 = r63 - 1;
            r45[r46 + r62] = (int) (j3 >>> 1);
            r52 = (int) j3;
            if (r8 <= 0) {
                long j4 = j * j;
                r45[r46 + 0] = (int) j4;
                long j5 = r43[r44 + 1] & 4294967295L;
                long j6 = (((r52 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r152 = (int) j6;
                r45[r46 + 1] = (r152 << 1) | (((int) (j4 >>> 32)) & 1);
                int r82 = r152 >>> 31;
                long j7 = (r45[r9] & 4294967295L) + (j6 >>> 32);
                long j8 = r43[r44 + 2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r12 = (int) j9;
                r45[r46 + 2] = (r12 << 1) | r82;
                int r83 = r12 >>> 31;
                long j10 = (r45[r15] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r45[r7] & 4294967295L) + (j10 >>> 32);
                long j12 = r43[r44 + 3] & 4294967295L;
                long j13 = (r45[r6] & 4294967295L) + (j11 >>> 32);
                long j14 = (r45[r24] & 4294967295L) + (j13 >>> 32);
                long j15 = (j10 & 4294967295L) + (j12 * j);
                int r2 = (int) j15;
                r45[r46 + 3] = (r2 << 1) | r83;
                long j16 = (j11 & 4294967295L) + (j15 >>> 32) + (j12 * j5);
                long j17 = (j13 & 4294967295L) + (j16 >>> 32) + (j12 * j8);
                long j18 = j14 + (j17 >>> 32);
                long j19 = j17 & 4294967295L;
                long j20 = r43[r44 + 4] & 4294967295L;
                long j21 = (r45[r3] & 4294967295L) + (j18 >>> 32);
                long j22 = j18 & 4294967295L;
                long j23 = (j16 & 4294967295L) + (j20 * j);
                int r53 = (int) j23;
                r45[r46 + 4] = (r2 >>> 31) | (r53 << 1);
                int r26 = r53 >>> 31;
                long j24 = j19 + (j23 >>> 32) + (j20 * j5);
                long j25 = j22 + (j24 >>> 32) + (j20 * j8);
                long j26 = (j21 & 4294967295L) + (j25 >>> 32) + (j20 * j12);
                long j27 = (r45[r22] & 4294967295L) + (j21 >>> 32) + (j26 >>> 32);
                long j28 = j26 & 4294967295L;
                long j29 = r43[r44 + 5] & 4294967295L;
                long j30 = (r45[r5] & 4294967295L) + (j27 >>> 32);
                long j31 = (j24 & 4294967295L) + (j29 * j);
                int r73 = (int) j31;
                r45[r46 + 5] = r26 | (r73 << 1);
                int r27 = r73 >>> 31;
                long j32 = (j25 & 4294967295L) + (j31 >>> 32) + (j29 * j5);
                long j33 = j28 + (j32 >>> 32) + (j29 * j8);
                long j34 = (j27 & 4294967295L) + (j33 >>> 32) + (j29 * j12);
                long j35 = (j30 & 4294967295L) + (j34 >>> 32) + (j29 * j20);
                long j36 = (r45[r25] & 4294967295L) + (j30 >>> 32) + (j35 >>> 32);
                long j37 = j35 & 4294967295L;
                long j38 = r43[r44 + 6] & 4294967295L;
                long j39 = (r45[r11] & 4294967295L) + (j36 >>> 32);
                long j40 = (j32 & 4294967295L) + (j * j38);
                int r0 = (int) j40;
                r45[r46 + 6] = (r0 << 1) | r27;
                long j41 = (j33 & 4294967295L) + (j40 >>> 32) + (j38 * j5);
                long j42 = (j34 & 4294967295L) + (j41 >>> 32) + (j38 * j8);
                long j43 = j37 + (j42 >>> 32) + (j38 * j12);
                long j44 = (j36 & 4294967295L) + (j43 >>> 32) + (j38 * j20);
                long j45 = (j39 & 4294967295L) + (j44 >>> 32) + (j38 * j29);
                long j46 = (r45[r23] & 4294967295L) + (j39 >>> 32) + (j45 >>> 32);
                int r84 = (int) j41;
                r45[r46 + 7] = (r0 >>> 31) | (r84 << 1);
                int r28 = (int) j42;
                r45[r46 + 8] = (r84 >>> 31) | (r28 << 1);
                int r1 = (int) j43;
                r45[r46 + 9] = (r28 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                int r13 = (int) j44;
                r45[r46 + 10] = r02 | (r13 << 1);
                int r03 = r13 >>> 31;
                int r14 = (int) j45;
                r45[r46 + 11] = r03 | (r14 << 1);
                int r04 = r14 >>> 31;
                int r16 = (int) j46;
                r45[r46 + 12] = r04 | (r16 << 1);
                int r05 = r16 >>> 31;
                int r17 = r46 + 13;
                r45[r17] = r05 | ((r45[r17] + ((int) (j46 >>> 32))) << 1);
                return;
            }
            r72 = r8;
        }
    }

    public static void square(int[] r42, int[] r43) {
        long j = r42[0] & 4294967295L;
        int r6 = 14;
        int r7 = 6;
        int r8 = 0;
        while (true) {
            int r9 = r7 - 1;
            long j2 = r42[r7] & 4294967295L;
            long j3 = j2 * j2;
            int r62 = r6 - 1;
            r43[r62] = (r8 << 31) | ((int) (j3 >>> 33));
            r6 = r62 - 1;
            r43[r6] = (int) (j3 >>> 1);
            int r11 = (int) j3;
            if (r9 <= 0) {
                long j4 = j * j;
                r43[0] = (int) j4;
                long j5 = r42[1] & 4294967295L;
                long j6 = (((r11 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r15 = (int) j6;
                r43[1] = (r15 << 1) | (((int) (j4 >>> 32)) & 1);
                int r63 = r15 >>> 31;
                long j7 = (r43[2] & 4294967295L) + (j6 >>> 32);
                long j8 = r42[2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r152 = (int) j9;
                r43[2] = (r152 << 1) | r63;
                long j10 = (r43[3] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r43[4] & 4294967295L) + (j10 >>> 32);
                long j12 = r42[3] & 4294967295L;
                long j13 = (r43[5] & 4294967295L) + (j11 >>> 32);
                long j14 = j11 & 4294967295L;
                long j15 = (r43[6] & 4294967295L) + (j13 >>> 32);
                long j16 = (j10 & 4294967295L) + (j12 * j);
                int r92 = (int) j16;
                r43[3] = (r152 >>> 31) | (r92 << 1);
                int r10 = r92 >>> 31;
                long j17 = j14 + (j16 >>> 32) + (j12 * j5);
                long j18 = (j13 & 4294967295L) + (j17 >>> 32) + (j12 * j8);
                long j19 = j15 + (j18 >>> 32);
                long j20 = r42[4] & 4294967295L;
                long j21 = (r43[7] & 4294967295L) + (j19 >>> 32);
                long j22 = j19 & 4294967295L;
                long j23 = (r43[8] & 4294967295L) + (j21 >>> 32);
                long j24 = (j17 & 4294967295L) + (j20 * j);
                int r153 = (int) j24;
                r43[4] = (r153 << 1) | r10;
                long j25 = (j18 & 4294967295L) + (j24 >>> 32) + (j20 * j5);
                long j26 = j22 + (j25 >>> 32) + (j20 * j8);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j20 * j12);
                long j28 = j23 + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = r42[5] & 4294967295L;
                long j31 = (r43[9] & 4294967295L) + (j28 >>> 32);
                long j32 = j28 & 4294967295L;
                long j33 = (r43[10] & 4294967295L) + (j31 >>> 32);
                long j34 = (j25 & 4294967295L) + (j30 * j);
                int r0 = (int) j34;
                r43[5] = (r0 << 1) | (r153 >>> 31);
                long j35 = (j26 & 4294967295L) + (j34 >>> 32) + (j30 * j5);
                long j36 = j29 + (j35 >>> 32) + (j30 * j8);
                long j37 = j32 + (j36 >>> 32) + (j30 * j12);
                long j38 = (j31 & 4294967295L) + (j37 >>> 32) + (j30 * j20);
                long j39 = j33 + (j38 >>> 32);
                long j40 = j38 & 4294967295L;
                long j41 = r42[6] & 4294967295L;
                long j42 = (r43[11] & 4294967295L) + (j39 >>> 32);
                long j43 = 4294967295L & j42;
                long j44 = (j35 & 4294967295L) + (j * j41);
                int r1 = (int) j44;
                r43[6] = (r0 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                long j45 = (j36 & 4294967295L) + (j44 >>> 32) + (j41 * j5);
                long j46 = (j37 & 4294967295L) + (j45 >>> 32) + (j41 * j8);
                long j47 = j40 + (j46 >>> 32) + (j41 * j12);
                long j48 = (j39 & 4294967295L) + (j47 >>> 32) + (j41 * j20);
                long j49 = j43 + (j48 >>> 32) + (j41 * j30);
                long j50 = (r43[12] & 4294967295L) + (j42 >>> 32) + (j49 >>> 32);
                int r93 = (int) j45;
                r43[7] = r02 | (r93 << 1);
                int r2 = (int) j46;
                r43[8] = (r93 >>> 31) | (r2 << 1);
                int r12 = (int) j47;
                r43[9] = (r2 >>> 31) | (r12 << 1);
                int r03 = r12 >>> 31;
                int r13 = (int) j48;
                r43[10] = r03 | (r13 << 1);
                int r04 = r13 >>> 31;
                int r14 = (int) j49;
                r43[11] = r04 | (r14 << 1);
                int r05 = r14 >>> 31;
                int r16 = (int) j50;
                r43[12] = r05 | (r16 << 1);
                r43[13] = (r16 >>> 31) | ((r43[13] + ((int) (j50 >>> 32))) << 1);
                return;
            }
            r7 = r9;
            r8 = r11;
        }
    }

    public static int sub(int[] r9, int r10, int[] r11, int r12, int[] r13, int r14) {
        long j = ((r9[r10 + 0] & 4294967295L) - (r11[r12 + 0] & 4294967295L)) + 0;
        r13[r14 + 0] = (int) j;
        long j2 = (j >> 32) + ((r9[r10 + 1] & 4294967295L) - (r11[r12 + 1] & 4294967295L));
        r13[r14 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((r9[r10 + 2] & 4294967295L) - (r11[r12 + 2] & 4294967295L));
        r13[r14 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((r9[r10 + 3] & 4294967295L) - (r11[r12 + 3] & 4294967295L));
        r13[r14 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((r9[r10 + 4] & 4294967295L) - (r11[r12 + 4] & 4294967295L));
        r13[r14 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((r9[r10 + 5] & 4294967295L) - (r11[r12 + 5] & 4294967295L));
        r13[r14 + 5] = (int) j6;
        long j7 = (j6 >> 32) + ((r9[r10 + 6] & 4294967295L) - (r11[r12 + 6] & 4294967295L));
        r13[r14 + 6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int sub(int[] r10, int[] r11, int[] r12) {
        long j = ((r10[0] & 4294967295L) - (r11[0] & 4294967295L)) + 0;
        r12[0] = (int) j;
        long j2 = (j >> 32) + ((r10[1] & 4294967295L) - (r11[1] & 4294967295L));
        r12[1] = (int) j2;
        long j3 = (j2 >> 32) + ((r10[2] & 4294967295L) - (r11[2] & 4294967295L));
        r12[2] = (int) j3;
        long j4 = (j3 >> 32) + ((r10[3] & 4294967295L) - (r11[3] & 4294967295L));
        r12[3] = (int) j4;
        long j5 = (j4 >> 32) + ((r10[4] & 4294967295L) - (r11[4] & 4294967295L));
        r12[4] = (int) j5;
        long j6 = (j5 >> 32) + ((r10[5] & 4294967295L) - (r11[5] & 4294967295L));
        r12[5] = (int) j6;
        long j7 = (j6 >> 32) + ((r10[6] & 4294967295L) - (r11[6] & 4294967295L));
        r12[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int subBothFrom(int[] r10, int[] r11, int[] r12) {
        long j = (((r12[0] & 4294967295L) - (r10[0] & 4294967295L)) - (r11[0] & 4294967295L)) + 0;
        r12[0] = (int) j;
        long j2 = (j >> 32) + (((r12[1] & 4294967295L) - (r10[1] & 4294967295L)) - (r11[1] & 4294967295L));
        r12[1] = (int) j2;
        long j3 = (j2 >> 32) + (((r12[2] & 4294967295L) - (r10[2] & 4294967295L)) - (r11[2] & 4294967295L));
        r12[2] = (int) j3;
        long j4 = (j3 >> 32) + (((r12[3] & 4294967295L) - (r10[3] & 4294967295L)) - (r11[3] & 4294967295L));
        r12[3] = (int) j4;
        long j5 = (j4 >> 32) + (((r12[4] & 4294967295L) - (r10[4] & 4294967295L)) - (r11[4] & 4294967295L));
        r12[4] = (int) j5;
        long j6 = (j5 >> 32) + (((r12[5] & 4294967295L) - (r10[5] & 4294967295L)) - (r11[5] & 4294967295L));
        r12[5] = (int) j6;
        long j7 = (j6 >> 32) + (((r12[6] & 4294967295L) - (r10[6] & 4294967295L)) - (r11[6] & 4294967295L));
        r12[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int subFrom(int[] r10, int r11, int[] r12, int r13) {
        int r0 = r13 + 0;
        long j = ((r12[r0] & 4294967295L) - (r10[r11 + 0] & 4294967295L)) + 0;
        r12[r0] = (int) j;
        int r5 = r13 + 1;
        long j2 = (j >> 32) + ((r12[r5] & 4294967295L) - (r10[r11 + 1] & 4294967295L));
        r12[r5] = (int) j2;
        int r52 = r13 + 2;
        long j3 = (j2 >> 32) + ((r12[r52] & 4294967295L) - (r10[r11 + 2] & 4294967295L));
        r12[r52] = (int) j3;
        int r53 = r13 + 3;
        long j4 = (j3 >> 32) + ((r12[r53] & 4294967295L) - (r10[r11 + 3] & 4294967295L));
        r12[r53] = (int) j4;
        int r54 = r13 + 4;
        long j5 = (j4 >> 32) + ((r12[r54] & 4294967295L) - (r10[r11 + 4] & 4294967295L));
        r12[r54] = (int) j5;
        int r55 = r13 + 5;
        long j6 = (j5 >> 32) + ((r12[r55] & 4294967295L) - (r10[r11 + 5] & 4294967295L));
        r12[r55] = (int) j6;
        int r132 = r13 + 6;
        long j7 = (j6 >> 32) + ((r12[r132] & 4294967295L) - (r10[r11 + 6] & 4294967295L));
        r12[r132] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static int subFrom(int[] r10, int[] r11) {
        long j = ((r11[0] & 4294967295L) - (r10[0] & 4294967295L)) + 0;
        r11[0] = (int) j;
        long j2 = (j >> 32) + ((r11[1] & 4294967295L) - (r10[1] & 4294967295L));
        r11[1] = (int) j2;
        long j3 = (j2 >> 32) + ((r11[2] & 4294967295L) - (r10[2] & 4294967295L));
        r11[2] = (int) j3;
        long j4 = (j3 >> 32) + ((r11[3] & 4294967295L) - (r10[3] & 4294967295L));
        r11[3] = (int) j4;
        long j5 = (j4 >> 32) + ((r11[4] & 4294967295L) - (r10[4] & 4294967295L));
        r11[4] = (int) j5;
        long j6 = (j5 >> 32) + ((r11[5] & 4294967295L) - (r10[5] & 4294967295L));
        r11[5] = (int) j6;
        long j7 = (j6 >> 32) + ((r11[6] & 4294967295L) - (4294967295L & r10[6]));
        r11[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    public static BigInteger toBigInteger(int[] r4) {
        byte[] bArr = new byte[28];
        for (int r1 = 0; r1 < 7; r1++) {
            int r2 = r4[r1];
            if (r2 != 0) {
                Pack.intToBigEndian(r2, bArr, (6 - r1) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] r2) {
        r2[0] = 0;
        r2[1] = 0;
        r2[2] = 0;
        r2[3] = 0;
        r2[4] = 0;
        r2[5] = 0;
        r2[6] = 0;
    }
}
