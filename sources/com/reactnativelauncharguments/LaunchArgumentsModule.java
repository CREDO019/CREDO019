package com.reactnativelauncharguments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class LaunchArgumentsModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    @ReactMethod
    public void foo() {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "LaunchArguments";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LaunchArgumentsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        Intent intent;
        HashMap hashMap = new HashMap();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null && (intent = currentActivity.getIntent()) != null) {
            Bundle bundleExtra = intent.getBundleExtra("launchArgs");
            if (bundleExtra != null) {
                for (String str : bundleExtra.keySet()) {
                    hashMap.put(str, bundleExtra.getString(str));
                }
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str2 : extras.keySet()) {
                    if (!"launchArgs".equals(str2) && !"android.nfc.extra.NDEF_MESSAGES".equals(str2)) {
                        if (!Serializable.class.isInstance(extras.get(str2))) {
                            hashMap.put(str2, extras.getString(str2));
                        } else {
                            hashMap.put(str2, extras.get(str2));
                        }
                    }
                }
            }
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("value", hashMap);
        return hashMap2;
    }
}
