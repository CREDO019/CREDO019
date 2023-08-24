package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.CollectionUtils;
import com.onesignal.NotificationBundleProcessor;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbpx implements zzbpq {
    static final Map zza = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final com.google.android.gms.ads.internal.zzb zzb;
    private final zzbxu zzc;
    private final zzbyb zzd;

    public zzbpx(com.google.android.gms.ads.internal.zzb zzbVar, zzbxu zzbxuVar, zzbyb zzbybVar) {
        this.zzb = zzbVar;
        this.zzc = zzbxuVar;
        this.zzd = zzbybVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzcmn zzcmnVar = (zzcmn) obj;
        int intValue = ((Integer) zza.get((String) map.get(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY))).intValue();
        int r1 = 6;
        if (intValue != 5) {
            if (intValue != 7) {
                if (!this.zzb.zzc()) {
                    this.zzb.zzb(null);
                    return;
                } else if (intValue == 1) {
                    this.zzc.zzb(map);
                    return;
                } else if (intValue == 3) {
                    new zzbxx(zzcmnVar, map).zzb();
                    return;
                } else if (intValue == 4) {
                    new zzbxs(zzcmnVar, map).zzc();
                    return;
                } else if (intValue != 5) {
                    if (intValue == 6) {
                        this.zzc.zza(true);
                        return;
                    } else if (intValue != 7) {
                        com.google.android.gms.ads.internal.util.zze.zzi("Unknown MRAID command called.");
                        return;
                    }
                }
            }
            this.zzd.zzc();
            return;
        }
        String str = (String) map.get("forceOrientation");
        boolean parseBoolean = map.containsKey("allowOrientationChange") ? Boolean.parseBoolean((String) map.get("allowOrientationChange")) : true;
        if (zzcmnVar == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("AdWebView is null");
            return;
        }
        if ("portrait".equalsIgnoreCase(str)) {
            r1 = 7;
        } else if (!"landscape".equalsIgnoreCase(str)) {
            r1 = parseBoolean ? -1 : 14;
        }
        zzcmnVar.zzas(r1);
    }
}
