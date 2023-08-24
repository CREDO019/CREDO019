package com.jakewharton.rxrelay;

import com.jakewharton.rxrelay.RelaySubscriptionManager;
import p042rx.Observable;

/* loaded from: classes3.dex */
public class PublishRelay<T> extends Relay<T, T> {
    private final RelaySubscriptionManager<T> state;

    public static <T> PublishRelay<T> create() {
        RelaySubscriptionManager relaySubscriptionManager = new RelaySubscriptionManager();
        return new PublishRelay<>(relaySubscriptionManager, relaySubscriptionManager);
    }

    protected PublishRelay(Observable.OnSubscribe<T> onSubscribe, RelaySubscriptionManager<T> relaySubscriptionManager) {
        super(onSubscribe);
        this.state = relaySubscriptionManager;
    }

    @Override // p042rx.functions.Action1
    public void call(T t) {
        for (RelaySubscriptionManager.RelayObserver<T> relayObserver : this.state.observers()) {
            relayObserver.onNext(t);
        }
    }

    @Override // com.jakewharton.rxrelay.Relay
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }
}
