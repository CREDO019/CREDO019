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
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: X */
    private final int[] f1960X = new int[4];

    /* renamed from: rk */
    private int[] f1961rk;
    private static final byte[] Sbox = {-42, -112, -23, -2, -52, -31, 61, -73, Ascii.SYN, -74, Ascii.DC4, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, Ascii.f1132VT, 67, -19, -49, -84, 98, -28, -77, Ascii.f1122FS, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, Utf8.REPLACEMENT_BYTE, -90, 71, 7, -89, -4, -13, 115, Ascii.ETB, -70, -125, 89, 60, Ascii.f1120EM, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, Ascii.f1128SI, 75, 112, 86, -99, 53, Ascii.f1127RS, 36, Ascii.f1129SO, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, SignedBytes.MAX_POWER_OF_TWO, -57, 56, -75, -93, -9, -14, -50, -7, 97, Ascii.NAK, -95, -32, -82, 93, -92, -101, 52, Ascii.SUB, 85, -83, -109, 50, 48, -11, -116, -79, -29, Ascii.f1123GS, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, 13, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, Ascii.ESC, -81, -110, -69, -35, PSSSigner.TRAILER_IMPLICIT, Byte.MAX_VALUE, 17, -39, 92, 65, Ascii.f1131US, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, MultipartStream.DASH, 116, -48, Ascii.DC2, -72, -27, -76, -80, -119, 105, -105, 74, Ascii.f1121FF, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, Ascii.CAN, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};

    /* renamed from: CK */
    private static final int[] f1958CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    /* renamed from: FK */
    private static final int[] f1959FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    /* renamed from: F0 */
    private int m48F0(int[] r4, int r5) {
        return m43T((r4[3] ^ (r4[1] ^ r4[2])) ^ r5) ^ r4[0];
    }

    /* renamed from: F1 */
    private int m47F1(int[] r4, int r5) {
        return m43T((r4[0] ^ (r4[2] ^ r4[3])) ^ r5) ^ r4[1];
    }

    /* renamed from: F2 */
    private int m46F2(int[] r4, int r5) {
        return m43T((r4[1] ^ (r4[3] ^ r4[0])) ^ r5) ^ r4[2];
    }

    /* renamed from: F3 */
    private int m45F3(int[] r4, int r5) {
        return m43T((r4[2] ^ (r4[0] ^ r4[1])) ^ r5) ^ r4[3];
    }

    /* renamed from: L */
    private int m44L(int r3) {
        return rotateLeft(r3, 24) ^ (((rotateLeft(r3, 2) ^ r3) ^ rotateLeft(r3, 10)) ^ rotateLeft(r3, 18));
    }

    private int L_ap(int r3) {
        return rotateLeft(r3, 23) ^ (rotateLeft(r3, 13) ^ r3);
    }

    /* renamed from: T */
    private int m43T(int r1) {
        return m44L(tau(r1));
    }

    private int T_ap(int r1) {
        return L_ap(tau(r1));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] r1 = new int[32];
        int[] r3 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
        int r8 = r3[0];
        int[] r9 = f1959FK;
        int[] r13 = {r8 ^ r9[0], r3[1] ^ r9[1], r3[2] ^ r9[2], r3[3] ^ r9[3]};
        if (z) {
            int r12 = r13[0];
            int r32 = (r13[1] ^ r13[2]) ^ r13[3];
            int[] r82 = f1958CK;
            r1[0] = r12 ^ T_ap(r32 ^ r82[0]);
            r1[1] = r13[1] ^ T_ap(((r13[2] ^ r13[3]) ^ r1[0]) ^ r82[1]);
            r1[2] = r13[2] ^ T_ap(((r13[3] ^ r1[0]) ^ r1[1]) ^ r82[2]);
            r1[3] = r13[3] ^ T_ap(((r1[0] ^ r1[1]) ^ r1[2]) ^ r82[3]);
            for (int r2 = 4; r2 < 32; r2++) {
                r1[r2] = r1[r2 - 4] ^ T_ap(((r1[r2 - 3] ^ r1[r2 - 2]) ^ r1[r2 - 1]) ^ f1958CK[r2]);
            }
        } else {
            int r122 = r13[0];
            int r0 = (r13[1] ^ r13[2]) ^ r13[3];
            int[] r22 = f1958CK;
            r1[31] = r122 ^ T_ap(r0 ^ r22[0]);
            r1[30] = r13[1] ^ T_ap(((r13[2] ^ r13[3]) ^ r1[31]) ^ r22[1]);
            r1[29] = r13[2] ^ T_ap(((r13[3] ^ r1[31]) ^ r1[30]) ^ r22[2]);
            r1[28] = r13[3] ^ T_ap(((r1[31] ^ r1[30]) ^ r1[29]) ^ r22[3]);
            for (int r123 = 27; r123 >= 0; r123--) {
                r1[r123] = r1[r123 + 4] ^ T_ap(((r1[r123 + 3] ^ r1[r123 + 2]) ^ r1[r123 + 1]) ^ f1958CK[31 - r123]);
            }
        }
        return r1;
    }

    private int rotateLeft(int r2, int r3) {
        return (r2 >>> (-r3)) | (r2 << r3);
    }

    private int tau(int r5) {
        byte[] bArr = Sbox;
        return (bArr[r5 & 255] & 255) | ((bArr[(r5 >> 24) & 255] & 255) << 24) | ((bArr[(r5 >> 16) & 255] & 255) << 16) | ((bArr[(r5 >> 8) & 255] & 255) << 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.f1961rk = expandKey(z, key);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        if (this.f1961rk != null) {
            if (r8 + 16 <= bArr.length) {
                if (r10 + 16 <= bArr2.length) {
                    this.f1960X[0] = Pack.bigEndianToInt(bArr, r8);
                    this.f1960X[1] = Pack.bigEndianToInt(bArr, r8 + 4);
                    this.f1960X[2] = Pack.bigEndianToInt(bArr, r8 + 8);
                    this.f1960X[3] = Pack.bigEndianToInt(bArr, r8 + 12);
                    for (int r7 = 0; r7 < 32; r7 += 4) {
                        int[] r0 = this.f1960X;
                        r0[0] = m48F0(r0, this.f1961rk[r7]);
                        int[] r02 = this.f1960X;
                        r02[1] = m47F1(r02, this.f1961rk[r7 + 1]);
                        int[] r03 = this.f1960X;
                        r03[2] = m46F2(r03, this.f1961rk[r7 + 2]);
                        int[] r04 = this.f1960X;
                        r04[3] = m45F3(r04, this.f1961rk[r7 + 3]);
                    }
                    Pack.intToBigEndian(this.f1960X[3], bArr2, r10);
                    Pack.intToBigEndian(this.f1960X[2], bArr2, r10 + 4);
                    Pack.intToBigEndian(this.f1960X[1], bArr2, r10 + 8);
                    Pack.intToBigEndian(this.f1960X[0], bArr2, r10 + 12);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("SM4 not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
