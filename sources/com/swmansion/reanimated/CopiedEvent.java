package com.swmansion.reanimated;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* loaded from: classes3.dex */
public class CopiedEvent {
    private String eventName;
    private WritableMap payload;
    private int targetTag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CopiedEvent(Event event) {
        event.dispatch(new RCTEventEmitter() { // from class: com.swmansion.reanimated.CopiedEvent.1
            @Override // com.facebook.react.uimanager.events.RCTEventEmitter
            public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
            }

            @Override // com.facebook.react.uimanager.events.RCTEventEmitter
            public void receiveEvent(int r2, String str, WritableMap writableMap) {
                CopiedEvent.this.targetTag = r2;
                CopiedEvent.this.eventName = str;
                CopiedEvent.this.payload = writableMap.copy();
            }
        });
    }

    public int getTargetTag() {
        return this.targetTag;
    }

    public String getEventName() {
        return this.eventName;
    }

    public WritableMap getPayload() {
        return this.payload;
    }
}