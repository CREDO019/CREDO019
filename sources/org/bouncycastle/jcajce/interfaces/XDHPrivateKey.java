package org.bouncycastle.jcajce.interfaces;

import java.security.PrivateKey;

/* loaded from: classes5.dex */
public interface XDHPrivateKey extends XDHKey, PrivateKey {
    XDHPublicKey getPublicKey();
}
