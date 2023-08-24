package com.facebook.react.bridge;

/* loaded from: classes.dex */
public interface WritableArray extends ReadableArray {
    void pushArray(ReadableArray readableArray);

    void pushBoolean(boolean z);

    void pushDouble(double d);

    void pushInt(int r1);

    void pushMap(ReadableMap readableMap);

    void pushNull();

    void pushString(String str);
}
