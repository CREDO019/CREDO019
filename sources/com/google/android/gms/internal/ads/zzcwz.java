package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcwz implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzcwz(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzdfn zzb() {
        return new zzdfn((ScheduledExecutorService) this.zza.zzb(), (Clock) this.zzb.zzb());
    }
}
