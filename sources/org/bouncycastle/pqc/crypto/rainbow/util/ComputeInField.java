package org.bouncycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public class ComputeInField {

    /* renamed from: A */
    private short[][] f2499A;

    /* renamed from: x */
    short[] f2500x;

    private void computeZerosAbove() throws RuntimeException {
        for (int length = this.f2499A.length - 1; length > 0; length--) {
            for (int r1 = length - 1; r1 >= 0; r1--) {
                short[][] sArr = this.f2499A;
                short s = sArr[r1][length];
                short invElem = GF2Field.invElem(sArr[length][length]);
                if (invElem == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                int r4 = length;
                while (true) {
                    short[][] sArr2 = this.f2499A;
                    if (r4 < sArr2.length * 2) {
                        short multElem = GF2Field.multElem(s, GF2Field.multElem(sArr2[length][r4], invElem));
                        short[][] sArr3 = this.f2499A;
                        sArr3[r1][r4] = GF2Field.addElem(sArr3[r1][r4], multElem);
                        r4++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
        r0 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void computeZerosUnder(boolean r10) throws java.lang.RuntimeException {
        /*
            r9 = this;
            if (r10 == 0) goto L8
            short[][] r10 = r9.f2499A
            int r10 = r10.length
            int r10 = r10 * 2
            goto Ld
        L8:
            short[][] r10 = r9.f2499A
            int r10 = r10.length
            int r10 = r10 + 1
        Ld:
            r0 = 0
        Le:
            short[][] r1 = r9.f2499A
            int r1 = r1.length
            int r1 = r1 + (-1)
            if (r0 >= r1) goto L5a
            int r1 = r0 + 1
            r2 = r1
        L18:
            short[][] r3 = r9.f2499A
            int r4 = r3.length
            if (r2 >= r4) goto L58
            r4 = r3[r2]
            short r4 = r4[r0]
            r3 = r3[r0]
            short r3 = r3[r0]
            short r3 = org.bouncycastle.pqc.crypto.rainbow.util.GF2Field.invElem(r3)
            if (r3 == 0) goto L50
            r5 = r0
        L2c:
            if (r5 >= r10) goto L4d
            short[][] r6 = r9.f2499A
            r6 = r6[r0]
            short r6 = r6[r5]
            short r6 = org.bouncycastle.pqc.crypto.rainbow.util.GF2Field.multElem(r6, r3)
            short r6 = org.bouncycastle.pqc.crypto.rainbow.util.GF2Field.multElem(r4, r6)
            short[][] r7 = r9.f2499A
            r8 = r7[r2]
            r7 = r7[r2]
            short r7 = r7[r5]
            short r6 = org.bouncycastle.pqc.crypto.rainbow.util.GF2Field.addElem(r7, r6)
            r8[r5] = r6
            int r5 = r5 + 1
            goto L2c
        L4d:
            int r2 = r2 + 1
            goto L18
        L50:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "Matrix not invertible! We have to choose another one!"
            r10.<init>(r0)
            throw r10
        L58:
            r0 = r1
            goto Le
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField.computeZerosUnder(boolean):void");
    }

    private void substitute() throws IllegalStateException {
        short[][] sArr;
        short invElem = GF2Field.invElem(this.f2499A[sArr.length - 1][sArr.length - 1]);
        if (invElem == 0) {
            throw new IllegalStateException("The equation system is not solvable");
        }
        short[] sArr2 = this.f2500x;
        short[][] sArr3 = this.f2499A;
        sArr2[sArr3.length - 1] = GF2Field.multElem(sArr3[sArr3.length - 1][sArr3.length], invElem);
        for (int length = this.f2499A.length - 2; length >= 0; length--) {
            short[][] sArr4 = this.f2499A;
            short s = sArr4[length][sArr4.length];
            for (int length2 = sArr4.length - 1; length2 > length; length2--) {
                s = GF2Field.addElem(s, GF2Field.multElem(this.f2499A[length][length2], this.f2500x[length2]));
            }
            short invElem2 = GF2Field.invElem(this.f2499A[length][length]);
            if (invElem2 == 0) {
                throw new IllegalStateException("Not solvable equation system");
            }
            this.f2500x[length] = GF2Field.multElem(s, invElem2);
        }
    }

    public short[][] addSquareMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr.length == sArr2.length && sArr[0].length == sArr2[0].length) {
            short[][] sArr3 = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length);
            for (int r2 = 0; r2 < sArr.length; r2++) {
                for (int r3 = 0; r3 < sArr2.length; r3++) {
                    sArr3[r2][r3] = GF2Field.addElem(sArr[r2][r3], sArr2[r2][r3]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Addition is not possible!");
    }

    public short[] addVect(short[] sArr, short[] sArr2) {
        if (sArr.length == sArr2.length) {
            int length = sArr.length;
            short[] sArr3 = new short[length];
            for (int r2 = 0; r2 < length; r2++) {
                sArr3[r2] = GF2Field.addElem(sArr[r2], sArr2[r2]);
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[][] inverse(short[][] sArr) {
        try {
            int r2 = 0;
            this.f2499A = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length * 2);
            if (sArr.length != sArr[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (int r1 = 0; r1 < sArr.length; r1++) {
                for (int r4 = 0; r4 < sArr.length; r4++) {
                    this.f2499A[r1][r4] = sArr[r1][r4];
                }
                for (int length = sArr.length; length < sArr.length * 2; length++) {
                    this.f2499A[r1][length] = 0;
                }
                short[][] sArr2 = this.f2499A;
                sArr2[r1][sArr2.length + r1] = 1;
            }
            computeZerosUnder(true);
            int r9 = 0;
            while (true) {
                short[][] sArr3 = this.f2499A;
                if (r9 >= sArr3.length) {
                    break;
                }
                short invElem = GF2Field.invElem(sArr3[r9][r9]);
                int r42 = r9;
                while (true) {
                    short[][] sArr4 = this.f2499A;
                    if (r42 < sArr4.length * 2) {
                        sArr4[r9][r42] = GF2Field.multElem(sArr4[r9][r42], invElem);
                        r42++;
                    }
                }
                r9++;
            }
            computeZerosAbove();
            short[][] sArr5 = this.f2499A;
            short[][] sArr6 = (short[][]) Array.newInstance(short.class, sArr5.length, sArr5.length);
            while (true) {
                short[][] sArr7 = this.f2499A;
                if (r2 >= sArr7.length) {
                    return sArr6;
                }
                int length2 = sArr7.length;
                while (true) {
                    short[][] sArr8 = this.f2499A;
                    if (length2 < sArr8.length * 2) {
                        sArr6[r2][length2 - sArr8.length] = sArr8[r2][length2];
                        length2++;
                    }
                }
                r2++;
            }
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short[][] multMatrix(short s, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, sArr.length, sArr[0].length);
        for (int r2 = 0; r2 < sArr.length; r2++) {
            for (int r3 = 0; r3 < sArr[0].length; r3++) {
                sArr2[r2][r3] = GF2Field.multElem(s, sArr[r2][r3]);
            }
        }
        return sArr2;
    }

    public short[] multVect(short s, short[] sArr) {
        int length = sArr.length;
        short[] sArr2 = new short[length];
        for (int r2 = 0; r2 < length; r2++) {
            sArr2[r2] = GF2Field.multElem(s, sArr[r2]);
        }
        return sArr2;
    }

    public short[][] multVects(short[] sArr, short[] sArr2) {
        if (sArr.length == sArr2.length) {
            short[][] sArr3 = (short[][]) Array.newInstance(short.class, sArr.length, sArr2.length);
            for (int r2 = 0; r2 < sArr.length; r2++) {
                for (int r3 = 0; r3 < sArr2.length; r3++) {
                    sArr3[r2][r3] = GF2Field.multElem(sArr[r2], sArr2[r3]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] multiplyMatrix(short[][] sArr, short[] sArr2) throws RuntimeException {
        if (sArr[0].length == sArr2.length) {
            short[] sArr3 = new short[sArr.length];
            for (int r2 = 0; r2 < sArr.length; r2++) {
                for (int r3 = 0; r3 < sArr2.length; r3++) {
                    sArr3[r2] = GF2Field.addElem(sArr3[r2], GF2Field.multElem(sArr[r2][r3], sArr2[r3]));
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[][] multiplyMatrix(short[][] sArr, short[][] sArr2) throws RuntimeException {
        if (sArr[0].length == sArr2.length) {
            this.f2499A = (short[][]) Array.newInstance(short.class, sArr.length, sArr2[0].length);
            for (int r1 = 0; r1 < sArr.length; r1++) {
                for (int r2 = 0; r2 < sArr2.length; r2++) {
                    for (int r3 = 0; r3 < sArr2[0].length; r3++) {
                        short multElem = GF2Field.multElem(sArr[r1][r2], sArr2[r2][r3]);
                        short[][] sArr3 = this.f2499A;
                        sArr3[r1][r3] = GF2Field.addElem(sArr3[r1][r3], multElem);
                    }
                }
            }
            return this.f2499A;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] solveEquation(short[][] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return null;
        }
        try {
            this.f2499A = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length + 1);
            this.f2500x = new short[sArr.length];
            for (int r0 = 0; r0 < sArr.length; r0++) {
                for (int r3 = 0; r3 < sArr[0].length; r3++) {
                    this.f2499A[r0][r3] = sArr[r0][r3];
                }
            }
            for (int r8 = 0; r8 < sArr2.length; r8++) {
                short[][] sArr3 = this.f2499A;
                sArr3[r8][sArr2.length] = GF2Field.addElem(sArr2[r8], sArr3[r8][sArr2.length]);
            }
            computeZerosUnder(false);
            substitute();
            return this.f2500x;
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
