package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface Wrapper {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters);

    byte[] unwrap(byte[] bArr, int r2, int r3) throws InvalidCipherTextException;

    byte[] wrap(byte[] bArr, int r2, int r3);
}
