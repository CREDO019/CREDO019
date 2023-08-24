package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcjt {
    private final zzazl zza = new zzazl(true, 65536);
    private long zzb = 15000000;
    private long zzc = 30000000;
    private long zzd = 2500000;
    private long zze = 5000000;
    private int zzf;
    private boolean zzg;

    public final void zza() {
        zze(false);
    }

    public final void zzb() {
        zze(true);
    }

    public final void zzc() {
        zze(true);
    }

    public final void zzd(zzasx[] zzasxVarArr, zzayp zzaypVar, zzazb zzazbVar) {
        this.zzf = 0;
        for (int r4 = 0; r4 < 2; r4++) {
            if (zzazbVar.zza(r4) != null) {
                this.zzf += zzban.zzf(zzasxVarArr[r4].zzc());
            }
        }
        this.zza.zzf(this.zzf);
    }

    final void zze(boolean z) {
        this.zzf = 0;
        this.zzg = false;
        if (z) {
            this.zza.zze();
        }
    }

    public final synchronized void zzf(int r5) {
        this.zzd = r5 * 1000;
    }

    public final synchronized void zzg(int r5) {
        this.zze = r5 * 1000;
    }

    public final synchronized void zzh(int r5) {
        this.zzc = r5 * 1000;
    }

    public final synchronized void zzi(int r5) {
        this.zzb = r5 * 1000;
    }

    public final synchronized boolean zzj(long j) {
        boolean z;
        z = true;
        char c = j > this.zzc ? (char) 0 : j < this.zzb ? (char) 2 : (char) 1;
        int zza = this.zza.zza();
        int r0 = this.zzf;
        if (c != 2 && (c != 1 || !this.zzg || zza >= r0)) {
            z = false;
        }
        this.zzg = z;
        return z;
    }

    public final synchronized boolean zzk(long j, boolean z) {
        long j2;
        j2 = z ? this.zze : this.zzd;
        return j2 <= 0 || j >= j2;
    }

    public final zzazl zzl() {
        return this.zza;
    }
}
