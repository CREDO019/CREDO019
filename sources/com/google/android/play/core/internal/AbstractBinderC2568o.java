package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.o */
/* loaded from: classes3.dex */
public abstract class AbstractBinderC2568o extends BinderC2563j implements InterfaceC2569p {
    public AbstractBinderC2568o() {
        super("com.google.android.play.core.appupdate.protocol.IAppUpdateServiceCallback");
    }

    @Override // com.google.android.play.core.internal.BinderC2563j
    /* renamed from: a */
    protected final boolean mo649a(int r2, Parcel parcel) throws RemoteException {
        if (r2 == 2) {
            mo675a((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
            return true;
        } else if (r2 != 3) {
            return false;
        } else {
            mo674b((Bundle) C2564k.m680a(parcel, Bundle.CREATOR));
            return true;
        }
    }
}
