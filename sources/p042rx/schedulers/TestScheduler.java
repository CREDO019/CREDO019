package p042rx.schedulers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import p042rx.Scheduler;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.internal.schedulers.SchedulePeriodicHelper;
import p042rx.subscriptions.BooleanSubscription;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.schedulers.TestScheduler */
/* loaded from: classes6.dex */
public class TestScheduler extends Scheduler {
    static long counter;
    final Queue<TimedAction> queue = new PriorityQueue(11, new CompareActionsByTime());
    long time;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.schedulers.TestScheduler$TimedAction */
    /* loaded from: classes6.dex */
    public static final class TimedAction {
        final Action0 action;
        private final long count;
        final Scheduler.Worker scheduler;
        final long time;

        TimedAction(Scheduler.Worker worker, long j, Action0 action0) {
            long j2 = TestScheduler.counter;
            TestScheduler.counter = 1 + j2;
            this.count = j2;
            this.time = j;
            this.action = action0;
            this.scheduler = worker;
        }

        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", Long.valueOf(this.time), this.action.toString());
        }
    }

    /* renamed from: rx.schedulers.TestScheduler$CompareActionsByTime */
    /* loaded from: classes6.dex */
    static final class CompareActionsByTime implements Comparator<TimedAction> {
        CompareActionsByTime() {
        }

        @Override // java.util.Comparator
        public int compare(TimedAction timedAction, TimedAction timedAction2) {
            if (timedAction.time == timedAction2.time) {
                if (timedAction.count < timedAction2.count) {
                    return -1;
                }
                return timedAction.count > timedAction2.count ? 1 : 0;
            } else if (timedAction.time < timedAction2.time) {
                return -1;
            } else {
                return timedAction.time > timedAction2.time ? 1 : 0;
            }
        }
    }

    @Override // p042rx.Scheduler
    public long now() {
        return TimeUnit.NANOSECONDS.toMillis(this.time);
    }

    public void advanceTimeBy(long j, TimeUnit timeUnit) {
        advanceTimeTo(this.time + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long j, TimeUnit timeUnit) {
        triggerActions(timeUnit.toNanos(j));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long j) {
        while (!this.queue.isEmpty()) {
            TimedAction peek = this.queue.peek();
            if (peek.time > j) {
                break;
            }
            this.time = peek.time == 0 ? this.time : peek.time;
            this.queue.remove();
            if (!peek.scheduler.isUnsubscribed()) {
                peek.action.call();
            }
        }
        this.time = j;
    }

    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new InnerTestScheduler();
    }

    /* renamed from: rx.schedulers.TestScheduler$InnerTestScheduler */
    /* loaded from: classes6.dex */
    final class InnerTestScheduler extends Scheduler.Worker implements SchedulePeriodicHelper.NowNanoSupplier {

        /* renamed from: s */
        private final BooleanSubscription f2589s = new BooleanSubscription();

        InnerTestScheduler() {
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            this.f2589s.unsubscribe();
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.f2589s.isUnsubscribed();
        }

        @Override // p042rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            final TimedAction timedAction = new TimedAction(this, TestScheduler.this.time + timeUnit.toNanos(j), action0);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() { // from class: rx.schedulers.TestScheduler.InnerTestScheduler.1
                @Override // p042rx.functions.Action0
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }

        @Override // p042rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            final TimedAction timedAction = new TimedAction(this, 0L, action0);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() { // from class: rx.schedulers.TestScheduler.InnerTestScheduler.2
                @Override // p042rx.functions.Action0
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }

        @Override // p042rx.Scheduler.Worker
        public Subscription schedulePeriodically(Action0 action0, long j, long j2, TimeUnit timeUnit) {
            return SchedulePeriodicHelper.schedulePeriodically(this, action0, j, j2, timeUnit, this);
        }

        @Override // p042rx.Scheduler.Worker
        public long now() {
            return TestScheduler.this.now();
        }

        @Override // p042rx.internal.schedulers.SchedulePeriodicHelper.NowNanoSupplier
        public long nowNanos() {
            return TestScheduler.this.time;
        }
    }
}
