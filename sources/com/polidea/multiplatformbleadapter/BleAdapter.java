package com.polidea.multiplatformbleadapter;

import com.polidea.multiplatformbleadapter.errors.BleError;
import java.util.List;

/* loaded from: classes3.dex */
public interface BleAdapter {
    void cancelDeviceConnection(String str, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback);

    void cancelTransaction(String str);

    void connectToDevice(String str, ConnectionOptions connectionOptions, OnSuccessCallback<Device> onSuccessCallback, OnEventCallback<ConnectionState> onEventCallback, OnErrorCallback onErrorCallback);

    void createClient(String str, OnEventCallback<String> onEventCallback, OnEventCallback<Integer> onEventCallback2);

    List<Descriptor> descriptorsForCharacteristic(int r1) throws BleError;

    List<Descriptor> descriptorsForDevice(String str, String str2, String str3) throws BleError;

    List<Descriptor> descriptorsForService(int r1, String str) throws BleError;

    void destroyClient();

    void disable(String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback);

    void discoverAllServicesAndCharacteristicsForDevice(String str, String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback);

    void enable(String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback);

    List<Characteristic> getCharacteristicsForDevice(String str, String str2) throws BleError;

    List<Characteristic> getCharacteristicsForService(int r1) throws BleError;

    void getConnectedDevices(String[] strArr, OnSuccessCallback<Device[]> onSuccessCallback, OnErrorCallback onErrorCallback);

    String getCurrentState();

    void getKnownDevices(String[] strArr, OnSuccessCallback<Device[]> onSuccessCallback, OnErrorCallback onErrorCallback);

    String getLogLevel();

    List<Service> getServicesForDevice(String str) throws BleError;

    void isDeviceConnected(String str, OnSuccessCallback<Boolean> onSuccessCallback, OnErrorCallback onErrorCallback);

    void monitorCharacteristic(int r1, String str, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback);

    void monitorCharacteristicForDevice(String str, String str2, String str3, String str4, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback);

    void monitorCharacteristicForService(int r1, String str, String str2, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback);

    void readCharacteristic(int r1, String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readCharacteristicForDevice(String str, String str2, String str3, String str4, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readCharacteristicForService(int r1, String str, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readDescriptor(int r1, String str, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readDescriptorForCharacteristic(int r1, String str, String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readDescriptorForDevice(String str, String str2, String str3, String str4, String str5, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readDescriptorForService(int r1, String str, String str2, String str3, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void readRSSIForDevice(String str, String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback);

    void requestConnectionPriorityForDevice(String str, int r2, String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback);

    void requestMTUForDevice(String str, int r2, String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback);

    void setLogLevel(String str);

    void startDeviceScan(String[] strArr, int r2, int r3, OnEventCallback<ScanResult> onEventCallback, OnErrorCallback onErrorCallback);

    void stopDeviceScan();

    void writeCharacteristic(int r1, String str, boolean z, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback);

    void writeCharacteristicForDevice(String str, String str2, String str3, String str4, boolean z, String str5, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback);

    void writeCharacteristicForService(int r1, String str, String str2, boolean z, String str3, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback);

    void writeDescriptor(int r1, String str, String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void writeDescriptorForCharacteristic(int r1, String str, String str2, String str3, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void writeDescriptorForDevice(String str, String str2, String str3, String str4, String str5, String str6, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);

    void writeDescriptorForService(int r1, String str, String str2, String str3, String str4, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback);
}
