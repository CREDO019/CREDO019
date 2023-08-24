package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.x */
/* loaded from: classes3.dex */
public final class C2577x extends C2562i implements InterfaceC2578y {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C2577x(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    @Override // com.google.android.play.core.internal.InterfaceC2578y
    /* renamed from: a */
    public final void mo646a(Bundle bundle) throws RemoteException {
        Parcel m683a = m683a();
        C2564k.m679a(m683a, bundle);
        m682a(3, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2578y
    /* renamed from: a */
    public final void mo645a(Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel m683a = m683a();
        C2564k.m679a(m683a, bundle);
        C2564k.m679a(m683a, bundle2);
        m682a(2, m683a);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2578y
    /* renamed from: b */
    public final void mo644b(Bundle bundle) throws RemoteException {
        Parcel m683a = m683a();
        C2564k.m679a(m683a, bundle);
        m682a(4, m683a);
    }
}
