package com.polidea.rxandroidble.internal.operations;

import android.os.DeadObjectException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.exceptions.BleScanException;
import com.polidea.rxandroidble.internal.QueueOperation;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import p042rx.Emitter;
import p042rx.functions.Cancellable;

/* loaded from: classes3.dex */
public abstract class ScanOperation<SCAN_RESULT_TYPE, SCAN_CALLBACK_TYPE> extends QueueOperation<SCAN_RESULT_TYPE> {
    private final RxBleAdapterWrapper rxBleAdapterWrapper;

    abstract SCAN_CALLBACK_TYPE createScanCallback(Emitter<SCAN_RESULT_TYPE> emitter);

    abstract boolean startScan(RxBleAdapterWrapper rxBleAdapterWrapper, SCAN_CALLBACK_TYPE scan_callback_type);

    abstract void stopScan(RxBleAdapterWrapper rxBleAdapterWrapper, SCAN_CALLBACK_TYPE scan_callback_type);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScanOperation(RxBleAdapterWrapper rxBleAdapterWrapper) {
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected final void protectedRun(Emitter<SCAN_RESULT_TYPE> emitter, QueueReleaseInterface queueReleaseInterface) {
        final SCAN_CALLBACK_TYPE createScanCallback = createScanCallback(emitter);
        try {
            emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.internal.operations.ScanOperation.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // p042rx.functions.Cancellable
                public void cancel() throws Exception {
                    RxBleLog.m239i("Scan operation is requested to stop.", new Object[0]);
                    ScanOperation scanOperation = ScanOperation.this;
                    scanOperation.stopScan(scanOperation.rxBleAdapterWrapper, createScanCallback);
                }
            });
            RxBleLog.m239i("Scan operation is requested to start.", new Object[0]);
            if (!startScan(this.rxBleAdapterWrapper, createScanCallback)) {
                emitter.onError(new BleScanException(0));
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleScanException(1, deadObjectException);
    }
}
