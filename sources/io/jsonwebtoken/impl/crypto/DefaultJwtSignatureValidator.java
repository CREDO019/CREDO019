package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.lang.Assert;
import java.nio.charset.Charset;
import java.security.Key;

/* loaded from: classes5.dex */
public class DefaultJwtSignatureValidator implements JwtSignatureValidator {
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
    private final SignatureValidator signatureValidator;

    public DefaultJwtSignatureValidator(SignatureAlgorithm signatureAlgorithm, Key key) {
        this(DefaultSignatureValidatorFactory.INSTANCE, signatureAlgorithm, key);
    }

    public DefaultJwtSignatureValidator(SignatureValidatorFactory signatureValidatorFactory, SignatureAlgorithm signatureAlgorithm, Key key) {
        Assert.notNull(signatureValidatorFactory, "SignerFactory argument cannot be null.");
        this.signatureValidator = signatureValidatorFactory.createSignatureValidator(signatureAlgorithm, key);
    }

    @Override // io.jsonwebtoken.impl.crypto.JwtSignatureValidator
    public boolean isValid(String str, String str2) {
        return this.signatureValidator.isValid(str.getBytes(US_ASCII), TextCodec.BASE64URL.decode(str2));
    }
}
