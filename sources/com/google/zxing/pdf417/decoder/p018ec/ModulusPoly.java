package com.google.zxing.pdf417.decoder.p018ec;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.zxing.pdf417.decoder.ec.ModulusPoly */
/* loaded from: classes3.dex */
public final class ModulusPoly {
    private final int[] coefficients;
    private final ModulusGF field;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly(ModulusGF modulusGF, int[] r6) {
        if (r6.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = modulusGF;
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

    int[] getCoefficients() {
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
    public int evaluateAt(int r6) {
        if (r6 == 0) {
            return getCoefficient(0);
        }
        if (r6 == 1) {
            int r2 = 0;
            for (int r3 : this.coefficients) {
                r2 = this.field.add(r2, r3);
            }
            return r2;
        }
        int[] r22 = this.coefficients;
        int r0 = r22[0];
        int length = r22.length;
        for (int r1 = 1; r1 < length; r1++) {
            ModulusGF modulusGF = this.field;
            r0 = modulusGF.add(modulusGF.multiply(r6, r0), this.coefficients[r1]);
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly add(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (isZero()) {
            return modulusPoly;
        }
        if (modulusPoly.isZero()) {
            return this;
        }
        int[] r0 = this.coefficients;
        int[] r9 = modulusPoly.coefficients;
        if (r0.length <= r9.length) {
            r0 = r9;
            r9 = r0;
        }
        int[] r1 = new int[r0.length];
        int length = r0.length - r9.length;
        System.arraycopy(r0, 0, r1, 0, length);
        for (int r3 = length; r3 < r0.length; r3++) {
            r1[r3] = this.field.add(r9[r3 - length], r0[r3]);
        }
        return new ModulusPoly(this.field, r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly subtract(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            return modulusPoly.isZero() ? this : add(modulusPoly.negative());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly multiply(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (isZero() || modulusPoly.isZero()) {
            return this.field.getZero();
        }
        int[] r0 = this.coefficients;
        int length = r0.length;
        int[] r13 = modulusPoly.coefficients;
        int length2 = r13.length;
        int[] r3 = new int[(length + length2) - 1];
        for (int r5 = 0; r5 < length; r5++) {
            int r6 = r0[r5];
            for (int r7 = 0; r7 < length2; r7++) {
                int r8 = r5 + r7;
                ModulusGF modulusGF = this.field;
                r3[r8] = modulusGF.add(r3[r8], modulusGF.multiply(r6, r13[r7]));
            }
        }
        return new ModulusPoly(this.field, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly negative() {
        int length = this.coefficients.length;
        int[] r1 = new int[length];
        for (int r3 = 0; r3 < length; r3++) {
            r1[r3] = this.field.subtract(0, this.coefficients[r3]);
        }
        return new ModulusPoly(this.field, r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly multiply(int r6) {
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
        return new ModulusPoly(this.field, r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly multiplyByMonomial(int r5, int r6) {
        if (r5 >= 0) {
            if (r6 == 0) {
                return this.field.getZero();
            }
            int length = this.coefficients.length;
            int[] r52 = new int[r5 + length];
            for (int r1 = 0; r1 < length; r1++) {
                r52[r1] = this.field.multiply(this.coefficients[r1], r6);
            }
            return new ModulusPoly(this.field, r52);
        }
        throw new IllegalArgumentException();
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
                    sb.append(coefficient);
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
