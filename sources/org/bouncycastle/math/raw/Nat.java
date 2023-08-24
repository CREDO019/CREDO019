package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat {

    /* renamed from: M */
    private static final long f2381M = 4294967295L;

    public static int add(int r9, int[] r10, int[] r11, int[] r12) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + (r10[r2] & 4294967295L) + (4294967295L & r11[r2]);
            r12[r2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int add33At(int r7, int r8, int[] r9, int r10) {
        int r0 = r10 + 0;
        long j = (r9[r0] & 4294967295L) + (r8 & 4294967295L);
        r9[r0] = (int) j;
        int r2 = r10 + 1;
        long j2 = (j >>> 32) + (4294967295L & r9[r2]) + 1;
        r9[r2] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(r7, r9, r10 + 2);
    }

    public static int add33At(int r7, int r8, int[] r9, int r10, int r11) {
        int r0 = r10 + r11;
        long j = (r9[r0] & 4294967295L) + (r8 & 4294967295L);
        r9[r0] = (int) j;
        int r02 = r0 + 1;
        long j2 = (j >>> 32) + (4294967295L & r9[r02]) + 1;
        r9[r02] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(r7, r9, r10, r11 + 2);
    }

    public static int add33To(int r8, int r9, int[] r10) {
        long j = (r10[0] & 4294967295L) + (r9 & 4294967295L);
        r10[0] = (int) j;
        long j2 = (j >>> 32) + (4294967295L & r10[1]) + 1;
        r10[1] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(r8, r10, 2);
    }

    public static int add33To(int r7, int r8, int[] r9, int r10) {
        int r0 = r10 + 0;
        long j = (r9[r0] & 4294967295L) + (r8 & 4294967295L);
        r9[r0] = (int) j;
        int r2 = r10 + 1;
        long j2 = (j >>> 32) + (4294967295L & r9[r2]) + 1;
        r9[r2] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(r7, r9, r10, 2);
    }

    public static int addBothTo(int r11, int[] r12, int r13, int[] r14, int r15, int[] r16, int r17) {
        long j = 0;
        for (int r2 = 0; r2 < r11; r2++) {
            int r8 = r17 + r2;
            long j2 = j + (r12[r13 + r2] & 4294967295L) + (r14[r15 + r2] & 4294967295L) + (4294967295L & r16[r8]);
            r16[r8] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int addBothTo(int r9, int[] r10, int[] r11, int[] r12) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + (r10[r2] & 4294967295L) + (r11[r2] & 4294967295L) + (4294967295L & r12[r2]);
            r12[r2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int addDWordAt(int r8, long j, int[] r11, int r12) {
        int r0 = r12 + 0;
        long j2 = (r11[r0] & 4294967295L) + (j & 4294967295L);
        r11[r0] = (int) j2;
        int r5 = r12 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & r11[r5]) + (j >>> 32);
        r11[r5] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(r8, r11, r12 + 2);
    }

    public static int addDWordAt(int r8, long j, int[] r11, int r12, int r13) {
        int r0 = r12 + r13;
        long j2 = (r11[r0] & 4294967295L) + (j & 4294967295L);
        r11[r0] = (int) j2;
        int r02 = r0 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & r11[r02]) + (j >>> 32);
        r11[r02] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(r8, r11, r12, r13 + 2);
    }

    public static int addDWordTo(int r9, long j, int[] r12) {
        long j2 = (r12[0] & 4294967295L) + (j & 4294967295L);
        r12[0] = (int) j2;
        long j3 = (j2 >>> 32) + (4294967295L & r12[1]) + (j >>> 32);
        r12[1] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(r9, r12, 2);
    }

    public static int addDWordTo(int r8, long j, int[] r11, int r12) {
        int r0 = r12 + 0;
        long j2 = (r11[r0] & 4294967295L) + (j & 4294967295L);
        r11[r0] = (int) j2;
        int r5 = r12 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & r11[r5]) + (j >>> 32);
        r11[r5] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(r8, r11, r12, 2);
    }

    public static int addTo(int r10, int[] r11, int r12, int[] r13, int r14) {
        long j = 0;
        for (int r2 = 0; r2 < r10; r2++) {
            int r7 = r14 + r2;
            long j2 = j + (r11[r12 + r2] & 4294967295L) + (4294967295L & r13[r7]);
            r13[r7] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int addTo(int r9, int[] r10, int r11, int[] r12, int r13, int r14) {
        long j = r14 & 4294967295L;
        for (int r142 = 0; r142 < r9; r142++) {
            int r6 = r13 + r142;
            long j2 = j + (r10[r11 + r142] & 4294967295L) + (r12[r6] & 4294967295L);
            r12[r6] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int addTo(int r9, int[] r10, int[] r11) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + (r10[r2] & 4294967295L) + (4294967295L & r11[r2]);
            r11[r2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int addToEachOther(int r11, int[] r12, int r13, int[] r14, int r15) {
        long j = 0;
        for (int r2 = 0; r2 < r11; r2++) {
            int r3 = r13 + r2;
            int r8 = r15 + r2;
            long j2 = j + (r12[r3] & 4294967295L) + (4294967295L & r14[r8]);
            int r4 = (int) j2;
            r12[r3] = r4;
            r14[r8] = r4;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int addWordAt(int r6, int r7, int[] r8, int r9) {
        long j = (r7 & 4294967295L) + (4294967295L & r8[r9]);
        r8[r9] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return incAt(r6, r8, r9 + 1);
    }

    public static int addWordAt(int r6, int r7, int[] r8, int r9, int r10) {
        int r72 = r9 + r10;
        long j = (r7 & 4294967295L) + (4294967295L & r8[r72]);
        r8[r72] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return incAt(r6, r8, r9, r10 + 1);
    }

    public static int addWordTo(int r6, int r7, int[] r8) {
        long j = (r7 & 4294967295L) + (4294967295L & r8[0]);
        r8[0] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return incAt(r6, r8, 1);
    }

    public static int addWordTo(int r6, int r7, int[] r8, int r9) {
        long j = (r7 & 4294967295L) + (4294967295L & r8[r9]);
        r8[r9] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return incAt(r6, r8, r9, 1);
    }

    public static int cadd(int r10, int r11, int[] r12, int[] r13, int[] r14) {
        long j = (-(r11 & 1)) & 4294967295L;
        long j2 = 0;
        for (int r112 = 0; r112 < r10; r112++) {
            long j3 = j2 + (r12[r112] & 4294967295L) + (r13[r112] & j);
            r14[r112] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static void cmov(int r4, int r5, int[] r6, int r7, int[] r8, int r9) {
        int r52 = -(r5 & 1);
        for (int r0 = 0; r0 < r4; r0++) {
            int r1 = r9 + r0;
            int r2 = r8[r1];
            r8[r1] = r2 ^ ((r6[r7 + r0] ^ r2) & r52);
        }
    }

    public static int compare(int r4, int[] r5, int r6, int[] r7, int r8) {
        for (int r42 = r4 - 1; r42 >= 0; r42--) {
            int r1 = r5[r6 + r42] ^ Integer.MIN_VALUE;
            int r2 = Integer.MIN_VALUE ^ r7[r8 + r42];
            if (r1 < r2) {
                return -1;
            }
            if (r1 > r2) {
                return 1;
            }
        }
        return 0;
    }

    public static int compare(int r4, int[] r5, int[] r6) {
        for (int r42 = r4 - 1; r42 >= 0; r42--) {
            int r1 = r5[r42] ^ Integer.MIN_VALUE;
            int r2 = Integer.MIN_VALUE ^ r6[r42];
            if (r1 < r2) {
                return -1;
            }
            if (r1 > r2) {
                return 1;
            }
        }
        return 0;
    }

    public static void copy(int r0, int[] r1, int r2, int[] r3, int r4) {
        System.arraycopy(r1, r2, r3, r4, r0);
    }

    public static void copy(int r1, int[] r2, int[] r3) {
        System.arraycopy(r2, 0, r3, 0, r1);
    }

    public static int[] copy(int r2, int[] r3) {
        int[] r0 = new int[r2];
        System.arraycopy(r3, 0, r0, 0, r2);
        return r0;
    }

    public static void copy64(int r0, long[] jArr, int r2, long[] jArr2, int r4) {
        System.arraycopy(jArr, r2, jArr2, r4, r0);
    }

    public static void copy64(int r1, long[] jArr, long[] jArr2) {
        System.arraycopy(jArr, 0, jArr2, 0, r1);
    }

    public static long[] copy64(int r2, long[] jArr) {
        long[] jArr2 = new long[r2];
        System.arraycopy(jArr, 0, jArr2, 0, r2);
        return jArr2;
    }

    public static int[] create(int r0) {
        return new int[r0];
    }

    public static long[] create64(int r0) {
        return new long[r0];
    }

    public static int csub(int r12, int r13, int[] r14, int r15, int[] r16, int r17, int[] r18, int r19) {
        long j = (-(r13 & 1)) & 4294967295L;
        long j2 = 0;
        for (int r6 = 0; r6 < r12; r6++) {
            long j3 = j2 + ((r14[r15 + r6] & 4294967295L) - (r16[r17 + r6] & j));
            r18[r19 + r6] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int csub(int r10, int r11, int[] r12, int[] r13, int[] r14) {
        long j = (-(r11 & 1)) & 4294967295L;
        long j2 = 0;
        for (int r112 = 0; r112 < r10; r112++) {
            long j3 = j2 + ((r12[r112] & 4294967295L) - (r13[r112] & j));
            r14[r112] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int dec(int r4, int[] r5) {
        for (int r1 = 0; r1 < r4; r1++) {
            int r3 = r5[r1] - 1;
            r5[r1] = r3;
            if (r3 != -1) {
                return 0;
            }
        }
        return -1;
    }

    public static int dec(int r4, int[] r5, int[] r6) {
        int r1 = 0;
        while (r1 < r4) {
            int r3 = r5[r1] - 1;
            r6[r1] = r3;
            r1++;
            if (r3 != -1) {
                while (r1 < r4) {
                    r6[r1] = r5[r1];
                    r1++;
                }
                return 0;
            }
        }
        return -1;
    }

    public static int decAt(int r2, int[] r3, int r4) {
        while (r4 < r2) {
            int r1 = r3[r4] - 1;
            r3[r4] = r1;
            if (r1 != -1) {
                return 0;
            }
            r4++;
        }
        return -1;
    }

    public static int decAt(int r3, int[] r4, int r5, int r6) {
        while (r6 < r3) {
            int r1 = r5 + r6;
            int r2 = r4[r1] - 1;
            r4[r1] = r2;
            if (r2 != -1) {
                return 0;
            }
            r6++;
        }
        return -1;
    }

    public static boolean diff(int r8, int[] r9, int r10, int[] r11, int r12, int[] r13, int r14) {
        boolean gte = gte(r8, r9, r10, r11, r12);
        if (gte) {
            sub(r8, r9, r10, r11, r12, r13, r14);
        } else {
            sub(r8, r11, r12, r9, r10, r13, r14);
        }
        return gte;
    }

    /* renamed from: eq */
    public static boolean m23eq(int r3, int[] r4, int[] r5) {
        for (int r32 = r3 - 1; r32 >= 0; r32--) {
            if (r4[r32] != r5[r32]) {
                return false;
            }
        }
        return true;
    }

    public static int equalTo(int r3, int[] r4, int r5) {
        int r52 = r5 ^ r4[0];
        for (int r1 = 1; r1 < r3; r1++) {
            r52 |= r4[r1];
        }
        return (((r52 >>> 1) | (r52 & 1)) - 1) >> 31;
    }

    public static int equalTo(int r3, int[] r4, int r5, int r6) {
        int r62 = r6 ^ r4[r5];
        for (int r1 = 1; r1 < r3; r1++) {
            r62 |= r4[r5 + r1];
        }
        return (((r62 >>> 1) | (r62 & 1)) - 1) >> 31;
    }

    public static int equalTo(int r4, int[] r5, int r6, int[] r7, int r8) {
        int r1 = 0;
        for (int r0 = 0; r0 < r4; r0++) {
            r1 |= r5[r6 + r0] ^ r7[r8 + r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static int equalTo(int r4, int[] r5, int[] r6) {
        int r1 = 0;
        for (int r0 = 0; r0 < r4; r0++) {
            r1 |= r5[r0] ^ r6[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static int equalToZero(int r3, int[] r4) {
        int r1 = 0;
        for (int r0 = 0; r0 < r3; r0++) {
            r1 |= r4[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static int equalToZero(int r3, int[] r4, int r5) {
        int r1 = 0;
        for (int r0 = 0; r0 < r3; r0++) {
            r1 |= r4[r5 + r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static int[] fromBigInteger(int r3, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > r3) {
            throw new IllegalArgumentException();
        }
        int r32 = (r3 + 31) >> 5;
        int[] create = create(r32);
        for (int r1 = 0; r1 < r32; r1++) {
            create[r1] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(int r4, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > r4) {
            throw new IllegalArgumentException();
        }
        int r42 = (r4 + 63) >> 6;
        long[] create64 = create64(r42);
        for (int r1 = 0; r1 < r42; r1++) {
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
            if (r1 < 0 || r1 >= r3.length) {
                return 0;
            }
            r32 = r3[r1] >>> (r4 & 31);
        }
        return r32 & 1;
    }

    public static boolean gte(int r4, int[] r5, int r6, int[] r7, int r8) {
        for (int r42 = r4 - 1; r42 >= 0; r42--) {
            int r1 = r5[r6 + r42] ^ Integer.MIN_VALUE;
            int r2 = Integer.MIN_VALUE ^ r7[r8 + r42];
            if (r1 < r2) {
                return false;
            }
            if (r1 > r2) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int r4, int[] r5, int[] r6) {
        for (int r42 = r4 - 1; r42 >= 0; r42--) {
            int r1 = r5[r42] ^ Integer.MIN_VALUE;
            int r2 = Integer.MIN_VALUE ^ r6[r42];
            if (r1 < r2) {
                return false;
            }
            if (r1 > r2) {
                return true;
            }
        }
        return true;
    }

    public static int inc(int r4, int[] r5) {
        for (int r1 = 0; r1 < r4; r1++) {
            int r3 = r5[r1] + 1;
            r5[r1] = r3;
            if (r3 != 0) {
                return 0;
            }
        }
        return 1;
    }

    public static int inc(int r4, int[] r5, int[] r6) {
        int r1 = 0;
        while (r1 < r4) {
            int r3 = r5[r1] + 1;
            r6[r1] = r3;
            r1++;
            if (r3 != 0) {
                while (r1 < r4) {
                    r6[r1] = r5[r1];
                    r1++;
                }
                return 0;
            }
        }
        return 1;
    }

    public static int incAt(int r2, int[] r3, int r4) {
        while (r4 < r2) {
            int r1 = r3[r4] + 1;
            r3[r4] = r1;
            if (r1 != 0) {
                return 0;
            }
            r4++;
        }
        return 1;
    }

    public static int incAt(int r3, int[] r4, int r5, int r6) {
        while (r6 < r3) {
            int r1 = r5 + r6;
            int r2 = r4[r1] + 1;
            r4[r1] = r2;
            if (r2 != 0) {
                return 0;
            }
            r6++;
        }
        return 1;
    }

    public static boolean isOne(int r4, int[] r5) {
        if (r5[0] != 1) {
            return false;
        }
        for (int r1 = 1; r1 < r4; r1++) {
            if (r5[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int r3, int[] r4) {
        for (int r1 = 0; r1 < r3; r1++) {
            if (r4[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int lessThan(int r9, int[] r10, int r11, int[] r12, int r13) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            j = (j + ((r10[r11 + r2] & 4294967295L) - (4294967295L & r12[r13 + r2]))) >> 32;
        }
        return (int) j;
    }

    public static int lessThan(int r9, int[] r10, int[] r11) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            j = (j + ((r10[r2] & 4294967295L) - (4294967295L & r11[r2]))) >> 32;
        }
        return (int) j;
    }

    public static void mul(int r8, int[] r9, int r10, int[] r11, int r12, int[] r13, int r14) {
        r13[r14 + r8] = mulWord(r8, r9[r10], r11, r12, r13, r14);
        for (int r0 = 1; r0 < r8; r0++) {
            int r6 = r14 + r0;
            r13[r6 + r8] = mulWordAddTo(r8, r9[r10 + r0], r11, r12, r13, r6);
        }
    }

    public static void mul(int r8, int[] r9, int[] r10, int[] r11) {
        r11[r8] = mulWord(r8, r9[0], r10, r11);
        for (int r0 = 1; r0 < r8; r0++) {
            r11[r0 + r8] = mulWordAddTo(r8, r9[r0], r10, 0, r11, r0);
        }
    }

    public static void mul(int[] r8, int r9, int r10, int[] r11, int r12, int r13, int[] r14, int r15) {
        r14[r15 + r13] = mulWord(r13, r8[r9], r11, r12, r14, r15);
        for (int r0 = 1; r0 < r10; r0++) {
            int r6 = r15 + r0;
            r14[r6 + r13] = mulWordAddTo(r13, r8[r9 + r0], r11, r12, r14, r6);
        }
    }

    public static int mul31BothAdd(int r14, int r15, int[] r16, int r17, int[] r18, int[] r19, int r20) {
        long j = r15 & 4294967295L;
        long j2 = r17 & 4294967295L;
        long j3 = 0;
        int r8 = 0;
        do {
            int r11 = r20 + r8;
            long j4 = j3 + ((r16[r8] & 4294967295L) * j) + ((r18[r8] & 4294967295L) * j2) + (r19[r11] & 4294967295L);
            r19[r11] = (int) j4;
            j3 = j4 >>> 32;
            r8++;
        } while (r8 < r14);
        return (int) j3;
    }

    public static int mulAddTo(int r9, int[] r10, int r11, int[] r12, int r13, int[] r14, int r15) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            int r3 = r15 + r9;
            long mulWordAddTo = j + (mulWordAddTo(r9, r10[r11 + r2], r12, r13, r14, r15) & 4294967295L) + (r14[r3] & 4294967295L);
            r14[r3] = (int) mulWordAddTo;
            j = mulWordAddTo >>> 32;
            r15++;
        }
        return (int) j;
    }

    public static int mulAddTo(int r9, int[] r10, int[] r11, int[] r12) {
        long j = 0;
        for (int r8 = 0; r8 < r9; r8++) {
            int r2 = r8 + r9;
            long mulWordAddTo = j + (mulWordAddTo(r9, r10[r8], r11, 0, r12, r8) & 4294967295L) + (r12[r2] & 4294967295L);
            r12[r2] = (int) mulWordAddTo;
            j = mulWordAddTo >>> 32;
        }
        return (int) j;
    }

    public static int mulWord(int r8, int r9, int[] r10, int r11, int[] r12, int r13) {
        long j = r9 & 4294967295L;
        long j2 = 0;
        int r92 = 0;
        do {
            long j3 = j2 + ((r10[r11 + r92] & 4294967295L) * j);
            r12[r13 + r92] = (int) j3;
            j2 = j3 >>> 32;
            r92++;
        } while (r92 < r8);
        return (int) j2;
    }

    public static int mulWord(int r8, int r9, int[] r10, int[] r11) {
        long j = r9 & 4294967295L;
        long j2 = 0;
        int r92 = 0;
        do {
            long j3 = j2 + ((r10[r92] & 4294967295L) * j);
            r11[r92] = (int) j3;
            j2 = j3 >>> 32;
            r92++;
        } while (r92 < r8);
        return (int) j2;
    }

    public static int mulWordAddTo(int r12, int r13, int[] r14, int r15, int[] r16, int r17) {
        long j = r13 & 4294967295L;
        long j2 = 0;
        int r6 = 0;
        do {
            int r9 = r17 + r6;
            long j3 = j2 + ((r14[r15 + r6] & 4294967295L) * j) + (r16[r9] & 4294967295L);
            r16[r9] = (int) j3;
            j2 = j3 >>> 32;
            r6++;
        } while (r6 < r12);
        return (int) j2;
    }

    public static int mulWordDwordAddAt(int r10, int r11, long j, int[] r14, int r15) {
        long j2 = r11 & 4294967295L;
        int r112 = r15 + 0;
        long j3 = ((j & 4294967295L) * j2) + (r14[r112] & 4294967295L) + 0;
        r14[r112] = (int) j3;
        long j4 = j2 * (j >>> 32);
        int r12 = r15 + 1;
        long j5 = (j3 >>> 32) + j4 + (r14[r12] & 4294967295L);
        r14[r12] = (int) j5;
        int r0 = r15 + 2;
        long j6 = (j5 >>> 32) + (r14[r0] & 4294967295L);
        r14[r0] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return incAt(r10, r14, r15 + 3);
    }

    public static int shiftDownBit(int r2, int[] r3, int r4) {
        while (true) {
            r2--;
            if (r2 < 0) {
                return r4 << 31;
            }
            int r0 = r3[r2];
            r3[r2] = (r4 << 31) | (r0 >>> 1);
            r4 = r0;
        }
    }

    public static int shiftDownBit(int r3, int[] r4, int r5, int r6) {
        while (true) {
            r3--;
            if (r3 < 0) {
                return r6 << 31;
            }
            int r0 = r5 + r3;
            int r1 = r4[r0];
            r4[r0] = (r6 << 31) | (r1 >>> 1);
            r6 = r1;
        }
    }

    public static int shiftDownBit(int r3, int[] r4, int r5, int r6, int[] r7, int r8) {
        while (true) {
            r3--;
            if (r3 < 0) {
                return r6 << 31;
            }
            int r0 = r4[r5 + r3];
            r7[r8 + r3] = (r6 << 31) | (r0 >>> 1);
            r6 = r0;
        }
    }

    public static int shiftDownBit(int r2, int[] r3, int r4, int[] r5) {
        while (true) {
            r2--;
            if (r2 < 0) {
                return r4 << 31;
            }
            int r0 = r3[r2];
            r5[r2] = (r4 << 31) | (r0 >>> 1);
            r4 = r0;
        }
    }

    public static int shiftDownBits(int r3, int[] r4, int r5, int r6) {
        while (true) {
            r3--;
            if (r3 < 0) {
                return r6 << (-r5);
            }
            int r0 = r4[r3];
            r4[r3] = (r6 << (-r5)) | (r0 >>> r5);
            r6 = r0;
        }
    }

    public static int shiftDownBits(int r4, int[] r5, int r6, int r7, int r8) {
        while (true) {
            r4--;
            if (r4 < 0) {
                return r8 << (-r7);
            }
            int r0 = r6 + r4;
            int r1 = r5[r0];
            r5[r0] = (r8 << (-r7)) | (r1 >>> r7);
            r8 = r1;
        }
    }

    public static int shiftDownBits(int r4, int[] r5, int r6, int r7, int r8, int[] r9, int r10) {
        while (true) {
            r4--;
            if (r4 < 0) {
                return r8 << (-r7);
            }
            int r0 = r5[r6 + r4];
            r9[r10 + r4] = (r8 << (-r7)) | (r0 >>> r7);
            r8 = r0;
        }
    }

    public static int shiftDownBits(int r3, int[] r4, int r5, int r6, int[] r7) {
        while (true) {
            r3--;
            if (r3 < 0) {
                return r6 << (-r5);
            }
            int r0 = r4[r3];
            r7[r3] = (r6 << (-r5)) | (r0 >>> r5);
            r6 = r0;
        }
    }

    public static int shiftDownWord(int r1, int[] r2, int r3) {
        while (true) {
            r1--;
            if (r1 < 0) {
                return r3;
            }
            int r0 = r2[r1];
            r2[r1] = r3;
            r3 = r0;
        }
    }

    public static int shiftUpBit(int r3, int[] r4, int r5) {
        int r0 = 0;
        while (r0 < r3) {
            int r1 = r4[r0];
            r4[r0] = (r5 >>> 31) | (r1 << 1);
            r0++;
            r5 = r1;
        }
        return r5 >>> 31;
    }

    public static int shiftUpBit(int r4, int[] r5, int r6, int r7) {
        int r0 = 0;
        while (r0 < r4) {
            int r1 = r6 + r0;
            int r2 = r5[r1];
            r5[r1] = (r7 >>> 31) | (r2 << 1);
            r0++;
            r7 = r2;
        }
        return r7 >>> 31;
    }

    public static int shiftUpBit(int r4, int[] r5, int r6, int r7, int[] r8, int r9) {
        int r0 = 0;
        while (r0 < r4) {
            int r1 = r5[r6 + r0];
            r8[r9 + r0] = (r7 >>> 31) | (r1 << 1);
            r0++;
            r7 = r1;
        }
        return r7 >>> 31;
    }

    public static int shiftUpBit(int r3, int[] r4, int r5, int[] r6) {
        int r0 = 0;
        while (r0 < r3) {
            int r1 = r4[r0];
            r6[r0] = (r5 >>> 31) | (r1 << 1);
            r0++;
            r5 = r1;
        }
        return r5 >>> 31;
    }

    public static long shiftUpBit64(int r7, long[] jArr, int r9, long j, long[] jArr2, int r13) {
        int r0 = 0;
        while (r0 < r7) {
            long j2 = jArr[r9 + r0];
            jArr2[r13 + r0] = (j >>> 63) | (j2 << 1);
            r0++;
            j = j2;
        }
        return j >>> 63;
    }

    public static int shiftUpBits(int r4, int[] r5, int r6, int r7) {
        int r0 = 0;
        while (r0 < r4) {
            int r1 = r5[r0];
            r5[r0] = (r7 >>> (-r6)) | (r1 << r6);
            r0++;
            r7 = r1;
        }
        return r7 >>> (-r6);
    }

    public static int shiftUpBits(int r5, int[] r6, int r7, int r8, int r9) {
        int r0 = 0;
        while (r0 < r5) {
            int r1 = r7 + r0;
            int r2 = r6[r1];
            r6[r1] = (r9 >>> (-r8)) | (r2 << r8);
            r0++;
            r9 = r2;
        }
        return r9 >>> (-r8);
    }

    public static int shiftUpBits(int r5, int[] r6, int r7, int r8, int r9, int[] r10, int r11) {
        int r0 = 0;
        while (r0 < r5) {
            int r1 = r6[r7 + r0];
            r10[r11 + r0] = (r9 >>> (-r8)) | (r1 << r8);
            r0++;
            r9 = r1;
        }
        return r9 >>> (-r8);
    }

    public static int shiftUpBits(int r4, int[] r5, int r6, int r7, int[] r8) {
        int r0 = 0;
        while (r0 < r4) {
            int r1 = r5[r0];
            r8[r0] = (r7 >>> (-r6)) | (r1 << r6);
            r0++;
            r7 = r1;
        }
        return r7 >>> (-r6);
    }

    public static long shiftUpBits64(int r7, long[] jArr, int r9, int r10, long j) {
        int r0 = 0;
        while (r0 < r7) {
            int r1 = r9 + r0;
            long j2 = jArr[r1];
            jArr[r1] = (j >>> (-r10)) | (j2 << r10);
            r0++;
            j = j2;
        }
        return j >>> (-r10);
    }

    public static long shiftUpBits64(int r7, long[] jArr, int r9, int r10, long j, long[] jArr2, int r14) {
        int r0 = 0;
        while (r0 < r7) {
            long j2 = jArr[r9 + r0];
            jArr2[r14 + r0] = (j >>> (-r10)) | (j2 << r10);
            r0++;
            j = j2;
        }
        return j >>> (-r10);
    }

    public static void square(int r11, int[] r12, int r13, int[] r14, int r15) {
        int r8;
        int r0 = r11 << 1;
        int r1 = 0;
        int r2 = r11;
        int r3 = r0;
        do {
            r2--;
            long j = r12[r13 + r2] & 4294967295L;
            long j2 = j * j;
            int r32 = r3 - 1;
            r14[r15 + r32] = (r1 << 31) | ((int) (j2 >>> 33));
            r3 = r32 - 1;
            r8 = 1;
            r14[r15 + r3] = (int) (j2 >>> 1);
            r1 = (int) j2;
        } while (r2 > 0);
        long j3 = 0;
        int r33 = r15 + 2;
        while (r8 < r11) {
            long squareWordAddTo = j3 + (squareWordAddTo(r12, r13, r8, r14, r15) & 4294967295L) + (r14[r33] & 4294967295L);
            int r4 = r33 + 1;
            r14[r33] = (int) squareWordAddTo;
            long j4 = (squareWordAddTo >>> 32) + (r14[r4] & 4294967295L);
            r14[r4] = (int) j4;
            j3 = j4 >>> 32;
            r8++;
            r33 = r4 + 1;
        }
        shiftUpBit(r0, r14, r15, r12[r13] << 31);
    }

    public static void square(int r11, int[] r12, int[] r13) {
        int r4;
        int r0 = r11 << 1;
        int r42 = 0;
        int r2 = r11;
        int r3 = r0;
        while (true) {
            r2--;
            long j = r12[r2] & 4294967295L;
            long j2 = j * j;
            int r32 = r3 - 1;
            r13[r32] = (r42 << 31) | ((int) (j2 >>> 33));
            r3 = r32 - 1;
            r4 = 1;
            r13[r3] = (int) (j2 >>> 1);
            int r6 = (int) j2;
            if (r2 <= 0) {
                break;
            }
            r42 = r6;
        }
        long j3 = 0;
        int r5 = 2;
        while (r4 < r11) {
            long squareWordAddTo = j3 + (squareWordAddTo(r12, r4, r13) & 4294967295L) + (r13[r5] & 4294967295L);
            int r62 = r5 + 1;
            r13[r5] = (int) squareWordAddTo;
            long j4 = (squareWordAddTo >>> 32) + (r13[r62] & 4294967295L);
            r13[r62] = (int) j4;
            j3 = j4 >>> 32;
            r4++;
            r5 = r62 + 1;
        }
        shiftUpBit(r0, r13, r12[0] << 31);
    }

    public static int squareWordAddTo(int[] r14, int r15, int r16, int[] r17, int r18) {
        int r11;
        long j = r14[r15 + r16] & 4294967295L;
        long j2 = 0;
        int r8 = 0;
        int r5 = r18;
        do {
            long j3 = j2 + ((r14[r15 + r8] & 4294967295L) * j) + (r17[r11] & 4294967295L);
            r17[r16 + r5] = (int) j3;
            j2 = j3 >>> 32;
            r5++;
            r8++;
        } while (r8 < r16);
        return (int) j2;
    }

    public static int squareWordAddTo(int[] r12, int r13, int[] r14) {
        long j = r12[r13] & 4294967295L;
        long j2 = 0;
        int r6 = 0;
        do {
            int r9 = r13 + r6;
            long j3 = j2 + ((r12[r6] & 4294967295L) * j) + (r14[r9] & 4294967295L);
            r14[r9] = (int) j3;
            j2 = j3 >>> 32;
            r6++;
        } while (r6 < r13);
        return (int) j2;
    }

    public static int sub(int r9, int[] r10, int r11, int[] r12, int r13, int[] r14, int r15) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + ((r10[r11 + r2] & 4294967295L) - (4294967295L & r12[r13 + r2]));
            r14[r15 + r2] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int sub(int r9, int[] r10, int[] r11, int[] r12) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + ((r10[r2] & 4294967295L) - (4294967295L & r11[r2]));
            r12[r2] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int sub33At(int r7, int r8, int[] r9, int r10) {
        int r0 = r10 + 0;
        long j = (r9[r0] & 4294967295L) - (r8 & 4294967295L);
        r9[r0] = (int) j;
        int r2 = r10 + 1;
        long j2 = (j >> 32) + ((4294967295L & r9[r2]) - 1);
        r9[r2] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(r7, r9, r10 + 2);
    }

    public static int sub33At(int r7, int r8, int[] r9, int r10, int r11) {
        int r0 = r10 + r11;
        long j = (r9[r0] & 4294967295L) - (r8 & 4294967295L);
        r9[r0] = (int) j;
        int r02 = r0 + 1;
        long j2 = (j >> 32) + ((4294967295L & r9[r02]) - 1);
        r9[r02] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(r7, r9, r10, r11 + 2);
    }

    public static int sub33From(int r8, int r9, int[] r10) {
        long j = (r10[0] & 4294967295L) - (r9 & 4294967295L);
        r10[0] = (int) j;
        long j2 = (j >> 32) + ((4294967295L & r10[1]) - 1);
        r10[1] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(r8, r10, 2);
    }

    public static int sub33From(int r7, int r8, int[] r9, int r10) {
        int r0 = r10 + 0;
        long j = (r9[r0] & 4294967295L) - (r8 & 4294967295L);
        r9[r0] = (int) j;
        int r2 = r10 + 1;
        long j2 = (j >> 32) + ((4294967295L & r9[r2]) - 1);
        r9[r2] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(r7, r9, r10, 2);
    }

    public static int subBothFrom(int r11, int[] r12, int r13, int[] r14, int r15, int[] r16, int r17) {
        long j = 0;
        for (int r2 = 0; r2 < r11; r2++) {
            int r4 = r17 + r2;
            long j2 = j + (((r16[r4] & 4294967295L) - (r12[r13 + r2] & 4294967295L)) - (4294967295L & r14[r15 + r2]));
            r16[r4] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int subBothFrom(int r9, int[] r10, int[] r11, int[] r12) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + (((r12[r2] & 4294967295L) - (r10[r2] & 4294967295L)) - (4294967295L & r11[r2]));
            r12[r2] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int subDWordAt(int r8, long j, int[] r11, int r12) {
        int r0 = r12 + 0;
        long j2 = (r11[r0] & 4294967295L) - (j & 4294967295L);
        r11[r0] = (int) j2;
        int r5 = r12 + 1;
        long j3 = (j2 >> 32) + ((4294967295L & r11[r5]) - (j >>> 32));
        r11[r5] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(r8, r11, r12 + 2);
    }

    public static int subDWordAt(int r8, long j, int[] r11, int r12, int r13) {
        int r0 = r12 + r13;
        long j2 = (r11[r0] & 4294967295L) - (j & 4294967295L);
        r11[r0] = (int) j2;
        int r02 = r0 + 1;
        long j3 = (j2 >> 32) + ((4294967295L & r11[r02]) - (j >>> 32));
        r11[r02] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(r8, r11, r12, r13 + 2);
    }

    public static int subDWordFrom(int r9, long j, int[] r12) {
        long j2 = (r12[0] & 4294967295L) - (j & 4294967295L);
        r12[0] = (int) j2;
        long j3 = (j2 >> 32) + ((4294967295L & r12[1]) - (j >>> 32));
        r12[1] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(r9, r12, 2);
    }

    public static int subDWordFrom(int r8, long j, int[] r11, int r12) {
        int r0 = r12 + 0;
        long j2 = (r11[r0] & 4294967295L) - (j & 4294967295L);
        r11[r0] = (int) j2;
        int r5 = r12 + 1;
        long j3 = (j2 >> 32) + ((4294967295L & r11[r5]) - (j >>> 32));
        r11[r5] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(r8, r11, r12, 2);
    }

    public static int subFrom(int r10, int[] r11, int r12, int[] r13, int r14) {
        long j = 0;
        for (int r2 = 0; r2 < r10; r2++) {
            int r3 = r14 + r2;
            long j2 = j + ((r13[r3] & 4294967295L) - (4294967295L & r11[r12 + r2]));
            r13[r3] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int subFrom(int r9, int[] r10, int[] r11) {
        long j = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            long j2 = j + ((r11[r2] & 4294967295L) - (4294967295L & r10[r2]));
            r11[r2] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int subWordAt(int r6, int r7, int[] r8, int r9) {
        long j = (r8[r9] & 4294967295L) - (4294967295L & r7);
        r8[r9] = (int) j;
        if ((j >> 32) == 0) {
            return 0;
        }
        return decAt(r6, r8, r9 + 1);
    }

    public static int subWordAt(int r7, int r8, int[] r9, int r10, int r11) {
        int r0 = r10 + r11;
        long j = (r9[r0] & 4294967295L) - (4294967295L & r8);
        r9[r0] = (int) j;
        if ((j >> 32) == 0) {
            return 0;
        }
        return decAt(r7, r9, r10, r11 + 1);
    }

    public static int subWordFrom(int r7, int r8, int[] r9) {
        long j = (r9[0] & 4294967295L) - (4294967295L & r8);
        r9[0] = (int) j;
        if ((j >> 32) == 0) {
            return 0;
        }
        return decAt(r7, r9, 1);
    }

    public static int subWordFrom(int r7, int r8, int[] r9, int r10) {
        int r0 = r10 + 0;
        long j = (r9[r0] & 4294967295L) - (4294967295L & r8);
        r9[r0] = (int) j;
        if ((j >> 32) == 0) {
            return 0;
        }
        return decAt(r7, r9, r10, 1);
    }

    public static BigInteger toBigInteger(int r4, int[] r5) {
        byte[] bArr = new byte[r4 << 2];
        for (int r1 = 0; r1 < r4; r1++) {
            int r2 = r5[r1];
            if (r2 != 0) {
                Pack.intToBigEndian(r2, bArr, ((r4 - 1) - r1) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int r2, int[] r3) {
        for (int r1 = 0; r1 < r2; r1++) {
            r3[r1] = 0;
        }
    }

    public static void zero(int r3, int[] r4, int r5) {
        for (int r1 = 0; r1 < r3; r1++) {
            r4[r5 + r1] = 0;
        }
    }

    public static void zero64(int r3, long[] jArr) {
        for (int r0 = 0; r0 < r3; r0++) {
            jArr[r0] = 0;
        }
    }
}
