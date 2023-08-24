package org.linusu;

import android.util.Base64;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* loaded from: classes5.dex */
public class RNGetRandomValuesModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNGetRandomValues";
    }

    public RNGetRandomValuesModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getRandomBase64(int r2) throws NoSuchAlgorithmException {
        byte[] bArr = new byte[r2];
        new SecureRandom().nextBytes(bArr);
        return Base64.encodeToString(bArr, 2);
    }
}
