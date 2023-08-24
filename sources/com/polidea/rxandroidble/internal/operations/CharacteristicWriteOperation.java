package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.SingleResponseOperation;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.util.ByteAssociation;
import java.util.UUID;
import p042rx.Observable;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class CharacteristicWriteOperation extends SingleResponseOperation<byte[]> {
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final byte[] data;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicWriteOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.CHARACTERISTIC_WRITE, timeoutConfiguration);
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.data = bArr;
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<byte[]> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnCharacteristicWrite().filter(new Func1<ByteAssociation<UUID>, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicWriteOperation.2
            @Override // p042rx.functions.Func1
            public Boolean call(ByteAssociation<UUID> byteAssociation) {
                return Boolean.valueOf(byteAssociation.first.equals(CharacteristicWriteOperation.this.bluetoothGattCharacteristic.getUuid()));
            }
        }).map(new Func1<ByteAssociation<UUID>, byte[]>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicWriteOperation.1
            @Override // p042rx.functions.Func1
            public byte[] call(ByteAssociation<UUID> byteAssociation) {
                return byteAssociation.second;
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        this.bluetoothGattCharacteristic.setValue(this.data);
        return bluetoothGatt.writeCharacteristic(this.bluetoothGattCharacteristic);
    }
}
