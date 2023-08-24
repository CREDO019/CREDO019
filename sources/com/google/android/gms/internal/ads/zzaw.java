package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaw {
    public static final zzaw zza = new zzaw(new zzau());
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzat
    };
    public final long zzc;
    public final long zzd;
    public final long zze;
    public final float zzf;
    public final float zzg;

    private zzaw(zzau zzauVar) {
        this.zzc = C1856C.TIME_UNSET;
        this.zzd = C1856C.TIME_UNSET;
        this.zze = C1856C.TIME_UNSET;
        this.zzf = -3.4028235E38f;
        this.zzg = -3.4028235E38f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzaw) {
            zzaw zzawVar = (zzaw) obj;
            long j = zzawVar.zzc;
            long j2 = zzawVar.zzd;
            long j3 = zzawVar.zze;
            float f = zzawVar.zzf;
            float f2 = zzawVar.zzg;
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int r1 = (int) (-9223372034707292159L);
        return (((((((r1 * 31) + r1) * 31) + r1) * 31) + Float.floatToIntBits(-3.4028235E38f)) * 31) + Float.floatToIntBits(-3.4028235E38f);
    }
}
