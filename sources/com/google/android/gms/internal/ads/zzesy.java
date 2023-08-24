package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzesy implements zzeun {
    private final zzeun zza;
    private final long zzb;
    private final ScheduledExecutorService zzc;

    public zzesy(zzeun zzeunVar, long j, ScheduledExecutorService scheduledExecutorService) {
        this.zza = zzeunVar;
        this.zzb = j;
        this.zzc = scheduledExecutorService;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return this.zza.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        zzfyx zzb = this.zza.zzb();
        long j = this.zzb;
        if (j > 0) {
            zzb = zzfyo.zzo(zzb, j, TimeUnit.MILLISECONDS, this.zzc);
        }
        return zzfyo.zzg(zzb, Throwable.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzesx
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                Throwable th = (Throwable) obj;
                return zzfyo.zzi(null);
            }
        }, zzcha.zzf);
    }
}
