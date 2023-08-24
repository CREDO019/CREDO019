package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzq implements zzaai {
    private final zzzs zza;
    private final long zzb;

    public zzzq(zzzs zzzsVar, long j) {
        this.zza = zzzsVar;
        this.zzb = j;
    }

    private final zzaaj zza(long j, long j2) {
        return new zzaaj((j * 1000000) / this.zza.zze, this.zzb + j2);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zza.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        zzdd.zzb(this.zza.zzk);
        zzzs zzzsVar = this.zza;
        zzzr zzzrVar = zzzsVar.zzk;
        long[] jArr = zzzrVar.zza;
        long[] jArr2 = zzzrVar.zzb;
        int zzd = zzel.zzd(jArr, zzzsVar.zzb(j), true, false);
        zzaaj zza = zza(zzd == -1 ? 0L : jArr[zzd], zzd != -1 ? jArr2[zzd] : 0L);
        if (zza.zzb == j || zzd == jArr.length - 1) {
            return new zzaag(zza, zza);
        }
        int r3 = zzd + 1;
        return new zzaag(zza, zza(jArr[r3], jArr2[r3]));
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
