package p042rx.observers;

import p042rx.Observer;
import p042rx.Subscriber;

/* renamed from: rx.observers.SerializedSubscriber */
/* loaded from: classes6.dex */
public class SerializedSubscriber<T> extends Subscriber<T> {

    /* renamed from: s */
    private final Observer<T> f2588s;

    public SerializedSubscriber(Subscriber<? super T> subscriber) {
        this(subscriber, true);
    }

    public SerializedSubscriber(Subscriber<? super T> subscriber, boolean z) {
        super(subscriber, z);
        this.f2588s = new SerializedObserver(subscriber);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.f2588s.onCompleted();
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.f2588s.onError(th);
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.f2588s.onNext(t);
    }
}
