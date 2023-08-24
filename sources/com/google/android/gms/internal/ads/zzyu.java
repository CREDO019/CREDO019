package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzyu implements zzaai {
    private final long zza;
    private final long zzb;
    private final int zzc;
    private final long zzd;
    private final int zze;
    private final long zzf;

    public zzyu(long j, long j2, int r6, int r7, boolean z) {
        long zzb;
        this.zza = j;
        this.zzb = j2;
        this.zzc = r7 == -1 ? 1 : r7;
        this.zze = r6;
        if (j == -1) {
            this.zzd = -1L;
            zzb = C1856C.TIME_UNSET;
        } else {
            this.zzd = j - j2;
            zzb = zzb(j, j2, r6);
        }
        this.zzf = zzb;
    }

    private static long zzb(long j, long j2, int r4) {
        return (Math.max(0L, j - j2) * 8000000) / r4;
    }

    public final long zza(long j) {
        return zzb(j, this.zzb, this.zze);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        long j2 = this.zzd;
        int r7 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
        if (r7 != 0) {
            int r8 = this.zze;
            long j3 = this.zzc;
            long j4 = (((r8 * j) / 8000000) / j3) * j3;
            if (r7 != 0) {
                j4 = Math.min(j4, j2 - j3);
            }
            long max = this.zzb + Math.max(j4, 0L);
            long zza = zza(max);
            zzaaj zzaajVar = new zzaaj(zza, max);
            if (this.zzd != -1 && zza < j) {
                long j5 = max + this.zzc;
                if (j5 < this.zza) {
                    return new zzaag(zzaajVar, new zzaaj(zza(j5), j5));
                }
            }
            return new zzaag(zzaajVar, zzaajVar);
        }
        zzaaj zzaajVar2 = new zzaaj(0L, this.zzb);
        return new zzaag(zzaajVar2, zzaajVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return this.zzd != -1;
    }
}
