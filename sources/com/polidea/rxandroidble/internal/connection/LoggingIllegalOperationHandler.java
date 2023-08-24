package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.BleIllegalOperationException;
import com.polidea.rxandroidble.internal.RxBleLog;

/* loaded from: classes3.dex */
public class LoggingIllegalOperationHandler extends IllegalOperationHandler {
    @Inject
    public LoggingIllegalOperationHandler(IllegalOperationMessageCreator illegalOperationMessageCreator) {
        super(illegalOperationMessageCreator);
    }

    @Override // com.polidea.rxandroidble.internal.connection.IllegalOperationHandler
    public BleIllegalOperationException handleMismatchData(BluetoothGattCharacteristic bluetoothGattCharacteristic, int r3) {
        RxBleLog.m235w(this.messageCreator.createMismatchMessage(bluetoothGattCharacteristic, r3), new Object[0]);
        return null;
    }
}
