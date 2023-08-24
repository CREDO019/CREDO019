package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzasd {
    public static final int zza;
    public static final UUID zzb;
    public static final UUID zzc;
    public static final UUID zzd;
    public static final UUID zze;

    static {
        zza = zzban.zza < 23 ? 1020 : 6396;
        zzb = new UUID(0L, 0L);
        zzc = new UUID(1186680826959645954L, -5988876978535335093L);
        zzd = new UUID(-1301668207276963122L, -6645017420763422227L);
        zze = new UUID(-7348484286925749626L, -6083546864340672619L);
    }

    public static long zza(long j) {
        return j == C1856C.TIME_UNSET ? C1856C.TIME_UNSET : j * 1000;
    }

    public static long zzb(long j) {
        return j == C1856C.TIME_UNSET ? C1856C.TIME_UNSET : j / 1000;
    }
}
