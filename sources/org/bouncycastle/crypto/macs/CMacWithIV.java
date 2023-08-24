package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class CMacWithIV extends CMac {
    public CMacWithIV(BlockCipher blockCipher) {
        super(blockCipher);
    }

    public CMacWithIV(BlockCipher blockCipher, int r2) {
        super(blockCipher, r2);
    }

    @Override // org.bouncycastle.crypto.macs.CMac
    void validate(CipherParameters cipherParameters) {
    }
}
