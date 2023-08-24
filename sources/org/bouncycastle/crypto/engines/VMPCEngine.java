package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class VMPCEngine implements StreamCipher {
    protected byte[] workingIV;
    protected byte[] workingKey;

    /* renamed from: n */
    protected byte f1978n = 0;

    /* renamed from: P */
    protected byte[] f1977P = null;

    /* renamed from: s */
    protected byte f1979s = 0;

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        byte[] bArr = parametersWithIV.getIV();
        this.workingIV = bArr;
        if (bArr == null || bArr.length < 1 || bArr.length > 768) {
            throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
        }
        byte[] key = keyParameter.getKey();
        this.workingKey = key;
        initKey(key, this.workingIV);
    }

    protected void initKey(byte[] bArr, byte[] bArr2) {
        this.f1979s = (byte) 0;
        this.f1977P = new byte[256];
        for (int r2 = 0; r2 < 256; r2++) {
            this.f1977P[r2] = (byte) r2;
        }
        for (int r1 = 0; r1 < 768; r1++) {
            byte[] bArr3 = this.f1977P;
            int r4 = r1 & 255;
            byte b = bArr3[(this.f1979s + bArr3[r4] + bArr[r1 % bArr.length]) & 255];
            this.f1979s = b;
            byte b2 = bArr3[r4];
            bArr3[r4] = bArr3[b & 255];
            bArr3[b & 255] = b2;
        }
        for (int r8 = 0; r8 < 768; r8++) {
            byte[] bArr4 = this.f1977P;
            int r42 = r8 & 255;
            byte b3 = bArr4[(this.f1979s + bArr4[r42] + bArr2[r8 % bArr2.length]) & 255];
            this.f1979s = b3;
            byte b4 = bArr4[r42];
            bArr4[r42] = bArr4[b3 & 255];
            bArr4[b3 & 255] = b4;
        }
        this.f1978n = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r10, int r11, byte[] bArr2, int r13) {
        if (r10 + r11 <= bArr.length) {
            if (r13 + r11 <= bArr2.length) {
                for (int r0 = 0; r0 < r11; r0++) {
                    byte[] bArr3 = this.f1977P;
                    byte b = this.f1979s;
                    byte b2 = this.f1978n;
                    byte b3 = bArr3[(b + bArr3[b2 & 255]) & 255];
                    this.f1979s = b3;
                    byte b4 = bArr3[(bArr3[bArr3[b3 & 255] & 255] + 1) & 255];
                    byte b5 = bArr3[b2 & 255];
                    bArr3[b2 & 255] = bArr3[b3 & 255];
                    bArr3[b3 & 255] = b5;
                    this.f1978n = (byte) ((b2 + 1) & 255);
                    bArr2[r0 + r13] = (byte) (bArr[r0 + r10] ^ b4);
                }
                return r11;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        initKey(this.workingKey, this.workingIV);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        byte[] bArr = this.f1977P;
        byte b2 = this.f1979s;
        byte b3 = this.f1978n;
        byte b4 = bArr[(b2 + bArr[b3 & 255]) & 255];
        this.f1979s = b4;
        byte b5 = bArr[(bArr[bArr[b4 & 255] & 255] + 1) & 255];
        byte b6 = bArr[b3 & 255];
        bArr[b3 & 255] = bArr[b4 & 255];
        bArr[b4 & 255] = b6;
        this.f1978n = (byte) ((b3 + 1) & 255);
        return (byte) (b ^ b5);
    }
}
