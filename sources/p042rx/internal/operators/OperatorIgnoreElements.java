package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OperatorIgnoreElements */
/* loaded from: classes6.dex */
public class OperatorIgnoreElements<T> implements Observable.Operator<T, T> {
    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorIgnoreElements$Holder */
    /* loaded from: classes6.dex */
    public static final class Holder {
        static final OperatorIgnoreElements<?> INSTANCE = new OperatorIgnoreElements<>();

        Holder() {
        }
    }

    public static <T> OperatorIgnoreElements<T> instance() {
        return (OperatorIgnoreElements<T>) Holder.INSTANCE;
    }

    OperatorIgnoreElements() {
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorIgnoreElements.1
            @Override // p042rx.Observer
            public void onNext(T t) {
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }
}
