package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat512;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP521R1Field */
/* loaded from: classes5.dex */
public class SecP521R1Field {

    /* renamed from: P */
    static final int[] f2339P = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 511};
    private static final int P16 = 511;

    public static void add(int[] r2, int[] r3, int[] r4) {
        int add = Nat.add(16, r2, r3, r4) + r2[16] + r3[16];
        if (add > 511 || (add == 511 && Nat.m23eq(16, r4, f2339P))) {
            add = (add + Nat.inc(16, r4)) & 511;
        }
        r4[16] = add;
    }

    public static void addOne(int[] r3, int[] r4) {
        int inc = Nat.inc(16, r3, r4) + r3[16];
        if (inc > 511 || (inc == 511 && Nat.m23eq(16, r4, f2339P))) {
            inc = (inc + Nat.inc(16, r4)) & 511;
        }
        r4[16] = inc;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat.fromBigInteger(521, bigInteger);
        if (Nat.m23eq(17, fromBigInteger, f2339P)) {
            Nat.zero(17, fromBigInteger);
        }
        return fromBigInteger;
    }

    public static void half(int[] r2, int[] r3) {
        int r1 = r2[16];
        r3[16] = (Nat.shiftDownBit(16, r2, r1, r3) >>> 23) | (r1 >>> 1);
    }

    protected static void implMultiply(int[] r9, int[] r10, int[] r11) {
        Nat512.mul(r9, r10, r11);
        int r8 = r9[16];
        int r0 = r10[16];
        r11[32] = Nat.mul31BothAdd(16, r8, r10, r0, r9, r11, 16) + (r8 * r0);
    }

    protected static void implSquare(int[] r7, int[] r8) {
        Nat512.square(r7, r8);
        int r0 = r7[16];
        r8[32] = Nat.mulWordAddTo(16, r0 << 1, r7, 0, r8, 16) + (r0 * r0);
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2339P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 17; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] create = Nat.create(33);
        implMultiply(r1, r2, create);
        reduce(create, r3);
    }

    public static void negate(int[] r2, int[] r3) {
        if (isZero(r2) == 0) {
            Nat.sub(17, f2339P, r2, r3);
            return;
        }
        int[] r22 = f2339P;
        Nat.sub(17, r22, r22, r3);
    }

    public static void random(SecureRandom secureRandom, int[] r5) {
        byte[] bArr = new byte[68];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r5, 0, 17);
            r5[16] = r5[16] & 511;
        } while (Nat.lessThan(17, r5, f2339P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r8, int[] r9) {
        int r0 = r8[32];
        int shiftDownBits = (Nat.shiftDownBits(16, r8, 16, 9, r0, r9, 0) >>> 23) + (r0 >>> 9) + Nat.addTo(16, r8, r9);
        if (shiftDownBits > 511 || (shiftDownBits == 511 && Nat.m23eq(16, r9, f2339P))) {
            shiftDownBits = (shiftDownBits + Nat.inc(16, r9)) & 511;
        }
        r9[16] = shiftDownBits;
    }

    public static void reduce23(int[] r4) {
        int r1 = r4[16];
        int addWordTo = Nat.addWordTo(16, r1 >>> 9, r4) + (r1 & 511);
        if (addWordTo > 511 || (addWordTo == 511 && Nat.m23eq(16, r4, f2339P))) {
            addWordTo = (addWordTo + Nat.inc(16, r4)) & 511;
        }
        r4[16] = addWordTo;
    }

    public static void square(int[] r1, int[] r2) {
        int[] create = Nat.create(33);
        implSquare(r1, create);
        reduce(create, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] create = Nat.create(33);
        implSquare(r1, create);
        while (true) {
            reduce(create, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            implSquare(r3, create);
        }
    }

    public static void subtract(int[] r2, int[] r3, int[] r4) {
        int sub = (Nat.sub(16, r2, r3, r4) + r2[16]) - r3[16];
        if (sub < 0) {
            sub = (sub + Nat.dec(16, r4)) & 511;
        }
        r4[16] = sub;
    }

    public static void twice(int[] r3, int[] r4) {
        int r1 = r3[16];
        r4[16] = (Nat.shiftUpBit(16, r3, r1 << 23, r4) | (r1 << 1)) & 511;
    }
}
