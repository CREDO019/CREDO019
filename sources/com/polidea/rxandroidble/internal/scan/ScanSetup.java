package com.polidea.rxandroidble.internal.scan;

import com.polidea.rxandroidble.internal.operations.Operation;
import p042rx.Observable;

/* loaded from: classes3.dex */
public class ScanSetup {
    public final Operation<RxBleInternalScanResult> scanOperation;
    public final Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> scanOperationBehaviourEmulatorTransformer;

    public ScanSetup(Operation<RxBleInternalScanResult> operation, Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> transformer) {
        this.scanOperation = operation;
        this.scanOperationBehaviourEmulatorTransformer = transformer;
    }
}
