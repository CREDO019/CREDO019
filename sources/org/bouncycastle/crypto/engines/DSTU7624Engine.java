package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class DSTU7624Engine implements BlockCipher {
    private static final int ROUNDS_128 = 10;
    private static final int ROUNDS_256 = 14;
    private static final int ROUNDS_512 = 18;

    /* renamed from: S0 */
    private static final byte[] f1916S0 = {-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, Ascii.ETB, -16, -40, 9, 109, -13, Ascii.f1123GS, -53, -55, 77, 44, -81, 121, -32, -105, -3, 111, 75, 69, 57, 62, -35, -93, 79, -76, -74, -102, Ascii.f1129SO, Ascii.f1131US, -65, Ascii.NAK, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, Ascii.f1120EM, -43, -83, 88, -92, -69, -95, -36, -14, -125, 55, 66, -28, 122, 50, -100, -52, -85, 74, -113, 110, 4, 39, 46, -25, -30, 90, -106, Ascii.SYN, 35, 43, -62, 101, 102, Ascii.f1128SI, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, 52, 72, -4, -73, 106, -120, -91, 83, -122, -7, 91, -37, 56, 123, -61, Ascii.f1127RS, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, -11, Ascii.DC4, -97, 8, 85, -101, 76, -2, 96, 92, -38, Ascii.CAN, 70, -51, 125, 33, -80, Utf8.REPLACEMENT_BYTE, Ascii.ESC, -119, -1, -21, -124, 105, 58, -99, -41, -45, 112, 103, SignedBytes.MAX_POWER_OF_TWO, -75, -34, 93, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, -96, -59, 2, -90, 116, MultipartStream.DASH, Ascii.f1132VT, -94, 118, -77, -66, -50, -67, -82, -23, -118, 49, Ascii.f1122FS, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, 61, -126, -9, -22, 10, 13, 126, -8, 80, Ascii.SUB, -60, 7, 87, -72, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, Ascii.f1121FF, Ascii.DC2, 41, 81, -71, -49, -42, 115, -115, -127, 84, -64, -19, 78, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, Byte.MIN_VALUE};

    /* renamed from: S1 */
    private static final byte[] f1917S1 = {-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, -42, -78, -46, -112, Ascii.ETB, -8, 66, Ascii.NAK, 86, -76, 101, Ascii.f1122FS, -120, 67, -59, 92, 54, -70, -11, 87, 103, -115, 49, -10, 100, 88, -98, -12, 34, -86, 117, Ascii.f1128SI, 2, -79, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, 68, 62, -97, Ascii.DC4, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, Ascii.SUB, 107, 105, -13, -67, 51, -85, -6, -47, -101, 104, 78, Ascii.SYN, -107, -111, -18, 76, 99, -114, 91, -52, 60, Ascii.f1120EM, -95, -127, 73, 123, -39, 111, 55, 96, -54, -25, 43, 72, -3, -106, 69, -4, 65, Ascii.DC2, 13, 121, -27, -119, -116, -29, 32, 48, -36, -73, 108, 74, -75, Utf8.REPLACEMENT_BYTE, -105, -44, 98, MultipartStream.DASH, 6, -92, -91, -125, 95, 42, -38, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, Ascii.f1129SO, 10, 61, 81, 125, -109, Ascii.ESC, -2, -60, 71, 9, -122, Ascii.f1132VT, -113, -99, 106, 7, -71, -80, -104, Ascii.CAN, 50, 113, 75, -17, 59, 112, -96, -28, SignedBytes.MAX_POWER_OF_TWO, -1, -61, -87, -26, 120, -7, -117, 70, Byte.MIN_VALUE, Ascii.f1127RS, 56, -31, -72, -88, -32, Ascii.f1121FF, 35, 118, Ascii.f1123GS, 37, 36, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, -14, -126, 80, 122, 47, 116, 83, -77, 97, -81, 57, 53, -34, -51, Ascii.f1131US, -103, -84, -83, 114, 44, -35, -48, -121, -66, 94, -90, -20, 4, -58, 3, 52, -5, -37, 89, -74, -62, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, -118, 39, -57, -64, 41, -41};

    /* renamed from: S2 */
    private static final byte[] f1918S2 = {-109, -39, -102, -75, -104, 34, 69, -4, -70, 106, -33, 2, -97, -36, 81, 89, 74, Ascii.ETB, 43, -62, -108, -12, -69, -93, 98, -28, 113, -44, -51, 112, Ascii.SYN, -31, 73, 60, -64, -40, 92, -101, -83, -123, 83, -95, 122, -56, MultipartStream.DASH, -32, -47, 114, -90, 44, -60, -29, 118, 120, -73, -76, 9, 59, Ascii.f1129SO, 65, 76, -34, -78, -112, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, 78, Ascii.DC2, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, Ascii.CAN, -105, -45, 54, -26, 72, 86, -127, -113, 119, -52, -100, -71, -30, -84, -72, 47, Ascii.NAK, -92, 124, -38, 56, Ascii.f1127RS, Ascii.f1132VT, 5, -42, Ascii.DC4, 110, 108, 126, 102, -3, -79, -27, 96, -81, 94, 51, -121, -55, -16, 93, 109, Utf8.REPLACEMENT_BYTE, -120, -115, -57, -9, Ascii.f1123GS, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, Ascii.f1128SI, 55, 36, 40, 48, -107, -46, 62, 91, SignedBytes.MAX_POWER_OF_TWO, -125, -77, 105, 87, Ascii.f1131US, 7, Ascii.f1122FS, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, -18, 49, -94, 115, -7, -54, 58, Ascii.SUB, -5, 13, -63, -2, -6, -14, 111, -67, -106, -35, 67, 82, -74, 8, -13, -82, -66, Ascii.f1120EM, -119, 50, 38, -80, -22, 75, 100, -124, -126, 107, -11, 121, -65, 1, 95, 117, 99, Ascii.ESC, 35, 61, 104, 42, 101, -24, -111, -10, -1, 19, 88, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, 68, 66, 4, -96, -37, 57, -122, 84, -86, -116, 52, 33, -117, -8, Ascii.f1121FF, 116, 103};

    /* renamed from: S3 */
    private static final byte[] f1919S3 = {104, -115, -54, 77, 115, 75, 78, 42, -44, 82, 38, -77, 84, Ascii.f1127RS, Ascii.f1120EM, Ascii.f1131US, 34, 3, 70, 61, MultipartStream.DASH, 74, 83, -125, 19, -118, -73, -43, 37, 121, -11, -67, 88, 47, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, Ascii.SYN, 60, 102, 112, 93, -13, 69, SignedBytes.MAX_POWER_OF_TWO, -52, -24, -108, 86, 8, -50, Ascii.SUB, 58, -46, -31, -33, -75, 56, 110, Ascii.f1129SO, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, Ascii.DC4, -82, -18, -56, 72, -45, 48, -95, -110, 65, -79, Ascii.CAN, -60, 44, 113, 114, 68, Ascii.NAK, -3, 55, -66, 95, -86, -101, -120, -40, -85, -119, -100, -6, 96, -22, PSSSigner.TRAILER_IMPLICIT, 98, Ascii.f1121FF, 36, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, 52, 126, 16, -15, 123, -113, 99, -96, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, -74, -41, 41, -62, -21, -64, -92, -117, -116, Ascii.f1123GS, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, 108, -112, 0, -114, 111, 80, 1, -59, -38, 71, Utf8.REPLACEMENT_BYTE, -51, 105, -94, -30, 122, -89, -58, -109, Ascii.f1128SI, 10, 6, -26, 43, -106, -93, Ascii.f1122FS, -81, 106, Ascii.DC2, -124, 57, -25, -80, -126, -9, -2, -99, -121, 92, -127, 53, -34, -76, -91, -4, Byte.MIN_VALUE, -17, -53, -69, 107, 118, -70, 90, 125, 120, Ascii.f1132VT, -107, -29, -83, 116, -104, 59, 54, 100, 109, -36, -16, 89, -87, 76, Ascii.ETB, Byte.MAX_VALUE, -111, -72, -55, 87, Ascii.ESC, -32, 97};

    /* renamed from: T0 */
    private static final byte[] f1920T0 = {-92, -94, -87, -59, 78, -55, 3, -39, 126, Ascii.f1128SI, -46, -83, -25, -45, 39, 91, -29, -95, -24, -26, 124, 42, 85, Ascii.f1121FF, -122, 57, -41, -115, -72, Ascii.DC2, 111, 40, -51, -118, 112, 86, 114, -7, -65, 79, 115, -23, -9, 87, Ascii.SYN, -84, 80, -64, -99, -73, 71, 113, 96, -60, 116, 67, 108, Ascii.f1131US, -109, 119, -36, -50, 32, -116, -103, 95, 68, 1, -11, Ascii.f1127RS, -121, 94, 97, 44, 75, Ascii.f1123GS, -127, Ascii.NAK, -12, 35, -42, -22, -31, 103, -15, Byte.MAX_VALUE, -2, -38, 60, 7, 83, 106, -124, -100, -53, 2, -125, 51, -35, 53, -30, 89, 90, -104, -91, -110, 100, 4, 6, 16, 77, Ascii.f1122FS, -105, 8, 49, -18, -85, 5, -81, 121, -96, Ascii.CAN, 70, 109, -4, -119, -44, -57, -1, -16, -49, 66, -111, -8, 104, 10, 101, -114, -74, -3, -61, -17, 120, 76, -52, -98, 48, 46, PSSSigner.TRAILER_IMPLICIT, Ascii.f1132VT, 84, Ascii.SUB, -90, -69, 38, Byte.MIN_VALUE, 72, -108, 50, 125, -89, Utf8.REPLACEMENT_BYTE, -82, 34, 61, 102, -86, -10, 0, 93, -67, 74, -32, 59, -76, Ascii.ETB, -117, -97, 118, -80, 36, -102, 37, 99, -37, -21, 122, 62, 92, -77, -79, 41, -14, -54, 88, 110, -40, -88, 47, 117, -33, Ascii.DC4, -5, 19, 73, -120, -78, -20, -28, 52, MultipartStream.DASH, -106, -58, 58, -19, -107, Ascii.f1129SO, -27, -123, 107, SignedBytes.MAX_POWER_OF_TWO, 33, -101, 9, Ascii.f1120EM, 43, 82, -34, 69, -93, -6, 81, -62, -75, -47, -112, -71, -13, 55, -63, 13, -70, 65, 17, 56, 123, -66, -48, -43, 105, 54, -56, 98, Ascii.ESC, -126, -113};

    /* renamed from: T1 */
    private static final byte[] f1921T1 = {-125, -14, 42, -21, -23, -65, 123, -100, 52, -106, -115, -104, -71, 105, -116, 41, 61, -120, 104, 6, 57, 17, 76, Ascii.f1129SO, -96, 86, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.NAK, PSSSigner.TRAILER_IMPLICIT, -77, -36, 111, -8, 38, -70, -66, -67, 49, -5, -61, -2, Byte.MIN_VALUE, 97, -31, 122, 50, -46, 112, 32, -95, 69, -20, -39, Ascii.SUB, 93, -76, -40, 9, -91, 85, -114, 55, 118, -87, 103, 16, Ascii.ETB, 54, 101, -79, -107, 98, 89, 116, -93, 80, 47, 75, -56, -48, -113, -51, -44, 60, -122, Ascii.DC2, Ascii.f1123GS, 35, -17, -12, 83, Ascii.f1120EM, 53, -26, Byte.MAX_VALUE, 94, -42, 121, 81, 34, Ascii.DC4, -9, Ascii.f1127RS, 74, 66, -101, 65, 115, MultipartStream.DASH, -63, 92, -90, -94, -32, 46, -45, 40, -69, -55, -82, 106, -47, 90, 48, -112, -124, -7, -78, 88, -49, 126, -59, -53, -105, -28, Ascii.SYN, 108, -6, -80, 109, Ascii.f1131US, 82, -103, 13, 78, 3, -111, -62, 77, 100, 119, -97, -35, -60, 73, -118, -102, 36, 56, -89, 87, -123, -57, 124, 125, -25, -10, -73, -84, 39, 70, -34, -33, 59, -41, -98, 43, Ascii.f1132VT, -43, 19, 117, -16, 114, -74, -99, Ascii.ESC, 1, Utf8.REPLACEMENT_BYTE, 68, -27, -121, -3, 7, -15, -85, -108, Ascii.CAN, -22, -4, 58, -126, 95, 5, 84, -37, 0, -117, -29, 72, Ascii.f1121FF, -54, 120, -119, 10, -1, 62, 91, -127, -18, 113, -30, -38, 44, -72, -75, -52, 110, -88, 107, -83, 96, -58, 8, 4, 2, -24, -11, 79, -92, -13, -64, -50, 67, 37, Ascii.f1122FS, 33, 51, Ascii.f1128SI, -81, 71, -19, 102, 99, -109, -86};

    /* renamed from: T2 */
    private static final byte[] f1922T2 = {69, -44, Ascii.f1132VT, 67, -15, 114, -19, -92, -62, 56, -26, 113, -3, -74, 58, -107, 80, 68, 75, -30, 116, 107, Ascii.f1127RS, 17, 90, -58, -76, -40, -91, -118, 112, -93, -88, -6, 5, -39, -105, SignedBytes.MAX_POWER_OF_TWO, -55, -112, -104, -113, -36, Ascii.DC2, 49, 44, 71, 106, -103, -82, -56, Byte.MAX_VALUE, -7, 79, 93, -106, 111, -12, -77, 57, 33, -38, -100, -123, -98, 59, -16, -65, -17, 6, -18, -27, 95, 32, 16, -52, 60, 84, 74, 82, -108, Ascii.f1129SO, -64, 40, -10, 86, 96, -94, -29, Ascii.f1128SI, -20, -99, 36, -125, 126, -43, 124, -21, Ascii.CAN, -41, -51, -35, 120, -1, -37, -95, 9, -48, 118, -124, 117, -69, Ascii.f1123GS, Ascii.SUB, 47, -80, -2, -42, 52, 99, 53, -46, 42, 89, 109, 77, 119, -25, -114, 97, -49, -97, -50, 39, -11, Byte.MIN_VALUE, -122, -57, -90, -5, -8, -121, -85, 98, Utf8.REPLACEMENT_BYTE, -33, 72, 0, Ascii.DC4, -102, -67, 91, 4, -110, 2, 37, 101, 76, 83, Ascii.f1121FF, -14, 41, -81, Ascii.ETB, 108, 65, 48, -23, -109, 85, -9, -84, 104, 38, -60, 125, -54, 122, 62, -96, 55, 3, -63, 54, 105, 102, 8, Ascii.SYN, -89, PSSSigner.TRAILER_IMPLICIT, -59, -45, 34, -73, 19, 70, 50, -24, 87, -120, 43, -127, -78, 78, 100, Ascii.f1122FS, -86, -111, 88, 46, -101, 92, Ascii.ESC, 81, 115, 66, 35, 1, 110, -13, 13, -66, 61, 10, MultipartStream.DASH, Ascii.f1131US, 103, 51, Ascii.f1120EM, 123, 94, -22, -34, -117, -53, -87, -116, -115, -83, 73, -126, -28, -70, -61, Ascii.NAK, -47, -32, -119, -4, -79, -71, -75, 7, 121, -72, -31};

    /* renamed from: T3 */
    private static final byte[] f1923T3 = {-78, -74, 35, 17, -89, -120, -59, -90, 57, -113, -60, -24, 115, 34, 67, -61, -126, 39, -51, Ascii.CAN, 81, 98, MultipartStream.DASH, -9, 92, Ascii.f1129SO, 59, -3, -54, -101, 13, Ascii.f1128SI, 121, -116, 16, 76, 116, Ascii.f1122FS, 10, -114, 124, -108, 7, -57, 94, Ascii.DC4, -95, 33, 87, 80, 78, -87, Byte.MIN_VALUE, -39, -17, 100, 65, -49, 60, -18, 46, 19, 41, -70, 52, 90, -82, -118, 97, 51, Ascii.DC2, -71, 85, -88, Ascii.NAK, 5, -10, 3, 6, 73, -75, 37, 9, Ascii.SYN, Ascii.f1121FF, 42, 56, -4, 32, -12, -27, Byte.MAX_VALUE, -41, 49, 43, 102, 111, -1, 114, -122, -16, -93, 47, 120, 0, PSSSigner.TRAILER_IMPLICIT, -52, -30, -80, -15, 66, -76, 48, 95, 96, 4, -20, -91, -29, -117, -25, Ascii.f1123GS, -65, -124, 123, -26, -127, -8, -34, -40, -46, Ascii.ETB, -50, 75, 71, -42, 105, 108, Ascii.f1120EM, -103, -102, 1, -77, -123, -79, -7, 89, -62, 55, -23, -56, -96, -19, 79, -119, 104, 109, -43, 38, -111, -121, 88, -67, -55, -104, -36, 117, -64, 118, -11, 103, 107, 126, -21, 82, -53, -47, 91, -97, Ascii.f1132VT, -37, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.SUB, -6, -84, -28, -31, 113, Ascii.f1131US, 101, -115, -105, -98, -107, -112, 93, -73, -63, -81, 84, -5, 2, -32, 53, -69, 58, 77, -83, 44, 61, 86, 8, Ascii.ESC, 74, -109, 106, -85, -72, 122, -14, 125, -38, Utf8.REPLACEMENT_BYTE, -2, 62, -66, -22, -86, 68, -58, -48, 54, 72, 112, -106, 119, 36, 83, -33, -13, -125, 40, 50, 69, Ascii.f1127RS, -92, -45, -94, 70, 110, -100, -35, 99, -44, -99};
    private boolean forEncryption;
    private long[] internalState;
    private long[][] roundKeys;
    private int roundsAmount;
    private int wordsInBlock;
    private int wordsInKey;
    private long[] workingKey;

    public DSTU7624Engine(int r2) throws IllegalArgumentException {
        if (r2 != 128 && r2 != 256 && r2 != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int r22 = r2 >>> 6;
        this.wordsInBlock = r22;
        this.internalState = new long[r22];
    }

    private void addRoundKey(int r7) {
        long[] jArr = this.roundKeys[r7];
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr2 = this.internalState;
            jArr2[r0] = jArr2[r0] + jArr[r0];
        }
    }

    private void decryptBlock_128(byte[] bArr, int r23, byte[] bArr2, int r25) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, r23);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, r23 + 8);
        long[][] jArr = this.roundKeys;
        int r8 = this.roundsAmount;
        long[] jArr2 = jArr[r8];
        long j = littleEndianToLong - jArr2[0];
        long j2 = littleEndianToLong2 - jArr2[1];
        while (true) {
            long mixColumnInv = mixColumnInv(j);
            long mixColumnInv2 = mixColumnInv(j2);
            int r7 = (int) mixColumnInv;
            int r4 = (int) (mixColumnInv >>> 32);
            int r3 = (int) mixColumnInv2;
            int r6 = (int) (mixColumnInv2 >>> 32);
            byte[] bArr3 = f1920T0;
            byte b = bArr3[r7 & 255];
            byte[] bArr4 = f1921T1;
            byte b2 = bArr4[(r7 >>> 8) & 255];
            byte[] bArr5 = f1922T2;
            byte b3 = bArr5[(r7 >>> 16) & 255];
            byte[] bArr6 = f1923T3;
            int r72 = (bArr6[r7 >>> 24] << Ascii.CAN) | ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8);
            byte b4 = bArr3[r6 & 255];
            byte b5 = bArr4[(r6 >>> 8) & 255];
            byte b6 = bArr5[(r6 >>> 16) & 255];
            long j3 = (((bArr6[r6 >>> 24] << Ascii.CAN) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (r72 & BodyPartID.bodyIdMax);
            int r32 = (bArr6[r3 >>> 24] << Ascii.CAN) | (bArr3[r3 & 255] & 255) | ((bArr4[(r3 >>> 8) & 255] & 255) << 8) | ((bArr5[(r3 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[r4 & 255];
            byte b8 = bArr4[(r4 >>> 8) & 255];
            byte b9 = bArr5[(r4 >>> 16) & 255];
            long j4 = (((bArr6[r4 >>> 24] << Ascii.CAN) | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32) | (r32 & BodyPartID.bodyIdMax);
            r8--;
            if (r8 == 0) {
                long[] jArr3 = this.roundKeys[0];
                Pack.longToLittleEndian(j3 - jArr3[0], bArr2, r25);
                Pack.longToLittleEndian(j4 - jArr3[1], bArr2, r25 + 8);
                return;
            }
            long[] jArr4 = this.roundKeys[r8];
            long j5 = j3 ^ jArr4[0];
            long j6 = j4 ^ jArr4[1];
            j = j5;
            j2 = j6;
        }
    }

    private void encryptBlock_128(byte[] bArr, int r23, byte[] bArr2, int r25) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, r23);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, r23 + 8);
        long[] jArr = this.roundKeys[0];
        long j = littleEndianToLong + jArr[0];
        long j2 = littleEndianToLong2 + jArr[1];
        int r7 = 0;
        while (true) {
            int r10 = (int) j;
            int r4 = (int) (j >>> 32);
            int r3 = (int) j2;
            int r6 = (int) (j2 >>> 32);
            byte[] bArr3 = f1916S0;
            byte b = bArr3[r10 & 255];
            byte[] bArr4 = f1917S1;
            byte b2 = bArr4[(r10 >>> 8) & 255];
            byte[] bArr5 = f1918S2;
            byte b3 = bArr5[(r10 >>> 16) & 255];
            byte[] bArr6 = f1919S3;
            int r8 = ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8) | (bArr6[r10 >>> 24] << Ascii.CAN);
            byte b4 = bArr3[r6 & 255];
            byte b5 = bArr4[(r6 >>> 8) & 255];
            byte b6 = bArr5[(r6 >>> 16) & 255];
            long j3 = (((bArr6[r6 >>> 24] << Ascii.CAN) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (r8 & BodyPartID.bodyIdMax);
            byte b7 = bArr3[r3 & 255];
            byte b8 = bArr4[(r3 >>> 8) & 255];
            byte b9 = bArr5[(r3 >>> 16) & 255];
            int r32 = (bArr6[r3 >>> 24] << Ascii.CAN) | (b7 & 255) | ((b8 & 255) << 8) | ((b9 & 255) << 16);
            byte b10 = bArr3[r4 & 255];
            byte b11 = bArr4[(r4 >>> 8) & 255];
            byte b12 = bArr5[(r4 >>> 16) & 255];
            long mixColumn = mixColumn(j3);
            long mixColumn2 = mixColumn((((bArr6[r4 >>> 24] << Ascii.CAN) | (((b10 & 255) | ((b11 & 255) << 8)) | ((b12 & 255) << 16))) << 32) | (r32 & BodyPartID.bodyIdMax));
            r7++;
            int r62 = this.roundsAmount;
            if (r7 == r62) {
                long[] jArr2 = this.roundKeys[r62];
                Pack.longToLittleEndian(mixColumn + jArr2[0], bArr2, r25);
                Pack.longToLittleEndian(mixColumn2 + jArr2[1], bArr2, r25 + 8);
                return;
            }
            long[] jArr3 = this.roundKeys[r7];
            long j4 = mixColumn ^ jArr3[0];
            j2 = mixColumn2 ^ jArr3[1];
            j = j4;
        }
    }

    private void invShiftRows() {
        int r1 = this.wordsInBlock;
        if (r1 == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
        } else if (r1 == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j5) & (-281470681808896L);
            long j9 = j4 ^ j8;
            long j10 = j5 ^ j8;
            long j11 = (j6 ^ j7) & (-281470681808896L);
            long j12 = j6 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-4294967296L);
            long j15 = j9 ^ j14;
            long j16 = (j10 ^ j13) & 281474976645120L;
            jArr2[0] = j15;
            jArr2[1] = j10 ^ j16;
            jArr2[2] = j12 ^ j14;
            jArr2[3] = j16 ^ j13;
        } else if (r1 != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        } else {
            long[] jArr3 = this.internalState;
            long j17 = jArr3[0];
            long j18 = jArr3[1];
            long j19 = jArr3[2];
            long j20 = jArr3[3];
            long j21 = jArr3[4];
            long j22 = jArr3[5];
            long j23 = jArr3[6];
            long j24 = jArr3[7];
            long j25 = (j17 ^ j18) & (-71777214294589696L);
            long j26 = j17 ^ j25;
            long j27 = j18 ^ j25;
            long j28 = (j19 ^ j20) & (-71777214294589696L);
            long j29 = j19 ^ j28;
            long j30 = j20 ^ j28;
            long j31 = (j21 ^ j22) & (-71777214294589696L);
            long j32 = j21 ^ j31;
            long j33 = j22 ^ j31;
            long j34 = (j23 ^ j24) & (-71777214294589696L);
            long j35 = j23 ^ j34;
            long j36 = j24 ^ j34;
            long j37 = (j26 ^ j29) & (-281470681808896L);
            long j38 = j26 ^ j37;
            long j39 = j29 ^ j37;
            long j40 = (j27 ^ j30) & 72056494543077120L;
            long j41 = j27 ^ j40;
            long j42 = j30 ^ j40;
            long j43 = (j32 ^ j35) & (-281470681808896L);
            long j44 = j32 ^ j43;
            long j45 = j35 ^ j43;
            long j46 = (j33 ^ j36) & 72056494543077120L;
            long j47 = j33 ^ j46;
            long j48 = j36 ^ j46;
            long j49 = (j38 ^ j44) & (-4294967296L);
            long j50 = j38 ^ j49;
            long j51 = j44 ^ j49;
            long j52 = (j41 ^ j47) & 72057594021150720L;
            long j53 = j41 ^ j52;
            long j54 = (j39 ^ j45) & 281474976645120L;
            long j55 = j39 ^ j54;
            long j56 = j54 ^ j45;
            long j57 = (j42 ^ j48) & 1099511627520L;
            jArr3[0] = j50;
            jArr3[1] = j53;
            jArr3[2] = j55;
            jArr3[3] = j42 ^ j57;
            jArr3[4] = j51;
            jArr3[5] = j47 ^ j52;
            jArr3[6] = j56;
            jArr3[7] = j48 ^ j57;
        }
    }

    private void invSubBytes() {
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr = this.internalState;
            long j = jArr[r0];
            int r4 = (int) j;
            int r3 = (int) (j >>> 32);
            byte[] bArr = f1920T0;
            byte b = bArr[r4 & 255];
            byte[] bArr2 = f1921T1;
            byte b2 = bArr2[(r4 >>> 8) & 255];
            byte[] bArr3 = f1922T2;
            byte b3 = bArr3[(r4 >>> 16) & 255];
            byte[] bArr4 = f1923T3;
            jArr[r0] = (((bArr4[r4 >>> 24] << Ascii.CAN) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16)) & BodyPartID.bodyIdMax) | (((((bArr[r3 & 255] & 255) | ((bArr2[(r3 >>> 8) & 255] & 255) << 8)) | ((bArr3[(r3 >>> 16) & 255] & 255) << 16)) | (bArr4[r3 >>> 24] << Ascii.CAN)) << 32);
        }
    }

    private static long mixColumn(long j) {
        long mulX = mulX(j);
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(16, rotate)) ^ rotate(48, j);
        return ((rotate(32, mulX2((j ^ rotate2) ^ mulX)) ^ rotate2) ^ rotate(40, mulX)) ^ rotate(48, mulX);
    }

    private static long mixColumnInv(long j) {
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(32, rotate)) ^ rotate(48, j);
        long j2 = rotate2 ^ j;
        long rotate3 = rotate(48, j);
        long rotate4 = rotate(56, j);
        long rotate5 = rotate(16, rotate2);
        return mulX(rotate(40, ((j ^ rotate(32, j2)) ^ rotate4) ^ mulX(((rotate3 ^ (rotate(24, j) ^ j2)) ^ rotate4) ^ mulX(mulX(mulX(rotate(40, mulX(mulX(j2 ^ rotate4) ^ rotate(56, j2)) ^ j) ^ (rotate(16, j2) ^ j)) ^ (j2 ^ rotate3)) ^ rotate5)))) ^ rotate2;
    }

    private void mixColumns() {
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr = this.internalState;
            jArr[r0] = mixColumn(jArr[r0]);
        }
    }

    private void mixColumnsInv() {
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr = this.internalState;
            jArr[r0] = mixColumnInv(jArr[r0]);
        }
    }

    private static long mulX(long j) {
        return (((j & (-9187201950435737472L)) >>> 7) * 29) ^ ((9187201950435737471L & j) << 1);
    }

    private static long mulX2(long j) {
        return (((j & 4629771061636907072L) >>> 6) * 29) ^ (((4557430888798830399L & j) << 2) ^ ((((-9187201950435737472L) & j) >>> 6) * 29));
    }

    private static long rotate(int r2, long j) {
        return (j << (-r2)) | (j >>> r2);
    }

    private void rotateLeft(long[] jArr, long[] jArr2) {
        int r1 = this.wordsInBlock;
        if (r1 == 2) {
            long j = jArr[0];
            long j2 = jArr[1];
            jArr2[0] = (j >>> 56) | (j2 << 8);
            jArr2[1] = (j << 8) | (j2 >>> 56);
        } else if (r1 == 4) {
            long j3 = jArr[0];
            long j4 = jArr[1];
            long j5 = jArr[2];
            long j6 = jArr[3];
            jArr2[0] = (j4 >>> 24) | (j5 << 40);
            jArr2[1] = (j5 >>> 24) | (j6 << 40);
            jArr2[2] = (j6 >>> 24) | (j3 << 40);
            jArr2[3] = (j3 >>> 24) | (j4 << 40);
        } else if (r1 != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        } else {
            long j7 = jArr[0];
            long j8 = jArr[1];
            long j9 = jArr[2];
            long j10 = jArr[3];
            long j11 = jArr[4];
            long j12 = jArr[5];
            long j13 = jArr[6];
            long j14 = jArr[7];
            jArr2[0] = (j9 >>> 24) | (j10 << 40);
            jArr2[1] = (j10 >>> 24) | (j11 << 40);
            jArr2[2] = (j11 >>> 24) | (j12 << 40);
            jArr2[3] = (j12 >>> 24) | (j13 << 40);
            jArr2[4] = (j13 >>> 24) | (j14 << 40);
            jArr2[5] = (j14 >>> 24) | (j7 << 40);
            jArr2[6] = (j7 >>> 24) | (j8 << 40);
            jArr2[7] = (j8 >>> 24) | (j9 << 40);
        }
    }

    private void shiftRows() {
        int r1 = this.wordsInBlock;
        if (r1 == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
        } else if (r1 == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j6) & (-4294967296L);
            long j9 = j4 ^ j8;
            long j10 = j6 ^ j8;
            long j11 = (j5 ^ j7) & 281474976645120L;
            long j12 = j5 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-281470681808896L);
            long j15 = (j10 ^ j13) & (-281470681808896L);
            jArr2[0] = j9 ^ j14;
            jArr2[1] = j12 ^ j14;
            jArr2[2] = j10 ^ j15;
            jArr2[3] = j13 ^ j15;
        } else if (r1 != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        } else {
            long[] jArr3 = this.internalState;
            long j16 = jArr3[0];
            long j17 = jArr3[1];
            long j18 = jArr3[2];
            long j19 = jArr3[3];
            long j20 = jArr3[4];
            long j21 = jArr3[5];
            long j22 = jArr3[6];
            long j23 = jArr3[7];
            long j24 = (j16 ^ j20) & (-4294967296L);
            long j25 = j16 ^ j24;
            long j26 = j20 ^ j24;
            long j27 = (j17 ^ j21) & 72057594021150720L;
            long j28 = j17 ^ j27;
            long j29 = j21 ^ j27;
            long j30 = (j18 ^ j22) & 281474976645120L;
            long j31 = j18 ^ j30;
            long j32 = j22 ^ j30;
            long j33 = (j19 ^ j23) & 1099511627520L;
            long j34 = j19 ^ j33;
            long j35 = j23 ^ j33;
            long j36 = (j25 ^ j31) & (-281470681808896L);
            long j37 = j25 ^ j36;
            long j38 = j31 ^ j36;
            long j39 = (j28 ^ j34) & 72056494543077120L;
            long j40 = j28 ^ j39;
            long j41 = j34 ^ j39;
            long j42 = (j26 ^ j32) & (-281470681808896L);
            long j43 = j26 ^ j42;
            long j44 = j32 ^ j42;
            long j45 = (j29 ^ j35) & 72056494543077120L;
            long j46 = j29 ^ j45;
            long j47 = j35 ^ j45;
            long j48 = (j37 ^ j40) & (-71777214294589696L);
            long j49 = j37 ^ j48;
            long j50 = j40 ^ j48;
            long j51 = (j38 ^ j41) & (-71777214294589696L);
            long j52 = j38 ^ j51;
            long j53 = j41 ^ j51;
            long j54 = (j43 ^ j46) & (-71777214294589696L);
            long j55 = j43 ^ j54;
            long j56 = j46 ^ j54;
            long j57 = (j44 ^ j47) & (-71777214294589696L);
            jArr3[0] = j49;
            jArr3[1] = j50;
            jArr3[2] = j52;
            jArr3[3] = j53;
            jArr3[4] = j55;
            jArr3[5] = j56;
            jArr3[6] = j44 ^ j57;
            jArr3[7] = j47 ^ j57;
        }
    }

    private void subBytes() {
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr = this.internalState;
            long j = jArr[r0];
            int r4 = (int) j;
            int r3 = (int) (j >>> 32);
            byte[] bArr = f1916S0;
            byte b = bArr[r4 & 255];
            byte[] bArr2 = f1917S1;
            byte b2 = bArr2[(r4 >>> 8) & 255];
            byte[] bArr3 = f1918S2;
            byte b3 = bArr3[(r4 >>> 16) & 255];
            byte[] bArr4 = f1919S3;
            jArr[r0] = (((bArr4[r4 >>> 24] << Ascii.CAN) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16)) & BodyPartID.bodyIdMax) | (((((bArr[r3 & 255] & 255) | ((bArr2[(r3 >>> 8) & 255] & 255) << 8)) | ((bArr3[(r3 >>> 16) & 255] & 255) << 16)) | (bArr4[r3 >>> 24] << Ascii.CAN)) << 32);
        }
    }

    private void subRoundKey(int r7) {
        long[] jArr = this.roundKeys[r7];
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr2 = this.internalState;
            jArr2[r0] = jArr2[r0] - jArr[r0];
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int r9;
        int r92;
        int r1 = this.wordsInKey;
        long[] jArr3 = new long[r1];
        long[] jArr4 = new long[this.wordsInBlock];
        System.arraycopy(jArr, 0, jArr3, 0, r1);
        long j = 281479271743489L;
        int r7 = 0;
        while (true) {
            for (int r8 = 0; r8 < this.wordsInBlock; r8++) {
                jArr4[r8] = jArr2[r8] + j;
            }
            for (int r82 = 0; r82 < this.wordsInBlock; r82++) {
                this.internalState[r82] = jArr3[r82] + jArr4[r82];
            }
            subBytes();
            shiftRows();
            mixColumns();
            for (int r83 = 0; r83 < this.wordsInBlock; r83++) {
                long[] jArr5 = this.internalState;
                jArr5[r83] = jArr5[r83] ^ jArr4[r83];
            }
            subBytes();
            shiftRows();
            mixColumns();
            int r84 = 0;
            while (true) {
                r9 = this.wordsInBlock;
                if (r84 >= r9) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[r84] = jArr6[r84] + jArr4[r84];
                r84++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[r7], 0, r9);
            if (this.roundsAmount == r7) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                r7 += 2;
                j <<= 1;
                for (int r85 = 0; r85 < this.wordsInBlock; r85++) {
                    jArr4[r85] = jArr2[r85] + j;
                }
                int r86 = 0;
                while (true) {
                    int r93 = this.wordsInBlock;
                    if (r86 >= r93) {
                        break;
                    }
                    this.internalState[r86] = jArr3[r93 + r86] + jArr4[r86];
                    r86++;
                }
                subBytes();
                shiftRows();
                mixColumns();
                for (int r87 = 0; r87 < this.wordsInBlock; r87++) {
                    long[] jArr7 = this.internalState;
                    jArr7[r87] = jArr7[r87] ^ jArr4[r87];
                }
                subBytes();
                shiftRows();
                mixColumns();
                int r88 = 0;
                while (true) {
                    r92 = this.wordsInBlock;
                    if (r88 >= r92) {
                        break;
                    }
                    long[] jArr8 = this.internalState;
                    jArr8[r88] = jArr8[r88] + jArr4[r88];
                    r88++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[r7], 0, r92);
                if (this.roundsAmount == r7) {
                    return;
                }
            }
            r7 += 2;
            j <<= 1;
            long j2 = jArr3[0];
            for (int r10 = 1; r10 < r1; r10++) {
                jArr3[r10 - 1] = jArr3[r10];
            }
            jArr3[r1 - 1] = j2;
        }
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int r0 = this.wordsInBlock;
        long[] jArr3 = new long[r0];
        long[] jArr4 = new long[r0];
        long[] jArr5 = new long[r0];
        this.internalState = jArr5;
        long j = jArr5[0];
        int r7 = this.wordsInKey;
        jArr5[0] = j + r0 + r7 + 1;
        System.arraycopy(jArr, 0, jArr3, 0, r0);
        if (r0 == r7) {
            System.arraycopy(jArr, 0, jArr4, 0, r0);
        } else {
            int r02 = this.wordsInBlock;
            System.arraycopy(jArr, r02, jArr4, 0, r02);
        }
        int r11 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (r11 >= jArr6.length) {
                break;
            }
            jArr6[r11] = jArr6[r11] + jArr3[r11];
            r11++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int r112 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (r112 >= jArr7.length) {
                break;
            }
            jArr7[r112] = jArr7[r112] ^ jArr4[r112];
            r112++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int r113 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (r113 >= jArr8.length) {
                subBytes();
                shiftRows();
                mixColumns();
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
            jArr8[r113] = jArr8[r113] + jArr3[r113];
            r113++;
        }
    }

    private void workingKeyExpandOdd() {
        for (int r0 = 1; r0 < this.roundsAmount; r0 += 2) {
            long[][] jArr = this.roundKeys;
            rotateLeft(jArr[r0 - 1], jArr[r0]);
        }
    }

    private void xorRoundKey(int r7) {
        long[] jArr = this.roundKeys[r7];
        for (int r0 = 0; r0 < this.wordsInBlock; r0++) {
            long[] jArr2 = this.internalState;
            jArr2[r0] = jArr2[r0] ^ jArr[r0];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.wordsInBlock << 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005b A[LOOP:0: B:26:0x0056->B:28:0x005b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064 A[EDGE_INSN: B:37:0x0064->B:29:0x0064 ?: BREAK  , SYNTHETIC] */
    @Override // org.bouncycastle.crypto.BlockCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(boolean r5, org.bouncycastle.crypto.CipherParameters r6) throws java.lang.IllegalArgumentException {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.bouncycastle.crypto.params.KeyParameter
            if (r0 == 0) goto L8c
            r4.forEncryption = r5
            org.bouncycastle.crypto.params.KeyParameter r6 = (org.bouncycastle.crypto.params.KeyParameter) r6
            byte[] r5 = r6.getKey()
            int r6 = r5.length
            int r6 = r6 << 3
            int r0 = r4.wordsInBlock
            int r0 = r0 << 6
            r1 = 512(0x200, float:7.175E-43)
            r2 = 256(0x100, float:3.59E-43)
            r3 = 128(0x80, float:1.794E-43)
            if (r6 == r3) goto L28
            if (r6 == r2) goto L28
            if (r6 != r1) goto L20
            goto L28
        L20:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "unsupported key length: only 128/256/512 are allowed"
            r5.<init>(r6)
            throw r5
        L28:
            if (r6 == r0) goto L37
            int r0 = r0 * 2
            if (r6 != r0) goto L2f
            goto L37
        L2f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Unsupported key length"
            r5.<init>(r6)
            throw r5
        L37:
            if (r6 == r3) goto L44
            if (r6 == r2) goto L41
            if (r6 == r1) goto L3e
            goto L48
        L3e:
            r0 = 18
            goto L46
        L41:
            r0 = 14
            goto L46
        L44:
            r0 = 10
        L46:
            r4.roundsAmount = r0
        L48:
            int r0 = r6 >>> 6
            r4.wordsInKey = r0
            int r0 = r4.roundsAmount
            int r0 = r0 + 1
            long[][] r0 = new long[r0]
            r4.roundKeys = r0
            r0 = 0
            r1 = 0
        L56:
            long[][] r2 = r4.roundKeys
            int r3 = r2.length
            if (r1 >= r3) goto L64
            int r3 = r4.wordsInBlock
            long[] r3 = new long[r3]
            r2[r1] = r3
            int r1 = r1 + 1
            goto L56
        L64:
            int r1 = r4.wordsInKey
            long[] r1 = new long[r1]
            r4.workingKey = r1
            int r2 = r5.length
            int r6 = r6 >>> 3
            if (r2 != r6) goto L84
            org.bouncycastle.util.Pack.littleEndianToLong(r5, r0, r1)
            int r5 = r4.wordsInBlock
            long[] r5 = new long[r5]
            long[] r6 = r4.workingKey
            r4.workingKeyExpandKT(r6, r5)
            long[] r6 = r4.workingKey
            r4.workingKeyExpandEven(r6, r5)
            r4.workingKeyExpandOdd()
            return
        L84:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Invalid key parameter passed to DSTU7624Engine init"
            r5.<init>(r6)
            throw r5
        L8c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Invalid parameter passed to DSTU7624Engine init"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.DSTU7624Engine.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r5, byte[] bArr2, int r7) throws DataLengthException, IllegalStateException {
        int r4;
        if (this.workingKey != null) {
            if (getBlockSize() + r5 <= bArr.length) {
                if (getBlockSize() + r7 <= bArr2.length) {
                    int r2 = 0;
                    if (this.forEncryption) {
                        if (this.wordsInBlock != 2) {
                            Pack.littleEndianToLong(bArr, r5, this.internalState);
                            addRoundKey(0);
                            while (true) {
                                subBytes();
                                shiftRows();
                                mixColumns();
                                r2++;
                                r4 = this.roundsAmount;
                                if (r2 == r4) {
                                    break;
                                }
                                xorRoundKey(r2);
                            }
                            addRoundKey(r4);
                            Pack.longToLittleEndian(this.internalState, bArr2, r7);
                        } else {
                            encryptBlock_128(bArr, r5, bArr2, r7);
                        }
                    } else if (this.wordsInBlock != 2) {
                        Pack.littleEndianToLong(bArr, r5, this.internalState);
                        subRoundKey(this.roundsAmount);
                        int r42 = this.roundsAmount;
                        while (true) {
                            mixColumnsInv();
                            invShiftRows();
                            invSubBytes();
                            r42--;
                            if (r42 == 0) {
                                break;
                            }
                            xorRoundKey(r42);
                        }
                        subRoundKey(0);
                        Pack.longToLittleEndian(this.internalState, bArr2, r7);
                    } else {
                        decryptBlock_128(bArr, r5, bArr2, r7);
                    }
                    return getBlockSize();
                }
                throw new OutputLengthException("Output buffer too short");
            }
            throw new DataLengthException("Input buffer too short");
        }
        throw new IllegalStateException("DSTU7624Engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
    }
}
