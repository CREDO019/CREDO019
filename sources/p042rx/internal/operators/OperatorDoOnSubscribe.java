package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OperatorDoOnSubscribe */
/* loaded from: classes6.dex */
public class OperatorDoOnSubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 subscribe;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnSubscribe(Action0 action0) {
        this.subscribe = action0;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        this.subscribe.call();
        return Subscribers.wrap(subscriber);
    }
}
