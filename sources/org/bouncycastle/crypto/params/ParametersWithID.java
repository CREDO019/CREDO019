package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class ParametersWithID implements CipherParameters {

    /* renamed from: id */
    private byte[] f2148id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f2148id = bArr;
    }

    public byte[] getID() {
        return this.f2148id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
