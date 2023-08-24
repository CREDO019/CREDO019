package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public class zzchm {
    private final zzchf zza;
    private final AtomicInteger zzb;

    public zzchm() {
        zzchf zzchfVar = new zzchf();
        this.zza = zzchfVar;
        this.zzb = new AtomicInteger(0);
        zzfyo.zzr(zzchfVar, new zzchk(this), zzcha.zzf);
    }

    @Deprecated
    public final int zze() {
        return this.zzb.get();
    }

    @Deprecated
    public final void zzg() {
        this.zza.zze(new Exception());
    }

    @Deprecated
    public final void zzh(Object obj) {
        this.zza.zzd(obj);
    }

    @Deprecated
    public final void zzi(zzchj zzchjVar, zzchh zzchhVar) {
        zzfyo.zzr(this.zza, new zzchl(this, zzchjVar, zzchhVar), zzcha.zzf);
    }
}
