package org.bouncycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a */
    private BigInteger f2245a;

    /* renamed from: p */
    private BigInteger f2246p;

    /* renamed from: q */
    private BigInteger f2247q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2246p = bigInteger;
        this.f2247q = bigInteger2;
        this.f2245a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410PublicKeyParameterSetSpec) {
            GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
            return this.f2245a.equals(gOST3410PublicKeyParameterSetSpec.f2245a) && this.f2246p.equals(gOST3410PublicKeyParameterSetSpec.f2246p) && this.f2247q.equals(gOST3410PublicKeyParameterSetSpec.f2247q);
        }
        return false;
    }

    public BigInteger getA() {
        return this.f2245a;
    }

    public BigInteger getP() {
        return this.f2246p;
    }

    public BigInteger getQ() {
        return this.f2247q;
    }

    public int hashCode() {
        return (this.f2245a.hashCode() ^ this.f2246p.hashCode()) ^ this.f2247q.hashCode();
    }
}
