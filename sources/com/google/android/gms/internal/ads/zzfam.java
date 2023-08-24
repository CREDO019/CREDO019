package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfam implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzfam(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzfah zzb() {
        zzcfq zzi;
        Context context = (Context) this.zza.zzb();
        zzfeu zzfeuVar = (zzfeu) this.zzb.zzb();
        zzffm zzffmVar = (zzffm) this.zzc.zzb();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfr)).booleanValue()) {
            zzi = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh();
        } else {
            zzi = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzi();
        }
        boolean z = false;
        if (zzi != null && zzi.zzh()) {
            z = true;
        }
        if (((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzft)).intValue() > 0) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfq)).booleanValue() || z) {
                zzffl zza = zzffmVar.zza(zzffc.Rewarded, context, zzfeuVar, new zzezl(new zzezi()));
                return new zzezn(new zzezx(new zzezw()), new zzezt(zza.zza, zzcha.zza), zza.zzb, zza.zza.zza().zzf, zzcha.zza);
            }
        }
        return new zzezw();
    }
}
