package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP224R1FieldElement */
/* loaded from: classes5.dex */
public class SecP224R1FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: Q */
    public static final BigInteger f2320Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001"));

    /* renamed from: x */
    protected int[] f2321x;

    public SecP224R1FieldElement() {
        this.f2321x = Nat224.create();
    }

    public SecP224R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f2320Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        this.f2321x = SecP224R1Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP224R1FieldElement(int[] r1) {
        this.f2321x = r1;
    }

    /* renamed from: RM */
    private static void m26RM(int[] r0, int[] r1, int[] r2, int[] r3, int[] r4, int[] r5, int[] r6) {
        SecP224R1Field.multiply(r4, r2, r6);
        SecP224R1Field.multiply(r6, r0, r6);
        SecP224R1Field.multiply(r3, r1, r5);
        SecP224R1Field.add(r5, r6, r5);
        SecP224R1Field.multiply(r3, r2, r6);
        Nat224.copy(r5, r3);
        SecP224R1Field.multiply(r4, r1, r4);
        SecP224R1Field.add(r4, r6, r4);
        SecP224R1Field.square(r4, r5);
        SecP224R1Field.multiply(r5, r0, r5);
    }

    /* renamed from: RP */
    private static void m25RP(int[] r10, int[] r11, int[] r12, int[] r13, int[] r14) {
        Nat224.copy(r10, r13);
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        for (int r9 = 0; r9 < 7; r9++) {
            Nat224.copy(r11, create);
            Nat224.copy(r12, create2);
            int r0 = 1 << r9;
            while (true) {
                r0--;
                if (r0 >= 0) {
                    m24RS(r11, r12, r13, r14);
                }
            }
            m26RM(r10, create, create2, r11, r12, r13, r14);
        }
    }

    /* renamed from: RS */
    private static void m24RS(int[] r0, int[] r1, int[] r2, int[] r3) {
        SecP224R1Field.multiply(r1, r0, r1);
        SecP224R1Field.twice(r1, r1);
        SecP224R1Field.square(r0, r3);
        SecP224R1Field.add(r2, r3, r0);
        SecP224R1Field.multiply(r2, r3, r2);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, r2, 2, 0), r2);
    }

    private static boolean isSquare(int[] r3) {
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        Nat224.copy(r3, create);
        for (int r32 = 0; r32 < 7; r32++) {
            Nat224.copy(create, create2);
            SecP224R1Field.squareN(create, 1 << r32, create);
            SecP224R1Field.multiply(create, create2, create);
        }
        SecP224R1Field.squareN(create, 95, create);
        return Nat224.isOne(create);
    }

    private static boolean trySqrt(int[] r7, int[] r8, int[] r9) {
        int[] create = Nat224.create();
        Nat224.copy(r8, create);
        int[] create2 = Nat224.create();
        create2[0] = 1;
        int[] create3 = Nat224.create();
        m25RP(r7, create, create2, create3, r9);
        int[] create4 = Nat224.create();
        int[] create5 = Nat224.create();
        for (int r5 = 1; r5 < 96; r5++) {
            Nat224.copy(create, create4);
            Nat224.copy(create2, create5);
            m24RS(create, create2, create3, r9);
            if (Nat224.isZero(create)) {
                SecP224R1Field.inv(create5, r9);
                SecP224R1Field.multiply(r9, create4, r9);
                return true;
            }
        }
        return false;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.add(this.f2321x, ((SecP224R1FieldElement) eCFieldElement).f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat224.create();
        SecP224R1Field.addOne(this.f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.inv(((SecP224R1FieldElement) eCFieldElement).f2321x, create);
        SecP224R1Field.multiply(create, this.f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP224R1FieldElement) {
            return Nat224.m19eq(this.f2321x, ((SecP224R1FieldElement) obj).f2321x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public String getFieldName() {
        return "SecP224R1Field";
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public int getFieldSize() {
        return f2320Q.bitLength();
    }

    public int hashCode() {
        return f2320Q.hashCode() ^ Arrays.hashCode(this.f2321x, 0, 7);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat224.create();
        SecP224R1Field.inv(this.f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean isOne() {
        return Nat224.isOne(this.f2321x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean isZero() {
        return Nat224.isZero(this.f2321x);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.multiply(this.f2321x, ((SecP224R1FieldElement) eCFieldElement).f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat224.create();
        SecP224R1Field.negate(this.f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] r0 = this.f2321x;
        if (Nat224.isZero(r0) || Nat224.isOne(r0)) {
            return this;
        }
        int[] create = Nat224.create();
        SecP224R1Field.negate(r0, create);
        int[] random = Mod.random(SecP224R1Field.f2318P);
        int[] create2 = Nat224.create();
        if (isSquare(r0)) {
            while (!trySqrt(create, random, create2)) {
                SecP224R1Field.addOne(random, random);
            }
            SecP224R1Field.square(create2, random);
            if (Nat224.m19eq(r0, random)) {
                return new SecP224R1FieldElement(create2);
            }
            return null;
        }
        return null;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat224.create();
        SecP224R1Field.square(this.f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.subtract(this.f2321x, ((SecP224R1FieldElement) eCFieldElement).f2321x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public boolean testBitZero() {
        return Nat224.getBit(this.f2321x, 0) == 1;
    }

    @Override // org.bouncycastle.math.p039ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.f2321x);
    }
}
