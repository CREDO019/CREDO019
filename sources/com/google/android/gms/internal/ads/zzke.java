package com.google.android.gms.internal.ads;

import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzke implements zzjg {
    private final zzde zza;
    private boolean zzb;
    private long zzc;
    private long zzd;
    private zzby zze = zzby.zza;

    public zzke(zzde zzdeVar) {
        this.zza = zzdeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final long zza() {
        long zza;
        long j = this.zzc;
        if (this.zzb) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzd;
            zzby zzbyVar = this.zze;
            if (zzbyVar.zzc == 1.0f) {
                zza = zzel.zzv(elapsedRealtime);
            } else {
                zza = zzbyVar.zza(elapsedRealtime);
            }
            return j + zza;
        }
        return j;
    }

    public final void zzb(long j) {
        this.zzc = j;
        if (this.zzb) {
            this.zzd = SystemClock.elapsedRealtime();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final zzby zzc() {
        return this.zze;
    }

    public final void zzd() {
        if (this.zzb) {
            return;
        }
        this.zzd = SystemClock.elapsedRealtime();
        this.zzb = true;
    }

    public final void zze() {
        if (this.zzb) {
            zzb(zza());
            this.zzb = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final void zzg(zzby zzbyVar) {
        if (this.zzb) {
            zzb(zza());
        }
        this.zze = zzbyVar;
    }
}
