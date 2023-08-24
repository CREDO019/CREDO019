package com.google.android.gms.ads.internal.util;

import android.content.Context;
import com.google.android.gms.internal.ads.zzcgm;
import com.google.android.gms.internal.ads.zzchd;
import com.google.android.gms.internal.ads.zzfyx;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzd {
    public static void zza(Context context) {
        if (zzcgm.zzk(context) && !zzcgm.zzm()) {
            zzfyx zzb = new zzc(context).zzb();
            zze.zzi("Updating ad debug logging enablement.");
            zzchd.zza(zzb, "AdDebugLogUpdater.updateEnablement");
        }
    }
}
