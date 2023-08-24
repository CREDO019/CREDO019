package com.polidea.multiplatformbleadapter;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.multiplatformbleadapter.utils.ByteUtils;
import com.polidea.multiplatformbleadapter.utils.Constants;
import com.polidea.multiplatformbleadapter.utils.IdGenerator;
import com.polidea.multiplatformbleadapter.utils.IdGeneratorKey;
import com.polidea.rxandroidble.internal.RxBleLog;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public class Characteristic {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private final String deviceID;
    final BluetoothGattCharacteristic gattCharacteristic;

    /* renamed from: id */
    private final int f1311id;
    private final int serviceID;
    private final UUID serviceUUID;
    private byte[] value;

    public void setValue(byte[] bArr) {
        this.value = bArr;
    }

    public Characteristic(Service service, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String deviceID = service.getDeviceID();
        this.deviceID = deviceID;
        this.serviceUUID = service.getUuid();
        this.serviceID = service.getId();
        this.gattCharacteristic = bluetoothGattCharacteristic;
        this.f1311id = IdGenerator.getIdForKey(new IdGeneratorKey(deviceID, bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getInstanceId()));
    }

    public Characteristic(int r1, Service service, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.f1311id = r1;
        this.deviceID = service.getDeviceID();
        this.serviceUUID = service.getUuid();
        this.serviceID = service.getId();
        this.gattCharacteristic = bluetoothGattCharacteristic;
    }

    public Characteristic(Characteristic characteristic) {
        this.f1311id = characteristic.f1311id;
        this.serviceID = characteristic.serviceID;
        this.serviceUUID = characteristic.serviceUUID;
        this.deviceID = characteristic.deviceID;
        byte[] bArr = characteristic.value;
        if (bArr != null) {
            this.value = (byte[]) bArr.clone();
        }
        this.gattCharacteristic = characteristic.gattCharacteristic;
    }

    public int getId() {
        return this.f1311id;
    }

    public UUID getUuid() {
        return this.gattCharacteristic.getUuid();
    }

    public int getServiceID() {
        return this.serviceID;
    }

    public UUID getServiceUUID() {
        return this.serviceUUID;
    }

    public String getDeviceId() {
        return this.deviceID;
    }

    public int getInstanceId() {
        return this.gattCharacteristic.getInstanceId();
    }

    public BluetoothGattDescriptor getGattDescriptor(UUID r2) {
        return this.gattCharacteristic.getDescriptor(r2);
    }

    public void setWriteType(int r2) {
        this.gattCharacteristic.setWriteType(r2);
    }

    public boolean isReadable() {
        return (this.gattCharacteristic.getProperties() & 2) != 0;
    }

    public boolean isWritableWithResponse() {
        return (this.gattCharacteristic.getProperties() & 8) != 0;
    }

    public boolean isWritableWithoutResponse() {
        return (this.gattCharacteristic.getProperties() & 4) != 0;
    }

    public boolean isNotifiable() {
        return (this.gattCharacteristic.getProperties() & 16) != 0;
    }

    public List<Descriptor> getDescriptors() {
        ArrayList arrayList = new ArrayList(this.gattCharacteristic.getDescriptors().size());
        for (BluetoothGattDescriptor bluetoothGattDescriptor : this.gattCharacteristic.getDescriptors()) {
            arrayList.add(new Descriptor(this, bluetoothGattDescriptor));
        }
        return arrayList;
    }

    public boolean isNotifying() {
        byte[] value;
        BluetoothGattDescriptor descriptor = this.gattCharacteristic.getDescriptor(Constants.CLIENT_CHARACTERISTIC_CONFIG_UUID);
        return (descriptor == null || (value = descriptor.getValue()) == null || (value[0] & 1) == 0) ? false : true;
    }

    public boolean isIndicatable() {
        return (this.gattCharacteristic.getProperties() & 32) != 0;
    }

    public byte[] getValue() {
        return this.value;
    }

    public Descriptor getDescriptorByUUID(UUID r2) {
        BluetoothGattDescriptor descriptor = this.gattCharacteristic.getDescriptor(r2);
        if (descriptor == null) {
            return null;
        }
        return new Descriptor(this, descriptor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logValue(String str, byte[] bArr) {
        if (bArr == null) {
            bArr = this.gattCharacteristic.getValue();
        }
        String bytesToHex = bArr != null ? ByteUtils.bytesToHex(bArr) : "(null)";
        RxBleLog.m237v(str + " Characteristic(uuid: " + this.gattCharacteristic.getUuid().toString() + ", id: " + this.f1311id + ", value: " + bytesToHex + ")", new Object[0]);
    }
}
