package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* loaded from: classes5.dex */
public interface AEADCipher {
    int doFinal(byte[] bArr, int r2) throws IllegalStateException, InvalidCipherTextException;

    String getAlgorithmName();

    byte[] getMac();

    int getOutputSize(int r1);

    int getUpdateOutputSize(int r1);

    void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

    void processAADByte(byte b);

    void processAADBytes(byte[] bArr, int r2, int r3);

    int processByte(byte b, byte[] bArr, int r3) throws DataLengthException;

    int processBytes(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws DataLengthException;

    void reset();
}
