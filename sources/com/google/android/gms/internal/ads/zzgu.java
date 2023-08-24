package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgu implements zzjf {
    private final zzwf zza;
    private final long zzb;
    private final long zzc;
    private final long zzd;
    private final long zze;
    private final long zzf;
    private int zzg;
    private boolean zzh;

    public zzgu() {
        zzwf zzwfVar = new zzwf(true, 65536);
        zzj(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 0, "bufferForPlaybackMs", SessionDescription.SUPPORTED_SDP_VERSION);
        zzj(5000, 0, "bufferForPlaybackAfterRebufferMs", SessionDescription.SUPPORTED_SDP_VERSION);
        zzj(50000, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, "minBufferMs", "bufferForPlaybackMs");
        zzj(50000, 5000, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        zzj(50000, 50000, "maxBufferMs", "minBufferMs");
        zzj(0, 0, "backBufferDurationMs", SessionDescription.SUPPORTED_SDP_VERSION);
        this.zza = zzwfVar;
        this.zzb = zzel.zzv(50000L);
        this.zzc = zzel.zzv(50000L);
        this.zzd = zzel.zzv(2500L);
        this.zze = zzel.zzv(5000L);
        this.zzg = 13107200;
        this.zzf = zzel.zzv(0L);
    }

    private static void zzj(int r0, int r1, String str, String str2) {
        boolean z = r0 >= r1;
        zzdd.zze(z, str + " cannot be less than " + str2);
    }

    private final void zzk(boolean z) {
        this.zzg = 13107200;
        this.zzh = false;
        if (z) {
            this.zza.zze();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final long zza() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zzb() {
        zzk(false);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zzc() {
        zzk(true);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zzd() {
        zzk(true);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zze(zzjy[] zzjyVarArr, zzue zzueVar, zzvq[] zzvqVarArr) {
        int r6 = 0;
        int r0 = 0;
        while (true) {
            int length = zzjyVarArr.length;
            if (r6 < 2) {
                if (zzvqVarArr[r6] != null) {
                    r0 += zzjyVarArr[r6].zzb() != 1 ? DefaultLoadControl.DEFAULT_VIDEO_BUFFER_SIZE : 13107200;
                }
                r6++;
            } else {
                int max = Math.max(13107200, r0);
                this.zzg = max;
                this.zza.zzf(max);
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final boolean zzg(long j, long j2, float f) {
        int zza = this.zza.zza();
        int r7 = this.zzg;
        long j3 = this.zzb;
        if (f > 1.0f) {
            j3 = Math.min(zzel.zzs(j3, f), this.zzc);
        }
        if (j2 < Math.max(j3, 500000L)) {
            boolean z = zza < r7;
            this.zzh = z;
            if (!z && j2 < 500000) {
                Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j2 >= this.zzc || zza >= r7) {
            this.zzh = false;
        }
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final boolean zzh(long j, float f, boolean z, long j2) {
        long zzu = zzel.zzu(j, f);
        long j3 = z ? this.zze : this.zzd;
        if (j2 != C1856C.TIME_UNSET) {
            j3 = Math.min(j2 / 2, j3);
        }
        return j3 <= 0 || zzu >= j3 || this.zza.zza() >= this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final zzwf zzi() {
        return this.zza;
    }
}
