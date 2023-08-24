package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble.exceptions.BleGattCannotStartException;
import com.polidea.rxandroidble.exceptions.BleGattCharacteristicException;
import com.polidea.rxandroidble.exceptions.BleGattException;
import com.polidea.rxandroidble.exceptions.BleGattOperationType;
import com.polidea.rxandroidble.internal.QueueOperation;
import com.polidea.rxandroidble.internal.connection.PayloadSizeLimitProvider;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble.internal.util.ByteAssociation;
import com.polidea.rxandroidble.internal.util.QueueReleasingEmitterWrapper;
import java.nio.ByteBuffer;
import java.util.UUID;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class CharacteristicLongWriteOperation extends QueueOperation<byte[]> {
    private final PayloadSizeLimitProvider batchSizeProvider;
    private final BluetoothGatt bluetoothGatt;
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final Scheduler bluetoothInteractionScheduler;
    private final byte[] bytesToWrite;
    private final RxBleGattCallback rxBleGattCallback;
    private byte[] tempBatchArray;
    private final TimeoutConfiguration timeoutConfiguration;
    private final RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy;
    private final RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicLongWriteOperation(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, @Named("bluetooth_interaction") Scheduler scheduler, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic, PayloadSizeLimitProvider payloadSizeLimitProvider, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, byte[] bArr) {
        this.bluetoothGatt = bluetoothGatt;
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothInteractionScheduler = scheduler;
        this.timeoutConfiguration = timeoutConfiguration;
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.batchSizeProvider = payloadSizeLimitProvider;
        this.writeOperationAckStrategy = writeOperationAckStrategy;
        this.writeOperationRetryStrategy = writeOperationRetryStrategy;
        this.bytesToWrite = bArr;
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected void protectedRun(Emitter<byte[]> emitter, QueueReleaseInterface queueReleaseInterface) throws Throwable {
        int payloadSizeLimit = this.batchSizeProvider.getPayloadSizeLimit();
        if (payloadSizeLimit <= 0) {
            throw new IllegalArgumentException("batchSizeProvider value must be greater than zero (now: " + payloadSizeLimit + ")");
        }
        Observable<? extends ByteAssociation<UUID>> error = Observable.error(new BleGattCallbackTimeoutException(this.bluetoothGatt, BleGattOperationType.CHARACTERISTIC_LONG_WRITE));
        ByteBuffer wrap = ByteBuffer.wrap(this.bytesToWrite);
        final QueueReleasingEmitterWrapper queueReleasingEmitterWrapper = new QueueReleasingEmitterWrapper(emitter, queueReleaseInterface);
        writeBatchAndObserve(payloadSizeLimit, wrap).subscribeOn(this.bluetoothInteractionScheduler).takeFirst(writeResponseForMatchingCharacteristic(this.bluetoothGattCharacteristic)).timeout(this.timeoutConfiguration.timeout, this.timeoutConfiguration.timeoutTimeUnit, error, this.timeoutConfiguration.timeoutScheduler).repeatWhen(m233x3cbe3949(this.writeOperationAckStrategy, wrap, queueReleasingEmitterWrapper)).retryWhen(errorIsRetryableAndAccordingTo(this.writeOperationRetryStrategy, wrap, payloadSizeLimit)).toCompletable().subscribe(new Action0() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.1
            @Override // p042rx.functions.Action0
            public void call() {
                queueReleasingEmitterWrapper.onNext(CharacteristicLongWriteOperation.this.bytesToWrite);
                queueReleasingEmitterWrapper.onCompleted();
            }
        }, new Action1<Throwable>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.2
            @Override // p042rx.functions.Action1
            public void call(Throwable th) {
                queueReleasingEmitterWrapper.onError(th);
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.QueueOperation
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.bluetoothGatt.getDevice().getAddress(), -1);
    }

    private Observable<ByteAssociation<UUID>> writeBatchAndObserve(final int r3, final ByteBuffer byteBuffer) {
        final Observable<ByteAssociation<UUID>> onCharacteristicWrite = this.rxBleGattCallback.getOnCharacteristicWrite();
        return Observable.create(new Action1<Emitter<ByteAssociation<UUID>>>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.3
            @Override // p042rx.functions.Action1
            public void call(Emitter<ByteAssociation<UUID>> emitter) {
                emitter.setSubscription(onCharacteristicWrite.subscribe(emitter));
                try {
                    CharacteristicLongWriteOperation.this.writeData(CharacteristicLongWriteOperation.this.getNextBatch(byteBuffer, r3));
                } catch (Throwable th) {
                    emitter.onError(th);
                }
            }
        }, Emitter.BackpressureMode.BUFFER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getNextBatch(ByteBuffer byteBuffer, int r3) {
        int min = Math.min(byteBuffer.remaining(), r3);
        byte[] bArr = this.tempBatchArray;
        if (bArr == null || bArr.length != min) {
            this.tempBatchArray = new byte[min];
        }
        byteBuffer.get(this.tempBatchArray);
        return this.tempBatchArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeData(byte[] bArr) {
        this.bluetoothGattCharacteristic.setValue(bArr);
        if (!this.bluetoothGatt.writeCharacteristic(this.bluetoothGattCharacteristic)) {
            throw new BleGattCannotStartException(this.bluetoothGatt, BleGattOperationType.CHARACTERISTIC_LONG_WRITE);
        }
    }

    private static Func1<ByteAssociation<UUID>, Boolean> writeResponseForMatchingCharacteristic(final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new Func1<ByteAssociation<UUID>, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.4
            @Override // p042rx.functions.Func1
            public Boolean call(ByteAssociation<UUID> byteAssociation) {
                return Boolean.valueOf(byteAssociation.first.equals(bluetoothGattCharacteristic.getUuid()));
            }
        };
    }

    /* renamed from: bufferIsNotEmptyAndOperationHasBeenAcknowledgedAndNotUnsubscribed */
    private static Func1<Observable<? extends Void>, Observable<?>> m233x3cbe3949(final RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, final ByteBuffer byteBuffer, final QueueReleasingEmitterWrapper<byte[]> queueReleasingEmitterWrapper) {
        return new Func1<Observable<? extends Void>, Observable<?>>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.5
            @Override // p042rx.functions.Func1
            public Observable<?> call(Observable<? extends Void> observable) {
                return RxBleConnection.WriteOperationAckStrategy.this.call(observable.takeWhile(notUnsubscribed(queueReleasingEmitterWrapper)).map(bufferIsNotEmpty(byteBuffer))).takeWhile(bufferIsNotEmpty(byteBuffer));
            }

            private Func1<Object, Boolean> bufferIsNotEmpty(final ByteBuffer byteBuffer2) {
                return new Func1<Object, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.5.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // p042rx.functions.Func1
                    public Boolean call(Object obj) {
                        return Boolean.valueOf(byteBuffer2.hasRemaining());
                    }
                };
            }

            private Func1<Object, Boolean> notUnsubscribed(final QueueReleasingEmitterWrapper<byte[]> queueReleasingEmitterWrapper2) {
                return new Func1<Object, Boolean>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.5.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // p042rx.functions.Func1
                    public Boolean call(Object obj) {
                        return Boolean.valueOf(!queueReleasingEmitterWrapper2.isWrappedEmitterUnsubscribed());
                    }
                };
            }
        };
    }

    private static Func1<Observable<? extends Throwable>, Observable<?>> errorIsRetryableAndAccordingTo(final RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, final ByteBuffer byteBuffer, final int r3) {
        return new Func1<Observable<? extends Throwable>, Observable<?>>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.6
            @Override // p042rx.functions.Func1
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(toLongWriteFailureOrError()).doOnNext(repositionByteBufferForRetry()).compose(RxBleConnection.WriteOperationRetryStrategy.this);
            }

            private Func1<Throwable, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>> toLongWriteFailureOrError() {
                return new Func1<Throwable, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.6.1
                    @Override // p042rx.functions.Func1
                    public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> call(Throwable th) {
                        if (!(th instanceof BleGattCharacteristicException) && !(th instanceof BleGattCannotStartException)) {
                            return Observable.error(th);
                        }
                        C38856 c38856 = C38856.this;
                        return Observable.just(new RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure(c38856.calculateFailedBatchIndex(byteBuffer, r3), (BleGattException) th));
                    }
                };
            }

            private Action1<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> repositionByteBufferForRetry() {
                return new Action1<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>() { // from class: com.polidea.rxandroidble.internal.operations.CharacteristicLongWriteOperation.6.2
                    @Override // p042rx.functions.Action1
                    public void call(RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure longWriteFailure) {
                        byteBuffer.position(longWriteFailure.getBatchIndex() * r3);
                    }
                };
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int calculateFailedBatchIndex(ByteBuffer byteBuffer2, int r2) {
                return ((int) Math.ceil(byteBuffer2.position() / r2)) - 1;
            }
        };
    }
}
