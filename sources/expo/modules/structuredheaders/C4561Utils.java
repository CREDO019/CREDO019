package expo.modules.structuredheaders;

import java.util.Map;
import java.util.Objects;

/* renamed from: expo.modules.structuredheaders.Utils */
/* loaded from: classes3.dex */
public class C4561Utils {
    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isLcAlpha(char c) {
        return c >= 'a' && c <= 'z';
    }

    private C4561Utils() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String checkKey(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Key can not be null or empty");
        }
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if ((r1 == 0 && charAt != '*' && !isLcAlpha(charAt)) || (!isLcAlpha(charAt) && !isDigit(charAt) && charAt != '_' && charAt != '-' && charAt != '.' && charAt != '*')) {
                throw new IllegalArgumentException(String.format("Invalid character in key at position %d: '%c' (0x%04x)", Integer.valueOf(r1), Character.valueOf(charAt), Integer.valueOf(charAt)));
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Map<String, ListElement<? extends Object>> checkKeys(Map<String, ListElement<? extends Object>> map) {
        Objects.requireNonNull(map, "value must not be null");
        for (String str : map.keySet()) {
            checkKey(str);
        }
        return map;
    }
}
