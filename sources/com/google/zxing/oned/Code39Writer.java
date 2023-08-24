package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes3.dex */
public final class Code39Writer extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r4, int r5, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.CODE_39) {
            throw new IllegalArgumentException("Can only encode CODE_39, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return super.encode(str, barcodeFormat, r4, r5, map);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
        int r4 = 0;
        while (true) {
            if (r4 >= length) {
                break;
            } else if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(r4)) < 0) {
                str = tryToConvertToExtendedMode(str);
                length = str.length();
                if (length > 80) {
                    throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length + " (extended full ASCII mode)");
                }
            } else {
                r4++;
            }
        }
        int[] r2 = new int[9];
        int r42 = length + 25;
        for (int r6 = 0; r6 < length; r6++) {
            toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(r6))], r2);
            for (int r7 = 0; r7 < 9; r7++) {
                r42 += r2[r7];
            }
        }
        boolean[] zArr = new boolean[r42];
        toIntArray(148, r2);
        int appendPattern = appendPattern(zArr, 0, r2, true);
        int[] r8 = {1};
        int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, r8, false);
        for (int r9 = 0; r9 < length; r9++) {
            toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(r9))], r2);
            int appendPattern3 = appendPattern2 + appendPattern(zArr, appendPattern2, r2, true);
            appendPattern2 = appendPattern3 + appendPattern(zArr, appendPattern3, r8, false);
        }
        toIntArray(148, r2);
        appendPattern(zArr, appendPattern2, r2, true);
        return zArr;
    }

    private static void toIntArray(int r3, int[] r4) {
        for (int r0 = 0; r0 < 9; r0++) {
            int r2 = 1;
            if (((1 << (8 - r0)) & r3) != 0) {
                r2 = 2;
            }
            r4[r0] = r2;
        }
    }

    private static String tryToConvertToExtendedMode(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int r2 = 0; r2 < length; r2++) {
            char charAt = str.charAt(r2);
            if (charAt == 0) {
                sb.append("%U");
            } else {
                if (charAt != ' ') {
                    if (charAt == '@') {
                        sb.append("%V");
                    } else if (charAt == '`') {
                        sb.append("%W");
                    } else if (charAt != '-' && charAt != '.') {
                        if (charAt <= 26) {
                            sb.append('$');
                            sb.append((char) ((charAt - 1) + 65));
                        } else if (charAt < ' ') {
                            sb.append('%');
                            sb.append((char) ((charAt - 27) + 65));
                        } else if (charAt <= ',' || charAt == '/' || charAt == ':') {
                            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                            sb.append((char) ((charAt - '!') + 65));
                        } else if (charAt <= '9') {
                            sb.append((char) ((charAt - '0') + 48));
                        } else if (charAt <= '?') {
                            sb.append('%');
                            sb.append((char) ((charAt - ';') + 70));
                        } else if (charAt <= 'Z') {
                            sb.append((char) ((charAt - 'A') + 65));
                        } else if (charAt <= '_') {
                            sb.append('%');
                            sb.append((char) ((charAt - '[') + 75));
                        } else if (charAt <= 'z') {
                            sb.append('+');
                            sb.append((char) ((charAt - 'a') + 65));
                        } else if (charAt <= 127) {
                            sb.append('%');
                            sb.append((char) ((charAt - '{') + 80));
                        } else {
                            throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + str.charAt(r2) + "'");
                        }
                    }
                }
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
