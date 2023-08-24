package org.bouncycastle.jce.interfaces;

import java.security.PublicKey;
import org.bouncycastle.math.p039ec.ECPoint;

/* loaded from: classes5.dex */
public interface ECPublicKey extends ECKey, PublicKey {
    ECPoint getQ();
}
