package org.bouncycastle.asn1.x500.style;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;

/* loaded from: classes5.dex */
public class BCStrictStyle extends BCStyle {
    public static final X500NameStyle INSTANCE = new BCStrictStyle();

    @Override // org.bouncycastle.asn1.x500.style.AbstractX500NameStyle, org.bouncycastle.asn1.x500.X500NameStyle
    public boolean areEqual(X500Name x500Name, X500Name x500Name2) {
        RDN[] rDNs = x500Name.getRDNs();
        RDN[] rDNs2 = x500Name2.getRDNs();
        if (rDNs.length != rDNs2.length) {
            return false;
        }
        for (int r0 = 0; r0 != rDNs.length; r0++) {
            if (!rdnAreEqual(rDNs[r0], rDNs2[r0])) {
                return false;
            }
        }
        return true;
    }
}
