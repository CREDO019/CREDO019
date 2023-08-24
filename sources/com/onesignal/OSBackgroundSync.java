package com.onesignal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.onesignal.AndroidSupportV4Compat;
import com.onesignal.OneSignal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class OSBackgroundSync {
    protected static final Object LOCK = new Object();
    protected boolean needsJobReschedule = false;
    private Thread syncBgThread;

    protected abstract Class getSyncServiceJobClass();

    protected abstract Class getSyncServicePendingIntentClass();

    protected abstract int getSyncTaskId();

    protected abstract String getSyncTaskThreadId();

    protected abstract void scheduleSyncTask(Context context);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doBackgroundSync(Context context, Runnable runnable) {
        OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "OSBackground sync, calling initWithContext");
        OneSignal.initWithContext(context);
        Thread thread = new Thread(runnable, getSyncTaskThreadId());
        this.syncBgThread = thread;
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean stopSyncBgThread() {
        Thread thread = this.syncBgThread;
        if (thread != null && thread.isAlive()) {
            this.syncBgThread.interrupt();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scheduleBackgroundSyncTask(Context context, long j) {
        synchronized (LOCK) {
            if (useJob()) {
                scheduleSyncServiceAsJob(context, j);
            } else {
                scheduleSyncServiceAsAlarm(context, j);
            }
        }
    }

    private boolean hasBootPermission(Context context) {
        return AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.RECEIVE_BOOT_COMPLETED") == 0;
    }

    private boolean isJobIdRunning(Context context) {
        Thread thread;
        for (JobInfo jobInfo : ((JobScheduler) context.getSystemService("jobscheduler")).getAllPendingJobs()) {
            if (jobInfo.getId() == getSyncTaskId() && (thread = this.syncBgThread) != null && thread.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private void scheduleSyncServiceAsJob(Context context, long j) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, "OSBackgroundSync scheduleSyncServiceAsJob:atTime: " + j);
        if (isJobIdRunning(context)) {
            OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "OSBackgroundSync scheduleSyncServiceAsJob Scheduler already running!");
            this.needsJobReschedule = true;
            return;
        }
        JobInfo.Builder builder = new JobInfo.Builder(getSyncTaskId(), new ComponentName(context, getSyncServiceJobClass()));
        builder.setMinimumLatency(j).setRequiredNetworkType(1);
        if (hasBootPermission(context)) {
            builder.setPersisted(true);
        }
        try {
            int schedule = ((JobScheduler) context.getSystemService("jobscheduler")).schedule(builder.build());
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.INFO;
            OneSignal.Log(log_level2, "OSBackgroundSync scheduleSyncServiceAsJob:result: " + schedule);
        } catch (NullPointerException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "scheduleSyncServiceAsJob called JobScheduler.jobScheduler which triggered an internal null Android error. Skipping job.", e);
        }
    }

    private void scheduleSyncServiceAsAlarm(Context context, long j) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, getClass().getSimpleName() + " scheduleServiceSyncTask:atTime: " + j);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, OneSignal.getTime().getCurrentTimeMillis() + j, syncServicePendingIntent(context));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cancelBackgroundSyncTask(Context context) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.onesignalLog(log_level, getClass().getSimpleName() + " cancel background sync");
        synchronized (LOCK) {
            if (useJob()) {
                ((JobScheduler) context.getSystemService("jobscheduler")).cancel(getSyncTaskId());
            } else {
                ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(syncServicePendingIntent(context));
            }
        }
    }

    private PendingIntent syncServicePendingIntent(Context context) {
        return PendingIntent.getService(context, getSyncTaskId(), new Intent(context, getSyncServicePendingIntentClass()), 201326592);
    }

    private static boolean useJob() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
