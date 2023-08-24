package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzej {
    private long zza;
    private long zzb;
    private long zzc;
    private final ThreadLocal zzd = new ThreadLocal();

    public zzej(long j) {
        zzf(0L);
    }

    public final synchronized long zza(long j) {
        if (this.zzb == C1856C.TIME_UNSET) {
            long j2 = this.zza;
            if (j2 == TimestampAdjuster.MODE_SHARED) {
                Long l = (Long) this.zzd.get();
                Objects.requireNonNull(l);
                j2 = l.longValue();
            }
            this.zzb = j2 - j;
            notifyAll();
        }
        this.zzc = j;
        return j + this.zzb;
    }

    public final synchronized long zzb(long j) {
        if (j == C1856C.TIME_UNSET) {
            return C1856C.TIME_UNSET;
        }
        long j2 = this.zzc;
        if (j2 != C1856C.TIME_UNSET) {
            long j3 = (j2 * 90000) / 1000000;
            long j4 = (4294967296L + j3) / 8589934592L;
            long j5 = (((-1) + j4) * 8589934592L) + j;
            j += j4 * 8589934592L;
            if (Math.abs(j5 - j3) < Math.abs(j - j3)) {
                j = j5;
            }
        }
        return zza((j * 1000000) / 90000);
    }

    public final synchronized long zzc() {
        long j = this.zza;
        return (j == Long.MAX_VALUE || j == TimestampAdjuster.MODE_SHARED) ? C1856C.TIME_UNSET : j;
    }

    public final synchronized long zzd() {
        long j;
        j = this.zzc;
        return j != C1856C.TIME_UNSET ? j + this.zzb : zzc();
    }

    public final synchronized long zze() {
        return this.zzb;
    }

    public final synchronized void zzf(long j) {
        this.zza = j;
        this.zzb = j == Long.MAX_VALUE ? 0L : -9223372036854775807L;
        this.zzc = C1856C.TIME_UNSET;
    }
}
