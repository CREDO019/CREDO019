package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat160 {

    /* renamed from: M */
    private static final long f2383M = 4294967295L;

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
        return (int) (j5 >>> 32);
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
        return (int) (j5 >>> 32);
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
        int r122 = r12 + 4;
        long j5 = (j4 >>> 32) + (r9[r10 + 4] & 4294967295L) + (4294967295L & r11[r122]);
        r11[r122] = (int) j5;
        return (int) (j5 >>> 32);
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
        long j5 = (j4 >>> 32) + (r10[4] & 4294967295L) + (4294967295L & r11[4]);
        r11[4] = (int) j5;
        return (int) (j5 >>> 32);
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
        int r122 = r12 + 4;
        int r142 = r14 + 4;
        long j5 = (j4 >>> 32) + (r11[r122] & 4294967295L) + (4294967295L & r13[r142]);
        int r3 = (int) j5;
        r11[r122] = r3;
        r13[r142] = r3;
        return (int) (j5 >>> 32);
    }

    public static void copy(int[] r2, int r3, int[] r4, int r5) {
        r4[r5 + 0] = r2[r3 + 0];
        r4[r5 + 1] = r2[r3 + 1];
        r4[r5 + 2] = r2[r3 + 2];
        r4[r5 + 3] = r2[r3 + 3];
        r4[r5 + 4] = r2[r3 + 4];
    }

    public static void copy(int[] r2, int[] r3) {
        r3[0] = r2[0];
        r3[1] = r2[1];
        r3[2] = r2[2];
        r3[3] = r2[3];
        r3[4] = r2[4];
    }

    public static int[] create() {
        return new int[5];
    }

    public static int[] createExt() {
        return new int[10];
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
    public static boolean m21eq(int[] r3, int[] r4) {
        for (int r0 = 4; r0 >= 0; r0--) {
            if (r3[r0] != r4[r0]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 160) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int r1 = 0; r1 < 5; r1++) {
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
            if (r1 < 0 || r1 >= 5) {
                return 0;
            }
            r32 = r3[r1] >>> (r4 & 31);
        }
        return r32 & 1;
    }

    public static boolean gte(int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 4; r0 >= 0; r0--) {
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
        for (int r0 = 4; r0 >= 0; r0--) {
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
        for (int r1 = 1; r1 < 5; r1++) {
            if (r4[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] r3) {
        for (int r1 = 0; r1 < 5; r1++) {
            if (r3[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] r27, int r28, int[] r29, int r30, int[] r31, int r32) {
        long j = r29[r30 + 0] & 4294967295L;
        long j2 = r29[r30 + 1] & 4294967295L;
        long j3 = r29[r30 + 2] & 4294967295L;
        long j4 = r29[r30 + 3] & 4294967295L;
        long j5 = r29[r30 + 4] & 4294967295L;
        long j6 = r27[r28 + 0] & 4294967295L;
        long j7 = (j6 * j) + 0;
        r31[r32 + 0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        r31[r32 + 1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j3);
        r31[r32 + 2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j4);
        r31[r32 + 3] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j5);
        r31[r32 + 4] = (int) j11;
        r31[r32 + 5] = (int) (j11 >>> 32);
        int r3 = 1;
        int r4 = r32;
        int r5 = 1;
        while (r5 < 5) {
            r4 += r3;
            long j12 = r27[r28 + r5] & 4294967295L;
            int r20 = r4 + 0;
            long j13 = (j12 * j) + (r31[r20] & 4294967295L) + 0;
            r31[r20] = (int) j13;
            int r15 = r4 + 1;
            long j14 = (j13 >>> 32) + (j12 * j2) + (r31[r15] & 4294967295L);
            r31[r15] = (int) j14;
            int r33 = r4 + 2;
            long j15 = (j14 >>> 32) + (j12 * j3) + (r31[r33] & 4294967295L);
            r31[r33] = (int) j15;
            int r34 = r4 + 3;
            long j16 = (j15 >>> 32) + (j12 * j4) + (r31[r34] & 4294967295L);
            r31[r34] = (int) j16;
            int r35 = r4 + 4;
            long j17 = (j16 >>> 32) + (j12 * j5) + (r31[r35] & 4294967295L);
            r31[r35] = (int) j17;
            r31[r4 + 5] = (int) (j17 >>> 32);
            r5++;
            j3 = j3;
            j = j;
            r3 = 1;
        }
    }

    public static void mul(int[] r27, int[] r28, int[] r29) {
        long j = r28[0] & 4294967295L;
        int r5 = 1;
        long j2 = r28[1] & 4294967295L;
        long j3 = r28[2] & 4294967295L;
        long j4 = r28[3] & 4294967295L;
        long j5 = r28[4] & 4294967295L;
        long j6 = r27[0] & 4294967295L;
        long j7 = (j6 * j) + 0;
        r29[0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        r29[1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j3);
        r29[2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j4);
        r29[3] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j5);
        r29[4] = (int) j11;
        int r4 = (int) (j11 >>> 32);
        r29[5] = r4;
        for (int r3 = 5; r5 < r3; r3 = 5) {
            long j12 = r27[r5] & 4294967295L;
            int r42 = r5 + 0;
            long j13 = (j12 * j) + (r29[r42] & 4294967295L) + 0;
            r29[r42] = (int) j13;
            int r16 = r5 + 1;
            long j14 = j2;
            long j15 = (j13 >>> 32) + (j12 * j2) + (r29[r16] & 4294967295L);
            r29[r16] = (int) j15;
            int r6 = r5 + 2;
            long j16 = j5;
            long j17 = (j15 >>> 32) + (j12 * j3) + (r29[r6] & 4294967295L);
            r29[r6] = (int) j17;
            int r62 = r5 + 3;
            long j18 = (j17 >>> 32) + (j12 * j4) + (r29[r62] & 4294967295L);
            r29[r62] = (int) j18;
            int r32 = r5 + 4;
            long j19 = (j18 >>> 32) + (j12 * j16) + (r29[r32] & 4294967295L);
            r29[r32] = (int) j19;
            r29[r5 + 5] = (int) (j19 >>> 32);
            r5 = r16;
            j5 = j16;
            j = j;
            j2 = j14;
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
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (4294967295L & r16[r17 + 4]);
        r18[r19 + 4] = (int) j12;
        return (j12 >>> 32) + j11;
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
        return Nat.incAt(5, r18, r19, 4);
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
        return Nat.incAt(5, r12, r13, 3);
    }

    public static int mulAddTo(int[] r29, int r30, int[] r31, int r32, int[] r33, int r34) {
        int r20;
        int r18;
        int r6;
        int r4;
        int r14;
        long j = 4294967295L;
        long j2 = r31[r32 + 0] & 4294967295L;
        long j3 = r31[r32 + 1] & 4294967295L;
        long j4 = r31[r32 + 2] & 4294967295L;
        long j5 = r31[r32 + 3] & 4294967295L;
        long j6 = r31[r32 + 4] & 4294967295L;
        int r142 = r34;
        int r15 = 0;
        long j7 = 0;
        while (r15 < 5) {
            long j8 = r29[r30 + r15] & j;
            long j9 = (j8 * j2) + (r33[r20] & j) + 0;
            r33[r142 + 0] = (int) j9;
            int r3 = r142 + 1;
            long j10 = j3;
            long j11 = (j9 >>> 32) + (j8 * j3) + (r33[r3] & 4294967295L);
            r33[r3] = (int) j11;
            long j12 = j4;
            long j13 = (j11 >>> 32) + (j8 * j4) + (r33[r18] & 4294967295L);
            r33[r142 + 2] = (int) j13;
            long j14 = (j13 >>> 32) + (j8 * j5) + (r33[r6] & 4294967295L);
            r33[r142 + 3] = (int) j14;
            long j15 = (j14 >>> 32) + (j8 * j6) + (r33[r4] & 4294967295L);
            r33[r142 + 4] = (int) j15;
            long j16 = (j15 >>> 32) + (r33[r14] & 4294967295L) + j7;
            r33[r142 + 5] = (int) j16;
            j7 = j16 >>> 32;
            r15++;
            r142 = r3;
            j2 = j2;
            j = 4294967295L;
            j3 = j10;
            j4 = j12;
        }
        return (int) j7;
    }

    public static int mulAddTo(int[] r30, int[] r31, int[] r32) {
        int r21;
        int r212;
        int r7;
        int r5;
        int r0;
        int r02 = 0;
        long j = 4294967295L;
        long j2 = r31[0] & 4294967295L;
        long j3 = r31[1] & 4294967295L;
        long j4 = r31[2] & 4294967295L;
        long j5 = r31[3] & 4294967295L;
        long j6 = r31[4] & 4294967295L;
        long j7 = 0;
        while (r02 < 5) {
            long j8 = r30[r02] & j;
            long j9 = (j8 * j2) + (r32[r21] & j) + 0;
            r32[r02 + 0] = (int) j9;
            int r4 = r02 + 1;
            long j10 = j3;
            long j11 = (j9 >>> 32) + (j8 * j3) + (r32[r4] & 4294967295L);
            r32[r4] = (int) j11;
            long j12 = j4;
            long j13 = (j11 >>> 32) + (j8 * j4) + (r32[r212] & 4294967295L);
            r32[r02 + 2] = (int) j13;
            long j14 = (j13 >>> 32) + (j8 * j5) + (r32[r7] & 4294967295L);
            r32[r02 + 3] = (int) j14;
            long j15 = (j14 >>> 32) + (j8 * j6) + (r32[r5] & 4294967295L);
            r32[r02 + 4] = (int) j15;
            long j16 = (j15 >>> 32) + (r32[r0] & 4294967295L) + j7;
            r32[r02 + 5] = (int) j16;
            j7 = j16 >>> 32;
            r02 = r4;
            j = 4294967295L;
            j2 = j2;
            j4 = j12;
            j3 = j10;
        }
        return (int) j7;
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
        } while (r82 < 5);
        return (int) j2;
    }

    public static int mulWordAddExt(int r11, int[] r12, int r13, int[] r14, int r15) {
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
        int r152 = r15 + 4;
        long j6 = (j5 >>> 32) + (j * (r12[r13 + 4] & 4294967295L)) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j6;
        return (int) (j6 >>> 32);
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
        return Nat.incAt(5, r13, r14, 3);
    }

    public static int mulWordsAdd(int r8, int r9, int[] r10, int r11) {
        int r0 = r11 + 0;
        long j = ((r9 & 4294967295L) * (r8 & 4294967295L)) + (r10[r0] & 4294967295L) + 0;
        r10[r0] = (int) j;
        int r1 = r11 + 1;
        long j2 = (j >>> 32) + (4294967295L & r10[r1]);
        r10[r1] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, r10, r11, 2);
    }

    public static void square(int[] r32, int r33, int[] r34, int r35) {
        int r9;
        int r15;
        int r7;
        int r6;
        int r24;
        int r3;
        int r27;
        long j = r32[r33 + 0] & 4294967295L;
        int r5 = 0;
        int r62 = 10;
        int r72 = 4;
        while (true) {
            int r8 = r72 - 1;
            long j2 = r32[r33 + r72] & 4294967295L;
            long j3 = j2 * j2;
            int r63 = r62 - 1;
            r34[r35 + r63] = (r5 << 31) | ((int) (j3 >>> 33));
            r62 = r63 - 1;
            r34[r35 + r62] = (int) (j3 >>> 1);
            r5 = (int) j3;
            if (r8 <= 0) {
                long j4 = j * j;
                r34[r35 + 0] = (int) j4;
                long j5 = r32[r33 + 1] & 4294967295L;
                long j6 = (((r5 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r152 = (int) j6;
                r34[r35 + 1] = (r152 << 1) | (((int) (j4 >>> 32)) & 1);
                int r82 = r152 >>> 31;
                long j7 = (r34[r9] & 4294967295L) + (j6 >>> 32);
                long j8 = r32[r33 + 2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r12 = (int) j9;
                r34[r35 + 2] = (r12 << 1) | r82;
                int r83 = r12 >>> 31;
                long j10 = (r34[r15] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r34[r7] & 4294967295L) + (j10 >>> 32);
                long j12 = r32[r33 + 3] & 4294967295L;
                long j13 = (r34[r6] & 4294967295L) + (j11 >>> 32);
                long j14 = (r34[r24] & 4294967295L) + (j13 >>> 32);
                long j15 = (j10 & 4294967295L) + (j12 * j);
                int r2 = (int) j15;
                r34[r35 + 3] = (r2 << 1) | r83;
                long j16 = (j11 & 4294967295L) + (j15 >>> 32) + (j12 * j5);
                long j17 = (j13 & 4294967295L) + (j16 >>> 32) + (j12 * j8);
                long j18 = j14 + (j17 >>> 32);
                long j19 = j17 & 4294967295L;
                long j20 = r32[r33 + 4] & 4294967295L;
                long j21 = (r34[r3] & 4294967295L) + (j18 >>> 32);
                long j22 = (j16 & 4294967295L) + (j * j20);
                int r0 = (int) j22;
                r34[r35 + 4] = (r0 << 1) | (r2 >>> 31);
                long j23 = j19 + (j22 >>> 32) + (j20 * j5);
                long j24 = (j18 & 4294967295L) + (j23 >>> 32) + (j20 * j8);
                long j25 = (j21 & 4294967295L) + (j24 >>> 32) + (j20 * j12);
                long j26 = (r34[r27] & 4294967295L) + (j21 >>> 32) + (j25 >>> 32);
                int r52 = (int) j23;
                r34[r35 + 5] = (r0 >>> 31) | (r52 << 1);
                int r22 = (int) j24;
                r34[r35 + 6] = (r52 >>> 31) | (r22 << 1);
                int r1 = (int) j25;
                r34[r35 + 7] = (r22 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                int r13 = (int) j26;
                r34[r35 + 8] = r02 | (r13 << 1);
                int r03 = r13 >>> 31;
                int r14 = r35 + 9;
                r34[r14] = r03 | ((r34[r14] + ((int) (j26 >>> 32))) << 1);
                return;
            }
            r72 = r8;
        }
    }

    public static void square(int[] r31, int[] r32) {
        long j = r31[0] & 4294967295L;
        int r6 = 10;
        int r7 = 4;
        int r8 = 0;
        while (true) {
            int r9 = r7 - 1;
            long j2 = r31[r7] & 4294967295L;
            long j3 = j2 * j2;
            int r62 = r6 - 1;
            r32[r62] = (r8 << 31) | ((int) (j3 >>> 33));
            r6 = r62 - 1;
            r32[r6] = (int) (j3 >>> 1);
            int r11 = (int) j3;
            if (r9 <= 0) {
                long j4 = j * j;
                r32[0] = (int) j4;
                long j5 = r31[1] & 4294967295L;
                long j6 = (((r11 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r15 = (int) j6;
                r32[1] = (r15 << 1) | (((int) (j4 >>> 32)) & 1);
                long j7 = (r32[2] & 4294967295L) + (j6 >>> 32);
                long j8 = r31[2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r72 = (int) j9;
                r32[2] = (r72 << 1) | (r15 >>> 31);
                long j10 = (r32[3] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r32[4] & 4294967295L) + (j10 >>> 32);
                long j12 = r31[3] & 4294967295L;
                long j13 = (r32[5] & 4294967295L) + (j11 >>> 32);
                long j14 = j11 & 4294967295L;
                long j15 = (r32[6] & 4294967295L) + (j13 >>> 32);
                long j16 = (j10 & 4294967295L) + (j12 * j);
                int r12 = (int) j16;
                r32[3] = (r12 << 1) | (r72 >>> 31);
                int r73 = r12 >>> 31;
                long j17 = j14 + (j16 >>> 32) + (j12 * j5);
                long j18 = (j13 & 4294967295L) + (j17 >>> 32) + (j12 * j8);
                long j19 = j15 + (j18 >>> 32);
                long j20 = r31[4] & 4294967295L;
                long j21 = (r32[7] & 4294967295L) + (j19 >>> 32);
                long j22 = 4294967295L & j21;
                long j23 = (j17 & 4294967295L) + (j20 * j);
                int r14 = (int) j23;
                r32[4] = r73 | (r14 << 1);
                long j24 = (j18 & 4294967295L) + (j23 >>> 32) + (j5 * j20);
                long j25 = (j19 & 4294967295L) + (j24 >>> 32) + (j20 * j8);
                long j26 = j22 + (j25 >>> 32) + (j20 * j12);
                long j27 = (r32[8] & 4294967295L) + (j21 >>> 32) + (j26 >>> 32);
                int r82 = (int) j24;
                r32[5] = (r14 >>> 31) | (r82 << 1);
                int r1 = (int) j25;
                r32[6] = (r1 << 1) | (r82 >>> 31);
                int r0 = r1 >>> 31;
                int r13 = (int) j26;
                r32[7] = r0 | (r13 << 1);
                int r02 = r13 >>> 31;
                int r16 = (int) j27;
                r32[8] = r02 | (r16 << 1);
                r32[9] = (r16 >>> 31) | ((r32[9] + ((int) (j27 >>> 32))) << 1);
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        return (int) (j5 >> 32);
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
        int r132 = r13 + 4;
        long j5 = (j4 >> 32) + ((r12[r132] & 4294967295L) - (r10[r11 + 4] & 4294967295L));
        r12[r132] = (int) j5;
        return (int) (j5 >> 32);
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
        long j5 = (j4 >> 32) + ((r11[4] & 4294967295L) - (4294967295L & r10[4]));
        r11[4] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static BigInteger toBigInteger(int[] r4) {
        byte[] bArr = new byte[20];
        for (int r1 = 0; r1 < 5; r1++) {
            int r2 = r4[r1];
            if (r2 != 0) {
                Pack.intToBigEndian(r2, bArr, (4 - r1) << 2);
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
    }
}
