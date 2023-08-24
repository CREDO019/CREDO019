package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzee implements zzde {
    @Override // com.google.android.gms.internal.ads.zzde
    public final long zza() {
        return SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.gms.internal.ads.zzde
    public final zzdn zzb(Looper looper, Handler.Callback callback) {
        return new zzeh(new Handler(looper, callback));
    }
}
