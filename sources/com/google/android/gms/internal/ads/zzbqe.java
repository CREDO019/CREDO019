package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbqe implements zzbpq {
    private final Object zza = new Object();
    private final Map zzb = new HashMap();

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        String str = (String) map.get("id");
        String str2 = (String) map.get("fail");
        String str3 = (String) map.get("fail_reason");
        String str4 = (String) map.get("fail_stack");
        String str5 = (String) map.get("result");
        if (true == TextUtils.isEmpty(str4)) {
            str3 = "Unknown Fail Reason.";
        }
        String concat = TextUtils.isEmpty(str4) ? "" : "\n".concat(String.valueOf(str4));
        synchronized (this.zza) {
            zzbqd zzbqdVar = (zzbqd) this.zzb.remove(str);
            if (zzbqdVar == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Received result for unexpected method invocation: " + str);
            } else if (!TextUtils.isEmpty(str2)) {
                zzbqdVar.zza(str3 + concat);
            } else if (str5 == null) {
                zzbqdVar.zzb(null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str5);
                    if (com.google.android.gms.ads.internal.util.zze.zzc()) {
                        String jSONObject2 = jSONObject.toString(2);
                        com.google.android.gms.ads.internal.util.zze.zza("Result GMSG: " + jSONObject2);
                    }
                    zzbqdVar.zzb(jSONObject);
                } catch (JSONException e) {
                    zzbqdVar.zza(e.getMessage());
                }
            }
        }
    }

    public final zzfyx zzb(zzbsv zzbsvVar, String str, JSONObject jSONObject) {
        zzchf zzchfVar = new zzchf();
        com.google.android.gms.ads.internal.zzt.zzq();
        String str2 = UUID.randomUUID().toString();
        zzc(str2, new zzbqc(this, zzchfVar));
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", str2);
            jSONObject2.put("args", jSONObject);
            zzbsvVar.zzl(str, jSONObject2);
        } catch (Exception e) {
            zzchfVar.zze(e);
        }
        return zzchfVar;
    }

    public final void zzc(String str, zzbqd zzbqdVar) {
        synchronized (this.zza) {
            this.zzb.put(str, zzbqdVar);
        }
    }
}
