package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzdxo {
    private final zzdxt zza;
    private final Executor zzb;
    private final Map zzc;

    public zzdxo(zzdxt zzdxtVar, Executor executor) {
        this.zza = zzdxtVar;
        this.zzc = zzdxtVar.zza();
        this.zzb = executor;
    }

    public final zzdxn zza() {
        zzdxn zzdxnVar = new zzdxn(this);
        zzdxn.zza(zzdxnVar);
        return zzdxnVar;
    }
}
