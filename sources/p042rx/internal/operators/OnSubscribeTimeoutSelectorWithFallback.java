package p042rx.internal.operators;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.internal.operators.OnSubscribeTimeoutTimedWithFallback;
import p042rx.internal.producers.ProducerArbiter;
import p042rx.internal.subscriptions.SequentialSubscription;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OnSubscribeTimeoutSelectorWithFallback */
/* loaded from: classes6.dex */
public final class OnSubscribeTimeoutSelectorWithFallback<T, U, V> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> fallback;
    final Observable<U> firstTimeoutIndicator;
    final Func1<? super T, ? extends Observable<V>> itemTimeoutIndicator;
    final Observable<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeTimeoutSelectorWithFallback(Observable<T> observable, Observable<U> observable2, Func1<? super T, ? extends Observable<V>> func1, Observable<? extends T> observable3) {
        this.source = observable;
        this.firstTimeoutIndicator = observable2;
        this.itemTimeoutIndicator = func1;
        this.fallback = observable3;
    }

    public void call(Subscriber<? super T> subscriber) {
        TimeoutMainSubscriber timeoutMainSubscriber = new TimeoutMainSubscriber(subscriber, this.itemTimeoutIndicator, this.fallback);
        subscriber.add(timeoutMainSubscriber.upstream);
        subscriber.setProducer(timeoutMainSubscriber.arbiter);
        timeoutMainSubscriber.startFirst(this.firstTimeoutIndicator);
        this.source.subscribe((Subscriber) timeoutMainSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeTimeoutSelectorWithFallback$TimeoutMainSubscriber */
    /* loaded from: classes6.dex */
    public static final class TimeoutMainSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        long consumed;
        final Observable<? extends T> fallback;
        final Func1<? super T, ? extends Observable<?>> itemTimeoutIndicator;
        final SequentialSubscription task;
        final SequentialSubscription upstream;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final AtomicLong index = new AtomicLong();

        TimeoutMainSubscriber(Subscriber<? super T> subscriber, Func1<? super T, ? extends Observable<?>> func1, Observable<? extends T> observable) {
            this.actual = subscriber;
            this.itemTimeoutIndicator = func1;
            this.fallback = observable;
            SequentialSubscription sequentialSubscription = new SequentialSubscription();
            this.task = sequentialSubscription;
            this.upstream = new SequentialSubscription(this);
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
                    this.actual.onNext(t);
                    this.consumed++;
                    try {
                        Observable<?> call = this.itemTimeoutIndicator.call(t);
                        if (call == null) {
                            throw new NullPointerException("The itemTimeoutIndicator returned a null Observable");
                        }
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2);
                        if (this.task.replace(timeoutConsumer)) {
                            call.subscribe((Subscriber<? super Object>) timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        unsubscribe();
                        this.index.getAndSet(Long.MAX_VALUE);
                        this.actual.onError(th);
                    }
                }
            }
        }

        void startFirst(Observable<?> observable) {
            if (observable != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0L);
                if (this.task.replace(timeoutConsumer)) {
                    observable.subscribe((Subscriber<? super Object>) timeoutConsumer);
                }
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.unsubscribe();
                this.actual.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.unsubscribe();
                this.actual.onCompleted();
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
                OnSubscribeTimeoutTimedWithFallback.FallbackSubscriber fallbackSubscriber = new OnSubscribeTimeoutTimedWithFallback.FallbackSubscriber(this.actual, this.arbiter);
                if (this.upstream.replace(fallbackSubscriber)) {
                    this.fallback.subscribe((Subscriber<? super Object>) fallbackSubscriber);
                }
            }
        }

        void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                unsubscribe();
                this.actual.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeTimeoutSelectorWithFallback$TimeoutMainSubscriber$TimeoutConsumer */
        /* loaded from: classes6.dex */
        public final class TimeoutConsumer extends Subscriber<Object> {
            boolean done;
            final long idx;

            TimeoutConsumer(long j) {
                this.idx = j;
            }

            @Override // p042rx.Observer
            public void onNext(Object obj) {
                if (this.done) {
                    return;
                }
                this.done = true;
                unsubscribe();
                TimeoutMainSubscriber.this.onTimeout(this.idx);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                if (!this.done) {
                    this.done = true;
                    TimeoutMainSubscriber.this.onTimeoutError(this.idx, th);
                    return;
                }
                RxJavaHooks.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                TimeoutMainSubscriber.this.onTimeout(this.idx);
            }
        }
    }
}
