package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzp {
    public static zzzr zzb(zzed zzedVar) {
        zzedVar.zzG(1);
        int zzm = zzedVar.zzm();
        long zzc = zzedVar.zzc() + zzm;
        int r0 = zzm / 18;
        long[] jArr = new long[r0];
        long[] jArr2 = new long[r0];
        int r5 = 0;
        while (true) {
            if (r5 >= r0) {
                break;
            }
            long zzr = zzedVar.zzr();
            if (zzr != -1) {
                jArr[r5] = zzr;
                jArr2[r5] = zzedVar.zzr();
                zzedVar.zzG(2);
                r5++;
            } else {
                jArr = Arrays.copyOf(jArr, r5);
                jArr2 = Arrays.copyOf(jArr2, r5);
                break;
            }
        }
        zzedVar.zzG((int) (zzc - zzedVar.zzc()));
        return new zzzr(jArr, jArr2);
    }

    public static zzbq zza(zzzg zzzgVar, boolean z) throws IOException {
        zzbq zza = new zzzw().zza(zzzgVar, z ? null : zzacz.zza);
        if (zza == null || zza.zza() == 0) {
            return null;
        }
        return zza;
    }
}
