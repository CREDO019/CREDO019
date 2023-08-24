package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface AsymmetricBlockCipher {
    int getInputBlockSize();

    int getOutputBlockSize();

    void init(boolean z, CipherParameters cipherParameters);

    byte[] processBlock(byte[] bArr, int r2, int r3) throws InvalidCipherTextException;
}
