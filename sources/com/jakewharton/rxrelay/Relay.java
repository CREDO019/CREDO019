package com.jakewharton.rxrelay;

import p042rx.Observable;
import p042rx.functions.Action1;

/* loaded from: classes3.dex */
public abstract class Relay<T, R> extends Observable<R> implements Action1<T> {
    public abstract boolean hasObservers();

    /* JADX INFO: Access modifiers changed from: protected */
    public Relay(Observable.OnSubscribe<R> onSubscribe) {
        super(onSubscribe);
    }

    public Action1<T> asAction() {
        return new Action1<T>() { // from class: com.jakewharton.rxrelay.Relay.1
            @Override // p042rx.functions.Action1
            public void call(T t) {
                Relay.this.call(t);
            }
        };
    }

    public final SerializedRelay<T, R> toSerialized() {
        if (getClass() == SerializedRelay.class) {
            return (SerializedRelay) this;
        }
        return new SerializedRelay<>(this);
    }
}
