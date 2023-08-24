package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class GF2Matrix extends Matrix {
    private int length;
    private int[][] matrix;

    public GF2Matrix(int r2, char c) {
        this(r2, c, new SecureRandom());
    }

    public GF2Matrix(int r2, char c, SecureRandom secureRandom) {
        if (r2 <= 0) {
            throw new ArithmeticException("Size of matrix is non-positive.");
        }
        if (c == 'I') {
            assignUnitMatrix(r2);
        } else if (c == 'L') {
            assignRandomLowerTriangularMatrix(r2, secureRandom);
        } else if (c == 'R') {
            assignRandomRegularMatrix(r2, secureRandom);
        } else if (c == 'U') {
            assignRandomUpperTriangularMatrix(r2, secureRandom);
        } else if (c != 'Z') {
            throw new ArithmeticException("Unknown matrix type.");
        } else {
            assignZeroMatrix(r2, r2);
        }
    }

    private GF2Matrix(int r1, int r2) {
        if (r2 <= 0 || r1 <= 0) {
            throw new ArithmeticException("size of matrix is non-positive");
        }
        assignZeroMatrix(r1, r2);
    }

    public GF2Matrix(int r6, int[][] r7) {
        if (r7[0].length != ((r6 + 31) >> 5)) {
            throw new ArithmeticException("Int array does not match given number of columns.");
        }
        this.numColumns = r6;
        this.numRows = r7.length;
        this.length = r7[0].length;
        int r62 = r6 & 31;
        int r63 = r62 == 0 ? -1 : (1 << r62) - 1;
        for (int r0 = 0; r0 < this.numRows; r0++) {
            int[] r2 = r7[r0];
            int r3 = this.length - 1;
            r2[r3] = r2[r3] & r63;
        }
        this.matrix = r7;
    }

    public GF2Matrix(GF2Matrix gF2Matrix) {
        this.numColumns = gF2Matrix.getNumColumns();
        this.numRows = gF2Matrix.getNumRows();
        this.length = gF2Matrix.length;
        this.matrix = new int[gF2Matrix.matrix.length];
        int r0 = 0;
        while (true) {
            int[][] r1 = this.matrix;
            if (r0 >= r1.length) {
                return;
            }
            r1[r0] = IntUtils.clone(gF2Matrix.matrix[r0]);
            r0++;
        }
    }

    public GF2Matrix(byte[] bArr) {
        if (bArr.length < 9) {
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        this.numRows = LittleEndianConversions.OS2IP(bArr, 0);
        this.numColumns = LittleEndianConversions.OS2IP(bArr, 4);
        int r2 = ((this.numColumns + 7) >>> 3) * this.numRows;
        if (this.numRows > 0) {
            int r4 = 8;
            if (r2 == bArr.length - 8) {
                this.length = (this.numColumns + 31) >>> 5;
                this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
                int r1 = this.numColumns >> 5;
                int r22 = this.numColumns & 31;
                for (int r3 = 0; r3 < this.numRows; r3++) {
                    int r5 = 0;
                    while (r5 < r1) {
                        this.matrix[r3][r5] = LittleEndianConversions.OS2IP(bArr, r4);
                        r5++;
                        r4 += 4;
                    }
                    int r52 = 0;
                    while (r52 < r22) {
                        int[] r6 = this.matrix[r3];
                        r6[r1] = ((bArr[r4] & 255) << r52) ^ r6[r1];
                        r52 += 8;
                        r4++;
                    }
                }
                return;
            }
        }
        throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
    }

    private static void addToRow(int[] r3, int[] r4, int r5) {
        for (int length = r4.length - 1; length >= r5; length--) {
            r4[length] = r3[length] ^ r4[length];
        }
    }

    private void assignRandomLowerTriangularMatrix(int r9, SecureRandom secureRandom) {
        this.numRows = r9;
        this.numColumns = r9;
        this.length = (r9 + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        for (int r92 = 0; r92 < this.numRows; r92++) {
            int r1 = r92 >>> 5;
            int r3 = r92 & 31;
            int r4 = 31 - r3;
            int r32 = 1 << r3;
            for (int r5 = 0; r5 < r1; r5++) {
                this.matrix[r92][r5] = secureRandom.nextInt();
            }
            this.matrix[r92][r1] = r32 | (secureRandom.nextInt() >>> r4);
            while (true) {
                r1++;
                if (r1 < this.length) {
                    this.matrix[r92][r1] = 0;
                }
            }
        }
    }

    private void assignRandomRegularMatrix(int r7, SecureRandom secureRandom) {
        this.numRows = r7;
        this.numColumns = r7;
        this.length = (r7 + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        GF2Matrix gF2Matrix = (GF2Matrix) new GF2Matrix(r7, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom).rightMultiply(new GF2Matrix(r7, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom));
        int[] vector = new Permutation(r7, secureRandom).getVector();
        for (int r2 = 0; r2 < r7; r2++) {
            System.arraycopy(gF2Matrix.matrix[r2], 0, this.matrix[vector[r2]], 0, this.length);
        }
    }

    private void assignRandomUpperTriangularMatrix(int r9, SecureRandom secureRandom) {
        int r4;
        this.numRows = r9;
        this.numColumns = r9;
        this.length = (r9 + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        int r92 = r9 & 31;
        int r93 = r92 == 0 ? -1 : (1 << r92) - 1;
        for (int r0 = 0; r0 < this.numRows; r0++) {
            int r2 = r0 >>> 5;
            int r42 = r0 & 31;
            int r5 = 1 << r42;
            for (int r6 = 0; r6 < r2; r6++) {
                this.matrix[r0][r6] = 0;
            }
            this.matrix[r0][r2] = (secureRandom.nextInt() << r42) | r5;
            while (true) {
                r2++;
                r4 = this.length;
                if (r2 < r4) {
                    this.matrix[r0][r2] = secureRandom.nextInt();
                }
            }
            int[] r22 = this.matrix[r0];
            int r43 = r4 - 1;
            r22[r43] = r22[r43] & r93;
        }
    }

    private void assignUnitMatrix(int r5) {
        this.numRows = r5;
        this.numColumns = r5;
        this.length = (r5 + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        for (int r52 = 0; r52 < this.numRows; r52++) {
            for (int r1 = 0; r1 < this.length; r1++) {
                this.matrix[r52][r1] = 0;
            }
        }
        for (int r0 = 0; r0 < this.numRows; r0++) {
            this.matrix[r0][r0 >>> 5] = 1 << (r0 & 31);
        }
    }

    private void assignZeroMatrix(int r3, int r4) {
        this.numRows = r3;
        this.numColumns = r4;
        this.length = (r4 + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        for (int r32 = 0; r32 < this.numRows; r32++) {
            for (int r0 = 0; r0 < this.length; r0++) {
                this.matrix[r32][r0] = 0;
            }
        }
    }

    public static GF2Matrix[] createRandomRegularMatrixAndItsInverse(int r18, SecureRandom secureRandom) {
        GF2Matrix[] gF2MatrixArr = new GF2Matrix[2];
        int r4 = (r18 + 31) >> 5;
        GF2Matrix gF2Matrix = new GF2Matrix(r18, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom);
        GF2Matrix gF2Matrix2 = new GF2Matrix(r18, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix) gF2Matrix.rightMultiply(gF2Matrix2);
        Permutation permutation = new Permutation(r18, secureRandom);
        int[] vector = permutation.getVector();
        int[][] r2 = (int[][]) Array.newInstance(int.class, r18, r4);
        for (int r11 = 0; r11 < r18; r11++) {
            System.arraycopy(gF2Matrix3.matrix[vector[r11]], 0, r2[r11], 0, r4);
        }
        gF2MatrixArr[0] = new GF2Matrix(r18, r2);
        GF2Matrix gF2Matrix4 = new GF2Matrix(r18, 'I');
        int r7 = 0;
        while (r7 < r18) {
            int r12 = r7 >>> 5;
            int r112 = 1 << (r7 & 31);
            int r13 = r7 + 1;
            for (int r14 = r13; r14 < r18; r14++) {
                if ((gF2Matrix.matrix[r14][r12] & r112) != 0) {
                    for (int r15 = 0; r15 <= r12; r15++) {
                        int[][] r10 = gF2Matrix4.matrix;
                        int[] r16 = r10[r14];
                        r16[r15] = r16[r15] ^ r10[r7][r15];
                    }
                }
            }
            r7 = r13;
        }
        GF2Matrix gF2Matrix5 = new GF2Matrix(r18, 'I');
        for (int r0 = r18 - 1; r0 >= 0; r0--) {
            int r72 = r0 >>> 5;
            int r22 = 1 << (r0 & 31);
            for (int r102 = r0 - 1; r102 >= 0; r102--) {
                if ((gF2Matrix2.matrix[r102][r72] & r22) != 0) {
                    for (int r113 = r72; r113 < r4; r113++) {
                        int[][] r122 = gF2Matrix5.matrix;
                        int[] r132 = r122[r102];
                        r132[r113] = r122[r0][r113] ^ r132[r113];
                    }
                }
            }
        }
        gF2MatrixArr[1] = (GF2Matrix) gF2Matrix5.rightMultiply(gF2Matrix4.rightMultiply(permutation));
        return gF2MatrixArr;
    }

    private static void swapRows(int[][] r2, int r3, int r4) {
        int[] r0 = r2[r3];
        r2[r3] = r2[r4];
        r2[r4] = r0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        if (this.numRows == this.numColumns) {
            int[][] r1 = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
            for (int r5 = this.numRows - 1; r5 >= 0; r5--) {
                r1[r5] = IntUtils.clone(this.matrix[r5]);
            }
            int[][] r0 = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
            for (int r4 = this.numRows - 1; r4 >= 0; r4--) {
                r0[r4][r4 >> 5] = 1 << (r4 & 31);
            }
            for (int r42 = 0; r42 < this.numRows; r42++) {
                int r52 = r42 >> 5;
                int r7 = 1 << (r42 & 31);
                if ((r1[r42][r52] & r7) == 0) {
                    int r8 = r42 + 1;
                    boolean z = false;
                    while (r8 < this.numRows) {
                        if ((r1[r8][r52] & r7) != 0) {
                            swapRows(r1, r42, r8);
                            swapRows(r0, r42, r8);
                            r8 = this.numRows;
                            z = true;
                        }
                        r8++;
                    }
                    if (!z) {
                        throw new ArithmeticException("Matrix is not invertible.");
                    }
                }
                for (int r82 = this.numRows - 1; r82 >= 0; r82--) {
                    if (r82 != r42 && (r1[r82][r52] & r7) != 0) {
                        addToRow(r1[r42], r1[r82], r52);
                        addToRow(r0[r42], r0[r82], 0);
                    }
                }
            }
            return new GF2Matrix(this.numColumns, r0);
        }
        throw new ArithmeticException("Matrix is not invertible.");
    }

    public Matrix computeTranspose() {
        int[][] r0 = (int[][]) Array.newInstance(int.class, this.numColumns, (this.numRows + 31) >>> 5);
        for (int r2 = 0; r2 < this.numRows; r2++) {
            for (int r4 = 0; r4 < this.numColumns; r4++) {
                int r6 = r2 >>> 5;
                int r7 = r2 & 31;
                if (((this.matrix[r2][r4 >>> 5] >>> (r4 & 31)) & 1) == 1) {
                    int[] r5 = r0[r4];
                    r5[r6] = (1 << r7) | r5[r6];
                }
            }
        }
        return new GF2Matrix(this.numRows, r0);
    }

    public boolean equals(Object obj) {
        if (obj instanceof GF2Matrix) {
            GF2Matrix gF2Matrix = (GF2Matrix) obj;
            if (this.numRows == gF2Matrix.numRows && this.numColumns == gF2Matrix.numColumns && this.length == gF2Matrix.length) {
                for (int r0 = 0; r0 < this.numRows; r0++) {
                    if (!IntUtils.equals(this.matrix[r0], gF2Matrix.matrix[r0])) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public GF2Matrix extendLeftCompactForm() {
        GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numColumns + this.numRows);
        int r0 = (this.numRows - 1) + this.numColumns;
        int r3 = this.numRows - 1;
        while (r3 >= 0) {
            System.arraycopy(this.matrix[r3], 0, gF2Matrix.matrix[r3], 0, this.length);
            int[] r4 = gF2Matrix.matrix[r3];
            int r5 = r0 >> 5;
            r4[r5] = r4[r5] | (1 << (r0 & 31));
            r3--;
            r0--;
        }
        return gF2Matrix;
    }

    public GF2Matrix extendRightCompactForm() {
        int r7;
        GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numRows + this.numColumns);
        int r1 = this.numRows >> 5;
        int r2 = this.numRows & 31;
        for (int r3 = this.numRows - 1; r3 >= 0; r3--) {
            int[][] r5 = gF2Matrix.matrix;
            int[] r6 = r5[r3];
            int r72 = r3 >> 5;
            r6[r72] = r6[r72] | (1 << (r3 & 31));
            int r62 = 0;
            if (r2 != 0) {
                int r52 = r1;
                while (true) {
                    r7 = this.length;
                    if (r62 >= r7 - 1) {
                        break;
                    }
                    int r73 = this.matrix[r3][r62];
                    int[][] r8 = gF2Matrix.matrix;
                    int[] r9 = r8[r3];
                    int r10 = r52 + 1;
                    r9[r52] = r9[r52] | (r73 << r2);
                    int[] r53 = r8[r3];
                    r53[r10] = (r73 >>> (32 - r2)) | r53[r10];
                    r62++;
                    r52 = r10;
                }
                int r63 = this.matrix[r3][r7 - 1];
                int[][] r74 = gF2Matrix.matrix;
                int[] r82 = r74[r3];
                int r92 = r52 + 1;
                r82[r52] = r82[r52] | (r63 << r2);
                if (r92 < gF2Matrix.length) {
                    int[] r54 = r74[r3];
                    r54[r92] = (r63 >>> (32 - r2)) | r54[r92];
                }
            } else {
                System.arraycopy(this.matrix[r3], 0, r5[r3], r1, this.length);
            }
        }
        return gF2Matrix;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int r1 = 8;
        byte[] bArr = new byte[(((this.numColumns + 7) >>> 3) * this.numRows) + 8];
        LittleEndianConversions.I2OSP(this.numRows, bArr, 0);
        LittleEndianConversions.I2OSP(this.numColumns, bArr, 4);
        int r2 = this.numColumns >>> 5;
        int r4 = this.numColumns & 31;
        for (int r5 = 0; r5 < this.numRows; r5++) {
            int r6 = 0;
            while (r6 < r2) {
                LittleEndianConversions.I2OSP(this.matrix[r5][r6], bArr, r1);
                r6++;
                r1 += 4;
            }
            int r62 = 0;
            while (r62 < r4) {
                bArr[r1] = (byte) ((this.matrix[r5][r2] >>> r62) & 255);
                r62 += 8;
                r1++;
            }
        }
        return bArr;
    }

    public double getHammingWeight() {
        int r0 = this.numColumns & 31;
        int r1 = this.length;
        if (r0 != 0) {
            r1--;
        }
        double d = 0.0d;
        double d2 = 0.0d;
        for (int r7 = 0; r7 < this.numRows; r7++) {
            for (int r8 = 0; r8 < r1; r8++) {
                int r11 = this.matrix[r7][r8];
                for (int r12 = 0; r12 < 32; r12++) {
                    d += (r11 >>> r12) & 1;
                    d2 += 1.0d;
                }
            }
            int r82 = this.matrix[r7][this.length - 1];
            for (int r112 = 0; r112 < r0; r112++) {
                d += (r82 >>> r112) & 1;
                d2 += 1.0d;
            }
        }
        return d / d2;
    }

    public int[][] getIntArray() {
        return this.matrix;
    }

    public GF2Matrix getLeftSubMatrix() {
        if (this.numColumns > this.numRows) {
            int r0 = (this.numRows + 31) >> 5;
            int[][] r1 = (int[][]) Array.newInstance(int.class, this.numRows, r0);
            int r2 = (1 << (this.numRows & 31)) - 1;
            if (r2 == 0) {
                r2 = -1;
            }
            for (int r5 = this.numRows - 1; r5 >= 0; r5--) {
                System.arraycopy(this.matrix[r5], 0, r1[r5], 0, r0);
                int[] r6 = r1[r5];
                int r7 = r0 - 1;
                r6[r7] = r6[r7] & r2;
            }
            return new GF2Matrix(this.numRows, r1);
        }
        throw new ArithmeticException("empty submatrix");
    }

    public int getLength() {
        return this.length;
    }

    public GF2Matrix getRightSubMatrix() {
        int r6;
        if (this.numColumns > this.numRows) {
            int r0 = this.numRows >> 5;
            int r1 = this.numRows & 31;
            GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numColumns - this.numRows);
            for (int r3 = this.numRows - 1; r3 >= 0; r3--) {
                int r4 = 0;
                if (r1 != 0) {
                    int r5 = r0;
                    while (true) {
                        r6 = gF2Matrix.length;
                        if (r4 >= r6 - 1) {
                            break;
                        }
                        int[] r62 = gF2Matrix.matrix[r3];
                        int[][] r7 = this.matrix;
                        int r9 = r5 + 1;
                        r62[r4] = (r7[r3][r5] >>> r1) | (r7[r3][r9] << (32 - r1));
                        r4++;
                        r5 = r9;
                    }
                    int[][] r42 = gF2Matrix.matrix;
                    int[][] r92 = this.matrix;
                    int r11 = r5 + 1;
                    r42[r3][r6 - 1] = r92[r3][r5] >>> r1;
                    if (r11 < this.length) {
                        int[] r43 = r42[r3];
                        int r63 = r6 - 1;
                        r43[r63] = r43[r63] | (r92[r3][r11] << (32 - r1));
                    }
                } else {
                    System.arraycopy(this.matrix[r3], r0, gF2Matrix.matrix[r3], 0, gF2Matrix.length);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("empty submatrix");
    }

    public int[] getRow(int r2) {
        return this.matrix[r2];
    }

    public int hashCode() {
        int r0 = (((this.numRows * 31) + this.numColumns) * 31) + this.length;
        for (int r1 = 0; r1 < this.numRows; r1++) {
            r0 = (r0 * 31) + Arrays.hashCode(this.matrix[r1]);
        }
        return r0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int r1 = 0; r1 < this.numRows; r1++) {
            for (int r2 = 0; r2 < this.length; r2++) {
                if (this.matrix[r1][r2] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix leftMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        if (vector.length == this.numRows) {
            int[][] r0 = new int[this.numRows];
            for (int r1 = this.numRows - 1; r1 >= 0; r1--) {
                r0[r1] = IntUtils.clone(this.matrix[vector[r1]]);
            }
            return new GF2Matrix(this.numRows, r0);
        }
        throw new ArithmeticException("length mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length == this.numRows) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] r0 = new int[this.length];
                int r1 = this.numRows >> 5;
                int r2 = 1 << (this.numRows & 31);
                int r6 = 0;
                for (int r5 = 0; r5 < r1; r5++) {
                    int r7 = 1;
                    do {
                        if ((vecArray[r5] & r7) != 0) {
                            for (int r8 = 0; r8 < this.length; r8++) {
                                r0[r8] = r0[r8] ^ this.matrix[r6][r8];
                            }
                        }
                        r6++;
                        r7 <<= 1;
                    } while (r7 != 0);
                }
                for (int r3 = 1; r3 != r2; r3 <<= 1) {
                    if ((vecArray[r1] & r3) != 0) {
                        for (int r52 = 0; r52 < this.length; r52++) {
                            r0[r52] = r0[r52] ^ this.matrix[r6][r52];
                        }
                    }
                    r6++;
                }
                return new GF2Vector(r0, this.numColumns);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    public Vector leftMultiplyLeftCompactForm(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length == this.numRows) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] r0 = new int[((this.numRows + this.numColumns) + 31) >>> 5];
                int r1 = this.numRows >>> 5;
                int r4 = 0;
                for (int r3 = 0; r3 < r1; r3++) {
                    int r6 = 1;
                    do {
                        if ((vecArray[r3] & r6) != 0) {
                            for (int r7 = 0; r7 < this.length; r7++) {
                                r0[r7] = r0[r7] ^ this.matrix[r4][r7];
                            }
                            int r72 = (this.numColumns + r4) >>> 5;
                            r0[r72] = (1 << ((this.numColumns + r4) & 31)) | r0[r72];
                        }
                        r4++;
                        r6 <<= 1;
                    } while (r6 != 0);
                }
                int r32 = 1 << (this.numRows & 31);
                for (int r62 = 1; r62 != r32; r62 <<= 1) {
                    if ((vecArray[r1] & r62) != 0) {
                        for (int r73 = 0; r73 < this.length; r73++) {
                            r0[r73] = r0[r73] ^ this.matrix[r4][r73];
                        }
                        int r74 = (this.numColumns + r4) >>> 5;
                        r0[r74] = (1 << ((this.numColumns + r4) & 31)) | r0[r74];
                    }
                    r4++;
                }
                return new GF2Vector(r0, this.numRows + this.numColumns);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        if (matrix instanceof GF2Matrix) {
            if (matrix.numRows == this.numColumns) {
                GF2Matrix gF2Matrix = (GF2Matrix) matrix;
                GF2Matrix gF2Matrix2 = new GF2Matrix(this.numRows, matrix.numColumns);
                int r15 = this.numColumns & 31;
                int r3 = this.length;
                if (r15 != 0) {
                    r3--;
                }
                for (int r5 = 0; r5 < this.numRows; r5++) {
                    int r7 = 0;
                    for (int r6 = 0; r6 < r3; r6++) {
                        int r8 = this.matrix[r5][r6];
                        for (int r9 = 0; r9 < 32; r9++) {
                            if (((1 << r9) & r8) != 0) {
                                for (int r10 = 0; r10 < gF2Matrix.length; r10++) {
                                    int[] r11 = gF2Matrix2.matrix[r5];
                                    r11[r10] = r11[r10] ^ gF2Matrix.matrix[r7][r10];
                                }
                            }
                            r7++;
                        }
                    }
                    int r62 = this.matrix[r5][this.length - 1];
                    for (int r82 = 0; r82 < r15; r82++) {
                        if (((1 << r82) & r62) != 0) {
                            for (int r92 = 0; r92 < gF2Matrix.length; r92++) {
                                int[] r102 = gF2Matrix2.matrix[r5];
                                r102[r92] = r102[r92] ^ gF2Matrix.matrix[r7][r92];
                            }
                        }
                        r7++;
                    }
                }
                return gF2Matrix2;
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("matrix is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        if (vector.length == this.numColumns) {
            GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numColumns);
            for (int r1 = this.numColumns - 1; r1 >= 0; r1--) {
                int r2 = r1 >>> 5;
                int r3 = r1 & 31;
                int r4 = vector[r1] >>> 5;
                int r5 = vector[r1] & 31;
                for (int r6 = this.numRows - 1; r6 >= 0; r6--) {
                    int[] r7 = gF2Matrix.matrix[r6];
                    r7[r2] = r7[r2] | (((this.matrix[r6][r4] >>> r5) & 1) << r3);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("length mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length == this.numColumns) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] r0 = new int[(this.numRows + 31) >>> 5];
                for (int r2 = 0; r2 < this.numRows; r2++) {
                    int r4 = 0;
                    for (int r3 = 0; r3 < this.length; r3++) {
                        r4 ^= this.matrix[r2][r3] & vecArray[r3];
                    }
                    int r5 = 0;
                    for (int r32 = 0; r32 < 32; r32++) {
                        r5 ^= (r4 >>> r32) & 1;
                    }
                    if (r5 == 1) {
                        int r33 = r2 >>> 5;
                        r0[r33] = r0[r33] | (1 << (r2 & 31));
                    }
                }
                return new GF2Vector(r0, this.numRows);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    public Vector rightMultiplyRightCompactForm(Vector vector) {
        int r11;
        if (vector instanceof GF2Vector) {
            if (vector.length == this.numColumns + this.numRows) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] r0 = new int[(this.numRows + 31) >>> 5];
                int r1 = this.numRows >> 5;
                int r2 = this.numRows & 31;
                for (int r4 = 0; r4 < this.numRows; r4++) {
                    int r5 = r4 >> 5;
                    int r7 = r4 & 31;
                    int r6 = (vecArray[r5] >>> r7) & 1;
                    int r10 = r1;
                    int r9 = 0;
                    if (r2 != 0) {
                        while (true) {
                            r11 = this.length;
                            if (r9 >= r11 - 1) {
                                break;
                            }
                            int r112 = r10 + 1;
                            r6 ^= ((vecArray[r10] >>> r2) | (vecArray[r112] << (32 - r2))) & this.matrix[r4][r9];
                            r9++;
                            r10 = r112;
                        }
                        int r92 = r10 + 1;
                        int r102 = vecArray[r10] >>> r2;
                        if (r92 < vecArray.length) {
                            r102 |= vecArray[r92] << (32 - r2);
                        }
                        r6 ^= this.matrix[r4][r11 - 1] & r102;
                    } else {
                        while (r9 < this.length) {
                            r6 ^= vecArray[r10] & this.matrix[r4][r9];
                            r9++;
                            r10++;
                        }
                    }
                    int r103 = 0;
                    for (int r93 = 0; r93 < 32; r93++) {
                        r103 ^= r6 & 1;
                        r6 >>>= 1;
                    }
                    if (r103 == 1) {
                        r0[r5] = r0[r5] | (1 << r7);
                    }
                }
                return new GF2Vector(r0, this.numRows);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        int r0 = this.numColumns & 31;
        int r1 = this.length;
        if (r0 != 0) {
            r1--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int r4 = 0; r4 < this.numRows; r4++) {
            stringBuffer.append(r4 + ": ");
            for (int r5 = 0; r5 < r1; r5++) {
                int r8 = this.matrix[r4][r5];
                for (int r9 = 0; r9 < 32; r9++) {
                    if (((r8 >>> r9) & 1) == 0) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append('1');
                    }
                }
                stringBuffer.append(' ');
            }
            int r52 = this.matrix[r4][this.length - 1];
            for (int r82 = 0; r82 < r0; r82++) {
                if (((r52 >>> r82) & 1) == 0) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append('1');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }
}
