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

/* loaded from: classes5.dex */
public class CamelliaLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int MASK8 = 255;
    private boolean _keyis128;
    private boolean initialized;
    private static final int[] SIGMA = {-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    private static final byte[] SBOX1 = {112, -126, 44, -20, -77, 39, -64, -27, -28, -123, 87, 53, -22, Ascii.f1121FF, -82, 65, 35, -17, 107, -109, 69, Ascii.f1120EM, -91, 33, -19, Ascii.f1129SO, 79, 78, Ascii.f1123GS, 101, -110, -67, -122, -72, -81, -113, 124, -21, Ascii.f1131US, -50, 62, 48, -36, 95, 94, -59, Ascii.f1132VT, Ascii.SUB, -90, -31, 57, -54, -43, 71, 93, 61, -39, 1, 90, -42, 81, 86, 108, 77, -117, 13, -102, 102, -5, -52, -80, MultipartStream.DASH, 116, Ascii.DC2, 43, 32, -16, -79, -124, -103, -33, 76, -53, -62, 52, 126, 118, 5, 109, -73, -87, 49, -47, Ascii.ETB, 4, -41, Ascii.DC4, 88, 58, 97, -34, Ascii.ESC, 17, Ascii.f1122FS, 50, Ascii.f1128SI, -100, Ascii.SYN, 83, Ascii.CAN, -14, 34, -2, 68, -49, -78, -61, -75, 122, -111, 36, 8, -24, -88, 96, -4, 105, 80, -86, -48, -96, 125, -95, -119, 98, -105, 84, 91, Ascii.f1127RS, -107, -32, -1, 100, -46, 16, -60, 0, 72, -93, -9, 117, -37, -118, 3, -26, -38, 9, Utf8.REPLACEMENT_BYTE, -35, -108, -121, 92, -125, 2, -51, 74, -112, 51, 115, 103, -10, -13, -99, Byte.MAX_VALUE, -65, -30, 82, -101, -40, 38, -56, 55, -58, 59, -127, -106, 111, 75, 19, -66, 99, 46, -23, 121, -89, -116, -97, 110, PSSSigner.TRAILER_IMPLICIT, -114, 41, -11, -7, -74, 47, -3, -76, 89, 120, -104, 6, 106, -25, 70, 113, -70, -44, 37, -85, 66, -120, -94, -115, -6, 114, 7, -71, 85, -8, -18, -84, 10, 54, 73, 42, 104, 60, 56, -15, -92, SignedBytes.MAX_POWER_OF_TWO, 40, -45, 123, -69, -55, 67, -63, Ascii.NAK, -29, -83, -12, 119, -57, Byte.MIN_VALUE, -98};
    private int[] subkey = new int[96];

    /* renamed from: kw */
    private int[] f1910kw = new int[8];

    /* renamed from: ke */
    private int[] f1909ke = new int[12];
    private int[] state = new int[4];

    private int bytes2int(byte[] bArr, int r5) {
        int r1 = 0;
        for (int r0 = 0; r0 < 4; r0++) {
            r1 = (r1 << 8) + (bArr[r0 + r5] & 255);
        }
        return r1;
    }

    private void camelliaF2(int[] r10, int[] r11, int r12) {
        int r1 = r10[0] ^ r11[r12 + 0];
        int sbox4 = sbox4(r1 & 255) | (sbox3((r1 >>> 8) & 255) << 8) | (sbox2((r1 >>> 16) & 255) << 16);
        byte[] bArr = SBOX1;
        int r6 = r10[1] ^ r11[r12 + 1];
        int leftRotate = leftRotate((sbox2((r6 >>> 24) & 255) << 24) | (bArr[r6 & 255] & 255) | (sbox4((r6 >>> 8) & 255) << 8) | (sbox3((r6 >>> 16) & 255) << 16), 8);
        int r13 = (((bArr[(r1 >>> 24) & 255] & 255) << 24) | sbox4) ^ leftRotate;
        int leftRotate2 = leftRotate(leftRotate, 8) ^ r13;
        int rightRotate = rightRotate(r13, 8) ^ leftRotate2;
        r10[2] = (leftRotate(leftRotate2, 16) ^ rightRotate) ^ r10[2];
        r10[3] = leftRotate(rightRotate, 8) ^ r10[3];
        int r14 = r10[2] ^ r11[r12 + 2];
        int sbox42 = ((bArr[(r14 >>> 24) & 255] & 255) << 24) | sbox4(r14 & 255) | (sbox3((r14 >>> 8) & 255) << 8) | (sbox2((r14 >>> 16) & 255) << 16);
        int r112 = r11[r12 + 3] ^ r10[3];
        int leftRotate3 = leftRotate((sbox2((r112 >>> 24) & 255) << 24) | (bArr[r112 & 255] & 255) | (sbox4((r112 >>> 8) & 255) << 8) | (sbox3((r112 >>> 16) & 255) << 16), 8);
        int r122 = sbox42 ^ leftRotate3;
        int leftRotate4 = leftRotate(leftRotate3, 8) ^ r122;
        int rightRotate2 = rightRotate(r122, 8) ^ leftRotate4;
        r10[0] = (leftRotate(leftRotate4, 16) ^ rightRotate2) ^ r10[0];
        r10[1] = r10[1] ^ leftRotate(rightRotate2, 8);
    }

    private void camelliaFLs(int[] r7, int[] r8, int r9) {
        r7[1] = r7[1] ^ leftRotate(r7[0] & r8[r9 + 0], 1);
        r7[0] = r7[0] ^ (r8[r9 + 1] | r7[1]);
        r7[2] = r7[2] ^ (r8[r9 + 3] | r7[3]);
        r7[3] = leftRotate(r8[r9 + 2] & r7[2], 1) ^ r7[3];
    }

    private static void decroldq(int r9, int[] r10, int r11, int[] r12, int r13) {
        int r0 = r13 + 2;
        int r1 = r11 + 0;
        int r3 = r11 + 1;
        int r5 = 32 - r9;
        r12[r0] = (r10[r1] << r9) | (r10[r3] >>> r5);
        int r2 = r13 + 3;
        int r6 = r11 + 2;
        r12[r2] = (r10[r3] << r9) | (r10[r6] >>> r5);
        int r4 = r13 + 0;
        int r112 = r11 + 3;
        r12[r4] = (r10[r6] << r9) | (r10[r112] >>> r5);
        int r132 = r13 + 1;
        r12[r132] = (r10[r112] << r9) | (r10[r1] >>> r5);
        r10[r1] = r12[r0];
        r10[r3] = r12[r2];
        r10[r6] = r12[r4];
        r10[r112] = r12[r132];
    }

    private static void decroldqo32(int r9, int[] r10, int r11, int[] r12, int r13) {
        int r0 = r13 + 2;
        int r1 = r11 + 1;
        int r3 = r9 - 32;
        int r4 = r11 + 2;
        int r92 = 64 - r9;
        r12[r0] = (r10[r1] << r3) | (r10[r4] >>> r92);
        int r2 = r13 + 3;
        int r6 = r11 + 3;
        r12[r2] = (r10[r4] << r3) | (r10[r6] >>> r92);
        int r5 = r13 + 0;
        int r112 = r11 + 0;
        r12[r5] = (r10[r6] << r3) | (r10[r112] >>> r92);
        int r132 = r13 + 1;
        r12[r132] = (r10[r1] >>> r92) | (r10[r112] << r3);
        r10[r112] = r12[r0];
        r10[r1] = r12[r2];
        r10[r4] = r12[r5];
        r10[r6] = r12[r132];
    }

    private void int2bytes(int r4, byte[] bArr, int r6) {
        for (int r0 = 0; r0 < 4; r0++) {
            bArr[(3 - r0) + r6] = (byte) r4;
            r4 >>>= 8;
        }
    }

    private byte lRot8(byte b, int r3) {
        return (byte) (((b & 255) >>> (8 - r3)) | (b << r3));
    }

    private static int leftRotate(int r1, int r2) {
        return (r1 << r2) + (r1 >>> (32 - r2));
    }

    private int processBlock128(byte[] bArr, int r10, byte[] bArr2, int r12) {
        for (int r1 = 0; r1 < 4; r1++) {
            this.state[r1] = bytes2int(bArr, (r1 * 4) + r10);
            int[] r2 = this.state;
            r2[r1] = r2[r1] ^ this.f1910kw[r1];
        }
        camelliaF2(this.state, this.subkey, 0);
        camelliaF2(this.state, this.subkey, 4);
        camelliaF2(this.state, this.subkey, 8);
        camelliaFLs(this.state, this.f1909ke, 0);
        camelliaF2(this.state, this.subkey, 12);
        camelliaF2(this.state, this.subkey, 16);
        camelliaF2(this.state, this.subkey, 20);
        camelliaFLs(this.state, this.f1909ke, 4);
        camelliaF2(this.state, this.subkey, 24);
        camelliaF2(this.state, this.subkey, 28);
        camelliaF2(this.state, this.subkey, 32);
        int[] r9 = this.state;
        int r4 = r9[2];
        int[] r5 = this.f1910kw;
        r9[2] = r5[4] ^ r4;
        r9[3] = r9[3] ^ r5[5];
        r9[0] = r9[0] ^ r5[6];
        r9[1] = r5[7] ^ r9[1];
        int2bytes(r9[2], bArr2, r12);
        int2bytes(this.state[3], bArr2, r12 + 4);
        int2bytes(this.state[0], bArr2, r12 + 8);
        int2bytes(this.state[1], bArr2, r12 + 12);
        return 16;
    }

    private int processBlock192or256(byte[] bArr, int r10, byte[] bArr2, int r12) {
        for (int r1 = 0; r1 < 4; r1++) {
            this.state[r1] = bytes2int(bArr, (r1 * 4) + r10);
            int[] r2 = this.state;
            r2[r1] = r2[r1] ^ this.f1910kw[r1];
        }
        camelliaF2(this.state, this.subkey, 0);
        camelliaF2(this.state, this.subkey, 4);
        camelliaF2(this.state, this.subkey, 8);
        camelliaFLs(this.state, this.f1909ke, 0);
        camelliaF2(this.state, this.subkey, 12);
        camelliaF2(this.state, this.subkey, 16);
        camelliaF2(this.state, this.subkey, 20);
        camelliaFLs(this.state, this.f1909ke, 4);
        camelliaF2(this.state, this.subkey, 24);
        camelliaF2(this.state, this.subkey, 28);
        camelliaF2(this.state, this.subkey, 32);
        camelliaFLs(this.state, this.f1909ke, 8);
        camelliaF2(this.state, this.subkey, 36);
        camelliaF2(this.state, this.subkey, 40);
        camelliaF2(this.state, this.subkey, 44);
        int[] r9 = this.state;
        int r13 = r9[2];
        int[] r5 = this.f1910kw;
        r9[2] = r13 ^ r5[4];
        r9[3] = r9[3] ^ r5[5];
        r9[0] = r9[0] ^ r5[6];
        r9[1] = r5[7] ^ r9[1];
        int2bytes(r9[2], bArr2, r12);
        int2bytes(this.state[3], bArr2, r12 + 4);
        int2bytes(this.state[0], bArr2, r12 + 8);
        int2bytes(this.state[1], bArr2, r12 + 12);
        return 16;
    }

    private static int rightRotate(int r1, int r2) {
        return (r1 >>> r2) + (r1 << (32 - r2));
    }

    private static void roldq(int r9, int[] r10, int r11, int[] r12, int r13) {
        int r0 = r13 + 0;
        int r1 = r11 + 0;
        int r3 = r11 + 1;
        int r5 = 32 - r9;
        r12[r0] = (r10[r1] << r9) | (r10[r3] >>> r5);
        int r2 = r13 + 1;
        int r6 = r11 + 2;
        r12[r2] = (r10[r3] << r9) | (r10[r6] >>> r5);
        int r4 = r13 + 2;
        int r112 = r11 + 3;
        r12[r4] = (r10[r6] << r9) | (r10[r112] >>> r5);
        int r132 = r13 + 3;
        r12[r132] = (r10[r112] << r9) | (r10[r1] >>> r5);
        r10[r1] = r12[r0];
        r10[r3] = r12[r2];
        r10[r6] = r12[r4];
        r10[r112] = r12[r132];
    }

    private static void roldqo32(int r9, int[] r10, int r11, int[] r12, int r13) {
        int r0 = r13 + 0;
        int r1 = r11 + 1;
        int r3 = r9 - 32;
        int r4 = r11 + 2;
        int r92 = 64 - r9;
        r12[r0] = (r10[r1] << r3) | (r10[r4] >>> r92);
        int r2 = r13 + 1;
        int r6 = r11 + 3;
        r12[r2] = (r10[r4] << r3) | (r10[r6] >>> r92);
        int r5 = r13 + 2;
        int r112 = r11 + 0;
        r12[r5] = (r10[r6] << r3) | (r10[r112] >>> r92);
        int r132 = r13 + 3;
        r12[r132] = (r10[r1] >>> r92) | (r10[r112] << r3);
        r10[r112] = r12[r0];
        r10[r1] = r12[r2];
        r10[r4] = r12[r5];
        r10[r6] = r12[r132];
    }

    private int sbox2(int r2) {
        return lRot8(SBOX1[r2], 1) & 255;
    }

    private int sbox3(int r2) {
        return lRot8(SBOX1[r2], 7) & 255;
    }

    private int sbox4(int r3) {
        return SBOX1[lRot8((byte) r3, 1) & 255] & 255;
    }

    private void setKey(boolean z, byte[] bArr) {
        int[] r3 = new int[8];
        int[] r5 = new int[4];
        int[] r6 = new int[4];
        int[] r7 = new int[4];
        int length = bArr.length;
        if (length != 16) {
            if (length == 24) {
                r3[0] = bytes2int(bArr, 0);
                r3[1] = bytes2int(bArr, 4);
                r3[2] = bytes2int(bArr, 8);
                r3[3] = bytes2int(bArr, 12);
                r3[4] = bytes2int(bArr, 16);
                r3[5] = bytes2int(bArr, 20);
                r3[6] = ~r3[4];
                r3[7] = ~r3[5];
            } else if (length != 32) {
                throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
            } else {
                r3[0] = bytes2int(bArr, 0);
                r3[1] = bytes2int(bArr, 4);
                r3[2] = bytes2int(bArr, 8);
                r3[3] = bytes2int(bArr, 12);
                r3[4] = bytes2int(bArr, 16);
                r3[5] = bytes2int(bArr, 20);
                r3[6] = bytes2int(bArr, 24);
                r3[7] = bytes2int(bArr, 28);
            }
            this._keyis128 = false;
        } else {
            this._keyis128 = true;
            r3[0] = bytes2int(bArr, 0);
            r3[1] = bytes2int(bArr, 4);
            r3[2] = bytes2int(bArr, 8);
            r3[3] = bytes2int(bArr, 12);
            r3[7] = 0;
            r3[6] = 0;
            r3[5] = 0;
            r3[4] = 0;
        }
        for (int r1 = 0; r1 < 4; r1++) {
            r5[r1] = r3[r1] ^ r3[r1 + 4];
        }
        camelliaF2(r5, SIGMA, 0);
        for (int r12 = 0; r12 < 4; r12++) {
            r5[r12] = r5[r12] ^ r3[r12];
        }
        camelliaF2(r5, SIGMA, 4);
        if (this._keyis128) {
            int[] r2 = this.f1910kw;
            if (z) {
                r2[0] = r3[0];
                r2[1] = r3[1];
                r2[2] = r3[2];
                r2[3] = r3[3];
                roldq(15, r3, 0, this.subkey, 4);
                roldq(30, r3, 0, this.subkey, 12);
                roldq(15, r3, 0, r7, 0);
                int[] r22 = this.subkey;
                r22[18] = r7[2];
                r22[19] = r7[3];
                roldq(17, r3, 0, this.f1909ke, 4);
                roldq(17, r3, 0, this.subkey, 24);
                roldq(17, r3, 0, this.subkey, 32);
                int[] r13 = this.subkey;
                r13[0] = r5[0];
                r13[1] = r5[1];
                r13[2] = r5[2];
                r13[3] = r5[3];
                roldq(15, r5, 0, r13, 8);
                roldq(15, r5, 0, this.f1909ke, 0);
                roldq(15, r5, 0, r7, 0);
                int[] r14 = this.subkey;
                r14[16] = r7[0];
                r14[17] = r7[1];
                roldq(15, r5, 0, r14, 20);
                roldqo32(34, r5, 0, this.subkey, 28);
                roldq(17, r5, 0, this.f1910kw, 4);
                return;
            }
            r2[4] = r3[0];
            r2[5] = r3[1];
            r2[6] = r3[2];
            r2[7] = r3[3];
            decroldq(15, r3, 0, this.subkey, 28);
            decroldq(30, r3, 0, this.subkey, 20);
            decroldq(15, r3, 0, r7, 0);
            int[] r23 = this.subkey;
            r23[16] = r7[0];
            r23[17] = r7[1];
            decroldq(17, r3, 0, this.f1909ke, 0);
            decroldq(17, r3, 0, this.subkey, 8);
            decroldq(17, r3, 0, this.subkey, 0);
            int[] r24 = this.subkey;
            r24[34] = r5[0];
            r24[35] = r5[1];
            r24[32] = r5[2];
            r24[33] = r5[3];
            decroldq(15, r5, 0, r24, 24);
            decroldq(15, r5, 0, this.f1909ke, 4);
            decroldq(15, r5, 0, r7, 0);
            int[] r25 = this.subkey;
            r25[18] = r7[2];
            r25[19] = r7[3];
            decroldq(15, r5, 0, r25, 12);
            decroldqo32(34, r5, 0, this.subkey, 4);
            roldq(17, r5, 0, this.f1910kw, 0);
            return;
        }
        for (int r15 = 0; r15 < 4; r15++) {
            r6[r15] = r5[r15] ^ r3[r15 + 4];
        }
        camelliaF2(r6, SIGMA, 8);
        int[] r26 = this.f1910kw;
        if (z) {
            r26[0] = r3[0];
            r26[1] = r3[1];
            r26[2] = r3[2];
            r26[3] = r3[3];
            roldqo32(45, r3, 0, this.subkey, 16);
            roldq(15, r3, 0, this.f1909ke, 4);
            roldq(17, r3, 0, this.subkey, 32);
            roldqo32(34, r3, 0, this.subkey, 44);
            roldq(15, r3, 4, this.subkey, 4);
            roldq(15, r3, 4, this.f1909ke, 0);
            roldq(30, r3, 4, this.subkey, 24);
            roldqo32(34, r3, 4, this.subkey, 36);
            roldq(15, r5, 0, this.subkey, 8);
            roldq(30, r5, 0, this.subkey, 20);
            int[] r16 = this.f1909ke;
            r16[8] = r5[1];
            r16[9] = r5[2];
            r16[10] = r5[3];
            r16[11] = r5[0];
            roldqo32(49, r5, 0, this.subkey, 40);
            int[] r17 = this.subkey;
            r17[0] = r6[0];
            r17[1] = r6[1];
            r17[2] = r6[2];
            r17[3] = r6[3];
            roldq(30, r6, 0, r17, 12);
            roldq(30, r6, 0, this.subkey, 28);
            roldqo32(51, r6, 0, this.f1910kw, 4);
            return;
        }
        r26[4] = r3[0];
        r26[5] = r3[1];
        r26[6] = r3[2];
        r26[7] = r3[3];
        decroldqo32(45, r3, 0, this.subkey, 28);
        decroldq(15, r3, 0, this.f1909ke, 4);
        decroldq(17, r3, 0, this.subkey, 12);
        decroldqo32(34, r3, 0, this.subkey, 0);
        decroldq(15, r3, 4, this.subkey, 40);
        decroldq(15, r3, 4, this.f1909ke, 8);
        decroldq(30, r3, 4, this.subkey, 20);
        decroldqo32(34, r3, 4, this.subkey, 8);
        decroldq(15, r5, 0, this.subkey, 36);
        decroldq(30, r5, 0, this.subkey, 24);
        int[] r27 = this.f1909ke;
        r27[2] = r5[1];
        r27[3] = r5[2];
        r27[0] = r5[3];
        r27[1] = r5[0];
        decroldqo32(49, r5, 0, this.subkey, 4);
        int[] r28 = this.subkey;
        r28[46] = r6[0];
        r28[47] = r6[1];
        r28[44] = r6[2];
        r28[45] = r6[3];
        decroldq(30, r6, 0, r28, 32);
        decroldq(30, r6, 0, this.subkey, 16);
        roldqo32(51, r6, 0, this.f1910kw, 0);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Camellia";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        setKey(z, ((KeyParameter) cipherParameters).getKey());
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) throws IllegalStateException {
        if (this.initialized) {
            if (r4 + 16 <= bArr.length) {
                if (r6 + 16 <= bArr2.length) {
                    return this._keyis128 ? processBlock128(bArr, r4, bArr2, r6) : processBlock192or256(bArr, r4, bArr2, r6);
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Camellia is not initialized");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
