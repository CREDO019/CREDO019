package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;

/* loaded from: classes4.dex */
public final class GoppaCode {

    /* loaded from: classes4.dex */
    public static class MaMaPe {

        /* renamed from: h */
        private GF2Matrix f2531h;

        /* renamed from: p */
        private Permutation f2532p;

        /* renamed from: s */
        private GF2Matrix f2533s;

        public MaMaPe(GF2Matrix gF2Matrix, GF2Matrix gF2Matrix2, Permutation permutation) {
            this.f2533s = gF2Matrix;
            this.f2531h = gF2Matrix2;
            this.f2532p = permutation;
        }

        public GF2Matrix getFirstMatrix() {
            return this.f2533s;
        }

        public Permutation getPermutation() {
            return this.f2532p;
        }

        public GF2Matrix getSecondMatrix() {
            return this.f2531h;
        }
    }

    /* loaded from: classes4.dex */
    public static class MatrixSet {

        /* renamed from: g */
        private GF2Matrix f2534g;
        private int[] setJ;

        public MatrixSet(GF2Matrix gF2Matrix, int[] r2) {
            this.f2534g = gF2Matrix;
            this.setJ = r2;
        }

        public GF2Matrix getG() {
            return this.f2534g;
        }

        public int[] getSetJ() {
            return this.setJ;
        }
    }

    private GoppaCode() {
    }

    public static MaMaPe computeSystematicForm(GF2Matrix gF2Matrix, SecureRandom secureRandom) {
        Permutation permutation;
        GF2Matrix gF2Matrix2;
        GF2Matrix leftSubMatrix;
        boolean z;
        int numColumns = gF2Matrix.getNumColumns();
        GF2Matrix gF2Matrix3 = null;
        do {
            permutation = new Permutation(numColumns, secureRandom);
            gF2Matrix2 = (GF2Matrix) gF2Matrix.rightMultiply(permutation);
            leftSubMatrix = gF2Matrix2.getLeftSubMatrix();
            z = true;
            try {
                gF2Matrix3 = (GF2Matrix) leftSubMatrix.computeInverse();
                continue;
            } catch (ArithmeticException unused) {
                z = false;
                continue;
            }
        } while (!z);
        return new MaMaPe(leftSubMatrix, ((GF2Matrix) gF2Matrix3.rightMultiply(gF2Matrix2)).getRightSubMatrix(), permutation);
    }

    public static GF2Matrix createCanonicalCheckMatrix(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int degree = gF2mField.getDegree();
        int r5 = 1 << degree;
        int degree2 = polynomialGF2mSmallM.getDegree();
        int[][] r8 = (int[][]) Array.newInstance(int.class, degree2, r5);
        int[][] r10 = (int[][]) Array.newInstance(int.class, degree2, r5);
        for (int r11 = 0; r11 < r5; r11++) {
            r10[0][r11] = gF2mField.inverse(polynomialGF2mSmallM.evaluateAt(r11));
        }
        for (int r112 = 1; r112 < degree2; r112++) {
            for (int r12 = 0; r12 < r5; r12++) {
                r10[r112][r12] = gF2mField.mult(r10[r112 - 1][r12], r12);
            }
        }
        for (int r113 = 0; r113 < degree2; r113++) {
            for (int r122 = 0; r122 < r5; r122++) {
                for (int r13 = 0; r13 <= r113; r13++) {
                    r8[r113][r122] = gF2mField.add(r8[r113][r122], gF2mField.mult(r10[r13][r122], polynomialGF2mSmallM.getCoefficient((degree2 + r13) - r113)));
                }
            }
        }
        int[][] r0 = (int[][]) Array.newInstance(int.class, degree2 * degree, (r5 + 31) >>> 5);
        for (int r2 = 0; r2 < r5; r2++) {
            int r4 = r2 >>> 5;
            int r9 = 1 << (r2 & 31);
            for (int r102 = 0; r102 < degree2; r102++) {
                int r114 = r8[r102][r2];
                for (int r123 = 0; r123 < degree; r123++) {
                    if (((r114 >>> r123) & 1) != 0) {
                        int[] r132 = r0[(((r102 + 1) * degree) - r123) - 1];
                        r132[r4] = r132[r4] ^ r9;
                    }
                }
            }
        }
        return new GF2Matrix(r5, r0);
    }

    public static GF2Vector syndromeDecode(GF2Vector gF2Vector, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int degree = 1 << gF2mField.getDegree();
        GF2Vector gF2Vector2 = new GF2Vector(degree);
        if (!gF2Vector.isZero()) {
            PolynomialGF2mSmallM[] modPolynomialToFracton = new PolynomialGF2mSmallM(gF2Vector.toExtensionFieldVector(gF2mField)).modInverse(polynomialGF2mSmallM).addMonomial(1).modSquareRootMatrix(polynomialGF2mSmallMArr).modPolynomialToFracton(polynomialGF2mSmallM);
            PolynomialGF2mSmallM add = modPolynomialToFracton[0].multiply(modPolynomialToFracton[0]).add(modPolynomialToFracton[1].multiply(modPolynomialToFracton[1]).multWithMonomial(1));
            PolynomialGF2mSmallM multWithElement = add.multWithElement(gF2mField.inverse(add.getHeadCoefficient()));
            for (int r6 = 0; r6 < degree; r6++) {
                if (multWithElement.evaluateAt(r6) == 0) {
                    gF2Vector2.setBit(r6);
                }
            }
        }
        return gF2Vector2;
    }
}
