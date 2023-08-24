package com.google.android.gms.internal.ads;

import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzck {
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzcj
    };
    public Object zzb;
    public Object zzc;
    public int zzd;
    public long zze;
    public long zzf;
    public boolean zzg;
    private zzd zzh = zzd.zza;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass().equals(obj.getClass())) {
            zzck zzckVar = (zzck) obj;
            if (zzel.zzT(this.zzb, zzckVar.zzb) && zzel.zzT(this.zzc, zzckVar.zzc) && this.zzd == zzckVar.zzd && this.zze == zzckVar.zze && this.zzg == zzckVar.zzg && zzel.zzT(this.zzh, zzckVar.zzh)) {
                return true;
            }
        }
        return false;
    }

    public final int zza(int r2) {
        return this.zzh.zza(r2).zzc;
    }

    public final int zzb() {
        int r0 = this.zzh.zzc;
        return 0;
    }

    public final int zzc(long j) {
        return -1;
    }

    public final int zzd(long j) {
        return -1;
    }

    public final int zze(int r2) {
        return this.zzh.zza(r2).zza(-1);
    }

    public final int zzf(int r2, int r3) {
        return this.zzh.zza(r2).zza(r3);
    }

    public final long zzg(int r3, int r4) {
        zzc zza2 = this.zzh.zza(r3);
        return zza2.zzc != -1 ? zza2.zzf[r4] : C1856C.TIME_UNSET;
    }

    public final long zzh(int r3) {
        long j = this.zzh.zza(r3).zzb;
        return 0L;
    }

    public final long zzi() {
        long j = this.zzh.zzd;
        return 0L;
    }

    public final long zzj(int r3) {
        long j = this.zzh.zza(r3).zzg;
        return 0L;
    }

    public final zzck zzk(Object obj, Object obj2, int r3, long j, long j2, zzd zzdVar, boolean z) {
        this.zzb = obj;
        this.zzc = obj2;
        this.zzd = 0;
        this.zze = j;
        this.zzf = 0L;
        this.zzh = zzdVar;
        this.zzg = z;
        return this;
    }

    public final boolean zzl(int r2) {
        boolean z = this.zzh.zza(r2).zzh;
        return false;
    }

    public final int hashCode() {
        Object obj = this.zzb;
        int hashCode = ((obj == null ? 0 : obj.hashCode()) + JfifUtil.MARKER_EOI) * 31;
        Object obj2 = this.zzc;
        int hashCode2 = obj2 != null ? obj2.hashCode() : 0;
        int r2 = this.zzd;
        long j = this.zze;
        return ((((((((hashCode + hashCode2) * 31) + r2) * 31) + ((int) ((j >>> 32) ^ j))) * 961) + (this.zzg ? 1 : 0)) * 31) + this.zzh.hashCode();
    }
}
