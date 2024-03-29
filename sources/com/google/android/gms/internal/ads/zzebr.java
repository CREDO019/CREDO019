package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzebr implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;

    public zzebr(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzebq zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzebq((ScheduledExecutorService) this.zza.zzb(), zzfyyVar, ((zzeci) this.zzc).zzb(), zzguq.zza(this.zzd));
    }
}
