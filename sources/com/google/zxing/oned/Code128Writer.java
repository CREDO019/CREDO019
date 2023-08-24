package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes3.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r4, int r5, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.CODE_128) {
            throw new IllegalArgumentException("Can only encode CODE_128, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return super.encode(str, barcodeFormat, r4, r5, map);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got ".concat(String.valueOf(length)));
        }
        int r1 = 0;
        for (int r2 = 0; r2 < length; r2++) {
            char charAt = str.charAt(r2);
            switch (charAt) {
                case 241:
                case 242:
                case 243:
                case 244:
                    break;
                default:
                    if (charAt <= 127) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Bad character in input: ".concat(String.valueOf(charAt)));
                    }
            }
        }
        ArrayList<int[]> arrayList = new ArrayList();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 1;
        while (true) {
            int r8 = 103;
            if (r4 < length) {
                int chooseCode = chooseCode(str, r4, r6);
                int r10 = 100;
                if (chooseCode == r6) {
                    switch (str.charAt(r4)) {
                        case 241:
                            r10 = 102;
                            break;
                        case 242:
                            r10 = 97;
                            break;
                        case 243:
                            r10 = 96;
                            break;
                        case 244:
                            if (r6 == 101) {
                                r10 = 101;
                                break;
                            }
                            break;
                        default:
                            if (r6 != 100) {
                                if (r6 != 101) {
                                    r10 = Integer.parseInt(str.substring(r4, r4 + 2));
                                    r4++;
                                    break;
                                } else {
                                    r10 = str.charAt(r4) - ' ';
                                    if (r10 < 0) {
                                        r10 += 96;
                                        break;
                                    }
                                }
                            } else {
                                r10 = str.charAt(r4) - ' ';
                                break;
                            }
                            break;
                    }
                    r4++;
                } else {
                    if (r6 != 0) {
                        r8 = chooseCode;
                    } else if (chooseCode == 100) {
                        r8 = 104;
                    } else if (chooseCode != 101) {
                        r8 = 105;
                    }
                    r10 = r8;
                    r6 = chooseCode;
                }
                arrayList.add(Code128Reader.CODE_PATTERNS[r10]);
                r5 += r10 * r7;
                if (r4 != 0) {
                    r7++;
                }
            } else {
                arrayList.add(Code128Reader.CODE_PATTERNS[r5 % 103]);
                arrayList.add(Code128Reader.CODE_PATTERNS[106]);
                int r0 = 0;
                for (int[] r42 : arrayList) {
                    for (int r72 : r42) {
                        r0 += r72;
                    }
                }
                boolean[] zArr = new boolean[r0];
                for (int[] r22 : arrayList) {
                    r1 += appendPattern(zArr, r1, r22, true);
                }
                return zArr;
            }
        }
    }

    private static CType findCType(CharSequence charSequence, int r5) {
        int length = charSequence.length();
        if (r5 >= length) {
            return CType.UNCODABLE;
        }
        char charAt = charSequence.charAt(r5);
        if (charAt == 241) {
            return CType.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return CType.UNCODABLE;
        }
        int r52 = r5 + 1;
        if (r52 >= length) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(r52);
        if (charAt2 < '0' || charAt2 > '9') {
            return CType.ONE_DIGIT;
        }
        return CType.TWO_DIGITS;
    }

    private static int chooseCode(CharSequence charSequence, int r4, int r5) {
        CType findCType;
        CType findCType2;
        char charAt;
        CType findCType3 = findCType(charSequence, r4);
        if (findCType3 == CType.ONE_DIGIT) {
            return 100;
        }
        if (findCType3 == CType.UNCODABLE) {
            return (r4 >= charSequence.length() || ((charAt = charSequence.charAt(r4)) >= ' ' && (r5 != 101 || charAt >= '`'))) ? 100 : 101;
        } else if (r5 == 99) {
            return 99;
        } else {
            if (r5 == 100) {
                if (findCType3 == CType.FNC_1 || (findCType = findCType(charSequence, r4 + 2)) == CType.UNCODABLE || findCType == CType.ONE_DIGIT) {
                    return 100;
                }
                if (findCType == CType.FNC_1) {
                    return findCType(charSequence, r4 + 3) == CType.TWO_DIGITS ? 99 : 100;
                }
                int r42 = r4 + 4;
                while (true) {
                    findCType2 = findCType(charSequence, r42);
                    if (findCType2 != CType.TWO_DIGITS) {
                        break;
                    }
                    r42 += 2;
                }
                return findCType2 == CType.ONE_DIGIT ? 100 : 99;
            }
            if (findCType3 == CType.FNC_1) {
                findCType3 = findCType(charSequence, r4 + 1);
            }
            return findCType3 == CType.TWO_DIGITS ? 99 : 100;
        }
    }
}
