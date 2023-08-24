package org.apache.commons.lang3.time;

import androidx.exifinterface.media.ExifInterface;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes5.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'";

    /* renamed from: y */
    static final Object f1580y = "y";

    /* renamed from: M */
    static final Object f1575M = "M";

    /* renamed from: d */
    static final Object f1577d = "d";

    /* renamed from: H */
    static final Object f1574H = "H";

    /* renamed from: m */
    static final Object f1578m = "m";

    /* renamed from: s */
    static final Object f1579s = "s";

    /* renamed from: S */
    static final Object f1576S = ExifInterface.LATITUDE_SOUTH;

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "HH:mm:ss.SSS");
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDuration(long j, String str, boolean z) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        Validate.inclusiveBetween(0L, Long.MAX_VALUE, j, "durationMillis must not be negative");
        Token[] lexx = lexx(str);
        if (Token.containsTokenWithValue(lexx, f1577d)) {
            long j8 = j / DateUtils.MILLIS_PER_DAY;
            j2 = j - (DateUtils.MILLIS_PER_DAY * j8);
            j3 = j8;
        } else {
            j2 = j;
            j3 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f1574H)) {
            long j9 = j2 / DateUtils.MILLIS_PER_HOUR;
            j2 -= DateUtils.MILLIS_PER_HOUR * j9;
            j4 = j9;
        } else {
            j4 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f1578m)) {
            long j10 = j2 / 60000;
            j2 -= 60000 * j10;
            j5 = j10;
        } else {
            j5 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f1579s)) {
            long j11 = j2 / 1000;
            j7 = j2 - (1000 * j11);
            j6 = j11;
        } else {
            j6 = 0;
            j7 = j2;
        }
        return format(lexx, 0L, 0L, j3, j4, j5, j6, j7, z);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String formatDuration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            formatDuration = " " + formatDuration;
            String replaceOnce = StringUtils.replaceOnce(formatDuration, " 0 days", "");
            if (replaceOnce.length() != formatDuration.length()) {
                String replaceOnce2 = StringUtils.replaceOnce(replaceOnce, " 0 hours", "");
                if (replaceOnce2.length() != replaceOnce.length()) {
                    formatDuration = StringUtils.replaceOnce(replaceOnce2, " 0 minutes", "");
                    if (formatDuration.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
                    }
                } else {
                    formatDuration = replaceOnce;
                }
            }
            if (!formatDuration.isEmpty()) {
                formatDuration = formatDuration.substring(1);
            }
        }
        if (z2) {
            String replaceOnce3 = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
            if (replaceOnce3.length() != formatDuration.length()) {
                formatDuration = StringUtils.replaceOnce(replaceOnce3, " 0 minutes", "");
                if (formatDuration.length() != replaceOnce3.length()) {
                    String replaceOnce4 = StringUtils.replaceOnce(formatDuration, " 0 hours", "");
                    if (replaceOnce4.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(replaceOnce4, " 0 days", "");
                    }
                } else {
                    formatDuration = replaceOnce3;
                }
            }
        }
        return StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(" " + formatDuration, " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int r4 = 0;
        Validate.isTrue(j <= j2, "startMillis must not be greater than endMillis", new Object[0]);
        Token[] lexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j2));
        int r2 = calendar2.get(14) - calendar.get(14);
        int r3 = calendar2.get(13) - calendar.get(13);
        int r7 = calendar2.get(12) - calendar.get(12);
        int r8 = calendar2.get(11) - calendar.get(11);
        int r10 = calendar2.get(5) - calendar.get(5);
        int r12 = calendar2.get(2) - calendar.get(2);
        int r13 = calendar2.get(1) - calendar.get(1);
        while (r2 < 0) {
            r2 += 1000;
            r3--;
        }
        while (r3 < 0) {
            r3 += 60;
            r7--;
        }
        while (r7 < 0) {
            r7 += 60;
            r8--;
        }
        while (r8 < 0) {
            r8 += 24;
            r10--;
        }
        if (Token.containsTokenWithValue(lexx, f1575M)) {
            while (r10 < 0) {
                r10 += calendar.getActualMaximum(5);
                r12--;
                calendar.add(2, 1);
            }
            while (r12 < 0) {
                r12 += 12;
                r13--;
            }
            if (!Token.containsTokenWithValue(lexx, f1580y) && r13 != 0) {
                while (r13 != 0) {
                    r12 += r13 * 12;
                    r13 = 0;
                }
            }
        } else {
            if (!Token.containsTokenWithValue(lexx, f1580y)) {
                int r132 = calendar2.get(1);
                if (r12 < 0) {
                    r132--;
                }
                while (calendar.get(1) != r132) {
                    int actualMaximum = r10 + (calendar.getActualMaximum(6) - calendar.get(6));
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum++;
                    }
                    calendar.add(1, 1);
                    r10 = actualMaximum + calendar.get(6);
                }
                r13 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                r10 += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            r12 = 0;
            while (r10 < 0) {
                r10 += calendar.getActualMaximum(5);
                r12--;
                calendar.add(2, 1);
            }
        }
        if (!Token.containsTokenWithValue(lexx, f1577d)) {
            r8 += r10 * 24;
            r10 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, f1574H)) {
            r7 += r8 * 60;
            r8 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, f1578m)) {
            r3 += r7 * 60;
            r7 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f1579s)) {
            r4 = r3;
        } else {
            r2 += r3 * 1000;
        }
        return format(lexx, r13, r12, r10, r8, r7, r4, r2, z);
    }

    static String format(Token[] tokenArr, long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z) {
        int r15;
        int r0;
        Token[] tokenArr2 = tokenArr;
        StringBuilder sb = new StringBuilder();
        int length = tokenArr2.length;
        int r7 = 0;
        boolean z2 = false;
        while (r7 < length) {
            Token token = tokenArr2[r7];
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuilder) {
                sb.append(value.toString());
                r0 = length;
                r15 = r7;
            } else {
                if (value.equals(f1580y)) {
                    sb.append(paddedValue(j, z, count));
                    r0 = length;
                    r15 = r7;
                } else {
                    if (value.equals(f1575M)) {
                        r15 = r7;
                        sb.append(paddedValue(j2, z, count));
                    } else {
                        r15 = r7;
                        if (value.equals(f1577d)) {
                            sb.append(paddedValue(j3, z, count));
                        } else if (value.equals(f1574H)) {
                            sb.append(paddedValue(j4, z, count));
                            r0 = length;
                        } else if (value.equals(f1578m)) {
                            sb.append(paddedValue(j5, z, count));
                            r0 = length;
                        } else {
                            if (value.equals(f1579s)) {
                                r0 = length;
                                sb.append(paddedValue(j6, z, count));
                                z2 = true;
                            } else {
                                r0 = length;
                                if (value.equals(f1576S)) {
                                    if (z2) {
                                        sb.append(paddedValue(j7, true, z ? Math.max(3, count) : 3));
                                    } else {
                                        sb.append(paddedValue(j7, z, count));
                                    }
                                    z2 = false;
                                }
                            }
                            r7 = r15 + 1;
                            length = r0;
                            tokenArr2 = tokenArr;
                        }
                    }
                    r0 = length;
                }
                z2 = false;
            }
            r7 = r15 + 1;
            length = r0;
            tokenArr2 = tokenArr;
        }
        return sb.toString();
    }

    private static String paddedValue(long j, boolean z, int r3) {
        String l = Long.toString(j);
        return z ? StringUtils.leftPad(l, r3, '0') : l;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static org.apache.commons.lang3.time.DurationFormatUtils.Token[] lexx(java.lang.String r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r9.length()
            r0.<init>(r1)
            r1 = 0
            r2 = 0
            r5 = r2
            r6 = r5
            r3 = 0
            r4 = 0
        Lf:
            int r7 = r9.length()
            if (r3 >= r7) goto L9f
            char r7 = r9.charAt(r3)
            r8 = 39
            if (r4 == 0) goto L24
            if (r7 == r8) goto L24
            r5.append(r7)
            goto L9b
        L24:
            if (r7 == r8) goto L6a
            r8 = 72
            if (r7 == r8) goto L67
            r8 = 77
            if (r7 == r8) goto L64
            r8 = 83
            if (r7 == r8) goto L61
            r8 = 100
            if (r7 == r8) goto L5e
            r8 = 109(0x6d, float:1.53E-43)
            if (r7 == r8) goto L5b
            r8 = 115(0x73, float:1.61E-43)
            if (r7 == r8) goto L58
            r8 = 121(0x79, float:1.7E-43)
            if (r7 == r8) goto L55
            if (r5 != 0) goto L51
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            org.apache.commons.lang3.time.DurationFormatUtils$Token r8 = new org.apache.commons.lang3.time.DurationFormatUtils$Token
            r8.<init>(r5)
            r0.add(r8)
        L51:
            r5.append(r7)
            goto L7e
        L55:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1580y
            goto L7f
        L58:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1579s
            goto L7f
        L5b:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1578m
            goto L7f
        L5e:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1577d
            goto L7f
        L61:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1576S
            goto L7f
        L64:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1575M
            goto L7f
        L67:
            java.lang.Object r7 = org.apache.commons.lang3.time.DurationFormatUtils.f1574H
            goto L7f
        L6a:
            if (r4 == 0) goto L70
            r5 = r2
            r7 = r5
            r4 = 0
            goto L7f
        L70:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            org.apache.commons.lang3.time.DurationFormatUtils$Token r4 = new org.apache.commons.lang3.time.DurationFormatUtils$Token
            r4.<init>(r5)
            r0.add(r4)
            r4 = 1
        L7e:
            r7 = r2
        L7f:
            if (r7 == 0) goto L9b
            if (r6 == 0) goto L91
            java.lang.Object r5 = r6.getValue()
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L91
            r6.increment()
            goto L9a
        L91:
            org.apache.commons.lang3.time.DurationFormatUtils$Token r5 = new org.apache.commons.lang3.time.DurationFormatUtils$Token
            r5.<init>(r7)
            r0.add(r5)
            r6 = r5
        L9a:
            r5 = r2
        L9b:
            int r3 = r3 + 1
            goto Lf
        L9f:
            if (r4 != 0) goto Lae
            int r9 = r0.size()
            org.apache.commons.lang3.time.DurationFormatUtils$Token[] r9 = new org.apache.commons.lang3.time.DurationFormatUtils.Token[r9]
            java.lang.Object[] r9 = r0.toArray(r9)
            org.apache.commons.lang3.time.DurationFormatUtils$Token[] r9 = (org.apache.commons.lang3.time.DurationFormatUtils.Token[]) r9
            return r9
        Lae:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unmatched quote in format: "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DurationFormatUtils.lexx(java.lang.String):org.apache.commons.lang3.time.DurationFormatUtils$Token[]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Token {
        private int count;
        private final Object value;

        static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token token : tokenArr) {
                if (token.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        Token(Object obj, int r2) {
            this.value = obj;
            this.count = r2;
        }

        void increment() {
            this.count++;
        }

        int getCount() {
            return this.count;
        }

        Object getValue() {
            return this.value;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Token) {
                Token token = (Token) obj;
                if (this.value.getClass() == token.value.getClass() && this.count == token.count) {
                    Object obj2 = this.value;
                    if (obj2 instanceof StringBuilder) {
                        return obj2.toString().equals(token.value.toString());
                    }
                    if (obj2 instanceof Number) {
                        return obj2.equals(token.value);
                    }
                    return obj2 == token.value;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }
    }
}
