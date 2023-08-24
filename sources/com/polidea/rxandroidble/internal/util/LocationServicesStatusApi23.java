package com.polidea.rxandroidble.internal.util;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;

/* loaded from: classes3.dex */
public class LocationServicesStatusApi23 implements LocationServicesStatus {
    private final CheckerLocationPermission checkerLocationPermission;
    private final CheckerLocationProvider checkerLocationProvider;
    private final boolean isAndroidWear;
    private final int targetSdk;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public LocationServicesStatusApi23(CheckerLocationProvider checkerLocationProvider, CheckerLocationPermission checkerLocationPermission, @Named("target-sdk") int r3, @Named("android-wear") boolean z) {
        this.checkerLocationProvider = checkerLocationProvider;
        this.checkerLocationPermission = checkerLocationPermission;
        this.targetSdk = r3;
        this.isAndroidWear = z;
    }

    @Override // com.polidea.rxandroidble.internal.util.LocationServicesStatus
    public boolean isLocationPermissionOk() {
        return this.checkerLocationPermission.isLocationPermissionGranted();
    }

    @Override // com.polidea.rxandroidble.internal.util.LocationServicesStatus
    public boolean isLocationProviderOk() {
        return !isLocationProviderEnabledRequired() || this.checkerLocationProvider.isLocationProviderEnabled();
    }

    private boolean isLocationProviderEnabledRequired() {
        return !this.isAndroidWear && this.targetSdk >= 23;
    }
}
