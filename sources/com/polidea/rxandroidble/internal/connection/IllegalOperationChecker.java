package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.BleIllegalOperationException;
import p042rx.Completable;
import p042rx.functions.Action0;

/* loaded from: classes3.dex */
public class IllegalOperationChecker {
    private IllegalOperationHandler resultHandler;

    @Inject
    public IllegalOperationChecker(IllegalOperationHandler illegalOperationHandler) {
        this.resultHandler = illegalOperationHandler;
    }

    public Completable checkAnyPropertyMatches(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int r3) {
        return Completable.fromAction(new Action0() { // from class: com.polidea.rxandroidble.internal.connection.IllegalOperationChecker.1
            @Override // p042rx.functions.Action0
            public void call() {
                BleIllegalOperationException handleMismatchData;
                if ((bluetoothGattCharacteristic.getProperties() & r3) == 0 && (handleMismatchData = IllegalOperationChecker.this.resultHandler.handleMismatchData(bluetoothGattCharacteristic, r3)) != null) {
                    throw handleMismatchData;
                }
            }
        });
    }
}
