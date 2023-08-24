package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzekw implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;

    public zzekw(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6, zzgve zzgveVar7) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
        this.zzf = zzgveVar6;
        this.zzg = zzgveVar7;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return new zzekv((Context) this.zza.zzb(), ((zzcpa) this.zzb).zza(), ((zzdcp) this.zzc).zza(), (Executor) this.zzd.zzb(), (zzduf) this.zze.zzb(), (zzduw) this.zzf.zzb(), new zzbpt());
    }
}
