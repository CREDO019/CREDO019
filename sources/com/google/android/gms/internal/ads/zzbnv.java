package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbnv extends zzarv implements zzbnx {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbnv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzA() throws RemoteException {
        zzbl(28, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzB(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        zzbl(17, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzC() throws RemoteException {
        zzbl(27, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzD(com.google.android.gms.ads.internal.client.zzcq zzcqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzcqVar);
        zzbl(26, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzE(com.google.android.gms.ads.internal.client.zzde zzdeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzdeVar);
        zzbl(32, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzF(zzbnu zzbnuVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbnuVar);
        zzbl(21, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final boolean zzG() throws RemoteException {
        Parcel zzbk = zzbk(30, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final boolean zzH() throws RemoteException {
        Parcel zzbk = zzbk(24, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final boolean zzI(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        Parcel zzbk = zzbk(16, zza);
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final double zze() throws RemoteException {
        Parcel zzbk = zzbk(8, zza());
        double readDouble = zzbk.readDouble();
        zzbk.recycle();
        return readDouble;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final Bundle zzf() throws RemoteException {
        Parcel zzbk = zzbk(20, zza());
        Bundle bundle = (Bundle) zzarx.zza(zzbk, Bundle.CREATOR);
        zzbk.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final com.google.android.gms.ads.internal.client.zzdh zzg() throws RemoteException {
        Parcel zzbk = zzbk(31, zza());
        com.google.android.gms.ads.internal.client.zzdh zzb = com.google.android.gms.ads.internal.client.zzdg.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final com.google.android.gms.ads.internal.client.zzdk zzh() throws RemoteException {
        Parcel zzbk = zzbk(11, zza());
        com.google.android.gms.ads.internal.client.zzdk zzb = com.google.android.gms.ads.internal.client.zzdj.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final zzbls zzi() throws RemoteException {
        zzbls zzblqVar;
        Parcel zzbk = zzbk(14, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzblqVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
            zzblqVar = queryLocalInterface instanceof zzbls ? (zzbls) queryLocalInterface : new zzblq(readStrongBinder);
        }
        zzbk.recycle();
        return zzblqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final zzblx zzj() throws RemoteException {
        zzblx zzblvVar;
        Parcel zzbk = zzbk(29, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzblvVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IMediaContent");
            zzblvVar = queryLocalInterface instanceof zzblx ? (zzblx) queryLocalInterface : new zzblv(readStrongBinder);
        }
        zzbk.recycle();
        return zzblvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final zzbma zzk() throws RemoteException {
        zzbma zzblyVar;
        Parcel zzbk = zzbk(5, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzblyVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            zzblyVar = queryLocalInterface instanceof zzbma ? (zzbma) queryLocalInterface : new zzbly(readStrongBinder);
        }
        zzbk.recycle();
        return zzblyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final IObjectWrapper zzl() throws RemoteException {
        Parcel zzbk = zzbk(19, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final IObjectWrapper zzm() throws RemoteException {
        Parcel zzbk = zzbk(18, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzn() throws RemoteException {
        Parcel zzbk = zzbk(7, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzo() throws RemoteException {
        Parcel zzbk = zzbk(4, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzp() throws RemoteException {
        Parcel zzbk = zzbk(6, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzq() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzr() throws RemoteException {
        Parcel zzbk = zzbk(12, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzs() throws RemoteException {
        Parcel zzbk = zzbk(10, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final String zzt() throws RemoteException {
        Parcel zzbk = zzbk(9, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final List zzu() throws RemoteException {
        Parcel zzbk = zzbk(3, zza());
        ArrayList zzb = zzarx.zzb(zzbk);
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final List zzv() throws RemoteException {
        Parcel zzbk = zzbk(23, zza());
        ArrayList zzb = zzarx.zzb(zzbk);
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzw() throws RemoteException {
        zzbl(22, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzx() throws RemoteException {
        zzbl(13, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzy(com.google.android.gms.ads.internal.client.zzcu zzcuVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzcuVar);
        zzbl(25, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbnx
    public final void zzz(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        zzbl(15, zza);
    }
}
