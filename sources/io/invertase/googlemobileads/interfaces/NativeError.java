package io.invertase.googlemobileads.interfaces;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes5.dex */
public interface NativeError {
    String getErrorCode();

    String getErrorMessage();

    WritableMap getUserInfo();
}
