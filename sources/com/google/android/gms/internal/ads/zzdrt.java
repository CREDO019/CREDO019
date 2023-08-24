package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdrt {
    private final Executor zza;
    private final zzdro zzb;

    public zzdrt(Executor executor, zzdro zzdroVar) {
        this.zza = executor;
        this.zzb = zzdroVar;
    }

    public final zzfyx zza(JSONObject jSONObject, String str) {
        zzfyx zzi;
        JSONArray optJSONArray = jSONObject.optJSONArray("custom_assets");
        if (optJSONArray == null) {
            return zzfyo.zzi(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        int length = optJSONArray.length();
        for (int r1 = 0; r1 < length; r1++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(r1);
            if (optJSONObject == null) {
                zzi = zzfyo.zzi(null);
            } else {
                final String optString = optJSONObject.optString("name");
                if (optString == null) {
                    zzi = zzfyo.zzi(null);
                } else {
                    String optString2 = optJSONObject.optString(SessionDescription.ATTR_TYPE);
                    if ("string".equals(optString2)) {
                        zzi = zzfyo.zzi(new zzdrs(optString, optJSONObject.optString("string_value")));
                    } else if ("image".equals(optString2)) {
                        zzi = zzfyo.zzm(this.zzb.zze(optJSONObject, "image_value"), new zzfru() { // from class: com.google.android.gms.internal.ads.zzdrq
                            @Override // com.google.android.gms.internal.ads.zzfru
                            public final Object apply(Object obj) {
                                return new zzdrs(optString, (zzblm) obj);
                            }
                        }, this.zza);
                    } else {
                        zzi = zzfyo.zzi(null);
                    }
                }
            }
            arrayList.add(zzi);
        }
        return zzfyo.zzm(zzfyo.zze(arrayList), new zzfru() { // from class: com.google.android.gms.internal.ads.zzdrr
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                ArrayList arrayList2 = new ArrayList();
                for (zzdrs zzdrsVar : (List) obj) {
                    if (zzdrsVar != null) {
                        arrayList2.add(zzdrsVar);
                    }
                }
                return arrayList2;
            }
        }, this.zza);
    }
}
