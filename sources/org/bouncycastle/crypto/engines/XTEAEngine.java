package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class XTEAEngine implements BlockCipher {
    private static final int block_size = 8;
    private static final int delta = -1640531527;
    private static final int rounds = 32;
    private boolean _forEncryption;

    /* renamed from: _S */
    private int[] f1980_S = new int[4];
    private int[] _sum0 = new int[32];
    private int[] _sum1 = new int[32];
    private boolean _initialised = false;

    private int bytesToInt(byte[] bArr, int r4) {
        int r0 = r4 + 1;
        int r1 = r0 + 1;
        int r42 = (bArr[r4] << Ascii.CAN) | ((bArr[r0] & 255) << 16);
        return (bArr[r1 + 1] & 255) | r42 | ((bArr[r1] & 255) << 8);
    }

    private int decryptBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        int bytesToInt = bytesToInt(bArr, r5);
        int bytesToInt2 = bytesToInt(bArr, r5 + 4);
        for (int r52 = 31; r52 >= 0; r52--) {
            bytesToInt2 -= (((bytesToInt << 4) ^ (bytesToInt >>> 5)) + bytesToInt) ^ this._sum1[r52];
            bytesToInt -= (((bytesToInt2 << 4) ^ (bytesToInt2 >>> 5)) + bytesToInt2) ^ this._sum0[r52];
        }
        unpackInt(bytesToInt, bArr2, r7);
        unpackInt(bytesToInt2, bArr2, r7 + 4);
        return 8;
    }

    private int encryptBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        int bytesToInt = bytesToInt(bArr, r5);
        int bytesToInt2 = bytesToInt(bArr, r5 + 4);
        for (int r52 = 0; r52 < 32; r52++) {
            bytesToInt += (((bytesToInt2 << 4) ^ (bytesToInt2 >>> 5)) + bytesToInt2) ^ this._sum0[r52];
            bytesToInt2 += (((bytesToInt << 4) ^ (bytesToInt >>> 5)) + bytesToInt) ^ this._sum1[r52];
        }
        unpackInt(bytesToInt, bArr2, r7);
        unpackInt(bytesToInt2, bArr2, r7 + 4);
        return 8;
    }

    private void setKey(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        int r1 = 0;
        int r2 = 0;
        while (r1 < 4) {
            this.f1980_S[r1] = bytesToInt(bArr, r2);
            r1++;
            r2 += 4;
        }
        int r7 = 0;
        for (int r0 = 0; r0 < 32; r0++) {
            int[] r12 = this._sum0;
            int[] r22 = this.f1980_S;
            r12[r0] = r22[r7 & 3] + r7;
            r7 -= 1640531527;
            this._sum1[r0] = r22[(r7 >>> 11) & 3] + r7;
        }
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
        return "XTEA";
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
