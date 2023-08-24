package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat448 {
    public static void copy64(long[] jArr, int r4, long[] jArr2, int r6) {
        jArr2[r6 + 0] = jArr[r4 + 0];
        jArr2[r6 + 1] = jArr[r4 + 1];
        jArr2[r6 + 2] = jArr[r4 + 2];
        jArr2[r6 + 3] = jArr[r4 + 3];
        jArr2[r6 + 4] = jArr[r4 + 4];
        jArr2[r6 + 5] = jArr[r4 + 5];
        jArr2[r6 + 6] = jArr[r4 + 6];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
        jArr2[5] = jArr[5];
        jArr2[6] = jArr[6];
    }

    public static long[] create64() {
        return new long[7];
    }

    public static long[] createExt64() {
        return new long[14];
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int r0 = 6; r0 >= 0; r0--) {
            if (jArr[r0] != jArr2[r0]) {
                return false;
            }
        }
        return true;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 448) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int r1 = 0; r1 < 7; r1++) {
            create64[r1] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int r2 = 1; r2 < 7; r2++) {
            if (jArr[r2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int r1 = 0; r1 < 7; r1++) {
            if (jArr[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] r13, int[] r14, int[] r15) {
        Nat224.mul(r13, r14, r15);
        Nat224.mul(r13, 7, r14, 7, r15, 14);
        int addToEachOther = Nat224.addToEachOther(r15, 7, r15, 14);
        int addTo = addToEachOther + Nat224.addTo(r15, 21, r15, 14, Nat224.addTo(r15, 0, r15, 7, 0) + addToEachOther);
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        boolean z = Nat224.diff(r13, 7, r13, 0, create, 0) != Nat224.diff(r14, 7, r14, 0, create2, 0);
        int[] createExt = Nat224.createExt();
        Nat224.mul(create, create2, createExt);
        Nat.addWordAt(28, addTo + (z ? Nat.addTo(14, createExt, 0, r15, 7) : Nat.subFrom(14, createExt, 0, r15, 7)), r15, 21);
    }

    public static void square(int[] r12, int[] r13) {
        Nat224.square(r12, r13);
        Nat224.square(r12, 7, r13, 14);
        int addToEachOther = Nat224.addToEachOther(r13, 7, r13, 14);
        int addTo = addToEachOther + Nat224.addTo(r13, 21, r13, 14, Nat224.addTo(r13, 0, r13, 7, 0) + addToEachOther);
        int[] create = Nat224.create();
        Nat224.diff(r12, 7, r12, 0, create, 0);
        int[] createExt = Nat224.createExt();
        Nat224.square(create, createExt);
        Nat.addWordAt(28, addTo + Nat.subFrom(14, createExt, 0, r13, 7), r13, 21);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[56];
        for (int r1 = 0; r1 < 7; r1++) {
            long j = jArr[r1];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (6 - r1) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
