package p042rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import p042rx.Scheduler;

/* renamed from: rx.internal.schedulers.NewThreadScheduler */
/* loaded from: classes6.dex */
public final class NewThreadScheduler extends Scheduler {
    private final ThreadFactory threadFactory;

    public NewThreadScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new NewThreadWorker(this.threadFactory);
    }
}
