package com.polidea.rxandroidble.internal.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.RxBleLog;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class RxBleAdapterWrapper {
    private final BluetoothAdapter bluetoothAdapter;

    @Inject
    public RxBleAdapterWrapper(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public BluetoothDevice getRemoteDevice(String str) {
        return this.bluetoothAdapter.getRemoteDevice(str);
    }

    public boolean hasBluetoothAdapter() {
        return this.bluetoothAdapter != null;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean startLegacyLeScan(BluetoothAdapter.LeScanCallback leScanCallback) {
        return this.bluetoothAdapter.startLeScan(leScanCallback);
    }

    public void stopLegacyLeScan(BluetoothAdapter.LeScanCallback leScanCallback) {
        this.bluetoothAdapter.stopLeScan(leScanCallback);
    }

    public void startLeScan(List<ScanFilter> list, ScanSettings scanSettings, ScanCallback scanCallback) {
        this.bluetoothAdapter.getBluetoothLeScanner().startScan(list, scanSettings, scanCallback);
    }

    public void stopLeScan(ScanCallback scanCallback) {
        BluetoothLeScanner bluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            RxBleLog.m243d("Cannot perform BluetoothLeScanner.stopScan(ScanCallback) because scanner is unavailable (Probably adapter is off)", new Object[0]);
        } else {
            bluetoothLeScanner.stopScan(scanCallback);
        }
    }

    public Set<BluetoothDevice> getBondedDevices() {
        return this.bluetoothAdapter.getBondedDevices();
    }
}
