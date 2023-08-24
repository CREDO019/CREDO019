package com.polidea.rxandroidble;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import p042rx.Observable;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public interface RxBleCustomOperation<T> {
    Observable<T> asObservable(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) throws Throwable;
}
