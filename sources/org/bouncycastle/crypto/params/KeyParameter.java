package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes5.dex */
public class KeyParameter implements CipherParameters {
    private byte[] key;

    public KeyParameter(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public KeyParameter(byte[] bArr, int r4, int r5) {
        byte[] bArr2 = new byte[r5];
        this.key = bArr2;
        System.arraycopy(bArr, r4, bArr2, 0, r5);
    }

    public byte[] getKey() {
        return this.key;
    }
}
