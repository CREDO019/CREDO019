package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractBinderC2537bv;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;
import java.util.List;

/* renamed from: com.google.android.play.core.splitinstall.au */
/* loaded from: classes3.dex */
class BinderC2631au<T> extends AbstractBinderC2537bv {

    /* renamed from: a */
    final C2682i<T> f974a;

    /* renamed from: b */
    final /* synthetic */ C2632av f975b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2631au(C2632av c2632av, C2682i<T> c2682i) {
        this.f975b = c2632av;
        this.f974a = c2682i;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: a */
    public final void mo564a() throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onCompleteInstallForAppUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: a */
    public final void mo563a(int r4) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onCompleteInstall(%d)", Integer.valueOf(r4));
    }

    /* renamed from: a */
    public void mo562a(int r3, Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onCancelInstall(%d)", Integer.valueOf(r3));
    }

    /* renamed from: a */
    public void mo561a(Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onDeferredInstall", new Object[0]);
    }

    /* renamed from: a */
    public void mo560a(List<Bundle> list) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onGetSessionStates", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: b */
    public final void mo559b() throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onGetSplitsForAppUpdate", new Object[0]);
    }

    /* renamed from: b */
    public void mo558b(int r3, Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onGetSession(%d)", Integer.valueOf(r3));
    }

    /* renamed from: b */
    public void mo557b(Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onDeferredLanguageInstall", new Object[0]);
    }

    /* renamed from: c */
    public void mo556c(int r3, Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onStartInstall(%d)", Integer.valueOf(r3));
    }

    /* renamed from: c */
    public void mo555c(Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onDeferredLanguageUninstall", new Object[0]);
    }

    /* renamed from: d */
    public void mo554d(Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        c2494af = C2632av.f976b;
        c2494af.m805c("onDeferredUninstall", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: e */
    public final void mo553e(Bundle bundle) throws RemoteException {
        C2494af c2494af;
        this.f975b.f978a.m801a();
        int r5 = bundle.getInt("error_code");
        c2494af = C2632av.f976b;
        c2494af.m806b("onError(%d)", Integer.valueOf(r5));
        this.f974a.m455b(new SplitInstallException(r5));
    }
}
