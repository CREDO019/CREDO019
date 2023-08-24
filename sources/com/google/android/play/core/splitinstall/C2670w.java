package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.IntentSender;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.listener.StateUpdatedListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.w */
/* loaded from: classes3.dex */
public final class C2670w implements SplitInstallManager {

    /* renamed from: a */
    private final C2632av f1078a;

    /* renamed from: b */
    private final C2656t f1079b;

    /* renamed from: c */
    private final C2652p f1080c;

    /* renamed from: d */
    private final C2634ax f1081d;

    /* renamed from: e */
    private final Handler f1082e = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2670w(C2632av c2632av, C2656t c2656t, C2652p c2652p, C2634ax c2634ax) {
        this.f1078a = c2632av;
        this.f1079b = c2656t;
        this.f1080c = c2652p;
        this.f1081d = c2634ax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static List<String> m477b(List<Locale> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Locale locale : list) {
            if (Build.VERSION.SDK_INT >= 21) {
                arrayList.add(locale.toLanguageTag());
            }
        }
        return arrayList;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(int r2) {
        return this.f1078a.m545b(r2);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return this.f1078a.m543b(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Build.VERSION.SDK_INT < 21 ? Tasks.m469a((Exception) new SplitInstallException(-5)) : this.f1078a.m541c(m477b(list));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Build.VERSION.SDK_INT < 21 ? Tasks.m469a((Exception) new SplitInstallException(-5)) : this.f1078a.m539d(m477b(list));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        this.f1081d.m537a(list);
        return this.f1078a.m547a(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        Set<String> m514b = this.f1080c.m514b();
        return m514b == null ? Collections.emptySet() : m514b;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        return this.f1080c.m516a();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int r2) {
        return this.f1078a.m551a(r2);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        return this.f1078a.m552a();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.f1079b.m641a((StateUpdatedListener) splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int r4) throws IntentSender.SendIntentException {
        return startConfirmationDialogForResult(splitInstallSessionState, new C2669v(activity), r4);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int r11) throws IntentSender.SendIntentException {
        if (splitInstallSessionState.status() != 8 || splitInstallSessionState.resolutionIntent() == null) {
            return false;
        }
        intentSenderForResultStarter.startIntentSenderForResult(splitInstallSessionState.resolutionIntent().getIntentSender(), r11, null, 0, 0, 0, null);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004a, code lost:
        if (r2.containsAll(r3) != false) goto L19;
     */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest r6) {
        /*
            r5 = this;
            java.util.List r0 = r6.getLanguages()
            boolean r0 = r0.isEmpty()
            r1 = 21
            if (r0 != 0) goto L1c
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L11
            goto L1c
        L11:
            com.google.android.play.core.splitinstall.SplitInstallException r6 = new com.google.android.play.core.splitinstall.SplitInstallException
            r0 = -5
            r6.<init>(r0)
            com.google.android.play.core.tasks.Task r6 = com.google.android.play.core.tasks.Tasks.m469a(r6)
            return r6
        L1c:
            java.util.List r0 = r6.getLanguages()
            com.google.android.play.core.splitinstall.p r2 = r5.f1080c
            java.util.Set r2 = r2.m514b()
            if (r2 != 0) goto L29
            goto L4c
        L29:
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Iterator r0 = r0.iterator()
        L32:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L46
            java.lang.Object r4 = r0.next()
            java.util.Locale r4 = (java.util.Locale) r4
            java.lang.String r4 = r4.getLanguage()
            r3.add(r4)
            goto L32
        L46:
            boolean r0 = r2.containsAll(r3)
            if (r0 == 0) goto L82
        L4c:
            java.util.List r0 = r6.getModuleNames()
            java.util.Set r2 = r5.getInstalledModules()
            boolean r0 = r2.containsAll(r0)
            if (r0 == 0) goto L82
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L6e
            java.util.List r0 = r6.getModuleNames()
            com.google.android.play.core.splitinstall.ax r1 = r5.f1081d
            java.util.Set r1 = r1.m538a()
            boolean r0 = java.util.Collections.disjoint(r0, r1)
            if (r0 == 0) goto L82
        L6e:
            android.os.Handler r0 = r5.f1082e
            com.google.android.play.core.splitinstall.u r1 = new com.google.android.play.core.splitinstall.u
            r1.<init>(r5, r6)
            r0.post(r1)
            r6 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            com.google.android.play.core.tasks.Task r6 = com.google.android.play.core.tasks.Tasks.m468a(r6)
            return r6
        L82:
            com.google.android.play.core.splitinstall.av r0 = r5.f1078a
            java.util.List r1 = r6.getModuleNames()
            java.util.List r6 = r6.getLanguages()
            java.util.List r6 = m477b(r6)
            com.google.android.play.core.tasks.Task r6 = r0.m548a(r1, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.C2670w.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.f1079b.m637b(splitInstallStateUpdatedListener);
    }
}
