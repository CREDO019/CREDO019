package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcfc extends zzarv implements zzcfe {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcfc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.signals.ISignalGenerator");
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zze(IObjectWrapper iObjectWrapper, zzcfi zzcfiVar, zzcfb zzcfbVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzcfiVar);
        zzarx.zzg(zza, zzcfbVar);
        zzbl(1, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzf(zzbzy zzbzyVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzbzyVar);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzg(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbzpVar);
        zzbl(10, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzh(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbzpVar);
        zzbl(9, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(8, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzk(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbzpVar);
        zzbl(6, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzl(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbzpVar);
        zzbl(5, zza);
    }
}
