package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble.RxBleDeviceServices;
import com.polidea.rxandroidble.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.SingleResponseOperation;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.util.RxBleServicesLogger;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action1;
import p042rx.functions.Func0;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class ServiceDiscoveryOperation extends SingleResponseOperation<RxBleDeviceServices> {
    private final RxBleServicesLogger bleServicesLogger;
    private final BluetoothGatt bluetoothGatt;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceDiscoveryOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, RxBleServicesLogger rxBleServicesLogger, TimeoutConfiguration timeoutConfiguration) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.SERVICE_DISCOVERY, timeoutConfiguration);
        this.bluetoothGatt = bluetoothGatt;
        this.bleServicesLogger = rxBleServicesLogger;
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<RxBleDeviceServices> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnServicesDiscovered().doOnNext(new Action1<RxBleDeviceServices>() { // from class: com.polidea.rxandroidble.internal.operations.ServiceDiscoveryOperation.1
            @Override // p042rx.functions.Action1
            public void call(RxBleDeviceServices rxBleDeviceServices) {
                ServiceDiscoveryOperation.this.bleServicesLogger.log(rxBleDeviceServices, ServiceDiscoveryOperation.this.bluetoothGatt.getDevice());
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.discoverServices();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.polidea.rxandroidble.internal.operations.ServiceDiscoveryOperation$2 */
    /* loaded from: classes3.dex */
    public class C39122 implements Func0<Observable<RxBleDeviceServices>> {
        final /* synthetic */ BluetoothGatt val$bluetoothGatt;
        final /* synthetic */ Scheduler val$timeoutScheduler;

        C39122(BluetoothGatt bluetoothGatt, Scheduler scheduler) {
            this.val$bluetoothGatt = bluetoothGatt;
            this.val$timeoutScheduler = scheduler;
        }

        @Override // p042rx.functions.Func0, java.util.concurrent.Callable
        public Observable<RxBleDeviceServices> call() {
            if (this.val$bluetoothGatt.getServices().size() == 0) {
                return Observable.error(new BleGattCallbackTimeoutException(this.val$bluetoothGatt, BleGattOperationType.SERVICE_DISCOVERY));
            }
            return Observable.timer(5L, TimeUnit.SECONDS, this.val$timeoutScheduler).flatMap(new Func1<Long, Observable<RxBleDeviceServices>>() { // from class: com.polidea.rxandroidble.internal.operations.ServiceDiscoveryOperation.2.1
                @Override // p042rx.functions.Func1
                public Observable<RxBleDeviceServices> call(Long l) {
                    return Observable.fromCallable(new Callable<RxBleDeviceServices>() { // from class: com.polidea.rxandroidble.internal.operations.ServiceDiscoveryOperation.2.1.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.concurrent.Callable
                        public RxBleDeviceServices call() throws Exception {
                            return new RxBleDeviceServices(C39122.this.val$bluetoothGatt.getServices());
                        }
                    });
                }
            });
        }
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<RxBleDeviceServices> timeoutFallbackProcedure(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) {
        return Observable.defer(new C39122(bluetoothGatt, scheduler));
    }
}
