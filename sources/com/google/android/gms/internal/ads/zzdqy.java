package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdqy implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdqy(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzdqx((com.google.android.gms.ads.internal.util.zzbo) this.zza.zzb(), (Clock) this.zzb.zzb(), zzfyyVar);
    }
}
