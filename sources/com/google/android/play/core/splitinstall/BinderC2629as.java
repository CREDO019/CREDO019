package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.tasks.C2682i;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.play.core.splitinstall.as */
/* loaded from: classes3.dex */
final class BinderC2629as extends BinderC2631au<List<SplitInstallSessionState>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2629as(C2632av c2632av, C2682i<List<SplitInstallSessionState>> c2682i) {
        super(c2632av, c2682i);
    }

    @Override // com.google.android.play.core.splitinstall.BinderC2631au, com.google.android.play.core.internal.InterfaceC2538bw
    /* renamed from: a */
    public final void mo560a(List<Bundle> list) throws RemoteException {
        super.mo560a(list);
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int r2 = 0; r2 < size; r2++) {
            arrayList.add(SplitInstallSessionState.m570a(list.get(r2)));
        }
        this.f974a.m454b((C2682i<T>) arrayList);
    }
}
