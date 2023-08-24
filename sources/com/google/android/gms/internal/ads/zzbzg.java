package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbzg {
    public final boolean zza;
    public final String zzb;

    public zzbzg(boolean z, String str) {
        this.zza = z;
        this.zzb = str;
    }

    public static zzbzg zza(JSONObject jSONObject) {
        return new zzbzg(jSONObject.optBoolean("enable_prewarming", false), jSONObject.optString("prefetch_url", ""));
    }
}
