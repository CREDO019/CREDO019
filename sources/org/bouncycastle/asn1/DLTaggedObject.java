package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class DLTaggedObject extends ASN1TaggedObject {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DLTaggedObject(int r1, int r2, int r3, ASN1Encodable aSN1Encodable) {
        super(r1, r2, r3, aSN1Encodable);
    }

    public DLTaggedObject(int r2, int r3, ASN1Encodable aSN1Encodable) {
        super(true, r2, r3, aSN1Encodable);
    }

    public DLTaggedObject(int r2, ASN1Encodable aSN1Encodable) {
        super(true, r2, aSN1Encodable);
    }

    public DLTaggedObject(boolean z, int r2, int r3, ASN1Encodable aSN1Encodable) {
        super(z, r2, r3, aSN1Encodable);
    }

    public DLTaggedObject(boolean z, int r2, ASN1Encodable aSN1Encodable) {
        super(z, r2, aSN1Encodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        ASN1Primitive dLObject = this.obj.toASN1Primitive().toDLObject();
        boolean isExplicit = isExplicit();
        if (z) {
            int r6 = this.tagClass;
            if (isExplicit || dLObject.encodeConstructed()) {
                r6 |= 32;
            }
            aSN1OutputStream.writeIdentifier(true, r6, this.tagNo);
        }
        if (isExplicit) {
            aSN1OutputStream.writeDL(dLObject.encodedLength(true));
        }
        dLObject.encode(aSN1OutputStream.getDLSubStream(), isExplicit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean encodeConstructed() {
        return isExplicit() || this.obj.toASN1Primitive().toDLObject().encodeConstructed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) throws IOException {
        ASN1Primitive dLObject = this.obj.toASN1Primitive().toDLObject();
        boolean isExplicit = isExplicit();
        int encodedLength = dLObject.encodedLength(isExplicit);
        if (isExplicit) {
            encodedLength += ASN1OutputStream.getLengthOfDL(encodedLength);
        }
        return encodedLength + (z ? ASN1OutputStream.getLengthOfIdentifier(this.tagNo) : 0);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    String getASN1Encoding() {
        return ASN1Encoding.f1585DL;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1Sequence rebuildConstructed(ASN1Primitive aSN1Primitive) {
        return new DLSequence(aSN1Primitive);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1TaggedObject replaceTag(int r4, int r5) {
        return new DLTaggedObject(this.explicitness, r4, r5, this.obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return this;
    }
}
