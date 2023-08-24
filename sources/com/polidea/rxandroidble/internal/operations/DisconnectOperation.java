package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.internal.QueueOperation;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class DisconnectOperation extends QueueOperation<Void> {
    private final BluetoothGattProvider bluetoothGattProvider;
    private final Scheduler bluetoothInteractionScheduler;
    private final BluetoothManager bluetoothManager;
    private final ConnectionStateChangeListener connectionStateChangeListener;
    private final String macAddress;
    private final RxBleGattCallback rxBleGattCallback;
    private final TimeoutConfiguration timeoutConfiguration;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DisconnectOperation(RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, @Named("mac-address") String str, BluetoothManager bluetoothManager, @Named("bluetooth_interaction") Scheduler scheduler, @Named("disconnect-timeout") TimeoutConfiguration timeoutConfiguration, ConnectionStateChangeListener connectionStateChangeListener) {
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothGattProvider = bluetoothGattProvider;
        this.macAddress = str;
        this.bluetoothManager = bluetoothManager;
        this.bluetoothInteractionScheduler = scheduler;
        this.timeoutConfiguration = timeoutConfiguration;
        this.connectionStateChangeListener = connectionStateChangeListener;
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected void protectedRun(final Emitter<Void> emitter, final QueueReleaseInterface queueReleaseInterface) {
        this.connectionStateChangeListener.onConnectionStateChange(RxBleConnection.RxBleConnectionState.DISCONNECTING);
        BluetoothGatt bluetoothGatt = this.bluetoothGattProvider.getBluetoothGatt();
        if (bluetoothGatt == null) {
            RxBleLog.m235w("Disconnect operation has been executed but GATT instance was null - considering disconnected.", new Object[0]);
            considerGattDisconnected(emitter, queueReleaseInterface);
            return;
        }
        (isDisconnected(bluetoothGatt) ? Observable.just(bluetoothGatt) : disconnect(bluetoothGatt)).observeOn(this.bluetoothInteractionScheduler).subscribe(new Observer<BluetoothGatt>() { // from class: com.polidea.rxandroidble.internal.operations.DisconnectOperation.1
            @Override // p042rx.Observer
            public void onNext(BluetoothGatt bluetoothGatt2) {
                bluetoothGatt2.close();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                RxBleLog.m234w(th, "Disconnect operation has been executed but finished with an error - considering disconnected.", new Object[0]);
                DisconnectOperation.this.considerGattDisconnected(emitter, queueReleaseInterface);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                DisconnectOperation.this.considerGattDisconnected(emitter, queueReleaseInterface);
            }
        });
    }

    void considerGattDisconnected(Emitter<Void> emitter, QueueReleaseInterface queueReleaseInterface) {
        this.connectionStateChangeListener.onConnectionStateChange(RxBleConnection.RxBleConnectionState.DISCONNECTED);
        queueReleaseInterface.release();
        emitter.onCompleted();
    }

    private boolean isDisconnected(BluetoothGatt bluetoothGatt) {
        return this.bluetoothManager.getConnectionState(bluetoothGatt.getDevice(), 7) == 0;
    }

    private Observable<BluetoothGatt> disconnect(BluetoothGatt bluetoothGatt) {
        return new DisconnectGattObservable(bluetoothGatt, this.rxBleGattCallback, this.bluetoothInteractionScheduler).timeout(this.timeoutConfiguration.timeout, this.timeoutConfiguration.timeoutTimeUnit, Observable.just(bluetoothGatt), this.timeoutConfiguration.timeoutScheduler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DisconnectGattObservable extends Observable<BluetoothGatt> {
        DisconnectGattObservable(final BluetoothGatt bluetoothGatt, final RxBleGattCallback rxBleGattCallback, final Scheduler scheduler) {
            super(new Observable.OnSubscribe<BluetoothGatt>() { // from class: com.polidea.rxandroidble.internal.operations.DisconnectOperation.DisconnectGattObservable.1
                @Override // p042rx.functions.Action1
                public void call(Subscriber<? super BluetoothGatt> subscriber) {
                    RxBleGattCallback.this.getOnConnectionStateChange().takeFirst(new Func1<RxBleConnection.RxBleConnectionState, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.DisconnectOperation.DisconnectGattObservable.1.2
                        @Override // p042rx.functions.Func1
                        public Boolean call(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                            return Boolean.valueOf(rxBleConnectionState == RxBleConnection.RxBleConnectionState.DISCONNECTED);
                        }
                    }).map(new Func1<RxBleConnection.RxBleConnectionState, BluetoothGatt>() { // from class: com.polidea.rxandroidble.internal.operations.DisconnectOperation.DisconnectGattObservable.1.1
                        @Override // p042rx.functions.Func1
                        public BluetoothGatt call(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                            return bluetoothGatt;
                        }
                    }).subscribe((Subscriber<? super R>) subscriber);
                    scheduler.createWorker().schedule(new Action0() { // from class: com.polidea.rxandroidble.internal.operations.DisconnectOperation.DisconnectGattObservable.1.3
                        @Override // p042rx.functions.Action0
                        public void call() {
                            bluetoothGatt.disconnect();
                        }
                    });
                }
            });
        }
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.macAddress, -1);
    }
}
