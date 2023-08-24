package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.internal.connection.PayloadSizeLimitProvider;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.util.RxBleServicesLogger;
import java.util.concurrent.TimeUnit;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public class OperationsProviderImpl implements OperationsProvider {
    private final RxBleServicesLogger bleServicesLogger;
    private final BluetoothGatt bluetoothGatt;
    private final Scheduler bluetoothInteractionScheduler;
    private final Provider<ReadRssiOperation> rssiReadOperationProvider;
    private final RxBleGattCallback rxBleGattCallback;
    private final TimeoutConfiguration timeoutConfiguration;
    private final Scheduler timeoutScheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public OperationsProviderImpl(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, RxBleServicesLogger rxBleServicesLogger, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, @Named("bluetooth_interaction") Scheduler scheduler, @Named("timeout") Scheduler scheduler2, Provider<ReadRssiOperation> provider) {
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothGatt = bluetoothGatt;
        this.bleServicesLogger = rxBleServicesLogger;
        this.timeoutConfiguration = timeoutConfiguration;
        this.bluetoothInteractionScheduler = scheduler;
        this.timeoutScheduler = scheduler2;
        this.rssiReadOperationProvider = provider;
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public CharacteristicLongWriteOperation provideLongWriteOperation(BluetoothGattCharacteristic bluetoothGattCharacteristic, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, PayloadSizeLimitProvider payloadSizeLimitProvider, byte[] bArr) {
        return new CharacteristicLongWriteOperation(this.bluetoothGatt, this.rxBleGattCallback, this.bluetoothInteractionScheduler, this.timeoutConfiguration, bluetoothGattCharacteristic, payloadSizeLimitProvider, writeOperationAckStrategy, writeOperationRetryStrategy, bArr);
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public MtuRequestOperation provideMtuChangeOperation(int r5) {
        return new MtuRequestOperation(this.rxBleGattCallback, this.bluetoothGatt, this.timeoutConfiguration, r5);
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public CharacteristicReadOperation provideReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new CharacteristicReadOperation(this.rxBleGattCallback, this.bluetoothGatt, this.timeoutConfiguration, bluetoothGattCharacteristic);
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public DescriptorReadOperation provideReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new DescriptorReadOperation(this.rxBleGattCallback, this.bluetoothGatt, this.timeoutConfiguration, bluetoothGattDescriptor);
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public ReadRssiOperation provideRssiReadOperation() {
        return this.rssiReadOperationProvider.get();
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public ServiceDiscoveryOperation provideServiceDiscoveryOperation(long j, TimeUnit timeUnit) {
        return new ServiceDiscoveryOperation(this.rxBleGattCallback, this.bluetoothGatt, this.bleServicesLogger, new TimeoutConfiguration(j, timeUnit, this.timeoutScheduler));
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public CharacteristicWriteOperation provideWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return new CharacteristicWriteOperation(this.rxBleGattCallback, this.bluetoothGatt, this.timeoutConfiguration, bluetoothGattCharacteristic, bArr);
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public DescriptorWriteOperation provideWriteDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return new DescriptorWriteOperation(this.rxBleGattCallback, this.bluetoothGatt, this.timeoutConfiguration, 2, bluetoothGattDescriptor, bArr);
    }

    @Override // com.polidea.rxandroidble.internal.operations.OperationsProvider
    public ConnectionPriorityChangeOperation provideConnectionPriorityChangeOperation(int r11, long j, TimeUnit timeUnit) {
        return new ConnectionPriorityChangeOperation(this.rxBleGattCallback, this.bluetoothGatt, this.timeoutConfiguration, r11, j, timeUnit, this.timeoutScheduler);
    }
}
