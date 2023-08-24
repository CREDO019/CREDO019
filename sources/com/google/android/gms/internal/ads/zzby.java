package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzby {
    public static final zzby zza = new zzby(1.0f, 1.0f);
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzbx
    };
    public final float zzc;
    public final float zzd;
    private final int zze;

    public zzby(float f, float f2) {
        zzdd.zzd(f > 0.0f);
        zzdd.zzd(f2 > 0.0f);
        this.zzc = f;
        this.zzd = f2;
        this.zze = Math.round(f * 1000.0f);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzby zzbyVar = (zzby) obj;
            if (this.zzc == zzbyVar.zzc && this.zzd == zzbyVar.zzd) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((Float.floatToRawIntBits(this.zzc) + 527) * 31) + Float.floatToRawIntBits(this.zzd);
    }

    public final String toString() {
        return zzel.zzI("PlaybackParameters(speed=%.2f, pitch=%.2f)", Float.valueOf(this.zzc), Float.valueOf(this.zzd));
    }

    public final long zza(long j) {
        return j * this.zze;
    }
}
