package com.facebook.react.modules.network;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.net.SocketTimeoutException;

/* loaded from: classes.dex */
public class ResponseUtil {
    public static void onDataSend(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, long j, long j2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushInt((int) j);
        createArray.pushInt((int) j2);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didSendNetworkData", createArray);
        }
    }

    public static void onIncrementalDataReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, String str, long j, long j2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushString(str);
        createArray.pushInt((int) j);
        createArray.pushInt((int) j2);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkIncrementalData", createArray);
        }
    }

    public static void onDataReceivedProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, long j, long j2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushInt((int) j);
        createArray.pushInt((int) j2);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", createArray);
        }
    }

    public static void onDataReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, String str) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushString(str);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkData", createArray);
        }
    }

    public static void onDataReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, WritableMap writableMap) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushMap(writableMap);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkData", createArray);
        }
    }

    public static void onRequestError(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, String str, Throwable th) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushString(str);
        if (th != null && th.getClass() == SocketTimeoutException.class) {
            createArray.pushBoolean(true);
        }
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
        }
    }

    public static void onRequestSuccess(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushNull();
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
        }
    }

    public static void onResponseReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int r2, int r3, WritableMap writableMap, String str) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r2);
        createArray.pushInt(r3);
        createArray.pushMap(writableMap);
        createArray.pushString(str);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkResponse", createArray);
        }
    }
}
