package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzsu implements zzse, zzsd {
    private final zzse zza;
    private final long zzb;
    private zzsd zzc;

    public zzsu(zzse zzseVar, long j) {
        this.zza = zzseVar;
        this.zzb = j;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zza(long j, zzkb zzkbVar) {
        return this.zza.zza(j - this.zzb, zzkbVar) + this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzb() {
        long zzb = this.zza.zzb();
        if (zzb == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return zzb + this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzc() {
        long zzc = this.zza.zzc();
        if (zzc == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return zzc + this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzd() {
        long zzd = this.zza.zzd();
        return zzd == C1856C.TIME_UNSET ? C1856C.TIME_UNSET : zzd + this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zze(long j) {
        return this.zza.zze(j - this.zzb) + this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzf(zzvq[] zzvqVarArr, boolean[] zArr, zztw[] zztwVarArr, boolean[] zArr2, long j) {
        zztw[] zztwVarArr2 = new zztw[zztwVarArr.length];
        int r3 = 0;
        while (true) {
            zztw zztwVar = null;
            if (r3 >= zztwVarArr.length) {
                break;
            }
            zzsv zzsvVar = (zzsv) zztwVarArr[r3];
            if (zzsvVar != null) {
                zztwVar = zzsvVar.zzc();
            }
            zztwVarArr2[r3] = zztwVar;
            r3++;
        }
        long zzf = this.zza.zzf(zzvqVarArr, zArr, zztwVarArr2, zArr2, j - this.zzb);
        for (int r10 = 0; r10 < zztwVarArr.length; r10++) {
            zztw zztwVar2 = zztwVarArr2[r10];
            if (zztwVar2 == null) {
                zztwVarArr[r10] = null;
            } else {
                zztw zztwVar3 = zztwVarArr[r10];
                if (zztwVar3 == null || ((zzsv) zztwVar3).zzc() != zztwVar2) {
                    zztwVarArr[r10] = new zzsv(zztwVar2, this.zzb);
                }
            }
        }
        return zzf + this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zztx
    public final /* bridge */ /* synthetic */ void zzg(zzty zztyVar) {
        zzse zzseVar = (zzse) zztyVar;
        zzsd zzsdVar = this.zzc;
        Objects.requireNonNull(zzsdVar);
        zzsdVar.zzg(this);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final zzue zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzj(long j, boolean z) {
        this.zza.zzj(j - this.zzb, false);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzk() throws IOException {
        this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzl(zzsd zzsdVar, long j) {
        this.zzc = zzsdVar;
        this.zza.zzl(this, j - this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final void zzm(long j) {
        this.zza.zzm(j - this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzo(long j) {
        return this.zza.zzo(j - this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzp() {
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.ads.zzsd
    public final void zzi(zzse zzseVar) {
        zzsd zzsdVar = this.zzc;
        Objects.requireNonNull(zzsdVar);
        zzsdVar.zzi(this);
    }
}
