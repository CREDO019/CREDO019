package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.al */
/* loaded from: classes3.dex */
final class BinderC2365al extends BinderC2364ak<ParcelFileDescriptor> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2365al(C2371ar c2371ar, C2682i<ParcelFileDescriptor> c2682i) {
        super(c2371ar, c2682i);
    }

    @Override // com.google.android.play.core.assetpacks.BinderC2364ak, com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: b */
    public final void mo653b(Bundle bundle, Bundle bundle2) throws RemoteException {
        super.mo653b(bundle, bundle2);
        this.f419a.m454b((C2682i<T>) ((ParcelFileDescriptor) bundle.getParcelable("chunk_file_descriptor")));
    }
}
