package org.apache.commons.lang3.time;

import java.util.Date;
import java.util.TimeZone;

/* loaded from: classes5.dex */
class GmtTimeZone extends TimeZone {
    private static final int HOURS_PER_DAY = 24;
    private static final int MILLISECONDS_PER_MINUTE = 60000;
    private static final int MINUTES_PER_HOUR = 60;
    static final long serialVersionUID = 1;
    private final int offset;
    private final String zoneId;

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return false;
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GmtTimeZone(boolean z, int r4, int r5) {
        if (r4 >= 24) {
            throw new IllegalArgumentException(r4 + " hours out of range");
        } else if (r5 >= 60) {
            throw new IllegalArgumentException(r5 + " minutes out of range");
        } else {
            int r0 = ((r4 * 60) + r5) * MILLISECONDS_PER_MINUTE;
            this.offset = z ? -r0 : r0;
            StringBuilder sb = new StringBuilder(9);
            sb.append(TimeZones.GMT_ID);
            sb.append(z ? '-' : '+');
            StringBuilder twoDigits = twoDigits(sb, r4);
            twoDigits.append(':');
            this.zoneId = twoDigits(twoDigits, r5).toString();
        }
    }

    private static StringBuilder twoDigits(StringBuilder sb, int r2) {
        sb.append((char) ((r2 / 10) + 48));
        sb.append((char) ((r2 % 10) + 48));
        return sb;
    }

    @Override // java.util.TimeZone
    public int getOffset(int r1, int r2, int r3, int r4, int r5, int r6) {
        return this.offset;
    }

    @Override // java.util.TimeZone
    public void setRawOffset(int r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.offset;
    }

    @Override // java.util.TimeZone
    public String getID() {
        return this.zoneId;
    }

    public String toString() {
        return "[GmtTimeZone id=\"" + this.zoneId + "\",offset=" + this.offset + ']';
    }

    public int hashCode() {
        return this.offset;
    }

    public boolean equals(Object obj) {
        return (obj instanceof GmtTimeZone) && this.zoneId == ((GmtTimeZone) obj).zoneId;
    }
}
