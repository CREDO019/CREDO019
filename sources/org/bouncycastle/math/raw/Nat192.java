package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat192 {

    /* renamed from: M */
    private static final long f2384M = 4294967295L;

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
        return (int) (j6 >>> 32);
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
        return (int) (j6 >>> 32);
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
        int r122 = r12 + 5;
        long j6 = (j5 >>> 32) + (r9[r10 + 5] & 4294967295L) + (4294967295L & r11[r122]);
        r11[r122] = (int) j6;
        return (int) (j6 >>> 32);
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
        long j6 = (j5 >>> 32) + (r10[5] & 4294967295L) + (4294967295L & r11[5]);
        r11[5] = (int) j6;
        return (int) (j6 >>> 32);
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
        int r122 = r12 + 5;
        int r142 = r14 + 5;
        long j6 = (j5 >>> 32) + (r11[r122] & 4294967295L) + (4294967295L & r13[r142]);
        int r3 = (int) j6;
        r11[r122] = r3;
        r13[r142] = r3;
        return (int) (j6 >>> 32);
    }

    public static void copy(int[] r2, int r3, int[] r4, int r5) {
        r4[r5 + 0] = r2[r3 + 0];
        r4[r5 + 1] = r2[r3 + 1];
        r4[r5 + 2] = r2[r3 + 2];
        r4[r5 + 3] = r2[r3 + 3];
        r4[r5 + 4] = r2[r3 + 4];
        r4[r5 + 5] = r2[r3 + 5];
    }

    public static void copy(int[] r2, int[] r3) {
        r3[0] = r2[0];
        r3[1] = r2[1];
        r3[2] = r2[2];
        r3[3] = r2[3];
        r3[4] = r2[4];
        r3[5] = r2[5];
    }

    public static void copy64(long[] jArr, int r4, long[] jArr2, int r6) {
        jArr2[r6 + 0] = jArr[r4 + 0];
        jArr2[r6 + 1] = jArr[r4 + 1];
        jArr2[r6 + 2] = jArr[r4 + 2];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    public static int[] create() {
        return new int[6];
    }

    public static long[] create64() {
        return new long[3];
    }

    public static int[] createExt() {
        return new int[12];
    }

    public static long[] createExt64() {
        return new long[6];
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
    public static boolean m20eq(int[] r3, int[] r4) {
        for (int r0 = 5; r0 >= 0; r0--) {
            if (r3[r0] != r4[r0]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int r0 = 2; r0 >= 0; r0--) {
            if (jArr[r0] != jArr2[r0]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int r1 = 0; r1 < 6; r1++) {
            create[r1] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int r1 = 0; r1 < 3; r1++) {
            create64[r1] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static int getBit(int[] r3, int r4) {
        int r32;
        if (r4 == 0) {
            r32 = r3[0];
        } else {
            int r1 = r4 >> 5;
            if (r1 < 0 || r1 >= 6) {
                return 0;
            }
            r32 = r3[r1] >>> (r4 & 31);
        }
        return r32 & 1;
    }

    public static boolean gte(int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 5; r0 >= 0; r0--) {
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
        for (int r0 = 5; r0 >= 0; r0--) {
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
        for (int r1 = 1; r1 < 6; r1++) {
            if (r4[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int r2 = 1; r2 < 3; r2++) {
            if (jArr[r2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] r3) {
        for (int r1 = 0; r1 < 6; r1++) {
            if (r3[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int r1 = 0; r1 < 3; r1++) {
            if (jArr[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] r28, int r29, int[] r30, int r31, int[] r32, int r33) {
        long j = r30[r31 + 0] & 4294967295L;
        long j2 = r30[r31 + 1] & 4294967295L;
        long j3 = r30[r31 + 2] & 4294967295L;
        long j4 = r30[r31 + 3] & 4294967295L;
        long j5 = r30[r31 + 4] & 4294967295L;
        long j6 = r30[r31 + 5] & 4294967295L;
        long j7 = r28[r29 + 0] & 4294967295L;
        long j8 = (j7 * j) + 0;
        r32[r33 + 0] = (int) j8;
        long j9 = (j8 >>> 32) + (j7 * j2);
        r32[r33 + 1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j3);
        r32[r33 + 2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j4);
        r32[r33 + 3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j5);
        r32[r33 + 4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        r32[r33 + 5] = (int) j13;
        r32[r33 + 6] = (int) (j13 >>> 32);
        int r1 = 1;
        int r2 = r33;
        int r3 = 1;
        while (r3 < 6) {
            r2 += r1;
            long j14 = r28[r29 + r3] & 4294967295L;
            int r24 = r2 + 0;
            long j15 = (j14 * j) + (r32[r24] & 4294967295L) + 0;
            r32[r24] = (int) j15;
            int r15 = r2 + 1;
            long j16 = j6;
            long j17 = (j15 >>> 32) + (j14 * j2) + (r32[r15] & 4294967295L);
            r32[r15] = (int) j17;
            int r152 = r2 + 2;
            long j18 = (j17 >>> 32) + (j14 * j3) + (r32[r152] & 4294967295L);
            r32[r152] = (int) j18;
            int r7 = r2 + 3;
            long j19 = (j18 >>> 32) + (j14 * j4) + (r32[r7] & 4294967295L);
            r32[r7] = (int) j19;
            int r72 = r2 + 4;
            long j20 = (j19 >>> 32) + (j14 * j5) + (r32[r72] & 4294967295L);
            r32[r72] = (int) j20;
            int r73 = r2 + 5;
            long j21 = (j20 >>> 32) + (j14 * j16) + (r32[r73] & 4294967295L);
            r32[r73] = (int) j21;
            r32[r2 + 6] = (int) (j21 >>> 32);
            r3++;
            j3 = j3;
            j6 = j16;
            r1 = 1;
        }
    }

    public static void mul(int[] r30, int[] r31, int[] r32) {
        long j = r31[0] & 4294967295L;
        long j2 = r31[1] & 4294967295L;
        long j3 = r31[2] & 4294967295L;
        long j4 = r31[3] & 4294967295L;
        long j5 = r31[4] & 4294967295L;
        long j6 = r31[5] & 4294967295L;
        long j7 = r30[0] & 4294967295L;
        long j8 = (j7 * j) + 0;
        r32[0] = (int) j8;
        long j9 = (j8 >>> 32) + (j7 * j2);
        r32[1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j3);
        r32[2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j4);
        r32[3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j5);
        r32[4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        r32[5] = (int) j13;
        int r4 = (int) (j13 >>> 32);
        r32[6] = r4;
        int r5 = 1;
        for (int r3 = 6; r5 < r3; r3 = 6) {
            long j14 = r30[r5] & 4294967295L;
            int r42 = r5 + 0;
            long j15 = (j14 * j) + (r32[r42] & 4294967295L) + 0;
            r32[r42] = (int) j15;
            int r18 = r5 + 1;
            long j16 = j2;
            long j17 = (j15 >>> 32) + (j14 * j2) + (r32[r18] & 4294967295L);
            r32[r18] = (int) j17;
            int r6 = r5 + 2;
            long j18 = j6;
            long j19 = (j17 >>> 32) + (j14 * j3) + (r32[r6] & 4294967295L);
            r32[r6] = (int) j19;
            int r62 = r5 + 3;
            long j20 = (j19 >>> 32) + (j14 * j4) + (r32[r62] & 4294967295L);
            r32[r62] = (int) j20;
            int r63 = r5 + 4;
            long j21 = (j20 >>> 32) + (j14 * j5) + (r32[r63] & 4294967295L);
            r32[r63] = (int) j21;
            int r33 = r5 + 5;
            long j22 = (j21 >>> 32) + (j14 * j18) + (r32[r33] & 4294967295L);
            r32[r33] = (int) j22;
            r32[r5 + 6] = (int) (j22 >>> 32);
            r5 = r18;
            j = j;
            j2 = j16;
            j6 = j18;
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
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (4294967295L & r16[r17 + 5]);
        r18[r19 + 5] = (int) j14;
        return (j14 >>> 32) + j13;
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
        return Nat.incAt(6, r18, r19, 4);
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
        return Nat.incAt(6, r12, r13, 3);
    }

    public static int mulAddTo(int[] r31, int r32, int[] r33, int r34, int[] r35, int r36) {
        int r22;
        int r20;
        int r6;
        int r62;
        int r4;
        int r16;
        long j = 4294967295L;
        long j2 = r33[r34 + 0] & 4294967295L;
        long j3 = r33[r34 + 1] & 4294967295L;
        long j4 = r33[r34 + 2] & 4294967295L;
        long j5 = r33[r34 + 3] & 4294967295L;
        long j6 = r33[r34 + 4] & 4294967295L;
        long j7 = r33[r34 + 5] & 4294967295L;
        int r162 = r36;
        int r14 = 0;
        long j8 = 0;
        while (r14 < 6) {
            int r19 = r14;
            long j9 = r31[r32 + r14] & j;
            long j10 = (j9 * j2) + (r35[r22] & j) + 0;
            r35[r162 + 0] = (int) j10;
            int r3 = r162 + 1;
            long j11 = j3;
            long j12 = (j10 >>> 32) + (j9 * j3) + (r35[r3] & 4294967295L);
            r35[r3] = (int) j12;
            long j13 = j4;
            long j14 = (j12 >>> 32) + (j9 * j4) + (r35[r20] & 4294967295L);
            r35[r162 + 2] = (int) j14;
            long j15 = (j14 >>> 32) + (j9 * j5) + (r35[r6] & 4294967295L);
            r35[r162 + 3] = (int) j15;
            long j16 = (j15 >>> 32) + (j9 * j6) + (r35[r62] & 4294967295L);
            r35[r162 + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (j9 * j7) + (r35[r4] & 4294967295L);
            r35[r162 + 5] = (int) j17;
            long j18 = (j17 >>> 32) + (r35[r16] & 4294967295L) + j8;
            r35[r162 + 6] = (int) j18;
            j8 = j18 >>> 32;
            r14 = r19 + 1;
            r162 = r3;
            j2 = j2;
            j = 4294967295L;
            j3 = j11;
            j4 = j13;
        }
        return (int) j8;
    }

    public static int mulAddTo(int[] r30, int[] r31, int[] r32) {
        int r23;
        int r232;
        int r6;
        int r62;
        int r4;
        int r0;
        int r02 = 0;
        long j = 4294967295L;
        long j2 = r31[0] & 4294967295L;
        long j3 = r31[1] & 4294967295L;
        long j4 = r31[2] & 4294967295L;
        long j5 = r31[3] & 4294967295L;
        long j6 = r31[4] & 4294967295L;
        long j7 = r31[5] & 4294967295L;
        long j8 = 0;
        while (r02 < 6) {
            long j9 = j7;
            long j10 = r30[r02] & j;
            long j11 = j2;
            long j12 = (j10 * j2) + (r32[r23] & j) + 0;
            r32[r02 + 0] = (int) j12;
            int r16 = r02 + 1;
            long j13 = (j12 >>> 32) + (j10 * j3) + (r32[r16] & 4294967295L);
            r32[r16] = (int) j13;
            long j14 = (j13 >>> 32) + (j10 * j4) + (r32[r232] & 4294967295L);
            r32[r02 + 2] = (int) j14;
            long j15 = (j14 >>> 32) + (j10 * j5) + (r32[r6] & 4294967295L);
            r32[r02 + 3] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j6) + (r32[r62] & 4294967295L);
            r32[r02 + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j9) + (r32[r4] & 4294967295L);
            r32[r02 + 5] = (int) j17;
            long j18 = (j17 >>> 32) + (r32[r0] & 4294967295L) + j8;
            r32[r02 + 6] = (int) j18;
            j8 = j18 >>> 32;
            r02 = r16;
            j = 4294967295L;
            j7 = j9;
            j2 = j11;
            j3 = j3;
            j4 = j4;
        }
        return (int) j8;
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
        } while (r82 < 6);
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
        int r84 = r15 + 4;
        long j6 = (j5 >>> 32) + ((r12[r13 + 4] & 4294967295L) * j) + (r14[r84] & 4294967295L);
        r14[r84] = (int) j6;
        int r152 = r15 + 5;
        long j7 = (j6 >>> 32) + (j * (r12[r13 + 5] & 4294967295L)) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j7;
        return (int) (j7 >>> 32);
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
        return Nat.incAt(6, r13, r14, 3);
    }

    public static void square(int[] r38, int r39, int[] r40, int r41) {
        int r9;
        int r15;
        int r7;
        int r6;
        int r24;
        int r3;
        int r22;
        int r5;
        int r23;
        long j = r38[r39 + 0] & 4294967295L;
        int r52 = 0;
        int r62 = 12;
        int r72 = 5;
        while (true) {
            int r8 = r72 - 1;
            long j2 = r38[r39 + r72] & 4294967295L;
            long j3 = j2 * j2;
            int r63 = r62 - 1;
            r40[r41 + r63] = (r52 << 31) | ((int) (j3 >>> 33));
            r62 = r63 - 1;
            r40[r41 + r62] = (int) (j3 >>> 1);
            r52 = (int) j3;
            if (r8 <= 0) {
                long j4 = j * j;
                r40[r41 + 0] = (int) j4;
                long j5 = r38[r39 + 1] & 4294967295L;
                long j6 = (((r52 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r152 = (int) j6;
                r40[r41 + 1] = (r152 << 1) | (((int) (j4 >>> 32)) & 1);
                int r82 = r152 >>> 31;
                long j7 = (r40[r9] & 4294967295L) + (j6 >>> 32);
                long j8 = r38[r39 + 2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r12 = (int) j9;
                r40[r41 + 2] = (r12 << 1) | r82;
                int r83 = r12 >>> 31;
                long j10 = (r40[r15] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r40[r7] & 4294967295L) + (j10 >>> 32);
                long j12 = r38[r39 + 3] & 4294967295L;
                long j13 = (r40[r6] & 4294967295L) + (j11 >>> 32);
                long j14 = (r40[r24] & 4294967295L) + (j13 >>> 32);
                long j15 = (j10 & 4294967295L) + (j12 * j);
                int r2 = (int) j15;
                r40[r41 + 3] = (r2 << 1) | r83;
                long j16 = (j11 & 4294967295L) + (j15 >>> 32) + (j12 * j5);
                long j17 = (j13 & 4294967295L) + (j16 >>> 32) + (j12 * j8);
                long j18 = j14 + (j17 >>> 32);
                long j19 = j17 & 4294967295L;
                long j20 = r38[r39 + 4] & 4294967295L;
                long j21 = (r40[r3] & 4294967295L) + (j18 >>> 32);
                long j22 = j18 & 4294967295L;
                long j23 = (j16 & 4294967295L) + (j20 * j);
                int r53 = (int) j23;
                r40[r41 + 4] = (r2 >>> 31) | (r53 << 1);
                int r25 = r53 >>> 31;
                long j24 = j19 + (j23 >>> 32) + (j20 * j5);
                long j25 = j22 + (j24 >>> 32) + (j20 * j8);
                long j26 = (j21 & 4294967295L) + (j25 >>> 32) + (j20 * j12);
                long j27 = (r40[r22] & 4294967295L) + (j21 >>> 32) + (j26 >>> 32);
                long j28 = j26 & 4294967295L;
                long j29 = r38[r39 + 5] & 4294967295L;
                long j30 = (r40[r5] & 4294967295L) + (j27 >>> 32);
                long j31 = (j24 & 4294967295L) + (j * j29);
                int r0 = (int) j31;
                r40[r41 + 5] = (r0 << 1) | r25;
                long j32 = (j25 & 4294967295L) + (j31 >>> 32) + (j29 * j5);
                long j33 = j28 + (j32 >>> 32) + (j29 * j8);
                long j34 = (j27 & 4294967295L) + (j33 >>> 32) + (j29 * j12);
                long j35 = (j30 & 4294967295L) + (j34 >>> 32) + (j29 * j20);
                long j36 = (r40[r23] & 4294967295L) + (j30 >>> 32) + (j35 >>> 32);
                int r92 = (int) j32;
                r40[r41 + 6] = (r0 >>> 31) | (r92 << 1);
                int r26 = (int) j33;
                r40[r41 + 7] = (r92 >>> 31) | (r26 << 1);
                int r1 = (int) j34;
                r40[r41 + 8] = (r26 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                int r13 = (int) j35;
                r40[r41 + 9] = r02 | (r13 << 1);
                int r03 = r13 >>> 31;
                int r14 = (int) j36;
                r40[r41 + 10] = r03 | (r14 << 1);
                int r04 = r14 >>> 31;
                int r16 = r41 + 11;
                r40[r16] = r04 | ((r40[r16] + ((int) (j36 >>> 32))) << 1);
                return;
            }
            r72 = r8;
        }
    }

    public static void square(int[] r38, int[] r39) {
        long j = r38[0] & 4294967295L;
        int r6 = 12;
        int r7 = 5;
        int r8 = 0;
        while (true) {
            int r9 = r7 - 1;
            long j2 = r38[r7] & 4294967295L;
            long j3 = j2 * j2;
            int r62 = r6 - 1;
            r39[r62] = (r8 << 31) | ((int) (j3 >>> 33));
            r6 = r62 - 1;
            r39[r6] = (int) (j3 >>> 1);
            int r11 = (int) j3;
            if (r9 <= 0) {
                long j4 = j * j;
                r39[0] = (int) j4;
                long j5 = r38[1] & 4294967295L;
                long j6 = (((r11 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r15 = (int) j6;
                r39[1] = (r15 << 1) | (((int) (j4 >>> 32)) & 1);
                int r63 = r15 >>> 31;
                long j7 = (r39[2] & 4294967295L) + (j6 >>> 32);
                long j8 = r38[2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r152 = (int) j9;
                r39[2] = (r152 << 1) | r63;
                long j10 = (r39[3] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r39[4] & 4294967295L) + (j10 >>> 32);
                long j12 = r38[3] & 4294967295L;
                long j13 = (r39[5] & 4294967295L) + (j11 >>> 32);
                long j14 = j11 & 4294967295L;
                long j15 = (r39[6] & 4294967295L) + (j13 >>> 32);
                long j16 = (j10 & 4294967295L) + (j12 * j);
                int r92 = (int) j16;
                r39[3] = (r92 << 1) | (r152 >>> 31);
                int r10 = r92 >>> 31;
                long j17 = j14 + (j16 >>> 32) + (j12 * j5);
                long j18 = (j13 & 4294967295L) + (j17 >>> 32) + (j12 * j8);
                long j19 = j15 + (j18 >>> 32);
                long j20 = r38[4] & 4294967295L;
                long j21 = (r39[7] & 4294967295L) + (j19 >>> 32);
                long j22 = j19 & 4294967295L;
                long j23 = (r39[8] & 4294967295L) + (j21 >>> 32);
                long j24 = (j17 & 4294967295L) + (j20 * j);
                int r153 = (int) j24;
                r39[4] = (r153 << 1) | r10;
                long j25 = (j18 & 4294967295L) + (j24 >>> 32) + (j20 * j5);
                long j26 = j22 + (j25 >>> 32) + (j20 * j8);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j20 * j12);
                long j28 = j23 + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = r38[5] & 4294967295L;
                long j31 = (r39[9] & 4294967295L) + (j28 >>> 32);
                long j32 = 4294967295L & j31;
                long j33 = (j25 & 4294967295L) + (j * j30);
                int r0 = (int) j33;
                r39[5] = (r0 << 1) | (r153 >>> 31);
                long j34 = (j26 & 4294967295L) + (j33 >>> 32) + (j30 * j5);
                long j35 = j29 + (j34 >>> 32) + (j30 * j8);
                long j36 = (j28 & 4294967295L) + (j35 >>> 32) + (j30 * j12);
                long j37 = j32 + (j36 >>> 32) + (j30 * j20);
                long j38 = (r39[10] & 4294967295L) + (j31 >>> 32) + (j37 >>> 32);
                int r64 = (int) j34;
                r39[6] = (r0 >>> 31) | (r64 << 1);
                int r5 = (int) j35;
                r39[7] = (r64 >>> 31) | (r5 << 1);
                int r2 = (int) j36;
                r39[8] = (r5 >>> 31) | (r2 << 1);
                int r1 = (int) j37;
                r39[9] = (r2 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                int r12 = (int) j38;
                r39[10] = r02 | (r12 << 1);
                r39[11] = (r12 >>> 31) | ((r39[11] + ((int) (j38 >>> 32))) << 1);
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
        return (int) (j6 >> 32);
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
        return (int) (j6 >> 32);
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
        return (int) (j6 >> 32);
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
        int r132 = r13 + 5;
        long j6 = (j5 >> 32) + ((r12[r132] & 4294967295L) - (r10[r11 + 5] & 4294967295L));
        r12[r132] = (int) j6;
        return (int) (j6 >> 32);
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
        long j6 = (j5 >> 32) + ((r11[5] & 4294967295L) - (4294967295L & r10[5]));
        r11[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static BigInteger toBigInteger(int[] r4) {
        byte[] bArr = new byte[24];
        for (int r1 = 0; r1 < 6; r1++) {
            int r2 = r4[r1];
            if (r2 != 0) {
                Pack.intToBigEndian(r2, bArr, (5 - r1) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[24];
        for (int r1 = 0; r1 < 3; r1++) {
            long j = jArr[r1];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (2 - r1) << 3);
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
    }
}
