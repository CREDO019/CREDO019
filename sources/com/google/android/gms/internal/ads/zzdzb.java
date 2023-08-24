package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdzb extends zzccp {
    final /* synthetic */ zzdzd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdzb(zzdzd zzdzdVar) {
        this.zza = zzdzdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzccq
    public final void zze(int r5) throws RemoteException {
        zzdys zzdysVar;
        long j;
        zzdzd zzdzdVar = this.zza;
        zzdysVar = zzdzdVar.zzc;
        j = zzdzdVar.zza;
        zzdysVar.zzm(j, r5);
    }

    @Override // com.google.android.gms.internal.ads.zzccq
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        zzdys zzdysVar;
        long j;
        zzdzd zzdzdVar = this.zza;
        zzdysVar = zzdzdVar.zzc;
        j = zzdzdVar.zza;
        zzdysVar.zzm(j, zzeVar.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzccq
    public final void zzg() throws RemoteException {
        zzdys zzdysVar;
        long j;
        zzdzd zzdzdVar = this.zza;
        zzdysVar = zzdzdVar.zzc;
        j = zzdzdVar.zza;
        zzdysVar.zzp(j);
    }
}
