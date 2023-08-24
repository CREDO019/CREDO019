package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.RxBleDeviceServices;
import com.polidea.rxandroidble.internal.operations.OperationsProvider;
import com.polidea.rxandroidble.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueue;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Func0;
import p042rx.functions.Func1;
import p042rx.schedulers.Schedulers;
import p042rx.subjects.BehaviorSubject;
import p042rx.subjects.SerializedSubject;

/* JADX INFO: Access modifiers changed from: package-private */
@ConnectionScope
/* loaded from: classes3.dex */
public class ServiceDiscoveryManager {
    private final BluetoothGatt bluetoothGatt;
    private Observable<RxBleDeviceServices> deviceServicesObservable;
    private final OperationsProvider operationProvider;
    private final ConnectionOperationQueue operationQueue;
    private SerializedSubject<TimeoutConfiguration, TimeoutConfiguration> timeoutBehaviorSubject = BehaviorSubject.create().toSerialized();
    private boolean hasCachedResults = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ServiceDiscoveryManager(ConnectionOperationQueue connectionOperationQueue, BluetoothGatt bluetoothGatt, OperationsProvider operationsProvider) {
        this.operationQueue = connectionOperationQueue;
        this.bluetoothGatt = bluetoothGatt;
        this.operationProvider = operationsProvider;
        reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<RxBleDeviceServices> getDiscoverServicesObservable(final long j, final TimeUnit timeUnit) {
        if (this.hasCachedResults) {
            return this.deviceServicesObservable;
        }
        return this.deviceServicesObservable.doOnSubscribe(new Action0() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.1
            @Override // p042rx.functions.Action0
            public void call() {
                ServiceDiscoveryManager.this.timeoutBehaviorSubject.onNext(new TimeoutConfiguration(j, timeUnit, Schedulers.computation()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.hasCachedResults = false;
        this.deviceServicesObservable = Observable.fromCallable(new Func0<List<BluetoothGattService>>() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.6
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public List<BluetoothGattService> call() {
                return ServiceDiscoveryManager.this.bluetoothGatt.getServices();
            }
        }).filter(new Func1<List<BluetoothGattService>, Boolean>() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.5
            @Override // p042rx.functions.Func1
            public Boolean call(List<BluetoothGattService> list) {
                return Boolean.valueOf(list.size() > 0);
            }
        }).map(new Func1<List<BluetoothGattService>, RxBleDeviceServices>() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.4
            @Override // p042rx.functions.Func1
            public RxBleDeviceServices call(List<BluetoothGattService> list) {
                return new RxBleDeviceServices(list);
            }
        }).switchIfEmpty(getTimeoutConfiguration().flatMap(scheduleActualDiscoveryWithTimeout())).doOnNext(new Action1<RxBleDeviceServices>() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.3
            @Override // p042rx.functions.Action1
            public void call(RxBleDeviceServices rxBleDeviceServices) {
                ServiceDiscoveryManager.this.hasCachedResults = true;
            }
        }).doOnError(new Action1<Throwable>() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.2
            @Override // p042rx.functions.Action1
            public void call(Throwable th) {
                ServiceDiscoveryManager.this.reset();
            }
        }).cacheWithInitialCapacity(1);
    }

    private Observable<TimeoutConfiguration> getTimeoutConfiguration() {
        return this.timeoutBehaviorSubject.take(1);
    }

    private Func1<TimeoutConfiguration, Observable<RxBleDeviceServices>> scheduleActualDiscoveryWithTimeout() {
        return new Func1<TimeoutConfiguration, Observable<RxBleDeviceServices>>() { // from class: com.polidea.rxandroidble.internal.connection.ServiceDiscoveryManager.7
            @Override // p042rx.functions.Func1
            public Observable<RxBleDeviceServices> call(TimeoutConfiguration timeoutConfiguration) {
                return ServiceDiscoveryManager.this.operationQueue.queue(ServiceDiscoveryManager.this.operationProvider.provideServiceDiscoveryOperation(timeoutConfiguration.timeout, timeoutConfiguration.timeoutTimeUnit));
            }
        };
    }
}
