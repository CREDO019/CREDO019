package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Producer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.functions.Action0;

/* renamed from: rx.internal.operators.OperatorSubscribeOn */
/* loaded from: classes6.dex */
public final class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    final boolean requestOn;
    final Scheduler scheduler;
    final Observable<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSubscribeOn(Observable<T> observable, Scheduler scheduler, boolean z) {
        this.scheduler = scheduler;
        this.source = observable;
        this.requestOn = z;
    }

    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        SubscribeOnSubscriber subscribeOnSubscriber = new SubscribeOnSubscriber(subscriber, this.requestOn, createWorker, this.source);
        subscriber.add(subscribeOnSubscriber);
        subscriber.add(createWorker);
        createWorker.schedule(subscribeOnSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorSubscribeOn$SubscribeOnSubscriber */
    /* loaded from: classes6.dex */
    public static final class SubscribeOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> actual;
        final boolean requestOn;
        Observable<T> source;

        /* renamed from: t */
        Thread f2566t;
        final Scheduler.Worker worker;

        SubscribeOnSubscriber(Subscriber<? super T> subscriber, boolean z, Scheduler.Worker worker, Observable<T> observable) {
            this.actual = subscriber;
            this.requestOn = z;
            this.worker = worker;
            this.source = observable;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            try {
                this.actual.onError(th);
            } finally {
                this.worker.unsubscribe();
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            try {
                this.actual.onCompleted();
            } finally {
                this.worker.unsubscribe();
            }
        }

        @Override // p042rx.functions.Action0
        public void call() {
            Observable<T> observable = this.source;
            this.source = null;
            this.f2566t = Thread.currentThread();
            observable.unsafeSubscribe(this);
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(final Producer producer) {
            this.actual.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSubscribeOn.SubscribeOnSubscriber.1
                @Override // p042rx.Producer
                public void request(final long j) {
                    if (SubscribeOnSubscriber.this.f2566t == Thread.currentThread() || !SubscribeOnSubscriber.this.requestOn) {
                        producer.request(j);
                    } else {
                        SubscribeOnSubscriber.this.worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorSubscribeOn.SubscribeOnSubscriber.1.1
                            @Override // p042rx.functions.Action0
                            public void call() {
                                producer.request(j);
                            }
                        });
                    }
                }
            });
        }
    }
}
