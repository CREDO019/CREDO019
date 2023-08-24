package com.google.android.gms.ads.internal.util;

import com.google.android.gms.common.internal.Objects;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbc {
    public final String zza;
    public final double zzb;
    public final double zzc;
    public final double zzd;
    public final int zze;

    public zzbc(String str, double d, double d2, double d3, int r8) {
        this.zza = str;
        this.zzc = d;
        this.zzb = d2;
        this.zzd = d3;
        this.zze = r8;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzbc) {
            zzbc zzbcVar = (zzbc) obj;
            return Objects.equal(this.zza, zzbcVar.zza) && this.zzb == zzbcVar.zzb && this.zzc == zzbcVar.zzc && this.zze == zzbcVar.zze && Double.compare(this.zzd, zzbcVar.zzd) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, Double.valueOf(this.zzb), Double.valueOf(this.zzc), Double.valueOf(this.zzd), Integer.valueOf(this.zze));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.zza).add("minBound", Double.valueOf(this.zzc)).add("maxBound", Double.valueOf(this.zzb)).add("percent", Double.valueOf(this.zzd)).add(NewHtcHomeBadger.COUNT, Integer.valueOf(this.zze)).toString();
    }
}
