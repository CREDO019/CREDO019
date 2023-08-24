package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzxa {
    private boolean zzc;
    private int zze;
    private zzwz zza = new zzwz();
    private zzwz zzb = new zzwz();
    private long zzd = C1856C.TIME_UNSET;

    public final float zza() {
        if (this.zza.zzf()) {
            return (float) (1.0E9d / this.zza.zza());
        }
        return -1.0f;
    }

    public final int zzb() {
        return this.zze;
    }

    public final long zzc() {
        return this.zza.zzf() ? this.zza.zza() : C1856C.TIME_UNSET;
    }

    public final long zzd() {
        return this.zza.zzf() ? this.zza.zzb() : C1856C.TIME_UNSET;
    }

    public final void zze(long j) {
        this.zza.zzc(j);
        if (this.zza.zzf()) {
            this.zzc = false;
        } else if (this.zzd != C1856C.TIME_UNSET) {
            if (!this.zzc || this.zzb.zze()) {
                this.zzb.zzd();
                this.zzb.zzc(this.zzd);
            }
            this.zzc = true;
            this.zzb.zzc(j);
        }
        if (this.zzc && this.zzb.zzf()) {
            zzwz zzwzVar = this.zza;
            this.zza = this.zzb;
            this.zzb = zzwzVar;
            this.zzc = false;
        }
        this.zzd = j;
        this.zze = this.zza.zzf() ? 0 : this.zze + 1;
    }

    public final void zzf() {
        this.zza.zzd();
        this.zzb.zzd();
        this.zzc = false;
        this.zzd = C1856C.TIME_UNSET;
        this.zze = 0;
    }

    public final boolean zzg() {
        return this.zza.zzf();
    }
}
