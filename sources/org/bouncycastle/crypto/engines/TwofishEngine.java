package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;

    /* renamed from: P */
    private static final byte[][] f1976P = {new byte[]{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, Byte.MIN_VALUE, 120, -28, -35, -47, 56, 13, -58, 53, -104, Ascii.CAN, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, Ascii.f1120EM, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, Ascii.SYN, Ascii.f1121FF, -29, 97, -64, -116, 58, -11, 115, 44, 37, Ascii.f1132VT, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, Ascii.f1122FS, Ascii.f1127RS, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, Ascii.f1123GS, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, Ascii.ESC, 95, -109, 10, -17, -111, -123, 73, -18, MultipartStream.DASH, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, Byte.MAX_VALUE, -43, Ascii.SUB, 75, Ascii.f1129SO, -89, 90, 40, Ascii.DC4, Utf8.REPLACEMENT_BYTE, 41, -120, 60, 76, 2, -72, -38, -80, Ascii.ETB, 85, Ascii.f1131US, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, Ascii.NAK, 34, Ascii.DC2, 88, 7, -103, 52, 110, 80, -34, 104, 101, PSSSigner.TRAILER_IMPLICIT, -37, -8, -56, -88, 43, SignedBytes.MAX_POWER_OF_TWO, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, Ascii.f1128SI, 0, 111, -99, 54, 66, 74, 94, -63, -32}, new byte[]{117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, Ascii.f1128SI, -8, Ascii.ESC, -121, -6, 6, Utf8.REPLACEMENT_BYTE, 94, -70, -82, 91, -118, 0, PSSSigner.TRAILER_IMPLICIT, -99, 109, -63, -79, Ascii.f1129SO, Byte.MIN_VALUE, 93, -46, -43, -96, -124, 7, Ascii.DC4, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, SignedBytes.MAX_POWER_OF_TWO, -25, 43, -30, 121, Ascii.f1121FF, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, Ascii.ETB, 102, -108, -95, Ascii.f1123GS, 61, -16, -34, -77, Ascii.f1132VT, 114, -89, Ascii.f1122FS, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, Ascii.SUB, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, Ascii.CAN, 35, -35, Ascii.f1131US, 78, MultipartStream.DASH, -7, 72, 79, -14, 101, -114, 120, 92, 88, Ascii.f1120EM, -115, -27, -104, 87, 103, Byte.MAX_VALUE, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, Ascii.NAK, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, Ascii.DC2, -94, 13, 82, -69, 2, 47, -87, -41, 97, Ascii.f1127RS, -76, 80, 4, -10, -62, Ascii.SYN, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        int[] r3 = new int[2];
        int[] r4 = new int[2];
        int[] r2 = new int[2];
        for (int r5 = 0; r5 < 256; r5++) {
            byte[][] bArr = f1976P;
            int r7 = bArr[0][r5] & 255;
            r3[0] = r7;
            r4[0] = Mx_X(r7) & 255;
            r2[0] = Mx_Y(r7) & 255;
            int r6 = bArr[1][r5] & 255;
            r3[1] = r6;
            r4[1] = Mx_X(r6) & 255;
            r2[1] = Mx_Y(r6) & 255;
            this.gMDS0[r5] = r3[1] | (r4[1] << 8) | (r2[1] << 16) | (r2[1] << 24);
            this.gMDS1[r5] = r2[0] | (r2[0] << 8) | (r4[0] << 16) | (r3[0] << 24);
            this.gMDS2[r5] = (r2[1] << 24) | r4[1] | (r2[1] << 8) | (r3[1] << 16);
            this.gMDS3[r5] = r4[0] | (r3[0] << 8) | (r2[0] << 16) | (r4[0] << 24);
        }
    }

    private int F32(int r12, int[] r13) {
        int r132;
        int r122;
        int m39b0 = m39b0(r12);
        int m38b1 = m38b1(r12);
        int m37b2 = m37b2(r12);
        int m36b3 = m36b3(r12);
        int r4 = r13[0];
        int r6 = r13[1];
        int r8 = r13[2];
        int r133 = r13[3];
        int r10 = this.k64Cnt & 3;
        if (r10 != 0) {
            if (r10 == 1) {
                int[] r134 = this.gMDS0;
                byte[][] bArr = f1976P;
                r132 = (r134[(bArr[0][m39b0] & 255) ^ m39b0(r4)] ^ this.gMDS1[(bArr[0][m38b1] & 255) ^ m38b1(r4)]) ^ this.gMDS2[(bArr[1][m37b2] & 255) ^ m37b2(r4)];
                r122 = this.gMDS3[(bArr[1][m36b3] & 255) ^ m36b3(r4)];
                return r132 ^ r122;
            }
            if (r10 != 2) {
                if (r10 != 3) {
                    return 0;
                }
            }
            int[] r135 = this.gMDS0;
            byte[][] bArr2 = f1976P;
            r132 = (r135[(bArr2[0][(bArr2[0][m39b0] & 255) ^ m39b0(r6)] & 255) ^ m39b0(r4)] ^ this.gMDS1[(bArr2[0][(bArr2[1][m38b1] & 255) ^ m38b1(r6)] & 255) ^ m38b1(r4)]) ^ this.gMDS2[(bArr2[1][(bArr2[0][m37b2] & 255) ^ m37b2(r6)] & 255) ^ m37b2(r4)];
            r122 = this.gMDS3[(bArr2[1][(bArr2[1][m36b3] & 255) ^ m36b3(r6)] & 255) ^ m36b3(r4)];
            return r132 ^ r122;
        }
        byte[][] bArr3 = f1976P;
        m39b0 = (bArr3[1][m39b0] & 255) ^ m39b0(r133);
        m38b1 = (bArr3[0][m38b1] & 255) ^ m38b1(r133);
        m37b2 = (bArr3[0][m37b2] & 255) ^ m37b2(r133);
        m36b3 = (bArr3[1][m36b3] & 255) ^ m36b3(r133);
        byte[][] bArr4 = f1976P;
        m39b0 = (bArr4[1][m39b0] & 255) ^ m39b0(r8);
        m38b1 = (bArr4[1][m38b1] & 255) ^ m38b1(r8);
        m37b2 = (bArr4[0][m37b2] & 255) ^ m37b2(r8);
        m36b3 = (bArr4[0][m36b3] & 255) ^ m36b3(r8);
        int[] r1352 = this.gMDS0;
        byte[][] bArr22 = f1976P;
        r132 = (r1352[(bArr22[0][(bArr22[0][m39b0] & 255) ^ m39b0(r6)] & 255) ^ m39b0(r4)] ^ this.gMDS1[(bArr22[0][(bArr22[1][m38b1] & 255) ^ m38b1(r6)] & 255) ^ m38b1(r4)]) ^ this.gMDS2[(bArr22[1][(bArr22[0][m37b2] & 255) ^ m37b2(r6)] & 255) ^ m37b2(r4)];
        r122 = this.gMDS3[(bArr22[1][(bArr22[1][m36b3] & 255) ^ m36b3(r6)] & 255) ^ m36b3(r4)];
        return r132 ^ r122;
    }

    private int Fe32_0(int r4) {
        int[] r0 = this.gSBox;
        return r0[(((r4 >>> 24) & 255) * 2) + InputDeviceCompat.SOURCE_DPAD] ^ ((r0[((r4 & 255) * 2) + 0] ^ r0[(((r4 >>> 8) & 255) * 2) + 1]) ^ r0[(((r4 >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int r4) {
        int[] r0 = this.gSBox;
        return r0[(((r4 >>> 16) & 255) * 2) + InputDeviceCompat.SOURCE_DPAD] ^ ((r0[(((r4 >>> 24) & 255) * 2) + 0] ^ r0[((r4 & 255) * 2) + 1]) ^ r0[(((r4 >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int r2) {
        return ((r2 & 1) != 0 ? 180 : 0) ^ (r2 >> 1);
    }

    private int LFSR2(int r4) {
        return ((r4 >> 2) ^ ((r4 & 2) != 0 ? 180 : 0)) ^ ((r4 & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int r2) {
        return r2 ^ LFSR2(r2);
    }

    private int Mx_Y(int r2) {
        return LFSR2(r2) ^ (LFSR1(r2) ^ r2);
    }

    private int RS_MDS_Encode(int r4, int r5) {
        for (int r1 = 0; r1 < 4; r1++) {
            r5 = RS_rem(r5);
        }
        int r42 = r4 ^ r5;
        for (int r0 = 0; r0 < 4; r0++) {
            r42 = RS_rem(r42);
        }
        return r42;
    }

    private int RS_rem(int r6) {
        int r0 = (r6 >>> 24) & 255;
        int r1 = ((r0 << 1) ^ ((r0 & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int r2 = ((r0 >>> 1) ^ ((r0 & 1) != 0 ? 166 : 0)) ^ r1;
        return ((((r6 << 8) ^ (r2 << 24)) ^ (r1 << 16)) ^ (r2 << 8)) ^ r0;
    }

    /* renamed from: b0 */
    private int m39b0(int r1) {
        return r1 & 255;
    }

    /* renamed from: b1 */
    private int m38b1(int r1) {
        return (r1 >>> 8) & 255;
    }

    /* renamed from: b2 */
    private int m37b2(int r1) {
        return (r1 >>> 16) & 255;
    }

    /* renamed from: b3 */
    private int m36b3(int r1) {
        return (r1 >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int r13, byte[] bArr2, int r15) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, r13) ^ this.gSubKeys[4];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, r13 + 4) ^ this.gSubKeys[5];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, r13 + 8) ^ this.gSubKeys[6];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, r13 + 12) ^ this.gSubKeys[7];
        int r3 = 39;
        int r4 = 0;
        while (r4 < 16) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            int r10 = r3 - 1;
            int r5 = Fe32_0 + Fe32_3;
            int r7 = r10 - 1;
            littleEndianToInt3 = Integers.rotateLeft(littleEndianToInt3, 1) ^ (r5 + this.gSubKeys[r10]);
            littleEndianToInt4 = Integers.rotateRight(littleEndianToInt4 ^ (((Fe32_3 * 2) + Fe32_0) + this.gSubKeys[r3]), 1);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int r102 = r7 - 1;
            littleEndianToInt = Integers.rotateLeft(littleEndianToInt, 1) ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[r102]);
            littleEndianToInt2 = Integers.rotateRight(littleEndianToInt2 ^ (((Fe32_32 * 2) + Fe32_02) + this.gSubKeys[r7]), 1);
            r4 += 2;
            r3 = r102 - 1;
        }
        Pack.intToLittleEndian(this.gSubKeys[0] ^ littleEndianToInt3, bArr2, r15);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[1], bArr2, r15 + 4);
        Pack.intToLittleEndian(this.gSubKeys[2] ^ littleEndianToInt, bArr2, r15 + 8);
        Pack.intToLittleEndian(this.gSubKeys[3] ^ littleEndianToInt2, bArr2, r15 + 12);
    }

    private void encryptBlock(byte[] bArr, int r13, byte[] bArr2, int r15) {
        int r2 = 0;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, r13) ^ this.gSubKeys[0];
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, r13 + 4) ^ this.gSubKeys[1];
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, r13 + 8) ^ this.gSubKeys[2];
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, r13 + 12) ^ this.gSubKeys[3];
        int r132 = 8;
        while (r2 < 16) {
            int Fe32_0 = Fe32_0(littleEndianToInt);
            int Fe32_3 = Fe32_3(littleEndianToInt2);
            int r10 = r132 + 1;
            littleEndianToInt3 = Integers.rotateRight(littleEndianToInt3 ^ ((Fe32_0 + Fe32_3) + this.gSubKeys[r132]), 1);
            int r5 = Fe32_0 + (Fe32_3 * 2);
            int r7 = r10 + 1;
            littleEndianToInt4 = Integers.rotateLeft(littleEndianToInt4, 1) ^ (r5 + this.gSubKeys[r10]);
            int Fe32_02 = Fe32_0(littleEndianToInt3);
            int Fe32_32 = Fe32_3(littleEndianToInt4);
            int r102 = r7 + 1;
            littleEndianToInt = Integers.rotateRight(littleEndianToInt ^ ((Fe32_02 + Fe32_32) + this.gSubKeys[r7]), 1);
            littleEndianToInt2 = Integers.rotateLeft(littleEndianToInt2, 1) ^ ((Fe32_02 + (Fe32_32 * 2)) + this.gSubKeys[r102]);
            r2 += 2;
            r132 = r102 + 1;
        }
        Pack.intToLittleEndian(this.gSubKeys[4] ^ littleEndianToInt3, bArr2, r15);
        Pack.intToLittleEndian(littleEndianToInt4 ^ this.gSubKeys[5], bArr2, r15 + 4);
        Pack.intToLittleEndian(this.gSubKeys[6] ^ littleEndianToInt, bArr2, r15 + 8);
        Pack.intToLittleEndian(this.gSubKeys[7] ^ littleEndianToInt2, bArr2, r15 + 12);
    }

    private void setKey(byte[] bArr) {
        int m39b0;
        int m38b1;
        int m37b2;
        int m36b3;
        int r13;
        int r12;
        int r11;
        int r10;
        int[] r3 = new int[4];
        int[] r4 = new int[4];
        int[] r5 = new int[4];
        this.gSubKeys = new int[40];
        for (int r7 = 0; r7 < this.k64Cnt; r7++) {
            int r8 = r7 * 8;
            r3[r7] = Pack.littleEndianToInt(bArr, r8);
            r4[r7] = Pack.littleEndianToInt(bArr, r8 + 4);
            r5[(this.k64Cnt - 1) - r7] = RS_MDS_Encode(r3[r7], r4[r7]);
        }
        for (int r1 = 0; r1 < 20; r1++) {
            int r2 = SK_STEP * r1;
            int F32 = F32(r2, r3);
            int rotateLeft = Integers.rotateLeft(F32(r2 + SK_BUMP, r4), 8);
            int r72 = F32 + rotateLeft;
            int[] r82 = this.gSubKeys;
            int r102 = r1 * 2;
            r82[r102] = r72;
            int r73 = r72 + rotateLeft;
            r82[r102 + 1] = (r73 << 9) | (r73 >>> 23);
        }
        int r14 = r5[0];
        int r22 = r5[1];
        int r32 = 2;
        int r42 = r5[2];
        int r52 = r5[3];
        this.gSBox = new int[1024];
        int r83 = 0;
        while (r83 < 256) {
            int r103 = this.k64Cnt & 3;
            if (r103 != 0) {
                if (r103 == 1) {
                    int[] r104 = this.gSBox;
                    int r112 = r83 * 2;
                    int[] r122 = this.gMDS0;
                    byte[][] bArr2 = f1976P;
                    r104[r112] = r122[(bArr2[0][r83] & 255) ^ m39b0(r14)];
                    this.gSBox[r112 + 1] = this.gMDS1[(bArr2[0][r83] & 255) ^ m38b1(r14)];
                    this.gSBox[r112 + 512] = this.gMDS2[(bArr2[1][r83] & 255) ^ m37b2(r14)];
                    this.gSBox[r112 + InputDeviceCompat.SOURCE_DPAD] = this.gMDS3[(bArr2[1][r83] & 255) ^ m36b3(r14)];
                } else if (r103 == r32) {
                    r10 = r83;
                    r11 = r10;
                    r12 = r11;
                    r13 = r12;
                    int[] r142 = this.gSBox;
                    int r15 = r83 * 2;
                    int[] r33 = this.gMDS0;
                    byte[][] bArr3 = f1976P;
                    r142[r15] = r33[(bArr3[0][(bArr3[0][r11] & 255) ^ m39b0(r22)] & 255) ^ m39b0(r14)];
                    this.gSBox[r15 + 1] = this.gMDS1[(bArr3[0][(bArr3[1][r12] & 255) ^ m38b1(r22)] & 255) ^ m38b1(r14)];
                    this.gSBox[r15 + 512] = this.gMDS2[(bArr3[1][(bArr3[0][r13] & 255) ^ m37b2(r22)] & 255) ^ m37b2(r14)];
                    this.gSBox[r15 + InputDeviceCompat.SOURCE_DPAD] = this.gMDS3[(bArr3[1][(bArr3[1][r10] & 255) ^ m36b3(r22)] & 255) ^ m36b3(r14)];
                } else if (r103 == 3) {
                    m36b3 = r83;
                    m39b0 = m36b3;
                    m38b1 = m39b0;
                    m37b2 = m38b1;
                }
                r83++;
                r32 = 2;
            } else {
                byte[][] bArr4 = f1976P;
                m39b0 = (bArr4[1][r83] & 255) ^ m39b0(r52);
                m38b1 = (bArr4[0][r83] & 255) ^ m38b1(r52);
                m37b2 = (bArr4[0][r83] & 255) ^ m37b2(r52);
                m36b3 = (bArr4[1][r83] & 255) ^ m36b3(r52);
            }
            byte[][] bArr5 = f1976P;
            r11 = (bArr5[1][m39b0] & 255) ^ m39b0(r42);
            r12 = (bArr5[1][m38b1] & 255) ^ m38b1(r42);
            r13 = (bArr5[0][m37b2] & 255) ^ m37b2(r42);
            r10 = (bArr5[0][m36b3] & 255) ^ m36b3(r42);
            int[] r1422 = this.gSBox;
            int r152 = r83 * 2;
            int[] r332 = this.gMDS0;
            byte[][] bArr32 = f1976P;
            r1422[r152] = r332[(bArr32[0][(bArr32[0][r11] & 255) ^ m39b0(r22)] & 255) ^ m39b0(r14)];
            this.gSBox[r152 + 1] = this.gMDS1[(bArr32[0][(bArr32[1][r12] & 255) ^ m38b1(r22)] & 255) ^ m38b1(r14)];
            this.gSBox[r152 + 512] = this.gMDS2[(bArr32[1][(bArr32[0][r13] & 255) ^ m37b2(r22)] & 255) ^ m37b2(r14)];
            this.gSBox[r152 + InputDeviceCompat.SOURCE_DPAD] = this.gMDS3[(bArr32[1][(bArr32[1][r10] & 255) ^ m36b3(r22)] & 255) ^ m36b3(r14)];
            r83++;
            r32 = 2;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = key;
        int length = key.length * 8;
        if (length != 128 && length != 192 && length != 256) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        this.k64Cnt = key.length / 8;
        setKey(key);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) {
        if (this.workingKey != null) {
            if (r4 + 16 <= bArr.length) {
                if (r6 + 16 <= bArr2.length) {
                    if (this.encrypting) {
                        encryptBlock(bArr, r4, bArr2, r6);
                        return 16;
                    }
                    decryptBlock(bArr, r4, bArr2, r6);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Twofish not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
