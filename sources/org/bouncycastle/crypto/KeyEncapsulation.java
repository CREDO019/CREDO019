package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface KeyEncapsulation {
    CipherParameters decrypt(byte[] bArr, int r2, int r3, int r4);

    CipherParameters encrypt(byte[] bArr, int r2, int r3);

    void init(CipherParameters cipherParameters);
}
