package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP128R1Field */
/* loaded from: classes5.dex */
public class SecP128R1Field {

    /* renamed from: M */
    private static final long f2284M = 4294967295L;
    private static final int P3s1 = 2147483646;
    private static final int PExt7s1 = 2147483646;

    /* renamed from: P */
    static final int[] f2285P = {-1, -1, -1, -3};
    private static final int[] PExt = {1, 0, 0, 4, -2, -1, 3, -4};
    private static final int[] PExtInv = {-1, -1, -1, -5, 1, 0, -4, 3};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat128.add(r0, r1, r2) != 0 || ((r2[3] >>> 1) >= 2147483646 && Nat128.gte(r2, f2285P))) {
            addPInvTo(r2);
        }
    }

    public static void addExt(int[] r0, int[] r1, int[] r2) {
        if (Nat256.add(r0, r1, r2) != 0 || ((r2[7] >>> 1) >= 2147483646 && Nat256.gte(r2, PExt))) {
            int[] r02 = PExtInv;
            Nat.addTo(r02.length, r02, r2);
        }
    }

    public static void addOne(int[] r1, int[] r2) {
        if (Nat.inc(4, r1, r2) != 0 || ((r2[3] >>> 1) >= 2147483646 && Nat128.gte(r2, f2285P))) {
            addPInvTo(r2);
        }
    }

    private static void addPInvTo(int[] r8) {
        long j = (r8[0] & 4294967295L) + 1;
        r8[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (r8[1] & 4294967295L);
            r8[1] = (int) j3;
            long j4 = (j3 >> 32) + (r8[2] & 4294967295L);
            r8[2] = (int) j4;
            j2 = j4 >> 32;
        }
        r8[3] = (int) (j2 + (4294967295L & r8[3]) + 2);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat128.fromBigInteger(bigInteger);
        if ((fromBigInteger[3] >>> 1) >= 2147483646) {
            int[] r0 = f2285P;
            if (Nat128.gte(fromBigInteger, r0)) {
                Nat128.subFrom(r0, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(4, r3, 0, r4);
        } else {
            Nat.shiftDownBit(4, r4, Nat128.add(r3, f2285P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2285P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 4; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] createExt = Nat128.createExt();
        Nat128.mul(r1, r2, createExt);
        reduce(createExt, r3);
    }

    public static void multiplyAddToExt(int[] r0, int[] r1, int[] r2) {
        if (Nat128.mulAddTo(r0, r1, r2) != 0 || ((r2[7] >>> 1) >= 2147483646 && Nat256.gte(r2, PExt))) {
            int[] r02 = PExtInv;
            Nat.addTo(r02.length, r02, r2);
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat128.sub(f2285P, r1, r2);
            return;
        }
        int[] r12 = f2285P;
        Nat128.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[16];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 4);
        } while (Nat.lessThan(4, r4, f2285P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r22, int[] r23) {
        long j = r22[7] & 4294967295L;
        long j2 = (r22[3] & 4294967295L) + j;
        long j3 = (r22[6] & 4294967295L) + (j << 1);
        long j4 = (r22[2] & 4294967295L) + j3;
        long j5 = (r22[5] & 4294967295L) + (j3 << 1);
        long j6 = (r22[1] & 4294967295L) + j5;
        long j7 = (r22[4] & 4294967295L) + (j5 << 1);
        long j8 = (r22[0] & 4294967295L) + j7;
        r23[0] = (int) j8;
        long j9 = j6 + (j8 >>> 32);
        r23[1] = (int) j9;
        long j10 = j4 + (j9 >>> 32);
        r23[2] = (int) j10;
        long j11 = j2 + (j7 << 1) + (j10 >>> 32);
        r23[3] = (int) j11;
        reduce32((int) (j11 >>> 32), r23);
    }

    public static void reduce32(int r11, int[] r12) {
        while (r11 != 0) {
            long j = r11 & 4294967295L;
            long j2 = (r12[0] & 4294967295L) + j;
            r12[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (r12[1] & 4294967295L);
                r12[1] = (int) j4;
                long j5 = (j4 >> 32) + (r12[2] & 4294967295L);
                r12[2] = (int) j5;
                j3 = j5 >> 32;
            }
            long j6 = j3 + (4294967295L & r12[3]) + (j << 1);
            r12[3] = (int) j6;
            r11 = (int) (j6 >> 32);
        }
        if ((r12[3] >>> 1) < 2147483646 || !Nat128.gte(r12, f2285P)) {
            return;
        }
        addPInvTo(r12);
    }

    public static void square(int[] r1, int[] r2) {
        int[] createExt = Nat128.createExt();
        Nat128.square(r1, createExt);
        reduce(createExt, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] createExt = Nat128.createExt();
        Nat128.square(r1, createExt);
        while (true) {
            reduce(createExt, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            Nat128.square(r3, createExt);
        }
    }

    private static void subPInvFrom(int[] r8) {
        long j = (r8[0] & 4294967295L) - 1;
        r8[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (r8[1] & 4294967295L);
            r8[1] = (int) j3;
            long j4 = (j3 >> 32) + (r8[2] & 4294967295L);
            r8[2] = (int) j4;
            j2 = j4 >> 32;
        }
        r8[3] = (int) (j2 + ((4294967295L & r8[3]) - 2));
    }

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat128.sub(r0, r1, r2) != 0) {
            subPInvFrom(r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(10, r1, r2, r3) != 0) {
            int[] r12 = PExtInv;
            Nat.subFrom(r12.length, r12, r3);
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(4, r2, 0, r3) != 0 || ((r3[3] >>> 1) >= 2147483646 && Nat128.gte(r3, f2285P))) {
            addPInvTo(r3);
        }
    }
}
