package org.bouncycastle.math.p039ec.custom.sec;

import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat384;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP384R1Point */
/* loaded from: classes5.dex */
public class SecP384R1Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement) this.f2267x;
        SecP384R1FieldElement secP384R1FieldElement2 = (SecP384R1FieldElement) this.f2268y;
        SecP384R1FieldElement secP384R1FieldElement3 = (SecP384R1FieldElement) eCPoint.getXCoord();
        SecP384R1FieldElement secP384R1FieldElement4 = (SecP384R1FieldElement) eCPoint.getYCoord();
        SecP384R1FieldElement secP384R1FieldElement5 = (SecP384R1FieldElement) this.f2269zs[0];
        SecP384R1FieldElement secP384R1FieldElement6 = (SecP384R1FieldElement) eCPoint.getZCoord(0);
        int[] create = Nat.create(24);
        int[] create2 = Nat.create(24);
        int[] create3 = Nat.create(12);
        int[] create4 = Nat.create(12);
        boolean isOne = secP384R1FieldElement5.isOne();
        if (isOne) {
            r5 = secP384R1FieldElement3.f2337x;
            r6 = secP384R1FieldElement4.f2337x;
        } else {
            SecP384R1Field.square(secP384R1FieldElement5.f2337x, create3);
            SecP384R1Field.multiply(create3, secP384R1FieldElement3.f2337x, create2);
            SecP384R1Field.multiply(create3, secP384R1FieldElement5.f2337x, create3);
            SecP384R1Field.multiply(create3, secP384R1FieldElement4.f2337x, create3);
            r5 = create2;
            r6 = create3;
        }
        boolean isOne2 = secP384R1FieldElement6.isOne();
        if (isOne2) {
            r3 = secP384R1FieldElement.f2337x;
            r4 = secP384R1FieldElement2.f2337x;
        } else {
            SecP384R1Field.square(secP384R1FieldElement6.f2337x, create4);
            SecP384R1Field.multiply(create4, secP384R1FieldElement.f2337x, create);
            SecP384R1Field.multiply(create4, secP384R1FieldElement6.f2337x, create4);
            SecP384R1Field.multiply(create4, secP384R1FieldElement2.f2337x, create4);
            r3 = create;
            r4 = create4;
        }
        int[] create5 = Nat.create(12);
        SecP384R1Field.subtract(r3, r5, create5);
        int[] create6 = Nat.create(12);
        SecP384R1Field.subtract(r4, r6, create6);
        if (Nat.isZero(12, create5)) {
            return Nat.isZero(12, create6) ? twice() : curve.getInfinity();
        }
        SecP384R1Field.square(create5, create3);
        int[] create7 = Nat.create(12);
        SecP384R1Field.multiply(create3, create5, create7);
        SecP384R1Field.multiply(create3, r3, create3);
        SecP384R1Field.negate(create7, create7);
        Nat384.mul(r4, create7, create);
        SecP384R1Field.reduce32(Nat.addBothTo(12, create3, create3, create7), create7);
        SecP384R1FieldElement secP384R1FieldElement7 = new SecP384R1FieldElement(create4);
        SecP384R1Field.square(create6, secP384R1FieldElement7.f2337x);
        SecP384R1Field.subtract(secP384R1FieldElement7.f2337x, create7, secP384R1FieldElement7.f2337x);
        SecP384R1FieldElement secP384R1FieldElement8 = new SecP384R1FieldElement(create7);
        SecP384R1Field.subtract(create3, secP384R1FieldElement7.f2337x, secP384R1FieldElement8.f2337x);
        Nat384.mul(secP384R1FieldElement8.f2337x, create6, create2);
        SecP384R1Field.addExt(create, create2, create);
        SecP384R1Field.reduce(create, secP384R1FieldElement8.f2337x);
        SecP384R1FieldElement secP384R1FieldElement9 = new SecP384R1FieldElement(create5);
        if (!isOne) {
            SecP384R1Field.multiply(secP384R1FieldElement9.f2337x, secP384R1FieldElement5.f2337x, secP384R1FieldElement9.f2337x);
        }
        if (!isOne2) {
            SecP384R1Field.multiply(secP384R1FieldElement9.f2337x, secP384R1FieldElement6.f2337x, secP384R1FieldElement9.f2337x);
        }
        return new SecP384R1Point(curve, secP384R1FieldElement7, secP384R1FieldElement8, new ECFieldElement[]{secP384R1FieldElement9});
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    protected ECPoint detach() {
        return new SecP384R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP384R1Point(this.curve, this.f2267x, this.f2268y.negate(), this.f2269zs);
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
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement) this.f2268y;
        if (secP384R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP384R1FieldElement secP384R1FieldElement2 = (SecP384R1FieldElement) this.f2267x;
        SecP384R1FieldElement secP384R1FieldElement3 = (SecP384R1FieldElement) this.f2269zs[0];
        int[] create = Nat.create(12);
        int[] create2 = Nat.create(12);
        int[] create3 = Nat.create(12);
        SecP384R1Field.square(secP384R1FieldElement.f2337x, create3);
        int[] create4 = Nat.create(12);
        SecP384R1Field.square(create3, create4);
        boolean isOne = secP384R1FieldElement3.isOne();
        int[] r11 = secP384R1FieldElement3.f2337x;
        if (!isOne) {
            SecP384R1Field.square(secP384R1FieldElement3.f2337x, create2);
            r11 = create2;
        }
        SecP384R1Field.subtract(secP384R1FieldElement2.f2337x, r11, create);
        SecP384R1Field.add(secP384R1FieldElement2.f2337x, r11, create2);
        SecP384R1Field.multiply(create2, create, create2);
        SecP384R1Field.reduce32(Nat.addBothTo(12, create2, create2, create2), create2);
        SecP384R1Field.multiply(create3, secP384R1FieldElement2.f2337x, create3);
        SecP384R1Field.reduce32(Nat.shiftUpBits(12, create3, 2, 0), create3);
        SecP384R1Field.reduce32(Nat.shiftUpBits(12, create4, 3, 0, create), create);
        SecP384R1FieldElement secP384R1FieldElement4 = new SecP384R1FieldElement(create4);
        SecP384R1Field.square(create2, secP384R1FieldElement4.f2337x);
        SecP384R1Field.subtract(secP384R1FieldElement4.f2337x, create3, secP384R1FieldElement4.f2337x);
        SecP384R1Field.subtract(secP384R1FieldElement4.f2337x, create3, secP384R1FieldElement4.f2337x);
        SecP384R1FieldElement secP384R1FieldElement5 = new SecP384R1FieldElement(create3);
        SecP384R1Field.subtract(create3, secP384R1FieldElement4.f2337x, secP384R1FieldElement5.f2337x);
        SecP384R1Field.multiply(secP384R1FieldElement5.f2337x, create2, secP384R1FieldElement5.f2337x);
        SecP384R1Field.subtract(secP384R1FieldElement5.f2337x, create, secP384R1FieldElement5.f2337x);
        SecP384R1FieldElement secP384R1FieldElement6 = new SecP384R1FieldElement(create2);
        SecP384R1Field.twice(secP384R1FieldElement.f2337x, secP384R1FieldElement6.f2337x);
        if (!isOne) {
            SecP384R1Field.multiply(secP384R1FieldElement6.f2337x, secP384R1FieldElement3.f2337x, secP384R1FieldElement6.f2337x);
        }
        return new SecP384R1Point(curve, secP384R1FieldElement4, secP384R1FieldElement5, new ECFieldElement[]{secP384R1FieldElement6});
    }

    @Override // org.bouncycastle.math.p039ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f2268y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
