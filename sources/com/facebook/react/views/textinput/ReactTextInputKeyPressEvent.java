package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public class ReactTextInputKeyPressEvent extends Event<ReactTextInputEvent> {
    public static final String EVENT_NAME = "topKeyPress";
    private String mKey;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public ReactTextInputKeyPressEvent(int r2, String str) {
        this(-1, r2, str);
    }

    ReactTextInputKeyPressEvent(int r1, int r2, String str) {
        super(r1, r2);
        this.mKey = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("key", this.mKey);
        return createMap;
    }
}
