package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

/* renamed from: com.google.android.play.core.internal.r */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2571r extends BinderC2563j implements InterfaceC2572s {
    /* renamed from: a */
    public static InterfaceC2572s m673a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
        return queryLocalInterface instanceof InterfaceC2572s ? (InterfaceC2572s) queryLocalInterface : new C2570q(iBinder);
    }
}
