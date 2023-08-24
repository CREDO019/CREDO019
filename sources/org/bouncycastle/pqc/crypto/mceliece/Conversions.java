package org.bouncycastle.pqc.crypto.mceliece;

import java.math.BigInteger;
import org.bouncycastle.pqc.math.linearalgebra.BigIntUtils;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

/* loaded from: classes3.dex */
final class Conversions {
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private Conversions() {
    }

    public static byte[] decode(int r7, int r8, GF2Vector gF2Vector) {
        if (gF2Vector.getLength() == r7 && gF2Vector.getHammingWeight() == r8) {
            int[] vecArray = gF2Vector.getVecArray();
            BigInteger binomial = IntegerFunctions.binomial(r7, r8);
            BigInteger bigInteger = ZERO;
            int r3 = r7;
            for (int r2 = 0; r2 < r7; r2++) {
                binomial = binomial.multiply(BigInteger.valueOf(r3 - r8)).divide(BigInteger.valueOf(r3));
                r3--;
                if ((vecArray[r2 >> 5] & (1 << (r2 & 31))) != 0) {
                    bigInteger = bigInteger.add(binomial);
                    r8--;
                    binomial = r3 == r8 ? ONE : binomial.multiply(BigInteger.valueOf(r8 + 1)).divide(BigInteger.valueOf(r3 - r8));
                }
            }
            return BigIntUtils.toMinimalByteArray(bigInteger);
        }
        throw new IllegalArgumentException("vector has wrong length or hamming weight");
    }

    public static GF2Vector encode(int r6, int r7, byte[] bArr) {
        if (r6 >= r7) {
            BigInteger binomial = IntegerFunctions.binomial(r6, r7);
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.compareTo(binomial) < 0) {
                GF2Vector gF2Vector = new GF2Vector(r6);
                int r3 = r6;
                for (int r2 = 0; r2 < r6; r2++) {
                    binomial = binomial.multiply(BigInteger.valueOf(r3 - r7)).divide(BigInteger.valueOf(r3));
                    r3--;
                    if (binomial.compareTo(bigInteger) <= 0) {
                        gF2Vector.setBit(r2);
                        bigInteger = bigInteger.subtract(binomial);
                        r7--;
                        binomial = r3 == r7 ? ONE : binomial.multiply(BigInteger.valueOf(r7 + 1)).divide(BigInteger.valueOf(r3 - r7));
                    }
                }
                return gF2Vector;
            }
            throw new IllegalArgumentException("Encoded number too large.");
        }
        throw new IllegalArgumentException("n < t");
    }

    public static byte[] signConversion(int r10, int r11, byte[] bArr) {
        if (r10 >= r11) {
            BigInteger binomial = IntegerFunctions.binomial(r10, r11);
            int bitLength = binomial.bitLength() - 1;
            int r3 = bitLength >> 3;
            int r1 = bitLength & 7;
            int r4 = 8;
            if (r1 == 0) {
                r3--;
                r1 = 8;
            }
            int r5 = r10 >> 3;
            int r6 = r10 & 7;
            if (r6 == 0) {
                r5--;
            } else {
                r4 = r6;
            }
            int r62 = r5 + 1;
            byte[] bArr2 = new byte[r62];
            if (bArr.length < r62) {
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                for (int length = bArr.length; length < r62; length++) {
                    bArr2[length] = 0;
                }
            } else {
                System.arraycopy(bArr, 0, bArr2, 0, r5);
                bArr2[r5] = (byte) (bArr[r5] & ((1 << r4) - 1));
            }
            BigInteger bigInteger = ZERO;
            int r52 = r10;
            for (int r42 = 0; r42 < r10; r42++) {
                binomial = binomial.multiply(new BigInteger(Integer.toString(r52 - r11))).divide(new BigInteger(Integer.toString(r52)));
                r52--;
                if (((byte) (bArr2[r42 >>> 3] & (1 << (r42 & 7)))) != 0) {
                    bigInteger = bigInteger.add(binomial);
                    r11--;
                    binomial = r52 == r11 ? ONE : binomial.multiply(new BigInteger(Integer.toString(r11 + 1))).divide(new BigInteger(Integer.toString(r52 - r11)));
                }
            }
            int r102 = r3 + 1;
            byte[] bArr3 = new byte[r102];
            byte[] byteArray = bigInteger.toByteArray();
            if (byteArray.length < r102) {
                System.arraycopy(byteArray, 0, bArr3, 0, byteArray.length);
                for (int length2 = byteArray.length; length2 < r102; length2++) {
                    bArr3[length2] = 0;
                }
            } else {
                System.arraycopy(byteArray, 0, bArr3, 0, r3);
                bArr3[r3] = (byte) (((1 << r1) - 1) & byteArray[r3]);
            }
            return bArr3;
        }
        throw new IllegalArgumentException("n < t");
    }
}
