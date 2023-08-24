package com.jakewharton.rxrelay;

import com.jakewharton.rxrelay.RelaySubscriptionManager;
import java.lang.reflect.Array;
import p042rx.Observable;
import p042rx.functions.Action1;

/* loaded from: classes3.dex */
public class BehaviorRelay<T> extends Relay<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final RelaySubscriptionManager<T> state;

    public static <T> BehaviorRelay<T> create() {
        return create((Object) null, false);
    }

    public static <T> BehaviorRelay<T> create(T t) {
        return create((Object) t, true);
    }

    private static <T> BehaviorRelay<T> create(T t, boolean z) {
        final RelaySubscriptionManager relaySubscriptionManager = new RelaySubscriptionManager();
        if (z) {
            relaySubscriptionManager.setLatest(NotificationLite.next(t));
        }
        relaySubscriptionManager.onAdded = new Action1<RelaySubscriptionManager.RelayObserver<T>>() { // from class: com.jakewharton.rxrelay.BehaviorRelay.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
            }

            public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
                relayObserver.emitFirst(RelaySubscriptionManager.this.getLatest());
            }
        };
        return new BehaviorRelay<>(relaySubscriptionManager, relaySubscriptionManager);
    }

    protected BehaviorRelay(Observable.OnSubscribe<T> onSubscribe, RelaySubscriptionManager<T> relaySubscriptionManager) {
        super(onSubscribe);
        this.state = relaySubscriptionManager;
    }

    @Override // p042rx.functions.Action1
    public void call(T t) {
        if (this.state.getLatest() == null || this.state.active) {
            Object next = NotificationLite.next(t);
            for (RelaySubscriptionManager.RelayObserver<T> relayObserver : this.state.next(next)) {
                relayObserver.emitNext(next);
            }
        }
    }

    int subscriberCount() {
        return this.state.observers().length;
    }

    @Override // com.jakewharton.rxrelay.Relay
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasValue() {
        return this.state.getLatest() != null;
    }

    public T getValue() {
        Object latest = this.state.getLatest();
        if (latest != null) {
            return (T) NotificationLite.getValue(latest);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] getValues(T[] tArr) {
        Object latest = this.state.getLatest();
        if (latest != null) {
            if (tArr.length == 0) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
            }
            tArr[0] = NotificationLite.getValue(latest);
            if (tArr.length > 1) {
                tArr[1] = null;
            }
        } else if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = EMPTY_ARRAY;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }
}
