package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzejj extends zzbwr {
    final /* synthetic */ zzejk zza;
    private final zzegn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzejj(zzejk zzejkVar, zzegn zzegnVar, zzeji zzejiVar) {
        this.zza = zzejkVar;
        this.zzb = zzegnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbws
    public final void zze(String str) throws RemoteException {
        ((zzeig) this.zzb.zzc).zzi(0, str);
    }

    @Override // com.google.android.gms.internal.ads.zzbws
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        ((zzeig) this.zzb.zzc).zzh(zzeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbws
    public final void zzg(zzbvu zzbvuVar) throws RemoteException {
        zzejk.zzc(this.zza, zzbvuVar);
        ((zzeig) this.zzb.zzc).zzo();
    }
}
