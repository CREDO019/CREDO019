package com.polidea.rxandroidble;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import p042rx.Observable;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public interface RxBleRadioOperationCustom<T> extends RxBleCustomOperation<T> {
    @Override // com.polidea.rxandroidble.RxBleCustomOperation
    Observable<T> asObservable(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) throws Throwable;
}