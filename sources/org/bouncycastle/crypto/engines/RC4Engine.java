package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class RC4Engine implements StreamCipher {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;

    /* renamed from: x */
    private int f1944x = 0;

    /* renamed from: y */
    private int f1945y = 0;
    private byte[] workingKey = null;

    private void setKey(byte[] bArr) {
        this.workingKey = bArr;
        this.f1944x = 0;
        this.f1945y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[256];
        }
        for (int r1 = 0; r1 < 256; r1++) {
            this.engineState[r1] = (byte) r1;
        }
        int r12 = 0;
        int r3 = 0;
        for (int r0 = 0; r0 < 256; r0++) {
            byte[] bArr2 = this.engineState;
            r3 = ((bArr[r12] & 255) + bArr2[r0] + r3) & 255;
            byte b = bArr2[r0];
            bArr2[r0] = bArr2[r3];
            bArr2[r3] = b;
            r12 = (r12 + 1) % bArr.length;
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "RC4";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = key;
            setKey(key);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r8, int r9, byte[] bArr2, int r11) {
        if (r8 + r9 <= bArr.length) {
            if (r11 + r9 <= bArr2.length) {
                for (int r0 = 0; r0 < r9; r0++) {
                    int r1 = (this.f1944x + 1) & 255;
                    this.f1944x = r1;
                    byte[] bArr3 = this.engineState;
                    int r3 = (bArr3[r1] + this.f1945y) & 255;
                    this.f1945y = r3;
                    byte b = bArr3[r1];
                    bArr3[r1] = bArr3[r3];
                    bArr3[r3] = b;
                    bArr2[r0 + r11] = (byte) (bArr3[(bArr3[r1] + bArr3[r3]) & 255] ^ bArr[r0 + r8]);
                }
                return r9;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        setKey(this.workingKey);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        int r0 = (this.f1944x + 1) & 255;
        this.f1944x = r0;
        byte[] bArr = this.engineState;
        int r2 = (bArr[r0] + this.f1945y) & 255;
        this.f1945y = r2;
        byte b2 = bArr[r0];
        bArr[r0] = bArr[r2];
        bArr[r2] = b2;
        return (byte) (b ^ bArr[(bArr[r0] + bArr[r2]) & 255]);
    }
}
