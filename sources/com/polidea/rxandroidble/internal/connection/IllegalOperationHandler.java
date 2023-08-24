package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import com.polidea.rxandroidble.internal.BleIllegalOperationException;

/* loaded from: classes3.dex */
public abstract class IllegalOperationHandler {
    protected IllegalOperationMessageCreator messageCreator;

    public abstract BleIllegalOperationException handleMismatchData(BluetoothGattCharacteristic bluetoothGattCharacteristic, int r2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public IllegalOperationHandler(IllegalOperationMessageCreator illegalOperationMessageCreator) {
        this.messageCreator = illegalOperationMessageCreator;
    }
}
