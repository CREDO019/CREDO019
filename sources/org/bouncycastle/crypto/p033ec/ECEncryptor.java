package org.bouncycastle.crypto.p033ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.math.p039ec.ECPoint;

/* renamed from: org.bouncycastle.crypto.ec.ECEncryptor */
/* loaded from: classes5.dex */
public interface ECEncryptor {
    ECPair encrypt(ECPoint eCPoint);

    void init(CipherParameters cipherParameters);
}
