package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcyr implements zzdem, zzbbm {
    private final zzfcs zza;
    private final zzddq zzb;
    private final zzdev zzc;
    private final AtomicBoolean zzd = new AtomicBoolean();
    private final AtomicBoolean zze = new AtomicBoolean();

    public zzcyr(zzfcs zzfcsVar, zzddq zzddqVar, zzdev zzdevVar) {
        this.zza = zzfcsVar;
        this.zzb = zzddqVar;
        this.zzc = zzdevVar;
    }

    private final void zza() {
        if (this.zzd.compareAndSet(false, true)) {
            this.zzb.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final void zzc(zzbbl zzbblVar) {
        if (this.zza.zzf == 1 && zzbblVar.zzj) {
            zza();
        }
        if (zzbblVar.zzj && this.zze.compareAndSet(false, true)) {
            this.zzc.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final synchronized void zzn() {
        if (this.zza.zzf != 1) {
            zza();
        }
    }
}
