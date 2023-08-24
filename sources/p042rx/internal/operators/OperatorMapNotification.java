package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func0;
import p042rx.functions.Func1;

/* renamed from: rx.internal.operators.OperatorMapNotification */
/* loaded from: classes6.dex */
public final class OperatorMapNotification<T, R> implements Observable.Operator<R, T> {
    final Func0<? extends R> onCompleted;
    final Func1<? super Throwable, ? extends R> onError;
    final Func1<? super T, ? extends R> onNext;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorMapNotification(Func1<? super T, ? extends R> func1, Func1<? super Throwable, ? extends R> func12, Func0<? extends R> func0) {
        this.onNext = func1;
        this.onError = func12;
        this.onCompleted = func0;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        final MapNotificationSubscriber mapNotificationSubscriber = new MapNotificationSubscriber(subscriber, this.onNext, this.onError, this.onCompleted);
        subscriber.add(mapNotificationSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorMapNotification.1
            @Override // p042rx.Producer
            public void request(long j) {
                mapNotificationSubscriber.requestInner(j);
            }
        });
        return mapNotificationSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorMapNotification$MapNotificationSubscriber */
    /* loaded from: classes6.dex */
    public static final class MapNotificationSubscriber<T, R> extends Subscriber<T> {
        static final long COMPLETED_FLAG = Long.MIN_VALUE;
        static final long REQUESTED_MASK = Long.MAX_VALUE;
        final Subscriber<? super R> actual;
        final Func0<? extends R> onCompleted;
        final Func1<? super Throwable, ? extends R> onError;
        final Func1<? super T, ? extends R> onNext;
        long produced;
        R value;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong missedRequested = new AtomicLong();
        final AtomicReference<Producer> producer = new AtomicReference<>();

        public MapNotificationSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1, Func1<? super Throwable, ? extends R> func12, Func0<? extends R> func0) {
            this.actual = subscriber;
            this.onNext = func1;
            this.onError = func12;
            this.onCompleted = func0;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            try {
                this.produced++;
                this.actual.onNext((R) this.onNext.call(t));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.actual, t);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            accountProduced();
            try {
                this.value = this.onError.call(th);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, this.actual, th);
            }
            tryEmit();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            accountProduced();
            try {
                this.value = this.onCompleted.call();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.actual);
            }
            tryEmit();
        }

        void accountProduced() {
            long j = this.produced;
            if (j == 0 || this.producer.get() == null) {
                return;
            }
            BackpressureUtils.produced(this.requested, j);
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            if (this.producer.compareAndSet(null, producer)) {
                long andSet = this.missedRequested.getAndSet(0L);
                if (andSet != 0) {
                    producer.request(andSet);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }

        void tryEmit() {
            long j;
            do {
                j = this.requested.get();
                if ((j & Long.MIN_VALUE) != 0) {
                    return;
                }
            } while (!this.requested.compareAndSet(j, Long.MIN_VALUE | j));
            if (j != 0 || this.producer.get() == null) {
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onNext((R) this.value);
                }
                if (this.actual.isUnsubscribed()) {
                    return;
                }
                this.actual.onCompleted();
            }
        }

        void requestInner(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (r2 == 0) {
            } else {
                while (true) {
                    long j2 = this.requested.get();
                    if ((j2 & Long.MIN_VALUE) != 0) {
                        long j3 = Long.MAX_VALUE & j2;
                        if (this.requested.compareAndSet(j2, Long.MIN_VALUE | BackpressureUtils.addCap(j3, j))) {
                            if (j3 == 0) {
                                if (!this.actual.isUnsubscribed()) {
                                    this.actual.onNext((R) this.value);
                                }
                                if (this.actual.isUnsubscribed()) {
                                    return;
                                }
                                this.actual.onCompleted();
                                return;
                            }
                            return;
                        }
                    } else {
                        if (this.requested.compareAndSet(j2, BackpressureUtils.addCap(j2, j))) {
                            AtomicReference<Producer> atomicReference = this.producer;
                            Producer producer = atomicReference.get();
                            if (producer != null) {
                                producer.request(j);
                                return;
                            }
                            BackpressureUtils.getAndAddRequest(this.missedRequested, j);
                            Producer producer2 = atomicReference.get();
                            if (producer2 != null) {
                                long andSet = this.missedRequested.getAndSet(0L);
                                if (andSet != 0) {
                                    producer2.request(andSet);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}
