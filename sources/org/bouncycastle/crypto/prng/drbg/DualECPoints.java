package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.math.p039ec.ECPoint;

/* loaded from: classes5.dex */
public class DualECPoints {
    private final int cofactor;

    /* renamed from: p */
    private final ECPoint f2165p;

    /* renamed from: q */
    private final ECPoint f2166q;
    private final int securityStrength;

    public DualECPoints(int r3, ECPoint eCPoint, ECPoint eCPoint2, int r6) {
        if (!eCPoint.getCurve().equals(eCPoint2.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.securityStrength = r3;
        this.f2165p = eCPoint;
        this.f2166q = eCPoint2;
        this.cofactor = r6;
    }

    private static int log2(int r1) {
        int r0 = 0;
        while (true) {
            r1 >>= 1;
            if (r1 == 0) {
                return r0;
            }
            r0++;
        }
    }

    public int getCofactor() {
        return this.cofactor;
    }

    public int getMaxOutlen() {
        return ((this.f2165p.getCurve().getFieldSize() - (log2(this.cofactor) + 13)) / 8) * 8;
    }

    public ECPoint getP() {
        return this.f2165p;
    }

    public ECPoint getQ() {
        return this.f2166q;
    }

    public int getSecurityStrength() {
        return this.securityStrength;
    }

    public int getSeedLen() {
        return this.f2165p.getCurve().getFieldSize();
    }
}
