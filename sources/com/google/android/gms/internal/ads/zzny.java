package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import androidx.work.WorkRequest;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzny {
    private final zznx zza;
    private int zzb;
    private long zzc;
    private long zzd;
    private long zze;
    private long zzf;

    public zzny(AudioTrack audioTrack) {
        int r0 = zzel.zza;
        this.zza = new zznx(audioTrack);
        zzh(0);
    }

    private final void zzh(int r7) {
        this.zzb = r7;
        long j = WorkRequest.MIN_BACKOFF_MILLIS;
        if (r7 == 0) {
            this.zze = 0L;
            this.zzf = -1L;
            this.zzc = System.nanoTime() / 1000;
        } else if (r7 == 1) {
            this.zzd = WorkRequest.MIN_BACKOFF_MILLIS;
            return;
        } else {
            j = (r7 == 2 || r7 == 3) ? 10000000L : 500000L;
        }
        this.zzd = j;
    }

    public final long zza() {
        return this.zza.zza();
    }

    public final long zzb() {
        return this.zza.zzb();
    }

    public final void zzc() {
        if (this.zzb == 4) {
            zzh(0);
        }
    }

    public final void zzd() {
        zzh(4);
    }

    public final void zze() {
        zzh(0);
    }

    public final boolean zzf() {
        return this.zzb == 2;
    }

    public final boolean zzg(long j) {
        zznx zznxVar = this.zza;
        if (j - this.zze < this.zzd) {
            return false;
        }
        this.zze = j;
        boolean zzc = zznxVar.zzc();
        int r1 = this.zzb;
        if (r1 != 0) {
            if (r1 != 1) {
                if (r1 == 2) {
                    if (zzc) {
                        return true;
                    }
                    zzh(0);
                    return false;
                } else if (r1 == 3) {
                    if (zzc) {
                        zzh(0);
                        return true;
                    }
                    return false;
                }
            } else if (!zzc) {
                zzh(0);
            } else if (this.zza.zza() > this.zzf) {
                zzh(2);
                return true;
            }
        } else if (zzc) {
            if (this.zza.zzb() < this.zzc) {
                return false;
            }
            this.zzf = this.zza.zza();
            zzh(1);
            return true;
        } else if (j - this.zzc > 500000) {
            zzh(3);
            return false;
        }
        return zzc;
    }
}
