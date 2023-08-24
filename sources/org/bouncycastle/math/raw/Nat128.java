package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat128 {

    /* renamed from: M */
    private static final long f2382M = 4294967295L;

    public static int add(int[] r10, int[] r11, int[] r12) {
        long j = (r10[0] & 4294967295L) + (r11[0] & 4294967295L) + 0;
        r12[0] = (int) j;
        long j2 = (j >>> 32) + (r10[1] & 4294967295L) + (r11[1] & 4294967295L);
        r12[1] = (int) j2;
        long j3 = (j2 >>> 32) + (r10[2] & 4294967295L) + (r11[2] & 4294967295L);
        r12[2] = (int) j3;
        long j4 = (j3 >>> 32) + (r10[3] & 4294967295L) + (r11[3] & 4294967295L);
        r12[3] = (int) j4;
        return (int) (j4 >>> 32);
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
        return (int) (j4 >>> 32);
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
        int r122 = r12 + 3;
        long j4 = (j3 >>> 32) + (r9[r10 + 3] & 4294967295L) + (4294967295L & r11[r122]);
        r11[r122] = (int) j4;
        return (int) (j4 >>> 32);
    }

    public static int addTo(int[] r10, int[] r11) {
        long j = (r10[0] & 4294967295L) + (r11[0] & 4294967295L) + 0;
        r11[0] = (int) j;
        long j2 = (j >>> 32) + (r10[1] & 4294967295L) + (r11[1] & 4294967295L);
        r11[1] = (int) j2;
        long j3 = (j2 >>> 32) + (r10[2] & 4294967295L) + (r11[2] & 4294967295L);
        r11[2] = (int) j3;
        long j4 = (j3 >>> 32) + (r10[3] & 4294967295L) + (4294967295L & r11[3]);
        r11[3] = (int) j4;
        return (int) (j4 >>> 32);
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
        int r122 = r12 + 3;
        int r142 = r14 + 3;
        long j4 = (j3 >>> 32) + (r11[r122] & 4294967295L) + (4294967295L & r13[r142]);
        int r3 = (int) j4;
        r11[r122] = r3;
        r13[r142] = r3;
        return (int) (j4 >>> 32);
    }

    public static void copy(int[] r2, int r3, int[] r4, int r5) {
        r4[r5 + 0] = r2[r3 + 0];
        r4[r5 + 1] = r2[r3 + 1];
        r4[r5 + 2] = r2[r3 + 2];
        r4[r5 + 3] = r2[r3 + 3];
    }

    public static void copy(int[] r2, int[] r3) {
        r3[0] = r2[0];
        r3[1] = r2[1];
        r3[2] = r2[2];
        r3[3] = r2[3];
    }

    public static void copy64(long[] jArr, int r4, long[] jArr2, int r6) {
        jArr2[r6 + 0] = jArr[r4 + 0];
        jArr2[r6 + 1] = jArr[r4 + 1];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
    }

    public static int[] create() {
        return new int[4];
    }

    public static long[] create64() {
        return new long[2];
    }

    public static int[] createExt() {
        return new int[8];
    }

    public static long[] createExt64() {
        return new long[4];
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
    public static boolean m22eq(int[] r3, int[] r4) {
        for (int r0 = 3; r0 >= 0; r0--) {
            if (r3[r0] != r4[r0]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int r1 = 1; r1 >= 0; r1--) {
            if (jArr[r1] != jArr2[r1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 128) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int r1 = 0; r1 < 4; r1++) {
            create[r1] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 128) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int r1 = 0; r1 < 2; r1++) {
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
            if (r1 < 0 || r1 >= 4) {
                return 0;
            }
            r32 = r3[r1] >>> (r4 & 31);
        }
        return r32 & 1;
    }

    public static boolean gte(int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 3; r0 >= 0; r0--) {
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
        for (int r0 = 3; r0 >= 0; r0--) {
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
        for (int r1 = 1; r1 < 4; r1++) {
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
        for (int r2 = 1; r2 < 2; r2++) {
            if (jArr[r2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] r3) {
        for (int r1 = 0; r1 < 4; r1++) {
            if (r3[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int r1 = 0; r1 < 2; r1++) {
            if (jArr[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] r23, int r24, int[] r25, int r26, int[] r27, int r28) {
        long j = r25[r26 + 0] & 4294967295L;
        long j2 = r25[r26 + 1] & 4294967295L;
        long j3 = r25[r26 + 2] & 4294967295L;
        long j4 = r25[r26 + 3] & 4294967295L;
        long j5 = r23[r24 + 0] & 4294967295L;
        long j6 = (j5 * j) + 0;
        r27[r28 + 0] = (int) j6;
        long j7 = (j6 >>> 32) + (j5 * j2);
        r27[r28 + 1] = (int) j7;
        long j8 = (j7 >>> 32) + (j5 * j3);
        r27[r28 + 2] = (int) j8;
        long j9 = (j8 >>> 32) + (j5 * j4);
        r27[r28 + 3] = (int) j9;
        r27[r28 + 4] = (int) (j9 >>> 32);
        int r2 = 1;
        int r3 = r28;
        int r10 = 1;
        while (r10 < 4) {
            r3 += r2;
            long j10 = r23[r24 + r10] & 4294967295L;
            int r13 = r3 + 0;
            long j11 = j;
            long j12 = (j10 * j) + (r27[r13] & 4294967295L) + 0;
            r27[r13] = (int) j12;
            int r17 = r3 + 1;
            long j13 = (j12 >>> 32) + (j10 * j2) + (r27[r17] & 4294967295L);
            r27[r17] = (int) j13;
            int r15 = r3 + 2;
            long j14 = j2;
            long j15 = (j13 >>> 32) + (j10 * j3) + (r27[r15] & 4294967295L);
            r27[r15] = (int) j15;
            int r4 = r3 + 3;
            long j16 = (j15 >>> 32) + (j10 * j4) + (r27[r4] & 4294967295L);
            r27[r4] = (int) j16;
            r27[r3 + 4] = (int) (j16 >>> 32);
            r10++;
            j2 = j14;
            j = j11;
            r2 = 1;
        }
    }

    public static void mul(int[] r25, int[] r26, int[] r27) {
        long j = r26[0] & 4294967295L;
        int r5 = 1;
        long j2 = r26[1] & 4294967295L;
        long j3 = r26[2] & 4294967295L;
        long j4 = r26[3] & 4294967295L;
        long j5 = r25[0] & 4294967295L;
        long j6 = (j5 * j) + 0;
        r27[0] = (int) j6;
        long j7 = (j6 >>> 32) + (j5 * j2);
        r27[1] = (int) j7;
        long j8 = (j7 >>> 32) + (j5 * j3);
        r27[2] = (int) j8;
        long j9 = (j8 >>> 32) + (j5 * j4);
        r27[3] = (int) j9;
        int r4 = (int) (j9 >>> 32);
        r27[4] = r4;
        for (int r3 = 4; r5 < r3; r3 = 4) {
            long j10 = r25[r5] & 4294967295L;
            int r42 = r5 + 0;
            long j11 = (j10 * j) + (r27[r42] & 4294967295L) + 0;
            r27[r42] = (int) j11;
            int r43 = r5 + 1;
            long j12 = (j11 >>> 32) + (j10 * j2) + (r27[r43] & 4294967295L);
            r27[r43] = (int) j12;
            int r32 = r5 + 2;
            long j13 = (j12 >>> 32) + (j10 * j3) + (r27[r32] & 4294967295L);
            r27[r32] = (int) j13;
            int r33 = r5 + 3;
            long j14 = (j13 >>> 32) + (j10 * j4) + (r27[r33] & 4294967295L);
            r27[r33] = (int) j14;
            r27[r5 + 4] = (int) (j14 >>> 32);
            r5 = r43;
            j = j;
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
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (4294967295L & r16[r17 + 3]);
        r18[r19 + 3] = (int) j10;
        return (j10 >>> 32) + j9;
    }

    public static int mul33DWordAdd(int r10, long j, int[] r13, int r14) {
        long j2 = r10 & 4294967295L;
        long j3 = j & 4294967295L;
        int r102 = r14 + 0;
        long j4 = (j2 * j3) + (r13[r102] & 4294967295L) + 0;
        r13[r102] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int r4 = r14 + 1;
        long j7 = (j4 >>> 32) + j6 + (r13[r4] & 4294967295L);
        r13[r4] = (int) j7;
        int r42 = r14 + 2;
        long j8 = (j7 >>> 32) + j5 + (r13[r42] & 4294967295L);
        r13[r42] = (int) j8;
        int r142 = r14 + 3;
        long j9 = (j8 >>> 32) + (r13[r142] & 4294967295L);
        r13[r142] = (int) j9;
        return (int) (j9 >>> 32);
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
        return Nat.incAt(4, r12, r13, 3);
    }

    public static int mulAddTo(int[] r27, int r28, int[] r29, int r30, int[] r31, int r32) {
        int r18;
        int r16;
        int r4;
        int r12;
        long j = 4294967295L;
        long j2 = r29[r30 + 0] & 4294967295L;
        long j3 = r29[r30 + 1] & 4294967295L;
        long j4 = r29[r30 + 2] & 4294967295L;
        long j5 = r29[r30 + 3] & 4294967295L;
        int r122 = r32;
        int r13 = 0;
        long j6 = 0;
        while (r13 < 4) {
            long j7 = r27[r28 + r13] & j;
            long j8 = (j7 * j2) + (r31[r18] & j) + 0;
            r31[r122 + 0] = (int) j8;
            int r3 = r122 + 1;
            long j9 = j3;
            long j10 = (j8 >>> 32) + (j7 * j3) + (r31[r3] & 4294967295L);
            r31[r3] = (int) j10;
            long j11 = (j10 >>> 32) + (j7 * j4) + (r31[r16] & 4294967295L);
            r31[r122 + 2] = (int) j11;
            long j12 = (j11 >>> 32) + (j7 * j5) + (r31[r4] & 4294967295L);
            r31[r122 + 3] = (int) j12;
            long j13 = j6 + (j12 >>> 32) + (r31[r12] & 4294967295L);
            r31[r122 + 4] = (int) j13;
            j6 = j13 >>> 32;
            r13++;
            r122 = r3;
            j2 = j2;
            j = 4294967295L;
            j3 = j9;
            j4 = j4;
        }
        return (int) j6;
    }

    public static int mulAddTo(int[] r26, int[] r27, int[] r28) {
        int r15;
        int r17;
        int r4;
        int r0;
        int r02 = 0;
        long j = 4294967295L;
        long j2 = r27[0] & 4294967295L;
        long j3 = r27[1] & 4294967295L;
        long j4 = r27[2] & 4294967295L;
        long j5 = r27[3] & 4294967295L;
        long j6 = 0;
        while (r02 < 4) {
            long j7 = r26[r02] & j;
            long j8 = (j7 * j2) + (r28[r15] & j) + 0;
            r28[r02 + 0] = (int) j8;
            int r42 = r02 + 1;
            long j9 = (j8 >>> 32) + (j7 * j3) + (r28[r42] & 4294967295L);
            r28[r42] = (int) j9;
            long j10 = (j9 >>> 32) + (j7 * j4) + (r28[r17] & 4294967295L);
            r28[r02 + 2] = (int) j10;
            long j11 = (j10 >>> 32) + (j7 * j5) + (r28[r4] & 4294967295L);
            r28[r02 + 3] = (int) j11;
            long j12 = j6 + (j11 >>> 32) + (r28[r0] & 4294967295L);
            r28[r02 + 4] = (int) j12;
            j6 = j12 >>> 32;
            r02 = r42;
            j = 4294967295L;
            j2 = j2;
            j3 = j3;
        }
        return (int) j6;
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
        } while (r82 < 4);
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
        int r152 = r15 + 3;
        long j5 = (j4 >>> 32) + (j * (r12[r13 + 3] & 4294967295L)) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j5;
        return (int) (j5 >>> 32);
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
        return Nat.incAt(4, r13, r14, 3);
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
        return Nat.incAt(4, r10, r11, 2);
    }

    public static void square(int[] r27, int r28, int[] r29, int r30) {
        int r9;
        int r15;
        int r7;
        int r6;
        int r22;
        long j = r27[r28 + 0] & 4294967295L;
        int r5 = 0;
        int r62 = 8;
        int r72 = 3;
        while (true) {
            int r8 = r72 - 1;
            long j2 = r27[r28 + r72] & 4294967295L;
            long j3 = j2 * j2;
            int r63 = r62 - 1;
            r29[r30 + r63] = (r5 << 31) | ((int) (j3 >>> 33));
            r62 = r63 - 1;
            r29[r30 + r62] = (int) (j3 >>> 1);
            r5 = (int) j3;
            if (r8 <= 0) {
                long j4 = j * j;
                r29[r30 + 0] = (int) j4;
                long j5 = r27[r28 + 1] & 4294967295L;
                long j6 = (((r5 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r152 = (int) j6;
                r29[r30 + 1] = (r152 << 1) | (((int) (j4 >>> 32)) & 1);
                int r82 = r152 >>> 31;
                long j7 = (r29[r9] & 4294967295L) + (j6 >>> 32);
                long j8 = r27[r28 + 2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r12 = (int) j9;
                r29[r30 + 2] = (r12 << 1) | r82;
                int r83 = r12 >>> 31;
                long j10 = (r29[r15] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r29[r7] & 4294967295L) + (j10 >>> 32);
                long j12 = r27[r28 + 3] & 4294967295L;
                long j13 = (r29[r6] & 4294967295L) + (j11 >>> 32);
                long j14 = 4294967295L & j13;
                long j15 = (j10 & 4294967295L) + (j * j12);
                int r0 = (int) j15;
                r29[r30 + 3] = (r0 << 1) | r83;
                long j16 = (j11 & 4294967295L) + (j15 >>> 32) + (j12 * j5);
                long j17 = j14 + (j16 >>> 32) + (j12 * j8);
                long j18 = (r29[r22] & 4294967295L) + (j13 >>> 32) + (j17 >>> 32);
                int r1 = (int) j16;
                r29[r30 + 4] = (r0 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                int r13 = (int) j17;
                r29[r30 + 5] = r02 | (r13 << 1);
                int r03 = r13 >>> 31;
                int r14 = (int) j18;
                r29[r30 + 6] = r03 | (r14 << 1);
                int r04 = r14 >>> 31;
                int r16 = r30 + 7;
                r29[r16] = r04 | ((r29[r16] + ((int) (j18 >>> 32))) << 1);
                return;
            }
            r72 = r8;
        }
    }

    public static void square(int[] r26, int[] r27) {
        long j = r26[0] & 4294967295L;
        int r6 = 8;
        int r7 = 3;
        int r8 = 0;
        while (true) {
            int r9 = r7 - 1;
            long j2 = r26[r7] & 4294967295L;
            long j3 = j2 * j2;
            int r62 = r6 - 1;
            r27[r62] = (r8 << 31) | ((int) (j3 >>> 33));
            r6 = r62 - 1;
            r27[r6] = (int) (j3 >>> 1);
            int r11 = (int) j3;
            if (r9 <= 0) {
                long j4 = j * j;
                r27[0] = (int) j4;
                long j5 = r26[1] & 4294967295L;
                long j6 = (((r11 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r15 = (int) j6;
                r27[1] = (r15 << 1) | (((int) (j4 >>> 32)) & 1);
                int r63 = r15 >>> 31;
                long j7 = (r27[2] & 4294967295L) + (j6 >>> 32);
                long j8 = r26[2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r152 = (int) j9;
                r27[2] = (r152 << 1) | r63;
                long j10 = (r27[3] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r27[4] & 4294967295L) + (j10 >>> 32);
                long j12 = r26[3] & 4294967295L;
                long j13 = (r27[5] & 4294967295L) + (j11 >>> 32);
                long j14 = (j10 & 4294967295L) + (j * j12);
                int r1 = (int) j14;
                r27[3] = (r1 << 1) | (r152 >>> 31);
                long j15 = (j11 & 4294967295L) + (j14 >>> 32) + (j5 * j12);
                long j16 = (j13 & 4294967295L) + (j15 >>> 32) + (j12 * j8);
                long j17 = (r27[6] & 4294967295L) + (j13 >>> 32) + (j16 >>> 32);
                int r4 = (int) j15;
                r27[4] = (r1 >>> 31) | (r4 << 1);
                int r3 = (int) (j16 & 4294967295L);
                r27[5] = (r4 >>> 31) | (r3 << 1);
                int r2 = (int) j17;
                r27[6] = (r3 >>> 31) | (r2 << 1);
                r27[7] = ((r27[7] + ((int) (j17 >>> 32))) << 1) | (r2 >>> 31);
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
        return (int) (j4 >> 32);
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
        return (int) (j4 >> 32);
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
        return (int) (j4 >> 32);
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
        int r132 = r13 + 3;
        long j4 = (j3 >> 32) + ((r12[r132] & 4294967295L) - (r10[r11 + 3] & 4294967295L));
        r12[r132] = (int) j4;
        return (int) (j4 >> 32);
    }

    public static int subFrom(int[] r10, int[] r11) {
        long j = ((r11[0] & 4294967295L) - (r10[0] & 4294967295L)) + 0;
        r11[0] = (int) j;
        long j2 = (j >> 32) + ((r11[1] & 4294967295L) - (r10[1] & 4294967295L));
        r11[1] = (int) j2;
        long j3 = (j2 >> 32) + ((r11[2] & 4294967295L) - (r10[2] & 4294967295L));
        r11[2] = (int) j3;
        long j4 = (j3 >> 32) + ((r11[3] & 4294967295L) - (4294967295L & r10[3]));
        r11[3] = (int) j4;
        return (int) (j4 >> 32);
    }

    public static BigInteger toBigInteger(int[] r4) {
        byte[] bArr = new byte[16];
        for (int r1 = 0; r1 < 4; r1++) {
            int r2 = r4[r1];
            if (r2 != 0) {
                Pack.intToBigEndian(r2, bArr, (3 - r1) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[16];
        for (int r1 = 0; r1 < 2; r1++) {
            long j = jArr[r1];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (1 - r1) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] r2) {
        r2[0] = 0;
        r2[1] = 0;
        r2[2] = 0;
        r2[3] = 0;
    }
}
