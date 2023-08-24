package com.google.android.gms.internal.gcm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public interface zzf {
    ExecutorService zzd(int r1, ThreadFactory threadFactory, int r3);

    ExecutorService zzd(ThreadFactory threadFactory, int r2);

    ScheduledExecutorService zze(int r1, ThreadFactory threadFactory, int r3);
}
