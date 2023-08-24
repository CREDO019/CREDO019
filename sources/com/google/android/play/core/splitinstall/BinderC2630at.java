package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.splitinstall.at */
/* loaded from: classes3.dex */
final class BinderC2630at extends BinderC2631au<Integer> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2630at(C2632av c2632av, C2682i<Integer> c2682i) {
        super(c2632av, c2682i);
    }

    @Override // com.google.android.play.core.splitinstall.BinderC2631au, com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: c */
    public final void mo556c(int r1, Bundle bundle) throws RemoteException {
        super.mo556c(r1, bundle);
        this.f974a.m454b((C2682i<T>) Integer.valueOf(r1));
    }
}
