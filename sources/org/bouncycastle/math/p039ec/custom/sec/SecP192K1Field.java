package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP192K1Field */
/* loaded from: classes5.dex */
public class SecP192K1Field {

    /* renamed from: P5 */
    private static final int f2302P5 = -1;
    private static final int PExt11 = -1;
    private static final int PInv33 = 4553;

    /* renamed from: P */
    static final int[] f2301P = {-4553, -2, -1, -1, -1, -1};
    private static final int[] PExt = {20729809, 9106, 1, 0, 0, 0, -9106, -3, -1, -1, -1, -1};
    private static final int[] PExtInv = {-20729809, -9107, -2, -1, -1, -1, 9105, 2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat192.add(r0, r1, r2) != 0 || (r2[5] == -1 && Nat192.gte(r2, f2301P))) {
            Nat.add33To(6, PInv33, r2);
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

    public static void addOne(int[] r2, int[] r3) {
        if (Nat.inc(6, r2, r3) != 0 || (r3[5] == -1 && Nat192.gte(r3, f2301P))) {
            Nat.add33To(6, PInv33, r3);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat192.fromBigInteger(bigInteger);
        if (fromBigInteger[5] == -1) {
            int[] r0 = f2301P;
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
            Nat.shiftDownBit(6, r4, Nat192.add(r3, f2301P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2301P, r1, r2);
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
            Nat192.sub(f2301P, r1, r2);
            return;
        }
        int[] r12 = f2301P;
        Nat192.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[24];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 6);
        } while (Nat.lessThan(6, r4, f2301P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r7, int[] r8) {
        if (Nat192.mul33DWordAdd(PInv33, Nat192.mul33Add(PInv33, r7, 6, r7, 0, r8, 0), r8, 0) != 0 || (r8[5] == -1 && Nat192.gte(r8, f2301P))) {
            Nat.add33To(6, PInv33, r8);
        }
    }

    public static void reduce32(int r2, int[] r3) {
        if ((r2 == 0 || Nat192.mul33WordAdd(PInv33, r2, r3, 0) == 0) && !(r3[5] == -1 && Nat192.gte(r3, f2301P))) {
            return;
        }
        Nat.add33To(6, PInv33, r3);
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

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat192.sub(r0, r1, r2) != 0) {
            Nat.sub33From(6, PInv33, r2);
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
        if (Nat.shiftUpBit(6, r2, 0, r3) != 0 || (r3[5] == -1 && Nat192.gte(r3, f2301P))) {
            Nat.add33To(6, PInv33, r3);
        }
    }
}
