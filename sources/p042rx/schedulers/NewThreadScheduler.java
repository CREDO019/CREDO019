package p042rx.schedulers;

import p042rx.Scheduler;

@Deprecated
/* renamed from: rx.schedulers.NewThreadScheduler */
/* loaded from: classes6.dex */
public final class NewThreadScheduler extends Scheduler {
    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private NewThreadScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
