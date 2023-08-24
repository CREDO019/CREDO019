package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface Mac {
    int doFinal(byte[] bArr, int r2) throws DataLengthException, IllegalStateException;

    String getAlgorithmName();

    int getMacSize();

    void init(CipherParameters cipherParameters) throws IllegalArgumentException;

    void reset();

    void update(byte b) throws IllegalStateException;

    void update(byte[] bArr, int r2, int r3) throws DataLengthException, IllegalStateException;
}
