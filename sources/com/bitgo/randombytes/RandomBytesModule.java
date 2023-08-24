package com.bitgo.randombytes;

import android.util.Base64;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
class RandomBytesModule extends ReactContextBaseJavaModule {
    private static final String SEED_KEY = "seed";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNRandomBytes";
    }

    public RandomBytesModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void randomBytes(int r4, Callback callback) {
        callback.invoke(null, getRandomBytes(r4));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(SEED_KEY, getRandomBytes(4096));
        return hashMap;
    }

    private String getRandomBytes(int r2) {
        byte[] bArr = new byte[r2];
        new SecureRandom().nextBytes(bArr);
        return Base64.encodeToString(bArr, 2);
    }
}
