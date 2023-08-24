package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class DERApplicationSpecific extends ASN1ApplicationSpecific {
    public DERApplicationSpecific(int r2, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, r2, aSN1Encodable);
    }

    public DERApplicationSpecific(int r4, ASN1EncodableVector aSN1EncodableVector) {
        super(new DERTaggedObject(false, 64, r4, (ASN1Encodable) DERFactory.createSequence(aSN1EncodableVector)));
    }

    public DERApplicationSpecific(int r4, byte[] bArr) {
        super(new DERTaggedObject(false, 64, r4, (ASN1Encodable) new DEROctetString(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public DERApplicationSpecific(boolean z, int r4, ASN1Encodable aSN1Encodable) throws IOException {
        super(new DERTaggedObject(z, 64, r4, aSN1Encodable));
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        return this;
    }
}
