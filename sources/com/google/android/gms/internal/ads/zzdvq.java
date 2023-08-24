package com.google.android.gms.internal.ads;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdvq {
    public static String zza(JSONObject jSONObject, String str, String str2) {
        JSONArray optJSONArray;
        if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray(str2)) != null) {
            for (int r6 = 0; r6 < optJSONArray.length(); r6++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(r6);
                if (optJSONObject != null) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("including");
                    JSONArray optJSONArray3 = optJSONObject.optJSONArray("excluding");
                    if (zzb(optJSONArray2, str) && !zzb(optJSONArray3, str)) {
                        return optJSONObject.optString("effective_ad_unit_id", "");
                    }
                }
            }
        }
        return "";
    }

    private static boolean zzb(JSONArray jSONArray, String str) {
        if (jSONArray != null && str != null) {
            for (int r1 = 0; r1 < jSONArray.length(); r1++) {
                String optString = jSONArray.optString(r1);
                try {
                } catch (PatternSyntaxException e) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "RtbAdapterMap.hasAtleastOneRegexMatch");
                }
                if ((((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziu)).booleanValue() ? Pattern.compile(optString, 2) : Pattern.compile(optString)).matcher(str).lookingAt()) {
                    return true;
                }
            }
        }
        return false;
    }
}
