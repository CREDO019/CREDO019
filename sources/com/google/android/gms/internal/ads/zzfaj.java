package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfaj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfah zza(Context context, zzfeu zzfeuVar, zzffm zzffmVar) {
        return zzc(context, zzfeuVar, zzffmVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfah zzb(Context context, zzfeu zzfeuVar, zzffm zzffmVar) {
        return zzc(context, zzfeuVar, zzffmVar);
    }

    private static zzfah zzc(Context context, zzfeu zzfeuVar, zzffm zzffmVar) {
        zzcfq zzi;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfr)).booleanValue()) {
            zzi = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh();
        } else {
            zzi = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzi();
        }
        boolean z = false;
        if (zzi != null && zzi.zzh()) {
            z = true;
        }
        if (((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfH)).intValue() > 0) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfq)).booleanValue() || z) {
                zzffl zza = zzffmVar.zza(zzffc.AppOpen, context, zzfeuVar, new zzezl(new zzezi()));
                return new zzezn(new zzezx(new zzezw()), new zzezt(zza.zza, zzcha.zza), zza.zzb, zza.zza.zza().zzf, zzcha.zza);
            }
        }
        return new zzezw();
    }
}
