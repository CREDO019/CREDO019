package com.polidea.rxandroidble.internal.operations;

import java.util.concurrent.TimeUnit;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public class TimeoutConfiguration {
    public final long timeout;
    public final Scheduler timeoutScheduler;
    public final TimeUnit timeoutTimeUnit;

    public TimeoutConfiguration(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.timeout = j;
        this.timeoutTimeUnit = timeUnit;
        this.timeoutScheduler = scheduler;
    }
}
