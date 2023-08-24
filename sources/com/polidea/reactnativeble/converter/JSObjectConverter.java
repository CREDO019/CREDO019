package com.polidea.reactnativeble.converter;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes3.dex */
abstract class JSObjectConverter<T> {
    public abstract WritableMap toJSObject(T t);

    public WritableArray toJSCallback(T t) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushNull();
        createArray.pushMap(toJSObject(t));
        return createArray;
    }
}
