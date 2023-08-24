package p042rx.internal.operators;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.internal.util.ExceptionsUtils;
import p042rx.internal.util.atomic.MpscLinkedAtomicQueue;
import p042rx.internal.util.unsafe.MpscLinkedQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.CompositeSubscription;

/* renamed from: rx.internal.operators.OnSubscribeFlatMapSingle */
/* loaded from: classes6.dex */
public final class OnSubscribeFlatMapSingle<T, R> implements Observable.OnSubscribe<R> {
    final boolean delayErrors;
    final Func1<? super T, ? extends Single<? extends R>> mapper;
    final int maxConcurrency;
    final Observable<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFlatMapSingle(Observable<T> observable, Func1<? super T, ? extends Single<? extends R>> func1, boolean z, int r5) {
        Objects.requireNonNull(func1, "mapper is null");
        if (r5 <= 0) {
            throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + r5);
        }
        this.source = observable;
        this.mapper = func1;
        this.delayErrors = z;
        this.maxConcurrency = r5;
    }

    public void call(Subscriber<? super R> subscriber) {
        FlatMapSingleSubscriber flatMapSingleSubscriber = new FlatMapSingleSubscriber(subscriber, this.mapper, this.delayErrors, this.maxConcurrency);
        subscriber.add(flatMapSingleSubscriber.set);
        subscriber.add(flatMapSingleSubscriber.requested);
        subscriber.setProducer(flatMapSingleSubscriber.requested);
        this.source.unsafeSubscribe(flatMapSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeFlatMapSingle$FlatMapSingleSubscriber */
    /* loaded from: classes6.dex */
    public static final class FlatMapSingleSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Func1<? super T, ? extends Single<? extends R>> mapper;
        final int maxConcurrency;
        final Queue<Object> queue;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Throwable> errors = new AtomicReference<>();
        final FlatMapSingleSubscriber<T, R>.Requested requested = new Requested();
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger active = new AtomicInteger();

        FlatMapSingleSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends Single<? extends R>> func1, boolean z, int r4) {
            this.actual = subscriber;
            this.mapper = func1;
            this.delayErrors = z;
            this.maxConcurrency = r4;
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new MpscLinkedQueue();
            } else {
                this.queue = new MpscLinkedAtomicQueue();
            }
            request(r4 != Integer.MAX_VALUE ? r4 : Long.MAX_VALUE);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            try {
                Single<? extends R> call = this.mapper.call(t);
                if (call == null) {
                    throw new NullPointerException("The mapper returned a null Single");
                }
                InnerSubscriber innerSubscriber = new InnerSubscriber();
                this.set.add(innerSubscriber);
                this.active.incrementAndGet();
                call.subscribe(innerSubscriber);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
            } else {
                this.set.unsubscribe();
                if (!this.errors.compareAndSet(null, th)) {
                    RxJavaHooks.onError(th);
                    return;
                }
            }
            this.done = true;
            drain();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void innerSuccess(FlatMapSingleSubscriber<T, R>.InnerSubscriber innerSubscriber, R r) {
            this.queue.offer(NotificationLite.next(r));
            this.set.remove(innerSubscriber);
            this.active.decrementAndGet();
            drain();
        }

        void innerError(FlatMapSingleSubscriber<T, R>.InnerSubscriber innerSubscriber, Throwable th) {
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
                this.set.remove(innerSubscriber);
                if (!this.done && this.maxConcurrency != Integer.MAX_VALUE) {
                    request(1L);
                }
            } else {
                this.set.unsubscribe();
                unsubscribe();
                if (!this.errors.compareAndSet(null, th)) {
                    RxJavaHooks.onError(th);
                    return;
                }
                this.done = true;
            }
            this.active.decrementAndGet();
            drain();
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0082, code lost:
            if (r13 != 0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0086, code lost:
            if (r17.cancelled == false) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0088, code lost:
            r2.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x008b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
            if (r17.done == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:
            if (r3 == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0096, code lost:
            if (r4.get() != 0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x009c, code lost:
            if (r2.isEmpty() == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00a6, code lost:
            if (r17.errors.get() == null) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00a8, code lost:
            r1.onError(p042rx.internal.util.ExceptionsUtils.terminate(r17.errors));
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b2, code lost:
            r1.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00b5, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00be, code lost:
            if (r17.errors.get() == null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00c0, code lost:
            r2.clear();
            r1.onError(p042rx.internal.util.ExceptionsUtils.terminate(r17.errors));
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00cc, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00d1, code lost:
            if (r4.get() != 0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00d7, code lost:
            if (r2.isEmpty() == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00d9, code lost:
            r1.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00dc, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00df, code lost:
            if (r11 == 0) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00e1, code lost:
            r17.requested.produced(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00e8, code lost:
            if (r17.done != false) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00ef, code lost:
            if (r17.maxConcurrency == Integer.MAX_VALUE) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00f1, code lost:
            request(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00f4, code lost:
            r6 = r17.wip.addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 254
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.internal.operators.OnSubscribeFlatMapSingle.FlatMapSingleSubscriber.drain():void");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeFlatMapSingle$FlatMapSingleSubscriber$Requested */
        /* loaded from: classes6.dex */
        public final class Requested extends AtomicLong implements Producer, Subscription {
            private static final long serialVersionUID = -887187595446742742L;

            Requested() {
            }

            @Override // p042rx.Producer
            public void request(long j) {
                if (j > 0) {
                    BackpressureUtils.getAndAddRequest(this, j);
                    FlatMapSingleSubscriber.this.drain();
                }
            }

            void produced(long j) {
                BackpressureUtils.produced(this, j);
            }

            @Override // p042rx.Subscription
            public void unsubscribe() {
                FlatMapSingleSubscriber.this.cancelled = true;
                FlatMapSingleSubscriber.this.unsubscribe();
                if (FlatMapSingleSubscriber.this.wip.getAndIncrement() == 0) {
                    FlatMapSingleSubscriber.this.queue.clear();
                }
            }

            @Override // p042rx.Subscription
            public boolean isUnsubscribed() {
                return FlatMapSingleSubscriber.this.cancelled;
            }
        }

        /* renamed from: rx.internal.operators.OnSubscribeFlatMapSingle$FlatMapSingleSubscriber$InnerSubscriber */
        /* loaded from: classes6.dex */
        final class InnerSubscriber extends SingleSubscriber<R> {
            InnerSubscriber() {
            }

            @Override // p042rx.SingleSubscriber
            public void onSuccess(R r) {
                FlatMapSingleSubscriber.this.innerSuccess(this, r);
            }

            @Override // p042rx.SingleSubscriber
            public void onError(Throwable th) {
                FlatMapSingleSubscriber.this.innerError(this, th);
            }
        }
    }
}
