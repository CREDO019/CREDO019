package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevs implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;

    public zzevs(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
    }

    public static zzevq zza(String str, zzbdo zzbdoVar, zzcfw zzcfwVar, ScheduledExecutorService scheduledExecutorService, zzfyy zzfyyVar) {
        return new zzevq(str, zzbdoVar, zzcfwVar, scheduledExecutorService, zzfyyVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzevq(((zzews) this.zza).zza(), new zzbdo(), (zzcfw) this.zzc.zzb(), (ScheduledExecutorService) this.zzd.zzb(), zzfyyVar, null);
    }
}
