package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdln implements zzgur {
    private final zzdli zza;
    private final zzgve zzb;

    public zzdln(zzdli zzdliVar, zzgve zzgveVar) {
        this.zza = zzdliVar;
        this.zzb = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Set singleton = Collections.singleton(new zzdke((zzdby) this.zzb.zzb(), zzcha.zzf));
        zzguz.zzb(singleton);
        return singleton;
    }
}
