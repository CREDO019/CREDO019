package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaag {
    public final zzaaj zza;
    public final zzaaj zzb;

    public zzaag(zzaaj zzaajVar, zzaaj zzaajVar2) {
        this.zza = zzaajVar;
        this.zzb = zzaajVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaag zzaagVar = (zzaag) obj;
            if (this.zza.equals(zzaagVar.zza) && this.zzb.equals(zzaagVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.zza.hashCode() * 31) + this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String concat = this.zza.equals(this.zzb) ? "" : ", ".concat(this.zzb.toString());
        return "[" + obj + concat + "]";
    }
}
