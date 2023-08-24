package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfhr implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzfhr(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzfhp(zzfyyVar, (ScheduledExecutorService) this.zzb.zzb(), ((zzfhs) this.zzc).zzb());
    }
}