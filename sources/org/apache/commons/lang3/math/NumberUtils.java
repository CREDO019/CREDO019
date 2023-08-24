package org.apache.commons.lang3.math;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes5.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_TWO = 2;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    public static int compare(byte b, byte b2) {
        return b - b2;
    }

    public static int compare(int r0, int r1) {
        if (r0 == r1) {
            return 0;
        }
        return r0 < r1 ? -1 : 1;
    }

    public static int compare(long j, long j2) {
        int r0 = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (r0 == 0) {
            return 0;
        }
        return r0 < 0 ? -1 : 1;
    }

    public static int compare(short s, short s2) {
        if (s == s2) {
            return 0;
        }
        return s < s2 ? -1 : 1;
    }

    public static byte max(byte b, byte b2, byte b3) {
        if (b2 > b) {
            b = b2;
        }
        return b3 > b ? b3 : b;
    }

    public static int max(int r0, int r1, int r2) {
        if (r1 > r0) {
            r0 = r1;
        }
        return r2 > r0 ? r2 : r0;
    }

    public static long max(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static short max(short s, short s2, short s3) {
        if (s2 > s) {
            s = s2;
        }
        return s3 > s ? s3 : s;
    }

    public static byte min(byte b, byte b2, byte b3) {
        if (b2 < b) {
            b = b2;
        }
        return b3 < b ? b3 : b;
    }

    public static int min(int r0, int r1, int r2) {
        if (r1 < r0) {
            r0 = r1;
        }
        return r2 < r0 ? r2 : r0;
    }

    public static long min(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static short min(short s, short s2, short s3) {
        if (s2 < s) {
            s = s2;
        }
        return s3 < s ? s3 : s;
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int toInt(String str, int r1) {
        if (str == null) {
            return r1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return r1;
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0d);
    }

    public static double toDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static double toDouble(BigDecimal bigDecimal) {
        return toDouble(bigDecimal, 0.0d);
    }

    public static double toDouble(BigDecimal bigDecimal, double d) {
        return bigDecimal == null ? d : bigDecimal.doubleValue();
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte b) {
        if (str == null) {
            return b;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short s) {
        if (str == null) {
            return s;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    public static BigDecimal toScaledBigDecimal(BigDecimal bigDecimal) {
        return toScaledBigDecimal(bigDecimal, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(BigDecimal bigDecimal, int r1, RoundingMode roundingMode) {
        if (bigDecimal == null) {
            return BigDecimal.ZERO;
        }
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_EVEN;
        }
        return bigDecimal.setScale(r1, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(Float f) {
        return toScaledBigDecimal(f, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(Float f, int r3, RoundingMode roundingMode) {
        if (f == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(BigDecimal.valueOf(f.floatValue()), r3, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(Double d) {
        return toScaledBigDecimal(d, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(Double d, int r3, RoundingMode roundingMode) {
        if (d == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(BigDecimal.valueOf(d.doubleValue()), r3, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(String str) {
        return toScaledBigDecimal(str, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(String str, int r1, RoundingMode roundingMode) {
        if (str == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(createBigDecimal(str), r1, roundingMode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x0136, code lost:
        if (r1 == 'l') goto L72;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Number createNumber(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static String getMantissa(String str) {
        return getMantissa(str, str.length());
    }

    private static String getMantissa(String str, int r5) {
        char charAt = str.charAt(0);
        return charAt == '-' || charAt == '+' ? str.substring(1, r5) : str.substring(0, r5);
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        return !str.isEmpty();
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    public static BigInteger createBigInteger(String str) {
        int r0;
        if (str == null) {
            return null;
        }
        boolean startsWith = str.startsWith("-");
        int r3 = 16;
        if (str.startsWith("0x", startsWith ? 1 : 0) || str.startsWith("0X", startsWith ? 1 : 0)) {
            r0 = (startsWith ? 1 : 0) + 2;
        } else if (str.startsWith("#", startsWith ? 1 : 0)) {
            r0 = (startsWith ? 1 : 0) + 1;
        } else {
            if (str.startsWith(SessionDescription.SUPPORTED_SDP_VERSION, startsWith ? 1 : 0)) {
                int length = str.length();
                int r32 = (startsWith ? 1 : 0) + 1;
                if (length > r32) {
                    r0 = r32;
                    r3 = 8;
                }
            }
            r0 = startsWith ? 1 : 0;
            r3 = 10;
        }
        BigInteger bigInteger = new BigInteger(str.substring(r0), r3);
        return startsWith ? bigInteger.negate() : bigInteger;
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (str.trim().startsWith("--")) {
            throw new NumberFormatException(str + " is not a valid number.");
        }
        return new BigDecimal(str);
    }

    public static long min(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int r2 = 1; r2 < jArr.length; r2++) {
            if (jArr[r2] < j) {
                j = jArr[r2];
            }
        }
        return j;
    }

    public static int min(int... r3) {
        validateArray(r3);
        int r0 = r3[0];
        for (int r1 = 1; r1 < r3.length; r1++) {
            if (r3[r1] < r0) {
                r0 = r3[r1];
            }
        }
        return r0;
    }

    public static short min(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int r1 = 1; r1 < sArr.length; r1++) {
            if (sArr[r1] < s) {
                s = sArr[r1];
            }
        }
        return s;
    }

    public static byte min(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int r1 = 1; r1 < bArr.length; r1++) {
            if (bArr[r1] < b) {
                b = bArr[r1];
            }
        }
        return b;
    }

    public static double min(double... dArr) {
        validateArray(dArr);
        double d = dArr[0];
        for (int r2 = 1; r2 < dArr.length; r2++) {
            if (Double.isNaN(dArr[r2])) {
                return Double.NaN;
            }
            if (dArr[r2] < d) {
                d = dArr[r2];
            }
        }
        return d;
    }

    public static float min(float... fArr) {
        validateArray(fArr);
        float f = fArr[0];
        for (int r1 = 1; r1 < fArr.length; r1++) {
            if (Float.isNaN(fArr[r1])) {
                return Float.NaN;
            }
            if (fArr[r1] < f) {
                f = fArr[r1];
            }
        }
        return f;
    }

    public static long max(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int r2 = 1; r2 < jArr.length; r2++) {
            if (jArr[r2] > j) {
                j = jArr[r2];
            }
        }
        return j;
    }

    public static int max(int... r3) {
        validateArray(r3);
        int r0 = r3[0];
        for (int r1 = 1; r1 < r3.length; r1++) {
            if (r3[r1] > r0) {
                r0 = r3[r1];
            }
        }
        return r0;
    }

    public static short max(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int r1 = 1; r1 < sArr.length; r1++) {
            if (sArr[r1] > s) {
                s = sArr[r1];
            }
        }
        return s;
    }

    public static byte max(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int r1 = 1; r1 < bArr.length; r1++) {
            if (bArr[r1] > b) {
                b = bArr[r1];
            }
        }
        return b;
    }

    public static double max(double... dArr) {
        validateArray(dArr);
        double d = dArr[0];
        for (int r2 = 1; r2 < dArr.length; r2++) {
            if (Double.isNaN(dArr[r2])) {
                return Double.NaN;
            }
            if (dArr[r2] > d) {
                d = dArr[r2];
            }
        }
        return d;
    }

    public static float max(float... fArr) {
        validateArray(fArr);
        float f = fArr[0];
        for (int r1 = 1; r1 < fArr.length; r1++) {
            if (Float.isNaN(fArr[r1])) {
                return Float.NaN;
            }
            if (fArr[r1] > f) {
                f = fArr[r1];
            }
        }
        return f;
    }

    private static void validateArray(Object obj) {
        Validate.isTrue(obj != null, "The Array must not be null", new Object[0]);
        Validate.isTrue(Array.getLength(obj) != 0, "Array cannot be empty.", new Object[0]);
    }

    public static double min(double d, double d2, double d3) {
        return Math.min(Math.min(d, d2), d3);
    }

    public static float min(float f, float f2, float f3) {
        return Math.min(Math.min(f, f2), f3);
    }

    public static double max(double d, double d2, double d3) {
        return Math.max(Math.max(d, d2), d3);
    }

    public static float max(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    public static boolean isDigits(String str) {
        return StringUtils.isNumeric(str);
    }

    @Deprecated
    public static boolean isNumber(String str) {
        return isCreatable(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x00ea, code lost:
        if (r14 != false) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x00ec, code lost:
        if (r15 != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00ee, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00f0, code lost:
        if (r7 != false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x00f2, code lost:
        if (r13 == false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00f4, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0114, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0130, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00a2, code lost:
        if (r3 >= r0.length) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00a6, code lost:
        if (r0[r3] < '0') goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00aa, code lost:
        if (r0[r3] > '9') goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ac, code lost:
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00af, code lost:
        if (r0[r3] == 'e') goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00b3, code lost:
        if (r0[r3] != 'E') goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00b8, code lost:
        if (r0[r3] != '.') goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00ba, code lost:
        if (r15 != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00bc, code lost:
        if (r14 == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00bf, code lost:
        return r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00c0, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00c1, code lost:
        if (r7 != false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00c7, code lost:
        if (r0[r3] == 'd') goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00cd, code lost:
        if (r0[r3] == 'D') goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00d1, code lost:
        if (r0[r3] == 'f') goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00d7, code lost:
        if (r0[r3] != 'F') goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00d9, code lost:
        return r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00de, code lost:
        if (r0[r3] == 'l') goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00e4, code lost:
        if (r0[r3] != 'L') goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x00e7, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00e8, code lost:
        if (r13 == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isCreatable(java.lang.String r16) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.isCreatable(java.lang.String):boolean");
    }

    public static boolean isParsable(String str) {
        if (StringUtils.isEmpty(str) || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean withDecimalsParsing(String str, int r6) {
        int r1 = 0;
        while (r6 < str.length()) {
            boolean z = str.charAt(r6) == '.';
            if (z) {
                r1++;
            }
            if (r1 > 1) {
                return false;
            }
            if (!z && !Character.isDigit(str.charAt(r6))) {
                return false;
            }
            r6++;
        }
        return true;
    }
}
