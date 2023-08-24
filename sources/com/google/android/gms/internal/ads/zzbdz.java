package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbdz extends zzarv implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbdz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.cache.ICacheService");
    }

    public final long zze(zzbdx zzbdxVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzbdxVar);
        Parcel zzbk = zzbk(3, zza);
        long readLong = zzbk.readLong();
        zzbk.recycle();
        return readLong;
    }

    public final zzbdu zzf(zzbdx zzbdxVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzbdxVar);
        Parcel zzbk = zzbk(1, zza);
        zzbdu zzbduVar = (zzbdu) zzarx.zza(zzbk, zzbdu.CREATOR);
        zzbk.recycle();
        return zzbduVar;
    }

    public final zzbdu zzg(zzbdx zzbdxVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzbdxVar);
        Parcel zzbk = zzbk(2, zza);
        zzbdu zzbduVar = (zzbdu) zzarx.zza(zzbk, zzbdu.CREATOR);
        zzbk.recycle();
        return zzbduVar;
    }
}
