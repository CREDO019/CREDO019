package org.bouncycastle.pqc.math.linearalgebra;

/* loaded from: classes4.dex */
public class GF2nPolynomial {
    private GF2nElement[] coeff;
    private int size;

    private GF2nPolynomial(int r1) {
        this.size = r1;
        this.coeff = new GF2nElement[r1];
    }

    public GF2nPolynomial(int r3, GF2nElement gF2nElement) {
        this.size = r3;
        this.coeff = new GF2nElement[r3];
        for (int r32 = 0; r32 < this.size; r32++) {
            this.coeff[r32] = (GF2nElement) gF2nElement.clone();
        }
    }

    public GF2nPolynomial(GF2Polynomial gF2Polynomial, GF2nField gF2nField) {
        int degree = gF2nField.getDegree() + 1;
        this.size = degree;
        this.coeff = new GF2nElement[degree];
        int r1 = 0;
        if (gF2nField instanceof GF2nONBField) {
            while (r1 < this.size) {
                if (gF2Polynomial.testBit(r1)) {
                    this.coeff[r1] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
                } else {
                    this.coeff[r1] = GF2nONBElement.ZERO((GF2nONBField) gF2nField);
                }
                r1++;
            }
        } else if (!(gF2nField instanceof GF2nPolynomialField)) {
            throw new IllegalArgumentException("PolynomialGF2n(Bitstring, GF2nField): B1 must be an instance of GF2nONBField or GF2nPolynomialField!");
        } else {
            while (r1 < this.size) {
                if (gF2Polynomial.testBit(r1)) {
                    this.coeff[r1] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
                } else {
                    this.coeff[r1] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) gF2nField);
                }
                r1++;
            }
        }
    }

    public GF2nPolynomial(GF2nPolynomial gF2nPolynomial) {
        int r0 = gF2nPolynomial.size;
        this.coeff = new GF2nElement[r0];
        this.size = r0;
        for (int r02 = 0; r02 < this.size; r02++) {
            this.coeff[r02] = (GF2nElement) gF2nPolynomial.coeff[r02].clone();
        }
    }

    public final GF2nPolynomial add(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2;
        int r2 = 0;
        if (size() >= gF2nPolynomial.size()) {
            gF2nPolynomial2 = new GF2nPolynomial(size());
            while (r2 < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[r2] = (GF2nElement) this.coeff[r2].add(gF2nPolynomial.coeff[r2]);
                r2++;
            }
            while (r2 < size()) {
                gF2nPolynomial2.coeff[r2] = this.coeff[r2];
                r2++;
            }
        } else {
            gF2nPolynomial2 = new GF2nPolynomial(gF2nPolynomial.size());
            while (r2 < size()) {
                gF2nPolynomial2.coeff[r2] = (GF2nElement) this.coeff[r2].add(gF2nPolynomial.coeff[r2]);
                r2++;
            }
            while (r2 < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[r2] = gF2nPolynomial.coeff[r2];
                r2++;
            }
        }
        return gF2nPolynomial2;
    }

    public final void assignZeroToElements() {
        for (int r0 = 0; r0 < this.size; r0++) {
            this.coeff[r0].assignZero();
        }
    }

    /* renamed from: at */
    public final GF2nElement m4at(int r2) {
        return this.coeff[r2];
    }

    public final GF2nPolynomial[] divide(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2;
        GF2nPolynomial[] gF2nPolynomialArr = new GF2nPolynomial[2];
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(this);
        gF2nPolynomial3.shrink();
        int degree = gF2nPolynomial.getDegree();
        GF2nElement gF2nElement = (GF2nElement) gF2nPolynomial.coeff[degree].invert();
        if (gF2nPolynomial3.getDegree() < degree) {
            gF2nPolynomialArr[0] = new GF2nPolynomial(this);
            gF2nPolynomialArr[0].assignZeroToElements();
            gF2nPolynomialArr[0].shrink();
            gF2nPolynomialArr[1] = new GF2nPolynomial(this);
            gF2nPolynomial2 = gF2nPolynomialArr[1];
        } else {
            gF2nPolynomialArr[0] = new GF2nPolynomial(this);
            gF2nPolynomialArr[0].assignZeroToElements();
            while (true) {
                int degree2 = gF2nPolynomial3.getDegree() - degree;
                if (degree2 < 0) {
                    break;
                }
                GF2nElement gF2nElement2 = (GF2nElement) gF2nPolynomial3.coeff[gF2nPolynomial3.getDegree()].multiply(gF2nElement);
                GF2nPolynomial scalarMultiply = gF2nPolynomial.scalarMultiply(gF2nElement2);
                scalarMultiply.shiftThisLeft(degree2);
                gF2nPolynomial3 = gF2nPolynomial3.add(scalarMultiply);
                gF2nPolynomial3.shrink();
                gF2nPolynomialArr[0].coeff[degree2] = (GF2nElement) gF2nElement2.clone();
            }
            gF2nPolynomialArr[1] = gF2nPolynomial3;
            gF2nPolynomial2 = gF2nPolynomialArr[0];
        }
        gF2nPolynomial2.shrink();
        return gF2nPolynomialArr;
    }

    public final void enlarge(int r6) {
        int r0 = this.size;
        if (r6 <= r0) {
            return;
        }
        GF2nElement[] gF2nElementArr = new GF2nElement[r6];
        System.arraycopy(this.coeff, 0, gF2nElementArr, 0, r0);
        GF2nField field = this.coeff[0].getField();
        GF2nElement[] gF2nElementArr2 = this.coeff;
        if (gF2nElementArr2[0] instanceof GF2nPolynomialElement) {
            for (int r2 = this.size; r2 < r6; r2++) {
                gF2nElementArr[r2] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
            }
        } else if (gF2nElementArr2[0] instanceof GF2nONBElement) {
            for (int r22 = this.size; r22 < r6; r22++) {
                gF2nElementArr[r22] = GF2nONBElement.ZERO((GF2nONBField) field);
            }
        }
        this.size = r6;
        this.coeff = gF2nElementArr;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nPolynomial)) {
            return false;
        }
        GF2nPolynomial gF2nPolynomial = (GF2nPolynomial) obj;
        if (getDegree() != gF2nPolynomial.getDegree()) {
            return false;
        }
        for (int r1 = 0; r1 < this.size; r1++) {
            if (!this.coeff[r1].equals(gF2nPolynomial.coeff[r1])) {
                return false;
            }
        }
        return true;
    }

    public final GF2nPolynomial gcd(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial);
        gF2nPolynomial2.shrink();
        gF2nPolynomial3.shrink();
        GF2nPolynomial gF2nPolynomial4 = gF2nPolynomial2;
        GF2nPolynomial gF2nPolynomial5 = gF2nPolynomial3;
        while (!gF2nPolynomial5.isZero()) {
            GF2nPolynomial gF2nPolynomial6 = gF2nPolynomial5;
            gF2nPolynomial5 = gF2nPolynomial4.remainder(gF2nPolynomial5);
            gF2nPolynomial4 = gF2nPolynomial6;
        }
        return gF2nPolynomial4.scalarMultiply((GF2nElement) gF2nPolynomial4.coeff[gF2nPolynomial4.getDegree()].invert());
    }

    public final int getDegree() {
        for (int r0 = this.size - 1; r0 >= 0; r0--) {
            if (!this.coeff[r0].isZero()) {
                return r0;
            }
        }
        return -1;
    }

    public int hashCode() {
        return getDegree() + this.coeff.hashCode();
    }

    public final boolean isZero() {
        for (int r1 = 0; r1 < this.size; r1++) {
            GF2nElement[] gF2nElementArr = this.coeff;
            if (gF2nElementArr[r1] != null && !gF2nElementArr[r1].isZero()) {
                return false;
            }
        }
        return true;
    }

    public final GF2nPolynomial multiply(GF2nPolynomial gF2nPolynomial) {
        int size = size();
        if (size == gF2nPolynomial.size()) {
            GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial((size << 1) - 1);
            for (int r2 = 0; r2 < size(); r2++) {
                for (int r3 = 0; r3 < gF2nPolynomial.size(); r3++) {
                    GF2nElement[] gF2nElementArr = gF2nPolynomial2.coeff;
                    int r5 = r2 + r3;
                    if (gF2nElementArr[r5] == null) {
                        gF2nElementArr[r5] = (GF2nElement) this.coeff[r2].multiply(gF2nPolynomial.coeff[r3]);
                    } else {
                        gF2nElementArr[r5] = (GF2nElement) gF2nElementArr[r5].add(this.coeff[r2].multiply(gF2nPolynomial.coeff[r3]));
                    }
                }
            }
            return gF2nPolynomial2;
        }
        throw new IllegalArgumentException("PolynomialGF2n.multiply: this and b must have the same size!");
    }

    public final GF2nPolynomial multiplyAndReduce(GF2nPolynomial gF2nPolynomial, GF2nPolynomial gF2nPolynomial2) {
        return multiply(gF2nPolynomial).reduce(gF2nPolynomial2);
    }

    public final GF2nPolynomial quotient(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return divide(gF2nPolynomial)[0];
    }

    public final GF2nPolynomial reduce(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return remainder(gF2nPolynomial);
    }

    public final GF2nPolynomial remainder(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return divide(gF2nPolynomial)[1];
    }

    public final GF2nPolynomial scalarMultiply(GF2nElement gF2nElement) {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(size());
        for (int r1 = 0; r1 < size(); r1++) {
            gF2nPolynomial.coeff[r1] = (GF2nElement) this.coeff[r1].multiply(gF2nElement);
        }
        return gF2nPolynomial;
    }

    public final void set(int r2, GF2nElement gF2nElement) {
        if (!(gF2nElement instanceof GF2nPolynomialElement) && !(gF2nElement instanceof GF2nONBElement)) {
            throw new IllegalArgumentException("PolynomialGF2n.set f must be an instance of either GF2nPolynomialElement or GF2nONBElement!");
        }
        this.coeff[r2] = (GF2nElement) gF2nElement.clone();
    }

    public final GF2nPolynomial shiftLeft(int r6) {
        if (r6 <= 0) {
            return new GF2nPolynomial(this);
        }
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(this.size + r6, this.coeff[0]);
        gF2nPolynomial.assignZeroToElements();
        for (int r3 = 0; r3 < this.size; r3++) {
            gF2nPolynomial.coeff[r3 + r6] = this.coeff[r3];
        }
        return gF2nPolynomial;
    }

    public final void shiftThisLeft(int r7) {
        if (r7 > 0) {
            int r0 = this.size;
            GF2nField field = this.coeff[0].getField();
            enlarge(this.size + r7);
            for (int r02 = r0 - 1; r02 >= 0; r02--) {
                GF2nElement[] gF2nElementArr = this.coeff;
                gF2nElementArr[r02 + r7] = gF2nElementArr[r02];
            }
            GF2nElement[] gF2nElementArr2 = this.coeff;
            if (gF2nElementArr2[0] instanceof GF2nPolynomialElement) {
                for (int r72 = r7 - 1; r72 >= 0; r72--) {
                    this.coeff[r72] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
                }
            } else if (gF2nElementArr2[0] instanceof GF2nONBElement) {
                for (int r73 = r7 - 1; r73 >= 0; r73--) {
                    this.coeff[r73] = GF2nONBElement.ZERO((GF2nONBField) field);
                }
            }
        }
    }

    public final void shrink() {
        int r0 = this.size;
        while (true) {
            r0--;
            if (!this.coeff[r0].isZero() || r0 <= 0) {
                break;
            }
        }
        int r02 = r0 + 1;
        if (r02 < this.size) {
            GF2nElement[] gF2nElementArr = new GF2nElement[r02];
            System.arraycopy(this.coeff, 0, gF2nElementArr, 0, r02);
            this.coeff = gF2nElementArr;
            this.size = r02;
        }
    }

    public final int size() {
        return this.size;
    }
}
