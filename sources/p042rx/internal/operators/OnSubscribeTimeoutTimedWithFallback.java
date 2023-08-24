package p042rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.internal.producers.ProducerArbiter;
import p042rx.internal.subscriptions.SequentialSubscription;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OnSubscribeTimeoutTimedWithFallback */
/* loaded from: classes6.dex */
public final class OnSubscribeTimeoutTimedWithFallback<T> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> fallback;
    final Scheduler scheduler;
    final Observable<T> source;
    final long timeout;
    final TimeUnit unit;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeTimeoutTimedWithFallback(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler, Observable<? extends T> observable2) {
        this.source = observable;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.fallback = observable2;
    }

    public void call(Subscriber<? super T> subscriber) {
        TimeoutMainSubscriber timeoutMainSubscriber = new TimeoutMainSubscriber(subscriber, this.timeout, this.unit, this.scheduler.createWorker(), this.fallback);
        subscriber.add(timeoutMainSubscriber.upstream);
        subscriber.setProducer(timeoutMainSubscriber.arbiter);
        timeoutMainSubscriber.startTimeout(0L);
        this.source.subscribe((Subscriber) timeoutMainSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeTimeoutTimedWithFallback$TimeoutMainSubscriber */
    /* loaded from: classes6.dex */
    public static final class TimeoutMainSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        long consumed;
        final Observable<? extends T> fallback;
        final SequentialSubscription task;
        final long timeout;
        final TimeUnit unit;
        final SequentialSubscription upstream;
        final Scheduler.Worker worker;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final AtomicLong index = new AtomicLong();

        TimeoutMainSubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker, Observable<? extends T> observable) {
            this.actual = subscriber;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = worker;
            this.fallback = observable;
            SequentialSubscription sequentialSubscription = new SequentialSubscription();
            this.task = sequentialSubscription;
            this.upstream = new SequentialSubscription(this);
            add(worker);
            add(sequentialSubscription);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = j + 1;
                if (this.index.compareAndSet(j, j2)) {
                    Subscription subscription = this.task.get();
                    if (subscription != null) {
                        subscription.unsubscribe();
                    }
                    this.consumed++;
                    this.actual.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        void startTimeout(long j) {
            this.task.replace(this.worker.schedule(new TimeoutTask(j), this.timeout, this.unit));
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.unsubscribe();
                this.actual.onError(th);
                this.worker.unsubscribe();
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.unsubscribe();
                this.actual.onCompleted();
                this.worker.unsubscribe();
            }
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                unsubscribe();
                if (this.fallback == null) {
                    this.actual.onError(new TimeoutException());
                    return;
                }
                long j2 = this.consumed;
                if (j2 != 0) {
                    this.arbiter.produced(j2);
                }
                FallbackSubscriber fallbackSubscriber = new FallbackSubscriber(this.actual, this.arbiter);
                if (this.upstream.replace(fallbackSubscriber)) {
                    this.fallback.subscribe((Subscriber<? super Object>) fallbackSubscriber);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeTimeoutTimedWithFallback$TimeoutMainSubscriber$TimeoutTask */
        /* loaded from: classes6.dex */
        public final class TimeoutTask implements Action0 {
            final long idx;

            TimeoutTask(long j) {
                this.idx = j;
            }

            @Override // p042rx.functions.Action0
            public void call() {
                TimeoutMainSubscriber.this.onTimeout(this.idx);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeTimeoutTimedWithFallback$FallbackSubscriber */
    /* loaded from: classes6.dex */
    public static final class FallbackSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        final ProducerArbiter arbiter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public FallbackSubscriber(Subscriber<? super T> subscriber, ProducerArbiter producerArbiter) {
            this.actual = subscriber;
            this.arbiter = producerArbiter;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }
    }
}
