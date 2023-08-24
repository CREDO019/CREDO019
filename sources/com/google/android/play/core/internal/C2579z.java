package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.z */
/* loaded from: classes3.dex */
public final class C2579z extends C2562i implements InterfaceC2490ab {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C2579z(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.inappreview.protocol.IInAppReviewService");
    }

    @Override // com.google.android.play.core.internal.InterfaceC2490ab
    /* renamed from: a */
    public final void mo643a(String str, Bundle bundle, InterfaceC2492ad interfaceC2492ad) throws RemoteException {
        Parcel m683a = m683a();
        m683a.writeString(str);
        C2564k.m679a(m683a, bundle);
        C2564k.m681a(m683a, interfaceC2492ad);
        m682a(2, m683a);
    }
}
