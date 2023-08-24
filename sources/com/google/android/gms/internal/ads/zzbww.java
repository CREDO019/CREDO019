package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbww extends zzarv implements zzbwy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbww(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final com.google.android.gms.ads.internal.client.zzdk zze() throws RemoteException {
        Parcel zzbk = zzbk(5, zza());
        com.google.android.gms.ads.internal.client.zzdk zzb = com.google.android.gms.ads.internal.client.zzdj.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final zzbxl zzf() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        zzbxl zzbxlVar = (zzbxl) zzarx.zza(zzbk, zzbxl.CREATOR);
        zzbk.recycle();
        return zzbxlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final zzbxl zzg() throws RemoteException {
        Parcel zzbk = zzbk(3, zza());
        zzbxl zzbxlVar = (zzbxl) zzarx.zza(zzbk, zzbxl.CREATOR);
        zzbk.recycle();
        return zzbxlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzh(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, com.google.android.gms.ads.internal.client.zzq zzqVar, zzbxb zzbxbVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zza.writeString(str);
        zzarx.zze(zza, bundle);
        zzarx.zze(zza, bundle2);
        zzarx.zze(zza, zzqVar);
        zzarx.zzg(zza, zzbxbVar);
        zzbl(1, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzi(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwm zzbwmVar, zzbvl zzbvlVar, com.google.android.gms.ads.internal.client.zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwmVar);
        zzarx.zzg(zza, zzbvlVar);
        zzarx.zze(zza, zzqVar);
        zzbl(13, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzj(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwm zzbwmVar, zzbvl zzbvlVar, com.google.android.gms.ads.internal.client.zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwmVar);
        zzarx.zzg(zza, zzbvlVar);
        zzarx.zze(zza, zzqVar);
        zzbl(21, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzk(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwp zzbwpVar, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwpVar);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(14, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzl(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbws zzbwsVar, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwsVar);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(18, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzm(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbws zzbwsVar, zzbvl zzbvlVar, zzblo zzbloVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwsVar);
        zzarx.zzg(zza, zzbvlVar);
        zzarx.zze(zza, zzbloVar);
        zzbl(22, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzn(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwv zzbwvVar, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwvVar);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(20, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzo(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwv zzbwvVar, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbwvVar);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(16, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final void zzp(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzbl(19, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final boolean zzq(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        Parcel zzbk = zzbk(15, zza);
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbwy
    public final boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        Parcel zzbk = zzbk(17, zza);
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }
}
