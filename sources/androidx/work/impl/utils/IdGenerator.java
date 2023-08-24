package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.sqlite.p007db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabaseMigrations;
import androidx.work.impl.model.Preference;

/* loaded from: classes.dex */
public class IdGenerator {
    public static final int INITIAL_ID = 0;
    public static final String NEXT_ALARM_MANAGER_ID_KEY = "next_alarm_manager_id";
    public static final String NEXT_JOB_SCHEDULER_ID_KEY = "next_job_scheduler_id";
    public static final String PREFERENCE_FILE_KEY = "androidx.work.util.id";
    private final WorkDatabase mWorkDatabase;

    public IdGenerator(WorkDatabase workDatabase) {
        this.mWorkDatabase = workDatabase;
    }

    public int nextJobSchedulerIdWithRange(int minInclusive, int maxInclusive) {
        synchronized (IdGenerator.class) {
            int nextId = nextId(NEXT_JOB_SCHEDULER_ID_KEY);
            if (nextId >= minInclusive && nextId <= maxInclusive) {
                minInclusive = nextId;
            }
            update(NEXT_JOB_SCHEDULER_ID_KEY, minInclusive + 1);
        }
        return minInclusive;
    }

    public int nextAlarmManagerId() {
        int nextId;
        synchronized (IdGenerator.class) {
            nextId = nextId(NEXT_ALARM_MANAGER_ID_KEY);
        }
        return nextId;
    }

    private int nextId(String key) {
        this.mWorkDatabase.beginTransaction();
        try {
            Long longValue = this.mWorkDatabase.preferenceDao().getLongValue(key);
            int r1 = 0;
            int intValue = longValue != null ? longValue.intValue() : 0;
            if (intValue != Integer.MAX_VALUE) {
                r1 = intValue + 1;
            }
            update(key, r1);
            this.mWorkDatabase.setTransactionSuccessful();
            return intValue;
        } finally {
            this.mWorkDatabase.endTransaction();
        }
    }

    private void update(String key, int value) {
        this.mWorkDatabase.preferenceDao().insertPreference(new Preference(key, value));
    }

    public static void migrateLegacyIdGenerator(Context context, SupportSQLiteDatabase sqLiteDatabase) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, 0);
        if (sharedPreferences.contains(NEXT_JOB_SCHEDULER_ID_KEY) || sharedPreferences.contains(NEXT_JOB_SCHEDULER_ID_KEY)) {
            int r3 = sharedPreferences.getInt(NEXT_JOB_SCHEDULER_ID_KEY, 0);
            int r5 = sharedPreferences.getInt(NEXT_ALARM_MANAGER_ID_KEY, 0);
            sqLiteDatabase.beginTransaction();
            try {
                sqLiteDatabase.execSQL(WorkDatabaseMigrations.INSERT_PREFERENCE, new Object[]{NEXT_JOB_SCHEDULER_ID_KEY, Integer.valueOf(r3)});
                sqLiteDatabase.execSQL(WorkDatabaseMigrations.INSERT_PREFERENCE, new Object[]{NEXT_ALARM_MANAGER_ID_KEY, Integer.valueOf(r5)});
                sharedPreferences.edit().clear().apply();
                sqLiteDatabase.setTransactionSuccessful();
            } finally {
                sqLiteDatabase.endTransaction();
            }
        }
    }
}