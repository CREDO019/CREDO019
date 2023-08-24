package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjb {
    public static void zza(zzfyx zzfyxVar, zzfjc zzfjcVar, zzfir zzfirVar) {
        zzg(zzfyxVar, zzfjcVar, zzfirVar, false);
    }

    public static void zzb(zzfyx zzfyxVar, zzfjc zzfjcVar, zzfir zzfirVar) {
        zzg(zzfyxVar, zzfjcVar, zzfirVar, true);
    }

    public static void zzc(zzfyx zzfyxVar, zzfjc zzfjcVar, zzfir zzfirVar) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            zzfyo.zzr(zzfyf.zzv(zzfyxVar), new zzfja(zzfjcVar, zzfirVar), zzcha.zzf);
        }
    }

    public static void zzd(zzfyx zzfyxVar, zzfir zzfirVar) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            zzfyo.zzr(zzfyf.zzv(zzfyxVar), new zzfiy(zzfirVar), zzcha.zzf);
        }
    }

    public static boolean zze(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.matches((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhv), str);
    }

    public static int zzf(zzfdn zzfdnVar) {
        int zzd = com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzd(zzfdnVar) - 1;
        return (zzd == 0 || zzd == 1) ? 7 : 23;
    }

    private static void zzg(zzfyx zzfyxVar, zzfjc zzfjcVar, zzfir zzfirVar, boolean z) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            zzfyo.zzr(zzfyf.zzv(zzfyxVar), new zzfiz(zzfjcVar, zzfirVar, z), zzcha.zzf);
        }
    }
}
