package com.polidea.multiplatformbleadapter.utils.mapper;

import com.polidea.multiplatformbleadapter.Device;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleDevice;

/* loaded from: classes3.dex */
public class RxBleDeviceToDeviceMapper {
    public Device map(RxBleDevice rxBleDevice, RxBleConnection rxBleConnection) {
        Device device = new Device(rxBleDevice.getMacAddress(), rxBleDevice.getName());
        if (rxBleConnection != null) {
            device.setMtu(Integer.valueOf(rxBleConnection.getMtu()));
        }
        return device;
    }
}
