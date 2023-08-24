package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzei {
    private long[] zza;
    private Object[] zzb;
    private int zzc;
    private int zzd;

    public zzei() {
        this(10);
    }

    public zzei(int r2) {
        this.zza = new long[10];
        this.zzb = new Object[10];
    }

    private final Object zzf() {
        zzdd.zzf(this.zzd > 0);
        Object[] objArr = this.zzb;
        int r2 = this.zzc;
        Object obj = objArr[r2];
        objArr[r2] = null;
        this.zzc = (r2 + 1) % objArr.length;
        this.zzd--;
        return obj;
    }

    public final synchronized int zza() {
        return this.zzd;
    }

    public final synchronized Object zzb() {
        if (this.zzd == 0) {
            return null;
        }
        return zzf();
    }

    public final synchronized Object zzc(long j) {
        Object obj;
        obj = null;
        while (this.zzd > 0 && j - this.zza[this.zzc] >= 0) {
            obj = zzf();
        }
        return obj;
    }

    public final synchronized void zzd(long j, Object obj) {
        int r0 = this.zzd;
        if (r0 > 0) {
            if (j <= this.zza[((this.zzc + r0) - 1) % this.zzb.length]) {
                zze();
            }
        }
        int length = this.zzb.length;
        if (this.zzd >= length) {
            int r1 = length + length;
            long[] jArr = new long[r1];
            Object[] objArr = new Object[r1];
            int r3 = this.zzc;
            int r02 = length - r3;
            System.arraycopy(this.zza, r3, jArr, 0, r02);
            System.arraycopy(this.zzb, this.zzc, objArr, 0, r02);
            int r32 = this.zzc;
            if (r32 > 0) {
                System.arraycopy(this.zza, 0, jArr, r02, r32);
                System.arraycopy(this.zzb, 0, objArr, r02, this.zzc);
            }
            this.zza = jArr;
            this.zzb = objArr;
            this.zzc = 0;
        }
        int r03 = this.zzc;
        int r12 = this.zzd;
        Object[] objArr2 = this.zzb;
        int length2 = (r03 + r12) % objArr2.length;
        this.zza[length2] = j;
        objArr2[length2] = obj;
        this.zzd = r12 + 1;
    }

    public final synchronized void zze() {
        this.zzc = 0;
        this.zzd = 0;
        Arrays.fill(this.zzb, (Object) null);
    }
}
