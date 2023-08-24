package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Properties;

/* loaded from: classes5.dex */
public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;

    /* renamed from: g */
    private BigInteger f2105g;

    /* renamed from: j */
    private BigInteger f2106j;

    /* renamed from: l */
    private int f2107l;

    /* renamed from: m */
    private int f2108m;

    /* renamed from: p */
    private BigInteger f2109p;

    /* renamed from: q */
    private BigInteger f2110q;
    private DHValidationParameters validation;

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int r12) {
        this(bigInteger, bigInteger2, bigInteger3, getDefaultMParam(r12), r12, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int r12, int r13) {
        this(bigInteger, bigInteger2, bigInteger3, r12, r13, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int r5, int r6, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        if (r6 != 0) {
            if (r6 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (r6 < r5) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (r5 > bigInteger.bitLength() && !Properties.isOverrideSet("org.bouncycastle.dh.allow_unsafe_p_value")) {
            throw new IllegalArgumentException("unsafe p value so small specific l required");
        }
        this.f2105g = bigInteger2;
        this.f2109p = bigInteger;
        this.f2110q = bigInteger3;
        this.f2108m = r5;
        this.f2107l = r6;
        this.f2106j = bigInteger4;
        this.validation = dHValidationParameters;
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        this(bigInteger, bigInteger2, bigInteger3, DEFAULT_MINIMUM_LENGTH, 0, bigInteger4, dHValidationParameters);
    }

    private static int getDefaultMParam(int r1) {
        return (r1 != 0 && r1 < DEFAULT_MINIMUM_LENGTH) ? r1 : DEFAULT_MINIMUM_LENGTH;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DHParameters) {
            DHParameters dHParameters = (DHParameters) obj;
            if (getQ() != null) {
                if (!getQ().equals(dHParameters.getQ())) {
                    return false;
                }
            } else if (dHParameters.getQ() != null) {
                return false;
            }
            return dHParameters.getP().equals(this.f2109p) && dHParameters.getG().equals(this.f2105g);
        }
        return false;
    }

    public BigInteger getG() {
        return this.f2105g;
    }

    public BigInteger getJ() {
        return this.f2106j;
    }

    public int getL() {
        return this.f2107l;
    }

    public int getM() {
        return this.f2108m;
    }

    public BigInteger getP() {
        return this.f2109p;
    }

    public BigInteger getQ() {
        return this.f2110q;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) ^ (getQ() != null ? getQ().hashCode() : 0);
    }
}
