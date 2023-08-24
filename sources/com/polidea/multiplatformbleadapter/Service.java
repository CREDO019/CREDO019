package com.polidea.multiplatformbleadapter;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public class Service {
    private final BluetoothGattService btGattService;
    private final String deviceID;

    /* renamed from: id */
    private final int f1314id;

    public Service(int r1, String str, BluetoothGattService bluetoothGattService) {
        this.f1314id = r1;
        this.deviceID = str;
        this.btGattService = bluetoothGattService;
    }

    public int getId() {
        return this.f1314id;
    }

    public UUID getUuid() {
        return this.btGattService.getUuid();
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public boolean isPrimary() {
        return this.btGattService.getType() == 0;
    }

    public Characteristic getCharacteristicByUUID(UUID r2) {
        BluetoothGattCharacteristic characteristic = this.btGattService.getCharacteristic(r2);
        if (characteristic == null) {
            return null;
        }
        return new Characteristic(this, characteristic);
    }

    public List<Characteristic> getCharacteristics() {
        ArrayList arrayList = new ArrayList(this.btGattService.getCharacteristics().size());
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.btGattService.getCharacteristics()) {
            arrayList.add(new Characteristic(this, bluetoothGattCharacteristic));
        }
        return arrayList;
    }
}
