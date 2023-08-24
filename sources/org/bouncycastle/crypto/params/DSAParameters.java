package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class DSAParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f2115g;

    /* renamed from: p */
    private BigInteger f2116p;

    /* renamed from: q */
    private BigInteger f2117q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2115g = bigInteger3;
        this.f2116p = bigInteger;
        this.f2117q = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f2115g = bigInteger3;
        this.f2116p = bigInteger;
        this.f2117q = bigInteger2;
        this.validation = dSAValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAParameters) {
            DSAParameters dSAParameters = (DSAParameters) obj;
            return dSAParameters.getP().equals(this.f2116p) && dSAParameters.getQ().equals(this.f2117q) && dSAParameters.getG().equals(this.f2115g);
        }
        return false;
    }

    public BigInteger getG() {
        return this.f2115g;
    }

    public BigInteger getP() {
        return this.f2116p;
    }

    public BigInteger getQ() {
        return this.f2117q;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
