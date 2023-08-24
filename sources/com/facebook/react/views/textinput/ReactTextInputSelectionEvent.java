package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
class ReactTextInputSelectionEvent extends Event<ReactTextInputSelectionEvent> {
    private static final String EVENT_NAME = "topSelectionChange";
    private int mSelectionEnd;
    private int mSelectionStart;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public ReactTextInputSelectionEvent(int r2, int r3, int r4) {
        this(-1, r2, r3, r4);
    }

    public ReactTextInputSelectionEvent(int r1, int r2, int r3, int r4) {
        super(r1, r2);
        this.mSelectionStart = r3;
        this.mSelectionEnd = r4;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("end", this.mSelectionEnd);
        createMap2.putInt("start", this.mSelectionStart);
        createMap.putMap(ReactTextInputShadowNode.PROP_SELECTION, createMap2);
        return createMap;
    }
}
