package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaek implements zzaep {
    private final long[] zza;
    private final long[] zzb;
    private final long zzc;

    private zzaek(long[] jArr, long[] jArr2, long j) {
        this.zza = jArr;
        this.zzb = jArr2;
        this.zzc = j == C1856C.TIME_UNSET ? zzel.zzv(jArr2[jArr2.length - 1]) : j;
    }

    public static zzaek zza(long j, zzade zzadeVar, long j2) {
        int length = zzadeVar.zzd.length;
        int r1 = length + 1;
        long[] jArr = new long[r1];
        long[] jArr2 = new long[r1];
        jArr[0] = j;
        long j3 = 0;
        jArr2[0] = 0;
        for (int r3 = 1; r3 <= length; r3++) {
            int r6 = r3 - 1;
            j += zzadeVar.zzb + zzadeVar.zzd[r6];
            j3 += zzadeVar.zzc + zzadeVar.zze[r6];
            jArr[r3] = j;
            jArr2[r3] = j3;
        }
        return new zzaek(jArr, jArr2, j2);
    }

    private static Pair zzd(long j, long[] jArr, long[] jArr2) {
        int zzd = zzel.zzd(jArr, j, true, true);
        long j2 = jArr[zzd];
        long j3 = jArr2[zzd];
        int r1 = zzd + 1;
        if (r1 == jArr.length) {
            return Pair.create(Long.valueOf(j2), Long.valueOf(j3));
        }
        long j4 = jArr[r1];
        return Pair.create(Long.valueOf(j), Long.valueOf(((long) ((j4 == j2 ? 0.0d : (j - j2) / (j4 - j2)) * (jArr2[r1] - j3))) + j3));
    }

    @Override // com.google.android.gms.internal.ads.zzaep
    public final long zzb() {
        return -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzaep
    public final long zzc(long j) {
        return zzel.zzv(((Long) zzd(j, this.zza, this.zzb).second).longValue());
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        Pair zzd = zzd(zzel.zzz(zzel.zzr(j, 0L, this.zzc)), this.zzb, this.zza);
        long longValue = ((Long) zzd.first).longValue();
        zzaaj zzaajVar = new zzaaj(zzel.zzv(longValue), ((Long) zzd.second).longValue());
        return new zzaag(zzaajVar, zzaajVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
