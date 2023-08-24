package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes5.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: g */
    private BigInteger f2237g;

    /* renamed from: p */
    private BigInteger f2238p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f2238p = bigInteger;
        this.f2237g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f2237g;
    }

    public BigInteger getP() {
        return this.f2238p;
    }
}
