package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* renamed from: com.google.android.play.core.internal.bs */
/* loaded from: classes3.dex */
public final class C2534bs extends C2562i implements InterfaceC2536bu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C2534bs(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: a */
    public final void mo733a(String str, int r3, Bundle bundle, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeInt(r3);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(4, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: a */
    public final void mo732a(String str, int r3, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeInt(r3);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(5, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: a */
    public final void mo731a(String str, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(6, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: a */
    public final void mo730a(String str, List<Bundle> list, Bundle bundle, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(2, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: b */
    public final void mo729b(String str, List<Bundle> list, Bundle bundle, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(7, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: c */
    public final void mo728c(String str, List<Bundle> list, Bundle bundle, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(8, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: d */
    public final void mo727d(String str, List<Bundle> list, Bundle bundle, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(13, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2536bu
    /* renamed from: e */
    public final void mo726e(String str, List<Bundle> list, Bundle bundle, InterfaceC2538bw interfaceC2538bw) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        m683a.writeTypedList(list);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2538bw);
        m682a(14, m683a);
    }
}
