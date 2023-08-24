package com.polidea.rxandroidble.internal.scan;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.exceptions.BleScanException;
import com.polidea.rxandroidble.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;

/* loaded from: classes3.dex */
public class ScanPreconditionsVerifierApi18 implements ScanPreconditionsVerifier {
    final LocationServicesStatus locationServicesStatus;
    final RxBleAdapterWrapper rxBleAdapterWrapper;

    @Inject
    public ScanPreconditionsVerifierApi18(RxBleAdapterWrapper rxBleAdapterWrapper, LocationServicesStatus locationServicesStatus) {
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
        this.locationServicesStatus = locationServicesStatus;
    }

    @Override // com.polidea.rxandroidble.internal.scan.ScanPreconditionsVerifier
    public void verify() {
        if (!this.rxBleAdapterWrapper.hasBluetoothAdapter()) {
            throw new BleScanException(2);
        }
        if (!this.rxBleAdapterWrapper.isBluetoothEnabled()) {
            throw new BleScanException(1);
        }
        if (!this.locationServicesStatus.isLocationPermissionOk()) {
            throw new BleScanException(3);
        }
        if (!this.locationServicesStatus.isLocationProviderOk()) {
            throw new BleScanException(4);
        }
    }
}
