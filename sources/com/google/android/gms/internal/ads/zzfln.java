package com.google.android.gms.internal.ads;

import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfln implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        Handler handler;
        Handler handler2;
        Runnable runnable;
        Handler handler3;
        Runnable runnable2;
        handler = zzflq.zzc;
        if (handler != null) {
            handler2 = zzflq.zzc;
            runnable = zzflq.zzd;
            handler2.post(runnable);
            handler3 = zzflq.zzc;
            runnable2 = zzflq.zze;
            handler3.postDelayed(runnable2, 200L);
        }
    }
}
