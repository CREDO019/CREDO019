package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtg implements zzchj {
    final /* synthetic */ zzbtq zza;
    final /* synthetic */ zzfir zzb;
    final /* synthetic */ zzbtr zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbtg(zzbtr zzbtrVar, zzbtq zzbtqVar, zzfir zzfirVar) {
        this.zzc = zzbtrVar;
        this.zza = zzbtqVar;
        this.zzb = zzfirVar;
    }

    @Override // com.google.android.gms.internal.ads.zzchj
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        Object obj2;
        zzbtq zzbtqVar;
        zzfje zzfjeVar;
        zzfje zzfjeVar2;
        zzbtq zzbtqVar2;
        zzbtq zzbtqVar3;
        zzbsm zzbsmVar = (zzbsm) obj;
        obj2 = this.zzc.zza;
        synchronized (obj2) {
            this.zzc.zzi = 0;
            zzbtr zzbtrVar = this.zzc;
            zzbtqVar = zzbtrVar.zzh;
            if (zzbtqVar != null) {
                zzbtq zzbtqVar4 = this.zza;
                zzbtqVar2 = zzbtrVar.zzh;
                if (zzbtqVar4 != zzbtqVar2) {
                    com.google.android.gms.ads.internal.util.zze.zza("New JS engine is loaded, marking previous one as destroyable.");
                    zzbtqVar3 = this.zzc.zzh;
                    zzbtqVar3.zzb();
                }
            }
            this.zzc.zzh = this.zza;
            if (((Boolean) zzbkh.zzd.zze()).booleanValue()) {
                zzbtr zzbtrVar2 = this.zzc;
                zzfjeVar = zzbtrVar2.zze;
                if (zzfjeVar != null) {
                    zzfjeVar2 = zzbtrVar2.zze;
                    zzfir zzfirVar = this.zzb;
                    zzfirVar.zze(true);
                    zzfjeVar2.zzb(zzfirVar.zzj());
                }
            }
        }
    }
}
