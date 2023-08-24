package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;

/* loaded from: classes5.dex */
public class DefaultJwt<B> implements Jwt<Header, B> {
    private final B body;
    private final Header header;

    public DefaultJwt(Header header, B b) {
        this.header = header;
        this.body = b;
    }

    @Override // io.jsonwebtoken.Jwt
    public Header getHeader() {
        return this.header;
    }

    @Override // io.jsonwebtoken.Jwt
    public B getBody() {
        return this.body;
    }

    public String toString() {
        return "header=" + this.header + ",body=" + this.body;
    }
}
