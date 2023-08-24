package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbse extends zzarv implements zzbsg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbse(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.instream.client.IInstreamAdLoadCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzbsg
    public final void zze(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbsg
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzeVar);
        zzbl(3, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbsg
    public final void zzg(zzbsa zzbsaVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbsaVar);
        zzbl(1, zza);
    }
}
