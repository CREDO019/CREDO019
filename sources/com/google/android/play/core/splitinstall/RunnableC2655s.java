package com.google.android.play.core.splitinstall;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.s */
/* loaded from: classes3.dex */
public final class RunnableC2655s implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SplitInstallSessionState f1018a;

    /* renamed from: b */
    final /* synthetic */ int f1019b;

    /* renamed from: c */
    final /* synthetic */ int f1020c;

    /* renamed from: d */
    final /* synthetic */ C2656t f1021d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2655s(C2656t c2656t, SplitInstallSessionState splitInstallSessionState, int r3, int r4) {
        this.f1021d = c2656t;
        this.f1018a = splitInstallSessionState;
        this.f1019b = r3;
        this.f1020c = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C2656t c2656t = this.f1021d;
        SplitInstallSessionState splitInstallSessionState = this.f1018a;
        c2656t.m640a((C2656t) new C2610a(splitInstallSessionState.sessionId(), this.f1019b, this.f1020c, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.mo569a(), splitInstallSessionState.mo568b(), splitInstallSessionState.resolutionIntent(), splitInstallSessionState.mo567c()));
    }
}
