package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    /* renamed from: dP */
    private BigInteger f2150dP;

    /* renamed from: dQ */
    private BigInteger f2151dQ;

    /* renamed from: e */
    private BigInteger f2152e;

    /* renamed from: p */
    private BigInteger f2153p;

    /* renamed from: q */
    private BigInteger f2154q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f2152e = bigInteger2;
        this.f2153p = bigInteger4;
        this.f2154q = bigInteger5;
        this.f2150dP = bigInteger6;
        this.f2151dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.f2150dP;
    }

    public BigInteger getDQ() {
        return this.f2151dQ;
    }

    public BigInteger getP() {
        return this.f2153p;
    }

    public BigInteger getPublicExponent() {
        return this.f2152e;
    }

    public BigInteger getQ() {
        return this.f2154q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
