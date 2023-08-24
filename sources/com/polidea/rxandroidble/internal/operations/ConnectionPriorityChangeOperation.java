package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.exceptions.BleGattCannotStartException;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.SingleResponseOperation;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public class ConnectionPriorityChangeOperation extends SingleResponseOperation<Long> {
    private final int connectionPriority;
    private final Scheduler delayScheduler;
    private final long operationTimeout;
    private final TimeUnit timeUnit;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ConnectionPriorityChangeOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration, int r5, long j, TimeUnit timeUnit, Scheduler scheduler) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.CONNECTION_PRIORITY_CHANGE, timeoutConfiguration);
        this.connectionPriority = r5;
        this.operationTimeout = j;
        this.timeUnit = timeUnit;
        this.delayScheduler = scheduler;
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected Observable<Long> getCallback(RxBleGattCallback rxBleGattCallback) {
        return Observable.timer(this.operationTimeout, this.timeUnit, this.delayScheduler);
    }

    @Override // com.polidea.rxandroidble.internal.SingleResponseOperation
    protected boolean startOperation(BluetoothGatt bluetoothGatt) throws IllegalArgumentException, BleGattCannotStartException {
        return bluetoothGatt.requestConnectionPriority(this.connectionPriority);
    }
}
