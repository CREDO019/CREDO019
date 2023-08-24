package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class GF2nPolynomialField extends GF2nField {
    private boolean isPentanomial;
    private boolean isTrinomial;

    /* renamed from: pc */
    private int[] f2529pc;
    GF2Polynomial[] squaringMatrix;

    /* renamed from: tc */
    private int f2530tc;

    public GF2nPolynomialField(int r2, SecureRandom secureRandom) {
        super(secureRandom);
        this.isTrinomial = false;
        this.isPentanomial = false;
        this.f2529pc = new int[3];
        if (r2 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = r2;
        computeFieldPolynomial();
        computeSquaringMatrix();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    public GF2nPolynomialField(int r4, SecureRandom secureRandom, GF2Polynomial gF2Polynomial) throws RuntimeException {
        super(secureRandom);
        this.isTrinomial = false;
        this.isPentanomial = false;
        this.f2529pc = new int[3];
        if (r4 < 3) {
            throw new IllegalArgumentException("degree must be at least 3");
        }
        if (gF2Polynomial.getLength() != r4 + 1) {
            throw new RuntimeException();
        }
        if (!gF2Polynomial.isIrreducible()) {
            throw new RuntimeException();
        }
        this.mDegree = r4;
        this.fieldPolynomial = gF2Polynomial;
        computeSquaringMatrix();
        int r42 = 2;
        for (int r0 = 1; r0 < this.fieldPolynomial.getLength() - 1; r0++) {
            if (this.fieldPolynomial.testBit(r0)) {
                r42++;
                if (r42 == 3) {
                    this.f2530tc = r0;
                }
                if (r42 <= 5) {
                    this.f2529pc[r42 - 3] = r0;
                }
            }
        }
        if (r42 == 3) {
            this.isTrinomial = true;
        }
        if (r42 == 5) {
            this.isPentanomial = true;
        }
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    public GF2nPolynomialField(int r2, SecureRandom secureRandom, boolean z) {
        super(secureRandom);
        this.isTrinomial = false;
        this.isPentanomial = false;
        this.f2529pc = new int[3];
        if (r2 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = r2;
        if (z) {
            computeFieldPolynomial();
        } else {
            computeFieldPolynomial2();
        }
        computeSquaringMatrix();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    private void computeSquaringMatrix() {
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[this.mDegree - 1];
        this.squaringMatrix = new GF2Polynomial[this.mDegree];
        int r3 = 0;
        while (true) {
            GF2Polynomial[] gF2PolynomialArr2 = this.squaringMatrix;
            if (r3 >= gF2PolynomialArr2.length) {
                break;
            }
            gF2PolynomialArr2[r3] = new GF2Polynomial(this.mDegree, "ZERO");
            r3++;
        }
        for (int r2 = 0; r2 < this.mDegree - 1; r2++) {
            gF2PolynomialArr[r2] = new GF2Polynomial(1, "ONE").shiftLeft(this.mDegree + r2).remainder(this.fieldPolynomial);
        }
        for (int r22 = 1; r22 <= Math.abs(this.mDegree >> 1); r22++) {
            for (int r32 = 1; r32 <= this.mDegree; r32++) {
                if (gF2PolynomialArr[this.mDegree - (r22 << 1)].testBit(this.mDegree - r32)) {
                    this.squaringMatrix[r32 - 1].setBit(this.mDegree - r22);
                }
            }
        }
        for (int abs = Math.abs(this.mDegree >> 1) + 1; abs <= this.mDegree; abs++) {
            this.squaringMatrix[((abs << 1) - this.mDegree) - 1].setBit(this.mDegree - abs);
        }
    }

    private boolean testPentanomials() {
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        this.fieldPolynomial.setBit(0);
        this.fieldPolynomial.setBit(this.mDegree);
        int r0 = 1;
        boolean z = false;
        while (r0 <= this.mDegree - 3 && !z) {
            this.fieldPolynomial.setBit(r0);
            int r4 = r0 + 1;
            int r5 = r4;
            while (r5 <= this.mDegree - 2 && !z) {
                this.fieldPolynomial.setBit(r5);
                int r6 = r5 + 1;
                for (int r8 = r6; r8 <= this.mDegree - 1 && !z; r8++) {
                    this.fieldPolynomial.setBit(r8);
                    if (((((this.mDegree & 1) != 0) | ((r0 & 1) != 0) | ((r5 & 1) != 0)) || ((r8 & 1) != 0)) && (z = this.fieldPolynomial.isIrreducible())) {
                        this.isPentanomial = true;
                        int[] r42 = this.f2529pc;
                        r42[0] = r0;
                        r42[1] = r5;
                        r42[2] = r8;
                        return z;
                    }
                    this.fieldPolynomial.resetBit(r8);
                }
                this.fieldPolynomial.resetBit(r5);
                r5 = r6;
            }
            this.fieldPolynomial.resetBit(r0);
            r0 = r4;
        }
        return z;
    }

    private boolean testRandom() {
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        do {
            this.fieldPolynomial.randomize();
            this.fieldPolynomial.setBit(this.mDegree);
            this.fieldPolynomial.setBit(0);
        } while (!this.fieldPolynomial.isIrreducible());
        return true;
    }

    private boolean testTrinomials() {
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        boolean z = false;
        this.fieldPolynomial.setBit(0);
        this.fieldPolynomial.setBit(this.mDegree);
        for (int r0 = 1; r0 < this.mDegree && !z; r0++) {
            this.fieldPolynomial.setBit(r0);
            boolean isIrreducible = this.fieldPolynomial.isIrreducible();
            if (isIrreducible) {
                this.isTrinomial = true;
                this.f2530tc = r0;
                return isIrreducible;
            }
            this.fieldPolynomial.resetBit(r0);
            z = this.fieldPolynomial.isIrreducible();
        }
        return z;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected void computeCOBMatrix(GF2nField gF2nField) {
        GF2nElement randomRoot;
        GF2nElement[] gF2nElementArr;
        if (this.mDegree != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nPolynomialField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        boolean z = gF2nField instanceof GF2nONBField;
        if (z) {
            gF2nField.computeCOBMatrix(this);
            return;
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[this.mDegree];
        for (int r3 = 0; r3 < this.mDegree; r3++) {
            gF2PolynomialArr[r3] = new GF2Polynomial(this.mDegree);
        }
        do {
            randomRoot = gF2nField.getRandomRoot(this.fieldPolynomial);
        } while (randomRoot.isZero());
        if (randomRoot instanceof GF2nONBElement) {
            gF2nElementArr = new GF2nONBElement[this.mDegree];
            gF2nElementArr[this.mDegree - 1] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
        } else {
            gF2nElementArr = new GF2nPolynomialElement[this.mDegree];
            gF2nElementArr[this.mDegree - 1] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
        }
        gF2nElementArr[this.mDegree - 2] = randomRoot;
        for (int r5 = this.mDegree - 3; r5 >= 0; r5--) {
            gF2nElementArr[r5] = (GF2nElement) gF2nElementArr[r5 + 1].multiply(randomRoot);
        }
        if (z) {
            for (int r0 = 0; r0 < this.mDegree; r0++) {
                for (int r32 = 0; r32 < this.mDegree; r32++) {
                    if (gF2nElementArr[r0].testBit((this.mDegree - r32) - 1)) {
                        gF2PolynomialArr[(this.mDegree - r32) - 1].setBit((this.mDegree - r0) - 1);
                    }
                }
            }
        } else {
            for (int r02 = 0; r02 < this.mDegree; r02++) {
                for (int r33 = 0; r33 < this.mDegree; r33++) {
                    if (gF2nElementArr[r02].testBit(r33)) {
                        gF2PolynomialArr[(this.mDegree - r33) - 1].setBit((this.mDegree - r02) - 1);
                    }
                }
            }
        }
        this.fields.addElement(gF2nField);
        this.matrices.addElement(gF2PolynomialArr);
        gF2nField.fields.addElement(this);
        gF2nField.matrices.addElement(invertMatrix(gF2PolynomialArr));
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected void computeFieldPolynomial() {
        if (testTrinomials() || testPentanomials()) {
            return;
        }
        testRandom();
    }

    protected void computeFieldPolynomial2() {
        if (testTrinomials() || testPentanomials()) {
            return;
        }
        testRandom();
    }

    public int[] getPc() throws RuntimeException {
        if (this.isPentanomial) {
            int[] r1 = new int[3];
            System.arraycopy(this.f2529pc, 0, r1, 0, 3);
            return r1;
        }
        throw new RuntimeException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        while (gF2nPolynomial.getDegree() > 1) {
            while (true) {
                GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this, this.random);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nPolynomialElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nPolynomialElement);
                GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial2);
                for (int r3 = 1; r3 <= this.mDegree - 1; r3++) {
                    gF2nPolynomial3 = gF2nPolynomial3.multiplyAndReduce(gF2nPolynomial3, gF2nPolynomial).add(gF2nPolynomial2);
                }
                gcd = gF2nPolynomial3.gcd(gF2nPolynomial);
                degree = gcd.getDegree();
                degree2 = gF2nPolynomial.getDegree();
                if (degree != 0 && degree != degree2) {
                    break;
                }
            }
            gF2nPolynomial = (degree << 1) > degree2 ? gF2nPolynomial.quotient(gcd) : new GF2nPolynomial(gcd);
        }
        return gF2nPolynomial.m4at(0);
    }

    public GF2Polynomial getSquaringVector(int r3) {
        return new GF2Polynomial(this.squaringMatrix[r3]);
    }

    public int getTc() throws RuntimeException {
        if (this.isTrinomial) {
            return this.f2530tc;
        }
        throw new RuntimeException();
    }

    public boolean isPentanomial() {
        return this.isPentanomial;
    }

    public boolean isTrinomial() {
        return this.isTrinomial;
    }
}
