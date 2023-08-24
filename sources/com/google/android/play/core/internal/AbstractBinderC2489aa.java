package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

/* renamed from: com.google.android.play.core.internal.aa */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2489aa extends BinderC2563j implements InterfaceC2490ab {
    /* renamed from: a */
    public static InterfaceC2490ab m814a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.inappreview.protocol.IInAppReviewService");
        return queryLocalInterface instanceof InterfaceC2490ab ? (InterfaceC2490ab) queryLocalInterface : new C2579z(iBinder);
    }
}
