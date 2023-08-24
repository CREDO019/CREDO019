package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.common.base.Ascii;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class DESEngine implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private int[] workingKey = null;
    private static final short[] bytebit = {128, 64, 32, 16, 8, 4, 2, 1};
    private static final int[] bigbyte = {8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    private static final byte[] pc1 = {56, 48, 40, 32, Ascii.CAN, 16, 8, 0, 57, 49, 41, 33, Ascii.f1120EM, 17, 9, 1, 58, 50, 42, 34, Ascii.SUB, Ascii.DC2, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, Ascii.f1127RS, Ascii.SYN, Ascii.f1129SO, 6, 61, 53, MultipartStream.DASH, 37, Ascii.f1123GS, Ascii.NAK, 13, 5, 60, 52, 44, 36, Ascii.f1122FS, Ascii.DC4, Ascii.f1121FF, 4, Ascii.ESC, 19, Ascii.f1132VT, 3};
    private static final byte[] totrot = {1, 2, 4, 6, 8, 10, Ascii.f1121FF, Ascii.f1129SO, Ascii.f1128SI, 17, 19, Ascii.NAK, Ascii.ETB, Ascii.f1120EM, Ascii.ESC, Ascii.f1122FS};
    private static final byte[] pc2 = {13, 16, 10, Ascii.ETB, 0, 4, 2, Ascii.ESC, Ascii.f1129SO, 5, Ascii.DC4, 9, Ascii.SYN, Ascii.DC2, Ascii.f1132VT, 3, Ascii.f1120EM, 7, Ascii.f1128SI, 6, Ascii.SUB, 19, Ascii.f1121FF, 1, 40, 51, Ascii.f1127RS, 36, 46, 54, Ascii.f1123GS, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, MultipartStream.DASH, 41, 49, 35, Ascii.f1122FS, Ascii.f1131US};
    private static final int[] SP1 = {16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, AnalyticsListener.EVENT_PLAYER_RELEASED, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, InputDeviceCompat.SOURCE_TRACKBALL, 16777220, 16777220, InputDeviceCompat.SOURCE_TRACKBALL, 0, AnalyticsListener.EVENT_PLAYER_RELEASED, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, InputDeviceCompat.SOURCE_TRACKBALL, 16842752, 16778244, 16777220, AnalyticsListener.EVENT_PLAYER_RELEASED, 66564, 16843776, AnalyticsListener.EVENT_PLAYER_RELEASED, 16778240, 16778240, 0, InputDeviceCompat.SOURCE_TRACKBALL, 66560, 0, 16842756};
    private static final int[] SP2 = {-2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 1081344};
    private static final int[] SP3 = {520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, C1856C.BUFFER_FLAG_FIRST_SAMPLE, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, C1856C.BUFFER_FLAG_FIRST_SAMPLE, 134349312, C1856C.BUFFER_FLAG_FIRST_SAMPLE, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, C1856C.BUFFER_FLAG_FIRST_SAMPLE, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584};
    private static final int[] SP4 = {8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, TsExtractor.TS_STREAM_TYPE_AC3, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, TsExtractor.TS_STREAM_TYPE_AC3, 8388736, 8388609, 8396800, 8396929, TsExtractor.TS_STREAM_TYPE_AC3, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, TsExtractor.TS_STREAM_TYPE_AC3, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928};
    private static final int[] SP5 = {256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080};
    private static final int[] SP6 = {536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312};
    private static final int[] SP7 = {2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154};
    private static final int[] SP8 = {268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696};

    /* JADX INFO: Access modifiers changed from: protected */
    public void desFunc(int[] r23, byte[] bArr, int r25, byte[] bArr2, int r27) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, r25);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, r25 + 4);
        int r4 = ((bigEndianToInt >>> 4) ^ bigEndianToInt2) & 252645135;
        int r3 = bigEndianToInt2 ^ r4;
        int r2 = bigEndianToInt ^ (r4 << 4);
        int r42 = ((r2 >>> 16) ^ r3) & 65535;
        int r32 = r3 ^ r42;
        int r22 = r2 ^ (r42 << 16);
        int r43 = ((r32 >>> 2) ^ r22) & 858993459;
        int r24 = r22 ^ r43;
        int r33 = r32 ^ (r43 << 2);
        int r44 = ((r33 >>> 8) ^ r24) & 16711935;
        int r26 = r24 ^ r44;
        int r34 = r33 ^ (r44 << 8);
        int r35 = (r34 >>> 31) | (r34 << 1);
        int r45 = (r26 ^ r35) & (-1431655766);
        int r28 = r26 ^ r45;
        int r36 = r35 ^ r45;
        int r29 = (r28 >>> 31) | (r28 << 1);
        for (int r46 = 0; r46 < 8; r46++) {
            int r12 = r46 * 4;
            int r11 = ((r36 << 28) | (r36 >>> 4)) ^ r23[r12 + 0];
            int[] r13 = SP7;
            int r14 = r13[r11 & 63];
            int[] r15 = SP5;
            int r142 = r14 | r15[(r11 >>> 8) & 63];
            int[] r16 = SP3;
            int r143 = r142 | r16[(r11 >>> 16) & 63];
            int[] r17 = SP1;
            int r112 = r17[(r11 >>> 24) & 63] | r143;
            int r144 = r23[r12 + 1] ^ r36;
            int[] r18 = SP8;
            int r113 = r112 | r18[r144 & 63];
            int[] r19 = SP6;
            int r114 = r113 | r19[(r144 >>> 8) & 63];
            int[] r20 = SP4;
            int r115 = r114 | r20[(r144 >>> 16) & 63];
            int[] r21 = SP2;
            r29 ^= r115 | r21[(r144 >>> 24) & 63];
            int r116 = ((r29 << 28) | (r29 >>> 4)) ^ r23[r12 + 2];
            int r117 = r17[(r116 >>> 24) & 63];
            int r122 = r23[r12 + 3] ^ r29;
            r36 ^= ((((r117 | ((r13[r116 & 63] | r15[(r116 >>> 8) & 63]) | r16[(r116 >>> 16) & 63])) | r18[r122 & 63]) | r19[(r122 >>> 8) & 63]) | r20[(r122 >>> 16) & 63]) | r21[(r122 >>> 24) & 63];
        }
        int r37 = (r36 >>> 1) | (r36 << 31);
        int r47 = (r29 ^ r37) & (-1431655766);
        int r210 = r29 ^ r47;
        int r38 = r37 ^ r47;
        int r211 = (r210 >>> 1) | (r210 << 31);
        int r48 = ((r211 >>> 8) ^ r38) & 16711935;
        int r39 = r38 ^ r48;
        int r212 = r211 ^ (r48 << 8);
        int r49 = ((r212 >>> 2) ^ r39) & 858993459;
        int r310 = r39 ^ r49;
        int r213 = r212 ^ (r49 << 2);
        int r410 = ((r310 >>> 16) ^ r213) & 65535;
        int r214 = r213 ^ r410;
        int r311 = r310 ^ (r410 << 16);
        int r411 = ((r311 >>> 4) ^ r214) & 252645135;
        Pack.intToBigEndian(r311 ^ (r411 << 4), bArr2, r27);
        Pack.intToBigEndian(r214 ^ r411, bArr2, r27 + 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] generateWorkingKey(boolean z, byte[] bArr) {
        int r10;
        int[] r1 = new int[32];
        boolean[] zArr = new boolean[56];
        boolean[] zArr2 = new boolean[56];
        int r6 = 0;
        while (true) {
            boolean z2 = true;
            if (r6 >= 56) {
                break;
            }
            byte b = pc1[r6];
            if ((bytebit[b & 7] & bArr[b >>> 3]) == 0) {
                z2 = false;
            }
            zArr[r6] = z2;
            r6++;
        }
        for (int r15 = 0; r15 < 16; r15++) {
            int r62 = z ? r15 << 1 : (15 - r15) << 1;
            int r8 = r62 + 1;
            r1[r8] = 0;
            r1[r62] = 0;
            int r9 = 0;
            while (true) {
                if (r9 >= 28) {
                    break;
                }
                int r11 = totrot[r15] + r9;
                if (r11 < 28) {
                    zArr2[r9] = zArr[r11];
                } else {
                    zArr2[r9] = zArr[r11 - 28];
                }
                r9++;
            }
            for (r10 = 28; r10 < 56; r10++) {
                int r92 = totrot[r15] + r10;
                if (r92 < 56) {
                    zArr2[r10] = zArr[r92];
                } else {
                    zArr2[r10] = zArr[r92 - 28];
                }
            }
            for (int r93 = 0; r93 < 24; r93++) {
                byte[] bArr2 = pc2;
                if (zArr2[bArr2[r93]]) {
                    r1[r62] = r1[r62] | bigbyte[r93];
                }
                if (zArr2[bArr2[r93 + 24]]) {
                    r1[r8] = r1[r8] | bigbyte[r93];
                }
            }
        }
        for (int r5 = 0; r5 != 32; r5 += 2) {
            int r14 = r1[r5];
            int r152 = r5 + 1;
            int r2 = r1[r152];
            r1[r5] = ((16515072 & r2) >>> 10) | ((r14 & 16515072) << 6) | ((r14 & 4032) << 10) | ((r2 & 4032) >>> 6);
            r1[r152] = ((r14 & 63) << 16) | ((r14 & 258048) << 12) | ((258048 & r2) >>> 4) | (r2 & 63);
        }
        return r1;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to DES init - " + cipherParameters.getClass().getName());
        }
        KeyParameter keyParameter = (KeyParameter) cipherParameters;
        if (keyParameter.getKey().length > 8) {
            throw new IllegalArgumentException("DES key too long - should be 8 bytes");
        }
        this.workingKey = generateWorkingKey(z, keyParameter.getKey());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) {
        int[] r1 = this.workingKey;
        if (r1 != null) {
            if (r8 + 8 <= bArr.length) {
                if (r10 + 8 <= bArr2.length) {
                    desFunc(r1, bArr, r8, bArr2, r10);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("DES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
