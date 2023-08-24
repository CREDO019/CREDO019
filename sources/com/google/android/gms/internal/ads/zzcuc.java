package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcuc implements zzctu {
    private final zzeai zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcuc(zzeai zzeaiVar) {
        this.zza = zzeaiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzctu
    public final void zza(Map map) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhP)).booleanValue()) {
            String str = (String) map.get("policy_violations");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.zza.zzk(str);
        }
    }
}
