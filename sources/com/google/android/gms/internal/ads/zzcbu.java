package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcbu extends zzarv implements zzcbw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zze(zzcbq zzcbqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzcbqVar);
        zzbl(5, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzf() throws RemoteException {
        zzbl(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzg(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzh() throws RemoteException {
        zzbl(6, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzi() throws RemoteException {
        zzbl(1, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzj() throws RemoteException {
        zzbl(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzk() throws RemoteException {
        zzbl(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzcbw
    public final void zzl() throws RemoteException {
        zzbl(3, zza());
    }
}
