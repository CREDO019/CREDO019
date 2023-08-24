package io.jsonwebtoken;

import io.jsonwebtoken.Header;

/* loaded from: classes5.dex */
public interface Jwt<H extends Header, B> {
    B getBody();

    H getHeader();
}
