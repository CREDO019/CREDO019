package com.facebook.react.fabric.events;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.fabric.FabricSoLoader;

/* loaded from: classes.dex */
public class EventEmitterWrapper {
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    private native void invokeEvent(String str, NativeMap nativeMap, int r3);

    private native void invokeUniqueEvent(String str, NativeMap nativeMap, int r3);

    static {
        FabricSoLoader.staticInit();
    }

    private EventEmitterWrapper() {
    }

    public synchronized void invoke(String str, WritableMap writableMap, int r4) {
        if (isValid()) {
            invokeEvent(str, writableMap == null ? new WritableNativeMap() : (NativeMap) writableMap, r4);
        }
    }

    public synchronized void invokeUnique(String str, WritableMap writableMap, int r4) {
        if (isValid()) {
            invokeUniqueEvent(str, writableMap == null ? new WritableNativeMap() : (NativeMap) writableMap, r4);
        }
    }

    public synchronized void destroy() {
        HybridData hybridData = this.mHybridData;
        if (hybridData != null) {
            hybridData.resetNative();
        }
    }

    private boolean isValid() {
        HybridData hybridData = this.mHybridData;
        if (hybridData != null) {
            return hybridData.isValid();
        }
        return false;
    }
}
