package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzayt {
    protected final zzayo zza;
    protected final int[] zzb;
    private final zzass[] zzc;
    private int zzd;

    public zzayt(zzayo zzayoVar, int... r7) {
        Objects.requireNonNull(zzayoVar);
        this.zza = zzayoVar;
        this.zzc = new zzass[1];
        for (int r2 = 0; r2 <= 0; r2++) {
            this.zzc[r2] = zzayoVar.zzb(r7[r2]);
        }
        Arrays.sort(this.zzc, new zzays(null));
        this.zzb = new int[1];
        for (int r1 = 0; r1 <= 0; r1++) {
            this.zzb[r1] = zzayoVar.zza(this.zzc[r1]);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzayt zzaytVar = (zzayt) obj;
            if (this.zza == zzaytVar.zza && Arrays.equals(this.zzb, zzaytVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzd;
        if (r0 == 0) {
            int identityHashCode = (System.identityHashCode(this.zza) * 31) + Arrays.hashCode(this.zzb);
            this.zzd = identityHashCode;
            return identityHashCode;
        }
        return r0;
    }

    public final int zza(int r2) {
        return this.zzb[0];
    }

    public final int zzb() {
        int length = this.zzb.length;
        return 1;
    }

    public final zzass zzc(int r2) {
        return this.zzc[r2];
    }

    public final zzayo zzd() {
        return this.zza;
    }
}
