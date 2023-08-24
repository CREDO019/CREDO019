package org.bouncycastle.math.field;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public abstract class FiniteFields {
    static final FiniteField GF_2 = new PrimeField(BigInteger.valueOf(2));
    static final FiniteField GF_3 = new PrimeField(BigInteger.valueOf(3));

    public static PolynomialExtensionField getBinaryExtensionField(int[] r3) {
        if (r3[0] == 0) {
            for (int r0 = 1; r0 < r3.length; r0++) {
                if (r3[r0] <= r3[r0 - 1]) {
                    throw new IllegalArgumentException("Polynomial exponents must be monotonically increasing");
                }
            }
            return new GenericPolynomialExtensionField(GF_2, new GF2Polynomial(r3));
        }
        throw new IllegalArgumentException("Irreducible polynomials in GF(2) must have constant term");
    }

    public static FiniteField getPrimeField(BigInteger bigInteger) {
        int bitLength = bigInteger.bitLength();
        if (bigInteger.signum() <= 0 || bitLength < 2) {
            throw new IllegalArgumentException("'characteristic' must be >= 2");
        }
        if (bitLength < 3) {
            int intValue = bigInteger.intValue();
            if (intValue == 2) {
                return GF_2;
            }
            if (intValue == 3) {
                return GF_3;
            }
        }
        return new PrimeField(bigInteger);
    }
}
