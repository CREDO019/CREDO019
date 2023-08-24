package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class GF2mVector extends Vector {
    private GF2mField field;
    private int[] vector;

    public GF2mVector(GF2mField gF2mField, byte[] bArr) {
        this.field = new GF2mField(gF2mField);
        int r0 = 8;
        int r1 = 1;
        while (gF2mField.getDegree() > r0) {
            r1++;
            r0 += 8;
        }
        if (bArr.length % r1 != 0) {
            throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
        }
        this.length = bArr.length / r1;
        this.vector = new int[this.length];
        int r4 = 0;
        for (int r2 = 0; r2 < this.vector.length; r2++) {
            int r5 = 0;
            while (r5 < r0) {
                int[] r6 = this.vector;
                r6[r2] = ((bArr[r4] & 255) << r5) | r6[r2];
                r5 += 8;
                r4++;
            }
            if (!gF2mField.isElementOfThisField(this.vector[r2])) {
                throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
            }
        }
    }

    public GF2mVector(GF2mField gF2mField, int[] r4) {
        this.field = gF2mField;
        this.length = r4.length;
        for (int length = r4.length - 1; length >= 0; length--) {
            if (!gF2mField.isElementOfThisField(r4[length])) {
                throw new ArithmeticException("Element array is not specified over the given finite field.");
            }
        }
        this.vector = IntUtils.clone(r4);
    }

    public GF2mVector(GF2mVector gF2mVector) {
        this.field = new GF2mField(gF2mVector.field);
        this.length = gF2mVector.length;
        this.vector = IntUtils.clone(gF2mVector.vector);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector add(Vector vector) {
        throw new RuntimeException("not implemented");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean equals(Object obj) {
        if (obj instanceof GF2mVector) {
            GF2mVector gF2mVector = (GF2mVector) obj;
            if (this.field.equals(gF2mVector.field)) {
                return IntUtils.equals(this.vector, gF2mVector.vector);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public byte[] getEncoded() {
        int r0 = 8;
        int r1 = 1;
        while (this.field.getDegree() > r0) {
            r1++;
            r0 += 8;
        }
        byte[] bArr = new byte[this.vector.length * r1];
        int r4 = 0;
        for (int r3 = 0; r3 < this.vector.length; r3++) {
            int r5 = 0;
            while (r5 < r0) {
                bArr[r4] = (byte) (this.vector[r3] >>> r5);
                r5 += 8;
                r4++;
            }
        }
        return bArr;
    }

    public GF2mField getField() {
        return this.field;
    }

    public int[] getIntArrayForm() {
        return IntUtils.clone(this.vector);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public int hashCode() {
        return (this.field.hashCode() * 31) + Arrays.hashCode(this.vector);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean isZero() {
        for (int length = this.vector.length - 1; length >= 0; length--) {
            if (this.vector[length] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector multiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        if (this.length == vector.length) {
            int[] r0 = new int[this.length];
            for (int r1 = 0; r1 < vector.length; r1++) {
                r0[r1] = this.vector[vector[r1]];
            }
            return new GF2mVector(this.field, r0);
        }
        throw new ArithmeticException("permutation size and vector size mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int r2 = 0; r2 < this.vector.length; r2++) {
            for (int r3 = 0; r3 < this.field.getDegree(); r3++) {
                stringBuffer.append(((1 << (r3 & 31)) & this.vector[r2]) != 0 ? '1' : '0');
            }
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }
}
