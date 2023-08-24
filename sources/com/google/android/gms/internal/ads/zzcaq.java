package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcaq extends zzarv implements zzcas {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcaq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zze(zzcba zzcbaVar, zzcaw zzcawVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzcbaVar);
        zzarx.zzg(zza, zzcawVar);
        zzbl(6, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zzf(zzcba zzcbaVar, zzcaw zzcawVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzcbaVar);
        zzarx.zzg(zza, zzcawVar);
        zzbl(5, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zzg(zzcba zzcbaVar, zzcaw zzcawVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzcbaVar);
        zzarx.zzg(zza, zzcawVar);
        zzbl(4, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zzh(String str, zzcaw zzcawVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzarx.zzg(zza, zzcawVar);
        zzbl(7, zza);
    }
}
