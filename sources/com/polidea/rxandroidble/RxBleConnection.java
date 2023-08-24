package com.polidea.rxandroidble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.rxandroidble.exceptions.BleGattException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import p042rx.Completable;
import p042rx.Observable;

/* loaded from: classes3.dex */
public interface RxBleConnection {
    public static final int GATT_MTU_MAXIMUM = 517;
    public static final int GATT_MTU_MINIMUM = 23;
    public static final int GATT_READ_MTU_OVERHEAD = 1;
    public static final int GATT_WRITE_MTU_OVERHEAD = 3;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ConnectionPriority {
    }

    @Deprecated
    /* loaded from: classes3.dex */
    public interface Connector {
        Observable<RxBleConnection> prepareConnection(boolean z);
    }

    /* loaded from: classes3.dex */
    public interface LongWriteOperationBuilder {
        Observable<byte[]> build();

        LongWriteOperationBuilder setBytes(byte[] bArr);

        LongWriteOperationBuilder setCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

        LongWriteOperationBuilder setCharacteristicUuid(UUID r1);

        LongWriteOperationBuilder setMaxBatchSize(int r1);

        LongWriteOperationBuilder setWriteOperationAckStrategy(WriteOperationAckStrategy writeOperationAckStrategy);

        LongWriteOperationBuilder setWriteOperationRetryStrategy(WriteOperationRetryStrategy writeOperationRetryStrategy);
    }

    /* loaded from: classes3.dex */
    public interface WriteOperationAckStrategy extends Observable.Transformer<Boolean, Boolean> {
    }

    LongWriteOperationBuilder createNewLongWriteBuilder();

    Observable<RxBleDeviceServices> discoverServices();

    Observable<RxBleDeviceServices> discoverServices(long j, TimeUnit timeUnit);

    Observable<BluetoothGattCharacteristic> getCharacteristic(UUID r1);

    int getMtu();

    <T> Observable<T> queue(RxBleCustomOperation<T> rxBleCustomOperation);

    Observable<byte[]> readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<byte[]> readCharacteristic(UUID r1);

    Observable<byte[]> readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor);

    Observable<byte[]> readDescriptor(UUID r1, UUID r2, UUID r3);

    Observable<Integer> readRssi();

    Completable requestConnectionPriority(int r1, long j, TimeUnit timeUnit);

    Observable<Integer> requestMtu(int r1);

    Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupIndication(UUID r1);

    Observable<Observable<byte[]>> setupIndication(UUID r1, NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupNotification(UUID r1);

    Observable<Observable<byte[]>> setupNotification(UUID r1, NotificationSetupMode notificationSetupMode);

    @Deprecated
    Observable<BluetoothGattCharacteristic> writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<byte[]> writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    Observable<byte[]> writeCharacteristic(UUID r1, byte[] bArr);

    Observable<byte[]> writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr);

    Observable<byte[]> writeDescriptor(UUID r1, UUID r2, UUID r3, byte[] bArr);

    /* loaded from: classes3.dex */
    public enum RxBleConnectionState {
        CONNECTING("CONNECTING"),
        CONNECTED("CONNECTED"),
        DISCONNECTED("DISCONNECTED"),
        DISCONNECTING("DISCONNECTING");
        
        private final String description;

        RxBleConnectionState(String str) {
            this.description = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "RxBleConnectionState{" + this.description + '}';
        }
    }

    /* loaded from: classes3.dex */
    public interface WriteOperationRetryStrategy extends Observable.Transformer<LongWriteFailure, LongWriteFailure> {

        /* loaded from: classes3.dex */
        public static class LongWriteFailure {
            final int batchIndex;
            final BleGattException cause;

            public LongWriteFailure(int r1, BleGattException bleGattException) {
                this.batchIndex = r1;
                this.cause = bleGattException;
            }

            public int getBatchIndex() {
                return this.batchIndex;
            }

            public BleGattException getCause() {
                return this.cause;
            }
        }
    }
}
