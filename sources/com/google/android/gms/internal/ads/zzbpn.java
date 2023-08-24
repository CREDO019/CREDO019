package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbpn implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzcmn zzcmnVar = (zzcmn) obj;
        if (map.keySet().contains("start")) {
            zzcmnVar.zzP().zzk();
        } else if (map.keySet().contains("stop")) {
            zzcmnVar.zzP().zzl();
        } else if (map.keySet().contains("cancel")) {
            zzcmnVar.zzP().zzj();
        }
    }
}
