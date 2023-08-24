package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdqv implements zzgur {
    private final zzdqo zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdqv(zzdqo zzdqoVar, zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzdqoVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return new zzdke(((zzdtw) this.zzb).zzb(), (Executor) this.zzc.zzb());
    }
}
