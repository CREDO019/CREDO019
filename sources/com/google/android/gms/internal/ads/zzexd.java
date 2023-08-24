package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzexd implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;

    public zzexd(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
        this.zzf = zzgveVar6;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzcfw zzcfwVar = (zzcfw) this.zza.zzb();
        boolean booleanValue = ((zzewu) this.zzb).zzb().booleanValue();
        zzcfl zzcflVar = new zzcfl();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzexb(zzcfwVar, booleanValue, zzcflVar, zzfyyVar, ((zzews) this.zze).zza(), (ScheduledExecutorService) this.zzf.zzb(), null);
    }
}
