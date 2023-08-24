package com.google.android.gms.internal.ads;

import java.io.IOException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzagk {
    private zzaam zzb;
    private zzzi zzc;
    private zzagf zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private int zzh;
    private int zzi;
    private long zzk;
    private boolean zzl;
    private boolean zzm;
    private final zzagd zza = new zzagd();
    private zzagh zzj = new zzagh();

    protected abstract long zza(zzed zzedVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzb(boolean z) {
        int r5;
        if (z) {
            this.zzj = new zzagh();
            this.zzf = 0L;
            r5 = 0;
        } else {
            r5 = 1;
        }
        this.zzh = r5;
        this.zze = -1L;
        this.zzg = 0L;
    }

    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    protected abstract boolean zzc(zzed zzedVar, long j, zzagh zzaghVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zze(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        zzdd.zzb(this.zzb);
        int r1 = zzel.zza;
        int r12 = this.zzh;
        if (r12 == 0) {
            while (this.zza.zze(zzzgVar)) {
                long zzf = zzzgVar.zzf();
                long j = this.zzf;
                this.zzk = zzf - j;
                if (!zzc(this.zza.zza(), j, this.zzj)) {
                    zzaf zzafVar = this.zzj.zza;
                    this.zzi = zzafVar.zzA;
                    if (!this.zzm) {
                        this.zzb.zzk(zzafVar);
                        this.zzm = true;
                    }
                    zzagf zzagfVar = this.zzj.zzb;
                    if (zzagfVar != null) {
                        this.zzd = zzagfVar;
                    } else if (zzzgVar.zzd() == -1) {
                        this.zzd = new zzagj(null);
                    } else {
                        zzage zzb = this.zza.zzb();
                        this.zzd = new zzafy(this, this.zzf, zzzgVar.zzd(), zzb.zzd + zzb.zze, zzb.zzb, (zzb.zza & 4) != 0);
                    }
                    this.zzh = 2;
                    this.zza.zzd();
                    return 0;
                }
                this.zzf = zzzgVar.zzf();
            }
            this.zzh = 3;
            return -1;
        } else if (r12 == 1) {
            ((zzyv) zzzgVar).zzo((int) this.zzf, false);
            this.zzh = 2;
            return 0;
        } else if (r12 != 2) {
            return -1;
        } else {
            long zzd = this.zzd.zzd(zzzgVar);
            if (zzd >= 0) {
                zzaafVar.zza = zzd;
                return 1;
            }
            if (zzd < -1) {
                zzi(-(zzd + 2));
            }
            if (!this.zzl) {
                zzaai zze = this.zzd.zze();
                zzdd.zzb(zze);
                this.zzc.zzL(zze);
                this.zzl = true;
            }
            if (this.zzk > 0 || this.zza.zze(zzzgVar)) {
                this.zzk = 0L;
                zzed zza = this.zza.zza();
                long zza2 = zza(zza);
                if (zza2 >= 0) {
                    long j2 = this.zzg;
                    if (j2 + zza2 >= this.zze) {
                        long zzf2 = zzf(j2);
                        zzaak.zzb(this.zzb, zza, zza.zzd());
                        this.zzb.zzs(zzf2, 1, zza.zzd(), 0, null);
                        this.zze = -1L;
                    }
                }
                this.zzg += zza2;
                return 0;
            }
            this.zzh = 3;
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzf(long j) {
        return (j * 1000000) / this.zzi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzg(long j) {
        return (this.zzi * j) / 1000000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(zzzi zzziVar, zzaam zzaamVar) {
        this.zzc = zzziVar;
        this.zzb = zzaamVar;
        zzb(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzi(long j) {
        this.zzg = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(long j, long j2) {
        this.zza.zzc();
        if (j == 0) {
            zzb(!this.zzl);
        } else if (this.zzh != 0) {
            this.zze = zzg(j2);
            zzagf zzagfVar = this.zzd;
            int r5 = zzel.zza;
            zzagfVar.zzg(this.zze);
            this.zzh = 2;
        }
    }
}
