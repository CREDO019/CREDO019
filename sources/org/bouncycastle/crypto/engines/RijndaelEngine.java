package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes5.dex */
public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;

    /* renamed from: A0 */
    private long f1952A0;

    /* renamed from: A1 */
    private long f1953A1;

    /* renamed from: A2 */
    private long f1954A2;

    /* renamed from: A3 */
    private long f1955A3;

    /* renamed from: BC */
    private int f1956BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, Ascii.f1120EM, 1, 50, 2, Ascii.SUB, -58, 75, -57, Ascii.ESC, 104, 51, -18, -33, 3, 100, 4, -32, Ascii.f1129SO, 52, -115, -127, -17, 76, 113, 8, -56, -8, 105, Ascii.f1122FS, -63, 125, -62, Ascii.f1123GS, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, 120, 101, 47, -118, 5, 33, Ascii.f1128SI, -31, 36, Ascii.DC2, -16, -126, 69, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, -15, SignedBytes.MAX_POWER_OF_TWO, 70, -125, 56, 102, -35, -3, 48, -65, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, 110, 72, -61, -93, -74, Ascii.f1127RS, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, Ascii.NAK, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, -89, 87, -81, 88, -88, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, Ascii.SYN, Ascii.f1132VT, -11, 89, -53, 95, -80, -100, -87, 81, -96, Byte.MAX_VALUE, Ascii.f1121FF, -10, 111, Ascii.ETB, -60, 73, -20, -40, 67, Ascii.f1131US, MultipartStream.DASH, -92, 118, 123, -73, -52, -69, 62, 90, -5, 96, -79, -122, 59, 82, -95, 108, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, Utf8.REPLACEMENT_BYTE, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, Ascii.DC4, 42, -98, 93, 86, -14, -45, -85, 68, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, -91, 103, 74, -19, -34, -59, 49, -2, Ascii.CAN, 13, 99, -116, Byte.MIN_VALUE, -64, -9, 112, 7};
    private static final byte[] aLogtable = {0, 3, 5, Ascii.f1128SI, 17, 51, 85, -1, Ascii.SUB, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, Ascii.f1127RS, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, Ascii.f1121FF, Ascii.DC4, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, Ascii.CAN, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, Byte.MAX_VALUE, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, Ascii.f1132VT, Ascii.f1123GS, 39, 105, -69, -42, 97, -93, -2, Ascii.f1120EM, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, Ascii.SYN, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, Ascii.NAK, Utf8.REPLACEMENT_BYTE, 65, -61, 94, -30, 61, 71, -55, SignedBytes.MAX_POWER_OF_TWO, -64, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, Byte.MIN_VALUE, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, Ascii.f1131US, 33, 99, -91, -12, 7, 9, Ascii.ESC, MultipartStream.DASH, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, Ascii.f1129SO, Ascii.DC2, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, Ascii.ETB, 57, 75, -35, 124, -124, -105, -94, -3, Ascii.f1122FS, 36, 108, -76, -57, 82, -10, 1, 3, 5, Ascii.f1128SI, 17, 51, 85, -1, Ascii.SUB, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, Ascii.f1127RS, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, Ascii.f1121FF, Ascii.DC4, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, Ascii.CAN, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, Byte.MAX_VALUE, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, Ascii.f1132VT, Ascii.f1123GS, 39, 105, -69, -42, 97, -93, -2, Ascii.f1120EM, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, Ascii.SYN, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, Ascii.NAK, Utf8.REPLACEMENT_BYTE, 65, -61, 94, -30, 61, 71, -55, SignedBytes.MAX_POWER_OF_TWO, -64, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, Byte.MIN_VALUE, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, Ascii.f1131US, 33, 99, -91, -12, 7, 9, Ascii.ESC, MultipartStream.DASH, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, Ascii.f1129SO, Ascii.DC2, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, Ascii.ETB, 57, 75, -35, 124, -124, -105, -94, -3, Ascii.f1122FS, 36, 108, -76, -57, 82, -10, 1};

    /* renamed from: S */
    private static final byte[] f1950S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, Ascii.f1121FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f1120EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f1132VT, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.f1122FS, -90, -76, -58, -24, -35, 116, Ascii.f1131US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f1129SO, 97, 53, 87, -71, -122, -63, Ascii.f1123GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f1127RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, MultipartStream.DASH, Ascii.f1128SI, -80, 84, -69, Ascii.SYN};

    /* renamed from: Si */
    private static final byte[] f1951Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, Ascii.f1132VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f1127RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.f1128SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f1122FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f1123GS, 41, -59, -119, 111, -73, 98, Ascii.f1129SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.f1131US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.f1120EM, -75, 74, 13, MultipartStream.DASH, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.f1121FF, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, Ascii.CAN, 32}};
    static byte[][] shifts1 = {new byte[]{0, Ascii.CAN, 16, 8}, new byte[]{0, 32, Ascii.CAN, 16}, new byte[]{0, 40, 32, Ascii.CAN}, new byte[]{0, 48, 40, Ascii.CAN}, new byte[]{0, 56, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int r3) {
        if (r3 == 128) {
            this.f1956BC = 32;
            this.BC_MASK = BodyPartID.bodyIdMax;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (r3 == 160) {
            this.f1956BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (r3 == 192) {
            this.f1956BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (r3 == 224) {
            this.f1956BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else if (r3 != 256) {
            throw new IllegalArgumentException("unknown blocksize to Rijndael");
        } else {
            this.f1956BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = r3;
    }

    private void InvMixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int r9 = 0; r9 < this.f1956BC; r9 += 8) {
            int r11 = (int) ((this.f1952A0 >> r9) & 255);
            int r10 = (int) ((this.f1953A1 >> r9) & 255);
            int r15 = (int) ((this.f1954A2 >> r9) & 255);
            long j5 = j4;
            int r8 = (int) ((this.f1955A3 >> r9) & 255);
            int r7 = -1;
            int r112 = r11 != 0 ? logtable[r11 & 255] & 255 : -1;
            int r102 = r10 != 0 ? logtable[r10 & 255] & 255 : -1;
            int r12 = r15 != 0 ? logtable[r15 & 255] & 255 : -1;
            if (r8 != 0) {
                r7 = logtable[r8 & 255] & 255;
            }
            j |= ((((mul0xe(r112) ^ mul0xb(r102)) ^ mul0xd(r12)) ^ mul0x9(r7)) & 255) << r9;
            j2 |= ((((mul0xe(r102) ^ mul0xb(r12)) ^ mul0xd(r7)) ^ mul0x9(r112)) & 255) << r9;
            j3 |= ((((mul0xe(r12) ^ mul0xb(r7)) ^ mul0xd(r112)) ^ mul0x9(r102)) & 255) << r9;
            j4 = j5 | (((((mul0xe(r7) ^ mul0xb(r112)) ^ mul0xd(r102)) ^ mul0x9(r12)) & 255) << r9);
        }
        this.f1952A0 = j;
        this.f1953A1 = j2;
        this.f1954A2 = j3;
        this.f1955A3 = j4;
    }

    private void KeyAddition(long[] jArr) {
        this.f1952A0 ^= jArr[0];
        this.f1953A1 ^= jArr[1];
        this.f1954A2 ^= jArr[2];
        this.f1955A3 ^= jArr[3];
    }

    private void MixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int r9 = 0; r9 < this.f1956BC; r9 += 8) {
            int r11 = (int) ((this.f1952A0 >> r9) & 255);
            int r10 = (int) ((this.f1953A1 >> r9) & 255);
            int r15 = (int) ((this.f1954A2 >> r9) & 255);
            int r8 = (int) ((this.f1955A3 >> r9) & 255);
            j |= ((((mul0x2(r11) ^ mul0x3(r10)) ^ r15) ^ r8) & 255) << r9;
            j2 |= ((((mul0x2(r10) ^ mul0x3(r15)) ^ r8) ^ r11) & 255) << r9;
            j3 |= ((((mul0x2(r15) ^ mul0x3(r8)) ^ r11) ^ r10) & 255) << r9;
            j4 |= ((((mul0x2(r8) ^ mul0x3(r11)) ^ r10) ^ r15) & 255) << r9;
        }
        this.f1952A0 = j;
        this.f1953A1 = j2;
        this.f1954A2 = j3;
        this.f1955A3 = j4;
    }

    private void ShiftRow(byte[] bArr) {
        this.f1953A1 = shift(this.f1953A1, bArr[1]);
        this.f1954A2 = shift(this.f1954A2, bArr[2]);
        this.f1955A3 = shift(this.f1955A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.f1952A0 = applyS(this.f1952A0, bArr);
        this.f1953A1 = applyS(this.f1953A1, bArr);
        this.f1954A2 = applyS(this.f1954A2, bArr);
        this.f1955A3 = applyS(this.f1955A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int r2 = 0; r2 < this.f1956BC; r2 += 8) {
            j2 |= (bArr[(int) ((j >> r2) & 255)] & 255) << r2;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(f1951Si);
        ShiftRow(this.shifts1SC);
        for (int r0 = this.ROUNDS - 1; r0 > 0; r0--) {
            KeyAddition(jArr[r0]);
            InvMixColumn();
            Substitution(f1951Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int r0 = 1; r0 < this.ROUNDS; r0++) {
            Substitution(f1950S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[r0]);
        }
        Substitution(f1950S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int r6;
        int r8;
        int r3 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance(long.class, 15, 4);
        int r9 = 4;
        if (length == 128) {
            r6 = 4;
        } else if (length == 160) {
            r6 = 5;
        } else if (length == 192) {
            r6 = 6;
        } else if (length == 224) {
            r6 = 7;
        } else if (length != 256) {
            throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        } else {
            r6 = 8;
        }
        this.ROUNDS = length >= this.blockBits ? r6 + 6 : (this.f1956BC / 8) + 6;
        char c = 0;
        int r10 = 0;
        int r11 = 0;
        while (r10 < bArr.length) {
            bArr2[r10 % 4][r10 / 4] = bArr[r11];
            r10++;
            r11++;
        }
        int r1 = 0;
        int r102 = 0;
        while (r1 < r6 && r102 < (this.ROUNDS + 1) * (this.f1956BC / 8)) {
            int r112 = 0;
            while (r112 < r9) {
                int r12 = this.f1956BC;
                long[] jArr2 = jArr[r102 / (r12 / 8)];
                jArr2[r112] = ((bArr2[r112][r1] & 255) << ((r102 * 8) % r12)) | jArr2[r112];
                r112++;
                r9 = 4;
            }
            r1++;
            r102++;
            r9 = 4;
        }
        int r13 = 0;
        while (r102 < (this.ROUNDS + 1) * (this.f1956BC / r3)) {
            int r7 = 0;
            while (r7 < 4) {
                byte[] bArr3 = bArr2[r7];
                r7++;
                bArr3[c] = (byte) (bArr3[c] ^ f1950S[bArr2[r7 % 4][r6 - 1] & 255]);
            }
            byte[] bArr4 = bArr2[c];
            int r122 = r13 + 1;
            bArr4[c] = (byte) (rcon[r13] ^ bArr4[c]);
            int r72 = 1;
            if (r6 <= 6) {
                while (r72 < r6) {
                    for (int r82 = 0; r82 < 4; r82++) {
                        byte[] bArr5 = bArr2[r82];
                        bArr5[r72] = (byte) (bArr5[r72] ^ bArr2[r82][r72 - 1]);
                    }
                    r72++;
                }
            } else {
                while (true) {
                    r8 = 4;
                    if (r72 >= 4) {
                        break;
                    }
                    int r92 = 0;
                    while (r92 < r8) {
                        byte[] bArr6 = bArr2[r92];
                        bArr6[r72] = (byte) (bArr6[r72] ^ bArr2[r92][r72 - 1]);
                        r92++;
                        r8 = 4;
                    }
                    r72++;
                }
                for (int r73 = 0; r73 < 4; r73++) {
                    byte[] bArr7 = bArr2[r73];
                    bArr7[4] = (byte) (bArr7[4] ^ f1950S[bArr2[r73][3] & 255]);
                }
                int r74 = 5;
                while (r74 < r6) {
                    int r93 = 0;
                    while (r93 < r8) {
                        byte[] bArr8 = bArr2[r93];
                        bArr8[r74] = (byte) (bArr8[r74] ^ bArr2[r93][r74 - 1]);
                        r93++;
                        r8 = 4;
                    }
                    r74++;
                    r8 = 4;
                }
            }
            int r75 = 0;
            while (r75 < r6 && r102 < (this.ROUNDS + 1) * (this.f1956BC / r3)) {
                for (int r83 = 0; r83 < 4; r83++) {
                    int r132 = this.f1956BC;
                    long[] jArr3 = jArr[r102 / (r132 / 8)];
                    jArr3[r83] = ((bArr2[r83][r75] & 255) << ((r102 * 8) % r132)) | jArr3[r83];
                }
                r75++;
                r102++;
                r3 = 8;
            }
            r13 = r122;
            c = 0;
            r3 = 8;
        }
        return jArr;
    }

    private byte mul0x2(int r3) {
        if (r3 != 0) {
            return aLogtable[(logtable[r3] & 255) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int r3) {
        if (r3 != 0) {
            return aLogtable[(logtable[r3] & 255) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int r2) {
        if (r2 >= 0) {
            return aLogtable[r2 + 199];
        }
        return (byte) 0;
    }

    private byte mul0xb(int r2) {
        if (r2 >= 0) {
            return aLogtable[r2 + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int r2) {
        if (r2 >= 0) {
            return aLogtable[r2 + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int r2) {
        if (r2 >= 0) {
            return aLogtable[r2 + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int r6) {
        for (int r0 = 0; r0 != this.f1956BC; r0 += 8) {
            int r1 = r6 + 1;
            bArr[r6] = (byte) (this.f1952A0 >> r0);
            int r62 = r1 + 1;
            bArr[r1] = (byte) (this.f1953A1 >> r0);
            int r12 = r62 + 1;
            bArr[r62] = (byte) (this.f1954A2 >> r0);
            r6 = r12 + 1;
            bArr[r12] = (byte) (this.f1955A3 >> r0);
        }
    }

    private long shift(long j, int r6) {
        return ((j << (this.f1956BC - r6)) | (j >>> r6)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int r8) {
        int r0;
        int r82;
        int r02;
        int r3;
        int r83;
        int r32;
        this.f1952A0 = bArr[r8] & 255;
        this.f1953A1 = bArr[r0] & 255;
        this.f1954A2 = bArr[r82] & 255;
        int r84 = r8 + 1 + 1 + 1 + 1;
        this.f1955A3 = bArr[r02] & 255;
        for (int r03 = 8; r03 != this.f1956BC; r03 += 8) {
            this.f1952A0 |= (bArr[r84] & 255) << r03;
            this.f1953A1 |= (bArr[r3] & 255) << r03;
            this.f1954A2 |= (bArr[r83] & 255) << r03;
            r84 = r84 + 1 + 1 + 1 + 1;
            this.f1955A3 |= (bArr[r32] & 255) << r03;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f1956BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            this.forEncryption = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r5, byte[] bArr2, int r7) {
        if (this.workingKey != null) {
            int r0 = this.f1956BC;
            if ((r0 / 2) + r5 <= bArr.length) {
                if ((r0 / 2) + r7 <= bArr2.length) {
                    boolean z = this.forEncryption;
                    unpackBlock(bArr, r5);
                    long[][] jArr = this.workingKey;
                    if (z) {
                        encryptBlock(jArr);
                    } else {
                        decryptBlock(jArr);
                    }
                    packBlock(bArr2, r7);
                    return this.f1956BC / 2;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Rijndael engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
