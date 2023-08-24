package com.polidea.rxandroidble.internal;

import bleshadow.dagger.Subcomponent;
import com.polidea.rxandroidble.RxBleDevice;

@Subcomponent(modules = {DeviceModule.class, DeviceModuleBinder.class})
@DeviceScope
/* loaded from: classes3.dex */
public interface DeviceComponent {

    /* loaded from: classes3.dex */
    public interface Builder {
        DeviceComponent build();

        Builder deviceModule(DeviceModule deviceModule);
    }

    @DeviceScope
    RxBleDevice provideDevice();
}
