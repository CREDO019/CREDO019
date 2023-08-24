package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcqe implements zzcwc {
    private final zzcpu zza;
    private zzfan zzb;
    private zzezo zzc;
    private zzdik zzd;
    private zzdck zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcqe(zzcpu zzcpuVar, zzcqd zzcqdVar) {
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

    @Override // com.google.android.gms.internal.ads.zzcwc
    public final /* synthetic */ zzcwc zzc(zzdik zzdikVar) {
        this.zzd = zzdikVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcwc
    public final /* synthetic */ zzcwc zzd(zzdck zzdckVar) {
        this.zze = zzdckVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* bridge */ /* synthetic */ Object zzh() {
        zzguz.zzc(this.zzd, zzdik.class);
        zzguz.zzc(this.zze, zzdck.class);
        return new zzcqg(this.zza, new zzdah(), new zzfeq(), new zzdbs(), new zzdyb(), this.zzd, this.zze, null, this.zzb, this.zzc, null);
    }
}
