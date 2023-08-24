package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes5.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f2241a;

    /* renamed from: p */
    private BigInteger f2242p;

    /* renamed from: q */
    private BigInteger f2243q;

    /* renamed from: x */
    private BigInteger f2244x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f2244x = bigInteger;
        this.f2242p = bigInteger2;
        this.f2243q = bigInteger3;
        this.f2241a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f2241a;
    }

    public BigInteger getP() {
        return this.f2242p;
    }

    public BigInteger getQ() {
        return this.f2243q;
    }

    public BigInteger getX() {
        return this.f2244x;
    }
}
