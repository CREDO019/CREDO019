package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzue {
    public static final zzue zza = new zzue(new zzcp[0]);
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzud
    };
    public final int zzc;
    private final zzfuv zzd;
    private int zze;

    public zzue(zzcp... zzcpVarArr) {
        this.zzd = zzfuv.zzn(zzcpVarArr);
        this.zzc = zzcpVarArr.length;
        int r6 = 0;
        while (r6 < this.zzd.size()) {
            int r0 = r6 + 1;
            for (int r1 = r0; r1 < this.zzd.size(); r1++) {
                if (((zzcp) this.zzd.get(r6)).equals(this.zzd.get(r1))) {
                    zzdu.zza("TrackGroupArray", "", new IllegalArgumentException("Multiple identical TrackGroups added to one TrackGroupArray."));
                }
            }
            r6 = r0;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzue zzueVar = (zzue) obj;
            if (this.zzc == zzueVar.zzc && this.zzd.equals(zzueVar.zzd)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zze;
        if (r0 == 0) {
            int hashCode = this.zzd.hashCode();
            this.zze = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final int zza(zzcp zzcpVar) {
        int indexOf = this.zzd.indexOf(zzcpVar);
        if (indexOf >= 0) {
            return indexOf;
        }
        return -1;
    }

    public final zzcp zzb(int r2) {
        return (zzcp) this.zzd.get(r2);
    }
}
