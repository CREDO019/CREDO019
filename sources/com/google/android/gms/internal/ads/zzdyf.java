package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdyf implements zzgur {
    private final zzdyb zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdyf(zzdyb zzdybVar, zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzdybVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        Set zzd = zzdyb.zzd((zzdyl) this.zzb.zzb(), zzfyyVar);
        zzguz.zzb(zzd);
        return zzd;
    }
}