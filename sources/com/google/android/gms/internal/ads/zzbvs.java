package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbvs extends zzarv implements zzbvu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbvs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final boolean zzA() throws RemoteException {
        Parcel zzbk = zzbk(18, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final boolean zzB() throws RemoteException {
        Parcel zzbk = zzbk(17, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final double zze() throws RemoteException {
        Parcel zzbk = zzbk(8, zza());
        double readDouble = zzbk.readDouble();
        zzbk.recycle();
        return readDouble;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final float zzf() throws RemoteException {
        Parcel zzbk = zzbk(23, zza());
        float readFloat = zzbk.readFloat();
        zzbk.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final float zzg() throws RemoteException {
        Parcel zzbk = zzbk(25, zza());
        float readFloat = zzbk.readFloat();
        zzbk.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final float zzh() throws RemoteException {
        Parcel zzbk = zzbk(24, zza());
        float readFloat = zzbk.readFloat();
        zzbk.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final Bundle zzi() throws RemoteException {
        Parcel zzbk = zzbk(16, zza());
        Bundle bundle = (Bundle) zzarx.zza(zzbk, Bundle.CREATOR);
        zzbk.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final com.google.android.gms.ads.internal.client.zzdk zzj() throws RemoteException {
        Parcel zzbk = zzbk(11, zza());
        com.google.android.gms.ads.internal.client.zzdk zzb = com.google.android.gms.ads.internal.client.zzdj.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final zzbls zzk() throws RemoteException {
        Parcel zzbk = zzbk(12, zza());
        zzbls zzj = zzblr.zzj(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzj;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final zzbma zzl() throws RemoteException {
        Parcel zzbk = zzbk(5, zza());
        zzbma zzg = zzblz.zzg(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final IObjectWrapper zzm() throws RemoteException {
        Parcel zzbk = zzbk(13, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final IObjectWrapper zzn() throws RemoteException {
        Parcel zzbk = zzbk(14, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final IObjectWrapper zzo() throws RemoteException {
        Parcel zzbk = zzbk(15, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final String zzp() throws RemoteException {
        Parcel zzbk = zzbk(7, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final String zzq() throws RemoteException {
        Parcel zzbk = zzbk(4, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final String zzr() throws RemoteException {
        Parcel zzbk = zzbk(6, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final String zzs() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final String zzt() throws RemoteException {
        Parcel zzbk = zzbk(10, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final String zzu() throws RemoteException {
        Parcel zzbk = zzbk(9, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final List zzv() throws RemoteException {
        Parcel zzbk = zzbk(3, zza());
        ArrayList zzb = zzarx.zzb(zzbk);
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final void zzw(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(20, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final void zzx() throws RemoteException {
        zzbl(19, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final void zzy(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, iObjectWrapper2);
        zzarx.zzg(zza, iObjectWrapper3);
        zzbl(21, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvu
    public final void zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(22, zza);
    }
}
