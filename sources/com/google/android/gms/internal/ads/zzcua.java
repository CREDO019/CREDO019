package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcua implements zzctu {
    private final zzeai zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcua(zzeai zzeaiVar) {
        this.zza = zzeaiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzctu
    public final void zza(Map map) {
        char c;
        String str = (String) map.get("gesture");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int hashCode = str.hashCode();
        if (hashCode != 97520651) {
            if (hashCode == 109399814 && str.equals("shake")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("flick")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.zza.zzj(zzeae.SHAKE);
        } else if (c == 1) {
            this.zza.zzj(zzeae.FLICK);
        } else {
            this.zza.zzj(zzeae.NONE);
        }
    }
}
