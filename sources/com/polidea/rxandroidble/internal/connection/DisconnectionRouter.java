package com.polidea.rxandroidble.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay.PublishRelay;
import com.polidea.rxandroidble.RxBleAdapterStateObservable;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.exceptions.BleGattException;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import p042rx.Observable;
import p042rx.functions.Func1;

/* JADX INFO: Access modifiers changed from: package-private */
@ConnectionScope
/* loaded from: classes3.dex */
public class DisconnectionRouter implements DisconnectionRouterInput, DisconnectionRouterOutput {
    private final PublishRelay<BleException> disconnectionErrorInputRelay;
    private final Observable<BleException> disconnectionErrorOutputObservable;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DisconnectionRouter(@Named("mac-address") final String str, RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable) {
        PublishRelay<BleException> create = PublishRelay.create();
        this.disconnectionErrorInputRelay = create;
        Observable<BleException> cache = Observable.merge(create, observable.map(new Func1<RxBleAdapterStateObservable.BleAdapterState, Boolean>() { // from class: com.polidea.rxandroidble.internal.connection.DisconnectionRouter.3
            @Override // p042rx.functions.Func1
            public Boolean call(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return Boolean.valueOf(bleAdapterState.isUsable());
            }
        }).startWith((Observable<R>) Boolean.valueOf(rxBleAdapterWrapper.isBluetoothEnabled())).filter(new Func1<Boolean, Boolean>() { // from class: com.polidea.rxandroidble.internal.connection.DisconnectionRouter.2
            @Override // p042rx.functions.Func1
            public Boolean call(Boolean bool) {
                return Boolean.valueOf(!bool.booleanValue());
            }
        }).map(new Func1<Boolean, BleException>() { // from class: com.polidea.rxandroidble.internal.connection.DisconnectionRouter.1
            @Override // p042rx.functions.Func1
            public BleException call(Boolean bool) {
                return BleDisconnectedException.adapterDisabled(str);
            }
        })).first().cache();
        this.disconnectionErrorOutputObservable = cache;
        cache.subscribe();
    }

    @Override // com.polidea.rxandroidble.internal.connection.DisconnectionRouterInput
    public void onDisconnectedException(BleDisconnectedException bleDisconnectedException) {
        this.disconnectionErrorInputRelay.call(bleDisconnectedException);
    }

    @Override // com.polidea.rxandroidble.internal.connection.DisconnectionRouterInput
    public void onGattConnectionStateException(BleGattException bleGattException) {
        this.disconnectionErrorInputRelay.call(bleGattException);
    }

    @Override // com.polidea.rxandroidble.internal.connection.DisconnectionRouterOutput
    public Observable<BleException> asValueOnlyObservable() {
        return this.disconnectionErrorOutputObservable;
    }

    @Override // com.polidea.rxandroidble.internal.connection.DisconnectionRouterOutput
    public <T> Observable<T> asErrorOnlyObservable() {
        return (Observable<T>) this.disconnectionErrorOutputObservable.flatMap(new Func1<BleException, Observable<T>>() { // from class: com.polidea.rxandroidble.internal.connection.DisconnectionRouter.4
            @Override // p042rx.functions.Func1
            public Observable<T> call(BleException bleException) {
                return Observable.error(bleException);
            }
        });
    }
}
