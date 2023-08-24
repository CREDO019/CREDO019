package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcpj implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzcpj(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzcbl zzb() {
        Context zza = ((zzcoq) this.zza).zza();
        zzfje zzfjeVar = (zzfje) this.zzb.zzb();
        com.google.android.gms.ads.internal.zzt.zzf().zzb(zza, zzcgt.zza(), zzfjeVar).zza("google.afma.request.getAdDictionary", zzbuc.zza, zzbuc.zza);
        zzbuf zzb = com.google.android.gms.ads.internal.zzt.zzf().zzb(zza, zzcgt.zza(), zzfjeVar);
        zzbtz zzbtzVar = zzbuc.zza;
        return new zzcbk(zza, zzb.zza("google.afma.sdkConstants.getSdkConstants", zzbtzVar, zzbtzVar));
    }
}
