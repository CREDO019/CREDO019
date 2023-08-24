package com.google.android.gms.internal.ads;

import androidx.core.app.NotificationCompat;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdyr {
    private Long zza;
    private final String zzb;
    private String zzc;
    private Integer zzd;
    private String zze;
    private Integer zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdyr(String str, zzdyq zzdyqVar) {
        this.zzb = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ String zza(zzdyr zzdyrVar) {
        String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzic);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("objectId", zzdyrVar.zza);
            jSONObject.put("eventCategory", zzdyrVar.zzb);
            jSONObject.putOpt(NotificationCompat.CATEGORY_EVENT, zzdyrVar.zzc);
            jSONObject.putOpt("errorCode", zzdyrVar.zzd);
            jSONObject.putOpt("rewardType", zzdyrVar.zze);
            jSONObject.putOpt("rewardAmount", zzdyrVar.zzf);
        } catch (JSONException unused) {
            com.google.android.gms.ads.internal.util.zze.zzj("Could not convert parameters to JSON.");
        }
        String jSONObject2 = jSONObject.toString();
        return str + "(\"h5adsEvent\"," + jSONObject2 + ");";
    }
}
