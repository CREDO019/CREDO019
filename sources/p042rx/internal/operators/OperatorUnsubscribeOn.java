package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Producer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorUnsubscribeOn */
/* loaded from: classes6.dex */
public class OperatorUnsubscribeOn<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorUnsubscribeOn(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorUnsubscribeOn.1
            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }

            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(producer);
            }
        };
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorUnsubscribeOn.2
            @Override // p042rx.functions.Action0
            public void call() {
                final Scheduler.Worker createWorker = OperatorUnsubscribeOn.this.scheduler.createWorker();
                createWorker.schedule(new Action0() { // from class: rx.internal.operators.OperatorUnsubscribeOn.2.1
                    @Override // p042rx.functions.Action0
                    public void call() {
                        subscriber2.unsubscribe();
                        createWorker.unsubscribe();
                    }
                });
            }
        }));
        return subscriber2;
    }
}
