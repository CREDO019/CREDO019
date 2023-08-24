package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.internal.operations.OperationsProvider;
import com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueue;
import java.util.UUID;
import p042rx.Observable;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public final class LongWriteOperationBuilderImpl implements RxBleConnection.LongWriteOperationBuilder {
    private byte[] bytes;
    private PayloadSizeLimitProvider maxBatchSizeProvider;
    private final ConnectionOperationQueue operationQueue;
    private final OperationsProvider operationsProvider;
    private final RxBleConnection rxBleConnection;
    private RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy = new ImmediateSerializedBatchAckStrategy();
    private RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy = new NoRetryStrategy();
    private Observable<BluetoothGattCharacteristic> writtenCharacteristicObservable;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public LongWriteOperationBuilderImpl(ConnectionOperationQueue connectionOperationQueue, MtuBasedPayloadSizeLimit mtuBasedPayloadSizeLimit, RxBleConnection rxBleConnection, OperationsProvider operationsProvider) {
        this.operationQueue = connectionOperationQueue;
        this.maxBatchSizeProvider = mtuBasedPayloadSizeLimit;
        this.rxBleConnection = rxBleConnection;
        this.operationsProvider = operationsProvider;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setBytes(byte[] bArr) {
        this.bytes = bArr;
        return this;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setCharacteristicUuid(UUID r2) {
        this.writtenCharacteristicObservable = this.rxBleConnection.getCharacteristic(r2);
        return this;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.writtenCharacteristicObservable = Observable.just(bluetoothGattCharacteristic);
        return this;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setMaxBatchSize(int r2) {
        this.maxBatchSizeProvider = new ConstantPayloadSizeLimit(r2);
        return this;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setWriteOperationRetryStrategy(RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy) {
        this.writeOperationRetryStrategy = writeOperationRetryStrategy;
        return this;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public RxBleConnection.LongWriteOperationBuilder setWriteOperationAckStrategy(RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy) {
        this.writeOperationAckStrategy = writeOperationAckStrategy;
        return this;
    }

    @Override // com.polidea.rxandroidble.RxBleConnection.LongWriteOperationBuilder
    public Observable<byte[]> build() {
        Observable<BluetoothGattCharacteristic> observable = this.writtenCharacteristicObservable;
        if (observable == null) {
            throw new IllegalArgumentException("setCharacteristicUuid() or setCharacteristic() needs to be called before build()");
        }
        if (this.bytes == null) {
            throw new IllegalArgumentException("setBytes() needs to be called before build()");
        }
        return observable.flatMap(new Func1<BluetoothGattCharacteristic, Observable<byte[]>>() { // from class: com.polidea.rxandroidble.internal.connection.LongWriteOperationBuilderImpl.1
            @Override // p042rx.functions.Func1
            public Observable<byte[]> call(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return LongWriteOperationBuilderImpl.this.operationQueue.queue(LongWriteOperationBuilderImpl.this.operationsProvider.provideLongWriteOperation(bluetoothGattCharacteristic, LongWriteOperationBuilderImpl.this.writeOperationAckStrategy, LongWriteOperationBuilderImpl.this.writeOperationRetryStrategy, LongWriteOperationBuilderImpl.this.maxBatchSizeProvider, LongWriteOperationBuilderImpl.this.bytes));
            }
        });
    }
}
