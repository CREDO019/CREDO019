package org.bouncycastle.pqc.math.linearalgebra;

/* loaded from: classes4.dex */
public class PolynomialRingGF2m {
    private GF2mField field;

    /* renamed from: p */
    private PolynomialGF2mSmallM f2536p;
    protected PolynomialGF2mSmallM[] sqMatrix;
    protected PolynomialGF2mSmallM[] sqRootMatrix;

    public PolynomialRingGF2m(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.field = gF2mField;
        this.f2536p = polynomialGF2mSmallM;
        computeSquaringMatrix();
        computeSquareRootMatrix();
    }

    private void computeSquareRootMatrix() {
        int coefficient;
        int degree = this.f2536p.getDegree();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArr = new PolynomialGF2mSmallM[degree];
        int r2 = degree - 1;
        for (int r3 = r2; r3 >= 0; r3--) {
            polynomialGF2mSmallMArr[r3] = new PolynomialGF2mSmallM(this.sqMatrix[r3]);
        }
        this.sqRootMatrix = new PolynomialGF2mSmallM[degree];
        while (r2 >= 0) {
            this.sqRootMatrix[r2] = new PolynomialGF2mSmallM(this.field, r2);
            r2--;
        }
        for (int r32 = 0; r32 < degree; r32++) {
            if (polynomialGF2mSmallMArr[r32].getCoefficient(r32) == 0) {
                int r4 = r32 + 1;
                boolean z = false;
                while (r4 < degree) {
                    if (polynomialGF2mSmallMArr[r4].getCoefficient(r32) != 0) {
                        swapColumns(polynomialGF2mSmallMArr, r32, r4);
                        swapColumns(this.sqRootMatrix, r32, r4);
                        r4 = degree;
                        z = true;
                    }
                    r4++;
                }
                if (!z) {
                    throw new ArithmeticException("Squaring matrix is not invertible.");
                }
            }
            int inverse = this.field.inverse(polynomialGF2mSmallMArr[r32].getCoefficient(r32));
            polynomialGF2mSmallMArr[r32].multThisWithElement(inverse);
            this.sqRootMatrix[r32].multThisWithElement(inverse);
            for (int r42 = 0; r42 < degree; r42++) {
                if (r42 != r32 && (coefficient = polynomialGF2mSmallMArr[r42].getCoefficient(r32)) != 0) {
                    PolynomialGF2mSmallM multWithElement = polynomialGF2mSmallMArr[r32].multWithElement(coefficient);
                    PolynomialGF2mSmallM multWithElement2 = this.sqRootMatrix[r32].multWithElement(coefficient);
                    polynomialGF2mSmallMArr[r42].addToThis(multWithElement);
                    this.sqRootMatrix[r42].addToThis(multWithElement2);
                }
            }
        }
    }

    private void computeSquaringMatrix() {
        int r2;
        int degree = this.f2536p.getDegree();
        this.sqMatrix = new PolynomialGF2mSmallM[degree];
        int r1 = 0;
        while (true) {
            r2 = degree >> 1;
            if (r1 >= r2) {
                break;
            }
            int r22 = r1 << 1;
            int[] r4 = new int[r22 + 1];
            r4[r22] = 1;
            this.sqMatrix[r1] = new PolynomialGF2mSmallM(this.field, r4);
            r1++;
        }
        while (r2 < degree) {
            int r12 = r2 << 1;
            int[] r42 = new int[r12 + 1];
            r42[r12] = 1;
            this.sqMatrix[r2] = new PolynomialGF2mSmallM(this.field, r42).mod(this.f2536p);
            r2++;
        }
    }

    private static void swapColumns(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr, int r3, int r4) {
        PolynomialGF2mSmallM polynomialGF2mSmallM = polynomialGF2mSmallMArr[r3];
        polynomialGF2mSmallMArr[r3] = polynomialGF2mSmallMArr[r4];
        polynomialGF2mSmallMArr[r4] = polynomialGF2mSmallM;
    }

    public PolynomialGF2mSmallM[] getSquareRootMatrix() {
        return this.sqRootMatrix;
    }

    public PolynomialGF2mSmallM[] getSquaringMatrix() {
        return this.sqMatrix;
    }
}
