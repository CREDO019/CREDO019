package org.bouncycastle.asn1.x509;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes5.dex */
public abstract class X509NameEntryConverter {
    protected boolean canBePrintable(String str) {
        return ASN1PrintableString.isPrintableString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Primitive convertHexEncoded(String str, int r3) throws IOException {
        return ASN1Primitive.fromByteArray(Hex.decodeStrict(str, r3, str.length() - r3));
    }

    public abstract ASN1Primitive getConvertedValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str);
}
