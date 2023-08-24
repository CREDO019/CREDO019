package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbth implements zzchh {
    final /* synthetic */ zzbtq zza;
    final /* synthetic */ zzfir zzb;
    final /* synthetic */ zzbtr zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbth(zzbtr zzbtrVar, zzbtq zzbtqVar, zzfir zzfirVar) {
        this.zzc = zzbtrVar;
        this.zza = zzbtqVar;
        this.zzb = zzfirVar;
    }

    @Override // com.google.android.gms.internal.ads.zzchh
    public final void zza() {
        Object obj;
        zzfje zzfjeVar;
        zzfje zzfjeVar2;
        obj = this.zzc.zza;
        synchronized (obj) {
            this.zzc.zzi = 1;
            com.google.android.gms.ads.internal.util.zze.zza("Failed loading new engine. Marking new engine destroyable.");
            this.zza.zzb();
            if (((Boolean) zzbkh.zzd.zze()).booleanValue()) {
                zzbtr zzbtrVar = this.zzc;
                zzfjeVar = zzbtrVar.zze;
                if (zzfjeVar != null) {
                    zzfjeVar2 = zzbtrVar.zze;
                    zzfir zzfirVar = this.zzb;
                    zzfirVar.zze(false);
                    zzfjeVar2.zzb(zzfirVar.zzj());
                }
            }
        }
    }
}
