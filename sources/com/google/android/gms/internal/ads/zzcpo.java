package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcpo implements zzdna {
    private final zzcpu zza;
    private zzfan zzb;
    private zzezo zzc;
    private zzdik zzd;
    private zzdck zze;
    private zzdmw zzf;
    private zzcwx zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcpo(zzcpu zzcpuVar, zzcpn zzcpnVar) {
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

    @Override // com.google.android.gms.internal.ads.zzdna
    public final /* synthetic */ zzdna zzc(zzcwx zzcwxVar) {
        this.zzg = zzcwxVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdna
    public final /* synthetic */ zzdna zzd(zzdmw zzdmwVar) {
        this.zzf = zzdmwVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdna
    public final /* synthetic */ zzdna zze(zzdik zzdikVar) {
        this.zzd = zzdikVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdna
    public final /* synthetic */ zzdna zzf(zzdck zzdckVar) {
        this.zze = zzdckVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    /* renamed from: zzg */
    public final zzdnb zzh() {
        zzguz.zzc(this.zzd, zzdik.class);
        zzguz.zzc(this.zze, zzdck.class);
        zzguz.zzc(this.zzf, zzdmw.class);
        zzguz.zzc(this.zzg, zzcwx.class);
        return new zzcpq(this.zza, this.zzg, this.zzf, new zzdah(), new zzfeq(), new zzdbs(), new zzdyb(), this.zzd, this.zze, null, this.zzb, this.zzc, null);
    }
}
