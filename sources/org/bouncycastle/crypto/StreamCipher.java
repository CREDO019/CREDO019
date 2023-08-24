package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface StreamCipher {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

    int processBytes(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws DataLengthException;

    void reset();

    byte returnByte(byte b);
}
