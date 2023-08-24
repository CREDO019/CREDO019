package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcxe implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;
    private final zzgve zzj;

    public zzcxe(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6, zzgve zzgveVar7, zzgve zzgveVar8, zzgve zzgveVar9, zzgve zzgveVar10) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
        this.zzf = zzgveVar6;
        this.zzg = zzgveVar7;
        this.zzh = zzgveVar8;
        this.zzi = zzgveVar9;
        this.zzj = zzgveVar10;
    }

    public static zzcxd zzc(zzczb zzczbVar, Context context, zzfct zzfctVar, View view, zzcmn zzcmnVar, zzcza zzczaVar, zzdoz zzdozVar, zzdkn zzdknVar, zzgul zzgulVar, Executor executor) {
        return new zzcxd(zzczbVar, context, zzfctVar, view, zzcmnVar, zzczaVar, zzdozVar, zzdknVar, zzgulVar, executor);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzcxd zzb() {
        return new zzcxd(((zzdax) this.zza).zzb(), (Context) this.zzb.zzb(), ((zzcxk) this.zzc).zza(), ((zzcxj) this.zzd).zza(), ((zzcxv) this.zze).zza(), ((zzcxl) this.zzf).zza(), ((zzdmz) this.zzg).zza(), (zzdkn) this.zzh.zzb(), zzguq.zza(this.zzi), (Executor) this.zzj.zzb());
    }
}
