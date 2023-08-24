package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcts {
    private final Map zza;
    private final Map zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcts(Map map, Map map2) {
        this.zza = map;
        this.zzb = map2;
    }

    public final void zza(zzfde zzfdeVar) throws Exception {
        for (zzfdc zzfdcVar : zzfdeVar.zzb.zzc) {
            if (this.zza.containsKey(zzfdcVar.zza)) {
                ((zzctv) this.zza.get(zzfdcVar.zza)).zza(zzfdcVar.zzb);
            } else if (this.zzb.containsKey(zzfdcVar.zza)) {
                zzctu zzctuVar = (zzctu) this.zzb.get(zzfdcVar.zza);
                JSONObject jSONObject = zzfdcVar.zzb;
                HashMap hashMap = new HashMap();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = jSONObject.optString(next);
                    if (optString != null) {
                        hashMap.put(next, optString);
                    }
                }
                zzctuVar.zza(hashMap);
            }
        }
    }
}
