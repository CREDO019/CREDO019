package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbpm implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzcmn zzcmnVar = (zzcmn) obj;
        String str = (String) map.get("action");
        if ("pause".equals(str)) {
            zzcmnVar.zzbn();
        } else if ("resume".equals(str)) {
            zzcmnVar.zzbo();
        }
    }
}
