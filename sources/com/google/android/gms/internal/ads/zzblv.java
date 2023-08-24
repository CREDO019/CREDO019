package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzblv extends zzarv implements zzblx {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzblv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IMediaContent");
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final float zze() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        float readFloat = zzbk.readFloat();
        zzbk.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final float zzf() throws RemoteException {
        Parcel zzbk = zzbk(6, zza());
        float readFloat = zzbk.readFloat();
        zzbk.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final float zzg() throws RemoteException {
        Parcel zzbk = zzbk(5, zza());
        float readFloat = zzbk.readFloat();
        zzbk.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final com.google.android.gms.ads.internal.client.zzdk zzh() throws RemoteException {
        Parcel zzbk = zzbk(7, zza());
        com.google.android.gms.ads.internal.client.zzdk zzb = com.google.android.gms.ads.internal.client.zzdj.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final IObjectWrapper zzi() throws RemoteException {
        Parcel zzbk = zzbk(4, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(3, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final boolean zzk() throws RemoteException {
        Parcel zzbk = zzbk(8, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzblx
    public final void zzl(zzbni zzbniVar) throws RemoteException {
        throw null;
    }
}
