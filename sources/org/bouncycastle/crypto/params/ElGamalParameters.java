package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class ElGamalParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f2125g;

    /* renamed from: l */
    private int f2126l;

    /* renamed from: p */
    private BigInteger f2127p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int r3) {
        this.f2125g = bigInteger2;
        this.f2127p = bigInteger;
        this.f2126l = r3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ElGamalParameters) {
            ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
            return elGamalParameters.getP().equals(this.f2127p) && elGamalParameters.getG().equals(this.f2125g) && elGamalParameters.getL() == this.f2126l;
        }
        return false;
    }

    public BigInteger getG() {
        return this.f2125g;
    }

    public int getL() {
        return this.f2126l;
    }

    public BigInteger getP() {
        return this.f2127p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f2126l;
    }
}
