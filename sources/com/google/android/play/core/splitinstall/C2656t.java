package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.listener.AbstractC2581b;

/* renamed from: com.google.android.play.core.splitinstall.t */
/* loaded from: classes3.dex */
public final class C2656t extends AbstractC2581b<SplitInstallSessionState> {

    /* renamed from: c */
    private static C2656t f1022c;

    /* renamed from: d */
    private final Handler f1023d;

    /* renamed from: e */
    private final InterfaceC2640e f1024e;

    public C2656t(Context context, InterfaceC2640e interfaceC2640e) {
        super(new C2494af("SplitInstallListenerRegistry"), new IntentFilter("com.google.android.play.core.splitinstall.receiver.SplitInstallUpdateIntentService"), context);
        this.f1023d = new Handler(Looper.getMainLooper());
        this.f1024e = interfaceC2640e;
    }

    /* renamed from: a */
    public static synchronized C2656t m508a(Context context) {
        C2656t c2656t;
        synchronized (C2656t.class) {
            if (f1022c == null) {
                f1022c = new C2656t(context, EnumC2647l.f1005a);
            }
            c2656t = f1022c;
        }
        return c2656t;
    }

    @Override // com.google.android.play.core.listener.AbstractC2581b
    /* renamed from: a */
    protected final void mo507a(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra == null) {
            return;
        }
        SplitInstallSessionState m570a = SplitInstallSessionState.m570a(bundleExtra);
        this.f868a.m808a("ListenerRegistryBroadcastReceiver.onReceive: %s", m570a);
        InterfaceC2641f mo524a = this.f1024e.mo524a();
        if (m570a.status() != 3 || mo524a == null) {
            m640a((C2656t) m570a);
        } else {
            mo524a.mo530a(m570a.mo567c(), new C2654r(this, m570a, intent, context));
        }
    }
}
