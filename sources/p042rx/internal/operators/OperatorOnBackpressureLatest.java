package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;

/* renamed from: rx.internal.operators.OperatorOnBackpressureLatest */
/* loaded from: classes6.dex */
public final class OperatorOnBackpressureLatest<T> implements Observable.Operator<T, T> {
    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureLatest$Holder */
    /* loaded from: classes6.dex */
    public static final class Holder {
        static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest<>();

        Holder() {
        }
    }

    public static <T> OperatorOnBackpressureLatest<T> instance() {
        return (OperatorOnBackpressureLatest<T>) Holder.INSTANCE;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        LatestEmitter latestEmitter = new LatestEmitter(subscriber);
        LatestSubscriber<? super T> latestSubscriber = new LatestSubscriber<>(latestEmitter);
        latestEmitter.parent = latestSubscriber;
        subscriber.add(latestSubscriber);
        subscriber.add(latestEmitter);
        subscriber.setProducer(latestEmitter);
        return latestSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureLatest$LatestEmitter */
    /* loaded from: classes6.dex */
    public static final class LatestEmitter<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        static final Object EMPTY = new Object();
        static final long NOT_REQUESTED = -4611686018427387904L;
        private static final long serialVersionUID = -1364393685005146274L;
        final Subscriber<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        LatestSubscriber<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value = new AtomicReference<>(EMPTY);

        public LatestEmitter(Subscriber<? super T> subscriber) {
            this.child = subscriber;
            lazySet(NOT_REQUESTED);
        }

        @Override // p042rx.Producer
        public void request(long j) {
            long j2;
            int r8;
            long j3;
            if (j >= 0) {
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    r8 = (j2 > NOT_REQUESTED ? 1 : (j2 == NOT_REQUESTED ? 0 : -1));
                    if (r8 == 0) {
                        j3 = j;
                    } else {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = Long.MAX_VALUE;
                        }
                    }
                } while (!compareAndSet(j2, j3));
                if (r8 == 0) {
                    this.parent.requestMore(Long.MAX_VALUE);
                }
                emit();
            }
        }

        long produced(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 < 0) {
                    return j2;
                }
                j3 = j2 - j;
            } while (!compareAndSet(j2, j3));
            return j3;
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.value.lazySet(t);
            emit();
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.terminal = th;
            this.done = true;
            emit();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        void emit() {
            boolean z;
            Object obj;
            synchronized (this) {
                boolean z2 = true;
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                this.missed = false;
                while (true) {
                    try {
                        long j = get();
                        if (j == Long.MIN_VALUE) {
                            return;
                        }
                        Object obj2 = this.value.get();
                        if (j > 0 && obj2 != (obj = EMPTY)) {
                            this.child.onNext(obj2);
                            this.value.compareAndSet(obj2, obj);
                            produced(1L);
                            obj2 = obj;
                        }
                        if (obj2 == EMPTY && this.done) {
                            Throwable th = this.terminal;
                            if (th != null) {
                                this.child.onError(th);
                            } else {
                                this.child.onCompleted();
                            }
                        }
                        try {
                            synchronized (this) {
                                try {
                                    if (!this.missed) {
                                        this.emitting = false;
                                        return;
                                    }
                                    this.missed = false;
                                } catch (Throwable th2) {
                                    th = th2;
                                    z2 = false;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            z = z2;
                            th = th4;
                            if (!z) {
                                synchronized (this) {
                                    this.emitting = false;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        z = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureLatest$LatestSubscriber */
    /* loaded from: classes6.dex */
    public static final class LatestSubscriber<T> extends Subscriber<T> {
        private final LatestEmitter<T> producer;

        LatestSubscriber(LatestEmitter<T> latestEmitter) {
            this.producer = latestEmitter;
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void onStart() {
            request(0L);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.producer.onNext(t);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.producer.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.producer.onCompleted();
        }

        void requestMore(long j) {
            request(j);
        }
    }
}
