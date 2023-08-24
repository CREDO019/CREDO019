package org.bouncycastle.crypto.p033ec;

import org.bouncycastle.math.p039ec.ECPoint;

/* renamed from: org.bouncycastle.crypto.ec.ECPair */
/* loaded from: classes5.dex */
public class ECPair {

    /* renamed from: x */
    private final ECPoint f1866x;

    /* renamed from: y */
    private final ECPoint f1867y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f1866x = eCPoint;
        this.f1867y = eCPoint2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public ECPoint getX() {
        return this.f1866x;
    }

    public ECPoint getY() {
        return this.f1867y;
    }

    public int hashCode() {
        return this.f1866x.hashCode() + (this.f1867y.hashCode() * 37);
    }
}
