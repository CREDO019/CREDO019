package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public class GF2mMatrix extends Matrix {
    protected GF2mField field;
    protected int[][] matrix;

    public GF2mMatrix(GF2mField gF2mField, byte[] bArr) {
        this.field = gF2mField;
        int r2 = 8;
        int r3 = 1;
        while (gF2mField.getDegree() > r2) {
            r3++;
            r2 += 8;
        }
        if (bArr.length < 5) {
            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
        }
        this.numRows = ((((bArr[3] & 255) << 24) ^ ((bArr[2] & 255) << 16)) ^ ((bArr[1] & 255) << 8)) ^ (bArr[0] & 255);
        int r32 = r3 * this.numRows;
        if (this.numRows > 0) {
            int r6 = 4;
            if ((bArr.length - 4) % r32 == 0) {
                this.numColumns = (bArr.length - 4) / r32;
                this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.numColumns);
                for (int r10 = 0; r10 < this.numRows; r10++) {
                    for (int r1 = 0; r1 < this.numColumns; r1++) {
                        int r33 = 0;
                        while (r33 < r2) {
                            int[] r4 = this.matrix[r10];
                            r4[r1] = ((bArr[r6] & 255) << r33) ^ r4[r1];
                            r33 += 8;
                            r6++;
                        }
                        if (!this.field.isElementOfThisField(this.matrix[r10][r1])) {
                            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
                        }
                    }
                }
                return;
            }
        }
        throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
    }

    protected GF2mMatrix(GF2mField gF2mField, int[][] r2) {
        this.field = gF2mField;
        this.matrix = r2;
        this.numRows = r2.length;
        this.numColumns = r2[0].length;
    }

    public GF2mMatrix(GF2mMatrix gF2mMatrix) {
        this.numRows = gF2mMatrix.numRows;
        this.numColumns = gF2mMatrix.numColumns;
        this.field = gF2mMatrix.field;
        this.matrix = new int[this.numRows];
        for (int r0 = 0; r0 < this.numRows; r0++) {
            this.matrix[r0] = IntUtils.clone(gF2mMatrix.matrix[r0]);
        }
    }

    private void addToRow(int[] r5, int[] r6) {
        for (int length = r6.length - 1; length >= 0; length--) {
            r6[length] = this.field.add(r5[length], r6[length]);
        }
    }

    private int[] multRowWithElement(int[] r5, int r6) {
        int[] r0 = new int[r5.length];
        for (int length = r5.length - 1; length >= 0; length--) {
            r0[length] = this.field.mult(r5[length], r6);
        }
        return r0;
    }

    private void multRowWithElementThis(int[] r4, int r5) {
        for (int length = r4.length - 1; length >= 0; length--) {
            r4[length] = this.field.mult(r4[length], r5);
        }
    }

    private static void swapColumns(int[][] r2, int r3, int r4) {
        int[] r0 = r2[r3];
        r2[r3] = r2[r4];
        r2[r4] = r0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int r7;
        if (this.numRows == this.numColumns) {
            int[][] r1 = (int[][]) Array.newInstance(int.class, this.numRows, this.numRows);
            for (int r5 = this.numRows - 1; r5 >= 0; r5--) {
                r1[r5] = IntUtils.clone(this.matrix[r5]);
            }
            int[][] r0 = (int[][]) Array.newInstance(int.class, this.numRows, this.numRows);
            for (int r4 = this.numRows - 1; r4 >= 0; r4--) {
                r0[r4][r4] = 1;
            }
            for (int r42 = 0; r42 < this.numRows; r42++) {
                if (r1[r42][r42] == 0) {
                    int r52 = r42 + 1;
                    boolean z = false;
                    while (r52 < this.numRows) {
                        if (r1[r52][r42] != 0) {
                            swapColumns(r1, r42, r52);
                            swapColumns(r0, r42, r52);
                            r52 = this.numRows;
                            z = true;
                        }
                        r52++;
                    }
                    if (!z) {
                        throw new ArithmeticException("Matrix is not invertible.");
                    }
                }
                int inverse = this.field.inverse(r1[r42][r42]);
                multRowWithElementThis(r1[r42], inverse);
                multRowWithElementThis(r0[r42], inverse);
                for (int r53 = 0; r53 < this.numRows; r53++) {
                    if (r53 != r42 && (r7 = r1[r53][r42]) != 0) {
                        int[] multRowWithElement = multRowWithElement(r1[r42], r7);
                        int[] multRowWithElement2 = multRowWithElement(r0[r42], r7);
                        addToRow(multRowWithElement, r1[r53]);
                        addToRow(multRowWithElement2, r0[r53]);
                    }
                }
            }
            return new GF2mMatrix(this.field, r0);
        }
        throw new ArithmeticException("Matrix is not invertible.");
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mMatrix)) {
            GF2mMatrix gF2mMatrix = (GF2mMatrix) obj;
            if (this.field.equals(gF2mMatrix.field) && gF2mMatrix.numRows == this.numColumns && gF2mMatrix.numColumns == this.numColumns) {
                for (int r1 = 0; r1 < this.numRows; r1++) {
                    for (int r2 = 0; r2 < this.numColumns; r2++) {
                        if (this.matrix[r1][r2] != gF2mMatrix.matrix[r1][r2]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int r2 = 8;
        int r3 = 1;
        while (this.field.getDegree() > r2) {
            r3++;
            r2 += 8;
        }
        int r4 = this.numRows * this.numColumns * r3;
        int r32 = 4;
        byte[] bArr = new byte[r4 + 4];
        bArr[0] = (byte) (this.numRows & 255);
        bArr[1] = (byte) ((this.numRows >>> 8) & 255);
        bArr[2] = (byte) ((this.numRows >>> 16) & 255);
        bArr[3] = (byte) ((this.numRows >>> 24) & 255);
        for (int r0 = 0; r0 < this.numRows; r0++) {
            for (int r1 = 0; r1 < this.numColumns; r1++) {
                int r5 = 0;
                while (r5 < r2) {
                    bArr[r32] = (byte) (this.matrix[r0][r1] >>> r5);
                    r5 += 8;
                    r32++;
                }
            }
        }
        return bArr;
    }

    public int hashCode() {
        int hashCode = (((this.field.hashCode() * 31) + this.numRows) * 31) + this.numColumns;
        for (int r2 = 0; r2 < this.numRows; r2++) {
            for (int r3 = 0; r3 < this.numColumns; r3++) {
                hashCode = (hashCode * 31) + this.matrix[r2][r3];
            }
        }
        return hashCode;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int r1 = 0; r1 < this.numRows; r1++) {
            for (int r2 = 0; r2 < this.numColumns; r2++) {
                if (this.matrix[r1][r2] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        String str = this.numRows + " x " + this.numColumns + " Matrix over " + this.field.toString() + ": \n";
        for (int r2 = 0; r2 < this.numRows; r2++) {
            for (int r3 = 0; r3 < this.numColumns; r3++) {
                str = str + this.field.elementToStr(this.matrix[r2][r3]) + " : ";
            }
            str = str + "\n";
        }
        return str;
    }
}
