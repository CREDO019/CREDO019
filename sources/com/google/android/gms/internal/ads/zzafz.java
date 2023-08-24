package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafz implements zzagf {
    private final zzzs zza;
    private final zzzr zzb;
    private long zzc = -1;
    private long zzd = -1;

    public zzafz(zzzs zzzsVar, zzzr zzzrVar) {
        this.zza = zzzsVar;
        this.zzb = zzzrVar;
    }

    public final void zza(long j) {
        this.zzc = j;
    }

    @Override // com.google.android.gms.internal.ads.zzagf
    public final long zzd(zzzg zzzgVar) {
        long j = this.zzd;
        if (j >= 0) {
            this.zzd = -1L;
            return -(j + 2);
        }
        return -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzagf
    public final zzaai zze() {
        zzdd.zzf(this.zzc != -1);
        return new zzzq(this.zza, this.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzagf
    public final void zzg(long j) {
        long[] jArr = this.zzb.zza;
        this.zzd = jArr[zzel.zzd(jArr, j, true, true)];
    }
}
