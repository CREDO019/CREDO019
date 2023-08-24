package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzawn {
    public final int zza;
    public final long[] zzb;
    public final int[] zzc;
    public final int zzd;
    public final long[] zze;
    public final int[] zzf;

    public zzawn(long[] jArr, int[] r7, int r8, long[] jArr2, int[] r10) {
        int length = jArr2.length;
        zzazy.zzc(r7.length == length);
        int length2 = jArr.length;
        zzazy.zzc(length2 == length);
        zzazy.zzc(r10.length == length);
        this.zzb = jArr;
        this.zzc = r7;
        this.zzd = r8;
        this.zze = jArr2;
        this.zzf = r10;
        this.zza = length2;
    }

    public final int zza(long j) {
        for (int zzc = zzban.zzc(this.zze, j, true, false); zzc >= 0; zzc--) {
            if ((this.zzf[zzc] & 1) != 0) {
                return zzc;
            }
        }
        return -1;
    }

    public final int zzb(long j) {
        for (int zzb = zzban.zzb(this.zze, j, true, false); zzb < this.zze.length; zzb++) {
            if ((this.zzf[zzb] & 1) != 0) {
                return zzb;
            }
        }
        return -1;
    }
}
