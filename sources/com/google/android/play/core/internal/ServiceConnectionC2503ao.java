package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.internal.ao */
/* loaded from: classes3.dex */
public final class ServiceConnectionC2503ao implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ C2504ap f814a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ ServiceConnectionC2503ao(C2504ap c2504ap) {
        this.f814a = c2504ap;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C2494af c2494af;
        c2494af = this.f814a.f817c;
        c2494af.m805c("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.f814a.m795b(new C2501am(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        C2494af c2494af;
        c2494af = this.f814a.f817c;
        c2494af.m805c("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.f814a.m795b(new C2502an(this));
    }
}
