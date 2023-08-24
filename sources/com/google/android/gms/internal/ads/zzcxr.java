package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcxr implements zzgur {
    private final zzcxh zza;
    private final zzgve zzb;

    public zzcxr(zzcxh zzcxhVar, zzgve zzgveVar) {
        this.zza = zzcxhVar;
        this.zzb = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Set singleton = Collections.singleton(new zzdke((zzcyr) this.zzb.zzb(), zzcha.zzf));
        zzguz.zzb(singleton);
        return singleton;
    }
}
