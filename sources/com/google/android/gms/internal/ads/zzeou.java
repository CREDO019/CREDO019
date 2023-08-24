package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeou implements zzeun {
    private final Clock zza;
    private final zzfdn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeou(Clock clock, zzfdn zzfdnVar) {
        this.zza = clock;
        this.zzb = zzfdnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 4;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzi(new zzeov(this.zzb, this.zza.currentTimeMillis()));
    }
}
