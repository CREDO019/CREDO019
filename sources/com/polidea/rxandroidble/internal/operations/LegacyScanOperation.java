package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble.internal.scan.RxBleInternalScanResultLegacy;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble.internal.util.UUIDUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import p042rx.Emitter;

/* loaded from: classes3.dex */
public class LegacyScanOperation extends ScanOperation<RxBleInternalScanResultLegacy, BluetoothAdapter.LeScanCallback> {
    private final Set<UUID> filterUuids;
    private final boolean isFilterDefined;
    private final UUIDUtil uuidUtil;

    public LegacyScanOperation(UUID[] r1, RxBleAdapterWrapper rxBleAdapterWrapper, UUIDUtil uUIDUtil) {
        super(rxBleAdapterWrapper);
        boolean z = r1 != null && r1.length > 0;
        this.isFilterDefined = z;
        this.uuidUtil = uUIDUtil;
        if (z) {
            HashSet hashSet = new HashSet(r1.length);
            this.filterUuids = hashSet;
            Collections.addAll(hashSet, r1);
            return;
        }
        this.filterUuids = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public BluetoothAdapter.LeScanCallback createScanCallback(final Emitter<RxBleInternalScanResultLegacy> emitter) {
        return new BluetoothAdapter.LeScanCallback() { // from class: com.polidea.rxandroidble.internal.operations.LegacyScanOperation.1
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int r4, byte[] bArr) {
                if (!LegacyScanOperation.this.isFilterDefined || LegacyScanOperation.this.uuidUtil.extractUUIDs(bArr).containsAll(LegacyScanOperation.this.filterUuids)) {
                    emitter.onNext(new RxBleInternalScanResultLegacy(bluetoothDevice, r4, bArr));
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
