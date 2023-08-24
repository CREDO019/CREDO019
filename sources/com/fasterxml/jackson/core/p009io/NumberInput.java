package com.fasterxml.jackson.core.p009io;

import java.math.BigDecimal;

/* renamed from: com.fasterxml.jackson.core.io.NumberInput */
/* loaded from: classes.dex */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    public static int parseInt(char[] cArr, int r5, int r6) {
        int r52;
        int r53;
        int r54;
        int r55;
        int r56;
        int r0 = cArr[r5] - '0';
        if (r6 > 4) {
            r5 = r5 + 1 + 1 + 1 + 1;
            r0 = (((((((r0 * 10) + (cArr[r52] - '0')) * 10) + (cArr[r53] - '0')) * 10) + (cArr[r54] - '0')) * 10) + (cArr[r5] - '0');
            r6 -= 4;
            if (r6 > 4) {
                int r57 = r5 + 1 + 1 + 1;
                return (((((((r0 * 10) + (cArr[r55] - '0')) * 10) + (cArr[r56] - '0')) * 10) + (cArr[r57] - '0')) * 10) + (cArr[r57 + 1] - '0');
            }
        }
        if (r6 > 1) {
            int r58 = r5 + 1;
            int r02 = (r0 * 10) + (cArr[r58] - '0');
            if (r6 > 2) {
                int r59 = r58 + 1;
                int r03 = (r02 * 10) + (cArr[r59] - '0');
                return r6 > 3 ? (r03 * 10) + (cArr[r59 + 1] - '0') : r03;
            }
            return r02;
        }
        return r0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0075, code lost:
        return java.lang.Integer.parseInt(r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parseInt(java.lang.String r8) {
        /*
            r0 = 0
            char r1 = r8.charAt(r0)
            int r2 = r8.length()
            r3 = 1
            r4 = 45
            if (r1 != r4) goto Lf
            r0 = 1
        Lf:
            r4 = 10
            if (r0 == 0) goto L25
            if (r2 == r3) goto L20
            if (r2 <= r4) goto L18
            goto L20
        L18:
            r1 = 2
            char r3 = r8.charAt(r3)
            r1 = r3
            r3 = 2
            goto L2e
        L20:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L25:
            r5 = 9
            if (r2 <= r5) goto L2e
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L2e:
            r5 = 57
            if (r1 > r5) goto L84
            r6 = 48
            if (r1 >= r6) goto L37
            goto L84
        L37:
            int r1 = r1 - r6
            if (r3 >= r2) goto L80
            int r7 = r3 + 1
            char r3 = r8.charAt(r3)
            if (r3 > r5) goto L7b
            if (r3 >= r6) goto L45
            goto L7b
        L45:
            int r1 = r1 * 10
            int r3 = r3 - r6
            int r1 = r1 + r3
            if (r7 >= r2) goto L80
            int r3 = r7 + 1
            char r7 = r8.charAt(r7)
            if (r7 > r5) goto L76
            if (r7 >= r6) goto L56
            goto L76
        L56:
            int r1 = r1 * 10
            int r7 = r7 - r6
            int r1 = r1 + r7
            if (r3 >= r2) goto L80
        L5c:
            int r7 = r3 + 1
            char r3 = r8.charAt(r3)
            if (r3 > r5) goto L71
            if (r3 >= r6) goto L67
            goto L71
        L67:
            int r1 = r1 * 10
            int r3 = r3 + (-48)
            int r1 = r1 + r3
            if (r7 < r2) goto L6f
            goto L80
        L6f:
            r3 = r7
            goto L5c
        L71:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L76:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L7b:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L80:
            if (r0 == 0) goto L83
            int r1 = -r1
        L83:
            return r1
        L84:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p009io.NumberInput.parseInt(java.lang.String):int");
    }

    public static long parseLong(char[] cArr, int r6, int r7) {
        int r72 = r7 - 9;
        return (parseInt(cArr, r6, r72) * 1000000000) + parseInt(cArr, r6 + r72, 9);
    }

    public static long parseLong(String str) {
        if (str.length() <= 9) {
            return parseInt(str);
        }
        return Long.parseLong(str);
    }

    public static boolean inLongRange(char[] cArr, int r6, int r7, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (r7 < length) {
            return true;
        }
        if (r7 > length) {
            return false;
        }
        for (int r72 = 0; r72 < length; r72++) {
            int charAt = cArr[r6 + r72] - str.charAt(r72);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int r1 = 0; r1 < length; r1++) {
            int charAt = str.charAt(r1) - str2.charAt(r1);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static int parseAsInt(String str, int r6) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return r6;
        }
        int r1 = 0;
        if (length > 0) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                r1 = 1;
            }
        }
        while (r1 < length) {
            char charAt2 = trim.charAt(r1);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return r6;
                }
            }
            r1++;
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused2) {
            return r6;
        }
    }

    public static long parseAsLong(String str, long j) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return j;
        }
        int r1 = 0;
        if (length > 0) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                r1 = 1;
            }
        }
        while (r1 < length) {
            char charAt2 = trim.charAt(r1);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return j;
                }
            }
            r1++;
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    public static double parseAsDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d;
        }
        try {
            return parseDouble(trim);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static double parseDouble(String str) throws NumberFormatException {
        if (NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    public static BigDecimal parseBigDecimal(String str) throws NumberFormatException {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException unused) {
            throw _badBD(str);
        }
    }

    public static BigDecimal parseBigDecimal(char[] cArr) throws NumberFormatException {
        return parseBigDecimal(cArr, 0, cArr.length);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int r2, int r3) throws NumberFormatException {
        try {
            return new BigDecimal(cArr, r2, r3);
        } catch (NumberFormatException unused) {
            throw _badBD(new String(cArr, r2, r3));
        }
    }

    private static NumberFormatException _badBD(String str) {
        return new NumberFormatException("Value \"" + str + "\" can not be represented as BigDecimal");
    }
}
