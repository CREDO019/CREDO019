package p042rx.observables;

import p042rx.Observable;
import p042rx.Subscription;
import p042rx.functions.Action1;
import p042rx.functions.Actions;
import p042rx.internal.operators.OnSubscribeAutoConnect;
import p042rx.internal.operators.OnSubscribeRefCount;

/* renamed from: rx.observables.ConnectableObservable */
/* loaded from: classes6.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {
    public abstract void connect(Action1<? super Subscription> action1);

    /* JADX INFO: Access modifiers changed from: protected */
    public ConnectableObservable(Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public final Subscription connect() {
        final Subscription[] subscriptionArr = new Subscription[1];
        connect(new Action1<Subscription>() { // from class: rx.observables.ConnectableObservable.1
            @Override // p042rx.functions.Action1
            public void call(Subscription subscription) {
                subscriptionArr[0] = subscription;
            }
        });
        return subscriptionArr[0];
    }

    public Observable<T> refCount() {
        return unsafeCreate(new OnSubscribeRefCount(this));
    }

    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    public Observable<T> autoConnect(int r2) {
        return autoConnect(r2, Actions.empty());
    }

    public Observable<T> autoConnect(int r2, Action1<? super Subscription> action1) {
        if (r2 <= 0) {
            connect(action1);
            return this;
        }
        return unsafeCreate(new OnSubscribeAutoConnect(this, r2, action1));
    }
}
