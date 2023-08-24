package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble.internal.scan.EmulatedScanFilterMatcher;
import com.polidea.rxandroidble.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import p042rx.Emitter;

/* loaded from: classes3.dex */
public class ScanOperationApi18 extends ScanOperation<RxBleInternalScanResult, BluetoothAdapter.LeScanCallback> {
    private final EmulatedScanFilterMatcher scanFilterMatcher;
    private final InternalScanResultCreator scanResultCreator;

    public ScanOperationApi18(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, EmulatedScanFilterMatcher emulatedScanFilterMatcher) {
        super(rxBleAdapterWrapper);
        this.scanResultCreator = internalScanResultCreator;
        this.scanFilterMatcher = emulatedScanFilterMatcher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public BluetoothAdapter.LeScanCallback createScanCallback(final Emitter<RxBleInternalScanResult> emitter) {
        return new BluetoothAdapter.LeScanCallback() { // from class: com.polidea.rxandroidble.internal.operations.ScanOperationApi18.1
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int r3, byte[] bArr) {
                RxBleInternalScanResult create = ScanOperationApi18.this.scanResultCreator.create(bluetoothDevice, r3, bArr);
                if (ScanOperationApi18.this.scanFilterMatcher.matches(create)) {
                    emitter.onNext(create);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public boolean startScan(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        return rxBleAdapterWrapper.startLegacyLeScan(leScanCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public void stopScan(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        rxBleAdapterWrapper.stopLegacyLeScan(leScanCallback);
    }
}
