package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcrw implements zzfcm {
    private final zzcpu zza;
    private final zzcrw zzb = this;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;
    private final zzgve zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcrw(zzcpu zzcpuVar, Context context, String str, zzcrv zzcrvVar) {
        zzgve zzgveVar;
        zzgve zzgveVar2;
        zzgve zzgveVar3;
        zzgve zzgveVar4;
        zzgve zzgveVar5;
        zzgve zzgveVar6;
        this.zza = zzcpuVar;
        zzgur zza = zzgus.zza(context);
        this.zzc = zza;
        zzgveVar = zzcpuVar.zzay;
        zzgveVar2 = zzcpuVar.zzaz;
        zzfam zzfamVar = new zzfam(zza, zzgveVar, zzgveVar2);
        this.zzd = zzfamVar;
        zzgveVar3 = zzcpuVar.zzay;
        zzgve zzc = zzguq.zzc(new zzfbw(zzgveVar3));
        this.zze = zzc;
        zzgve zzc2 = zzguq.zzc(zzfdk.zza());
        this.zzf = zzc2;
        zzgveVar4 = zzcpuVar.zzp;
        zzgveVar5 = zzcpuVar.zzQ;
        zzgve zzc3 = zzguq.zzc(new zzfcg(zza, zzgveVar4, zzgveVar5, zzfamVar, zzc, zzfdp.zza(), zzc2));
        this.zzg = zzc3;
        this.zzh = zzguq.zzc(new zzfcq(zzc3, zzc, zzc2));
        zzgur zzc4 = zzgus.zzc(str);
        this.zzi = zzc4;
        zzgveVar6 = zzcpuVar.zzi;
        this.zzj = zzguq.zzc(new zzfck(zzc4, zzc3, zza, zzc, zzc2, zzgveVar6));
    }

    @Override // com.google.android.gms.internal.ads.zzfcm
    public final zzfcj zza() {
        return (zzfcj) this.zzj.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzfcm
    public final zzfcp zzb() {
        return (zzfcp) this.zzh.zzb();
    }
}
