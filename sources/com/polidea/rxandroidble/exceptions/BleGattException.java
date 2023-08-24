package com.polidea.rxandroidble.exceptions;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble.utils.GattStatusParser;

/* loaded from: classes3.dex */
public class BleGattException extends BleException {
    public static final int UNKNOWN_STATUS = -1;
    private final BleGattOperationType bleGattOperationType;
    private final BluetoothGatt gatt;
    private final int status;

    @Deprecated
    public BleGattException(int r3, BleGattOperationType bleGattOperationType) {
        super(createMessage(null, r3, bleGattOperationType));
        this.gatt = null;
        this.status = r3;
        this.bleGattOperationType = bleGattOperationType;
    }

    public BleGattException(BluetoothGatt bluetoothGatt, int r3, BleGattOperationType bleGattOperationType) {
        super(createMessage(bluetoothGatt, r3, bleGattOperationType));
        this.gatt = bluetoothGatt;
        this.status = r3;
        this.bleGattOperationType = bleGattOperationType;
    }

    public BleGattException(BluetoothGatt bluetoothGatt, BleGattOperationType bleGattOperationType) {
        this(bluetoothGatt, -1, bleGattOperationType);
    }

    public String getMacAddress() {
        return getMacAddress(this.gatt);
    }

    public BleGattOperationType getBleGattOperationType() {
        return this.bleGattOperationType;
    }

    public int getStatus() {
        return this.status;
    }

    private static String getMacAddress(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
            return null;
        }
        return bluetoothGatt.getDevice().getAddress();
    }

    private static String createMessage(BluetoothGatt bluetoothGatt, int r6, BleGattOperationType bleGattOperationType) {
        if (r6 == -1) {
            return String.format("GATT exception from MAC address %s, with type %s", getMacAddress(bluetoothGatt), bleGattOperationType);
        }
        return String.format("GATT exception from MAC address %s, status %d (%s), type %s. (Look up status 0x%02x here %s)", getMacAddress(bluetoothGatt), Integer.valueOf(r6), GattStatusParser.getGattCallbackStatusDescription(r6), bleGattOperationType, Integer.valueOf(r6), "https://android.googlesource.com/platform/external/bluetooth/bluedroid/+/android-5.1.0_r1/stack/include/gatt_api.h");
    }
}
