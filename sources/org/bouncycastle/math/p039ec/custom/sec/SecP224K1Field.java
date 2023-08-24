package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP224K1Field */
/* loaded from: classes5.dex */
public class SecP224K1Field {

    /* renamed from: P6 */
    private static final int f2313P6 = -1;
    private static final int PExt13 = -1;
    private static final int PInv33 = 6803;

    /* renamed from: P */
    static final int[] f2312P = {-6803, -2, -1, -1, -1, -1, -1};
    private static final int[] PExt = {46280809, 13606, 1, 0, 0, 0, 0, -13606, -3, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = {-46280809, -13607, -2, -1, -1, -1, -1, 13605, 2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat224.add(r0, r1, r2) != 0 || (r2[6] == -1 && Nat224.gte(r2, f2312P))) {
            Nat.add33To(7, PInv33, r2);
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

    public static void addOne(int[] r2, int[] r3) {
        if (Nat.inc(7, r2, r3) != 0 || (r3[6] == -1 && Nat224.gte(r3, f2312P))) {
            Nat.add33To(7, PInv33, r3);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat224.fromBigInteger(bigInteger);
        if (fromBigInteger[6] == -1 && Nat224.gte(fromBigInteger, f2312P)) {
            Nat.add33To(7, PInv33, fromBigInteger);
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(7, r3, 0, r4);
        } else {
            Nat.shiftDownBit(7, r4, Nat224.add(r3, f2312P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2312P, r1, r2);
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
            Nat224.sub(f2312P, r1, r2);
            return;
        }
        int[] r12 = f2312P;
        Nat224.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[28];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 7);
        } while (Nat.lessThan(7, r4, f2312P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r7, int[] r8) {
        if (Nat224.mul33DWordAdd(PInv33, Nat224.mul33Add(PInv33, r7, 7, r7, 0, r8, 0), r8, 0) != 0 || (r8[6] == -1 && Nat224.gte(r8, f2312P))) {
            Nat.add33To(7, PInv33, r8);
        }
    }

    public static void reduce32(int r2, int[] r3) {
        if ((r2 == 0 || Nat224.mul33WordAdd(PInv33, r2, r3, 0) == 0) && !(r3[6] == -1 && Nat224.gte(r3, f2312P))) {
            return;
        }
        Nat.add33To(7, PInv33, r3);
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

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat224.sub(r0, r1, r2) != 0) {
            Nat.sub33From(7, PInv33, r2);
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
        if (Nat.shiftUpBit(7, r2, 0, r3) != 0 || (r3[6] == -1 && Nat224.gte(r3, f2312P))) {
            Nat.add33To(7, PInv33, r3);
        }
    }
}
