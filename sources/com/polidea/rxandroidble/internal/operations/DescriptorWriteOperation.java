package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.SingleResponseOperation;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.util.ByteAssociation;
import p042rx.Observable;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class DescriptorWriteOperation extends SingleResponseOperation<byte[]> {
    private final int bluetoothGattCharacteristicDefaultWriteType;
    private BluetoothGattDescriptor bluetoothGattDescriptor;
    private byte[] data;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorWriteOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, int r5, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.DESCRIPTOR_WRITE, timeoutConfiguration);
        this.bluetoothGattCharacteristicDefaultWriteType = r5;
        this.bluetoothGattDescriptor = bluetoothGattDescriptor;
        this.data = bArr;
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<byte[]> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnDescriptorWrite().filter(new Func1<ByteAssociation<BluetoothGattDescriptor>, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.DescriptorWriteOperation.2
            @Override // p042rx.functions.Func1
            public Boolean call(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
                return Boolean.valueOf(byteAssociation.first.equals(DescriptorWriteOperation.this.bluetoothGattDescriptor));
            }
        }).map(new Func1<ByteAssociation<BluetoothGattDescriptor>, byte[]>() { // from class: com.polidea.rxandroidble.internal.operations.DescriptorWriteOperation.1
            @Override // p042rx.functions.Func1
            public byte[] call(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
                return byteAssociation.second;
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        this.bluetoothGattDescriptor.setValue(this.data);
        BluetoothGattCharacteristic characteristic = this.bluetoothGattDescriptor.getCharacteristic();
        int writeType = characteristic.getWriteType();
        characteristic.setWriteType(this.bluetoothGattCharacteristicDefaultWriteType);
        boolean writeDescriptor = bluetoothGatt.writeDescriptor(this.bluetoothGattDescriptor);
        characteristic.setWriteType(writeType);
        return writeDescriptor;
    }
}
