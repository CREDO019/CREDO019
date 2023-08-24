package org.bouncycastle.math.p039ec;

/* renamed from: org.bouncycastle.math.ec.WNafPreCompInfo */
/* loaded from: classes5.dex */
public class WNafPreCompInfo implements PreCompInfo {
    volatile int promotionCountdown = 4;
    protected int confWidth = -1;
    protected ECPoint[] preComp = null;
    protected ECPoint[] preCompNeg = null;
    protected ECPoint twice = null;
    protected int width = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int decrementPromotionCountdown() {
        int r0 = this.promotionCountdown;
        if (r0 > 0) {
            int r02 = r0 - 1;
            this.promotionCountdown = r02;
            return r02;
        }
        return r0;
    }

    public int getConfWidth() {
        return this.confWidth;
    }

    public ECPoint[] getPreComp() {
        return this.preComp;
    }

    public ECPoint[] getPreCompNeg() {
        return this.preCompNeg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPromotionCountdown() {
        return this.promotionCountdown;
    }

    public ECPoint getTwice() {
        return this.twice;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isPromoted() {
        return this.promotionCountdown <= 0;
    }

    public void setConfWidth(int r1) {
        this.confWidth = r1;
    }

    public void setPreComp(ECPoint[] eCPointArr) {
        this.preComp = eCPointArr;
    }

    public void setPreCompNeg(ECPoint[] eCPointArr) {
        this.preCompNeg = eCPointArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPromotionCountdown(int r1) {
        this.promotionCountdown = r1;
    }

    public void setTwice(ECPoint eCPoint) {
        this.twice = eCPoint;
    }

    public void setWidth(int r1) {
        this.width = r1;
    }
}
