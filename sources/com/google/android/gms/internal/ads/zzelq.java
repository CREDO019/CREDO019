package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzelq implements com.google.android.gms.ads.internal.zzf {
    final AtomicBoolean zza = new AtomicBoolean(false);
    private final zzdcw zzb;
    private final zzddq zzc;
    private final zzdkn zzd;
    private final zzdkg zze;
    private final zzcvi zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzelq(zzdcw zzdcwVar, zzddq zzddqVar, zzdkn zzdknVar, zzdkg zzdkgVar, zzcvi zzcviVar) {
        this.zzb = zzdcwVar;
        this.zzc = zzddqVar;
        this.zzd = zzdknVar;
        this.zze = zzdkgVar;
        this.zzf = zzcviVar;
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final synchronized void zza(View view) {
        if (this.zza.compareAndSet(false, true)) {
            this.zzf.zzl();
            this.zze.zza(view);
        }
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zzb() {
        if (this.zza.get()) {
            this.zzb.onAdClicked();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zzc() {
        if (this.zza.get()) {
            this.zzc.zza();
            this.zzd.zza();
        }
    }
}
