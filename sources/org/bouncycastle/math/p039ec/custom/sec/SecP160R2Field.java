package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP160R2Field */
/* loaded from: classes5.dex */
public class SecP160R2Field {

    /* renamed from: P4 */
    private static final int f2297P4 = -1;
    private static final int PExt9 = -1;
    private static final int PInv33 = 21389;

    /* renamed from: P */
    static final int[] f2296P = {-21389, -2, -1, -1, -1};
    private static final int[] PExt = {457489321, 42778, 1, 0, 0, -42778, -3, -1, -1, -1};
    private static final int[] PExtInv = {-457489321, -42779, -2, -1, -1, 42777, 2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat160.add(r0, r1, r2) != 0 || (r2[4] == -1 && Nat160.gte(r2, f2296P))) {
            Nat.add33To(5, PInv33, r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(10, r1, r2, r3) != 0 || (r3[9] == -1 && Nat.gte(10, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(10, r3, r12.length);
            }
        }
    }

    public static void addOne(int[] r2, int[] r3) {
        if (Nat.inc(5, r2, r3) != 0 || (r3[4] == -1 && Nat160.gte(r3, f2296P))) {
            Nat.add33To(5, PInv33, r3);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat160.fromBigInteger(bigInteger);
        if (fromBigInteger[4] == -1) {
            int[] r0 = f2296P;
            if (Nat160.gte(fromBigInteger, r0)) {
                Nat160.subFrom(r0, fromBigInteger);
            }
        }
        return fromBigInteger;
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(5, r3, 0, r4);
        } else {
            Nat.shiftDownBit(5, r4, Nat160.add(r3, f2296P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2296P, r1, r2);
    }

    public static int isZero(int[] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 < 5; r0++) {
            r1 |= r3[r0];
        }
        return (((r1 >>> 1) | (r1 & 1)) - 1) >> 31;
    }

    public static void multiply(int[] r1, int[] r2, int[] r3) {
        int[] createExt = Nat160.createExt();
        Nat160.mul(r1, r2, createExt);
        reduce(createExt, r3);
    }

    public static void multiplyAddToExt(int[] r1, int[] r2, int[] r3) {
        if (Nat160.mulAddTo(r1, r2, r3) != 0 || (r3[9] == -1 && Nat.gte(10, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(10, r3, r12.length);
            }
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat160.sub(f2296P, r1, r2);
            return;
        }
        int[] r12 = f2296P;
        Nat160.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[20];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 5);
        } while (Nat.lessThan(5, r4, f2296P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r7, int[] r8) {
        if (Nat160.mul33DWordAdd(PInv33, Nat160.mul33Add(PInv33, r7, 5, r7, 0, r8, 0), r8, 0) != 0 || (r8[4] == -1 && Nat160.gte(r8, f2296P))) {
            Nat.add33To(5, PInv33, r8);
        }
    }

    public static void reduce32(int r2, int[] r3) {
        if ((r2 == 0 || Nat160.mul33WordAdd(PInv33, r2, r3, 0) == 0) && !(r3[4] == -1 && Nat160.gte(r3, f2296P))) {
            return;
        }
        Nat.add33To(5, PInv33, r3);
    }

    public static void square(int[] r1, int[] r2) {
        int[] createExt = Nat160.createExt();
        Nat160.square(r1, createExt);
        reduce(createExt, r2);
    }

    public static void squareN(int[] r1, int r2, int[] r3) {
        int[] createExt = Nat160.createExt();
        Nat160.square(r1, createExt);
        while (true) {
            reduce(createExt, r3);
            r2--;
            if (r2 <= 0) {
                return;
            }
            Nat160.square(r3, createExt);
        }
    }

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat160.sub(r0, r1, r2) != 0) {
            Nat.sub33From(5, PInv33, r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(10, r1, r2, r3) != 0) {
            int[] r12 = PExtInv;
            if (Nat.subFrom(r12.length, r12, r3) != 0) {
                Nat.decAt(10, r3, r12.length);
            }
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(5, r2, 0, r3) != 0 || (r3[4] == -1 && Nat160.gte(r3, f2296P))) {
            Nat.add33To(5, PInv33, r3);
        }
    }
}
