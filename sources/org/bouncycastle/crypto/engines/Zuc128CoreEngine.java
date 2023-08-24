package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.UShort;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;

    /* renamed from: F */
    private final int[] f1983F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;

    /* renamed from: S0 */
    private static final byte[] f1981S0 = {62, 114, 91, 71, -54, -32, 0, 51, 4, -47, 84, -104, 9, -71, 109, -53, 123, Ascii.ESC, -7, 50, -81, -99, 106, -91, -72, MultipartStream.DASH, -4, Ascii.f1123GS, 8, 83, 3, -112, 77, 78, -124, -103, -28, -50, -39, -111, -35, -74, -123, 72, -117, 41, 110, -84, -51, -63, -8, Ascii.f1127RS, 115, 67, 105, -58, -75, -67, -3, 57, 99, 32, -44, 56, 118, 125, -78, -89, -49, -19, 87, -59, -13, 44, -69, Ascii.DC4, 33, 6, 85, -101, -29, -17, 94, 49, 79, Byte.MAX_VALUE, 90, -92, 13, -126, 81, 73, 95, -70, 88, Ascii.f1122FS, 74, Ascii.SYN, -43, Ascii.ETB, -88, -110, 36, Ascii.f1131US, -116, -1, -40, -82, 46, 1, -45, -83, 59, 75, -38, 70, -21, -55, -34, -102, -113, -121, -41, 58, Byte.MIN_VALUE, 111, 47, -56, -79, -76, 55, -9, 10, 34, 19, 40, 124, -52, 60, -119, -57, -61, -106, 86, 7, -65, 126, -16, Ascii.f1132VT, 43, -105, 82, 53, 65, 121, 97, -90, 76, 16, -2, PSSSigner.TRAILER_IMPLICIT, 38, -107, -120, -118, -80, -93, -5, -64, Ascii.CAN, -108, -14, -31, -27, -23, 93, -48, -36, 17, 102, 100, 92, -20, 89, 66, 117, Ascii.DC2, -11, 116, -100, -86, 35, Ascii.f1129SO, -122, -85, -66, 42, 2, -25, 103, -26, 68, -94, 108, -62, -109, -97, -15, -10, -6, 54, -46, 80, 104, -98, 98, 113, Ascii.NAK, 61, -42, SignedBytes.MAX_POWER_OF_TWO, -60, -30, Ascii.f1128SI, -114, -125, 119, 107, 37, 5, Utf8.REPLACEMENT_BYTE, Ascii.f1121FF, 48, -22, 112, -73, -95, -24, -87, 101, -115, 39, Ascii.SUB, -37, -127, -77, -96, -12, 69, 122, Ascii.f1120EM, -33, -18, 120, 52, 96};

    /* renamed from: S1 */
    private static final byte[] f1982S1 = {85, -62, 99, 113, 59, -56, 71, -122, -97, 60, -38, 91, 41, -86, -3, 119, -116, -59, -108, Ascii.f1121FF, -90, Ascii.SUB, 19, 0, -29, -88, Ascii.SYN, 114, SignedBytes.MAX_POWER_OF_TWO, -7, -8, 66, 68, 38, 104, -106, -127, -39, 69, 62, 16, 118, -58, -89, -117, 57, 67, -31, 58, -75, 86, 42, -64, 109, -77, 5, 34, 102, -65, -36, Ascii.f1132VT, -6, 98, 72, -35, 32, 17, 6, 54, -55, -63, -49, -10, 39, 82, -69, 105, -11, -44, -121, Byte.MAX_VALUE, -124, 76, -46, -100, 87, -92, PSSSigner.TRAILER_IMPLICIT, 79, -102, -33, -2, -42, -115, 122, -21, 43, 83, -40, 92, -95, Ascii.DC4, Ascii.ETB, -5, 35, -43, 125, 48, 103, 115, 8, 9, -18, -73, 112, Utf8.REPLACEMENT_BYTE, 97, -78, Ascii.f1120EM, -114, 78, -27, 75, -109, -113, 93, -37, -87, -83, -15, -82, 46, -53, 13, -4, -12, MultipartStream.DASH, 70, 110, Ascii.f1123GS, -105, -24, -47, -23, 77, 55, -91, 117, 94, -125, -98, -85, -126, -99, -71, Ascii.f1122FS, -32, -51, 73, -119, 1, -74, -67, 88, 36, -94, 95, 56, 120, -103, Ascii.NAK, -112, 80, -72, -107, -28, -48, -111, -57, -50, -19, Ascii.f1128SI, -76, 111, -96, -52, -16, 2, 74, 121, -61, -34, -93, -17, -22, 81, -26, 107, Ascii.CAN, -20, Ascii.ESC, 44, Byte.MIN_VALUE, -9, 116, -25, -1, 33, 90, 106, 84, Ascii.f1127RS, 65, 49, -110, 53, -60, 51, 7, 10, -70, 126, Ascii.f1129SO, 52, -120, -79, -104, 124, -13, 61, 96, 108, 123, -54, -45, Ascii.f1131US, 50, 101, 4, 40, 100, -66, -123, -101, 47, 89, -118, -41, -80, 37, -84, -81, Ascii.DC2, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.f1983F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.f1983F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int r1, int r2) {
        int r12 = r1 + r2;
        return (Integer.MAX_VALUE & r12) + (r12 >>> 31);
    }

    private void BitReorganization() {
        int[] r0 = this.BRC;
        int[] r1 = this.LFSR;
        r0[0] = ((r1[15] & 2147450880) << 1) | (r1[14] & 65535);
        r0[1] = ((r1[11] & 65535) << 16) | (r1[9] >>> 15);
        r0[2] = ((r1[7] & 65535) << 16) | (r1[5] >>> 15);
        r0[3] = (r1[0] >>> 15) | ((r1[2] & 65535) << 16);
    }

    /* renamed from: L1 */
    private static int m34L1(int r2) {
        return ROT(r2, 24) ^ (((ROT(r2, 2) ^ r2) ^ ROT(r2, 10)) ^ ROT(r2, 18));
    }

    /* renamed from: L2 */
    private static int m33L2(int r2) {
        return ROT(r2, 30) ^ (((ROT(r2, 8) ^ r2) ^ ROT(r2, 14)) ^ ROT(r2, 22));
    }

    private void LFSRWithInitialisationMode(int r10) {
        int[] r0 = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(AddM(r0[0], MulByPow2(r0[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), r10);
        int[] r02 = this.LFSR;
        r02[0] = r02[1];
        r02[1] = r02[2];
        r02[2] = r02[3];
        r02[3] = r02[4];
        r02[4] = r02[5];
        r02[5] = r02[6];
        r02[6] = r02[7];
        r02[7] = r02[8];
        r02[8] = r02[9];
        r02[9] = r02[10];
        r02[10] = r02[11];
        r02[11] = r02[12];
        r02[12] = r02[13];
        r02[13] = r02[14];
        r02[14] = r02[15];
        r02[15] = AddM;
    }

    private void LFSRWithWorkMode() {
        int[] r0 = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(r0[0], MulByPow2(r0[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] r2 = this.LFSR;
        r2[0] = r2[1];
        r2[1] = r2[2];
        r2[2] = r2[3];
        r2[3] = r2[4];
        r2[4] = r2[5];
        r2[5] = r2[6];
        r2[6] = r2[7];
        r2[7] = r2[8];
        r2[8] = r2[9];
        r2[9] = r2[10];
        r2[10] = r2[11];
        r2[11] = r2[12];
        r2[12] = r2[13];
        r2[13] = r2[14];
        r2[14] = r2[15];
        r2[15] = AddM;
    }

    private static int MAKEU31(byte b, short s, byte b2) {
        return ((b & 255) << 23) | ((s & UShort.MAX_VALUE) << 8) | (b2 & 255);
    }

    private static int MAKEU32(byte b, byte b2, byte b3, byte b4) {
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    private static int MulByPow2(int r1, int r2) {
        return ((r1 >>> (31 - r2)) | (r1 << r2)) & Integer.MAX_VALUE;
    }

    static int ROT(int r1, int r2) {
        return (r1 >>> (32 - r2)) | (r1 << r2);
    }

    public static void encode32be(int r2, byte[] bArr, int r4) {
        bArr[r4] = (byte) (r2 >> 24);
        bArr[r4 + 1] = (byte) (r2 >> 16);
        bArr[r4 + 2] = (byte) (r2 >> 8);
        bArr[r4 + 3] = (byte) r2;
    }

    private void makeKeyStream() {
        encode32be(makeKeyStreamWord(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        setKeyAndIV(this.LFSR, bArr, bArr2);
        int[] r2 = this.f1983F;
        r2[0] = 0;
        r2[1] = 0;
        int r22 = 32;
        while (true) {
            BitReorganization();
            if (r22 <= 0) {
                m35F();
                LFSRWithWorkMode();
                return;
            }
            LFSRWithInitialisationMode(m35F() >>> 1);
            r22--;
        }
    }

    /* renamed from: F */
    int m35F() {
        int[] r0 = this.BRC;
        int r2 = r0[0];
        int[] r3 = this.f1983F;
        int r22 = (r2 ^ r3[0]) + r3[1];
        int r5 = r3[0] + r0[1];
        int r02 = r0[2] ^ r3[1];
        int m34L1 = m34L1((r5 << 16) | (r02 >>> 16));
        int m33L2 = m33L2((r02 << 16) | (r5 >>> 16));
        int[] r52 = this.f1983F;
        byte[] bArr = f1981S0;
        byte b = bArr[m34L1 >>> 24];
        byte[] bArr2 = f1982S1;
        r52[0] = MAKEU32(b, bArr2[(m34L1 >>> 16) & 255], bArr[(m34L1 >>> 8) & 255], bArr2[m34L1 & 255]);
        this.f1983F[1] = MAKEU32(bArr[m33L2 >>> 24], bArr2[(m33L2 >>> 16) & 255], bArr[(m33L2 >>> 8) & 255], bArr2[m33L2 & 255]);
        return r22;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    protected int getMaxIterations() {
        return 2047;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            bArr = null;
        }
        byte[] key = cipherParameters instanceof KeyParameter ? ((KeyParameter) cipherParameters).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, bArr);
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int makeKeyStreamWord() {
        int r0 = this.theIterations;
        this.theIterations = r0 + 1;
        if (r0 < getMaxIterations()) {
            BitReorganization();
            int m35F = m35F() ^ this.BRC[3];
            LFSRWithWorkMode();
            return m35F;
        }
        throw new IllegalStateException("Too much data processed by singleKey/IV");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r5, int r6, byte[] bArr2, int r8) {
        if (this.theResetState == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r5 + r6 <= bArr.length) {
            if (r8 + r6 <= bArr2.length) {
                for (int r0 = 0; r0 < r6; r0++) {
                    bArr2[r0 + r8] = returnByte(bArr[r0 + r5]);
                }
                return r6;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] r0 = zuc128CoreEngine.LFSR;
        int[] r1 = this.LFSR;
        System.arraycopy(r0, 0, r1, 0, r1.length);
        int[] r02 = zuc128CoreEngine.f1983F;
        int[] r12 = this.f1983F;
        System.arraycopy(r02, 0, r12, 0, r12.length);
        int[] r03 = zuc128CoreEngine.BRC;
        int[] r13 = this.BRC;
        System.arraycopy(r03, 0, r13, 0, r13.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int r1 = this.theIndex;
        byte b2 = (byte) (b ^ bArr[r1]);
        this.theIndex = (r1 + 1) % 4;
        return b2;
    }

    protected void setKeyAndIV(int[] r6, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        int[] r62 = this.LFSR;
        byte b = bArr[0];
        short[] sArr = EK_d;
        r62[0] = MAKEU31(b, sArr[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], sArr[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], sArr[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], sArr[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], sArr[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], sArr[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], sArr[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], sArr[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], sArr[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], sArr[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], sArr[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], sArr[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], sArr[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], sArr[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], sArr[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], sArr[15], bArr2[15]);
    }
}
