package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzayo {
    public final int zza = 1;
    private final zzass[] zzb;
    private int zzc;

    public zzayo(zzass... zzassVarArr) {
        this.zzb = zzassVarArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.equals(this.zzb, ((zzayo) obj).zzb);
    }

    public final int hashCode() {
        int r0 = this.zzc;
        if (r0 == 0) {
            int hashCode = Arrays.hashCode(this.zzb) + 527;
            this.zzc = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final int zza(zzass zzassVar) {
        for (int r0 = 0; r0 <= 0; r0++) {
            if (zzassVar == this.zzb[r0]) {
                return r0;
            }
        }
        return -1;
    }

    public final zzass zzb(int r2) {
        return this.zzb[r2];
    }
}
