package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* renamed from: com.google.android.play.core.internal.q */
/* loaded from: classes3.dex */
public final class C2570q extends C2562i implements InterfaceC2572s {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C2570q(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: a */
    public final void mo672a(String str, Bundle bundle, Bundle bundle2, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m679a(m683a, bundle2);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(6, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: a */
    public final void mo671a(String str, Bundle bundle, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(5, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: a */
    public final void mo670a(String str, List<Bundle> list, Bundle bundle, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(2, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: b */
    public final void mo669b(String str, Bundle bundle, Bundle bundle2, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m679a(m683a, bundle2);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(7, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: b */
    public final void mo668b(String str, Bundle bundle, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(10, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: b */
    public final void mo667b(String str, List<Bundle> list, Bundle bundle, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(14, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: c */
    public final void mo666c(String str, Bundle bundle, Bundle bundle2, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m679a(m683a, bundle2);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(9, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: c */
    public final void mo665c(String str, List<Bundle> list, Bundle bundle, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(12, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: d */
    public final void mo664d(String str, Bundle bundle, Bundle bundle2, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m679a(m683a, bundle2);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(11, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2572s
    /* renamed from: e */
    public final void mo663e(String str, Bundle bundle, Bundle bundle2, InterfaceC2574u interfaceC2574u) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m679a(m683a, bundle2);
        C2564k.m681a(m683a, interfaceC2574u);
        m682a(13, m683a);
    }
}
