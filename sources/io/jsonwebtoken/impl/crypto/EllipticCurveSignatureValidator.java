package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.lang.Assert;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;

/* loaded from: classes5.dex */
public class EllipticCurveSignatureValidator extends EllipticCurveProvider implements SignatureValidator {
    private static final String EC_PUBLIC_KEY_REQD_MSG = "Elliptic Curve signature validation requires an ECPublicKey instance.";

    public EllipticCurveSignatureValidator(SignatureAlgorithm signatureAlgorithm, Key key) {
        super(signatureAlgorithm, key);
        Assert.isTrue(key instanceof ECPublicKey, EC_PUBLIC_KEY_REQD_MSG);
    }

    @Override // io.jsonwebtoken.impl.crypto.SignatureValidator
    public boolean isValid(byte[] bArr, byte[] bArr2) {
        Signature createSignatureInstance = createSignatureInstance();
        PublicKey publicKey = (PublicKey) this.key;
        try {
            if (getSignatureByteArrayLength(this.alg) == bArr2.length || bArr2[0] != 48) {
                bArr2 = EllipticCurveProvider.transcodeSignatureToDER(bArr2);
            }
            return doVerify(createSignatureInstance, publicKey, bArr, bArr2);
        } catch (Exception e) {
            throw new SignatureException("Unable to verify Elliptic Curve signature using configured ECPublicKey. " + e.getMessage(), e);
        }
    }

    protected boolean doVerify(Signature signature, PublicKey publicKey, byte[] bArr, byte[] bArr2) throws InvalidKeyException, java.security.SignatureException {
        signature.initVerify(publicKey);
        signature.update(bArr);
        return signature.verify(bArr2);
    }
}
