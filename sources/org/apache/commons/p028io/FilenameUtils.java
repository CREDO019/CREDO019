package org.apache.commons.p028io;

import androidx.webkit.ProxyConfig;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/* renamed from: org.apache.commons.io.FilenameUtils */
/* loaded from: classes5.dex */
public class FilenameUtils {
    public static final char EXTENSION_SEPARATOR = '.';
    private static final int NOT_FOUND = -1;
    private static final char OTHER_SEPARATOR;
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

    private static boolean isSeparator(char c) {
        return c == '/' || c == '\\';
    }

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    public static String normalize(String str) {
        return doNormalize(str, SYSTEM_SEPARATOR, true);
    }

    public static String normalize(String str, boolean z) {
        return doNormalize(str, z ? '/' : '\\', true);
    }

    public static String normalizeNoEndSeparator(String str) {
        return doNormalize(str, SYSTEM_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String str, boolean z) {
        return doNormalize(str, z ? '/' : '\\', false);
    }

    private static String doNormalize(String str, char c, boolean z) {
        boolean z2;
        if (str == null) {
            return null;
        }
        failIfNullBytePresent(str);
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int prefixLength = getPrefixLength(str);
        if (prefixLength < 0) {
            return null;
        }
        int r5 = length + 2;
        char[] cArr = new char[r5];
        str.getChars(0, str.length(), cArr, 0);
        char c2 = SYSTEM_SEPARATOR;
        if (c == c2) {
            c2 = OTHER_SEPARATOR;
        }
        for (int r7 = 0; r7 < r5; r7++) {
            if (cArr[r7] == c2) {
                cArr[r7] = c;
            }
        }
        if (cArr[length - 1] != c) {
            cArr[length] = c;
            length++;
            z2 = false;
        } else {
            z2 = true;
        }
        int r72 = prefixLength + 1;
        int r9 = r72;
        while (r9 < length) {
            if (cArr[r9] == c) {
                int r10 = r9 - 1;
                if (cArr[r10] == c) {
                    System.arraycopy(cArr, r9, cArr, r10, length - r9);
                    length--;
                    r9--;
                }
            }
            r9++;
        }
        int r92 = r72;
        while (r92 < length) {
            if (cArr[r92] == c) {
                int r11 = r92 - 1;
                if (cArr[r11] == '.' && (r92 == r72 || cArr[r92 - 2] == c)) {
                    if (r92 == length - 1) {
                        z2 = true;
                    }
                    System.arraycopy(cArr, r92 + 1, cArr, r11, length - r92);
                    length -= 2;
                    r92--;
                }
            }
            r92++;
        }
        int r93 = prefixLength + 2;
        int r112 = r93;
        while (r112 < length) {
            if (cArr[r112] == c && cArr[r112 - 1] == '.' && cArr[r112 - 2] == '.' && (r112 == r93 || cArr[r112 - 3] == c)) {
                if (r112 == r93) {
                    return null;
                }
                if (r112 == length - 1) {
                    z2 = true;
                }
                int r12 = r112 - 4;
                while (true) {
                    if (r12 >= prefixLength) {
                        if (cArr[r12] == c) {
                            int r14 = r12 + 1;
                            System.arraycopy(cArr, r112 + 1, cArr, r14, length - r112);
                            length -= r112 - r12;
                            r112 = r14;
                            break;
                        }
                        r12--;
                    } else {
                        int r122 = r112 + 1;
                        System.arraycopy(cArr, r122, cArr, prefixLength, length - r112);
                        length -= r122 - prefixLength;
                        r112 = r72;
                        break;
                    }
                }
            }
            r112++;
        }
        if (length <= 0) {
            return "";
        }
        if (length <= prefixLength) {
            return new String(cArr, 0, length);
        }
        if (z2 && z) {
            return new String(cArr, 0, length);
        }
        return new String(cArr, 0, length - 1);
    }

    public static String concat(String str, String str2) {
        int prefixLength = getPrefixLength(str2);
        if (prefixLength < 0) {
            return null;
        }
        if (prefixLength > 0) {
            return normalize(str2);
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return normalize(str2);
        }
        if (isSeparator(str.charAt(length - 1))) {
            return normalize(str + str2);
        }
        return normalize(str + '/' + str2);
    }

    public static boolean directoryContains(String str, String str2) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Directory must not be null");
        }
        if (str2 == null || IOCase.SYSTEM.checkEquals(str, str2)) {
            return false;
        }
        return IOCase.SYSTEM.checkStartsWith(str2, str);
    }

    public static String separatorsToUnix(String str) {
        return (str == null || str.indexOf(92) == -1) ? str : str.replace('\\', '/');
    }

    public static String separatorsToWindows(String str) {
        return (str == null || str.indexOf(47) == -1) ? str : str.replace('/', '\\');
    }

    public static String separatorsToSystem(String str) {
        if (str == null) {
            return null;
        }
        if (isSystemWindows()) {
            return separatorsToWindows(str);
        }
        return separatorsToUnix(str);
    }

    public static int getPrefixLength(String str) {
        int min;
        if (str == null) {
            return -1;
        }
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        char charAt = str.charAt(0);
        if (charAt == ':') {
            return -1;
        }
        if (length == 1) {
            if (charAt == '~') {
                return 2;
            }
            return isSeparator(charAt) ? 1 : 0;
        }
        if (charAt == '~') {
            int indexOf = str.indexOf(47, 1);
            int indexOf2 = str.indexOf(92, 1);
            if (indexOf == -1 && indexOf2 == -1) {
                return length + 1;
            }
            if (indexOf == -1) {
                indexOf = indexOf2;
            }
            if (indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            min = Math.min(indexOf, indexOf2);
        } else {
            char charAt2 = str.charAt(1);
            if (charAt2 == ':') {
                char upperCase = Character.toUpperCase(charAt);
                return (upperCase < 'A' || upperCase > 'Z') ? upperCase == '/' ? 1 : -1 : (length == 2 || !isSeparator(str.charAt(2))) ? 2 : 3;
            } else if (!isSeparator(charAt) || !isSeparator(charAt2)) {
                return isSeparator(charAt) ? 1 : 0;
            } else {
                int indexOf3 = str.indexOf(47, 2);
                int indexOf4 = str.indexOf(92, 2);
                if ((indexOf3 == -1 && indexOf4 == -1) || indexOf3 == 2 || indexOf4 == 2) {
                    return -1;
                }
                if (indexOf3 == -1) {
                    indexOf3 = indexOf4;
                }
                if (indexOf4 == -1) {
                    indexOf4 = indexOf3;
                }
                min = Math.min(indexOf3, indexOf4);
            }
        }
        return min + 1;
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static int indexOfExtension(String str) {
        int lastIndexOf;
        if (str != null && indexOfLastSeparator(str) <= (lastIndexOf = str.lastIndexOf(46))) {
            return lastIndexOf;
        }
        return -1;
    }

    public static String getPrefix(String str) {
        int prefixLength;
        if (str != null && (prefixLength = getPrefixLength(str)) >= 0) {
            if (prefixLength > str.length()) {
                failIfNullBytePresent(str + '/');
                return str + '/';
            }
            String substring = str.substring(0, prefixLength);
            failIfNullBytePresent(substring);
            return substring;
        }
        return null;
    }

    public static String getPath(String str) {
        return doGetPath(str, 1);
    }

    public static String getPathNoEndSeparator(String str) {
        return doGetPath(str, 0);
    }

    private static String doGetPath(String str, int r4) {
        int prefixLength;
        if (str != null && (prefixLength = getPrefixLength(str)) >= 0) {
            int indexOfLastSeparator = indexOfLastSeparator(str);
            int r42 = r4 + indexOfLastSeparator;
            if (prefixLength >= str.length() || indexOfLastSeparator < 0 || prefixLength >= r42) {
                return "";
            }
            String substring = str.substring(prefixLength, r42);
            failIfNullBytePresent(substring);
            return substring;
        }
        return null;
    }

    public static String getFullPath(String str) {
        return doGetFullPath(str, true);
    }

    public static String getFullPathNoEndSeparator(String str) {
        return doGetFullPath(str, false);
    }

    private static String doGetFullPath(String str, boolean z) {
        int prefixLength;
        if (str != null && (prefixLength = getPrefixLength(str)) >= 0) {
            if (prefixLength >= str.length()) {
                return z ? getPrefix(str) : str;
            }
            int indexOfLastSeparator = indexOfLastSeparator(str);
            if (indexOfLastSeparator < 0) {
                return str.substring(0, prefixLength);
            }
            int r0 = indexOfLastSeparator + (z ? 1 : 0);
            if (r0 == 0) {
                r0++;
            }
            return str.substring(0, r0);
        }
        return null;
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        failIfNullBytePresent(str);
        return str.substring(indexOfLastSeparator(str) + 1);
    }

    private static void failIfNullBytePresent(String str) {
        int length = str.length();
        for (int r1 = 0; r1 < length; r1++) {
            if (str.charAt(r1) == 0) {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static String getBaseName(String str) {
        return removeExtension(getName(str));
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int indexOfExtension = indexOfExtension(str);
        return indexOfExtension == -1 ? "" : str.substring(indexOfExtension + 1);
    }

    public static String removeExtension(String str) {
        if (str == null) {
            return null;
        }
        failIfNullBytePresent(str);
        int indexOfExtension = indexOfExtension(str);
        return indexOfExtension == -1 ? str : str.substring(0, indexOfExtension);
    }

    public static boolean equals(String str, String str2) {
        return equals(str, str2, false, IOCase.SENSITIVE);
    }

    public static boolean equalsOnSystem(String str, String str2) {
        return equals(str, str2, false, IOCase.SYSTEM);
    }

    public static boolean equalsNormalized(String str, String str2) {
        return equals(str, str2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String str, String str2) {
        return equals(str, str2, true, IOCase.SYSTEM);
    }

    public static boolean equals(String str, String str2, boolean z, IOCase iOCase) {
        if (str == null || str2 == null) {
            return str == null && str2 == null;
        }
        if (z) {
            str = normalize(str);
            str2 = normalize(str2);
            if (str == null || str2 == null) {
                throw new NullPointerException("Error normalizing one or both of the file names");
            }
        }
        if (iOCase == null) {
            iOCase = IOCase.SENSITIVE;
        }
        return iOCase.checkEquals(str, str2);
    }

    public static boolean isExtension(String str, String str2) {
        if (str == null) {
            return false;
        }
        failIfNullBytePresent(str);
        if (str2 == null || str2.isEmpty()) {
            return indexOfExtension(str) == -1;
        }
        return getExtension(str).equals(str2);
    }

    public static boolean isExtension(String str, String[] strArr) {
        if (str == null) {
            return false;
        }
        failIfNullBytePresent(str);
        if (strArr == null || strArr.length == 0) {
            return indexOfExtension(str) == -1;
        }
        String extension = getExtension(str);
        for (String str2 : strArr) {
            if (extension.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExtension(String str, Collection<String> collection) {
        if (str == null) {
            return false;
        }
        failIfNullBytePresent(str);
        if (collection == null || collection.isEmpty()) {
            return indexOfExtension(str) == -1;
        }
        String extension = getExtension(str);
        for (String str2 : collection) {
            if (extension.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean wildcardMatch(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatchOnSystem(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SYSTEM);
    }

    public static boolean wildcardMatch(String str, String str2, IOCase iOCase) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        if (iOCase == null) {
            iOCase = IOCase.SENSITIVE;
        }
        String[] splitOnTokens = splitOnTokens(str2);
        Stack stack = new Stack();
        boolean z = false;
        int r4 = 0;
        int r5 = 0;
        do {
            if (stack.size() > 0) {
                int[] r3 = (int[]) stack.pop();
                r5 = r3[0];
                r4 = r3[1];
                z = true;
            }
            while (r5 < splitOnTokens.length) {
                if (splitOnTokens[r5].equals("?")) {
                    r4++;
                    if (r4 > str.length()) {
                        break;
                    }
                    z = false;
                    r5++;
                } else if (splitOnTokens[r5].equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
                    if (r5 == splitOnTokens.length - 1) {
                        r4 = str.length();
                    }
                    z = true;
                    r5++;
                } else {
                    if (z) {
                        r4 = iOCase.checkIndexOf(str, r4, splitOnTokens[r5]);
                        if (r4 == -1) {
                            break;
                        }
                        int checkIndexOf = iOCase.checkIndexOf(str, r4 + 1, splitOnTokens[r5]);
                        if (checkIndexOf >= 0) {
                            stack.push(new int[]{r5, checkIndexOf});
                        }
                        r4 += splitOnTokens[r5].length();
                        z = false;
                    } else {
                        if (!iOCase.checkRegionMatches(str, r4, splitOnTokens[r5])) {
                            break;
                        }
                        r4 += splitOnTokens[r5].length();
                        z = false;
                    }
                    r5++;
                }
            }
            if (r5 == splitOnTokens.length && r4 == str.length()) {
                return true;
            }
        } while (stack.size() > 0);
        return false;
    }

    static String[] splitOnTokens(String str) {
        if (str.indexOf(63) == -1 && str.indexOf(42) == -1) {
            return new String[]{str};
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int length = charArray.length;
        int r6 = 0;
        char c = 0;
        while (r6 < length) {
            char c2 = charArray[r6];
            if (c2 == '?' || c2 == '*') {
                if (sb.length() != 0) {
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }
                if (c2 == '?') {
                    arrayList.add("?");
                } else if (c != '*') {
                    arrayList.add(ProxyConfig.MATCH_ALL_SCHEMES);
                }
            } else {
                sb.append(c2);
            }
            r6++;
            c = c2;
        }
        if (sb.length() != 0) {
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
