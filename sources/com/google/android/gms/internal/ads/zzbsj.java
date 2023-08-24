package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzbsj {
    public static void zza(zzbsk zzbskVar, String str, Map map) {
        try {
            zzbskVar.zze(str, com.google.android.gms.ads.internal.client.zzaw.zzb().zzi(map));
        } catch (JSONException unused) {
            com.google.android.gms.ads.internal.util.zze.zzj("Could not convert parameters to JSON.");
        }
    }

    public static void zzb(zzbsk zzbskVar, String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("',");
        sb.append(jSONObject2);
        sb.append(");");
        com.google.android.gms.ads.internal.util.zze.zze("Dispatching AFMA event: ".concat(sb.toString()));
        zzbskVar.zza(sb.toString());
    }

    public static void zzc(zzbsk zzbskVar, String str, String str2) {
        zzbskVar.zza(str + "(" + str2 + ");");
    }

    public static void zzd(zzbsk zzbskVar, String str, JSONObject jSONObject) {
        zzbskVar.zzb(str, jSONObject.toString());
    }
}
