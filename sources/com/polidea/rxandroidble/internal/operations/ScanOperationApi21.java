package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import com.polidea.rxandroidble.exceptions.BleScanException;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.scan.AndroidScanObjectsConverter;
import com.polidea.rxandroidble.internal.scan.EmulatedScanFilterMatcher;
import com.polidea.rxandroidble.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanSettings;
import java.util.List;
import p042rx.Emitter;

/* loaded from: classes3.dex */
public class ScanOperationApi21 extends ScanOperation<RxBleInternalScanResult, ScanCallback> {
    private final AndroidScanObjectsConverter androidScanObjectsConverter;
    private final EmulatedScanFilterMatcher emulatedScanFilterMatcher;
    private final InternalScanResultCreator internalScanResultCreator;
    private final ScanFilter[] scanFilters;
    private final ScanSettings scanSettings;

    public ScanOperationApi21(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, AndroidScanObjectsConverter androidScanObjectsConverter, ScanSettings scanSettings, EmulatedScanFilterMatcher emulatedScanFilterMatcher, ScanFilter[] scanFilterArr) {
        super(rxBleAdapterWrapper);
        this.internalScanResultCreator = internalScanResultCreator;
        this.scanSettings = scanSettings;
        this.emulatedScanFilterMatcher = emulatedScanFilterMatcher;
        this.scanFilters = scanFilterArr;
        this.androidScanObjectsConverter = androidScanObjectsConverter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public ScanCallback createScanCallback(final Emitter<RxBleInternalScanResult> emitter) {
        return new ScanCallback() { // from class: com.polidea.rxandroidble.internal.operations.ScanOperationApi21.1
            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int r2, ScanResult scanResult) {
                RxBleInternalScanResult create = ScanOperationApi21.this.internalScanResultCreator.create(r2, scanResult);
                if (ScanOperationApi21.this.emulatedScanFilterMatcher.matches(create)) {
                    emitter.onNext(create);
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                for (ScanResult scanResult : list) {
                    RxBleInternalScanResult create = ScanOperationApi21.this.internalScanResultCreator.create(scanResult);
                    if (ScanOperationApi21.this.emulatedScanFilterMatcher.matches(create)) {
                        emitter.onNext(create);
                    }
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int r3) {
                emitter.onError(new BleScanException(ScanOperationApi21.errorCodeToBleErrorCode(r3)));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public boolean startScan(RxBleAdapterWrapper rxBleAdapterWrapper, ScanCallback scanCallback) {
        rxBleAdapterWrapper.startLeScan(this.androidScanObjectsConverter.toNativeFilters(this.scanFilters), this.androidScanObjectsConverter.toNativeSettings(this.scanSettings), scanCallback);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.polidea.rxandroidble.internal.operations.ScanOperation
    public void stopScan(RxBleAdapterWrapper rxBleAdapterWrapper, ScanCallback scanCallback) {
        rxBleAdapterWrapper.stopLeScan(scanCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int errorCodeToBleErrorCode(int r2) {
        if (r2 != 1) {
            if (r2 != 2) {
                if (r2 != 3) {
                    if (r2 != 4) {
                        if (r2 != 5) {
                            RxBleLog.m235w("Encountered unknown scanning error code: %d -> check android.bluetooth.le.ScanCallback", new Object[0]);
                            return Integer.MAX_VALUE;
                        }
                        return 9;
                    }
                    return 8;
                }
                return 7;
            }
            return 6;
        }
        return 5;
    }
}
