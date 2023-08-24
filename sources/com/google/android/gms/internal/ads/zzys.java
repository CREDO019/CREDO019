package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzys {
    public static void zza(long j, zzed zzedVar, zzaam[] zzaamVarArr) {
        int r6;
        while (true) {
            if (zzedVar.zza() <= 1) {
                return;
            }
            int zzc = zzc(zzedVar);
            int zzc2 = zzc(zzedVar);
            int zzc3 = zzedVar.zzc() + zzc2;
            if (zzc2 == -1 || zzc2 > zzedVar.zza()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                zzc3 = zzedVar.zzd();
            } else if (zzc == 4 && zzc2 >= 8) {
                int zzk = zzedVar.zzk();
                int zzo = zzedVar.zzo();
                if (zzo == 49) {
                    r6 = zzedVar.zze();
                    zzo = 49;
                } else {
                    r6 = 0;
                }
                int zzk2 = zzedVar.zzk();
                if (zzo == 47) {
                    zzedVar.zzG(1);
                    zzo = 47;
                }
                boolean z = zzk == 181 && (zzo == 49 || zzo == 47) && zzk2 == 3;
                if (zzo == 49) {
                    z &= r6 == 1195456820;
                }
                if (z) {
                    zzb(j, zzedVar, zzaamVarArr);
                }
            }
            zzedVar.zzF(zzc3);
        }
    }

    public static void zzb(long j, zzed zzedVar, zzaam[] zzaamVarArr) {
        int zzk = zzedVar.zzk();
        if ((zzk & 64) != 0) {
            zzedVar.zzG(1);
            int r0 = (zzk & 31) * 3;
            int zzc = zzedVar.zzc();
            for (zzaam zzaamVar : zzaamVarArr) {
                zzedVar.zzF(zzc);
                zzaamVar.zzq(zzedVar, r0);
                if (j != C1856C.TIME_UNSET) {
                    zzaamVar.zzs(j, 1, r0, 0, null);
                }
            }
        }
    }

    private static int zzc(zzed zzedVar) {
        int r0 = 0;
        while (zzedVar.zza() != 0) {
            int zzk = zzedVar.zzk();
            r0 += zzk;
            if (zzk != 255) {
                return r0;
            }
        }
        return -1;
    }
}
