package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeld implements zzegm {
    private final zzemh zza;

    public zzeld(zzemh zzemhVar) {
        this.zza = zzemhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegm
    public final zzegn zza(String str, JSONObject jSONObject) throws zzfds {
        zzbwy zza = this.zza.zza(str);
        if (zza == null) {
            return null;
        }
        return new zzegn(zza, new zzeig(), str);
    }
}
