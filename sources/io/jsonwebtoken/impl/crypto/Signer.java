package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureException;

/* loaded from: classes5.dex */
public interface Signer {
    byte[] sign(byte[] bArr) throws SignatureException;
}
