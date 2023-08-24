package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.BleIllegalOperationException;
import com.polidea.rxandroidble.internal.RxBleLog;

/* loaded from: classes3.dex */
public class ThrowingIllegalOperationHandler extends IllegalOperationHandler {
    @Inject
    public ThrowingIllegalOperationHandler(IllegalOperationMessageCreator illegalOperationMessageCreator) {
        super(illegalOperationMessageCreator);
    }

    @Override // com.polidea.rxandroidble.internal.connection.IllegalOperationHandler
    public BleIllegalOperationException handleMismatchData(BluetoothGattCharacteristic bluetoothGattCharacteristic, int r5) {
        String createMismatchMessage = this.messageCreator.createMismatchMessage(bluetoothGattCharacteristic, r5);
        RxBleLog.m241e(createMismatchMessage, new Object[0]);
        return new BleIllegalOperationException(createMismatchMessage, bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getProperties(), r5);
    }
}
