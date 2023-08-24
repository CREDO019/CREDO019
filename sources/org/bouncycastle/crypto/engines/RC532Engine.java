package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RC5Parameters;

/* loaded from: classes5.dex */
public class RC532Engine implements BlockCipher {
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;
    private boolean forEncryption;
    private int _noRounds = 12;

    /* renamed from: _S */
    private int[] f1946_S = null;

    private int bytesToWord(byte[] bArr, int r4) {
        return ((bArr[r4 + 3] & 255) << 24) | (bArr[r4] & 255) | ((bArr[r4 + 1] & 255) << 8) | ((bArr[r4 + 2] & 255) << 16);
    }

    private int decryptBlock(byte[] bArr, int r6, byte[] bArr2, int r8) {
        int bytesToWord = bytesToWord(bArr, r6);
        int bytesToWord2 = bytesToWord(bArr, r6 + 4);
        for (int r62 = this._noRounds; r62 >= 1; r62--) {
            int r2 = r62 * 2;
            bytesToWord2 = rotateRight(bytesToWord2 - this.f1946_S[r2 + 1], bytesToWord) ^ bytesToWord;
            bytesToWord = rotateRight(bytesToWord - this.f1946_S[r2], bytesToWord2) ^ bytesToWord2;
        }
        wordToBytes(bytesToWord - this.f1946_S[0], bArr2, r8);
        wordToBytes(bytesToWord2 - this.f1946_S[1], bArr2, r8 + 4);
        return 8;
    }

    private int encryptBlock(byte[] bArr, int r6, byte[] bArr2, int r8) {
        int bytesToWord = bytesToWord(bArr, r6) + this.f1946_S[0];
        int bytesToWord2 = bytesToWord(bArr, r6 + 4) + this.f1946_S[1];
        for (int r62 = 1; r62 <= this._noRounds; r62++) {
            int r3 = r62 * 2;
            bytesToWord = rotateLeft(bytesToWord ^ bytesToWord2, bytesToWord2) + this.f1946_S[r3];
            bytesToWord2 = rotateLeft(bytesToWord2 ^ bytesToWord, bytesToWord) + this.f1946_S[r3 + 1];
        }
        wordToBytes(bytesToWord, bArr2, r8);
        wordToBytes(bytesToWord2, bArr2, r8 + 4);
        return 8;
    }

    private int rotateLeft(int r2, int r3) {
        int r32 = r3 & 31;
        return (r2 >>> (32 - r32)) | (r2 << r32);
    }

    private int rotateRight(int r2, int r3) {
        int r32 = r3 & 31;
        return (r2 << (32 - r32)) | (r2 >>> r32);
    }

    private void setKey(byte[] bArr) {
        int[] r5;
        int length = (bArr.length + 3) / 4;
        int[] r2 = new int[length];
        for (int r4 = 0; r4 != bArr.length; r4++) {
            int r52 = r4 / 4;
            r2[r52] = r2[r52] + ((bArr[r4] & 255) << ((r4 % 4) * 8));
        }
        int[] r12 = new int[(this._noRounds + 1) * 2];
        this.f1946_S = r12;
        r12[0] = P32;
        int r122 = 1;
        while (true) {
            r5 = this.f1946_S;
            if (r122 >= r5.length) {
                break;
            }
            r5[r122] = r5[r122 - 1] + Q32;
            r122++;
        }
        int length2 = length > r5.length ? length * 3 : r5.length * 3;
        int r53 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        for (int r3 = 0; r3 < length2; r3++) {
            int[] r9 = this.f1946_S;
            r6 = rotateLeft(r9[r53] + r6 + r7, 3);
            r9[r53] = r6;
            r7 = rotateLeft(r2[r8] + r6 + r7, r7 + r6);
            r2[r8] = r7;
            r53 = (r53 + 1) % this.f1946_S.length;
            r8 = (r8 + 1) % length;
        }
    }

    private void wordToBytes(int r3, byte[] bArr, int r5) {
        bArr[r5] = (byte) r3;
        bArr[r5 + 1] = (byte) (r3 >> 8);
        bArr[r5 + 2] = (byte) (r3 >> 16);
        bArr[r5 + 3] = (byte) (r3 >> 24);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC5-32";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
            this._noRounds = rC5Parameters.getRounds();
            setKey(rC5Parameters.getKey());
        } else if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to RC532 init - " + cipherParameters.getClass().getName());
        } else {
            setKey(((KeyParameter) cipherParameters).getKey());
        }
        this.forEncryption = z;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r3, byte[] bArr2, int r5) {
        return this.forEncryption ? encryptBlock(bArr, r3, bArr2, r5) : decryptBlock(bArr, r3, bArr2, r5);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
