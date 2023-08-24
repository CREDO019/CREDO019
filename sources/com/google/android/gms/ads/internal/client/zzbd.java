package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbd extends zzarv implements zzbf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdListener");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzc() throws RemoteException {
        zzbl(6, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzd() throws RemoteException {
        zzbl(1, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zze(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzf(zze zzeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzeVar);
        zzbl(8, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzg() throws RemoteException {
        zzbl(7, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzh() throws RemoteException {
        zzbl(3, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzi() throws RemoteException {
        zzbl(4, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzj() throws RemoteException {
        zzbl(5, zza());
    }
}
