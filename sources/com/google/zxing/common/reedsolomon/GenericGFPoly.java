package com.google.zxing.common.reedsolomon;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly(GenericGF genericGF, int[] r6) {
        if (r6.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = genericGF;
        int length = r6.length;
        if (length > 1 && r6[0] == 0) {
            int r2 = 1;
            while (r2 < length && r6[r2] == 0) {
                r2++;
            }
            if (r2 == length) {
                this.coefficients = new int[]{0};
                return;
            }
            int[] r5 = new int[length - r2];
            this.coefficients = r5;
            System.arraycopy(r6, r2, r5, 0, r5.length);
            return;
        }
        this.coefficients = r6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] getCoefficients() {
        return this.coefficients;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDegree() {
        return this.coefficients.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCoefficient(int r3) {
        int[] r0 = this.coefficients;
        return r0[(r0.length - 1) - r3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int evaluateAt(int r5) {
        if (r5 == 0) {
            return getCoefficient(0);
        }
        if (r5 == 1) {
            int r2 = 0;
            for (int r3 : this.coefficients) {
                r2 = GenericGF.addOrSubtract(r2, r3);
            }
            return r2;
        }
        int[] r22 = this.coefficients;
        int r0 = r22[0];
        int length = r22.length;
        for (int r1 = 1; r1 < length; r1++) {
            r0 = GenericGF.addOrSubtract(this.field.multiply(r5, r0), this.coefficients[r1]);
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly addOrSubtract(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero()) {
            return genericGFPoly;
        }
        if (genericGFPoly.isZero()) {
            return this;
        }
        int[] r0 = this.coefficients;
        int[] r8 = genericGFPoly.coefficients;
        if (r0.length <= r8.length) {
            r0 = r8;
            r8 = r0;
        }
        int[] r1 = new int[r0.length];
        int length = r0.length - r8.length;
        System.arraycopy(r0, 0, r1, 0, length);
        for (int r3 = length; r3 < r0.length; r3++) {
            r1[r3] = GenericGF.addOrSubtract(r8[r3 - length], r0[r3]);
        }
        return new GenericGFPoly(this.field, r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly multiply(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero() || genericGFPoly.isZero()) {
            return this.field.getZero();
        }
        int[] r0 = this.coefficients;
        int length = r0.length;
        int[] r13 = genericGFPoly.coefficients;
        int length2 = r13.length;
        int[] r3 = new int[(length + length2) - 1];
        for (int r5 = 0; r5 < length; r5++) {
            int r6 = r0[r5];
            for (int r7 = 0; r7 < length2; r7++) {
                int r8 = r5 + r7;
                r3[r8] = GenericGF.addOrSubtract(r3[r8], this.field.multiply(r6, r13[r7]));
            }
        }
        return new GenericGFPoly(this.field, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly multiply(int r6) {
        if (r6 == 0) {
            return this.field.getZero();
        }
        if (r6 == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] r1 = new int[length];
        for (int r2 = 0; r2 < length; r2++) {
            r1[r2] = this.field.multiply(this.coefficients[r2], r6);
        }
        return new GenericGFPoly(this.field, r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly multiplyByMonomial(int r5, int r6) {
        if (r5 >= 0) {
            if (r6 == 0) {
                return this.field.getZero();
            }
            int length = this.coefficients.length;
            int[] r52 = new int[r5 + length];
            for (int r1 = 0; r1 < length; r1++) {
                r52[r1] = this.field.multiply(this.coefficients[r1], r6);
            }
            return new GenericGFPoly(this.field, r52);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly[] divide(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (genericGFPoly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        GenericGFPoly zero = this.field.getZero();
        int inverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
        GenericGFPoly genericGFPoly2 = this;
        while (genericGFPoly2.getDegree() >= genericGFPoly.getDegree() && !genericGFPoly2.isZero()) {
            int degree = genericGFPoly2.getDegree() - genericGFPoly.getDegree();
            int multiply = this.field.multiply(genericGFPoly2.getCoefficient(genericGFPoly2.getDegree()), inverse);
            GenericGFPoly multiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, multiply);
            zero = zero.addOrSubtract(this.field.buildMonomial(degree, multiply));
            genericGFPoly2 = genericGFPoly2.addOrSubtract(multiplyByMonomial);
        }
        return new GenericGFPoly[]{zero, genericGFPoly2};
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int log = this.field.log(coefficient);
                    if (log == 0) {
                        sb.append('1');
                    } else if (log == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(log);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(degree);
                    }
                }
            }
        }
        return sb.toString();
    }
}
