package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c */
    private BigInteger f2102c;

    /* renamed from: d */
    private BigInteger f2103d;

    /* renamed from: h */
    private BigInteger f2104h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f2102c = bigInteger;
        this.f2103d = bigInteger2;
        this.f2104h = bigInteger3;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPublicKeyParameters) {
            CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
            return cramerShoupPublicKeyParameters.getC().equals(this.f2102c) && cramerShoupPublicKeyParameters.getD().equals(this.f2103d) && cramerShoupPublicKeyParameters.getH().equals(this.f2104h) && super.equals(obj);
        }
        return false;
    }

    public BigInteger getC() {
        return this.f2102c;
    }

    public BigInteger getD() {
        return this.f2103d;
    }

    public BigInteger getH() {
        return this.f2104h;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f2102c.hashCode() ^ this.f2103d.hashCode()) ^ this.f2104h.hashCode()) ^ super.hashCode();
    }
}
