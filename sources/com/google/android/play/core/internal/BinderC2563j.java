package com.google.android.play.core.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.j */
/* loaded from: classes3.dex */
public class BinderC2563j extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public BinderC2563j(String str) {
        attachInterface(this, str);
    }

    /* renamed from: a */
    protected boolean mo649a(int r1, Parcel parcel) throws RemoteException {
        throw null;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 <= 16777215) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(r2, parcel, parcel2, r5)) {
            return true;
        }
        return mo649a(r2, parcel);
    }
}
