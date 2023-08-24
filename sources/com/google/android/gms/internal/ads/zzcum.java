package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcum implements zzctu {
    private final zzeai zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcum(zzeai zzeaiVar) {
        this.zza = zzeaiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzctu
    public final void zza(Map map) {
        String str = (String) map.get("test_mode_enabled");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zza.zzl(str.equals("true"));
    }
}
