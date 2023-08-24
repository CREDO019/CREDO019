package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdjh implements zzgur {
    private final zzdik zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;

    public zzdjh(zzdik zzdikVar, zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4) {
        this.zza = zzdikVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
        this.zzd = zzgveVar3;
        this.zze = zzgveVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzejx zzb = this.zza.zzb((Clock) this.zzb.zzb(), ((zzejz) this.zzc).zzb(), (zzegp) this.zzd.zzb(), (zzfju) this.zze.zzb());
        zzguz.zzb(zzb);
        return zzb;
    }
}
