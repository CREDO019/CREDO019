package org.bouncycastle.crypto.engines;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class LEAEngine implements BlockCipher {
    private static final int BASEROUNDS = 16;
    private static final int BLOCKSIZE = 16;
    private static final int[] DELTA = {-1007687205, 1147300610, 2044886154, 2027892972, 1902027934, -947529206, -531697110, -440137385};
    private static final int KEY0 = 0;
    private static final int KEY1 = 1;
    private static final int KEY2 = 2;
    private static final int KEY3 = 3;
    private static final int KEY4 = 4;
    private static final int KEY5 = 5;
    private static final int MASK128 = 3;
    private static final int MASK256 = 7;
    private static final int NUMWORDS = 4;
    private static final int NUMWORDS128 = 4;
    private static final int NUMWORDS192 = 6;
    private static final int NUMWORDS256 = 8;
    private static final int ROT1 = 1;
    private static final int ROT11 = 11;
    private static final int ROT13 = 13;
    private static final int ROT17 = 17;
    private static final int ROT3 = 3;
    private static final int ROT5 = 5;
    private static final int ROT6 = 6;
    private static final int ROT9 = 9;
    private boolean forEncryption;
    private final int[] theBlock = new int[4];
    private int[][] theRoundKeys;
    private int theRounds;

    private static int bufLength(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    private static void checkBuffer(byte[] bArr, int r2, boolean z) {
        int bufLength = bufLength(bArr);
        int r0 = r2 + 16;
        if ((r2 < 0 || r0 < 0) || r0 > bufLength) {
            if (!z) {
                throw new DataLengthException("Input buffer too short.");
            }
        }
    }

    private int decryptBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        Pack.littleEndianToInt(bArr, r5, this.theBlock, 0, 4);
        for (int r4 = this.theRounds - 1; r4 >= 0; r4--) {
            decryptRound(r4);
        }
        Pack.intToLittleEndian(this.theBlock, bArr2, r7);
        return 16;
    }

    private void decryptRound(int r8) {
        int[] r0 = this.theRoundKeys[r8];
        int r82 = r8 % 4;
        int rightIndex = rightIndex(r82);
        int[] r3 = this.theBlock;
        r3[rightIndex] = r0[1] ^ (ror32(r3[rightIndex], 9) - (this.theBlock[r82] ^ r0[0]));
        int rightIndex2 = rightIndex(rightIndex);
        int[] r32 = this.theBlock;
        r32[rightIndex2] = (rol32(r32[rightIndex2], 5) - (this.theBlock[rightIndex] ^ r0[2])) ^ r0[3];
        int rightIndex3 = rightIndex(rightIndex2);
        int[] r4 = this.theBlock;
        r4[rightIndex3] = r0[5] ^ (rol32(r4[rightIndex3], 3) - (this.theBlock[rightIndex2] ^ r0[4]));
    }

    private int encryptBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        Pack.littleEndianToInt(bArr, r5, this.theBlock, 0, 4);
        for (int r1 = 0; r1 < this.theRounds; r1++) {
            encryptRound(r1);
        }
        Pack.intToLittleEndian(this.theBlock, bArr2, r7);
        return 16;
    }

    private void encryptRound(int r9) {
        int[] r0 = this.theRoundKeys[r9];
        int r92 = (r9 + 3) % 4;
        int leftIndex = leftIndex(r92);
        int[] r4 = this.theBlock;
        r4[r92] = ror32((r0[4] ^ r4[leftIndex]) + (r4[r92] ^ r0[5]), 3);
        int leftIndex2 = leftIndex(leftIndex);
        int[] r2 = this.theBlock;
        r2[leftIndex] = ror32((r2[leftIndex2] ^ r0[2]) + (r0[3] ^ r2[leftIndex]), 5);
        int leftIndex3 = leftIndex(leftIndex2);
        int[] r22 = this.theBlock;
        r22[leftIndex2] = rol32((r22[leftIndex3] ^ r0[0]) + (r0[1] ^ r22[leftIndex2]), 9);
    }

    private void generate128RoundKeys(int[] r9) {
        for (int r1 = 0; r1 < this.theRounds; r1++) {
            int rol32 = rol32(DELTA[r1 & 3], r1);
            r9[0] = rol32(r9[0] + rol32, 1);
            r9[1] = rol32(r9[1] + rol32(rol32, 1), 3);
            r9[2] = rol32(r9[2] + rol32(rol32, 2), 6);
            r9[3] = rol32(r9[3] + rol32(rol32, 3), 11);
            int[] r2 = this.theRoundKeys[r1];
            r2[0] = r9[0];
            r2[1] = r9[1];
            r2[2] = r9[2];
            r2[3] = r9[1];
            r2[4] = r9[3];
            r2[5] = r9[1];
        }
    }

    private void generate192RoundKeys(int[] r9) {
        for (int r1 = 0; r1 < this.theRounds; r1++) {
            int rol32 = rol32(DELTA[r1 % 6], r1);
            r9[0] = rol32(r9[0] + rol32(rol32, 0), 1);
            r9[1] = rol32(r9[1] + rol32(rol32, 1), 3);
            r9[2] = rol32(r9[2] + rol32(rol32, 2), 6);
            r9[3] = rol32(r9[3] + rol32(rol32, 3), 11);
            r9[4] = rol32(r9[4] + rol32(rol32, 4), 13);
            r9[5] = rol32(r9[5] + rol32(rol32, 5), 17);
            System.arraycopy(r9, 0, this.theRoundKeys[r1], 0, 6);
        }
    }

    private void generate256RoundKeys(int[] r12) {
        int r2 = 0;
        for (int r1 = 0; r1 < this.theRounds; r1++) {
            int rol32 = rol32(DELTA[r1 & 7], r1);
            int[] r4 = this.theRoundKeys[r1];
            int r5 = r2 & 7;
            r4[0] = rol32(r12[r5] + rol32, 1);
            int r22 = r2 + 1;
            r12[r5] = r4[0];
            int r52 = r22 & 7;
            r4[1] = rol32(r12[r52] + rol32(rol32, 1), 3);
            int r23 = r22 + 1;
            r12[r52] = r4[1];
            int r53 = r23 & 7;
            r4[2] = rol32(r12[r53] + rol32(rol32, 2), 6);
            int r24 = r23 + 1;
            r12[r53] = r4[2];
            int r54 = r24 & 7;
            r4[3] = rol32(r12[r54] + rol32(rol32, 3), 11);
            int r25 = r24 + 1;
            r12[r54] = r4[3];
            int r55 = r25 & 7;
            r4[4] = rol32(r12[r55] + rol32(rol32, 4), 13);
            int r26 = r25 + 1;
            r12[r55] = r4[4];
            int r56 = r26 & 7;
            r4[5] = rol32(r12[r56] + rol32(rol32, 5), 17);
            r2 = r26 + 1;
            r12[r56] = r4[5];
        }
    }

    private void generateRoundKeys(byte[] bArr) {
        int length = (bArr.length >> 1) + 16;
        this.theRounds = length;
        this.theRoundKeys = (int[][]) Array.newInstance(int.class, length, 6);
        int length2 = bArr.length / 4;
        int[] r4 = new int[length2];
        Pack.littleEndianToInt(bArr, 0, r4, 0, length2);
        if (length2 == 4) {
            generate128RoundKeys(r4);
        } else if (length2 != 6) {
            generate256RoundKeys(r4);
        } else {
            generate192RoundKeys(r4);
        }
    }

    private static int leftIndex(int r0) {
        if (r0 == 0) {
            return 3;
        }
        return r0 - 1;
    }

    private static int rightIndex(int r1) {
        if (r1 == 3) {
            return 0;
        }
        return r1 + 1;
    }

    private static int rol32(int r1, int r2) {
        return (r1 >>> (32 - r2)) | (r1 << r2);
    }

    private static int ror32(int r1, int r2) {
        return (r1 << (32 - r2)) | (r1 >>> r2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "LEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to LEA init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        int length = key.length;
        if ((length << 1) % 16 != 0 || length < 16 || length > 32) {
            throw new IllegalArgumentException("KeyBitSize must be 128, 192 or 256");
        }
        this.forEncryption = z;
        generateRoundKeys(key);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r3, byte[] bArr2, int r5) {
        checkBuffer(bArr, r3, false);
        checkBuffer(bArr2, r5, true);
        return this.forEncryption ? encryptBlock(bArr, r3, bArr2, r5) : decryptBlock(bArr, r3, bArr2, r5);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
