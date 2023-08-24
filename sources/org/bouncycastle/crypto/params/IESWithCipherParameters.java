package org.bouncycastle.crypto.params;

/* loaded from: classes5.dex */
public class IESWithCipherParameters extends IESParameters {
    private int cipherKeySize;

    public IESWithCipherParameters(byte[] bArr, byte[] bArr2, int r3, int r4) {
        super(bArr, bArr2, r3);
        this.cipherKeySize = r4;
    }

    public int getCipherKeySize() {
        return this.cipherKeySize;
    }
}
