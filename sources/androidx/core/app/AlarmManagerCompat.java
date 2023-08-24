package androidx.core.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;

/* loaded from: classes.dex */
public final class AlarmManagerCompat {
    public static void setAlarmClock(AlarmManager alarmManager, long j, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        if (Build.VERSION.SDK_INT >= 21) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(j, pendingIntent), pendingIntent2);
        } else {
            setExact(alarmManager, 0, j, pendingIntent2);
        }
    }

    public static void setAndAllowWhileIdle(AlarmManager alarmManager, int r3, long j, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setAndAllowWhileIdle(r3, j, pendingIntent);
        } else {
            alarmManager.set(r3, j, pendingIntent);
        }
    }

    public static void setExact(AlarmManager alarmManager, int r3, long j, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(r3, j, pendingIntent);
        } else {
            alarmManager.set(r3, j, pendingIntent);
        }
    }

    public static void setExactAndAllowWhileIdle(AlarmManager alarmManager, int r3, long j, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(r3, j, pendingIntent);
        } else {
            setExact(alarmManager, r3, j, pendingIntent);
        }
    }

    private AlarmManagerCompat() {
    }
}
