package com.onesignal;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import com.onesignal.LocationController;
import com.onesignal.OneSignal;
import java.lang.ref.WeakReference;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSSyncService extends OSBackgroundSync {
    private static final Object INSTANCE_LOCK = new Object();
    private static final long SYNC_AFTER_BG_DELAY_MS = 30000;
    private static final int SYNC_TASK_ID = 2071862118;
    private static final String SYNC_TASK_THREAD_ID = "OS_SYNCSRV_BG_SYNC";
    private static OSSyncService sInstance;
    private Long nextScheduledSyncTimeMs = 0L;

    @Override // com.onesignal.OSBackgroundSync
    protected int getSyncTaskId() {
        return SYNC_TASK_ID;
    }

    @Override // com.onesignal.OSBackgroundSync
    protected String getSyncTaskThreadId() {
        return SYNC_TASK_THREAD_ID;
    }

    OSSyncService() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OSSyncService getInstance() {
        if (sInstance == null) {
            synchronized (INSTANCE_LOCK) {
                if (sInstance == null) {
                    sInstance = new OSSyncService();
                }
            }
        }
        return sInstance;
    }

    @Override // com.onesignal.OSBackgroundSync
    protected Class getSyncServiceJobClass() {
        return SyncJobService.class;
    }

    @Override // com.onesignal.OSBackgroundSync
    protected Class getSyncServicePendingIntentClass() {
        return SyncService.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.onesignal.OSBackgroundSync
    public void scheduleSyncTask(Context context) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "OSSyncService scheduleSyncTask:SYNC_AFTER_BG_DELAY_MS: 30000");
        scheduleSyncTask(context, 30000L);
    }

    protected void scheduleSyncTask(Context context, long j) {
        synchronized (LOCK) {
            if (this.nextScheduledSyncTimeMs.longValue() != 0 && OneSignal.getTime().getCurrentTimeMillis() + j > this.nextScheduledSyncTimeMs.longValue()) {
                OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "OSSyncService scheduleSyncTask already update scheduled nextScheduledSyncTimeMs: " + this.nextScheduledSyncTimeMs);
                return;
            }
            if (j < 5000) {
                j = 5000;
            }
            scheduleBackgroundSyncTask(context, j);
            this.nextScheduledSyncTimeMs = Long.valueOf(OneSignal.getTime().getCurrentTimeMillis() + j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scheduleLocationUpdateTask(Context context, long j) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, "OSSyncService scheduleLocationUpdateTask:delayMs: " + j);
        scheduleSyncTask(context, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancelSyncTask(Context context) {
        synchronized (LOCK) {
            this.nextScheduledSyncTimeMs = 0L;
            if (LocationController.scheduleUpdate(context)) {
                return;
            }
            cancelBackgroundSyncTask(context);
        }
    }

    /* loaded from: classes3.dex */
    static abstract class SyncRunnable implements Runnable {
        protected abstract void stopSync();

        SyncRunnable() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (OSBackgroundSync.LOCK) {
                OSSyncService.getInstance().nextScheduledSyncTimeMs = 0L;
            }
            if (OneSignal.getUserId() == null) {
                stopSync();
                return;
            }
            OneSignal.appId = OneSignal.getSavedAppId();
            OneSignalStateSynchronizer.initUserState();
            try {
                final ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
                LocationController.getLocation(OneSignal.appContext, false, false, new LocationController.LocationHandler() { // from class: com.onesignal.OSSyncService.SyncRunnable.1
                    @Override // com.onesignal.LocationController.LocationHandler
                    public LocationController.PermissionType getType() {
                        return LocationController.PermissionType.SYNC_SERVICE;
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
                        r2 = r2;
                     */
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.onesignal.LocationController.LocationHandler
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void onComplete(com.onesignal.LocationController.LocationPoint r2) {
                        /*
                            r1 = this;
                            if (r2 == 0) goto L3
                            goto L8
                        L3:
                            java.lang.Object r2 = new java.lang.Object
                            r2.<init>()
                        L8:
                            java.util.concurrent.BlockingQueue r0 = r2
                            r0.offer(r2)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSSyncService.SyncRunnable.C35751.onComplete(com.onesignal.LocationController$LocationPoint):void");
                    }
                });
                Object take = arrayBlockingQueue.take();
                if (take instanceof LocationController.LocationPoint) {
                    OneSignalStateSynchronizer.updateLocation((LocationController.LocationPoint) take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            OneSignalStateSynchronizer.syncUserState(true);
            OneSignal.getFocusTimeController().doBlockingBackgroundSyncOfUnsentTime();
            stopSync();
        }
    }

    /* loaded from: classes3.dex */
    static class LollipopSyncRunnable extends SyncRunnable {
        private JobParameters jobParameters;
        private WeakReference<JobService> jobService;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LollipopSyncRunnable(JobService jobService, JobParameters jobParameters) {
            this.jobService = new WeakReference<>(jobService);
            this.jobParameters = jobParameters;
        }

        @Override // com.onesignal.OSSyncService.SyncRunnable
        protected void stopSync() {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "LollipopSyncRunnable:JobFinished needsJobReschedule: " + OSSyncService.getInstance().needsJobReschedule);
            boolean z = OSSyncService.getInstance().needsJobReschedule;
            OSSyncService.getInstance().needsJobReschedule = false;
            if (this.jobService.get() != null) {
                this.jobService.get().jobFinished(this.jobParameters, z);
            }
        }
    }

    /* loaded from: classes3.dex */
    static class LegacySyncRunnable extends SyncRunnable {
        private WeakReference<Service> callerService;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LegacySyncRunnable(Service service) {
            this.callerService = new WeakReference<>(service);
        }

        @Override // com.onesignal.OSSyncService.SyncRunnable
        protected void stopSync() {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "LegacySyncRunnable:Stopped");
            if (this.callerService.get() != null) {
                this.callerService.get().stopSelf();
            }
        }
    }
}
