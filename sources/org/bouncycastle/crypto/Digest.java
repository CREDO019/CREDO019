package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface Digest {
    int doFinal(byte[] bArr, int r2);

    String getAlgorithmName();

    int getDigestSize();

    void reset();

    void update(byte b);

    void update(byte[] bArr, int r2, int r3);
}
