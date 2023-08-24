package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP192R1Field */
/* loaded from: classes5.dex */
public class SecP192R1Field {

    /* renamed from: M */
    private static final long f2306M = 4294967295L;

    /* renamed from: P5 */
    private static final int f2308P5 = -1;
    private static final int PExt11 = -1;

    /* renamed from: P */
    static final int[] f2307P = {-1, -1, -2, -1, -1, -1};
    private static final int[] PExt = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};
    private static final int[] PExtInv = {-1, -1, -3, -1, -2, -1, 1, 0, 2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat192.add(r0, r1, r2) != 0 || (r2[5] == -1 && Nat192.gte(r2, f2307P))) {
            addPInvTo(r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(12, r1, r2, r3) != 0 || (r3[11] == -1 && Nat.gte(12, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(12, r3, r12.length);
            }
        }
    }

    public static void addOne(int[] r1, int[] r2) {
        if (Nat.inc(6, r1, r2) != 0 || (r2[5] == -1 && Nat192.gte(r2, f2307P))) {
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
        long j4 = j2 + (4294967295L & r12[2]) + 1;
        r12[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            Nat.incAt(6, r12, 3);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat192.fromBigInteger(bigInteger);
        if (fromBigInteger[5] == -1) {
            int[] r0 = f2307P;
            if (Nat192.gte(fromBigInteger, r0)) {
                Nat192.subFrom(r0, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(6, r3, 0, r4);
        } else {
            Nat.shiftDownBit(6, r4, Nat192.add(r3, f2307P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2307P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 6; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] createExt = Nat192.createExt();
        Nat192.mul(r1, r2, createExt);
        reduce(createExt, r3);
    }

    public static void multiplyAddToExt(int[] r1, int[] r2, int[] r3) {
        if (Nat192.mulAddTo(r1, r2, r3) != 0 || (r3[11] == -1 && Nat.gte(12, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(12, r3, r12.length);
            }
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat192.sub(f2307P, r1, r2);
            return;
        }
        int[] r12 = f2307P;
        Nat192.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[24];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 6);
        } while (Nat.lessThan(6, r4, f2307P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r26, int[] r27) {
        long j = r26[6] & 4294967295L;
        long j2 = r26[7] & 4294967295L;
        long j3 = (r26[10] & 4294967295L) + j;
        long j4 = (r26[11] & 4294967295L) + j2;
        long j5 = (r26[0] & 4294967295L) + j3 + 0;
        int r1 = (int) j5;
        long j6 = (j5 >> 32) + (r26[1] & 4294967295L) + j4;
        r27[1] = (int) j6;
        long j7 = j3 + (r26[8] & 4294967295L);
        long j8 = j4 + (r26[9] & 4294967295L);
        long j9 = (j6 >> 32) + (r26[2] & 4294967295L) + j7;
        long j10 = j9 & 4294967295L;
        long j11 = (j9 >> 32) + (r26[3] & 4294967295L) + j8;
        r27[3] = (int) j11;
        long j12 = (j11 >> 32) + (r26[4] & 4294967295L) + (j7 - j);
        r27[4] = (int) j12;
        long j13 = (j12 >> 32) + (r26[5] & 4294967295L) + (j8 - j2);
        r27[5] = (int) j13;
        long j14 = j13 >> 32;
        long j15 = j10 + j14;
        long j16 = j14 + (r1 & 4294967295L);
        r27[0] = (int) j16;
        long j17 = j16 >> 32;
        if (j17 != 0) {
            long j18 = j17 + (4294967295L & r27[1]);
            r27[1] = (int) j18;
            j15 += j18 >> 32;
        }
        r27[2] = (int) j15;
        if (((j15 >> 32) == 0 || Nat.incAt(6, r27, 3) == 0) && !(r27[5] == -1 && Nat192.gte(r27, f2307P))) {
            return;
        }
        addPInvTo(r27);
    }

    public static void reduce32(int r11, int[] r12) {
        long j;
        if (r11 != 0) {
            long j2 = r11 & 4294967295L;
            long j3 = (r12[0] & 4294967295L) + j2 + 0;
            r12[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (r12[1] & 4294967295L);
                r12[1] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = j4 + (4294967295L & r12[2]) + j2;
            r12[2] = (int) j6;
            j = j6 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || Nat.incAt(6, r12, 3) == 0) && !(r12[5] == -1 && Nat192.gte(r12, f2307P))) {
            return;
        }
        addPInvTo(r12);
    }

    public static void square(int[] r1, int[] r2) {
        int[] createExt = Nat192.createExt();
        Nat192.square(r1, createExt);
        reduce(createExt, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] createExt = Nat192.createExt();
        Nat192.square(r1, createExt);
        while (true) {
            reduce(createExt, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            Nat192.square(r3, createExt);
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
        long j4 = j2 + ((4294967295L & r12[2]) - 1);
        r12[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            Nat.decAt(6, r12, 3);
        }
    }

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat192.sub(r0, r1, r2) != 0) {
            subPInvFrom(r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(12, r1, r2, r3) != 0) {
            int[] r12 = PExtInv;
            if (Nat.subFrom(r12.length, r12, r3) != 0) {
                Nat.decAt(12, r3, r12.length);
            }
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(6, r2, 0, r3) != 0 || (r3[5] == -1 && Nat192.gte(r3, f2307P))) {
            addPInvTo(r3);
        }
    }
}
