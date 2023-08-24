package com.google.android.gms.internal.appset;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-appset@@16.0.0 */
/* loaded from: classes2.dex */
public class zzb extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public zzb(String str) {
        attachInterface(this, "com.google.android.gms.appset.internal.IAppSetIdCallback");
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 > 16777215) {
            if (super.onTransact(r2, parcel, parcel2, r5)) {
                return true;
            }
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
        }
        return zza(r2, parcel, parcel2, r5);
    }

    protected boolean zza(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        throw null;
    }
}
