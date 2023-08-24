package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat256 {

    /* renamed from: M */
    private static final long f2386M = 4294967295L;

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
        long j8 = (j7 >>> 32) + (r9[r10 + 7] & 4294967295L) + (r11[r12 + 7] & 4294967295L);
        r13[r14 + 7] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j8 = (j7 >>> 32) + (r10[7] & 4294967295L) + (r11[7] & 4294967295L);
        r12[7] = (int) j8;
        return (int) (j8 >>> 32);
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
        int r76 = r15 + 6;
        long j7 = (j6 >>> 32) + (r10[r11 + 6] & 4294967295L) + (r12[r13 + 6] & 4294967295L) + (r14[r76] & 4294967295L);
        r14[r76] = (int) j7;
        int r152 = r15 + 7;
        long j8 = (j7 >>> 32) + (r10[r11 + 7] & 4294967295L) + (r12[r13 + 7] & 4294967295L) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j8 = (j7 >>> 32) + (r10[7] & 4294967295L) + (r11[7] & 4294967295L) + (r12[7] & 4294967295L);
        r12[7] = (int) j8;
        return (int) (j8 >>> 32);
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
        int r66 = r12 + 6;
        long j7 = (j6 >>> 32) + (r9[r10 + 6] & 4294967295L) + (r11[r66] & 4294967295L);
        r11[r66] = (int) j7;
        int r122 = r12 + 7;
        long j8 = (j7 >>> 32) + (r9[r10 + 7] & 4294967295L) + (4294967295L & r11[r122]);
        r11[r122] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j7 = (j6 >>> 32) + (r10[6] & 4294967295L) + (r11[6] & 4294967295L);
        r11[6] = (int) j7;
        long j8 = (j7 >>> 32) + (r10[7] & 4294967295L) + (4294967295L & r11[7]);
        r11[7] = (int) j8;
        return (int) (j8 >>> 32);
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
        int r57 = r12 + 6;
        int r86 = r14 + 6;
        long j7 = (j6 >>> 32) + (r11[r57] & 4294967295L) + (r13[r86] & 4294967295L);
        int r67 = (int) j7;
        r11[r57] = r67;
        r13[r86] = r67;
        int r122 = r12 + 7;
        int r142 = r14 + 7;
        long j8 = (j7 >>> 32) + (r11[r122] & 4294967295L) + (4294967295L & r13[r142]);
        int r3 = (int) j8;
        r11[r122] = r3;
        r13[r142] = r3;
        return (int) (j8 >>> 32);
    }

    public static void copy(int[] r2, int r3, int[] r4, int r5) {
        r4[r5 + 0] = r2[r3 + 0];
        r4[r5 + 1] = r2[r3 + 1];
        r4[r5 + 2] = r2[r3 + 2];
        r4[r5 + 3] = r2[r3 + 3];
        r4[r5 + 4] = r2[r3 + 4];
        r4[r5 + 5] = r2[r3 + 5];
        r4[r5 + 6] = r2[r3 + 6];
        r4[r5 + 7] = r2[r3 + 7];
    }

    public static void copy(int[] r2, int[] r3) {
        r3[0] = r2[0];
        r3[1] = r2[1];
        r3[2] = r2[2];
        r3[3] = r2[3];
        r3[4] = r2[4];
        r3[5] = r2[5];
        r3[6] = r2[6];
        r3[7] = r2[7];
    }

    public static void copy64(long[] jArr, int r4, long[] jArr2, int r6) {
        jArr2[r6 + 0] = jArr[r4 + 0];
        jArr2[r6 + 1] = jArr[r4 + 1];
        jArr2[r6 + 2] = jArr[r4 + 2];
        jArr2[r6 + 3] = jArr[r4 + 3];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static int[] create() {
        return new int[8];
    }

    public static long[] create64() {
        return new long[4];
    }

    public static int[] createExt() {
        return new int[16];
    }

    public static long[] createExt64() {
        return new long[8];
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
    public static boolean m18eq(int[] r3, int[] r4) {
        for (int r0 = 7; r0 >= 0; r0--) {
            if (r3[r0] != r4[r0]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int r0 = 3; r0 >= 0; r0--) {
            if (jArr[r0] != jArr2[r0]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int r1 = 0; r1 < 8; r1++) {
            create[r1] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int r1 = 0; r1 < 4; r1++) {
            create64[r1] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static int getBit(int[] r2, int r3) {
        int r22;
        if (r3 == 0) {
            r22 = r2[0];
        } else if ((r3 & 255) != r3) {
            return 0;
        } else {
            r22 = r2[r3 >>> 5] >>> (r3 & 31);
        }
        return r22 & 1;
    }

    public static boolean gte(int[] r5, int r6, int[] r7, int r8) {
        for (int r0 = 7; r0 >= 0; r0--) {
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
        for (int r0 = 7; r0 >= 0; r0--) {
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
        for (int r1 = 1; r1 < 8; r1++) {
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
        for (int r2 = 1; r2 < 4; r2++) {
            if (jArr[r2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] r3) {
        for (int r1 = 0; r1 < 8; r1++) {
            if (r3[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int r1 = 0; r1 < 4; r1++) {
            if (jArr[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] r34, int r35, int[] r36, int r37, int[] r38, int r39) {
        long j = r36[r37 + 0] & 4294967295L;
        long j2 = r36[r37 + 1] & 4294967295L;
        long j3 = r36[r37 + 2] & 4294967295L;
        long j4 = r36[r37 + 3] & 4294967295L;
        long j5 = r36[r37 + 4] & 4294967295L;
        long j6 = r36[r37 + 5] & 4294967295L;
        long j7 = r36[r37 + 6] & 4294967295L;
        long j8 = r36[r37 + 7] & 4294967295L;
        long j9 = r34[r35 + 0] & 4294967295L;
        long j10 = (j9 * j) + 0;
        r38[r39 + 0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        r38[r39 + 1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        r38[r39 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        r38[r39 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        r38[r39 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        r38[r39 + 5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        r38[r39 + 6] = (int) j16;
        long j17 = j8;
        long j18 = (j16 >>> 32) + (j9 * j17);
        r38[r39 + 7] = (int) j18;
        r38[r39 + 8] = (int) (j18 >>> 32);
        int r1 = 1;
        int r2 = r39;
        int r5 = 1;
        while (r5 < 8) {
            r2 += r1;
            long j19 = r34[r35 + r5] & 4294967295L;
            int r16 = r2 + 0;
            long j20 = (j19 * j) + (r38[r16] & 4294967295L) + 0;
            r38[r16] = (int) j20;
            int r162 = r2 + 1;
            long j21 = j17;
            long j22 = (j20 >>> 32) + (j19 * j2) + (r38[r162] & 4294967295L);
            r38[r162] = (int) j22;
            int r163 = r2 + 2;
            long j23 = j3;
            long j24 = (j22 >>> 32) + (j19 * j3) + (r38[r163] & 4294967295L);
            r38[r163] = (int) j24;
            int r6 = r2 + 3;
            long j25 = (j24 >>> 32) + (j19 * j4) + (r38[r6] & 4294967295L);
            r38[r6] = (int) j25;
            int r62 = r2 + 4;
            long j26 = (j25 >>> 32) + (j19 * j5) + (r38[r62] & 4294967295L);
            r38[r62] = (int) j26;
            int r63 = r2 + 5;
            long j27 = (j26 >>> 32) + (j19 * j6) + (r38[r63] & 4294967295L);
            r38[r63] = (int) j27;
            int r64 = r2 + 6;
            long j28 = (j27 >>> 32) + (j19 * j7) + (r38[r64] & 4294967295L);
            r38[r64] = (int) j28;
            int r4 = r2 + 7;
            long j29 = (j28 >>> 32) + (j19 * j21) + (r38[r4] & 4294967295L);
            r38[r4] = (int) j29;
            r38[r2 + 8] = (int) (j29 >>> 32);
            r5++;
            j3 = j23;
            j17 = j21;
            j4 = j4;
            r1 = 1;
        }
    }

    public static void mul(int[] r35, int[] r36, int[] r37) {
        long j = r36[0] & 4294967295L;
        long j2 = r36[1] & 4294967295L;
        long j3 = r36[2] & 4294967295L;
        long j4 = r36[3] & 4294967295L;
        long j5 = r36[4] & 4294967295L;
        long j6 = r36[5] & 4294967295L;
        long j7 = r36[6] & 4294967295L;
        long j8 = r36[7] & 4294967295L;
        long j9 = r35[0] & 4294967295L;
        long j10 = (j9 * j) + 0;
        r37[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        r37[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        r37[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        r37[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        r37[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        r37[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        r37[6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        r37[7] = (int) j17;
        int r4 = (int) (j17 >>> 32);
        r37[8] = r4;
        int r5 = 1;
        for (int r3 = 8; r5 < r3; r3 = 8) {
            long j18 = r35[r5] & 4294967295L;
            int r42 = r5 + 0;
            long j19 = (j18 * j) + (r37[r42] & 4294967295L) + 0;
            r37[r42] = (int) j19;
            int r18 = r5 + 1;
            long j20 = j2;
            long j21 = (j19 >>> 32) + (j18 * j2) + (r37[r18] & 4294967295L);
            r37[r18] = (int) j21;
            int r6 = r5 + 2;
            long j22 = j6;
            long j23 = (j21 >>> 32) + (j18 * j3) + (r37[r6] & 4294967295L);
            r37[r6] = (int) j23;
            int r62 = r5 + 3;
            long j24 = (j23 >>> 32) + (j18 * j4) + (r37[r62] & 4294967295L);
            r37[r62] = (int) j24;
            int r63 = r5 + 4;
            long j25 = (j24 >>> 32) + (j18 * j5) + (r37[r63] & 4294967295L);
            r37[r63] = (int) j25;
            int r32 = r5 + 5;
            long j26 = (j25 >>> 32) + (j18 * j22) + (r37[r32] & 4294967295L);
            r37[r32] = (int) j26;
            int r64 = r5 + 6;
            long j27 = (j26 >>> 32) + (j18 * j7) + (r37[r64] & 4294967295L);
            r37[r64] = (int) j27;
            int r33 = r5 + 7;
            long j28 = (j27 >>> 32) + (j18 * j8) + (r37[r33] & 4294967295L);
            r37[r33] = (int) j28;
            r37[r5 + 8] = (int) (j28 >>> 32);
            r5 = r18;
            j = j;
            j2 = j20;
            j6 = j22;
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
        long j16 = (j14 >>> 32) + (j * j15) + j13 + (r16[r17 + 6] & 4294967295L);
        r18[r19 + 6] = (int) j16;
        long j17 = r14[r15 + 7] & 4294967295L;
        long j18 = (j16 >>> 32) + (j * j17) + j15 + (4294967295L & r16[r17 + 7]);
        r18[r19 + 7] = (int) j18;
        return (j18 >>> 32) + j17;
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
        return Nat.incAt(8, r18, r19, 4);
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
        return Nat.incAt(8, r12, r13, 3);
    }

    public static int mulAddTo(int[] r35, int r36, int[] r37, int r38, int[] r39, int r40) {
        int r28;
        int r5;
        int r52;
        int r53;
        int r54;
        int r55;
        int r56;
        int r16;
        long j = r37[r38 + 0] & 4294967295L;
        long j2 = r37[r38 + 1] & 4294967295L;
        long j3 = r37[r38 + 2] & 4294967295L;
        long j4 = r37[r38 + 3] & 4294967295L;
        long j5 = r37[r38 + 4] & 4294967295L;
        long j6 = r37[r38 + 5] & 4294967295L;
        long j7 = r37[r38 + 6] & 4294967295L;
        long j8 = r37[r38 + 7] & 4294967295L;
        int r162 = r40;
        long j9 = 0;
        int r2 = 0;
        while (r2 < 8) {
            int r25 = r2;
            long j10 = r35[r36 + r2] & 4294967295L;
            long j11 = j;
            long j12 = (j10 * j) + (r39[r28] & 4294967295L) + 0;
            long j13 = j8;
            r39[r162 + 0] = (int) j12;
            int r15 = r162 + 1;
            long j14 = (j12 >>> 32) + (j10 * j2) + (r39[r15] & 4294967295L);
            r39[r15] = (int) j14;
            long j15 = (j14 >>> 32) + (j10 * j3) + (r39[r5] & 4294967295L);
            r39[r162 + 2] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j4) + (r39[r52] & 4294967295L);
            r39[r162 + 3] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j5) + (r39[r53] & 4294967295L);
            r39[r162 + 4] = (int) j17;
            long j18 = (j17 >>> 32) + (j10 * j6) + (r39[r54] & 4294967295L);
            r39[r162 + 5] = (int) j18;
            long j19 = (j18 >>> 32) + (j10 * j7) + (r39[r55] & 4294967295L);
            r39[r162 + 6] = (int) j19;
            long j20 = (j19 >>> 32) + (j10 * j13) + (r39[r56] & 4294967295L);
            r39[r162 + 7] = (int) j20;
            long j21 = (j20 >>> 32) + (r39[r16] & 4294967295L) + j9;
            r39[r162 + 8] = (int) j21;
            j9 = j21 >>> 32;
            r2 = r25 + 1;
            r162 = r15;
            j8 = j13;
            j = j11;
            j2 = j2;
        }
        return (int) j9;
    }

    public static int mulAddTo(int[] r34, int[] r35, int[] r36) {
        int r27;
        int r6;
        int r62;
        int r63;
        int r64;
        int r65;
        int r66;
        int r2;
        long j = r35[0] & 4294967295L;
        long j2 = r35[1] & 4294967295L;
        long j3 = r35[2] & 4294967295L;
        long j4 = r35[3] & 4294967295L;
        long j5 = r35[4] & 4294967295L;
        long j6 = r35[5] & 4294967295L;
        long j7 = r35[6] & 4294967295L;
        long j8 = r35[7] & 4294967295L;
        long j9 = 0;
        int r22 = 0;
        while (r22 < 8) {
            long j10 = j8;
            long j11 = r34[r22] & 4294967295L;
            long j12 = j6;
            long j13 = (j11 * j) + (r36[r27] & 4294967295L) + 0;
            r36[r22 + 0] = (int) j13;
            int r16 = r22 + 1;
            long j14 = j2;
            long j15 = (j13 >>> 32) + (j11 * j2) + (r36[r16] & 4294967295L);
            r36[r16] = (int) j15;
            long j16 = (j15 >>> 32) + (j11 * j3) + (r36[r6] & 4294967295L);
            r36[r22 + 2] = (int) j16;
            long j17 = (j16 >>> 32) + (j11 * j4) + (r36[r62] & 4294967295L);
            r36[r22 + 3] = (int) j17;
            long j18 = (j17 >>> 32) + (j11 * j5) + (r36[r63] & 4294967295L);
            r36[r22 + 4] = (int) j18;
            long j19 = (j18 >>> 32) + (j11 * j12) + (r36[r64] & 4294967295L);
            r36[r22 + 5] = (int) j19;
            long j20 = (j19 >>> 32) + (j11 * j7) + (r36[r65] & 4294967295L);
            r36[r22 + 6] = (int) j20;
            long j21 = (j20 >>> 32) + (j11 * j10) + (r36[r66] & 4294967295L);
            r36[r22 + 7] = (int) j21;
            long j22 = (j21 >>> 32) + (r36[r2] & 4294967295L) + j9;
            r36[r22 + 8] = (int) j22;
            j9 = j22 >>> 32;
            r22 = r16;
            j8 = j10;
            j6 = j12;
            j2 = j14;
        }
        return (int) j9;
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
        long j8 = (j7 >>> 32) + ((r10[6] & 4294967295L) * j);
        r10[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (4294967295L & r10[7]));
        r10[7] = (int) j9;
        return (int) (j9 >>> 32);
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
        long j8 = (j7 >>> 32) + ((r13[6] & 4294967295L) * j) + (r12[6] & 4294967295L);
        r13[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (r13[7] & 4294967295L)) + (4294967295L & r12[7]);
        r13[7] = (int) j9;
        return (int) (j9 >>> 32);
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
        } while (r82 < 8);
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
        int r86 = r15 + 6;
        long j8 = (j7 >>> 32) + ((r12[r13 + 6] & 4294967295L) * j) + (r14[r86] & 4294967295L);
        r14[r86] = (int) j8;
        int r152 = r15 + 7;
        long j9 = (j8 >>> 32) + (j * (r12[r13 + 7] & 4294967295L)) + (r14[r152] & 4294967295L);
        r14[r152] = (int) j9;
        return (int) (j9 >>> 32);
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
        return Nat.incAt(8, r13, r14, 3);
    }

    public static void square(int[] r47, int r48, int[] r49, int r50) {
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
        int r26;
        int r152;
        int r42;
        long j = r47[r48 + 0] & 4294967295L;
        int r52 = 0;
        int r62 = 16;
        int r72 = 7;
        while (true) {
            int r8 = r72 - 1;
            long j2 = r47[r48 + r72] & 4294967295L;
            long j3 = j2 * j2;
            int r63 = r62 - 1;
            r49[r50 + r63] = (r52 << 31) | ((int) (j3 >>> 33));
            r62 = r63 - 1;
            r49[r50 + r62] = (int) (j3 >>> 1);
            r52 = (int) j3;
            if (r8 <= 0) {
                long j4 = j * j;
                r49[r50 + 0] = (int) j4;
                long j5 = r47[r48 + 1] & 4294967295L;
                long j6 = (((r52 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r153 = (int) j6;
                r49[r50 + 1] = (r153 << 1) | (((int) (j4 >>> 32)) & 1);
                int r82 = r153 >>> 31;
                long j7 = (r49[r9] & 4294967295L) + (j6 >>> 32);
                long j8 = r47[r48 + 2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r12 = (int) j9;
                r49[r50 + 2] = (r12 << 1) | r82;
                int r83 = r12 >>> 31;
                long j10 = (r49[r15] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r49[r7] & 4294967295L) + (j10 >>> 32);
                long j12 = r47[r48 + 3] & 4294967295L;
                long j13 = (r49[r6] & 4294967295L) + (j11 >>> 32);
                long j14 = (r49[r24] & 4294967295L) + (j13 >>> 32);
                long j15 = (j10 & 4294967295L) + (j12 * j);
                int r2 = (int) j15;
                r49[r50 + 3] = (r2 << 1) | r83;
                long j16 = (j11 & 4294967295L) + (j15 >>> 32) + (j12 * j5);
                long j17 = (j13 & 4294967295L) + (j16 >>> 32) + (j12 * j8);
                long j18 = j14 + (j17 >>> 32);
                long j19 = j17 & 4294967295L;
                long j20 = r47[r48 + 4] & 4294967295L;
                long j21 = (r49[r3] & 4294967295L) + (j18 >>> 32);
                long j22 = j18 & 4294967295L;
                long j23 = (j16 & 4294967295L) + (j20 * j);
                int r53 = (int) j23;
                r49[r50 + 4] = (r2 >>> 31) | (r53 << 1);
                int r23 = r53 >>> 31;
                long j24 = j19 + (j23 >>> 32) + (j20 * j5);
                long j25 = j22 + (j24 >>> 32) + (j20 * j8);
                long j26 = (j21 & 4294967295L) + (j25 >>> 32) + (j20 * j12);
                long j27 = (r49[r22] & 4294967295L) + (j21 >>> 32) + (j26 >>> 32);
                long j28 = j26 & 4294967295L;
                long j29 = r47[r48 + 5] & 4294967295L;
                long j30 = (r49[r5] & 4294967295L) + (j27 >>> 32);
                long j31 = (j24 & 4294967295L) + (j29 * j);
                int r73 = (int) j31;
                r49[r50 + 5] = r23 | (r73 << 1);
                int r27 = r73 >>> 31;
                long j32 = (j25 & 4294967295L) + (j31 >>> 32) + (j29 * j5);
                long j33 = j28 + (j32 >>> 32) + (j29 * j8);
                long j34 = (j27 & 4294967295L) + (j33 >>> 32) + (j29 * j12);
                long j35 = (j30 & 4294967295L) + (j34 >>> 32) + (j29 * j20);
                long j36 = j34 & 4294967295L;
                long j37 = (r49[r25] & 4294967295L) + (j30 >>> 32) + (j35 >>> 32);
                long j38 = j35 & 4294967295L;
                long j39 = r47[r48 + 6] & 4294967295L;
                long j40 = (r49[r11] & 4294967295L) + (j37 >>> 32);
                long j41 = j37 & 4294967295L;
                long j42 = (r49[r26] & 4294967295L) + (j40 >>> 32);
                long j43 = (j32 & 4294967295L) + (j39 * j);
                int r112 = (int) j43;
                r49[r50 + 6] = r27 | (r112 << 1);
                int r28 = r112 >>> 31;
                long j44 = (j33 & 4294967295L) + (j43 >>> 32) + (j39 * j5);
                long j45 = j36 + (j44 >>> 32) + (j39 * j8);
                long j46 = j38 + (j45 >>> 32) + (j39 * j12);
                long j47 = j45 & 4294967295L;
                long j48 = j41 + (j46 >>> 32) + (j39 * j20);
                long j49 = (j40 & 4294967295L) + (j48 >>> 32) + (j39 * j29);
                long j50 = j42 + (j49 >>> 32);
                long j51 = j49 & 4294967295L;
                long j52 = r47[r48 + 7] & 4294967295L;
                long j53 = (r49[r152] & 4294967295L) + (j50 >>> 32);
                long j54 = (j44 & 4294967295L) + (j * j52);
                int r0 = (int) j54;
                r49[r50 + 7] = (r0 << 1) | r28;
                long j55 = j47 + (j54 >>> 32) + (j52 * j5);
                long j56 = (j46 & 4294967295L) + (j55 >>> 32) + (j52 * j8);
                long j57 = (j48 & 4294967295L) + (j56 >>> 32) + (j52 * j12);
                long j58 = j51 + (j57 >>> 32) + (j52 * j20);
                long j59 = (j50 & 4294967295L) + (j58 >>> 32) + (j52 * j29);
                long j60 = (j53 & 4294967295L) + (j59 >>> 32) + (j52 * j39);
                long j61 = (r49[r42] & 4294967295L) + (j53 >>> 32) + (j60 >>> 32);
                int r29 = (int) j55;
                r49[r50 + 8] = (r0 >>> 31) | (r29 << 1);
                int r1 = (int) j56;
                r49[r50 + 9] = (r29 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                int r13 = (int) j57;
                r49[r50 + 10] = r02 | (r13 << 1);
                int r210 = (int) j58;
                r49[r50 + 11] = (r13 >>> 31) | (r210 << 1);
                int r14 = (int) j59;
                r49[r50 + 12] = (r210 >>> 31) | (r14 << 1);
                int r03 = r14 >>> 31;
                int r16 = (int) j60;
                r49[r50 + 13] = r03 | (r16 << 1);
                int r04 = r16 >>> 31;
                int r17 = (int) j61;
                r49[r50 + 14] = r04 | (r17 << 1);
                int r05 = r17 >>> 31;
                int r18 = r50 + 15;
                r49[r18] = r05 | ((r49[r18] + ((int) (j61 >>> 32))) << 1);
                return;
            }
            r72 = r8;
        }
    }

    public static void square(int[] r46, int[] r47) {
        long j = r46[0] & 4294967295L;
        int r6 = 16;
        int r7 = 7;
        int r8 = 0;
        while (true) {
            int r9 = r7 - 1;
            long j2 = r46[r7] & 4294967295L;
            long j3 = j2 * j2;
            int r62 = r6 - 1;
            r47[r62] = (r8 << 31) | ((int) (j3 >>> 33));
            r6 = r62 - 1;
            r47[r6] = (int) (j3 >>> 1);
            int r11 = (int) j3;
            if (r9 <= 0) {
                long j4 = j * j;
                r47[0] = (int) j4;
                long j5 = r46[1] & 4294967295L;
                long j6 = (((r11 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int r15 = (int) j6;
                r47[1] = (r15 << 1) | (((int) (j4 >>> 32)) & 1);
                int r63 = r15 >>> 31;
                long j7 = (r47[2] & 4294967295L) + (j6 >>> 32);
                long j8 = r46[2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int r152 = (int) j9;
                r47[2] = (r152 << 1) | r63;
                long j10 = (r47[3] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (r47[4] & 4294967295L) + (j10 >>> 32);
                long j12 = r46[3] & 4294967295L;
                long j13 = (r47[5] & 4294967295L) + (j11 >>> 32);
                long j14 = j11 & 4294967295L;
                long j15 = (r47[6] & 4294967295L) + (j13 >>> 32);
                long j16 = (j10 & 4294967295L) + (j12 * j);
                int r92 = (int) j16;
                r47[3] = (r92 << 1) | (r152 >>> 31);
                int r10 = r92 >>> 31;
                long j17 = j14 + (j16 >>> 32) + (j12 * j5);
                long j18 = (j13 & 4294967295L) + (j17 >>> 32) + (j12 * j8);
                long j19 = j15 + (j18 >>> 32);
                long j20 = r46[4] & 4294967295L;
                long j21 = (r47[7] & 4294967295L) + (j19 >>> 32);
                long j22 = j19 & 4294967295L;
                long j23 = (r47[8] & 4294967295L) + (j21 >>> 32);
                long j24 = (j17 & 4294967295L) + (j20 * j);
                int r153 = (int) j24;
                r47[4] = (r153 << 1) | r10;
                long j25 = (j18 & 4294967295L) + (j24 >>> 32) + (j20 * j5);
                long j26 = j22 + (j25 >>> 32) + (j20 * j8);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j20 * j12);
                long j28 = j23 + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = r46[5] & 4294967295L;
                long j31 = (r47[9] & 4294967295L) + (j28 >>> 32);
                long j32 = j28 & 4294967295L;
                long j33 = (r47[10] & 4294967295L) + (j31 >>> 32);
                long j34 = (j25 & 4294967295L) + (j30 * j);
                int r0 = (int) j34;
                r47[5] = (r0 << 1) | (r153 >>> 31);
                long j35 = (j26 & 4294967295L) + (j34 >>> 32) + (j30 * j5);
                long j36 = j29 + (j35 >>> 32) + (j30 * j8);
                long j37 = j32 + (j36 >>> 32) + (j30 * j12);
                long j38 = (j31 & 4294967295L) + (j37 >>> 32) + (j30 * j20);
                long j39 = j33 + (j38 >>> 32);
                long j40 = j38 & 4294967295L;
                long j41 = r46[6] & 4294967295L;
                long j42 = (r47[11] & 4294967295L) + (j39 >>> 32);
                long j43 = j39 & 4294967295L;
                long j44 = (r47[12] & 4294967295L) + (j42 >>> 32);
                long j45 = (j35 & 4294967295L) + (j41 * j);
                int r154 = (int) j45;
                r47[6] = (r154 << 1) | (r0 >>> 31);
                long j46 = (j36 & 4294967295L) + (j45 >>> 32) + (j41 * j5);
                long j47 = (j37 & 4294967295L) + (j46 >>> 32) + (j41 * j8);
                long j48 = j46 & 4294967295L;
                long j49 = j40 + (j47 >>> 32) + (j41 * j12);
                long j50 = j43 + (j49 >>> 32) + (j41 * j20);
                long j51 = (j42 & 4294967295L) + (j50 >>> 32) + (j41 * j30);
                long j52 = j44 + (j51 >>> 32);
                long j53 = j51 & 4294967295L;
                long j54 = r46[7] & 4294967295L;
                long j55 = (r47[13] & 4294967295L) + (j52 >>> 32);
                long j56 = 4294967295L & j55;
                long j57 = j48 + (j * j54);
                int r1 = (int) j57;
                r47[7] = (r154 >>> 31) | (r1 << 1);
                int r02 = r1 >>> 31;
                long j58 = (j47 & 4294967295L) + (j57 >>> 32) + (j54 * j5);
                long j59 = (j49 & 4294967295L) + (j58 >>> 32) + (j54 * j8);
                long j60 = (j50 & 4294967295L) + (j59 >>> 32) + (j54 * j12);
                long j61 = j53 + (j60 >>> 32) + (j54 * j20);
                long j62 = (j52 & 4294967295L) + (j61 >>> 32) + (j54 * j30);
                long j63 = j56 + (j62 >>> 32) + (j54 * j41);
                long j64 = (r47[14] & 4294967295L) + (j55 >>> 32) + (j63 >>> 32);
                int r2 = (int) j58;
                r47[8] = r02 | (r2 << 1);
                int r12 = (int) j59;
                r47[9] = (r2 >>> 31) | (r12 << 1);
                int r03 = r12 >>> 31;
                int r13 = (int) j60;
                r47[10] = r03 | (r13 << 1);
                int r22 = (int) j61;
                r47[11] = (r13 >>> 31) | (r22 << 1);
                int r14 = (int) j62;
                r47[12] = (r22 >>> 31) | (r14 << 1);
                int r04 = r14 >>> 31;
                int r16 = (int) j63;
                r47[13] = r04 | (r16 << 1);
                int r05 = r16 >>> 31;
                int r17 = (int) j64;
                r47[14] = r05 | (r17 << 1);
                r47[15] = (r17 >>> 31) | ((r47[15] + ((int) (j64 >>> 32))) << 1);
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
        long j8 = (j7 >> 32) + ((r9[r10 + 7] & 4294967295L) - (r11[r12 + 7] & 4294967295L));
        r13[r14 + 7] = (int) j8;
        return (int) (j8 >> 32);
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
        long j8 = (j7 >> 32) + ((r10[7] & 4294967295L) - (r11[7] & 4294967295L));
        r12[7] = (int) j8;
        return (int) (j8 >> 32);
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
        long j8 = (j7 >> 32) + (((r12[7] & 4294967295L) - (r10[7] & 4294967295L)) - (r11[7] & 4294967295L));
        r12[7] = (int) j8;
        return (int) (j8 >> 32);
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
        int r56 = r13 + 6;
        long j7 = (j6 >> 32) + ((r12[r56] & 4294967295L) - (r10[r11 + 6] & 4294967295L));
        r12[r56] = (int) j7;
        int r132 = r13 + 7;
        long j8 = (j7 >> 32) + ((r12[r132] & 4294967295L) - (r10[r11 + 7] & 4294967295L));
        r12[r132] = (int) j8;
        return (int) (j8 >> 32);
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
        long j7 = (j6 >> 32) + ((r11[6] & 4294967295L) - (r10[6] & 4294967295L));
        r11[6] = (int) j7;
        long j8 = (j7 >> 32) + ((r11[7] & 4294967295L) - (4294967295L & r10[7]));
        r11[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger toBigInteger(int[] r4) {
        byte[] bArr = new byte[32];
        for (int r1 = 0; r1 < 8; r1++) {
            int r2 = r4[r1];
            if (r2 != 0) {
                Pack.intToBigEndian(r2, bArr, (7 - r1) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[32];
        for (int r1 = 0; r1 < 4; r1++) {
            long j = jArr[r1];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (3 - r1) << 3);
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
        r2[7] = 0;
    }
}
