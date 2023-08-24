package com.google.android.gms.internal.ads;

import android.util.Log;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgue extends zzguj {
    final String zza;

    public zzgue(String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.ads.zzguj
    public final void zza(String str) {
        String str2 = this.zza;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
        sb.append(str2);
        sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
        sb.append(str);
        Log.d("isoparser", sb.toString());
    }
}
