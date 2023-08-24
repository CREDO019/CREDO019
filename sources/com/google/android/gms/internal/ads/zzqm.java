package com.google.android.gms.internal.ads;

import android.media.metrics.LogSessionId;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzqm {
    public static void zza(zzqh zzqhVar, zzmz zzmzVar) {
        LogSessionId zza = zzmzVar.zza();
        if (zza.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
            return;
        }
        zzqhVar.zzb.setString("log-session-id", zza.getStringId());
    }
}
