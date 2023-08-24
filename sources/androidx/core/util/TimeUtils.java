package androidx.core.util;

import java.io.PrintWriter;

/* loaded from: classes.dex */
public final class TimeUtils {
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final Object sFormatSync = new Object();
    private static char[] sFormatStr = new char[24];

    private static int accumField(int r2, int r3, boolean z, int r5) {
        if (r2 > 99 || (z && r5 >= 3)) {
            return r3 + 3;
        }
        if (r2 > 9 || (z && r5 >= 2)) {
            return r3 + 2;
        }
        if (z || r2 > 0) {
            return r3 + 1;
        }
        return 0;
    }

    private static int printField(char[] cArr, int r3, char c, int r5, boolean z, int r7) {
        int r1;
        if (z || r3 > 0) {
            if ((!z || r7 < 3) && r3 <= 99) {
                r1 = r5;
            } else {
                int r0 = r3 / 100;
                cArr[r5] = (char) (r0 + 48);
                r1 = r5 + 1;
                r3 -= r0 * 100;
            }
            if ((z && r7 >= 2) || r3 > 9 || r5 != r1) {
                int r52 = r3 / 10;
                cArr[r1] = (char) (r52 + 48);
                r1++;
                r3 -= r52 * 10;
            }
            cArr[r1] = (char) (r3 + 48);
            int r12 = r1 + 1;
            cArr[r12] = c;
            return r12 + 1;
        }
        return r5;
    }

    private static int formatDurationLocked(long j, int r19) {
        char c;
        int r5;
        int r1;
        int r12;
        int r0;
        int r8;
        long j2 = j;
        if (sFormatStr.length < r19) {
            sFormatStr = new char[r19];
        }
        char[] cArr = sFormatStr;
        int r7 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (r7 == 0) {
            int r02 = r19 - 1;
            while (r02 > 0) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 1;
        }
        if (r7 > 0) {
            c = '+';
        } else {
            c = '-';
            j2 = -j2;
        }
        int r13 = (int) (j2 % 1000);
        int floor = (int) Math.floor(j2 / 1000);
        if (floor > SECONDS_PER_DAY) {
            r5 = floor / SECONDS_PER_DAY;
            floor -= SECONDS_PER_DAY * r5;
        } else {
            r5 = 0;
        }
        if (floor > SECONDS_PER_HOUR) {
            r1 = floor / SECONDS_PER_HOUR;
            floor -= r1 * SECONDS_PER_HOUR;
        } else {
            r1 = 0;
        }
        if (floor > 60) {
            int r72 = floor / 60;
            r12 = floor - (r72 * 60);
            r0 = r72;
        } else {
            r12 = floor;
            r0 = 0;
        }
        if (r19 != 0) {
            int accumField = accumField(r5, 1, false, 0);
            int accumField2 = accumField + accumField(r1, 1, accumField > 0, 2);
            int accumField3 = accumField2 + accumField(r0, 1, accumField2 > 0, 2);
            int accumField4 = accumField3 + accumField(r12, 1, accumField3 > 0, 2);
            r8 = 0;
            for (int accumField5 = accumField4 + accumField(r13, 2, true, accumField4 > 0 ? 3 : 0) + 1; accumField5 < r19; accumField5++) {
                cArr[r8] = ' ';
                r8++;
            }
        } else {
            r8 = 0;
        }
        cArr[r8] = c;
        int r9 = r8 + 1;
        boolean z = r19 != 0;
        int printField = printField(cArr, r5, 'd', r9, false, 0);
        int printField2 = printField(cArr, r1, 'h', printField, printField != r9, z ? 2 : 0);
        int printField3 = printField(cArr, r0, 'm', printField2, printField2 != r9, z ? 2 : 0);
        int printField4 = printField(cArr, r12, 's', printField3, printField3 != r9, z ? 2 : 0);
        int printField5 = printField(cArr, r13, 'm', printField4, true, (!z || printField4 == r9) ? 0 : 3);
        cArr[printField5] = 's';
        return printField5 + 1;
    }

    public static void formatDuration(long j, StringBuilder sb) {
        synchronized (sFormatSync) {
            sb.append(sFormatStr, 0, formatDurationLocked(j, 0));
        }
    }

    public static void formatDuration(long j, PrintWriter printWriter, int r5) {
        synchronized (sFormatSync) {
            printWriter.print(new String(sFormatStr, 0, formatDurationLocked(j, r5)));
        }
    }

    public static void formatDuration(long j, PrintWriter printWriter) {
        formatDuration(j, printWriter, 0);
    }

    public static void formatDuration(long j, long j2, PrintWriter printWriter) {
        if (j == 0) {
            printWriter.print("--");
        } else {
            formatDuration(j - j2, printWriter, 0);
        }
    }

    private TimeUtils() {
    }
}
