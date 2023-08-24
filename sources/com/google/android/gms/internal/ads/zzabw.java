package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabw extends zzzt {
    private final long zza;

    public zzabw(zzzg zzzgVar, long j) {
        super(zzzgVar);
        zzdd.zzd(zzzgVar.zzf() >= j);
        this.zza = j;
    }

    @Override // com.google.android.gms.internal.ads.zzzt, com.google.android.gms.internal.ads.zzzg
    public final long zzd() {
        return super.zzd() - this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzzt, com.google.android.gms.internal.ads.zzzg
    public final long zze() {
        return super.zze() - this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzzt, com.google.android.gms.internal.ads.zzzg
    public final long zzf() {
        return super.zzf() - this.zza;
    }
}
