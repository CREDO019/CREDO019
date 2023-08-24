package com.polidea.rxandroidble.internal.scan;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.operations.ScanOperationApi18;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanSettings;
import p042rx.Observable;

/* loaded from: classes3.dex */
public class ScanSetupBuilderImplApi18 implements ScanSetupBuilder {
    private final InternalScanResultCreator internalScanResultCreator;
    private final RxBleAdapterWrapper rxBleAdapterWrapper;
    private final ScanSettingsEmulator scanSettingsEmulator;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ScanSetupBuilderImplApi18(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator) {
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
        this.internalScanResultCreator = internalScanResultCreator;
        this.scanSettingsEmulator = scanSettingsEmulator;
    }

    @Override // com.polidea.rxandroidble.internal.scan.ScanSetupBuilder
    public ScanSetup build(ScanSettings scanSettings, ScanFilter... scanFilterArr) {
        final Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateScanMode = this.scanSettingsEmulator.emulateScanMode(scanSettings.getScanMode());
        final Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateCallbackType = this.scanSettingsEmulator.emulateCallbackType(scanSettings.getCallbackType());
        return new ScanSetup(new ScanOperationApi18(this.rxBleAdapterWrapper, this.internalScanResultCreator, new EmulatedScanFilterMatcher(scanFilterArr)), new Observable.Transformer<RxBleInternalScanResult, RxBleInternalScanResult>() { // from class: com.polidea.rxandroidble.internal.scan.ScanSetupBuilderImplApi18.1
            @Override // p042rx.functions.Func1
            public Observable<RxBleInternalScanResult> call(Observable<RxBleInternalScanResult> observable) {
                return observable.compose(emulateScanMode).compose(emulateCallbackType);
            }
        });
    }
}
