package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzedt implements zzbty {
    @Override // com.google.android.gms.internal.ads.zzbty
    public final /* bridge */ /* synthetic */ JSONObject zzb(Object obj) throws JSONException {
        zzedu zzeduVar = (zzedu) obj;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhS)).booleanValue()) {
            jSONObject2.put("ad_request_url", zzeduVar.zzd.zze());
            jSONObject2.put("ad_request_post_body", zzeduVar.zzd.zzd());
        }
        jSONObject2.put("base_url", zzeduVar.zzd.zzb());
        jSONObject2.put("signals", zzeduVar.zzc);
        jSONObject3.put(TtmlNode.TAG_BODY, zzeduVar.zzb.zzc);
        jSONObject3.put("headers", com.google.android.gms.ads.internal.client.zzaw.zzb().zzi(zzeduVar.zzb.zzb));
        jSONObject3.put("response_code", zzeduVar.zzb.zza);
        jSONObject3.put("latency", zzeduVar.zzb.zzd);
        jSONObject.put("request", jSONObject2);
        jSONObject.put("response", jSONObject3);
        jSONObject.put("flags", zzeduVar.zzd.zzg());
        return jSONObject;
    }
}
