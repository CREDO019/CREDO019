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
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;

    /* renamed from: PI */
    private static final byte[] f1929PI = {-4, -18, -35, 17, -49, 110, 49, Ascii.SYN, -5, -60, -6, -38, 35, -59, 4, 77, -23, 119, -16, -37, -109, 46, -103, -70, Ascii.ETB, 54, -15, -69, Ascii.DC4, -51, 95, -63, -7, Ascii.CAN, 101, 90, -30, 92, -17, 33, -127, Ascii.f1122FS, 60, 66, -117, 1, -114, 79, 5, -124, 2, -82, -29, 106, -113, -96, 6, Ascii.f1132VT, -19, -104, Byte.MAX_VALUE, -44, -45, Ascii.f1131US, -21, 52, 44, 81, -22, -56, 72, -85, -14, 42, 104, -94, -3, 58, -50, -52, -75, 112, Ascii.f1129SO, 86, 8, Ascii.f1121FF, 118, Ascii.DC2, -65, 114, 19, 71, -100, -73, 93, -121, Ascii.NAK, -95, -106, 41, 16, 123, -102, -57, -13, -111, 120, 111, -99, -98, -78, -79, 50, 117, Ascii.f1120EM, 61, -1, 53, -118, 126, 109, 84, -58, Byte.MIN_VALUE, -61, -67, 13, 87, -33, -11, 36, -87, 62, -88, 67, -55, -41, 121, -42, -10, 124, 34, -71, 3, -32, Ascii.f1128SI, -20, -34, 122, -108, -80, PSSSigner.TRAILER_IMPLICIT, -36, -24, 40, 80, 78, 51, 10, 74, -89, -105, 96, 115, Ascii.f1127RS, 0, 98, 68, Ascii.SUB, -72, 56, -126, 100, -97, 38, 65, -83, 69, 70, -110, 39, 94, 85, 47, -116, -93, -91, 125, 105, -43, -107, 59, 7, 88, -77, SignedBytes.MAX_POWER_OF_TWO, -122, -84, Ascii.f1123GS, -9, 48, 55, 107, -28, -120, -39, -25, -119, -31, Ascii.ESC, -125, 73, 76, Utf8.REPLACEMENT_BYTE, -8, -2, -115, 83, -86, -112, -54, -40, -123, 97, 32, 113, 103, -92, MultipartStream.DASH, 43, 9, 91, -53, -101, 37, -48, -66, -27, 108, 82, 89, -90, 116, -46, -26, -12, -76, -64, -47, 102, -81, -62, 57, 75, 99, -74};
    private static final byte[] inversePI = {-91, MultipartStream.DASH, 50, -113, Ascii.f1129SO, 48, 56, -64, 84, -26, -98, 57, 85, 126, 82, -111, 100, 3, 87, 90, Ascii.f1122FS, 96, 7, Ascii.CAN, 33, 114, -88, -47, 41, -58, -92, Utf8.REPLACEMENT_BYTE, -32, 39, -115, Ascii.f1121FF, -126, -22, -82, -76, -102, 99, 73, -27, 66, -28, Ascii.NAK, -73, -56, 6, 112, -99, 65, 117, Ascii.f1120EM, -55, -86, -4, 77, -65, 42, 115, -124, -43, -61, -81, 43, -122, -89, -79, -78, 91, 70, -45, -97, -3, -44, Ascii.f1128SI, -100, 47, -101, 67, -17, -39, 121, -74, 83, Byte.MAX_VALUE, -63, -16, 35, -25, 37, 94, -75, Ascii.f1127RS, -94, -33, -90, -2, -84, 34, -7, -30, 74, PSSSigner.TRAILER_IMPLICIT, 53, -54, -18, 120, 5, 107, 81, -31, 89, -93, -14, 113, 86, 17, 106, -119, -108, 101, -116, -69, 119, 60, 123, 40, -85, -46, 49, -34, -60, 95, -52, -49, 118, 44, -72, -40, 46, 54, -37, 105, -77, Ascii.DC4, -107, -66, 98, -95, 59, Ascii.SYN, 102, -23, 92, 108, 109, -83, 55, 97, 75, -71, -29, -70, -15, -96, -123, -125, -38, 71, -59, -80, 51, -6, -106, 111, 110, -62, -10, 80, -1, 93, -87, -114, Ascii.ETB, Ascii.ESC, -105, 125, -20, 88, -9, Ascii.f1131US, -5, 124, 9, 13, 122, 103, 69, -121, -36, -24, 79, Ascii.f1123GS, 78, 4, -21, -8, -13, 62, 61, -67, -118, -120, -35, -51, Ascii.f1132VT, 19, -104, 2, -109, Byte.MIN_VALUE, -112, -48, 36, 52, -53, -19, -12, -50, -103, 16, 68, SignedBytes.MAX_POWER_OF_TWO, -110, 58, 1, 38, Ascii.DC2, Ascii.SUB, 72, 104, -11, -127, -117, -57, -42, 32, 10, 8, 0, 76, -41, 116};
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, 16, -62, -64, 1, -5, 1, -64, -62, 16, -123, 32, -108, 1};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = 32 / 2;
    private byte[][] subKeys = null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    /* renamed from: C */
    private void m63C(byte[] bArr, int r3) {
        Arrays.clear(bArr);
        bArr[15] = (byte) r3;
        m61L(bArr);
    }

    /* renamed from: F */
    private void m62F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] LSX = LSX(bArr, bArr2);
        m58X(LSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(LSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int r6, byte[] bArr2, int r8) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, r6, bArr4, 0, 16);
        int r62 = 9;
        if (this.forEncryption) {
            for (int r5 = 0; r5 < 9; r5++) {
                bArr4 = Arrays.copyOf(LSX(this.subKeys[r5], bArr4), 16);
            }
            m58X(bArr4, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (r62 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(XSL(bArr3[r62], bArr4), 16);
                r62--;
            }
            m58X(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, r8, 16);
    }

    /* renamed from: L */
    private void m61L(byte[] bArr) {
        for (int r0 = 0; r0 < 16; r0++) {
            m60R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        m58X(copyOf, bArr2);
        m59S(copyOf);
        m61L(copyOf);
        return copyOf;
    }

    /* renamed from: R */
    private void m60R(byte[] bArr) {
        byte m57l = m57l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = m57l;
    }

    /* renamed from: S */
    private void m59S(byte[] bArr) {
        for (int r0 = 0; r0 < bArr.length; r0++) {
            bArr[r0] = f1929PI[unsignedByte(bArr[r0])];
        }
    }

    /* renamed from: X */
    private void m58X(byte[] bArr, byte[] bArr2) {
        for (int r0 = 0; r0 < bArr.length; r0++) {
            bArr[r0] = (byte) (bArr[r0] ^ bArr2[r0]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        m58X(copyOf, bArr2);
        inverseL(copyOf);
        inverseS(copyOf);
        return copyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int r4;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10];
        for (int r2 = 0; r2 < 10; r2++) {
            this.subKeys[r2] = new byte[this.SUB_LENGTH];
        }
        int r0 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[r0];
        byte[] bArr3 = new byte[r0];
        int r3 = 0;
        while (true) {
            r4 = this.SUB_LENGTH;
            if (r3 >= r4) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[r3];
            bArr2[r3] = b;
            bArr5[r3] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[r4 + r3];
            bArr3[r3] = b2;
            bArr6[r3] = b2;
            r3++;
        }
        byte[] bArr7 = new byte[r4];
        for (int r32 = 1; r32 < 5; r32++) {
            for (int r42 = 1; r42 <= 8; r42++) {
                m63C(bArr7, ((r32 - 1) * 8) + r42);
                m62F(bArr7, bArr2, bArr3);
            }
            int r6 = r32 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[r6], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[r6 + 1], 0, this.SUB_LENGTH);
        }
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256];
        for (int r3 = 0; r3 < 256; r3++) {
            bArr[r3] = new byte[256];
            for (int r4 = 0; r4 < 256; r4++) {
                bArr[r3][r4] = kuz_mul_gf256_slow((byte) r3, (byte) r4);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int r0 = 0; r0 < 16; r0++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte m57l = m57l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = m57l;
    }

    private void inverseS(byte[] bArr) {
        for (int r0 = 0; r0 < bArr.length; r0++) {
            bArr[r0] = inversePI[unsignedByte(bArr[r0])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & 128);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    /* renamed from: l */
    private byte m57l(byte[] bArr) {
        byte b = bArr[15];
        for (int r1 = 14; r1 >= 0; r1--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[r1])][unsignedByte(this.lFactors[r1])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & 255;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters == null) {
        } else {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) throws DataLengthException, IllegalStateException {
        if (this.subKeys != null) {
            if (r4 + 16 <= bArr.length) {
                if (r6 + 16 <= bArr2.length) {
                    GOST3412_2015Func(bArr, r4, bArr2, r6);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("GOST3412_2015 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
