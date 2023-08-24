package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractBinderC2568o;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.appupdate.m */
/* loaded from: classes3.dex */
class BinderC2340m<T> extends AbstractBinderC2568o {

    /* renamed from: a */
    final C2494af f315a;

    /* renamed from: b */
    final C2682i<T> f316b;

    /* renamed from: c */
    final /* synthetic */ C2343p f317c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2340m(C2343p c2343p, C2494af c2494af, C2682i<T> c2682i) {
        this.f317c = c2343p;
        this.f315a = c2494af;
        this.f316b = c2682i;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2569p
    /* renamed from: a */
    public void mo675a(Bundle bundle) throws RemoteException {
        this.f317c.f322a.m801a();
        this.f315a.m805c("onRequestInfo", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2569p
    /* renamed from: b */
    public void mo674b(Bundle bundle) throws RemoteException {
        this.f317c.f322a.m801a();
        this.f315a.m805c("onCompleteUpdate", new Object[0]);
    }
}
