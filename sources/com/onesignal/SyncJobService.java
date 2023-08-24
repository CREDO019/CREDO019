package com.onesignal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.onesignal.OSSyncService;
import com.onesignal.OneSignal;

/* loaded from: classes3.dex */
public class SyncJobService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        OSSyncService.getInstance().doBackgroundSync(this, new OSSyncService.LollipopSyncRunnable(this, jobParameters));
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        boolean stopSyncBgThread = OSSyncService.getInstance().stopSyncBgThread();
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "SyncJobService onStopJob called, system conditions not available reschedule: " + stopSyncBgThread);
        return stopSyncBgThread;
    }
}
