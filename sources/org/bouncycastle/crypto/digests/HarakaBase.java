package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes5.dex */
public abstract class HarakaBase implements Digest {
    protected static final int DIGEST_SIZE = 32;

    /* renamed from: S */
    private static final byte[][] f1757S = {new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118}, new byte[]{-54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64}, new byte[]{-73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK}, new byte[]{4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117}, new byte[]{9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124}, new byte[]{83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49}, new byte[]{-48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88}, new byte[]{81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46}, new byte[]{-51, Ascii.f1121FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f1120EM, 115}, new byte[]{96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f1132VT, -37}, new byte[]{-32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121}, new byte[]{-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8}, new byte[]{-70, 120, 37, 46, Ascii.f1122FS, -90, -76, -58, -24, -35, 116, Ascii.f1131US, 75, -67, -117, -118}, new byte[]{112, 62, -75, 102, 72, 3, -10, Ascii.f1129SO, 97, 53, 87, -71, -122, -63, Ascii.f1123GS, -98}, new byte[]{-31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f1127RS, -121, -23, -50, 85, 40, -33}, new byte[]{-116, -95, -119, 13, -65, -26, 66, 104, 65, -103, MultipartStream.DASH, Ascii.f1128SI, -80, 84, -69, Ascii.SYN}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] mixColumns = mixColumns(shiftRows(subBytes(bArr)));
        xorReverse(mixColumns, bArr2);
        return mixColumns;
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int r2 = 0;
        for (int r1 = 0; r1 < 4; r1++) {
            int r3 = r2 + 1;
            int r4 = r1 * 4;
            int r6 = r4 + 1;
            int r7 = r4 + 2;
            int r8 = r4 + 3;
            bArr2[r2] = (byte) ((((xTime(bArr[r4]) ^ xTime(bArr[r6])) ^ bArr[r6]) ^ bArr[r7]) ^ bArr[r8]);
            int r22 = r3 + 1;
            bArr2[r3] = (byte) ((((bArr[r4] ^ xTime(bArr[r6])) ^ xTime(bArr[r7])) ^ bArr[r7]) ^ bArr[r8]);
            int r32 = r22 + 1;
            bArr2[r22] = (byte) ((((bArr[r4] ^ bArr[r6]) ^ xTime(bArr[r7])) ^ xTime(bArr[r8])) ^ bArr[r8]);
            r2 = r32 + 1;
            bArr2[r32] = (byte) ((((bArr[r4] ^ xTime(bArr[r4])) ^ bArr[r6]) ^ bArr[r7]) ^ xTime(bArr[r8]));
        }
        return bArr2;
    }

    static byte sBox(byte b) {
        return f1757S[(b & 255) >>> 4][b & Ascii.f1128SI];
    }

    static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    static byte xTime(byte b) {
        int r0 = b >>> 7;
        int r1 = b << 1;
        if (r0 > 0) {
            r1 ^= 27;
        }
        return (byte) (r1 & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] xor(byte[] bArr, byte[] bArr2, int r7) {
        byte[] bArr3 = new byte[16];
        int r2 = 0;
        while (r2 < 16) {
            bArr3[r2] = (byte) (bArr2[r7] ^ bArr[r2]);
            r2++;
            r7++;
        }
        return bArr3;
    }

    static void xorReverse(byte[] bArr, byte[] bArr2) {
        bArr[0] = (byte) (bArr[0] ^ bArr2[15]);
        bArr[1] = (byte) (bArr[1] ^ bArr2[14]);
        bArr[2] = (byte) (bArr[2] ^ bArr2[13]);
        bArr[3] = (byte) (bArr[3] ^ bArr2[12]);
        bArr[4] = (byte) (bArr[4] ^ bArr2[11]);
        bArr[5] = (byte) (bArr[5] ^ bArr2[10]);
        bArr[6] = (byte) (bArr[6] ^ bArr2[9]);
        bArr[7] = (byte) (bArr[7] ^ bArr2[8]);
        bArr[8] = (byte) (bArr2[7] ^ bArr[8]);
        bArr[9] = (byte) (bArr2[6] ^ bArr[9]);
        bArr[10] = (byte) (bArr2[5] ^ bArr[10]);
        bArr[11] = (byte) (bArr2[4] ^ bArr[11]);
        bArr[12] = (byte) (bArr2[3] ^ bArr[12]);
        bArr[13] = (byte) (bArr2[2] ^ bArr[13]);
        bArr[14] = (byte) (bArr2[1] ^ bArr[14]);
        bArr[15] = (byte) (bArr2[0] ^ bArr[15]);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }
}
