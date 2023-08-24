package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes.dex */
public interface RCTModernEventEmitter extends RCTEventEmitter {
    void receiveEvent(int r1, int r2, String str, WritableMap writableMap);

    void receiveEvent(int r1, int r2, String str, boolean z, int r5, WritableMap writableMap, int r7);

    void receiveTouches(TouchEvent touchEvent);
}
