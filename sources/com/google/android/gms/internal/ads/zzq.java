package com.google.android.gms.internal.ads;

import java.util.Arrays;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzq {
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzp
    };
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final byte[] zze;
    private int zzf;

    public zzq(int r1, int r2, int r3, byte[] bArr) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = bArr;
    }

    @Pure
    public static int zza(int r2) {
        if (r2 != 1) {
            if (r2 != 9) {
                return (r2 == 4 || r2 == 5 || r2 == 6 || r2 == 7) ? 2 : -1;
            }
            return 6;
        }
        return 1;
    }

    @Pure
    public static int zzb(int r3) {
        if (r3 != 1) {
            if (r3 != 16) {
                if (r3 != 18) {
                    return (r3 == 6 || r3 == 7) ? 3 : -1;
                }
                return 7;
            }
            return 6;
        }
        return 3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzq zzqVar = (zzq) obj;
            if (this.zzb == zzqVar.zzb && this.zzc == zzqVar.zzc && this.zzd == zzqVar.zzd && Arrays.equals(this.zze, zzqVar.zze)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzf;
        if (r0 == 0) {
            int hashCode = ((((((this.zzb + 527) * 31) + this.zzc) * 31) + this.zzd) * 31) + Arrays.hashCode(this.zze);
            this.zzf = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final String toString() {
        int r0 = this.zzb;
        int r1 = this.zzc;
        int r2 = this.zzd;
        boolean z = this.zze != null;
        return "ColorInfo(" + r0 + ", " + r1 + ", " + r2 + ", " + z + ")";
    }
}
