package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.SingleResponseOperation;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.util.ByteAssociation;
import p042rx.Observable;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class DescriptorReadOperation extends SingleResponseOperation<ByteAssociation<BluetoothGattDescriptor>> {
    private final BluetoothGattDescriptor bluetoothGattDescriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DescriptorReadOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.DESCRIPTOR_READ, timeoutConfiguration);
        this.bluetoothGattDescriptor = bluetoothGattDescriptor;
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<ByteAssociation<BluetoothGattDescriptor>> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnDescriptorRead().filter(new Func1<ByteAssociation<BluetoothGattDescriptor>, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.DescriptorReadOperation.1
            @Override // p042rx.functions.Func1
            public Boolean call(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
                return Boolean.valueOf(byteAssociation.first.equals(DescriptorReadOperation.this.bluetoothGattDescriptor));
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.readDescriptor(this.bluetoothGattDescriptor);
    }
}
