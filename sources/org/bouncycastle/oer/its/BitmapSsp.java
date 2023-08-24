package org.bouncycastle.oer.its;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DEROctetString;

/* loaded from: classes5.dex */
public class BitmapSsp extends DEROctetString {
    public BitmapSsp(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable);
    }

    public BitmapSsp(byte[] bArr) {
        super(bArr);
    }
}
