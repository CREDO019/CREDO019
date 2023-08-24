package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class SkipjackEngine implements BlockCipher {
    static final int BLOCK_SIZE = 8;
    static short[] ftable = {163, 215, 9, 131, 248, 72, 246, 244, 179, 33, 21, 120, 153, 177, 175, 249, 231, 45, 77, 138, 206, 76, 202, 46, 82, 149, 217, 30, 78, 56, 68, 40, 10, 223, 2, 160, 23, 241, 96, 104, 18, 183, 122, 195, 233, 250, 61, 83, 150, 132, 107, 186, 242, 99, 154, 25, 124, 174, 229, 245, 247, 22, 106, 162, 57, 182, 123, 15, 193, 147, 129, 27, 238, 180, 26, 234, 208, 145, 47, 184, 85, 185, 218, 133, 63, 65, 191, 224, 90, 88, 128, 95, 102, 11, 216, 144, 53, 213, 192, 167, 51, 6, 101, 105, 69, 0, 148, 86, 109, 152, 155, 118, 151, 252, 178, 194, 176, 254, 219, 32, 225, 235, 214, 228, 221, 71, 74, 29, 66, 237, 158, 110, 73, 60, 205, 67, 39, 210, 7, 212, 222, 199, 103, 24, 137, 203, 48, 31, 141, 198, 143, 170, 200, 116, 220, 201, 93, 92, 49, 164, 112, 136, 97, 44, 159, 13, 43, 135, 80, 130, 84, 100, 38, 125, 3, 64, 52, 75, 28, 115, 209, 196, 253, 59, 204, 251, 127, 171, 230, 62, 91, 165, 173, 4, 35, 156, 20, 81, 34, 240, 41, 121, 113, 126, 255, 140, 14, 226, 12, 239, 188, 114, 117, 111, 55, 161, 236, 211, 142, 98, 139, 134, 16, 232, 8, 119, 17, 190, 146, 79, 36, 197, 50, 54, 157, 207, 243, 166, 187, 172, 94, 108, 169, 19, 87, 37, 181, 227, 189, 168, 58, 1, 5, 89, 42, 70};
    private boolean encrypting;
    private int[] key0;
    private int[] key1;
    private int[] key2;
    private int[] key3;

    /* renamed from: g */
    private int m41g(int r4, int r5) {
        int r52 = r5 & 255;
        short[] sArr = ftable;
        int r0 = ((r5 >> 8) & 255) ^ sArr[this.key0[r4] ^ r52];
        int r53 = r52 ^ sArr[this.key1[r4] ^ r0];
        int r02 = r0 ^ sArr[this.key2[r4] ^ r53];
        return (r02 << 8) + (sArr[this.key3[r4] ^ r02] ^ r53);
    }

    /* renamed from: h */
    private int m40h(int r4, int r5) {
        int r0 = r5 & 255;
        int r52 = (r5 >> 8) & 255;
        short[] sArr = ftable;
        int r02 = r0 ^ sArr[this.key3[r4] ^ r52];
        int r53 = r52 ^ sArr[this.key2[r4] ^ r02];
        int r03 = r02 ^ sArr[this.key1[r4] ^ r53];
        return ((sArr[this.key0[r4] ^ r03] ^ r53) << 8) + r03;
    }

    public int decryptBlock(byte[] bArr, int r11, byte[] bArr2, int r13) {
        int r0 = (bArr[r11 + 0] << 8) + (bArr[r11 + 1] & 255);
        int r2 = (bArr[r11 + 2] << 8) + (bArr[r11 + 3] & 255);
        int r3 = (bArr[r11 + 4] << 8) + (bArr[r11 + 5] & 255);
        int r4 = (bArr[r11 + 6] << 8) + (bArr[r11 + 7] & 255);
        int r112 = 31;
        for (int r5 = 0; r5 < 2; r5++) {
            int r6 = 0;
            while (r6 < 8) {
                int m40h = m40h(r112, r2);
                r112--;
                r6++;
                int r8 = r4;
                r4 = r0;
                r0 = m40h;
                r2 = (r3 ^ m40h) ^ (r112 + 1);
                r3 = r8;
            }
            int r62 = 0;
            while (r62 < 8) {
                int m40h2 = m40h(r112, r2);
                r112--;
                r62++;
                int r82 = r4;
                r4 = (r0 ^ r2) ^ (r112 + 1);
                r0 = m40h2;
                r2 = r3;
                r3 = r82;
            }
        }
        bArr2[r13 + 0] = (byte) (r0 >> 8);
        bArr2[r13 + 1] = (byte) r0;
        bArr2[r13 + 2] = (byte) (r2 >> 8);
        bArr2[r13 + 3] = (byte) r2;
        bArr2[r13 + 4] = (byte) (r3 >> 8);
        bArr2[r13 + 5] = (byte) r3;
        bArr2[r13 + 6] = (byte) (r4 >> 8);
        bArr2[r13 + 7] = (byte) r4;
        return 8;
    }

    public int encryptBlock(byte[] bArr, int r11, byte[] bArr2, int r13) {
        int r0 = (bArr[r11 + 0] << 8) + (bArr[r11 + 1] & 255);
        int r2 = (bArr[r11 + 2] << 8) + (bArr[r11 + 3] & 255);
        int r3 = (bArr[r11 + 4] << 8) + (bArr[r11 + 5] & 255);
        int r4 = (bArr[r11 + 6] << 8) + (bArr[r11 + 7] & 255);
        int r5 = 0;
        for (int r112 = 0; r112 < 2; r112++) {
            int r6 = 0;
            while (r6 < 8) {
                int m41g = m41g(r5, r0);
                r5++;
                r6++;
                int r8 = r2;
                r2 = m41g;
                r0 = (r4 ^ m41g) ^ r5;
                r4 = r3;
                r3 = r8;
            }
            int r62 = 0;
            while (r62 < 8) {
                int r7 = r5 + 1;
                int r22 = (r2 ^ r0) ^ r7;
                int m41g2 = m41g(r5, r0);
                r62++;
                r5 = r7;
                r2 = m41g2;
                r0 = r4;
                r4 = r3;
                r3 = r22;
            }
        }
        bArr2[r13 + 0] = (byte) (r0 >> 8);
        bArr2[r13 + 1] = (byte) r0;
        bArr2[r13 + 2] = (byte) (r2 >> 8);
        bArr2[r13 + 3] = (byte) r2;
        bArr2[r13 + 4] = (byte) (r3 >> 8);
        bArr2[r13 + 5] = (byte) r3;
        bArr2[r13 + 6] = (byte) (r4 >> 8);
        bArr2[r13 + 7] = (byte) r4;
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SKIPJACK";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SKIPJACK init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.encrypting = z;
        this.key0 = new int[32];
        this.key1 = new int[32];
        this.key2 = new int[32];
        this.key3 = new int[32];
        for (int r0 = 0; r0 < 32; r0++) {
            int r2 = r0 * 4;
            this.key0[r0] = key[r2 % 10] & 255;
            this.key1[r0] = key[(r2 + 1) % 10] & 255;
            this.key2[r0] = key[(r2 + 2) % 10] & 255;
            this.key3[r0] = key[(r2 + 3) % 10] & 255;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) {
        if (this.key1 != null) {
            if (r4 + 8 <= bArr.length) {
                if (r6 + 8 <= bArr2.length) {
                    if (this.encrypting) {
                        encryptBlock(bArr, r4, bArr2, r6);
                        return 8;
                    }
                    decryptBlock(bArr, r4, bArr2, r6);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("SKIPJACK engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
