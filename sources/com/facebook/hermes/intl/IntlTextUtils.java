package com.facebook.hermes.intl;

/* loaded from: classes.dex */
public class IntlTextUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static boolean isASCIIDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isASCIILetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isASCIILetterOrDigit(char c) {
        return isASCIILetter(c) || isASCIIDigit(c);
    }

    public static boolean isAlphaNum(CharSequence charSequence, int r4, int r5, int r6, int r7) {
        int r0;
        if (r5 < charSequence.length() && (r0 = (r5 - r4) + 1) >= r6 && r0 <= r7) {
            while (r4 <= r5) {
                if (!isASCIILetterOrDigit(charSequence.charAt(r4))) {
                    return false;
                }
                r4++;
            }
            return true;
        }
        return false;
    }

    public static boolean isAlpha(CharSequence charSequence, int r4, int r5, int r6, int r7) {
        int r0;
        if (r5 < charSequence.length() && (r0 = (r5 - r4) + 1) >= r6 && r0 <= r7) {
            while (r4 <= r5) {
                if (!isASCIILetter(charSequence.charAt(r4))) {
                    return false;
                }
                r4++;
            }
            return true;
        }
        return false;
    }

    public static boolean isDigit(CharSequence charSequence, int r4, int r5, int r6, int r7) {
        int r0;
        if (r5 < charSequence.length() && (r0 = (r5 - r4) + 1) >= r6 && r0 <= r7) {
            while (r4 <= r5) {
                if (!isASCIIDigit(charSequence.charAt(r4))) {
                    return false;
                }
                r4++;
            }
            return true;
        }
        return false;
    }

    public static boolean isDigitAlphanum3(CharSequence charSequence, int r4, int r5) {
        return (r5 - r4) + 1 == 4 && isASCIILetter(charSequence.charAt(r4)) && isAlphaNum(charSequence, r4 + 1, r5, 3, 3);
    }

    public static boolean isUnicodeLanguageSubtag(CharSequence charSequence, int r5, int r6) {
        if (isAlpha(charSequence, r5, r6, 2, 3) || isAlpha(charSequence, r5, r6, 5, 8)) {
            return true;
        }
        return (r6 - r5) + 1 == 4 && charSequence.charAt(r5) == 'r' && charSequence.charAt(r5 + 1) == 'o' && charSequence.charAt(r5 + 2) == 'o' && charSequence.charAt(r5 + 3) == 't';
    }

    public static boolean isExtensionSingleton(CharSequence charSequence, int r2, int r3) {
        return isAlphaNum(charSequence, r2, r3, 1, 1);
    }

    public static boolean isUnicodeScriptSubtag(CharSequence charSequence, int r2, int r3) {
        return isAlpha(charSequence, r2, r3, 4, 4);
    }

    public static boolean isUnicodeRegionSubtag(CharSequence charSequence, int r2, int r3) {
        return isAlpha(charSequence, r2, r3, 2, 2) || isDigit(charSequence, r2, r3, 3, 3);
    }

    public static boolean isUnicodeVariantSubtag(CharSequence charSequence, int r3, int r4) {
        return isAlphaNum(charSequence, r3, r4, 5, 8) || isDigitAlphanum3(charSequence, r3, r4);
    }

    public static boolean isUnicodeExtensionAttribute(CharSequence charSequence, int r3, int r4) {
        return isAlphaNum(charSequence, r3, r4, 3, 8);
    }

    public static boolean isUnicodeExtensionKey(CharSequence charSequence, int r3, int r4) {
        return r4 == r3 + 1 && isASCIILetterOrDigit(charSequence.charAt(r3)) && isASCIILetter(charSequence.charAt(r4));
    }

    public static boolean isUnicodeExtensionKeyTypeItem(CharSequence charSequence, int r3, int r4) {
        return isAlphaNum(charSequence, r3, r4, 3, 8);
    }

    public static boolean isTranformedExtensionTKey(CharSequence charSequence, int r3, int r4) {
        return r4 == r3 + 1 && isASCIILetter(charSequence.charAt(r3)) && isASCIIDigit(charSequence.charAt(r4));
    }

    public static boolean isTranformedExtensionTValueItem(CharSequence charSequence, int r3, int r4) {
        return isAlphaNum(charSequence, r3, r4, 3, 8);
    }

    public static boolean isPrivateUseExtension(CharSequence charSequence, int r3, int r4) {
        return isAlphaNum(charSequence, r3, r4, 1, 8);
    }

    public static boolean isOtherExtension(CharSequence charSequence, int r3, int r4) {
        return isAlphaNum(charSequence, r3, r4, 2, 8);
    }
}
