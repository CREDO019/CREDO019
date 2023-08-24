package io.invertase.googlemobileads.common;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.onesignal.OneSignalDbContract;
import io.invertase.googlemobileads.interfaces.ContextProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* loaded from: classes5.dex */
public class ReactNativeModule extends ReactContextBaseJavaModule implements ContextProvider {
    private final TaskExecutorService executorService;
    private String moduleName;

    public ReactNativeModule(ReactApplicationContext reactApplicationContext, String str) {
        super(reactApplicationContext);
        this.moduleName = str;
        this.executorService = new TaskExecutorService(getName());
    }

    public static void rejectPromiseWithCodeAndMessage(Promise promise, String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("code", str);
        createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str2);
        promise.reject(str, str2, createMap);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        super.initialize();
    }

    @Override // io.invertase.googlemobileads.interfaces.ContextProvider
    public ReactContext getContext() {
        return getReactApplicationContext();
    }

    public ExecutorService getExecutor() {
        return this.executorService.getExecutor();
    }

    public ExecutorService getTransactionalExecutor() {
        return this.executorService.getTransactionalExecutor();
    }

    public ExecutorService getTransactionalExecutor(String str) {
        return this.executorService.getTransactionalExecutor(str);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.executorService.shutdown();
    }

    public void removeEventListeningExecutor(String str) {
        this.executorService.removeExecutor(this.executorService.getExecutorName(true, str));
    }

    @Override // io.invertase.googlemobileads.interfaces.ContextProvider
    public Context getApplicationContext() {
        return getReactApplicationContext().getApplicationContext();
    }

    @Override // io.invertase.googlemobileads.interfaces.ContextProvider
    public Activity getActivity() {
        return getCurrentActivity();
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return this.moduleName;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return new HashMap();
    }
}
