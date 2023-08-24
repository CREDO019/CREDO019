package com.google.android.gms.vision.clearcut;

import android.content.Context;
import com.google.android.gms.internal.vision.zzea;
import com.google.android.gms.vision.C2148L;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class DynamiteClearcutLogger {
    private static final ThreadPoolExecutor zzbn = new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new ThreadPoolExecutor.DiscardPolicy());
    private zzb zzbo = new zzb(0.03333333333333333d);
    private VisionClearcutLogger zzbp;

    public DynamiteClearcutLogger(Context context) {
        this.zzbp = new VisionClearcutLogger(context);
    }

    public final void zza(int r3, zzea.zzo zzoVar) {
        if (r3 == 3 && !this.zzbo.tryAcquire()) {
            C2148L.m1077v("Skipping image analysis log due to rate limiting", new Object[0]);
        } else {
            zzbn.execute(new zza(this, r3, zzoVar));
        }
    }
}
