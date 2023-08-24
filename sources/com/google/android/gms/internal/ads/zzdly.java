package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdly implements zzgur {
    private final zzdli zza;
    private final zzgve zzb;

    public zzdly(zzdli zzdliVar, zzgve zzgveVar) {
        this.zza = zzdliVar;
        this.zzb = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return this.zza.zzd((Executor) this.zzb.zzb());
    }
}
