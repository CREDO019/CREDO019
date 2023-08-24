package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeqw {
    public final zzfyx zza;
    private final long zzb;
    private final Clock zzc;

    public zzeqw(zzfyx zzfyxVar, long j, Clock clock) {
        this.zza = zzfyxVar;
        this.zzc = clock;
        this.zzb = clock.elapsedRealtime() + j;
    }

    public final boolean zza() {
        return this.zzb < this.zzc.elapsedRealtime();
    }
}
