package com.google.android.p010a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.a.b */
/* loaded from: classes2.dex */
public class BaseStub extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public BaseStub(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        throw null;
    }
}
