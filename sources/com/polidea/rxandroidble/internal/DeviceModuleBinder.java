package com.polidea.rxandroidble.internal;

import bleshadow.dagger.Binds;
import bleshadow.dagger.Module;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.internal.connection.Connector;
import com.polidea.rxandroidble.internal.connection.ConnectorImpl;

@Module
/* loaded from: classes3.dex */
abstract class DeviceModuleBinder {
    @Binds
    abstract Connector bindConnector(ConnectorImpl connectorImpl);

    @Binds
    abstract RxBleDevice bindDevice(RxBleDeviceImpl rxBleDeviceImpl);

    DeviceModuleBinder() {
    }
}
