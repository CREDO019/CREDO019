package org.bouncycastle.asn1;

import java.util.Objects;

/* loaded from: classes5.dex */
public class ASN1EncodableVector {
    private static final int DEFAULT_CAPACITY = 10;
    static final ASN1Encodable[] EMPTY_ELEMENTS = new ASN1Encodable[0];
    private boolean copyOnWrite;
    private int elementCount;
    private ASN1Encodable[] elements;

    public ASN1EncodableVector() {
        this(10);
    }

    public ASN1EncodableVector(int r2) {
        if (r2 < 0) {
            throw new IllegalArgumentException("'initialCapacity' must not be negative");
        }
        this.elements = r2 == 0 ? EMPTY_ELEMENTS : new ASN1Encodable[r2];
        this.elementCount = 0;
        this.copyOnWrite = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Encodable[] cloneElements(ASN1Encodable[] aSN1EncodableArr) {
        return aSN1EncodableArr.length < 1 ? EMPTY_ELEMENTS : (ASN1Encodable[]) aSN1EncodableArr.clone();
    }

    private void doAddAll(ASN1Encodable[] aSN1EncodableArr, String str) {
        int length = aSN1EncodableArr.length;
        if (length < 1) {
            return;
        }
        int length2 = this.elements.length;
        int r3 = this.elementCount + length;
        int r4 = 0;
        if ((r3 > length2) | this.copyOnWrite) {
            reallocate(r3);
        }
        do {
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[r4];
            Objects.requireNonNull(aSN1Encodable, str);
            this.elements[this.elementCount + r4] = aSN1Encodable;
            r4++;
        } while (r4 < length);
        this.elementCount = r3;
    }

    private void reallocate(int r4) {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[Math.max(this.elements.length, r4 + (r4 >> 1))];
        System.arraycopy(this.elements, 0, aSN1EncodableArr, 0, this.elementCount);
        this.elements = aSN1EncodableArr;
        this.copyOnWrite = false;
    }

    public void add(ASN1Encodable aSN1Encodable) {
        Objects.requireNonNull(aSN1Encodable, "'element' cannot be null");
        int length = this.elements.length;
        int r1 = this.elementCount + 1;
        if (this.copyOnWrite | (r1 > length)) {
            reallocate(r1);
        }
        this.elements[this.elementCount] = aSN1Encodable;
        this.elementCount = r1;
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Objects.requireNonNull(aSN1EncodableVector, "'other' cannot be null");
        doAddAll(aSN1EncodableVector.elements, "'other' elements cannot be null");
    }

    public void addAll(ASN1Encodable[] aSN1EncodableArr) {
        Objects.requireNonNull(aSN1EncodableArr, "'others' cannot be null");
        doAddAll(aSN1EncodableArr, "'others' elements cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable[] copyElements() {
        int r0 = this.elementCount;
        if (r0 == 0) {
            return EMPTY_ELEMENTS;
        }
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[r0];
        System.arraycopy(this.elements, 0, aSN1EncodableArr, 0, r0);
        return aSN1EncodableArr;
    }

    public ASN1Encodable get(int r3) {
        if (r3 < this.elementCount) {
            return this.elements[r3];
        }
        throw new ArrayIndexOutOfBoundsException(r3 + " >= " + this.elementCount);
    }

    public int size() {
        return this.elementCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable[] takeElements() {
        int r0 = this.elementCount;
        if (r0 == 0) {
            return EMPTY_ELEMENTS;
        }
        ASN1Encodable[] aSN1EncodableArr = this.elements;
        if (aSN1EncodableArr.length == r0) {
            this.copyOnWrite = true;
            return aSN1EncodableArr;
        }
        ASN1Encodable[] aSN1EncodableArr2 = new ASN1Encodable[r0];
        System.arraycopy(aSN1EncodableArr, 0, aSN1EncodableArr2, 0, r0);
        return aSN1EncodableArr2;
    }
}
