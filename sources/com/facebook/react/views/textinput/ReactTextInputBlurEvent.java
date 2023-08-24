package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;

/* loaded from: classes.dex */
class ReactTextInputBlurEvent extends Event<ReactTextInputBlurEvent> {
    private static final String EVENT_NAME = "topBlur";

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topBlur";
    }

    @Deprecated
    public ReactTextInputBlurEvent(int r2) {
        this(-1, r2);
    }

    public ReactTextInputBlurEvent(int r1, int r2) {
        super(r1, r2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap;
    }
}
