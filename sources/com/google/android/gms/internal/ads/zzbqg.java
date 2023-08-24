package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbqg implements zzbpq {
    private final zzbqf zza;

    public zzbqg(zzbqf zzbqfVar) {
        this.zza = zzbqfVar;
    }

    public static void zzb(zzcmn zzcmnVar, zzbqf zzbqfVar) {
        zzcmnVar.zzaf("/reward", new zzbqg(zzbqfVar));
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        String str = (String) map.get("action");
        if ("grant".equals(str)) {
            zzccc zzcccVar = null;
            try {
                int parseInt = Integer.parseInt((String) map.get("amount"));
                String str2 = (String) map.get(SessionDescription.ATTR_TYPE);
                if (!TextUtils.isEmpty(str2)) {
                    zzcccVar = new zzccc(str2, parseInt);
                }
            } catch (NumberFormatException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Unable to parse reward amount.", e);
            }
            this.zza.zza(zzcccVar);
        } else if ("video_start".equals(str)) {
            this.zza.zzc();
        } else if ("video_complete".equals(str)) {
            this.zza.zzb();
        }
    }
}
