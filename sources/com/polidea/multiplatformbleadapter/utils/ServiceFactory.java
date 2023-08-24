package com.polidea.multiplatformbleadapter.utils;

import android.bluetooth.BluetoothGattService;
import com.polidea.multiplatformbleadapter.Service;

/* loaded from: classes3.dex */
public class ServiceFactory {
    public Service create(String str, BluetoothGattService bluetoothGattService) {
        return new Service(IdGenerator.getIdForKey(new IdGeneratorKey(str, bluetoothGattService.getUuid(), bluetoothGattService.getInstanceId())), str, bluetoothGattService);
    }
}
