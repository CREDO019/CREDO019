package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzehm implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;

    public zzehm(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
        this.zzf = zzgveVar6;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzehl zzb() {
        return new zzehl((zzcxx) this.zza.zzb(), (Context) this.zzb.zzb(), (Executor) this.zzc.zzb(), (zzduw) this.zzd.zzb(), ((zzdcp) this.zze).zza(), (zzfru) this.zzf.zzb());
    }
}
