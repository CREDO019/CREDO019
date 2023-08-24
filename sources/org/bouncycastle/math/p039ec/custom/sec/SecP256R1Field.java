package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP256R1Field */
/* loaded from: classes5.dex */
public class SecP256R1Field {

    /* renamed from: M */
    private static final long f2328M = 4294967295L;

    /* renamed from: P7 */
    private static final int f2330P7 = -1;
    private static final int PExt15s1 = Integer.MAX_VALUE;

    /* renamed from: P */
    static final int[] f2329P = {-1, -1, -1, 0, 0, 0, 1, -1};
    private static final int[] PExt = {1, 0, 0, -2, -1, -1, -2, 1, -2, 1, -2, 1, 1, -2, 2, -2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat256.add(r0, r1, r2) != 0 || (r2[7] == -1 && Nat256.gte(r2, f2329P))) {
            addPInvTo(r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(16, r1, r2, r3) != 0 || ((r3[15] >>> 1) >= Integer.MAX_VALUE && Nat.gte(16, r3, PExt))) {
            Nat.subFrom(16, PExt, r3);
        }
    }

    public static void addOne(int[] r1, int[] r2) {
        if (Nat.inc(8, r1, r2) != 0 || (r2[7] == -1 && Nat256.gte(r2, f2329P))) {
            addPInvTo(r2);
        }
    }

    private static void addPInvTo(int[] r12) {
        long j = (r12[0] & 4294967295L) + 1;
        r12[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (r12[1] & 4294967295L);
            r12[1] = (int) j3;
            long j4 = (j3 >> 32) + (r12[2] & 4294967295L);
            r12[2] = (int) j4;
            j2 = j4 >> 32;
        }
        long j5 = j2 + ((r12[3] & 4294967295L) - 1);
        r12[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (r12[4] & 4294967295L);
            r12[4] = (int) j7;
            long j8 = (j7 >> 32) + (r12[5] & 4294967295L);
            r12[5] = (int) j8;
            j6 = j8 >> 32;
        }
        long j9 = j6 + ((r12[6] & 4294967295L) - 1);
        r12[6] = (int) j9;
        r12[7] = (int) ((j9 >> 32) + (4294967295L & r12[7]) + 1);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat256.fromBigInteger(bigInteger);
        if (fromBigInteger[7] == -1) {
            int[] r0 = f2329P;
            if (Nat256.gte(fromBigInteger, r0)) {
                Nat256.subFrom(r0, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(8, r3, 0, r4);
        } else {
            Nat.shiftDownBit(8, r4, Nat256.add(r3, f2329P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2329P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 8; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] createExt = Nat256.createExt();
        Nat256.mul(r1, r2, createExt);
        reduce(createExt, r3);
    }

    public static void multiplyAddToExt(int[] r1, int[] r2, int[] r3) {
        if (Nat256.mulAddTo(r1, r2, r3) != 0 || ((r3[15] >>> 1) >= Integer.MAX_VALUE && Nat.gte(16, r3, PExt))) {
            Nat.subFrom(16, PExt, r3);
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat256.sub(f2329P, r1, r2);
            return;
        }
        int[] r12 = f2329P;
        Nat256.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[32];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 8);
        } while (Nat.lessThan(8, r4, f2329P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r34, int[] r35) {
        long j = r34[9] & 4294967295L;
        long j2 = r34[10] & 4294967295L;
        long j3 = r34[11] & 4294967295L;
        long j4 = r34[12] & 4294967295L;
        long j5 = r34[13] & 4294967295L;
        long j6 = r34[14] & 4294967295L;
        long j7 = r34[15] & 4294967295L;
        long j8 = (r34[8] & 4294967295L) - 6;
        long j9 = j8 + j;
        long j10 = j + j2;
        long j11 = (j2 + j3) - j7;
        long j12 = j3 + j4;
        long j13 = j4 + j5;
        long j14 = j5 + j6;
        long j15 = j6 + j7;
        long j16 = j14 - j9;
        long j17 = (((r34[0] & 4294967295L) - j12) - j16) + 0;
        r35[0] = (int) j17;
        long j18 = (j17 >> 32) + ((((r34[1] & 4294967295L) + j10) - j13) - j15);
        r35[1] = (int) j18;
        long j19 = (j18 >> 32) + (((r34[2] & 4294967295L) + j11) - j14);
        r35[2] = (int) j19;
        long j20 = (j19 >> 32) + ((((r34[3] & 4294967295L) + (j12 << 1)) + j16) - j15);
        r35[3] = (int) j20;
        long j21 = (j20 >> 32) + ((((r34[4] & 4294967295L) + (j13 << 1)) + j6) - j10);
        r35[4] = (int) j21;
        long j22 = (j21 >> 32) + (((r34[5] & 4294967295L) + (j14 << 1)) - j11);
        r35[5] = (int) j22;
        long j23 = (j22 >> 32) + (r34[6] & 4294967295L) + (j15 << 1) + j16;
        r35[6] = (int) j23;
        long j24 = (j23 >> 32) + (((((r34[7] & 4294967295L) + (j7 << 1)) + j8) - j11) - j13);
        r35[7] = (int) j24;
        reduce32((int) ((j24 >> 32) + 6), r35);
    }

    public static void reduce32(int r12, int[] r13) {
        long j;
        if (r12 != 0) {
            long j2 = r12 & 4294967295L;
            long j3 = (r13[0] & 4294967295L) + j2 + 0;
            r13[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (r13[1] & 4294967295L);
                r13[1] = (int) j5;
                long j6 = (j5 >> 32) + (r13[2] & 4294967295L);
                r13[2] = (int) j6;
                j4 = j6 >> 32;
            }
            long j7 = j4 + ((r13[3] & 4294967295L) - j2);
            r13[3] = (int) j7;
            long j8 = j7 >> 32;
            if (j8 != 0) {
                long j9 = j8 + (r13[4] & 4294967295L);
                r13[4] = (int) j9;
                long j10 = (j9 >> 32) + (r13[5] & 4294967295L);
                r13[5] = (int) j10;
                j8 = j10 >> 32;
            }
            long j11 = j8 + ((r13[6] & 4294967295L) - j2);
            r13[6] = (int) j11;
            long j12 = (j11 >> 32) + (4294967295L & r13[7]) + j2;
            r13[7] = (int) j12;
            j = j12 >> 32;
        } else {
            j = 0;
        }
        if (j != 0 || (r13[7] == -1 && Nat256.gte(r13, f2329P))) {
            addPInvTo(r13);
        }
    }

    public static void square(int[] r1, int[] r2) {
        int[] createExt = Nat256.createExt();
        Nat256.square(r1, createExt);
        reduce(createExt, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] createExt = Nat256.createExt();
        Nat256.square(r1, createExt);
        while (true) {
            reduce(createExt, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            Nat256.square(r3, createExt);
        }
    }

    private static void subPInvFrom(int[] r12) {
        long j = (r12[0] & 4294967295L) - 1;
        r12[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (r12[1] & 4294967295L);
            r12[1] = (int) j3;
            long j4 = (j3 >> 32) + (r12[2] & 4294967295L);
            r12[2] = (int) j4;
            j2 = j4 >> 32;
        }
        long j5 = j2 + (r12[3] & 4294967295L) + 1;
        r12[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (r12[4] & 4294967295L);
            r12[4] = (int) j7;
            long j8 = (j7 >> 32) + (r12[5] & 4294967295L);
            r12[5] = (int) j8;
            j6 = j8 >> 32;
        }
        long j9 = j6 + (r12[6] & 4294967295L) + 1;
        r12[6] = (int) j9;
        r12[7] = (int) ((j9 >> 32) + ((4294967295L & r12[7]) - 1));
    }

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat256.sub(r0, r1, r2) != 0) {
            subPInvFrom(r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(16, r1, r2, r3) != 0) {
            Nat.addTo(16, PExt, r3);
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(8, r2, 0, r3) != 0 || (r3[7] == -1 && Nat256.gte(r3, f2329P))) {
            addPInvTo(r3);
        }
    }
}
