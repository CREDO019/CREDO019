package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdzr implements zzgur {
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

    public zzdzr(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6, zzgve zzgveVar7, zzgve zzgveVar8, zzgve zzgveVar9, zzgve zzgveVar10) {
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

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Executor executor = (Executor) this.zza.zzb();
        Context zza = ((zzcoq) this.zzb).zza();
        WeakReference zza2 = ((zzcor) this.zzc).zza();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzdzq(executor, zza, zza2, zzfyyVar, (zzdvj) this.zze.zzb(), (ScheduledExecutorService) this.zzf.zzb(), (zzdxx) this.zzg.zzb(), ((zzcpa) this.zzh).zza(), ((zzdjq) this.zzi).zzb(), (zzfje) this.zzj.zzb());
    }
}
