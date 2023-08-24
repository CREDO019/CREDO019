package org.bouncycastle.jcajce.spec;

import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHValidationParameters;

/* loaded from: classes5.dex */
public class DHDomainParameterSpec extends DHParameterSpec {

    /* renamed from: j */
    private final BigInteger f2213j;

    /* renamed from: m */
    private final int f2214m;

    /* renamed from: q */
    private final BigInteger f2215q;
    private DHValidationParameters validationParameters;

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, null, 0);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int r10) {
        this(bigInteger, bigInteger2, bigInteger3, null, r10);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int r12) {
        this(bigInteger, bigInteger2, bigInteger3, bigInteger4, 0, r12);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int r5, int r6) {
        super(bigInteger, bigInteger3, r6);
        this.f2215q = bigInteger2;
        this.f2213j = bigInteger4;
        this.f2214m = r5;
    }

    public DHDomainParameterSpec(DHParameters dHParameters) {
        this(dHParameters.getP(), dHParameters.getQ(), dHParameters.getG(), dHParameters.getJ(), dHParameters.getM(), dHParameters.getL());
        this.validationParameters = dHParameters.getValidationParameters();
    }

    public DHParameters getDomainParameters() {
        return new DHParameters(getP(), getG(), this.f2215q, this.f2214m, getL(), this.f2213j, this.validationParameters);
    }

    public BigInteger getJ() {
        return this.f2213j;
    }

    public int getM() {
        return this.f2214m;
    }

    public BigInteger getQ() {
        return this.f2215q;
    }
}
