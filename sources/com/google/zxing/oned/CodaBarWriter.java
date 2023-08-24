package com.google.zxing.oned;

import org.apache.commons.p028io.IOUtils;

/* loaded from: classes3.dex */
public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {IOUtils.DIR_SEPARATOR_UNIX, ':', '+', '.'};
    private static final char DEFAULT_GUARD;
    private static final char[] START_END_CHARS;

    static {
        char[] cArr = {'A', 'B', 'C', 'D'};
        START_END_CHARS = cArr;
        DEFAULT_GUARD = cArr[0];
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int r5;
        if (str.length() < 2) {
            StringBuilder sb = new StringBuilder();
            char c = DEFAULT_GUARD;
            sb.append(c);
            sb.append(str);
            sb.append(c);
            str = sb.toString();
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            char[] cArr = START_END_CHARS;
            boolean arrayContains = CodaBarReader.arrayContains(cArr, upperCase);
            boolean arrayContains2 = CodaBarReader.arrayContains(cArr, upperCase2);
            char[] cArr2 = ALT_START_END_CHARS;
            boolean arrayContains3 = CodaBarReader.arrayContains(cArr2, upperCase);
            boolean arrayContains4 = CodaBarReader.arrayContains(cArr2, upperCase2);
            if (arrayContains) {
                if (!arrayContains2) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
            } else if (arrayContains3) {
                if (!arrayContains4) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
            } else if (arrayContains2 || arrayContains4) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
            } else {
                StringBuilder sb2 = new StringBuilder();
                char c2 = DEFAULT_GUARD;
                sb2.append(c2);
                sb2.append(str);
                sb2.append(c2);
                str = sb2.toString();
            }
        }
        int r0 = 20;
        for (int r3 = 1; r3 < str.length() - 1; r3++) {
            if (Character.isDigit(str.charAt(r3)) || str.charAt(r3) == '-' || str.charAt(r3) == '$') {
                r0 += 9;
            } else if (!CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, str.charAt(r3))) {
                throw new IllegalArgumentException("Cannot encode : '" + str.charAt(r3) + '\'');
            } else {
                r0 += 10;
            }
        }
        boolean[] zArr = new boolean[r0 + (str.length() - 1)];
        int r4 = 0;
        for (int r32 = 0; r32 < str.length(); r32++) {
            char upperCase3 = Character.toUpperCase(str.charAt(r32));
            if (r32 == 0 || r32 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int r6 = 0;
            while (true) {
                if (r6 >= CodaBarReader.ALPHABET.length) {
                    r5 = 0;
                    break;
                } else if (upperCase3 == CodaBarReader.ALPHABET[r6]) {
                    r5 = CodaBarReader.CHARACTER_ENCODINGS[r6];
                    break;
                } else {
                    r6++;
                }
            }
            int r62 = 0;
            boolean z = true;
            while (true) {
                int r8 = 0;
                while (r62 < 7) {
                    zArr[r4] = z;
                    r4++;
                    if (((r5 >> (6 - r62)) & 1) == 0 || r8 == 1) {
                        z = !z;
                        r62++;
                    } else {
                        r8++;
                    }
                }
                break;
            }
            if (r32 < str.length() - 1) {
                zArr[r4] = false;
                r4++;
            }
        }
        return zArr;
    }
}
