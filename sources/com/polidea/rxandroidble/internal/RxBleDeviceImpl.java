package com.polidea.rxandroidble.internal;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import bleshadow.javax.inject.Inject;
import com.jakewharton.rxrelay.BehaviorRelay;
import com.polidea.rxandroidble.ConnectionSetup;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.Timeout;
import com.polidea.rxandroidble.exceptions.BleAlreadyConnectedException;
import com.polidea.rxandroidble.internal.connection.Connector;
import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Observable;
import p042rx.functions.Action0;
import p042rx.functions.Func0;

/* JADX INFO: Access modifiers changed from: package-private */
@DeviceScope
/* loaded from: classes3.dex */
public class RxBleDeviceImpl implements RxBleDevice {
    private final BluetoothDevice bluetoothDevice;
    private final BehaviorRelay<RxBleConnection.RxBleConnectionState> connectionStateRelay;
    private final Connector connector;
    private AtomicBoolean isConnected = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public RxBleDeviceImpl(BluetoothDevice bluetoothDevice, Connector connector, BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay) {
        this.bluetoothDevice = bluetoothDevice;
        this.connector = connector;
        this.connectionStateRelay = behaviorRelay;
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public Observable<RxBleConnection.RxBleConnectionState> observeConnectionStateChanges() {
        return this.connectionStateRelay.distinctUntilChanged().skip(1);
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public RxBleConnection.RxBleConnectionState getConnectionState() {
        return this.connectionStateRelay.getValue();
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    @Deprecated
    public Observable<RxBleConnection> establishConnection(Context context, boolean z) {
        return establishConnection(z);
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public Observable<RxBleConnection> establishConnection(boolean z) {
        return establishConnection(new ConnectionSetup.Builder().setAutoConnect(z).setSuppressIllegalOperationCheck(true).build());
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public Observable<RxBleConnection> establishConnection(boolean z, Timeout timeout) {
        return establishConnection(new ConnectionSetup.Builder().setAutoConnect(z).setOperationTimeout(timeout).setSuppressIllegalOperationCheck(true).build());
    }

    public Observable<RxBleConnection> establishConnection(final ConnectionSetup connectionSetup) {
        return Observable.defer(new Func0<Observable<RxBleConnection>>() { // from class: com.polidea.rxandroidble.internal.RxBleDeviceImpl.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<RxBleConnection> call() {
                if (RxBleDeviceImpl.this.isConnected.compareAndSet(false, true)) {
                    return RxBleDeviceImpl.this.connector.prepareConnection(connectionSetup).doOnUnsubscribe(new Action0() { // from class: com.polidea.rxandroidble.internal.RxBleDeviceImpl.1.1
                        @Override // p042rx.functions.Action0
                        public void call() {
                            RxBleDeviceImpl.this.isConnected.set(false);
                        }
                    });
                }
                return Observable.error(new BleAlreadyConnectedException(RxBleDeviceImpl.this.bluetoothDevice.getAddress()));
            }
        });
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public String getName() {
        return this.bluetoothDevice.getName();
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public String getMacAddress() {
        return this.bluetoothDevice.getAddress();
    }

    @Override // com.polidea.rxandroidble.RxBleDevice
    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RxBleDeviceImpl) {
            return this.bluetoothDevice.equals(((RxBleDeviceImpl) obj).bluetoothDevice);
        }
        return false;
    }

    public int hashCode() {
        return this.bluetoothDevice.hashCode();
    }

    public String toString() {
        return "RxBleDeviceImpl{bluetoothDevice=" + this.bluetoothDevice.getName() + '(' + this.bluetoothDevice.getAddress() + ")}";
    }
}
