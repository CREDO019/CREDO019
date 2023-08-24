package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class zzau extends com.google.android.gms.internal.maps.zzb implements zzav {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zzd(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
        } else if (r2 == 2) {
            zzb(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
        } else if (r2 != 3) {
            return false;
        } else {
            zzc(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
