package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzji {
    public final zzsg zza;
    public final long zzb;
    public final long zzc;
    public final long zzd;
    public final long zze;
    public final boolean zzf;
    public final boolean zzg;
    public final boolean zzh;
    public final boolean zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzji(zzsg zzsgVar, long j, long j2, long j3, long j4, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5 = true;
        zzdd.zzd(!z4 || z2);
        if (z3 && !z2) {
            z5 = false;
        }
        zzdd.zzd(z5);
        this.zza = zzsgVar;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzf = false;
        this.zzg = z2;
        this.zzh = z3;
        this.zzi = z4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzji zzjiVar = (zzji) obj;
            if (this.zzb == zzjiVar.zzb && this.zzc == zzjiVar.zzc && this.zzd == zzjiVar.zzd && this.zze == zzjiVar.zze && this.zzg == zzjiVar.zzg && this.zzh == zzjiVar.zzh && this.zzi == zzjiVar.zzi && zzel.zzT(this.zza, zzjiVar.zza)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((this.zza.hashCode() + 527) * 31) + ((int) this.zzb)) * 31) + ((int) this.zzc)) * 31) + ((int) this.zzd)) * 31) + ((int) this.zze)) * 961) + (this.zzg ? 1 : 0)) * 31) + (this.zzh ? 1 : 0)) * 31) + (this.zzi ? 1 : 0);
    }

    public final zzji zza(long j) {
        return j == this.zzc ? this : new zzji(this.zza, this.zzb, j, this.zzd, this.zze, false, this.zzg, this.zzh, this.zzi);
    }

    public final zzji zzb(long j) {
        return j == this.zzb ? this : new zzji(this.zza, j, this.zzc, this.zzd, this.zze, false, this.zzg, this.zzh, this.zzi);
    }
}
