package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RC5Parameters;

/* loaded from: classes5.dex */
public class RC564Engine implements BlockCipher {
    private static final long P64 = -5196783011329398165L;
    private static final long Q64 = -7046029254386353131L;
    private static final int bytesPerWord = 8;
    private static final int wordSize = 64;
    private boolean forEncryption;
    private int _noRounds = 12;

    /* renamed from: _S */
    private long[] f1947_S = null;

    private long bytesToWord(byte[] bArr, int r7) {
        long j = 0;
        for (int r2 = 7; r2 >= 0; r2--) {
            j = (j << 8) + (bArr[r2 + r7] & 255);
        }
        return j;
    }

    private int decryptBlock(byte[] bArr, int r9, byte[] bArr2, int r11) {
        long bytesToWord = bytesToWord(bArr, r9);
        long bytesToWord2 = bytesToWord(bArr, r9 + 8);
        for (int r2 = this._noRounds; r2 >= 1; r2--) {
            int r4 = r2 * 2;
            bytesToWord2 = rotateRight(bytesToWord2 - this.f1947_S[r4 + 1], bytesToWord) ^ bytesToWord;
            bytesToWord = rotateRight(bytesToWord - this.f1947_S[r4], bytesToWord2) ^ bytesToWord2;
        }
        wordToBytes(bytesToWord - this.f1947_S[0], bArr2, r11);
        wordToBytes(bytesToWord2 - this.f1947_S[1], bArr2, r11 + 8);
        return 16;
    }

    private int encryptBlock(byte[] bArr, int r10, byte[] bArr2, int r12) {
        long bytesToWord = bytesToWord(bArr, r10) + this.f1947_S[0];
        long bytesToWord2 = bytesToWord(bArr, r10 + 8) + this.f1947_S[1];
        for (int r2 = 1; r2 <= this._noRounds; r2++) {
            int r5 = r2 * 2;
            bytesToWord = rotateLeft(bytesToWord ^ bytesToWord2, bytesToWord2) + this.f1947_S[r5];
            bytesToWord2 = rotateLeft(bytesToWord2 ^ bytesToWord, bytesToWord) + this.f1947_S[r5 + 1];
        }
        wordToBytes(bytesToWord, bArr2, r12);
        wordToBytes(bytesToWord2, bArr2, r12 + 8);
        return 16;
    }

    private long rotateLeft(long j, long j2) {
        long j3 = j2 & 63;
        return (j >>> ((int) (64 - j3))) | (j << ((int) j3));
    }

    private long rotateRight(long j, long j2) {
        long j3 = j2 & 63;
        return (j << ((int) (64 - j3))) | (j >>> ((int) j3));
    }

    private void setKey(byte[] bArr) {
        long[] jArr;
        int length = (bArr.length + 7) / 8;
        long[] jArr2 = new long[length];
        for (int r3 = 0; r3 != bArr.length; r3++) {
            int r4 = r3 / 8;
            jArr2[r4] = jArr2[r4] + ((bArr[r3] & 255) << ((r3 % 8) * 8));
        }
        long[] jArr3 = new long[(this._noRounds + 1) * 2];
        this.f1947_S = jArr3;
        jArr3[0] = -5196783011329398165L;
        int r14 = 1;
        while (true) {
            jArr = this.f1947_S;
            if (r14 >= jArr.length) {
                break;
            }
            jArr[r14] = jArr[r14 - 1] + Q64;
            r14++;
        }
        int length2 = length > jArr.length ? length * 3 : jArr.length * 3;
        long j = 0;
        long j2 = 0;
        int r42 = 0;
        int r5 = 0;
        for (int r2 = 0; r2 < length2; r2++) {
            long[] jArr4 = this.f1947_S;
            j = rotateLeft(jArr4[r42] + j + j2, 3L);
            jArr4[r42] = j;
            j2 = rotateLeft(jArr2[r5] + j + j2, j2 + j);
            jArr2[r5] = j2;
            r42 = (r42 + 1) % this.f1947_S.length;
            r5 = (r5 + 1) % length;
        }
    }

    private void wordToBytes(long j, byte[] bArr, int r8) {
        for (int r0 = 0; r0 < 8; r0++) {
            bArr[r0 + r8] = (byte) j;
            j >>>= 8;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC5-64";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof RC5Parameters)) {
            throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + cipherParameters.getClass().getName());
        }
        RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
        this.forEncryption = z;
        this._noRounds = rC5Parameters.getRounds();
        setKey(rC5Parameters.getKey());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r3, byte[] bArr2, int r5) {
        return this.forEncryption ? encryptBlock(bArr, r3, bArr2, r5) : decryptBlock(bArr, r3, bArr2, r5);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
