package com.google.android.gms.internal.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzewe implements zzeun {
    private final JSONObject zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzewe(Context context) {
        this.zza = zzcbk.zzc(context);
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 46;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzi(new zzeum() { // from class: com.google.android.gms.internal.ads.zzewd
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                zzewe.this.zzc((JSONObject) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(JSONObject jSONObject) {
        try {
            jSONObject.put("gms_sdk_env", this.zza);
        } catch (JSONException unused) {
            com.google.android.gms.ads.internal.util.zze.zza("Failed putting version constants.");
        }
    }
}
