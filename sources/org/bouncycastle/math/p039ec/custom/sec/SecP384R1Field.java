package org.bouncycastle.math.p039ec.custom.sec;

import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat384;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP384R1Field */
/* loaded from: classes5.dex */
public class SecP384R1Field {

    /* renamed from: M */
    private static final long f2334M = 4294967295L;
    private static final int P11 = -1;
    private static final int PExt23 = -1;

    /* renamed from: P */
    static final int[] f2335P = {-1, 0, 0, -1, -2, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] PExt = {1, -2, 0, 2, 0, -2, 0, 2, 1, 0, 0, 0, -2, 1, 0, -2, -3, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = {-1, 1, -1, -3, -1, 1, -1, -3, -2, -1, -1, -1, 1, -2, -1, 1, 2};

    public static void add(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(12, r1, r2, r3) != 0 || (r3[11] == -1 && Nat.gte(12, r3, f2335P))) {
            addPInvTo(r3);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(24, r1, r2, r3) != 0 || (r3[23] == -1 && Nat.gte(24, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(24, r3, r12.length);
            }
        }
    }

    public static void addOne(int[] r2, int[] r3) {
        if (Nat.inc(12, r2, r3) != 0 || (r3[11] == -1 && Nat.gte(12, r3, f2335P))) {
            addPInvTo(r3);
        }
    }

    private static void addPInvTo(int[] r12) {
        long j = (r12[0] & 4294967295L) + 1;
        r12[0] = (int) j;
        long j2 = (j >> 32) + ((r12[1] & 4294967295L) - 1);
        r12[1] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (r12[2] & 4294967295L);
            r12[2] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + (r12[3] & 4294967295L) + 1;
        r12[3] = (int) j5;
        long j6 = (j5 >> 32) + (4294967295L & r12[4]) + 1;
        r12[4] = (int) j6;
        if ((j6 >> 32) != 0) {
            Nat.incAt(12, r12, 5);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat.fromBigInteger(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, bigInteger);
        if (fromBigInteger[11] == -1) {
            int[] r0 = f2335P;
            if (Nat.gte(12, fromBigInteger, r0)) {
                Nat.subFrom(12, r0, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(12, r3, 0, r4);
        } else {
            Nat.shiftDownBit(12, r4, Nat.add(12, r3, f2335P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2335P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 12; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] create = Nat.create(24);
        Nat384.mul(r1, r2, create);
        reduce(create, r3);
    }

    public static void negate(int[] r2, int[] r3) {
        if (isZero(r2) == 0) {
            Nat.sub(12, f2335P, r2, r3);
            return;
        }
        int[] r22 = f2335P;
        Nat.sub(12, r22, r22, r3);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[48];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 12);
        } while (Nat.lessThan(12, r4, f2335P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r40, int[] r41) {
        long j = r40[16] & 4294967295L;
        long j2 = r40[17] & 4294967295L;
        long j3 = r40[18] & 4294967295L;
        long j4 = r40[19] & 4294967295L;
        long j5 = r40[20] & 4294967295L;
        long j6 = r40[21] & 4294967295L;
        long j7 = r40[22] & 4294967295L;
        long j8 = r40[23] & 4294967295L;
        long j9 = ((r40[12] & 4294967295L) + j5) - 1;
        long j10 = (r40[13] & 4294967295L) + j7;
        long j11 = (r40[14] & 4294967295L) + j7 + j8;
        long j12 = (r40[15] & 4294967295L) + j8;
        long j13 = j2 + j6;
        long j14 = j6 - j8;
        long j15 = j7 - j8;
        long j16 = j9 + j14;
        long j17 = (r40[0] & 4294967295L) + j16 + 0;
        r41[0] = (int) j17;
        long j18 = (j17 >> 32) + (((r40[1] & 4294967295L) + j8) - j9) + j10;
        r41[1] = (int) j18;
        long j19 = (j18 >> 32) + (((r40[2] & 4294967295L) - j6) - j10) + j11;
        r41[2] = (int) j19;
        long j20 = (j19 >> 32) + ((r40[3] & 4294967295L) - j11) + j12 + j16;
        r41[3] = (int) j20;
        long j21 = (j20 >> 32) + (((((r40[4] & 4294967295L) + j) + j6) + j10) - j12) + j16;
        r41[4] = (int) j21;
        long j22 = (j21 >> 32) + ((r40[5] & 4294967295L) - j) + j10 + j11 + j13;
        r41[5] = (int) j22;
        long j23 = (j22 >> 32) + (((r40[6] & 4294967295L) + j3) - j2) + j11 + j12;
        r41[6] = (int) j23;
        long j24 = (j23 >> 32) + ((((r40[7] & 4294967295L) + j) + j4) - j3) + j12;
        r41[7] = (int) j24;
        long j25 = (j24 >> 32) + (((((r40[8] & 4294967295L) + j) + j2) + j5) - j4);
        r41[8] = (int) j25;
        long j26 = (j25 >> 32) + (((r40[9] & 4294967295L) + j3) - j5) + j13;
        r41[9] = (int) j26;
        long j27 = (j26 >> 32) + ((((r40[10] & 4294967295L) + j3) + j4) - j14) + j15;
        r41[10] = (int) j27;
        long j28 = (j27 >> 32) + ((((r40[11] & 4294967295L) + j4) + j5) - j15);
        r41[11] = (int) j28;
        reduce32((int) ((j28 >> 32) + 1), r41);
    }

    public static void reduce32(int r11, int[] r12) {
        long j;
        if (r11 != 0) {
            long j2 = r11 & 4294967295L;
            long j3 = (r12[0] & 4294967295L) + j2 + 0;
            r12[0] = (int) j3;
            long j4 = (j3 >> 32) + ((r12[1] & 4294967295L) - j2);
            r12[1] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (r12[2] & 4294967295L);
                r12[2] = (int) j6;
                j5 = j6 >> 32;
            }
            long j7 = j5 + (r12[3] & 4294967295L) + j2;
            r12[3] = (int) j7;
            long j8 = (j7 >> 32) + (4294967295L & r12[4]) + j2;
            r12[4] = (int) j8;
            j = j8 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || Nat.incAt(12, r12, 5) == 0) && !(r12[11] == -1 && Nat.gte(12, r12, f2335P))) {
            return;
        }
        addPInvTo(r12);
    }

    public static void square(int[] r1, int[] r2) {
        int[] create = Nat.create(24);
        Nat384.square(r1, create);
        reduce(create, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] create = Nat.create(24);
        Nat384.square(r1, create);
        while (true) {
            reduce(create, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            Nat384.square(r3, create);
        }
    }

    private static void subPInvFrom(int[] r12) {
        long j = (r12[0] & 4294967295L) - 1;
        r12[0] = (int) j;
        long j2 = (j >> 32) + (r12[1] & 4294967295L) + 1;
        r12[1] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (r12[2] & 4294967295L);
            r12[2] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + ((r12[3] & 4294967295L) - 1);
        r12[3] = (int) j5;
        long j6 = (j5 >> 32) + ((4294967295L & r12[4]) - 1);
        r12[4] = (int) j6;
        if ((j6 >> 32) != 0) {
            Nat.decAt(12, r12, 5);
        }
    }

    public static void subtract(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(12, r1, r2, r3) != 0) {
            subPInvFrom(r3);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(24, r1, r2, r3) != 0) {
            int[] r12 = PExtInv;
            if (Nat.subFrom(r12.length, r12, r3) != 0) {
                Nat.decAt(24, r3, r12.length);
            }
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(12, r2, 0, r3) != 0 || (r3[11] == -1 && Nat.gte(12, r3, f2335P))) {
            addPInvTo(r3);
        }
    }
}
