package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbvg extends zzarv implements zzbvi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbvg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzA(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzB(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(32, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzC(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(21, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzD() throws RemoteException {
        zzbl(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzE() throws RemoteException {
        zzbl(9, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzF(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzd(zza, z);
        zzbl(25, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzG() throws RemoteException {
        zzbl(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzH(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(37, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzI(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(30, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzJ() throws RemoteException {
        zzbl(12, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final boolean zzK() throws RemoteException {
        Parcel zzbk = zzbk(22, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final boolean zzL() throws RemoteException {
        Parcel zzbk = zzbk(13, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvq zzM() throws RemoteException {
        zzbvq zzbvqVar;
        Parcel zzbk = zzbk(15, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbvqVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
            zzbvqVar = queryLocalInterface instanceof zzbvq ? (zzbvq) queryLocalInterface : new zzbvq(readStrongBinder);
        }
        zzbk.recycle();
        return zzbvqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvr zzN() throws RemoteException {
        zzbvr zzbvrVar;
        Parcel zzbk = zzbk(16, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbvrVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
            zzbvrVar = queryLocalInterface instanceof zzbvr ? (zzbvr) queryLocalInterface : new zzbvr(readStrongBinder);
        }
        zzbk.recycle();
        return zzbvrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final Bundle zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final Bundle zzf() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final Bundle zzg() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final com.google.android.gms.ads.internal.client.zzdk zzh() throws RemoteException {
        Parcel zzbk = zzbk(26, zza());
        com.google.android.gms.ads.internal.client.zzdk zzb = com.google.android.gms.ads.internal.client.zzdj.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbmu zzi() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvo zzj() throws RemoteException {
        zzbvo zzbvmVar;
        Parcel zzbk = zzbk(36, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbvmVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationInterscrollerAd");
            zzbvmVar = queryLocalInterface instanceof zzbvo ? (zzbvo) queryLocalInterface : new zzbvm(readStrongBinder);
        }
        zzbk.recycle();
        return zzbvmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvu zzk() throws RemoteException {
        zzbvu zzbvsVar;
        Parcel zzbk = zzbk(27, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbvsVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
            zzbvsVar = queryLocalInterface instanceof zzbvu ? (zzbvu) queryLocalInterface : new zzbvs(readStrongBinder);
        }
        zzbk.recycle();
        return zzbvsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbxl zzl() throws RemoteException {
        Parcel zzbk = zzbk(33, zza());
        zzbxl zzbxlVar = (zzbxl) zzarx.zza(zzbk, zzbxl.CREATOR);
        zzbk.recycle();
        return zzbxlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbxl zzm() throws RemoteException {
        Parcel zzbk = zzbk(34, zza());
        zzbxl zzbxlVar = (zzbxl) zzarx.zza(zzbk, zzbxl.CREATOR);
        zzbk.recycle();
        return zzbxlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final IObjectWrapper zzn() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzo() throws RemoteException {
        zzbl(5, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzp(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzccb zzccbVar, String str2) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzlVar);
        zza.writeString(null);
        zzarx.zzg(zza, zzccbVar);
        zza.writeString(str2);
        zzbl(10, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzq(IObjectWrapper iObjectWrapper, zzbrp zzbrpVar, List list) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbrpVar);
        zza.writeTypedList(list);
        zzbl(31, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzr(IObjectWrapper iObjectWrapper, zzccb zzccbVar, List list) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzccbVar);
        zza.writeStringList(list);
        zzbl(23, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzs(com.google.android.gms.ads.internal.client.zzl zzlVar, String str) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zzbl(11, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzt(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzu(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzqVar);
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(6, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzv(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzqVar);
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(35, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzw(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzx(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzy(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar, zzblo zzbloVar, List list) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zza.writeString(str2);
        zzarx.zzg(zza, zzbvlVar);
        zzarx.zze(zza, zzbloVar);
        zza.writeStringList(list);
        zzbl(14, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzz(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzlVar);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvlVar);
        zzbl(28, zza);
    }
}
