package org.bouncycastle.math.p039ec.custom.djb;

import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;

/* renamed from: org.bouncycastle.math.ec.custom.djb.Curve25519Point */
/* loaded from: classes5.dex */
public class Curve25519Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] r5;
        int[] r6;
        int[] r1;
        int[] r2;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement) this.f2267x;
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement) this.f2268y;
        Curve25519FieldElement curve25519FieldElement3 = (Curve25519FieldElement) this.f2269zs[0];
        Curve25519FieldElement curve25519FieldElement4 = (Curve25519FieldElement) eCPoint.getXCoord();
        Curve25519FieldElement curve25519FieldElement5 = (Curve25519FieldElement) eCPoint.getYCoord();
        Curve25519FieldElement curve25519FieldElement6 = (Curve25519FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat256.createExt();
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        boolean isOne = curve25519FieldElement3.isOne();
        if (isOne) {
            r5 = curve25519FieldElement4.f2277x;
            r6 = curve25519FieldElement5.f2277x;
        } else {
            Curve25519Field.square(curve25519FieldElement3.f2277x, create2);
            Curve25519Field.multiply(create2, curve25519FieldElement4.f2277x, create);
            Curve25519Field.multiply(create2, curve25519FieldElement3.f2277x, create2);
            Curve25519Field.multiply(create2, curve25519FieldElement5.f2277x, create2);
            r5 = create;
            r6 = create2;
        }
        boolean isOne2 = curve25519FieldElement6.isOne();
        if (isOne2) {
            r1 = curve25519FieldElement.f2277x;
            r2 = curve25519FieldElement2.f2277x;
        } else {
            Curve25519Field.square(curve25519FieldElement6.f2277x, create3);
            Curve25519Field.multiply(create3, curve25519FieldElement.f2277x, createExt);
            Curve25519Field.multiply(create3, curve25519FieldElement6.f2277x, create3);
            Curve25519Field.multiply(create3, curve25519FieldElement2.f2277x, create3);
            r1 = createExt;
            r2 = create3;
        }
        int[] create4 = Nat256.create();
        Curve25519Field.subtract(r1, r5, create4);
        Curve25519Field.subtract(r2, r6, create);
        if (Nat256.isZero(create4)) {
            return Nat256.isZero(create) ? twice() : curve.getInfinity();
        }
        int[] create5 = Nat256.create();
        Curve25519Field.square(create4, create5);
        int[] create6 = Nat256.create();
        Curve25519Field.multiply(create5, create4, create6);
        Curve25519Field.multiply(create5, r1, create2);
        Curve25519Field.negate(create6, create6);
        Nat256.mul(r2, create6, createExt);
        Curve25519Field.reduce27(Nat256.addBothTo(create2, create2, create6), create6);
        Curve25519FieldElement curve25519FieldElement7 = new Curve25519FieldElement(create3);
        Curve25519Field.square(create, curve25519FieldElement7.f2277x);
        Curve25519Field.subtract(curve25519FieldElement7.f2277x, create6, curve25519FieldElement7.f2277x);
        Curve25519FieldElement curve25519FieldElement8 = new Curve25519FieldElement(create6);
        Curve25519Field.subtract(create2, curve25519FieldElement7.f2277x, curve25519FieldElement8.f2277x);
        Curve25519Field.multiplyAddToExt(curve25519FieldElement8.f2277x, create, createExt);
        Curve25519Field.reduce(createExt, curve25519FieldElement8.f2277x);
        Curve25519FieldElement curve25519FieldElement9 = new Curve25519FieldElement(create4);
        if (!isOne) {
            Curve25519Field.multiply(curve25519FieldElement9.f2277x, curve25519FieldElement3.f2277x, curve25519FieldElement9.f2277x);
        }
        if (!isOne2) {
            Curve25519Field.multiply(curve25519FieldElement9.f2277x, curve25519FieldElement6.f2277x, curve25519FieldElement9.f2277x);
        }
        return new Curve25519Point(curve, curve25519FieldElement7, curve25519FieldElement8, new ECFieldElement[]{curve25519FieldElement9, calculateJacobianModifiedW(curve25519FieldElement9, (isOne && isOne2) ? null : null)});
    }

    protected Curve25519FieldElement calculateJacobianModifiedW(Curve25519FieldElement curve25519FieldElement, int[] r4) {
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement) getCurve().getA();
        if (curve25519FieldElement.isOne()) {
            return curve25519FieldElement2;
        }
        Curve25519FieldElement curve25519FieldElement3 = new Curve25519FieldElement();
        if (r4 == null) {
            r4 = curve25519FieldElement3.f2277x;
            Curve25519Field.square(curve25519FieldElement.f2277x, r4);
        }
        Curve25519Field.square(r4, curve25519FieldElement3.f2277x);
        Curve25519Field.multiply(curve25519FieldElement3.f2277x, curve25519FieldElement2.f2277x, curve25519FieldElement3.f2277x);
        return curve25519FieldElement3;
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    protected ECPoint detach() {
        return new Curve25519Point(null, getAffineXCoord(), getAffineYCoord());
    }

    protected Curve25519FieldElement getJacobianModifiedW() {
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement) this.f2269zs[1];
        if (curve25519FieldElement == null) {
            ECFieldElement[] eCFieldElementArr = this.f2269zs;
            Curve25519FieldElement calculateJacobianModifiedW = calculateJacobianModifiedW((Curve25519FieldElement) this.f2269zs[0], null);
            eCFieldElementArr[1] = calculateJacobianModifiedW;
            return calculateJacobianModifiedW;
        }
        return curve25519FieldElement;
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECFieldElement getZCoord(int r2) {
        return r2 == 1 ? getJacobianModifiedW() : super.getZCoord(r2);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new Curve25519Point(getCurve(), this.f2267x, this.f2268y.negate(), this.f2269zs);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f2268y.isZero()) ? this : twiceJacobianModified(false).add(this);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        return this.f2268y.isZero() ? getCurve().getInfinity() : twiceJacobianModified(true);
    }

    protected Curve25519Point twiceJacobianModified(boolean z) {
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement) this.f2267x;
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement) this.f2268y;
        Curve25519FieldElement curve25519FieldElement3 = (Curve25519FieldElement) this.f2269zs[0];
        Curve25519FieldElement jacobianModifiedW = getJacobianModifiedW();
        int[] create = Nat256.create();
        Curve25519Field.square(curve25519FieldElement.f2277x, create);
        Curve25519Field.reduce27(Nat256.addBothTo(create, create, create) + Nat256.addTo(jacobianModifiedW.f2277x, create), create);
        int[] create2 = Nat256.create();
        Curve25519Field.twice(curve25519FieldElement2.f2277x, create2);
        int[] create3 = Nat256.create();
        Curve25519Field.multiply(create2, curve25519FieldElement2.f2277x, create3);
        int[] create4 = Nat256.create();
        Curve25519Field.multiply(create3, curve25519FieldElement.f2277x, create4);
        Curve25519Field.twice(create4, create4);
        int[] create5 = Nat256.create();
        Curve25519Field.square(create3, create5);
        Curve25519Field.twice(create5, create5);
        Curve25519FieldElement curve25519FieldElement4 = new Curve25519FieldElement(create3);
        Curve25519Field.square(create, curve25519FieldElement4.f2277x);
        Curve25519Field.subtract(curve25519FieldElement4.f2277x, create4, curve25519FieldElement4.f2277x);
        Curve25519Field.subtract(curve25519FieldElement4.f2277x, create4, curve25519FieldElement4.f2277x);
        Curve25519FieldElement curve25519FieldElement5 = new Curve25519FieldElement(create4);
        Curve25519Field.subtract(create4, curve25519FieldElement4.f2277x, curve25519FieldElement5.f2277x);
        Curve25519Field.multiply(curve25519FieldElement5.f2277x, create, curve25519FieldElement5.f2277x);
        Curve25519Field.subtract(curve25519FieldElement5.f2277x, create5, curve25519FieldElement5.f2277x);
        Curve25519FieldElement curve25519FieldElement6 = new Curve25519FieldElement(create2);
        if (!Nat256.isOne(curve25519FieldElement3.f2277x)) {
            Curve25519Field.multiply(curve25519FieldElement6.f2277x, curve25519FieldElement3.f2277x, curve25519FieldElement6.f2277x);
        }
        Curve25519FieldElement curve25519FieldElement7 = null;
        if (z) {
            curve25519FieldElement7 = new Curve25519FieldElement(create5);
            Curve25519Field.multiply(curve25519FieldElement7.f2277x, jacobianModifiedW.f2277x, curve25519FieldElement7.f2277x);
            Curve25519Field.twice(curve25519FieldElement7.f2277x, curve25519FieldElement7.f2277x);
        }
        return new Curve25519Point(getCurve(), curve25519FieldElement4, curve25519FieldElement5, new ECFieldElement[]{curve25519FieldElement6, curve25519FieldElement7});
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f2268y.isZero() ? eCPoint : twiceJacobianModified(false).add(eCPoint);
    }
}
