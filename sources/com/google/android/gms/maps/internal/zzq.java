package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class zzq extends com.google.android.gms.internal.maps.zzb implements zzr {
    public zzq() {
        super("com.google.android.gms.maps.internal.IOnCameraMoveCanceledListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            zzb();
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
