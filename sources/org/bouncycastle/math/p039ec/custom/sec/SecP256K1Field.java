package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP256K1Field */
/* loaded from: classes5.dex */
public class SecP256K1Field {

    /* renamed from: P7 */
    private static final int f2324P7 = -1;
    private static final int PExt15 = -1;
    private static final int PInv33 = 977;

    /* renamed from: P */
    static final int[] f2323P = {-977, -2, -1, -1, -1, -1, -1, -1};
    private static final int[] PExt = {954529, 1954, 1, 0, 0, 0, 0, 0, -1954, -3, -1, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = {-954529, -1955, -2, -1, -1, -1, -1, -1, 1953, 2};

    public static void add(int[] r0, int[] r1, int[] r2) {
        if (Nat256.add(r0, r1, r2) != 0 || (r2[7] == -1 && Nat256.gte(r2, f2323P))) {
            Nat.add33To(8, PInv33, r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.add(16, r1, r2, r3) != 0 || (r3[15] == -1 && Nat.gte(16, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(16, r3, r12.length);
            }
        }
    }

    public static void addOne(int[] r2, int[] r3) {
        if (Nat.inc(8, r2, r3) != 0 || (r3[7] == -1 && Nat256.gte(r3, f2323P))) {
            Nat.add33To(8, PInv33, r3);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat256.fromBigInteger(bigInteger);
        if (fromBigInteger[7] == -1) {
            int[] r0 = f2323P;
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
            Nat.shiftDownBit(8, r4, Nat256.add(r3, f2323P, r4));
        }
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2323P, r1, r2);
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
        if (Nat256.mulAddTo(r1, r2, r3) != 0 || (r3[15] == -1 && Nat.gte(16, r3, PExt))) {
            int[] r12 = PExtInv;
            if (Nat.addTo(r12.length, r12, r3) != 0) {
                Nat.incAt(16, r3, r12.length);
            }
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat256.sub(f2323P, r1, r2);
            return;
        }
        int[] r12 = f2323P;
        Nat256.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r4) {
        byte[] bArr = new byte[32];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r4, 0, 8);
        } while (Nat.lessThan(8, r4, f2323P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r7, int[] r8) {
        if (Nat256.mul33DWordAdd(PInv33, Nat256.mul33Add(PInv33, r7, 8, r7, 0, r8, 0), r8, 0) != 0 || (r8[7] == -1 && Nat256.gte(r8, f2323P))) {
            Nat.add33To(8, PInv33, r8);
        }
    }

    public static void reduce32(int r2, int[] r3) {
        if ((r2 == 0 || Nat256.mul33WordAdd(PInv33, r2, r3, 0) == 0) && !(r3[7] == -1 && Nat256.gte(r3, f2323P))) {
            return;
        }
        Nat.add33To(8, PInv33, r3);
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

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat256.sub(r0, r1, r2) != 0) {
            Nat.sub33From(8, PInv33, r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(16, r1, r2, r3) != 0) {
            int[] r12 = PExtInv;
            if (Nat.subFrom(r12.length, r12, r3) != 0) {
                Nat.decAt(16, r3, r12.length);
            }
        }
    }

    public static void twice(int[] r2, int[] r3) {
        if (Nat.shiftUpBit(8, r2, 0, r3) != 0 || (r3[7] == -1 && Nat256.gte(r3, f2323P))) {
            Nat.add33To(8, PInv33, r3);
        }
    }
}
