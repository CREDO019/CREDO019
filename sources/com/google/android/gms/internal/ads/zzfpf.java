package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpf implements zzfpd {
    private zzfpf() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfpf(zzfpe zzfpeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzfpd
    public final ExecutorService zza(int r3) {
        return zzc(1, Executors.defaultThreadFactory(), 2);
    }

    @Override // com.google.android.gms.internal.ads.zzfpd
    public final ExecutorService zzb(ThreadFactory threadFactory, int r2) {
        return zzc(1, threadFactory, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzfpd
    public final ExecutorService zzc(int r9, ThreadFactory threadFactory, int r11) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(r9, r9, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}
