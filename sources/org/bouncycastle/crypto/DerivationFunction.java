package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface DerivationFunction {
    int generateBytes(byte[] bArr, int r2, int r3) throws DataLengthException, IllegalArgumentException;

    void init(DerivationParameters derivationParameters);
}
