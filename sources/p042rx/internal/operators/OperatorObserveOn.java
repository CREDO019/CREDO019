package p042rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.functions.Action0;
import p042rx.internal.schedulers.ImmediateScheduler;
import p042rx.internal.schedulers.TrampolineScheduler;
import p042rx.internal.util.RxRingBuffer;
import p042rx.internal.util.atomic.SpscAtomicArrayQueue;
import p042rx.internal.util.unsafe.SpscArrayQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;
import p042rx.plugins.RxJavaHooks;
import p042rx.schedulers.Schedulers;

/* renamed from: rx.internal.operators.OperatorObserveOn */
/* loaded from: classes6.dex */
public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z) {
        this(scheduler, z, RxRingBuffer.SIZE);
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z, int r3) {
        this.scheduler = scheduler;
        this.delayError = z;
        this.bufferSize = r3 <= 0 ? RxRingBuffer.SIZE : r3;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler scheduler = this.scheduler;
        if ((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler)) {
            return subscriber;
        }
        ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(scheduler, subscriber, this.delayError, this.bufferSize);
        observeOnSubscriber.init();
        return observeOnSubscriber;
    }

    public static <T> Observable.Operator<T, T> rebatch(final int r1) {
        return new Observable.Operator<T, T>() { // from class: rx.internal.operators.OperatorObserveOn.1
            @Override // p042rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object obj) {
                return call((Subscriber) ((Subscriber) obj));
            }

            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(Schedulers.immediate(), subscriber, false, r1);
                observeOnSubscriber.init();
                return observeOnSubscriber;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorObserveOn$ObserveOnSubscriber */
    /* loaded from: classes6.dex */
    public static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong counter = new AtomicLong();

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> subscriber, boolean z, int r5) {
            this.child = subscriber;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = z;
            r5 = r5 <= 0 ? RxRingBuffer.SIZE : r5;
            this.limit = r5 - (r5 >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(r5);
            } else {
                this.queue = new SpscAtomicArrayQueue(r5);
            }
            request(r5);
        }

        void init() {
            Subscriber<? super T> subscriber = this.child;
            subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorObserveOn.ObserveOnSubscriber.1
                @Override // p042rx.Producer
                public void request(long j) {
                    if (j > 0) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, j);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            subscriber.add(this.recursiveScheduler);
            subscriber.add(this);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            if (!this.queue.offer(NotificationLite.next(t))) {
                onError(new MissingBackpressureException());
            } else {
                schedule();
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            this.finished = true;
            schedule();
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (isUnsubscribed() || this.finished) {
                RxJavaHooks.onError(th);
                return;
            }
            this.error = th;
            this.finished = true;
            schedule();
        }

        protected void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this);
            }
        }

        @Override // p042rx.functions.Action0
        public void call() {
            int r13;
            long j = this.emitted;
            Queue<Object> queue = this.queue;
            Subscriber<? super T> subscriber = this.child;
            long j2 = 1;
            do {
                long j3 = this.requested.get();
                while (true) {
                    r13 = (j3 > j ? 1 : (j3 == j ? 0 : -1));
                    if (r13 == 0) {
                        break;
                    }
                    boolean z = this.finished;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (checkTerminated(z, z2, subscriber, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext((Object) NotificationLite.getValue(poll));
                    j++;
                    if (j == this.limit) {
                        j3 = BackpressureUtils.produced(this.requested, j);
                        request(j);
                        j = 0;
                    }
                }
                if (r13 == 0 && checkTerminated(this.finished, queue.isEmpty(), subscriber, queue)) {
                    return;
                }
                this.emitted = j;
                j2 = this.counter.addAndGet(-j2);
            } while (j2 != 0);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, Queue<Object> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            } else if (z) {
                if (this.delayError) {
                    if (z2) {
                        Throwable th = this.error;
                        try {
                            if (th != null) {
                                subscriber.onError(th);
                            } else {
                                subscriber.onCompleted();
                            }
                            return false;
                        } finally {
                        }
                    }
                    return false;
                }
                Throwable th2 = this.error;
                if (th2 != null) {
                    queue.clear();
                    try {
                        subscriber.onError(th2);
                        return true;
                    } finally {
                    }
                } else if (z2) {
                    try {
                        subscriber.onCompleted();
                        return true;
                    } finally {
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
