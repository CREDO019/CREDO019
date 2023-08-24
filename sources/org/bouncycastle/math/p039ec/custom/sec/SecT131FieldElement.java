package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT131FieldElement */
/* loaded from: classes5.dex */
public class SecT131FieldElement extends ECFieldElement.AbstractF2m {

    /* renamed from: x */
    protected long[] f2343x;

    public SecT131FieldElement() {
        this.f2343x = Nat192.create64();
    }

    public SecT131FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.f2343x = SecT131Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecT131FieldElement(long[] jArr) {
        this.f2343x = jArr;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] create64 = Nat192.create64();
        SecT131Field.add(this.f2343x, ((SecT131FieldElement) eCFieldElement).f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement addOne() {
        long[] create64 = Nat192.create64();
        SecT131Field.addOne(this.f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT131FieldElement) {
            return Nat192.eq64(this.f2343x, ((SecT131FieldElement) obj).f2343x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public String getFieldName() {
        return "SecT131Field";
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public int getFieldSize() {
        return 131;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }

    public int getM() {
        return 131;
    }

    public int getRepresentation() {
        return 3;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement.AbstractF2m
    public ECFieldElement halfTrace() {
        long[] create64 = Nat192.create64();
        SecT131Field.halfTrace(this.f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement.AbstractF2m
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f2343x, 0, 3) ^ 131832;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement invert() {
        long[] create64 = Nat192.create64();
        SecT131Field.invert(this.f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean isOne() {
        return Nat192.isOne64(this.f2343x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean isZero() {
        return Nat192.isZero64(this.f2343x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] create64 = Nat192.create64();
        SecT131Field.multiply(this.f2343x, ((SecT131FieldElement) eCFieldElement).f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f2343x;
        long[] jArr2 = ((SecT131FieldElement) eCFieldElement).f2343x;
        long[] jArr3 = ((SecT131FieldElement) eCFieldElement2).f2343x;
        long[] jArr4 = ((SecT131FieldElement) eCFieldElement3).f2343x;
        long[] create64 = Nat.create64(5);
        SecT131Field.multiplyAddToExt(jArr, jArr2, create64);
        SecT131Field.multiplyAddToExt(jArr3, jArr4, create64);
        long[] create642 = Nat192.create64();
        SecT131Field.reduce(create64, create642);
        return new SecT131FieldElement(create642);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement negate() {
        return this;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement sqrt() {
        long[] create64 = Nat192.create64();
        SecT131Field.sqrt(this.f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement square() {
        long[] create64 = Nat192.create64();
        SecT131Field.square(this.f2343x, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f2343x;
        long[] jArr2 = ((SecT131FieldElement) eCFieldElement).f2343x;
        long[] jArr3 = ((SecT131FieldElement) eCFieldElement2).f2343x;
        long[] create64 = Nat.create64(5);
        SecT131Field.squareAddToExt(jArr, create64);
        SecT131Field.multiplyAddToExt(jArr2, jArr3, create64);
        long[] create642 = Nat192.create64();
        SecT131Field.reduce(create64, create642);
        return new SecT131FieldElement(create642);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement squarePow(int r3) {
        if (r3 < 1) {
            return this;
        }
        long[] create64 = Nat192.create64();
        SecT131Field.squareN(this.f2343x, r3, create64);
        return new SecT131FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean testBitZero() {
        return (this.f2343x[0] & 1) != 0;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger64(this.f2343x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement.AbstractF2m
    public int trace() {
        return SecT131Field.trace(this.f2343x);
    }
}
