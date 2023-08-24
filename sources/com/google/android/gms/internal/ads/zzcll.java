package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.DefaultLoadControl;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcll implements zzjf {
    private final zzwf zza = new zzwf(true, 65536);
    private long zzb = 15000000;
    private long zzc = 30000000;
    private long zzd = 2500000;
    private long zze = 5000000;
    private int zzf;
    private boolean zzg;

    @Override // com.google.android.gms.internal.ads.zzjf
    public final long zza() {
        return 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zzb() {
        zzj(false);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zzc() {
        zzj(true);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zzd() {
        zzj(true);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zze(zzjy[] zzjyVarArr, zzue zzueVar, zzvq[] zzvqVarArr) {
        int r5 = 0;
        this.zzf = 0;
        while (true) {
            int length = zzjyVarArr.length;
            if (r5 < 2) {
                if (zzvqVarArr[r5] != null) {
                    this.zzf += zzjyVarArr[r5].zzb() != 1 ? DefaultLoadControl.DEFAULT_VIDEO_BUFFER_SIZE : 13107200;
                }
                r5++;
            } else {
                this.zza.zzf(this.zzf);
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
        boolean z = true;
        char c = j2 > this.zzc ? (char) 0 : j2 < this.zzb ? (char) 2 : (char) 1;
        int zza = this.zza.zza();
        int r6 = this.zzf;
        if (c != 2 && (c != 1 || !this.zzg || zza >= r6)) {
            z = false;
        }
        this.zzg = z;
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final boolean zzh(long j, float f, boolean z, long j2) {
        long j3 = z ? this.zze : this.zzd;
        return j3 <= 0 || j >= j3;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final zzwf zzi() {
        return this.zza;
    }

    final void zzj(boolean z) {
        this.zzf = 0;
        this.zzg = false;
        if (z) {
            this.zza.zze();
        }
    }

    public final synchronized void zzk(int r5) {
        this.zzd = r5 * 1000;
    }

    public final synchronized void zzl(int r5) {
        this.zze = r5 * 1000;
    }

    public final synchronized void zzm(int r5) {
        this.zzc = r5 * 1000;
    }

    public final synchronized void zzn(int r5) {
        this.zzb = r5 * 1000;
    }
}
