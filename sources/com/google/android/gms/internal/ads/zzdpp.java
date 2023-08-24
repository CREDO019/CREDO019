package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdpp implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;

    public zzdpp(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6, zzgve zzgveVar7, zzgve zzgveVar8, zzgve zzgveVar9) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
        this.zzf = zzgveVar6;
        this.zzg = zzgveVar7;
        this.zzh = zzgveVar8;
        this.zzi = zzgveVar9;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzdpo zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzdpo(((zzcoo) this.zza).zzb(), ((zzdcp) this.zzb).zza(), (zzdot) this.zzc.zzb(), ((zzdph) this.zzd).zza(), (zzdpz) this.zze.zzb(), (zzdqh) this.zzf.zzb(), (Executor) this.zzg.zzb(), zzfyyVar, (zzdol) this.zzi.zzb());
    }
}
