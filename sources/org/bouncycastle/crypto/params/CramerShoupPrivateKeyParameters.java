package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: pk */
    private CramerShoupPublicKeyParameters f2096pk;

    /* renamed from: x1 */
    private BigInteger f2097x1;

    /* renamed from: x2 */
    private BigInteger f2098x2;

    /* renamed from: y1 */
    private BigInteger f2099y1;

    /* renamed from: y2 */
    private BigInteger f2100y2;

    /* renamed from: z */
    private BigInteger f2101z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.f2097x1 = bigInteger;
        this.f2098x2 = bigInteger2;
        this.f2099y1 = bigInteger3;
        this.f2100y2 = bigInteger4;
        this.f2101z = bigInteger5;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPrivateKeyParameters) {
            CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
            return cramerShoupPrivateKeyParameters.getX1().equals(this.f2097x1) && cramerShoupPrivateKeyParameters.getX2().equals(this.f2098x2) && cramerShoupPrivateKeyParameters.getY1().equals(this.f2099y1) && cramerShoupPrivateKeyParameters.getY2().equals(this.f2100y2) && cramerShoupPrivateKeyParameters.getZ().equals(this.f2101z) && super.equals(obj);
        }
        return false;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f2096pk;
    }

    public BigInteger getX1() {
        return this.f2097x1;
    }

    public BigInteger getX2() {
        return this.f2098x2;
    }

    public BigInteger getY1() {
        return this.f2099y1;
    }

    public BigInteger getY2() {
        return this.f2100y2;
    }

    public BigInteger getZ() {
        return this.f2101z;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((((this.f2097x1.hashCode() ^ this.f2098x2.hashCode()) ^ this.f2099y1.hashCode()) ^ this.f2100y2.hashCode()) ^ this.f2101z.hashCode()) ^ super.hashCode();
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.f2096pk = cramerShoupPublicKeyParameters;
    }
}
