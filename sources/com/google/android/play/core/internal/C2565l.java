package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.l */
/* loaded from: classes3.dex */
public final class C2565l extends C2562i implements InterfaceC2567n {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C2565l(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.appupdate.protocol.IAppUpdateService");
    }

    @Override // com.google.android.play.core.internal.InterfaceC2567n
    /* renamed from: a */
    public final void mo677a(String str, Bundle bundle, InterfaceC2569p interfaceC2569p) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2569p);
        m682a(2, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2567n
    /* renamed from: b */
    public final void mo676b(String str, Bundle bundle, InterfaceC2569p interfaceC2569p) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2569p);
        m682a(3, m683a);
    }
}
