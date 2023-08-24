package org.bouncycastle.asn1.cryptlib;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes5.dex */
public class CryptlibObjectIdentifiers {
    public static final ASN1ObjectIdentifier cryptlib;
    public static final ASN1ObjectIdentifier curvey25519;
    public static final ASN1ObjectIdentifier ecc;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.4.1.3029");
        cryptlib = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).branch("5");
        ecc = branch;
        curvey25519 = branch.branch(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }
}
