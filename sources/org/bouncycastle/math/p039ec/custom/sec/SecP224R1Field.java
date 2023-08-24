package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP224R1Field */
/* loaded from: classes5.dex */
public class SecP224R1Field {

    /* renamed from: M */
    private static final long f2317M = 4294967295L;

    /* renamed from: P6 */
    private static final int f2319P6 = -1;
    private static final int PExt13 = -1;

    /* renamed from: P */
    static final int[] f2318P = {1, 0, 0, -1, -1, -1, -1};
    private static final int[] PExt = {1, 0, 0, -2, -1, -1, 0, 2, 0, 0, -2, -1, -1, -1};
    private static final int[] PExtInv = {-1, -1, -1, 1, 0, 0, -1, -3, -1, -1, 1};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat224.add(r0, r1, r2) != 0 || (r2[6] == -1 && Nat224.gte(r2, f2318P))) {
            addPInvTo(r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(14, r1, r2, r3) != 0 || (r3[13] == -1 && Nat.gte(14, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(14, r3, r12.length);
            }
        }
    }

    public static void addOne(int[] r1, int[] r2) {
        if (Nat.inc(7, r1, r2) != 0 || (r2[6] == -1 && Nat224.gte(r2, f2318P))) {
            addPInvTo(r2);
        }
    }

    private static void addPInvTo(int[] r12) {
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
        long j5 = j2 + (4294967295L & r12[3]) + 1;
        r12[3] = (int) j5;
        if ((j5 >> 32) != 0) {
            Nat.incAt(7, r12, 4);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat224.fromBigInteger(bigInteger);
        if (fromBigInteger[6] == -1) {
            int[] r0 = f2318P;
            if (Nat224.gte(fromBigInteger, r0)) {
                Nat224.subFrom(r0, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(7, r3, 0, r4);
        } else {
            Nat.shiftDownBit(7, r4, Nat224.add(r3, f2318P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2318P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 7; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] createExt = Nat224.createExt();
        Nat224.mul(r1, r2, createExt);
        reduce(createExt, r3);
    }

    public static void multiplyAddToExt(int[] r1, int[] r2, int[] r3) {
        if (Nat224.mulAddTo(r1, r2, r3) != 0 || (r3[13] == -1 && Nat.gte(14, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(14, r3, r12.length);
            }
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat224.sub(f2318P, r1, r2);
            return;
        }
        int[] r12 = f2318P;
        Nat224.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[28];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 7);
        } while (Nat.lessThan(7, r4, f2318P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r30, int[] r31) {
        long j = r30[10] & 4294967295L;
        long j2 = r30[11] & 4294967295L;
        long j3 = r30[12] & 4294967295L;
        long j4 = r30[13] & 4294967295L;
        long j5 = ((r30[7] & 4294967295L) + j2) - 1;
        long j6 = (r30[8] & 4294967295L) + j3;
        long j7 = (r30[9] & 4294967295L) + j4;
        long j8 = ((r30[0] & 4294967295L) - j5) + 0;
        long j9 = j8 & 4294967295L;
        long j10 = (j8 >> 32) + ((r30[1] & 4294967295L) - j6);
        r31[1] = (int) j10;
        long j11 = (j10 >> 32) + ((r30[2] & 4294967295L) - j7);
        r31[2] = (int) j11;
        long j12 = (j11 >> 32) + (((r30[3] & 4294967295L) + j5) - j);
        long j13 = j12 & 4294967295L;
        long j14 = (j12 >> 32) + (((r30[4] & 4294967295L) + j6) - j2);
        r31[4] = (int) j14;
        long j15 = (j14 >> 32) + (((r30[5] & 4294967295L) + j7) - j3);
        r31[5] = (int) j15;
        long j16 = (j15 >> 32) + (((r30[6] & 4294967295L) + j) - j4);
        r31[6] = (int) j16;
        long j17 = (j16 >> 32) + 1;
        long j18 = j13 + j17;
        long j19 = j9 - j17;
        r31[0] = (int) j19;
        long j20 = j19 >> 32;
        if (j20 != 0) {
            long j21 = j20 + (r31[1] & 4294967295L);
            r31[1] = (int) j21;
            long j22 = (j21 >> 32) + (4294967295L & r31[2]);
            r31[2] = (int) j22;
            j18 += j22 >> 32;
        }
        r31[3] = (int) j18;
        if (((j18 >> 32) == 0 || Nat.incAt(7, r31, 4) == 0) && !(r31[6] == -1 && Nat224.gte(r31, f2318P))) {
            return;
        }
        addPInvTo(r31);
    }

    public static void reduce32(int r11, int[] r12) {
        long j;
        if (r11 != 0) {
            long j2 = r11 & 4294967295L;
            long j3 = ((r12[0] & 4294967295L) - j2) + 0;
            r12[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (r12[1] & 4294967295L);
                r12[1] = (int) j5;
                long j6 = (j5 >> 32) + (r12[2] & 4294967295L);
                r12[2] = (int) j6;
                j4 = j6 >> 32;
            }
            long j7 = j4 + (4294967295L & r12[3]) + j2;
            r12[3] = (int) j7;
            j = j7 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || Nat.incAt(7, r12, 4) == 0) && !(r12[6] == -1 && Nat224.gte(r12, f2318P))) {
            return;
        }
        addPInvTo(r12);
    }

    public static void square(int[] r1, int[] r2) {
        int[] createExt = Nat224.createExt();
        Nat224.square(r1, createExt);
        reduce(createExt, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] createExt = Nat224.createExt();
        Nat224.square(r1, createExt);
        while (true) {
            reduce(createExt, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            Nat224.square(r3, createExt);
        }
    }

    private static void subPInvFrom(int[] r12) {
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
        long j5 = j2 + ((4294967295L & r12[3]) - 1);
        r12[3] = (int) j5;
        if ((j5 >> 32) != 0) {
            Nat.decAt(7, r12, 4);
        }
    }

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat224.sub(r0, r1, r2) != 0) {
            subPInvFrom(r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(14, r1, r2, r3) != 0) {
            int[] r12 = PExtInv;
            if (Nat.subFrom(r12.length, r12, r3) != 0) {
                Nat.decAt(14, r3, r12.length);
            }
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(7, r2, 0, r3) != 0 || (r3[6] == -1 && Nat224.gte(r3, f2318P))) {
            addPInvTo(r3);
        }
    }
}
