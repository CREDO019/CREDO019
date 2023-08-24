package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

/* loaded from: classes5.dex */
public class MGFParameters implements DerivationParameters {
    byte[] seed;

    public MGFParameters(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public MGFParameters(byte[] bArr, int r4, int r5) {
        byte[] bArr2 = new byte[r5];
        this.seed = bArr2;
        System.arraycopy(bArr, r4, bArr2, 0, r5);
    }

    public byte[] getSeed() {
        return this.seed;
    }
}
