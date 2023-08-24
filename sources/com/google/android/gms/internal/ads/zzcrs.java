package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcrs implements zzfay {
    private final zzcpu zza;
    private final zzcrs zzb = this;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcrs(zzcpu zzcpuVar, Context context, String str, com.google.android.gms.ads.internal.client.zzq zzqVar, zzcrr zzcrrVar) {
        zzgve zzgveVar;
        zzgve zzgveVar2;
        zzgve zzgveVar3;
        zzgve zzgveVar4;
        zzgve zzgveVar5;
        this.zza = zzcpuVar;
        zzgur zza = zzgus.zza(context);
        this.zzc = zza;
        zzgur zza2 = zzgus.zza(zzqVar);
        this.zzd = zza2;
        zzgur zza3 = zzgus.zza(str);
        this.zze = zza3;
        zzgveVar = zzcpuVar.zzo;
        zzgve zzc = zzguq.zzc(new zzenp(zzgveVar));
        this.zzf = zzc;
        zzgveVar2 = zzcpuVar.zzay;
        zzgve zzc2 = zzguq.zzc(new zzfbw(zzgveVar2));
        this.zzg = zzc2;
        zzgveVar3 = zzcpuVar.zzp;
        zzgveVar4 = zzcpuVar.zzQ;
        zzgve zzc3 = zzguq.zzc(new zzfaw(zza, zzgveVar3, zzgveVar4, zzc, zzc2, zzfdp.zza()));
        this.zzh = zzc3;
        zzgveVar5 = zzcpuVar.zzi;
        this.zzi = zzguq.zzc(new zzenx(zza, zza2, zza3, zzc3, zzc, zzc2, zzgveVar5));
    }

    @Override // com.google.android.gms.internal.ads.zzfay
    public final zzenw zza() {
        return (zzenw) this.zzi.zzb();
    }
}
