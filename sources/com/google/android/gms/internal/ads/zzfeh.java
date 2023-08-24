package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfeh {
    public static void zza(Context context, boolean z) {
        if (z) {
            com.google.android.gms.ads.internal.util.zze.zzi("This request is sent from a test device.");
            return;
        }
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        String zzx = zzcgg.zzx(context);
        com.google.android.gms.ads.internal.util.zze.zzi("Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(\"" + zzx + "\")) to get test ads on this device.");
    }

    public static void zzb(int r2, Throwable th, String str) {
        com.google.android.gms.ads.internal.util.zze.zzi("Ad failed to load : " + r2);
        com.google.android.gms.ads.internal.util.zze.zzb(str, th);
        if (r2 == 3) {
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzp().zzs(th, str);
    }
}
