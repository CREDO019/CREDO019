package org.apache.commons.lang3.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* loaded from: classes5.dex */
public class WordUtils {
    public static String wrap(String str, int r3) {
        return wrap(str, r3, null, false);
    }

    public static String wrap(String str, int r2, String str2, boolean z) {
        return wrap(str, r2, str2, z, " ");
    }

    public static String wrap(String str, int r12, String str2, boolean z, String str3) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = System.lineSeparator();
        }
        if (r12 < 1) {
            r12 = 1;
        }
        if (StringUtils.isBlank(str3)) {
            str3 = " ";
        }
        Pattern compile = Pattern.compile(str3);
        int length = str.length();
        int r1 = 0;
        StringBuilder sb = new StringBuilder(length + 32);
        while (r1 < length) {
            int r3 = -1;
            int r6 = r1 + r12;
            Matcher matcher = compile.matcher(str.substring(r1, Math.min((int) Math.min(2147483647L, r6 + 1), length)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    r1 += matcher.end();
                } else {
                    r3 = matcher.start() + r1;
                }
            }
            if (length - r1 <= r12) {
                break;
            }
            while (matcher.find()) {
                r3 = matcher.start() + r1;
            }
            if (r3 >= r1) {
                sb.append((CharSequence) str, r1, r3);
                sb.append(str2);
            } else if (z) {
                sb.append((CharSequence) str, r1, r6);
                sb.append(str2);
                r1 = r6;
            } else {
                Matcher matcher2 = compile.matcher(str.substring(r6));
                if (matcher2.find()) {
                    r3 = matcher2.start() + r1 + r12;
                }
                if (r3 >= 0) {
                    sb.append((CharSequence) str, r1, r3);
                    sb.append(str2);
                } else {
                    sb.append((CharSequence) str, r1, str.length());
                    r1 = length;
                }
            }
            r1 = r3 + 1;
        }
        sb.append((CharSequence) str, r1, str.length());
        return sb.toString();
    }

    public static String capitalize(String str) {
        return capitalize(str, null);
    }

    public static String capitalize(String str, char... cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (StringUtils.isEmpty(str) || length == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int r2 = 0; r2 < charArray.length; r2++) {
            char c = charArray[r2];
            if (isDelimiter(c, cArr)) {
                z = true;
            } else if (z) {
                charArray[r2] = Character.toTitleCase(c);
                z = false;
            }
        }
        return new String(charArray);
    }

    public static String capitalizeFully(String str) {
        return capitalizeFully(str, null);
    }

    public static String capitalizeFully(String str, char... cArr) {
        return (StringUtils.isEmpty(str) || (cArr == null ? -1 : cArr.length) == 0) ? str : capitalize(str.toLowerCase(), cArr);
    }

    public static String uncapitalize(String str) {
        return uncapitalize(str, null);
    }

    public static String uncapitalize(String str, char... cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (StringUtils.isEmpty(str) || length == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int r2 = 0; r2 < charArray.length; r2++) {
            char c = charArray[r2];
            if (isDelimiter(c, cArr)) {
                z = true;
            } else if (z) {
                charArray[r2] = Character.toLowerCase(c);
                z = false;
            }
        }
        return new String(charArray);
    }

    public static String swapCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int r2 = 0; r2 < charArray.length; r2++) {
            char c = charArray[r2];
            if (Character.isUpperCase(c)) {
                charArray[r2] = Character.toLowerCase(c);
            } else if (Character.isTitleCase(c)) {
                charArray[r2] = Character.toLowerCase(c);
            } else {
                if (!Character.isLowerCase(c)) {
                    z = Character.isWhitespace(c);
                } else if (z) {
                    charArray[r2] = Character.toTitleCase(c);
                } else {
                    charArray[r2] = Character.toUpperCase(c);
                }
            }
            z = false;
        }
        return new String(charArray);
    }

    public static String initials(String str) {
        return initials(str, null);
    }

    public static String initials(String str, char... cArr) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (cArr == null || cArr.length != 0) {
            int length = str.length();
            char[] cArr2 = new char[(length / 2) + 1];
            int r5 = 0;
            boolean z = true;
            for (int r4 = 0; r4 < length; r4++) {
                char charAt = str.charAt(r4);
                if (isDelimiter(charAt, cArr)) {
                    z = true;
                } else if (z) {
                    cArr2[r5] = charAt;
                    r5++;
                    z = false;
                }
            }
            return new String(cArr2, 0, r5);
        }
        return "";
    }

    public static boolean containsAllWords(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (StringUtils.isEmpty(charSequence) || ArrayUtils.isEmpty(charSequenceArr)) {
            return false;
        }
        for (CharSequence charSequence2 : charSequenceArr) {
            if (StringUtils.isBlank(charSequence2)) {
                return false;
            }
            if (!Pattern.compile(".*\\b" + ((Object) charSequence2) + "\\b.*").matcher(charSequence).matches()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDelimiter(char c, char[] cArr) {
        if (cArr == null) {
            return Character.isWhitespace(c);
        }
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }
}
