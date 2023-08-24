package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgt {
    private final long zza;
    private final long zzb;
    private long zzc = C1856C.TIME_UNSET;
    private long zzd = C1856C.TIME_UNSET;
    private long zzf = C1856C.TIME_UNSET;
    private long zzg = C1856C.TIME_UNSET;
    private float zzj = 0.97f;
    private float zzi = 1.03f;
    private float zzk = 1.0f;
    private long zzl = C1856C.TIME_UNSET;
    private long zze = C1856C.TIME_UNSET;
    private long zzh = C1856C.TIME_UNSET;
    private long zzm = C1856C.TIME_UNSET;
    private long zzn = C1856C.TIME_UNSET;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgt(float f, float f2, long j, float f3, long j2, long j3, float f4, zzgs zzgsVar) {
        this.zza = j2;
        this.zzb = j3;
    }

    private static long zzf(long j, long j2, float f) {
        return (((float) j) * 0.999f) + (((float) j2) * 9.999871E-4f);
    }

    private final void zzg() {
        long j = this.zzc;
        if (j != C1856C.TIME_UNSET) {
            long j2 = this.zzd;
            if (j2 != C1856C.TIME_UNSET) {
                j = j2;
            }
            long j3 = this.zzf;
            if (j3 != C1856C.TIME_UNSET && j < j3) {
                j = j3;
            }
            long j4 = this.zzg;
            if (j4 != C1856C.TIME_UNSET && j > j4) {
                j = j4;
            }
        } else {
            j = -9223372036854775807L;
        }
        if (this.zze == j) {
            return;
        }
        this.zze = j;
        this.zzh = j;
        this.zzm = C1856C.TIME_UNSET;
        this.zzn = C1856C.TIME_UNSET;
        this.zzl = C1856C.TIME_UNSET;
    }

    public final long zzb() {
        return this.zzh;
    }

    public final void zzc() {
        long j = this.zzh;
        if (j == C1856C.TIME_UNSET) {
            return;
        }
        long j2 = j + this.zzb;
        this.zzh = j2;
        long j3 = this.zzg;
        if (j3 != C1856C.TIME_UNSET && j2 > j3) {
            this.zzh = j3;
        }
        this.zzl = C1856C.TIME_UNSET;
    }

    public final void zzd(zzaw zzawVar) {
        long j = zzawVar.zzc;
        this.zzc = zzel.zzv(C1856C.TIME_UNSET);
        long j2 = zzawVar.zzd;
        this.zzf = zzel.zzv(C1856C.TIME_UNSET);
        long j3 = zzawVar.zze;
        this.zzg = zzel.zzv(C1856C.TIME_UNSET);
        float f = zzawVar.zzf;
        this.zzj = 0.97f;
        float f2 = zzawVar.zzg;
        this.zzi = 1.03f;
        zzg();
    }

    public final void zze(long j) {
        this.zzd = j;
        zzg();
    }

    public final float zza(long j, long j2) {
        long zzr;
        if (this.zzc != C1856C.TIME_UNSET) {
            long j3 = j - j2;
            long j4 = this.zzm;
            if (j4 == C1856C.TIME_UNSET) {
                this.zzm = j3;
                this.zzn = 0L;
            } else {
                long max = Math.max(j3, zzf(j4, j3, 0.999f));
                this.zzm = max;
                this.zzn = zzf(this.zzn, Math.abs(j3 - max), 0.999f);
            }
            if (this.zzl == C1856C.TIME_UNSET || SystemClock.elapsedRealtime() - this.zzl >= 1000) {
                this.zzl = SystemClock.elapsedRealtime();
                long j5 = this.zzm + (this.zzn * 3);
                if (this.zzh > j5) {
                    float zzv = (float) zzel.zzv(1000L);
                    long[] jArr = {j5, this.zze, this.zzh - (((this.zzk - 1.0f) * zzv) + ((this.zzi - 1.0f) * zzv))};
                    zzr = jArr[0];
                    for (int r12 = 1; r12 < 3; r12++) {
                        long j6 = jArr[r12];
                        if (j6 > zzr) {
                            zzr = j6;
                        }
                    }
                    this.zzh = zzr;
                } else {
                    zzr = zzel.zzr(j - (Math.max(0.0f, this.zzk - 1.0f) / 1.0E-7f), this.zzh, j5);
                    this.zzh = zzr;
                    long j7 = this.zzg;
                    if (j7 != C1856C.TIME_UNSET && zzr > j7) {
                        this.zzh = j7;
                        zzr = j7;
                    }
                }
                long j8 = j - zzr;
                if (Math.abs(j8) < this.zza) {
                    this.zzk = 1.0f;
                    return 1.0f;
                }
                float zza = zzel.zza((((float) j8) * 1.0E-7f) + 1.0f, this.zzj, this.zzi);
                this.zzk = zza;
                return zza;
            }
            return this.zzk;
        }
        return 1.0f;
    }
}
