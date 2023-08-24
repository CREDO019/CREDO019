package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.commons.codec.language.p027bm.Rule;

/* loaded from: classes4.dex */
public class GF2nONBField extends GF2nField {
    private static final int MAXLONG = 64;
    private int mBit;
    private int mLength;
    int[][] mMult;
    private int mType;

    public GF2nONBField(int r5, SecureRandom secureRandom) throws RuntimeException {
        super(secureRandom);
        if (r5 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = r5;
        this.mLength = this.mDegree / 64;
        int r52 = this.mDegree & 63;
        this.mBit = r52;
        if (r52 == 0) {
            this.mBit = 64;
        } else {
            this.mLength++;
        }
        computeType();
        if (this.mType >= 3) {
            throw new RuntimeException("\nThe type of this field is " + this.mType);
        }
        this.mMult = (int[][]) Array.newInstance(int.class, this.mDegree, 2);
        for (int r53 = 0; r53 < this.mDegree; r53++) {
            int[][] r0 = this.mMult;
            r0[r53][0] = -1;
            r0[r53][1] = -1;
        }
        computeMultMatrix();
        computeFieldPolynomial();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    private void computeMultMatrix() {
        int r8;
        int r0 = this.mType;
        if ((r0 & 7) == 0) {
            throw new RuntimeException("bisher nur fuer Gausssche Normalbasen implementiert");
        }
        int r02 = (r0 * this.mDegree) + 1;
        int[] r2 = new int[r02];
        int r3 = this.mType;
        int elementOfOrder = r3 == 1 ? 1 : r3 == 2 ? r02 - 1 : elementOfOrder(r3, r02);
        int r6 = 0;
        int r7 = 1;
        while (true) {
            r8 = this.mType;
            if (r6 >= r8) {
                break;
            }
            int r9 = r7;
            for (int r82 = 0; r82 < this.mDegree; r82++) {
                r2[r9] = r82;
                r9 = (r9 << 1) % r02;
                if (r9 < 0) {
                    r9 += r02;
                }
            }
            r7 = (r7 * elementOfOrder) % r02;
            if (r7 < 0) {
                r7 += r02;
            }
            r6++;
        }
        if (r8 != 1) {
            if (r8 != 2) {
                throw new RuntimeException("only type 1 or type 2 implemented");
            }
            int r4 = 1;
            while (r4 < r02 - 1) {
                int[][] r62 = this.mMult;
                int r72 = r4 + 1;
                if (r62[r2[r72]][0] == -1) {
                    r62[r2[r72]][0] = r2[r02 - r4];
                } else {
                    r62[r2[r72]][1] = r2[r02 - r4];
                }
                r4 = r72;
            }
            return;
        }
        int r42 = 1;
        while (r42 < r02 - 1) {
            int[][] r63 = this.mMult;
            int r73 = r42 + 1;
            if (r63[r2[r73]][0] == -1) {
                r63[r2[r73]][0] = r2[r02 - r42];
            } else {
                r63[r2[r73]][1] = r2[r02 - r42];
            }
            r42 = r73;
        }
        int r03 = this.mDegree >> 1;
        for (int r22 = 1; r22 <= r03; r22++) {
            int[][] r43 = this.mMult;
            int r64 = r22 - 1;
            if (r43[r64][0] == -1) {
                r43[r64][0] = (r03 + r22) - 1;
            } else {
                r43[r64][1] = (r03 + r22) - 1;
            }
            int r74 = (r03 + r22) - 1;
            if (r43[r74][0] == -1) {
                r43[r74][0] = r64;
            } else {
                r43[r74][1] = r64;
            }
        }
    }

    private void computeType() throws RuntimeException {
        if ((this.mDegree & 7) == 0) {
            throw new RuntimeException("The extension degree is divisible by 8!");
        }
        this.mType = 1;
        int r1 = 0;
        while (r1 != 1) {
            int r3 = (this.mType * this.mDegree) + 1;
            if (IntegerFunctions.isPrime(r3)) {
                r1 = IntegerFunctions.gcd((this.mType * this.mDegree) / IntegerFunctions.order(2, r3), this.mDegree);
            }
            this.mType++;
        }
        int r12 = this.mType - 1;
        this.mType = r12;
        if (r12 == 1) {
            int r13 = (this.mDegree << 1) + 1;
            if (IntegerFunctions.isPrime(r13)) {
                if (IntegerFunctions.gcd((this.mDegree << 1) / IntegerFunctions.order(2, r13), this.mDegree) == 1) {
                    this.mType++;
                }
            }
        }
    }

    private int elementOfOrder(int r5, int r6) {
        int order;
        Random random = new Random();
        int r1 = 0;
        while (r1 == 0) {
            int r2 = r6 - 1;
            r1 = random.nextInt() % r2;
            if (r1 < 0) {
                r1 += r2;
            }
        }
        while (true) {
            order = IntegerFunctions.order(r1, r6);
            if (order % r5 == 0 && order != 0) {
                break;
            }
            while (r1 == 0) {
                int r22 = r6 - 1;
                r1 = random.nextInt() % r22;
                if (r1 < 0) {
                    r1 += r22;
                }
            }
        }
        int r0 = r1;
        for (int r62 = 2; r62 <= r5 / order; r62++) {
            r0 *= r1;
        }
        return r0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected void computeCOBMatrix(GF2nField gF2nField) {
        GF2nElement randomRoot;
        if (this.mDegree != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[this.mDegree];
        for (int r2 = 0; r2 < this.mDegree; r2++) {
            gF2PolynomialArr[r2] = new GF2Polynomial(this.mDegree);
        }
        do {
            randomRoot = gF2nField.getRandomRoot(this.fieldPolynomial);
        } while (randomRoot.isZero());
        GF2nElement[] gF2nElementArr = new GF2nPolynomialElement[this.mDegree];
        gF2nElementArr[0] = (GF2nElement) randomRoot.clone();
        for (int r4 = 1; r4 < this.mDegree; r4++) {
            gF2nElementArr[r4] = gF2nElementArr[r4 - 1].square();
        }
        for (int r42 = 0; r42 < this.mDegree; r42++) {
            for (int r5 = 0; r5 < this.mDegree; r5++) {
                if (gF2nElementArr[r42].testBit(r5)) {
                    gF2PolynomialArr[(this.mDegree - r5) - 1].setBit((this.mDegree - r42) - 1);
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
        GF2Polynomial gF2Polynomial;
        int r0 = this.mType;
        if (r0 == 1) {
            gF2Polynomial = new GF2Polynomial(this.mDegree + 1, Rule.ALL);
        } else if (r0 != 2) {
            return;
        } else {
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree + 1, "ONE");
            GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this.mDegree + 1, "X");
            gF2Polynomial3.addToThis(gF2Polynomial2);
            GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
            gF2Polynomial = gF2Polynomial3;
            int r2 = 1;
            while (r2 < this.mDegree) {
                GF2Polynomial shiftLeft = gF2Polynomial.shiftLeft();
                shiftLeft.addToThis(gF2Polynomial4);
                r2++;
                gF2Polynomial4 = gF2Polynomial;
                gF2Polynomial = shiftLeft;
            }
        }
        this.fieldPolynomial = gF2Polynomial;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getONBBit() {
        return this.mBit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getONBLength() {
        return this.mLength;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        while (gF2nPolynomial.getDegree() > 1) {
            while (true) {
                GF2nONBElement gF2nONBElement = new GF2nONBElement(this, this.random);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nONBElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nONBElement);
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

    int[][] invMatrix(int[][] r7) {
        int[][] r1 = (int[][]) Array.newInstance(int.class, this.mDegree, this.mDegree);
        int[][] r0 = (int[][]) Array.newInstance(int.class, this.mDegree, this.mDegree);
        for (int r12 = 0; r12 < this.mDegree; r12++) {
            r0[r12][r12] = 1;
        }
        for (int r2 = 0; r2 < this.mDegree; r2++) {
            for (int r02 = r2; r02 < this.mDegree; r02++) {
                r7[(this.mDegree - 1) - r2][r02] = r7[r2][r2];
            }
        }
        return null;
    }
}
