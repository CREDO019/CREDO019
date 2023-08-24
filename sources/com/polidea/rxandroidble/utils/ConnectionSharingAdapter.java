package com.polidea.rxandroidble.utils;

import com.polidea.rxandroidble.RxBleConnection;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.functions.Action0;

/* loaded from: classes3.dex */
public class ConnectionSharingAdapter implements Observable.Transformer<RxBleConnection, RxBleConnection> {
    private final AtomicReference<Observable<RxBleConnection>> connectionObservable = new AtomicReference<>();

    @Override // p042rx.functions.Func1
    public Observable<RxBleConnection> call(Observable<RxBleConnection> observable) {
        synchronized (this.connectionObservable) {
            Observable<RxBleConnection> observable2 = this.connectionObservable.get();
            if (observable2 != null) {
                return observable2;
            }
            Observable<RxBleConnection> refCount = observable.doOnUnsubscribe(new Action0() { // from class: com.polidea.rxandroidble.utils.ConnectionSharingAdapter.1
                @Override // p042rx.functions.Action0
                public void call() {
                    ConnectionSharingAdapter.this.connectionObservable.set(null);
                }
            }).replay(1).refCount();
            this.connectionObservable.set(refCount);
            return refCount;
        }
    }
}
