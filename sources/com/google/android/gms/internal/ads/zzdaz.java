package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdaz implements zzgur {
    private final zzday zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdaz(zzday zzdayVar, zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzdayVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return new com.google.android.gms.ads.internal.zzb((Context) this.zzb.zzb(), (zzcdo) this.zzc.zzb(), null);
    }
}
