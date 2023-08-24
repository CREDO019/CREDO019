package p042rx.internal.util;

import p042rx.Observer;
import p042rx.Subscriber;

/* renamed from: rx.internal.util.ObserverSubscriber */
/* loaded from: classes6.dex */
public final class ObserverSubscriber<T> extends Subscriber<T> {
    final Observer<? super T> observer;

    public ObserverSubscriber(Observer<? super T> observer) {
        this.observer = observer;
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.observer.onNext(t);
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.observer.onCompleted();
    }
}
