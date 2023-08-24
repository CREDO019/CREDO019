package com.polidea.rxandroidble.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import p042rx.Observable;
import p042rx.functions.Action1;
import p042rx.subscriptions.SerialSubscription;

@ConnectionScope
/* loaded from: classes3.dex */
class MtuWatcher implements ConnectionSubscriptionWatcher, MtuProvider, Action1<Integer> {
    private Integer currentMtu;
    private final Observable<Integer> mtuObservable;
    private final SerialSubscription serialSubscription = new SerialSubscription();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MtuWatcher(RxBleGattCallback rxBleGattCallback, @Named("GATT_MTU_MINIMUM") int r3) {
        this.mtuObservable = rxBleGattCallback.getOnMtuChanged().retry();
        this.currentMtu = Integer.valueOf(r3);
    }

    @Override // com.polidea.rxandroidble.internal.connection.MtuProvider
    public int getMtu() {
        return this.currentMtu.intValue();
    }

    @Override // com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionSubscribed() {
        this.serialSubscription.set(this.mtuObservable.subscribe(this));
    }

    @Override // com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionUnsubscribed() {
        this.serialSubscription.unsubscribe();
    }

    @Override // p042rx.functions.Action1
    public void call(Integer num) {
        this.currentMtu = num;
    }
}
