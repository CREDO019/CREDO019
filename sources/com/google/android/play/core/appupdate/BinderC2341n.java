package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.appupdate.n */
/* loaded from: classes3.dex */
final class BinderC2341n extends BinderC2340m<Void> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2341n(C2343p c2343p, C2682i<Void> c2682i) {
        super(c2343p, new C2494af("OnCompleteUpdateCallback"), c2682i);
    }

    @Override // com.google.android.play.core.appupdate.BinderC2340m, com.google.android.play.core.internal.InterfaceC2569p
    /* renamed from: b */
    public final void mo674b(Bundle bundle) throws RemoteException {
        int r0;
        int r3;
        super.mo674b(bundle);
        r0 = bundle.getInt("error.code", -2);
        if (r0 == 0) {
            this.f316b.m454b((C2682i<T>) null);
            return;
        }
        C2682i<T> c2682i = this.f316b;
        r3 = bundle.getInt("error.code", -2);
        c2682i.m455b(new InstallException(r3));
    }
}
