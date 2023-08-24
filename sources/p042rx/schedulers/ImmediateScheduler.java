package p042rx.schedulers;

import p042rx.Scheduler;

@Deprecated
/* renamed from: rx.schedulers.ImmediateScheduler */
/* loaded from: classes6.dex */
public final class ImmediateScheduler extends Scheduler {
    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
