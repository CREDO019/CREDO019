package com.polidea.rxandroidble.internal.scan;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.ClientScope;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.util.UUIDUtil;
import com.polidea.rxandroidble.scan.ScanCallbackType;

@ClientScope
/* loaded from: classes3.dex */
public class InternalScanResultCreator {
    private final UUIDUtil uuidUtil;

    @Inject
    public InternalScanResultCreator(UUIDUtil uUIDUtil) {
        this.uuidUtil = uUIDUtil;
    }

    public RxBleInternalScanResult create(BluetoothDevice bluetoothDevice, int r10, byte[] bArr) {
        return new RxBleInternalScanResult(bluetoothDevice, r10, System.nanoTime(), this.uuidUtil.parseFromBytes(bArr), ScanCallbackType.CALLBACK_TYPE_UNSPECIFIED);
    }

    public RxBleInternalScanResult create(ScanResult scanResult) {
        return new RxBleInternalScanResult(scanResult.getDevice(), scanResult.getRssi(), scanResult.getTimestampNanos(), new ScanRecordImplNativeWrapper(scanResult.getScanRecord()), ScanCallbackType.CALLBACK_TYPE_BATCH);
    }

    public RxBleInternalScanResult create(int r9, ScanResult scanResult) {
        return new RxBleInternalScanResult(scanResult.getDevice(), scanResult.getRssi(), scanResult.getTimestampNanos(), new ScanRecordImplNativeWrapper(scanResult.getScanRecord()), toScanCallbackType(r9));
    }

    private static ScanCallbackType toScanCallbackType(int r1) {
        if (r1 != 1) {
            if (r1 != 2) {
                if (r1 == 4) {
                    return ScanCallbackType.CALLBACK_TYPE_MATCH_LOST;
                }
                RxBleLog.m235w("Unknown callback type %d -> check android.bluetooth.le.ScanSettings", new Object[0]);
                return ScanCallbackType.CALLBACK_TYPE_UNKNOWN;
            }
            return ScanCallbackType.CALLBACK_TYPE_FIRST_MATCH;
        }
        return ScanCallbackType.CALLBACK_TYPE_ALL_MATCHES;
    }
}
