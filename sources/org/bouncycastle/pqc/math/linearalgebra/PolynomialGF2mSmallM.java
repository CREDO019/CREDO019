package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class PolynomialGF2mSmallM {
    public static final char RANDOM_IRREDUCIBLE_POLYNOMIAL = 'I';
    private int[] coefficients;
    private int degree;
    private GF2mField field;

    public PolynomialGF2mSmallM(GF2mField gF2mField) {
        this.field = gF2mField;
        this.degree = -1;
        this.coefficients = new int[1];
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int r3) {
        this.field = gF2mField;
        this.degree = r3;
        int[] r2 = new int[r3 + 1];
        this.coefficients = r2;
        r2[r3] = 1;
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int r2, char c, SecureRandom secureRandom) {
        this.field = gF2mField;
        if (c == 'I') {
            this.coefficients = createRandomIrreduciblePolynomial(r2, secureRandom);
            computeDegree();
            return;
        }
        throw new IllegalArgumentException(" Error: type " + c + " is not defined for GF2smallmPolynomial");
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, byte[] bArr) {
        this.field = gF2mField;
        int r0 = 8;
        int r2 = 1;
        while (gF2mField.getDegree() > r0) {
            r2++;
            r0 += 8;
        }
        if (bArr.length % r2 != 0) {
            throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
        }
        this.coefficients = new int[bArr.length / r2];
        int r22 = 0;
        int r4 = 0;
        while (true) {
            int[] r5 = this.coefficients;
            if (r22 >= r5.length) {
                if (r5.length != 1 && r5[r5.length - 1] == 0) {
                    throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
                }
                computeDegree();
                return;
            }
            int r52 = 0;
            while (r52 < r0) {
                int[] r6 = this.coefficients;
                r6[r22] = ((bArr[r4] & 255) << r52) ^ r6[r22];
                r52 += 8;
                r4++;
            }
            if (!this.field.isElementOfThisField(this.coefficients[r22])) {
                throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
            }
            r22++;
        }
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int[] r2) {
        this.field = gF2mField;
        this.coefficients = normalForm(r2);
        computeDegree();
    }

    public PolynomialGF2mSmallM(GF2mVector gF2mVector) {
        this(gF2mVector.getField(), gF2mVector.getIntArrayForm());
    }

    public PolynomialGF2mSmallM(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.field = polynomialGF2mSmallM.field;
        this.degree = polynomialGF2mSmallM.degree;
        this.coefficients = IntUtils.clone(polynomialGF2mSmallM.coefficients);
    }

    private int[] add(int[] r5, int[] r6) {
        int[] r0;
        if (r5.length < r6.length) {
            r0 = new int[r6.length];
            System.arraycopy(r6, 0, r0, 0, r6.length);
        } else {
            r0 = new int[r5.length];
            System.arraycopy(r5, 0, r0, 0, r5.length);
            r5 = r6;
        }
        for (int length = r5.length - 1; length >= 0; length--) {
            r0[length] = this.field.add(r0[length], r5[length]);
        }
        return r0;
    }

    private static int computeDegree(int[] r2) {
        int length = r2.length - 1;
        while (length >= 0 && r2[length] == 0) {
            length--;
        }
        return length;
    }

    private void computeDegree() {
        int length = this.coefficients.length;
        do {
            this.degree = length - 1;
            length = this.degree;
            if (length < 0) {
                return;
            }
        } while (this.coefficients[length] == 0);
    }

    private int[] createRandomIrreduciblePolynomial(int r5, SecureRandom secureRandom) {
        int[] r0 = new int[r5 + 1];
        r0[r5] = 1;
        r0[0] = this.field.getRandomNonZeroElement(secureRandom);
        for (int r1 = 1; r1 < r5; r1++) {
            r0[r1] = this.field.getRandomElement(secureRandom);
        }
        while (!isIrreducible(r0)) {
            int nextInt = RandUtils.nextInt(secureRandom, r5);
            if (nextInt == 0) {
                r0[0] = this.field.getRandomNonZeroElement(secureRandom);
            } else {
                r0[nextInt] = this.field.getRandomElement(secureRandom);
            }
        }
        return r0;
    }

    private int[][] div(int[] r8, int[] r9) {
        int computeDegree = computeDegree(r9);
        int computeDegree2 = computeDegree(r8) + 1;
        if (computeDegree != -1) {
            int[][] r3 = {new int[1], new int[computeDegree2]};
            int inverse = this.field.inverse(headCoefficient(r9));
            r3[0][0] = 0;
            System.arraycopy(r8, 0, r3[1], 0, r3[1].length);
            while (computeDegree <= computeDegree(r3[1])) {
                int[] r82 = {this.field.mult(headCoefficient(r3[1]), inverse)};
                int[] multWithElement = multWithElement(r9, r82[0]);
                int computeDegree3 = computeDegree(r3[1]) - computeDegree;
                int[] multWithMonomial = multWithMonomial(multWithElement, computeDegree3);
                r3[0] = add(multWithMonomial(r82, computeDegree3), r3[0]);
                r3[1] = add(multWithMonomial, r3[1]);
            }
            return r3;
        }
        throw new ArithmeticException("Division by zero.");
    }

    private int[] gcd(int[] r5, int[] r6) {
        if (computeDegree(r5) == -1) {
            return r6;
        }
        while (computeDegree(r6) != -1) {
            int[] mod = mod(r5, r6);
            int length = r6.length;
            int[] r2 = new int[length];
            System.arraycopy(r6, 0, r2, 0, length);
            int length2 = mod.length;
            int[] r0 = new int[length2];
            System.arraycopy(mod, 0, r0, 0, length2);
            r6 = r0;
            r5 = r2;
        }
        return multWithElement(r5, this.field.inverse(headCoefficient(r5)));
    }

    private static int headCoefficient(int[] r2) {
        int computeDegree = computeDegree(r2);
        if (computeDegree == -1) {
            return 0;
        }
        return r2[computeDegree];
    }

    private static boolean isEqual(int[] r5, int[] r6) {
        int computeDegree = computeDegree(r5);
        if (computeDegree != computeDegree(r6)) {
            return false;
        }
        for (int r1 = 0; r1 <= computeDegree; r1++) {
            if (r5[r1] != r6[r1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isIrreducible(int[] r9) {
        if (r9[0] == 0) {
            return false;
        }
        int computeDegree = computeDegree(r9) >> 1;
        int[] r4 = {0, 1};
        int[] r3 = {0, 1};
        int degree = this.field.getDegree();
        for (int r6 = 0; r6 < computeDegree; r6++) {
            for (int r7 = degree - 1; r7 >= 0; r7--) {
                r4 = modMultiply(r4, r4, r9);
            }
            r4 = normalForm(r4);
            if (computeDegree(gcd(add(r4, r3), r9)) != 0) {
                return false;
            }
        }
        return true;
    }

    private int[] mod(int[] r6, int[] r7) {
        int computeDegree = computeDegree(r7);
        if (computeDegree != -1) {
            int length = r6.length;
            int[] r2 = new int[length];
            int inverse = this.field.inverse(headCoefficient(r7));
            System.arraycopy(r6, 0, r2, 0, length);
            while (computeDegree <= computeDegree(r2)) {
                r2 = add(multWithElement(multWithMonomial(r7, computeDegree(r2) - computeDegree), this.field.mult(headCoefficient(r2), inverse)), r2);
            }
            return r2;
        }
        throw new ArithmeticException("Division by zero");
    }

    private int[] modDiv(int[] r7, int[] r8, int[] r9) {
        int[] normalForm = normalForm(r9);
        int[] mod = mod(r8, r9);
        int[] r2 = {0};
        int[] mod2 = mod(r7, r9);
        while (computeDegree(mod) != -1) {
            int[][] div = div(normalForm, mod);
            int[] normalForm2 = normalForm(mod);
            int[] normalForm3 = normalForm(div[1]);
            int[] add = add(r2, modMultiply(div[0], mod2, r9));
            r2 = normalForm(mod2);
            mod2 = normalForm(add);
            normalForm = normalForm2;
            mod = normalForm3;
        }
        return multWithElement(r2, this.field.inverse(headCoefficient(normalForm)));
    }

    private int[] modMultiply(int[] r1, int[] r2, int[] r3) {
        return mod(multiply(r1, r2), r3);
    }

    private int[] multWithElement(int[] r5, int r6) {
        int computeDegree = computeDegree(r5);
        if (computeDegree == -1 || r6 == 0) {
            return new int[1];
        }
        if (r6 == 1) {
            return IntUtils.clone(r5);
        }
        int[] r1 = new int[computeDegree + 1];
        while (computeDegree >= 0) {
            r1[computeDegree] = this.field.mult(r5[computeDegree], r6);
            computeDegree--;
        }
        return r1;
    }

    private static int[] multWithMonomial(int[] r4, int r5) {
        int computeDegree = computeDegree(r4);
        if (computeDegree == -1) {
            return new int[1];
        }
        int[] r2 = new int[computeDegree + r5 + 1];
        System.arraycopy(r4, 0, r2, r5, computeDegree + 1);
        return r2;
    }

    private int[] multiply(int[] r9, int[] r10) {
        if (computeDegree(r9) < computeDegree(r10)) {
            r10 = r9;
            r9 = r10;
        }
        int[] normalForm = normalForm(r9);
        int[] normalForm2 = normalForm(r10);
        if (normalForm2.length == 1) {
            return multWithElement(normalForm, normalForm2[0]);
        }
        int length = normalForm.length;
        int length2 = normalForm2.length;
        int[] r4 = new int[(length + length2) - 1];
        if (length2 != length) {
            int[] r1 = new int[length2];
            int r0 = length - length2;
            int[] r42 = new int[r0];
            System.arraycopy(normalForm, 0, r1, 0, length2);
            System.arraycopy(normalForm, length2, r42, 0, r0);
            return add(multiply(r1, normalForm2), multWithMonomial(multiply(r42, normalForm2), length2));
        }
        int r12 = (length + 1) >>> 1;
        int r02 = length - r12;
        int[] r3 = new int[r12];
        int[] r43 = new int[r12];
        int[] r5 = new int[r02];
        int[] r6 = new int[r02];
        System.arraycopy(normalForm, 0, r3, 0, r12);
        System.arraycopy(normalForm, r12, r5, 0, r02);
        System.arraycopy(normalForm2, 0, r43, 0, r12);
        System.arraycopy(normalForm2, r12, r6, 0, r02);
        int[] add = add(r3, r5);
        int[] add2 = add(r43, r6);
        int[] multiply = multiply(r3, r43);
        int[] multiply2 = multiply(add, add2);
        int[] multiply3 = multiply(r5, r6);
        return add(multWithMonomial(add(add(add(multiply2, multiply), multiply3), multWithMonomial(multiply3, r12)), r12), multiply);
    }

    private static int[] normalForm(int[] r3) {
        int computeDegree = computeDegree(r3);
        if (computeDegree == -1) {
            return new int[1];
        }
        int r0 = computeDegree + 1;
        if (r3.length == r0) {
            return IntUtils.clone(r3);
        }
        int[] r1 = new int[r0];
        System.arraycopy(r3, 0, r1, 0, r0);
        return r1;
    }

    public PolynomialGF2mSmallM add(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.field, add(this.coefficients, polynomialGF2mSmallM.coefficients));
    }

    public PolynomialGF2mSmallM addMonomial(int r3) {
        int[] r0 = new int[r3 + 1];
        r0[r3] = 1;
        return new PolynomialGF2mSmallM(this.field, add(this.coefficients, r0));
    }

    public void addToThis(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.coefficients = add(this.coefficients, polynomialGF2mSmallM.coefficients);
        computeDegree();
    }

    public PolynomialGF2mSmallM[] div(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int[][] div = div(this.coefficients, polynomialGF2mSmallM.coefficients);
        return new PolynomialGF2mSmallM[]{new PolynomialGF2mSmallM(this.field, div[0]), new PolynomialGF2mSmallM(this.field, div[1])};
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof PolynomialGF2mSmallM)) {
            PolynomialGF2mSmallM polynomialGF2mSmallM = (PolynomialGF2mSmallM) obj;
            if (this.field.equals(polynomialGF2mSmallM.field) && this.degree == polynomialGF2mSmallM.degree && isEqual(this.coefficients, polynomialGF2mSmallM.coefficients)) {
                return true;
            }
        }
        return false;
    }

    public int evaluateAt(int r4) {
        int[] r0 = this.coefficients;
        int r1 = this.degree;
        int r02 = r0[r1];
        for (int r12 = r1 - 1; r12 >= 0; r12--) {
            r02 = this.field.mult(r02, r4) ^ this.coefficients[r12];
        }
        return r02;
    }

    public PolynomialGF2mSmallM gcd(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.field, gcd(this.coefficients, polynomialGF2mSmallM.coefficients));
    }

    public int getCoefficient(int r2) {
        if (r2 < 0 || r2 > this.degree) {
            return 0;
        }
        return this.coefficients[r2];
    }

    public int getDegree() {
        int[] r0 = this.coefficients;
        int length = r0.length - 1;
        if (r0[length] == 0) {
            return -1;
        }
        return length;
    }

    public byte[] getEncoded() {
        int r0 = 8;
        int r1 = 1;
        while (this.field.getDegree() > r0) {
            r1++;
            r0 += 8;
        }
        byte[] bArr = new byte[this.coefficients.length * r1];
        int r4 = 0;
        for (int r3 = 0; r3 < this.coefficients.length; r3++) {
            int r5 = 0;
            while (r5 < r0) {
                bArr[r4] = (byte) (this.coefficients[r3] >>> r5);
                r5 += 8;
                r4++;
            }
        }
        return bArr;
    }

    public int getHeadCoefficient() {
        int r0 = this.degree;
        if (r0 == -1) {
            return 0;
        }
        return this.coefficients[r0];
    }

    public int hashCode() {
        int hashCode = this.field.hashCode();
        int r1 = 0;
        while (true) {
            int[] r2 = this.coefficients;
            if (r1 >= r2.length) {
                return hashCode;
            }
            hashCode = (hashCode * 31) + r2[r1];
            r1++;
        }
    }

    public PolynomialGF2mSmallM mod(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.field, mod(this.coefficients, polynomialGF2mSmallM.coefficients));
    }

    public PolynomialGF2mSmallM modDiv(PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM polynomialGF2mSmallM2) {
        return new PolynomialGF2mSmallM(this.field, modDiv(this.coefficients, polynomialGF2mSmallM.coefficients, polynomialGF2mSmallM2.coefficients));
    }

    public PolynomialGF2mSmallM modInverse(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.field, modDiv(new int[]{1}, this.coefficients, polynomialGF2mSmallM.coefficients));
    }

    public PolynomialGF2mSmallM modMultiply(PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM polynomialGF2mSmallM2) {
        return new PolynomialGF2mSmallM(this.field, modMultiply(this.coefficients, polynomialGF2mSmallM.coefficients, polynomialGF2mSmallM2.coefficients));
    }

    public PolynomialGF2mSmallM[] modPolynomialToFracton(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int r0 = polynomialGF2mSmallM.degree >> 1;
        int[] normalForm = normalForm(polynomialGF2mSmallM.coefficients);
        int[] mod = mod(this.coefficients, polynomialGF2mSmallM.coefficients);
        int[] r6 = {0};
        int[] r4 = {1};
        while (computeDegree(mod) > r0) {
            int[][] div = div(normalForm, mod);
            int[] r7 = div[1];
            int[] add = add(r6, modMultiply(div[0], r4, polynomialGF2mSmallM.coefficients));
            r6 = r4;
            r4 = add;
            normalForm = mod;
            mod = r7;
        }
        return new PolynomialGF2mSmallM[]{new PolynomialGF2mSmallM(this.field, mod), new PolynomialGF2mSmallM(this.field, r4)};
    }

    public PolynomialGF2mSmallM modSquareMatrix(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int length = polynomialGF2mSmallMArr.length;
        int[] r1 = new int[length];
        int[] r2 = new int[length];
        int r4 = 0;
        while (true) {
            int[] r5 = this.coefficients;
            if (r4 >= r5.length) {
                break;
            }
            r2[r4] = this.field.mult(r5[r4], r5[r4]);
            r4++;
        }
        for (int r42 = 0; r42 < length; r42++) {
            for (int r52 = 0; r52 < length; r52++) {
                if (r42 < polynomialGF2mSmallMArr[r52].coefficients.length) {
                    r1[r42] = this.field.add(r1[r42], this.field.mult(polynomialGF2mSmallMArr[r52].coefficients[r42], r2[r52]));
                }
            }
        }
        return new PolynomialGF2mSmallM(this.field, r1);
    }

    public PolynomialGF2mSmallM modSquareRoot(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int[] clone = IntUtils.clone(this.coefficients);
        int[] modMultiply = modMultiply(clone, clone, polynomialGF2mSmallM.coefficients);
        while (!isEqual(modMultiply, this.coefficients)) {
            clone = normalForm(modMultiply);
            modMultiply = modMultiply(clone, clone, polynomialGF2mSmallM.coefficients);
        }
        return new PolynomialGF2mSmallM(this.field, clone);
    }

    public PolynomialGF2mSmallM modSquareRootMatrix(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int length = polynomialGF2mSmallMArr.length;
        int[] r1 = new int[length];
        for (int r3 = 0; r3 < length; r3++) {
            for (int r4 = 0; r4 < length; r4++) {
                if (r3 < polynomialGF2mSmallMArr[r4].coefficients.length) {
                    int[] r5 = this.coefficients;
                    if (r4 < r5.length) {
                        r1[r3] = this.field.add(r1[r3], this.field.mult(polynomialGF2mSmallMArr[r4].coefficients[r3], r5[r4]));
                    }
                }
            }
        }
        for (int r2 = 0; r2 < length; r2++) {
            r1[r2] = this.field.sqRoot(r1[r2]);
        }
        return new PolynomialGF2mSmallM(this.field, r1);
    }

    public void multThisWithElement(int r2) {
        if (!this.field.isElementOfThisField(r2)) {
            throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
        }
        this.coefficients = multWithElement(this.coefficients, r2);
        computeDegree();
    }

    public PolynomialGF2mSmallM multWithElement(int r3) {
        if (this.field.isElementOfThisField(r3)) {
            return new PolynomialGF2mSmallM(this.field, multWithElement(this.coefficients, r3));
        }
        throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
    }

    public PolynomialGF2mSmallM multWithMonomial(int r3) {
        return new PolynomialGF2mSmallM(this.field, multWithMonomial(this.coefficients, r3));
    }

    public PolynomialGF2mSmallM multiply(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.field, multiply(this.coefficients, polynomialGF2mSmallM.coefficients));
    }

    public String toString() {
        String str = " Polynomial over " + this.field.toString() + ": \n";
        for (int r1 = 0; r1 < this.coefficients.length; r1++) {
            str = str + this.field.elementToStr(this.coefficients[r1]) + "Y^" + r1 + "+";
        }
        return str + ";";
    }
}
