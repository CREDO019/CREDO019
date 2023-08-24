package com.polidea.rxandroidble.exceptions;

import android.bluetooth.BluetoothGattCharacteristic;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes3.dex */
public class BleCannotSetCharacteristicNotificationException extends BleException {
    public static final int CANNOT_FIND_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR = 2;
    public static final int CANNOT_SET_LOCAL_NOTIFICATION = 1;
    public static final int CANNOT_WRITE_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR = 3;
    @Deprecated
    public static final int UNKNOWN = -1;
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final int reason;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Reason {
    }

    private static String reasonDescription(int r1) {
        return r1 != 1 ? r1 != 2 ? r1 != 3 ? "Unknown error" : "Cannot write client characteristic config descriptor" : "Cannot find client characteristic config descriptor" : "Cannot set local notification";
    }

    @Deprecated
    public BleCannotSetCharacteristicNotificationException(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(createMessage(bluetoothGattCharacteristic, -1));
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.reason = -1;
    }

    public BleCannotSetCharacteristicNotificationException(BluetoothGattCharacteristic bluetoothGattCharacteristic, int r3, Throwable th) {
        super(createMessage(bluetoothGattCharacteristic, r3), th);
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.reason = r3;
    }

    public BluetoothGattCharacteristic getBluetoothGattCharacteristic() {
        return this.bluetoothGattCharacteristic;
    }

    public int getReason() {
        return this.reason;
    }

    private static String createMessage(BluetoothGattCharacteristic bluetoothGattCharacteristic, int r3) {
        return reasonDescription(r3) + " (code " + r3 + ") with characteristic UUID " + bluetoothGattCharacteristic.getUuid();
    }
}
