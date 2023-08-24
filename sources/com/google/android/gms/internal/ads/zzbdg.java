package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbdg extends zzarv implements zzbdi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbdg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.appopen.client.IAppOpenAdLoadCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final void zzb(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final void zzc(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzeVar);
        zzbl(3, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final void zzd(zzbdf zzbdfVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbdfVar);
        zzbl(1, zza);
    }
}
