package com.google.android.gms.internal.gcm;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public class zzd implements IInterface {
    private final IBinder zzd;
    private final String zze;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzd(IBinder iBinder, String str) {
        this.zzd = iBinder;
        this.zze = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parcel zzd() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zze);
        return obtain;
    }

    protected final void zzd(int r4, Parcel parcel) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            this.zzd.transact(2, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zze(int r3, Parcel parcel) throws RemoteException {
        try {
            this.zzd.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
