package com.google.zxing.common.reedsolomon;

/* loaded from: classes3.dex */
public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.field = genericGF;
    }

    public void decode(int[] r9, int r10) throws ReedSolomonException {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.field, r9);
        int[] r1 = new int[r10];
        boolean z = true;
        for (int r4 = 0; r4 < r10; r4++) {
            GenericGF genericGF = this.field;
            int evaluateAt = genericGFPoly.evaluateAt(genericGF.exp(genericGF.getGeneratorBase() + r4));
            r1[(r10 - 1) - r4] = evaluateAt;
            if (evaluateAt != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        GenericGFPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(r10, 1), new GenericGFPoly(this.field, r1), r10);
        GenericGFPoly genericGFPoly2 = runEuclideanAlgorithm[0];
        GenericGFPoly genericGFPoly3 = runEuclideanAlgorithm[1];
        int[] findErrorLocations = findErrorLocations(genericGFPoly2);
        int[] findErrorMagnitudes = findErrorMagnitudes(genericGFPoly3, findErrorLocations);
        for (int r2 = 0; r2 < findErrorLocations.length; r2++) {
            int length = (r9.length - 1) - this.field.log(findErrorLocations[r2]);
            if (length < 0) {
                throw new ReedSolomonException("Bad error location");
            }
            r9[length] = GenericGF.addOrSubtract(r9[length], findErrorMagnitudes[r2]);
        }
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, int r12) throws ReedSolomonException {
        if (genericGFPoly.getDegree() < genericGFPoly2.getDegree()) {
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly2;
        }
        GenericGFPoly zero = this.field.getZero();
        GenericGFPoly one = this.field.getOne();
        do {
            GenericGFPoly genericGFPoly3 = genericGFPoly2;
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly3;
            GenericGFPoly genericGFPoly4 = one;
            GenericGFPoly genericGFPoly5 = zero;
            zero = genericGFPoly4;
            if (genericGFPoly.getDegree() >= r12 / 2) {
                if (genericGFPoly.isZero()) {
                    throw new ReedSolomonException("r_{i-1} was zero");
                }
                GenericGFPoly zero2 = this.field.getZero();
                int inverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
                while (genericGFPoly2.getDegree() >= genericGFPoly.getDegree() && !genericGFPoly2.isZero()) {
                    int degree = genericGFPoly2.getDegree() - genericGFPoly.getDegree();
                    int multiply = this.field.multiply(genericGFPoly2.getCoefficient(genericGFPoly2.getDegree()), inverse);
                    zero2 = zero2.addOrSubtract(this.field.buildMonomial(degree, multiply));
                    genericGFPoly2 = genericGFPoly2.addOrSubtract(genericGFPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).addOrSubtract(genericGFPoly5);
            } else {
                int coefficient = zero.getCoefficient(0);
                if (coefficient == 0) {
                    throw new ReedSolomonException("sigmaTilde(0) was zero");
                }
                int inverse2 = this.field.inverse(coefficient);
                return new GenericGFPoly[]{zero.multiply(inverse2), genericGFPoly.multiply(inverse2)};
            }
        } while (genericGFPoly2.getDegree() < genericGFPoly.getDegree());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    private int[] findErrorLocations(GenericGFPoly genericGFPoly) throws ReedSolomonException {
        int degree = genericGFPoly.getDegree();
        int r1 = 0;
        if (degree == 1) {
            return new int[]{genericGFPoly.getCoefficient(1)};
        }
        int[] r3 = new int[degree];
        for (int r2 = 1; r2 < this.field.getSize() && r1 < degree; r2++) {
            if (genericGFPoly.evaluateAt(r2) == 0) {
                r3[r1] = this.field.inverse(r2);
                r1++;
            }
        }
        if (r1 == degree) {
            return r3;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GenericGFPoly genericGFPoly, int[] r11) {
        int length = r11.length;
        int[] r1 = new int[length];
        for (int r3 = 0; r3 < length; r3++) {
            int inverse = this.field.inverse(r11[r3]);
            int r5 = 1;
            for (int r6 = 0; r6 < length; r6++) {
                if (r3 != r6) {
                    int multiply = this.field.multiply(r11[r6], inverse);
                    r5 = this.field.multiply(r5, (multiply & 1) == 0 ? multiply | 1 : multiply & (-2));
                }
            }
            r1[r3] = this.field.multiply(genericGFPoly.evaluateAt(inverse), this.field.inverse(r5));
            if (this.field.getGeneratorBase() != 0) {
                r1[r3] = this.field.multiply(r1[r3], inverse);
            }
        }
        return r1;
    }
}
