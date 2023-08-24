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
public class CharacteristicReadOperation extends SingleResponseOperation<byte[]> {
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicReadOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.CHARACTERISTIC_READ, timeoutConfiguration);
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<byte[]> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnCharacteristicRead().filter(new Func1<ByteAssociation<UUID>, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicReadOperation.2
            @Override // p042rx.functions.Func1
            public Boolean call(ByteAssociation<UUID> byteAssociation) {
                return Boolean.valueOf(byteAssociation.first.equals(CharacteristicReadOperation.this.bluetoothGattCharacteristic.getUuid()));
            }
        }).map(new Func1<ByteAssociation<UUID>, byte[]>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicReadOperation.1
            @Override // p042rx.functions.Func1
            public byte[] call(ByteAssociation<UUID> byteAssociation) {
                return byteAssociation.second;
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.readCharacteristic(this.bluetoothGattCharacteristic);
    }
}
