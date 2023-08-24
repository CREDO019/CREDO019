package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqx implements zzeun {
    private final AtomicReference zza = new AtomicReference();
    private final Clock zzb;
    private final zzeun zzc;
    private final long zzd;

    public zzeqx(zzeun zzeunVar, long j, Clock clock) {
        this.zzb = clock;
        this.zzc = zzeunVar;
        this.zzd = j;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 16;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        zzeqw zzeqwVar = (zzeqw) this.zza.get();
        if (zzeqwVar == null || zzeqwVar.zza()) {
            zzeqwVar = new zzeqw(this.zzc.zzb(), this.zzd, this.zzb);
            this.zza.set(zzeqwVar);
        }
        return zzeqwVar.zza;
    }
}
