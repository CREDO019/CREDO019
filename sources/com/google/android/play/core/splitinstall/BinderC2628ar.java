package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.splitinstall.ar */
/* loaded from: classes3.dex */
final class BinderC2628ar extends BinderC2631au<SplitInstallSessionState> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2628ar(C2632av c2632av, C2682i<SplitInstallSessionState> c2682i) {
        super(c2632av, c2682i);
    }

    @Override // com.google.android.play.core.splitinstall.BinderC2631au, com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: b */
    public final void mo558b(int r1, Bundle bundle) throws RemoteException {
        super.mo558b(r1, bundle);
        this.f974a.m454b((C2682i<T>) SplitInstallSessionState.m570a(bundle));
    }
}
