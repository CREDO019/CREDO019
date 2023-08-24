package org.bouncycastle.math.p039ec;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

/* renamed from: org.bouncycastle.math.ec.ECFieldElement */
/* loaded from: classes5.dex */
public abstract class ECFieldElement implements ECConstants {

    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$AbstractF2m */
    /* loaded from: classes5.dex */
    public static abstract class AbstractF2m extends ECFieldElement {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.bouncycastle.math.ec.ECFieldElement] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r4v3, types: [org.bouncycastle.math.ec.ECFieldElement] */
        public ECFieldElement halfTrace() {
            int fieldSize = getFieldSize();
            if ((fieldSize & 1) != 0) {
                int r0 = (fieldSize + 1) >>> 1;
                int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(r0);
                int r4 = 1;
                ECFieldElement eCFieldElement = this;
                while (numberOfLeadingZeros > 0) {
                    eCFieldElement = eCFieldElement.squarePow(r4 << 1).add(eCFieldElement);
                    numberOfLeadingZeros--;
                    r4 = r0 >>> numberOfLeadingZeros;
                    if ((r4 & 1) != 0) {
                        eCFieldElement = eCFieldElement.squarePow(2).add(this);
                    }
                }
                return eCFieldElement;
            }
            throw new IllegalStateException("Half-trace only defined for odd m");
        }

        public boolean hasFastTrace() {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.bouncycastle.math.ec.ECFieldElement] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r4v2, types: [org.bouncycastle.math.ec.ECFieldElement] */
        public int trace() {
            int fieldSize = getFieldSize();
            int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(fieldSize);
            int r4 = 1;
            ECFieldElement eCFieldElement = this;
            while (numberOfLeadingZeros > 0) {
                eCFieldElement = eCFieldElement.squarePow(r4).add(eCFieldElement);
                numberOfLeadingZeros--;
                r4 = fieldSize >>> numberOfLeadingZeros;
                if ((r4 & 1) != 0) {
                    eCFieldElement = eCFieldElement.square().add(this);
                }
            }
            if (eCFieldElement.isZero()) {
                return 0;
            }
            if (eCFieldElement.isOne()) {
                return 1;
            }
            throw new IllegalStateException("Internal error in trace calculation");
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$AbstractFp */
    /* loaded from: classes5.dex */
    public static abstract class AbstractFp extends ECFieldElement {
    }

    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$F2m */
    /* loaded from: classes5.dex */
    public static class F2m extends AbstractF2m {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        /* renamed from: ks */
        private int[] f2261ks;

        /* renamed from: m */
        private int f2262m;
        private int representation;

        /* renamed from: x */
        LongArray f2263x;

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(int r5, int r6, int r7, int r8, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > r5) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (r7 == 0 && r8 == 0) {
                this.representation = 2;
                this.f2261ks = new int[]{r6};
            } else if (r7 >= r8) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else {
                if (r7 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.representation = 3;
                this.f2261ks = new int[]{r6, r7, r8};
            }
            this.f2262m = r5;
            this.f2263x = new LongArray(bigInteger);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(int r2, int[] r3, LongArray longArray) {
            this.f2262m = r2;
            this.representation = r3.length == 1 ? 2 : 3;
            this.f2261ks = r3;
            this.f2263x = longArray;
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f2263x.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f2263x, 0);
            return new F2m(this.f2262m, this.f2261ks, longArray);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement addOne() {
            return new F2m(this.f2262m, this.f2261ks, this.f2263x.addOne());
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public int bitLength() {
            return this.f2263x.degree();
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof F2m) {
                F2m f2m = (F2m) obj;
                return this.f2262m == f2m.f2262m && this.representation == f2m.representation && Arrays.areEqual(this.f2261ks, f2m.f2261ks) && this.f2263x.equals(f2m.f2263x);
            }
            return false;
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public String getFieldName() {
            return "F2m";
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public int getFieldSize() {
            return this.f2262m;
        }

        public int getK1() {
            return this.f2261ks[0];
        }

        public int getK2() {
            int[] r0 = this.f2261ks;
            if (r0.length >= 2) {
                return r0[1];
            }
            return 0;
        }

        public int getK3() {
            int[] r0 = this.f2261ks;
            if (r0.length >= 3) {
                return r0[2];
            }
            return 0;
        }

        public int getM() {
            return this.f2262m;
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int hashCode() {
            return (this.f2263x.hashCode() ^ this.f2262m) ^ Arrays.hashCode(this.f2261ks);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement invert() {
            int r1 = this.f2262m;
            int[] r2 = this.f2261ks;
            return new F2m(r1, r2, this.f2263x.modInverse(r1, r2));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public boolean isOne() {
            return this.f2263x.isOne();
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public boolean isZero() {
            return this.f2263x.isZero();
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int r1 = this.f2262m;
            int[] r2 = this.f2261ks;
            return new F2m(r1, r2, this.f2263x.modMultiply(((F2m) eCFieldElement).f2263x, r1, r2));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f2263x;
            LongArray longArray2 = ((F2m) eCFieldElement).f2263x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f2263x;
            LongArray longArray4 = ((F2m) eCFieldElement3).f2263x;
            LongArray multiply = longArray.multiply(longArray2, this.f2262m, this.f2261ks);
            LongArray multiply2 = longArray3.multiply(longArray4, this.f2262m, this.f2261ks);
            if (multiply == longArray || multiply == longArray2) {
                multiply = (LongArray) multiply.clone();
            }
            multiply.addShiftedByWords(multiply2, 0);
            multiply.reduce(this.f2262m, this.f2261ks);
            return new F2m(this.f2262m, this.f2261ks, multiply);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement negate() {
            return this;
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement sqrt() {
            return (this.f2263x.isZero() || this.f2263x.isOne()) ? this : squarePow(this.f2262m - 1);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement square() {
            int r1 = this.f2262m;
            int[] r2 = this.f2261ks;
            return new F2m(r1, r2, this.f2263x.modSquare(r1, r2));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f2263x;
            LongArray longArray2 = ((F2m) eCFieldElement).f2263x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f2263x;
            LongArray square = longArray.square(this.f2262m, this.f2261ks);
            LongArray multiply = longArray2.multiply(longArray3, this.f2262m, this.f2261ks);
            if (square == longArray) {
                square = (LongArray) square.clone();
            }
            square.addShiftedByWords(multiply, 0);
            square.reduce(this.f2262m, this.f2261ks);
            return new F2m(this.f2262m, this.f2261ks, square);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement squarePow(int r5) {
            if (r5 < 1) {
                return this;
            }
            int r1 = this.f2262m;
            int[] r2 = this.f2261ks;
            return new F2m(r1, r2, this.f2263x.modSquareN(r5, r1, r2));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public boolean testBitZero() {
            return this.f2263x.testBitZero();
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f2263x.toBigInteger();
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$Fp */
    /* loaded from: classes5.dex */
    public static class C5336Fp extends AbstractFp {

        /* renamed from: q */
        BigInteger f2264q;

        /* renamed from: r */
        BigInteger f2265r;

        /* renamed from: x */
        BigInteger f2266x;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C5336Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f2264q = bigInteger;
            this.f2265r = bigInteger2;
            this.f2266x = bigInteger3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static BigInteger calculateResidue(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength < 96 || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return ONE.shiftLeft(bitLength).subtract(bigInteger);
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.ONE;
            BigInteger bigInteger5 = ECConstants.TWO;
            BigInteger bigInteger6 = ECConstants.ONE;
            BigInteger bigInteger7 = ECConstants.ONE;
            BigInteger bigInteger8 = bigInteger;
            for (int r0 = bitLength - 1; r0 >= lowestSetBit + 1; r0--) {
                bigInteger6 = modMult(bigInteger6, bigInteger7);
                if (bigInteger3.testBit(r0)) {
                    bigInteger7 = modMult(bigInteger6, bigInteger2);
                    bigInteger4 = modMult(bigInteger4, bigInteger8);
                    bigInteger5 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger8 = modReduce(bigInteger8.multiply(bigInteger8).subtract(bigInteger7.shiftLeft(1)));
                } else {
                    bigInteger4 = modReduce(bigInteger4.multiply(bigInteger5).subtract(bigInteger6));
                    BigInteger modReduce = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger5 = modReduce(bigInteger5.multiply(bigInteger5).subtract(bigInteger6.shiftLeft(1)));
                    bigInteger8 = modReduce;
                    bigInteger7 = bigInteger6;
                }
            }
            BigInteger modMult = modMult(bigInteger6, bigInteger7);
            BigInteger modMult2 = modMult(modMult, bigInteger2);
            BigInteger modReduce2 = modReduce(bigInteger4.multiply(bigInteger5).subtract(modMult));
            BigInteger modReduce3 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(modMult)));
            BigInteger modMult3 = modMult(modMult, modMult2);
            for (int r12 = 1; r12 <= lowestSetBit; r12++) {
                modReduce2 = modMult(modReduce2, modReduce3);
                modReduce3 = modReduce(modReduce3.multiply(modReduce3).subtract(modMult3.shiftLeft(1)));
                modMult3 = modMult(modMult3, modMult3);
            }
            return new BigInteger[]{modReduce2, modReduce3};
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new C5336Fp(this.f2264q, this.f2265r, modAdd(this.f2266x, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement addOne() {
            BigInteger add = this.f2266x.add(ECConstants.ONE);
            if (add.compareTo(this.f2264q) == 0) {
                add = ECConstants.ZERO;
            }
            return new C5336Fp(this.f2264q, this.f2265r, add);
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new C5336Fp(this.f2264q, this.f2265r, modMult(this.f2266x, modInverse(eCFieldElement.toBigInteger())));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C5336Fp) {
                C5336Fp c5336Fp = (C5336Fp) obj;
                return this.f2264q.equals(c5336Fp.f2264q) && this.f2266x.equals(c5336Fp.f2266x);
            }
            return false;
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public String getFieldName() {
            return "Fp";
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public int getFieldSize() {
            return this.f2264q.bitLength();
        }

        public BigInteger getQ() {
            return this.f2264q;
        }

        public int hashCode() {
            return this.f2264q.hashCode() ^ this.f2266x.hashCode();
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement invert() {
            return new C5336Fp(this.f2264q, this.f2265r, modInverse(this.f2266x));
        }

        protected BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            return add.compareTo(this.f2264q) >= 0 ? add.subtract(this.f2264q) : add;
        }

        protected BigInteger modDouble(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            return shiftLeft.compareTo(this.f2264q) >= 0 ? shiftLeft.subtract(this.f2264q) : shiftLeft;
        }

        protected BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f2264q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f2264q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modInverse(BigInteger bigInteger) {
            return BigIntegers.modOddInverse(this.f2264q, bigInteger);
        }

        protected BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return modReduce(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger modReduce(BigInteger bigInteger) {
            if (this.f2265r != null) {
                boolean z = bigInteger.signum() < 0;
                if (z) {
                    bigInteger = bigInteger.abs();
                }
                int bitLength = this.f2264q.bitLength();
                boolean equals = this.f2265r.equals(ECConstants.ONE);
                while (bigInteger.bitLength() > bitLength + 1) {
                    BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                    BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                    if (!equals) {
                        shiftRight = shiftRight.multiply(this.f2265r);
                    }
                    bigInteger = shiftRight.add(subtract);
                }
                while (bigInteger.compareTo(this.f2264q) >= 0) {
                    bigInteger = bigInteger.subtract(this.f2264q);
                }
                return (!z || bigInteger.signum() == 0) ? bigInteger : this.f2264q.subtract(bigInteger);
            }
            return bigInteger.mod(this.f2264q);
        }

        protected BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger subtract = bigInteger.subtract(bigInteger2);
            return subtract.signum() < 0 ? subtract.add(this.f2264q) : subtract;
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new C5336Fp(this.f2264q, this.f2265r, modMult(this.f2266x, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f2266x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C5336Fp(this.f2264q, this.f2265r, modReduce(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f2266x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C5336Fp(this.f2264q, this.f2265r, modReduce(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement negate() {
            if (this.f2266x.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f2264q;
            return new C5336Fp(bigInteger, this.f2265r, bigInteger.subtract(this.f2266x));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f2264q.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f2264q.testBit(1)) {
                BigInteger add = this.f2264q.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f2264q;
                return checkSqrt(new C5336Fp(bigInteger, this.f2265r, this.f2266x.modPow(add, bigInteger)));
            } else if (this.f2264q.testBit(2)) {
                BigInteger modPow = this.f2266x.modPow(this.f2264q.shiftRight(3), this.f2264q);
                BigInteger modMult = modMult(modPow, this.f2266x);
                if (modMult(modMult, modPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new C5336Fp(this.f2264q, this.f2265r, modMult));
                }
                return checkSqrt(new C5336Fp(this.f2264q, this.f2265r, modMult(modMult, ECConstants.TWO.modPow(this.f2264q.shiftRight(2), this.f2264q))));
            } else {
                BigInteger shiftRight = this.f2264q.shiftRight(1);
                if (!this.f2266x.modPow(shiftRight, this.f2264q).equals(ECConstants.ONE)) {
                    return null;
                }
                BigInteger bigInteger2 = this.f2266x;
                BigInteger modDouble = modDouble(modDouble(bigInteger2));
                BigInteger add2 = shiftRight.add(ECConstants.ONE);
                BigInteger subtract = this.f2264q.subtract(ECConstants.ONE);
                Random random = new Random();
                while (true) {
                    BigInteger bigInteger3 = new BigInteger(this.f2264q.bitLength(), random);
                    if (bigInteger3.compareTo(this.f2264q) < 0 && modReduce(bigInteger3.multiply(bigInteger3).subtract(modDouble)).modPow(shiftRight, this.f2264q).equals(subtract)) {
                        BigInteger[] lucasSequence = lucasSequence(bigInteger3, bigInteger2, add2);
                        BigInteger bigInteger4 = lucasSequence[0];
                        BigInteger bigInteger5 = lucasSequence[1];
                        if (modMult(bigInteger5, bigInteger5).equals(modDouble)) {
                            return new C5336Fp(this.f2264q, this.f2265r, modHalfAbs(bigInteger5));
                        }
                        if (!bigInteger4.equals(ECConstants.ONE) && !bigInteger4.equals(subtract)) {
                            return null;
                        }
                    }
                }
            }
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement square() {
            BigInteger bigInteger = this.f2264q;
            BigInteger bigInteger2 = this.f2265r;
            BigInteger bigInteger3 = this.f2266x;
            return new C5336Fp(bigInteger, bigInteger2, modMult(bigInteger3, bigInteger3));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f2266x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C5336Fp(this.f2264q, this.f2265r, modReduce(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f2266x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C5336Fp(this.f2264q, this.f2265r, modReduce(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new C5336Fp(this.f2264q, this.f2265r, modSubtract(this.f2266x, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.p039ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f2266x;
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int r3) {
        ECFieldElement eCFieldElement = this;
        for (int r0 = 0; r0 < r3; r0++) {
            eCFieldElement = eCFieldElement.square();
        }
        return eCFieldElement;
    }

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public abstract BigInteger toBigInteger();

    public String toString() {
        return toBigInteger().toString(16);
    }
}
