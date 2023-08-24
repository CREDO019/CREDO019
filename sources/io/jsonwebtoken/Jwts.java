package io.jsonwebtoken;

import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import io.jsonwebtoken.impl.DefaultJwsHeader;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.impl.DefaultJwtParser;
import java.util.Map;

/* loaded from: classes5.dex */
public final class Jwts {
    private Jwts() {
    }

    public static Header header() {
        return new DefaultHeader();
    }

    public static Header header(Map<String, Object> map) {
        return new DefaultHeader(map);
    }

    public static JwsHeader jwsHeader() {
        return new DefaultJwsHeader();
    }

    public static JwsHeader jwsHeader(Map<String, Object> map) {
        return new DefaultJwsHeader(map);
    }

    public static Claims claims() {
        return new DefaultClaims();
    }

    public static Claims claims(Map<String, Object> map) {
        return new DefaultClaims(map);
    }

    public static JwtParser parser() {
        return new DefaultJwtParser();
    }

    public static JwtBuilder builder() {
        return new DefaultJwtBuilder();
    }
}
