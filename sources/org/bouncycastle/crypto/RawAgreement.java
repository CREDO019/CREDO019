package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface RawAgreement {
    void calculateAgreement(CipherParameters cipherParameters, byte[] bArr, int r3);

    int getAgreementSize();

    void init(CipherParameters cipherParameters);
}
