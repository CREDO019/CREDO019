package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaix implements zzaai {
    private final zzaiu zza;
    private final int zzb;
    private final long zzc;
    private final long zzd;
    private final long zze;

    public zzaix(zzaiu zzaiuVar, int r2, long j, long j2) {
        this.zza = zzaiuVar;
        this.zzb = r2;
        this.zzc = j;
        long j3 = (j2 - j) / zzaiuVar.zzd;
        this.zzd = j3;
        this.zze = zza(j3);
    }

    private final long zza(long j) {
        return zzel.zzw(j * this.zzb, 1000000L, this.zza.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        long zzr = zzel.zzr((this.zza.zzc * j) / (this.zzb * 1000000), 0L, this.zzd - 1);
        long j2 = this.zzc;
        int r6 = this.zza.zzd;
        long zza = zza(zzr);
        zzaaj zzaajVar = new zzaaj(zza, j2 + (r6 * zzr));
        if (zza >= j || zzr == this.zzd - 1) {
            return new zzaag(zzaajVar, zzaajVar);
        }
        long j3 = zzr + 1;
        return new zzaag(zzaajVar, new zzaaj(zza(j3), this.zzc + (j3 * this.zza.zzd)));
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
