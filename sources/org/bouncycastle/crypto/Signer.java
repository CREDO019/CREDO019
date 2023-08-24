package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface Signer {
    byte[] generateSignature() throws CryptoException, DataLengthException;

    void init(boolean z, CipherParameters cipherParameters);

    void reset();

    void update(byte b);

    void update(byte[] bArr, int r2, int r3);

    boolean verifySignature(byte[] bArr);
}
