package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafv {
    public final zzafs zza;
    public final int zzb;
    public final long[] zzc;
    public final int[] zzd;
    public final int zze;
    public final long[] zzf;
    public final int[] zzg;
    public final long zzh;

    public zzafv(zzafs zzafsVar, long[] jArr, int[] r8, int r9, long[] jArr2, int[] r11, long j) {
        int length = r8.length;
        int length2 = jArr2.length;
        zzdd.zzd(length == length2);
        int length3 = jArr.length;
        zzdd.zzd(length3 == length2);
        int length4 = r11.length;
        zzdd.zzd(length4 == length2);
        this.zza = zzafsVar;
        this.zzc = jArr;
        this.zzd = r8;
        this.zze = r9;
        this.zzf = jArr2;
        this.zzg = r11;
        this.zzh = j;
        this.zzb = length3;
        if (length4 > 0) {
            int r4 = length4 - 1;
            r11[r4] = r11[r4] | 536870912;
        }
    }

    public final int zza(long j) {
        for (int zzd = zzel.zzd(this.zzf, j, true, false); zzd >= 0; zzd--) {
            if ((this.zzg[zzd] & 1) != 0) {
                return zzd;
            }
        }
        return -1;
    }

    public final int zzb(long j) {
        for (int zzb = zzel.zzb(this.zzf, j, true, false); zzb < this.zzf.length; zzb++) {
            if ((this.zzg[zzb] & 1) != 0) {
                return zzb;
            }
        }
        return -1;
    }
}
