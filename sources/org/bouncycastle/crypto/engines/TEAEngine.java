package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class TEAEngine implements BlockCipher {
    private static final int block_size = 8;
    private static final int d_sum = -957401312;
    private static final int delta = -1640531527;
    private static final int rounds = 32;

    /* renamed from: _a */
    private int f1968_a;

    /* renamed from: _b */
    private int f1969_b;

    /* renamed from: _c */
    private int f1970_c;

    /* renamed from: _d */
    private int f1971_d;
    private boolean _forEncryption;
    private boolean _initialised = false;

    private int bytesToInt(byte[] bArr, int r4) {
        int r0 = r4 + 1;
        int r1 = r0 + 1;
        int r42 = (bArr[r4] << Ascii.CAN) | ((bArr[r0] & 255) << 16);
        return (bArr[r1 + 1] & 255) | r42 | ((bArr[r1] & 255) << 8);
    }

    private int decryptBlock(byte[] bArr, int r7, byte[] bArr2, int r9) {
        int bytesToInt = bytesToInt(bArr, r7);
        int bytesToInt2 = bytesToInt(bArr, r7 + 4);
        int r72 = d_sum;
        for (int r1 = 0; r1 != 32; r1++) {
            bytesToInt2 -= (((bytesToInt << 4) + this.f1970_c) ^ (bytesToInt + r72)) ^ ((bytesToInt >>> 5) + this.f1971_d);
            bytesToInt -= (((bytesToInt2 << 4) + this.f1968_a) ^ (bytesToInt2 + r72)) ^ ((bytesToInt2 >>> 5) + this.f1969_b);
            r72 += 1640531527;
        }
        unpackInt(bytesToInt, bArr2, r9);
        unpackInt(bytesToInt2, bArr2, r9 + 4);
        return 8;
    }

    private int encryptBlock(byte[] bArr, int r7, byte[] bArr2, int r9) {
        int bytesToInt = bytesToInt(bArr, r7);
        int bytesToInt2 = bytesToInt(bArr, r7 + 4);
        int r1 = bytesToInt;
        int r0 = 0;
        for (int r72 = 0; r72 != 32; r72++) {
            r0 -= 1640531527;
            r1 += (((bytesToInt2 << 4) + this.f1968_a) ^ (bytesToInt2 + r0)) ^ ((bytesToInt2 >>> 5) + this.f1969_b);
            bytesToInt2 += (((r1 << 4) + this.f1970_c) ^ (r1 + r0)) ^ ((r1 >>> 5) + this.f1971_d);
        }
        unpackInt(r1, bArr2, r9);
        unpackInt(bytesToInt2, bArr2, r9 + 4);
        return 8;
    }

    private void setKey(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        this.f1968_a = bytesToInt(bArr, 0);
        this.f1969_b = bytesToInt(bArr, 4);
        this.f1970_c = bytesToInt(bArr, 8);
        this.f1971_d = bytesToInt(bArr, 12);
    }

    private void unpackInt(int r3, byte[] bArr, int r5) {
        int r0 = r5 + 1;
        bArr[r5] = (byte) (r3 >>> 24);
        int r52 = r0 + 1;
        bArr[r0] = (byte) (r3 >>> 16);
        bArr[r52] = (byte) (r3 >>> 8);
        bArr[r52 + 1] = (byte) r3;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "TEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this._forEncryption = z;
            this._initialised = true;
            setKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to TEA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r4 + 8 <= bArr.length) {
            if (r6 + 8 <= bArr2.length) {
                return this._forEncryption ? encryptBlock(bArr, r4, bArr2, r6) : decryptBlock(bArr, r4, bArr2, r6);
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
