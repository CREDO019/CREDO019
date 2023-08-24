package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.observers.Subscribers;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorDoOnUnsubscribe */
/* loaded from: classes6.dex */
public class OperatorDoOnUnsubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 unsubscribe;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnUnsubscribe(Action0 action0) {
        this.unsubscribe = action0;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        subscriber.add(Subscriptions.create(this.unsubscribe));
        return Subscribers.wrap(subscriber);
    }
}
