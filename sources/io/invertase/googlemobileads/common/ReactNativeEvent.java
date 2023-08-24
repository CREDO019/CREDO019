package io.invertase.googlemobileads.common;

import com.facebook.react.bridge.WritableMap;
import io.invertase.googlemobileads.interfaces.NativeEvent;

/* loaded from: classes5.dex */
public class ReactNativeEvent implements NativeEvent {
    private WritableMap eventBody;
    private String eventName;

    public ReactNativeEvent(String str, WritableMap writableMap) {
        this.eventName = str;
        this.eventBody = writableMap;
    }

    @Override // io.invertase.googlemobileads.interfaces.NativeEvent
    public String getEventName() {
        return this.eventName;
    }

    @Override // io.invertase.googlemobileads.interfaces.NativeEvent
    public WritableMap getEventBody() {
        return this.eventBody;
    }
}
