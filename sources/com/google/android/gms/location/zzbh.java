package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes3.dex */
public abstract class zzbh extends com.google.android.gms.internal.location.zzb implements zzbi {
    public zzbh() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzbi zzb(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return queryLocalInterface instanceof zzbi ? (zzbi) queryLocalInterface : new zzbg(iBinder);
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean zza(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            zze((LocationResult) com.google.android.gms.internal.location.zzc.zza(parcel, LocationResult.CREATOR));
        } else if (r1 != 2) {
            return false;
        } else {
            zzd((LocationAvailability) com.google.android.gms.internal.location.zzc.zza(parcel, LocationAvailability.CREATOR));
        }
        return true;
    }
}
