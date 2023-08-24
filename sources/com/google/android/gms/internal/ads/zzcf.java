package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcf {
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzce
    };
    public final Object zzb;
    public final int zzc;
    public final zzbg zzd;
    public final Object zze;
    public final int zzf;
    public final long zzg;
    public final long zzh;
    public final int zzi;
    public final int zzj;

    public zzcf(Object obj, int r2, zzbg zzbgVar, Object obj2, int r5, long j, long j2, int r10, int r11) {
        this.zzb = obj;
        this.zzc = r2;
        this.zzd = zzbgVar;
        this.zze = obj2;
        this.zzf = r5;
        this.zzg = j;
        this.zzh = j2;
        this.zzi = r10;
        this.zzj = r11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzcf zzcfVar = (zzcf) obj;
            if (this.zzc == zzcfVar.zzc && this.zzf == zzcfVar.zzf && this.zzg == zzcfVar.zzg && this.zzh == zzcfVar.zzh && this.zzi == zzcfVar.zzi && this.zzj == zzcfVar.zzj && zzfsa.zza(this.zzb, zzcfVar.zzb) && zzfsa.zza(this.zze, zzcfVar.zze) && zzfsa.zza(this.zzd, zzcfVar.zzd)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzb, Integer.valueOf(this.zzc), this.zzd, this.zze, Integer.valueOf(this.zzf), Long.valueOf(this.zzg), Long.valueOf(this.zzh), Integer.valueOf(this.zzi), Integer.valueOf(this.zzj)});
    }
}
