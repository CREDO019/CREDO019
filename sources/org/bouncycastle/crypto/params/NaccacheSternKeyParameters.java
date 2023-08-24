package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: g */
    private BigInteger f2146g;
    int lowerSigmaBound;

    /* renamed from: n */
    private BigInteger f2147n;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int r4) {
        super(z);
        this.f2146g = bigInteger;
        this.f2147n = bigInteger2;
        this.lowerSigmaBound = r4;
    }

    public BigInteger getG() {
        return this.f2146g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.f2147n;
    }
}
