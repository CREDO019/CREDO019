package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeix implements zzegm {
    private final zzdvj zza;

    public zzeix(zzdvj zzdvjVar) {
        this.zza = zzdvjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegm
    public final zzegn zza(String str, JSONObject jSONObject) throws zzfds {
        return new zzegn(this.zza.zzc(str, jSONObject), new zzeig(), str);
    }
}
