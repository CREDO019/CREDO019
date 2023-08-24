package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public interface ASN1TaggedObjectParser extends ASN1Encodable, InMemoryRepresentable {
    ASN1Encodable getObjectParser(int r1, boolean z) throws IOException;

    int getTagClass();

    int getTagNo();

    boolean hasContextTag(int r1);

    boolean hasTag(int r1, int r2);

    ASN1Encodable parseBaseUniversal(boolean z, int r2) throws IOException;

    ASN1Encodable parseExplicitBaseObject() throws IOException;

    ASN1TaggedObjectParser parseExplicitBaseTagged() throws IOException;

    ASN1TaggedObjectParser parseImplicitBaseTagged(int r1, int r2) throws IOException;
}
