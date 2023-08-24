package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.internal.producers.SingleDelayedProducer;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OperatorAll */
/* loaded from: classes6.dex */
public final class OperatorAll<T> implements Observable.Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;

    public OperatorAll(Func1<? super T, Boolean> func1) {
        this.predicate = func1;
    }

    @Override // p042rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorAll.1
            boolean done;

            @Override // p042rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                try {
                    if (OperatorAll.this.predicate.call(t).booleanValue()) {
                        return;
                    }
                    this.done = true;
                    singleDelayedProducer.setValue(false);
                    unsubscribe();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                if (!this.done) {
                    this.done = true;
                    subscriber.onError(th);
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
                singleDelayedProducer.setValue(true);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }
}
