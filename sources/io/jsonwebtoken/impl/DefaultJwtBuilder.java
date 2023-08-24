package io.jsonwebtoken.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSigner;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public class DefaultJwtBuilder implements JwtBuilder {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private SignatureAlgorithm algorithm;
    private Claims claims;
    private CompressionCodec compressionCodec;
    private Header header;
    private Key key;
    private byte[] keyBytes;
    private String payload;

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeader(Header header) {
        this.header = header;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeader(Map<String, Object> map) {
        this.header = new DefaultHeader(map);
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeaderParams(Map<String, Object> map) {
        if (!Collections.isEmpty(map)) {
            Header ensureHeader = ensureHeader();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                ensureHeader.put(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    protected Header ensureHeader() {
        if (this.header == null) {
            this.header = new DefaultHeader();
        }
        return this.header;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeaderParam(String str, Object obj) {
        ensureHeader().put(str, obj);
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, byte[] bArr) {
        Assert.notNull(signatureAlgorithm, "SignatureAlgorithm cannot be null.");
        Assert.notEmpty(bArr, "secret key byte array cannot be null or empty.");
        Assert.isTrue(signatureAlgorithm.isHmac(), "Key bytes may only be specified for HMAC signatures.  If using RSA or Elliptic Curve, use the signWith(SignatureAlgorithm, Key) method instead.");
        this.algorithm = signatureAlgorithm;
        this.keyBytes = bArr;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, String str) {
        Assert.hasText(str, "base64-encoded secret key cannot be null or empty.");
        Assert.isTrue(signatureAlgorithm.isHmac(), "Base64-encoded key bytes may only be specified for HMAC signatures.  If using RSA or Elliptic Curve, use the signWith(SignatureAlgorithm, Key) method instead.");
        return signWith(signatureAlgorithm, TextCodec.BASE64.decode(str));
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, Key key) {
        Assert.notNull(signatureAlgorithm, "SignatureAlgorithm cannot be null.");
        Assert.notNull(key, "Key argument cannot be null.");
        this.algorithm = signatureAlgorithm;
        this.key = key;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder compressWith(CompressionCodec compressionCodec) {
        Assert.notNull(compressionCodec, "compressionCodec cannot be null");
        this.compressionCodec = compressionCodec;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setPayload(String str) {
        this.payload = str;
        return this;
    }

    protected Claims ensureClaims() {
        if (this.claims == null) {
            this.claims = new DefaultClaims();
        }
        return this.claims;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setClaims(Claims claims) {
        this.claims = claims;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setClaims(Map<String, Object> map) {
        this.claims = Jwts.claims(map);
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder addClaims(Map<String, Object> map) {
        ensureClaims().putAll(map);
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setIssuer(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setIssuer(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setIssuer(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setSubject(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setSubject(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setSubject(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setAudience(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setAudience(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setAudience(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setExpiration(Date date) {
        if (date != null) {
            ensureClaims().setExpiration(date);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setExpiration(date);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setNotBefore(Date date) {
        if (date != null) {
            ensureClaims().setNotBefore(date);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setNotBefore(date);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setIssuedAt(Date date) {
        if (date != null) {
            ensureClaims().setIssuedAt(date);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setIssuedAt(date);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setId(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setId(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setId(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder claim(String str, Object obj) {
        Assert.hasText(str, "Claim property name cannot be null or empty.");
        Claims claims = this.claims;
        if (claims == null) {
            if (obj != null) {
                ensureClaims().put(str, obj);
            }
        } else if (obj == null) {
            claims.remove(str);
        } else {
            claims.put(str, obj);
        }
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public String compact() {
        JwsHeader defaultJwsHeader;
        String encode;
        if (this.payload == null && Collections.isEmpty(this.claims)) {
            throw new IllegalStateException("Either 'payload' or 'claims' must be specified.");
        }
        if (this.payload != null && !Collections.isEmpty(this.claims)) {
            throw new IllegalStateException("Both 'payload' and 'claims' cannot both be specified. Choose either one.");
        }
        if (this.key != null && this.keyBytes != null) {
            throw new IllegalStateException("A key object and key bytes cannot both be specified. Choose either one.");
        }
        Header ensureHeader = ensureHeader();
        Key key = this.key;
        if (key == null && !Objects.isEmpty(this.keyBytes)) {
            key = new SecretKeySpec(this.keyBytes, this.algorithm.getJcaName());
        }
        if (ensureHeader instanceof JwsHeader) {
            defaultJwsHeader = (JwsHeader) ensureHeader;
        } else {
            defaultJwsHeader = new DefaultJwsHeader(ensureHeader);
        }
        if (key != null) {
            defaultJwsHeader.setAlgorithm(this.algorithm.getValue());
        } else {
            defaultJwsHeader.setAlgorithm(SignatureAlgorithm.NONE.getValue());
        }
        CompressionCodec compressionCodec = this.compressionCodec;
        if (compressionCodec != null) {
            defaultJwsHeader.setCompressionAlgorithm(compressionCodec.getAlgorithmName());
        }
        String base64UrlEncode = base64UrlEncode(defaultJwsHeader, "Unable to serialize header to json.");
        if (this.compressionCodec != null) {
            try {
                String str = this.payload;
                encode = TextCodec.BASE64URL.encode(this.compressionCodec.compress(str != null ? str.getBytes(Strings.UTF_8) : toJson(this.claims)));
            } catch (JsonProcessingException unused) {
                throw new IllegalArgumentException("Unable to serialize claims object to json.");
            }
        } else if (this.payload != null) {
            encode = TextCodec.BASE64URL.encode(this.payload);
        } else {
            encode = base64UrlEncode(this.claims, "Unable to serialize claims object to json.");
        }
        String str2 = base64UrlEncode + '.' + encode;
        if (key != null) {
            return str2 + '.' + createSigner(this.algorithm, key).sign(str2);
        }
        return str2 + '.';
    }

    protected JwtSigner createSigner(SignatureAlgorithm signatureAlgorithm, Key key) {
        return new DefaultJwtSigner(signatureAlgorithm, key);
    }

    protected String base64UrlEncode(Object obj, String str) {
        try {
            return TextCodec.BASE64URL.encode(toJson(obj));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(str, e);
        }
    }

    protected byte[] toJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }
}
