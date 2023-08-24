package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class ASN1UniversalType extends ASN1Type {
    final ASN1Tag tag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UniversalType(Class cls, int r2) {
        super(cls);
        this.tag = ASN1Tag.create(0, r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ASN1Primitive checkedCast(ASN1Primitive aSN1Primitive) {
        if (this.javaClass.isInstance(aSN1Primitive)) {
            return aSN1Primitive;
        }
        throw new IllegalStateException("unexpected object: " + aSN1Primitive.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ASN1Primitive fromByteArray(byte[] bArr) throws IOException {
        return checkedCast(ASN1Primitive.fromByteArray(bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive fromImplicitConstructed(ASN1Sequence aSN1Sequence) {
        throw new IllegalStateException("unexpected implicit constructed encoding");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
        throw new IllegalStateException("unexpected implicit primitive encoding");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ASN1Primitive getContextInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (128 == aSN1TaggedObject.getTagClass()) {
            return checkedCast(aSN1TaggedObject.getBaseUniversal(z, this));
        }
        throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
    }

    final ASN1Tag getTag() {
        return this.tag;
    }
}
