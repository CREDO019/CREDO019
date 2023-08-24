package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.play.core.internal.i */
/* loaded from: classes3.dex */
public class C2562i implements IInterface {

    /* renamed from: a */
    private final IBinder f865a;

    /* renamed from: b */
    private final String f866b;

    /* JADX INFO: Access modifiers changed from: protected */
    public C2562i(IBinder iBinder, String str) {
        this.f865a = iBinder;
        this.f866b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel m683a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f866b);
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m682a(int r4, Parcel parcel) throws RemoteException {
        try {
            this.f865a.transact(r4, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f865a;
    }
}
