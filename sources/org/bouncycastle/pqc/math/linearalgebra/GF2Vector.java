package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class GF2Vector extends Vector {

    /* renamed from: v */
    private int[] f2528v;

    public GF2Vector(int r2) {
        if (r2 < 0) {
            throw new ArithmeticException("Negative length.");
        }
        this.length = r2;
        this.f2528v = new int[(r2 + 31) >> 5];
    }

    public GF2Vector(int r5, int r6, SecureRandom secureRandom) {
        if (r6 > r5) {
            throw new ArithmeticException("The hamming weight is greater than the length of vector.");
        }
        this.length = r5;
        this.f2528v = new int[(r5 + 31) >> 5];
        int[] r0 = new int[r5];
        for (int r2 = 0; r2 < r5; r2++) {
            r0[r2] = r2;
        }
        for (int r1 = 0; r1 < r6; r1++) {
            int nextInt = RandUtils.nextInt(secureRandom, r5);
            setBit(r0[nextInt]);
            r5--;
            r0[nextInt] = r0[r5];
        }
    }

    public GF2Vector(int r6, SecureRandom secureRandom) {
        this.length = r6;
        int r0 = (r6 + 31) >> 5;
        this.f2528v = new int[r0];
        int r02 = r0 - 1;
        for (int r2 = r02; r2 >= 0; r2--) {
            this.f2528v[r2] = secureRandom.nextInt();
        }
        int r62 = r6 & 31;
        if (r62 != 0) {
            int[] r7 = this.f2528v;
            r7[r02] = ((1 << r62) - 1) & r7[r02];
        }
    }

    public GF2Vector(int r4, int[] r5) {
        if (r4 < 0) {
            throw new ArithmeticException("negative length");
        }
        this.length = r4;
        int r0 = (r4 + 31) >> 5;
        if (r5.length != r0) {
            throw new ArithmeticException("length mismatch");
        }
        int[] clone = IntUtils.clone(r5);
        this.f2528v = clone;
        int r42 = r4 & 31;
        if (r42 != 0) {
            int r02 = r0 - 1;
            clone[r02] = ((1 << r42) - 1) & clone[r02];
        }
    }

    public GF2Vector(GF2Vector gF2Vector) {
        this.length = gF2Vector.length;
        this.f2528v = IntUtils.clone(gF2Vector.f2528v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GF2Vector(int[] r1, int r2) {
        this.f2528v = r1;
        this.length = r2;
    }

    public static GF2Vector OS2VP(int r2, byte[] bArr) {
        if (r2 >= 0) {
            if (bArr.length <= ((r2 + 7) >> 3)) {
                return new GF2Vector(r2, LittleEndianConversions.toIntArray(bArr));
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("negative length");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector add(Vector vector) {
        if (vector instanceof GF2Vector) {
            GF2Vector gF2Vector = (GF2Vector) vector;
            if (this.length == gF2Vector.length) {
                int[] clone = IntUtils.clone(gF2Vector.f2528v);
                for (int length = clone.length - 1; length >= 0; length--) {
                    clone[length] = clone[length] ^ this.f2528v[length];
                }
                return new GF2Vector(this.length, clone);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean equals(Object obj) {
        if (obj instanceof GF2Vector) {
            GF2Vector gF2Vector = (GF2Vector) obj;
            return this.length == gF2Vector.length && IntUtils.equals(this.f2528v, gF2Vector.f2528v);
        }
        return false;
    }

    public GF2Vector extractLeftVector(int r6) {
        if (r6 <= this.length) {
            if (r6 == this.length) {
                return new GF2Vector(this);
            }
            GF2Vector gF2Vector = new GF2Vector(r6);
            int r1 = r6 >> 5;
            int r62 = r6 & 31;
            System.arraycopy(this.f2528v, 0, gF2Vector.f2528v, 0, r1);
            if (r62 != 0) {
                gF2Vector.f2528v[r1] = ((1 << r62) - 1) & this.f2528v[r1];
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid length");
    }

    public GF2Vector extractRightVector(int r9) {
        int r4;
        if (r9 <= this.length) {
            if (r9 == this.length) {
                return new GF2Vector(this);
            }
            GF2Vector gF2Vector = new GF2Vector(r9);
            int r1 = (this.length - r9) >> 5;
            int r2 = (this.length - r9) & 31;
            int r92 = (r9 + 31) >> 5;
            int r3 = 0;
            if (r2 != 0) {
                while (true) {
                    r4 = r92 - 1;
                    if (r3 >= r4) {
                        break;
                    }
                    int[] r42 = gF2Vector.f2528v;
                    int[] r5 = this.f2528v;
                    int r6 = r1 + 1;
                    r42[r3] = (r5[r1] >>> r2) | (r5[r6] << (32 - r2));
                    r3++;
                    r1 = r6;
                }
                int[] r93 = gF2Vector.f2528v;
                int[] r32 = this.f2528v;
                int r52 = r1 + 1;
                r93[r4] = r32[r1] >>> r2;
                if (r52 < r32.length) {
                    r93[r4] = r93[r4] | (r32[r52] << (32 - r2));
                }
            } else {
                System.arraycopy(this.f2528v, r1, gF2Vector.f2528v, 0, r92);
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid length");
    }

    public GF2Vector extractVector(int[] r9) {
        int length = r9.length;
        if (r9[length - 1] <= this.length) {
            GF2Vector gF2Vector = new GF2Vector(length);
            for (int r2 = 0; r2 < length; r2++) {
                if ((this.f2528v[r9[r2] >> 5] & (1 << (r9[r2] & 31))) != 0) {
                    int[] r3 = gF2Vector.f2528v;
                    int r4 = r2 >> 5;
                    r3[r4] = (1 << (r2 & 31)) | r3[r4];
                }
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid index set");
    }

    public int getBit(int r3) {
        if (r3 < this.length) {
            int r0 = r3 >> 5;
            int r32 = r3 & 31;
            return (this.f2528v[r0] & (1 << r32)) >>> r32;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public byte[] getEncoded() {
        return LittleEndianConversions.toByteArray(this.f2528v, (this.length + 7) >> 3);
    }

    public int getHammingWeight() {
        int r1 = 0;
        int r2 = 0;
        while (true) {
            int[] r3 = this.f2528v;
            if (r1 >= r3.length) {
                return r2;
            }
            int r32 = r3[r1];
            for (int r4 = 0; r4 < 32; r4++) {
                if ((r32 & 1) != 0) {
                    r2++;
                }
                r32 >>>= 1;
            }
            r1++;
        }
    }

    public int[] getVecArray() {
        return this.f2528v;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public int hashCode() {
        return (this.length * 31) + Arrays.hashCode(this.f2528v);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean isZero() {
        for (int length = this.f2528v.length - 1; length >= 0; length--) {
            if (this.f2528v[length] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector multiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        if (this.length == vector.length) {
            GF2Vector gF2Vector = new GF2Vector(this.length);
            for (int r1 = 0; r1 < vector.length; r1++) {
                if ((this.f2528v[vector[r1] >> 5] & (1 << (vector[r1] & 31))) != 0) {
                    int[] r2 = gF2Vector.f2528v;
                    int r3 = r1 >> 5;
                    r2[r3] = (1 << (r1 & 31)) | r2[r3];
                }
            }
            return gF2Vector;
        }
        throw new ArithmeticException("length mismatch");
    }

    public void setBit(int r5) {
        if (r5 >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        int[] r0 = this.f2528v;
        int r1 = r5 >> 5;
        r0[r1] = (1 << (r5 & 31)) | r0[r1];
    }

    public GF2mVector toExtensionFieldVector(GF2mField gF2mField) {
        int degree = gF2mField.getDegree();
        if (this.length % degree == 0) {
            int r1 = this.length / degree;
            int[] r0 = new int[r1];
            int r2 = 0;
            for (int r12 = r1 - 1; r12 >= 0; r12--) {
                for (int degree2 = gF2mField.getDegree() - 1; degree2 >= 0; degree2--) {
                    if (((this.f2528v[r2 >>> 5] >>> (r2 & 31)) & 1) == 1) {
                        r0[r12] = r0[r12] ^ (1 << degree2);
                    }
                    r2++;
                }
            }
            return new GF2mVector(gF2mField, r0);
        }
        throw new ArithmeticException("conversion is impossible");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int r1 = 0; r1 < this.length; r1++) {
            if (r1 != 0 && (r1 & 31) == 0) {
                stringBuffer.append(' ');
            }
            stringBuffer.append((this.f2528v[r1 >> 5] & (1 << (r1 & 31))) == 0 ? '0' : '1');
        }
        return stringBuffer.toString();
    }
}
