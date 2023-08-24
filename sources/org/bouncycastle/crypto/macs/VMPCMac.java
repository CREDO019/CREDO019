package org.bouncycastle.crypto.macs;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class VMPCMac implements Mac {

    /* renamed from: T */
    private byte[] f2036T;

    /* renamed from: g */
    private byte f2037g;
    private byte[] workingIV;
    private byte[] workingKey;

    /* renamed from: x1 */
    private byte f2040x1;

    /* renamed from: x2 */
    private byte f2041x2;

    /* renamed from: x3 */
    private byte f2042x3;

    /* renamed from: x4 */
    private byte f2043x4;

    /* renamed from: n */
    private byte f2038n = 0;

    /* renamed from: P */
    private byte[] f2035P = null;

    /* renamed from: s */
    private byte f2039s = 0;

    private void initKey(byte[] bArr, byte[] bArr2) {
        this.f2039s = (byte) 0;
        this.f2035P = new byte[256];
        for (int r2 = 0; r2 < 256; r2++) {
            this.f2035P[r2] = (byte) r2;
        }
        for (int r1 = 0; r1 < 768; r1++) {
            byte[] bArr3 = this.f2035P;
            int r4 = r1 & 255;
            byte b = bArr3[(this.f2039s + bArr3[r4] + bArr[r1 % bArr.length]) & 255];
            this.f2039s = b;
            byte b2 = bArr3[r4];
            bArr3[r4] = bArr3[b & 255];
            bArr3[b & 255] = b2;
        }
        for (int r8 = 0; r8 < 768; r8++) {
            byte[] bArr4 = this.f2035P;
            int r42 = r8 & 255;
            byte b3 = bArr4[(this.f2039s + bArr4[r42] + bArr2[r8 % bArr2.length]) & 255];
            this.f2039s = b3;
            byte b4 = bArr4[r42];
            bArr4[r42] = bArr4[b3 & 255];
            bArr4[b3 & 255] = b4;
        }
        this.f2038n = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r15) throws DataLengthException, IllegalStateException {
        for (int r1 = 1; r1 < 25; r1++) {
            byte[] bArr2 = this.f2035P;
            byte b = this.f2039s;
            byte b2 = this.f2038n;
            byte b3 = bArr2[(b + bArr2[b2 & 255]) & 255];
            this.f2039s = b3;
            byte b4 = this.f2043x4;
            byte b5 = this.f2042x3;
            byte b6 = bArr2[(b4 + b5 + r1) & 255];
            this.f2043x4 = b6;
            byte b7 = this.f2041x2;
            byte b8 = bArr2[(b5 + b7 + r1) & 255];
            this.f2042x3 = b8;
            byte b9 = this.f2040x1;
            byte b10 = bArr2[(b7 + b9 + r1) & 255];
            this.f2041x2 = b10;
            byte b11 = bArr2[(b9 + b3 + r1) & 255];
            this.f2040x1 = b11;
            byte[] bArr3 = this.f2036T;
            byte b12 = this.f2037g;
            bArr3[b12 & Ascii.f1131US] = (byte) (b11 ^ bArr3[b12 & Ascii.f1131US]);
            bArr3[(b12 + 1) & 31] = (byte) (b10 ^ bArr3[(b12 + 1) & 31]);
            bArr3[(b12 + 2) & 31] = (byte) (b8 ^ bArr3[(b12 + 2) & 31]);
            bArr3[(b12 + 3) & 31] = (byte) (b6 ^ bArr3[(b12 + 3) & 31]);
            this.f2037g = (byte) ((b12 + 4) & 31);
            byte b13 = bArr2[b2 & 255];
            bArr2[b2 & 255] = bArr2[b3 & 255];
            bArr2[b3 & 255] = b13;
            this.f2038n = (byte) ((b2 + 1) & 255);
        }
        for (int r2 = 0; r2 < 768; r2++) {
            byte[] bArr4 = this.f2035P;
            int r5 = r2 & 255;
            byte b14 = bArr4[(this.f2039s + bArr4[r5] + this.f2036T[r2 & 31]) & 255];
            this.f2039s = b14;
            byte b15 = bArr4[r5];
            bArr4[r5] = bArr4[b14 & 255];
            bArr4[b14 & 255] = b15;
        }
        byte[] bArr5 = new byte[20];
        for (int r4 = 0; r4 < 20; r4++) {
            byte[] bArr6 = this.f2035P;
            int r7 = r4 & 255;
            byte b16 = bArr6[(this.f2039s + bArr6[r7]) & 255];
            this.f2039s = b16;
            bArr5[r4] = bArr6[(bArr6[bArr6[b16 & 255] & 255] + 1) & 255];
            byte b17 = bArr6[r7];
            bArr6[r7] = bArr6[b16 & 255];
            bArr6[b16 & 255] = b17;
        }
        System.arraycopy(bArr5, 0, bArr, r15, 20);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        byte[] bArr = parametersWithIV.getIV();
        this.workingIV = bArr;
        if (bArr == null || bArr.length < 1 || bArr.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.workingKey = keyParameter.getKey();
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        initKey(this.workingKey, this.workingIV);
        this.f2038n = (byte) 0;
        this.f2043x4 = (byte) 0;
        this.f2042x3 = (byte) 0;
        this.f2041x2 = (byte) 0;
        this.f2040x1 = (byte) 0;
        this.f2037g = (byte) 0;
        this.f2036T = new byte[32];
        for (int r2 = 0; r2 < 32; r2++) {
            this.f2036T[r2] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.f2035P;
        byte b2 = this.f2039s;
        byte b3 = this.f2038n;
        byte b4 = bArr[(b2 + bArr[b3 & 255]) & 255];
        this.f2039s = b4;
        byte b5 = this.f2043x4;
        byte b6 = this.f2042x3;
        byte b7 = bArr[(b5 + b6) & 255];
        this.f2043x4 = b7;
        byte b8 = this.f2041x2;
        byte b9 = bArr[(b6 + b8) & 255];
        this.f2042x3 = b9;
        byte b10 = this.f2040x1;
        byte b11 = bArr[(b8 + b10) & 255];
        this.f2041x2 = b11;
        byte b12 = bArr[(b10 + b4 + ((byte) (b ^ bArr[(bArr[bArr[b4 & 255] & 255] + 1) & 255]))) & 255];
        this.f2040x1 = b12;
        byte[] bArr2 = this.f2036T;
        byte b13 = this.f2037g;
        bArr2[b13 & Ascii.f1131US] = (byte) (b12 ^ bArr2[b13 & Ascii.f1131US]);
        bArr2[(b13 + 1) & 31] = (byte) (b11 ^ bArr2[(b13 + 1) & 31]);
        bArr2[(b13 + 2) & 31] = (byte) (b9 ^ bArr2[(b13 + 2) & 31]);
        bArr2[(b13 + 3) & 31] = (byte) (b7 ^ bArr2[(b13 + 3) & 31]);
        this.f2037g = (byte) ((b13 + 4) & 31);
        byte b14 = bArr[b3 & 255];
        bArr[b3 & 255] = bArr[b4 & 255];
        bArr[b4 & 255] = b14;
        this.f2038n = (byte) ((b3 + 1) & 255);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r4, int r5) throws DataLengthException, IllegalStateException {
        if (r4 + r5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int r0 = 0; r0 < r5; r0++) {
            update(bArr[r4 + r0]);
        }
    }
}
