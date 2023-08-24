package p042rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Scheduler;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.internal.util.RxThreadFactory;
import p042rx.internal.util.SubscriptionList;
import p042rx.subscriptions.CompositeSubscription;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.schedulers.EventLoopsScheduler */
/* loaded from: classes6.dex */
public final class EventLoopsScheduler extends Scheduler implements SchedulerLifecycle {
    static final String KEY_MAX_THREADS = "rx.scheduler.max-computation-threads";
    static final int MAX_THREADS;
    static final FixedSchedulerPool NONE;
    static final PoolWorker SHUTDOWN_WORKER;
    final AtomicReference<FixedSchedulerPool> pool = new AtomicReference<>(NONE);
    final ThreadFactory threadFactory;

    static {
        int intValue = Integer.getInteger(KEY_MAX_THREADS, 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        MAX_THREADS = intValue;
        PoolWorker poolWorker = new PoolWorker(RxThreadFactory.NONE);
        SHUTDOWN_WORKER = poolWorker;
        poolWorker.unsubscribe();
        NONE = new FixedSchedulerPool(null, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.schedulers.EventLoopsScheduler$FixedSchedulerPool */
    /* loaded from: classes6.dex */
    public static final class FixedSchedulerPool {
        final int cores;
        final PoolWorker[] eventLoops;

        /* renamed from: n */
        long f2570n;

        FixedSchedulerPool(ThreadFactory threadFactory, int r5) {
            this.cores = r5;
            this.eventLoops = new PoolWorker[r5];
            for (int r0 = 0; r0 < r5; r0++) {
                this.eventLoops[r0] = new PoolWorker(threadFactory);
            }
        }

        public PoolWorker getEventLoop() {
            int r0 = this.cores;
            if (r0 == 0) {
                return EventLoopsScheduler.SHUTDOWN_WORKER;
            }
            PoolWorker[] poolWorkerArr = this.eventLoops;
            long j = this.f2570n;
            this.f2570n = 1 + j;
            return poolWorkerArr[(int) (j % r0)];
        }

        public void shutdown() {
            for (PoolWorker poolWorker : this.eventLoops) {
                poolWorker.unsubscribe();
            }
        }
    }

    public EventLoopsScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        start();
    }

    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new EventLoopWorker(this.pool.get().getEventLoop());
    }

    @Override // p042rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        FixedSchedulerPool fixedSchedulerPool = new FixedSchedulerPool(this.threadFactory, MAX_THREADS);
        if (this.pool.compareAndSet(NONE, fixedSchedulerPool)) {
            return;
        }
        fixedSchedulerPool.shutdown();
    }

    @Override // p042rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        FixedSchedulerPool fixedSchedulerPool;
        FixedSchedulerPool fixedSchedulerPool2;
        do {
            fixedSchedulerPool = this.pool.get();
            fixedSchedulerPool2 = NONE;
            if (fixedSchedulerPool == fixedSchedulerPool2) {
                return;
            }
        } while (!this.pool.compareAndSet(fixedSchedulerPool, fixedSchedulerPool2));
        fixedSchedulerPool.shutdown();
    }

    public Subscription scheduleDirect(Action0 action0) {
        return this.pool.get().getEventLoop().scheduleActual(action0, -1L, TimeUnit.NANOSECONDS);
    }

    /* renamed from: rx.internal.schedulers.EventLoopsScheduler$EventLoopWorker */
    /* loaded from: classes6.dex */
    static final class EventLoopWorker extends Scheduler.Worker {
        private final SubscriptionList both;
        private final PoolWorker poolWorker;
        private final SubscriptionList serial;
        private final CompositeSubscription timed;

        EventLoopWorker(PoolWorker poolWorker) {
            SubscriptionList subscriptionList = new SubscriptionList();
            this.serial = subscriptionList;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.timed = compositeSubscription;
            this.both = new SubscriptionList(subscriptionList, compositeSubscription);
            this.poolWorker = poolWorker;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            this.both.unsubscribe();
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.both.isUnsubscribed();
        }

        @Override // p042rx.Scheduler.Worker
        public Subscription schedule(final Action0 action0) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() { // from class: rx.internal.schedulers.EventLoopsScheduler.EventLoopWorker.1
                @Override // p042rx.functions.Action0
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, 0L, (TimeUnit) null, this.serial);
        }

        @Override // p042rx.Scheduler.Worker
        public Subscription schedule(final Action0 action0, long j, TimeUnit timeUnit) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() { // from class: rx.internal.schedulers.EventLoopsScheduler.EventLoopWorker.2
                @Override // p042rx.functions.Action0
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, j, timeUnit, this.timed);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.schedulers.EventLoopsScheduler$PoolWorker */
    /* loaded from: classes6.dex */
    public static final class PoolWorker extends NewThreadWorker {
        PoolWorker(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}
