package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzayg {
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zzass zzq;
    private int zza = 1000;
    private int[] zzb = new int[1000];
    private long[] zzc = new long[1000];
    private long[] zzf = new long[1000];
    private int[] zze = new int[1000];
    private int[] zzd = new int[1000];
    private zzavd[] zzg = new zzavd[1000];
    private zzass[] zzh = new zzass[1000];
    private long zzm = Long.MIN_VALUE;
    private long zzn = Long.MIN_VALUE;
    private boolean zzp = true;
    private boolean zzo = true;

    public final int zza() {
        return this.zzj + this.zzi;
    }

    public final synchronized int zzb(zzast zzastVar, zzaun zzaunVar, boolean z, boolean z2, zzass zzassVar, zzayf zzayfVar) {
        if (!zzl()) {
            if (z2) {
                zzaunVar.zzc(4);
                return -4;
            }
            zzass zzassVar2 = this.zzq;
            if (zzassVar2 == null || (!z && zzassVar2 == zzassVar)) {
                return -3;
            }
            zzastVar.zza = zzassVar2;
            return -5;
        }
        if (!z) {
            zzass[] zzassVarArr = this.zzh;
            int r8 = this.zzk;
            if (zzassVarArr[r8] == zzassVar) {
                if (zzaunVar.zzb != null) {
                    zzaunVar.zzc = this.zzf[r8];
                    zzaunVar.zzc(this.zze[r8]);
                    int[] r5 = this.zzd;
                    int r7 = this.zzk;
                    zzayfVar.zza = r5[r7];
                    zzayfVar.zzb = this.zzc[r7];
                    zzayfVar.zzd = this.zzg[r7];
                    this.zzm = Math.max(this.zzm, zzaunVar.zzc);
                    int r52 = this.zzi - 1;
                    this.zzi = r52;
                    int r6 = this.zzk + 1;
                    this.zzk = r6;
                    this.zzj++;
                    if (r6 == this.zza) {
                        this.zzk = 0;
                        r6 = 0;
                    }
                    zzayfVar.zzc = r52 > 0 ? this.zzc[r6] : zzayfVar.zzb + zzayfVar.zza;
                    return -4;
                }
                return -3;
            }
        }
        zzastVar.zza = this.zzh[this.zzk];
        return -5;
    }

    public final synchronized long zzc() {
        return Math.max(this.zzm, this.zzn);
    }

    public final synchronized long zzd() {
        if (zzl()) {
            int r0 = this.zzk;
            int r1 = this.zzi;
            int r02 = r0 + r1;
            int r2 = this.zza;
            int r3 = (r02 - 1) % r2;
            this.zzk = r02 % r2;
            this.zzj += r1;
            this.zzi = 0;
            return this.zzc[r3] + this.zzd[r3];
        }
        return -1L;
    }

    public final synchronized long zze(long j, boolean z) {
        if (zzl()) {
            long[] jArr = this.zzf;
            int r3 = this.zzk;
            if (j >= jArr[r3]) {
                int r0 = 0;
                if (j <= this.zzn || z) {
                    int r12 = -1;
                    while (r3 != this.zzl && this.zzf[r3] <= j) {
                        if (1 == (this.zze[r3] & 1)) {
                            r12 = r0;
                        }
                        r3 = (r3 + 1) % this.zza;
                        r0++;
                    }
                    if (r12 == -1) {
                        return -1L;
                    }
                    int r10 = (this.zzk + r12) % this.zza;
                    this.zzk = r10;
                    this.zzj += r12;
                    this.zzi -= r12;
                    return this.zzc[r10];
                }
                return -1L;
            }
        }
        return -1L;
    }

    public final synchronized zzass zzf() {
        if (this.zzp) {
            return null;
        }
        return this.zzq;
    }

    public final void zzg() {
        this.zzj = 0;
        this.zzk = 0;
        this.zzl = 0;
        this.zzi = 0;
        this.zzo = true;
    }

    public final synchronized void zzh(long j, int r8, long j2, int r11, zzavd zzavdVar) {
        if (this.zzo) {
            if ((r8 & 1) == 0) {
                return;
            }
            this.zzo = false;
        }
        zzazy.zze(!this.zzp);
        zzi(j);
        long[] jArr = this.zzf;
        int r2 = this.zzl;
        jArr[r2] = j;
        long[] jArr2 = this.zzc;
        jArr2[r2] = j2;
        this.zzd[r2] = r11;
        this.zze[r2] = r8;
        this.zzg[r2] = zzavdVar;
        this.zzh[r2] = this.zzq;
        this.zzb[r2] = 0;
        int r7 = this.zzi + 1;
        this.zzi = r7;
        int r82 = this.zza;
        if (r7 != r82) {
            int r22 = r2 + 1;
            this.zzl = r22;
            if (r22 == r82) {
                this.zzl = 0;
                return;
            }
            return;
        }
        int r72 = r82 + 1000;
        int[] r9 = new int[r72];
        long[] jArr3 = new long[r72];
        long[] jArr4 = new long[r72];
        int[] r12 = new int[r72];
        int[] r0 = new int[r72];
        zzavd[] zzavdVarArr = new zzavd[r72];
        zzass[] zzassVarArr = new zzass[r72];
        int r4 = this.zzk;
        int r83 = r82 - r4;
        System.arraycopy(jArr2, r4, jArr3, 0, r83);
        System.arraycopy(this.zzf, this.zzk, jArr4, 0, r83);
        System.arraycopy(this.zze, this.zzk, r12, 0, r83);
        System.arraycopy(this.zzd, this.zzk, r0, 0, r83);
        System.arraycopy(this.zzg, this.zzk, zzavdVarArr, 0, r83);
        System.arraycopy(this.zzh, this.zzk, zzassVarArr, 0, r83);
        System.arraycopy(this.zzb, this.zzk, r9, 0, r83);
        int r6 = this.zzk;
        System.arraycopy(this.zzc, 0, jArr3, r83, r6);
        System.arraycopy(this.zzf, 0, jArr4, r83, r6);
        System.arraycopy(this.zze, 0, r12, r83, r6);
        System.arraycopy(this.zzd, 0, r0, r83, r6);
        System.arraycopy(this.zzg, 0, zzavdVarArr, r83, r6);
        System.arraycopy(this.zzh, 0, zzassVarArr, r83, r6);
        System.arraycopy(this.zzb, 0, r9, r83, r6);
        this.zzc = jArr3;
        this.zzf = jArr4;
        this.zze = r12;
        this.zzd = r0;
        this.zzg = zzavdVarArr;
        this.zzh = zzassVarArr;
        this.zzb = r9;
        this.zzk = 0;
        int r62 = this.zza;
        this.zzl = r62;
        this.zzi = r62;
        this.zza = r72;
    }

    public final synchronized void zzi(long j) {
        this.zzn = Math.max(this.zzn, j);
    }

    public final void zzj() {
        this.zzm = Long.MIN_VALUE;
        this.zzn = Long.MIN_VALUE;
    }

    public final synchronized boolean zzk(zzass zzassVar) {
        if (zzassVar == null) {
            this.zzp = true;
            return false;
        }
        this.zzp = false;
        if (zzban.zzo(zzassVar, this.zzq)) {
            return false;
        }
        this.zzq = zzassVar;
        return true;
    }

    public final synchronized boolean zzl() {
        return this.zzi != 0;
    }
}
