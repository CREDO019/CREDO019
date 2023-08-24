package com.polidea.rxandroidble.internal.util;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.RxBleAdapterStateObservable;
import com.polidea.rxandroidble.RxBleClient;
import java.util.concurrent.TimeUnit;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Single;
import p042rx.Subscription;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.functions.Func1;
import p042rx.internal.operators.OnSubscribeCreate;

/* loaded from: classes3.dex */
public class ClientStateObservable extends Observable<RxBleClient.State> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Inject
    public ClientStateObservable(final RxBleAdapterWrapper rxBleAdapterWrapper, final Observable<RxBleAdapterStateObservable.BleAdapterState> observable, @Named("location-ok-boolean-observable") final Observable<Boolean> observable2, final LocationServicesStatus locationServicesStatus, @Named("timeout") final Scheduler scheduler) {
        super(new OnSubscribeCreate(new Action1<Emitter<RxBleClient.State>>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.1
            @Override // p042rx.functions.Action1
            public void call(Emitter<RxBleClient.State> emitter) {
                if (!RxBleAdapterWrapper.this.hasBluetoothAdapter()) {
                    emitter.onCompleted();
                    return;
                }
                final Subscription subscribe = ClientStateObservable.checkPermissionUntilGranted(locationServicesStatus, scheduler).flatMapObservable(new Func1<Boolean, Observable<RxBleClient.State>>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.1.1
                    @Override // p042rx.functions.Func1
                    public Observable<RxBleClient.State> call(Boolean bool) {
                        return ClientStateObservable.checkAdapterAndServicesState(bool, RxBleAdapterWrapper.this, observable, observable2);
                    }
                }).distinctUntilChanged().subscribe(emitter);
                emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.1.2
                    @Override // p042rx.functions.Cancellable
                    public void cancel() throws Exception {
                        subscribe.unsubscribe();
                    }
                });
            }
        }, Emitter.BackpressureMode.LATEST));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Single<Boolean> checkPermissionUntilGranted(final LocationServicesStatus locationServicesStatus, Scheduler scheduler) {
        return Observable.interval(0L, 1L, TimeUnit.SECONDS, scheduler).map(new Func1<Long, Boolean>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.4
            @Override // p042rx.functions.Func1
            public Boolean call(Long l) {
                return Boolean.valueOf(LocationServicesStatus.this.isLocationPermissionOk());
            }
        }).takeWhile(new Func1<Boolean, Boolean>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.3
            @Override // p042rx.functions.Func1
            public Boolean call(Boolean bool) {
                return Boolean.valueOf(!bool.booleanValue());
            }
        }).count().toSingle().map(new Func1<Integer, Boolean>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.2
            @Override // p042rx.functions.Func1
            public Boolean call(Integer num) {
                return Boolean.valueOf(num.intValue() == 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Observable<RxBleClient.State> checkAdapterAndServicesState(Boolean bool, RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, final Observable<Boolean> observable2) {
        Observable switchMap = observable.startWith((Observable<RxBleAdapterStateObservable.BleAdapterState>) (rxBleAdapterWrapper.isBluetoothEnabled() ? RxBleAdapterStateObservable.BleAdapterState.STATE_ON : RxBleAdapterStateObservable.BleAdapterState.STATE_OFF)).switchMap(new Func1<RxBleAdapterStateObservable.BleAdapterState, Observable<RxBleClient.State>>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.5
            @Override // p042rx.functions.Func1
            public Observable<RxBleClient.State> call(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                if (bleAdapterState != RxBleAdapterStateObservable.BleAdapterState.STATE_ON) {
                    return Observable.just(RxBleClient.State.BLUETOOTH_NOT_ENABLED);
                }
                return Observable.this.map(new Func1<Boolean, RxBleClient.State>() { // from class: com.polidea.rxandroidble.internal.util.ClientStateObservable.5.1
                    @Override // p042rx.functions.Func1
                    public RxBleClient.State call(Boolean bool2) {
                        return bool2.booleanValue() ? RxBleClient.State.READY : RxBleClient.State.LOCATION_SERVICES_NOT_ENABLED;
                    }
                });
            }
        });
        return bool.booleanValue() ? switchMap.skip(1) : switchMap;
    }
}
