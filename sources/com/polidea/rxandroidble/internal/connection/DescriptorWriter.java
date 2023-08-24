package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.operations.OperationsProvider;
import com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueue;
import p042rx.Observable;

/* JADX INFO: Access modifiers changed from: package-private */
@ConnectionScope
/* loaded from: classes3.dex */
public class DescriptorWriter {
    private final ConnectionOperationQueue operationQueue;
    private final OperationsProvider operationsProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DescriptorWriter(ConnectionOperationQueue connectionOperationQueue, OperationsProvider operationsProvider) {
        this.operationQueue = connectionOperationQueue;
        this.operationsProvider = operationsProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<byte[]> writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return this.operationQueue.queue(this.operationsProvider.provideWriteDescriptor(bluetoothGattDescriptor, bArr));
    }
}
