package com.jakewharton.rxrelay;

import p042rx.Observable;
import p042rx.Subscriber;

/* loaded from: classes3.dex */
public class SerializedRelay<T, R> extends Relay<T, R> {
    private final SerializedAction1<T> action;
    private final Relay<T, R> actual;

    public SerializedRelay(final Relay<T, R> relay) {
        super(new Observable.OnSubscribe<R>() { // from class: com.jakewharton.rxrelay.SerializedRelay.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super R> subscriber) {
                Relay.this.unsafeSubscribe(subscriber);
            }
        });
        this.actual = relay;
        this.action = new SerializedAction1<>(relay);
    }

    @Override // p042rx.functions.Action1
    public void call(T t) {
        this.action.call(t);
    }

    @Override // com.jakewharton.rxrelay.Relay
    public boolean hasObservers() {
        return this.actual.hasObservers();
    }
}
