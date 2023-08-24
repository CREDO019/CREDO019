package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.exception.ExceptionUtils;

/* loaded from: classes5.dex */
public class FastDatePrinter implements DatePrinter, Serializable {
    public static final int FULL = 0;
    public static final int LONG = 1;
    private static final int MAX_DIGITS = 10;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface NumberRule extends Rule {
        void appendTo(Appendable appendable, int r2) throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface Rule {
        void appendTo(Appendable appendable, Calendar calendar) throws IOException;

        int estimateLength();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FastDatePrinter(String str, TimeZone timeZone, Locale locale) {
        this.mPattern = str;
        this.mTimeZone = timeZone;
        this.mLocale = locale;
        init();
    }

    private void init() {
        List<Rule> parsePattern = parsePattern();
        Rule[] ruleArr = (Rule[]) parsePattern.toArray(new Rule[parsePattern.size()]);
        this.mRules = ruleArr;
        int length = ruleArr.length;
        int r1 = 0;
        while (true) {
            length--;
            if (length >= 0) {
                r1 += this.mRules[length].estimateLength();
            } else {
                this.mMaxLengthEstimate = r1;
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v18, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v12, types: [org.apache.commons.lang3.time.FastDatePrinter$StringLiteral] */
    /* JADX WARN: Type inference failed for: r9v13, types: [org.apache.commons.lang3.time.FastDatePrinter$CharacterLiteral] */
    /* JADX WARN: Type inference failed for: r9v15, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v17, types: [org.apache.commons.lang3.time.FastDatePrinter$UnpaddedMonthField] */
    /* JADX WARN: Type inference failed for: r9v18, types: [org.apache.commons.lang3.time.FastDatePrinter$TwoDigitMonthField] */
    /* JADX WARN: Type inference failed for: r9v19, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v20, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v22, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v23, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v25, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v26, types: [org.apache.commons.lang3.time.FastDatePrinter$TwelveHourField] */
    /* JADX WARN: Type inference failed for: r9v27, types: [org.apache.commons.lang3.time.FastDatePrinter$TwentyFourHourField] */
    /* JADX WARN: Type inference failed for: r9v29, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v31, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v32, types: [org.apache.commons.lang3.time.FastDatePrinter$DayInWeekField] */
    /* JADX WARN: Type inference failed for: r9v34, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v36, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v41, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v42, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v45, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v46, types: [org.apache.commons.lang3.time.FastDatePrinter$NumberRule] */
    /* JADX WARN: Type inference failed for: r9v47, types: [org.apache.commons.lang3.time.FastDatePrinter$Iso8601_Rule] */
    /* JADX WARN: Type inference failed for: r9v50, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNumberRule] */
    /* JADX WARN: Type inference failed for: r9v51, types: [org.apache.commons.lang3.time.FastDatePrinter$Iso8601_Rule] */
    /* JADX WARN: Type inference failed for: r9v52, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNumberRule] */
    /* JADX WARN: Type inference failed for: r9v7, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNameRule] */
    /* JADX WARN: Type inference failed for: r9v8, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNameRule] */
    protected List<Rule> parsePattern() {
        int r9;
        WeekYear selectNumberRule;
        ?? timeZoneNameRule;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.mLocale);
        ArrayList arrayList = new ArrayList();
        String[] eras = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.mPattern.length();
        int[] r10 = new int[1];
        int r11 = 0;
        int r12 = 0;
        while (r12 < length) {
            r10[r11] = r12;
            String parseToken = parseToken(this.mPattern, r10);
            int r13 = r10[r11];
            int length2 = parseToken.length();
            if (length2 == 0) {
                return arrayList;
            }
            char charAt = parseToken.charAt(r11);
            if (charAt != 'y') {
                if (charAt != 'z') {
                    switch (charAt) {
                        case '\'':
                            String substring = parseToken.substring(1);
                            if (substring.length() == 1) {
                                timeZoneNameRule = new CharacterLiteral(substring.charAt(0));
                                break;
                            } else {
                                timeZoneNameRule = new StringLiteral(substring);
                                break;
                            }
                        case 'K':
                            timeZoneNameRule = selectNumberRule(10, length2);
                            break;
                        case 'M':
                            if (length2 < 4) {
                                if (length2 != 3) {
                                    if (length2 == 2) {
                                        timeZoneNameRule = TwoDigitMonthField.INSTANCE;
                                        break;
                                    } else {
                                        timeZoneNameRule = UnpaddedMonthField.INSTANCE;
                                        break;
                                    }
                                } else {
                                    timeZoneNameRule = new TextField(2, shortMonths);
                                    break;
                                }
                            } else {
                                timeZoneNameRule = new TextField(2, months);
                                break;
                            }
                        case 'S':
                            timeZoneNameRule = selectNumberRule(14, length2);
                            break;
                        case 'a':
                            timeZoneNameRule = new TextField(9, amPmStrings);
                            break;
                        case 'd':
                            timeZoneNameRule = selectNumberRule(5, length2);
                            break;
                        case 'h':
                            timeZoneNameRule = new TwelveHourField(selectNumberRule(10, length2));
                            break;
                        case 'k':
                            timeZoneNameRule = new TwentyFourHourField(selectNumberRule(11, length2));
                            break;
                        case 'm':
                            timeZoneNameRule = selectNumberRule(12, length2);
                            break;
                        case 's':
                            timeZoneNameRule = selectNumberRule(13, length2);
                            break;
                        case 'u':
                            timeZoneNameRule = new DayInWeekField(selectNumberRule(7, length2));
                            break;
                        case 'w':
                            timeZoneNameRule = selectNumberRule(3, length2);
                            break;
                        default:
                            switch (charAt) {
                                case 'D':
                                    timeZoneNameRule = selectNumberRule(6, length2);
                                    break;
                                case 'E':
                                    selectNumberRule = new TextField(7, length2 < 4 ? shortWeekdays : weekdays);
                                    r11 = 0;
                                    break;
                                case 'F':
                                    timeZoneNameRule = selectNumberRule(8, length2);
                                    break;
                                case 'G':
                                    r11 = 0;
                                    selectNumberRule = new TextField(0, eras);
                                    break;
                                case 'H':
                                    timeZoneNameRule = selectNumberRule(11, length2);
                                    break;
                                default:
                                    switch (charAt) {
                                        case 'W':
                                            timeZoneNameRule = selectNumberRule(4, length2);
                                            break;
                                        case 'X':
                                            timeZoneNameRule = Iso8601_Rule.getRule(length2);
                                            break;
                                        case 'Y':
                                            break;
                                        case 'Z':
                                            if (length2 != 1) {
                                                if (length2 == 2) {
                                                    timeZoneNameRule = Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
                                                    break;
                                                } else {
                                                    timeZoneNameRule = TimeZoneNumberRule.INSTANCE_COLON;
                                                    break;
                                                }
                                            } else {
                                                timeZoneNameRule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                                                break;
                                            }
                                        default:
                                            throw new IllegalArgumentException("Illegal pattern component: " + parseToken);
                                    }
                            }
                    }
                    arrayList.add(selectNumberRule);
                    r12 = r13 + 1;
                } else if (length2 >= 4) {
                    timeZoneNameRule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
                } else {
                    timeZoneNameRule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
                }
                selectNumberRule = timeZoneNameRule;
                r11 = 0;
                arrayList.add(selectNumberRule);
                r12 = r13 + 1;
            }
            r11 = 0;
            if (length2 == 2) {
                selectNumberRule = TwoDigitYearField.INSTANCE;
            } else {
                if (length2 < 4) {
                    r9 = 1;
                    length2 = 4;
                } else {
                    r9 = 1;
                }
                selectNumberRule = selectNumberRule(r9, length2);
            }
            if (charAt == 'Y') {
                selectNumberRule = new WeekYear(selectNumberRule);
            }
            arrayList.add(selectNumberRule);
            r12 = r13 + 1;
        }
        return arrayList;
    }

    protected String parseToken(String str, int[] r15) {
        StringBuilder sb = new StringBuilder();
        int r2 = r15[0];
        int length = str.length();
        char charAt = str.charAt(r2);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int r5 = r2 + 1;
                if (r5 >= length || str.charAt(r5) != charAt) {
                    break;
                }
                sb.append(charAt);
                r2 = r5;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (r2 < length) {
                char charAt2 = str.charAt(r2);
                if (charAt2 != '\'') {
                    if (!z && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        r2--;
                        break;
                    }
                    sb.append(charAt2);
                } else {
                    int r11 = r2 + 1;
                    if (r11 >= length || str.charAt(r11) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        r2 = r11;
                    }
                }
                r2++;
            }
        }
        r15[0] = r2;
        return sb.toString();
    }

    protected NumberRule selectNumberRule(int r2, int r3) {
        if (r3 != 1) {
            if (r3 == 2) {
                return new TwoDigitNumberField(r2);
            }
            return new PaddedNumberField(r2, r3);
        }
        return new UnpaddedNumberField(r2);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    @Deprecated
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String format(Object obj) {
        if (obj instanceof Date) {
            return format((Date) obj);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(long j) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return applyRulesToString(newCalendar);
    }

    private String applyRulesToString(Calendar calendar) {
        return ((StringBuilder) applyRules(calendar, (Calendar) new StringBuilder(this.mMaxLengthEstimate))).toString();
    }

    private Calendar newCalendar() {
        return Calendar.getInstance(this.mTimeZone, this.mLocale);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Date date) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return applyRulesToString(newCalendar);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Calendar calendar) {
        return ((StringBuilder) format(calendar, (Calendar) new StringBuilder(this.mMaxLengthEstimate))).toString();
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(long j, StringBuffer stringBuffer) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (StringBuffer) applyRules(newCalendar, (Calendar) stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (StringBuffer) applyRules(newCalendar, (Calendar) stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return format(calendar.getTime(), stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(long j, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Date date, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Calendar calendar, B b) {
        if (!calendar.getTimeZone().equals(this.mTimeZone)) {
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.mTimeZone);
        }
        return (B) applyRules(calendar, (Calendar) b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        return (StringBuffer) applyRules(calendar, (Calendar) stringBuffer);
    }

    private <B extends Appendable> B applyRules(Calendar calendar, B b) {
        try {
            for (Rule rule : this.mRules) {
                rule.appendTo(b, calendar);
            }
        } catch (IOException e) {
            ExceptionUtils.rethrow(e);
        }
        return b;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.mPattern;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FastDatePrinter) {
            FastDatePrinter fastDatePrinter = (FastDatePrinter) obj;
            return this.mPattern.equals(fastDatePrinter.mPattern) && this.mTimeZone.equals(fastDatePrinter.mTimeZone) && this.mLocale.equals(fastDatePrinter.mLocale);
        }
        return false;
    }

    public int hashCode() {
        return this.mPattern.hashCode() + ((this.mTimeZone.hashCode() + (this.mLocale.hashCode() * 13)) * 13);
    }

    public String toString() {
        return "FastDatePrinter[" + this.mPattern + "," + this.mLocale + "," + this.mTimeZone.getID() + "]";
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendDigits(Appendable appendable, int r2) throws IOException {
        appendable.append((char) ((r2 / 10) + 48));
        appendable.append((char) ((r2 % 10) + 48));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendFullDigits(Appendable appendable, int r9, int r10) throws IOException {
        if (r9 < 10000) {
            int r6 = r9 < 1000 ? r9 < 100 ? r9 < 10 ? 1 : 2 : 3 : 4;
            for (int r102 = r10 - r6; r102 > 0; r102--) {
                appendable.append('0');
            }
            if (r6 != 1) {
                if (r6 != 2) {
                    if (r6 != 3) {
                        if (r6 != 4) {
                            return;
                        }
                        appendable.append((char) ((r9 / 1000) + 48));
                        r9 %= 1000;
                    }
                    if (r9 >= 100) {
                        appendable.append((char) ((r9 / 100) + 48));
                        r9 %= 100;
                    } else {
                        appendable.append('0');
                    }
                }
                if (r9 >= 10) {
                    appendable.append((char) ((r9 / 10) + 48));
                    r9 %= 10;
                } else {
                    appendable.append('0');
                }
            }
            appendable.append((char) (r9 + 48));
            return;
        }
        char[] cArr = new char[10];
        int r2 = 0;
        while (r9 != 0) {
            cArr[r2] = (char) ((r9 % 10) + 48);
            r9 /= 10;
            r2++;
        }
        while (r2 < r10) {
            appendable.append('0');
            r10--;
        }
        while (true) {
            r2--;
            if (r2 < 0) {
                return;
            }
            appendable.append(cArr[r2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class CharacterLiteral implements Rule {
        private final char mValue;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.mValue = c;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.mValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class StringLiteral implements Rule {
        private final String mValue;

        StringLiteral(String str) {
            this.mValue = str;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mValue.length();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.mValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        TextField(int r1, String[] strArr) {
            this.mField = r1;
            this.mValues = strArr;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            int length = this.mValues.length;
            int r1 = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return r1;
                }
                int length2 = this.mValues[length].length();
                if (length2 > r1) {
                    r1 = length2;
                }
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.mValues[calendar.get(this.mField)]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }

        UnpaddedNumberField(int r1) {
            this.mField = r1;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int r3) throws IOException {
            if (r3 < 10) {
                appendable.append((char) (r3 + 48));
            } else if (r3 < 100) {
                FastDatePrinter.appendDigits(appendable, r3);
            } else {
                FastDatePrinter.appendFullDigits(appendable, r3, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        UnpaddedMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int r3) throws IOException {
            if (r3 >= 10) {
                FastDatePrinter.appendDigits(appendable, r3);
            } else {
                appendable.append((char) (r3 + 48));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        PaddedNumberField(int r2, int r3) {
            if (r3 < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = r2;
            this.mSize = r3;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mSize;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int r3) throws IOException {
            FastDatePrinter.appendFullDigits(appendable, r3, this.mSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitNumberField(int r1) {
            this.mField = r1;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int r3) throws IOException {
            if (r3 < 100) {
                FastDatePrinter.appendDigits(appendable, r3);
            } else {
                FastDatePrinter.appendFullDigits(appendable, r3, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitYearField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(1) % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int r2) throws IOException {
            FastDatePrinter.appendDigits(appendable, r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int r2) throws IOException {
            FastDatePrinter.appendDigits(appendable, r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        TwelveHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int r1 = calendar.get(10);
            if (r1 == 0) {
                r1 = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(appendable, r1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int r3) throws IOException {
            this.mRule.appendTo(appendable, r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int r1 = calendar.get(11);
            if (r1 == 0) {
                r1 = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(appendable, r1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int r3) throws IOException {
            this.mRule.appendTo(appendable, r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DayInWeekField implements NumberRule {
        private final NumberRule mRule;

        DayInWeekField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int r5 = calendar.get(7);
            this.mRule.appendTo(appendable, r5 != 1 ? r5 - 1 : 7);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int r3) throws IOException {
            this.mRule.appendTo(appendable, r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class WeekYear implements NumberRule {
        private final NumberRule mRule;

        WeekYear(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            this.mRule.appendTo(appendable, calendar.getWeekYear());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int r3) throws IOException {
            this.mRule.appendTo(appendable, r3);
        }
    }

    static String getTimeZoneDisplay(TimeZone timeZone, boolean z, int r5, Locale locale) {
        TimeZoneDisplayKey timeZoneDisplayKey = new TimeZoneDisplayKey(timeZone, z, r5, locale);
        ConcurrentMap<TimeZoneDisplayKey, String> concurrentMap = cTimeZoneDisplayCache;
        String str = concurrentMap.get(timeZoneDisplayKey);
        if (str == null) {
            String displayName = timeZone.getDisplayName(z, r5, locale);
            String putIfAbsent = concurrentMap.putIfAbsent(timeZoneDisplayKey, displayName);
            return putIfAbsent != null ? putIfAbsent : displayName;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final Locale mLocale;
        private final String mStandard;
        private final int mStyle;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int r4) {
            this.mLocale = locale;
            this.mStyle = r4;
            this.mStandard = FastDatePrinter.getTimeZoneDisplay(timeZone, false, r4, locale);
            this.mDaylight = FastDatePrinter.getTimeZoneDisplay(timeZone, true, r4, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            TimeZone timeZone = calendar.getTimeZone();
            if (calendar.get(16) == 0) {
                appendable.append(FastDatePrinter.getTimeZoneDisplay(timeZone, false, this.mStyle, this.mLocale));
            } else {
                appendable.append(FastDatePrinter.getTimeZoneDisplay(timeZone, true, this.mStyle, this.mLocale));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        final boolean mColon;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 5;
        }

        TimeZoneNumberRule(boolean z) {
            this.mColon = z;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int r0 = calendar.get(15) + calendar.get(16);
            if (r0 < 0) {
                appendable.append('-');
                r0 = -r0;
            } else {
                appendable.append('+');
            }
            int r4 = r0 / 3600000;
            FastDatePrinter.appendDigits(appendable, r4);
            if (this.mColon) {
                appendable.append(':');
            }
            FastDatePrinter.appendDigits(appendable, (r0 / 60000) - (r4 * 60));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Iso8601_Rule implements Rule {
        final int length;
        static final Iso8601_Rule ISO8601_HOURS = new Iso8601_Rule(3);
        static final Iso8601_Rule ISO8601_HOURS_MINUTES = new Iso8601_Rule(5);
        static final Iso8601_Rule ISO8601_HOURS_COLON_MINUTES = new Iso8601_Rule(6);

        static Iso8601_Rule getRule(int r1) {
            if (r1 != 1) {
                if (r1 != 2) {
                    if (r1 == 3) {
                        return ISO8601_HOURS_COLON_MINUTES;
                    }
                    throw new IllegalArgumentException("invalid number of X");
                }
                return ISO8601_HOURS_MINUTES;
            }
            return ISO8601_HOURS;
        }

        Iso8601_Rule(int r1) {
            this.length = r1;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.length;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int r0 = calendar.get(15) + calendar.get(16);
            if (r0 == 0) {
                appendable.append("Z");
                return;
            }
            if (r0 < 0) {
                appendable.append('-');
                r0 = -r0;
            } else {
                appendable.append('+');
            }
            int r5 = r0 / 3600000;
            FastDatePrinter.appendDigits(appendable, r5);
            int r1 = this.length;
            if (r1 < 5) {
                return;
            }
            if (r1 == 6) {
                appendable.append(':');
            }
            FastDatePrinter.appendDigits(appendable, (r0 / 60000) - (r5 * 60));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean z, int r3, Locale locale) {
            this.mTimeZone = timeZone;
            if (z) {
                this.mStyle = Integer.MIN_VALUE | r3;
            } else {
                this.mStyle = r3;
            }
            this.mLocale = locale;
        }

        public int hashCode() {
            return (((this.mStyle * 31) + this.mLocale.hashCode()) * 31) + this.mTimeZone.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeZoneDisplayKey) {
                TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
                return this.mTimeZone.equals(timeZoneDisplayKey.mTimeZone) && this.mStyle == timeZoneDisplayKey.mStyle && this.mLocale.equals(timeZoneDisplayKey.mLocale);
            }
            return false;
        }
    }
}
