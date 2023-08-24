package com.polidea.rxandroidble.internal;

import android.bluetooth.BluetoothGatt;
import android.os.DeadObjectException;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble.exceptions.BleGattCannotStartException;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble.internal.util.QueueReleasingEmitterWrapper;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscription;

/* loaded from: classes3.dex */
public abstract class SingleResponseOperation<T> extends QueueOperation<T> {
    private final BluetoothGatt bluetoothGatt;
    private final BleGattOperationType operationType;
    private final RxBleGattCallback rxBleGattCallback;
    private final TimeoutConfiguration timeoutConfiguration;

    protected abstract Observable<T> getCallback(RxBleGattCallback rxBleGattCallback);

    protected abstract boolean startOperation(BluetoothGatt bluetoothGatt);

    public SingleResponseOperation(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, BleGattOperationType bleGattOperationType, TimeoutConfiguration timeoutConfiguration) {
        this.bluetoothGatt = bluetoothGatt;
        this.rxBleGattCallback = rxBleGattCallback;
        this.operationType = bleGattOperationType;
        this.timeoutConfiguration = timeoutConfiguration;
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected final void protectedRun(Emitter<T> emitter, QueueReleaseInterface queueReleaseInterface) throws Throwable {
        QueueReleasingEmitterWrapper queueReleasingEmitterWrapper = new QueueReleasingEmitterWrapper(emitter, queueReleaseInterface);
        Subscription subscribe = getCallback(this.rxBleGattCallback).first().timeout(this.timeoutConfiguration.timeout, this.timeoutConfiguration.timeoutTimeUnit, timeoutFallbackProcedure(this.bluetoothGatt, this.rxBleGattCallback, this.timeoutConfiguration.timeoutScheduler), this.timeoutConfiguration.timeoutScheduler).subscribe(queueReleasingEmitterWrapper);
        if (startOperation(this.bluetoothGatt)) {
            return;
        }
        subscribe.unsubscribe();
        queueReleasingEmitterWrapper.onError(new BleGattCannotStartException(this.bluetoothGatt, this.operationType));
    }

    protected Observable<T> timeoutFallbackProcedure(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) {
        return Observable.error(new BleGattCallbackTimeoutException(this.bluetoothGatt, this.operationType));
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.bluetoothGatt.getDevice().getAddress(), -1);
    }
}
