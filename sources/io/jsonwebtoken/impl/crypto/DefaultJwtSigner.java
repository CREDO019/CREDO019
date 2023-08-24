package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.lang.Assert;
import java.nio.charset.Charset;
import java.security.Key;

/* loaded from: classes5.dex */
public class DefaultJwtSigner implements JwtSigner {
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
    private final Signer signer;

    public DefaultJwtSigner(SignatureAlgorithm signatureAlgorithm, Key key) {
        this(DefaultSignerFactory.INSTANCE, signatureAlgorithm, key);
    }

    public DefaultJwtSigner(SignerFactory signerFactory, SignatureAlgorithm signatureAlgorithm, Key key) {
        Assert.notNull(signerFactory, "SignerFactory argument cannot be null.");
        this.signer = signerFactory.createSigner(signatureAlgorithm, key);
    }

    @Override // io.jsonwebtoken.impl.crypto.JwtSigner
    public String sign(String str) {
        return TextCodec.BASE64URL.encode(this.signer.sign(str.getBytes(US_ASCII)));
    }
}
