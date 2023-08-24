package org.bouncycastle.crypto.params;

/* loaded from: classes5.dex */
public class RC2Parameters extends KeyParameter {
    private int bits;

    public RC2Parameters(byte[] bArr) {
        this(bArr, bArr.length > 128 ? 1024 : bArr.length * 8);
    }

    public RC2Parameters(byte[] bArr, int r2) {
        super(bArr);
        this.bits = r2;
    }

    public int getEffectiveKeyBits() {
        return this.bits;
    }
}
