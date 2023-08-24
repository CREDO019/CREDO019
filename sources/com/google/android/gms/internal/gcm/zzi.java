package com.google.android.gms.internal.gcm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zzi implements zzf {
    private zzi() {
    }

    @Override // com.google.android.gms.internal.gcm.zzf
    public final ExecutorService zzd(int r9, ThreadFactory threadFactory, int r11) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(r9, r9, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }

    @Override // com.google.android.gms.internal.gcm.zzf
    public final ExecutorService zzd(ThreadFactory threadFactory, int r3) {
        return zzd(1, threadFactory, 9);
    }

    @Override // com.google.android.gms.internal.gcm.zzf
    public final ScheduledExecutorService zze(int r1, ThreadFactory threadFactory, int r3) {
        return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, threadFactory));
    }
}
