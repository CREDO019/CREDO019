package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extension;

/* loaded from: classes5.dex */
public class ExtensionReq extends ASN1Object {
    private final Extension[] extensions;

    private ExtensionReq(ASN1Sequence aSN1Sequence) {
        this.extensions = new Extension[aSN1Sequence.size()];
        for (int r0 = 0; r0 != aSN1Sequence.size(); r0++) {
            this.extensions[r0] = Extension.getInstance(aSN1Sequence.getObjectAt(r0));
        }
    }

    public ExtensionReq(Extension extension) {
        this.extensions = new Extension[]{extension};
    }

    public ExtensionReq(Extension[] extensionArr) {
        this.extensions = C5126Utils.clone(extensionArr);
    }

    public static ExtensionReq getInstance(Object obj) {
        if (obj instanceof ExtensionReq) {
            return (ExtensionReq) obj;
        }
        if (obj != null) {
            return new ExtensionReq(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static ExtensionReq getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extension[] getExtensions() {
        return C5126Utils.clone(this.extensions);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.extensions);
    }
}
