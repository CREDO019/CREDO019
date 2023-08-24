package com.google.android.p010a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.a.a */
/* loaded from: classes2.dex */
public class BaseProxy implements IInterface {

    /* renamed from: a */
    private final IBinder f204a;

    /* renamed from: b */
    private final String f205b;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseProxy(IBinder iBinder, String str) {
        this.f204a = iBinder;
        this.f205b = str;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f204a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel m1240a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f205b);
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel m1239a(Parcel parcel) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            try {
                this.f204a.transact(1, parcel, obtain, 0);
                obtain.readException();
                return obtain;
            } catch (RuntimeException e) {
                obtain.recycle();
                throw e;
            }
        } finally {
            parcel.recycle();
        }
    }
}
