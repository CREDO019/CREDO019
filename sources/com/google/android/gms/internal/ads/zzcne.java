package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcne implements zzbpq {
    final /* synthetic */ zzcng zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcne(zzcng zzcngVar) {
        this.zza = zzcngVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        int r1;
        zzcmn zzcmnVar = (zzcmn) obj;
        if (map != null) {
            String str = (String) map.get("height");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                int parseInt = Integer.parseInt(str);
                synchronized (this.zza) {
                    zzcng zzcngVar = this.zza;
                    r1 = zzcngVar.zzG;
                    if (r1 != parseInt) {
                        zzcngVar.zzG = parseInt;
                        this.zza.requestLayout();
                    }
                }
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Exception occurred while getting webview content height", e);
            }
        }
    }
}
