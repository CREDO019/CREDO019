package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.observers.SerializedSubscriber;

/* renamed from: rx.internal.operators.OperatorTakeUntil */
/* loaded from: classes6.dex */
public final class OperatorTakeUntil<T, E> implements Observable.Operator<T, T> {
    private final Observable<? extends E> other;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeUntil(Observable<? extends E> observable) {
        this.other = observable;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(serializedSubscriber, false) { // from class: rx.internal.operators.OperatorTakeUntil.1
            @Override // p042rx.Observer
            public void onNext(T t) {
                serializedSubscriber.onNext(t);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                try {
                    serializedSubscriber.onError(th);
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                try {
                    serializedSubscriber.onCompleted();
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        Subscriber<E> subscriber3 = new Subscriber<E>() { // from class: rx.internal.operators.OperatorTakeUntil.2
            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber2.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber2.onError(th);
            }

            @Override // p042rx.Observer
            public void onNext(E e) {
                onCompleted();
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        subscriber.add(serializedSubscriber);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
