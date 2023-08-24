package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay.PublishRelay;
import com.jakewharton.rxrelay.SerializedRelay;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleDeviceServices;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleGattCharacteristicException;
import com.polidea.rxandroidble.exceptions.BleGattDescriptorException;
import com.polidea.rxandroidble.exceptions.BleGattException;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.util.ByteAssociation;
import com.polidea.rxandroidble.internal.util.CharacteristicChangedEvent;
import java.util.UUID;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Func1;

@ConnectionScope
/* loaded from: classes3.dex */
public class RxBleGattCallback {
    private final BluetoothGattProvider bluetoothGattProvider;
    private final Scheduler callbackScheduler;
    private final DisconnectionRouter disconnectionRouter;
    private final NativeCallbackDispatcher nativeCallbackDispatcher;
    private final PublishRelay<RxBleConnection.RxBleConnectionState> connectionStatePublishRelay = PublishRelay.create();
    private final Output<RxBleDeviceServices> servicesDiscoveredOutput = new Output<>();
    private final Output<ByteAssociation<UUID>> readCharacteristicOutput = new Output<>();
    private final Output<ByteAssociation<UUID>> writeCharacteristicOutput = new Output<>();
    private final SerializedRelay<CharacteristicChangedEvent, CharacteristicChangedEvent> changedCharacteristicSerializedPublishRelay = PublishRelay.create().toSerialized();
    private final Output<ByteAssociation<BluetoothGattDescriptor>> readDescriptorOutput = new Output<>();
    private final Output<ByteAssociation<BluetoothGattDescriptor>> writeDescriptorOutput = new Output<>();
    private final Output<Integer> readRssiOutput = new Output<>();
    private final Output<Integer> changedMtuOutput = new Output<>();
    private final Func1<BleGattException, Observable<?>> errorMapper = new Func1<BleGattException, Observable<?>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleGattCallback.1
        @Override // p042rx.functions.Func1
        public Observable<?> call(BleGattException bleGattException) {
            return Observable.error(bleGattException);
        }
    };
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() { // from class: com.polidea.rxandroidble.internal.connection.RxBleGattCallback.2
        private boolean isDisconnectedOrDisconnecting(int r2) {
            return r2 == 0 || r2 == 3;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int r5, int r6) {
            RxBleLog.m243d("onConnectionStateChange newState=%d status=%d", Integer.valueOf(r6), Integer.valueOf(r5));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeConnectionStateCallback(bluetoothGatt, r5, r6);
            super.onConnectionStateChange(bluetoothGatt, r5, r6);
            RxBleGattCallback.this.bluetoothGattProvider.updateBluetoothGatt(bluetoothGatt);
            if (isDisconnectedOrDisconnecting(r6)) {
                RxBleGattCallback.this.disconnectionRouter.onDisconnectedException(new BleDisconnectedException(bluetoothGatt.getDevice().getAddress(), r5));
            } else if (r5 != 0) {
                RxBleGattCallback.this.disconnectionRouter.onGattConnectionStateException(new BleGattException(bluetoothGatt, r5, BleGattOperationType.CONNECTION_STATE));
            }
            RxBleGattCallback.this.connectionStatePublishRelay.call(RxBleGattCallback.this.mapConnectionStateToRxBleConnectionStatus(r6));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int r5) {
            RxBleLog.m243d("onServicesDiscovered status=%d", Integer.valueOf(r5));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeServicesDiscoveredCallback(bluetoothGatt, r5);
            super.onServicesDiscovered(bluetoothGatt, r5);
            if (RxBleGattCallback.this.servicesDiscoveredOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.servicesDiscoveredOutput, bluetoothGatt, r5, BleGattOperationType.SERVICE_DISCOVERY)) {
                    return;
                }
                RxBleGattCallback.this.servicesDiscoveredOutput.valueRelay.call(new RxBleDeviceServices(bluetoothGatt.getServices()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int r10) {
            RxBleLog.m243d("onCharacteristicRead characteristic=%s status=%d", bluetoothGattCharacteristic.getUuid(), Integer.valueOf(r10));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeReadCallback(bluetoothGatt, bluetoothGattCharacteristic, r10);
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, r10);
            if (RxBleGattCallback.this.readCharacteristicOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.readCharacteristicOutput, bluetoothGatt, bluetoothGattCharacteristic, r10, BleGattOperationType.CHARACTERISTIC_READ)) {
                    return;
                }
                RxBleGattCallback.this.readCharacteristicOutput.valueRelay.call(new ByteAssociation(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int r10) {
            RxBleLog.m243d("onCharacteristicWrite characteristic=%s status=%d", bluetoothGattCharacteristic.getUuid(), Integer.valueOf(r10));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeWriteCallback(bluetoothGatt, bluetoothGattCharacteristic, r10);
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, r10);
            if (RxBleGattCallback.this.writeCharacteristicOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.writeCharacteristicOutput, bluetoothGatt, bluetoothGattCharacteristic, r10, BleGattOperationType.CHARACTERISTIC_WRITE)) {
                    return;
                }
                RxBleGattCallback.this.writeCharacteristicOutput.valueRelay.call(new ByteAssociation(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            RxBleLog.m243d("onCharacteristicChanged characteristic=%s", bluetoothGattCharacteristic.getUuid());
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeChangedCallback(bluetoothGatt, bluetoothGattCharacteristic);
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (RxBleGattCallback.this.changedCharacteristicSerializedPublishRelay.hasObservers()) {
                RxBleGattCallback.this.changedCharacteristicSerializedPublishRelay.call(new CharacteristicChangedEvent(bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getValue()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int r10) {
            RxBleLog.m243d("onCharacteristicRead descriptor=%s status=%d", bluetoothGattDescriptor.getUuid(), Integer.valueOf(r10));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeDescriptorReadCallback(bluetoothGatt, bluetoothGattDescriptor, r10);
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, r10);
            if (RxBleGattCallback.this.readDescriptorOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.readDescriptorOutput, bluetoothGatt, bluetoothGattDescriptor, r10, BleGattOperationType.DESCRIPTOR_READ)) {
                    return;
                }
                RxBleGattCallback.this.readDescriptorOutput.valueRelay.call(new ByteAssociation(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int r10) {
            RxBleLog.m243d("onDescriptorWrite descriptor=%s status=%d", bluetoothGattDescriptor.getUuid(), Integer.valueOf(r10));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeDescriptorWriteCallback(bluetoothGatt, bluetoothGattDescriptor, r10);
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, r10);
            if (RxBleGattCallback.this.writeDescriptorOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.writeDescriptorOutput, bluetoothGatt, bluetoothGattDescriptor, r10, BleGattOperationType.DESCRIPTOR_WRITE)) {
                    return;
                }
                RxBleGattCallback.this.writeDescriptorOutput.valueRelay.call(new ByteAssociation(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int r5) {
            RxBleLog.m243d("onReliableWriteCompleted status=%d", Integer.valueOf(r5));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeReliableWriteCallback(bluetoothGatt, r5);
            super.onReliableWriteCompleted(bluetoothGatt, r5);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int r5, int r6) {
            RxBleLog.m243d("onReadRemoteRssi rssi=%d status=%d", Integer.valueOf(r5), Integer.valueOf(r6));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeReadRssiCallback(bluetoothGatt, r5, r6);
            super.onReadRemoteRssi(bluetoothGatt, r5, r6);
            if (RxBleGattCallback.this.readRssiOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.readRssiOutput, bluetoothGatt, r6, BleGattOperationType.READ_RSSI)) {
                    return;
                }
                RxBleGattCallback.this.readRssiOutput.valueRelay.call(Integer.valueOf(r5));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int r5, int r6) {
            RxBleLog.m243d("onMtuChanged mtu=%d status=%d", Integer.valueOf(r5), Integer.valueOf(r6));
            RxBleGattCallback.this.nativeCallbackDispatcher.notifyNativeMtuChangedCallback(bluetoothGatt, r5, r6);
            super.onMtuChanged(bluetoothGatt, r5, r6);
            if (RxBleGattCallback.this.changedMtuOutput.hasObservers()) {
                RxBleGattCallback rxBleGattCallback = RxBleGattCallback.this;
                if (rxBleGattCallback.propagateErrorIfOccurred(rxBleGattCallback.changedMtuOutput, bluetoothGatt, r6, BleGattOperationType.ON_MTU_CHANGED)) {
                    return;
                }
                RxBleGattCallback.this.changedMtuOutput.valueRelay.call(Integer.valueOf(r5));
            }
        }
    };

    private boolean isException(int r1) {
        return r1 != 0;
    }

    @Inject
    public RxBleGattCallback(@Named("bluetooth_callbacks") Scheduler scheduler, BluetoothGattProvider bluetoothGattProvider, DisconnectionRouter disconnectionRouter, NativeCallbackDispatcher nativeCallbackDispatcher) {
        this.callbackScheduler = scheduler;
        this.bluetoothGattProvider = bluetoothGattProvider;
        this.disconnectionRouter = disconnectionRouter;
        this.nativeCallbackDispatcher = nativeCallbackDispatcher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RxBleConnection.RxBleConnectionState mapConnectionStateToRxBleConnectionStatus(int r2) {
        if (r2 != 1) {
            if (r2 != 2) {
                if (r2 == 3) {
                    return RxBleConnection.RxBleConnectionState.DISCONNECTING;
                }
                return RxBleConnection.RxBleConnectionState.DISCONNECTED;
            }
            return RxBleConnection.RxBleConnectionState.CONNECTED;
        }
        return RxBleConnection.RxBleConnectionState.CONNECTING;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean propagateErrorIfOccurred(Output output, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int r5, BleGattOperationType bleGattOperationType) {
        return isException(r5) && propagateStatusError(output, new BleGattCharacteristicException(bluetoothGatt, bluetoothGattCharacteristic, r5, bleGattOperationType));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean propagateErrorIfOccurred(Output output, BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int r5, BleGattOperationType bleGattOperationType) {
        return isException(r5) && propagateStatusError(output, new BleGattDescriptorException(bluetoothGatt, bluetoothGattDescriptor, r5, bleGattOperationType));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean propagateErrorIfOccurred(Output output, BluetoothGatt bluetoothGatt, int r4, BleGattOperationType bleGattOperationType) {
        return isException(r4) && propagateStatusError(output, new BleGattException(bluetoothGatt, r4, bleGattOperationType));
    }

    private boolean propagateStatusError(Output output, BleGattException bleGattException) {
        output.errorRelay.call(bleGattException);
        return true;
    }

    private <T> Observable<T> withDisconnectionHandling(Output<T> output) {
        return Observable.merge(this.disconnectionRouter.asErrorOnlyObservable(), output.valueRelay, output.errorRelay.flatMap(this.errorMapper));
    }

    public BluetoothGattCallback getBluetoothGattCallback() {
        return this.bluetoothGattCallback;
    }

    public <T> Observable<T> observeDisconnect() {
        return this.disconnectionRouter.asErrorOnlyObservable();
    }

    public Observable<RxBleConnection.RxBleConnectionState> getOnConnectionStateChange() {
        return this.connectionStatePublishRelay.observeOn(this.callbackScheduler);
    }

    public Observable<RxBleDeviceServices> getOnServicesDiscovered() {
        return withDisconnectionHandling(this.servicesDiscoveredOutput).observeOn(this.callbackScheduler);
    }

    public Observable<Integer> getOnMtuChanged() {
        return withDisconnectionHandling(this.changedMtuOutput).observeOn(this.callbackScheduler);
    }

    public Observable<ByteAssociation<UUID>> getOnCharacteristicRead() {
        return withDisconnectionHandling(this.readCharacteristicOutput).observeOn(this.callbackScheduler);
    }

    public Observable<ByteAssociation<UUID>> getOnCharacteristicWrite() {
        return withDisconnectionHandling(this.writeCharacteristicOutput).observeOn(this.callbackScheduler);
    }

    public Observable<CharacteristicChangedEvent> getOnCharacteristicChanged() {
        return Observable.merge(this.disconnectionRouter.asErrorOnlyObservable(), this.changedCharacteristicSerializedPublishRelay).observeOn(this.callbackScheduler);
    }

    public Observable<ByteAssociation<BluetoothGattDescriptor>> getOnDescriptorRead() {
        return withDisconnectionHandling(this.readDescriptorOutput).observeOn(this.callbackScheduler);
    }

    public Observable<ByteAssociation<BluetoothGattDescriptor>> getOnDescriptorWrite() {
        return withDisconnectionHandling(this.writeDescriptorOutput).observeOn(this.callbackScheduler);
    }

    public Observable<Integer> getOnRssiRead() {
        return withDisconnectionHandling(this.readRssiOutput).observeOn(this.callbackScheduler);
    }

    public void setNativeCallback(BluetoothGattCallback bluetoothGattCallback) {
        this.nativeCallbackDispatcher.setNativeCallback(bluetoothGattCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Output<T> {
        final PublishRelay<T> valueRelay = PublishRelay.create();
        final PublishRelay<BleGattException> errorRelay = PublishRelay.create();

        Output() {
        }

        boolean hasObservers() {
            return this.valueRelay.hasObservers() || this.errorRelay.hasObservers();
        }
    }
}
