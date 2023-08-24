package com.google.android.play.core.appupdate.testing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.appupdate.C2328a;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.listener.StateUpdatedListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* loaded from: classes3.dex */
public class FakeAppUpdateManager implements AppUpdateManager {

    /* renamed from: a */
    private final C2328a f344a;

    /* renamed from: b */
    private final Context f345b;

    /* renamed from: c */
    private int f346c = 0;

    /* renamed from: d */
    private int f347d = 0;

    /* renamed from: e */
    private boolean f348e = false;

    /* renamed from: f */
    private int f349f = 0;

    /* renamed from: g */
    private Integer f350g = null;

    /* renamed from: h */
    private int f351h = 0;

    /* renamed from: i */
    private long f352i = 0;

    /* renamed from: j */
    private long f353j = 0;

    /* renamed from: k */
    private boolean f354k = false;

    /* renamed from: l */
    private boolean f355l = false;

    /* renamed from: m */
    private boolean f356m = false;

    /* renamed from: n */
    private Integer f357n;

    public FakeAppUpdateManager(Context context) {
        this.f344a = new C2328a(context);
        this.f345b = context;
    }

    /* renamed from: a */
    private final int m1051a() {
        if (this.f348e) {
            int r0 = this.f346c;
            return (r0 == 0 || r0 == 4 || r0 == 5 || r0 == 6) ? 2 : 3;
        }
        return 1;
    }

    /* renamed from: a */
    private final boolean m1050a(AppUpdateInfo appUpdateInfo, AppUpdateOptions appUpdateOptions) {
        int r3;
        if (appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) || (AppUpdateOptions.defaultOptions(appUpdateOptions.appUpdateType()).equals(appUpdateOptions) && appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions.appUpdateType()))) {
            if (appUpdateOptions.appUpdateType() == 1) {
                this.f355l = true;
                r3 = 1;
            } else {
                this.f354k = true;
                r3 = 0;
            }
            this.f357n = r3;
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private final void m1049b() {
        this.f344a.m640a((C2328a) InstallState.m816a(this.f346c, this.f352i, this.f353j, this.f347d, this.f345b.getPackageName()));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public Task<Void> completeUpdate() {
        int r0 = this.f347d;
        if (r0 != 0) {
            return Tasks.m469a((Exception) new InstallException(r0));
        }
        int r02 = this.f346c;
        if (r02 != 11) {
            return r02 == 3 ? Tasks.m469a((Exception) new InstallException(-8)) : Tasks.m469a((Exception) new InstallException(-7));
        }
        this.f346c = 3;
        this.f356m = true;
        Integer num = 0;
        if (num.equals(this.f357n)) {
            m1049b();
        }
        return Tasks.m468a((Object) null);
    }

    public void downloadCompletes() {
        int r0 = this.f346c;
        if (r0 == 2 || r0 == 1) {
            this.f346c = 11;
            this.f352i = 0L;
            this.f353j = 0L;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
                return;
            }
            Integer num2 = 1;
            if (num2.equals(this.f357n)) {
                completeUpdate();
            }
        }
    }

    public void downloadFails() {
        int r0 = this.f346c;
        if (r0 == 1 || r0 == 2) {
            this.f346c = 5;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
            this.f357n = null;
            this.f355l = false;
            this.f346c = 0;
        }
    }

    public void downloadStarts() {
        if (this.f346c == 1) {
            this.f346c = 2;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
        }
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public Task<AppUpdateInfo> getAppUpdateInfo() {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        int r1 = this.f347d;
        if (r1 != 0) {
            return Tasks.m469a((Exception) new InstallException(r1));
        }
        PendingIntent broadcast = (m1051a() == 2 && this.f347d == 0) ? PendingIntent.getBroadcast(this.f345b, 0, new Intent(), 0) : null;
        PendingIntent broadcast2 = (m1051a() == 2 && this.f347d == 0) ? PendingIntent.getBroadcast(this.f345b, 0, new Intent(), 0) : null;
        if (m1051a() == 2 && this.f347d == 0) {
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this.f345b, 0, new Intent(), 0);
            pendingIntent = PendingIntent.getBroadcast(this.f345b, 0, new Intent(), 0);
            pendingIntent2 = broadcast3;
        } else {
            pendingIntent = null;
            pendingIntent2 = null;
        }
        return Tasks.m468a(AppUpdateInfo.m1073a(this.f345b.getPackageName(), this.f349f, m1051a(), this.f346c, this.f350g, this.f351h, this.f352i, this.f353j, 0L, 0L, broadcast2, broadcast, pendingIntent, pendingIntent2));
    }

    public Integer getTypeForUpdateInProgress() {
        return this.f357n;
    }

    public void installCompletes() {
        if (this.f346c == 3) {
            this.f346c = 4;
            this.f348e = false;
            this.f349f = 0;
            this.f350g = null;
            this.f351h = 0;
            this.f352i = 0L;
            this.f353j = 0L;
            this.f355l = false;
            this.f356m = false;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
            this.f357n = null;
            this.f346c = 0;
        }
    }

    public void installFails() {
        if (this.f346c == 3) {
            this.f346c = 5;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
            this.f357n = null;
            this.f356m = false;
            this.f355l = false;
            this.f346c = 0;
        }
    }

    public boolean isConfirmationDialogVisible() {
        return this.f354k;
    }

    public boolean isImmediateFlowVisible() {
        return this.f355l;
    }

    public boolean isInstallSplashScreenVisible() {
        return this.f356m;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.f344a.m641a((StateUpdatedListener) installStateUpdatedListener);
    }

    public void setBytesDownloaded(long j) {
        if (this.f346c != 2 || j > this.f353j) {
            return;
        }
        this.f352i = j;
        Integer num = 0;
        if (num.equals(this.f357n)) {
            m1049b();
        }
    }

    public void setClientVersionStalenessDays(Integer num) {
        if (this.f348e) {
            this.f350g = num;
        }
    }

    public void setInstallErrorCode(int r1) {
        this.f347d = r1;
    }

    public void setTotalBytesToDownload(long j) {
        if (this.f346c == 2) {
            this.f353j = j;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
        }
    }

    public void setUpdateAvailable(int r2) {
        this.f348e = true;
        this.f349f = r2;
    }

    public void setUpdateNotAvailable() {
        this.f348e = false;
        this.f350g = null;
    }

    public void setUpdatePriority(int r2) {
        if (this.f348e) {
            this.f351h = r2;
        }
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        return m1050a(appUpdateInfo, appUpdateOptions) ? Tasks.m468a(-1) : Tasks.m469a((Exception) new InstallException(-6));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int r2, Activity activity, int r4) {
        return m1050a(appUpdateInfo, AppUpdateOptions.newBuilder(r2).build());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int r2, IntentSenderForResultStarter intentSenderForResultStarter, int r4) {
        return m1050a(appUpdateInfo, AppUpdateOptions.newBuilder(r2).build());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int r4) {
        return m1050a(appUpdateInfo, appUpdateOptions);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int r4) {
        return m1050a(appUpdateInfo, appUpdateOptions);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.f344a.m637b(installStateUpdatedListener);
    }

    public void userAcceptsUpdate() {
        if (this.f354k || this.f355l) {
            this.f354k = false;
            this.f346c = 1;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
        }
    }

    public void userCancelsDownload() {
        int r0 = this.f346c;
        if (r0 == 1 || r0 == 2) {
            this.f346c = 6;
            Integer num = 0;
            if (num.equals(this.f357n)) {
                m1049b();
            }
            this.f357n = null;
            this.f355l = false;
            this.f346c = 0;
        }
    }

    public void userRejectsUpdate() {
        if (this.f354k || this.f355l) {
            this.f354k = false;
            this.f355l = false;
            this.f357n = null;
            this.f346c = 0;
        }
    }
}
