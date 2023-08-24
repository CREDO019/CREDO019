package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class NativeCallbackDispatcher {
    private BluetoothGattCallback nativeCallback;

    public void notifyNativeChangedCallback(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        }
    }

    public void notifyNativeConnectionStateCallback(BluetoothGatt bluetoothGatt, int r3, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, r3, r4);
        }
    }

    public void notifyNativeDescriptorReadCallback(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, r4);
        }
    }

    public void notifyNativeDescriptorWriteCallback(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, r4);
        }
    }

    public void notifyNativeMtuChangedCallback(BluetoothGatt bluetoothGatt, int r3, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onMtuChanged(bluetoothGatt, r3, r4);
        }
    }

    public void notifyNativeReadRssiCallback(BluetoothGatt bluetoothGatt, int r3, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onReadRemoteRssi(bluetoothGatt, r3, r4);
        }
    }

    public void notifyNativeReliableWriteCallback(BluetoothGatt bluetoothGatt, int r3) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onReliableWriteCompleted(bluetoothGatt, r3);
        }
    }

    public void notifyNativeServicesDiscoveredCallback(BluetoothGatt bluetoothGatt, int r3) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onServicesDiscovered(bluetoothGatt, r3);
        }
    }

    public void notifyNativeWriteCallback(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, r4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNativeCallback(BluetoothGattCallback bluetoothGattCallback) {
        this.nativeCallback = bluetoothGattCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyNativeReadCallback(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int r4) {
        BluetoothGattCallback bluetoothGattCallback = this.nativeCallback;
        if (bluetoothGattCallback != null) {
            bluetoothGattCallback.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, r4);
        }
    }
}
