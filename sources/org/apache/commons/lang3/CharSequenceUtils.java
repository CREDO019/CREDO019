package org.apache.commons.lang3;

/* loaded from: classes5.dex */
public class CharSequenceUtils {
    private static final int NOT_FOUND = -1;

    public static CharSequence subSequence(CharSequence charSequence, int r2) {
        if (charSequence == null) {
            return null;
        }
        return charSequence.subSequence(r2, charSequence.length());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int indexOf(CharSequence charSequence, int r8, int r9) {
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(r8, r9);
        }
        int length = charSequence.length();
        if (r9 < 0) {
            r9 = 0;
        }
        if (r8 < 65536) {
            for (int r2 = r9; r2 < length; r2++) {
                if (charSequence.charAt(r2) == r8) {
                    return r2;
                }
            }
        }
        if (r8 <= 1114111) {
            char[] chars = Character.toChars(r8);
            while (r9 < length - 1) {
                char charAt = charSequence.charAt(r9);
                int r4 = r9 + 1;
                char charAt2 = charSequence.charAt(r4);
                if (charAt == chars[0] && charAt2 == chars[1]) {
                    return r9;
                }
                r9 = r4;
            }
            return -1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int indexOf(CharSequence charSequence, CharSequence charSequence2, int r2) {
        return charSequence.toString().indexOf(charSequence2.toString(), r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lastIndexOf(CharSequence charSequence, int r6, int r7) {
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(r6, r7);
        }
        int length = charSequence.length();
        if (r7 < 0) {
            return -1;
        }
        if (r7 >= length) {
            r7 = length - 1;
        }
        if (r6 < 65536) {
            for (int r2 = r7; r2 >= 0; r2--) {
                if (charSequence.charAt(r2) == r6) {
                    return r2;
                }
            }
        }
        if (r6 <= 1114111) {
            char[] chars = Character.toChars(r6);
            if (r7 == length - 1) {
                return -1;
            }
            while (r7 >= 0) {
                char charAt = charSequence.charAt(r7);
                char charAt2 = charSequence.charAt(r7 + 1);
                if (chars[0] == charAt && chars[1] == charAt2) {
                    return r7;
                }
                r7--;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int r2) {
        return charSequence.toString().lastIndexOf(charSequence2.toString(), r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char[] toCharArray(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return ((String) charSequence).toCharArray();
        }
        int length = charSequence.length();
        char[] cArr = new char[charSequence.length()];
        for (int r2 = 0; r2 < length; r2++) {
            cArr[r2] = charSequence.charAt(r2);
        }
        return cArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean regionMatches(CharSequence charSequence, boolean z, int r9, CharSequence charSequence2, int r11, int r12) {
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).regionMatches(z, r9, (String) charSequence2, r11, r12);
        }
        int length = charSequence.length() - r9;
        int length2 = charSequence2.length() - r11;
        if (r9 < 0 || r11 < 0 || r12 < 0 || length < r12 || length2 < r12) {
            return false;
        }
        while (true) {
            int r0 = r12 - 1;
            if (r12 <= 0) {
                return true;
            }
            int r122 = r9 + 1;
            char charAt = charSequence.charAt(r9);
            int r1 = r11 + 1;
            char charAt2 = charSequence2.charAt(r11);
            if (charAt != charAt2) {
                if (!z) {
                    return false;
                }
                if (Character.toUpperCase(charAt) != Character.toUpperCase(charAt2) && Character.toLowerCase(charAt) != Character.toLowerCase(charAt2)) {
                    return false;
                }
            }
            r9 = r122;
            r12 = r0;
            r11 = r1;
        }
    }
}
