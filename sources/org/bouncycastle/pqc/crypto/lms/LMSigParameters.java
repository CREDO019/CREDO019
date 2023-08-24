package org.bouncycastle.pqc.crypto.lms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;

/* loaded from: classes3.dex */
public class LMSigParameters {
    private final ASN1ObjectIdentifier digestOid;

    /* renamed from: h */
    private final int f2438h;

    /* renamed from: m */
    private final int f2439m;
    private final int type;
    public static final LMSigParameters lms_sha256_n32_h5 = new LMSigParameters(5, 32, 5, NISTObjectIdentifiers.id_sha256);
    public static final LMSigParameters lms_sha256_n32_h10 = new LMSigParameters(6, 32, 10, NISTObjectIdentifiers.id_sha256);
    public static final LMSigParameters lms_sha256_n32_h15 = new LMSigParameters(7, 32, 15, NISTObjectIdentifiers.id_sha256);
    public static final LMSigParameters lms_sha256_n32_h20 = new LMSigParameters(8, 32, 20, NISTObjectIdentifiers.id_sha256);
    public static final LMSigParameters lms_sha256_n32_h25 = new LMSigParameters(9, 32, 25, NISTObjectIdentifiers.id_sha256);
    private static Map<Object, LMSigParameters> paramBuilders = new HashMap<Object, LMSigParameters>() { // from class: org.bouncycastle.pqc.crypto.lms.LMSigParameters.1
        {
            put(Integer.valueOf(LMSigParameters.lms_sha256_n32_h5.type), LMSigParameters.lms_sha256_n32_h5);
            put(Integer.valueOf(LMSigParameters.lms_sha256_n32_h10.type), LMSigParameters.lms_sha256_n32_h10);
            put(Integer.valueOf(LMSigParameters.lms_sha256_n32_h15.type), LMSigParameters.lms_sha256_n32_h15);
            put(Integer.valueOf(LMSigParameters.lms_sha256_n32_h20.type), LMSigParameters.lms_sha256_n32_h20);
            put(Integer.valueOf(LMSigParameters.lms_sha256_n32_h25.type), LMSigParameters.lms_sha256_n32_h25);
        }
    };

    protected LMSigParameters(int r1, int r2, int r3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.type = r1;
        this.f2439m = r2;
        this.f2438h = r3;
        this.digestOid = aSN1ObjectIdentifier;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LMSigParameters getParametersForType(int r1) {
        return paramBuilders.get(Integer.valueOf(r1));
    }

    public ASN1ObjectIdentifier getDigestOID() {
        return this.digestOid;
    }

    public int getH() {
        return this.f2438h;
    }

    public int getM() {
        return this.f2439m;
    }

    public int getType() {
        return this.type;
    }
}
