package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzkn {
    public final long zza;
    public final zzcn zzb;
    public final int zzc;
    public final zzsg zzd;
    public final long zze;
    public final zzcn zzf;
    public final int zzg;
    public final zzsg zzh;
    public final long zzi;
    public final long zzj;

    public zzkn(long j, zzcn zzcnVar, int r4, zzsg zzsgVar, long j2, zzcn zzcnVar2, int r9, zzsg zzsgVar2, long j3, long j4) {
        this.zza = j;
        this.zzb = zzcnVar;
        this.zzc = r4;
        this.zzd = zzsgVar;
        this.zze = j2;
        this.zzf = zzcnVar2;
        this.zzg = r9;
        this.zzh = zzsgVar2;
        this.zzi = j3;
        this.zzj = j4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzkn zzknVar = (zzkn) obj;
            if (this.zza == zzknVar.zza && this.zzc == zzknVar.zzc && this.zze == zzknVar.zze && this.zzg == zzknVar.zzg && this.zzi == zzknVar.zzi && this.zzj == zzknVar.zzj && zzfsa.zza(this.zzb, zzknVar.zzb) && zzfsa.zza(this.zzd, zzknVar.zzd) && zzfsa.zza(this.zzf, zzknVar.zzf) && zzfsa.zza(this.zzh, zzknVar.zzh)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zza), this.zzb, Integer.valueOf(this.zzc), this.zzd, Long.valueOf(this.zze), this.zzf, Integer.valueOf(this.zzg), this.zzh, Long.valueOf(this.zzi), Long.valueOf(this.zzj)});
    }
}
