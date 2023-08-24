package com.google.android.gms.internal.ads;

import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbaj implements zzbac {
    private boolean zza;
    private long zzb;
    private long zzc;
    private zzasw zzd = zzasw.zza;

    @Override // com.google.android.gms.internal.ads.zzbac
    public final long zzI() {
        long zza;
        long j = this.zzb;
        if (this.zza) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzc;
            zzasw zzaswVar = this.zzd;
            if (zzaswVar.zzb == 1.0f) {
                zza = zzasd.zza(elapsedRealtime);
            } else {
                zza = zzaswVar.zza(elapsedRealtime);
            }
            return j + zza;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzbac
    public final zzasw zzJ() {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbac
    public final zzasw zzK(zzasw zzaswVar) {
        if (this.zza) {
            zza(zzI());
        }
        this.zzd = zzaswVar;
        return zzaswVar;
    }

    public final void zza(long j) {
        this.zzb = j;
        if (this.zza) {
            this.zzc = SystemClock.elapsedRealtime();
        }
    }

    public final void zzb() {
        if (this.zza) {
            return;
        }
        this.zzc = SystemClock.elapsedRealtime();
        this.zza = true;
    }

    public final void zzc() {
        if (this.zza) {
            zza(zzI());
            this.zza = false;
        }
    }

    public final void zzd(zzbac zzbacVar) {
        zza(zzbacVar.zzI());
        this.zzd = zzbacVar.zzJ();
    }
}
