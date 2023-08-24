package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbom implements zzbpq {
    private final zzbon zza;

    public zzbom(zzbon zzbonVar) {
        this.zza = zzbonVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        String str = (String) map.get("name");
        if (str == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("App event with no name parameter.");
        } else {
            this.zza.zzbD(str, (String) map.get("info"));
        }
    }
}
