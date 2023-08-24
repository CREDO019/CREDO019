package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.internal.C2494af;

/* renamed from: com.google.android.play.core.splitinstall.r */
/* loaded from: classes3.dex */
final class C2654r implements InterfaceC2639d {

    /* renamed from: a */
    final /* synthetic */ SplitInstallSessionState f1014a;

    /* renamed from: b */
    final /* synthetic */ Intent f1015b;

    /* renamed from: c */
    final /* synthetic */ Context f1016c;

    /* renamed from: d */
    final /* synthetic */ C2656t f1017d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2654r(C2656t c2656t, SplitInstallSessionState splitInstallSessionState, Intent intent, Context context) {
        this.f1017d = c2656t;
        this.f1014a = splitInstallSessionState;
        this.f1015b = intent;
        this.f1016c = context;
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2639d
    /* renamed from: a */
    public final void mo483a() {
        r0.f1023d.post(new RunnableC2655s(this.f1017d, this.f1014a, 5, 0));
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2639d
    /* renamed from: a */
    public final void mo482a(int r4) {
        r0.f1023d.post(new RunnableC2655s(this.f1017d, this.f1014a, 6, r4));
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2639d
    /* renamed from: b */
    public final void mo481b() {
        C2494af c2494af;
        if (this.f1015b.getBooleanExtra("triggered_from_app_after_verification", false)) {
            c2494af = this.f1017d.f868a;
            c2494af.m806b("Splits copied and verified more than once.", new Object[0]);
            return;
        }
        this.f1015b.putExtra("triggered_from_app_after_verification", true);
        this.f1016c.sendBroadcast(this.f1015b);
    }
}
