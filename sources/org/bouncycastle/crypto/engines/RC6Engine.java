package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class RC6Engine implements BlockCipher {
    private static final int LGW = 5;
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;
    private static final int _noRounds = 20;
    private static final int bytesPerWord = 4;
    private static final int wordSize = 32;

    /* renamed from: _S */
    private int[] f1948_S = null;
    private boolean forEncryption;

    private int bytesToWord(byte[] bArr, int r5) {
        int r0 = 0;
        for (int r1 = 3; r1 >= 0; r1--) {
            r0 = (r0 << 8) + (bArr[r1 + r5] & 255);
        }
        return r0;
    }

    private int decryptBlock(byte[] bArr, int r11, byte[] bArr2, int r13) {
        int bytesToWord = bytesToWord(bArr, r11);
        int bytesToWord2 = bytesToWord(bArr, r11 + 4);
        int bytesToWord3 = bytesToWord(bArr, r11 + 8);
        int bytesToWord4 = bytesToWord(bArr, r11 + 12);
        int[] r112 = this.f1948_S;
        int r2 = bytesToWord3 - r112[43];
        int r0 = bytesToWord - r112[42];
        int r113 = 20;
        while (r113 >= 1) {
            int rotateLeft = rotateLeft(((r0 * 2) + 1) * r0, 5);
            int rotateLeft2 = rotateLeft(((r2 * 2) + 1) * r2, 5);
            int r6 = r113 * 2;
            r113--;
            int r8 = r0;
            r0 = rotateRight(bytesToWord4 - this.f1948_S[r6], rotateLeft2) ^ rotateLeft;
            bytesToWord4 = r2;
            r2 = rotateRight(bytesToWord2 - this.f1948_S[r6 + 1], rotateLeft) ^ rotateLeft2;
            bytesToWord2 = r8;
        }
        int[] r114 = this.f1948_S;
        wordToBytes(r0, bArr2, r13);
        wordToBytes(bytesToWord2 - r114[0], bArr2, r13 + 4);
        wordToBytes(r2, bArr2, r13 + 8);
        wordToBytes(bytesToWord4 - r114[1], bArr2, r13 + 12);
        return 16;
    }

    private int encryptBlock(byte[] bArr, int r11, byte[] bArr2, int r13) {
        int bytesToWord = bytesToWord(bArr, r11);
        int bytesToWord2 = bytesToWord(bArr, r11 + 4);
        int bytesToWord3 = bytesToWord(bArr, r11 + 8);
        int bytesToWord4 = bytesToWord(bArr, r11 + 12);
        int[] r112 = this.f1948_S;
        int r1 = bytesToWord2 + r112[0];
        int r10 = bytesToWord4 + r112[1];
        int r113 = 1;
        while (r113 <= 20) {
            int rotateLeft = rotateLeft(((r1 * 2) + 1) * r1, 5);
            int rotateLeft2 = rotateLeft(((r10 * 2) + 1) * r10, 5);
            int r7 = r113 * 2;
            int rotateLeft3 = rotateLeft(bytesToWord3 ^ rotateLeft2, rotateLeft) + this.f1948_S[r7 + 1];
            r113++;
            bytesToWord3 = r10;
            r10 = rotateLeft(bytesToWord ^ rotateLeft, rotateLeft2) + this.f1948_S[r7];
            bytesToWord = r1;
            r1 = rotateLeft3;
        }
        int[] r114 = this.f1948_S;
        int r2 = bytesToWord3 + r114[43];
        wordToBytes(bytesToWord + r114[42], bArr2, r13);
        wordToBytes(r1, bArr2, r13 + 4);
        wordToBytes(r2, bArr2, r13 + 8);
        wordToBytes(r10, bArr2, r13 + 12);
        return 16;
    }

    private int rotateLeft(int r2, int r3) {
        return (r2 >>> (-r3)) | (r2 << r3);
    }

    private int rotateRight(int r2, int r3) {
        return (r2 << (-r3)) | (r2 >>> r3);
    }

    private void setKey(byte[] bArr) {
        int[] r4;
        int length = (bArr.length + 3) / 4;
        int length2 = ((bArr.length + 4) - 1) / 4;
        int[] r3 = new int[length2];
        for (int length3 = bArr.length - 1; length3 >= 0; length3--) {
            int r5 = length3 / 4;
            r3[r5] = (r3[r5] << 8) + (bArr[length3] & 255);
        }
        int[] r12 = new int[44];
        this.f1948_S = r12;
        r12[0] = P32;
        int r122 = 1;
        while (true) {
            r4 = this.f1948_S;
            if (r122 >= r4.length) {
                break;
            }
            r4[r122] = r4[r122 - 1] + Q32;
            r122++;
        }
        int length4 = length2 > r4.length ? length2 * 3 : r4.length * 3;
        int r42 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        for (int r52 = 0; r52 < length4; r52++) {
            int[] r9 = this.f1948_S;
            r6 = rotateLeft(r9[r42] + r6 + r7, 3);
            r9[r42] = r6;
            r7 = rotateLeft(r3[r8] + r6 + r7, r7 + r6);
            r3[r8] = r7;
            r42 = (r42 + 1) % this.f1948_S.length;
            r8 = (r8 + 1) % length2;
        }
    }

    private void wordToBytes(int r4, byte[] bArr, int r6) {
        for (int r0 = 0; r0 < 4; r0++) {
            bArr[r0 + r6] = (byte) r4;
            r4 >>>= 8;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC6";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            setKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC6 init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        int blockSize = getBlockSize();
        if (this.f1948_S != null) {
            if (r5 + blockSize <= bArr.length) {
                if (blockSize + r7 <= bArr2.length) {
                    return this.forEncryption ? encryptBlock(bArr, r5, bArr2, r7) : decryptBlock(bArr, r5, bArr2, r7);
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("RC6 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
