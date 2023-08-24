package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.ECKey;

/* loaded from: classes5.dex */
public class EllipticCurveSigner extends EllipticCurveProvider implements Signer {
    public EllipticCurveSigner(SignatureAlgorithm signatureAlgorithm, Key key) {
        super(signatureAlgorithm, key);
        if ((key instanceof PrivateKey) && (key instanceof ECKey)) {
            return;
        }
        throw new IllegalArgumentException("Elliptic Curve signatures must be computed using an EC PrivateKey.  The specified key of type " + key.getClass().getName() + " is not an EC PrivateKey.");
    }

    @Override // io.jsonwebtoken.impl.crypto.Signer
    public byte[] sign(byte[] bArr) {
        try {
            return doSign(bArr);
        } catch (JwtException e) {
            throw new SignatureException("Unable to convert signature to JOSE format. " + e.getMessage(), e);
        } catch (InvalidKeyException e2) {
            throw new SignatureException("Invalid Elliptic Curve PrivateKey. " + e2.getMessage(), e2);
        } catch (java.security.SignatureException e3) {
            throw new SignatureException("Unable to calculate signature using Elliptic Curve PrivateKey. " + e3.getMessage(), e3);
        }
    }

    protected byte[] doSign(byte[] bArr) throws InvalidKeyException, java.security.SignatureException, JwtException {
        Signature createSignatureInstance = createSignatureInstance();
        createSignatureInstance.initSign((PrivateKey) this.key);
        createSignatureInstance.update(bArr);
        return transcodeSignatureToConcat(createSignatureInstance.sign(), getSignatureByteArrayLength(this.alg));
    }
}
