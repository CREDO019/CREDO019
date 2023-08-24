package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleCustomOperation;
import com.polidea.rxandroidble.RxBleDeviceServices;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.internal.QueueOperation;
import com.polidea.rxandroidble.internal.operations.OperationsProvider;
import com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueue;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble.internal.util.ByteAssociation;
import com.polidea.rxandroidble.internal.util.QueueReleasingEmitterWrapper;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import p042rx.Completable;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action0;
import p042rx.functions.Func1;

@ConnectionScope
/* loaded from: classes3.dex */
public class RxBleConnectionImpl implements RxBleConnection {
    private final BluetoothGatt bluetoothGatt;
    private final Scheduler callbackScheduler;
    private final DescriptorWriter descriptorWriter;
    private final RxBleGattCallback gattCallback;
    private final IllegalOperationChecker illegalOperationChecker;
    private final Provider<RxBleConnection.LongWriteOperationBuilder> longWriteOperationBuilderProvider;
    private final MtuProvider mtuProvider;
    private final NotificationAndIndicationManager notificationIndicationManager;
    private final ConnectionOperationQueue operationQueue;
    private final OperationsProvider operationsProvider;
    private final ServiceDiscoveryManager serviceDiscoveryManager;

    @Inject
    public RxBleConnectionImpl(ConnectionOperationQueue connectionOperationQueue, RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, ServiceDiscoveryManager serviceDiscoveryManager, NotificationAndIndicationManager notificationAndIndicationManager, MtuProvider mtuProvider, DescriptorWriter descriptorWriter, OperationsProvider operationsProvider, Provider<RxBleConnection.LongWriteOperationBuilder> provider, @Named("bluetooth_interaction") Scheduler scheduler, IllegalOperationChecker illegalOperationChecker) {
        this.operationQueue = connectionOperationQueue;
        this.gattCallback = rxBleGattCallback;
        this.bluetoothGatt = bluetoothGatt;
        this.serviceDiscoveryManager = serviceDiscoveryManager;
        this.notificationIndicationManager = notificationAndIndicationManager;
        this.mtuProvider = mtuProvider;
        this.descriptorWriter = descriptorWriter;
        this.operationsProvider = operationsProvider;
        this.longWriteOperationBuilderProvider = provider;
        this.callbackScheduler = scheduler;
        this.illegalOperationChecker = illegalOperationChecker;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public RxBleConnection.LongWriteOperationBuilder createNewLongWriteBuilder() {
        return this.longWriteOperationBuilderProvider.get();
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Completable requestConnectionPriority(int r4, long j, TimeUnit timeUnit) {
        if (r4 == 2 || r4 == 0 || r4 == 1) {
            if (j <= 0) {
                return Completable.error(new IllegalArgumentException("Delay must be bigger than 0"));
            }
            return this.operationQueue.queue(this.operationsProvider.provideConnectionPriorityChangeOperation(r4, j, timeUnit)).toCompletable();
        }
        return Completable.error(new IllegalArgumentException("Connection priority must have valid value from BluetoothGatt (received " + r4 + ")"));
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Integer> requestMtu(int r3) {
        return this.operationQueue.queue(this.operationsProvider.provideMtuChangeOperation(r3));
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public int getMtu() {
        return this.mtuProvider.getMtu();
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<RxBleDeviceServices> discoverServices() {
        return this.serviceDiscoveryManager.getDiscoverServicesObservable(20L, TimeUnit.SECONDS);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<RxBleDeviceServices> discoverServices(long j, TimeUnit timeUnit) {
        return this.serviceDiscoveryManager.getDiscoverServicesObservable(j, timeUnit);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<BluetoothGattCharacteristic> getCharacteristic(final UUID r3) {
        return discoverServices().flatMap(new Func1<RxBleDeviceServices, Observable<? extends BluetoothGattCharacteristic>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.1
            @Override // p042rx.functions.Func1
            public Observable<? extends BluetoothGattCharacteristic> call(RxBleDeviceServices rxBleDeviceServices) {
                return rxBleDeviceServices.getCharacteristic(r3);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(UUID r2) {
        return setupNotification(r2, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setupNotification(bluetoothGattCharacteristic, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(UUID r2, final NotificationSetupMode notificationSetupMode) {
        return getCharacteristic(r2).flatMap(new Func1<BluetoothGattCharacteristic, Observable<? extends Observable<byte[]>>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.2
            @Override // p042rx.functions.Func1
            public Observable<? extends Observable<byte[]>> call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return RxBleConnectionImpl.this.setupNotification(bluetoothGattCharacteristic, notificationSetupMode);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 16).andThen(this.notificationIndicationManager.setupServerInitiatedCharacteristicRead(bluetoothGattCharacteristic, notificationSetupMode, false));
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(UUID r2) {
        return setupIndication(r2, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setupIndication(bluetoothGattCharacteristic, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(UUID r2, final NotificationSetupMode notificationSetupMode) {
        return getCharacteristic(r2).flatMap(new Func1<BluetoothGattCharacteristic, Observable<? extends Observable<byte[]>>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.3
            @Override // p042rx.functions.Func1
            public Observable<? extends Observable<byte[]>> call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return RxBleConnectionImpl.this.setupIndication(bluetoothGattCharacteristic, notificationSetupMode);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 32).andThen(this.notificationIndicationManager.setupServerInitiatedCharacteristicRead(bluetoothGattCharacteristic, notificationSetupMode, true));
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> readCharacteristic(UUID r2) {
        return getCharacteristic(r2).flatMap(new Func1<BluetoothGattCharacteristic, Observable<? extends byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.4
            @Override // p042rx.functions.Func1
            public Observable<? extends byte[]> call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return RxBleConnectionImpl.this.readCharacteristic(bluetoothGattCharacteristic);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 2).andThen(this.operationQueue.queue(this.operationsProvider.provideReadCharacteristic(bluetoothGattCharacteristic)));
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> writeCharacteristic(UUID r2, final byte[] bArr) {
        return getCharacteristic(r2).flatMap(new Func1<BluetoothGattCharacteristic, Observable<? extends byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.5
            @Override // p042rx.functions.Func1
            public Observable<? extends byte[]> call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return RxBleConnectionImpl.this.writeCharacteristic(bluetoothGattCharacteristic, bArr);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    @Deprecated
    public Observable<BluetoothGattCharacteristic> writeCharacteristic(final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return writeCharacteristic(bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue()).map(new Func1<byte[], BluetoothGattCharacteristic>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.6
            @Override // p042rx.functions.Func1
            public BluetoothGattCharacteristic call(byte[] bArr) {
                return bluetoothGattCharacteristic;
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 76).andThen(this.operationQueue.queue(this.operationsProvider.provideWriteCharacteristic(bluetoothGattCharacteristic, bArr)));
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> readDescriptor(final UUID r3, final UUID r4, final UUID r5) {
        return discoverServices().flatMap(new Func1<RxBleDeviceServices, Observable<BluetoothGattDescriptor>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.8
            @Override // p042rx.functions.Func1
            public Observable<BluetoothGattDescriptor> call(RxBleDeviceServices rxBleDeviceServices) {
                return rxBleDeviceServices.getDescriptor(r3, r4, r5);
            }
        }).flatMap(new Func1<BluetoothGattDescriptor, Observable<byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.7
            @Override // p042rx.functions.Func1
            public Observable<byte[]> call(BluetoothGattDescriptor bluetoothGattDescriptor) {
                return RxBleConnectionImpl.this.readDescriptor(bluetoothGattDescriptor);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return this.operationQueue.queue(this.operationsProvider.provideReadDescriptor(bluetoothGattDescriptor)).map(new Func1<ByteAssociation<BluetoothGattDescriptor>, byte[]>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.9
            @Override // p042rx.functions.Func1
            public byte[] call(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
                return byteAssociation.second;
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> writeDescriptor(final UUID r3, final UUID r4, final UUID r5, final byte[] bArr) {
        return discoverServices().flatMap(new Func1<RxBleDeviceServices, Observable<BluetoothGattDescriptor>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.11
            @Override // p042rx.functions.Func1
            public Observable<BluetoothGattDescriptor> call(RxBleDeviceServices rxBleDeviceServices) {
                return rxBleDeviceServices.getDescriptor(r3, r4, r5);
            }
        }).flatMap(new Func1<BluetoothGattDescriptor, Observable<? extends byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.10
            @Override // p042rx.functions.Func1
            public Observable<? extends byte[]> call(BluetoothGattDescriptor bluetoothGattDescriptor) {
                return RxBleConnectionImpl.this.writeDescriptor(bluetoothGattDescriptor, bArr);
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<byte[]> writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return this.descriptorWriter.writeDescriptor(bluetoothGattDescriptor, bArr);
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public Observable<Integer> readRssi() {
        return this.operationQueue.queue(this.operationsProvider.provideRssiReadOperation());
    }

    @Override // com.polidea.rxandroidble.RxBleConnection
    public <T> Observable<T> queue(final RxBleCustomOperation<T> rxBleCustomOperation) {
        return this.operationQueue.queue(new QueueOperation<T>() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.12
            @Override // com.polidea.rxandroidble.internal.QueueOperation
            protected void protectedRun(Emitter<T> emitter, QueueReleaseInterface queueReleaseInterface) throws Throwable {
                try {
                    Observable<T> asObservable = rxBleCustomOperation.asObservable(RxBleConnectionImpl.this.bluetoothGatt, RxBleConnectionImpl.this.gattCallback, RxBleConnectionImpl.this.callbackScheduler);
                    if (asObservable == null) {
                        queueReleaseInterface.release();
                        throw new IllegalArgumentException("The custom operation asObservable method must return a non-null observable");
                    }
                    asObservable.doOnTerminate(clearNativeCallbackReferenceAction()).subscribe(new QueueReleasingEmitterWrapper(emitter, queueReleaseInterface));
                } catch (Throwable th) {
                    queueReleaseInterface.release();
                    throw th;
                }
            }

            private Action0 clearNativeCallbackReferenceAction() {
                return new Action0() { // from class: com.polidea.rxandroidble.internal.connection.RxBleConnectionImpl.12.1
                    @Override // p042rx.functions.Action0
                    public void call() {
                        RxBleConnectionImpl.this.gattCallback.setNativeCallback(null);
                    }
                };
            }

            @Override // com.polidea.rxandroidble.internal.QueueOperation
            protected BleException provideException(DeadObjectException deadObjectException) {
                return new BleDisconnectedException(deadObjectException, RxBleConnectionImpl.this.bluetoothGatt.getDevice().getAddress(), -1);
            }
        });
    }
}
