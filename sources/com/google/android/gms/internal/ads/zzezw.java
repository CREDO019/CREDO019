package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezw implements zzfah {
    private zzdch zza;

    @Override // com.google.android.gms.internal.ads.zzfah
    /* renamed from: zza */
    public final synchronized zzdch zzd() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* bridge */ /* synthetic */ zzfyx zzc(zzfai zzfaiVar, zzfag zzfagVar, Object obj) {
        return zzb(zzfaiVar, zzfagVar, null);
    }

    public final synchronized zzfyx zzb(zzfai zzfaiVar, zzfag zzfagVar, zzdch zzdchVar) {
        zzdaf zzb;
        if (zzdchVar == null) {
            this.zza = (zzdch) zzfagVar.zza(zzfaiVar.zzb).zzh();
        } else {
            this.zza = zzdchVar;
        }
        zzb = this.zza.zzb();
        return zzb.zzh(zzb.zzi());
    }
}
