package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.util.Arrays;

/* loaded from: classes3.dex */
public final class HighLevelEncoder {
    static final int ASCII_ENCODATION = 0;
    static final int BASE256_ENCODATION = 5;
    static final int C40_ENCODATION = 1;
    static final char C40_UNLATCH = 254;
    static final int EDIFACT_ENCODATION = 4;
    static final char LATCH_TO_ANSIX12 = 238;
    static final char LATCH_TO_BASE256 = 231;
    static final char LATCH_TO_C40 = 230;
    static final char LATCH_TO_EDIFACT = 240;
    static final char LATCH_TO_TEXT = 239;
    private static final char MACRO_05 = 236;
    private static final String MACRO_05_HEADER = "[)>\u001e05\u001d";
    private static final char MACRO_06 = 237;
    private static final String MACRO_06_HEADER = "[)>\u001e06\u001d";
    private static final String MACRO_TRAILER = "\u001e\u0004";
    private static final char PAD = 129;
    static final int TEXT_ENCODATION = 2;
    static final char UPPER_SHIFT = 235;
    static final int X12_ENCODATION = 3;
    static final char X12_UNLATCH = 254;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isExtendedASCII(char c) {
        return c >= 128 && c <= 255;
    }

    private static boolean isNativeC40(char c) {
        if (c != ' ') {
            if (c < '0' || c > '9') {
                return c >= 'A' && c <= 'Z';
            }
            return true;
        }
        return true;
    }

    private static boolean isNativeEDIFACT(char c) {
        return c >= ' ' && c <= '^';
    }

    private static boolean isNativeText(char c) {
        if (c != ' ') {
            if (c < '0' || c > '9') {
                return c >= 'a' && c <= 'z';
            }
            return true;
        }
        return true;
    }

    private static boolean isSpecialB256(char c) {
        return false;
    }

    private static boolean isX12TermSep(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    private HighLevelEncoder() {
    }

    private static char randomize253State(char c, int r1) {
        int r0 = c + ((r1 * 149) % 253) + 1;
        if (r0 > 254) {
            r0 -= 254;
        }
        return (char) r0;
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, SymbolShapeHint.FORCE_NONE, null, null);
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        int r2 = 0;
        Encoder[] encoderArr = {new ASCIIEncoder(), new C40Encoder(), new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext encoderContext = new EncoderContext(str);
        encoderContext.setSymbolShape(symbolShapeHint);
        encoderContext.setSizeConstraints(dimension, dimension2);
        if (str.startsWith(MACRO_05_HEADER) && str.endsWith(MACRO_TRAILER)) {
            encoderContext.writeCodeword(MACRO_05);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        } else if (str.startsWith(MACRO_06_HEADER) && str.endsWith(MACRO_TRAILER)) {
            encoderContext.writeCodeword(MACRO_06);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        }
        while (encoderContext.hasMoreCharacters()) {
            encoderArr[r2].encode(encoderContext);
            if (encoderContext.getNewEncoding() >= 0) {
                r2 = encoderContext.getNewEncoding();
                encoderContext.resetEncoderSignal();
            }
        }
        int codewordCount = encoderContext.getCodewordCount();
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity();
        if (codewordCount < dataCapacity && r2 != 0 && r2 != 5 && r2 != 4) {
            encoderContext.writeCodeword((char) 254);
        }
        StringBuilder codewords = encoderContext.getCodewords();
        if (codewords.length() < dataCapacity) {
            codewords.append(PAD);
        }
        while (codewords.length() < dataCapacity) {
            codewords.append(randomize253State(PAD, codewords.length() + 1));
        }
        return encoderContext.getCodewords().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lookAheadTest(CharSequence charSequence, int r18, int r19) {
        float[] fArr;
        char c;
        if (r18 >= charSequence.length()) {
            return r19;
        }
        if (r19 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[r19] = 0.0f;
        }
        int r5 = 0;
        while (true) {
            int r6 = r18 + r5;
            if (r6 == charSequence.length()) {
                byte[] bArr = new byte[6];
                int[] r1 = new int[6];
                int findMinimums = findMinimums(fArr, r1, Integer.MAX_VALUE, bArr);
                int minimumCount = getMinimumCount(bArr);
                if (r1[0] == findMinimums) {
                    return 0;
                }
                if (minimumCount != 1 || bArr[5] <= 0) {
                    if (minimumCount != 1 || bArr[4] <= 0) {
                        if (minimumCount != 1 || bArr[2] <= 0) {
                            return (minimumCount != 1 || bArr[3] <= 0) ? 1 : 3;
                        }
                        return 2;
                    }
                    return 4;
                }
                return 5;
            }
            char charAt = charSequence.charAt(r6);
            r5++;
            if (isDigit(charAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (isExtendedASCII(charAt)) {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 1.0f;
            }
            if (isNativeC40(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (isNativeText(charAt)) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (isNativeX12(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (isNativeEDIFACT(charAt)) {
                fArr[4] = fArr[4] + 0.75f;
            } else if (isExtendedASCII(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            if (isSpecialB256(charAt)) {
                c = 5;
                fArr[5] = fArr[5] + 4.0f;
            } else {
                c = 5;
                fArr[5] = fArr[5] + 1.0f;
            }
            if (r5 >= 4) {
                int[] r7 = new int[6];
                byte[] bArr2 = new byte[6];
                findMinimums(fArr, r7, Integer.MAX_VALUE, bArr2);
                int minimumCount2 = getMinimumCount(bArr2);
                if (r7[0] < r7[c] && r7[0] < r7[1] && r7[0] < r7[2] && r7[0] < r7[3] && r7[0] < r7[4]) {
                    return 0;
                }
                if (r7[5] < r7[0] || bArr2[1] + bArr2[2] + bArr2[3] + bArr2[4] == 0) {
                    return 5;
                }
                if (minimumCount2 == 1 && bArr2[4] > 0) {
                    return 4;
                }
                if (minimumCount2 == 1 && bArr2[2] > 0) {
                    return 2;
                }
                if (minimumCount2 == 1 && bArr2[3] > 0) {
                    return 3;
                }
                if (r7[1] + 1 < r7[0] && r7[1] + 1 < r7[5] && r7[1] + 1 < r7[4] && r7[1] + 1 < r7[2]) {
                    if (r7[1] < r7[3]) {
                        return 1;
                    }
                    if (r7[1] == r7[3]) {
                        for (int r12 = r18 + r5 + 1; r12 < charSequence.length(); r12++) {
                            char charAt2 = charSequence.charAt(r12);
                            if (isX12TermSep(charAt2)) {
                                return 3;
                            }
                            if (!isNativeX12(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
    }

    private static int findMinimums(float[] fArr, int[] r5, int r6, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        for (int r1 = 0; r1 < 6; r1++) {
            r5[r1] = (int) Math.ceil(fArr[r1]);
            int r2 = r5[r1];
            if (r6 > r2) {
                Arrays.fill(bArr, (byte) 0);
                r6 = r2;
            }
            if (r6 == r2) {
                bArr[r1] = (byte) (bArr[r1] + 1);
            }
        }
        return r6;
    }

    private static int getMinimumCount(byte[] bArr) {
        int r1 = 0;
        for (int r0 = 0; r0 < 6; r0++) {
            r1 += bArr[r0];
        }
        return r1;
    }

    private static boolean isNativeX12(char c) {
        if (isX12TermSep(c) || c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    public static int determineConsecutiveDigitCount(CharSequence charSequence, int r5) {
        int length = charSequence.length();
        int r1 = 0;
        if (r5 < length) {
            char charAt = charSequence.charAt(r5);
            while (isDigit(charAt) && r5 < length) {
                r1++;
                r5++;
                if (r5 < length) {
                    charAt = charSequence.charAt(r5);
                }
            }
        }
        return r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void illegalCharacter(char c) {
        String hexString;
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hexString.length()) + Integer.toHexString(c)) + ')');
    }
}
