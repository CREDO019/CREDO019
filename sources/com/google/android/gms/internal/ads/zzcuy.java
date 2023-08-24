package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcuy implements zzgur {
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
    private final zzgve zzk;
    private final zzgve zzl;
    private final zzgve zzm;
    private final zzgve zzn;

    public zzcuy(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6, zzgve zzgveVar7, zzgve zzgveVar8, zzgve zzgveVar9, zzgve zzgveVar10, zzgve zzgveVar11, zzgve zzgveVar12, zzgve zzgveVar13, zzgve zzgveVar14) {
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
        this.zzk = zzgveVar11;
        this.zzl = zzgveVar12;
        this.zzm = zzgveVar13;
        this.zzn = zzgveVar14;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* synthetic */ Object zzb() {
        Context zza = ((zzcoq) this.zza).zza();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzcux(zza, zzfyyVar, (Executor) this.zzc.zzb(), (ScheduledExecutorService) this.zzd.zzb(), ((zzczv) this.zze).zza(), ((zzczs) this.zzf).zza(), (zzfjq) this.zzg.zzb(), (zzfdw) this.zzh.zzb(), (View) this.zzi.zzb(), (zzcmn) this.zzj.zzb(), (zzapb) this.zzk.zzb(), (zzbjx) this.zzl.zzb(), new zzbjz(), (zzfjc) this.zzn.zzb(), null);
    }
}
