package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.imageutils.JfifUtil;
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
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: m1 */
    private static final int f1887m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f1888m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f1889m3 = 27;

    /* renamed from: m4 */
    private static final int f1890m4 = -1061109568;

    /* renamed from: m5 */
    private static final int f1891m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    /* renamed from: S */
    private static final byte[] f1885S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, Ascii.f1121FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f1120EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f1132VT, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.f1122FS, -90, -76, -58, -24, -35, 116, Ascii.f1131US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f1129SO, 97, 53, 87, -71, -122, -63, Ascii.f1123GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f1127RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, MultipartStream.DASH, Ascii.f1128SI, -80, 84, -69, Ascii.SYN};

    /* renamed from: Si */
    private static final byte[] f1886Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, Ascii.f1132VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f1127RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.f1128SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f1122FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f1123GS, 41, -59, -119, 111, -73, 98, Ascii.f1129SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.f1131US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.f1120EM, -75, 74, 13, MultipartStream.DASH, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.f1121FF, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};

    private static int FFmulX(int r2) {
        return (((r2 & f1887m1) >>> 7) * 27) ^ ((f1888m2 & r2) << 1);
    }

    private static int FFmulX2(int r2) {
        int r22 = r2 & f1890m4;
        int r23 = r22 ^ (r22 >>> 1);
        return (r23 >>> 5) ^ (((f1891m5 & r2) << 2) ^ (r23 >>> 2));
    }

    private void decryptBlock(byte[] bArr, int r18, byte[] bArr2, int r20, int[][] r21) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, r18 + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, r18 + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, r18 + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, r18 + 12);
        int r6 = this.ROUNDS;
        int r2 = littleEndianToInt ^ r21[r6][0];
        int r3 = littleEndianToInt2 ^ r21[r6][1];
        int r4 = littleEndianToInt3 ^ r21[r6][2];
        int r7 = r6 - 1;
        int r0 = littleEndianToInt4 ^ r21[r6][3];
        while (true) {
            byte[] bArr3 = f1886Si;
            int r12 = r2 & 255;
            if (r7 <= 1) {
                int inv_mcol = inv_mcol((((bArr3[r12] & 255) ^ ((bArr3[(r0 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r3 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][0];
                int inv_mcol2 = inv_mcol((((bArr3[r3 & 255] & 255) ^ ((bArr3[(r2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r0 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r4 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][1];
                int inv_mcol3 = inv_mcol((((bArr3[r4 & 255] & 255) ^ ((bArr3[(r3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r0 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][2];
                int inv_mcol4 = inv_mcol((((bArr3[r0 & 255] & 255) ^ ((bArr3[(r4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r2 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][3];
                int r22 = ((((bArr3[inv_mcol & 255] & 255) ^ ((bArr3[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol2 >> 24) & 255] << Ascii.CAN)) ^ r21[0][0];
                int r32 = ((((bArr3[inv_mcol2 & 255] & 255) ^ ((bArr3[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol3 >> 24) & 255] << Ascii.CAN)) ^ r21[0][1];
                int r42 = ((((bArr3[inv_mcol3 & 255] & 255) ^ ((bArr3[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol4 >> 24) & 255] << Ascii.CAN)) ^ r21[0][2];
                int r02 = ((((bArr3[inv_mcol4 & 255] & 255) ^ ((bArr3[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol >> 24) & 255] << Ascii.CAN)) ^ r21[0][3];
                Pack.intToLittleEndian(r22, bArr2, r20 + 0);
                Pack.intToLittleEndian(r32, bArr2, r20 + 4);
                Pack.intToLittleEndian(r42, bArr2, r20 + 8);
                Pack.intToLittleEndian(r02, bArr2, r20 + 12);
                return;
            }
            int inv_mcol5 = inv_mcol((((bArr3[r12] & 255) ^ ((bArr3[(r0 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r3 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][0];
            int inv_mcol6 = inv_mcol((((bArr3[r3 & 255] & 255) ^ ((bArr3[(r2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r0 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r4 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][1];
            int inv_mcol7 = inv_mcol((((bArr3[r4 & 255] & 255) ^ ((bArr3[(r3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r0 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][2];
            int r23 = r7 - 1;
            int inv_mcol8 = inv_mcol((((bArr3[r0 & 255] & 255) ^ ((bArr3[(r4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r2 >> 24) & 255] << Ascii.CAN)) ^ r21[r7][3];
            int inv_mcol9 = inv_mcol((((bArr3[inv_mcol5 & 255] & 255) ^ ((bArr3[(inv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol6 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][0];
            int inv_mcol10 = inv_mcol((((bArr3[inv_mcol6 & 255] & 255) ^ ((bArr3[(inv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol7 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][1];
            int r62 = r23 - 1;
            r0 = inv_mcol((((bArr3[inv_mcol8 & 255] & 255) ^ ((bArr3[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol5 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][3];
            r2 = inv_mcol9;
            r3 = inv_mcol10;
            r4 = inv_mcol((((bArr3[inv_mcol7 & 255] & 255) ^ ((bArr3[(inv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol5 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol8 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][2];
            r7 = r62;
        }
    }

    private void encryptBlock(byte[] bArr, int r18, byte[] bArr2, int r20, int[][] r21) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, r18 + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, r18 + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, r18 + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, r18 + 12);
        int r2 = littleEndianToInt ^ r21[0][0];
        int r3 = littleEndianToInt2 ^ r21[0][1];
        int r4 = littleEndianToInt3 ^ r21[0][2];
        int r0 = littleEndianToInt4 ^ r21[0][3];
        int r10 = 1;
        while (r10 < this.ROUNDS - 1) {
            byte[] bArr3 = f1885S;
            int mcol = mcol((((bArr3[r2 & 255] & 255) ^ ((bArr3[(r3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r0 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][0];
            int mcol2 = mcol((((bArr3[r3 & 255] & 255) ^ ((bArr3[(r4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r0 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r2 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][1];
            int mcol3 = mcol((((bArr3[r4 & 255] & 255) ^ ((bArr3[(r0 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r3 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][2];
            int r22 = r10 + 1;
            int mcol4 = mcol((((bArr3[r0 & 255] & 255) ^ ((bArr3[(r2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(r3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(r4 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][3];
            int mcol5 = mcol((((bArr3[mcol & 255] & 255) ^ ((bArr3[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol4 >> 24) & 255] << Ascii.CAN)) ^ r21[r22][0];
            int mcol6 = mcol((((bArr3[mcol2 & 255] & 255) ^ ((bArr3[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol >> 24) & 255] << Ascii.CAN)) ^ r21[r22][1];
            int r11 = r22 + 1;
            r0 = mcol((((bArr3[mcol4 & 255] & 255) ^ ((bArr3[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol3 >> 24) & 255] << Ascii.CAN)) ^ r21[r22][3];
            r2 = mcol5;
            r3 = mcol6;
            r4 = mcol((((bArr3[mcol3 & 255] & 255) ^ ((bArr3[(mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol2 >> 24) & 255] << Ascii.CAN)) ^ r21[r22][2];
            r10 = r11;
        }
        byte[] bArr4 = f1885S;
        int mcol7 = mcol((((bArr4[r2 & 255] & 255) ^ ((bArr4[(r3 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(r4 >> 16) & 255] & 255) << 16)) ^ (bArr4[(r0 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][0];
        int mcol8 = mcol((((bArr4[r3 & 255] & 255) ^ ((bArr4[(r4 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(r0 >> 16) & 255] & 255) << 16)) ^ (bArr4[(r2 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][1];
        int mcol9 = mcol((((bArr4[r4 & 255] & 255) ^ ((bArr4[(r0 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(r2 >> 16) & 255] & 255) << 16)) ^ (bArr4[(r3 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][2];
        int r23 = r10 + 1;
        int mcol10 = mcol((((bArr4[r0 & 255] & 255) ^ ((bArr4[(r2 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(r3 >> 16) & 255] & 255) << 16)) ^ (bArr4[(r4 >> 24) & 255] << Ascii.CAN)) ^ r21[r10][3];
        int r32 = ((((bArr4[mcol7 & 255] & 255) ^ ((bArr4[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol10 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][0];
        int r42 = ((((bArr4[mcol8 & 255] & 255) ^ ((bArr4[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol7 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][1];
        int r5 = ((((bArr4[mcol9 & 255] & 255) ^ ((bArr4[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol8 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][2];
        int r02 = ((((bArr4[mcol10 & 255] & 255) ^ ((bArr4[(mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol9 >> 24) & 255] << Ascii.CAN)) ^ r21[r23][3];
        Pack.intToLittleEndian(r32, bArr2, r20 + 0);
        Pack.intToLittleEndian(r42, bArr2, r20 + 4);
        Pack.intToLittleEndian(r5, bArr2, r20 + 8);
        Pack.intToLittleEndian(r02, bArr2, r20 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int r2 = length >>> 2;
        int r5 = r2 + 6;
        this.ROUNDS = r5;
        int[][] r52 = (int[][]) Array.newInstance(int.class, r5 + 1, 4);
        int r10 = 8;
        char c = 3;
        if (r2 == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            r52[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            r52[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            r52[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            r52[0][3] = littleEndianToInt4;
            for (int r7 = 1; r7 <= 10; r7++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[r7 - 1];
                r52[r7][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                r52[r7][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                r52[r7][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                r52[r7][3] = littleEndianToInt4;
            }
        } else if (r2 == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            r52[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            r52[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            r52[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            r52[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int r8 = 1;
            int r12 = 1;
            while (true) {
                r52[r8][0] = littleEndianToInt9;
                r52[r8][1] = littleEndianToInt10;
                int subWord = subWord(shift(littleEndianToInt10, 8)) ^ r12;
                int r122 = r12 << 1;
                int r22 = littleEndianToInt5 ^ subWord;
                r52[r8][2] = r22;
                int r102 = littleEndianToInt6 ^ r22;
                r52[r8][3] = r102;
                int r11 = littleEndianToInt7 ^ r102;
                int r13 = r8 + 1;
                r52[r13][0] = r11;
                int r72 = littleEndianToInt8 ^ r11;
                r52[r13][1] = r72;
                int r3 = littleEndianToInt9 ^ r72;
                r52[r13][2] = r3;
                int r1 = littleEndianToInt10 ^ r3;
                r52[r13][3] = r1;
                int subWord2 = subWord(shift(r1, 8)) ^ r122;
                r12 = r122 << 1;
                littleEndianToInt5 = r22 ^ subWord2;
                int r132 = r8 + 2;
                r52[r132][0] = littleEndianToInt5;
                littleEndianToInt6 = r102 ^ littleEndianToInt5;
                r52[r132][1] = littleEndianToInt6;
                littleEndianToInt7 = r11 ^ littleEndianToInt6;
                r52[r132][2] = littleEndianToInt7;
                littleEndianToInt8 = r72 ^ littleEndianToInt7;
                r52[r132][3] = littleEndianToInt8;
                r8 += 3;
                if (r8 >= 13) {
                    break;
                }
                littleEndianToInt9 = r3 ^ littleEndianToInt8;
                littleEndianToInt10 = r1 ^ littleEndianToInt9;
            }
        } else if (r2 != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            r52[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            r52[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            r52[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            r52[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            r52[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            r52[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            r52[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            r52[1][3] = littleEndianToInt18;
            int r82 = 1;
            int r17 = 2;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, r10)) ^ r82;
                r82 <<= 1;
                littleEndianToInt11 ^= subWord3;
                r52[r17][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                r52[r17][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                r52[r17][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                r52[r17][c] = littleEndianToInt14;
                int r103 = r17 + 1;
                if (r103 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                r52[r103][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                r52[r103][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                r52[r103][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                r52[r103][3] = littleEndianToInt18;
                r17 = r103 + 1;
                r10 = 8;
                c = 3;
            }
        }
        if (!z) {
            for (int r6 = 1; r6 < this.ROUNDS; r6++) {
                for (int r14 = 0; r14 < 4; r14++) {
                    r52[r6][r14] = inv_mcol(r52[r6][r14]);
                }
            }
        }
        return r52;
    }

    private static int inv_mcol(int r2) {
        int shift = shift(r2, 8) ^ r2;
        int FFmulX = r2 ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int r2) {
        int shift = shift(r2, 8);
        int r22 = r2 ^ shift;
        return FFmulX(r22) ^ (shift ^ shift(r22, 16));
    }

    private static int shift(int r1, int r2) {
        return (r1 << (-r2)) | (r1 >>> r2);
    }

    private static int subWord(int r3) {
        byte[] bArr = f1885S;
        return (bArr[(r3 >> 24) & 255] << Ascii.CAN) | (bArr[r3 & 255] & 255) | ((bArr[(r3 >> 8) & 255] & 255) << 8) | ((bArr[(r3 >> 16) & 255] & 255) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r9, byte[] bArr2, int r11) {
        int[][] r5 = this.WorkingKey;
        if (r5 != null) {
            if (r9 <= bArr.length - 16) {
                if (r11 <= bArr2.length - 16) {
                    if (this.forEncryption) {
                        encryptBlock(bArr, r9, bArr2, r11, r5);
                    } else {
                        decryptBlock(bArr, r9, bArr2, r11, r5);
                    }
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
