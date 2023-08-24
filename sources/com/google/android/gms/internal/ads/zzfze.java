package com.google.android.gms.internal.ads;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfze {
    public static zzfyy zza(ExecutorService executorService) {
        zzfyy zzfzaVar;
        if (executorService instanceof zzfyy) {
            return (zzfyy) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            zzfzaVar = new zzfzd((ScheduledExecutorService) executorService);
        } else {
            zzfzaVar = new zzfza(executorService);
        }
        return zzfzaVar;
    }

    public static Executor zzb() {
        return zzfyc.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor zzc(Executor executor, zzfxf zzfxfVar) {
        Objects.requireNonNull(executor);
        return executor == zzfyc.INSTANCE ? executor : new zzfyz(executor, zzfxfVar);
    }
}
