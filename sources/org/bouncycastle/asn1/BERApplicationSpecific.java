package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class BERApplicationSpecific extends ASN1ApplicationSpecific {
    public BERApplicationSpecific(int r2, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, r2, aSN1Encodable);
    }

    public BERApplicationSpecific(int r4, ASN1EncodableVector aSN1EncodableVector) {
        super(new BERTaggedObject(false, 64, r4, (ASN1Encodable) BERFactory.createSequence(aSN1EncodableVector)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public BERApplicationSpecific(boolean z, int r4, ASN1Encodable aSN1Encodable) throws IOException {
        super(new BERTaggedObject(z, 64, r4, aSN1Encodable));
    }
}
