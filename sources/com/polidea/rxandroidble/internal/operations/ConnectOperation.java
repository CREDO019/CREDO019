package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.QueueOperation;
import com.polidea.rxandroidble.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble.internal.util.BleConnectionCompat;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.functions.Func0;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class ConnectOperation extends QueueOperation<BluetoothGatt> {
    private final boolean autoConnect;
    private final BluetoothDevice bluetoothDevice;
    private final BluetoothGattProvider bluetoothGattProvider;
    private final TimeoutConfiguration connectTimeout;
    private final BleConnectionCompat connectionCompat;
    private final ConnectionStateChangeListener connectionStateChangedAction;
    private final RxBleGattCallback rxBleGattCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ConnectOperation(BluetoothDevice bluetoothDevice, BleConnectionCompat bleConnectionCompat, RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, @Named("connect-timeout") TimeoutConfiguration timeoutConfiguration, @Named("autoConnect") boolean z, ConnectionStateChangeListener connectionStateChangeListener) {
        this.bluetoothDevice = bluetoothDevice;
        this.connectionCompat = bleConnectionCompat;
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothGattProvider = bluetoothGattProvider;
        this.connectTimeout = timeoutConfiguration;
        this.autoConnect = z;
        this.connectionStateChangedAction = connectionStateChangeListener;
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected void protectedRun(Emitter<BluetoothGatt> emitter, final QueueReleaseInterface queueReleaseInterface) {
        Action0 action0 = new Action0() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.1
            @Override // p042rx.functions.Action0
            public void call() {
                queueReleaseInterface.release();
            }
        };
        emitter.setSubscription(getConnectedBluetoothGatt().compose(wrapWithTimeoutWhenNotAutoconnecting()).doOnUnsubscribe(action0).doOnTerminate(action0).subscribe(emitter));
        if (this.autoConnect) {
            queueReleaseInterface.release();
        }
    }

    private Observable.Transformer<BluetoothGatt, BluetoothGatt> wrapWithTimeoutWhenNotAutoconnecting() {
        return new Observable.Transformer<BluetoothGatt, BluetoothGatt>() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.2
            @Override // p042rx.functions.Func1
            public Observable<BluetoothGatt> call(Observable<BluetoothGatt> observable) {
                return ConnectOperation.this.autoConnect ? observable : observable.timeout(ConnectOperation.this.connectTimeout.timeout, ConnectOperation.this.connectTimeout.timeoutTimeUnit, ConnectOperation.this.prepareConnectionTimeoutErrorObservable(), ConnectOperation.this.connectTimeout.timeoutScheduler);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<BluetoothGatt> prepareConnectionTimeoutErrorObservable() {
        return Observable.fromCallable(new Func0<BluetoothGatt>() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.3
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public BluetoothGatt call() {
                throw new BleGattCallbackTimeoutException(ConnectOperation.this.bluetoothGattProvider.getBluetoothGatt(), BleGattOperationType.CONNECTION_STATE);
            }
        });
    }

    private Observable<BluetoothGatt> getConnectedBluetoothGatt() {
        return Observable.create(new Action1<Emitter<BluetoothGatt>>() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.4
            @Override // p042rx.functions.Action1
            public void call(Emitter<BluetoothGatt> emitter) {
                final Subscription subscribe = Observable.fromCallable(new Func0<BluetoothGatt>() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.4.2
                    @Override // p042rx.functions.Func0, java.util.concurrent.Callable
                    public BluetoothGatt call() {
                        ConnectOperation.this.connectionStateChangedAction.onConnectionStateChange(RxBleConnection.RxBleConnectionState.CONNECTED);
                        return ConnectOperation.this.bluetoothGattProvider.getBluetoothGatt();
                    }
                }).delaySubscription(ConnectOperation.this.rxBleGattCallback.getOnConnectionStateChange().takeFirst(new Func1<RxBleConnection.RxBleConnectionState, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.4.1
                    @Override // p042rx.functions.Func1
                    public Boolean call(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                        return Boolean.valueOf(rxBleConnectionState == RxBleConnection.RxBleConnectionState.CONNECTED);
                    }
                })).mergeWith(ConnectOperation.this.rxBleGattCallback.observeDisconnect()).take(1).subscribe(emitter);
                emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.internal.operations.ConnectOperation.4.3
                    @Override // p042rx.functions.Cancellable
                    public void cancel() throws Exception {
                        subscribe.unsubscribe();
                    }
                });
                ConnectOperation.this.connectionStateChangedAction.onConnectionStateChange(RxBleConnection.RxBleConnectionState.CONNECTING);
                ConnectOperation.this.bluetoothGattProvider.updateBluetoothGatt(ConnectOperation.this.connectionCompat.connectGatt(ConnectOperation.this.bluetoothDevice, ConnectOperation.this.autoConnect, ConnectOperation.this.rxBleGattCallback.getBluetoothGattCallback()));
            }
        }, Emitter.BackpressureMode.NONE);
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.bluetoothDevice.getAddress(), -1);
    }
}
