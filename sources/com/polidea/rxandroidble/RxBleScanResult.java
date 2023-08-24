package com.polidea.rxandroidble;

/* loaded from: classes3.dex */
public class RxBleScanResult {
    private final RxBleDevice bleDevice;
    private final int rssi;
    private final byte[] scanRecord;

    public RxBleScanResult(RxBleDevice rxBleDevice, int r2, byte[] bArr) {
        this.bleDevice = rxBleDevice;
        this.rssi = r2;
        this.scanRecord = bArr;
    }

    public RxBleDevice getBleDevice() {
        return this.bleDevice;
    }

    public int getRssi() {
        return this.rssi;
    }

    public byte[] getScanRecord() {
        return this.scanRecord;
    }
}
