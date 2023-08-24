package org.bouncycastle.math.p039ec.custom.p040gm;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.gm.SM2P256V1Field */
/* loaded from: classes5.dex */
public class SM2P256V1Field {

    /* renamed from: M */
    private static final long f2279M = 4294967295L;
    private static final int P7s1 = Integer.MAX_VALUE;
    private static final int PExt15s1 = Integer.MAX_VALUE;

    /* renamed from: P */
    static final int[] f2280P = {-1, -1, 0, -1, -1, -1, -1, -2};
    private static final int[] PExt = {1, 0, -2, 1, 1, -2, 0, 2, -2, -3, 3, -2, -1, -1, 0, -2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat256.add(r0, r1, r2) != 0 || ((r2[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(r2, f2280P))) {
            addPInvTo(r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(16, r1, r2, r3) != 0 || ((r3[15] >>> 1) >= Integer.MAX_VALUE && Nat.gte(16, r3, PExt))) {
            Nat.subFrom(16, PExt, r3);
        }
    }

    public static void addOne(int[] r1, int[] r2) {
        if (Nat.inc(8, r1, r2) != 0 || ((r2[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(r2, f2280P))) {
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
            j2 = j3 >> 32;
        }
        long j4 = j2 + ((r12[2] & 4294967295L) - 1);
        r12[2] = (int) j4;
        long j5 = (j4 >> 32) + (r12[3] & 4294967295L) + 1;
        r12[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (r12[4] & 4294967295L);
            r12[4] = (int) j7;
            long j8 = (j7 >> 32) + (r12[5] & 4294967295L);
            r12[5] = (int) j8;
            long j9 = (j8 >> 32) + (r12[6] & 4294967295L);
            r12[6] = (int) j9;
            j6 = j9 >> 32;
        }
        r12[7] = (int) (j6 + (4294967295L & r12[7]) + 1);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat256.fromBigInteger(bigInteger);
        if ((fromBigInteger[7] >>> 1) >= Integer.MAX_VALUE) {
            int[] r0 = f2280P;
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
            Nat.shiftDownBit(8, r4, Nat256.add(r3, f2280P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2280P, r1, r2);
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
            Nat256.sub(f2280P, r1, r2);
            return;
        }
        int[] r12 = f2280P;
        Nat256.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[32];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 8);
        } while (Nat.lessThan(8, r4, f2280P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r36, int[] r37) {
        long j = r36[8] & 4294967295L;
        long j2 = r36[9] & 4294967295L;
        long j3 = r36[10] & 4294967295L;
        long j4 = r36[11] & 4294967295L;
        long j5 = r36[12] & 4294967295L;
        long j6 = r36[13] & 4294967295L;
        long j7 = r36[14] & 4294967295L;
        long j8 = r36[15] & 4294967295L;
        long j9 = j3 + j4;
        long j10 = j6 + j7;
        long j11 = j10 + (j8 << 1);
        long j12 = j + j2 + j10;
        long j13 = j9 + j5 + j8 + j12;
        long j14 = (r36[0] & 4294967295L) + j13 + j6 + j7 + j8 + 0;
        r37[0] = (int) j14;
        long j15 = (j14 >> 32) + (((r36[1] & 4294967295L) + j13) - j) + j7 + j8;
        r37[1] = (int) j15;
        long j16 = (j15 >> 32) + ((r36[2] & 4294967295L) - j12);
        r37[2] = (int) j16;
        long j17 = (j16 >> 32) + ((((r36[3] & 4294967295L) + j13) - j2) - j3) + j6;
        r37[3] = (int) j17;
        long j18 = (j17 >> 32) + ((((r36[4] & 4294967295L) + j13) - j9) - j) + j7;
        r37[4] = (int) j18;
        long j19 = (j18 >> 32) + (r36[5] & 4294967295L) + j11 + j3;
        r37[5] = (int) j19;
        long j20 = (j19 >> 32) + (r36[6] & 4294967295L) + j4 + j7 + j8;
        r37[6] = (int) j20;
        long j21 = (j20 >> 32) + (4294967295L & r36[7]) + j13 + j11 + j5;
        r37[7] = (int) j21;
        reduce32((int) (j21 >> 32), r37);
    }

    public static void reduce32(int r13, int[] r14) {
        long j;
        if (r13 != 0) {
            long j2 = r13 & 4294967295L;
            long j3 = (r14[0] & 4294967295L) + j2 + 0;
            r14[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (r14[1] & 4294967295L);
                r14[1] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = j4 + ((r14[2] & 4294967295L) - j2);
            r14[2] = (int) j6;
            long j7 = (j6 >> 32) + (r14[3] & 4294967295L) + j2;
            r14[3] = (int) j7;
            long j8 = j7 >> 32;
            if (j8 != 0) {
                long j9 = j8 + (r14[4] & 4294967295L);
                r14[4] = (int) j9;
                long j10 = (j9 >> 32) + (r14[5] & 4294967295L);
                r14[5] = (int) j10;
                long j11 = (j10 >> 32) + (r14[6] & 4294967295L);
                r14[6] = (int) j11;
                j8 = j11 >> 32;
            }
            long j12 = j8 + (4294967295L & r14[7]) + j2;
            r14[7] = (int) j12;
            j = j12 >> 32;
        } else {
            j = 0;
        }
        if (j != 0 || ((r14[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(r14, f2280P))) {
            addPInvTo(r14);
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
            j2 = j3 >> 32;
        }
        long j4 = j2 + (r12[2] & 4294967295L) + 1;
        r12[2] = (int) j4;
        long j5 = (j4 >> 32) + ((r12[3] & 4294967295L) - 1);
        r12[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (r12[4] & 4294967295L);
            r12[4] = (int) j7;
            long j8 = (j7 >> 32) + (r12[5] & 4294967295L);
            r12[5] = (int) j8;
            long j9 = (j8 >> 32) + (r12[6] & 4294967295L);
            r12[6] = (int) j9;
            j6 = j9 >> 32;
        }
        r12[7] = (int) (j6 + ((4294967295L & r12[7]) - 1));
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
        if (Nat.shiftUpBit(8, r2, 0, r3) != 0 || ((r3[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(r3, f2280P))) {
            addPInvTo(r3);
        }
    }
}
