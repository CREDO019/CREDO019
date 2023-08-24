package com.polidea.rxandroidble.internal.scan;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble.scan.ScanResult;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class InternalToExternalScanResultConverter implements Func1<RxBleInternalScanResult, ScanResult> {
    private final RxBleDeviceProvider deviceProvider;

    @Inject
    public InternalToExternalScanResultConverter(RxBleDeviceProvider rxBleDeviceProvider) {
        this.deviceProvider = rxBleDeviceProvider;
    }

    @Override // p042rx.functions.Func1
    public ScanResult call(RxBleInternalScanResult rxBleInternalScanResult) {
        return new ScanResult(this.deviceProvider.getBleDevice(rxBleInternalScanResult.getBluetoothDevice().getAddress()), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanCallbackType(), rxBleInternalScanResult.getScanRecord());
    }
}
