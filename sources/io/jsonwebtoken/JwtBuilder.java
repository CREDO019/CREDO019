package io.jsonwebtoken;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/* loaded from: classes5.dex */
public interface JwtBuilder extends ClaimsMutator<JwtBuilder> {
    JwtBuilder addClaims(Map<String, Object> map);

    JwtBuilder claim(String str, Object obj);

    String compact();

    JwtBuilder compressWith(CompressionCodec compressionCodec);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setAudience(String str);

    JwtBuilder setClaims(Claims claims);

    JwtBuilder setClaims(Map<String, Object> map);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setExpiration(Date date);

    JwtBuilder setHeader(Header header);

    JwtBuilder setHeader(Map<String, Object> map);

    JwtBuilder setHeaderParam(String str, Object obj);

    JwtBuilder setHeaderParams(Map<String, Object> map);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setId(String str);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setIssuedAt(Date date);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setIssuer(String str);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setNotBefore(Date date);

    JwtBuilder setPayload(String str);

    @Override // io.jsonwebtoken.ClaimsMutator
    JwtBuilder setSubject(String str);

    JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, String str);

    JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, Key key);

    JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, byte[] bArr);
}
