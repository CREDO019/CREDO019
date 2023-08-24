package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcdv implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzcdv(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzcdu zzb() {
        return new zzcdu((Clock) this.zza.zzb(), (zzcds) this.zzb.zzb());
    }
}
