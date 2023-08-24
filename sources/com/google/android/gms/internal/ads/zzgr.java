package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgr {
    public final String zza;
    public final zzaf zzb;
    public final zzaf zzc;
    public final int zzd;
    public final int zze;

    public zzgr(String str, zzaf zzafVar, zzaf zzafVar2, int r6, int r7) {
        boolean z = false;
        if (r6 != 0) {
            r7 = r7 == 0 ? 0 : r7;
            zzdd.zzd(z);
            zzdd.zzc(str);
            this.zza = str;
            Objects.requireNonNull(zzafVar);
            this.zzb = zzafVar;
            Objects.requireNonNull(zzafVar2);
            this.zzc = zzafVar2;
            this.zzd = r6;
            this.zze = r7;
        }
        z = true;
        zzdd.zzd(z);
        zzdd.zzc(str);
        this.zza = str;
        Objects.requireNonNull(zzafVar);
        this.zzb = zzafVar;
        Objects.requireNonNull(zzafVar2);
        this.zzc = zzafVar2;
        this.zzd = r6;
        this.zze = r7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzgr zzgrVar = (zzgr) obj;
            if (this.zzd == zzgrVar.zzd && this.zze == zzgrVar.zze && this.zza.equals(zzgrVar.zza) && this.zzb.equals(zzgrVar.zzb) && this.zzc.equals(zzgrVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((this.zzd + 527) * 31) + this.zze) * 31) + this.zza.hashCode()) * 31) + this.zzb.hashCode()) * 31) + this.zzc.hashCode();
    }
}
