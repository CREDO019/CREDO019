package com.polidea.rxandroidble.internal.operations;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;

/* loaded from: classes3.dex */
public final class MtuRequestOperation_Factory implements Factory<MtuRequestOperation> {
    private final Provider<BluetoothGatt> bluetoothGattProvider;
    private final Provider<Integer> requestedMtuProvider;
    private final Provider<RxBleGattCallback> rxBleGattCallbackProvider;
    private final Provider<TimeoutConfiguration> timeoutConfigurationProvider;

    public MtuRequestOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4) {
        this.rxBleGattCallbackProvider = provider;
        this.bluetoothGattProvider = provider2;
        this.timeoutConfigurationProvider = provider3;
        this.requestedMtuProvider = provider4;
    }

    @Override // bleshadow.javax.inject.Provider
    public MtuRequestOperation get() {
        return new MtuRequestOperation(this.rxBleGattCallbackProvider.get(), this.bluetoothGattProvider.get(), this.timeoutConfigurationProvider.get(), this.requestedMtuProvider.get().intValue());
    }

    public static MtuRequestOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4) {
        return new MtuRequestOperation_Factory(provider, provider2, provider3, provider4);
    }

    public static MtuRequestOperation newMtuRequestOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration, int r4) {
        return new MtuRequestOperation(rxBleGattCallback, bluetoothGatt, timeoutConfiguration, r4);
    }
}
