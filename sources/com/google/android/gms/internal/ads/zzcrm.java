package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcrm implements zzdme {
    private final zzcpu zza;
    private zzfan zzb;
    private zzezo zzc;
    private zzdik zzd;
    private zzdck zze;
    private zzely zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcrm(zzcpu zzcpuVar, zzcrl zzcrlVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ zzdcg zza(zzezo zzezoVar) {
        this.zzc = zzezoVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ zzdcg zzb(zzfan zzfanVar) {
        this.zzb = zzfanVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdme
    public final /* synthetic */ zzdme zzc(zzely zzelyVar) {
        this.zzf = zzelyVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdme
    public final /* synthetic */ zzdme zzd(zzdik zzdikVar) {
        this.zzd = zzdikVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdme
    public final /* synthetic */ zzdme zze(zzdck zzdckVar) {
        this.zze = zzdckVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    /* renamed from: zzf */
    public final zzdmf zzh() {
        zzguz.zzc(this.zzd, zzdik.class);
        zzguz.zzc(this.zze, zzdck.class);
        zzguz.zzc(this.zzf, zzely.class);
        return new zzcro(this.zza, new zzdah(), new zzfeq(), new zzdbs(), new zzdyb(), this.zzd, this.zze, this.zzf, null, this.zzb, this.zzc, null);
    }
}
