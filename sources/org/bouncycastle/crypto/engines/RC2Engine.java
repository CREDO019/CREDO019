package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RC2Parameters;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes5.dex */
public class RC2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 8;
    private static byte[] piTable = {-39, 120, -7, -60, Ascii.f1120EM, -35, -75, -19, 40, -23, -3, 121, 74, -96, -40, -99, -58, 126, 55, -125, 43, 118, 83, -114, 98, 76, 100, -120, 68, -117, -5, -94, Ascii.ETB, -102, 89, -11, -121, -77, 79, 19, 97, 69, 109, -115, 9, -127, 125, 50, -67, -113, SignedBytes.MAX_POWER_OF_TWO, -21, -122, -73, 123, Ascii.f1132VT, -16, -107, 33, 34, 92, 107, 78, -126, 84, -42, 101, -109, -50, 96, -78, Ascii.f1122FS, 115, 86, -64, Ascii.DC4, -89, -116, -15, -36, Ascii.DC2, 117, -54, Ascii.f1131US, 59, -66, -28, -47, 66, 61, -44, 48, -93, 60, -74, 38, 111, -65, Ascii.f1129SO, -38, 70, 105, 7, 87, 39, -14, Ascii.f1123GS, -101, PSSSigner.TRAILER_IMPLICIT, -108, 67, 3, -8, 17, -57, -10, -112, -17, 62, -25, 6, -61, -43, 47, -56, 102, Ascii.f1127RS, -41, 8, -24, -22, -34, Byte.MIN_VALUE, 82, -18, -9, -124, -86, 114, -84, 53, 77, 106, 42, -106, Ascii.SUB, -46, 113, 90, Ascii.NAK, 73, 116, 75, -97, -48, 94, 4, Ascii.CAN, -92, -20, -62, -32, 65, 110, Ascii.f1128SI, 81, -53, -52, 36, -111, -81, 80, -95, -12, 112, 57, -103, 124, 58, -123, 35, -72, -76, 122, -4, 2, 54, 91, 37, 85, -105, 49, MultipartStream.DASH, 93, -6, -104, -29, -118, -110, -82, 5, -33, 41, 16, 103, 108, -70, -55, -45, 0, -26, -49, -31, -98, -88, 44, 99, Ascii.SYN, 1, Utf8.REPLACEMENT_BYTE, 88, -30, -119, -87, 13, 56, 52, Ascii.ESC, -85, 51, -1, -80, -69, 72, Ascii.f1121FF, 95, -71, -79, -51, 46, -59, -13, -37, 71, -27, -91, -100, 119, 10, -90, 32, 104, -2, Byte.MAX_VALUE, -63, -83};
    private boolean encrypting;
    private int[] workingKey;

    private void decryptBlock(byte[] bArr, int r12, byte[] bArr2, int r14) {
        int r0 = ((bArr[r12 + 7] & 255) << 8) + (bArr[r12 + 6] & 255);
        int r1 = ((bArr[r12 + 5] & 255) << 8) + (bArr[r12 + 4] & 255);
        int r2 = ((bArr[r12 + 3] & 255) << 8) + (bArr[r12 + 2] & 255);
        int r3 = ((bArr[r12 + 1] & 255) << 8) + (bArr[r12 + 0] & 255);
        for (int r11 = 60; r11 >= 44; r11 -= 4) {
            r0 = rotateWordLeft(r0, 11) - ((((~r1) & r3) + (r2 & r1)) + this.workingKey[r11 + 3]);
            r1 = rotateWordLeft(r1, 13) - ((((~r2) & r0) + (r3 & r2)) + this.workingKey[r11 + 2]);
            r2 = rotateWordLeft(r2, 14) - ((((~r3) & r1) + (r0 & r3)) + this.workingKey[r11 + 1]);
            r3 = rotateWordLeft(r3, 15) - ((((~r0) & r2) + (r1 & r0)) + this.workingKey[r11]);
        }
        int[] r112 = this.workingKey;
        int r02 = r0 - r112[r1 & 63];
        int r13 = r1 - r112[r2 & 63];
        int r22 = r2 - r112[r3 & 63];
        int r32 = r3 - r112[r02 & 63];
        for (int r113 = 40; r113 >= 20; r113 -= 4) {
            r02 = rotateWordLeft(r02, 11) - ((((~r13) & r32) + (r22 & r13)) + this.workingKey[r113 + 3]);
            r13 = rotateWordLeft(r13, 13) - ((((~r22) & r02) + (r32 & r22)) + this.workingKey[r113 + 2]);
            r22 = rotateWordLeft(r22, 14) - ((((~r32) & r13) + (r02 & r32)) + this.workingKey[r113 + 1]);
            r32 = rotateWordLeft(r32, 15) - ((((~r02) & r22) + (r13 & r02)) + this.workingKey[r113]);
        }
        int[] r114 = this.workingKey;
        int r03 = r02 - r114[r13 & 63];
        int r15 = r13 - r114[r22 & 63];
        int r23 = r22 - r114[r32 & 63];
        int r33 = r32 - r114[r03 & 63];
        for (int r115 = 16; r115 >= 0; r115 -= 4) {
            r03 = rotateWordLeft(r03, 11) - ((((~r15) & r33) + (r23 & r15)) + this.workingKey[r115 + 3]);
            r15 = rotateWordLeft(r15, 13) - ((((~r23) & r03) + (r33 & r23)) + this.workingKey[r115 + 2]);
            r23 = rotateWordLeft(r23, 14) - ((((~r33) & r15) + (r03 & r33)) + this.workingKey[r115 + 1]);
            r33 = rotateWordLeft(r33, 15) - ((((~r03) & r23) + (r15 & r03)) + this.workingKey[r115]);
        }
        bArr2[r14 + 0] = (byte) r33;
        bArr2[r14 + 1] = (byte) (r33 >> 8);
        bArr2[r14 + 2] = (byte) r23;
        bArr2[r14 + 3] = (byte) (r23 >> 8);
        bArr2[r14 + 4] = (byte) r15;
        bArr2[r14 + 5] = (byte) (r15 >> 8);
        bArr2[r14 + 6] = (byte) r03;
        bArr2[r14 + 7] = (byte) (r03 >> 8);
    }

    private void encryptBlock(byte[] bArr, int r11, byte[] bArr2, int r13) {
        int r0 = ((bArr[r11 + 7] & 255) << 8) + (bArr[r11 + 6] & 255);
        int r1 = ((bArr[r11 + 5] & 255) << 8) + (bArr[r11 + 4] & 255);
        int r2 = ((bArr[r11 + 3] & 255) << 8) + (bArr[r11 + 2] & 255);
        int r3 = ((bArr[r11 + 1] & 255) << 8) + (bArr[r11 + 0] & 255);
        for (int r4 = 0; r4 <= 16; r4 += 4) {
            r3 = rotateWordLeft(r3 + ((~r0) & r2) + (r1 & r0) + this.workingKey[r4], 1);
            r2 = rotateWordLeft(r2 + ((~r3) & r1) + (r0 & r3) + this.workingKey[r4 + 1], 2);
            r1 = rotateWordLeft(r1 + ((~r2) & r0) + (r3 & r2) + this.workingKey[r4 + 2], 3);
            r0 = rotateWordLeft(r0 + ((~r1) & r3) + (r2 & r1) + this.workingKey[r4 + 3], 5);
        }
        int[] r10 = this.workingKey;
        int r32 = r3 + r10[r0 & 63];
        int r22 = r2 + r10[r32 & 63];
        int r12 = r1 + r10[r22 & 63];
        int r02 = r0 + r10[r12 & 63];
        for (int r102 = 20; r102 <= 40; r102 += 4) {
            r32 = rotateWordLeft(r32 + ((~r02) & r22) + (r12 & r02) + this.workingKey[r102], 1);
            r22 = rotateWordLeft(r22 + ((~r32) & r12) + (r02 & r32) + this.workingKey[r102 + 1], 2);
            r12 = rotateWordLeft(r12 + ((~r22) & r02) + (r32 & r22) + this.workingKey[r102 + 2], 3);
            r02 = rotateWordLeft(r02 + ((~r12) & r32) + (r22 & r12) + this.workingKey[r102 + 3], 5);
        }
        int[] r103 = this.workingKey;
        int r33 = r32 + r103[r02 & 63];
        int r23 = r22 + r103[r33 & 63];
        int r14 = r12 + r103[r23 & 63];
        int r03 = r02 + r103[r14 & 63];
        for (int r104 = 44; r104 < 64; r104 += 4) {
            r33 = rotateWordLeft(r33 + ((~r03) & r23) + (r14 & r03) + this.workingKey[r104], 1);
            r23 = rotateWordLeft(r23 + ((~r33) & r14) + (r03 & r33) + this.workingKey[r104 + 1], 2);
            r14 = rotateWordLeft(r14 + ((~r23) & r03) + (r33 & r23) + this.workingKey[r104 + 2], 3);
            r03 = rotateWordLeft(r03 + ((~r14) & r33) + (r23 & r14) + this.workingKey[r104 + 3], 5);
        }
        bArr2[r13 + 0] = (byte) r33;
        bArr2[r13 + 1] = (byte) (r33 >> 8);
        bArr2[r13 + 2] = (byte) r23;
        bArr2[r13 + 3] = (byte) (r23 >> 8);
        bArr2[r13 + 4] = (byte) r14;
        bArr2[r13 + 5] = (byte) (r14 >> 8);
        bArr2[r13 + 6] = (byte) r03;
        bArr2[r13 + 7] = (byte) (r03 >> 8);
    }

    private int[] generateWorkingKey(byte[] bArr, int r10) {
        int[] r1 = new int[128];
        for (int r3 = 0; r3 != bArr.length; r3++) {
            r1[r3] = bArr[r3] & 255;
        }
        int length = bArr.length;
        if (length < 128) {
            int r32 = r1[length - 1];
            int r4 = 0;
            while (true) {
                int r7 = r4 + 1;
                r32 = piTable[(r32 + r1[r4]) & 255] & 255;
                int r42 = length + 1;
                r1[length] = r32;
                if (r42 >= 128) {
                    break;
                }
                length = r42;
                r4 = r7;
            }
        }
        int r9 = (r10 + 7) >> 3;
        int r33 = 128 - r9;
        int r102 = piTable[(255 >> ((-r10) & 7)) & r1[r33]] & 255;
        r1[r33] = r102;
        for (int r34 = r33 - 1; r34 >= 0; r34--) {
            r102 = piTable[r102 ^ r1[r34 + r9]] & 255;
            r1[r34] = r102;
        }
        int[] r103 = new int[64];
        for (int r2 = 0; r2 != 64; r2++) {
            int r0 = r2 * 2;
            r103[r2] = r1[r0] + (r1[r0 + 1] << 8);
        }
        return r103;
    }

    private int rotateWordLeft(int r2, int r3) {
        int r22 = r2 & 65535;
        return (r22 >> (16 - r3)) | (r22 << r3);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.encrypting = z;
        if (cipherParameters instanceof RC2Parameters) {
            RC2Parameters rC2Parameters = (RC2Parameters) cipherParameters;
            this.workingKey = generateWorkingKey(rC2Parameters.getKey(), rC2Parameters.getEffectiveKeyBits());
        } else if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = generateWorkingKey(key, key.length * 8);
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC2 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) {
        if (this.workingKey != null) {
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
        throw new IllegalStateException("RC2 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
