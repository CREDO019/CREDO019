package com.google.android.play.core.appupdate;

import android.app.PendingIntent;

/* loaded from: classes3.dex */
public abstract class AppUpdateInfo {
    /* renamed from: a */
    public static AppUpdateInfo m1073a(String str, int r21, int r22, int r23, Integer num, int r25, long j, long j2, long j3, long j4, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3, PendingIntent pendingIntent4) {
        return new C2347t(str, r21, r22, r23, num, r25, j, j2, j3, j4, pendingIntent, pendingIntent2, pendingIntent3, pendingIntent4);
    }

    /* renamed from: b */
    private final boolean m1072b(AppUpdateOptions appUpdateOptions) {
        return appUpdateOptions.allowAssetPackDeletion() && mo1057a() <= mo1056b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract long mo1057a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final PendingIntent m1074a(AppUpdateOptions appUpdateOptions) {
        if (appUpdateOptions.appUpdateType() == 0) {
            if (mo1054d() != null) {
                return mo1054d();
            }
            if (m1072b(appUpdateOptions)) {
                return mo1052f();
            }
            return null;
        }
        if (appUpdateOptions.appUpdateType() == 1) {
            if (mo1055c() != null) {
                return mo1055c();
            }
            if (m1072b(appUpdateOptions)) {
                return mo1053e();
            }
        }
        return null;
    }

    public abstract int availableVersionCode();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract long mo1056b();

    public abstract long bytesDownloaded();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract PendingIntent mo1055c();

    public abstract Integer clientVersionStalenessDays();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public abstract PendingIntent mo1054d();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public abstract PendingIntent mo1053e();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public abstract PendingIntent mo1052f();

    public abstract int installStatus();

    public boolean isUpdateTypeAllowed(int r1) {
        return m1074a(AppUpdateOptions.defaultOptions(r1)) != null;
    }

    public boolean isUpdateTypeAllowed(AppUpdateOptions appUpdateOptions) {
        return m1074a(appUpdateOptions) != null;
    }

    public abstract String packageName();

    public abstract long totalBytesToDownload();

    public abstract int updateAvailability();

    public abstract int updatePriority();
}
