package io.jsonwebtoken.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecResolver;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtHandler;
import io.jsonwebtoken.JwtHandlerAdapter;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.compression.DefaultCompressionCodecResolver;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import java.io.IOException;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public class DefaultJwtParser implements JwtParser {
    private static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private Key key;
    private byte[] keyBytes;
    private SigningKeyResolver signingKeyResolver;
    private ObjectMapper objectMapper = new ObjectMapper();
    private CompressionCodecResolver compressionCodecResolver = new DefaultCompressionCodecResolver();
    Claims expectedClaims = new DefaultClaims();
    private Clock clock = DefaultClock.INSTANCE;
    private long allowedClockSkewMillis = 0;

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireIssuedAt(Date date) {
        this.expectedClaims.setIssuedAt(date);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireIssuer(String str) {
        this.expectedClaims.setIssuer(str);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireAudience(String str) {
        this.expectedClaims.setAudience(str);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireSubject(String str) {
        this.expectedClaims.setSubject(str);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireId(String str) {
        this.expectedClaims.setId(str);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireExpiration(Date date) {
        this.expectedClaims.setExpiration(date);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser requireNotBefore(Date date) {
        this.expectedClaims.setNotBefore(date);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser require(String str, Object obj) {
        Assert.hasText(str, "claim name cannot be null or empty.");
        Assert.notNull(obj, "The value cannot be null for claim name: " + str);
        this.expectedClaims.put(str, obj);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setClock(Clock clock) {
        Assert.notNull(clock, "Clock instance cannot be null.");
        this.clock = clock;
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setAllowedClockSkewSeconds(long j) {
        this.allowedClockSkewMillis = Math.max(0L, j * 1000);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setSigningKey(byte[] bArr) {
        Assert.notEmpty(bArr, "signing key cannot be null or empty.");
        this.keyBytes = bArr;
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setSigningKey(String str) {
        Assert.hasText(str, "signing key cannot be null or empty.");
        this.keyBytes = TextCodec.BASE64.decode(str);
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setSigningKey(Key key) {
        Assert.notNull(key, "signing key cannot be null.");
        this.key = key;
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setSigningKeyResolver(SigningKeyResolver signingKeyResolver) {
        Assert.notNull(signingKeyResolver, "SigningKeyResolver cannot be null.");
        this.signingKeyResolver = signingKeyResolver;
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public JwtParser setCompressionCodecResolver(CompressionCodecResolver compressionCodecResolver) {
        Assert.notNull(compressionCodecResolver, "compressionCodecResolver cannot be null.");
        this.compressionCodecResolver = compressionCodecResolver;
        return this;
    }

    @Override // io.jsonwebtoken.JwtParser
    public boolean isSigned(String str) {
        if (str == null) {
            return false;
        }
        int r2 = 0;
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if (r2 == 2) {
                return (Character.isWhitespace(charAt) || charAt == '.') ? false : true;
            }
            if (charAt == '.') {
                r2++;
            }
        }
        return false;
    }

    @Override // io.jsonwebtoken.JwtParser
    public Jwt parse(String str) throws ExpiredJwtException, MalformedJwtException, SignatureException {
        char[] charArray;
        CompressionCodec compressionCodec;
        JwsHeader jwsHeader;
        String decodeToString;
        String str2;
        Claims claims;
        long j;
        long j2;
        SigningKeyResolver signingKeyResolver;
        Key resolveSigningKey;
        Assert.hasText(str, "JWT String argument cannot be null or empty.");
        StringBuilder sb = new StringBuilder(128);
        SignatureAlgorithm signatureAlgorithm = null;
        String str3 = null;
        String str4 = null;
        int r8 = 0;
        for (char c : str.toCharArray()) {
            if (c == '.') {
                CharSequence clean = Strings.clean(sb);
                String charSequence = clean != null ? clean.toString() : null;
                if (r8 == 0) {
                    str4 = charSequence;
                } else if (r8 == 1) {
                    str3 = charSequence;
                }
                r8++;
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (r8 != 2) {
            throw new MalformedJwtException("JWT strings must contain exactly 2 period characters. Found: " + r8);
        }
        String sb2 = sb.length() > 0 ? sb.toString() : null;
        if (str3 == null) {
            throw new MalformedJwtException("JWT string '" + str + "' is missing a body/payload.");
        }
        if (str4 != null) {
            Map<String, Object> readValue = readValue(TextCodec.BASE64URL.decodeToString(str4));
            if (sb2 != null) {
                jwsHeader = new DefaultJwsHeader(readValue);
            } else {
                jwsHeader = new DefaultHeader(readValue);
            }
            compressionCodec = this.compressionCodecResolver.resolveCompressionCodec(jwsHeader);
        } else {
            compressionCodec = null;
            jwsHeader = null;
        }
        if (compressionCodec != null) {
            decodeToString = new String(compressionCodec.decompress(TextCodec.BASE64URL.decode(str3)), Strings.UTF_8);
        } else {
            decodeToString = TextCodec.BASE64URL.decodeToString(str3);
        }
        DefaultClaims defaultClaims = (decodeToString.charAt(0) == '{' && decodeToString.charAt(decodeToString.length() - 1) == '}') ? new DefaultClaims(readValue(decodeToString)) : null;
        if (sb2 != null) {
            JwsHeader jwsHeader2 = jwsHeader;
            if (jwsHeader != null) {
                String algorithm = jwsHeader2.getAlgorithm();
                if (Strings.hasText(algorithm)) {
                    signatureAlgorithm = SignatureAlgorithm.forName(algorithm);
                }
            }
            if (signatureAlgorithm == null || signatureAlgorithm == SignatureAlgorithm.NONE) {
                throw new MalformedJwtException("JWT string has a digest/signature, but the header does not reference a valid signature algorithm.");
            }
            Key key = this.key;
            if (key != null && this.keyBytes != null) {
                throw new IllegalStateException("A key object and key bytes cannot both be specified. Choose either.");
            }
            if ((key != null || this.keyBytes != null) && this.signingKeyResolver != null) {
                throw new IllegalStateException("A signing key resolver and " + (key != null ? "a key object" : "key bytes") + " cannot both be specified. Choose either.");
            }
            if (key == null) {
                byte[] bArr = this.keyBytes;
                if (Objects.isEmpty(bArr) && (signingKeyResolver = this.signingKeyResolver) != null) {
                    if (defaultClaims != null) {
                        resolveSigningKey = signingKeyResolver.resolveSigningKey(jwsHeader2, defaultClaims);
                    } else {
                        resolveSigningKey = signingKeyResolver.resolveSigningKey(jwsHeader2, decodeToString);
                    }
                    key = resolveSigningKey;
                }
                if (!Objects.isEmpty(bArr)) {
                    Assert.isTrue(signatureAlgorithm.isHmac(), "Key bytes can only be specified for HMAC signatures. Please specify a PublicKey or PrivateKey instance.");
                    key = new SecretKeySpec(bArr, signatureAlgorithm.getJcaName());
                }
            }
            Assert.notNull(key, "A signing key must be specified if the specified JWT is digitally signed.");
            try {
                if (!createSignatureValidator(signatureAlgorithm, key).isValid(str4 + '.' + str3, sb2)) {
                    throw new SignatureException("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.");
                }
            } catch (IllegalArgumentException e) {
                String value = signatureAlgorithm.getValue();
                throw new UnsupportedJwtException("The parsed JWT indicates it was signed with the " + value + " signature algorithm, but the specified signing key of type " + key.getClass().getName() + " may not be used to validate " + value + " signatures.  Because the specified signing key reflects a specific and expected algorithm, and the JWT does not reflect this algorithm, it is likely that the JWT was not expected and therefore should not be trusted.  Another possibility is that the parser was configured with the incorrect signing key, but this cannot be assumed for security reasons.", e);
            }
        }
        boolean z = this.allowedClockSkewMillis > 0;
        if (defaultClaims != null) {
            Date now = this.clock.now();
            long time = now.getTime();
            Date expiration = defaultClaims.getExpiration();
            str2 = sb2;
            if (expiration != null) {
                JwsHeader jwsHeader3 = jwsHeader;
                claims = decodeToString;
                if ((z ? new Date(time - this.allowedClockSkewMillis) : now).after(expiration)) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ISO_8601_FORMAT);
                    throw new ExpiredJwtException(jwsHeader3, defaultClaims, "JWT expired at " + simpleDateFormat.format(expiration) + ". Current time: " + simpleDateFormat.format(now) + ", a difference of " + (j2 - expiration.getTime()) + " milliseconds.  Allowed clock skew: " + this.allowedClockSkewMillis + " milliseconds.");
                }
                jwsHeader = jwsHeader3;
            } else {
                claims = decodeToString;
            }
            Date notBefore = defaultClaims.getNotBefore();
            if (notBefore != null) {
                JwsHeader jwsHeader4 = jwsHeader;
                if ((z ? new Date(time + this.allowedClockSkewMillis) : now).before(notBefore)) {
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(ISO_8601_FORMAT);
                    throw new PrematureJwtException(jwsHeader4, defaultClaims, "JWT must not be accepted before " + simpleDateFormat2.format(notBefore) + ". Current time: " + simpleDateFormat2.format(now) + ", a difference of " + (notBefore.getTime() - j) + " milliseconds.  Allowed clock skew: " + this.allowedClockSkewMillis + " milliseconds.");
                }
                jwsHeader = jwsHeader4;
            }
            validateExpectedClaims(jwsHeader, defaultClaims);
        } else {
            str2 = sb2;
            claims = decodeToString;
        }
        DefaultClaims defaultClaims2 = defaultClaims != null ? defaultClaims : claims;
        if (str2 != null) {
            return new DefaultJws(jwsHeader, defaultClaims2, str2);
        }
        return new DefaultJwt(jwsHeader, defaultClaims2);
    }

    private void validateExpectedClaims(Header header, Claims claims) {
        for (String str : this.expectedClaims.keySet()) {
            Object obj = this.expectedClaims.get(str);
            Object obj2 = claims.get(str);
            if (Claims.ISSUED_AT.equals(str) || Claims.EXPIRATION.equals(str) || Claims.NOT_BEFORE.equals(str)) {
                obj = this.expectedClaims.get(str, Date.class);
                obj2 = claims.get(str, Date.class);
            } else if ((obj instanceof Date) && obj2 != null && (obj2 instanceof Long)) {
                obj2 = new Date(((Long) obj2).longValue());
            }
            InvalidClaimException invalidClaimException = null;
            if (obj2 == null) {
                invalidClaimException = new MissingClaimException(header, claims, String.format(ClaimJwtException.MISSING_EXPECTED_CLAIM_MESSAGE_TEMPLATE, str, obj));
                continue;
            } else if (obj.equals(obj2)) {
                continue;
            } else {
                invalidClaimException = new IncorrectClaimException(header, claims, String.format(ClaimJwtException.INCORRECT_EXPECTED_CLAIM_MESSAGE_TEMPLATE, str, obj, obj2));
                continue;
            }
            if (invalidClaimException != null) {
                invalidClaimException.setClaimName(str);
                invalidClaimException.setClaimValue(obj);
                throw invalidClaimException;
            }
        }
    }

    protected JwtSignatureValidator createSignatureValidator(SignatureAlgorithm signatureAlgorithm, Key key) {
        return new DefaultJwtSignatureValidator(signatureAlgorithm, key);
    }

    @Override // io.jsonwebtoken.JwtParser
    public <T> T parse(String str, JwtHandler<T> jwtHandler) throws ExpiredJwtException, MalformedJwtException, SignatureException {
        Assert.notNull(jwtHandler, "JwtHandler argument cannot be null.");
        Assert.hasText(str, "JWT String argument cannot be null or empty.");
        Jwt<Header, String> parse = parse(str);
        if (parse instanceof Jws) {
            Jws<String> jws = (Jws) parse;
            if (jws.getBody() instanceof Claims) {
                return jwtHandler.onClaimsJws(jws);
            }
            return jwtHandler.onPlaintextJws(jws);
        } else if (parse.getBody() instanceof Claims) {
            return jwtHandler.onClaimsJwt(parse);
        } else {
            return jwtHandler.onPlaintextJwt(parse);
        }
    }

    @Override // io.jsonwebtoken.JwtParser
    public Jwt<Header, String> parsePlaintextJwt(String str) {
        return (Jwt) parse(str, new JwtHandlerAdapter<Jwt<Header, String>>() { // from class: io.jsonwebtoken.impl.DefaultJwtParser.1
            @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
            public Jwt<Header, String> onPlaintextJwt(Jwt<Header, String> jwt) {
                return jwt;
            }

            @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
            public /* bridge */ /* synthetic */ Object onPlaintextJwt(Jwt jwt) {
                return onPlaintextJwt((Jwt<Header, String>) jwt);
            }
        });
    }

    @Override // io.jsonwebtoken.JwtParser
    public Jwt<Header, Claims> parseClaimsJwt(String str) {
        try {
            return (Jwt) parse(str, new JwtHandlerAdapter<Jwt<Header, Claims>>() { // from class: io.jsonwebtoken.impl.DefaultJwtParser.2
                @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
                public Jwt<Header, Claims> onClaimsJwt(Jwt<Header, Claims> jwt) {
                    return jwt;
                }

                @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
                public /* bridge */ /* synthetic */ Object onClaimsJwt(Jwt jwt) {
                    return onClaimsJwt((Jwt<Header, Claims>) jwt);
                }
            });
        } catch (IllegalArgumentException e) {
            throw new UnsupportedJwtException("Signed JWSs are not supported.", e);
        }
    }

    @Override // io.jsonwebtoken.JwtParser
    public Jws<String> parsePlaintextJws(String str) {
        try {
            return (Jws) parse(str, new JwtHandlerAdapter<Jws<String>>() { // from class: io.jsonwebtoken.impl.DefaultJwtParser.3
                @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
                public Jws<String> onPlaintextJws(Jws<String> jws) {
                    return jws;
                }

                @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
                public /* bridge */ /* synthetic */ Object onPlaintextJws(Jws jws) {
                    return onPlaintextJws((Jws<String>) jws);
                }
            });
        } catch (IllegalArgumentException e) {
            throw new UnsupportedJwtException("Signed JWSs are not supported.", e);
        }
    }

    @Override // io.jsonwebtoken.JwtParser
    public Jws<Claims> parseClaimsJws(String str) {
        return (Jws) parse(str, new JwtHandlerAdapter<Jws<Claims>>() { // from class: io.jsonwebtoken.impl.DefaultJwtParser.4
            @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
            public Jws<Claims> onClaimsJws(Jws<Claims> jws) {
                return jws;
            }

            @Override // io.jsonwebtoken.JwtHandlerAdapter, io.jsonwebtoken.JwtHandler
            public /* bridge */ /* synthetic */ Object onClaimsJws(Jws jws) {
                return onClaimsJws((Jws<Claims>) jws);
            }
        });
    }

    protected Map<String, Object> readValue(String str) {
        try {
            return (Map) this.objectMapper.readValue(str, Map.class);
        } catch (IOException e) {
            throw new MalformedJwtException("Unable to read JSON value: " + str, e);
        }
    }
}
