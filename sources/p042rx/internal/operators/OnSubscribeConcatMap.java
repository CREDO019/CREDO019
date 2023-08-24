package p042rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.functions.Func1;
import p042rx.internal.producers.ProducerArbiter;
import p042rx.internal.util.ExceptionsUtils;
import p042rx.internal.util.ScalarSynchronousObservable;
import p042rx.internal.util.atomic.SpscAtomicArrayQueue;
import p042rx.internal.util.unsafe.SpscArrayQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;
import p042rx.observers.SerializedSubscriber;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.OnSubscribeConcatMap */
/* loaded from: classes6.dex */
public final class OnSubscribeConcatMap<T, R> implements Observable.OnSubscribe<R> {
    public static final int BOUNDARY = 1;
    public static final int END = 2;
    public static final int IMMEDIATE = 0;
    final int delayErrorMode;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeConcatMap(Observable<? extends T> observable, Func1<? super T, ? extends Observable<? extends R>> func1, int r3, int r4) {
        this.source = observable;
        this.mapper = func1;
        this.prefetch = r3;
        this.delayErrorMode = r4;
    }

    public void call(Subscriber<? super R> subscriber) {
        final ConcatMapSubscriber concatMapSubscriber = new ConcatMapSubscriber(this.delayErrorMode == 0 ? new SerializedSubscriber<>(subscriber) : subscriber, this.mapper, this.prefetch, this.delayErrorMode);
        subscriber.add(concatMapSubscriber);
        subscriber.add(concatMapSubscriber.inner);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeConcatMap.1
            @Override // p042rx.Producer
            public void request(long j) {
                concatMapSubscriber.requestMore(j);
            }
        });
        if (subscriber.isUnsubscribed()) {
            return;
        }
        this.source.unsafeSubscribe(concatMapSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeConcatMap$ConcatMapSubscriber */
    /* loaded from: classes6.dex */
    public static final class ConcatMapSubscriber<T, R> extends Subscriber<T> {
        volatile boolean active;
        final Subscriber<? super R> actual;
        final int delayErrorMode;
        volatile boolean done;
        final SerialSubscription inner;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        final Queue<Object> queue;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        public ConcatMapSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends Observable<? extends R>> func1, int r3, int r4) {
            Queue<Object> spscAtomicArrayQueue;
            this.actual = subscriber;
            this.mapper = func1;
            this.delayErrorMode = r4;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscAtomicArrayQueue = new SpscArrayQueue<>(r3);
            } else {
                spscAtomicArrayQueue = new SpscAtomicArrayQueue<>(r3);
            }
            this.queue = spscAtomicArrayQueue;
            this.inner = new SerialSubscription();
            request(r3);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(NotificationLite.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (ExceptionsUtils.addThrowable(this.error, th)) {
                this.done = true;
                if (this.delayErrorMode == 0) {
                    Throwable terminate = ExceptionsUtils.terminate(this.error);
                    if (!ExceptionsUtils.isTerminated(terminate)) {
                        this.actual.onError(terminate);
                    }
                    this.inner.unsubscribe();
                    return;
                }
                drain();
                return;
            }
            pluginError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void requestMore(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 > 0) {
                this.arbiter.request(j);
            } else if (r2 >= 0) {
            } else {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
        }

        void innerNext(R r) {
            this.actual.onNext(r);
        }

        void innerError(Throwable th, long j) {
            if (!ExceptionsUtils.addThrowable(this.error, th)) {
                pluginError(th);
            } else if (this.delayErrorMode == 0) {
                Throwable terminate = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(terminate)) {
                    this.actual.onError(terminate);
                }
                unsubscribe();
            } else {
                if (j != 0) {
                    this.arbiter.produced(j);
                }
                this.active = false;
                drain();
            }
        }

        void innerCompleted(long j) {
            if (j != 0) {
                this.arbiter.produced(j);
            }
            this.active = false;
            drain();
        }

        void pluginError(Throwable th) {
            RxJavaHooks.onError(th);
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int r0 = this.delayErrorMode;
            while (!this.actual.isUnsubscribed()) {
                if (!this.active) {
                    if (r0 == 1 && this.error.get() != null) {
                        Throwable terminate = ExceptionsUtils.terminate(this.error);
                        if (ExceptionsUtils.isTerminated(terminate)) {
                            return;
                        }
                        this.actual.onError(terminate);
                        return;
                    }
                    boolean z = this.done;
                    Object poll = this.queue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable terminate2 = ExceptionsUtils.terminate(this.error);
                        if (terminate2 == null) {
                            this.actual.onCompleted();
                            return;
                        } else if (ExceptionsUtils.isTerminated(terminate2)) {
                            return;
                        } else {
                            this.actual.onError(terminate2);
                            return;
                        }
                    } else if (!z2) {
                        try {
                            Observable<? extends R> call = this.mapper.call((Object) NotificationLite.getValue(poll));
                            if (call == null) {
                                drainError(new NullPointerException("The source returned by the mapper was null"));
                                return;
                            } else if (call != Observable.empty()) {
                                if (call instanceof ScalarSynchronousObservable) {
                                    this.active = true;
                                    this.arbiter.setProducer(new ConcatMapInnerScalarProducer(((ScalarSynchronousObservable) call).get(), this));
                                } else {
                                    ConcatMapInnerSubscriber concatMapInnerSubscriber = new ConcatMapInnerSubscriber(this);
                                    this.inner.set(concatMapInnerSubscriber);
                                    if (concatMapInnerSubscriber.isUnsubscribed()) {
                                        return;
                                    }
                                    this.active = true;
                                    call.unsafeSubscribe(concatMapInnerSubscriber);
                                }
                                request(1L);
                            } else {
                                request(1L);
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            drainError(th);
                            return;
                        }
                    }
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        void drainError(Throwable th) {
            unsubscribe();
            if (ExceptionsUtils.addThrowable(this.error, th)) {
                Throwable terminate = ExceptionsUtils.terminate(this.error);
                if (ExceptionsUtils.isTerminated(terminate)) {
                    return;
                }
                this.actual.onError(terminate);
                return;
            }
            pluginError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeConcatMap$ConcatMapInnerSubscriber */
    /* loaded from: classes6.dex */
    public static final class ConcatMapInnerSubscriber<T, R> extends Subscriber<R> {
        final ConcatMapSubscriber<T, R> parent;
        long produced;

        public ConcatMapInnerSubscriber(ConcatMapSubscriber<T, R> concatMapSubscriber) {
            this.parent = concatMapSubscriber;
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.parent.arbiter.setProducer(producer);
        }

        @Override // p042rx.Observer
        public void onNext(R r) {
            this.produced++;
            this.parent.innerNext(r);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.parent.innerError(th, this.produced);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.parent.innerCompleted(this.produced);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeConcatMap$ConcatMapInnerScalarProducer */
    /* loaded from: classes6.dex */
    public static final class ConcatMapInnerScalarProducer<T, R> implements Producer {
        boolean once;
        final ConcatMapSubscriber<T, R> parent;
        final R value;

        public ConcatMapInnerScalarProducer(R r, ConcatMapSubscriber<T, R> concatMapSubscriber) {
            this.value = r;
            this.parent = concatMapSubscriber;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            if (this.once || j <= 0) {
                return;
            }
            this.once = true;
            ConcatMapSubscriber<T, R> concatMapSubscriber = this.parent;
            concatMapSubscriber.innerNext(this.value);
            concatMapSubscriber.innerCompleted(1L);
        }
    }
}
