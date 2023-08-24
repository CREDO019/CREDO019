package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.functions.Action1;
import p042rx.observables.ConnectableObservable;
import p042rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OnSubscribeAutoConnect */
/* loaded from: classes6.dex */
public final class OnSubscribeAutoConnect<T> extends AtomicInteger implements Observable.OnSubscribe<T> {
    final Action1<? super Subscription> connection;
    final int numberOfSubscribers;
    final ConnectableObservable<? extends T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeAutoConnect(ConnectableObservable<? extends T> connectableObservable, int r2, Action1<? super Subscription> action1) {
        if (r2 <= 0) {
            throw new IllegalArgumentException("numberOfSubscribers > 0 required");
        }
        this.source = connectableObservable;
        this.numberOfSubscribers = r2;
        this.connection = action1;
    }

    public void call(Subscriber<? super T> subscriber) {
        this.source.unsafeSubscribe(Subscribers.wrap(subscriber));
        if (incrementAndGet() == this.numberOfSubscribers) {
            this.source.connect(this.connection);
        }
    }
}
