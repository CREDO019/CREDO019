package com.google.android.gms.internal.ads;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfyo extends zzfyq {
    public static zzfyn zza(Iterable iterable) {
        return new zzfyn(false, zzfuv.zzl(iterable), null);
    }

    @SafeVarargs
    public static zzfyn zzb(zzfyx... zzfyxVarArr) {
        return new zzfyn(false, zzfuv.zzn(zzfyxVarArr), null);
    }

    public static zzfyn zzc(Iterable iterable) {
        return new zzfyn(true, zzfuv.zzl(iterable), null);
    }

    @SafeVarargs
    public static zzfyn zzd(zzfyx... zzfyxVarArr) {
        return new zzfyn(true, zzfuv.zzn(zzfyxVarArr), null);
    }

    public static zzfyx zze(Iterable iterable) {
        return new zzfxw(zzfuv.zzl(iterable), true);
    }

    public static zzfyx zzf(zzfyx zzfyxVar, Class cls, zzfru zzfruVar, Executor executor) {
        zzfxd zzfxdVar = new zzfxd(zzfyxVar, cls, zzfruVar);
        zzfyxVar.zzc(zzfxdVar, zzfze.zzc(executor, zzfxdVar));
        return zzfxdVar;
    }

    public static zzfyx zzg(zzfyx zzfyxVar, Class cls, zzfxv zzfxvVar, Executor executor) {
        zzfxc zzfxcVar = new zzfxc(zzfyxVar, cls, zzfxvVar);
        zzfyxVar.zzc(zzfxcVar, zzfze.zzc(executor, zzfxcVar));
        return zzfxcVar;
    }

    public static zzfyx zzi(Object obj) {
        if (obj == null) {
            return zzfys.zza;
        }
        return new zzfys(obj);
    }

    public static zzfyx zzj() {
        return zzfys.zza;
    }

    public static zzfyx zzk(Callable callable, Executor executor) {
        zzfzn zzfznVar = new zzfzn(callable);
        executor.execute(zzfznVar);
        return zzfznVar;
    }

    public static zzfyx zzl(zzfxu zzfxuVar, Executor executor) {
        zzfzn zzfznVar = new zzfzn(zzfxuVar);
        executor.execute(zzfznVar);
        return zzfznVar;
    }

    public static zzfyx zzm(zzfyx zzfyxVar, zzfru zzfruVar, Executor executor) {
        int r0 = zzfxl.zzc;
        Objects.requireNonNull(zzfruVar);
        zzfxk zzfxkVar = new zzfxk(zzfyxVar, zzfruVar);
        zzfyxVar.zzc(zzfxkVar, zzfze.zzc(executor, zzfxkVar));
        return zzfxkVar;
    }

    public static zzfyx zzn(zzfyx zzfyxVar, zzfxv zzfxvVar, Executor executor) {
        int r0 = zzfxl.zzc;
        Objects.requireNonNull(executor);
        zzfxj zzfxjVar = new zzfxj(zzfyxVar, zzfxvVar);
        zzfyxVar.zzc(zzfxjVar, zzfze.zzc(executor, zzfxjVar));
        return zzfxjVar;
    }

    public static zzfyx zzo(zzfyx zzfyxVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return zzfyxVar.isDone() ? zzfyxVar : zzfzk.zzg(zzfyxVar, j, timeUnit, scheduledExecutorService);
    }

    public static Object zzp(Future future) throws ExecutionException {
        if (!future.isDone()) {
            throw new IllegalStateException(zzfsu.zzb("Future was expected to be done: %s", future));
        }
        return zzfzp.zza(future);
    }

    public static Object zzq(Future future) {
        try {
            return zzfzp.zza(future);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Error) {
                throw new zzfyd((Error) cause);
            }
            throw new zzfzo(cause);
        }
    }

    public static zzfyx zzh(Throwable th) {
        Objects.requireNonNull(th);
        return new zzfyr(th);
    }

    public static void zzr(zzfyx zzfyxVar, zzfyk zzfykVar, Executor executor) {
        Objects.requireNonNull(zzfykVar);
        zzfyxVar.zzc(new zzfym(zzfyxVar, zzfykVar), executor);
    }
}
