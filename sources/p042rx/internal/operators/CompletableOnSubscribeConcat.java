package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p042rx.Completable;
import p042rx.CompletableSubscriber;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.internal.subscriptions.SequentialSubscription;
import p042rx.internal.util.unsafe.SpscArrayQueue;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.CompletableOnSubscribeConcat */
/* loaded from: classes6.dex */
public final class CompletableOnSubscribeConcat implements Completable.OnSubscribe {
    final int prefetch;
    final Observable<Completable> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeConcat(Observable<? extends Completable> observable, int r2) {
        this.sources = observable;
        this.prefetch = r2;
    }

    @Override // p042rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        CompletableConcatSubscriber completableConcatSubscriber = new CompletableConcatSubscriber(completableSubscriber, this.prefetch);
        completableSubscriber.onSubscribe(completableConcatSubscriber);
        this.sources.unsafeSubscribe(completableConcatSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.CompletableOnSubscribeConcat$CompletableConcatSubscriber */
    /* loaded from: classes6.dex */
    public static final class CompletableConcatSubscriber extends Subscriber<Completable> {
        volatile boolean active;
        final CompletableSubscriber actual;
        volatile boolean done;
        final ConcatInnerSubscriber inner;
        final AtomicBoolean once;
        final SpscArrayQueue<Completable> queue;

        /* renamed from: sr */
        final SequentialSubscription f2550sr;

        public CompletableConcatSubscriber(CompletableSubscriber completableSubscriber, int r3) {
            this.actual = completableSubscriber;
            this.queue = new SpscArrayQueue<>(r3);
            SequentialSubscription sequentialSubscription = new SequentialSubscription();
            this.f2550sr = sequentialSubscription;
            this.inner = new ConcatInnerSubscriber();
            this.once = new AtomicBoolean();
            add(sequentialSubscription);
            request(r3);
        }

        @Override // p042rx.Observer
        public void onNext(Completable completable) {
            if (!this.queue.offer(completable)) {
                onError(new MissingBackpressureException());
            } else {
                drain();
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(th);
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        void innerError(Throwable th) {
            unsubscribe();
            onError(th);
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        void drain() {
            ConcatInnerSubscriber concatInnerSubscriber = this.inner;
            if (concatInnerSubscriber.getAndIncrement() != 0) {
                return;
            }
            while (!isUnsubscribed()) {
                if (!this.active) {
                    boolean z = this.done;
                    Completable poll = this.queue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        this.actual.onCompleted();
                        return;
                    } else if (!z2) {
                        this.active = true;
                        poll.subscribe(concatInnerSubscriber);
                        request(1L);
                    }
                }
                if (concatInnerSubscriber.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.CompletableOnSubscribeConcat$CompletableConcatSubscriber$ConcatInnerSubscriber */
        /* loaded from: classes6.dex */
        public final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
            private static final long serialVersionUID = 7233503139645205620L;

            ConcatInnerSubscriber() {
            }

            @Override // p042rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                CompletableConcatSubscriber.this.f2550sr.set(subscription);
            }

            @Override // p042rx.CompletableSubscriber
            public void onError(Throwable th) {
                CompletableConcatSubscriber.this.innerError(th);
            }

            @Override // p042rx.CompletableSubscriber
            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }
        }
    }
}
