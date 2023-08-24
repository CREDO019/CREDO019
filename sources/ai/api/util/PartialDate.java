package ai.api.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class PartialDate {
    private static final String UNSPECIFIED_DATE = "uu";
    private static final String UNSPECIFIED_HOUR = "uu";
    private static final String UNSPECIFIED_MINUTE = "uu";
    private static final String UNSPECIFIED_MONTH = "uu";
    public static final Integer UNSPECIFIED_VALUE = null;
    private static final String UNSPECIFIED_YEAR = "uuuu";

    /* renamed from: c */
    private final Calendar f2c;
    private final Set<Integer> unspecifiedFields;

    public PartialDate() {
        this.unspecifiedFields = new HashSet();
        this.f2c = Calendar.getInstance();
    }

    public PartialDate(Calendar calendar) {
        this.unspecifiedFields = new HashSet();
        this.f2c = calendar;
    }

    public PartialDate(Date date) {
        this.unspecifiedFields = new HashSet();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.f2c = gregorianCalendar;
        gregorianCalendar.setTime(date);
    }

    public void set(int r3, Integer num) {
        if (num != UNSPECIFIED_VALUE) {
            this.unspecifiedFields.remove(Integer.valueOf(r3));
            this.f2c.set(r3, num.intValue());
        } else if (r3 == 1) {
            this.unspecifiedFields.add(1);
        } else if (r3 == 2) {
            this.unspecifiedFields.add(2);
        } else if (r3 >= 3 && r3 <= 8) {
            this.unspecifiedFields.add(5);
        } else if (r3 >= 10 && r3 <= 11) {
            this.unspecifiedFields.add(11);
        } else if (r3 == 12) {
            this.unspecifiedFields.add(12);
        }
    }

    public Integer get(int r3) {
        if (r3 == 1) {
            if (!this.unspecifiedFields.contains(1)) {
                return Integer.valueOf(this.f2c.get(r3));
            }
            return UNSPECIFIED_VALUE;
        } else if (r3 == 2) {
            if (!this.unspecifiedFields.contains(2)) {
                return Integer.valueOf(this.f2c.get(r3));
            }
            return UNSPECIFIED_VALUE;
        } else if (r3 >= 3 && r3 <= 8) {
            if (!this.unspecifiedFields.contains(5)) {
                return Integer.valueOf(this.f2c.get(r3));
            }
            return UNSPECIFIED_VALUE;
        } else if (r3 >= 10 && r3 <= 11) {
            if (!this.unspecifiedFields.contains(11)) {
                return Integer.valueOf(this.f2c.get(r3));
            }
            return UNSPECIFIED_VALUE;
        } else if (r3 == 12) {
            if (!this.unspecifiedFields.contains(12)) {
                return Integer.valueOf(this.f2c.get(12));
            }
            return UNSPECIFIED_VALUE;
        } else {
            return Integer.valueOf(this.f2c.get(r3));
        }
    }

    private String getFieldAsString(int r7) {
        return r7 == 1 ? this.unspecifiedFields.contains(1) ? UNSPECIFIED_YEAR : String.format("%4d", Integer.valueOf(this.f2c.get(r7))) : r7 == 2 ? this.unspecifiedFields.contains(2) ? "uu" : String.format("%02d", Integer.valueOf(this.f2c.get(r7))) : (r7 < 3 || r7 > 8) ? (r7 < 10 || r7 > 11) ? r7 == 12 ? this.unspecifiedFields.contains(12) ? "uu" : String.format("%02d", Integer.valueOf(this.f2c.get(r7))) : String.format("%s", Integer.valueOf(this.f2c.get(r7))) : this.unspecifiedFields.contains(11) ? "uu" : String.format("%02d", Integer.valueOf(this.f2c.get(r7))) : this.unspecifiedFields.contains(5) ? "uu" : String.format("%02d", Integer.valueOf(this.f2c.get(r7)));
    }

    public String toString() {
        return String.format("%s-%s-%s", getFieldAsString(1), getFieldAsString(2), getFieldAsString(5));
    }
}
