package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaaj {
    public static final zzaaj zza = new zzaaj(0, 0);
    public final long zzb;
    public final long zzc;

    public zzaaj(long j, long j2) {
        this.zzb = j;
        this.zzc = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaaj zzaajVar = (zzaaj) obj;
            if (this.zzb == zzaajVar.zzb && this.zzc == zzaajVar.zzc) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((int) this.zzb) * 31) + ((int) this.zzc);
    }

    public final String toString() {
        long j = this.zzb;
        long j2 = this.zzc;
        return "[timeUs=" + j + ", position=" + j2 + "]";
    }
}
