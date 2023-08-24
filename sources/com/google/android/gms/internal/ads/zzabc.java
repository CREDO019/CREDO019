package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzabc {
    protected final zzaam zza;
    private final int zzb;
    private final int zzc;
    private final long zzd;
    private final int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private long[] zzk;
    private int[] zzl;

    public zzabc(int r3, int r4, long j, int r7, zzaam zzaamVar) {
        r4 = r4 != 1 ? 2 : r4;
        this.zzd = j;
        this.zze = r7;
        this.zza = zzaamVar;
        this.zzb = zzi(r3, r4 == 2 ? 1667497984 : 1651965952);
        this.zzc = r4 == 2 ? zzi(r3, 1650720768) : -1;
        this.zzk = new long[512];
        this.zzl = new int[512];
    }

    private static int zzi(int r1, int r2) {
        return ((r1 / 10) + 48) | (((r1 % 10) + 48) << 8) | r2;
    }

    private final long zzj(int r5) {
        return (this.zzd * r5) / this.zze;
    }

    private final zzaaj zzk(int r7) {
        return new zzaaj(this.zzl[r7] * zzj(1), this.zzk[r7]);
    }

    public final zzaag zza(long j) {
        int zzj = (int) (j / zzj(1));
        int zzc = zzel.zzc(this.zzl, zzj, true, true);
        if (this.zzl[zzc] == zzj) {
            zzaaj zzk = zzk(zzc);
            return new zzaag(zzk, zzk);
        }
        zzaaj zzk2 = zzk(zzc);
        int r4 = zzc + 1;
        return r4 < this.zzk.length ? new zzaag(zzk2, zzk(r4)) : new zzaag(zzk2, zzk2);
    }

    public final void zzb(long j) {
        if (this.zzj == this.zzl.length) {
            long[] jArr = this.zzk;
            this.zzk = Arrays.copyOf(jArr, (jArr.length * 3) / 2);
            int[] r0 = this.zzl;
            this.zzl = Arrays.copyOf(r0, (r0.length * 3) / 2);
        }
        long[] jArr2 = this.zzk;
        int r1 = this.zzj;
        jArr2[r1] = j;
        this.zzl[r1] = this.zzi;
        this.zzj = r1 + 1;
    }

    public final void zzc() {
        this.zzk = Arrays.copyOf(this.zzk, this.zzj);
        this.zzl = Arrays.copyOf(this.zzl, this.zzj);
    }

    public final void zzd() {
        this.zzi++;
    }

    public final void zze(int r1) {
        this.zzf = r1;
        this.zzg = r1;
    }

    public final void zzf(long j) {
        if (this.zzj == 0) {
            this.zzh = 0;
            return;
        }
        this.zzh = this.zzl[zzel.zzd(this.zzk, j, true, true)];
    }

    public final boolean zzg(int r2) {
        return this.zzb == r2 || this.zzc == r2;
    }

    public final boolean zzh(zzzg zzzgVar) throws IOException {
        int r0 = this.zzg;
        int zze = r0 - this.zza.zze(zzzgVar, r0, false);
        this.zzg = zze;
        boolean z = zze == 0;
        if (z) {
            if (this.zzf > 0) {
                this.zza.zzs(zzj(this.zzh), Arrays.binarySearch(this.zzl, this.zzh) >= 0 ? 1 : 0, this.zzf, 0, null);
            }
            this.zzh++;
        }
        return z;
    }
}
