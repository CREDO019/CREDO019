package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class ParametersWithIV implements CipherParameters {

    /* renamed from: iv */
    private byte[] f2149iv;
    private CipherParameters parameters;

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr, int r4, int r5) {
        byte[] bArr2 = new byte[r5];
        this.f2149iv = bArr2;
        this.parameters = cipherParameters;
        System.arraycopy(bArr, r4, bArr2, 0, r5);
    }

    public byte[] getIV() {
        return this.f2149iv;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
