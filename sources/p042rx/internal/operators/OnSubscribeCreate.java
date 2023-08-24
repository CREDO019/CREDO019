package p042rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.internal.subscriptions.CancellableSubscription;
import p042rx.internal.util.RxRingBuffer;
import p042rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import p042rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.OnSubscribeCreate */
/* loaded from: classes6.dex */
public final class OnSubscribeCreate<T> implements Observable.OnSubscribe<T> {
    final Action1<Emitter<T>> Emitter;
    final Emitter.BackpressureMode backpressure;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeCreate(Action1<Emitter<T>> action1, Emitter.BackpressureMode backpressureMode) {
        this.Emitter = action1;
        this.backpressure = backpressureMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$1 */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class C55761 {
        static final /* synthetic */ int[] $SwitchMap$rx$Emitter$BackpressureMode;

        static {
            int[] r0 = new int[Emitter.BackpressureMode.values().length];
            $SwitchMap$rx$Emitter$BackpressureMode = r0;
            try {
                r0[Emitter.BackpressureMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$rx$Emitter$BackpressureMode[Emitter.BackpressureMode.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$rx$Emitter$BackpressureMode[Emitter.BackpressureMode.DROP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$rx$Emitter$BackpressureMode[Emitter.BackpressureMode.LATEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void call(Subscriber<? super T> subscriber) {
        BaseEmitter noneEmitter;
        int r0 = C55761.$SwitchMap$rx$Emitter$BackpressureMode[this.backpressure.ordinal()];
        if (r0 == 1) {
            noneEmitter = new NoneEmitter(subscriber);
        } else if (r0 == 2) {
            noneEmitter = new ErrorEmitter(subscriber);
        } else if (r0 == 3) {
            noneEmitter = new DropEmitter(subscriber);
        } else if (r0 == 4) {
            noneEmitter = new LatestEmitter(subscriber);
        } else {
            noneEmitter = new BufferEmitter(subscriber, RxRingBuffer.SIZE);
        }
        subscriber.add(noneEmitter);
        subscriber.setProducer(noneEmitter);
        this.Emitter.call(noneEmitter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$BaseEmitter */
    /* loaded from: classes6.dex */
    public static abstract class BaseEmitter<T> extends AtomicLong implements Emitter<T>, Producer, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> actual;
        final SerialSubscription serial = new SerialSubscription();

        void onRequested() {
        }

        void onUnsubscribed() {
        }

        public BaseEmitter(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            try {
                this.actual.onCompleted();
            } finally {
                this.serial.unsubscribe();
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            try {
                this.actual.onError(th);
            } finally {
                this.serial.unsubscribe();
            }
        }

        @Override // p042rx.Subscription
        public final void unsubscribe() {
            this.serial.unsubscribe();
            onUnsubscribed();
        }

        @Override // p042rx.Subscription
        public final boolean isUnsubscribed() {
            return this.serial.isUnsubscribed();
        }

        @Override // p042rx.Producer
        public final void request(long j) {
            if (BackpressureUtils.validate(j)) {
                BackpressureUtils.getAndAddRequest(this, j);
                onRequested();
            }
        }

        @Override // p042rx.Emitter
        public final void setSubscription(Subscription subscription) {
            this.serial.set(subscription);
        }

        @Override // p042rx.Emitter
        public final void setCancellation(Cancellable cancellable) {
            setSubscription(new CancellableSubscription(cancellable));
        }

        @Override // p042rx.Emitter
        public final long requested() {
            return get();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$NoneEmitter */
    /* loaded from: classes6.dex */
    public static final class NoneEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        public NoneEmitter(Subscriber<? super T> subscriber) {
            super(subscriber);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            long j;
            if (this.actual.isUnsubscribed()) {
                return;
            }
            this.actual.onNext(t);
            do {
                j = get();
                if (j == 0) {
                    return;
                }
            } while (!compareAndSet(j, j - 1));
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeCreate$NoOverflowBaseEmitter */
    /* loaded from: classes6.dex */
    static abstract class NoOverflowBaseEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        public NoOverflowBaseEmitter(Subscriber<? super T> subscriber) {
            super(subscriber);
        }

        public void onNext(T t) {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            if (get() != 0) {
                this.actual.onNext(t);
                BackpressureUtils.produced(this, 1L);
                return;
            }
            onOverflow();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$DropEmitter */
    /* loaded from: classes6.dex */
    public static final class DropEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        @Override // p042rx.internal.operators.OnSubscribeCreate.NoOverflowBaseEmitter
        void onOverflow() {
        }

        public DropEmitter(Subscriber<? super T> subscriber) {
            super(subscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$ErrorEmitter */
    /* loaded from: classes6.dex */
    public static final class ErrorEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;
        private boolean done;

        public ErrorEmitter(Subscriber<? super T> subscriber) {
            super(subscriber);
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.NoOverflowBaseEmitter, p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            super.onNext(t);
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter, p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            super.onCompleted();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter, p042rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.done = true;
            super.onError(th);
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.NoOverflowBaseEmitter
        void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$BufferEmitter */
    /* loaded from: classes6.dex */
    public static final class BufferEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final Queue<Object> queue;
        final AtomicInteger wip;

        public BufferEmitter(Subscriber<? super T> subscriber, int r2) {
            super(subscriber);
            this.queue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(r2) : new SpscUnboundedAtomicArrayQueue<>(r2);
            this.wip = new AtomicInteger();
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.queue.offer(NotificationLite.next(t));
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter, p042rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter, p042rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            int r10;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.actual;
            Queue<Object> queue = this.queue;
            int r3 = 1;
            do {
                long j = get();
                long j2 = 0;
                while (true) {
                    r10 = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (r10 == 0) {
                        break;
                    } else if (subscriber.isUnsubscribed()) {
                        queue.clear();
                        return;
                    } else {
                        boolean z = this.done;
                        Object poll = queue.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            Throwable th = this.error;
                            if (th != null) {
                                super.onError(th);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (z2) {
                            break;
                        } else {
                            subscriber.onNext((Object) NotificationLite.getValue(poll));
                            j2++;
                        }
                    }
                }
                if (r10 == 0) {
                    if (subscriber.isUnsubscribed()) {
                        queue.clear();
                        return;
                    }
                    boolean z3 = this.done;
                    boolean isEmpty = queue.isEmpty();
                    if (z3 && isEmpty) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            super.onError(th2);
                            return;
                        } else {
                            super.onCompleted();
                            return;
                        }
                    }
                }
                if (j2 != 0) {
                    BackpressureUtils.produced(this, j2);
                }
                r3 = this.wip.addAndGet(-r3);
            } while (r3 != 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCreate$LatestEmitter */
    /* loaded from: classes6.dex */
    public static final class LatestEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<Object> queue;
        final AtomicInteger wip;

        public LatestEmitter(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.queue = new AtomicReference<>();
            this.wip = new AtomicInteger();
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.queue.set(NotificationLite.next(t));
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter, p042rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter, p042rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // p042rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        void drain() {
            int r13;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.actual;
            AtomicReference<Object> atomicReference = this.queue;
            int r4 = 1;
            do {
                long j = get();
                long j2 = 0;
                while (true) {
                    r13 = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (r13 == 0) {
                        break;
                    } else if (subscriber.isUnsubscribed()) {
                        atomicReference.lazySet(null);
                        return;
                    } else {
                        boolean z = this.done;
                        Object andSet = atomicReference.getAndSet(null);
                        boolean z2 = andSet == null;
                        if (z && z2) {
                            Throwable th = this.error;
                            if (th != null) {
                                super.onError(th);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (z2) {
                            break;
                        } else {
                            subscriber.onNext((Object) NotificationLite.getValue(andSet));
                            j2++;
                        }
                    }
                }
                if (r13 == 0) {
                    if (subscriber.isUnsubscribed()) {
                        atomicReference.lazySet(null);
                        return;
                    }
                    boolean z3 = this.done;
                    boolean z4 = atomicReference.get() == null;
                    if (z3 && z4) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            super.onError(th2);
                            return;
                        } else {
                            super.onCompleted();
                            return;
                        }
                    }
                }
                if (j2 != 0) {
                    BackpressureUtils.produced(this, j2);
                }
                r4 = this.wip.addAndGet(-r4);
            } while (r4 != 0);
        }
    }
}
