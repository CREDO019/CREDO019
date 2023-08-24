package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbab {
    private int zza;
    private long[] zzb;

    public zzbab() {
        this(32);
    }

    public zzbab(int r1) {
        this.zzb = new long[32];
    }

    public final int zza() {
        return this.zza;
    }

    public final long zzb(int r5) {
        if (r5 < 0 || r5 >= this.zza) {
            int r1 = this.zza;
            throw new IndexOutOfBoundsException("Invalid index " + r5 + ", size is " + r1);
        }
        return this.zzb[r5];
    }

    public final void zzc(long j) {
        int r0 = this.zza;
        long[] jArr = this.zzb;
        if (r0 == jArr.length) {
            this.zzb = Arrays.copyOf(jArr, r0 + r0);
        }
        long[] jArr2 = this.zzb;
        int r1 = this.zza;
        this.zza = r1 + 1;
        jArr2[r1] = j;
    }
}
