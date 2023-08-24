package com.google.zxing.oned;

import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.TiffUtil;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
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
public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    static final int ASTERISK_ENCODING = 148;
    static final int[] CHARACTER_ENCODINGS = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, TiffUtil.TIFF_TAG_ORIENTATION, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, JfifUtil.MARKER_RST0, 133, 388, 196, 168, 162, TsExtractor.TS_STREAM_TYPE_DTS, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r12, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] findAsteriskPattern;
        String sb;
        int[] r14 = this.counters;
        Arrays.fill(r14, 0);
        StringBuilder sb2 = this.decodeRowResult;
        sb2.setLength(0);
        int nextSet = bitArray.getNextSet(findAsteriskPattern(bitArray, r14)[1]);
        int size = bitArray.getSize();
        while (true) {
            recordPattern(bitArray, nextSet, r14);
            int narrowWidePattern = toNarrowWidePattern(r14);
            if (narrowWidePattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char patternToChar = patternToChar(narrowWidePattern);
            sb2.append(patternToChar);
            int r9 = nextSet;
            for (int r10 : r14) {
                r9 += r10;
            }
            int nextSet2 = bitArray.getNextSet(r9);
            if (patternToChar == '*') {
                sb2.setLength(sb2.length() - 1);
                int r8 = 0;
                for (int r92 : r14) {
                    r8 += r92;
                }
                int r13 = (nextSet2 - nextSet) - r8;
                if (nextSet2 != size && (r13 << 1) < r8) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (this.usingCheckDigit) {
                    int length = sb2.length() - 1;
                    int r5 = 0;
                    for (int r142 = 0; r142 < length; r142++) {
                        r5 += ALPHABET_STRING.indexOf(this.decodeRowResult.charAt(r142));
                    }
                    if (sb2.charAt(length) != ALPHABET_STRING.charAt(r5 % 43)) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    sb2.setLength(length);
                }
                if (sb2.length() == 0) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (this.extendedMode) {
                    sb = decodeExtended(sb2);
                } else {
                    sb = sb2.toString();
                }
                float f = r12;
                return new Result(sb, null, new ResultPoint[]{new ResultPoint((findAsteriskPattern[1] + findAsteriskPattern[0]) / 2.0f, f), new ResultPoint(nextSet + (r8 / 2.0f), f)}, BarcodeFormat.CODE_39);
            }
            nextSet = nextSet2;
        }
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] r12) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int length = r12.length;
        int r4 = nextSet;
        boolean z = false;
        int r6 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                r12[r6] = r12[r6] + 1;
            } else {
                if (r6 != length - 1) {
                    r6++;
                } else if (toNarrowWidePattern(r12) == ASTERISK_ENCODING && bitArray.isRange(Math.max(0, r4 - ((nextSet - r4) / 2)), r4, false)) {
                    return new int[]{r4, nextSet};
                } else {
                    r4 += r12[0] + r12[1];
                    int r7 = r6 - 1;
                    System.arraycopy(r12, 2, r12, 0, r7);
                    r12[r7] = 0;
                    r12[r6] = 0;
                    r6--;
                }
                r12[r6] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] r10) {
        int length = r10.length;
        int r2 = 0;
        while (true) {
            int r3 = Integer.MAX_VALUE;
            for (int r6 : r10) {
                if (r6 < r3 && r6 > r2) {
                    r3 = r6;
                }
            }
            int r4 = 0;
            int r5 = 0;
            int r62 = 0;
            for (int r22 = 0; r22 < length; r22++) {
                int r7 = r10[r22];
                if (r7 > r3) {
                    r5 |= 1 << ((length - 1) - r22);
                    r4++;
                    r62 += r7;
                }
            }
            if (r4 == 3) {
                for (int r1 = 0; r1 < length && r4 > 0; r1++) {
                    int r23 = r10[r1];
                    if (r23 > r3) {
                        r4--;
                        if ((r23 << 1) >= r62) {
                            return -1;
                        }
                    }
                }
                return r5;
            } else if (r4 <= 3) {
                return -1;
            } else {
                r2 = r3;
            }
        }
    }

    private static char patternToChar(int r3) throws NotFoundException {
        int r0 = 0;
        while (true) {
            int[] r1 = CHARACTER_ENCODINGS;
            if (r0 >= r1.length) {
                if (r3 == ASTERISK_ENCODING) {
                    return '*';
                }
                throw NotFoundException.getNotFoundInstance();
            } else if (r1[r0] == r3) {
                return ALPHABET_STRING.charAt(r0);
            } else {
                r0++;
            }
        }
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int r9;
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int r3 = 0;
        while (r3 < length) {
            char charAt = charSequence.charAt(r3);
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                r3++;
                char charAt2 = charSequence.charAt(r3);
                if (charAt != '$') {
                    if (charAt != '%') {
                        if (charAt != '+') {
                            if (charAt == '/') {
                                if (charAt2 >= 'A' && charAt2 <= 'O') {
                                    r9 = charAt2 - ' ';
                                } else if (charAt2 != 'Z') {
                                    throw FormatException.getFormatInstance();
                                } else {
                                    c = ':';
                                    sb.append(c);
                                }
                            }
                            c = 0;
                            sb.append(c);
                        } else if (charAt2 < 'A' || charAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        } else {
                            r9 = charAt2 + ' ';
                        }
                    } else if (charAt2 >= 'A' && charAt2 <= 'E') {
                        r9 = charAt2 - '&';
                    } else if (charAt2 >= 'F' && charAt2 <= 'J') {
                        r9 = charAt2 - 11;
                    } else if (charAt2 >= 'K' && charAt2 <= 'O') {
                        r9 = charAt2 + 16;
                    } else if (charAt2 < 'P' || charAt2 > 'T') {
                        if (charAt2 != 'U') {
                            if (charAt2 == 'V') {
                                c = '@';
                            } else if (charAt2 == 'W') {
                                c = '`';
                            } else if (charAt2 != 'X' && charAt2 != 'Y' && charAt2 != 'Z') {
                                throw FormatException.getFormatInstance();
                            } else {
                                c = Ascii.MAX;
                            }
                            sb.append(c);
                        }
                        c = 0;
                        sb.append(c);
                    } else {
                        r9 = charAt2 + '+';
                    }
                } else if (charAt2 < 'A' || charAt2 > 'Z') {
                    throw FormatException.getFormatInstance();
                } else {
                    r9 = charAt2 - '@';
                }
                c = (char) r9;
                sb.append(c);
            } else {
                sb.append(charAt);
            }
            r3++;
        }
        return sb.toString();
    }
}
