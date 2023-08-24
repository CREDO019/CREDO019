package io.jsonwebtoken;

import com.polidea.multiplatformbleadapter.utils.Constants;
import io.jsonwebtoken.lang.RuntimeEnvironment;

/* loaded from: classes5.dex */
public enum SignatureAlgorithm {
    NONE("none", "No digital signature or MAC performed", Constants.BluetoothLogLevel.NONE, null, false),
    HS256("HS256", "HMAC using SHA-256", "HMAC", "HmacSHA256", true),
    HS384("HS384", "HMAC using SHA-384", "HMAC", "HmacSHA384", true),
    HS512("HS512", "HMAC using SHA-512", "HMAC", "HmacSHA512", true),
    RS256("RS256", "RSASSA-PKCS-v1_5 using SHA-256", "RSA", "SHA256withRSA", true),
    RS384("RS384", "RSASSA-PKCS-v1_5 using SHA-384", "RSA", "SHA384withRSA", true),
    RS512("RS512", "RSASSA-PKCS-v1_5 using SHA-512", "RSA", "SHA512withRSA", true),
    ES256("ES256", "ECDSA using P-256 and SHA-256", "Elliptic Curve", "SHA256withECDSA", false),
    ES384("ES384", "ECDSA using P-384 and SHA-384", "Elliptic Curve", "SHA384withECDSA", false),
    ES512("ES512", "ECDSA using P-512 and SHA-512", "Elliptic Curve", "SHA512withECDSA", false),
    PS256("PS256", "RSASSA-PSS using SHA-256 and MGF1 with SHA-256", "RSA", "SHA256withRSAandMGF1", false),
    PS384("PS384", "RSASSA-PSS using SHA-384 and MGF1 with SHA-384", "RSA", "SHA384withRSAandMGF1", false),
    PS512("PS512", "RSASSA-PSS using SHA-512 and MGF1 with SHA-512", "RSA", "SHA512withRSAandMGF1", false);
    
    private final String description;
    private final String familyName;
    private final String jcaName;
    private final boolean jdkStandard;
    private final String value;

    static {
        RuntimeEnvironment.enableBouncyCastleIfPossible();
    }

    SignatureAlgorithm(String str, String str2, String str3, String str4, boolean z) {
        this.value = str;
        this.description = str2;
        this.familyName = str3;
        this.jcaName = str4;
        this.jdkStandard = z;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getJcaName() {
        return this.jcaName;
    }

    public boolean isJdkStandard() {
        return this.jdkStandard;
    }

    public boolean isHmac() {
        return name().startsWith("HS");
    }

    public boolean isRsa() {
        return getDescription().startsWith("RSASSA");
    }

    public boolean isEllipticCurve() {
        return name().startsWith("ES");
    }

    public static SignatureAlgorithm forName(String str) throws SignatureException {
        SignatureAlgorithm[] values;
        for (SignatureAlgorithm signatureAlgorithm : values()) {
            if (signatureAlgorithm.getValue().equalsIgnoreCase(str)) {
                return signatureAlgorithm;
            }
        }
        throw new SignatureException("Unsupported signature algorithm '" + str + "'");
    }
}
