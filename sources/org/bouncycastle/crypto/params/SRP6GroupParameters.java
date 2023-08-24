package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class SRP6GroupParameters {

    /* renamed from: N */
    private BigInteger f2155N;

    /* renamed from: g */
    private BigInteger f2156g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f2155N = bigInteger;
        this.f2156g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f2156g;
    }

    public BigInteger getN() {
        return this.f2155N;
    }
}
