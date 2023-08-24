package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcck extends zzarv implements zzccm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcck(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zze() throws RemoteException {
        zzbl(7, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zzf() throws RemoteException {
        zzbl(6, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zzg() throws RemoteException {
        zzbl(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zzh(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(4, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zzi(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzeVar);
        zzbl(5, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zzj() throws RemoteException {
        zzbl(1, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzccm
    public final void zzk(zzccg zzccgVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzccgVar);
        zzbl(3, zza);
    }
}
