package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdps implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzdps(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return new zzdpr(((zzdnv) this.zza).zza(), (Clock) this.zzb.zzb());
    }
}
