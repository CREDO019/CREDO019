package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.appupdate.o */
/* loaded from: classes3.dex */
final class BinderC2342o extends BinderC2340m<AppUpdateInfo> {

    /* renamed from: d */
    final /* synthetic */ C2343p f318d;

    /* renamed from: e */
    private final String f319e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BinderC2342o(C2343p c2343p, C2682i<AppUpdateInfo> c2682i, String str) {
        super(c2343p, new C2494af("OnRequestInstallCallback"), c2682i);
        this.f318d = c2343p;
        this.f319e = str;
    }

    @Override // com.google.android.play.core.appupdate.BinderC2340m, com.google.android.play.core.internal.InterfaceC2569p
    /* renamed from: a */
    public final void mo675a(Bundle bundle) throws RemoteException {
        int r0;
        AppUpdateInfo m1073a;
        int r4;
        super.mo675a(bundle);
        r0 = bundle.getInt("error.code", -2);
        if (r0 != 0) {
            C2682i<T> c2682i = this.f316b;
            r4 = bundle.getInt("error.code", -2);
            c2682i.m455b(new InstallException(r4));
            return;
        }
        C2682i<T> c2682i2 = this.f316b;
        m1073a = AppUpdateInfo.m1073a(this.f319e, bundle.getInt("version.code", -1), bundle.getInt("update.availability"), bundle.getInt("install.status", 0), r22.getInt("client.version.staleness", -1) == -1 ? null : Integer.valueOf(bundle.getInt("client.version.staleness")), bundle.getInt("in.app.update.priority", 0), bundle.getLong("bytes.downloaded"), bundle.getLong("total.bytes.to.download"), bundle.getLong("additional.size.required"), this.f318d.f325f.m1059a(), (PendingIntent) bundle.getParcelable("blocking.intent"), (PendingIntent) bundle.getParcelable("nonblocking.intent"), (PendingIntent) bundle.getParcelable("blocking.destructive.intent"), (PendingIntent) bundle.getParcelable("nonblocking.destructive.intent"));
        c2682i2.m454b((C2682i<T>) m1073a);
    }
}
