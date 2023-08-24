package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class zzh extends com.google.android.gms.internal.maps.zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            IObjectWrapper zzc = zzc(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzf(parcel2, zzc);
        } else if (r2 != 2) {
            return false;
        } else {
            IObjectWrapper zzb = zzb(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzf(parcel2, zzb);
        }
        return true;
    }
}
