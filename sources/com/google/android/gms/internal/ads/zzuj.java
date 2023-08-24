package com.google.android.gms.internal.ads;

import androidx.work.WorkRequest;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzuj {
    private final zzde zza = zzde.zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzuk zza(zzcp zzcpVar, int[] r21, int r22, zzwe zzweVar, zzfuv zzfuvVar) {
        return new zzuk(zzcpVar, r21, 0, zzweVar, WorkRequest.MIN_BACKOFF_MILLIS, 25000L, 25000L, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0.7f, 0.75f, zzfuvVar, this.zza);
    }
}
