package io.invertase.googlemobileads;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import io.invertase.googlemobileads.common.RCTConvert;
import io.invertase.googlemobileads.common.ReactNativeEvent;
import io.invertase.googlemobileads.common.ReactNativeEventEmitter;
import io.invertase.googlemobileads.common.ReactNativeJSON;
import io.invertase.googlemobileads.common.ReactNativeMeta;
import io.invertase.googlemobileads.common.ReactNativeModule;
import io.invertase.googlemobileads.common.ReactNativePreferences;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ReactNativeAppModule extends ReactNativeModule {
    private static final String TAG = "RNAppModule";

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    @ReactMethod
    public void setAutomaticDataCollectionEnabled(String str, Boolean bool) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactNativeAppModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    @Override // io.invertase.googlemobileads.common.ReactNativeModule, com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        super.initialize();
        ReactNativeEventEmitter.getSharedInstance().attachReactContext(getContext());
    }

    @ReactMethod
    public void initializeApp(ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        promise.resolve(readableMap);
    }

    @ReactMethod
    public void deleteApp(String str, Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void eventsNotifyReady(Boolean bool) {
        ReactNativeEventEmitter.getSharedInstance().notifyJsReady(bool);
    }

    @ReactMethod
    public void eventsGetListeners(Promise promise) {
        promise.resolve(ReactNativeEventEmitter.getSharedInstance().getListenersMap());
    }

    @ReactMethod
    public void eventsPing(String str, ReadableMap readableMap, Promise promise) {
        ReactNativeEventEmitter.getSharedInstance().sendEvent(new ReactNativeEvent(str, RCTConvert.readableMapToWritableMap(readableMap)));
        promise.resolve(RCTConvert.readableMapToWritableMap(readableMap));
    }

    @ReactMethod
    public void eventsAddListener(String str) {
        ReactNativeEventEmitter.getSharedInstance().addListener(str);
    }

    @ReactMethod
    public void eventsRemoveListener(String str, Boolean bool) {
        ReactNativeEventEmitter.getSharedInstance().removeListener(str, bool);
    }

    @ReactMethod
    public void metaGetAll(Promise promise) {
        promise.resolve(ReactNativeMeta.getSharedInstance().getAll());
    }

    @ReactMethod
    public void jsonGetAll(Promise promise) {
        promise.resolve(ReactNativeJSON.getSharedInstance().getAll());
    }

    @ReactMethod
    public void preferencesSetBool(String str, boolean z, Promise promise) {
        ReactNativePreferences.getSharedInstance().setBooleanValue(str, z);
        promise.resolve(null);
    }

    @ReactMethod
    public void preferencesSetString(String str, String str2, Promise promise) {
        ReactNativePreferences.getSharedInstance().setStringValue(str, str2);
        promise.resolve(null);
    }

    @ReactMethod
    public void preferencesGetAll(Promise promise) {
        promise.resolve(ReactNativePreferences.getSharedInstance().getAll());
    }

    @ReactMethod
    public void preferencesClearAll(Promise promise) {
        ReactNativePreferences.getSharedInstance().clearAll();
        promise.resolve(null);
    }

    @Override // io.invertase.googlemobileads.common.ReactNativeModule, com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return new HashMap();
    }
}
