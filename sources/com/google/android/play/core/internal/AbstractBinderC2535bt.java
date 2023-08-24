package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

/* renamed from: com.google.android.play.core.internal.bt */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2535bt extends BinderC2563j implements InterfaceC2536bu {
    /* renamed from: a */
    public static InterfaceC2536bu m734a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
        return queryLocalInterface instanceof InterfaceC2536bu ? (InterfaceC2536bu) queryLocalInterface : new C2534bs(iBinder);
    }
}
