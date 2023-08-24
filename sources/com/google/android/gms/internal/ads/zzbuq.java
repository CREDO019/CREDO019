package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbuq implements zzbqd {
    final /* synthetic */ zzbur zza;
    private final zzchf zzb;

    public zzbuq(zzbur zzburVar, zzchf zzchfVar) {
        this.zza = zzburVar;
        this.zzb = zzchfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbqd
    public final void zza(String str) {
        try {
            if (str == null) {
                this.zzb.zze(new zzbtu());
            } else {
                this.zzb.zze(new zzbtu(str));
            }
        } catch (IllegalStateException unused) {
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbqd
    public final void zzb(JSONObject jSONObject) {
        try {
            this.zzb.zzd(jSONObject);
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzb.zze(e);
        }
    }
}
