package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

/* loaded from: classes5.dex */
public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum ModifyType {
        TRUNCATE,
        ROUND,
        CEILING
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(11) == calendar2.get(11) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Date parseDate(String str, String... strArr) throws ParseException {
        return parseDate(str, null, strArr);
    }

    public static Date parseDate(String str, Locale locale, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, locale, strArr, true);
    }

    public static Date parseDateStrictly(String str, String... strArr) throws ParseException {
        return parseDateStrictly(str, null, strArr);
    }

    public static Date parseDateStrictly(String str, Locale locale, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, locale, strArr, false);
    }

    private static Date parseDateWithLeniency(String str, Locale locale, String[] strArr, boolean z) throws ParseException {
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        TimeZone timeZone = TimeZone.getDefault();
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        calendar.setLenient(z);
        for (String str2 : strArr) {
            FastDateParser fastDateParser = new FastDateParser(str2, timeZone, locale);
            calendar.clear();
            try {
                if (fastDateParser.parse(str, parsePosition, calendar) && parsePosition.getIndex() == str.length()) {
                    return calendar.getTime();
                }
            } catch (IllegalArgumentException unused) {
            }
            parsePosition.setIndex(0);
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    public static Date addYears(Date date, int r2) {
        return add(date, 1, r2);
    }

    public static Date addMonths(Date date, int r2) {
        return add(date, 2, r2);
    }

    public static Date addWeeks(Date date, int r2) {
        return add(date, 3, r2);
    }

    public static Date addDays(Date date, int r2) {
        return add(date, 5, r2);
    }

    public static Date addHours(Date date, int r2) {
        return add(date, 11, r2);
    }

    public static Date addMinutes(Date date, int r2) {
        return add(date, 12, r2);
    }

    public static Date addSeconds(Date date, int r2) {
        return add(date, 13, r2);
    }

    public static Date addMilliseconds(Date date, int r2) {
        return add(date, 14, r2);
    }

    private static Date add(Date date, int r2, int r3) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(r2, r3);
        return calendar.getTime();
    }

    public static Date setYears(Date date, int r2) {
        return set(date, 1, r2);
    }

    public static Date setMonths(Date date, int r2) {
        return set(date, 2, r2);
    }

    public static Date setDays(Date date, int r2) {
        return set(date, 5, r2);
    }

    public static Date setHours(Date date, int r2) {
        return set(date, 11, r2);
    }

    public static Date setMinutes(Date date, int r2) {
        return set(date, 12, r2);
    }

    public static Date setSeconds(Date date, int r2) {
        return set(date, 13, r2);
    }

    public static Date setMilliseconds(Date date, int r2) {
        return set(date, 14, r2);
    }

    private static Date set(Date date, int r3, int r4) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(r3, r4);
        return calendar.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar toCalendar(Date date, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        return calendar;
    }

    public static Date round(Date date, int r2) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, r2, ModifyType.ROUND);
        return calendar.getTime();
    }

    public static Calendar round(Calendar calendar, int r2) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, r2, ModifyType.ROUND);
        return calendar2;
    }

    public static Date round(Object obj, int r3) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return round((Date) obj, r3);
        }
        if (obj instanceof Calendar) {
            return round((Calendar) obj, r3).getTime();
        }
        throw new ClassCastException("Could not round " + obj);
    }

    public static Date truncate(Date date, int r2) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, r2, ModifyType.TRUNCATE);
        return calendar.getTime();
    }

    public static Calendar truncate(Calendar calendar, int r2) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, r2, ModifyType.TRUNCATE);
        return calendar2;
    }

    public static Date truncate(Object obj, int r3) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return truncate((Date) obj, r3);
        }
        if (obj instanceof Calendar) {
            return truncate((Calendar) obj, r3).getTime();
        }
        throw new ClassCastException("Could not truncate " + obj);
    }

    public static Date ceiling(Date date, int r2) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, r2, ModifyType.CEILING);
        return calendar.getTime();
    }

    public static Calendar ceiling(Calendar calendar, int r2) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, r2, ModifyType.CEILING);
        return calendar2;
    }

    public static Date ceiling(Object obj, int r3) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return ceiling((Date) obj, r3);
        }
        if (obj instanceof Calendar) {
            return ceiling((Calendar) obj, r3).getTime();
        }
        throw new ClassCastException("Could not find ceiling of for type: " + obj.getClass());
    }

    private static void modify(Calendar calendar, int r17, ModifyType modifyType) {
        int r4;
        boolean z;
        int r9;
        boolean z2;
        char c;
        if (calendar.get(1) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }
        if (r17 == 14) {
            return;
        }
        Date time = calendar.getTime();
        long time2 = time.getTime();
        int r42 = calendar.get(14);
        if (ModifyType.TRUNCATE == modifyType || r42 < 500) {
            time2 -= r42;
        }
        boolean z3 = r17 == 13;
        int r43 = calendar.get(13);
        if (!z3 && (ModifyType.TRUNCATE == modifyType || r43 < 30)) {
            time2 -= r43 * 1000;
        }
        if (r17 == 12) {
            z3 = true;
        }
        int r11 = calendar.get(12);
        if (!z3 && (ModifyType.TRUNCATE == modifyType || r11 < 30)) {
            time2 -= r11 * 60000;
        }
        if (time.getTime() != time2) {
            time.setTime(time2);
            calendar.setTime(time);
        }
        int[][] r5 = fields;
        int length = r5.length;
        int r7 = 0;
        boolean z4 = false;
        while (r7 < length) {
            int[] r10 = r5[r7];
            for (int r8 : r10) {
                if (r8 == r17) {
                    if (modifyType == ModifyType.CEILING || (modifyType == ModifyType.ROUND && z4)) {
                        if (r17 == 1001) {
                            if (calendar.get(5) == 1) {
                                calendar.add(5, 15);
                                return;
                            }
                            calendar.add(5, -15);
                            calendar.add(2, 1);
                            return;
                        } else if (r17 == 9) {
                            if (calendar.get(11) == 0) {
                                calendar.add(11, 12);
                                return;
                            }
                            calendar.add(11, -12);
                            calendar.add(5, 1);
                            return;
                        } else {
                            calendar.add(r10[0], 1);
                            return;
                        }
                    }
                    return;
                }
            }
            if (r17 != 9) {
                if (r17 == 1001 && r10[0] == 5) {
                    r4 = calendar.get(5) - 1;
                    if (r4 >= 15) {
                        r4 -= 15;
                    }
                    z = r4 > 7;
                    r9 = r4;
                    z2 = true;
                }
                z = z4;
                z2 = false;
                r9 = 0;
            } else {
                if (r10[0] == 11) {
                    r4 = calendar.get(11);
                    if (r4 >= 12) {
                        r4 -= 12;
                    }
                    z = r4 >= 6;
                    r9 = r4;
                    z2 = true;
                }
                z = z4;
                z2 = false;
                r9 = 0;
            }
            if (z2) {
                c = 0;
            } else {
                c = 0;
                int actualMinimum = calendar.getActualMinimum(r10[0]);
                int actualMaximum = calendar.getActualMaximum(r10[0]);
                int r12 = calendar.get(r10[0]) - actualMinimum;
                z = r12 > (actualMaximum - actualMinimum) / 2;
                r9 = r12;
            }
            if (r9 != 0) {
                calendar.set(r10[c], calendar.get(r10[c]) - r9);
            }
            r7++;
            z4 = z;
        }
        throw new IllegalArgumentException("The field " + r17 + " is not supported");
    }

    public static Iterator<Calendar> iterator(Date date, int r2) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return iterator(calendar, r2);
    }

    public static Iterator<Calendar> iterator(Calendar calendar, int r9) {
        Calendar truncate;
        Calendar truncate2;
        int r8;
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        int r1 = 2;
        switch (r9) {
            case 1:
            case 2:
            case 3:
            case 4:
                truncate = truncate(calendar, 5);
                truncate2 = truncate(calendar, 5);
                if (r9 != 2) {
                    if (r9 == 3) {
                        r1 = calendar.get(7);
                        r8 = r1 - 1;
                        break;
                    } else if (r9 == 4) {
                        r8 = calendar.get(7) + 3;
                        r1 = calendar.get(7) - 3;
                        break;
                    } else {
                        r8 = 7;
                        r1 = 1;
                        break;
                    }
                }
                r8 = 1;
                break;
            case 5:
            case 6:
                Calendar truncate3 = truncate(calendar, 2);
                Calendar calendar2 = (Calendar) truncate3.clone();
                calendar2.add(2, 1);
                calendar2.add(5, -1);
                if (r9 != 6) {
                    truncate2 = calendar2;
                    r1 = 1;
                    truncate = truncate3;
                    r8 = 7;
                    break;
                } else {
                    truncate2 = calendar2;
                    truncate = truncate3;
                    r8 = 1;
                    break;
                }
            default:
                throw new IllegalArgumentException("The range style " + r9 + " is not valid.");
        }
        if (r1 < 1) {
            r1 += 7;
        }
        if (r1 > 7) {
            r1 -= 7;
        }
        if (r8 < 1) {
            r8 += 7;
        }
        if (r8 > 7) {
            r8 -= 7;
        }
        while (truncate.get(7) != r1) {
            truncate.add(5, -1);
        }
        while (truncate2.get(7) != r8) {
            truncate2.add(5, 1);
        }
        return new DateIterator(truncate, truncate2);
    }

    public static Iterator<?> iterator(Object obj, int r3) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return iterator((Date) obj, r3);
        }
        if (obj instanceof Calendar) {
            return iterator((Calendar) obj, r3);
        }
        throw new ClassCastException("Could not iterate based on " + obj);
    }

    public static long getFragmentInMilliseconds(Date date, int r2) {
        return getFragment(date, r2, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInSeconds(Date date, int r2) {
        return getFragment(date, r2, TimeUnit.SECONDS);
    }

    public static long getFragmentInMinutes(Date date, int r2) {
        return getFragment(date, r2, TimeUnit.MINUTES);
    }

    public static long getFragmentInHours(Date date, int r2) {
        return getFragment(date, r2, TimeUnit.HOURS);
    }

    public static long getFragmentInDays(Date date, int r2) {
        return getFragment(date, r2, TimeUnit.DAYS);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int r2) {
        return getFragment(calendar, r2, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInSeconds(Calendar calendar, int r2) {
        return getFragment(calendar, r2, TimeUnit.SECONDS);
    }

    public static long getFragmentInMinutes(Calendar calendar, int r2) {
        return getFragment(calendar, r2, TimeUnit.MINUTES);
    }

    public static long getFragmentInHours(Calendar calendar, int r2) {
        return getFragment(calendar, r2, TimeUnit.HOURS);
    }

    public static long getFragmentInDays(Calendar calendar, int r2) {
        return getFragment(calendar, r2, TimeUnit.DAYS);
    }

    private static long getFragment(Date date, int r2, TimeUnit timeUnit) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, r2, timeUnit);
    }

    private static long getFragment(Calendar calendar, int r10, TimeUnit timeUnit) {
        long convert;
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        long j = 0;
        int r2 = timeUnit == TimeUnit.DAYS ? 0 : 1;
        if (r10 == 1) {
            convert = timeUnit.convert(calendar.get(6) - r2, TimeUnit.DAYS);
        } else {
            if (r10 == 2) {
                convert = timeUnit.convert(calendar.get(5) - r2, TimeUnit.DAYS);
            }
            if (r10 != 1 || r10 == 2 || r10 == 5 || r10 == 6) {
                j += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
            } else {
                switch (r10) {
                    case 11:
                        break;
                    case 12:
                        j += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
                        break;
                    case 13:
                        break;
                    case 14:
                        return j;
                    default:
                        throw new IllegalArgumentException("The fragment " + r10 + " is not supported");
                }
                return j + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            }
            j += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
            j += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
            return j + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
        }
        j = 0 + convert;
        if (r10 != 1) {
        }
        j += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
        j += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
        j += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
        return j + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int r2) {
        return truncatedCompareTo(calendar, calendar2, r2) == 0;
    }

    public static boolean truncatedEquals(Date date, Date date2, int r2) {
        return truncatedCompareTo(date, date2, r2) == 0;
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int r2) {
        return truncate(calendar, r2).compareTo(truncate(calendar2, r2));
    }

    public static int truncatedCompareTo(Date date, Date date2, int r2) {
        return truncate(date, r2).compareTo(truncate(date2, r2));
    }

    private static void validateDateNotNull(Date date) {
        Validate.isTrue(date != null, "The date must not be null", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class DateIterator implements Iterator<Calendar> {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            calendar.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public Calendar next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return (Calendar) this.spot.clone();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
