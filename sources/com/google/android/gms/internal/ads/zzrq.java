package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzrq implements zzty {
    protected final zzty[] zza;

    public zzrq(zzty[] zztyVarArr) {
        this.zza = zztyVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzty
    public final long zzb() {
        long j = Long.MAX_VALUE;
        for (zzty zztyVar : this.zza) {
            long zzb = zztyVar.zzb();
            if (zzb != Long.MIN_VALUE) {
                j = Math.min(j, zzb);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzty
    public final long zzc() {
        long j = Long.MAX_VALUE;
        for (zzty zztyVar : this.zza) {
            long zzc = zztyVar.zzc();
            if (zzc != Long.MIN_VALUE) {
                j = Math.min(j, zzc);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzty
    public final void zzm(long j) {
        for (zzty zztyVar : this.zza) {
            zztyVar.zzm(j);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzty
    public final boolean zzo(long j) {
        zzty[] zztyVarArr;
        boolean z;
        boolean z2 = false;
        do {
            long zzc = zzc();
            if (zzc == Long.MIN_VALUE) {
                break;
            }
            z = false;
            for (zzty zztyVar : this.zza) {
                long zzc2 = zztyVar.zzc();
                boolean z3 = zzc2 != Long.MIN_VALUE && zzc2 <= j;
                if (zzc2 == zzc || z3) {
                    z |= zztyVar.zzo(j);
                }
            }
            z2 |= z;
        } while (z);
        return z2;
    }

    @Override // com.google.android.gms.internal.ads.zzty
    public final boolean zzp() {
        for (zzty zztyVar : this.zza) {
            if (zztyVar.zzp()) {
                return true;
            }
        }
        return false;
    }
}
