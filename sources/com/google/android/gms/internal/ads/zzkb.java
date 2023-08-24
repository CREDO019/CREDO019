package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzkb {
    public static final zzkb zza;
    public static final zzkb zzb;
    public static final zzkb zzc;
    public static final zzkb zzd;
    public static final zzkb zze;
    public final long zzf;
    public final long zzg;

    static {
        zzkb zzkbVar = new zzkb(0L, 0L);
        zza = zzkbVar;
        zzb = new zzkb(Long.MAX_VALUE, Long.MAX_VALUE);
        zzc = new zzkb(Long.MAX_VALUE, 0L);
        zzd = new zzkb(0L, Long.MAX_VALUE);
        zze = zzkbVar;
    }

    public zzkb(long j, long j2) {
        zzdd.zzd(j >= 0);
        zzdd.zzd(j2 >= 0);
        this.zzf = j;
        this.zzg = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzkb zzkbVar = (zzkb) obj;
            if (this.zzf == zzkbVar.zzf && this.zzg == zzkbVar.zzg) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((int) this.zzf) * 31) + ((int) this.zzg);
    }
}
