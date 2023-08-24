package com.google.zxing.oned;

import com.canhub.cropper.CropImageOptions;
import com.facebook.imageutils.TiffUtil;
import com.google.common.base.Ascii;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes3.dex */
public final class Code93Reader extends OneDReader {
    private static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private final StringBuilder decodeRowResult = new StringBuilder(20);
    private final int[] counters = new int[6];

    static {
        int[] r0 = {276, 328, 324, 322, 296, 292, 290, 336, TiffUtil.TIFF_TAG_ORIENTATION, 266, 424, 420, 418, 404, 402, 394, CropImageOptions.DEGREES_360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = r0;
        ASTERISK_ENCODING = r0[47];
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r12, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] findAsteriskPattern;
        int nextSet = bitArray.getNextSet(findAsteriskPattern(bitArray)[1]);
        int size = bitArray.getSize();
        int[] r3 = this.counters;
        Arrays.fill(r3, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        while (true) {
            recordPattern(bitArray, nextSet, r3);
            int pattern = toPattern(r3);
            if (pattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char patternToChar = patternToChar(pattern);
            sb.append(patternToChar);
            int r9 = nextSet;
            for (int r10 : r3) {
                r9 += r10;
            }
            int nextSet2 = bitArray.getNextSet(r9);
            if (patternToChar == '*') {
                sb.deleteCharAt(sb.length() - 1);
                int r92 = 0;
                for (int r102 : r3) {
                    r92 += r102;
                }
                if (nextSet2 == size || !bitArray.get(nextSet2)) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (sb.length() < 2) {
                    throw NotFoundException.getNotFoundInstance();
                }
                checkChecksums(sb);
                sb.setLength(sb.length() - 2);
                float f = r12;
                return new Result(decodeExtended(sb), null, new ResultPoint[]{new ResultPoint((findAsteriskPattern[1] + findAsteriskPattern[0]) / 2.0f, f), new ResultPoint(nextSet + (r92 / 2.0f), f)}, BarcodeFormat.CODE_93);
            }
            nextSet = nextSet2;
        }
    }

    private int[] findAsteriskPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(this.counters, 0);
        int[] r3 = this.counters;
        int length = r3.length;
        int r5 = nextSet;
        boolean z = false;
        int r7 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                r3[r7] = r3[r7] + 1;
            } else {
                if (r7 != length - 1) {
                    r7++;
                } else if (toPattern(r3) == ASTERISK_ENCODING) {
                    return new int[]{r5, nextSet};
                } else {
                    r5 += r3[0] + r3[1];
                    int r8 = r7 - 1;
                    System.arraycopy(r3, 2, r3, 0, r8);
                    r3[r8] = 0;
                    r3[r7] = 0;
                    r7--;
                }
                r3[r7] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toPattern(int[] r7) {
        int r3 = 0;
        for (int r4 : r7) {
            r3 += r4;
        }
        int length = r7.length;
        int r42 = 0;
        for (int r2 = 0; r2 < length; r2++) {
            int round = Math.round((r7[r2] * 9.0f) / r3);
            if (round <= 0 || round > 4) {
                return -1;
            }
            if ((r2 & 1) == 0) {
                for (int r6 = 0; r6 < round; r6++) {
                    r42 = (r42 << 1) | 1;
                }
            } else {
                r42 <<= round;
            }
        }
        return r42;
    }

    private static char patternToChar(int r3) throws NotFoundException {
        int r0 = 0;
        while (true) {
            int[] r1 = CHARACTER_ENCODINGS;
            if (r0 < r1.length) {
                if (r1[r0] == r3) {
                    return ALPHABET[r0];
                }
                r0++;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int r5;
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int r3 = 0;
        while (r3 < length) {
            char charAt = charSequence.charAt(r3);
            if (charAt < 'a' || charAt > 'd') {
                sb.append(charAt);
            } else if (r3 >= length - 1) {
                throw FormatException.getFormatInstance();
            } else {
                r3++;
                char charAt2 = charSequence.charAt(r3);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            r5 = charAt2 - '@';
                            c = (char) r5;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 'b':
                        if (charAt2 >= 'A' && charAt2 <= 'E') {
                            r5 = charAt2 - '&';
                        } else if (charAt2 >= 'F' && charAt2 <= 'J') {
                            r5 = charAt2 - 11;
                        } else if (charAt2 >= 'K' && charAt2 <= 'O') {
                            r5 = charAt2 + 16;
                        } else if (charAt2 >= 'P' && charAt2 <= 'S') {
                            r5 = charAt2 + '+';
                        } else if (charAt2 >= 'T' && charAt2 <= 'Z') {
                            c = Ascii.MAX;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        c = (char) r5;
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            r5 = charAt2 - ' ';
                            c = (char) r5;
                            break;
                        } else if (charAt2 == 'Z') {
                            c = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            r5 = charAt2 + ' ';
                            c = (char) r5;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    default:
                        c = 0;
                        break;
                }
                sb.append(c);
            }
            r3++;
        }
        return sb.toString();
    }

    private static void checkChecksums(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        checkOneChecksum(charSequence, length - 2, 20);
        checkOneChecksum(charSequence, length - 1, 15);
    }

    private static void checkOneChecksum(CharSequence charSequence, int r7, int r8) throws ChecksumException {
        int r2 = 0;
        int r3 = 1;
        for (int r0 = r7 - 1; r0 >= 0; r0--) {
            r2 += ALPHABET_STRING.indexOf(charSequence.charAt(r0)) * r3;
            r3++;
            if (r3 > r8) {
                r3 = 1;
            }
        }
        if (charSequence.charAt(r7) != ALPHABET[r2 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
