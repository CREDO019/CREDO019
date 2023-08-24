package com.polidea.multiplatformbleadapter;

import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.multiplatformbleadapter.utils.ByteUtils;
import com.polidea.multiplatformbleadapter.utils.IdGenerator;
import com.polidea.multiplatformbleadapter.utils.IdGeneratorKey;
import com.polidea.rxandroidble.internal.RxBleLog;
import java.util.UUID;

/* loaded from: classes3.dex */
public class Descriptor {
    private final int characteristicId;
    private final UUID characteristicUuid;
    private final BluetoothGattDescriptor descriptor;
    private final String deviceId;

    /* renamed from: id */
    private final int f1312id;
    private final int serviceId;
    private final UUID serviceUuid;
    private final UUID uuid;
    private byte[] value;

    public Descriptor(Characteristic characteristic, BluetoothGattDescriptor bluetoothGattDescriptor) {
        this.value = null;
        int id = characteristic.getId();
        this.characteristicId = id;
        this.characteristicUuid = characteristic.getUuid();
        this.serviceId = characteristic.getServiceID();
        this.serviceUuid = characteristic.getServiceUUID();
        this.descriptor = bluetoothGattDescriptor;
        String deviceId = characteristic.getDeviceId();
        this.deviceId = deviceId;
        this.f1312id = IdGenerator.getIdForKey(new IdGeneratorKey(deviceId, bluetoothGattDescriptor.getUuid(), id));
        this.uuid = bluetoothGattDescriptor.getUuid();
    }

    public Descriptor(int r2, int r3, UUID r4, UUID r5, String str, BluetoothGattDescriptor bluetoothGattDescriptor, int r8, UUID r9) {
        this.value = null;
        this.characteristicId = r2;
        this.serviceId = r3;
        this.characteristicUuid = r4;
        this.serviceUuid = r5;
        this.deviceId = str;
        this.descriptor = bluetoothGattDescriptor;
        this.f1312id = r8;
        this.uuid = r9;
    }

    public Descriptor(Descriptor descriptor) {
        this.value = null;
        this.characteristicUuid = descriptor.characteristicUuid;
        this.characteristicId = descriptor.characteristicId;
        this.serviceUuid = descriptor.serviceUuid;
        this.serviceId = descriptor.serviceId;
        this.deviceId = descriptor.deviceId;
        this.descriptor = descriptor.descriptor;
        this.f1312id = descriptor.f1312id;
        this.uuid = descriptor.uuid;
        byte[] bArr = descriptor.value;
        if (bArr != null) {
            this.value = (byte[]) bArr.clone();
        }
    }

    public int getId() {
        return this.f1312id;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int getCharacteristicId() {
        return this.characteristicId;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public UUID getCharacteristicUuid() {
        return this.characteristicUuid;
    }

    public UUID getServiceUuid() {
        return this.serviceUuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public byte[] getValue() {
        return this.value;
    }

    public void setValue(byte[] bArr) {
        this.value = bArr;
    }

    public void setValueFromCache() {
        this.value = this.descriptor.getValue();
    }

    public BluetoothGattDescriptor getNativeDescriptor() {
        return this.descriptor;
    }

    public void logValue(String str, byte[] bArr) {
        if (bArr == null) {
            bArr = this.descriptor.getValue();
        }
        String bytesToHex = bArr != null ? ByteUtils.bytesToHex(bArr) : "(null)";
        RxBleLog.m237v(str + " Descriptor(uuid: " + this.descriptor.getUuid().toString() + ", id: " + this.f1312id + ", value: " + bytesToHex + ")", new Object[0]);
    }
}
