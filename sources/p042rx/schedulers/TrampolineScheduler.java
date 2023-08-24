package p042rx.schedulers;

import p042rx.Scheduler;

@Deprecated
/* renamed from: rx.schedulers.TrampolineScheduler */
/* loaded from: classes6.dex */
public final class TrampolineScheduler extends Scheduler {
    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private TrampolineScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
