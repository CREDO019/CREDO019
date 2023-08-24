package org.bouncycastle.math.p039ec.custom.sec;

import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP521R1Point */
/* loaded from: classes5.dex */
public class SecP521R1Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] r5;
        int[] r6;
        int[] r3;
        int[] r4;
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f2267x;
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f2268y;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) eCPoint.getXCoord();
        SecP521R1FieldElement secP521R1FieldElement4 = (SecP521R1FieldElement) eCPoint.getYCoord();
        SecP521R1FieldElement secP521R1FieldElement5 = (SecP521R1FieldElement) this.f2269zs[0];
        SecP521R1FieldElement secP521R1FieldElement6 = (SecP521R1FieldElement) eCPoint.getZCoord(0);
        int[] create = Nat.create(17);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        int[] create4 = Nat.create(17);
        boolean isOne = secP521R1FieldElement5.isOne();
        if (isOne) {
            r5 = secP521R1FieldElement3.f2341x;
            r6 = secP521R1FieldElement4.f2341x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement5.f2341x, create3);
            SecP521R1Field.multiply(create3, secP521R1FieldElement3.f2341x, create2);
            SecP521R1Field.multiply(create3, secP521R1FieldElement5.f2341x, create3);
            SecP521R1Field.multiply(create3, secP521R1FieldElement4.f2341x, create3);
            r5 = create2;
            r6 = create3;
        }
        boolean isOne2 = secP521R1FieldElement6.isOne();
        if (isOne2) {
            r3 = secP521R1FieldElement.f2341x;
            r4 = secP521R1FieldElement2.f2341x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement6.f2341x, create4);
            SecP521R1Field.multiply(create4, secP521R1FieldElement.f2341x, create);
            SecP521R1Field.multiply(create4, secP521R1FieldElement6.f2341x, create4);
            SecP521R1Field.multiply(create4, secP521R1FieldElement2.f2341x, create4);
            r3 = create;
            r4 = create4;
        }
        int[] create5 = Nat.create(17);
        SecP521R1Field.subtract(r3, r5, create5);
        SecP521R1Field.subtract(r4, r6, create2);
        if (Nat.isZero(17, create5)) {
            return Nat.isZero(17, create2) ? twice() : curve.getInfinity();
        }
        SecP521R1Field.square(create5, create3);
        int[] create6 = Nat.create(17);
        SecP521R1Field.multiply(create3, create5, create6);
        SecP521R1Field.multiply(create3, r3, create3);
        SecP521R1Field.multiply(r4, create6, create);
        SecP521R1FieldElement secP521R1FieldElement7 = new SecP521R1FieldElement(create4);
        SecP521R1Field.square(create2, secP521R1FieldElement7.f2341x);
        SecP521R1Field.add(secP521R1FieldElement7.f2341x, create6, secP521R1FieldElement7.f2341x);
        SecP521R1Field.subtract(secP521R1FieldElement7.f2341x, create3, secP521R1FieldElement7.f2341x);
        SecP521R1Field.subtract(secP521R1FieldElement7.f2341x, create3, secP521R1FieldElement7.f2341x);
        SecP521R1FieldElement secP521R1FieldElement8 = new SecP521R1FieldElement(create6);
        SecP521R1Field.subtract(create3, secP521R1FieldElement7.f2341x, secP521R1FieldElement8.f2341x);
        SecP521R1Field.multiply(secP521R1FieldElement8.f2341x, create2, create2);
        SecP521R1Field.subtract(create2, create, secP521R1FieldElement8.f2341x);
        SecP521R1FieldElement secP521R1FieldElement9 = new SecP521R1FieldElement(create5);
        if (!isOne) {
            SecP521R1Field.multiply(secP521R1FieldElement9.f2341x, secP521R1FieldElement5.f2341x, secP521R1FieldElement9.f2341x);
        }
        if (!isOne2) {
            SecP521R1Field.multiply(secP521R1FieldElement9.f2341x, secP521R1FieldElement6.f2341x, secP521R1FieldElement9.f2341x);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement7, secP521R1FieldElement8, new ECFieldElement[]{secP521R1FieldElement9});
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    protected ECPoint detach() {
        return new SecP521R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
        return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
    }

    protected ECFieldElement eight(ECFieldElement eCFieldElement) {
        return four(two(eCFieldElement));
    }

    protected ECFieldElement four(ECFieldElement eCFieldElement) {
        return two(two(eCFieldElement));
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP521R1Point(this.curve, this.f2267x, this.f2268y.negate(), this.f2269zs);
    }

    protected ECFieldElement three(ECFieldElement eCFieldElement) {
        return two(eCFieldElement).add(eCFieldElement);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f2268y.isZero()) ? this : twice().add(this);
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f2268y;
        if (secP521R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f2267x;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) this.f2269zs[0];
        int[] create = Nat.create(17);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        SecP521R1Field.square(secP521R1FieldElement.f2341x, create3);
        int[] create4 = Nat.create(17);
        SecP521R1Field.square(create3, create4);
        boolean isOne = secP521R1FieldElement3.isOne();
        int[] r11 = secP521R1FieldElement3.f2341x;
        if (!isOne) {
            SecP521R1Field.square(secP521R1FieldElement3.f2341x, create2);
            r11 = create2;
        }
        SecP521R1Field.subtract(secP521R1FieldElement2.f2341x, r11, create);
        SecP521R1Field.add(secP521R1FieldElement2.f2341x, r11, create2);
        SecP521R1Field.multiply(create2, create, create2);
        Nat.addBothTo(17, create2, create2, create2);
        SecP521R1Field.reduce23(create2);
        SecP521R1Field.multiply(create3, secP521R1FieldElement2.f2341x, create3);
        Nat.shiftUpBits(17, create3, 2, 0);
        SecP521R1Field.reduce23(create3);
        Nat.shiftUpBits(17, create4, 3, 0, create);
        SecP521R1Field.reduce23(create);
        SecP521R1FieldElement secP521R1FieldElement4 = new SecP521R1FieldElement(create4);
        SecP521R1Field.square(create2, secP521R1FieldElement4.f2341x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f2341x, create3, secP521R1FieldElement4.f2341x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f2341x, create3, secP521R1FieldElement4.f2341x);
        SecP521R1FieldElement secP521R1FieldElement5 = new SecP521R1FieldElement(create3);
        SecP521R1Field.subtract(create3, secP521R1FieldElement4.f2341x, secP521R1FieldElement5.f2341x);
        SecP521R1Field.multiply(secP521R1FieldElement5.f2341x, create2, secP521R1FieldElement5.f2341x);
        SecP521R1Field.subtract(secP521R1FieldElement5.f2341x, create, secP521R1FieldElement5.f2341x);
        SecP521R1FieldElement secP521R1FieldElement6 = new SecP521R1FieldElement(create2);
        SecP521R1Field.twice(secP521R1FieldElement.f2341x, secP521R1FieldElement6.f2341x);
        if (!isOne) {
            SecP521R1Field.multiply(secP521R1FieldElement6.f2341x, secP521R1FieldElement3.f2341x, secP521R1FieldElement6.f2341x);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement4, secP521R1FieldElement5, new ECFieldElement[]{secP521R1FieldElement6});
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f2268y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    protected ECFieldElement two(ECFieldElement eCFieldElement) {
        return eCFieldElement.add(eCFieldElement);
    }
}
