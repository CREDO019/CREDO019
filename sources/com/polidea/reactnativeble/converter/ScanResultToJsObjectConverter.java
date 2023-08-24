package com.polidea.reactnativeble.converter;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.polidea.multiplatformbleadapter.AdvertisementData;
import com.polidea.multiplatformbleadapter.ScanResult;
import com.polidea.multiplatformbleadapter.utils.Base64Converter;
import com.polidea.multiplatformbleadapter.utils.UUIDConverter;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes3.dex */
public class ScanResultToJsObjectConverter extends JSObjectConverter<ScanResult> {

    /* loaded from: classes3.dex */
    interface Metadata {

        /* renamed from: ID */
        public static final String f1321ID = "id";
        public static final String IS_CONNECTABLE = "isConnectable";
        public static final String LOCAL_NAME = "localName";
        public static final String MANUFACTURER_DATA = "manufacturerData";
        public static final String MTU = "mtu";
        public static final String NAME = "name";
        public static final String OVERFLOW_SERVICE_UUIDS = "overflowServiceUUIDs";
        public static final String RSSI = "rssi";
        public static final String SERVICE_DATA = "serviceData";
        public static final String SERVICE_UUIDS = "serviceUUIDs";
        public static final String SOLICITED_SERVICE_UUIDS = "solicitedServiceUUIDs";
        public static final String TX_POWER_LEVEL = "txPowerLevel";
    }

    @Override // com.polidea.reactnativeble.converter.JSObjectConverter
    public /* bridge */ /* synthetic */ WritableArray toJSCallback(ScanResult scanResult) {
        return super.toJSCallback(scanResult);
    }

    @Override // com.polidea.reactnativeble.converter.JSObjectConverter
    public WritableMap toJSObject(ScanResult scanResult) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("id", scanResult.getDeviceId());
        createMap.putString("name", scanResult.getDeviceName());
        createMap.putInt("rssi", scanResult.getRssi());
        createMap.putInt("mtu", scanResult.getMtu());
        AdvertisementData advertisementData = scanResult.getAdvertisementData();
        createMap.putString("manufacturerData", advertisementData.getManufacturerData() != null ? Base64Converter.encode(advertisementData.getManufacturerData()) : null);
        if (advertisementData.getServiceData() != null) {
            WritableMap createMap2 = Arguments.createMap();
            for (Map.Entry<UUID, byte[]> entry : advertisementData.getServiceData().entrySet()) {
                createMap2.putString(UUIDConverter.fromUUID(entry.getKey()), Base64Converter.encode(entry.getValue()));
            }
            createMap.putMap("serviceData", createMap2);
        } else {
            createMap.putNull("serviceData");
        }
        if (advertisementData.getServiceUUIDs() != null) {
            WritableArray createArray = Arguments.createArray();
            for (UUID r4 : advertisementData.getServiceUUIDs()) {
                createArray.pushString(UUIDConverter.fromUUID(r4));
            }
            createMap.putArray("serviceUUIDs", createArray);
        } else {
            createMap.putNull("serviceUUIDs");
        }
        if (advertisementData.getLocalName() != null) {
            createMap.putString("localName", advertisementData.getLocalName());
        } else {
            createMap.putNull("localName");
        }
        if (advertisementData.getTxPowerLevel() != null) {
            createMap.putInt("txPowerLevel", advertisementData.getTxPowerLevel().intValue());
        } else {
            createMap.putNull("txPowerLevel");
        }
        if (advertisementData.getSolicitedServiceUUIDs() != null) {
            WritableArray createArray2 = Arguments.createArray();
            for (UUID r3 : advertisementData.getSolicitedServiceUUIDs()) {
                createArray2.pushString(UUIDConverter.fromUUID(r3));
            }
            createMap.putArray("solicitedServiceUUIDs", createArray2);
        } else {
            createMap.putNull("solicitedServiceUUIDs");
        }
        createMap.putNull("isConnectable");
        createMap.putNull("overflowServiceUUIDs");
        return createMap;
    }
}
