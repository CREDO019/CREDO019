package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Ascii {
    public static final byte ACK = 6;
    public static final byte BEL = 7;

    /* renamed from: BS */
    public static final byte f1118BS = 8;
    public static final byte CAN = 24;
    private static final char CASE_MASK = ' ';

    /* renamed from: CR */
    public static final byte f1119CR = 13;
    public static final byte DC1 = 17;
    public static final byte DC2 = 18;
    public static final byte DC3 = 19;
    public static final byte DC4 = 20;
    public static final byte DEL = Byte.MAX_VALUE;
    public static final byte DLE = 16;

    /* renamed from: EM */
    public static final byte f1120EM = 25;
    public static final byte ENQ = 5;
    public static final byte EOT = 4;
    public static final byte ESC = 27;
    public static final byte ETB = 23;
    public static final byte ETX = 3;

    /* renamed from: FF */
    public static final byte f1121FF = 12;

    /* renamed from: FS */
    public static final byte f1122FS = 28;

    /* renamed from: GS */
    public static final byte f1123GS = 29;

    /* renamed from: HT */
    public static final byte f1124HT = 9;

    /* renamed from: LF */
    public static final byte f1125LF = 10;
    public static final char MAX = 127;
    public static final char MIN = 0;
    public static final byte NAK = 21;

    /* renamed from: NL */
    public static final byte f1126NL = 10;
    public static final byte NUL = 0;

    /* renamed from: RS */
    public static final byte f1127RS = 30;

    /* renamed from: SI */
    public static final byte f1128SI = 15;

    /* renamed from: SO */
    public static final byte f1129SO = 14;
    public static final byte SOH = 1;

    /* renamed from: SP */
    public static final byte f1130SP = 32;
    public static final byte SPACE = 32;
    public static final byte STX = 2;
    public static final byte SUB = 26;
    public static final byte SYN = 22;

    /* renamed from: US */
    public static final byte f1131US = 31;

    /* renamed from: VT */
    public static final byte f1132VT = 11;
    public static final byte XOFF = 19;
    public static final byte XON = 17;

    private static int getAlphaIndex(char c) {
        return (char) ((c | CASE_MASK) - 97);
    }

    public static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private Ascii() {
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        int r1 = 0;
        while (r1 < length) {
            if (isUpperCase(str.charAt(r1))) {
                char[] charArray = str.toCharArray();
                while (r1 < length) {
                    char c = charArray[r1];
                    if (isUpperCase(c)) {
                        charArray[r1] = (char) (c ^ CASE_MASK);
                    }
                    r1++;
                }
                return String.valueOf(charArray);
            }
            r1++;
        }
        return str;
    }

    public static String toLowerCase(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return toLowerCase((String) charSequence);
        }
        int length = charSequence.length();
        char[] cArr = new char[length];
        for (int r2 = 0; r2 < length; r2++) {
            cArr[r2] = toLowerCase(charSequence.charAt(r2));
        }
        return String.valueOf(cArr);
    }

    public static char toLowerCase(char c) {
        return isUpperCase(c) ? (char) (c ^ CASE_MASK) : c;
    }

    public static String toUpperCase(String str) {
        int length = str.length();
        int r1 = 0;
        while (r1 < length) {
            if (isLowerCase(str.charAt(r1))) {
                char[] charArray = str.toCharArray();
                while (r1 < length) {
                    char c = charArray[r1];
                    if (isLowerCase(c)) {
                        charArray[r1] = (char) (c ^ CASE_MASK);
                    }
                    r1++;
                }
                return String.valueOf(charArray);
            }
            r1++;
        }
        return str;
    }

    public static String toUpperCase(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return toUpperCase((String) charSequence);
        }
        int length = charSequence.length();
        char[] cArr = new char[length];
        for (int r2 = 0; r2 < length; r2++) {
            cArr[r2] = toUpperCase(charSequence.charAt(r2));
        }
        return String.valueOf(cArr);
    }

    public static char toUpperCase(char c) {
        return isLowerCase(c) ? (char) (c ^ CASE_MASK) : c;
    }

    public static String truncate(CharSequence charSequence, int r6, String str) {
        Preconditions.checkNotNull(charSequence);
        int length = r6 - str.length();
        Preconditions.checkArgument(length >= 0, "maxLength (%s) must be >= length of the truncation indicator (%s)", r6, str.length());
        int length2 = charSequence.length();
        String str2 = charSequence;
        if (length2 <= r6) {
            String charSequence2 = charSequence.toString();
            int length3 = charSequence2.length();
            str2 = charSequence2;
            if (length3 <= r6) {
                return charSequence2;
            }
        }
        StringBuilder sb = new StringBuilder(r6);
        sb.append((CharSequence) str2, 0, length);
        sb.append(str);
        return sb.toString();
    }

    public static boolean equalsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        int alphaIndex;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length != charSequence2.length()) {
            return false;
        }
        for (int r2 = 0; r2 < length; r2++) {
            char charAt = charSequence.charAt(r2);
            char charAt2 = charSequence2.charAt(r2);
            if (charAt != charAt2 && ((alphaIndex = getAlphaIndex(charAt)) >= 26 || alphaIndex != getAlphaIndex(charAt2))) {
                return false;
            }
        }
        return true;
    }
}
