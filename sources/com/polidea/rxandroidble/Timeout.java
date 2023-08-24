package com.polidea.rxandroidble;

import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class Timeout {
    public final TimeUnit timeUnit;
    public final long timeout;

    public Timeout(long j, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        this.timeout = j;
    }
}
