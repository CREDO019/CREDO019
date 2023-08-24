package org.bouncycastle.math.p039ec.custom.djb;

import java.math.BigInteger;
import java.security.SecureRandom;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Pack;

/* renamed from: org.bouncycastle.math.ec.custom.djb.Curve25519Field */
/* loaded from: classes5.dex */
public class Curve25519Field {

    /* renamed from: M */
    private static final long f2273M = 4294967295L;

    /* renamed from: P7 */
    private static final int f2275P7 = Integer.MAX_VALUE;
    private static final int PInv = 19;

    /* renamed from: P */
    static final int[] f2274P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] PExt = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};

    public static void add(int[] r0, int[] r1, int[] r2) {
        Nat256.add(r0, r1, r2);
        if (Nat256.gte(r2, f2274P)) {
            subPFrom(r2);
        }
    }

    public static void addExt(int[] r1, int[] r2, int[] r3) {
        Nat.add(16, r1, r2, r3);
        if (Nat.gte(16, r3, PExt)) {
            subPExtFrom(r3);
        }
    }

    public static void addOne(int[] r1, int[] r2) {
        Nat.inc(8, r1, r2);
        if (Nat256.gte(r2, f2274P)) {
            subPFrom(r2);
        }
    }

    private static int addPExtTo(int[] r14) {
        int[] r5 = PExt;
        long j = (r14[0] & 4294967295L) + (r5[0] & 4294967295L);
        r14[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.incAt(8, r14, 1);
        }
        long j3 = j2 + ((r14[8] & 4294967295L) - 19);
        r14[8] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            j4 = Nat.decAt(15, r14, 9);
        }
        long j5 = j4 + (r14[15] & 4294967295L) + (4294967295L & (r5[15] + 1));
        r14[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    private static int addPTo(int[] r9) {
        long j = (r9[0] & 4294967295L) - 19;
        r9[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.decAt(7, r9, 1);
        }
        long j3 = j2 + (4294967295L & r9[7]) + 2147483648L;
        r9[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] fromBigInteger = Nat256.fromBigInteger(bigInteger);
        while (true) {
            int[] r0 = f2274P;
            if (!Nat256.gte(fromBigInteger, r0)) {
                return fromBigInteger;
            }
            Nat256.subFrom(r0, fromBigInteger);
        }
    }

    public static void half(int[] r3, int[] r4) {
        if ((r3[0] & 1) == 0) {
            Nat.shiftDownBit(8, r3, 0, r4);
            return;
        }
        Nat256.add(r3, f2274P, r4);
        Nat.shiftDownBit(8, r4, 0);
    }

    public static void inv(int[] r1, int[] r2) {
        Mod.checkedModOddInverse(f2274P, r1, r2);
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

    public static void multiplyAddToExt(int[] r0, int[] r1, int[] r2) {
        Nat256.mulAddTo(r0, r1, r2);
        if (Nat.gte(16, r2, PExt)) {
            subPExtFrom(r2);
        }
    }

    public static void negate(int[] r1, int[] r2) {
        if (isZero(r1) == 0) {
            Nat256.sub(f2274P, r1, r2);
            return;
        }
        int[] r12 = f2274P;
        Nat256.sub(r12, r12, r2);
    }

    public static void random(SecureRandom secureRandom, int[] r6) {
        byte[] bArr = new byte[32];
        do {
            secureRandom.nextBytes(bArr);
            Pack.littleEndianToInt(bArr, 0, r6, 0, 8);
            r6[7] = r6[7] & Integer.MAX_VALUE;
        } while (Nat.lessThan(8, r6, f2274P) == 0);
    }

    public static void randomMult(SecureRandom secureRandom, int[] r2) {
        do {
            random(secureRandom, r2);
        } while (isZero(r2) != 0);
    }

    public static void reduce(int[] r8, int[] r9) {
        int r7 = r8[7];
        Nat.shiftUpBit(8, r8, 8, r7, r9, 0);
        int r2 = r9[7];
        r9[7] = (r2 & Integer.MAX_VALUE) + Nat.addWordTo(7, ((Nat256.mulByWordAddTo(19, r8, r9) << 1) + ((r2 >>> 31) - (r7 >>> 31))) * 19, r9);
        if (Nat256.gte(r9, f2274P)) {
            subPFrom(r9);
        }
    }

    public static void reduce27(int r3, int[] r4) {
        int r1 = r4[7];
        r4[7] = (r1 & Integer.MAX_VALUE) + Nat.addWordTo(7, ((r3 << 1) | (r1 >>> 31)) * 19, r4);
        if (Nat256.gte(r4, f2274P)) {
            subPFrom(r4);
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

    private static int subPExtFrom(int[] r14) {
        int[] r5 = PExt;
        long j = (r14[0] & 4294967295L) - (r5[0] & 4294967295L);
        r14[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.decAt(8, r14, 1);
        }
        long j3 = j2 + (r14[8] & 4294967295L) + 19;
        r14[8] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            j4 = Nat.incAt(15, r14, 9);
        }
        long j5 = j4 + ((r14[15] & 4294967295L) - (4294967295L & (r5[15] + 1)));
        r14[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    private static int subPFrom(int[] r9) {
        long j = (r9[0] & 4294967295L) + 19;
        r9[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            j2 = Nat.incAt(7, r9, 1);
        }
        long j3 = j2 + ((4294967295L & r9[7]) - 2147483648L);
        r9[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    public static void subtract(int[] r0, int[] r1, int[] r2) {
        if (Nat256.sub(r0, r1, r2) != 0) {
            addPTo(r2);
        }
    }

    public static void subtractExt(int[] r1, int[] r2, int[] r3) {
        if (Nat.sub(16, r1, r2, r3) != 0) {
            addPExtTo(r3);
        }
    }

    public static void twice(int[] r2, int[] r3) {
        Nat.shiftUpBit(8, r2, 0, r3);
        if (Nat256.gte(r3, f2274P)) {
            subPFrom(r3);
        }
    }
}
