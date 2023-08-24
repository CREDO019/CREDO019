package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface BlockCipher {
    String getAlgorithmName();

    int getBlockSize();

    void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

    int processBlock(byte[] bArr, int r2, byte[] bArr2, int r4) throws DataLengthException, IllegalStateException;

    void reset();
}
