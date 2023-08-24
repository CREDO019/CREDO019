package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class BERApplicationSpecificParser extends BERTaggedObjectParser implements ASN1ApplicationSpecificParser {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BERApplicationSpecificParser(int r2, ASN1StreamParser aSN1StreamParser) {
        super(64, r2, aSN1StreamParser);
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecificParser
    public ASN1Encodable readObject() throws IOException {
        return parseExplicitBaseObject();
    }
}
