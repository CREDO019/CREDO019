package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.IntentSender;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.InterfaceC2552cj;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import com.google.android.play.core.tasks.Task;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.i */
/* loaded from: classes3.dex */
public final class C2644i implements SplitInstallManager {

    /* renamed from: a */
    private final InterfaceC2552cj<C2670w> f998a;

    /* renamed from: b */
    private final InterfaceC2552cj<FakeSplitInstallManager> f999b;

    /* renamed from: c */
    private final InterfaceC2552cj<File> f1000c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2644i(InterfaceC2552cj<C2670w> interfaceC2552cj, InterfaceC2552cj<FakeSplitInstallManager> interfaceC2552cj2, InterfaceC2552cj<File> interfaceC2552cj3) {
        this.f998a = interfaceC2552cj;
        this.f999b = interfaceC2552cj2;
        this.f1000c = interfaceC2552cj3;
    }

    /* renamed from: a */
    private final SplitInstallManager m526a() {
        return (SplitInstallManager) (this.f1000c.m713a() == null ? this.f998a : this.f999b).m713a();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(int r2) {
        return m526a().cancelInstall(r2);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return m526a().deferredInstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return m526a().deferredLanguageInstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return m526a().deferredLanguageUninstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        return m526a().deferredUninstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        return m526a().getInstalledLanguages();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        return m526a().getInstalledModules();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int r2) {
        return m526a().getSessionState(r2);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        return m526a().getSessionStates();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        m526a().registerListener(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int r4) throws IntentSender.SendIntentException {
        return m526a().startConfirmationDialogForResult(splitInstallSessionState, activity, r4);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int r4) throws IntentSender.SendIntentException {
        return m526a().startConfirmationDialogForResult(splitInstallSessionState, intentSenderForResultStarter, r4);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Integer> startInstall(SplitInstallRequest splitInstallRequest) {
        return m526a().startInstall(splitInstallRequest);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        m526a().unregisterListener(splitInstallStateUpdatedListener);
    }
}
