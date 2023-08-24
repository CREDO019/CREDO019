package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func0;
import p042rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector */
/* loaded from: classes6.dex */
public final class OnSubscribeDelaySubscriptionWithSelector<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> source;
    final Func0<? extends Observable<U>> subscriptionDelay;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> observable, Func0<? extends Observable<U>> func0) {
        this.source = observable;
        this.subscriptionDelay = func0;
    }

    public void call(final Subscriber<? super T> subscriber) {
        try {
            this.subscriptionDelay.call().take(1).unsafeSubscribe((Subscriber<U>) new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector.1
                @Override // p042rx.Observer
                public void onNext(U u) {
                }

                @Override // p042rx.Observer
                public void onCompleted() {
                    OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap(subscriber));
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }
            });
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
