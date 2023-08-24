package com.google.android.play.core.appupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.listener.StateUpdatedListener;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.appupdate.e */
/* loaded from: classes3.dex */
public final class C2332e implements AppUpdateManager {

    /* renamed from: a */
    private final C2343p f298a;

    /* renamed from: b */
    private final C2328a f299b;

    /* renamed from: c */
    private final Context f300c;

    /* renamed from: d */
    private final Handler f301d = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2332e(C2343p c2343p, C2328a c2328a, Context context) {
        this.f298a = c2343p;
        this.f299b = c2328a;
        this.f300c = context;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final Task<Void> completeUpdate() {
        return this.f298a.m1062b(this.f300c.getPackageName());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final Task<AppUpdateInfo> getAppUpdateInfo() {
        return this.f298a.m1064a(this.f300c.getPackageName());
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final synchronized void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.f299b.m641a((StateUpdatedListener) installStateUpdatedListener);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        PlayCoreDialogWrapperActivity.m824a(this.f300c);
        if (appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions)) {
            Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
            intent.putExtra("confirmation_intent", appUpdateInfo.m1074a(appUpdateOptions));
            C2682i c2682i = new C2682i();
            intent.putExtra("result_receiver", new ResultReceiverC2330c(this.f301d, c2682i));
            activity.startActivity(intent);
            return c2682i.m458a();
        }
        return Tasks.m469a((Exception) new InstallException(-6));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int r3, Activity activity, int r5) throws IntentSender.SendIntentException {
        return startUpdateFlowForResult(appUpdateInfo, new C2331d(activity), AppUpdateOptions.defaultOptions(r3), r5);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int r2, IntentSenderForResultStarter intentSenderForResultStarter, int r4) throws IntentSender.SendIntentException {
        return startUpdateFlowForResult(appUpdateInfo, intentSenderForResultStarter, AppUpdateOptions.defaultOptions(r2), r4);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int r5) throws IntentSender.SendIntentException {
        return startUpdateFlowForResult(appUpdateInfo, new C2331d(activity), appUpdateOptions, r5);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int r12) throws IntentSender.SendIntentException {
        if (appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions)) {
            intentSenderForResultStarter.startIntentSenderForResult(appUpdateInfo.m1074a(appUpdateOptions).getIntentSender(), r12, null, 0, 0, 0, null);
            return true;
        }
        return false;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateManager
    public final synchronized void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.f299b.m637b(installStateUpdatedListener);
    }
}
