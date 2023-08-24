package org.bouncycastle.jcajce.interfaces;

import java.security.PublicKey;

/* loaded from: classes5.dex */
public interface EdDSAPublicKey extends EdDSAKey, PublicKey {
    byte[] getPointEncoding();
}
