package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezx implements zzfah {
    private final zzfah zza;
    private zzdch zzb;

    public zzezx(zzfah zzfahVar) {
        this.zza = zzfahVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    /* renamed from: zza */
    public final synchronized zzdch zzd() {
        return this.zzb;
    }

    public final synchronized zzfyx zzb(zzfai zzfaiVar, zzfag zzfagVar, zzdch zzdchVar) {
        this.zzb = zzdchVar;
        if (zzfaiVar.zza != null) {
            zzdaf zzb = this.zzb.zzb();
            return zzb.zzh(zzb.zzj(zzfyo.zzi(zzfaiVar.zza)));
        }
        return ((zzezw) this.zza).zzb(zzfaiVar, zzfagVar, zzdchVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* bridge */ /* synthetic */ zzfyx zzc(zzfai zzfaiVar, zzfag zzfagVar, Object obj) {
        return zzb(zzfaiVar, zzfagVar, null);
    }
}
