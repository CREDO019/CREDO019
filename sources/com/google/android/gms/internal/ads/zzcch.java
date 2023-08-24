package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcch extends zzarv implements zzccj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcch(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final Bundle zzb() throws RemoteException {
        Parcel zzbk = zzbk(9, zza());
        Bundle bundle = (Bundle) zzarx.zza(zzbk, Bundle.CREATOR);
        zzbk.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final com.google.android.gms.ads.internal.client.zzdh zzc() throws RemoteException {
        Parcel zzbk = zzbk(12, zza());
        com.google.android.gms.ads.internal.client.zzdh zzb = com.google.android.gms.ads.internal.client.zzdg.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final zzccg zzd() throws RemoteException {
        zzccg zzcceVar;
        Parcel zzbk = zzbk(11, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzcceVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
            zzcceVar = queryLocalInterface instanceof zzccg ? (zzccg) queryLocalInterface : new zzcce(readStrongBinder);
        }
        zzbk.recycle();
        return zzcceVar;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final String zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzf(com.google.android.gms.ads.internal.client.zzl zzlVar, zzccq zzccqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, zzccqVar);
        zzbl(1, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzg(com.google.android.gms.ads.internal.client.zzl zzlVar, zzccq zzccqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, zzccqVar);
        zzbl(14, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzh(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzd(zza, z);
        zzbl(15, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzi(com.google.android.gms.ads.internal.client.zzdb zzdbVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzdbVar);
        zzbl(8, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzj(com.google.android.gms.ads.internal.client.zzde zzdeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzdeVar);
        zzbl(13, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzk(zzccm zzccmVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzccmVar);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzl(zzccx zzccxVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzccxVar);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(5, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzn(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final boolean zzo() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzp(zzccr zzccrVar) throws RemoteException {
        throw null;
    }
}
