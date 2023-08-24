package org.bouncycastle.crypto.p033ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.math.p039ec.ECPoint;

/* renamed from: org.bouncycastle.crypto.ec.ECDecryptor */
/* loaded from: classes5.dex */
public interface ECDecryptor {
    ECPoint decrypt(ECPair eCPair);

    void init(CipherParameters cipherParameters);
}
