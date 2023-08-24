package io.jsonwebtoken;

/* loaded from: classes5.dex */
public interface Jws<B> extends Jwt<JwsHeader, B> {
    String getSignature();
}
