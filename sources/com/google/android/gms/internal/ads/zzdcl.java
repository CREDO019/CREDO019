package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdcl implements zzgur {
    private final zzdck zza;
    private final zzgve zzb;

    public zzdcl(zzdck zzdckVar, zzgve zzgveVar) {
        this.zza = zzdckVar;
        this.zzb = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Context zza = this.zza.zza(((zzcoq) this.zzb).zza());
        zzguz.zzb(zza);
        return zza;
    }
}
