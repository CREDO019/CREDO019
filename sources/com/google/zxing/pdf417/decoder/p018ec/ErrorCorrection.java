package com.google.zxing.pdf417.decoder.p018ec;

import com.google.zxing.ChecksumException;

/* renamed from: com.google.zxing.pdf417.decoder.ec.ErrorCorrection */
/* loaded from: classes3.dex */
public final class ErrorCorrection {
    private final ModulusGF field = ModulusGF.PDF417_GF;

    public int decode(int[] r11, int r12, int[] r13) throws ChecksumException {
        ModulusPoly modulusPoly = new ModulusPoly(this.field, r11);
        int[] r1 = new int[r12];
        boolean z = false;
        for (int r3 = r12; r3 > 0; r3--) {
            int evaluateAt = modulusPoly.evaluateAt(this.field.exp(r3));
            r1[r12 - r3] = evaluateAt;
            if (evaluateAt != 0) {
                z = true;
            }
        }
        if (z) {
            ModulusPoly one = this.field.getOne();
            if (r13 != null) {
                for (int r6 : r13) {
                    int exp = this.field.exp((r11.length - 1) - r6);
                    ModulusGF modulusGF = this.field;
                    one = one.multiply(new ModulusPoly(modulusGF, new int[]{modulusGF.subtract(0, exp), 1}));
                }
            }
            ModulusPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(r12, 1), new ModulusPoly(this.field, r1), r12);
            ModulusPoly modulusPoly2 = runEuclideanAlgorithm[0];
            ModulusPoly modulusPoly3 = runEuclideanAlgorithm[1];
            int[] findErrorLocations = findErrorLocations(modulusPoly2);
            int[] findErrorMagnitudes = findErrorMagnitudes(modulusPoly3, modulusPoly2, findErrorLocations);
            for (int r2 = 0; r2 < findErrorLocations.length; r2++) {
                int length = (r11.length - 1) - this.field.log(findErrorLocations[r2]);
                if (length < 0) {
                    throw ChecksumException.getChecksumInstance();
                }
                r11[length] = this.field.subtract(r11[length], findErrorMagnitudes[r2]);
            }
            return findErrorLocations.length;
        }
        return 0;
    }

    private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int r12) throws ChecksumException {
        if (modulusPoly.getDegree() < modulusPoly2.getDegree()) {
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly2;
        }
        ModulusPoly zero = this.field.getZero();
        ModulusPoly one = this.field.getOne();
        while (true) {
            ModulusPoly modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
            ModulusPoly modulusPoly4 = one;
            ModulusPoly modulusPoly5 = zero;
            zero = modulusPoly4;
            if (modulusPoly.getDegree() >= r12 / 2) {
                if (modulusPoly.isZero()) {
                    throw ChecksumException.getChecksumInstance();
                }
                ModulusPoly zero2 = this.field.getZero();
                int inverse = this.field.inverse(modulusPoly.getCoefficient(modulusPoly.getDegree()));
                while (modulusPoly2.getDegree() >= modulusPoly.getDegree() && !modulusPoly2.isZero()) {
                    int degree = modulusPoly2.getDegree() - modulusPoly.getDegree();
                    int multiply = this.field.multiply(modulusPoly2.getCoefficient(modulusPoly2.getDegree()), inverse);
                    zero2 = zero2.add(this.field.buildMonomial(degree, multiply));
                    modulusPoly2 = modulusPoly2.subtract(modulusPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).subtract(modulusPoly5).negative();
            } else {
                int coefficient = zero.getCoefficient(0);
                if (coefficient == 0) {
                    throw ChecksumException.getChecksumInstance();
                }
                int inverse2 = this.field.inverse(coefficient);
                return new ModulusPoly[]{zero.multiply(inverse2), modulusPoly.multiply(inverse2)};
            }
        }
    }

    private int[] findErrorLocations(ModulusPoly modulusPoly) throws ChecksumException {
        int degree = modulusPoly.getDegree();
        int[] r1 = new int[degree];
        int r3 = 0;
        for (int r2 = 1; r2 < this.field.getSize() && r3 < degree; r2++) {
            if (modulusPoly.evaluateAt(r2) == 0) {
                r1[r3] = this.field.inverse(r2);
                r3++;
            }
        }
        if (r3 == degree) {
            return r1;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] findErrorMagnitudes(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int[] r10) {
        int degree = modulusPoly2.getDegree();
        int[] r1 = new int[degree];
        for (int r2 = 1; r2 <= degree; r2++) {
            r1[degree - r2] = this.field.multiply(r2, modulusPoly2.getCoefficient(r2));
        }
        ModulusPoly modulusPoly3 = new ModulusPoly(this.field, r1);
        int length = r10.length;
        int[] r12 = new int[length];
        for (int r3 = 0; r3 < length; r3++) {
            int inverse = this.field.inverse(r10[r3]);
            r12[r3] = this.field.multiply(this.field.subtract(0, modulusPoly.evaluateAt(inverse)), this.field.inverse(modulusPoly3.evaluateAt(inverse)));
        }
        return r12;
    }
}
