package com.google.android.gms.internal.vision;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zza extends Binder implements IInterface {
    private static zzc zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zza(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    protected boolean dispatchTransaction(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        return false;
    }

    @Override // android.os.Binder
    public boolean onTransact(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        boolean z;
        if (r2 > 16777215) {
            z = super.onTransact(r2, parcel, parcel2, r5);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            z = false;
        }
        if (z) {
            return true;
        }
        return dispatchTransaction(r2, parcel, parcel2, r5);
    }
}
