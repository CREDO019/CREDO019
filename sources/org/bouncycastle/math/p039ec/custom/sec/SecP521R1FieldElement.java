package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement */
/* loaded from: classes5.dex */
public class SecP521R1FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: Q */
    public static final BigInteger f2340Q = new BigInteger(1, Hex.decodeStrict("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"));

    /* renamed from: x */
    protected int[] f2341x;

    public SecP521R1FieldElement() {
        this.f2341x = Nat.create(17);
    }

    public SecP521R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f2340Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.f2341x = SecP521R1Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP521R1FieldElement(int[] r1) {
        this.f2341x = r1;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.add(this.f2341x, ((SecP521R1FieldElement) eCFieldElement).f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat.create(17);
        SecP521R1Field.addOne(this.f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.inv(((SecP521R1FieldElement) eCFieldElement).f2341x, create);
        SecP521R1Field.multiply(create, this.f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP521R1FieldElement) {
            return Nat.m23eq(17, this.f2341x, ((SecP521R1FieldElement) obj).f2341x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public String getFieldName() {
        return "SecP521R1Field";
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public int getFieldSize() {
        return f2340Q.bitLength();
    }

    public int hashCode() {
        return f2340Q.hashCode() ^ Arrays.hashCode(this.f2341x, 0, 17);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat.create(17);
        SecP521R1Field.inv(this.f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean isOne() {
        return Nat.isOne(17, this.f2341x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean isZero() {
        return Nat.isZero(17, this.f2341x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.multiply(this.f2341x, ((SecP521R1FieldElement) eCFieldElement).f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat.create(17);
        SecP521R1Field.negate(this.f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] r0 = this.f2341x;
        if (Nat.isZero(17, r0) || Nat.isOne(17, r0)) {
            return this;
        }
        int[] create = Nat.create(17);
        int[] create2 = Nat.create(17);
        SecP521R1Field.squareN(r0, 519, create);
        SecP521R1Field.square(create, create2);
        if (Nat.m23eq(17, r0, create2)) {
            return new SecP521R1FieldElement(create);
        }
        return null;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat.create(17);
        SecP521R1Field.square(this.f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.subtract(this.f2341x, ((SecP521R1FieldElement) eCFieldElement).f2341x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean testBitZero() {
        return Nat.getBit(this.f2341x, 0) == 1;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(17, this.f2341x);
    }
}
