package org.bouncycastle.crypto.agreement.kdf;

import org.bouncycastle.crypto.DerivationParameters;

/* loaded from: classes5.dex */
public class GSKKDFParameters implements DerivationParameters {
    private final byte[] nonce;
    private final int startCounter;

    /* renamed from: z */
    private final byte[] f1697z;

    public GSKKDFParameters(byte[] bArr, int r3) {
        this(bArr, r3, null);
    }

    public GSKKDFParameters(byte[] bArr, int r2, byte[] bArr2) {
        this.f1697z = bArr;
        this.startCounter = r2;
        this.nonce = bArr2;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public int getStartCounter() {
        return this.startCounter;
    }

    public byte[] getZ() {
        return this.f1697z;
    }
}
