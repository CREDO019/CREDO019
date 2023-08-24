package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
class DLTaggedObjectParser extends BERTaggedObjectParser {
    private final boolean _constructed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLTaggedObjectParser(int r1, int r2, boolean z, ASN1StreamParser aSN1StreamParser) {
        super(r1, r2, aSN1StreamParser);
        this._constructed = z;
    }

    @Override // org.bouncycastle.asn1.BERTaggedObjectParser, org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return this._parser.loadTaggedDL(this._tagClass, this._tagNo, this._constructed);
    }

    @Override // org.bouncycastle.asn1.BERTaggedObjectParser
    public boolean isConstructed() {
        return this._constructed;
    }

    @Override // org.bouncycastle.asn1.BERTaggedObjectParser, org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseBaseUniversal(boolean z, int r2) throws IOException {
        if (!z) {
            return this._constructed ? this._parser.parseImplicitConstructedDL(r2) : this._parser.parseImplicitPrimitive(r2);
        } else if (this._constructed) {
            return this._parser.parseObject(r2);
        } else {
            throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
        }
    }

    @Override // org.bouncycastle.asn1.BERTaggedObjectParser, org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseExplicitBaseObject() throws IOException {
        if (this._constructed) {
            return this._parser.readObject();
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    @Override // org.bouncycastle.asn1.BERTaggedObjectParser, org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseExplicitBaseTagged() throws IOException {
        if (this._constructed) {
            return this._parser.parseTaggedObject();
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    @Override // org.bouncycastle.asn1.BERTaggedObjectParser, org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseImplicitBaseTagged(int r4, int r5) throws IOException {
        return 64 == r4 ? (DLApplicationSpecific) this._parser.loadTaggedDL(r4, r5, this._constructed) : new DLTaggedObjectParser(r4, r5, this._constructed, this._parser);
    }
}
