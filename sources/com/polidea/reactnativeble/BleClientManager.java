package com.polidea.reactnativeble;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.polidea.multiplatformbleadapter.BleAdapter;
import com.polidea.multiplatformbleadapter.BleAdapterFactory;
import com.polidea.multiplatformbleadapter.Characteristic;
import com.polidea.multiplatformbleadapter.ConnectionOptions;
import com.polidea.multiplatformbleadapter.ConnectionState;
import com.polidea.multiplatformbleadapter.Descriptor;
import com.polidea.multiplatformbleadapter.Device;
import com.polidea.multiplatformbleadapter.OnErrorCallback;
import com.polidea.multiplatformbleadapter.OnEventCallback;
import com.polidea.multiplatformbleadapter.OnSuccessCallback;
import com.polidea.multiplatformbleadapter.RefreshGattMoment;
import com.polidea.multiplatformbleadapter.Service;
import com.polidea.multiplatformbleadapter.errors.BleError;
import com.polidea.reactnativeble.converter.BleErrorToJsObjectConverter;
import com.polidea.reactnativeble.converter.CharacteristicToJsObjectConverter;
import com.polidea.reactnativeble.converter.DescriptorToJsObjectConverter;
import com.polidea.reactnativeble.converter.DeviceToJsObjectConverter;
import com.polidea.reactnativeble.converter.ScanResultToJsObjectConverter;
import com.polidea.reactnativeble.converter.ServiceToJsObjectConverter;
import com.polidea.reactnativeble.utils.ReadableArrayConverter;
import com.polidea.reactnativeble.utils.SafePromise;
import com.polidea.rxandroidble.ClientComponent;
import com.polidea.rxandroidble.internal.connection.ConnectionComponent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class BleClientManager extends ReactContextBaseJavaModule {
    private static final String NAME = "BleClientManager";
    private BleAdapter bleAdapter;
    private final CharacteristicToJsObjectConverter characteristicConverter;
    private final DescriptorToJsObjectConverter descriptorConverter;
    private final DeviceToJsObjectConverter deviceConverter;
    private final BleErrorToJsObjectConverter errorConverter;
    private final ScanResultToJsObjectConverter scanResultConverter;
    private final ServiceToJsObjectConverter serviceConverter;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public BleClientManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.errorConverter = new BleErrorToJsObjectConverter();
        this.scanResultConverter = new ScanResultToJsObjectConverter();
        this.deviceConverter = new DeviceToJsObjectConverter();
        this.characteristicConverter = new CharacteristicToJsObjectConverter();
        this.descriptorConverter = new DescriptorToJsObjectConverter();
        this.serviceConverter = new ServiceToJsObjectConverter();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        Event[] values;
        HashMap hashMap = new HashMap();
        for (Event event : Event.values()) {
            hashMap.put(event.name, event.name);
        }
        return hashMap;
    }

    @ReactMethod
    public void createClient(String str) {
        BleAdapter newAdapter = BleAdapterFactory.getNewAdapter(getReactApplicationContext());
        this.bleAdapter = newAdapter;
        newAdapter.createClient(str, new OnEventCallback<String>() { // from class: com.polidea.reactnativeble.BleClientManager.1
            @Override // com.polidea.multiplatformbleadapter.OnEventCallback
            public void onEvent(String str2) {
                BleClientManager.this.sendEvent(Event.StateChangeEvent, str2);
            }
        }, new OnEventCallback<Integer>() { // from class: com.polidea.reactnativeble.BleClientManager.2
            @Override // com.polidea.multiplatformbleadapter.OnEventCallback
            public void onEvent(Integer num) {
                BleClientManager.this.sendEvent(Event.RestoreStateEvent, null);
            }
        });
    }

    @ReactMethod
    public void destroyClient() {
        this.bleAdapter.destroyClient();
        this.bleAdapter = null;
    }

    @ReactMethod
    public void cancelTransaction(String str) {
        this.bleAdapter.cancelTransaction(str);
    }

    @ReactMethod
    public void setLogLevel(String str) {
        this.bleAdapter.setLogLevel(str);
    }

    @ReactMethod
    public void logLevel(Promise promise) {
        promise.resolve(this.bleAdapter.getLogLevel());
    }

    @ReactMethod
    public void enable(String str, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.enable(str, new OnSuccessCallback<Void>() { // from class: com.polidea.reactnativeble.BleClientManager.3
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Void r2) {
                safePromise.resolve(null);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.4
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void disable(String str, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.disable(str, new OnSuccessCallback<Void>() { // from class: com.polidea.reactnativeble.BleClientManager.5
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Void r2) {
                safePromise.resolve(null);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.6
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void state(Promise promise) {
        promise.resolve(this.bleAdapter.getCurrentState());
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void startDeviceScan(com.facebook.react.bridge.ReadableArray r9, com.facebook.react.bridge.ReadableMap r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            if (r10 == 0) goto L31
            java.lang.String r2 = "scanMode"
            boolean r3 = r10.hasKey(r2)
            if (r3 == 0) goto L18
            com.facebook.react.bridge.ReadableType r3 = r10.getType(r2)
            com.facebook.react.bridge.ReadableType r4 = com.facebook.react.bridge.ReadableType.Number
            if (r3 != r4) goto L18
            int r0 = r10.getInt(r2)
        L18:
            java.lang.String r2 = "callbackType"
            boolean r3 = r10.hasKey(r2)
            if (r3 == 0) goto L2f
            com.facebook.react.bridge.ReadableType r3 = r10.getType(r2)
            com.facebook.react.bridge.ReadableType r4 = com.facebook.react.bridge.ReadableType.Number
            if (r3 != r4) goto L2f
            int r1 = r10.getInt(r2)
            r4 = r0
            r5 = r1
            goto L33
        L2f:
            r4 = r0
            goto L32
        L31:
            r4 = 0
        L32:
            r5 = 1
        L33:
            com.polidea.multiplatformbleadapter.BleAdapter r2 = r8.bleAdapter
            if (r9 == 0) goto L3c
            java.lang.String[] r9 = com.polidea.reactnativeble.utils.ReadableArrayConverter.toStringArray(r9)
            goto L3d
        L3c:
            r9 = 0
        L3d:
            r3 = r9
            com.polidea.reactnativeble.BleClientManager$7 r6 = new com.polidea.reactnativeble.BleClientManager$7
            r6.<init>()
            com.polidea.reactnativeble.BleClientManager$8 r7 = new com.polidea.reactnativeble.BleClientManager$8
            r7.<init>()
            r2.startDeviceScan(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.polidea.reactnativeble.BleClientManager.startDeviceScan(com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableMap):void");
    }

    @ReactMethod
    public void stopDeviceScan() {
        this.bleAdapter.stopDeviceScan();
    }

    @ReactMethod
    public void devices(ReadableArray readableArray, final Promise promise) {
        this.bleAdapter.getKnownDevices(ReadableArrayConverter.toStringArray(readableArray), new OnSuccessCallback<Device[]>() { // from class: com.polidea.reactnativeble.BleClientManager.9
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device[] deviceArr) {
                WritableArray createArray = Arguments.createArray();
                for (Device device : deviceArr) {
                    createArray.pushMap(BleClientManager.this.deviceConverter.toJSObject(device));
                }
                promise.resolve(createArray);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.10
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void connectedDevices(ReadableArray readableArray, final Promise promise) {
        this.bleAdapter.getConnectedDevices(ReadableArrayConverter.toStringArray(readableArray), new OnSuccessCallback<Device[]>() { // from class: com.polidea.reactnativeble.BleClientManager.11
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device[] deviceArr) {
                WritableArray createArray = Arguments.createArray();
                for (Device device : deviceArr) {
                    createArray.pushMap(BleClientManager.this.deviceConverter.toJSObject(device));
                }
                promise.resolve(createArray);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.12
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void requestConnectionPriorityForDevice(String str, int r9, String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.requestConnectionPriorityForDevice(str, r9, str2, new OnSuccessCallback<Device>() { // from class: com.polidea.reactnativeble.BleClientManager.13
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device device) {
                safePromise.resolve(BleClientManager.this.deviceConverter.toJSObject(device));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.14
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void requestMTUForDevice(String str, int r9, String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.requestMTUForDevice(str, r9, str2, new OnSuccessCallback<Device>() { // from class: com.polidea.reactnativeble.BleClientManager.15
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device device) {
                safePromise.resolve(BleClientManager.this.deviceConverter.toJSObject(device));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.16
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readRSSIForDevice(String str, String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.readRSSIForDevice(str, str2, new OnSuccessCallback<Device>() { // from class: com.polidea.reactnativeble.BleClientManager.17
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device device) {
                safePromise.resolve(BleClientManager.this.deviceConverter.toJSObject(device));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.18
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void connectToDevice(final String str, ReadableMap readableMap, Promise promise) {
        Integer num;
        RefreshGattMoment refreshGattMoment;
        int r7;
        int r10;
        final SafePromise safePromise = new SafePromise(promise);
        boolean z = false;
        if (readableMap != null) {
            boolean z2 = (readableMap.hasKey(ConnectionComponent.NamedBooleans.AUTO_CONNECT) && readableMap.getType(ConnectionComponent.NamedBooleans.AUTO_CONNECT) == ReadableType.Boolean) ? readableMap.getBoolean(ConnectionComponent.NamedBooleans.AUTO_CONNECT) : false;
            int r6 = (readableMap.hasKey("requestMTU") && readableMap.getType("requestMTU") == ReadableType.Number) ? readableMap.getInt("requestMTU") : 0;
            RefreshGattMoment byName = (readableMap.hasKey("refreshGatt") && readableMap.getType("refreshGatt") == ReadableType.String) ? RefreshGattMoment.getByName(readableMap.getString("refreshGatt")) : null;
            Integer valueOf = (readableMap.hasKey(ClientComponent.NamedSchedulers.TIMEOUT) && readableMap.getType(ClientComponent.NamedSchedulers.TIMEOUT) == ReadableType.Number) ? Integer.valueOf(readableMap.getInt(ClientComponent.NamedSchedulers.TIMEOUT)) : null;
            if (readableMap.hasKey("connectionPriority") && readableMap.getType("connectionPriority") == ReadableType.Number) {
                r10 = readableMap.getInt("connectionPriority");
                z = z2;
                num = valueOf;
            } else {
                z = z2;
                num = valueOf;
                r10 = 0;
            }
            refreshGattMoment = byName;
            r7 = r6;
        } else {
            num = null;
            refreshGattMoment = null;
            r7 = 0;
            r10 = 0;
        }
        this.bleAdapter.connectToDevice(str, new ConnectionOptions(Boolean.valueOf(z), r7, refreshGattMoment, num != null ? Long.valueOf(num.longValue()) : null, r10), new OnSuccessCallback<Device>() { // from class: com.polidea.reactnativeble.BleClientManager.19
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device device) {
                safePromise.resolve(BleClientManager.this.deviceConverter.toJSObject(device));
            }
        }, new OnEventCallback<ConnectionState>() { // from class: com.polidea.reactnativeble.BleClientManager.20
            @Override // com.polidea.multiplatformbleadapter.OnEventCallback
            public void onEvent(ConnectionState connectionState) {
                if (connectionState == ConnectionState.DISCONNECTED) {
                    WritableArray createArray = Arguments.createArray();
                    createArray.pushNull();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("id", str);
                    createArray.pushMap(createMap);
                    BleClientManager.this.sendEvent(Event.DisconnectionEvent, createArray);
                }
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.21
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void cancelDeviceConnection(String str, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.cancelDeviceConnection(str, new OnSuccessCallback<Device>() { // from class: com.polidea.reactnativeble.BleClientManager.22
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device device) {
                safePromise.resolve(BleClientManager.this.deviceConverter.toJSObject(device));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.23
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void isDeviceConnected(String str, final Promise promise) {
        this.bleAdapter.isDeviceConnected(str, new OnSuccessCallback<Boolean>() { // from class: com.polidea.reactnativeble.BleClientManager.24
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Boolean bool) {
                promise.resolve(bool);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.25
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void discoverAllServicesAndCharacteristicsForDevice(String str, String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.discoverAllServicesAndCharacteristicsForDevice(str, str2, new OnSuccessCallback<Device>() { // from class: com.polidea.reactnativeble.BleClientManager.26
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Device device) {
                safePromise.resolve(BleClientManager.this.deviceConverter.toJSObject(device));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.27
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void servicesForDevice(String str, Promise promise) {
        try {
            List<Service> servicesForDevice = this.bleAdapter.getServicesForDevice(str);
            WritableArray createArray = Arguments.createArray();
            for (Service service : servicesForDevice) {
                createArray.pushMap(this.serviceConverter.toJSObject(service));
            }
            promise.resolve(createArray);
        } catch (BleError e) {
            promise.reject((String) null, this.errorConverter.toJs(e));
        }
    }

    @ReactMethod
    public void characteristicsForDevice(String str, String str2, Promise promise) {
        try {
            List<Characteristic> characteristicsForDevice = this.bleAdapter.getCharacteristicsForDevice(str, str2);
            WritableArray createArray = Arguments.createArray();
            for (Characteristic characteristic : characteristicsForDevice) {
                createArray.pushMap(this.characteristicConverter.toJSObject(characteristic));
            }
            promise.resolve(createArray);
        } catch (BleError e) {
            promise.reject((String) null, this.errorConverter.toJs(e));
        }
    }

    @ReactMethod
    public void characteristicsForService(int r4, Promise promise) {
        try {
            List<Characteristic> characteristicsForService = this.bleAdapter.getCharacteristicsForService(r4);
            WritableArray createArray = Arguments.createArray();
            for (Characteristic characteristic : characteristicsForService) {
                createArray.pushMap(this.characteristicConverter.toJSObject(characteristic));
            }
            promise.resolve(createArray);
        } catch (BleError e) {
            promise.reject((String) null, this.errorConverter.toJs(e));
        }
    }

    @ReactMethod
    public void descriptorsForDevice(String str, String str2, String str3, Promise promise) {
        try {
            List<Descriptor> descriptorsForDevice = this.bleAdapter.descriptorsForDevice(str, str2, str3);
            WritableArray createArray = Arguments.createArray();
            for (Descriptor descriptor : descriptorsForDevice) {
                createArray.pushMap(this.descriptorConverter.toJSObject(descriptor));
            }
            promise.resolve(createArray);
        } catch (BleError e) {
            promise.reject((String) null, this.errorConverter.toJs(e));
        }
    }

    @ReactMethod
    public void descriptorsForService(int r3, String str, Promise promise) {
        try {
            List<Descriptor> descriptorsForService = this.bleAdapter.descriptorsForService(r3, str);
            WritableArray createArray = Arguments.createArray();
            for (Descriptor descriptor : descriptorsForService) {
                createArray.pushMap(this.descriptorConverter.toJSObject(descriptor));
            }
            promise.resolve(createArray);
        } catch (BleError e) {
            promise.reject((String) null, this.errorConverter.toJs(e));
        }
    }

    @ReactMethod
    public void descriptorsForCharacteristic(int r4, Promise promise) {
        try {
            List<Descriptor> descriptorsForCharacteristic = this.bleAdapter.descriptorsForCharacteristic(r4);
            WritableArray createArray = Arguments.createArray();
            for (Descriptor descriptor : descriptorsForCharacteristic) {
                createArray.pushMap(this.descriptorConverter.toJSObject(descriptor));
            }
            promise.resolve(createArray);
        } catch (BleError e) {
            promise.reject((String) null, this.errorConverter.toJs(e));
        }
    }

    @ReactMethod
    public void writeCharacteristicForDevice(String str, String str2, String str3, String str4, Boolean bool, String str5, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.writeCharacteristicForDevice(str, str2, str3, str4, bool.booleanValue(), str5, new OnSuccessCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.28
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Characteristic characteristic) {
                safePromise.resolve(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.29
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void writeCharacteristicForService(int r10, String str, String str2, Boolean bool, String str3, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.writeCharacteristicForService(r10, str, str2, bool.booleanValue(), str3, new OnSuccessCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.30
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Characteristic characteristic) {
                safePromise.resolve(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.31
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void writeCharacteristic(int r9, String str, Boolean bool, String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.writeCharacteristic(r9, str, bool.booleanValue(), str2, new OnSuccessCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.32
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Characteristic characteristic) {
                safePromise.resolve(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.33
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readCharacteristicForDevice(String str, String str2, String str3, String str4, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.readCharacteristicForDevice(str, str2, str3, str4, new OnSuccessCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.34
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Characteristic characteristic) {
                safePromise.resolve(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.35
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readCharacteristicForService(int r8, String str, String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.readCharacteristicForService(r8, str, str2, new OnSuccessCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.36
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Characteristic characteristic) {
                safePromise.resolve(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.37
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readCharacteristic(int r4, String str, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.readCharacteristic(r4, str, new OnSuccessCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.38
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Characteristic characteristic) {
                safePromise.resolve(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.39
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void monitorCharacteristicForDevice(String str, String str2, String str3, final String str4, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.monitorCharacteristicForDevice(str, str2, str3, str4, new OnEventCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.40
            @Override // com.polidea.multiplatformbleadapter.OnEventCallback
            public void onEvent(Characteristic characteristic) {
                WritableArray createArray = Arguments.createArray();
                createArray.pushNull();
                createArray.pushMap(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
                createArray.pushString(str4);
                BleClientManager.this.sendEvent(Event.ReadEvent, createArray);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.41
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void monitorCharacteristicForService(int r8, String str, final String str2, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.monitorCharacteristicForService(r8, str, str2, new OnEventCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.42
            @Override // com.polidea.multiplatformbleadapter.OnEventCallback
            public void onEvent(Characteristic characteristic) {
                WritableArray createArray = Arguments.createArray();
                createArray.pushNull();
                createArray.pushMap(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
                createArray.pushString(str2);
                BleClientManager.this.sendEvent(Event.ReadEvent, createArray);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.43
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void monitorCharacteristic(int r4, final String str, Promise promise) {
        final SafePromise safePromise = new SafePromise(promise);
        this.bleAdapter.monitorCharacteristic(r4, str, new OnEventCallback<Characteristic>() { // from class: com.polidea.reactnativeble.BleClientManager.44
            @Override // com.polidea.multiplatformbleadapter.OnEventCallback
            public void onEvent(Characteristic characteristic) {
                WritableArray createArray = Arguments.createArray();
                createArray.pushNull();
                createArray.pushMap(BleClientManager.this.characteristicConverter.toJSObject(characteristic));
                createArray.pushString(str);
                BleClientManager.this.sendEvent(Event.ReadEvent, createArray);
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.45
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                safePromise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readDescriptorForDevice(String str, String str2, String str3, String str4, String str5, final Promise promise) {
        this.bleAdapter.readDescriptorForDevice(str, str2, str3, str4, str5, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.46
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.47
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readDescriptorForService(int r8, String str, String str2, String str3, final Promise promise) {
        this.bleAdapter.readDescriptorForService(r8, str, str2, str3, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.48
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.49
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readDescriptorForCharacteristic(int r7, String str, String str2, final Promise promise) {
        this.bleAdapter.readDescriptorForCharacteristic(r7, str, str2, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.50
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.51
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void readDescriptor(int r4, String str, final Promise promise) {
        this.bleAdapter.readDescriptor(r4, str, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.52
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.53
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void writeDescriptorForDevice(String str, String str2, String str3, String str4, String str5, String str6, final Promise promise) {
        this.bleAdapter.writeDescriptorForDevice(str, str2, str3, str4, str5, str6, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.54
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.55
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void writeDescriptorForService(int r9, String str, String str2, String str3, String str4, final Promise promise) {
        this.bleAdapter.writeDescriptorForService(r9, str, str2, str3, str4, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.56
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.57
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void writeDescriptorForCharacteristic(int r8, String str, String str2, String str3, final Promise promise) {
        this.bleAdapter.writeDescriptorForCharacteristic(r8, str, str2, str3, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.58
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.59
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    @ReactMethod
    public void writeDescriptor(int r7, String str, String str2, final Promise promise) {
        this.bleAdapter.writeDescriptor(r7, str, str2, new OnSuccessCallback<Descriptor>() { // from class: com.polidea.reactnativeble.BleClientManager.60
            @Override // com.polidea.multiplatformbleadapter.OnSuccessCallback
            public void onSuccess(Descriptor descriptor) {
                promise.resolve(BleClientManager.this.descriptorConverter.toJSObject(descriptor));
            }
        }, new OnErrorCallback() { // from class: com.polidea.reactnativeble.BleClientManager.61
            @Override // com.polidea.multiplatformbleadapter.OnErrorCallback
            public void onError(BleError bleError) {
                promise.reject((String) null, BleClientManager.this.errorConverter.toJs(bleError));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(Event event, Object obj) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(event.name, obj);
    }
}
