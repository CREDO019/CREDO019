package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int r3) {
        super(false, r3, new BERSequence());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERTaggedObject(int r1, int r2, int r3, ASN1Encodable aSN1Encodable) {
        super(r1, r2, r3, aSN1Encodable);
    }

    public BERTaggedObject(int r2, int r3, ASN1Encodable aSN1Encodable) {
        super(true, r2, r3, aSN1Encodable);
    }

    public BERTaggedObject(int r2, ASN1Encodable aSN1Encodable) {
        super(true, r2, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int r2, int r3, ASN1Encodable aSN1Encodable) {
        super(z, r2, r3, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int r2, ASN1Encodable aSN1Encodable) {
        super(z, r2, aSN1Encodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        ASN1Primitive aSN1Primitive = this.obj.toASN1Primitive();
        boolean isExplicit = isExplicit();
        if (z) {
            int r6 = this.tagClass;
            if (isExplicit || aSN1Primitive.encodeConstructed()) {
                r6 |= 32;
            }
            aSN1OutputStream.writeIdentifier(true, r6, this.tagNo);
        }
        if (!isExplicit) {
            aSN1Primitive.encode(aSN1OutputStream, false);
            return;
        }
        aSN1OutputStream.write(128);
        aSN1Primitive.encode(aSN1OutputStream, true);
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean encodeConstructed() {
        return isExplicit() || this.obj.toASN1Primitive().encodeConstructed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) throws IOException {
        ASN1Primitive aSN1Primitive = this.obj.toASN1Primitive();
        boolean isExplicit = isExplicit();
        int encodedLength = aSN1Primitive.encodedLength(isExplicit);
        if (isExplicit) {
            encodedLength += 3;
        }
        return encodedLength + (z ? ASN1OutputStream.getLengthOfIdentifier(this.tagNo) : 0);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    String getASN1Encoding() {
        return ASN1Encoding.BER;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1Sequence rebuildConstructed(ASN1Primitive aSN1Primitive) {
        return new BERSequence(aSN1Primitive);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1TaggedObject replaceTag(int r4, int r5) {
        return new BERTaggedObject(this.explicitness, r4, r5, this.obj);
    }
}
