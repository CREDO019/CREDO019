package com.google.android.gms.internal.ads;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaer implements zzaep {
    private final long zza;
    private final int zzb;
    private final long zzc;
    private final long zzd;
    private final long zze;
    private final long[] zzf;

    private zzaer(long j, int r3, long j2, long j3, long[] jArr) {
        this.zza = j;
        this.zzb = r3;
        this.zzc = j2;
        this.zzf = jArr;
        this.zzd = j3;
        this.zze = j3 != -1 ? j + j3 : -1L;
    }

    public static zzaer zza(long j, long j2, zzzy zzzyVar, zzed zzedVar) {
        int zzn;
        int r3 = zzzyVar.zzg;
        int r4 = zzzyVar.zzd;
        int zze = zzedVar.zze();
        if ((zze & 1) != 1 || (zzn = zzedVar.zzn()) == 0) {
            return null;
        }
        long zzw = zzel.zzw(zzn, r3 * 1000000, r4);
        if ((zze & 6) != 6) {
            return new zzaer(j2, zzzyVar.zzc, zzw, -1L, null);
        }
        long zzs = zzedVar.zzs();
        long[] jArr = new long[100];
        for (int r5 = 0; r5 < 100; r5++) {
            jArr[r5] = zzedVar.zzk();
        }
        if (j != -1) {
            long j3 = j2 + zzs;
            if (j != j3) {
                Log.w("XingSeeker", "XING data size mismatch: " + j + ", " + j3);
            }
        }
        return new zzaer(j2, zzzyVar.zzc, zzw, zzs, jArr);
    }

    private final long zzd(int r5) {
        return (this.zzc * r5) / 100;
    }

    @Override // com.google.android.gms.internal.ads.zzaep
    public final long zzb() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzaep
    public final long zzc(long j) {
        long j2 = j - this.zza;
        if (!zzh() || j2 <= this.zzb) {
            return 0L;
        }
        long[] jArr = (long[]) zzdd.zzb(this.zzf);
        double d = (j2 * 256.0d) / this.zzd;
        int zzd = zzel.zzd(jArr, (long) d, true, true);
        long zzd2 = zzd(zzd);
        long j3 = jArr[zzd];
        int r6 = zzd + 1;
        long zzd3 = zzd(r6);
        long j4 = zzd == 99 ? 256L : jArr[r6];
        return zzd2 + Math.round((j3 == j4 ? 0.0d : (d - j3) / (j4 - j3)) * (zzd3 - zzd2));
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        long[] jArr;
        if (!zzh()) {
            zzaaj zzaajVar = new zzaaj(0L, this.zza + this.zzb);
            return new zzaag(zzaajVar, zzaajVar);
        }
        long zzr = zzel.zzr(j, 0L, this.zzc);
        double d = (zzr * 100.0d) / this.zzc;
        double d2 = 0.0d;
        if (d > 0.0d) {
            if (d >= 100.0d) {
                d2 = 256.0d;
            } else {
                int r2 = (int) d;
                double d3 = ((long[]) zzdd.zzb(this.zzf))[r2];
                d2 = d3 + ((d - r2) * ((r2 == 99 ? 256.0d : jArr[r2 + 1]) - d3));
            }
        }
        zzaaj zzaajVar2 = new zzaaj(zzr, this.zza + zzel.zzr(Math.round((d2 / 256.0d) * this.zzd), this.zzb, this.zzd - 1));
        return new zzaag(zzaajVar2, zzaajVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return this.zzf != null;
    }
}
