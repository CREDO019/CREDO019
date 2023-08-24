package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdrp implements zzgur {
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
    private final zzgve zzo;
    private final zzgve zzp;

    public zzdrp(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5, zzgve zzgveVar6, zzgve zzgveVar7, zzgve zzgveVar8, zzgve zzgveVar9, zzgve zzgveVar10, zzgve zzgveVar11, zzgve zzgveVar12, zzgve zzgveVar13, zzgve zzgveVar14, zzgve zzgveVar15, zzgve zzgveVar16) {
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
        this.zzo = zzgveVar15;
        this.zzp = zzgveVar16;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzdro zzb() {
        Context context = (Context) this.zza.zzb();
        zzdqx zzdqxVar = (zzdqx) this.zzb.zzb();
        zzapb zzapbVar = (zzapb) this.zzc.zzb();
        zzcgt zza = ((zzcpa) this.zzd).zza();
        com.google.android.gms.ads.internal.zza zza2 = com.google.android.gms.ads.internal.zza.zza();
        zzbel zzbelVar = (zzbel) this.zzf.zzb();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzdro(context, zzdqxVar, zzapbVar, zza, zza2, zzbelVar, zzfyyVar, ((zzdcp) this.zzh).zza(), (zzdsg) this.zzi.zzb(), (zzduw) this.zzj.zzb(), (ScheduledExecutorService) this.zzk.zzb(), (zzdxo) this.zzl.zzb(), (zzfhz) this.zzm.zzb(), (zzfju) this.zzn.zzb(), (zzefz) this.zzo.zzb(), (zzdtr) this.zzp.zzb());
    }
}
