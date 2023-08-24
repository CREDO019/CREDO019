package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzboz implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        JSONObject zza;
        zzcmn zzcmnVar = (zzcmn) obj;
        zzbln zzM = zzcmnVar.zzM();
        if (zzM == null || (zza = zzM.zza()) == null) {
            zzcmnVar.zze("nativeAdViewSignalsReady", new JSONObject());
        } else {
            zzcmnVar.zze("nativeAdViewSignalsReady", zza);
        }
    }
}
