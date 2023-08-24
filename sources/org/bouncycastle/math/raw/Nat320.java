package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class Nat320 {
    public static void copy64(long[] jArr, int r4, long[] jArr2, int r6) {
        jArr2[r6 + 0] = jArr[r4 + 0];
        jArr2[r6 + 1] = jArr[r4 + 1];
        jArr2[r6 + 2] = jArr[r4 + 2];
        jArr2[r6 + 3] = jArr[r4 + 3];
        jArr2[r6 + 4] = jArr[r4 + 4];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
    }

    public static long[] create64() {
        return new long[5];
    }

    public static long[] createExt64() {
        return new long[10];
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int r0 = 4; r0 >= 0; r0--) {
            if (jArr[r0] != jArr2[r0]) {
                return false;
            }
        }
        return true;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 320) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int r1 = 0; r1 < 5; r1++) {
            create64[r1] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int r2 = 1; r2 < 5; r2++) {
            if (jArr[r2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int r1 = 0; r1 < 5; r1++) {
            if (jArr[r1] != 0) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[40];
        for (int r1 = 0; r1 < 5; r1++) {
            long j = jArr[r1];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (4 - r1) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
