package com.polidea.rxandroidble.exceptions;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes3.dex */
public class BleGattCharacteristicException extends BleGattException {
    public final BluetoothGattCharacteristic characteristic;

    public BleGattCharacteristicException(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int r3, BleGattOperationType bleGattOperationType) {
        super(bluetoothGatt, r3, bleGattOperationType);
        this.characteristic = bluetoothGattCharacteristic;
    }
}
