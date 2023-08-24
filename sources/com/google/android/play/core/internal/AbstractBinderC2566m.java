package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

/* renamed from: com.google.android.play.core.internal.m */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2566m extends BinderC2563j implements InterfaceC2567n {
    /* renamed from: a */
    public static InterfaceC2567n m678a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.appupdate.protocol.IAppUpdateService");
        return queryLocalInterface instanceof InterfaceC2567n ? (InterfaceC2567n) queryLocalInterface : new C2565l(iBinder);
    }
}
