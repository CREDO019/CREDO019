package p042rx.internal.schedulers;

import p042rx.Scheduler;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;

/* renamed from: rx.internal.schedulers.SleepingAction */
/* loaded from: classes6.dex */
class SleepingAction implements Action0 {
    private final long execTime;
    private final Scheduler.Worker innerScheduler;
    private final Action0 underlying;

    public SleepingAction(Action0 action0, Scheduler.Worker worker, long j) {
        this.underlying = action0;
        this.innerScheduler = worker;
        this.execTime = j;
    }

    @Override // p042rx.functions.Action0
    public void call() {
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        long now = this.execTime - this.innerScheduler.now();
        if (now > 0) {
            try {
                Thread.sleep(now);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Exceptions.propagate(e);
            }
        }
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        this.underlying.call();
    }
}
