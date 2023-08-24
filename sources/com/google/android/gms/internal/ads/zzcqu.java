package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcqu implements zzezf {
    private final Context zza;
    private final com.google.android.gms.ads.internal.client.zzq zzb;
    private final String zzc;
    private final zzcpu zzd;
    private final zzcqu zze = this;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;
    private final zzgve zzj;
    private final zzgve zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcqu(zzcpu zzcpuVar, Context context, String str, com.google.android.gms.ads.internal.client.zzq zzqVar, zzcqt zzcqtVar) {
        zzgve zzgveVar;
        zzgve zzgveVar2;
        zzgve zzgveVar3;
        this.zzd = zzcpuVar;
        this.zza = context;
        this.zzb = zzqVar;
        this.zzc = str;
        zzgur zza = zzgus.zza(context);
        this.zzf = zza;
        zzgur zza2 = zzgus.zza(zzqVar);
        this.zzg = zza2;
        zzgveVar = zzcpuVar.zzo;
        zzgve zzc = zzguq.zzc(new zzenp(zzgveVar));
        this.zzh = zzc;
        zzgve zzc2 = zzguq.zzc(zzenu.zza());
        this.zzi = zzc2;
        zzgve zzc3 = zzguq.zzc(zzdhv.zza());
        this.zzj = zzc3;
        zzgveVar2 = zzcpuVar.zzp;
        zzgveVar3 = zzcpuVar.zzQ;
        this.zzk = zzguq.zzc(new zzezd(zza, zzgveVar2, zza2, zzgveVar3, zzc, zzc2, zzfdp.zza(), zzc3));
    }

    @Override // com.google.android.gms.internal.ads.zzezf
    public final zzemv zza() {
        zzcon zzconVar;
        Context context = this.zza;
        com.google.android.gms.ads.internal.client.zzq zzqVar = this.zzb;
        String str = this.zzc;
        zzezc zzezcVar = (zzezc) this.zzk.zzb();
        zzeno zzenoVar = (zzeno) this.zzh.zzb();
        zzconVar = this.zzd.zza;
        zzcgt zzd = zzconVar.zzd();
        zzguz.zzb(zzd);
        return new zzemv(context, zzqVar, str, zzezcVar, zzenoVar, zzd);
    }
}
