package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzdxt extends zzdxv {
    private final zzfih zzf;

    public zzdxt(Executor executor, zzcgs zzcgsVar, zzfih zzfihVar, zzfij zzfijVar) {
        super(executor, zzcgsVar, zzfijVar);
        this.zzf = zzfihVar;
        zzfihVar.zza(this.zzb);
    }

    public final Map zza() {
        return new HashMap(this.zzb);
    }
}
