package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdcu implements zzgur {
    private final zzdct zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;

    public zzdcu(zzdct zzdctVar, zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4) {
        this.zza = zzdctVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
        this.zzd = zzgveVar3;
        this.zze = zzgveVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* synthetic */ Object zzb() {
        Context context = (Context) this.zzb.zzb();
        zzcgt zza = ((zzcpa) this.zzc).zza();
        zzfcs zza2 = ((zzczs) this.zzd).zza();
        zzcdk zzcdkVar = new zzcdk();
        if (zza2.zzB != null) {
            return new zzcdj(context, zza, zza2.zzB, zza2.zzt.zzb, zzcdkVar, null);
        }
        return null;
    }
}
