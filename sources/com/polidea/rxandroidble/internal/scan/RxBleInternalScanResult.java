package com.polidea.rxandroidble.internal.scan;

import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble.scan.ScanCallbackType;
import com.polidea.rxandroidble.scan.ScanRecord;

/* loaded from: classes3.dex */
public class RxBleInternalScanResult {
    private final BluetoothDevice bluetoothDevice;
    private final int rssi;
    private final ScanCallbackType scanCallbackType;
    private final ScanRecord scanRecord;
    private final long timestampNanos;

    public RxBleInternalScanResult(BluetoothDevice bluetoothDevice, int r2, long j, ScanRecord scanRecord, ScanCallbackType scanCallbackType) {
        this.bluetoothDevice = bluetoothDevice;
        this.rssi = r2;
        this.timestampNanos = j;
        this.scanRecord = scanRecord;
        this.scanCallbackType = scanCallbackType;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    public int getRssi() {
        return this.rssi;
    }

    public ScanRecord getScanRecord() {
        return this.scanRecord;
    }

    public long getTimestampNanos() {
        return this.timestampNanos;
    }

    public ScanCallbackType getScanCallbackType() {
        return this.scanCallbackType;
    }
}
