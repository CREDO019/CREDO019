package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a */
    private BigInteger f2130a;

    /* renamed from: p */
    private BigInteger f2131p;

    /* renamed from: q */
    private BigInteger f2132q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2131p = bigInteger;
        this.f2132q = bigInteger2;
        this.f2130a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f2130a = bigInteger3;
        this.f2131p = bigInteger;
        this.f2132q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410Parameters) {
            GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
            return gOST3410Parameters.getP().equals(this.f2131p) && gOST3410Parameters.getQ().equals(this.f2132q) && gOST3410Parameters.getA().equals(this.f2130a);
        }
        return false;
    }

    public BigInteger getA() {
        return this.f2130a;
    }

    public BigInteger getP() {
        return this.f2131p;
    }

    public BigInteger getQ() {
        return this.f2132q;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f2131p.hashCode() ^ this.f2132q.hashCode()) ^ this.f2130a.hashCode();
    }
}
