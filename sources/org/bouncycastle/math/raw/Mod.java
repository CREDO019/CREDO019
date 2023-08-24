package org.bouncycastle.math.raw;

import java.util.Random;
import org.bouncycastle.util.Integers;

/* loaded from: classes5.dex */
public abstract class Mod {
    private static final int M30 = 1073741823;
    private static final long M32L = 4294967295L;

    private static int add30(int r4, int[] r5, int[] r6) {
        int r42 = r4 - 1;
        int r1 = 0;
        for (int r0 = 0; r0 < r42; r0++) {
            int r12 = r1 + r5[r0] + r6[r0];
            r5[r0] = 1073741823 & r12;
            r1 = r12 >> 30;
        }
        int r13 = r1 + r5[r42] + r6[r42];
        r5[r42] = r13;
        return r13 >> 30;
    }

    public static void checkedModOddInverse(int[] r0, int[] r1, int[] r2) {
        if (modOddInverse(r0, r1, r2) == 0) {
            throw new ArithmeticException("Inverse does not exist.");
        }
    }

    public static void checkedModOddInverseVar(int[] r0, int[] r1, int[] r2) {
        if (!modOddInverseVar(r0, r1, r2)) {
            throw new ArithmeticException("Inverse does not exist.");
        }
    }

    private static void cnegate30(int r3, int r4, int[] r5) {
        int r32 = r3 - 1;
        int r1 = 0;
        for (int r0 = 0; r0 < r32; r0++) {
            int r12 = r1 + ((r5[r0] ^ r4) - r4);
            r5[r0] = 1073741823 & r12;
            r1 = r12 >> 30;
        }
        r5[r32] = r1 + ((r5[r32] ^ r4) - r4);
    }

    private static void cnormalize30(int r7, int r8, int[] r9, int[] r10) {
        int r72 = r7 - 1;
        int r0 = r9[r72] >> 31;
        int r3 = 0;
        for (int r2 = 0; r2 < r72; r2++) {
            int r32 = r3 + (((r9[r2] + (r10[r2] & r0)) ^ r8) - r8);
            r9[r2] = 1073741823 & r32;
            r3 = r32 >> 30;
        }
        r9[r72] = r3 + (((r9[r72] + (r0 & r10[r72])) ^ r8) - r8);
        int r82 = r9[r72] >> 31;
        int r02 = 0;
        for (int r1 = 0; r1 < r72; r1++) {
            int r03 = r02 + r9[r1] + (r10[r1] & r82);
            r9[r1] = r03 & 1073741823;
            r02 = r03 >> 30;
        }
        r9[r72] = r02 + r9[r72] + (r82 & r10[r72]);
    }

    private static void decode30(int r6, int[] r7, int r8, int[] r9, int r10) {
        int r0 = 0;
        long j = 0;
        while (r6 > 0) {
            while (r0 < Math.min(32, r6)) {
                j |= r7[r8] << r0;
                r0 += 30;
                r8++;
            }
            r9[r10] = (int) j;
            j >>>= 32;
            r0 -= 32;
            r6 -= 32;
            r10++;
        }
    }

    private static int divsteps30(int r12, int r13, int r14, int[] r15) {
        int r3 = 1;
        int r4 = 0;
        int r5 = 0;
        int r6 = 1;
        for (int r2 = 0; r2 < 30; r2++) {
            int r7 = r12 >> 31;
            int r8 = -(r14 & 1);
            int r142 = r14 + (((r13 ^ r7) - r7) & r8);
            r5 += ((r3 ^ r7) - r7) & r8;
            r6 += ((r4 ^ r7) - r7) & r8;
            int r72 = r7 & r8;
            r12 = (r12 ^ r72) - (r72 + 1);
            r13 += r142 & r72;
            r14 = r142 >> 1;
            r3 = (r3 + (r5 & r72)) << 1;
            r4 = (r4 + (r72 & r6)) << 1;
        }
        r15[0] = r3;
        r15[1] = r4;
        r15[2] = r5;
        r15[3] = r6;
        return r12;
    }

    private static int divsteps30Var(int r17, int r18, int r19, int[] r20) {
        int r10;
        int r2 = r17;
        int r3 = r18;
        int r4 = r19;
        int r5 = 30;
        int r6 = 1;
        int r7 = 0;
        int r8 = 0;
        int r9 = 1;
        while (true) {
            int numberOfTrailingZeros = Integers.numberOfTrailingZeros(((-1) << r5) | r4);
            int r42 = r4 >> numberOfTrailingZeros;
            r6 <<= numberOfTrailingZeros;
            r7 <<= numberOfTrailingZeros;
            r2 -= numberOfTrailingZeros;
            r5 -= numberOfTrailingZeros;
            if (r5 <= 0) {
                r20[0] = r6;
                r20[1] = r7;
                r20[2] = r8;
                r20[3] = r9;
                return r2;
            }
            if (r2 < 0) {
                r2 = -r2;
                int r32 = -r3;
                int r62 = -r6;
                int r72 = -r7;
                int r12 = r2 + 1;
                if (r12 > r5) {
                    r12 = r5;
                }
                r10 = ((-1) >>> (32 - r12)) & 63 & (r42 * r32 * ((r42 * r42) - 2));
                r42 = r32;
                r3 = r42;
                int r15 = r8;
                r8 = r62;
                r6 = r15;
                int r16 = r9;
                r9 = r72;
                r7 = r16;
            } else {
                int r11 = r2 + 1;
                if (r11 > r5) {
                    r11 = r5;
                }
                r10 = ((-1) >>> (32 - r11)) & 15 & ((-((((r3 + 1) & 4) << 1) + r3)) * r42);
            }
            r4 = r42 + (r3 * r10);
            r8 += r6 * r10;
            r9 += r10 * r7;
        }
    }

    private static void encode30(int r9, int[] r10, int r11, int[] r12, int r13) {
        int r0 = 0;
        long j = 0;
        while (r9 > 0) {
            if (r0 < Math.min(30, r9)) {
                j |= (r10[r11] & 4294967295L) << r0;
                r0 += 32;
                r11++;
            }
            r12[r13] = ((int) j) & 1073741823;
            j >>>= 30;
            r0 -= 30;
            r9 -= 30;
            r13++;
        }
    }

    private static int getMaximumDivsteps(int r2) {
        return ((r2 * 49) + (r2 < 46 ? 80 : 47)) / 17;
    }

    public static int inverse32(int r2) {
        int r0 = (2 - (r2 * r2)) * r2;
        int r02 = r0 * (2 - (r2 * r0));
        int r03 = r02 * (2 - (r2 * r02));
        return r03 * (2 - (r2 * r03));
    }

    public static int modOddInverse(int[] r19, int[] r20, int[] r21) {
        int length = r19.length;
        int numberOfLeadingZeros = (length << 5) - Integers.numberOfLeadingZeros(r19[length - 1]);
        int r1 = (numberOfLeadingZeros + 29) / 30;
        int[] r10 = new int[4];
        int[] r11 = new int[r1];
        int[] r12 = new int[r1];
        int[] r13 = new int[r1];
        int[] r14 = new int[r1];
        int[] r15 = new int[r1];
        char c = 0;
        r12[0] = 1;
        encode30(numberOfLeadingZeros, r20, 0, r14, 0);
        encode30(numberOfLeadingZeros, r19, 0, r15, 0);
        System.arraycopy(r15, 0, r13, 0, r1);
        int inverse32 = inverse32(r15[0]);
        int maximumDivsteps = getMaximumDivsteps(numberOfLeadingZeros);
        int r4 = -1;
        int r7 = 0;
        while (r7 < maximumDivsteps) {
            int divsteps30 = divsteps30(r4, r13[c], r14[c], r10);
            updateDE30(r1, r11, r12, r10, inverse32, r15);
            updateFG30(r1, r13, r14, r10);
            r7 += 30;
            r4 = divsteps30;
            maximumDivsteps = maximumDivsteps;
            c = 0;
        }
        int r0 = r13[r1 - 1] >> 31;
        cnegate30(r1, r0, r13);
        cnormalize30(r1, r0, r11, r15);
        decode30(numberOfLeadingZeros, r11, 0, r21, 0);
        return Nat.equalTo(r1, r13, 1) & Nat.equalToZero(r1, r14);
    }

    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v3 */
    public static boolean modOddInverseVar(int[] r21, int[] r22, int[] r23) {
        int length = r21.length;
        int numberOfLeadingZeros = (length << 5) - Integers.numberOfLeadingZeros(r21[length - 1]);
        int r1 = (numberOfLeadingZeros + 29) / 30;
        int[] r10 = new int[4];
        int[] r11 = new int[r1];
        int[] r12 = new int[r1];
        int[] r13 = new int[r1];
        int[] r14 = new int[r1];
        int[] r15 = new int[r1];
        ?? r9 = 0;
        r12[0] = 1;
        encode30(numberOfLeadingZeros, r22, 0, r14, 0);
        encode30(numberOfLeadingZeros, r21, 0, r15, 0);
        System.arraycopy(r15, 0, r13, 0, r1);
        int r0 = r1 - 1;
        int numberOfLeadingZeros2 = (-1) - (Integers.numberOfLeadingZeros(r14[r0] | 1) - (((r1 * 30) + 2) - numberOfLeadingZeros));
        int inverse32 = inverse32(r15[0]);
        int maximumDivsteps = getMaximumDivsteps(numberOfLeadingZeros);
        int r7 = r1;
        int r5 = 0;
        while (!Nat.isZero(r7, r14)) {
            if (r5 >= maximumDivsteps) {
                return r9;
            }
            int r17 = r5 + 30;
            int divsteps30Var = divsteps30Var(numberOfLeadingZeros2, r13[r9], r14[r9], r10);
            int r3 = r7;
            int r19 = maximumDivsteps;
            int[] r20 = r12;
            updateDE30(r1, r11, r12, r10, inverse32, r15);
            updateFG30(r3, r13, r14, r10);
            int r72 = r3 - 1;
            int r4 = r13[r72];
            int r52 = r14[r72];
            int r73 = r3 - 2;
            if (((r73 >> 31) | ((r4 >> 31) ^ r4) | ((r52 >> 31) ^ r52)) == 0) {
                r13[r73] = (r4 << 30) | r13[r73];
                r14[r73] = r14[r73] | (r52 << 30);
                r7 = r3 - 1;
            } else {
                r7 = r3;
            }
            r5 = r17;
            numberOfLeadingZeros2 = divsteps30Var;
            maximumDivsteps = r19;
            r12 = r20;
            r9 = 0;
        }
        int r32 = r7;
        int r42 = r13[r32 - 1] >> 31;
        int r02 = r11[r0] >> 31;
        if (r02 < 0) {
            r02 = add30(r1, r11, r15);
        }
        if (r42 < 0) {
            r02 = negate30(r1, r11);
            negate30(r32, r13);
        }
        if (Nat.isOne(r32, r13)) {
            if (r02 < 0) {
                add30(r1, r11, r15);
            }
            decode30(numberOfLeadingZeros, r11, 0, r23, 0);
            return true;
        }
        return false;
    }

    private static int negate30(int r3, int[] r4) {
        int r32 = r3 - 1;
        int r1 = 0;
        for (int r0 = 0; r0 < r32; r0++) {
            int r12 = r1 - r4[r0];
            r4[r0] = 1073741823 & r12;
            r1 = r12 >> 30;
        }
        int r13 = r1 - r4[r32];
        r4[r32] = r13;
        return r13 >> 30;
    }

    public static int[] random(int[] r7) {
        int length = r7.length;
        Random random = new Random();
        int[] create = Nat.create(length);
        int r3 = length - 1;
        int r4 = r7[r3];
        int r42 = r4 | (r4 >>> 1);
        int r43 = r42 | (r42 >>> 2);
        int r44 = r43 | (r43 >>> 4);
        int r45 = r44 | (r44 >>> 8);
        int r46 = r45 | (r45 >>> 16);
        do {
            for (int r5 = 0; r5 != length; r5++) {
                create[r5] = random.nextInt();
            }
            create[r3] = create[r3] & r46;
        } while (Nat.gte(length, create, r7));
        return create;
    }

    private static void updateDE30(int r30, int[] r31, int[] r32, int[] r33, int r34, int[] r35) {
        int r0 = r30;
        int r2 = r33[0];
        int r4 = r33[1];
        int r5 = r33[2];
        int r6 = r33[3];
        int r7 = r0 - 1;
        int r8 = r31[r7] >> 31;
        int r9 = r32[r7] >> 31;
        int r10 = (r2 & r8) + (r4 & r9);
        int r82 = (r8 & r5) + (r9 & r6);
        int r92 = r35[0];
        long j = r2;
        long j2 = r31[0];
        long j3 = r4;
        long j4 = r32[0];
        long j5 = (j * j2) + (j3 * j4);
        long j6 = r5;
        long j7 = r6;
        long j8 = (j2 * j6) + (j4 * j7);
        long j9 = r92;
        long j10 = r10 - (((((int) j5) * r34) + r10) & 1073741823);
        int r42 = r7;
        long j11 = r82 - (((((int) j8) * r34) + r82) & 1073741823);
        long j12 = (j8 + (j9 * j11)) >> 30;
        long j13 = (j5 + (j9 * j10)) >> 30;
        int r3 = 1;
        while (r3 < r0) {
            int r15 = r35[r3];
            long j14 = j12;
            long j15 = r31[r3];
            int r02 = r3;
            long j16 = r32[r3];
            long j17 = j11;
            long j18 = r15;
            long j19 = j13 + (j * j15) + (j3 * j16) + (j18 * j10);
            long j20 = j14 + (j15 * j6) + (j16 * j7) + (j18 * j17);
            int r36 = r02 - 1;
            r31[r36] = ((int) j19) & 1073741823;
            j13 = j19 >> 30;
            r32[r36] = ((int) j20) & 1073741823;
            j12 = j20 >> 30;
            r3 = r02 + 1;
            r0 = r30;
            r42 = r42;
            j11 = j17;
        }
        int r18 = r42;
        r31[r18] = (int) j13;
        r32[r18] = (int) j12;
    }

    private static void updateFG30(int r24, int[] r25, int[] r26, int[] r27) {
        int r2 = r27[0];
        int r4 = r27[1];
        int r5 = r27[2];
        int r6 = r27[3];
        long j = r2;
        long j2 = r25[0];
        long j3 = r4;
        long j4 = r26[0];
        long j5 = r5;
        long j6 = r6;
        long j7 = ((j * j2) + (j3 * j4)) >> 30;
        long j8 = ((j2 * j5) + (j4 * j6)) >> 30;
        int r22 = 1;
        while (r22 < r24) {
            int r3 = r25[r22];
            int r1 = r26[r22];
            int r17 = r22;
            long j9 = r3;
            long j10 = j * j9;
            long j11 = j;
            long j12 = r1;
            long j13 = j7 + j10 + (j3 * j12);
            long j14 = j8 + (j9 * j5) + (j12 * j6);
            int r23 = r17 - 1;
            r25[r23] = ((int) j13) & 1073741823;
            j7 = j13 >> 30;
            r26[r23] = 1073741823 & ((int) j14);
            j8 = j14 >> 30;
            r22 = r17 + 1;
            j = j11;
        }
        int r0 = r24 - 1;
        r25[r0] = (int) j7;
        r26[r0] = (int) j8;
    }
}
