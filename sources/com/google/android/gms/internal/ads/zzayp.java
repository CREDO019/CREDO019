package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzayp {
    public static final zzayp zza = new zzayp(new zzayo[0]);
    public final int zzb;
    private final zzayo[] zzc;
    private int zzd;

    public zzayp(zzayo... zzayoVarArr) {
        this.zzc = zzayoVarArr;
        this.zzb = zzayoVarArr.length;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzayp zzaypVar = (zzayp) obj;
            if (this.zzb == zzaypVar.zzb && Arrays.equals(this.zzc, zzaypVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzd;
        if (r0 == 0) {
            int hashCode = Arrays.hashCode(this.zzc);
            this.zzd = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final int zza(zzayo zzayoVar) {
        for (int r0 = 0; r0 < this.zzb; r0++) {
            if (this.zzc[r0] == zzayoVar) {
                return r0;
            }
        }
        return -1;
    }

    public final zzayo zzb(int r2) {
        return this.zzc[r2];
    }
}
