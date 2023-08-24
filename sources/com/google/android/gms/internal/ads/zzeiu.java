package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeiu extends zzbwo {
    private final zzegn zza;

    @Override // com.google.android.gms.internal.ads.zzbwp
    public final void zze(String str) throws RemoteException {
        ((zzeig) this.zza.zzc).zzi(0, str);
    }

    @Override // com.google.android.gms.internal.ads.zzbwp
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        ((zzeig) this.zza.zzc).zzh(zzeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbwp
    public final void zzg() throws RemoteException {
        ((zzeig) this.zza.zzc).zzo();
    }
}
