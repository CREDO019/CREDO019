package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzt {
    public static final zzt zza = new zzt(0, 0, 0);
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzs
    };
    public final int zzc = 0;
    public final int zzd;
    public final int zze;

    public zzt(int r1, int r2, int r3) {
        this.zzd = r2;
        this.zze = r3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzt) {
            zzt zztVar = (zzt) obj;
            int r1 = zztVar.zzc;
            return this.zzd == zztVar.zzd && this.zze == zztVar.zze;
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzd + 16337) * 31) + this.zze;
    }
}
