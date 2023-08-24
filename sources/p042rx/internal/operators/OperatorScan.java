package p042rx.internal.operators;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func0;
import p042rx.functions.Func2;
import p042rx.internal.util.atomic.SpscLinkedAtomicQueue;
import p042rx.internal.util.unsafe.SpscLinkedQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;

/* renamed from: rx.internal.operators.OperatorScan */
/* loaded from: classes6.dex */
public final class OperatorScan<R, T> implements Observable.Operator<R, T> {
    private static final Object NO_INITIAL_VALUE = new Object();
    final Func2<R, ? super T, R> accumulator;
    private final Func0<R> initialValueFactory;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorScan(final R r, Func2<R, ? super T, R> func2) {
        this((Func0) new Func0<R>() { // from class: rx.internal.operators.OperatorScan.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public R call() {
                return (R) r;
            }
        }, (Func2) func2);
    }

    public OperatorScan(Func0<R> func0, Func2<R, ? super T, R> func2) {
        this.initialValueFactory = func0;
        this.accumulator = func2;
    }

    public OperatorScan(Func2<R, ? super T, R> func2) {
        this(NO_INITIAL_VALUE, func2);
    }

    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        R call = this.initialValueFactory.call();
        if (call == NO_INITIAL_VALUE) {
            return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorScan.2
                boolean once;
                R value;

                @Override // p042rx.Observer
                public void onNext(T t) {
                    R r;
                    if (!this.once) {
                        this.once = true;
                        r = t;
                    } else {
                        try {
                            r = OperatorScan.this.accumulator.call(this.value, t);
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber, t);
                            return;
                        }
                    }
                    this.value = r;
                    subscriber.onNext(r);
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }

                @Override // p042rx.Observer
                public void onCompleted() {
                    subscriber.onCompleted();
                }
            };
        }
        InitialProducer initialProducer = new InitialProducer(call, subscriber);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(call, initialProducer) { // from class: rx.internal.operators.OperatorScan.3
            final /* synthetic */ Object val$initialValue;
            final /* synthetic */ InitialProducer val$ip;
            private R value;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.val$initialValue = call;
                this.val$ip = initialProducer;
                this.value = call;
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                try {
                    R call2 = OperatorScan.this.accumulator.call(this.value, t);
                    this.value = call2;
                    this.val$ip.onNext(call2);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                this.val$ip.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                this.val$ip.onCompleted();
            }

            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                this.val$ip.setProducer(producer);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(initialProducer);
        return subscriber2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorScan$InitialProducer */
    /* loaded from: classes6.dex */
    public static final class InitialProducer<R> implements Producer, Observer<R> {
        final Subscriber<? super R> child;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        long missedRequested;
        volatile Producer producer;
        final Queue<Object> queue;
        final AtomicLong requested;

        public InitialProducer(R r, Subscriber<? super R> subscriber) {
            Queue<Object> spscLinkedAtomicQueue;
            this.child = subscriber;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscLinkedAtomicQueue = new SpscLinkedQueue<>();
            } else {
                spscLinkedAtomicQueue = new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedAtomicQueue;
            spscLinkedAtomicQueue.offer(NotificationLite.next(r));
            this.requested = new AtomicLong();
        }

        @Override // p042rx.Observer
        public void onNext(R r) {
            this.queue.offer(NotificationLite.next(r));
            emit();
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super R> subscriber) {
            if (subscriber.isUnsubscribed()) {
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    subscriber.onError(th);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            emit();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (r2 != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                Producer producer = this.producer;
                if (producer == null) {
                    synchronized (this.requested) {
                        producer = this.producer;
                        if (producer == null) {
                            this.missedRequested = BackpressureUtils.addCap(this.missedRequested, j);
                        }
                    }
                }
                if (producer != null) {
                    producer.request(j);
                }
                emit();
            }
        }

        public void setProducer(Producer producer) {
            long j;
            Objects.requireNonNull(producer);
            synchronized (this.requested) {
                if (this.producer != null) {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
                j = this.missedRequested;
                if (j != Long.MAX_VALUE) {
                    j--;
                }
                this.missedRequested = 0L;
                this.producer = producer;
            }
            if (j > 0) {
                producer.request(j);
            }
            emit();
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        void emitLoop() {
            Subscriber<? super R> subscriber = this.child;
            Queue<Object> queue = this.queue;
            AtomicLong atomicLong = this.requested;
            long j = atomicLong.get();
            while (!checkTerminated(this.done, queue.isEmpty(), subscriber)) {
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (checkTerminated(z, z2, subscriber)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    Object obj = (Object) NotificationLite.getValue(poll);
                    try {
                        subscriber.onNext(obj);
                        j2++;
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, obj);
                        return;
                    }
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    j = BackpressureUtils.produced(atomicLong, j2);
                }
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
                        return;
                    }
                    this.missed = false;
                }
            }
        }
    }
}
