package com.google.zxing.datamatrix.encoder;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.canhub.cropper.CropImage;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import org.bouncycastle.math.Primes;

/* loaded from: classes3.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, 144, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, PsExtractor.VIDEO_STREAM_MASK, 92, 254}, new int[]{28, 24, 185, 166, 223, 248, 116, 255, 110, 61}, new int[]{175, TsExtractor.TS_STREAM_TYPE_DTS, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, TsExtractor.TS_STREAM_TYPE_DTS, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, TsExtractor.TS_STREAM_TYPE_AC3, 94, 254, JfifUtil.MARKER_APP1, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, TsExtractor.TS_STREAM_TYPE_AC4}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 251, 223, 155, 21, 5, TsExtractor.TS_STREAM_TYPE_AC4, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{Primes.SMALL_FACTOR_LIMIT, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, TsExtractor.TS_STREAM_TYPE_DTS, 110, 213, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, JfifUtil.MARKER_SOS, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, JfifUtil.MARKER_APP1, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 9, 5}, new int[]{245, 132, TsExtractor.TS_STREAM_TYPE_AC4, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, TsExtractor.TS_STREAM_TYPE_DTS, 186, PsExtractor.VIDEO_STREAM_MASK, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, 253, JfifUtil.MARKER_APP1, 19}, new int[]{175, 9, 223, 238, 12, 17, 220, JfifUtil.MARKER_RST0, 100, 29, 175, 170, 230, 192, JfifUtil.MARKER_RST7, 235, 150, 159, 36, 223, 38, 200, 132, 54, 228, 146, JfifUtil.MARKER_SOS, 234, 117, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE, 29, 232, 144, 238, 22, 150, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, 210, 39, 118, 202, 188, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, PsExtractor.PRIVATE_STREAM_1, 143, 108, 196, 37, 185, 112, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 230, 245, 63, 197, 190, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, JfifUtil.MARKER_SOS, 51, 63, 87, 10, 40, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE}, new int[]{220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, 213, 136, 248, RotationOptions.ROTATE_180, 234, 197, 158, 177, 68, 122, 93, 213, 15, 160, 227, 236, 66, 139, 153, 185, 202, 167, 179, 25, 220, 232, 96, 210, 231, 136, 223, 239, 181, 241, 59, 52, TsExtractor.TS_STREAM_TYPE_AC4, 25, 49, 232, Primes.SMALL_FACTOR_LIMIT, PsExtractor.PRIVATE_STREAM_1, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int r2 = 1;
        for (int r3 = 0; r3 < 255; r3++) {
            ALOG[r3] = r2;
            LOG[r2] = r3;
            r2 <<= 1;
            if (r2 >= 256) {
                r2 ^= MODULO_VALUE;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] r2 = new int[interleavedBlockCount];
            int[] r3 = new int[interleavedBlockCount];
            int[] r4 = new int[interleavedBlockCount];
            int r6 = 0;
            while (r6 < interleavedBlockCount) {
                int r7 = r6 + 1;
                r2[r6] = symbolInfo.getDataLengthForInterleavedBlock(r7);
                r3[r6] = symbolInfo.getErrorLengthForInterleavedBlock(r7);
                r4[r6] = 0;
                if (r6 > 0) {
                    r4[r6] = r4[r6 - 1] + r2[r6];
                }
                r6 = r7;
            }
            for (int r42 = 0; r42 < interleavedBlockCount; r42++) {
                StringBuilder sb2 = new StringBuilder(r2[r42]);
                for (int r72 = r42; r72 < symbolInfo.getDataCapacity(); r72 += interleavedBlockCount) {
                    sb2.append(str.charAt(r72));
                }
                String createECCBlock = createECCBlock(sb2.toString(), r3[r42]);
                int r73 = r42;
                int r8 = 0;
                while (r73 < r3[r42] * interleavedBlockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + r73, createECCBlock.charAt(r8));
                    r73 += interleavedBlockCount;
                    r8++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence charSequence, int r3) {
        return createECCBlock(charSequence, 0, charSequence.length(), r3);
    }

    private static String createECCBlock(CharSequence charSequence, int r12, int r13, int r14) {
        int r1 = 0;
        while (true) {
            int[] r2 = FACTOR_SETS;
            if (r1 >= r2.length) {
                r1 = -1;
                break;
            } else if (r2[r1] == r14) {
                break;
            } else {
                r1++;
            }
        }
        if (r1 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(r14)));
        }
        int[] r15 = FACTORS[r1];
        char[] cArr = new char[r14];
        for (int r3 = 0; r3 < r14; r3++) {
            cArr[r3] = 0;
        }
        for (int r32 = r12; r32 < r12 + r13; r32++) {
            int r4 = r14 - 1;
            int charAt = cArr[r4] ^ charSequence.charAt(r32);
            while (r4 > 0) {
                if (charAt != 0 && r15[r4] != 0) {
                    char c = cArr[r4 - 1];
                    int[] r7 = ALOG;
                    int[] r8 = LOG;
                    cArr[r4] = (char) (c ^ r7[(r8[charAt] + r8[r15[r4]]) % 255]);
                } else {
                    cArr[r4] = cArr[r4 - 1];
                }
                r4--;
            }
            if (charAt != 0 && r15[0] != 0) {
                int[] r42 = ALOG;
                int[] r6 = LOG;
                cArr[0] = (char) r42[(r6[charAt] + r6[r15[0]]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[r14];
        for (int r0 = 0; r0 < r14; r0++) {
            cArr2[r0] = cArr[(r14 - r0) - 1];
        }
        return String.valueOf(cArr2);
    }
}
