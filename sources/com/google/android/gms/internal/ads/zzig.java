package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.metrics.LogSessionId;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzig {
    public static zzmz zza(Context context, zzir zzirVar, boolean z) {
        zzmv zzb = zzmv.zzb(context);
        if (zzb == null) {
            Log.w("ExoPlayerImpl", "MediaMetricsService unavailable.");
            return new zzmz(LogSessionId.LOG_SESSION_ID_NONE);
        }
        if (z) {
            zzirVar.zzR(zzb);
        }
        return new zzmz(zzb.zza());
    }
}
