package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

/* loaded from: classes4.dex */
public abstract class GF2nField {
    protected GF2Polynomial fieldPolynomial;
    protected java.util.Vector fields;
    protected int mDegree;
    protected java.util.Vector matrices;
    protected final SecureRandom random;

    /* JADX INFO: Access modifiers changed from: protected */
    public GF2nField(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void computeCOBMatrix(GF2nField gF2nField);

    protected abstract void computeFieldPolynomial();

    public final GF2nElement convert(GF2nElement gF2nElement, GF2nField gF2nField) throws RuntimeException {
        if (gF2nField == this || this.fieldPolynomial.equals(gF2nField.fieldPolynomial)) {
            return (GF2nElement) gF2nElement.clone();
        }
        if (this.mDegree == gF2nField.mDegree) {
            int indexOf = this.fields.indexOf(gF2nField);
            if (indexOf == -1) {
                computeCOBMatrix(gF2nField);
                indexOf = this.fields.indexOf(gF2nField);
            }
            GF2Polynomial[] gF2PolynomialArr = (GF2Polynomial[]) this.matrices.elementAt(indexOf);
            GF2nElement gF2nElement2 = (GF2nElement) gF2nElement.clone();
            if (gF2nElement2 instanceof GF2nONBElement) {
                ((GF2nONBElement) gF2nElement2).reverseOrder();
            }
            GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree, gF2nElement2.toFlexiBigInt());
            gF2Polynomial.expandN(this.mDegree);
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree);
            for (int r2 = 0; r2 < this.mDegree; r2++) {
                if (gF2Polynomial.vectorMult(gF2PolynomialArr[r2])) {
                    gF2Polynomial2.setBit((this.mDegree - 1) - r2);
                }
            }
            if (gF2nField instanceof GF2nPolynomialField) {
                return new GF2nPolynomialElement((GF2nPolynomialField) gF2nField, gF2Polynomial2);
            }
            if (gF2nField instanceof GF2nONBField) {
                GF2nONBElement gF2nONBElement = new GF2nONBElement((GF2nONBField) gF2nField, gF2Polynomial2.toFlexiBigInt());
                gF2nONBElement.reverseOrder();
                return gF2nONBElement;
            }
            throw new RuntimeException("GF2nField.convert: B1 must be an instance of GF2nPolynomialField or GF2nONBField!");
        }
        throw new RuntimeException("GF2nField.convert: B1 has a different degree and thus cannot be coverted to!");
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nField)) {
            return false;
        }
        GF2nField gF2nField = (GF2nField) obj;
        if (gF2nField.mDegree == this.mDegree && this.fieldPolynomial.equals(gF2nField.fieldPolynomial)) {
            if (!(this instanceof GF2nPolynomialField) || (gF2nField instanceof GF2nPolynomialField)) {
                return !(this instanceof GF2nONBField) || (gF2nField instanceof GF2nONBField);
            }
            return false;
        }
        return false;
    }

    public final int getDegree() {
        return this.mDegree;
    }

    public final GF2Polynomial getFieldPolynomial() {
        if (this.fieldPolynomial == null) {
            computeFieldPolynomial();
        }
        return new GF2Polynomial(this.fieldPolynomial);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial);

    public int hashCode() {
        return this.mDegree + this.fieldPolynomial.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final GF2Polynomial[] invertMatrix(GF2Polynomial[] gF2PolynomialArr) {
        GF2Polynomial[] gF2PolynomialArr2 = new GF2Polynomial[gF2PolynomialArr.length];
        GF2Polynomial[] gF2PolynomialArr3 = new GF2Polynomial[gF2PolynomialArr.length];
        int r2 = 0;
        for (int r3 = 0; r3 < this.mDegree; r3++) {
            gF2PolynomialArr2[r3] = new GF2Polynomial(gF2PolynomialArr[r3]);
            gF2PolynomialArr3[r3] = new GF2Polynomial(this.mDegree);
            gF2PolynomialArr3[r3].setBit((this.mDegree - 1) - r3);
        }
        while (true) {
            int r7 = this.mDegree;
            if (r2 >= r7 - 1) {
                for (int r72 = r7 - 1; r72 > 0; r72--) {
                    for (int r22 = r72 - 1; r22 >= 0; r22--) {
                        if (gF2PolynomialArr2[r22].testBit((this.mDegree - 1) - r72)) {
                            gF2PolynomialArr2[r22].addToThis(gF2PolynomialArr2[r72]);
                            gF2PolynomialArr3[r22].addToThis(gF2PolynomialArr3[r72]);
                        }
                    }
                }
                return gF2PolynomialArr3;
            }
            int r73 = r2;
            while (true) {
                int r32 = this.mDegree;
                if (r73 >= r32 || gF2PolynomialArr2[r73].testBit((r32 - 1) - r2)) {
                    break;
                }
                r73++;
            }
            if (r73 >= this.mDegree) {
                throw new RuntimeException("GF2nField.invertMatrix: Matrix cannot be inverted!");
            }
            if (r2 != r73) {
                GF2Polynomial gF2Polynomial = gF2PolynomialArr2[r2];
                gF2PolynomialArr2[r2] = gF2PolynomialArr2[r73];
                gF2PolynomialArr2[r73] = gF2Polynomial;
                GF2Polynomial gF2Polynomial2 = gF2PolynomialArr3[r2];
                gF2PolynomialArr3[r2] = gF2PolynomialArr3[r73];
                gF2PolynomialArr3[r73] = gF2Polynomial2;
            }
            int r74 = r2 + 1;
            int r33 = r74;
            while (true) {
                int r4 = this.mDegree;
                if (r33 < r4) {
                    if (gF2PolynomialArr2[r33].testBit((r4 - 1) - r2)) {
                        gF2PolynomialArr2[r33].addToThis(gF2PolynomialArr2[r2]);
                        gF2PolynomialArr3[r33].addToThis(gF2PolynomialArr3[r2]);
                    }
                    r33++;
                }
            }
            r2 = r74;
        }
    }
}
