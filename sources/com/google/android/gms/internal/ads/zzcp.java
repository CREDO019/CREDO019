package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcp {
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzco
    };
    public final int zzb = 1;
    public final String zzc;
    public final int zzd;
    private final zzaf[] zze;
    private int zzf;

    public zzcp(String str, zzaf... zzafVarArr) {
        this.zzc = str;
        this.zze = zzafVarArr;
        int zzb = zzbt.zzb(zzafVarArr[0].zzm);
        this.zzd = zzb == -1 ? zzbt.zzb(zzafVarArr[0].zzl) : zzb;
        zzd(zzafVarArr[0].zzd);
        int r3 = zzafVarArr[0].zzf;
    }

    private static String zzd(String str) {
        return (str == null || str.equals(C1856C.LANGUAGE_UNDETERMINED)) ? "" : str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzcp zzcpVar = (zzcp) obj;
            if (this.zzc.equals(zzcpVar.zzc) && Arrays.equals(this.zze, zzcpVar.zze)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzf;
        if (r0 == 0) {
            int hashCode = ((this.zzc.hashCode() + 527) * 31) + Arrays.hashCode(this.zze);
            this.zzf = hashCode;
            return hashCode;
        }
        return r0;
    }

    public final int zza(zzaf zzafVar) {
        for (int r0 = 0; r0 <= 0; r0++) {
            if (zzafVar == this.zze[r0]) {
                return r0;
            }
        }
        return -1;
    }

    public final zzaf zzb(int r2) {
        return this.zze[r2];
    }

    public final zzcp zzc(String str) {
        return new zzcp(str, this.zze);
    }
}
