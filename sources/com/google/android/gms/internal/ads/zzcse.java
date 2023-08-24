package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcse implements com.google.android.gms.ads.nonagon.signalgeneration.zzg {
    private final zzcpu zza;
    private zzdck zzb;
    private com.google.android.gms.ads.nonagon.signalgeneration.zzae zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcse(zzcpu zzcpuVar, zzcsd zzcsdVar) {
        this.zza = zzcpuVar;
    }

    @Override // com.google.android.gms.ads.nonagon.signalgeneration.zzg
    public final /* synthetic */ com.google.android.gms.ads.nonagon.signalgeneration.zzg zza(zzdck zzdckVar) {
        this.zzb = zzdckVar;
        return this;
    }

    @Override // com.google.android.gms.ads.nonagon.signalgeneration.zzg
    public final /* synthetic */ com.google.android.gms.ads.nonagon.signalgeneration.zzg zzb(com.google.android.gms.ads.nonagon.signalgeneration.zzae zzaeVar) {
        this.zzc = zzaeVar;
        return this;
    }

    @Override // com.google.android.gms.ads.nonagon.signalgeneration.zzg
    public final com.google.android.gms.ads.nonagon.signalgeneration.zzh zzc() {
        zzguz.zzc(this.zzb, zzdck.class);
        zzguz.zzc(this.zzc, com.google.android.gms.ads.nonagon.signalgeneration.zzae.class);
        return new zzcsg(this.zza, this.zzc, new zzdah(), new zzdyb(), this.zzb, null, null, null);
    }
}
