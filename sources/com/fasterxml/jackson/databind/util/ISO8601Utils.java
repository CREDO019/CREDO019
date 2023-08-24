package com.fasterxml.jackson.databind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public class ISO8601Utils {
    @Deprecated
    private static final String GMT_ID = "GMT";
    @Deprecated
    private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");
    private static final TimeZone TIMEZONE_UTC;
    private static final TimeZone TIMEZONE_Z;
    private static final String UTC_ID = "UTC";

    static {
        TimeZone timeZone = TimeZone.getTimeZone(UTC_ID);
        TIMEZONE_UTC = timeZone;
        TIMEZONE_Z = timeZone;
    }

    @Deprecated
    public static TimeZone timeZoneGMT() {
        return TIMEZONE_GMT;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int r8 = offset / 60000;
            int abs = Math.abs(r8 / 60);
            int abs2 = Math.abs(r8 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append(Matrix.MATRIX_TYPE_ZERO);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf A[Catch: IllegalArgumentException -> 0x01a3, NumberFormatException -> 0x01a5, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01a7, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01a7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c9, B:49:0x00cf, B:51:0x00d5, B:71:0x016a, B:55:0x00df, B:56:0x00fa, B:57:0x00fb, B:59:0x010c, B:62:0x0115, B:64:0x0134, B:67:0x0143, B:68:0x0165, B:70:0x0168, B:73:0x019b, B:74:0x01a2, B:40:0x00b0, B:41:0x00b3), top: B:90:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x019b A[Catch: IllegalArgumentException -> 0x01a3, NumberFormatException -> 0x01a5, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01a7, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01a7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c9, B:49:0x00cf, B:51:0x00d5, B:71:0x016a, B:55:0x00df, B:56:0x00fa, B:57:0x00fb, B:59:0x010c, B:62:0x0115, B:64:0x0134, B:67:0x0143, B:68:0x0165, B:70:0x0168, B:73:0x019b, B:74:0x01a2, B:40:0x00b0, B:41:0x00b3), top: B:90:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date parse(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int r2, char c) {
        return r2 < str.length() && str.charAt(r2) == c;
    }

    private static int parseInt(String str, int r6, int r7) throws NumberFormatException {
        int r0;
        int r3;
        if (r6 < 0 || r7 > str.length() || r6 > r7) {
            throw new NumberFormatException(str);
        }
        if (r6 < r7) {
            r0 = r6 + 1;
            int digit = Character.digit(str.charAt(r6), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(r6, r7));
            }
            r3 = -digit;
        } else {
            r0 = r6;
            r3 = 0;
        }
        while (r0 < r7) {
            int r4 = r0 + 1;
            int digit2 = Character.digit(str.charAt(r0), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(r6, r7));
            }
            r3 = (r3 * 10) - digit2;
            r0 = r4;
        }
        return -r3;
    }

    private static void padInt(StringBuilder sb, int r2, int r3) {
        String num = Integer.toString(r2);
        for (int length = r3 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int r3) {
        while (r3 < str.length()) {
            char charAt = str.charAt(r3);
            if (charAt < '0' || charAt > '9') {
                return r3;
            }
            r3++;
        }
        return str.length();
    }
}
