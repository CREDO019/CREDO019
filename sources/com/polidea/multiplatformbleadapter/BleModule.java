package com.polidea.multiplatformbleadapter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.polidea.multiplatformbleadapter.errors.BleError;
import com.polidea.multiplatformbleadapter.errors.BleErrorCode;
import com.polidea.multiplatformbleadapter.errors.BleErrorUtils;
import com.polidea.multiplatformbleadapter.errors.ErrorConverter;
import com.polidea.multiplatformbleadapter.exceptions.CannotMonitorCharacteristicException;
import com.polidea.multiplatformbleadapter.utils.Base64Converter;
import com.polidea.multiplatformbleadapter.utils.Constants;
import com.polidea.multiplatformbleadapter.utils.DisposableMap;
import com.polidea.multiplatformbleadapter.utils.IdGenerator;
import com.polidea.multiplatformbleadapter.utils.LogLevel;
import com.polidea.multiplatformbleadapter.utils.RefreshGattCustomOperation;
import com.polidea.multiplatformbleadapter.utils.SafeExecutor;
import com.polidea.multiplatformbleadapter.utils.ServiceFactory;
import com.polidea.multiplatformbleadapter.utils.UUIDConverter;
import com.polidea.multiplatformbleadapter.utils.mapper.RxBleDeviceToDeviceMapper;
import com.polidea.multiplatformbleadapter.utils.mapper.RxScanResultToScanResultMapper;
import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.RxBleAdapterStateObservable;
import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.RxBleDeviceServices;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanSettings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Func0;
import p042rx.functions.Func1;
import p042rx.schedulers.Schedulers;

/* loaded from: classes3.dex */
public class BleModule implements BleAdapter {
    private Subscription adapterStateChangesSubscription;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private Context context;
    private RxBleClient rxBleClient;
    private Subscription scanSubscription;
    private final ErrorConverter errorConverter = new ErrorConverter();
    private HashMap<String, Device> discoveredDevices = new HashMap<>();
    private HashMap<String, Device> connectedDevices = new HashMap<>();
    private HashMap<String, RxBleConnection> activeConnections = new HashMap<>();
    private SparseArray<Service> discoveredServices = new SparseArray<>();
    private SparseArray<Characteristic> discoveredCharacteristics = new SparseArray<>();
    private SparseArray<Descriptor> discoveredDescriptors = new SparseArray<>();
    private final DisposableMap pendingTransactions = new DisposableMap();
    private final DisposableMap connectingDevices = new DisposableMap();
    private RxBleDeviceToDeviceMapper rxBleDeviceToDeviceMapper = new RxBleDeviceToDeviceMapper();
    private RxScanResultToScanResultMapper rxScanResultToScanResultMapper = new RxScanResultToScanResultMapper();
    private ServiceFactory serviceFactory = new ServiceFactory();
    private int currentLogLevel = Integer.MAX_VALUE;

    private String mapNativeAdapterStateToLocalBluetoothState(int r1) {
        switch (r1) {
            case 10:
                return Constants.BluetoothState.POWERED_OFF;
            case 11:
            case 13:
                return Constants.BluetoothState.RESETTING;
            case 12:
                return Constants.BluetoothState.POWERED_ON;
            default:
                return Constants.BluetoothState.UNKNOWN;
        }
    }

    public BleModule(Context context) {
        this.context = context;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.bluetoothManager = bluetoothManager;
        this.bluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void createClient(String str, OnEventCallback<String> onEventCallback, OnEventCallback<Integer> onEventCallback2) {
        this.rxBleClient = RxBleClient.create(this.context);
        this.adapterStateChangesSubscription = monitorAdapterStateChanges(this.context, onEventCallback);
        if (str != null) {
            onEventCallback2.onEvent(null);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void destroyClient() {
        Subscription subscription = this.adapterStateChangesSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.adapterStateChangesSubscription = null;
        }
        Subscription subscription2 = this.scanSubscription;
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            this.scanSubscription.unsubscribe();
            this.scanSubscription = null;
        }
        this.pendingTransactions.removeAllSubscriptions();
        this.connectingDevices.removeAllSubscriptions();
        this.discoveredServices.clear();
        this.discoveredCharacteristics.clear();
        this.discoveredDescriptors.clear();
        this.connectedDevices.clear();
        this.activeConnections.clear();
        this.discoveredDevices.clear();
        this.rxBleClient = null;
        IdGenerator.clear();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void enable(String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback) {
        changeAdapterState(RxBleAdapterStateObservable.BleAdapterState.STATE_ON, str, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void disable(String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback) {
        changeAdapterState(RxBleAdapterStateObservable.BleAdapterState.STATE_OFF, str, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public String getCurrentState() {
        return !supportsBluetoothLowEnergy() ? Constants.BluetoothState.UNSUPPORTED : this.bluetoothManager == null ? Constants.BluetoothState.POWERED_OFF : mapNativeAdapterStateToLocalBluetoothState(this.bluetoothAdapter.getState());
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void startDeviceScan(String[] strArr, int r9, int r10, OnEventCallback<ScanResult> onEventCallback, OnErrorCallback onErrorCallback) {
        UUID[] r0;
        if (strArr != null) {
            r0 = UUIDConverter.convert(strArr);
            if (r0 == null) {
                onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(strArr));
                return;
            }
        } else {
            r0 = null;
        }
        safeStartDeviceScan(r0, r9, r10, onEventCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void stopDeviceScan() {
        Subscription subscription = this.scanSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.scanSubscription = null;
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void requestConnectionPriorityForDevice(String str, int r5, final String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            final Device deviceById = getDeviceById(str);
            RxBleConnection connectionOrEmitError = getConnectionOrEmitError(deviceById.getId(), onErrorCallback);
            if (connectionOrEmitError == null) {
                return;
            }
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            if (Build.VERSION.SDK_INT >= 21) {
                this.pendingTransactions.replaceSubscription(str2, connectionOrEmitError.requestConnectionPriority(r5, 1L, TimeUnit.MILLISECONDS).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.3
                    @Override // p042rx.functions.Action0
                    public void call() {
                        safeExecutor.error(BleErrorUtils.cancelled());
                        BleModule.this.pendingTransactions.removeSubscription(str2);
                    }
                }).subscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.1
                    @Override // p042rx.functions.Action0
                    public void call() {
                        safeExecutor.success(deviceById);
                        BleModule.this.pendingTransactions.removeSubscription(str2);
                    }
                }, new Action1<Throwable>() { // from class: com.polidea.multiplatformbleadapter.BleModule.2
                    @Override // p042rx.functions.Action1
                    public void call(Throwable th) {
                        safeExecutor.error(BleModule.this.errorConverter.toError(th));
                        BleModule.this.pendingTransactions.removeSubscription(str2);
                    }
                }));
                return;
            }
            onSuccessCallback.onSuccess(deviceById);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readRSSIForDevice(String str, final String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            final Device deviceById = getDeviceById(str);
            RxBleConnection connectionOrEmitError = getConnectionOrEmitError(deviceById.getId(), onErrorCallback);
            if (connectionOrEmitError == null) {
                return;
            }
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            this.pendingTransactions.replaceSubscription(str2, connectionOrEmitError.readRssi().doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.5
                @Override // p042rx.functions.Action0
                public void call() {
                    safeExecutor.error(BleErrorUtils.cancelled());
                    BleModule.this.pendingTransactions.removeSubscription(str2);
                }
            }).subscribe(new Observer<Integer>() { // from class: com.polidea.multiplatformbleadapter.BleModule.4
                @Override // p042rx.Observer
                public void onCompleted() {
                    BleModule.this.pendingTransactions.removeSubscription(str2);
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    safeExecutor.error(BleModule.this.errorConverter.toError(th));
                    BleModule.this.pendingTransactions.removeSubscription(str2);
                }

                @Override // p042rx.Observer
                public void onNext(Integer num) {
                    deviceById.setRssi(num);
                    safeExecutor.success(deviceById);
                }
            }));
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void requestMTUForDevice(String str, int r5, final String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            final Device deviceById = getDeviceById(str);
            RxBleConnection connectionOrEmitError = getConnectionOrEmitError(deviceById.getId(), onErrorCallback);
            if (connectionOrEmitError == null) {
                return;
            }
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            if (Build.VERSION.SDK_INT >= 21) {
                this.pendingTransactions.replaceSubscription(str2, connectionOrEmitError.requestMtu(r5).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.7
                    @Override // p042rx.functions.Action0
                    public void call() {
                        safeExecutor.error(BleErrorUtils.cancelled());
                        BleModule.this.pendingTransactions.removeSubscription(str2);
                    }
                }).subscribe(new Observer<Integer>() { // from class: com.polidea.multiplatformbleadapter.BleModule.6
                    @Override // p042rx.Observer
                    public void onCompleted() {
                        BleModule.this.pendingTransactions.removeSubscription(str2);
                    }

                    @Override // p042rx.Observer
                    public void onError(Throwable th) {
                        safeExecutor.error(BleModule.this.errorConverter.toError(th));
                        BleModule.this.pendingTransactions.removeSubscription(str2);
                    }

                    @Override // p042rx.Observer
                    public void onNext(Integer num) {
                        deviceById.setMtu(num);
                        safeExecutor.success(deviceById);
                    }
                }));
                return;
            }
            onSuccessCallback.onSuccess(deviceById);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void getKnownDevices(String[] strArr, OnSuccessCallback<Device[]> onSuccessCallback, OnErrorCallback onErrorCallback) {
        if (this.rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothManagerDestroyed, "BleManager not created when tried to get known devices", null));
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str == null) {
                onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(strArr));
                return;
            }
            Device device = this.discoveredDevices.get(str);
            if (device != null) {
                arrayList.add(device);
            }
        }
        onSuccessCallback.onSuccess(arrayList.toArray(new Device[arrayList.size()]));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void getConnectedDevices(String[] strArr, OnSuccessCallback<Device[]> onSuccessCallback, OnErrorCallback onErrorCallback) {
        if (this.rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothManagerDestroyed, "BleManager not created when tried to get connected devices", null));
        } else if (strArr.length == 0) {
            onSuccessCallback.onSuccess(new Device[0]);
        } else {
            int length = strArr.length;
            UUID[] r2 = new UUID[length];
            for (int r3 = 0; r3 < strArr.length; r3++) {
                UUID convert = UUIDConverter.convert(strArr[r3]);
                if (convert == null) {
                    onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(strArr));
                    return;
                }
                r2[r3] = convert;
            }
            ArrayList arrayList = new ArrayList();
            for (Device device : this.connectedDevices.values()) {
                int r4 = 0;
                while (true) {
                    if (r4 >= length) {
                        break;
                    } else if (device.getServiceByUUID(r2[r4]) != null) {
                        arrayList.add(device);
                        break;
                    } else {
                        r4++;
                    }
                }
            }
            onSuccessCallback.onSuccess(arrayList.toArray(new Device[arrayList.size()]));
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void connectToDevice(String str, ConnectionOptions connectionOptions, OnSuccessCallback<Device> onSuccessCallback, OnEventCallback<ConnectionState> onEventCallback, OnErrorCallback onErrorCallback) {
        RxBleClient rxBleClient = this.rxBleClient;
        if (rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothManagerDestroyed, "BleManager not created when tried to connect to device", null));
            return;
        }
        RxBleDevice bleDevice = rxBleClient.getBleDevice(str);
        if (bleDevice == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotFound(str));
        } else {
            safeConnectToDevice(bleDevice, connectionOptions.getAutoConnect().booleanValue(), connectionOptions.getRequestMTU(), connectionOptions.getRefreshGattMoment(), connectionOptions.getTimeoutInMillis(), connectionOptions.getConnectionPriority(), onSuccessCallback, onEventCallback, onErrorCallback);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void cancelDeviceConnection(String str, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleClient rxBleClient = this.rxBleClient;
        if (rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothManagerDestroyed, "BleManager not created when tried to cancel device connection", null));
            return;
        }
        RxBleDevice bleDevice = rxBleClient.getBleDevice(str);
        if (this.connectingDevices.removeSubscription(str) && bleDevice != null) {
            onSuccessCallback.onSuccess(this.rxBleDeviceToDeviceMapper.map(bleDevice, null));
        } else if (bleDevice == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotFound(str));
        } else {
            onErrorCallback.onError(BleErrorUtils.deviceNotConnected(str));
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void isDeviceConnected(String str, OnSuccessCallback<Boolean> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleClient rxBleClient = this.rxBleClient;
        if (rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothManagerDestroyed, "BleManager not created when tried to check if device is connected", null));
            return;
        }
        RxBleDevice bleDevice = rxBleClient.getBleDevice(str);
        if (bleDevice == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotFound(str));
        } else {
            onSuccessCallback.onSuccess(Boolean.valueOf(bleDevice.getConnectionState().equals(RxBleConnection.RxBleConnectionState.CONNECTED)));
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void discoverAllServicesAndCharacteristicsForDevice(String str, String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeDiscoverAllServicesAndCharacteristicsForDevice(getDeviceById(str), str2, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public List<Service> getServicesForDevice(String str) throws BleError {
        Device deviceById = getDeviceById(str);
        List<Service> services = deviceById.getServices();
        if (services != null) {
            return services;
        }
        throw BleErrorUtils.deviceServicesNotDiscovered(deviceById.getId());
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public List<Characteristic> getCharacteristicsForDevice(String str, String str2) throws BleError {
        UUID convert = UUIDConverter.convert(str2);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str2);
        }
        Service serviceByUUID = getDeviceById(str).getServiceByUUID(convert);
        if (serviceByUUID == null) {
            throw BleErrorUtils.serviceNotFound(str2);
        }
        return serviceByUUID.getCharacteristics();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public List<Characteristic> getCharacteristicsForService(int r2) throws BleError {
        Service service = this.discoveredServices.get(r2);
        if (service == null) {
            throw BleErrorUtils.serviceNotFound(Integer.toString(r2));
        }
        return service.getCharacteristics();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public List<Descriptor> descriptorsForDevice(String str, String str2, String str3) throws BleError {
        UUID[] convert = UUIDConverter.convert(str2, str3);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str2, str3);
        }
        Service serviceByUUID = getDeviceById(str).getServiceByUUID(convert[0]);
        if (serviceByUUID == null) {
            throw BleErrorUtils.serviceNotFound(str2);
        }
        Characteristic characteristicByUUID = serviceByUUID.getCharacteristicByUUID(convert[1]);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str3);
        }
        return characteristicByUUID.getDescriptors();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public List<Descriptor> descriptorsForService(int r3, String str) throws BleError {
        UUID convert = UUIDConverter.convert(str);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str);
        }
        Service service = this.discoveredServices.get(r3);
        if (service == null) {
            throw BleErrorUtils.serviceNotFound(Integer.toString(r3));
        }
        Characteristic characteristicByUUID = service.getCharacteristicByUUID(convert);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str);
        }
        return characteristicByUUID.getDescriptors();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public List<Descriptor> descriptorsForCharacteristic(int r2) throws BleError {
        Characteristic characteristic = this.discoveredCharacteristics.get(r2);
        if (characteristic == null) {
            throw BleErrorUtils.characteristicNotFound(Integer.toString(r2));
        }
        return characteristic.getDescriptors();
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readCharacteristicForDevice(String str, String str2, String str3, String str4, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(str, str2, str3, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeReadCharacteristicForDevice(characteristicOrEmitError, str4, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readCharacteristicForService(int r1, String str, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(r1, str, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeReadCharacteristicForDevice(characteristicOrEmitError, str2, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readCharacteristic(int r1, String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(r1, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeReadCharacteristicForDevice(characteristicOrEmitError, str, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeCharacteristicForDevice(String str, String str2, String str3, String str4, boolean z, String str5, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(str, str2, str3, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        writeCharacteristicWithValue(characteristicOrEmitError, str4, Boolean.valueOf(z), str5, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeCharacteristicForService(int r8, String str, String str2, boolean z, String str3, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(r8, str, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        writeCharacteristicWithValue(characteristicOrEmitError, str2, Boolean.valueOf(z), str3, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeCharacteristic(int r8, String str, boolean z, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(r8, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        writeCharacteristicWithValue(characteristicOrEmitError, str, Boolean.valueOf(z), str2, onSuccessCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void monitorCharacteristicForDevice(String str, String str2, String str3, String str4, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(str, str2, str3, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeMonitorCharacteristicForDevice(characteristicOrEmitError, str4, onEventCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void monitorCharacteristicForService(int r1, String str, String str2, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(r1, str, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeMonitorCharacteristicForDevice(characteristicOrEmitError, str2, onEventCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void monitorCharacteristic(int r1, String str, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(r1, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeMonitorCharacteristicForDevice(characteristicOrEmitError, str, onEventCallback, onErrorCallback);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readDescriptorForDevice(String str, String str2, String str3, String str4, String str5, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(str, str2, str3, str4), str5, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readDescriptorForService(int r1, String str, String str2, String str3, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(r1, str, str2), str3, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readDescriptorForCharacteristic(int r1, String str, String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(r1, str), str2, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void readDescriptor(int r1, String str, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(r1), str, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    private void safeReadDescriptorForDevice(final Descriptor descriptor, final String str, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(descriptor.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        this.pendingTransactions.replaceSubscription(str, connectionOrEmitError.readDescriptor(descriptor.getNativeDescriptor()).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.9
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }).subscribe(new Observer<byte[]>() { // from class: com.polidea.multiplatformbleadapter.BleModule.8
            @Override // p042rx.Observer
            public void onCompleted() {
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onNext(byte[] bArr) {
                descriptor.logValue("Read from", bArr);
                descriptor.setValue(bArr);
                safeExecutor.success(new Descriptor(descriptor));
            }
        }));
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeDescriptorForDevice(String str, String str2, String str3, String str4, String str5, String str6, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(str, str2, str3, str4), str5, str6, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeDescriptorForService(int r7, String str, String str2, String str3, String str4, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(r7, str, str2), str3, str4, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeDescriptorForCharacteristic(int r7, String str, String str2, String str3, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(r7, str), str2, str3, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void writeDescriptor(int r7, String str, String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(r7), str, str2, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    private void safeWriteDescriptorForDevice(final Descriptor descriptor, String str, final String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        BluetoothGattDescriptor nativeDescriptor = descriptor.getNativeDescriptor();
        if (nativeDescriptor.getUuid().equals(Constants.CLIENT_CHARACTERISTIC_CONFIG_UUID)) {
            onErrorCallback.onError(BleErrorUtils.descriptorWriteNotAllowed(UUIDConverter.fromUUID(nativeDescriptor.getUuid())));
            return;
        }
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(descriptor.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        try {
            byte[] decode = Base64Converter.decode(str);
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            this.pendingTransactions.replaceSubscription(str2, connectionOrEmitError.writeDescriptor(nativeDescriptor, decode).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.11
                @Override // p042rx.functions.Action0
                public void call() {
                    safeExecutor.error(BleErrorUtils.cancelled());
                    BleModule.this.pendingTransactions.removeSubscription(str2);
                }
            }).subscribe(new Observer<byte[]>() { // from class: com.polidea.multiplatformbleadapter.BleModule.10
                @Override // p042rx.Observer
                public void onCompleted() {
                    BleModule.this.pendingTransactions.removeSubscription(str2);
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    safeExecutor.error(BleModule.this.errorConverter.toError(th));
                    BleModule.this.pendingTransactions.removeSubscription(str2);
                }

                @Override // p042rx.Observer
                public void onNext(byte[] bArr) {
                    descriptor.logValue("Write to", bArr);
                    descriptor.setValue(bArr);
                    safeExecutor.success(new Descriptor(descriptor));
                }
            }));
        } catch (Throwable unused) {
            onErrorCallback.onError(BleErrorUtils.invalidWriteDataForDescriptor(str, UUIDConverter.fromUUID(nativeDescriptor.getUuid())));
        }
    }

    private Descriptor getDescriptor(String str, String str2, String str3, String str4) throws BleError {
        UUID[] convert = UUIDConverter.convert(str2, str3, str4);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str2, str3, str4);
        }
        Device device = this.connectedDevices.get(str);
        if (device == null) {
            throw BleErrorUtils.deviceNotConnected(str);
        }
        Service serviceByUUID = device.getServiceByUUID(convert[0]);
        if (serviceByUUID == null) {
            throw BleErrorUtils.serviceNotFound(str2);
        }
        Characteristic characteristicByUUID = serviceByUUID.getCharacteristicByUUID(convert[1]);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str3);
        }
        Descriptor descriptorByUUID = characteristicByUUID.getDescriptorByUUID(convert[2]);
        if (descriptorByUUID != null) {
            return descriptorByUUID;
        }
        throw BleErrorUtils.descriptorNotFound(str4);
    }

    private Descriptor getDescriptor(int r5, String str, String str2) throws BleError {
        UUID[] convert = UUIDConverter.convert(str, str2);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str, str2);
        }
        Service service = this.discoveredServices.get(r5);
        if (service == null) {
            throw BleErrorUtils.serviceNotFound(Integer.toString(r5));
        }
        Characteristic characteristicByUUID = service.getCharacteristicByUUID(convert[0]);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str);
        }
        Descriptor descriptorByUUID = characteristicByUUID.getDescriptorByUUID(convert[1]);
        if (descriptorByUUID != null) {
            return descriptorByUUID;
        }
        throw BleErrorUtils.descriptorNotFound(str2);
    }

    private Descriptor getDescriptor(int r3, String str) throws BleError {
        UUID convert = UUIDConverter.convert(str);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str);
        }
        Characteristic characteristic = this.discoveredCharacteristics.get(r3);
        if (characteristic == null) {
            throw BleErrorUtils.characteristicNotFound(Integer.toString(r3));
        }
        Descriptor descriptorByUUID = characteristic.getDescriptorByUUID(convert);
        if (descriptorByUUID != null) {
            return descriptorByUUID;
        }
        throw BleErrorUtils.descriptorNotFound(str);
    }

    private Descriptor getDescriptor(int r2) throws BleError {
        Descriptor descriptor = this.discoveredDescriptors.get(r2);
        if (descriptor != null) {
            return descriptor;
        }
        throw BleErrorUtils.descriptorNotFound(Integer.toString(r2));
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void cancelTransaction(String str) {
        this.pendingTransactions.removeSubscription(str);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public void setLogLevel(String str) {
        int logLevel = LogLevel.toLogLevel(str);
        this.currentLogLevel = logLevel;
        RxBleLog.setLogLevel(logLevel);
    }

    @Override // com.polidea.multiplatformbleadapter.BleAdapter
    public String getLogLevel() {
        return LogLevel.fromLogLevel(this.currentLogLevel);
    }

    private Subscription monitorAdapterStateChanges(Context context, final OnEventCallback<String> onEventCallback) {
        if (supportsBluetoothLowEnergy()) {
            return new RxBleAdapterStateObservable(context).map(new Func1<RxBleAdapterStateObservable.BleAdapterState, String>() { // from class: com.polidea.multiplatformbleadapter.BleModule.13
                @Override // p042rx.functions.Func1
                public String call(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                    return BleModule.this.mapRxBleAdapterStateToLocalBluetoothState(bleAdapterState);
                }
            }).subscribe(new Action1<String>() { // from class: com.polidea.multiplatformbleadapter.BleModule.12
                @Override // p042rx.functions.Action1
                public void call(String str) {
                    onEventCallback.onEvent(str);
                }
            });
        }
        return null;
    }

    private boolean supportsBluetoothLowEnergy() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String mapRxBleAdapterStateToLocalBluetoothState(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
        return bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_ON ? Constants.BluetoothState.POWERED_ON : bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_OFF ? Constants.BluetoothState.POWERED_OFF : Constants.BluetoothState.RESETTING;
    }

    private void changeAdapterState(final RxBleAdapterStateObservable.BleAdapterState bleAdapterState, final String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback) {
        boolean disable;
        if (this.bluetoothManager == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothStateChangeFailed, "BluetoothManager is null", null));
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Subscription subscribe = new RxBleAdapterStateObservable(this.context).takeUntil(new Func1<RxBleAdapterStateObservable.BleAdapterState, Boolean>() { // from class: com.polidea.multiplatformbleadapter.BleModule.17
            @Override // p042rx.functions.Func1
            public Boolean call(RxBleAdapterStateObservable.BleAdapterState bleAdapterState2) {
                return Boolean.valueOf(bleAdapterState == bleAdapterState2);
            }
        }).toCompletable().doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.16
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }).subscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.14
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.success(null);
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }, new Action1<Throwable>() { // from class: com.polidea.multiplatformbleadapter.BleModule.15
            @Override // p042rx.functions.Action1
            public void call(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        });
        if (bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_ON) {
            disable = this.bluetoothAdapter.enable();
        } else {
            disable = this.bluetoothAdapter.disable();
        }
        if (!disable) {
            subscribe.unsubscribe();
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothStateChangeFailed, String.format("Couldn't set bluetooth adapter state to %s", bleAdapterState.toString()), null));
            return;
        }
        this.pendingTransactions.replaceSubscription(str, subscribe);
    }

    private void safeStartDeviceScan(UUID[] r5, int r6, int r7, final OnEventCallback<ScanResult> onEventCallback, final OnErrorCallback onErrorCallback) {
        if (this.rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorCode.BluetoothManagerDestroyed, "BleManager not created when tried to start device scan", null));
            return;
        }
        ScanSettings build = new ScanSettings.Builder().setScanMode(r6).setCallbackType(r7).build();
        int length = r5 == null ? 0 : r5.length;
        ScanFilter[] scanFilterArr = new ScanFilter[length];
        for (int r72 = 0; r72 < length; r72++) {
            scanFilterArr[r72] = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(r5[r72].toString())).build();
        }
        this.scanSubscription = this.rxBleClient.scanBleDevices(build, scanFilterArr).subscribe(new Action1<com.polidea.rxandroidble.scan.ScanResult>() { // from class: com.polidea.multiplatformbleadapter.BleModule.18
            @Override // p042rx.functions.Action1
            public void call(com.polidea.rxandroidble.scan.ScanResult scanResult) {
                String macAddress = scanResult.getBleDevice().getMacAddress();
                if (!BleModule.this.discoveredDevices.containsKey(macAddress)) {
                    BleModule.this.discoveredDevices.put(macAddress, BleModule.this.rxBleDeviceToDeviceMapper.map(scanResult.getBleDevice(), null));
                }
                onEventCallback.onEvent(BleModule.this.rxScanResultToScanResultMapper.map(scanResult));
            }
        }, new Action1<Throwable>() { // from class: com.polidea.multiplatformbleadapter.BleModule.19
            @Override // p042rx.functions.Action1
            public void call(Throwable th) {
                onErrorCallback.onError(BleModule.this.errorConverter.toError(th));
            }
        });
    }

    private Device getDeviceById(String str) throws BleError {
        Device device = this.connectedDevices.get(str);
        if (device != null) {
            return device;
        }
        throw BleErrorUtils.deviceNotConnected(str);
    }

    private RxBleConnection getConnectionOrEmitError(String str, OnErrorCallback onErrorCallback) {
        RxBleConnection rxBleConnection = this.activeConnections.get(str);
        if (rxBleConnection == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotConnected(str));
            return null;
        }
        return rxBleConnection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void safeConnectToDevice(final RxBleDevice rxBleDevice, boolean z, final int r4, RefreshGattMoment refreshGattMoment, final Long l, final int r7, OnSuccessCallback<Device> onSuccessCallback, final OnEventCallback<ConnectionState> onEventCallback, OnErrorCallback onErrorCallback) {
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Observable doOnUnsubscribe = rxBleDevice.establishConnection(z).doOnSubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.21
            @Override // p042rx.functions.Action0
            public void call() {
                onEventCallback.onEvent(ConnectionState.CONNECTING);
            }
        }).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.20
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.onDeviceDisconnected(rxBleDevice);
                onEventCallback.onEvent(ConnectionState.DISCONNECTED);
            }
        });
        Observable observable = doOnUnsubscribe;
        if (refreshGattMoment == RefreshGattMoment.ON_CONNECTED) {
            observable = doOnUnsubscribe.flatMap(new Func1<RxBleConnection, Observable<RxBleConnection>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.22
                @Override // p042rx.functions.Func1
                public Observable<RxBleConnection> call(final RxBleConnection rxBleConnection) {
                    return rxBleConnection.queue(new RefreshGattCustomOperation()).map(new Func1<Boolean, RxBleConnection>() { // from class: com.polidea.multiplatformbleadapter.BleModule.22.1
                        @Override // p042rx.functions.Func1
                        public RxBleConnection call(Boolean bool) {
                            return rxBleConnection;
                        }
                    });
                }
            });
        }
        Observable observable2 = observable;
        if (r7 > 0) {
            observable2 = observable;
            if (Build.VERSION.SDK_INT >= 21) {
                observable2 = observable.flatMap(new Func1<RxBleConnection, Observable<RxBleConnection>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.23
                    @Override // p042rx.functions.Func1
                    public Observable<RxBleConnection> call(RxBleConnection rxBleConnection) {
                        return rxBleConnection.requestConnectionPriority(r7, 1L, TimeUnit.MILLISECONDS).andThen(Observable.just(rxBleConnection));
                    }
                });
            }
        }
        if (r4 > 0 && Build.VERSION.SDK_INT >= 21) {
            observable2 = observable2.flatMap(new Func1<RxBleConnection, Observable<RxBleConnection>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.24
                @Override // p042rx.functions.Func1
                public Observable<RxBleConnection> call(final RxBleConnection rxBleConnection) {
                    return rxBleConnection.requestMtu(r4).map(new Func1<Integer, RxBleConnection>() { // from class: com.polidea.multiplatformbleadapter.BleModule.24.1
                        @Override // p042rx.functions.Func1
                        public RxBleConnection call(Integer num) {
                            return rxBleConnection;
                        }
                    });
                }
            });
        }
        if (l != null) {
            observable2 = observable2.timeout(new Func0<Observable<Long>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.25
                @Override // p042rx.functions.Func0, java.util.concurrent.Callable
                public Observable<Long> call() {
                    return Observable.timer(l.longValue(), TimeUnit.MILLISECONDS);
                }
            }, new Func1<RxBleConnection, Observable<Long>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.26
                @Override // p042rx.functions.Func1
                public Observable<Long> call(RxBleConnection rxBleConnection) {
                    return Observable.never();
                }
            });
        }
        this.connectingDevices.replaceSubscription(rxBleDevice.getMacAddress(), observable2.subscribe(new Observer<RxBleConnection>() { // from class: com.polidea.multiplatformbleadapter.BleModule.27
            @Override // p042rx.Observer
            public void onCompleted() {
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.onDeviceDisconnected(rxBleDevice);
            }

            @Override // p042rx.Observer
            public void onNext(RxBleConnection rxBleConnection) {
                Device map = BleModule.this.rxBleDeviceToDeviceMapper.map(rxBleDevice, rxBleConnection);
                onEventCallback.onEvent(ConnectionState.CONNECTED);
                BleModule.this.cleanServicesAndCharacteristicsForDevice(map);
                BleModule.this.connectedDevices.put(rxBleDevice.getMacAddress(), map);
                BleModule.this.activeConnections.put(rxBleDevice.getMacAddress(), rxBleConnection);
                safeExecutor.success(map);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceDisconnected(RxBleDevice rxBleDevice) {
        this.activeConnections.remove(rxBleDevice.getMacAddress());
        Device remove = this.connectedDevices.remove(rxBleDevice.getMacAddress());
        if (remove == null) {
            return;
        }
        cleanServicesAndCharacteristicsForDevice(remove);
        this.connectingDevices.removeSubscription(remove.getId());
    }

    private void safeDiscoverAllServicesAndCharacteristicsForDevice(final Device device, final String str, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(device.getId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        this.pendingTransactions.replaceSubscription(str, connectionOrEmitError.discoverServices().doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.29
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }).subscribe(new Observer<RxBleDeviceServices>() { // from class: com.polidea.multiplatformbleadapter.BleModule.28
            @Override // p042rx.Observer
            public void onCompleted() {
                safeExecutor.success(device);
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onNext(RxBleDeviceServices rxBleDeviceServices) {
                ArrayList arrayList = new ArrayList();
                for (BluetoothGattService bluetoothGattService : rxBleDeviceServices.getBluetoothGattServices()) {
                    Service create = BleModule.this.serviceFactory.create(device.getId(), bluetoothGattService);
                    BleModule.this.discoveredServices.put(create.getId(), create);
                    arrayList.add(create);
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        Characteristic characteristic = new Characteristic(create, bluetoothGattCharacteristic);
                        BleModule.this.discoveredCharacteristics.put(characteristic.getId(), characteristic);
                        for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                            Descriptor descriptor = new Descriptor(characteristic, bluetoothGattDescriptor);
                            BleModule.this.discoveredDescriptors.put(descriptor.getId(), descriptor);
                        }
                    }
                }
                device.setServices(arrayList);
            }
        }));
    }

    private void safeReadCharacteristicForDevice(final Characteristic characteristic, final String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(characteristic.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        this.pendingTransactions.replaceSubscription(str, connectionOrEmitError.readCharacteristic(characteristic.gattCharacteristic).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.31
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }).subscribe(new Observer<byte[]>() { // from class: com.polidea.multiplatformbleadapter.BleModule.30
            @Override // p042rx.Observer
            public void onCompleted() {
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onNext(byte[] bArr) {
                characteristic.logValue("Read from", bArr);
                characteristic.setValue(bArr);
                safeExecutor.success(new Characteristic(characteristic));
            }
        }));
    }

    private void writeCharacteristicWithValue(Characteristic characteristic, String str, Boolean bool, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            byte[] decode = Base64Converter.decode(str);
            characteristic.setWriteType(bool.booleanValue() ? 2 : 1);
            safeWriteCharacteristicForDevice(characteristic, decode, str2, onSuccessCallback, onErrorCallback);
        } catch (Throwable unused) {
            onErrorCallback.onError(BleErrorUtils.invalidWriteDataForCharacteristic(str, UUIDConverter.fromUUID(characteristic.getUuid())));
        }
    }

    private void safeWriteCharacteristicForDevice(final Characteristic characteristic, byte[] bArr, final String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(characteristic.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        this.pendingTransactions.replaceSubscription(str, connectionOrEmitError.writeCharacteristic(characteristic.gattCharacteristic, bArr).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.33
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }).subscribe(new Observer<byte[]>() { // from class: com.polidea.multiplatformbleadapter.BleModule.32
            @Override // p042rx.Observer
            public void onCompleted() {
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onNext(byte[] bArr2) {
                characteristic.logValue("Write to", bArr2);
                characteristic.setValue(bArr2);
                safeExecutor.success(new Characteristic(characteristic));
            }
        }));
    }

    private void safeMonitorCharacteristicForDevice(final Characteristic characteristic, final String str, final OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        final RxBleConnection connectionOrEmitError = getConnectionOrEmitError(characteristic.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(null, onErrorCallback);
        this.pendingTransactions.replaceSubscription(str, Observable.defer(new Func0<Observable<Observable<byte[]>>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.37
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<Observable<byte[]>> call() {
                NotificationSetupMode notificationSetupMode = characteristic.getGattDescriptor(Constants.CLIENT_CHARACTERISTIC_CONFIG_UUID) != null ? NotificationSetupMode.QUICK_SETUP : NotificationSetupMode.COMPAT;
                if (characteristic.isNotifiable()) {
                    return connectionOrEmitError.setupNotification(characteristic.gattCharacteristic, notificationSetupMode);
                }
                if (characteristic.isIndicatable()) {
                    return connectionOrEmitError.setupIndication(characteristic.gattCharacteristic, notificationSetupMode);
                }
                return Observable.error(new CannotMonitorCharacteristicException(characteristic));
            }
        }).flatMap(new Func1<Observable<byte[]>, Observable<byte[]>>() { // from class: com.polidea.multiplatformbleadapter.BleModule.36
            @Override // p042rx.functions.Func1
            public Observable<byte[]> call(Observable<byte[]> observable) {
                return observable;
            }
        }).onBackpressureBuffer().observeOn(Schedulers.computation()).doOnUnsubscribe(new Action0() { // from class: com.polidea.multiplatformbleadapter.BleModule.35
            @Override // p042rx.functions.Action0
            public void call() {
                safeExecutor.error(BleErrorUtils.cancelled());
                BleModule.this.pendingTransactions.removeSubscription(str);
            }
        }).subscribe(new Observer<byte[]>() { // from class: com.polidea.multiplatformbleadapter.BleModule.34
            @Override // p042rx.Observer
            public void onCompleted() {
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                safeExecutor.error(BleModule.this.errorConverter.toError(th));
                BleModule.this.pendingTransactions.removeSubscription(str);
            }

            @Override // p042rx.Observer
            public void onNext(byte[] bArr) {
                characteristic.logValue("Notification from", bArr);
                characteristic.setValue(bArr);
                onEventCallback.onEvent(new Characteristic(characteristic));
            }
        }));
    }

    private Characteristic getCharacteristicOrEmitError(String str, String str2, String str3, OnErrorCallback onErrorCallback) {
        UUID[] convert = UUIDConverter.convert(str2, str3);
        if (convert == null) {
            onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(str2, str3));
            return null;
        }
        Device device = this.connectedDevices.get(str);
        if (device == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotConnected(str));
            return null;
        }
        Service serviceByUUID = device.getServiceByUUID(convert[0]);
        if (serviceByUUID == null) {
            onErrorCallback.onError(BleErrorUtils.serviceNotFound(str2));
            return null;
        }
        Characteristic characteristicByUUID = serviceByUUID.getCharacteristicByUUID(convert[1]);
        if (characteristicByUUID == null) {
            onErrorCallback.onError(BleErrorUtils.characteristicNotFound(str3));
            return null;
        }
        return characteristicByUUID;
    }

    private Characteristic getCharacteristicOrEmitError(int r4, String str, OnErrorCallback onErrorCallback) {
        UUID convert = UUIDConverter.convert(str);
        if (convert == null) {
            onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(str));
            return null;
        }
        Service service = this.discoveredServices.get(r4);
        if (service == null) {
            onErrorCallback.onError(BleErrorUtils.serviceNotFound(Integer.toString(r4)));
            return null;
        }
        Characteristic characteristicByUUID = service.getCharacteristicByUUID(convert);
        if (characteristicByUUID == null) {
            onErrorCallback.onError(BleErrorUtils.characteristicNotFound(str));
            return null;
        }
        return characteristicByUUID;
    }

    private Characteristic getCharacteristicOrEmitError(int r2, OnErrorCallback onErrorCallback) {
        Characteristic characteristic = this.discoveredCharacteristics.get(r2);
        if (characteristic == null) {
            onErrorCallback.onError(BleErrorUtils.characteristicNotFound(Integer.toString(r2)));
            return null;
        }
        return characteristic;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanServicesAndCharacteristicsForDevice(Device device) {
        for (int size = this.discoveredServices.size() - 1; size >= 0; size--) {
            int keyAt = this.discoveredServices.keyAt(size);
            if (this.discoveredServices.get(keyAt).getDeviceID().equals(device.getId())) {
                this.discoveredServices.remove(keyAt);
            }
        }
        for (int size2 = this.discoveredCharacteristics.size() - 1; size2 >= 0; size2--) {
            int keyAt2 = this.discoveredCharacteristics.keyAt(size2);
            if (this.discoveredCharacteristics.get(keyAt2).getDeviceId().equals(device.getId())) {
                this.discoveredCharacteristics.remove(keyAt2);
            }
        }
        for (int size3 = this.discoveredDescriptors.size() - 1; size3 >= 0; size3--) {
            int keyAt3 = this.discoveredDescriptors.keyAt(size3);
            if (this.discoveredDescriptors.get(keyAt3).getDeviceId().equals(device.getId())) {
                this.discoveredDescriptors.remove(keyAt3);
            }
        }
    }
}
