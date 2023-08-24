package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class DERSet extends ASN1Set {
    private int contentsLength;

    public DERSet() {
        this.contentsLength = -1;
    }

    public DERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
        this.contentsLength = -1;
    }

    public DERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, true);
        this.contentsLength = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERSet(boolean z, ASN1Encodable[] aSN1EncodableArr) {
        super(checkSorted(z), aSN1EncodableArr);
        this.contentsLength = -1;
    }

    public DERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, true);
        this.contentsLength = -1;
    }

    private static boolean checkSorted(boolean z) {
        if (z) {
            return z;
        }
        throw new IllegalStateException("DERSet elements should always be in sorted order");
    }

    public static DERSet convert(ASN1Set aSN1Set) {
        return (DERSet) aSN1Set.toDERObject();
    }

    private int getContentsLength() throws IOException {
        if (this.contentsLength < 0) {
            int length = this.elements.length;
            int r2 = 0;
            for (int r1 = 0; r1 < length; r1++) {
                r2 += this.elements[r1].toASN1Primitive().toDERObject().encodedLength(true);
            }
            this.contentsLength = r2;
        }
        return this.contentsLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeIdentifier(z, 49);
        DEROutputStream dERSubStream = aSN1OutputStream.getDERSubStream();
        int length = this.elements.length;
        int r2 = 0;
        if (this.contentsLength >= 0 || length > 16) {
            aSN1OutputStream.writeDL(getContentsLength());
            while (r2 < length) {
                this.elements[r2].toASN1Primitive().toDERObject().encode(dERSubStream, true);
                r2++;
            }
            return;
        }
        ASN1Primitive[] aSN1PrimitiveArr = new ASN1Primitive[length];
        int r5 = 0;
        for (int r4 = 0; r4 < length; r4++) {
            ASN1Primitive dERObject = this.elements[r4].toASN1Primitive().toDERObject();
            aSN1PrimitiveArr[r4] = dERObject;
            r5 += dERObject.encodedLength(true);
        }
        this.contentsLength = r5;
        aSN1OutputStream.writeDL(r5);
        while (r2 < length) {
            aSN1PrimitiveArr[r2].encode(dERSubStream, true);
            r2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) throws IOException {
        return ASN1OutputStream.getLengthOfEncodingDL(z, getContentsLength());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Set, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return this.isSorted ? this : super.toDERObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Set, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return this;
    }
}
