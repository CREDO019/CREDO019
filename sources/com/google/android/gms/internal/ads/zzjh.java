package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.MediaPeriodQueue;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzjh {
    public final zzse zza;
    public final Object zzb;
    public boolean zzd;
    public boolean zze;
    public zzji zzf;
    public boolean zzg;
    private final zzjz[] zzi;
    private final zzvw zzj;
    private final zzjr zzk;
    private zzjh zzl;
    private zzvx zzn;
    private long zzo;
    private zzue zzm = zzue.zza;
    public final zztw[] zzc = new zztw[2];
    private final boolean[] zzh = new boolean[2];

    public zzjh(zzjz[] zzjzVarArr, long j, zzvw zzvwVar, zzwf zzwfVar, zzjr zzjrVar, zzji zzjiVar, zzvx zzvxVar, byte[] bArr) {
        this.zzi = zzjzVarArr;
        this.zzo = j;
        this.zzj = zzvwVar;
        this.zzk = zzjrVar;
        this.zzb = zzjiVar.zza.zza;
        this.zzf = zzjiVar;
        this.zzn = zzvxVar;
        zzsg zzsgVar = zzjiVar.zza;
        long j2 = zzjiVar.zzb;
        long j3 = zzjiVar.zzd;
        zzse zzo = zzjrVar.zzo(zzsgVar, zzwfVar, j2);
        this.zza = j3 != C1856C.TIME_UNSET ? new zzrl(zzo, true, 0L, j3) : zzo;
    }

    private final void zzs() {
        if (!zzu()) {
            return;
        }
        int r0 = 0;
        while (true) {
            zzvx zzvxVar = this.zzn;
            if (r0 >= zzvxVar.zza) {
                return;
            }
            zzvxVar.zzb(r0);
            zzvq zzvqVar = this.zzn.zzc[r0];
            r0++;
        }
    }

    private final void zzt() {
        if (!zzu()) {
            return;
        }
        int r0 = 0;
        while (true) {
            zzvx zzvxVar = this.zzn;
            if (r0 >= zzvxVar.zza) {
                return;
            }
            zzvxVar.zzb(r0);
            zzvq zzvqVar = this.zzn.zzc[r0];
            r0++;
        }
    }

    private final boolean zzu() {
        return this.zzl == null;
    }

    public final long zza(zzvx zzvxVar, long j, boolean z) {
        return zzb(zzvxVar, j, false, new boolean[2]);
    }

    public final long zzb(zzvx zzvxVar, long j, boolean z, boolean[] zArr) {
        int r3 = 0;
        while (true) {
            boolean z2 = true;
            if (r3 >= zzvxVar.zza) {
                break;
            }
            boolean[] zArr2 = this.zzh;
            if (z || !zzvxVar.zza(this.zzn, r3)) {
                z2 = false;
            }
            zArr2[r3] = z2;
            r3++;
        }
        int r32 = 0;
        while (true) {
            zzjz[] zzjzVarArr = this.zzi;
            if (r32 >= 2) {
                break;
            }
            zzjzVarArr[r32].zzb();
            r32++;
        }
        zzs();
        this.zzn = zzvxVar;
        zzt();
        long zzf = this.zza.zzf(zzvxVar.zzc, this.zzh, this.zzc, zArr, j);
        int r7 = 0;
        while (true) {
            zzjz[] zzjzVarArr2 = this.zzi;
            if (r7 >= 2) {
                break;
            }
            zzjzVarArr2[r7].zzb();
            r7++;
        }
        this.zze = false;
        int r72 = 0;
        while (true) {
            zztw[] zztwVarArr = this.zzc;
            if (r72 >= 2) {
                return zzf;
            }
            if (zztwVarArr[r72] != null) {
                zzdd.zzf(zzvxVar.zzb(r72));
                this.zzi[r72].zzb();
                this.zze = true;
            } else {
                zzdd.zzf(zzvxVar.zzc[r72] == null);
            }
            r72++;
        }
    }

    public final long zzc() {
        if (this.zzd) {
            long zzb = this.zze ? this.zza.zzb() : Long.MIN_VALUE;
            return zzb == Long.MIN_VALUE ? this.zzf.zze : zzb;
        }
        return this.zzf.zzb;
    }

    public final long zzd() {
        if (this.zzd) {
            return this.zza.zzc();
        }
        return 0L;
    }

    public final long zze() {
        return this.zzo;
    }

    public final long zzf() {
        return this.zzf.zzb + this.zzo;
    }

    public final zzjh zzg() {
        return this.zzl;
    }

    public final zzue zzh() {
        return this.zzm;
    }

    public final zzvx zzi() {
        return this.zzn;
    }

    public final zzvx zzj(float f, zzcn zzcnVar) throws zzgy {
        zzvq[] zzvqVarArr;
        zzvx zzn = this.zzj.zzn(this.zzi, this.zzm, this.zzf.zza, zzcnVar);
        for (zzvq zzvqVar : zzn.zzc) {
        }
        return zzn;
    }

    public final void zzk(long j) {
        zzdd.zzf(zzu());
        this.zza.zzo(j - this.zzo);
    }

    public final void zzl(float f, zzcn zzcnVar) throws zzgy {
        this.zzd = true;
        this.zzm = this.zza.zzh();
        zzvx zzj = zzj(f, zzcnVar);
        zzji zzjiVar = this.zzf;
        long j = zzjiVar.zzb;
        long j2 = zzjiVar.zze;
        if (j2 != C1856C.TIME_UNSET && j >= j2) {
            j = Math.max(0L, j2 - 1);
        }
        long zza = zza(zzj, j, false);
        long j3 = this.zzo;
        zzji zzjiVar2 = this.zzf;
        this.zzo = j3 + (zzjiVar2.zzb - zza);
        this.zzf = zzjiVar2.zzb(zza);
    }

    public final void zzm(long j) {
        zzdd.zzf(zzu());
        if (this.zzd) {
            this.zza.zzm(j - this.zzo);
        }
    }

    public final void zzn() {
        zzs();
        zzjr zzjrVar = this.zzk;
        zzse zzseVar = this.zza;
        try {
            if (zzseVar instanceof zzrl) {
                zzjrVar.zzh(((zzrl) zzseVar).zza);
            } else {
                zzjrVar.zzh(zzseVar);
            }
        } catch (RuntimeException e) {
            zzdu.zza("MediaPeriodHolder", "Period release failed.", e);
        }
    }

    public final void zzo(zzjh zzjhVar) {
        if (zzjhVar == this.zzl) {
            return;
        }
        zzs();
        this.zzl = zzjhVar;
        zzt();
    }

    public final void zzp(long j) {
        this.zzo = MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US;
    }

    public final void zzq() {
        zzse zzseVar = this.zza;
        if (zzseVar instanceof zzrl) {
            long j = this.zzf.zzd;
            if (j == C1856C.TIME_UNSET) {
                j = Long.MIN_VALUE;
            }
            ((zzrl) zzseVar).zzn(0L, j);
        }
    }

    public final boolean zzr() {
        return this.zzd && (!this.zze || this.zza.zzb() == Long.MIN_VALUE);
    }
}
