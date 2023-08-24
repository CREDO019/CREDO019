package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbpa implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        JSONObject zzb;
        zzcmn zzcmnVar = (zzcmn) obj;
        zzbln zzM = zzcmnVar.zzM();
        if (zzM == null || (zzb = zzM.zzb()) == null) {
            zzcmnVar.zze("nativeClickMetaReady", new JSONObject());
        } else {
            zzcmnVar.zze("nativeClickMetaReady", zzb);
        }
    }
}
