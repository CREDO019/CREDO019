package io.jsonwebtoken;

/* loaded from: classes5.dex */
public class ExpiredJwtException extends ClaimJwtException {
    public ExpiredJwtException(Header header, Claims claims, String str) {
        super(header, claims, str);
    }

    public ExpiredJwtException(Header header, Claims claims, String str, Throwable th) {
        super(header, claims, str, th);
    }
}
