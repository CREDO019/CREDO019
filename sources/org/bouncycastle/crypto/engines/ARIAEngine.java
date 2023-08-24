package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes5.dex */
public class ARIAEngine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;

    /* renamed from: C */
    private static final byte[][] f1892C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, Ascii.f1121FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f1120EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f1132VT, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.f1122FS, -90, -76, -58, -24, -35, 116, Ascii.f1131US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f1129SO, 97, 53, 87, -71, -122, -63, Ascii.f1123GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f1127RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, MultipartStream.DASH, Ascii.f1128SI, -80, 84, -69, Ascii.SYN};
    private static final byte[] SB2_sbox = {-30, 78, 84, -4, -108, -62, 74, -52, 98, 13, 106, 70, 60, 77, -117, -47, 94, -6, 100, -53, -76, -105, -66, 43, PSSSigner.TRAILER_IMPLICIT, 119, 46, 3, -45, Ascii.f1120EM, 89, -63, Ascii.f1123GS, 6, 65, 107, 85, -16, -103, 105, -22, -100, Ascii.CAN, -82, 99, -33, -25, -69, 0, 115, 102, -5, -106, 76, -123, -28, 58, 9, 69, -86, Ascii.f1128SI, -18, 16, -21, MultipartStream.DASH, Byte.MAX_VALUE, -12, 41, -84, -49, -83, -111, -115, 120, -56, -107, -7, 47, -50, -51, 8, 122, -120, 56, 92, -125, 42, 40, 71, -37, -72, -57, -109, -92, Ascii.DC2, 83, -1, -121, Ascii.f1129SO, 49, 54, 33, 88, 72, 1, -114, 55, 116, 50, -54, -23, -79, -73, -85, Ascii.f1121FF, -41, -60, 86, 66, 38, 7, -104, 96, -39, -74, -71, 17, SignedBytes.MAX_POWER_OF_TWO, -20, 32, -116, -67, -96, -55, -124, 4, 73, 35, -15, 79, 80, Ascii.f1131US, 19, -36, -40, -64, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, Ascii.NAK, -35, -3, Ascii.ETB, -87, -65, -44, -102, 126, -59, 57, 103, -2, 118, -99, 67, -89, -31, -48, -11, 104, -14, Ascii.ESC, 52, 112, 5, -93, -118, -43, 121, -122, -88, 48, -58, 81, 75, Ascii.f1127RS, -90, 39, -10, 53, -46, 110, 36, Ascii.SYN, -126, 95, -38, -26, 117, -94, -17, 44, -78, Ascii.f1122FS, -97, 93, 111, Byte.MIN_VALUE, 10, 114, 68, -101, 108, -112, Ascii.f1132VT, 91, 51, 125, 90, 82, -13, 97, -95, -9, -80, -42, Utf8.REPLACEMENT_BYTE, 124, 109, -19, Ascii.DC4, -32, -91, 61, 34, -77, -8, -119, -34, 113, Ascii.SUB, -81, -70, -75, -127};
    private static final byte[] SB3_sbox = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, Ascii.f1132VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f1127RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.f1128SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f1122FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f1123GS, 41, -59, -119, 111, -73, 98, Ascii.f1129SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.f1131US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.f1120EM, -75, 74, 13, MultipartStream.DASH, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.f1121FF, 125};
    private static final byte[] SB4_sbox = {48, 104, -103, Ascii.ESC, -121, -71, 33, 120, 80, 57, -37, -31, 114, 9, 98, 60, 62, 126, 94, -114, -15, -96, -52, -93, 42, Ascii.f1123GS, -5, -74, -42, 32, -60, -115, -127, 101, -11, -119, -53, -99, 119, -58, 87, 67, 86, Ascii.ETB, -44, SignedBytes.MAX_POWER_OF_TWO, Ascii.SUB, 77, -64, 99, 108, -29, -73, -56, 100, 106, 83, -86, 56, -104, Ascii.f1121FF, -12, -101, -19, Byte.MAX_VALUE, 34, 118, -81, -35, 58, Ascii.f1132VT, 88, 103, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, 95, 2, 36, 117, -109, 102, Ascii.f1127RS, -27, -30, 84, -40, 16, -50, 122, -24, 8, 44, Ascii.DC2, -105, 50, -85, -76, 39, 10, 35, -33, -17, -54, -39, -72, -6, -36, 49, 107, -47, -83, Ascii.f1120EM, 73, -67, 81, -106, -18, -28, -88, 65, -38, -1, -51, 85, -122, 54, -66, 97, 82, -8, -69, Ascii.f1129SO, -126, 72, 105, -102, -32, 71, -98, 92, 4, 75, 52, Ascii.NAK, 121, 38, -89, -34, 41, -82, -110, -41, -124, -23, -46, -70, 93, -13, -59, -80, -65, -92, 59, 113, 68, 70, 43, -4, -21, 111, -43, -10, Ascii.DC4, -2, 124, 112, 90, 125, -3, 47, Ascii.CAN, -125, Ascii.SYN, -91, -111, Ascii.f1131US, 5, -107, 116, -87, -63, 91, 74, -123, 109, 19, 7, 79, 78, 69, -78, Ascii.f1128SI, -55, Ascii.f1122FS, -90, PSSSigner.TRAILER_IMPLICIT, -20, 115, -112, 123, -49, 89, -113, -95, -7, MultipartStream.DASH, -14, -79, 0, -108, 55, -97, -48, 46, -100, 110, 40, Utf8.REPLACEMENT_BYTE, Byte.MIN_VALUE, -16, 61, -45, 37, -118, -75, -25, 66, -77, -57, -22, -9, 76, 17, 51, 3, -94, -84, 96};
    private byte[][] roundKeys;

    /* renamed from: A */
    protected static void m70A(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        byte b7 = bArr[6];
        byte b8 = bArr[7];
        byte b9 = bArr[8];
        byte b10 = bArr[9];
        byte b11 = bArr[10];
        byte b12 = bArr[11];
        byte b13 = bArr[12];
        byte b14 = bArr[13];
        byte b15 = bArr[14];
        byte b16 = bArr[15];
        bArr[0] = (byte) ((((((b4 ^ b5) ^ b7) ^ b9) ^ b10) ^ b14) ^ b15);
        bArr[1] = (byte) ((((((b3 ^ b6) ^ b8) ^ b9) ^ b10) ^ b13) ^ b16);
        bArr[2] = (byte) ((((((b2 ^ b5) ^ b7) ^ b11) ^ b12) ^ b13) ^ b16);
        bArr[3] = (byte) ((((((b ^ b6) ^ b8) ^ b11) ^ b12) ^ b14) ^ b15);
        int r0 = b ^ b3;
        bArr[4] = (byte) (((((r0 ^ b6) ^ b9) ^ b12) ^ b15) ^ b16);
        int r2 = b2 ^ b4;
        bArr[5] = (byte) (((((r2 ^ b5) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[6] = (byte) (((((r0 ^ b8) ^ b10) ^ b11) ^ b13) ^ b14);
        bArr[7] = (byte) (((((r2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b14);
        int r02 = b ^ b2;
        bArr[8] = (byte) (((((r02 ^ b5) ^ b8) ^ b11) ^ b14) ^ b16);
        bArr[9] = (byte) (((((r02 ^ b6) ^ b7) ^ b12) ^ b13) ^ b15);
        int r03 = b3 ^ b4;
        bArr[10] = (byte) (((((r03 ^ b6) ^ b7) ^ b9) ^ b14) ^ b16);
        bArr[11] = (byte) (((((r03 ^ b5) ^ b8) ^ b10) ^ b13) ^ b15);
        int r04 = b2 ^ b3;
        bArr[12] = (byte) (((((r04 ^ b7) ^ b8) ^ b10) ^ b12) ^ b13);
        int r1 = b ^ b4;
        bArr[13] = (byte) (((((r1 ^ b7) ^ b8) ^ b9) ^ b11) ^ b14);
        bArr[14] = (byte) (((((r1 ^ b5) ^ b6) ^ b10) ^ b12) ^ b15);
        bArr[15] = (byte) (((((r04 ^ b5) ^ b6) ^ b9) ^ b11) ^ b16);
    }

    /* renamed from: FE */
    protected static void m69FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        m70A(bArr);
    }

    /* renamed from: FO */
    protected static void m68FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        m70A(bArr);
    }

    protected static byte SB1(byte b) {
        return SB1_sbox[b & 255];
    }

    protected static byte SB2(byte b) {
        return SB2_sbox[b & 255];
    }

    protected static byte SB3(byte b) {
        return SB3_sbox[b & 255];
    }

    protected static byte SB4(byte b) {
        return SB4_sbox[b & 255];
    }

    protected static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    protected static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    protected static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int r2 = (length >>> 3) - 2;
        byte[][] bArr2 = f1892C;
        byte[] bArr3 = bArr2[r2];
        byte[] bArr4 = bArr2[(r2 + 1) % 3];
        byte[] bArr5 = bArr2[(r2 + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        m68FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        m69FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        m68FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int r22 = (r2 * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance(byte.class, r22 + 1, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (r22 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (r22 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int r8 = 1; r8 < r22; r8++) {
                m70A(bArr12[r8]);
            }
        }
        return bArr12;
    }

    protected static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int r9) {
        int r0 = r9 >>> 3;
        int r92 = r9 & 7;
        int r1 = 8 - r92;
        int r2 = bArr3[15 - r0] & 255;
        int r3 = 0;
        while (r3 < 16) {
            int r4 = bArr3[(r3 - r0) & 15] & 255;
            bArr[r3] = (byte) (((r2 << r1) | (r4 >>> r92)) ^ (bArr2[r3] & 255));
            r3++;
            r2 = r4;
        }
    }

    protected static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int r1 = length / 2;
        int r0 = length - 1;
        for (int r2 = 0; r2 < r1; r2++) {
            byte[] bArr2 = bArr[r2];
            int r4 = r0 - r2;
            bArr[r2] = bArr[r4];
            bArr[r4] = bArr2;
        }
    }

    protected static void xor(byte[] bArr, byte[] bArr2) {
        for (int r0 = 0; r0 < 16; r0++) {
            bArr[r0] = (byte) (bArr[r0] ^ bArr2[r0]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.roundKeys = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r7, byte[] bArr2, int r9) throws DataLengthException, IllegalStateException {
        if (this.roundKeys != null) {
            if (r7 <= bArr.length - 16) {
                if (r9 <= bArr2.length - 16) {
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(bArr, r7, bArr3, 0, 16);
                    int length = this.roundKeys.length - 3;
                    int r72 = 0;
                    while (r72 < length) {
                        int r4 = r72 + 1;
                        m68FO(bArr3, this.roundKeys[r72]);
                        m69FE(bArr3, this.roundKeys[r4]);
                        r72 = r4 + 1;
                    }
                    int r3 = r72 + 1;
                    m68FO(bArr3, this.roundKeys[r72]);
                    xor(bArr3, this.roundKeys[r3]);
                    SL2(bArr3);
                    xor(bArr3, this.roundKeys[r3 + 1]);
                    System.arraycopy(bArr3, 0, bArr2, r9, 16);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("ARIA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
