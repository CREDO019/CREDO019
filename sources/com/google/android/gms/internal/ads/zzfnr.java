package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfnr extends zzarv implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfnr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    public final zzfnp zze(zzfnn zzfnnVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzfnnVar);
        Parcel zzbk = zzbk(1, zza);
        zzfnp zzfnpVar = (zzfnp) zzarx.zza(zzbk, zzfnp.CREATOR);
        zzbk.recycle();
        return zzfnpVar;
    }

    public final zzfny zzf(zzfnw zzfnwVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzfnwVar);
        Parcel zzbk = zzbk(3, zza);
        zzfny zzfnyVar = (zzfny) zzarx.zza(zzbk, zzfny.CREATOR);
        zzbk.recycle();
        return zzfnyVar;
    }

    public final void zzg(zzfnk zzfnkVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzfnkVar);
        zzbl(2, zza);
    }
}
