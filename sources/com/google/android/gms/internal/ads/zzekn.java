package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzekn implements zzegm {
    private final Map zza = new HashMap();
    private final zzdvj zzb;

    public zzekn(zzdvj zzdvjVar) {
        this.zzb = zzdvjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegm
    public final zzegn zza(String str, JSONObject jSONObject) throws zzfds {
        zzegn zzegnVar;
        synchronized (this) {
            zzegnVar = (zzegn) this.zza.get(str);
            if (zzegnVar == null) {
                zzegnVar = new zzegn(this.zzb.zzc(str, jSONObject), new zzeih(), str);
                this.zza.put(str, zzegnVar);
            }
        }
        return zzegnVar;
    }
}
