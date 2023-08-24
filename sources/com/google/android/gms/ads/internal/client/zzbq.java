package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzbdi;
import com.google.android.gms.internal.ads.zzbjt;
import com.google.android.gms.internal.ads.zzbzj;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzcbw;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbq extends zzarv implements zzbs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzA() throws RemoteException {
        zzbl(11, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzB() throws RemoteException {
        zzbl(6, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzC(zzbc zzbcVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbcVar);
        zzbl(20, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzD(zzbf zzbfVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbfVar);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzE(zzbw zzbwVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzF(zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzqVar);
        zzbl(13, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzG(zzbz zzbzVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbzVar);
        zzbl(8, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzH(zzbdi zzbdiVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbdiVar);
        zzbl(40, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzI(zzw zzwVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzwVar);
        zzbl(39, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzJ(zzcg zzcgVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzcgVar);
        zzbl(45, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzK(zzdo zzdoVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzL(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzd(zza, z);
        zzbl(34, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzM(zzbzj zzbzjVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzN(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzd(zza, z);
        zzbl(22, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzO(zzbjt zzbjtVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzP(zzde zzdeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzdeVar);
        zzbl(42, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzQ(zzbzm zzbzmVar, String str) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzR(String str) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzS(zzcbw zzcbwVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzT(String str) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzU(zzff zzffVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzffVar);
        zzbl(29, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzW(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(44, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzX() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final boolean zzY() throws RemoteException {
        Parcel zzbk = zzbk(23, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final boolean zzZ() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final boolean zzaa(zzl zzlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        Parcel zzbk = zzbk(4, zza);
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzab(zzcd zzcdVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final Bundle zzd() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final zzq zzg() throws RemoteException {
        Parcel zzbk = zzbk(12, zza());
        zzq zzqVar = (zzq) zzarx.zza(zzbk, zzq.CREATOR);
        zzbk.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final zzbf zzi() throws RemoteException {
        zzbf zzbdVar;
        Parcel zzbk = zzbk(33, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbdVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
            zzbdVar = queryLocalInterface instanceof zzbf ? (zzbf) queryLocalInterface : new zzbd(readStrongBinder);
        }
        zzbk.recycle();
        return zzbdVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final zzbz zzj() throws RemoteException {
        zzbz zzbxVar;
        Parcel zzbk = zzbk(32, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbxVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
            zzbxVar = queryLocalInterface instanceof zzbz ? (zzbz) queryLocalInterface : new zzbx(readStrongBinder);
        }
        zzbk.recycle();
        return zzbxVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final zzdh zzk() throws RemoteException {
        zzdh zzdfVar;
        Parcel zzbk = zzbk(41, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzdfVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
            zzdfVar = queryLocalInterface instanceof zzdh ? (zzdh) queryLocalInterface : new zzdf(readStrongBinder);
        }
        zzbk.recycle();
        return zzdfVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final zzdk zzl() throws RemoteException {
        zzdk zzdiVar;
        Parcel zzbk = zzbk(26, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzdiVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
            zzdiVar = queryLocalInterface instanceof zzdk ? (zzdk) queryLocalInterface : new zzdi(readStrongBinder);
        }
        zzbk.recycle();
        return zzdiVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final IObjectWrapper zzn() throws RemoteException {
        Parcel zzbk = zzbk(1, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final String zzr() throws RemoteException {
        Parcel zzbk = zzbk(31, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final String zzs() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final String zzt() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzx() throws RemoteException {
        zzbl(2, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzy(zzl zzlVar, zzbi zzbiVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        zzarx.zzg(zza, zzbiVar);
        zzbl(43, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzz() throws RemoteException {
        zzbl(5, zza());
    }
}
