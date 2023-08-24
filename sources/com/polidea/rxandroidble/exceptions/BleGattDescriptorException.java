package com.polidea.rxandroidble.exceptions;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes3.dex */
public class BleGattDescriptorException extends BleGattException {
    public final BluetoothGattDescriptor descriptor;

    public BleGattDescriptorException(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int r3, BleGattOperationType bleGattOperationType) {
        super(bluetoothGatt, r3, bleGattOperationType);
        this.descriptor = bluetoothGattDescriptor;
    }
}
