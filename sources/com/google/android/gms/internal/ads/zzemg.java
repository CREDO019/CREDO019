package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzemg implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzemg(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzemf zzb() {
        return new zzemf((Context) this.zza.zzb(), (zzdmf) this.zzb.zzb());
    }
}
