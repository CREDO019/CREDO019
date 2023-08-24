package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class CramerShoupParameters implements CipherParameters {

    /* renamed from: H */
    private Digest f2092H;

    /* renamed from: g1 */
    private BigInteger f2093g1;

    /* renamed from: g2 */
    private BigInteger f2094g2;

    /* renamed from: p */
    private BigInteger f2095p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f2095p = bigInteger;
        this.f2093g1 = bigInteger2;
        this.f2094g2 = bigInteger3;
        Digest digest2 = (Digest) ((Memoable) digest).copy();
        this.f2092H = digest2;
        digest2.reset();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupParameters) {
            CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
            return cramerShoupParameters.getP().equals(this.f2095p) && cramerShoupParameters.getG1().equals(this.f2093g1) && cramerShoupParameters.getG2().equals(this.f2094g2);
        }
        return false;
    }

    public BigInteger getG1() {
        return this.f2093g1;
    }

    public BigInteger getG2() {
        return this.f2094g2;
    }

    public Digest getH() {
        return (Digest) ((Memoable) this.f2092H).copy();
    }

    public BigInteger getP() {
        return this.f2095p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }
}
