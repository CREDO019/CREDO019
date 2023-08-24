package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcvt implements zzgur {
    private final zzgve zza;

    public zzcvt(zzgve zzgveVar) {
        this.zza = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        try {
            return new JSONObject(((zzczs) this.zza).zza().zzA);
        } catch (JSONException unused) {
            return null;
        }
    }
}
