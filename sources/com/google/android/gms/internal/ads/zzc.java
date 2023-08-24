package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.exoplayer2.C1856C;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzc {
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzb
    };
    public final long zzb;
    public final int zzc;
    public final Uri[] zzd;
    public final int[] zze;
    public final long[] zzf;
    public final long zzg;
    public final boolean zzh;

    public zzc(long j) {
        this(0L, -1, new int[0], new Uri[0], new long[0], 0L, false);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzc zzcVar = (zzc) obj;
            if (this.zzc == zzcVar.zzc && Arrays.equals(this.zzd, zzcVar.zzd) && Arrays.equals(this.zze, zzcVar.zze) && Arrays.equals(this.zzf, zzcVar.zzf)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.zzc * 961) + Arrays.hashCode(this.zzd)) * 31) + Arrays.hashCode(this.zze)) * 31) + Arrays.hashCode(this.zzf)) * 961;
    }

    public final int zza(int r4) {
        int r1;
        int r42 = r4 + 1;
        while (true) {
            int[] r12 = this.zze;
            if (r42 >= r12.length || (r1 = r12[r42]) == 0 || r1 == 1) {
                break;
            }
            r42++;
        }
        return r42;
    }

    public final zzc zzb(int r14) {
        int[] r142 = this.zze;
        int length = r142.length;
        int max = Math.max(0, length);
        int[] copyOf = Arrays.copyOf(r142, max);
        Arrays.fill(copyOf, length, max, 0);
        long[] jArr = this.zzf;
        int length2 = jArr.length;
        int max2 = Math.max(0, length2);
        long[] copyOf2 = Arrays.copyOf(jArr, max2);
        Arrays.fill(copyOf2, length2, max2, (long) C1856C.TIME_UNSET);
        return new zzc(0L, 0, copyOf, (Uri[]) Arrays.copyOf(this.zzd, 0), copyOf2, 0L, false);
    }

    private zzc(long j, int r3, int[] r4, Uri[] uriArr, long[] jArr, long j2, boolean z) {
        zzdd.zzd(r4.length == uriArr.length);
        this.zzb = 0L;
        this.zzc = r3;
        this.zze = r4;
        this.zzd = uriArr;
        this.zzf = jArr;
        this.zzg = 0L;
        this.zzh = false;
    }
}
