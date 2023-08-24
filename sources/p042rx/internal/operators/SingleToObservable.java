package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Single;
import p042rx.Subscriber;
import p042rx.internal.operators.SingleLiftObservableOperator;

/* renamed from: rx.internal.operators.SingleToObservable */
/* loaded from: classes6.dex */
public final class SingleToObservable<T> implements Observable.OnSubscribe<T> {
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public SingleToObservable(Single.OnSubscribe<T> onSubscribe) {
        this.source = onSubscribe;
    }

    public void call(Subscriber<? super T> subscriber) {
        SingleLiftObservableOperator.WrapSubscriberIntoSingle wrapSubscriberIntoSingle = new SingleLiftObservableOperator.WrapSubscriberIntoSingle(subscriber);
        subscriber.add(wrapSubscriberIntoSingle);
        this.source.call(wrapSubscriberIntoSingle);
    }
}
