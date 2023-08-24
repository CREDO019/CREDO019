package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzazb {
    private int zza;
    private final zzayt[] zzb;

    public zzazb(zzayt[] zzaytVarArr, byte... bArr) {
        this.zzb = zzaytVarArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzb, ((zzazb) obj).zzb);
    }

    public final int hashCode() {
        int r0 = this.zza;
        if (r0 == 0) {
            int hashCode = Arrays.hashCode(this.zzb) + 527;
            this.zza = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final zzayt zza(int r2) {
        return this.zzb[r2];
    }

    public final zzayt[] zzb() {
        return (zzayt[]) this.zzb.clone();
    }
}
