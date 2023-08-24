package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes5.dex */
public class SubsequentMessage extends ASN1Integer {
    public static final SubsequentMessage encrCert = new SubsequentMessage(0);
    public static final SubsequentMessage challengeResp = new SubsequentMessage(1);

    private SubsequentMessage(int r3) {
        super(r3);
    }

    public static SubsequentMessage valueOf(int r3) {
        if (r3 == 0) {
            return encrCert;
        }
        if (r3 == 1) {
            return challengeResp;
        }
        throw new IllegalArgumentException("unknown value: " + r3);
    }
}
