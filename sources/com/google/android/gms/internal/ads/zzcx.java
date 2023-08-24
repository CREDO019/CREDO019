package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcx {
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzcw
    };
    public final int zzb;
    private final zzcp zzc;
    private final int[] zzd;
    private final boolean[] zze;

    public zzcx(zzcp zzcpVar, boolean z, int[] r3, boolean[] zArr) {
        int r2 = zzcpVar.zzb;
        this.zzb = 1;
        this.zzc = zzcpVar;
        this.zzd = (int[]) r3.clone();
        this.zze = (boolean[]) zArr.clone();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzcx zzcxVar = (zzcx) obj;
            if (this.zzc.equals(zzcxVar.zzc) && Arrays.equals(this.zzd, zzcxVar.zzd) && Arrays.equals(this.zze, zzcxVar.zze)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((this.zzc.hashCode() * 961) + Arrays.hashCode(this.zzd)) * 31) + Arrays.hashCode(this.zze);
    }

    public final int zza() {
        return this.zzc.zzd;
    }

    public final zzaf zzb(int r2) {
        return this.zzc.zzb(r2);
    }

    public final boolean zzc() {
        for (boolean z : this.zze) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzd(int r2) {
        return this.zze[r2];
    }
}
