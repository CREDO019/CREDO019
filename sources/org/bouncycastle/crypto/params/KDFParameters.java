package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

/* loaded from: classes5.dex */
public class KDFParameters implements DerivationParameters {

    /* renamed from: iv */
    byte[] f2145iv;
    byte[] shared;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.shared = bArr;
        this.f2145iv = bArr2;
    }

    public byte[] getIV() {
        return this.f2145iv;
    }

    public byte[] getSharedSecret() {
        return this.shared;
    }
}
