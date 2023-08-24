package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.internal.connection.PayloadSizeLimitProvider;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public interface OperationsProvider {
    ConnectionPriorityChangeOperation provideConnectionPriorityChangeOperation(int r1, long j, TimeUnit timeUnit);

    CharacteristicLongWriteOperation provideLongWriteOperation(BluetoothGattCharacteristic bluetoothGattCharacteristic, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, PayloadSizeLimitProvider payloadSizeLimitProvider, byte[] bArr);

    MtuRequestOperation provideMtuChangeOperation(int r1);

    CharacteristicReadOperation provideReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    DescriptorReadOperation provideReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor);

    ReadRssiOperation provideRssiReadOperation();

    ServiceDiscoveryOperation provideServiceDiscoveryOperation(long j, TimeUnit timeUnit);

    CharacteristicWriteOperation provideWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    DescriptorWriteOperation provideWriteDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr);
}
