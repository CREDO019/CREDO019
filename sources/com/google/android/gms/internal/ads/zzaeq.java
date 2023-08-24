package com.google.android.gms.internal.ads;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaeq implements zzaep {
    private final long[] zza;
    private final long[] zzb;
    private final long zzc;
    private final long zzd;

    private zzaeq(long[] jArr, long[] jArr2, long j, long j2) {
        this.zza = jArr;
        this.zzb = jArr2;
        this.zzc = j;
        this.zzd = j2;
    }

    public static zzaeq zza(long j, long j2, zzzy zzzyVar, zzed zzedVar) {
        int zzk;
        zzedVar.zzG(10);
        int zze = zzedVar.zze();
        if (zze <= 0) {
            return null;
        }
        int r6 = zzzyVar.zzd;
        long zzw = zzel.zzw(zze, (r6 >= 32000 ? 1152 : 576) * 1000000, r6);
        int zzo = zzedVar.zzo();
        int zzo2 = zzedVar.zzo();
        int zzo3 = zzedVar.zzo();
        zzedVar.zzG(2);
        long j3 = j2 + zzzyVar.zzc;
        long[] jArr = new long[zzo];
        long[] jArr2 = new long[zzo];
        int r2 = 0;
        long j4 = j2;
        while (r2 < zzo) {
            int r18 = zzo2;
            long j5 = j3;
            jArr[r2] = (r2 * zzw) / zzo;
            jArr2[r2] = Math.max(j4, j5);
            if (zzo3 == 1) {
                zzk = zzedVar.zzk();
            } else if (zzo3 == 2) {
                zzk = zzedVar.zzo();
            } else if (zzo3 == 3) {
                zzk = zzedVar.zzm();
            } else if (zzo3 != 4) {
                return null;
            } else {
                zzk = zzedVar.zzn();
            }
            j4 += zzk * r18;
            r2++;
            jArr = jArr;
            zzo2 = r18;
            j3 = j5;
        }
        long[] jArr3 = jArr;
        if (j != -1 && j != j4) {
            Log.w("VbriSeeker", "VBRI data size mismatch: " + j + ", " + j4);
        }
        return new zzaeq(jArr3, jArr2, zzw, j4);
    }

    @Override // com.google.android.gms.internal.ads.zzaep
    public final long zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzaep
    public final long zzc(long j) {
        return this.zza[zzel.zzd(this.zzb, j, true, true)];
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        int zzd = zzel.zzd(this.zza, j, true, true);
        zzaaj zzaajVar = new zzaaj(this.zza[zzd], this.zzb[zzd]);
        if (zzaajVar.zzb < j) {
            long[] jArr = this.zza;
            if (zzd != jArr.length - 1) {
                int r0 = zzd + 1;
                return new zzaag(zzaajVar, new zzaaj(jArr[r0], this.zzb[r0]));
            }
        }
        return new zzaag(zzaajVar, zzaajVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
