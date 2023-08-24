package p042rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import p042rx.Scheduler;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.internal.subscriptions.SequentialSubscription;

/* renamed from: rx.internal.schedulers.SchedulePeriodicHelper */
/* loaded from: classes6.dex */
public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* renamed from: rx.internal.schedulers.SchedulePeriodicHelper$NowNanoSupplier */
    /* loaded from: classes6.dex */
    public interface NowNanoSupplier {
        long nowNanos();
    }

    private SchedulePeriodicHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription schedulePeriodically(Scheduler.Worker worker, Action0 action0, long j, long j2, TimeUnit timeUnit, NowNanoSupplier nowNanoSupplier) {
        long nanos = timeUnit.toNanos(j2);
        long nowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        SequentialSubscription sequentialSubscription = new SequentialSubscription();
        SequentialSubscription sequentialSubscription2 = new SequentialSubscription(sequentialSubscription);
        sequentialSubscription.replace(worker.schedule(new Action0(nowNanos, timeUnit.toNanos(j) + nowNanos, action0, sequentialSubscription2, nowNanoSupplier, worker, nanos) { // from class: rx.internal.schedulers.SchedulePeriodicHelper.1
            long count;
            long lastNowNanos;
            long startInNanos;
            final /* synthetic */ Action0 val$action;
            final /* synthetic */ long val$firstNowNanos;
            final /* synthetic */ long val$firstStartInNanos;
            final /* synthetic */ SequentialSubscription val$mas;
            final /* synthetic */ NowNanoSupplier val$nowNanoSupplier;
            final /* synthetic */ long val$periodInNanos;
            final /* synthetic */ Scheduler.Worker val$worker;

            {
                this.val$firstNowNanos = nowNanos;
                this.val$firstStartInNanos = r3;
                this.val$action = action0;
                this.val$mas = sequentialSubscription2;
                this.val$nowNanoSupplier = nowNanoSupplier;
                this.val$worker = worker;
                this.val$periodInNanos = nanos;
                this.lastNowNanos = nowNanos;
                this.startInNanos = r3;
            }

            @Override // p042rx.functions.Action0
            public void call() {
                long j3;
                this.val$action.call();
                if (this.val$mas.isUnsubscribed()) {
                    return;
                }
                NowNanoSupplier nowNanoSupplier2 = this.val$nowNanoSupplier;
                long nowNanos2 = nowNanoSupplier2 != null ? nowNanoSupplier2.nowNanos() : TimeUnit.MILLISECONDS.toNanos(this.val$worker.now());
                long j4 = this.lastNowNanos;
                if (SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS + nowNanos2 < j4 || nowNanos2 >= j4 + this.val$periodInNanos + SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS) {
                    long j5 = this.val$periodInNanos;
                    long j6 = nowNanos2 + j5;
                    long j7 = this.count + 1;
                    this.count = j7;
                    this.startInNanos = j6 - (j5 * j7);
                    j3 = j6;
                } else {
                    long j8 = this.startInNanos;
                    long j9 = this.count + 1;
                    this.count = j9;
                    j3 = j8 + (j9 * this.val$periodInNanos);
                }
                this.lastNowNanos = nowNanos2;
                this.val$mas.replace(this.val$worker.schedule(this, j3 - nowNanos2, TimeUnit.NANOSECONDS));
            }
        }, j, timeUnit));
        return sequentialSubscription2;
    }
}
