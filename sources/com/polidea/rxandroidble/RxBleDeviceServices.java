package com.polidea.rxandroidble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.polidea.rxandroidble.exceptions.BleCharacteristicNotFoundException;
import com.polidea.rxandroidble.exceptions.BleServiceNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import p042rx.Observable;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class RxBleDeviceServices {
    private final List<BluetoothGattService> bluetoothGattServices;

    public RxBleDeviceServices(List<BluetoothGattService> list) {
        this.bluetoothGattServices = list;
    }

    public List<BluetoothGattService> getBluetoothGattServices() {
        return this.bluetoothGattServices;
    }

    public Observable<BluetoothGattService> getService(final UUID r3) {
        return Observable.from(this.bluetoothGattServices).takeFirst(new Func1<BluetoothGattService, Boolean>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.1
            @Override // p042rx.functions.Func1
            public Boolean call(BluetoothGattService bluetoothGattService) {
                return Boolean.valueOf(bluetoothGattService.getUuid().equals(r3));
            }
        }).switchIfEmpty(Observable.error(new BleServiceNotFoundException(r3)));
    }

    public Observable<BluetoothGattCharacteristic> getCharacteristic(final UUID r2) {
        return Observable.fromCallable(new Callable<BluetoothGattCharacteristic>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public BluetoothGattCharacteristic call() throws Exception {
                for (BluetoothGattService bluetoothGattService : RxBleDeviceServices.this.bluetoothGattServices) {
                    BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(r2);
                    if (characteristic != null) {
                        return characteristic;
                    }
                }
                throw new BleCharacteristicNotFoundException(r2);
            }
        });
    }

    public Observable<BluetoothGattCharacteristic> getCharacteristic(UUID r2, final UUID r3) {
        return getService(r2).map(new Func1<BluetoothGattService, BluetoothGattCharacteristic>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.4
            @Override // p042rx.functions.Func1
            public BluetoothGattCharacteristic call(BluetoothGattService bluetoothGattService) {
                return bluetoothGattService.getCharacteristic(r3);
            }
        }).takeFirst(new Func1<BluetoothGattCharacteristic, Boolean>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.3
            @Override // p042rx.functions.Func1
            public Boolean call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return Boolean.valueOf(bluetoothGattCharacteristic != null);
            }
        }).switchIfEmpty(Observable.error(new BleCharacteristicNotFoundException(r3)));
    }

    public Observable<BluetoothGattDescriptor> getDescriptor(UUID r2, final UUID r3) {
        return getCharacteristic(r2).map(new Func1<BluetoothGattCharacteristic, BluetoothGattDescriptor>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.6
            @Override // p042rx.functions.Func1
            public BluetoothGattDescriptor call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return bluetoothGattCharacteristic.getDescriptor(r3);
            }
        }).filter(new Func1<Object, Boolean>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // p042rx.functions.Func1
            public Boolean call(Object obj) {
                return Boolean.valueOf(obj != null);
            }
        });
    }

    public Observable<BluetoothGattDescriptor> getDescriptor(UUID r2, final UUID r3, final UUID r4) {
        return getService(r2).map(new Func1<BluetoothGattService, BluetoothGattCharacteristic>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.8
            @Override // p042rx.functions.Func1
            public BluetoothGattCharacteristic call(BluetoothGattService bluetoothGattService) {
                return bluetoothGattService.getCharacteristic(r3);
            }
        }).map(new Func1<BluetoothGattCharacteristic, BluetoothGattDescriptor>() { // from class: com.polidea.rxandroidble.RxBleDeviceServices.7
            @Override // p042rx.functions.Func1
            public BluetoothGattDescriptor call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return bluetoothGattCharacteristic.getDescriptor(r4);
            }
        });
    }
}
