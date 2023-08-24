package com.google.android.play.core.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.listener.AbstractC2581b;

/* renamed from: com.google.android.play.core.appupdate.a */
/* loaded from: classes3.dex */
public final class C2328a extends AbstractC2581b<InstallState> {
    public C2328a(Context context) {
        super(new C2494af("AppUpdateListenerRegistry"), new IntentFilter("com.google.android.play.core.install.ACTION_INSTALL_STATUS"), context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.listener.AbstractC2581b
    /* renamed from: a */
    public final void mo507a(Context context, Intent intent) {
        if (!context.getPackageName().equals(intent.getStringExtra("package.name"))) {
            this.f868a.m808a("ListenerRegistryBroadcastReceiver received broadcast for third party app: %s", intent.getStringExtra("package.name"));
            return;
        }
        this.f868a.m808a("List of extras in received intent:", new Object[0]);
        for (String str : intent.getExtras().keySet()) {
            this.f868a.m808a("Key: %s; value: %s", str, intent.getExtras().get(str));
        }
        C2494af c2494af = this.f868a;
        c2494af.m808a("List of extras in received intent needed by fromUpdateIntent:", new Object[0]);
        c2494af.m808a("Key: %s; value: %s", "install.status", Integer.valueOf(intent.getIntExtra("install.status", 0)));
        c2494af.m808a("Key: %s; value: %s", "error.code", Integer.valueOf(intent.getIntExtra("error.code", 0)));
        InstallState m816a = InstallState.m816a(intent.getIntExtra("install.status", 0), intent.getLongExtra("bytes.downloaded", 0L), intent.getLongExtra("total.bytes.to.download", 0L), intent.getIntExtra("error.code", 0), intent.getStringExtra("package.name"));
        this.f868a.m808a("ListenerRegistryBroadcastReceiver.onReceive: %s", m816a);
        m640a((C2328a) m816a);
    }
}
