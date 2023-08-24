package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes5.dex */
public class GOST3410PublicKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f2248a;

    /* renamed from: p */
    private BigInteger f2249p;

    /* renamed from: q */
    private BigInteger f2250q;

    /* renamed from: y */
    private BigInteger f2251y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f2251y = bigInteger;
        this.f2249p = bigInteger2;
        this.f2250q = bigInteger3;
        this.f2248a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f2248a;
    }

    public BigInteger getP() {
        return this.f2249p;
    }

    public BigInteger getQ() {
        return this.f2250q;
    }

    public BigInteger getY() {
        return this.f2251y;
    }
}
