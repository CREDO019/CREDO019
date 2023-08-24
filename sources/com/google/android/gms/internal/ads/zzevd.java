package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.amplitude.api.Constants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevd implements zzeum {
    private final AdvertisingIdClient.Info zza;
    private final String zzb;

    public zzevd(AdvertisingIdClient.Info info, String str) {
        this.zza = info;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.ads.zzeum
    public final /* bridge */ /* synthetic */ void zzf(Object obj) {
        try {
            JSONObject zzf = com.google.android.gms.ads.internal.util.zzbu.zzf((JSONObject) obj, "pii");
            AdvertisingIdClient.Info info = this.zza;
            if (info == null || TextUtils.isEmpty(info.getId())) {
                zzf.put("pdid", this.zzb);
                zzf.put("pdidtype", "ssaid");
                return;
            }
            zzf.put("rdid", this.zza.getId());
            zzf.put("is_lat", this.zza.isLimitAdTrackingEnabled());
            zzf.put("idtype", Constants.AMP_TRACKING_OPTION_ADID);
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzb("Failed putting Ad ID.", e);
        }
    }
}
