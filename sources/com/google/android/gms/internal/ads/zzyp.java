package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyp {
    public static final zzyp zza = new zzyp(-3, C1856C.TIME_UNSET, -1);
    private final int zzb;
    private final long zzc;
    private final long zzd;

    private zzyp(int r1, long j, long j2) {
        this.zzb = r1;
        this.zzc = j;
        this.zzd = j2;
    }

    public static zzyp zzd(long j, long j2) {
        return new zzyp(-1, j, j2);
    }

    public static zzyp zze(long j) {
        return new zzyp(0, C1856C.TIME_UNSET, j);
    }

    public static zzyp zzf(long j, long j2) {
        return new zzyp(-2, j, j2);
    }
}
