package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcqk implements zzexr {
    private final zzcpu zza;
    private final zzcqk zzb = this;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;
    private final zzgve zzj;
    private final zzgve zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcqk(zzcpu zzcpuVar, Context context, String str, zzcqj zzcqjVar) {
        zzgve zzgveVar;
        zzgve zzgveVar2;
        zzgve zzgveVar3;
        zzgve zzgveVar4;
        zzgve zzgveVar5;
        zzgve zzgveVar6;
        zzgve zzgveVar7;
        zzgve zzgveVar8;
        zzgve zzgveVar9;
        zzgve zzgveVar10;
        zzgve zzgveVar11;
        zzgve zzgveVar12;
        zzgve zzgveVar13;
        zzgve zzgveVar14;
        zzgve zzgveVar15;
        this.zza = zzcpuVar;
        zzgur zza = zzgus.zza(context);
        this.zzc = zza;
        zzgur zza2 = zzgus.zza(str);
        this.zzd = zza2;
        zzgveVar = zzcpuVar.zzay;
        zzgveVar2 = zzcpuVar.zzaz;
        zzfal zzfalVar = new zzfal(zza, zzgveVar, zzgveVar2);
        this.zze = zzfalVar;
        zzgveVar3 = zzcpuVar.zzay;
        zzgve zzc = zzguq.zzc(new zzeyp(zzgveVar3));
        this.zzf = zzc;
        zzgveVar4 = zzcpuVar.zzp;
        zzgveVar5 = zzcpuVar.zzQ;
        zzfdp zza3 = zzfdp.zza();
        zzgveVar6 = zzcpuVar.zzi;
        zzgve zzc2 = zzguq.zzc(new zzexj(zza, zzgveVar4, zzgveVar5, zzfalVar, zzc, zza3, zzgveVar6));
        this.zzg = zzc2;
        zzgveVar7 = zzcpuVar.zzQ;
        zzgveVar8 = zzcpuVar.zzi;
        this.zzh = zzguq.zzc(new zzexp(zzgveVar7, zza, zza2, zzc2, zzc, zzgveVar8));
        zzgveVar9 = zzcpuVar.zzay;
        zzgveVar10 = zzcpuVar.zzaz;
        zzfak zzfakVar = new zzfak(zza, zzgveVar9, zzgveVar10);
        this.zzi = zzfakVar;
        zzgveVar11 = zzcpuVar.zzp;
        zzgveVar12 = zzcpuVar.zzQ;
        zzfdp zza4 = zzfdp.zza();
        zzgveVar13 = zzcpuVar.zzi;
        zzgve zzc3 = zzguq.zzc(new zzeyr(zza, zzgveVar11, zzgveVar12, zzfakVar, zzc, zza4, zzgveVar13));
        this.zzj = zzc3;
        zzgveVar14 = zzcpuVar.zzQ;
        zzgveVar15 = zzcpuVar.zzi;
        this.zzk = zzguq.zzc(new zzeyx(zzgveVar14, zza, zza2, zzc3, zzc, zzgveVar15));
    }

    @Override // com.google.android.gms.internal.ads.zzexr
    public final zzexo zza() {
        return (zzexo) this.zzh.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzexr
    public final zzeyw zzb() {
        return (zzeyw) this.zzk.zzb();
    }
}
