package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzry implements zzse, zzsd {
    public final zzsg zza;
    private final long zzb;
    private zzsi zzc;
    private zzse zzd;
    private zzsd zze;
    private long zzf = C1856C.TIME_UNSET;
    private final zzwf zzg;

    public zzry(zzsg zzsgVar, zzwf zzwfVar, long j, byte[] bArr) {
        this.zza = zzsgVar;
        this.zzg = zzwfVar;
        this.zzb = j;
    }

    private final long zzv(long j) {
        long j2 = this.zzf;
        return j2 != C1856C.TIME_UNSET ? j2 : j;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zza(long j, zzkb zzkbVar) {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zza(j, zzkbVar);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzb() {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzc() {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzd() {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zze(long j) {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zze(j);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzf(zzvq[] zzvqVarArr, boolean[] zArr, zztw[] zztwVarArr, boolean[] zArr2, long j) {
        long j2;
        long j3 = this.zzf;
        if (j3 == C1856C.TIME_UNSET || j != this.zzb) {
            j2 = j;
        } else {
            this.zzf = C1856C.TIME_UNSET;
            j2 = j3;
        }
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zzf(zzvqVarArr, zArr, zztwVarArr, zArr2, j2);
    }

    @Override // com.google.android.gms.internal.ads.zztx
    public final /* bridge */ /* synthetic */ void zzg(zzty zztyVar) {
        zzse zzseVar = (zzse) zztyVar;
        zzsd zzsdVar = this.zze;
        int r0 = zzel.zza;
        zzsdVar.zzg(this);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final zzue zzh() {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        return zzseVar.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzsd
    public final void zzi(zzse zzseVar) {
        zzsd zzsdVar = this.zze;
        int r0 = zzel.zza;
        zzsdVar.zzi(this);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzj(long j, boolean z) {
        zzse zzseVar = this.zzd;
        int r0 = zzel.zza;
        zzseVar.zzj(j, false);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzk() throws IOException {
        try {
            zzse zzseVar = this.zzd;
            if (zzseVar != null) {
                zzseVar.zzk();
                return;
            }
            zzsi zzsiVar = this.zzc;
            if (zzsiVar != null) {
                zzsiVar.zzw();
            }
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzl(zzsd zzsdVar, long j) {
        this.zze = zzsdVar;
        zzse zzseVar = this.zzd;
        if (zzseVar != null) {
            zzseVar.zzl(this, zzv(this.zzb));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final void zzm(long j) {
        zzse zzseVar = this.zzd;
        int r1 = zzel.zza;
        zzseVar.zzm(j);
    }

    public final long zzn() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzo(long j) {
        zzse zzseVar = this.zzd;
        return zzseVar != null && zzseVar.zzo(j);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzp() {
        zzse zzseVar = this.zzd;
        return zzseVar != null && zzseVar.zzp();
    }

    public final long zzq() {
        return this.zzb;
    }

    public final void zzr(zzsg zzsgVar) {
        long zzv = zzv(this.zzb);
        zzsi zzsiVar = this.zzc;
        Objects.requireNonNull(zzsiVar);
        zzse zzD = zzsiVar.zzD(zzsgVar, this.zzg, zzv);
        this.zzd = zzD;
        if (this.zze != null) {
            zzD.zzl(this, zzv);
        }
    }

    public final void zzs(long j) {
        this.zzf = j;
    }

    public final void zzu(zzsi zzsiVar) {
        zzdd.zzf(this.zzc == null);
        this.zzc = zzsiVar;
    }

    public final void zzt() {
        zzse zzseVar = this.zzd;
        if (zzseVar != null) {
            zzsi zzsiVar = this.zzc;
            Objects.requireNonNull(zzsiVar);
            zzsiVar.zzB(zzseVar);
        }
    }
}
